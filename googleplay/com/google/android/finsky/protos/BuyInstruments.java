package com.google.android.finsky.protos;

import com.google.android.finsky.protos.BillingProfileProtos.BillingProfile;
import com.google.android.finsky.protos.ChallengeProto.AddressChallenge;
import com.google.android.finsky.protos.ChallengeProto.InputValidationError;
import com.google.android.finsky.protos.CheckPromoOffer.RedeemedPromoOffer;
import com.google.android.finsky.protos.CommonDevice.Instrument;
import com.google.android.finsky.protos.CreateInstrument.DeviceCreateInstrumentFlowHandle;
import com.google.android.finsky.protos.CreateInstrument.DeviceCreateInstrumentFlowState;
import com.google.android.finsky.protos.CreateInstrument.DeviceCreateInstrumentMetadata;
import com.google.android.finsky.protos.CreateInstrument.ProfileFormInput;
import com.google.android.finsky.protos.CreateInstrument.UsernamePasswordFormInput;
import com.google.android.finsky.protos.InstrumentSetupInfoProto.InstrumentSetupInfo;
import com.google.android.wallet.instrumentmanager.R;
import com.google.protobuf.nano.CodedInputByteBufferNano;
import com.google.protobuf.nano.CodedOutputByteBufferNano;
import com.google.protobuf.nano.MessageNano;
import com.google.protobuf.nano.WireFormatNano;
import java.io.IOException;

public interface BuyInstruments {

    public static final class BillingProfileResponse extends MessageNano {
        public BillingProfile billingProfile;
        public boolean hasResult;
        public boolean hasUserMessageHtml;
        public int result;
        public String userMessageHtml;

        public BillingProfileResponse() {
            clear();
        }

        public BillingProfileResponse clear() {
            this.result = 1;
            this.hasResult = false;
            this.billingProfile = null;
            this.userMessageHtml = "";
            this.hasUserMessageHtml = false;
            this.cachedSize = -1;
            return this;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.result != 1 || this.hasResult) {
                output.writeInt32(1, this.result);
            }
            if (this.billingProfile != null) {
                output.writeMessage(2, this.billingProfile);
            }
            if (this.hasUserMessageHtml || !this.userMessageHtml.equals("")) {
                output.writeString(3, this.userMessageHtml);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.result != 1 || this.hasResult) {
                size += CodedOutputByteBufferNano.computeInt32Size(1, this.result);
            }
            if (this.billingProfile != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(2, this.billingProfile);
            }
            if (this.hasUserMessageHtml || !this.userMessageHtml.equals("")) {
                return size + CodedOutputByteBufferNano.computeStringSize(3, this.userMessageHtml);
            }
            return size;
        }

        public BillingProfileResponse mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case R.styleable.WalletImButtonBar_capitalizeButtonText /*0*/:
                        break;
                    case R.styleable.WalletFragmentStyle_maskedWalletDetailsButtonBackground /*8*/:
                        int value = input.readInt32();
                        switch (value) {
                            case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                            case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                            case R.styleable.WalletImFormEditText_requiredErrorString /*3*/:
                                this.result = value;
                                this.hasResult = true;
                                break;
                            default:
                                continue;
                        }
                    case com.google.android.play.R.styleable.Toolbar_collapseIcon /*18*/:
                        if (this.billingProfile == null) {
                            this.billingProfile = new BillingProfile();
                        }
                        input.readMessage(this.billingProfile);
                        continue;
                    case com.google.android.play.R.styleable.Theme_actionModeBackground /*26*/:
                        this.userMessageHtml = input.readString();
                        this.hasUserMessageHtml = true;
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

    public static final class CheckIabPromoResponse extends MessageNano {
        public boolean eligible;
        public boolean hasEligible;

        public CheckIabPromoResponse() {
            clear();
        }

        public CheckIabPromoResponse clear() {
            this.eligible = false;
            this.hasEligible = false;
            this.cachedSize = -1;
            return this;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.hasEligible || this.eligible) {
                output.writeBool(1, this.eligible);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.hasEligible || this.eligible) {
                return size + CodedOutputByteBufferNano.computeBoolSize(1, this.eligible);
            }
            return size;
        }

        public CheckIabPromoResponse mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case R.styleable.WalletImButtonBar_capitalizeButtonText /*0*/:
                        break;
                    case R.styleable.WalletFragmentStyle_maskedWalletDetailsButtonBackground /*8*/:
                        this.eligible = input.readBool();
                        this.hasEligible = true;
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

