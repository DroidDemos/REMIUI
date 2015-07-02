package com.google.android.finsky.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.FragmentActivity;
import com.android.vending.R;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.google.android.finsky.FinskyApp;
import com.google.android.finsky.protos.PlusProfile.PlusProfileResponse;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesClient.ConnectionCallbacks;
import com.google.android.gms.common.GooglePlayServicesClient.OnConnectionFailedListener;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.common.audience.dialogs.CircleSelection;
import com.google.android.gms.common.audience.dialogs.CircleSelection.UpdateBuilder;
import com.google.android.gms.common.oob.SignUp;
import com.google.android.gms.common.people.data.AudienceMember;
import com.google.android.gms.people.PeopleClient;
import com.google.android.gms.people.PeopleClient.LoadPeopleOptions;
import com.google.android.gms.people.PeopleClient.OnCirclesLoadedListener;
import com.google.android.gms.people.PeopleClient.OnPeopleLoadedListener;
import com.google.android.gms.people.model.Circle;
import com.google.android.gms.people.model.CircleBuffer;
import com.google.android.gms.people.model.PersonBuffer;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class GPlusUtils {
    private static boolean sIsCirclePickerActive;
    private static CirclePickerListener sLastCirclePickerListener;
    private static String sLastUserToAddObfuscatedId;

    public interface GetCirclesListener {
        void onCirclesLoaded(ArrayList<AudienceMember> arrayList);

        void onCirclesLoadedFailed();
    }

    public interface CirclePickerListener {
        void onCirclesSelected(ArrayList<AudienceMember> arrayList);
    }

    private static class CirclesLoader implements ConnectionCallbacks, OnConnectionFailedListener, OnCirclesLoadedListener, OnPeopleLoadedListener {
        private String[] mBelongingCircleIds;
        private ArrayList<AudienceMember> mCircles;
        private boolean mCirclesLoaded;
        private final String mCurrentAccountName;
        private final GetCirclesListener mGetCirclesListener;
        private final PeopleClient mPeopleClient;
        private boolean mPeopleLoaded;
        private final String mUserToLookUpGaiaObfId;

        public CirclesLoader(PeopleClient peopleClient, String currentAccountName, String userToLookUpGaiaObfId, GetCirclesListener circleStatusListener) {
            this.mPeopleClient = peopleClient;
            this.mCurrentAccountName = currentAccountName;
            this.mUserToLookUpGaiaObfId = userToLookUpGaiaObfId;
            this.mGetCirclesListener = circleStatusListener;
        }

        public void loadCircles() {
            this.mPeopleClient.registerConnectionCallbacks(this);
            this.mPeopleClient.registerConnectionFailedListener(this);
            if (!this.mPeopleClient.isConnected() && !this.mPeopleClient.isConnecting()) {
                this.mPeopleClient.connect();
            }
        }

        public void onConnected(Bundle arg) {
            loadData();
        }

        public void onDisconnected() {
            this.mPeopleClient.unregisterConnectionCallbacks(this);
            this.mPeopleClient.unregisterConnectionFailedListener(this);
        }

        public void onConnectionFailed(ConnectionResult connectionResult) {
            Utils.ensureOnMainThread();
            this.mPeopleClient.unregisterConnectionCallbacks(this);
            this.mPeopleClient.unregisterConnectionFailedListener(this);
            this.mGetCirclesListener.onCirclesLoadedFailed();
        }

        private void loadData() {
            this.mPeopleClient.loadCircles(this, this.mCurrentAccountName, null, null, -1, null, false);
            LoadPeopleOptions options = new LoadPeopleOptions();
            ArrayList<String> qualifiedIds = Lists.newArrayList();
            qualifiedIds.add(GPlusUtils.gaiaIdToPeopleQualifiedId(this.mUserToLookUpGaiaObfId));
            options.setQualifiedIds(qualifiedIds);
            this.mPeopleClient.loadPeople(this, this.mCurrentAccountName, null, options);
        }

        public void onCirclesLoaded(ConnectionResult connectionResult, CircleBuffer circleBuffer) {
            Utils.ensureOnMainThread();
            if (connectionResult.isSuccess()) {
                try {
                    this.mCircles = Lists.newArrayList();
                    Iterator i$ = circleBuffer.iterator();
                    while (i$.hasNext()) {
                        Circle circle = (Circle) i$.next();
                        this.mCircles.add(AudienceMember.forCircle(circle.getCircleId(), circle.getCircleName()));
                    }
                    this.mCirclesLoaded = true;
                    computeBelongingCircles();
                } finally {
                    circleBuffer.close();
                }
            } else {
                this.mGetCirclesListener.onCirclesLoadedFailed();
                this.mPeopleClient.unregisterConnectionCallbacks(this);
                this.mPeopleClient.unregisterConnectionFailedListener(this);
            }
        }

        public void onPeopleLoaded(ConnectionResult connectionResult, PersonBuffer personBuffer) {
            Utils.ensureOnMainThread();
            if (connectionResult.isSuccess()) {
                try {
                    if (personBuffer.getCount() > 0) {
                        this.mBelongingCircleIds = personBuffer.get(0).getBelongingCircleIds();
                    }
                    this.mPeopleLoaded = true;
                    computeBelongingCircles();
                } finally {
                    personBuffer.close();
                }
            } else {
                this.mGetCirclesListener.onCirclesLoadedFailed();
                this.mPeopleClient.unregisterConnectionCallbacks(this);
                this.mPeopleClient.unregisterConnectionFailedListener(this);
            }
        }

        private void computeBelongingCircles() {
            if (this.mCirclesLoaded && this.mPeopleLoaded) {
                ArrayList<AudienceMember> belongingCircles = Lists.newArrayList();
                if (this.mBelongingCircleIds != null) {
                    for (String belongingCircleId : this.mBelongingCircleIds) {
                        int size = this.mCircles.size();
                        for (int j = 0; j < size; j++) {
                            AudienceMember audienceMember = (AudienceMember) this.mCircles.get(j);
                            if (belongingCircleId.equals(audienceMember.getCircleId())) {
                                belongingCircles.add(audienceMember);
                            }
                        }
                    }
                }
                this.mPeopleClient.unregisterConnectionCallbacks(this);
                this.mPeopleClient.unregisterConnectionFailedListener(this);
                this.mGetCirclesListener.onCirclesLoaded(belongingCircles);
            }
        }
    }

    public static void launchGPlusSignUp(Activity activity) {
        if (checkGooglePlayServicesShowErrorDialogs(activity)) {
            activity.startActivityForResult(SignUp.newSignUpIntent(FinskyApp.get().getCurrentAccountName(), null, null, activity.getResources().getString(R.string.gplus_sign_up_text)), 35);
        }
    }

    public static void checkGPlusAndLaunchCirclePicker(final FragmentActivity activity, final String userToAddObfuscatedGaiaId, final ArrayList<AudienceMember> intialSelectedCircles, final CirclePickerListener circlePickerListner) {
        FinskyApp.get().getPlayDfeApi().getPlusProfile(new Listener<PlusProfileResponse>() {
            public void onResponse(PlusProfileResponse response) {
                if (!activity.isFinishing()) {
                    if (response.partialUserProfile == null) {
                        GPlusDialogsHelper.showGPlusSignUpDialog(activity.getSupportFragmentManager());
                    } else {
                        GPlusUtils.launchCirclePicker(activity, userToAddObfuscatedGaiaId, intialSelectedCircles, circlePickerListner);
                    }
                }
            }
        }, new ErrorListener() {
            public void onErrorResponse(VolleyError error) {
            }
        }, true);
    }

    public static void reattachToActiveCirclePickerIfMatches(String userToAddObfuscatedGaiaId, CirclePickerListener circlePickerListener) {
        if (sIsCirclePickerActive && userToAddObfuscatedGaiaId == sLastUserToAddObfuscatedId) {
            sLastCirclePickerListener = circlePickerListener;
        }
    }

    private static void launchCirclePicker(Activity activity, String userToAddObfuscatedGaiaId, ArrayList<AudienceMember> intialSelectedCircles, CirclePickerListener circlePickerListner) {
        if (checkGooglePlayServicesShowErrorDialogs(activity) && !sIsCirclePickerActive) {
            UpdateBuilder intentBuilder = CircleSelection.getUpdateCirclesBuilder();
            intentBuilder.setAccountName(FinskyApp.get().getCurrentAccountName());
            intentBuilder.setUpdatePersonId(gaiaIdToPeopleQualifiedId(userToAddObfuscatedGaiaId));
            intentBuilder.setInitialCircles(intialSelectedCircles);
            intentBuilder.setClientApplicationId(String.valueOf(121));
            activity.startActivityForResult(intentBuilder.build(), 39);
            sLastCirclePickerListener = circlePickerListner;
            sLastUserToAddObfuscatedId = userToAddObfuscatedGaiaId;
            sIsCirclePickerActive = true;
        }
    }

    public static void circlePickerOnActivityResult(int resultCode, Intent intent) {
        if (sLastCirclePickerListener != null) {
            if (resultCode == -1) {
                sLastCirclePickerListener.onCirclesSelected(CircleSelection.getSelectedCirclesFromResult(intent));
            }
            sLastCirclePickerListener = null;
            sLastUserToAddObfuscatedId = null;
            sIsCirclePickerActive = false;
        }
    }

    private static boolean checkGooglePlayServices(Context context) {
        return GooglePlayServicesUtil.isGooglePlayServicesAvailable(context) == 0;
    }

    private static boolean checkGooglePlayServicesShowErrorDialogs(Activity activity) {
        int result = GooglePlayServicesUtil.isGooglePlayServicesAvailable(activity);
        if (result == 0) {
            return true;
        }
        GooglePlayServicesUtil.getErrorDialog(result, activity, -1).show();
        return false;
    }

    public static void getCircles(Context context, PeopleClient peopleClient, String userToLookUpGaiaObfId, final GetCirclesListener getCirclesListener) {
        if (checkGooglePlayServices(context)) {
            new CirclesLoader(peopleClient, FinskyApp.get().getCurrentAccountName(), userToLookUpGaiaObfId, getCirclesListener).loadCircles();
        } else {
            new Handler().post(new Runnable() {
                public void run() {
                    getCirclesListener.onCirclesLoadedFailed();
                }
            });
        }
    }

    public static String getCirclesString(List<AudienceMember> circles, Resources resources) {
        if (circles == null || circles.size() == 0) {
            return resources.getString(R.string.circles_follow);
        }
        if (circles.size() == 1) {
            return ((AudienceMember) circles.get(0)).getDisplayName();
        }
        return resources.getQuantityString(R.plurals.circles_multiple, circles.size(), new Object[]{Integer.valueOf(circles.size())});
    }

    private static String gaiaIdToPeopleQualifiedId(String gaiaId) {
        return "g:" + gaiaId;
    }
}
