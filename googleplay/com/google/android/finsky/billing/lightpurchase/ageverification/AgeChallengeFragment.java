package com.google.android.finsky.billing.lightpurchase.ageverification;

import android.app.DatePickerDialog.OnDateSetListener;
import android.os.Bundle;
import android.text.Html;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import com.android.vending.R;
import com.google.android.finsky.billing.BillingUtils;
import com.google.android.finsky.fragments.LoggingFragment;
import com.google.android.finsky.layout.IconButtonGroup;
import com.google.android.finsky.protos.ChallengeProto.AgeChallenge;
import com.google.android.finsky.protos.ChallengeProto.FormRadioButton;
import com.google.android.finsky.protos.ChallengeProto.InputValidationError;
import com.google.android.finsky.utils.DateUtils;
import com.google.android.finsky.utils.FinskyLog;
import com.google.android.finsky.utils.Lists;
import com.google.android.finsky.utils.ParcelableProto;
import com.google.android.finsky.utils.UiUtils;
import com.google.android.finsky.utils.Utils;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AgeChallengeFragment extends LoggingFragment implements OnDateSetListener, OnClickListener {
    private int mBackend;
    private EditText mBirthday;
    private Date mBirthdayDate;
    private RadioGroup mCarrierSelection;
    private AgeChallenge mChallenge;
    private final OnCheckedChangeListener mCheckBoxWatcher;
    private CheckBox mCitizenship;
    private TextView mCitizenshipError;
    private EditText mFullName;
    private RadioGroup mGenderSelection;
    private ViewGroup mMainView;
    private EditText mPhoneNumber;
    private IconButtonGroup mSubmitButton;

    public interface Listener {
        void onSubmit(String str, Map<String, String> map);
    }

    public AgeChallengeFragment() {
        this.mCheckBoxWatcher = new OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    AgeChallengeFragment.this.mCitizenshipError.setError(null);
                }
            }
        };
    }

    public static AgeChallengeFragment newInstance(String accountName, int backend, AgeChallenge challenge) {
        Bundle args = new Bundle();
        args.putString("authAccount", accountName);
        args.putInt("AgeChallengeFragment.backend", backend);
        args.putParcelable("AgeChallengeFragment.challenge", ParcelableProto.forProto(challenge));
        AgeChallengeFragment result = new AgeChallengeFragment();
        result.setArguments(args);
        return result;
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.mBackend = getArguments().getInt("AgeChallengeFragment.backend");
        this.mChallenge = (AgeChallenge) ParcelableProto.getProtoFromBundle(getArguments(), "AgeChallengeFragment.challenge");
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        this.mMainView = (ViewGroup) inflater.inflate(R.layout.age_verification_age_challenge, container, false);
        TextView title = (TextView) this.mMainView.findViewById(R.id.title);
        if (TextUtils.isEmpty(this.mChallenge.title)) {
            FinskyLog.wtf("Title is not returned.", new Object[0]);
        } else {
            title.setText(this.mChallenge.title);
        }
        ((TextView) this.mMainView.findViewById(R.id.account_name)).setText(getArguments().getString("authAccount"));
        TextView description = (TextView) this.mMainView.findViewById(R.id.description);
        if (TextUtils.isEmpty(this.mChallenge.descriptionHtml)) {
            description.setVisibility(8);
        } else {
            description.setText(Html.fromHtml(this.mChallenge.descriptionHtml));
            description.setMovementMethod(LinkMovementMethod.getInstance());
            description.setLinkTextColor(description.getTextColors());
        }
        initInputFields(savedInstanceState);
        TextView footer = (TextView) this.mMainView.findViewById(R.id.footer);
        if (TextUtils.isEmpty(this.mChallenge.submitFooterHtml)) {
            footer.setVisibility(8);
        } else {
            footer.setText(Html.fromHtml(this.mChallenge.submitFooterHtml));
        }
        this.mSubmitButton = (IconButtonGroup) this.mMainView.findViewById(R.id.continue_button);
        this.mSubmitButton.setBackendForLabel(this.mBackend);
        if (this.mChallenge.submitButton == null || TextUtils.isEmpty(this.mChallenge.submitButton.label)) {
            FinskyLog.wtf("Submit button is not returned", new Object[0]);
        } else {
            this.mSubmitButton.setText(this.mChallenge.submitButton.label);
        }
        this.mSubmitButton.setOnClickListener(this);
        return this.mMainView;
    }

    public void onResume() {
        super.onResume();
        UiUtils.sendAccessibilityEventWithText(this.mMainView.getContext(), this.mChallenge.title, this.mMainView);
    }

    private void initInputFields(Bundle savedInstanceState) {
        LayoutInflater layoutInflater;
        FormRadioButton[] radioButtons;
        int firstRadioButtonId;
        int i;
        this.mFullName = (EditText) this.mMainView.findViewById(R.id.name_entry);
        if (this.mChallenge.fullName != null) {
            if (!TextUtils.isEmpty(this.mChallenge.fullName.defaultValue)) {
                this.mFullName.setText(this.mChallenge.fullName.defaultValue);
            }
            if (!TextUtils.isEmpty(this.mChallenge.fullName.hint)) {
                this.mFullName.setHint(this.mChallenge.fullName.hint);
            }
        } else {
            this.mFullName.setVisibility(8);
        }
        this.mBirthday = (EditText) this.mMainView.findViewById(R.id.birthday);
        if (this.mChallenge.birthdate != null) {
            if (savedInstanceState != null) {
                this.mBirthdayDate = (Date) savedInstanceState.getSerializable("AgeChallengeFragment.birthday_date");
            } else if (!TextUtils.isEmpty(this.mChallenge.birthdate.defaultValue)) {
                this.mBirthdayDate = DateUtils.parseDate(this.mChallenge.birthdate.defaultValue, "yyyyMMdd");
            }
            if (this.mBirthdayDate != null) {
                this.mBirthday.setText(DateUtils.formatDate(this.mBirthdayDate));
            }
            if (!TextUtils.isEmpty(this.mChallenge.birthdate.hint)) {
                this.mBirthday.setHint(this.mChallenge.birthdate.hint);
            }
            this.mBirthday.setKeyListener(null);
            this.mBirthday.setOnClickListener(this);
        } else {
            this.mBirthday.setVisibility(8);
        }
        int nextRadioButtonId = 1;
        this.mGenderSelection = (RadioGroup) this.mMainView.findViewById(R.id.genders);
        if (this.mChallenge.genderSelection != null) {
            layoutInflater = LayoutInflater.from(getActivity());
            radioButtons = this.mChallenge.genderSelection.radioButton;
            firstRadioButtonId = 1;
            i = 0;
            while (i < radioButtons.length) {
                FormRadioButton formRadioButton = radioButtons[i];
                RadioButton radioButton = (RadioButton) layoutInflater.inflate(R.layout.age_verification_radiobutton, this.mMainView, false);
                radioButton.setText(formRadioButton.label);
                int nextRadioButtonId2 = nextRadioButtonId + 1;
                radioButton.setId(nextRadioButtonId);
                radioButton.setChecked(formRadioButton.selected);
                this.mGenderSelection.addView(radioButton, i);
                i++;
                nextRadioButtonId = nextRadioButtonId2;
            }
            if (this.mGenderSelection.getCheckedRadioButtonId() == -1) {
                this.mGenderSelection.check(firstRadioButtonId);
            }
        } else {
            this.mGenderSelection.setVisibility(8);
        }
        this.mPhoneNumber = (EditText) this.mMainView.findViewById(R.id.phone_number);
        if (this.mChallenge.phoneNumber != null) {
            if (!TextUtils.isEmpty(this.mChallenge.phoneNumber.defaultValue)) {
                this.mPhoneNumber.setText(this.mChallenge.phoneNumber.defaultValue);
            }
            if (!TextUtils.isEmpty(this.mChallenge.phoneNumber.hint)) {
                this.mPhoneNumber.setHint(this.mChallenge.phoneNumber.hint);
            }
        } else {
            this.mPhoneNumber.setVisibility(8);
        }
        this.mCarrierSelection = (RadioGroup) this.mMainView.findViewById(R.id.carriers);
        if (this.mChallenge.carrierSelection != null) {
            layoutInflater = LayoutInflater.from(getActivity());
            radioButtons = this.mChallenge.carrierSelection.radioButton;
            firstRadioButtonId = nextRadioButtonId;
            i = 0;
            while (i < radioButtons.length) {
                formRadioButton = radioButtons[i];
                radioButton = (RadioButton) layoutInflater.inflate(R.layout.age_verification_radiobutton, this.mMainView, false);
                radioButton.setText(formRadioButton.label);
                nextRadioButtonId2 = nextRadioButtonId + 1;
                radioButton.setId(nextRadioButtonId);
                radioButton.setChecked(formRadioButton.selected);
                this.mCarrierSelection.addView(radioButton, i);
                i++;
                nextRadioButtonId = nextRadioButtonId2;
            }
            if (this.mCarrierSelection.getCheckedRadioButtonId() == -1) {
                this.mCarrierSelection.check(firstRadioButtonId);
            }
        } else {
            this.mCarrierSelection.setVisibility(8);
        }
        this.mCitizenship = (CheckBox) this.mMainView.findViewById(R.id.citizenship);
        if (this.mChallenge.citizenship != null) {
            this.mCitizenship.setText(this.mChallenge.citizenship.description);
            this.mCitizenship.setChecked(this.mChallenge.citizenship.checked);
            this.mCitizenship.setOnCheckedChangeListener(this.mCheckBoxWatcher);
            this.mCitizenshipError = (TextView) this.mMainView.findViewById(R.id.citizenship_error);
            return;
        }
        this.mCitizenship.setVisibility(8);
        this.mCitizenshipError.setVisibility(8);
    }

    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable("AgeChallengeFragment.birthday_date", this.mBirthdayDate);
    }

    private boolean validateInputAndShowErrors() {
        List<InputValidationError> validationErrors = Lists.newArrayList();
        if (this.mFullName.getVisibility() == 0 && Utils.isEmptyOrSpaces(this.mFullName.getText())) {
            validationErrors.add(BillingUtils.createInputValidationError(1, getString(R.string.invalid_name)));
        }
        if (this.mBirthday.getVisibility() == 0 && this.mBirthdayDate == null) {
            validationErrors.add(BillingUtils.createInputValidationError(2, getString(R.string.invalid_entry)));
        }
        if (this.mPhoneNumber.getVisibility() == 0 && Utils.isEmptyOrSpaces(this.mPhoneNumber.getText())) {
            validationErrors.add(BillingUtils.createInputValidationError(4, getString(R.string.invalid_phone)));
        }
        if (this.mCitizenship.getVisibility() == 0 && !this.mCitizenship.isChecked() && this.mChallenge.citizenship.required) {
            validationErrors.add(BillingUtils.createInputValidationError(6, getString(R.string.invalid_entry)));
        }
        displayErrors(validationErrors);
        return validationErrors.isEmpty();
    }

    private void clearErrorMessages() {
        this.mFullName.setError(null);
        this.mBirthday.setError(null);
        this.mPhoneNumber.setError(null);
        this.mCitizenshipError.setError(null);
    }

    private void displayErrors(final List<InputValidationError> inputValidationErrors) {
        clearErrorMessages();
        if (!inputValidationErrors.isEmpty()) {
            new Runnable() {
                public void run() {
                    for (InputValidationError error : inputValidationErrors) {
                        AgeChallengeFragment.this.displayError(error);
                    }
                }
            }.run();
        }
    }

    private TextView displayError(InputValidationError error) {
        TextView textView = null;
        String label = null;
        switch (error.inputField) {
            case com.google.android.wallet.instrumentmanager.R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                textView = this.mFullName;
                label = textView.getHint().toString();
                break;
            case com.google.android.wallet.instrumentmanager.R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                textView = this.mBirthday;
                label = textView.getHint().toString();
                break;
            case com.google.android.wallet.instrumentmanager.R.styleable.WalletImFormEditText_required /*4*/:
                textView = this.mPhoneNumber;
                label = textView.getHint().toString();
                break;
            case com.google.android.wallet.instrumentmanager.R.styleable.WalletFragmentStyle_maskedWalletDetailsBackground /*6*/:
                textView = this.mCitizenshipError;
                label = this.mCitizenship.getText().toString();
                break;
        }
        if (textView != null) {
            UiUtils.setErrorOnTextView(textView, label, error.errorMessage);
        }
        return textView;
    }

    private Map<String, String> serializeInputResult() {
        Map<String, String> inputResult = new HashMap();
        if (this.mFullName.getVisibility() == 0) {
            inputResult.put(this.mChallenge.fullName.postParam, this.mFullName.getText().toString());
        }
        if (this.mBirthday.getVisibility() == 0) {
            inputResult.put(this.mChallenge.birthdate.postParam, DateUtils.formatDate(this.mBirthdayDate, "yyyyMMdd"));
        }
        if (this.mGenderSelection.getVisibility() == 0) {
            inputResult.put(this.mChallenge.genderSelection.postParam, this.mChallenge.genderSelection.radioButton[this.mGenderSelection.indexOfChild(this.mGenderSelection.findViewById(this.mGenderSelection.getCheckedRadioButtonId()))].value);
        }
        if (this.mPhoneNumber.getVisibility() == 0) {
            inputResult.put(this.mChallenge.phoneNumber.postParam, this.mPhoneNumber.getText().toString());
        }
        if (this.mCarrierSelection.getVisibility() == 0) {
            inputResult.put(this.mChallenge.carrierSelection.postParam, this.mChallenge.carrierSelection.radioButton[this.mCarrierSelection.indexOfChild(this.mCarrierSelection.findViewById(this.mCarrierSelection.getCheckedRadioButtonId()))].value);
        }
        if (this.mCitizenship.getVisibility() == 0 && this.mCitizenship.isChecked()) {
            inputResult.put(this.mChallenge.citizenship.postParam, this.mChallenge.citizenship.id);
        }
        return inputResult;
    }

    private Listener getListener() {
        if (getTargetFragment() instanceof Listener) {
            return (Listener) getTargetFragment();
        }
        if (getParentFragment() instanceof Listener) {
            return (Listener) getParentFragment();
        }
        if (getActivity() instanceof Listener) {
            return (Listener) getActivity();
        }
        throw new IllegalStateException("No listener registered.");
    }

    public void onDateSet(DatePicker view, int year, int month, int day) {
        this.mBirthdayDate = new GregorianCalendar(year, month, day).getTime();
        this.mBirthday.setText(DateUtils.formatDate(this.mBirthdayDate));
        this.mBirthday.setError(null);
    }

    public void onClick(View v) {
        if (v == this.mBirthday) {
            if (getFragmentManager().findFragmentByTag("AgeChallengeFragment.date_picker") == null) {
                Calendar calendar = Calendar.getInstance();
                if (this.mBirthdayDate != null) {
                    calendar.setTime(this.mBirthdayDate);
                }
                DatePickerDialogFragment datePickerDialog = DatePickerDialogFragment.newInstance(calendar);
                datePickerDialog.setTargetFragment(this);
                datePickerDialog.show(getFragmentManager(), "AgeChallengeFragment.date_picker");
            }
        } else if (v == this.mSubmitButton && validateInputAndShowErrors()) {
            logClickEvent(1402);
            UiUtils.hideKeyboard(getActivity(), this.mMainView);
            getListener().onSubmit(this.mChallenge.submitButton.actionUrl, serializeInputResult());
        }
    }

    protected int getPlayStoreUiElementType() {
        return 1401;
    }
}
