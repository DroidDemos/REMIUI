package com.google.android.finsky.activities;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.TextView;
import android.widget.Toast;
import com.android.vending.R;
import com.android.volley.Response;
import com.android.volley.Response.ErrorListener;
import com.android.volley.VolleyError;
import com.google.android.finsky.FinskyApp;
import com.google.android.finsky.activities.FlagItemUserMessageDialog.Listener;
import com.google.android.finsky.analytics.FinskyEventLog;
import com.google.android.finsky.analytics.PlayStore.PlayStoreUiElement;
import com.google.android.finsky.api.model.DfeDetails;
import com.google.android.finsky.api.model.Document;
import com.google.android.finsky.api.model.OnDataChangedListener;
import com.google.android.finsky.config.G;
import com.google.android.finsky.fragments.UrlBasedPageFragment;
import com.google.android.finsky.layout.ButtonBar;
import com.google.android.finsky.layout.ButtonBar.ClickListener;
import com.google.android.finsky.local.AssetUtils;
import com.google.android.finsky.protos.ContentFlagging.FlagContentResponse;
import com.google.android.finsky.protos.VendingProtos.ModifyCommentResponseProto;
import com.google.android.finsky.utils.CorpusResourceUtils;
import com.google.android.finsky.utils.ErrorStrings;
import com.google.android.finsky.utils.FinskyLog;
import com.google.android.finsky.utils.Lists;
import java.util.List;

public class FlagItemFragment extends UrlBasedPageFragment implements Listener, ClickListener {
    private ViewGroup mDetailsPanel;
    private DfeDetails mDfeDetails;
    private Document mDoc;
    private String mFlagMessage;
    private RadioGroup mFlagRadioButtons;
    private int mSelectedRadioButtonId;
    private DetailsSummaryViewBinder mSummaryViewBinder;
    private PlayStoreUiElement mUiElementProto;

    public static abstract class FlagType {
        public final int stringId;
        public final int textEntryStringId;

        public abstract void postFlag(Context context, Document document, String str);

        protected FlagType(int stringId, int textEntryStringId) {
            this.stringId = stringId;
            this.textEntryStringId = textEntryStringId;
        }

        public boolean requireUserComment() {
            return this.textEntryStringId != -1;
        }
    }

    public static class AppFlagType extends FlagType {
        private final int mRpcId;

        public static List<FlagType> getAppFlags(boolean showOwnershipFlags) {
            boolean hideContent = ((Boolean) G.vendingHideContentRating.get()).booleanValue();
            List<FlagType> output = Lists.newArrayList();
            output.add(new AppFlagType(1, R.string.flag_sexual_content, -1));
            output.add(new AppFlagType(2, R.string.flag_graphic_violence, -1));
            output.add(new AppFlagType(3, R.string.flag_hateful_content, -1));
            if (showOwnershipFlags) {
                output.add(new AppFlagType(4, R.string.flag_harmful_to_device, R.string.flag_harmful_prompt));
            }
            if (!hideContent) {
                output.add(new AppFlagType(6, R.string.flag_improper_content_rating, -1));
            }
            output.add(new AppFlagType(5, R.string.flag_other_objection, R.string.flag_other_concern_prompt));
            return output;
        }

        private AppFlagType(int rpcId, int stringId, int textEntryStringId) {
            super(stringId, textEntryStringId);
            this.mRpcId = rpcId;
        }

        public void postFlag(final Context context, Document doc, String flagMessage) {
            FinskyApp.get().getVendingApi().flagAsset(AssetUtils.makeAssetId(doc.getAppDetails()), this.mRpcId, flagMessage, new Response.Listener<ModifyCommentResponseProto>() {
                public void onResponse(ModifyCommentResponseProto response) {
                    Toast.makeText(context, R.string.content_flagged, 1).show();
                }
            }, new ErrorListener() {
                public void onErrorResponse(VolleyError error) {
                }
            });
        }
    }

    public static class MusicFlagType extends FlagType {
        private final int mContentFlagType;

        public static List<FlagType> getMusicFlags() {
            List<FlagType> output = Lists.newArrayList();
            output.add(new MusicFlagType(5, R.string.flag_incorrect_metadata, R.string.flag_other_concern_prompt));
            output.add(new MusicFlagType(1, R.string.flag_sexual_content, R.string.flag_other_concern_prompt));
            output.add(new MusicFlagType(4, R.string.flag_hateful_content, R.string.flag_other_concern_prompt));
            output.add(new MusicFlagType(6, R.string.flag_spam, R.string.flag_other_concern_prompt));
            output.add(new MusicFlagType(2, R.string.flag_minor_abuse, R.string.flag_other_concern_prompt));
            output.add(new MusicFlagType(8, R.string.flag_other_objection, R.string.flag_other_concern_prompt));
            return output;
        }

