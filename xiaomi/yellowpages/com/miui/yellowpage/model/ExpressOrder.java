package com.miui.yellowpage.model;

import android.text.TextUtils;
import com.alipay.sdk.cons.GlobalDefine;
import com.alipay.sdk.cons.MiniDefine;
import com.miui.yellowpage.R;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import org.json.JSONException;
import org.json.JSONObject;

public class ExpressOrder implements Serializable {
    private ExpressAddress mAddressee;
    private Cargo mCargo;
    private ExpressAddress mSender;

    public final class Cargo implements Serializable {
        private static final SimpleDateFormat Ms;
        private String mCompanyCode;
        private String mCompanyName;
        private long mCreatedTime;
        private String mInternalId;
        private String mMemo;
        private String mName;
        private String mPresentationNumber;
        private String mServicePhone;
        private State mState;
        private String mStateNote;
        private String mTrackingNumber;
        private long mUrgeTime;

        static {
            Ms = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.US);
        }

        public String iM() {
            return Ms.format(new Date(this.mCreatedTime));
        }

        public String iN() {
            return this.mServicePhone;
        }

        public long iO() {
            return this.mCreatedTime;
        }

        public String iP() {
            return this.mTrackingNumber;
        }

        public String iQ() {
            return this.mPresentationNumber;
        }

        public String ed() {
            return this.mCompanyCode;
        }

        public String ee() {
            return this.mCompanyName;
        }

        public State iR() {
            if (this.mState == null) {
                return State.UNKNOWN;
            }
            return this.mState;
        }

        public String iS() {
            return this.mStateNote;
        }

        public String E() {
            return this.mInternalId;
        }

        public String getName() {
            return this.mName;
        }

        public String iT() {
            return this.mMemo;
        }

        public void cu(String str) {
            this.mCompanyName = str;
        }

        public void cv(String str) {
            this.mCompanyCode = str;
        }

        public long iU() {
            return this.mUrgeTime;
        }

        public Cargo(String str, String str2, String str3, State state, String str4, String str5, String str6, String str7, long j, String str8, String str9, long j2) {
            this.mTrackingNumber = str;
            this.mCompanyCode = str2;
            this.mCompanyName = str3;
            this.mState = state;
            this.mStateNote = str4;
            this.mInternalId = str5;
            this.mName = str6;
            this.mMemo = str7;
            this.mCreatedTime = j;
            this.mServicePhone = str8;
            this.mPresentationNumber = str9;
            this.mUrgeTime = j2;
        }