    public static final class CheckInstrumentResponse extends MessageNano {
        public boolean hasUserHasValidInstrument;
        public boolean userHasValidInstrument;

        public CheckInstrumentResponse() {
            clear();
        }

        public CheckInstrumentResponse clear() {
            this.userHasValidInstrument = false;
            this.hasUserHasValidInstrument = false;
            this.cachedSize = -1;
            return this;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.hasUserHasValidInstrument || this.userHasValidInstrument) {
                output.writeBool(1, this.userHasValidInstrument);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.hasUserHasValidInstrument || this.userHasValidInstrument) {
                return size + CodedOutputByteBufferNano.computeBoolSize(1, this.userHasValidInstrument);
            }
            return size;
        }

        public CheckInstrumentResponse mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case R.styleable.WalletImButtonBar_capitalizeButtonText /*0*/:
                        break;
                    case R.styleable.WalletFragmentStyle_maskedWalletDetailsButtonBackground /*8*/:
                        this.userHasValidInstrument = input.readBool();
                        this.hasUserHasValidInstrument = true;
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

    public static final class CreateInstrumentRequest extends MessageNano {
        public DeviceCreateInstrumentFlowHandle flowHandle;
        public ProfileFormInput profileFormInput;
        public UsernamePasswordFormInput usernamePasswordFormInput;

        public CreateInstrumentRequest() {
            clear();
        }

        public CreateInstrumentRequest clear() {
            this.flowHandle = null;
            this.profileFormInput = null;
            this.usernamePasswordFormInput = null;
            this.cachedSize = -1;
            return this;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.flowHandle != null) {
                output.writeMessage(1, this.flowHandle);
            }
            if (this.profileFormInput != null) {
                output.writeMessage(2, this.profileFormInput);
            }
            if (this.usernamePasswordFormInput != null) {
                output.writeMessage(3, this.usernamePasswordFormInput);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.flowHandle != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(1, this.flowHandle);
            }
            if (this.profileFormInput != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(2, this.profileFormInput);
            }
            if (this.usernamePasswordFormInput != null) {
                return size + CodedOutputByteBufferNano.computeMessageSize(3, this.usernamePasswordFormInput);
            }
            return size;
        }

        public CreateInstrumentRequest mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case R.styleable.WalletImButtonBar_capitalizeButtonText /*0*/:
                        break;
                    case R.styleable.WalletFragmentStyle_maskedWalletDetailsLogoImageType /*10*/:
                        if (this.flowHandle == null) {
                            this.flowHandle = new DeviceCreateInstrumentFlowHandle();
                        }
                        input.readMessage(this.flowHandle);
                        continue;
                    case com.google.android.play.R.styleable.Toolbar_collapseIcon /*18*/:
                        if (this.profileFormInput == null) {
                            this.profileFormInput = new ProfileFormInput();
                        }
                        input.readMessage(this.profileFormInput);
                        continue;
                    case com.google.android.play.R.styleable.Theme_actionModeBackground /*26*/:
                        if (this.usernamePasswordFormInput == null) {
                            this.usernamePasswordFormInput = new UsernamePasswordFormInput();
                        }
                        input.readMessage(this.usernamePasswordFormInput);
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

    public static final class CreateInstrumentResponse extends MessageNano {
        public DeviceCreateInstrumentFlowState createInstrumentFlowState;
        public boolean hasInstrumentId;
        public boolean hasResult;
        public boolean hasUserMessageHtml;
        public String instrumentId;
        public int result;
        public String userMessageHtml;

        public CreateInstrumentResponse() {
            clear();
        }

        public CreateInstrumentResponse clear() {
            this.result = 1;
            this.hasResult = false;
            this.userMessageHtml = "";
            this.hasUserMessageHtml = false;
            this.instrumentId = "";
            this.hasInstrumentId = false;
            this.createInstrumentFlowState = null;
            this.cachedSize = -1;
            return this;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.result != 1 || this.hasResult) {
                output.writeInt32(1, this.result);
            }
            if (this.hasUserMessageHtml || !this.userMessageHtml.equals("")) {
                output.writeString(2, this.userMessageHtml);
            }
            if (this.hasInstrumentId || !this.instrumentId.equals("")) {
                output.writeString(3, this.instrumentId);
            }
            if (this.createInstrumentFlowState != null) {
                output.writeMessage(4, this.createInstrumentFlowState);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.result != 1 || this.hasResult) {
                size += CodedOutputByteBufferNano.computeInt32Size(1, this.result);
            }
            if (this.hasUserMessageHtml || !this.userMessageHtml.equals("")) {
                size += CodedOutputByteBufferNano.computeStringSize(2, this.userMessageHtml);
            }
            if (this.hasInstrumentId || !this.instrumentId.equals("")) {
                size += CodedOutputByteBufferNano.computeStringSize(3, this.instrumentId);
            }
            if (this.createInstrumentFlowState != null) {
                return size + CodedOutputByteBufferNano.computeMessageSize(4, this.createInstrumentFlowState);
            }
            return size;
        }