        private MusicFlagType(int contentFlagType, int stringId, int textEntryStringId) {
            super(stringId, textEntryStringId);
            this.mContentFlagType = contentFlagType;
        }

        public void postFlag(final Context context, Document doc, String flagMessage) {
            FinskyApp.get().getDfeApi().flagContent(doc.getDocId(), this.mContentFlagType, flagMessage, new Response.Listener<FlagContentResponse>() {
                public void onResponse(FlagContentResponse response) {
                    Toast.makeText(context, R.string.content_flagged, 1).show();
                }
            }, new ErrorListener() {
                public void onErrorResponse(VolleyError error) {
                }
            });
        }
    }

    public FlagItemFragment() {
        this.mUiElementProto = FinskyEventLog.obtainPlayStoreUiElement(301);
    }

    public static FlagItemFragment newInstance(String url) {
        FlagItemFragment fragment = new FlagItemFragment();
        fragment.setDfeTocAndUrl(FinskyApp.get().getToc(), url);
        return fragment;
    }

    public void onDestroyView() {
        if (this.mSummaryViewBinder != null) {
            this.mSummaryViewBinder.onDestroyView();
        }
        super.onDestroyView();
    }

    protected void onInitViewBinders() {
        View view = getView();
        this.mDetailsPanel = (ViewGroup) view.findViewById(R.id.item_details_panel);
        this.mFlagRadioButtons = (RadioGroup) view.findViewById(R.id.flag_item_list);
        final ButtonBar buttonBar = (ButtonBar) view.findViewById(R.id.button_bar);
        buttonBar.setPositiveButtonTitle((int) R.string.submit);
        buttonBar.setPositiveButtonEnabled(false);
        buttonBar.setClickListener(this);
        this.mFlagRadioButtons.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                buttonBar.setPositiveButtonEnabled(true);
            }
        });
    }

    public void onPositiveButtonClick() {
        FlagType currentFlag = getSelectedFlagType();
        if (currentFlag != null) {
            if (currentFlag.requireUserComment()) {
                FragmentManager fragmentManager = getFragmentManager();
                if (fragmentManager.findFragmentByTag("flag_item_dialog") == null) {
                    FlagItemUserMessageDialog dialog = FlagItemUserMessageDialog.newInstance(currentFlag.textEntryStringId);
                    dialog.setTargetFragment(this, 0);
                    dialog.show(fragmentManager, "flag_item_dialog");
                    return;
                }
                return;
            }
            postFlag();
        }
    }

    public void onNegativeButtonClick() {
        this.mPageFragmentHost.goBack();
    }

    protected void requestData() {
    }

    protected int getLayoutRes() {
        return R.layout.flag_item;
    }

    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (savedInstanceState != null) {
            this.mFlagMessage = savedInstanceState.getString("flag_free_text_message");
            this.mSelectedRadioButtonId = savedInstanceState.containsKey("flag_selected_button_id") ? savedInstanceState.getInt("flag_selected_button_id") : -1;
        }
        if (savedInstanceState == null || !savedInstanceState.containsKey("doc")) {
            switchToLoading();
            this.mDfeDetails = new DfeDetails(this.mDfeApi, this.mUrl);
            this.mDfeDetails.addDataChangedListener(new OnDataChangedListener() {
                public void onDataChanged() {
                    if (FlagItemFragment.this.mDoc == null) {
                        FlagItemFragment.this.onDocumentLoaded(FlagItemFragment.this.mDfeDetails.getDocument());
                    } else {
                        FinskyLog.d("Ignoring soft TTL refresh.", new Object[0]);
                    }
                }
            });
            this.mDfeDetails.addErrorListener(new ErrorListener() {
                public void onErrorResponse(VolleyError error) {
                    String message = ErrorStrings.get(FlagItemFragment.this.getActivity(), error);
                    if (message != null) {
                        FlagItemFragment.this.mPageFragmentHost.showErrorDialog(null, message, true);
                    } else {
                        FlagItemFragment.this.mPageFragmentHost.goBack();
                    }
                }
            });
            return;
        }
        onDocumentLoaded((Document) savedInstanceState.getParcelable("doc"));
    }

    public void onDocumentLoaded(Document document) {
        this.mDoc = document;
        switchToData();
        FinskyEventLog.setServerLogCookie(this.mUiElementProto, this.mDoc.getServerLogsCookie());
        if (this.mSummaryViewBinder == null) {
            this.mSummaryViewBinder = BinderFactory.getSummaryViewBinder(getToc(), this.mDoc.getBackend(), this.mDfeApi.getAccount());
            this.mSummaryViewBinder.setDynamicFeaturesVisibility(false);
            this.mSummaryViewBinder.setCompactMode(true);
            this.mSummaryViewBinder.init(this.mContext, this.mNavigationManager, this.mBitmapLoader, this, false, null, null, this);
        }
        this.mFlagRadioButtons.removeAllViews();
        LayoutInflater inflater = LayoutInflater.from(this.mContext);
        for (FlagType type : getFlagTypesForCurrentCorpus(this.mDoc.getBackend())) {
            RadioButton button = (RadioButton) inflater.inflate(R.layout.radio_button_row, this.mFlagRadioButtons, false);
            button.setText(type.stringId);
            button.setTag(type);
            this.mFlagRadioButtons.addView(button);
            if (this.mSelectedRadioButtonId != -1 && this.mSelectedRadioButtonId == type.stringId) {
                this.mFlagRadioButtons.check(button.getId());
            }
        }
        getView().requestFocus();
        onDataChanged();
    }

    public void rebindViews() {
        if (this.mDoc != null) {
            ((TextView) this.mDataView.findViewById(R.id.flag_content_instruction)).setText(this.mDoc.getBackend() == 3 ? R.string.flag_page_description : R.string.flag_page_description_non_app);
            if (this.mDoc.getBackend() == 2) {
                TextView footer = (TextView) this.mDataView.findViewById(R.id.flag_content_footer);
                footer.setText(Html.fromHtml(getString(R.string.flag_page_footer_music, G.musicDmcaReportLink.get())));
                footer.setMovementMethod(LinkMovementMethod.getInstance());
                footer.setVisibility(0);
            }
            this.mSummaryViewBinder.bind(this.mDoc, false, this.mDetailsPanel);
            this.mDataView.findViewById(R.id.item_summary_trailing).setBackgroundColor(CorpusResourceUtils.getPrimaryColor(this.mContext, this.mDoc.getBackend()));
            rebindActionBar();
        }
    }

    public void rebindActionBar() {
        this.mPageFragmentHost.updateBreadcrumb(this.mContext.getString(R.string.flagging_title));
        this.mPageFragmentHost.updateCurrentBackendId(this.mDoc.getBackend(), false);
        this.mPageFragmentHost.updateActionBarMode(false);
    }

    public boolean isDataReady() {
        return this.mDoc != null;
    }

    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if (this.mDoc != null) {
            outState.putParcelable("doc", this.mDoc);
            outState.putString("flag_free_text_message", this.mFlagMessage);
            if (getSelectedFlagType() != null) {
                outState.putInt("flag_selected_button_id", getSelectedFlagType().stringId);
            }
        }
    }

    public void onPositiveClick(String flagMessage) {
        this.mFlagMessage = flagMessage;
        postFlag();
    }

    private void postFlag() {
        this.mPageFragmentHost.goBack();
        getSelectedFlagType().postFlag(this.mContext, this.mDoc, this.mFlagMessage);
    }

    private List<FlagType> getFlagTypesForCurrentCorpus(int backendId) {
        if (backendId == 3) {
            return getAppFlagTypes();
        }
        if (backendId == 2) {
            return MusicFlagType.getMusicFlags();
        }
        throw new IllegalStateException("unsupported backend type");
    }

    private List<FlagType> getAppFlagTypes() {
        return AppFlagType.getAppFlags(!FinskyApp.get().getLibraries().getAppOwners(this.mDoc.getAppDetails().packageName).isEmpty());
    }

    private FlagType getSelectedFlagType() {
        if (getView() == null || this.mFlagRadioButtons.getCheckedRadioButtonId() == -1) {
            return null;
        }
        int index = this.mFlagRadioButtons.indexOfChild(getView().findViewById(this.mFlagRadioButtons.getCheckedRadioButtonId()));
        List<FlagType> currentTypes = getFlagTypesForCurrentCorpus(this.mDoc.getBackend());
        if (index < currentTypes.size()) {
            return (FlagType) currentTypes.get(index);
        }
        return null;
    }

    public PlayStoreUiElement getPlayStoreUiElement() {
        return this.mUiElementProto;
    }
}
