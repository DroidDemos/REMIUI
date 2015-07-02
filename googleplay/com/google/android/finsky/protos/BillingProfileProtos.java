package com.google.android.finsky.protos;

import com.google.android.finsky.protos.Common.Image;
import com.google.android.finsky.protos.CommonDevice.CarrierBillingInstrumentStatus;
import com.google.android.finsky.protos.CommonDevice.GenericInstrument;
import com.google.android.finsky.protos.CommonDevice.Instrument;
import com.google.android.finsky.protos.CommonDevice.TopupInfo;
import com.google.android.wallet.instrumentmanager.R;
import com.google.protobuf.nano.CodedInputByteBufferNano;
import com.google.protobuf.nano.CodedOutputByteBufferNano;
import com.google.protobuf.nano.InternalNano;
import com.google.protobuf.nano.MessageNano;
import com.google.protobuf.nano.WireFormatNano;
import java.io.IOException;
import java.util.Arrays;

public interface BillingProfileProtos {

    public static final class BillingProfile extends MessageNano {
        public BillingProfileOption[] billingProfileOption;
        public boolean hasPaymentsIntegratorCommonToken;
        public boolean hasSelectedExternalInstrumentId;
        public Instrument[] instrument;
        public byte[] paymentsIntegratorCommonToken;
        public Image remindMeLaterIconImage;
        public String selectedExternalInstrumentId;

        public BillingProfile() {
            clear();
        }

        public BillingProfile clear() {
            this.instrument = Instrument.emptyArray();
            this.selectedExternalInstrumentId = "";
            this.hasSelectedExternalInstrumentId = false;
            this.billingProfileOption = BillingProfileOption.emptyArray();
            this.paymentsIntegratorCommonToken = WireFormatNano.EMPTY_BYTES;
            this.hasPaymentsIntegratorCommonToken = false;
            this.remindMeLaterIconImage = null;
            this.cachedSize = -1;
            return this;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.instrument != null && this.instrument.length > 0) {
                for (Instrument element : this.instrument) {
                    if (element != null) {
                        output.writeMessage(1, element);
                    }
                }
            }
            if (this.hasSelectedExternalInstrumentId || !this.selectedExternalInstrumentId.equals("")) {
                output.writeString(2, this.selectedExternalInstrumentId);
            }
            if (this.billingProfileOption != null && this.billingProfileOption.length > 0) {
                for (BillingProfileOption element2 : this.billingProfileOption) {
                    if (element2 != null) {
                        output.writeMessage(3, element2);
                    }
                }
            }
            if (this.hasPaymentsIntegratorCommonToken || !Arrays.equals(this.paymentsIntegratorCommonToken, WireFormatNano.EMPTY_BYTES)) {
                output.writeBytes(6, this.paymentsIntegratorCommonToken);
            }
            if (this.remindMeLaterIconImage != null) {
                output.writeMessage(7, this.remindMeLaterIconImage);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.instrument != null && this.instrument.length > 0) {
                for (Instrument element : this.instrument) {
                    if (element != null) {
                        size += CodedOutputByteBufferNano.computeMessageSize(1, element);
                    }
                }
            }
            if (this.hasSelectedExternalInstrumentId || !this.selectedExternalInstrumentId.equals("")) {
                size += CodedOutputByteBufferNano.computeStringSize(2, this.selectedExternalInstrumentId);
            }
            if (this.billingProfileOption != null && this.billingProfileOption.length > 0) {
                for (BillingProfileOption element2 : this.billingProfileOption) {
                    if (element2 != null) {
                        size += CodedOutputByteBufferNano.computeMessageSize(3, element2);
                    }
                }
            }
            if (this.hasPaymentsIntegratorCommonToken || !Arrays.equals(this.paymentsIntegratorCommonToken, WireFormatNano.EMPTY_BYTES)) {
                size += CodedOutputByteBufferNano.computeBytesSize(6, this.paymentsIntegratorCommonToken);
            }
            if (this.remindMeLaterIconImage != null) {
                return size + CodedOutputByteBufferNano.computeMessageSize(7, this.remindMeLaterIconImage);
            }
            return size;
        }