        public CreateInstrumentResponse mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case R.styleable.WalletImButtonBar_capitalizeButtonText /*0*/:
                        break;
                    case R.styleable.WalletFragmentStyle_maskedWalletDetailsButtonBackground /*8*/:
                        int value = input.readInt32();
                        switch (value) {
                            case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                            case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                            case R.styleable.WalletImFormEditText_requiredErrorString /*3*/:
                            case R.styleable.WalletImFormEditText_required /*4*/:
                                this.result = value;
                                this.hasResult = true;
                                break;
                            default:
                                continue;
                        }
                    case com.google.android.play.R.styleable.Toolbar_collapseIcon /*18*/:
                        this.userMessageHtml = input.readString();
                        this.hasUserMessageHtml = true;
                        continue;
                    case com.google.android.play.R.styleable.Theme_actionModeBackground /*26*/:
                        this.instrumentId = input.readString();
                        this.hasInstrumentId = true;
                        continue;
                    case com.google.android.play.R.styleable.Theme_actionModeFindDrawable /*34*/:
                        if (this.createInstrumentFlowState == null) {
                            this.createInstrumentFlowState = new DeviceCreateInstrumentFlowState();
                        }
                        input.readMessage(this.createInstrumentFlowState);
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

    public static final class GetInitialInstrumentFlowStateResponse extends MessageNano {
        public DeviceCreateInstrumentFlowState createInstrumentFlowState;
        public DeviceCreateInstrumentMetadata createInstrumentMetadata;
        public boolean hasResult;
        public boolean hasUserMessageHtml;
        public int result;
        public String userMessageHtml;

        public GetInitialInstrumentFlowStateResponse() {
            clear();
        }

        public GetInitialInstrumentFlowStateResponse clear() {
            this.result = 1;
            this.hasResult = false;
            this.userMessageHtml = "";
            this.hasUserMessageHtml = false;
            this.createInstrumentFlowState = null;
            this.createInstrumentMetadata = null;
            this.cachedSize = -1;
            return this;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.result != 1 || this.hasResult) {
                output.writeInt32(1, this.result);
            }
            if (this.hasUserMessageHtml || !this.userMessageHtml.equals("")) {
                output.writeString(2, this.userMessageHtml);
            }
            if (this.createInstrumentFlowState != null) {
                output.writeMessage(3, this.createInstrumentFlowState);
            }
            if (this.createInstrumentMetadata != null) {
                output.writeMessage(4, this.createInstrumentMetadata);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.result != 1 || this.hasResult) {
                size += CodedOutputByteBufferNano.computeInt32Size(1, this.result);
            }
            if (this.hasUserMessageHtml || !this.userMessageHtml.equals("")) {
                size += CodedOutputByteBufferNano.computeStringSize(2, this.userMessageHtml);
            }
            if (this.createInstrumentFlowState != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(3, this.createInstrumentFlowState);
            }
            if (this.createInstrumentMetadata != null) {
                return size + CodedOutputByteBufferNano.computeMessageSize(4, this.createInstrumentMetadata);
            }
            return size;
        }

        public GetInitialInstrumentFlowStateResponse mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case R.styleable.WalletImButtonBar_capitalizeButtonText /*0*/:
                        break;
                    case R.styleable.WalletFragmentStyle_maskedWalletDetailsButtonBackground /*8*/:
                        int value = input.readInt32();
                        switch (value) {
                            case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                            case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                                this.result = value;
                                this.hasResult = true;
                                break;
                            default:
                                continue;
                        }
                    case com.google.android.play.R.styleable.Toolbar_collapseIcon /*18*/:
                        this.userMessageHtml = input.readString();
                        this.hasUserMessageHtml = true;
                        continue;
                    case com.google.android.play.R.styleable.Theme_actionModeBackground /*26*/:
                        if (this.createInstrumentFlowState == null) {
                            this.createInstrumentFlowState = new DeviceCreateInstrumentFlowState();
                        }
                        input.readMessage(this.createInstrumentFlowState);
                        continue;
                    case com.google.android.play.R.styleable.Theme_actionModeFindDrawable /*34*/:
                        if (this.createInstrumentMetadata == null) {
                            this.createInstrumentMetadata = new DeviceCreateInstrumentMetadata();
                        }
                        input.readMessage(this.createInstrumentMetadata);
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

