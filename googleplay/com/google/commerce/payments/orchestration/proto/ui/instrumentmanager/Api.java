package com.google.commerce.payments.orchestration.proto.ui.instrumentmanager;

import com.google.android.wallet.instrumentmanager.R;
import com.google.commerce.payments.orchestration.proto.ui.common.FormFieldReferenceOuterClass.FormFieldReference;
import com.google.commerce.payments.orchestration.proto.ui.common.RequestContextOuterClass.RequestContext;
import com.google.commerce.payments.orchestration.proto.ui.common.ResponseContextOuterClass.ResponseContext;
import com.google.commerce.payments.orchestration.proto.ui.common.UiErrorOuterClass.UiError;
import com.google.commerce.payments.orchestration.proto.ui.common.components.InfoMessageOuterClass.InfoMessage;
import com.google.commerce.payments.orchestration.proto.ui.common.components.customer.CustomerFormOuterClass.CustomerForm;
import com.google.commerce.payments.orchestration.proto.ui.common.components.customer.CustomerFormOuterClass.CustomerFormValue;
import com.google.commerce.payments.orchestration.proto.ui.common.components.instrument.InstrumentFormOuterClass.InstrumentForm;
import com.google.commerce.payments.orchestration.proto.ui.common.components.instrument.InstrumentFormOuterClass.InstrumentFormValue;
import com.google.commerce.payments.orchestration.proto.ui.common.components.instrument.types.CreditCard.CreditCardExpirationDateForm;
import com.google.commerce.payments.orchestration.proto.ui.common.components.instrument.types.CreditCard.CreditCardExpirationDateFormValue;
import com.google.commerce.payments.orchestration.proto.ui.common.generic.ImageWithCaptionOuterClass.ImageWithCaption;
import com.google.protobuf.nano.CodedInputByteBufferNano;
import com.google.protobuf.nano.CodedOutputByteBufferNano;
import com.google.protobuf.nano.MessageNano;
import com.google.protobuf.nano.WireFormatNano;
import java.io.IOException;

public interface Api {

    public static final class InitializeResponse extends MessageNano {
        public ResponseContext context;
        public UiError error;
        public Page initialPage;

        public InitializeResponse() {
            clear();
        }

        public InitializeResponse clear() {
            this.error = null;
            this.context = null;
            this.initialPage = null;
            this.cachedSize = -1;
            return this;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.initialPage != null) {
                output.writeMessage(4, this.initialPage);
            }
            if (this.error != null) {
                output.writeMessage(5, this.error);
            }
            if (this.context != null) {
                output.writeMessage(6, this.context);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.initialPage != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(4, this.initialPage);
            }
            if (this.error != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(5, this.error);
            }
            if (this.context != null) {
                return size + CodedOutputByteBufferNano.computeMessageSize(6, this.context);
            }
            return size;
        }

