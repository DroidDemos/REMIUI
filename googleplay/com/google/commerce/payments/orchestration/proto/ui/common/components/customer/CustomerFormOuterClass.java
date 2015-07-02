package com.google.commerce.payments.orchestration.proto.ui.common.components.customer;

import com.google.android.wallet.instrumentmanager.R;
import com.google.commerce.payments.orchestration.proto.ui.common.components.AddressFormOuterClass.AddressForm;
import com.google.commerce.payments.orchestration.proto.ui.common.components.AddressFormOuterClass.AddressFormValue;
import com.google.commerce.payments.orchestration.proto.ui.common.components.AddressFormOuterClass.CountrySelector;
import com.google.commerce.payments.orchestration.proto.ui.common.components.instrument.InstrumentFormOuterClass.InstrumentForm;
import com.google.commerce.payments.orchestration.proto.ui.common.components.instrument.InstrumentFormOuterClass.InstrumentFormValue;
import com.google.commerce.payments.orchestration.proto.ui.common.components.legal.LegalMessageSetOuterClass.LegalMessageSet;
import com.google.commerce.payments.orchestration.proto.ui.common.components.tax.TaxInfoFormOuterClass.TaxInfoForm;
import com.google.commerce.payments.orchestration.proto.ui.common.components.tax.TaxInfoFormOuterClass.TaxInfoFormValue;
import com.google.protobuf.nano.CodedInputByteBufferNano;
import com.google.protobuf.nano.CodedOutputByteBufferNano;
import com.google.protobuf.nano.MessageNano;
import com.google.protobuf.nano.WireFormatNano;
import java.io.IOException;

public interface CustomerFormOuterClass {

    public static final class CustomerForm extends MessageNano {
        public String id;
        public int initialTaxInfoForm;
        public InstrumentForm instrumentForm;
        public AddressForm legalAddressForm;
        public CountrySelector legalCountrySelector;
        public LegalMessageSet legalMessages;
        public TaxInfoForm[] taxInfoForm;

        public CustomerForm() {
            clear();
        }

        public CustomerForm clear() {
            this.id = "";
            this.legalCountrySelector = null;
            this.legalAddressForm = null;
            this.instrumentForm = null;
            this.legalMessages = null;
            this.taxInfoForm = TaxInfoForm.emptyArray();
            this.initialTaxInfoForm = -1;
            this.cachedSize = -1;
            return this;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.legalAddressForm != null) {
                output.writeMessage(1, this.legalAddressForm);
            }
            if (this.instrumentForm != null) {
                output.writeMessage(2, this.instrumentForm);
            }
            if (this.legalMessages != null) {
                output.writeMessage(4, this.legalMessages);
            }
            if (!this.id.equals("")) {
                output.writeString(5, this.id);
            }
            if (this.legalCountrySelector != null) {
                output.writeMessage(6, this.legalCountrySelector);
            }
            if (this.taxInfoForm != null && this.taxInfoForm.length > 0) {
                for (TaxInfoForm element : this.taxInfoForm) {
                    if (element != null) {
                        output.writeMessage(7, element);
                    }
                }
            }
            if (this.initialTaxInfoForm != -1) {
                output.writeInt32(8, this.initialTaxInfoForm);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.legalAddressForm != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(1, this.legalAddressForm);
            }
            if (this.instrumentForm != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(2, this.instrumentForm);
            }
            if (this.legalMessages != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(4, this.legalMessages);
            }
            if (!this.id.equals("")) {
                size += CodedOutputByteBufferNano.computeStringSize(5, this.id);
            }
            if (this.legalCountrySelector != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(6, this.legalCountrySelector);
            }
            if (this.taxInfoForm != null && this.taxInfoForm.length > 0) {
                for (TaxInfoForm element : this.taxInfoForm) {
                    if (element != null) {
                        size += CodedOutputByteBufferNano.computeMessageSize(7, element);
                    }
                }
            }
            if (this.initialTaxInfoForm != -1) {
                return size + CodedOutputByteBufferNano.computeInt32Size(8, this.initialTaxInfoForm);
            }
            return size;
        }