        public static Cargo n(JSONObject jSONObject) {
            String str = null;
            try {
                String str2;
                String str3;
                String str4;
                String str5;
                String str6;
                String str7;
                String str8;
                String string = jSONObject.getString("internalId");
                long optLong = jSONObject.optLong("createTime") * 1000;
                long optLong2 = jSONObject.optLong("urgeTime") * 1000;
                State Q = State.Q(jSONObject.optInt("state"));
                if (jSONObject.isNull("expressNumber")) {
                    str2 = null;
                } else {
                    str2 = jSONObject.optString("expressNumber");
                }
                if (jSONObject.isNull("companyCode")) {
                    str3 = null;
                } else {
                    str3 = jSONObject.optString("companyCode");
                }
                if (jSONObject.isNull("companyName")) {
                    str4 = null;
                } else {
                    str4 = jSONObject.optString("companyName");
                }
                if (jSONObject.isNull("stateNote")) {
                    str5 = null;
                } else {
                    str5 = jSONObject.optString("stateNote");
                }
                if (jSONObject.isNull("description")) {
                    str6 = null;
                } else {
                    str6 = jSONObject.optString("description");
                }
                if (jSONObject.isNull(GlobalDefine.h)) {
                    str7 = null;
                } else {
                    str7 = jSONObject.optString(GlobalDefine.h);
                }
                if (jSONObject.isNull("servicePhone")) {
                    str8 = null;
                } else {
                    str8 = jSONObject.optString("servicePhone");
                }
                if (!jSONObject.isNull("orderId")) {
                    str = jSONObject.optString("orderId");
                }
                return new Cargo(str2, str3, str4, Q, str5, string, str6, str7, optLong, str8, str, optLong2);
            } catch (JSONException e) {
                e.printStackTrace();
                return new Cargo();
            }
        }
    }

    public enum State {
        ORDERED(R.string.express_order_state_ordered),
        PROCESSING(R.string.express_order_state_processing),
        ACCEPTED(R.string.express_order_state_accepted),
        ORDER_FAILURE(R.string.express_order_state_failed),
        ORDER_CANCELLED(R.string.express_order_state_cancelled),
        EXPRESS_WAITING_PICK(R.string.express_order_state_wait_picking),
        EXPRESS_PICK_FAILURE(R.string.express_order_state_picking_failed),
        EXPRESS_PICKED(R.string.express_order_state_picked),
        EXPRESS_TRANSPORT(R.string.express_order_state_transporting),
        EXPRESS_DISPATCH(R.string.express_order_state_dispatching),
        EXPRESS_SIGNED(R.string.express_order_state_signed),
        EXPRESS_ALLOGRAPH(R.string.express_order_state_allograph),
        EXPRESS_DISPATCH_FAILURE(R.string.express_order_state_dispatching_failed),
        UNKNOWN(R.string.express_order_state_unknown);
        
        private int mRes;

        private State(int i) {
            this.mRes = i;
        }

        public boolean gD() {
            return this == ORDERED || this == PROCESSING;
        }

        public boolean isFailed() {
            return this == ORDER_CANCELLED || this == ORDER_FAILURE || this == EXPRESS_PICK_FAILURE || this == EXPRESS_DISPATCH_FAILURE;
        }

        public boolean isFinished() {
            return this == EXPRESS_SIGNED || this == EXPRESS_ALLOGRAPH;
        }

        public int gE() {
            return this.mRes;
        }

        public static State Q(int i) {
            if (i < 0 || i >= values().length) {
                return UNKNOWN;
            }
            return values()[i];
        }
    }

    public ExpressOrder() {
        this.mCargo = new Cargo();
        this.mSender = new ExpressAddress();
        this.mAddressee = new ExpressAddress();
    }

    public ExpressOrder(Cargo cargo, ExpressAddress expressAddress, ExpressAddress expressAddress2) {
        this.mCargo = cargo;
        this.mSender = expressAddress2;
        this.mAddressee = expressAddress;
    }

    public void c(ExpressAddress expressAddress) {
        if (expressAddress != null) {
            this.mAddressee = expressAddress;
        }
    }

    public void d(ExpressAddress expressAddress) {
        if (expressAddress != null) {
            this.mSender = expressAddress;
        }
    }

    public Cargo as() {
        return this.mCargo;
    }

    public ExpressAddress at() {
        return this.mAddressee;
    }

    public ExpressAddress au() {
        return this.mSender;
    }

    public static ExpressOrder c(JSONObject jSONObject) {
        Cargo cargo = new Cargo();
        ExpressAddress expressAddress = new ExpressAddress();
        ExpressAddress expressAddress2 = new ExpressAddress();
        try {
            Cargo n;
            if (jSONObject.has("cargo")) {
                n = Cargo.n(jSONObject.getJSONObject("cargo"));
            } else {
                n = cargo;
            }
            if (jSONObject.has("receiver")) {
                a(expressAddress, jSONObject.getJSONObject("receiver"));
            }
            if (jSONObject.has("sender")) {
                a(expressAddress2, jSONObject.getJSONObject("sender"));
            }
            return new ExpressOrder(n, expressAddress, expressAddress2);
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    private static void a(ExpressAddress expressAddress, JSONObject jSONObject) {
        if (!jSONObject.isNull(MiniDefine.l)) {
            expressAddress.setName(jSONObject.optString(MiniDefine.l));
        }
        if (!jSONObject.isNull("phone")) {
            expressAddress.setPhone(jSONObject.optString("phone"));
        }
        if (!jSONObject.isNull("adminArea")) {
            expressAddress.setAdminArea(jSONObject.optString("adminArea"));
        }
        if (!jSONObject.isNull("subAdminArea")) {
            expressAddress.setSubAdminArea(jSONObject.optString("subAdminArea"));
        }
        if (!jSONObject.isNull("locality")) {
            expressAddress.setLocality(jSONObject.optString("locality"));
        }
        if (!jSONObject.isNull("subLocality")) {
            expressAddress.setSubLocality(jSONObject.optString("subLocality"));
        }
        if (!jSONObject.isNull("address")) {
            expressAddress.be(jSONObject.optString("address"));
        }
        if (!jSONObject.isNull("postalCode")) {
            expressAddress.setPostalCode(jSONObject.optString("postalCode"));
        }
    }

    public static boolean e(ExpressAddress expressAddress) {
        return expressAddress == null || (TextUtils.isEmpty(expressAddress.getName()) && TextUtils.isEmpty(expressAddress.getPhone()) && TextUtils.isEmpty(expressAddress.getAdminArea()) && TextUtils.isEmpty(expressAddress.getSubAdminArea()) && TextUtils.isEmpty(expressAddress.getLocality()) && TextUtils.isEmpty(expressAddress.getSubAdminArea()) && TextUtils.isEmpty(expressAddress.eQ()));
    }

    private void writeObject(ObjectOutputStream objectOutputStream) {
        objectOutputStream.writeObject(this.mSender.eW().toString());
        objectOutputStream.writeObject(this.mAddressee.eW().toString());
        objectOutputStream.writeObject(this.mCargo);
    }

    private void readObject(ObjectInputStream objectInputStream) {
        this.mSender = C((String) objectInputStream.readObject());
        this.mAddressee = C((String) objectInputStream.readObject());
        this.mCargo = (Cargo) objectInputStream.readObject();
    }

    private ExpressAddress C(String str) {
        ExpressAddress j;
        try {
            j = ExpressAddress.j(new JSONObject(str));
        } catch (JSONException e) {
            e.printStackTrace();
            j = null;
        }
        if (j == null) {
            return new ExpressAddress();
        }
        return j;
    }
}
