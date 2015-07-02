package com.miui.yellowpage.a;

import android.os.Handler;
import com.miui.yellowpage.base.exception.NetworkUnavailableException;
import com.miui.yellowpage.base.exception.ServerException;
import com.miui.yellowpage.base.request.BaseRequest;
import com.miui.yellowpage.base.request.HttpRequest;
import com.miui.yellowpage.base.utils.ThreadPool;
import com.miui.yellowpage.request.BaseResult;
import com.miui.yellowpage.request.BaseResult.State;
import java.net.UnknownServiceException;
import org.json.JSONException;

/* compiled from: RequestLoader */
class f implements Runnable {
    private String mDescription;
    private Handler mHandler;
    private BaseResult rA;
    final /* synthetic */ d rB;
    private BaseRequest rz;

    private f(d dVar) {
        this.rB = dVar;
    }

    protected void onPreExecute() {
        if (this.rB.it != null) {
            this.rB.it.onPreRequest(this.rz.getRequestCode());
        }
        if (this.rB.is != null) {
            this.rB.is.a(true, true, null);
        }
    }

    protected void dA() {
        State state = this.rA.getState();
        if (state != State.OK) {
            if (this.rB.is != null) {
                this.rB.is.a(true, state, this.mDescription);
            }
        } else if (this.rB.is != null) {
            this.rB.is.onStopLoading(true);
        }
        if (this.rB.it != null) {
            this.rB.it.onRequestFinished(this.rz.getRequestCode(), this.rA);
        }
    }

    public void b(BaseRequest baseRequest, BaseResult baseResult) {
        this.rA = baseResult;
        this.rz = baseRequest;
        this.mHandler = new Handler(new j(this));
        onPreExecute();
        ThreadPool.execute(this);
    }

    public void run() {
        try {
            Object requestServer;
            if (this.rz instanceof HttpRequest) {
                requestServer = ((HttpRequest) this.rz).requestServer();
            } else {
                requestServer = this.rz.request();
            }
            if (this.rB.it != null) {
                this.rA = this.rB.it.onParseRequest(this.rz.getRequestCode(), requestServer, this.rA);
            }
        } catch (Exception e) {
            State state;
            e.printStackTrace();
            State state2 = State.SERVICE_ERROR;
            if (e instanceof JSONException) {
                state = State.DATA_ERROR;
            } else if (e instanceof UnknownServiceException) {
                state = State.SERVICE_ERROR;
            } else if (e instanceof NetworkUnavailableException) {
                state = State.NETWORK_ERROR;
            } else if (e instanceof ServerException) {
                state = State.SERVICE_ERROR;
            } else {
                state = State.SERVICE_ERROR;
            }
            this.mDescription = this.rz.getDescription();
            this.rA.setState(state);
        }
        this.mHandler.sendEmptyMessage(0);
    }
}