        public BillingProfile mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                int arrayLength;
                int i;
                switch (tag) {
                    case R.styleable.WalletImButtonBar_capitalizeButtonText /*0*/:
                        break;
                    case R.styleable.WalletFragmentStyle_maskedWalletDetailsLogoImageType /*10*/:
                        arrayLength = WireFormatNano.getRepeatedFieldArrayLength(input, 10);
                        if (this.instrument == null) {
                            i = 0;
                        } else {
                            i = this.instrument.length;
                        }
                        Instrument[] newArray = new Instrument[(i + arrayLength)];
                        if (i != 0) {
                            System.arraycopy(this.instrument, 0, newArray, 0, i);
                        }
                        while (i < newArray.length - 1) {
                            newArray[i] = new Instrument();
                            input.readMessage(newArray[i]);
                            input.readTag();
                            i++;
                        }
                        newArray[i] = new Instrument();
                        input.readMessage(newArray[i]);
                        this.instrument = newArray;
                        continue;
                    case com.google.android.play.R.styleable.Toolbar_collapseIcon /*18*/:
                        this.selectedExternalInstrumentId = input.readString();
                        this.hasSelectedExternalInstrumentId = true;
                        continue;
                    case com.google.android.play.R.styleable.Theme_actionModeBackground /*26*/:
                        arrayLength = WireFormatNano.getRepeatedFieldArrayLength(input, 26);
                        if (this.billingProfileOption == null) {
                            i = 0;
                        } else {
                            i = this.billingProfileOption.length;
                        }
                        BillingProfileOption[] newArray2 = new BillingProfileOption[(i + arrayLength)];
                        if (i != 0) {
                            System.arraycopy(this.billingProfileOption, 0, newArray2, 0, i);
                        }
                        while (i < newArray2.length - 1) {
                            newArray2[i] = new BillingProfileOption();
                            input.readMessage(newArray2[i]);
                            input.readTag();
                            i++;
                        }
                        newArray2[i] = new BillingProfileOption();
                        input.readMessage(newArray2[i]);
                        this.billingProfileOption = newArray2;
                        continue;
                    case com.google.android.play.R.styleable.Theme_dividerHorizontal /*50*/:
                        this.paymentsIntegratorCommonToken = input.readBytes();
                        this.hasPaymentsIntegratorCommonToken = true;
                        continue;
                    case com.google.android.play.R.styleable.Theme_switchStyle /*58*/:
                        if (this.remindMeLaterIconImage == null) {
                            this.remindMeLaterIconImage = new Image();
                        }
                        input.readMessage(this.remindMeLaterIconImage);
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

    public static final class BillingProfileOption extends MessageNano {
        private static volatile BillingProfileOption[] _emptyArray;
        public CarrierBillingInstrumentStatus carrierBillingInstrumentStatus;
        public String displayTitle;
        public String externalInstrumentId;
        public GenericInstrument genericInstrument;
        public boolean hasDisplayTitle;
        public boolean hasExternalInstrumentId;
        public boolean hasPaymentsIntegratorInstrumentToken;
        public boolean hasServerLogsCookie;
        public boolean hasType;
        public boolean hasTypeName;
        public Image iconImage;
        public byte[] paymentsIntegratorInstrumentToken;
        public byte[] serverLogsCookie;
        public TopupInfo topupInfo;
        public int type;
        public String typeName;

        public static BillingProfileOption[] emptyArray() {
            if (_emptyArray == null) {
                synchronized (InternalNano.LAZY_INIT_LOCK) {
                    if (_emptyArray == null) {
                        _emptyArray = new BillingProfileOption[0];
                    }
                }
            }
            return _emptyArray;
        }

        public BillingProfileOption() {
            clear();
        }

        public BillingProfileOption clear() {
            this.type = 0;
            this.hasType = false;
            this.displayTitle = "";
            this.hasDisplayTitle = false;
            this.iconImage = null;
            this.serverLogsCookie = WireFormatNano.EMPTY_BYTES;
            this.hasServerLogsCookie = false;
            this.typeName = "";
            this.hasTypeName = false;
            this.externalInstrumentId = "";
            this.hasExternalInstrumentId = false;
            this.carrierBillingInstrumentStatus = null;
            this.topupInfo = null;
            this.genericInstrument = null;
            this.paymentsIntegratorInstrumentToken = WireFormatNano.EMPTY_BYTES;
            this.hasPaymentsIntegratorInstrumentToken = false;
            this.cachedSize = -1;
            return this;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.type != 0 || this.hasType) {
                output.writeInt32(1, this.type);
            }
            if (this.hasDisplayTitle || !this.displayTitle.equals("")) {
                output.writeString(2, this.displayTitle);
            }
            if (this.hasExternalInstrumentId || !this.externalInstrumentId.equals("")) {
                output.writeString(3, this.externalInstrumentId);
            }
            if (this.topupInfo != null) {
                output.writeMessage(4, this.topupInfo);
            }
            if (this.carrierBillingInstrumentStatus != null) {
                output.writeMessage(5, this.carrierBillingInstrumentStatus);
            }
            if (this.genericInstrument != null) {
                output.writeMessage(6, this.genericInstrument);
            }
            if (this.hasPaymentsIntegratorInstrumentToken || !Arrays.equals(this.paymentsIntegratorInstrumentToken, WireFormatNano.EMPTY_BYTES)) {
                output.writeBytes(7, this.paymentsIntegratorInstrumentToken);
            }
            if (this.iconImage != null) {
                output.writeMessage(8, this.iconImage);
            }
            if (this.hasServerLogsCookie || !Arrays.equals(this.serverLogsCookie, WireFormatNano.EMPTY_BYTES)) {
                output.writeBytes(9, this.serverLogsCookie);
            }
            if (this.hasTypeName || !this.typeName.equals("")) {
                output.writeString(10, this.typeName);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.type != 0 || this.hasType) {
                size += CodedOutputByteBufferNano.computeInt32Size(1, this.type);
            }
            if (this.hasDisplayTitle || !this.displayTitle.equals("")) {
                size += CodedOutputByteBufferNano.computeStringSize(2, this.displayTitle);
            }
            if (this.hasExternalInstrumentId || !this.externalInstrumentId.equals("")) {
                size += CodedOutputByteBufferNano.computeStringSize(3, this.externalInstrumentId);
            }
            if (this.topupInfo != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(4, this.topupInfo);
            }
            if (this.carrierBillingInstrumentStatus != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(5, this.carrierBillingInstrumentStatus);
            }
            if (this.genericInstrument != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(6, this.genericInstrument);
            }
            if (this.hasPaymentsIntegratorInstrumentToken || !Arrays.equals(this.paymentsIntegratorInstrumentToken, WireFormatNano.EMPTY_BYTES)) {
                size += CodedOutputByteBufferNano.computeBytesSize(7, this.paymentsIntegratorInstrumentToken);
            }
            if (this.iconImage != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(8, this.iconImage);
            }
            if (this.hasServerLogsCookie || !Arrays.equals(this.serverLogsCookie, WireFormatNano.EMPTY_BYTES)) {
                size += CodedOutputByteBufferNano.computeBytesSize(9, this.serverLogsCookie);
            }
            if (this.hasTypeName || !this.typeName.equals("")) {
                return size + CodedOutputByteBufferNano.computeStringSize(10, this.typeName);
            }
            return size;
        }

        public BillingProfileOption mergeFrom(CodedInputByteBufferNano input) throws IOException {
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
                            case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                            case R.styleable.WalletImFormEditText_requiredErrorString /*3*/:
                            case R.styleable.WalletImFormEditText_required /*4*/:
                            case R.styleable.WalletImFormEditText_validateWhenNotVisible /*5*/:
                            case R.styleable.WalletFragmentStyle_maskedWalletDetailsBackground /*6*/:
                                this.type = value;
                                this.hasType = true;
                                break;
                            default:
                                continue;
                        }
                    case com.google.android.play.R.styleable.Toolbar_collapseIcon /*18*/:
                        this.displayTitle = input.readString();
                        this.hasDisplayTitle = true;
                        continue;
                    case com.google.android.play.R.styleable.Theme_actionModeBackground /*26*/:
                        this.externalInstrumentId = input.readString();
                        this.hasExternalInstrumentId = true;
                        continue;
                    case com.google.android.play.R.styleable.Theme_actionModeFindDrawable /*34*/:
                        if (this.topupInfo == null) {
                            this.topupInfo = new TopupInfo();
                        }
                        input.readMessage(this.topupInfo);
                        continue;
                    case com.google.android.play.R.styleable.Theme_spinnerDropDownItemStyle /*42*/:
                        if (this.carrierBillingInstrumentStatus == null) {
                            this.carrierBillingInstrumentStatus = new CarrierBillingInstrumentStatus();
                        }
                        input.readMessage(this.carrierBillingInstrumentStatus);
                        continue;
                    case com.google.android.play.R.styleable.Theme_dividerHorizontal /*50*/:
                        if (this.genericInstrument == null) {
                            this.genericInstrument = new GenericInstrument();
                        }
                        input.readMessage(this.genericInstrument);
                        continue;
                    case com.google.android.play.R.styleable.Theme_switchStyle /*58*/:
                        this.paymentsIntegratorInstrumentToken = input.readBytes();
                        this.hasPaymentsIntegratorInstrumentToken = true;
                        continue;
                    case com.google.android.play.R.styleable.Theme_listPreferredItemPaddingLeft /*66*/:
                        if (this.iconImage == null) {
                            this.iconImage = new Image();
                        }
                        input.readMessage(this.iconImage);
                        continue;
                    case com.google.android.play.R.styleable.Theme_panelMenuListTheme /*74*/:
                        this.serverLogsCookie = input.readBytes();
                        this.hasServerLogsCookie = true;
                        continue;
                    case com.google.android.play.R.styleable.Theme_colorButtonNormal /*82*/:
                        this.typeName = input.readString();
                        this.hasTypeName = true;
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
