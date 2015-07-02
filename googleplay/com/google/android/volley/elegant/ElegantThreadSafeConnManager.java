package com.google.android.volley.elegant;

import com.android.volley.VolleyLog;
import java.io.IOException;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import org.apache.http.conn.ClientConnectionOperator;
import org.apache.http.conn.ClientConnectionRequest;
import org.apache.http.conn.ConnectionPoolTimeoutException;
import org.apache.http.conn.ManagedClientConnection;
import org.apache.http.conn.routing.HttpRoute;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.impl.conn.AbstractPoolEntry;
import org.apache.http.impl.conn.tsccm.AbstractConnPool;
import org.apache.http.impl.conn.tsccm.BasicPoolEntry;
import org.apache.http.impl.conn.tsccm.BasicPooledConnAdapter;
import org.apache.http.impl.conn.tsccm.ConnPoolByRoute;
import org.apache.http.impl.conn.tsccm.PoolEntryRequest;
import org.apache.http.impl.conn.tsccm.RouteSpecificPool;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.impl.conn.tsccm.WaitingThread;
import org.apache.http.impl.conn.tsccm.WaitingThreadAborter;
import org.apache.http.params.HttpParams;

public class ElegantThreadSafeConnManager extends ThreadSafeClientConnManager {

    public static class ElegantBasicPooledConnAdapter extends BasicPooledConnAdapter {
        public final long startTime;

        protected ElegantBasicPooledConnAdapter(ThreadSafeClientConnManager tsccm, AbstractPoolEntry entry) {
            super(tsccm, entry);
            this.startTime = System.currentTimeMillis();
        }
    }

    public static class ElegantPool extends ConnPoolByRoute {
        public ElegantPool(ClientConnectionOperator operator, HttpParams params) {
            super(operator, params);
        }

        public PoolEntryRequest requestPoolEntry(final HttpRoute route, final Object state) {
            final WaitingThreadAborter aborter = new WaitingThreadAborter();
            return new PoolEntryRequest() {
                public void abortRequest() {
                    ElegantPool.this.poolLock.lock();
                    try {
                        aborter.abort();
                    } finally {
                        ElegantPool.this.poolLock.unlock();
                    }
                }

                public BasicPoolEntry getPoolEntry(long timeout, TimeUnit tunit) throws InterruptedException, ConnectionPoolTimeoutException {
                    return ElegantPool.this.getEntryBlocking(route, state, timeout, tunit, aborter);
                }
            };
        }

        protected BasicPoolEntry getEntryBlocking(HttpRoute route, Object state, long timeout, TimeUnit tunit, WaitingThreadAborter aborter) throws ConnectionPoolTimeoutException, InterruptedException {
            Date deadline = null;
            if (timeout > 0) {
                deadline = new Date(System.currentTimeMillis() + tunit.toMillis(timeout));
            }
            BasicPoolEntry entry = null;
            long startTime = System.currentTimeMillis();
            this.poolLock.lock();
            RouteSpecificPool rospl = getRoutePool(route, true);
            WaitingThread waitingThread = null;
            while (entry == null) {
                if (this.isShutDown) {
                    throw new IllegalStateException("Connection pool shut down.");
                }
                entry = getFreeEntry(rospl, state);
                if (entry != null) {
                    break;
                }
                VolleyLog.v("Constructed new connection to route=[%s]", route);
                boolean hasCapacity = rospl.getCapacity() > 0;
                if (hasCapacity && this.numConnections < this.maxTotalConnections) {
                    entry = createEntry(rospl, this.operator);
                } else if (!hasCapacity || this.freeConnections.isEmpty()) {
                    if (waitingThread == null) {
                        waitingThread = newWaitingThread(this.poolLock.newCondition(), rospl);
                        aborter.setWaitingThread(waitingThread);
                    }
                    try {
                        rospl.queueThread(waitingThread);
                        this.waitingThreads.add(waitingThread);
                        boolean success = waitingThread.await(deadline);
                        rospl.removeThread(waitingThread);
                        this.waitingThreads.remove(waitingThread);
                        if (!(success || deadline == null || deadline.getTime() > System.currentTimeMillis())) {
                            throw new ConnectionPoolTimeoutException("Timeout waiting for connection");
                        }
                    } catch (Throwable th) {
                        this.poolLock.unlock();
                        if (System.currentTimeMillis() - startTime > 10) {
                            VolleyLog.v("GetEntryBlocking() took %s ms", Long.valueOf(System.currentTimeMillis() - startTime));
                        }
                    }
                } else {
                    deleteLeastUsedEntry();
                    entry = createEntry(rospl, this.operator);
                }
            }
            this.poolLock.unlock();
            if (System.currentTimeMillis() - startTime > 10) {
                VolleyLog.v("GetEntryBlocking() took %s ms", Long.valueOf(System.currentTimeMillis() - startTime));
            }
            return entry;
        }
    }

    public ElegantThreadSafeConnManager(HttpParams params, SchemeRegistry schreg) {
        super(params, schreg);
    }

    protected AbstractConnPool createConnectionPool(HttpParams params) {
        AbstractConnPool acp = new ElegantPool(this.connOperator, params);
        if (true) {
            acp.enableConnectionGC();
        }
        return acp;
    }

    public ClientConnectionRequest requestConnection(final HttpRoute route, Object state) {
        final PoolEntryRequest poolRequest = this.connectionPool.requestPoolEntry(route, state);
        return new ClientConnectionRequest() {
            public void abortRequest() {
                poolRequest.abortRequest();
            }

            public ManagedClientConnection getConnection(long timeout, TimeUnit tunit) throws InterruptedException, ConnectionPoolTimeoutException {
                if (route == null) {
                    throw new IllegalArgumentException("Route may not be null.");
                }
                return new ElegantBasicPooledConnAdapter(ElegantThreadSafeConnManager.this, poolRequest.getPoolEntry(timeout, tunit));
            }
        };
    }

    public void releaseConnection(ManagedClientConnection conn, long validDuration, TimeUnit timeUnit) {
        if ((conn instanceof ElegantBasicPooledConnAdapter) && conn.getRoute() != null) {
            if (System.currentTimeMillis() - ((ElegantBasicPooledConnAdapter) conn).startTime > (conn.getRoute().isSecure() ? 5000 : 2500)) {
                try {
                    conn.close();
                } catch (IOException e) {
                }
            }
        }
        super.releaseConnection(conn, validDuration, timeUnit);
    }
}
