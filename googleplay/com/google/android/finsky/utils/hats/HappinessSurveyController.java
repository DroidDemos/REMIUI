package com.google.android.finsky.utils.hats;

import android.animation.Animator;
import android.animation.Animator.AnimatorListener;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.os.AsyncTask;
import android.os.Build.VERSION;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import android.widget.TextView;
import android.widget.Toast;
import com.android.vending.R;
import com.google.android.finsky.FinskyApp;
import com.google.android.finsky.fragments.PageFragment;
import com.google.android.finsky.utils.FinskyLog;
import com.google.android.finsky.utils.Utils;
import com.google.android.libraries.happiness.HatsSurveyClient;
import com.google.android.libraries.happiness.HatsSurveyManager;
import com.google.android.libraries.happiness.HatsSurveyParams;
import java.io.IOException;
import java.util.List;
import org.apache.http.client.CookieStore;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;

public class HappinessSurveyController implements HatsSurveyClient {
    private static boolean mHasRequestedCookie;
    private static String mZwiebackCookie;
    private final ViewGroup mContainerView;
    private final Activity mFragmentActivity;
    private final FragmentManager mFragmentManager;
    public final String mSiteId;
    private boolean mSkipPrompt;
    private HatsSurveyManager mSurveyManager;

    private class LoadZwiebackTask extends AsyncTask<Void, Void, String> {
        private LoadZwiebackTask() {
        }

        protected String doInBackground(Void... params) {
            int i;
            CookieSyncManager.createInstance(FinskyApp.get());
            String cookie = CookieManager.getInstance().getCookie("https://www.google.com");
            if (cookie != null) {
                String[] rawCookieStrings = cookie.split(";");
                for (String startsWith : rawCookieStrings) {
                    if (startsWith.startsWith("NID=")) {
                        return cookie;
                    }
                }
            }
            CookieStore cookieStore = new BasicCookieStore();
            DefaultHttpClient httpClient = new DefaultHttpClient();
            HttpContext httpContext = new BasicHttpContext();
            httpContext.setAttribute("http.cookie-store", cookieStore);
            try {
                httpClient.execute(new HttpGet("https://www.google.com"), httpContext);
                List<Cookie> cookies = cookieStore.getCookies();
                int j = cookies.size();
                for (i = 0; i < j; i++) {
                    if ("NID".equals(((Cookie) cookies.get(i)).getName())) {
                        return ((Cookie) cookies.get(i)).getValue();
                    }
                }
            } catch (IOException e) {
                FinskyLog.w("Exception making HaTS Zwieback cookie HTTP request: %s", e.getMessage());
            }
            return null;
        }

        protected void onPostExecute(String result) {
            if (!TextUtils.isEmpty(result)) {
                HappinessSurveyController.mZwiebackCookie = result;
                HappinessSurveyController.this.start();
            }
        }
    }

    static {
        mHasRequestedCookie = false;
    }

    public HappinessSurveyController(PageFragment originatingFragment, String siteId, boolean skipPrompt) {
        this.mSkipPrompt = false;
        this.mFragmentActivity = originatingFragment.getActivity();
        this.mFragmentManager = originatingFragment.getFragmentManager();
        this.mContainerView = (ViewGroup) originatingFragment.getActivity().findViewById(R.id.outer_content_frame);
        this.mSiteId = siteId;
        this.mSkipPrompt = skipPrompt;
    }

    public void start() {
        if (mZwiebackCookie == null) {
            if (!mHasRequestedCookie) {
                Utils.executeMultiThreaded(new LoadZwiebackTask(), new Void[0]);
                mHasRequestedCookie = true;
            }
        } else if (this.mSurveyManager == null && this.mFragmentActivity != null) {
            this.mSurveyManager = new HatsSurveyManager(this.mFragmentActivity, this, new HatsSurveyParams(mZwiebackCookie, this.mSiteId), R.layout.survey_dialog, R.id.survey_container);
            this.mSurveyManager.requestSurvey();
        }
    }

    public String getSiteId() {
        return this.mSiteId;
    }