        public CustomerForm mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case R.styleable.WalletImButtonBar_capitalizeButtonText /*0*/:
                        break;
                    case R.styleable.WalletFragmentStyle_maskedWalletDetailsLogoImageType /*10*/:
                        if (this.legalAddressForm == null) {
                            this.legalAddressForm = new AddressForm();
                        }
                        input.readMessage(this.legalAddressForm);
                        continue;
                    case com.google.android.play.R.styleable.Toolbar_collapseIcon /*18*/:
                        if (this.instrumentForm == null) {
                            this.instrumentForm = new InstrumentForm();
                        }
                        input.readMessage(this.instrumentForm);
                        continue;
                    case com.google.android.play.R.styleable.Theme_actionModeFindDrawable /*34*/:
                        if (this.legalMessages == null) {
                            this.legalMessages = new LegalMessageSet();
                        }
                        input.readMessage(this.legalMessages);
                        continue;
                    case com.google.android.play.R.styleable.Theme_spinnerDropDownItemStyle /*42*/:
                        this.id = input.readString();
                        continue;
                    case com.google.android.play.R.styleable.Theme_dividerHorizontal /*50*/:
                        if (this.legalCountrySelector == null) {
                            this.legalCountrySelector = new CountrySelector();
                        }
                        input.readMessage(this.legalCountrySelector);
                        continue;
                    case com.google.android.play.R.styleable.Theme_switchStyle /*58*/:
                        int i;
                        int arrayLength = WireFormatNano.getRepeatedFieldArrayLength(input, 58);
                        if (this.taxInfoForm == null) {
                            i = 0;
                        } else {
                            i = this.taxInfoForm.length;
                        }
                        TaxInfoForm[] newArray = new TaxInfoForm[(i + arrayLength)];
                        if (i != 0) {
                            System.arraycopy(this.taxInfoForm, 0, newArray, 0, i);
                        }
                        while (i < newArray.length - 1) {
                            newArray[i] = new TaxInfoForm();
                            input.readMessage(newArray[i]);
                            input.readTag();
                            i++;
                        }
                        newArray[i] = new TaxInfoForm();
                        input.readMessage(newArray[i]);
                        this.taxInfoForm = newArray;
                        continue;
                    case com.google.android.play.R.styleable.Theme_listPreferredItemHeightSmall /*64*/:
                        this.initialTaxInfoForm = input.readInt32();
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

    public static final class CustomerFormValue extends MessageNano {
        public InstrumentFormValue instrument;
        public AddressFormValue legalAddress;
        public String legalCountryCode;
        public String legalDocData;
        public TaxInfoFormValue taxInfo;

        public CustomerFormValue() {
            clear();
        }

        public CustomerFormValue clear() {
            this.instrument = null;
            this.legalAddress = null;
            this.legalDocData = "";
            this.legalCountryCode = "";
            this.taxInfo = null;
            this.cachedSize = -1;
            return this;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.instrument != null) {
                output.writeMessage(1, this.instrument);
            }
            if (!this.legalDocData.equals("")) {
                output.writeString(3, this.legalDocData);
            }
            if (this.legalAddress != null) {
                output.writeMessage(4, this.legalAddress);
            }
            if (!this.legalCountryCode.equals("")) {
                output.writeString(5, this.legalCountryCode);
            }
            if (this.taxInfo != null) {
                output.writeMessage(6, this.taxInfo);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.instrument != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(1, this.instrument);
            }
            if (!this.legalDocData.equals("")) {
                size += CodedOutputByteBufferNano.computeStringSize(3, this.legalDocData);
            }
            if (this.legalAddress != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(4, this.legalAddress);
            }
            if (!this.legalCountryCode.equals("")) {
                size += CodedOutputByteBufferNano.computeStringSize(5, this.legalCountryCode);
            }
            if (this.taxInfo != null) {
                return size + CodedOutputByteBufferNano.computeMessageSize(6, this.taxInfo);
            }
            return size;
        }

        public CustomerFormValue mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case R.styleable.WalletImButtonBar_capitalizeButtonText /*0*/:
                        break;
                    case R.styleable.WalletFragmentStyle_maskedWalletDetailsLogoImageType /*10*/:
                        if (this.instrument == null) {
                            this.instrument = new InstrumentFormValue();
                        }
                        input.readMessage(this.instrument);
                        continue;
                    case com.google.android.play.R.styleable.Theme_actionModeBackground /*26*/:
                        this.legalDocData = input.readString();
                        continue;
                    case com.google.android.play.R.styleable.Theme_actionModeFindDrawable /*34*/:
                        if (this.legalAddress == null) {
                            this.legalAddress = new AddressFormValue();
                        }
                        input.readMessage(this.legalAddress);
                        continue;
                    case com.google.android.play.R.styleable.Theme_spinnerDropDownItemStyle /*42*/:
                        this.legalCountryCode = input.readString();
                        continue;
                    case com.google.android.play.R.styleable.Theme_dividerHorizontal /*50*/:
                        if (this.taxInfo == null) {
                            this.taxInfo = new TaxInfoFormValue();
                        }
                        input.readMessage(this.taxInfo);
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