    public static final class InstrumentSetupInfoResponse extends MessageNano {
        public boolean checkoutTokenRequired;
        public boolean hasCheckoutTokenRequired;
        public InstrumentSetupInfo[] setupInfo;

        public InstrumentSetupInfoResponse() {
            clear();
        }

        public InstrumentSetupInfoResponse clear() {
            this.setupInfo = InstrumentSetupInfo.emptyArray();
            this.checkoutTokenRequired = false;
            this.hasCheckoutTokenRequired = false;
            this.cachedSize = -1;
            return this;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.setupInfo != null && this.setupInfo.length > 0) {
                for (InstrumentSetupInfo element : this.setupInfo) {
                    if (element != null) {
                        output.writeMessage(1, element);
                    }
                }
            }
            if (this.hasCheckoutTokenRequired || this.checkoutTokenRequired) {
                output.writeBool(2, this.checkoutTokenRequired);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.setupInfo != null && this.setupInfo.length > 0) {
                for (InstrumentSetupInfo element : this.setupInfo) {
                    if (element != null) {
                        size += CodedOutputByteBufferNano.computeMessageSize(1, element);
                    }
                }
            }
            if (this.hasCheckoutTokenRequired || this.checkoutTokenRequired) {
                return size + CodedOutputByteBufferNano.computeBoolSize(2, this.checkoutTokenRequired);
            }
            return size;
        }

        public InstrumentSetupInfoResponse mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case R.styleable.WalletImButtonBar_capitalizeButtonText /*0*/:
                        break;
                    case R.styleable.WalletFragmentStyle_maskedWalletDetailsLogoImageType /*10*/:
                        int i;
                        int arrayLength = WireFormatNano.getRepeatedFieldArrayLength(input, 10);
                        if (this.setupInfo == null) {
                            i = 0;
                        } else {
                            i = this.setupInfo.length;
                        }
                        InstrumentSetupInfo[] newArray = new InstrumentSetupInfo[(i + arrayLength)];
                        if (i != 0) {
                            System.arraycopy(this.setupInfo, 0, newArray, 0, i);
                        }
                        while (i < newArray.length - 1) {
                            newArray[i] = new InstrumentSetupInfo();
                            input.readMessage(newArray[i]);
                            input.readTag();
                            i++;
                        }
                        newArray[i] = new InstrumentSetupInfo();
                        input.readMessage(newArray[i]);
                        this.setupInfo = newArray;
                        continue;
                    case com.google.android.play.R.styleable.Toolbar_maxButtonHeight /*16*/:
                        this.checkoutTokenRequired = input.readBool();
                        this.hasCheckoutTokenRequired = true;
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

    public static final class RedeemGiftCardResponse extends MessageNano {
        public AddressChallenge addressChallenge;
        public String balanceHtml;
        public boolean checkoutTokenRequired;
        public boolean hasBalanceHtml;
        public boolean hasCheckoutTokenRequired;
        public boolean hasResult;
        public boolean hasUserMessageHtml;
        public int result;
        public String userMessageHtml;

        public RedeemGiftCardResponse() {
            clear();
        }