    private void showSurveyDialog() {
        Fragment dialogFragment = this.mFragmentManager.findFragmentByTag("hats-survey");
        if (dialogFragment != null) {
            this.mFragmentManager.beginTransaction().remove(dialogFragment).commit();
        }
        this.mFragmentManager.beginTransaction().add(this.mSurveyManager.getSurveyDialog(), "hats-survey").commit();
        this.mFragmentActivity.setRequestedOrientation(5);
    }

    private void showSurveyPrompt() {
        if (this.mFragmentActivity != null && this.mSurveyManager != null && this.mContainerView != null && this.mContainerView.findViewById(R.id.survey_prompt) == null) {
            View promptView = LayoutInflater.from(this.mFragmentActivity).inflate(R.layout.survey_prompt, this.mContainerView, false);
            ((TextView) promptView.findViewById(R.id.title)).setText(R.string.survey_prompt_title_improve);
            ((TextView) promptView.findViewById(R.id.subtitle)).setText(R.string.survey_prompt_subtitle_survey);
            promptView.findViewById(R.id.take_survey).setOnClickListener(new OnClickListener() {
                public void onClick(View v) {
                    HappinessSurveyController.this.showSurveyDialog();
                }
            });
            promptView.findViewById(R.id.decline_survey_button).setOnClickListener(new OnClickListener() {
                public void onClick(View v) {
                    HappinessSurveyController.this.removeSurveyPrompt();
                }
            });
            this.mContainerView.addView(promptView);
            if (VERSION.SDK_INT >= 11) {
                promptView.measure(MeasureSpec.makeMeasureSpec(this.mContainerView.getWidth(), 1073741824), MeasureSpec.makeMeasureSpec(this.mContainerView.getHeight(), Integer.MIN_VALUE));
                promptView.setTranslationY((float) promptView.getMeasuredHeight());
                ObjectAnimator.ofFloat(promptView, "translationY", new float[]{0.0f}).start();
            }
        }
    }

    private void removeSurveyPrompt() {
        if (this.mContainerView != null) {
            final View surveyPrompt = this.mContainerView.findViewById(R.id.survey_prompt);
            if (surveyPrompt != null) {
                if (VERSION.SDK_INT >= 11) {
                    ObjectAnimator animator = ObjectAnimator.ofFloat(surveyPrompt, "translationY", new float[]{(float) surveyPrompt.getHeight()});
                    animator.addListener(new AnimatorListener() {
                        public void onAnimationStart(Animator animation) {
                        }

                        public void onAnimationEnd(Animator animation) {
                            HappinessSurveyController.this.mContainerView.removeView(surveyPrompt);
                        }

                        public void onAnimationCancel(Animator animation) {
                            HappinessSurveyController.this.mContainerView.removeView(surveyPrompt);
                        }

                        public void onAnimationRepeat(Animator animation) {
                        }
                    });
                    animator.start();
                } else {
                    this.mContainerView.removeView(surveyPrompt);
                }
                if (this.mFragmentActivity != null) {
                    HatsUtils.persistSurveyTimestampAction(this.mSiteId);
                }
            }
        }
    }

    private void dismissSurvey() {
        DialogFragment dialog = this.mSurveyManager != null ? this.mSurveyManager.getSurveyDialog() : null;
        if (!(dialog == null || dialog.getFragmentManager() == null)) {
            dialog.dismiss();
        }
        this.mFragmentActivity.setRequestedOrientation(4);
        removeSurveyPrompt();
    }

    public void onWindowError() {
    }

    public void onSurveyReady() {
        if (this.mSkipPrompt) {
            showSurveyDialog();
        } else {
            showSurveyPrompt();
        }
    }

    public void onSurveyComplete(boolean justAnswered, boolean unused) {
        dismissSurvey();
        if (justAnswered) {
            Activity activity = this.mFragmentActivity;
            if (activity != null) {
                Toast.makeText(activity, R.string.survey_completed_toast, 0).show();
            }
        }
    }

    public void onSurveyResponse(String response, String surveyId) {
    }

    public void onSurveyCanceled() {
        dismissSurvey();
    }
}
