package com.google.android.gms.people;

import android.content.Context;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesClient;
import com.google.android.gms.common.GooglePlayServicesClient.ConnectionCallbacks;
import com.google.android.gms.common.GooglePlayServicesClient.OnConnectionFailedListener;
import com.google.android.gms.common.api.BaseImplementation.b;
import com.google.android.gms.internal.rn;
import com.google.android.gms.people.Graph.LoadCirclesResult;
import com.google.android.gms.people.Graph.LoadPeopleResult;
import com.google.android.gms.people.model.CircleBuffer;
import com.google.android.gms.people.model.PersonBuffer;
import java.util.Collection;

@Deprecated
public class PeopleClient implements GooglePlayServicesClient {
    private final rn aBI;

    public interface OnCirclesLoadedListener {
        void onCirclesLoaded(ConnectionResult connectionResult, CircleBuffer circleBuffer);
    }

    public interface OnPeopleLoadedListener {
        void onPeopleLoaded(ConnectionResult connectionResult, PersonBuffer personBuffer);
    }

    public static class LoadPeopleOptions {
        public static final LoadPeopleOptions DEFAULT;
        private String WL;
        private Collection<String> aBB;
        private long aBC;
        private boolean aBn;
        private int aBo;
        private int aBs;
        private int aBu;
        private String xM;

        static {
            DEFAULT = new LoadPeopleOptions();
        }

        public LoadPeopleOptions() {
            this.aBo = 2097151;
            this.aBs = 7;
            this.aBu = 0;
        }

        public LoadPeopleOptions clone() {
            return new LoadPeopleOptions().setCircleId(this.WL).setQualifiedIds(this.aBB).setProjection(this.aBo).setPeopleOnly(this.aBn).setChangedSince(this.aBC).setQuery(this.xM).setSearchFields(this.aBs).setSortOrder(this.aBu);
        }

        public long getChangedSince() {
            return this.aBC;
        }

        public String getCircleId() {
            return this.WL;
        }

        public int getProjection() {
            return this.aBo;
        }

        public Collection<String> getQualifiedIds() {
            return this.aBB;
        }

        public String getQuery() {
            return this.xM;
        }

        public int getSearchFields() {
            return this.aBs;
        }

        public int getSortOrder() {
            return this.aBu;
        }

        public boolean isPeopleOnly() {
            return this.aBn;
        }

        public LoadPeopleOptions setChangedSince(long changedSince) {
            this.aBC = changedSince;
            return this;
        }

        public LoadPeopleOptions setCircleId(String circleId) {
            this.WL = circleId;
            return this;
        }

        public LoadPeopleOptions setPeopleOnly(boolean peopleOnly) {
            this.aBn = peopleOnly;
            return this;
        }

        public LoadPeopleOptions setProjection(int projection) {
            this.aBo = projection;
            return this;
        }

        public LoadPeopleOptions setQualifiedIds(Collection<String> qualifiedIds) {
            this.aBB = qualifiedIds;
            return this;
        }

        public LoadPeopleOptions setQuery(String query) {
            this.xM = query;
            return this;
        }

        public LoadPeopleOptions setSearchFields(int searchFields) {
            this.aBs = searchFields;
            return this;
        }

        public LoadPeopleOptions setSortOrder(int sortOrder) {
            this.aBu = sortOrder;
            return this;
        }
    }

    public interface OnDataChangedListener {
        void onDataChanged(String str, String str2, int i);
    }

    private static class c implements b<LoadCirclesResult> {
        private final OnCirclesLoadedListener aBX;

        private c(OnCirclesLoadedListener onCirclesLoadedListener) {
            this.aBX = onCirclesLoadedListener;
        }

        public void a(LoadCirclesResult loadCirclesResult) {
            this.aBX.onCirclesLoaded(loadCirclesResult.getStatus().il(), loadCirclesResult.getCircles());
        }

        public /* synthetic */ void d(Object obj) {
            a((LoadCirclesResult) obj);
        }
    }

    public PeopleClient(Context context, ConnectionCallbacks connectedListener, OnConnectionFailedListener connectionFailedListener, int clientApplicationId) {
        this(context, connectedListener, connectionFailedListener, clientApplicationId, null);
    }

    public PeopleClient(Context context, ConnectionCallbacks connectedListener, OnConnectionFailedListener connectionFailedListener, int clientApplicationId, String realClientPackage) {
        this(new rn(context, connectedListener, connectionFailedListener, String.valueOf(clientApplicationId), realClientPackage));
    }

    PeopleClient(rn impl) {
        this.aBI = impl;
    }

    public void connect() {
        this.aBI.connect();
    }

    public void disconnect() {
        this.aBI.disconnect();
    }

    public boolean isConnected() {
        return this.aBI.isConnected();
    }

    public boolean isConnecting() {
        return this.aBI.isConnecting();
    }

    public void loadCircles(OnCirclesLoadedListener listener, String account, String pageId, String circleId, int circleType, String circleNamePrefix, boolean getVisibility) {
        this.aBI.a(new c(listener), account, pageId, circleId, circleType, circleNamePrefix, getVisibility);
    }

    public void loadPeople(final OnPeopleLoadedListener listener, String account, String pageId, LoadPeopleOptions options) {
        this.aBI.a(new b<LoadPeopleResult>(this) {
            final /* synthetic */ PeopleClient aBK;

            public void a(LoadPeopleResult loadPeopleResult) {
                listener.onPeopleLoaded(loadPeopleResult.getStatus().il(), loadPeopleResult.getPeople());
            }

            public /* synthetic */ void d(Object obj) {
                a((LoadPeopleResult) obj);
            }
        }, account, pageId, options);
    }

    public void registerConnectionCallbacks(ConnectionCallbacks listener) {
        this.aBI.registerConnectionCallbacks(listener);
    }

    public void registerConnectionFailedListener(OnConnectionFailedListener listener) {
        this.aBI.registerConnectionFailedListener(listener);
    }

    public void unregisterConnectionCallbacks(ConnectionCallbacks listener) {
        this.aBI.unregisterConnectionCallbacks(listener);
    }

    public void unregisterConnectionFailedListener(OnConnectionFailedListener listener) {
        this.aBI.unregisterConnectionFailedListener(listener);
    }
}