        public RedeemGiftCardResponse clear() {
            this.result = 0;
            this.hasResult = false;
            this.userMessageHtml = "";
            this.hasUserMessageHtml = false;
            this.balanceHtml = "";
            this.hasBalanceHtml = false;
            this.addressChallenge = null;
            this.checkoutTokenRequired = false;
            this.hasCheckoutTokenRequired = false;
            this.cachedSize = -1;
            return this;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.result != 0 || this.hasResult) {
                output.writeInt32(1, this.result);
            }
            if (this.hasUserMessageHtml || !this.userMessageHtml.equals("")) {
                output.writeString(2, this.userMessageHtml);
            }
            if (this.hasBalanceHtml || !this.balanceHtml.equals("")) {
                output.writeString(3, this.balanceHtml);
            }
            if (this.addressChallenge != null) {
                output.writeMessage(4, this.addressChallenge);
            }
            if (this.hasCheckoutTokenRequired || this.checkoutTokenRequired) {
                output.writeBool(5, this.checkoutTokenRequired);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.result != 0 || this.hasResult) {
                size += CodedOutputByteBufferNano.computeInt32Size(1, this.result);
            }
            if (this.hasUserMessageHtml || !this.userMessageHtml.equals("")) {
                size += CodedOutputByteBufferNano.computeStringSize(2, this.userMessageHtml);
            }
            if (this.hasBalanceHtml || !this.balanceHtml.equals("")) {
                size += CodedOutputByteBufferNano.computeStringSize(3, this.balanceHtml);
            }
            if (this.addressChallenge != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(4, this.addressChallenge);
            }
            if (this.hasCheckoutTokenRequired || this.checkoutTokenRequired) {
                return size + CodedOutputByteBufferNano.computeBoolSize(5, this.checkoutTokenRequired);
            }
            return size;
        }

        public RedeemGiftCardResponse mergeFrom(CodedInputByteBufferNano input) throws IOException {
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
                            case R.styleable.WalletFragmentStyle_maskedWalletDetailsButtonTextAppearance /*7*/:
                            case R.styleable.WalletFragmentStyle_maskedWalletDetailsButtonBackground /*8*/:
                            case R.styleable.WalletFragmentStyle_maskedWalletDetailsLogoTextColor /*9*/:
                            case R.styleable.WalletFragmentStyle_maskedWalletDetailsLogoImageType /*10*/:
                            case R.styleable.MapAttrs_uiZoomControls /*11*/:
                            case R.styleable.MapAttrs_uiZoomGestures /*12*/:
                                this.result = value;
                                this.hasResult = true;
                                break;
                            default:
                                continue;
                        }
                    case com.google.android.play.R.styleable.Toolbar_collapseIcon /*18*/:
                        this.userMessageHtml = input.readString();
                        this.hasUserMessageHtml = true;
                        continue;
                    case com.google.android.play.R.styleable.Theme_actionModeBackground /*26*/:
                        this.balanceHtml = input.readString();
                        this.hasBalanceHtml = true;
                        continue;
                    case com.google.android.play.R.styleable.Theme_actionModeFindDrawable /*34*/:
                        if (this.addressChallenge == null) {
                            this.addressChallenge = new AddressChallenge();
                        }
                        input.readMessage(this.addressChallenge);
                        continue;
                    case com.google.android.play.R.styleable.Theme_dropdownListPreferredItemHeight /*40*/:
                        this.checkoutTokenRequired = input.readBool();
                        this.hasCheckoutTokenRequired = true;
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

    public static final class UpdateInstrumentRequest extends MessageNano {
        public String checkoutToken;
        public boolean hasCheckoutToken;
        public Instrument instrument;

        public UpdateInstrumentRequest() {
            clear();
        }

        public UpdateInstrumentRequest clear() {
            this.instrument = null;
            this.checkoutToken = "";
            this.hasCheckoutToken = false;
            this.cachedSize = -1;
            return this;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.instrument != null) {
                output.writeMessage(1, this.instrument);
            }
            if (this.hasCheckoutToken || !this.checkoutToken.equals("")) {
                output.writeString(2, this.checkoutToken);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.instrument != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(1, this.instrument);
            }
            if (this.hasCheckoutToken || !this.checkoutToken.equals("")) {
                return size + CodedOutputByteBufferNano.computeStringSize(2, this.checkoutToken);
            }
            return size;
        }

        public UpdateInstrumentRequest mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case R.styleable.WalletImButtonBar_capitalizeButtonText /*0*/:
                        break;
                    case R.styleable.WalletFragmentStyle_maskedWalletDetailsLogoImageType /*10*/:
                        if (this.instrument == null) {
                            this.instrument = new Instrument();
                        }
                        input.readMessage(this.instrument);
                        continue;
                    case com.google.android.play.R.styleable.Toolbar_collapseIcon /*18*/:
                        this.checkoutToken = input.readString();
                        this.hasCheckoutToken = true;
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

    public static final class UpdateInstrumentResponse extends MessageNano {
        public boolean checkoutTokenRequired;
        public InputValidationError[] errorInputField;
        public boolean hasCheckoutTokenRequired;
        public boolean hasInstrumentId;
        public boolean hasResult;
        public boolean hasUserMessageHtml;
        public String instrumentId;
        public RedeemedPromoOffer redeemedOffer;
        public int result;
        public String userMessageHtml;