        public InitializeResponse mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case R.styleable.WalletImButtonBar_capitalizeButtonText /*0*/:
                        break;
                    case com.google.android.play.R.styleable.Theme_actionModeFindDrawable /*34*/:
                        if (this.initialPage == null) {
                            this.initialPage = new Page();
                        }
                        input.readMessage(this.initialPage);
                        continue;
                    case com.google.android.play.R.styleable.Theme_spinnerDropDownItemStyle /*42*/:
                        if (this.error == null) {
                            this.error = new UiError();
                        }
                        input.readMessage(this.error);
                        continue;
                    case com.google.android.play.R.styleable.Theme_dividerHorizontal /*50*/:
                        if (this.context == null) {
                            this.context = new ResponseContext();
                        }
                        input.readMessage(this.context);
                        continue;
                    default:
                        if (!WireFormatNano.parseUnknownField(input, tag)) {
                            break;
                        }
                        continue;
                }
                return this;
            }
        }
    }

    public static final class InstrumentManagerParameters extends MessageNano {
        public int action;
        public boolean allowCreditCardCameraInput;
        public String cdpBrokerId;
        public String country;
        public String currencyCode;
        public String instrumentId;
        public String languageCode;

        public InstrumentManagerParameters() {
            clear();
        }

        public InstrumentManagerParameters clear() {
            this.action = 0;
            this.instrumentId = "";
            this.cdpBrokerId = "";
            this.currencyCode = "USD";
            this.country = "US";
            this.languageCode = "";
            this.allowCreditCardCameraInput = false;
            this.cachedSize = -1;
            return this;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.action != 0) {
                output.writeInt32(1, this.action);
            }
            if (!this.cdpBrokerId.equals("")) {
                output.writeString(2, this.cdpBrokerId);
            }
            if (!this.currencyCode.equals("USD")) {
                output.writeString(3, this.currencyCode);
            }
            if (!this.country.equals("US")) {
                output.writeString(4, this.country);
            }
            if (!this.instrumentId.equals("")) {
                output.writeString(5, this.instrumentId);
            }
            if (!this.languageCode.equals("")) {
                output.writeString(6, this.languageCode);
            }
            if (this.allowCreditCardCameraInput) {
                output.writeBool(7, this.allowCreditCardCameraInput);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.action != 0) {
                size += CodedOutputByteBufferNano.computeInt32Size(1, this.action);
            }
            if (!this.cdpBrokerId.equals("")) {
                size += CodedOutputByteBufferNano.computeStringSize(2, this.cdpBrokerId);
            }
            if (!this.currencyCode.equals("USD")) {
                size += CodedOutputByteBufferNano.computeStringSize(3, this.currencyCode);
            }
            if (!this.country.equals("US")) {
                size += CodedOutputByteBufferNano.computeStringSize(4, this.country);
            }
            if (!this.instrumentId.equals("")) {
                size += CodedOutputByteBufferNano.computeStringSize(5, this.instrumentId);
            }
            if (!this.languageCode.equals("")) {
                size += CodedOutputByteBufferNano.computeStringSize(6, this.languageCode);
            }
            if (this.allowCreditCardCameraInput) {
                return size + CodedOutputByteBufferNano.computeBoolSize(7, this.allowCreditCardCameraInput);
            }
            return size;
        }

        public InstrumentManagerParameters mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case R.styleable.WalletImButtonBar_capitalizeButtonText /*0*/:
                        break;
                    case R.styleable.WalletFragmentStyle_maskedWalletDetailsButtonBackground /*8*/:
                        int value = input.readInt32();
                        switch (value) {
                            case R.styleable.WalletImButtonBar_capitalizeButtonText /*0*/:
                            case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                            case R.styleable.WalletImFormEditText_requiredErrorString /*3*/:
                            case R.styleable.WalletImFormEditText_validateWhenNotVisible /*5*/:
                            case R.styleable.WalletFragmentStyle_maskedWalletDetailsBackground /*6*/:
                            case R.styleable.WalletFragmentStyle_maskedWalletDetailsButtonTextAppearance /*7*/:
                            case R.styleable.WalletFragmentStyle_maskedWalletDetailsButtonBackground /*8*/:
                                this.action = value;
                                break;
                            default:
                                continue;
                        }
                    case com.google.android.play.R.styleable.Toolbar_collapseIcon /*18*/:
                        this.cdpBrokerId = input.readString();
                        continue;
                    case com.google.android.play.R.styleable.Theme_actionModeBackground /*26*/:
                        this.currencyCode = input.readString();
                        continue;
                    case com.google.android.play.R.styleable.Theme_actionModeFindDrawable /*34*/:
                        this.country = input.readString();
                        continue;
                    case com.google.android.play.R.styleable.Theme_spinnerDropDownItemStyle /*42*/:
                        this.instrumentId = input.readString();
                        continue;
                    case com.google.android.play.R.styleable.Theme_dividerHorizontal /*50*/:
                        this.languageCode = input.readString();
                        continue;
                    case com.google.android.play.R.styleable.Theme_editTextColor /*56*/:
                        this.allowCreditCardCameraInput = input.readBool();
                        continue;
                    default:
                        if (!WireFormatNano.parseUnknownField(input, tag)) {
                            break;
                        }
                        continue;
                }
                return this;
            }
        }
    }

    public static final class Page extends MessageNano {
        public CreditCardExpirationDateForm creditCardExpirationDateForm;
        public CustomerForm customerForm;
        public InstrumentForm instrumentForm;
        public FormFieldReference[] refreshTriggerField;
        public String submitButtonText;
        public String title;
        public ImageWithCaption titleImage;
        public InfoMessage topInfoMessage;

        public Page() {
            clear();
        }

        public Page clear() {
            this.title = "";
            this.titleImage = null;
            this.topInfoMessage = null;
            this.submitButtonText = "";
            this.refreshTriggerField = FormFieldReference.emptyArray();
            this.customerForm = null;
            this.instrumentForm = null;
            this.creditCardExpirationDateForm = null;
            this.cachedSize = -1;
            return this;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.customerForm != null) {
                output.writeMessage(1, this.customerForm);
            }
            if (this.instrumentForm != null) {
                output.writeMessage(2, this.instrumentForm);
            }
            if (this.creditCardExpirationDateForm != null) {
                output.writeMessage(3, this.creditCardExpirationDateForm);
            }
            if (!this.title.equals("")) {
                output.writeString(4, this.title);
            }
            if (!this.submitButtonText.equals("")) {
                output.writeString(5, this.submitButtonText);
            }
            if (this.refreshTriggerField != null && this.refreshTriggerField.length > 0) {
                for (FormFieldReference element : this.refreshTriggerField) {
                    if (element != null) {
                        output.writeMessage(8, element);
                    }
                }
            }
            if (this.topInfoMessage != null) {
                output.writeMessage(10, this.topInfoMessage);
            }
            if (this.titleImage != null) {
                output.writeMessage(11, this.titleImage);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.customerForm != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(1, this.customerForm);
            }
            if (this.instrumentForm != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(2, this.instrumentForm);
            }
            if (this.creditCardExpirationDateForm != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(3, this.creditCardExpirationDateForm);
            }
            if (!this.title.equals("")) {
                size += CodedOutputByteBufferNano.computeStringSize(4, this.title);
            }
            if (!this.submitButtonText.equals("")) {
                size += CodedOutputByteBufferNano.computeStringSize(5, this.submitButtonText);
            }
            if (this.refreshTriggerField != null && this.refreshTriggerField.length > 0) {
                for (FormFieldReference element : this.refreshTriggerField) {
                    if (element != null) {
                        size += CodedOutputByteBufferNano.computeMessageSize(8, element);
                    }
                }
            }
            if (this.topInfoMessage != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(10, this.topInfoMessage);
            }
            if (this.titleImage != null) {
                return size + CodedOutputByteBufferNano.computeMessageSize(11, this.titleImage);
            }
            return size;
        }

        public Page mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case R.styleable.WalletImButtonBar_capitalizeButtonText /*0*/:
                        break;
                    case R.styleable.WalletFragmentStyle_maskedWalletDetailsLogoImageType /*10*/:
                        if (this.customerForm == null) {
                            this.customerForm = new CustomerForm();
                        }
                        input.readMessage(this.customerForm);
                        continue;
                    case com.google.android.play.R.styleable.Toolbar_collapseIcon /*18*/:
                        if (this.instrumentForm == null) {
                            this.instrumentForm = new InstrumentForm();
                        }
                        input.readMessage(this.instrumentForm);
                        continue;
                    case com.google.android.play.R.styleable.Theme_actionModeBackground /*26*/:
                        if (this.creditCardExpirationDateForm == null) {
                            this.creditCardExpirationDateForm = new CreditCardExpirationDateForm();
                        }
                        input.readMessage(this.creditCardExpirationDateForm);
                        continue;
                    case com.google.android.play.R.styleable.Theme_actionModeFindDrawable /*34*/:
                        this.title = input.readString();
                        continue;
                    case com.google.android.play.R.styleable.Theme_spinnerDropDownItemStyle /*42*/:
                        this.submitButtonText = input.readString();
                        continue;
                    case com.google.android.play.R.styleable.Theme_listPreferredItemPaddingLeft /*66*/:
                        int i;
                        int arrayLength = WireFormatNano.getRepeatedFieldArrayLength(input, 66);
                        if (this.refreshTriggerField == null) {
                            i = 0;
                        } else {
                            i = this.refreshTriggerField.length;
                        }
                        FormFieldReference[] newArray = new FormFieldReference[(i + arrayLength)];
                        if (i != 0) {
                            System.arraycopy(this.refreshTriggerField, 0, newArray, 0, i);
                        }
                        while (i < newArray.length - 1) {
                            newArray[i] = new FormFieldReference();
                            input.readMessage(newArray[i]);
                            input.readTag();
                            i++;
                        }
                        newArray[i] = new FormFieldReference();
                        input.readMessage(newArray[i]);
                        this.refreshTriggerField = newArray;
                        continue;
                    case com.google.android.play.R.styleable.Theme_colorButtonNormal /*82*/:
                        if (this.topInfoMessage == null) {
                            this.topInfoMessage = new InfoMessage();
                        }
                        input.readMessage(this.topInfoMessage);
                        continue;
                    case 90:
                        if (this.titleImage == null) {
                            this.titleImage = new ImageWithCaption();
                        }
                        input.readMessage(this.titleImage);
                        continue;
                    default:
                        if (!WireFormatNano.parseUnknownField(input, tag)) {
                            break;
                        }
                        continue;
                }
                return this;
            }
        }
    }

    public static final class PageValue extends MessageNano {
        public CreditCardExpirationDateFormValue newCreditCardExpirationDate;
        public CustomerFormValue newCustomer;
        public InstrumentFormValue newInstrument;

        public PageValue() {
            clear();
        }

        public PageValue clear() {
            this.newCustomer = null;
            this.newInstrument = null;
            this.newCreditCardExpirationDate = null;
            this.cachedSize = -1;
            return this;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.newCustomer != null) {
                output.writeMessage(1, this.newCustomer);
            }
            if (this.newInstrument != null) {
                output.writeMessage(2, this.newInstrument);
            }
            if (this.newCreditCardExpirationDate != null) {
                output.writeMessage(3, this.newCreditCardExpirationDate);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.newCustomer != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(1, this.newCustomer);
            }
            if (this.newInstrument != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(2, this.newInstrument);
            }
            if (this.newCreditCardExpirationDate != null) {
                return size + CodedOutputByteBufferNano.computeMessageSize(3, this.newCreditCardExpirationDate);
            }
            return size;
        }

        public PageValue mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case R.styleable.WalletImButtonBar_capitalizeButtonText /*0*/:
                        break;
                    case R.styleable.WalletFragmentStyle_maskedWalletDetailsLogoImageType /*10*/:
                        if (this.newCustomer == null) {
                            this.newCustomer = new CustomerFormValue();
                        }
                        input.readMessage(this.newCustomer);
                        continue;
                    case com.google.android.play.R.styleable.Toolbar_collapseIcon /*18*/:
                        if (this.newInstrument == null) {
                            this.newInstrument = new InstrumentFormValue();
                        }
                        input.readMessage(this.newInstrument);
                        continue;
                    case com.google.android.play.R.styleable.Theme_actionModeBackground /*26*/:
                        if (this.newCreditCardExpirationDate == null) {
                            this.newCreditCardExpirationDate = new CreditCardExpirationDateFormValue();
                        }
                        input.readMessage(this.newCreditCardExpirationDate);
                        continue;
                    default:
                        if (!WireFormatNano.parseUnknownField(input, tag)) {
                            break;
                        }
                        continue;
                }
                return this;
            }
        }
    }

    public static final class RefreshPageRequest extends MessageNano {
        public RequestContext context;
        public PageValue pageValue;
        public FormFieldReference refreshTriggerField;

        public RefreshPageRequest() {
            clear();
        }

        public RefreshPageRequest clear() {
            this.context = null;
            this.pageValue = null;
            this.refreshTriggerField = null;
            this.cachedSize = -1;
            return this;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.context != null) {
                output.writeMessage(1, this.context);
            }
            if (this.pageValue != null) {
                output.writeMessage(2, this.pageValue);
            }
            if (this.refreshTriggerField != null) {
                output.writeMessage(3, this.refreshTriggerField);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.context != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(1, this.context);
            }
            if (this.pageValue != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(2, this.pageValue);
            }
            if (this.refreshTriggerField != null) {
                return size + CodedOutputByteBufferNano.computeMessageSize(3, this.refreshTriggerField);
            }
            return size;
        }

        public RefreshPageRequest mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case R.styleable.WalletImButtonBar_capitalizeButtonText /*0*/:
                        break;
                    case R.styleable.WalletFragmentStyle_maskedWalletDetailsLogoImageType /*10*/:
                        if (this.context == null) {
                            this.context = new RequestContext();
                        }
                        input.readMessage(this.context);
                        continue;
                    case com.google.android.play.R.styleable.Toolbar_collapseIcon /*18*/:
                        if (this.pageValue == null) {
                            this.pageValue = new PageValue();
                        }
                        input.readMessage(this.pageValue);
                        continue;
                    case com.google.android.play.R.styleable.Theme_actionModeBackground /*26*/:
                        if (this.refreshTriggerField == null) {
                            this.refreshTriggerField = new FormFieldReference();
                        }
                        input.readMessage(this.refreshTriggerField);
                        continue;
                    default:
                        if (!WireFormatNano.parseUnknownField(input, tag)) {
                            break;
                        }
                        continue;
                }
                return this;
            }
        }
    }

    public static final class RefreshPageResponse extends MessageNano {
        public ResponseContext context;
        public UiError error;
        public Page nextPage;

        public RefreshPageResponse() {
            clear();
        }

        public RefreshPageResponse clear() {
            this.error = null;
            this.context = null;
            this.nextPage = null;
            this.cachedSize = -1;
            return this;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.error != null) {
                output.writeMessage(1, this.error);
            }
            if (this.context != null) {
                output.writeMessage(2, this.context);
            }
            if (this.nextPage != null) {
                output.writeMessage(3, this.nextPage);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.error != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(1, this.error);
            }
            if (this.context != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(2, this.context);
            }
            if (this.nextPage != null) {
                return size + CodedOutputByteBufferNano.computeMessageSize(3, this.nextPage);
            }
            return size;
        }

        public RefreshPageResponse mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case R.styleable.WalletImButtonBar_capitalizeButtonText /*0*/:
                        break;
                    case R.styleable.WalletFragmentStyle_maskedWalletDetailsLogoImageType /*10*/:
                        if (this.error == null) {
                            this.error = new UiError();
                        }
                        input.readMessage(this.error);
                        continue;
                    case com.google.android.play.R.styleable.Toolbar_collapseIcon /*18*/:
                        if (this.context == null) {
                            this.context = new ResponseContext();
                        }
                        input.readMessage(this.context);
                        continue;
                    case com.google.android.play.R.styleable.Theme_actionModeBackground /*26*/:
                        if (this.nextPage == null) {
                            this.nextPage = new Page();
                        }
                        input.readMessage(this.nextPage);
                        continue;
                    default:
                        if (!WireFormatNano.parseUnknownField(input, tag)) {
                            break;
                        }
                        continue;
                }
                return this;
            }
        }
    }

    public static final class SavePageRequest extends MessageNano {
        public RequestContext context;
        public PageValue pageValue;
        public InstrumentManagerParameters parameters;

        public SavePageRequest() {
            clear();
        }

        public SavePageRequest clear() {
            this.context = null;
            this.parameters = null;
            this.pageValue = null;
            this.cachedSize = -1;
            return this;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.context != null) {
                output.writeMessage(1, this.context);
            }
            if (this.parameters != null) {
                output.writeMessage(2, this.parameters);
            }
            if (this.pageValue != null) {
                output.writeMessage(4, this.pageValue);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.context != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(1, this.context);
            }
            if (this.parameters != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(2, this.parameters);
            }
            if (this.pageValue != null) {
                return size + CodedOutputByteBufferNano.computeMessageSize(4, this.pageValue);
            }
            return size;
        }

        public SavePageRequest mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case R.styleable.WalletImButtonBar_capitalizeButtonText /*0*/:
                        break;
                    case R.styleable.WalletFragmentStyle_maskedWalletDetailsLogoImageType /*10*/:
                        if (this.context == null) {
                            this.context = new RequestContext();
                        }
                        input.readMessage(this.context);
                        continue;
                    case com.google.android.play.R.styleable.Toolbar_collapseIcon /*18*/:
                        if (this.parameters == null) {
                            this.parameters = new InstrumentManagerParameters();
                        }
                        input.readMessage(this.parameters);
                        continue;
                    case com.google.android.play.R.styleable.Theme_actionModeFindDrawable /*34*/:
                        if (this.pageValue == null) {
                            this.pageValue = new PageValue();
                        }
                        input.readMessage(this.pageValue);
                        continue;
                    default:
                        if (!WireFormatNano.parseUnknownField(input, tag)) {
                            break;
                        }
                        continue;
                }
                return this;
            }
        }
    }

    public static final class SavePageResponse extends MessageNano {
        public ResponseContext context;
        public UiError error;
        public boolean flowComplete;
        public String instrumentId;
        public Page nextPage;

        public SavePageResponse() {
            clear();
        }

        public SavePageResponse clear() {
            this.error = null;
            this.context = null;
            this.flowComplete = false;
            this.instrumentId = "";
            this.nextPage = null;
            this.cachedSize = -1;
            return this;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (!this.instrumentId.equals("")) {
                output.writeString(1, this.instrumentId);
            }
            if (this.nextPage != null) {
                output.writeMessage(2, this.nextPage);
            }
            if (this.error != null) {
                output.writeMessage(3, this.error);
            }
            if (this.context != null) {
                output.writeMessage(4, this.context);
            }
            if (this.flowComplete) {
                output.writeBool(5, this.flowComplete);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (!this.instrumentId.equals("")) {
                size += CodedOutputByteBufferNano.computeStringSize(1, this.instrumentId);
            }
            if (this.nextPage != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(2, this.nextPage);
            }
            if (this.error != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(3, this.error);
            }
            if (this.context != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(4, this.context);
            }
            if (this.flowComplete) {
                return size + CodedOutputByteBufferNano.computeBoolSize(5, this.flowComplete);
            }
            return size;
        }

        public SavePageResponse mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case R.styleable.WalletImButtonBar_capitalizeButtonText /*0*/:
                        break;
                    case R.styleable.WalletFragmentStyle_maskedWalletDetailsLogoImageType /*10*/:
                        this.instrumentId = input.readString();
                        continue;
                    case com.google.android.play.R.styleable.Toolbar_collapseIcon /*18*/:
                        if (this.nextPage == null) {
                            this.nextPage = new Page();
                        }
                        input.readMessage(this.nextPage);
                        continue;
                    case com.google.android.play.R.styleable.Theme_actionModeBackground /*26*/:
                        if (this.error == null) {
                            this.error = new UiError();
                        }
                        input.readMessage(this.error);
                        continue;
                    case com.google.android.play.R.styleable.Theme_actionModeFindDrawable /*34*/:
                        if (this.context == null) {
                            this.context = new ResponseContext();
                        }
                        input.readMessage(this.context);
                        continue;
                    case com.google.android.play.R.styleable.Theme_dropdownListPreferredItemHeight /*40*/:
                        this.flowComplete = input.readBool();
                        continue;
                    default:
                        if (!WireFormatNano.parseUnknownField(input, tag)) {
                            break;
                        }
                        continue;
                }
                return this;
            }
        }
    }
}