        public UpdateInstrumentResponse() {
            clear();
        }

        public UpdateInstrumentResponse clear() {
            this.result = 0;
            this.hasResult = false;
            this.instrumentId = "";
            this.hasInstrumentId = false;
            this.userMessageHtml = "";
            this.hasUserMessageHtml = false;
            this.errorInputField = InputValidationError.emptyArray();
            this.checkoutTokenRequired = false;
            this.hasCheckoutTokenRequired = false;
            this.redeemedOffer = null;
            this.cachedSize = -1;
            return this;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.result != 0 || this.hasResult) {
                output.writeInt32(1, this.result);
            }
            if (this.hasInstrumentId || !this.instrumentId.equals("")) {
                output.writeString(2, this.instrumentId);
            }
            if (this.hasUserMessageHtml || !this.userMessageHtml.equals("")) {
                output.writeString(3, this.userMessageHtml);
            }
            if (this.errorInputField != null && this.errorInputField.length > 0) {
                for (InputValidationError element : this.errorInputField) {
                    if (element != null) {
                        output.writeMessage(4, element);
                    }
                }
            }
            if (this.hasCheckoutTokenRequired || this.checkoutTokenRequired) {
                output.writeBool(5, this.checkoutTokenRequired);
            }
            if (this.redeemedOffer != null) {
                output.writeMessage(6, this.redeemedOffer);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.result != 0 || this.hasResult) {
                size += CodedOutputByteBufferNano.computeInt32Size(1, this.result);
            }
            if (this.hasInstrumentId || !this.instrumentId.equals("")) {
                size += CodedOutputByteBufferNano.computeStringSize(2, this.instrumentId);
            }
            if (this.hasUserMessageHtml || !this.userMessageHtml.equals("")) {
                size += CodedOutputByteBufferNano.computeStringSize(3, this.userMessageHtml);
            }
            if (this.errorInputField != null && this.errorInputField.length > 0) {
                for (InputValidationError element : this.errorInputField) {
                    if (element != null) {
                        size += CodedOutputByteBufferNano.computeMessageSize(4, element);
                    }
                }
            }
            if (this.hasCheckoutTokenRequired || this.checkoutTokenRequired) {
                size += CodedOutputByteBufferNano.computeBoolSize(5, this.checkoutTokenRequired);
            }
            if (this.redeemedOffer != null) {
                return size + CodedOutputByteBufferNano.computeMessageSize(6, this.redeemedOffer);
            }
            return size;
        }

        public UpdateInstrumentResponse mergeFrom(CodedInputByteBufferNano input) throws IOException {
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
                                this.result = value;
                                this.hasResult = true;
                                break;
                            default:
                                continue;
                        }
                    case com.google.android.play.R.styleable.Toolbar_collapseIcon /*18*/:
                        this.instrumentId = input.readString();
                        this.hasInstrumentId = true;
                        continue;
                    case com.google.android.play.R.styleable.Theme_actionModeBackground /*26*/:
                        this.userMessageHtml = input.readString();
                        this.hasUserMessageHtml = true;
                        continue;
                    case com.google.android.play.R.styleable.Theme_actionModeFindDrawable /*34*/:
                        int i;
                        int arrayLength = WireFormatNano.getRepeatedFieldArrayLength(input, 34);
                        if (this.errorInputField == null) {
                            i = 0;
                        } else {
                            i = this.errorInputField.length;
                        }
                        InputValidationError[] newArray = new InputValidationError[(i + arrayLength)];
                        if (i != 0) {
                            System.arraycopy(this.errorInputField, 0, newArray, 0, i);
                        }
                        while (i < newArray.length - 1) {
                            newArray[i] = new InputValidationError();
                            input.readMessage(newArray[i]);
                            input.readTag();
                            i++;
                        }
                        newArray[i] = new InputValidationError();
                        input.readMessage(newArray[i]);
                        this.errorInputField = newArray;
                        continue;
                    case com.google.android.play.R.styleable.Theme_dropdownListPreferredItemHeight /*40*/:
                        this.checkoutTokenRequired = input.readBool();
                        this.hasCheckoutTokenRequired = true;
                        continue;
                    case com.google.android.play.R.styleable.Theme_dividerHorizontal /*50*/:
                        if (this.redeemedOffer == null) {
                            this.redeemedOffer = new RedeemedPromoOffer();
                        }
                        input.readMessage(this.redeemedOffer);
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
