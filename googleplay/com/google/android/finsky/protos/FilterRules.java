package com.google.android.finsky.protos;

import com.google.android.finsky.protos.Common.Install;
import com.google.android.finsky.protos.Ownership.OwnershipInfo;
import com.google.android.wallet.instrumentmanager.R;
import com.google.protobuf.nano.CodedInputByteBufferNano;
import com.google.protobuf.nano.CodedOutputByteBufferNano;
import com.google.protobuf.nano.InternalNano;
import com.google.protobuf.nano.MessageNano;
import com.google.protobuf.nano.WireFormatNano;
import java.io.IOException;

public interface FilterRules {

    public static final class Availability extends MessageNano {
        public AvailabilityProblem[] availabilityProblem;
        public boolean availableIfOwned;
        public FilterEvaluationInfo filterInfo;
        public boolean hasAvailableIfOwned;
        public boolean hasHidden;
        public boolean hasOfferType;
        public boolean hasRestriction;
        public boolean hidden;
        public Install[] install;
        public int offerType;
        public OwnershipInfo ownershipInfo;
        public PerDeviceAvailabilityRestriction[] perDeviceAvailabilityRestriction;
        public int restriction;
        public Rule rule;

        public static final class PerDeviceAvailabilityRestriction extends MessageNano {
            private static volatile PerDeviceAvailabilityRestriction[] _emptyArray;
            public long androidId;
            public long channelId;
            public int deviceRestriction;
            public FilterEvaluationInfo filterInfo;
            public boolean hasAndroidId;
            public boolean hasChannelId;
            public boolean hasDeviceRestriction;

            public static PerDeviceAvailabilityRestriction[] emptyArray() {
                if (_emptyArray == null) {
                    synchronized (InternalNano.LAZY_INIT_LOCK) {
                        if (_emptyArray == null) {
                            _emptyArray = new PerDeviceAvailabilityRestriction[0];
                        }
                    }
                }
                return _emptyArray;
            }

            public PerDeviceAvailabilityRestriction() {
                clear();
            }

            public PerDeviceAvailabilityRestriction clear() {
                this.androidId = 0;
                this.hasAndroidId = false;
                this.deviceRestriction = 1;
                this.hasDeviceRestriction = false;
                this.channelId = 0;
                this.hasChannelId = false;
                this.filterInfo = null;
                this.cachedSize = -1;
                return this;
            }

            public void writeTo(CodedOutputByteBufferNano output) throws IOException {
                if (this.hasAndroidId || this.androidId != 0) {
                    output.writeFixed64(10, this.androidId);
                }
                if (this.deviceRestriction != 1 || this.hasDeviceRestriction) {
                    output.writeInt32(11, this.deviceRestriction);
                }
                if (this.hasChannelId || this.channelId != 0) {
                    output.writeInt64(12, this.channelId);
                }
                if (this.filterInfo != null) {
                    output.writeMessage(15, this.filterInfo);
                }
                super.writeTo(output);
            }

            protected int computeSerializedSize() {
                int size = super.computeSerializedSize();
                if (this.hasAndroidId || this.androidId != 0) {
                    size += CodedOutputByteBufferNano.computeFixed64Size(10, this.androidId);
                }
                if (this.deviceRestriction != 1 || this.hasDeviceRestriction) {
                    size += CodedOutputByteBufferNano.computeInt32Size(11, this.deviceRestriction);
                }
                if (this.hasChannelId || this.channelId != 0) {
                    size += CodedOutputByteBufferNano.computeInt64Size(12, this.channelId);
                }
                if (this.filterInfo != null) {
                    return size + CodedOutputByteBufferNano.computeMessageSize(15, this.filterInfo);
                }
                return size;
            }

            public PerDeviceAvailabilityRestriction mergeFrom(CodedInputByteBufferNano input) throws IOException {
                while (true) {
                    int tag = input.readTag();
                    switch (tag) {
                        case R.styleable.WalletImButtonBar_capitalizeButtonText /*0*/:
                            break;
                        case com.google.android.play.R.styleable.Theme_colorControlHighlight /*81*/:
                            this.androidId = input.readFixed64();
                            this.hasAndroidId = true;
                            continue;
                        case 88:
                            int value = input.readInt32();
                            switch (value) {
                                case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                                case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                                case R.styleable.WalletFragmentStyle_maskedWalletDetailsBackground /*6*/:
                                case R.styleable.WalletFragmentStyle_maskedWalletDetailsButtonTextAppearance /*7*/:
                                case R.styleable.WalletFragmentStyle_maskedWalletDetailsButtonBackground /*8*/:
                                case R.styleable.WalletFragmentStyle_maskedWalletDetailsLogoTextColor /*9*/:
                                case R.styleable.WalletFragmentStyle_maskedWalletDetailsLogoImageType /*10*/:
                                case R.styleable.MapAttrs_uiZoomControls /*11*/:
                                case R.styleable.MapAttrs_uiZoomGestures /*12*/:
                                case R.styleable.MapAttrs_useViewLifecycle /*13*/:
                                case R.styleable.MapAttrs_zOrderOnTop /*14*/:
                                case R.styleable.MapAttrs_uiMapToolbar /*15*/:
                                case com.google.android.play.R.styleable.Toolbar_maxButtonHeight /*16*/:
                                case com.google.android.play.R.styleable.Toolbar_theme /*17*/:
                                case com.google.android.play.R.styleable.Toolbar_collapseIcon /*18*/:
                                case com.google.android.play.R.styleable.Toolbar_navigationIcon /*20*/:
                                case com.google.android.play.R.styleable.Toolbar_navigationContentDescription /*21*/:
                                case com.google.android.play.R.styleable.Theme_actionMenuTextAppearance /*22*/:
                                    this.deviceRestriction = value;
                                    this.hasDeviceRestriction = true;
                                    break;
                                default:
                                    continue;
                            }
                        case 96:
                            this.channelId = input.readInt64();
                            this.hasChannelId = true;
                            continue;
                        case 122:
                            if (this.filterInfo == null) {
                                this.filterInfo = new FilterEvaluationInfo();
                            }
                            input.readMessage(this.filterInfo);
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

        public Availability() {
            clear();
        }

        public Availability clear() {
            this.restriction = 1;
            this.hasRestriction = false;
            this.availabilityProblem = AvailabilityProblem.emptyArray();
            this.availableIfOwned = false;
            this.hasAvailableIfOwned = false;
            this.offerType = 1;
            this.hasOfferType = false;
            this.ownershipInfo = null;
            this.hidden = false;
            this.hasHidden = false;
            this.install = Install.emptyArray();
            this.rule = null;
            this.perDeviceAvailabilityRestriction = PerDeviceAvailabilityRestriction.emptyArray();
            this.filterInfo = null;
            this.cachedSize = -1;
            return this;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.restriction != 1 || this.hasRestriction) {
                output.writeInt32(5, this.restriction);
            }
            if (this.offerType != 1 || this.hasOfferType) {
                output.writeInt32(6, this.offerType);
            }
            if (this.rule != null) {
                output.writeMessage(7, this.rule);
            }
            if (this.perDeviceAvailabilityRestriction != null && this.perDeviceAvailabilityRestriction.length > 0) {
                for (PerDeviceAvailabilityRestriction element : this.perDeviceAvailabilityRestriction) {
                    if (element != null) {
                        output.writeGroup(9, element);
                    }
                }
            }
            if (this.hasAvailableIfOwned || this.availableIfOwned) {
                output.writeBool(13, this.availableIfOwned);
            }
            if (this.install != null && this.install.length > 0) {
                for (Install element2 : this.install) {
                    if (element2 != null) {
                        output.writeMessage(14, element2);
                    }
                }
            }
            if (this.filterInfo != null) {
                output.writeMessage(16, this.filterInfo);
            }
            if (this.ownershipInfo != null) {
                output.writeMessage(17, this.ownershipInfo);
            }
            if (this.availabilityProblem != null && this.availabilityProblem.length > 0) {
                for (AvailabilityProblem element3 : this.availabilityProblem) {
                    if (element3 != null) {
                        output.writeMessage(18, element3);
                    }
                }
            }
            if (this.hasHidden || this.hidden) {
                output.writeBool(21, this.hidden);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.restriction != 1 || this.hasRestriction) {
                size += CodedOutputByteBufferNano.computeInt32Size(5, this.restriction);
            }
            if (this.offerType != 1 || this.hasOfferType) {
                size += CodedOutputByteBufferNano.computeInt32Size(6, this.offerType);
            }
            if (this.rule != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(7, this.rule);
            }
            if (this.perDeviceAvailabilityRestriction != null && this.perDeviceAvailabilityRestriction.length > 0) {
                for (PerDeviceAvailabilityRestriction element : this.perDeviceAvailabilityRestriction) {
                    if (element != null) {
                        size += CodedOutputByteBufferNano.computeGroupSize(9, element);
                    }
                }
            }
            if (this.hasAvailableIfOwned || this.availableIfOwned) {
                size += CodedOutputByteBufferNano.computeBoolSize(13, this.availableIfOwned);
            }
            if (this.install != null && this.install.length > 0) {
                for (Install element2 : this.install) {
                    if (element2 != null) {
                        size += CodedOutputByteBufferNano.computeMessageSize(14, element2);
                    }
                }
            }
            if (this.filterInfo != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(16, this.filterInfo);
            }
            if (this.ownershipInfo != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(17, this.ownershipInfo);
            }
            if (this.availabilityProblem != null && this.availabilityProblem.length > 0) {
                for (AvailabilityProblem element3 : this.availabilityProblem) {
                    if (element3 != null) {
                        size += CodedOutputByteBufferNano.computeMessageSize(18, element3);
                    }
                }
            }
            if (this.hasHidden || this.hidden) {
                return size + CodedOutputByteBufferNano.computeBoolSize(21, this.hidden);
            }
            return size;
        }

        public Availability mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                int value;
                int arrayLength;
                int i;
                switch (tag) {
                    case R.styleable.WalletImButtonBar_capitalizeButtonText /*0*/:
                        break;
                    case com.google.android.play.R.styleable.Theme_dropdownListPreferredItemHeight /*40*/:
                        value = input.readInt32();
                        switch (value) {
                            case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                            case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                            case R.styleable.WalletFragmentStyle_maskedWalletDetailsBackground /*6*/:
                            case R.styleable.WalletFragmentStyle_maskedWalletDetailsButtonTextAppearance /*7*/:
                            case R.styleable.WalletFragmentStyle_maskedWalletDetailsButtonBackground /*8*/:
                            case R.styleable.WalletFragmentStyle_maskedWalletDetailsLogoTextColor /*9*/:
                            case R.styleable.WalletFragmentStyle_maskedWalletDetailsLogoImageType /*10*/:
                            case R.styleable.MapAttrs_uiZoomControls /*11*/:
                            case R.styleable.MapAttrs_uiZoomGestures /*12*/:
                            case R.styleable.MapAttrs_useViewLifecycle /*13*/:
                            case R.styleable.MapAttrs_zOrderOnTop /*14*/:
                            case R.styleable.MapAttrs_uiMapToolbar /*15*/:
                            case com.google.android.play.R.styleable.Toolbar_maxButtonHeight /*16*/:
                            case com.google.android.play.R.styleable.Toolbar_theme /*17*/:
                            case com.google.android.play.R.styleable.Toolbar_collapseIcon /*18*/:
                            case com.google.android.play.R.styleable.Toolbar_navigationIcon /*20*/:
                            case com.google.android.play.R.styleable.Toolbar_navigationContentDescription /*21*/:
                            case com.google.android.play.R.styleable.Theme_actionMenuTextAppearance /*22*/:
                                this.restriction = value;
                                this.hasRestriction = true;
                                break;
                            default:
                                continue;
                        }
                    case com.google.android.play.R.styleable.Theme_selectableItemBackgroundBorderless /*48*/:
                        value = input.readInt32();
                        switch (value) {
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
                                this.offerType = value;
                                this.hasOfferType = true;
                                break;
                            default:
                                continue;
                        }
                    case com.google.android.play.R.styleable.Theme_switchStyle /*58*/:
                        if (this.rule == null) {
                            this.rule = new Rule();
                        }
                        input.readMessage(this.rule);
                        continue;
                    case com.google.android.play.R.styleable.Theme_listChoiceBackgroundIndicator /*75*/:
                        arrayLength = WireFormatNano.getRepeatedFieldArrayLength(input, 75);
                        if (this.perDeviceAvailabilityRestriction == null) {
                            i = 0;
                        } else {
                            i = this.perDeviceAvailabilityRestriction.length;
                        }
                        PerDeviceAvailabilityRestriction[] newArray = new PerDeviceAvailabilityRestriction[(i + arrayLength)];
                        if (i != 0) {
                            System.arraycopy(this.perDeviceAvailabilityRestriction, 0, newArray, 0, i);
                        }
                        while (i < newArray.length - 1) {
                            newArray[i] = new PerDeviceAvailabilityRestriction();
                            input.readGroup(newArray[i], 9);
                            input.readTag();
                            i++;
                        }
                        newArray[i] = new PerDeviceAvailabilityRestriction();
                        input.readGroup(newArray[i], 9);
                        this.perDeviceAvailabilityRestriction = newArray;
                        continue;
                    case 104:
                        this.availableIfOwned = input.readBool();
                        this.hasAvailableIfOwned = true;
                        continue;
                    case 114:
                        arrayLength = WireFormatNano.getRepeatedFieldArrayLength(input, 114);
                        if (this.install == null) {
                            i = 0;
                        } else {
                            i = this.install.length;
                        }
                        Install[] newArray2 = new Install[(i + arrayLength)];
                        if (i != 0) {
                            System.arraycopy(this.install, 0, newArray2, 0, i);
                        }
                        while (i < newArray2.length - 1) {
                            newArray2[i] = new Install();
                            input.readMessage(newArray2[i]);
                            input.readTag();
                            i++;
                        }
                        newArray2[i] = new Install();
                        input.readMessage(newArray2[i]);
                        this.install = newArray2;
                        continue;
                    case 130:
                        if (this.filterInfo == null) {
                            this.filterInfo = new FilterEvaluationInfo();
                        }
                        input.readMessage(this.filterInfo);
                        continue;
                    case 138:
                        if (this.ownershipInfo == null) {
                            this.ownershipInfo = new OwnershipInfo();
                        }
                        input.readMessage(this.ownershipInfo);
                        continue;
                    case 146:
                        arrayLength = WireFormatNano.getRepeatedFieldArrayLength(input, 146);
                        if (this.availabilityProblem == null) {
                            i = 0;
                        } else {
                            i = this.availabilityProblem.length;
                        }
                        AvailabilityProblem[] newArray3 = new AvailabilityProblem[(i + arrayLength)];
                        if (i != 0) {
                            System.arraycopy(this.availabilityProblem, 0, newArray3, 0, i);
                        }
                        while (i < newArray3.length - 1) {
                            newArray3[i] = new AvailabilityProblem();
                            input.readMessage(newArray3[i]);
                            input.readTag();
                            i++;
                        }
                        newArray3[i] = new AvailabilityProblem();
                        input.readMessage(newArray3[i]);
                        this.availabilityProblem = newArray3;
                        continue;
                    case 168:
                        this.hidden = input.readBool();
                        this.hasHidden = true;
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

    public static final class AvailabilityProblem extends MessageNano {
        private static volatile AvailabilityProblem[] _emptyArray;
        public boolean hasProblemType;
        public String[] missingValue;
        public int problemType;

        public static AvailabilityProblem[] emptyArray() {
            if (_emptyArray == null) {
                synchronized (InternalNano.LAZY_INIT_LOCK) {
                    if (_emptyArray == null) {
                        _emptyArray = new AvailabilityProblem[0];
                    }
                }
            }
            return _emptyArray;
        }

        public AvailabilityProblem() {
            clear();
        }

        public AvailabilityProblem clear() {
            this.problemType = 1;
            this.hasProblemType = false;
            this.missingValue = WireFormatNano.EMPTY_STRING_ARRAY;
            this.cachedSize = -1;
            return this;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.problemType != 1 || this.hasProblemType) {
                output.writeInt32(1, this.problemType);
            }
            if (this.missingValue != null && this.missingValue.length > 0) {
                for (String element : this.missingValue) {
                    if (element != null) {
                        output.writeString(2, element);
                    }
                }
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.problemType != 1 || this.hasProblemType) {
                size += CodedOutputByteBufferNano.computeInt32Size(1, this.problemType);
            }
            if (this.missingValue == null || this.missingValue.length <= 0) {
                return size;
            }
            int dataCount = 0;
            int dataSize = 0;
            for (String element : this.missingValue) {
                if (element != null) {
                    dataCount++;
                    dataSize += CodedOutputByteBufferNano.computeStringSizeNoTag(element);
                }
            }
            return (size + dataSize) + (dataCount * 1);
        }

        public AvailabilityProblem mergeFrom(CodedInputByteBufferNano input) throws IOException {
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
                            case R.styleable.WalletImFormEditText_validateWhenNotVisible /*5*/:
                            case R.styleable.WalletFragmentStyle_maskedWalletDetailsBackground /*6*/:
                            case R.styleable.WalletFragmentStyle_maskedWalletDetailsButtonTextAppearance /*7*/:
                            case R.styleable.WalletFragmentStyle_maskedWalletDetailsButtonBackground /*8*/:
                                this.problemType = value;
                                this.hasProblemType = true;
                                break;
                            default:
                                continue;
                        }
                    case com.google.android.play.R.styleable.Toolbar_collapseIcon /*18*/:
                        int arrayLength = WireFormatNano.getRepeatedFieldArrayLength(input, 18);
                        int i = this.missingValue == null ? 0 : this.missingValue.length;
                        String[] newArray = new String[(i + arrayLength)];
                        if (i != 0) {
                            System.arraycopy(this.missingValue, 0, newArray, 0, i);
                        }
                        while (i < newArray.length - 1) {
                            newArray[i] = input.readString();
                            input.readTag();
                            i++;
                        }
                        newArray[i] = input.readString();
                        this.missingValue = newArray;
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

    public static final class FilterEvaluationInfo extends MessageNano {
        public RuleEvaluation[] ruleEvaluation;

        public FilterEvaluationInfo() {
            clear();
        }

        public FilterEvaluationInfo clear() {
            this.ruleEvaluation = RuleEvaluation.emptyArray();
            this.cachedSize = -1;
            return this;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.ruleEvaluation != null && this.ruleEvaluation.length > 0) {
                for (RuleEvaluation element : this.ruleEvaluation) {
                    if (element != null) {
                        output.writeMessage(1, element);
                    }
                }
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.ruleEvaluation != null && this.ruleEvaluation.length > 0) {
                for (RuleEvaluation element : this.ruleEvaluation) {
                    if (element != null) {
                        size += CodedOutputByteBufferNano.computeMessageSize(1, element);
                    }
                }
            }
            return size;
        }

        public FilterEvaluationInfo mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case R.styleable.WalletImButtonBar_capitalizeButtonText /*0*/:
                        break;
                    case R.styleable.WalletFragmentStyle_maskedWalletDetailsLogoImageType /*10*/:
                        int i;
                        int arrayLength = WireFormatNano.getRepeatedFieldArrayLength(input, 10);
                        if (this.ruleEvaluation == null) {
                            i = 0;
                        } else {
                            i = this.ruleEvaluation.length;
                        }
                        RuleEvaluation[] newArray = new RuleEvaluation[(i + arrayLength)];
                        if (i != 0) {
                            System.arraycopy(this.ruleEvaluation, 0, newArray, 0, i);
                        }
                        while (i < newArray.length - 1) {
                            newArray[i] = new RuleEvaluation();
                            input.readMessage(newArray[i]);
                            input.readTag();
                            i++;
                        }
                        newArray[i] = new RuleEvaluation();
                        input.readMessage(newArray[i]);
                        this.ruleEvaluation = newArray;
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

    public static final class Rule extends MessageNano {
        private static volatile Rule[] _emptyArray;
        public int availabilityProblemType;
        public String comment;
        public int[] constArg;
        public double[] doubleArg;
        public boolean hasAvailabilityProblemType;
        public boolean hasComment;
        public boolean hasIncludeMissingValues;
        public boolean hasKey;
        public boolean hasNegate;
        public boolean hasOperator;
        public boolean hasResponseCode;
        public boolean includeMissingValues;
        public int key;
        public long[] longArg;
        public boolean negate;
        public int operator;
        public int responseCode;
        public String[] stringArg;
        public long[] stringArgHash;
        public Rule[] subrule;

        public static Rule[] emptyArray() {
            if (_emptyArray == null) {
                synchronized (InternalNano.LAZY_INIT_LOCK) {
                    if (_emptyArray == null) {
                        _emptyArray = new Rule[0];
                    }
                }
            }
            return _emptyArray;
        }

        public Rule() {
            clear();
        }

        public Rule clear() {
            this.negate = false;
            this.hasNegate = false;
            this.operator = 1;
            this.hasOperator = false;
            this.key = 1;
            this.hasKey = false;
            this.stringArg = WireFormatNano.EMPTY_STRING_ARRAY;
            this.stringArgHash = WireFormatNano.EMPTY_LONG_ARRAY;
            this.longArg = WireFormatNano.EMPTY_LONG_ARRAY;
            this.doubleArg = WireFormatNano.EMPTY_DOUBLE_ARRAY;
            this.constArg = WireFormatNano.EMPTY_INT_ARRAY;
            this.subrule = emptyArray();
            this.responseCode = 1;
            this.hasResponseCode = false;
            this.availabilityProblemType = 1;
            this.hasAvailabilityProblemType = false;
            this.includeMissingValues = false;
            this.hasIncludeMissingValues = false;
            this.comment = "";
            this.hasComment = false;
            this.cachedSize = -1;
            return this;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.hasNegate || this.negate) {
                output.writeBool(1, this.negate);
            }
            if (this.operator != 1 || this.hasOperator) {
                output.writeInt32(2, this.operator);
            }
            if (this.key != 1 || this.hasKey) {
                output.writeInt32(3, this.key);
            }
            if (this.stringArg != null && this.stringArg.length > 0) {
                for (String element : this.stringArg) {
                    if (element != null) {
                        output.writeString(4, element);
                    }
                }
            }
            if (this.longArg != null && this.longArg.length > 0) {
                for (long writeInt64 : this.longArg) {
                    output.writeInt64(5, writeInt64);
                }
            }
            if (this.doubleArg != null && this.doubleArg.length > 0) {
                for (double writeDouble : this.doubleArg) {
                    output.writeDouble(6, writeDouble);
                }
            }
            if (this.subrule != null && this.subrule.length > 0) {
                for (Rule element2 : this.subrule) {
                    if (element2 != null) {
                        output.writeMessage(7, element2);
                    }
                }
            }
            if (this.responseCode != 1 || this.hasResponseCode) {
                output.writeInt32(8, this.responseCode);
            }
            if (this.hasComment || !this.comment.equals("")) {
                output.writeString(9, this.comment);
            }
            if (this.stringArgHash != null && this.stringArgHash.length > 0) {
                for (long writeInt642 : this.stringArgHash) {
                    output.writeFixed64(10, writeInt642);
                }
            }
            if (this.constArg != null && this.constArg.length > 0) {
                for (int writeInt32 : this.constArg) {
                    output.writeInt32(11, writeInt32);
                }
            }
            if (this.availabilityProblemType != 1 || this.hasAvailabilityProblemType) {
                output.writeInt32(12, this.availabilityProblemType);
            }
            if (this.hasIncludeMissingValues || this.includeMissingValues) {
                output.writeBool(13, this.includeMissingValues);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int dataSize;
            int size = super.computeSerializedSize();
            if (this.hasNegate || this.negate) {
                size += CodedOutputByteBufferNano.computeBoolSize(1, this.negate);
            }
            if (this.operator != 1 || this.hasOperator) {
                size += CodedOutputByteBufferNano.computeInt32Size(2, this.operator);
            }
            if (this.key != 1 || this.hasKey) {
                size += CodedOutputByteBufferNano.computeInt32Size(3, this.key);
            }
            if (this.stringArg != null && this.stringArg.length > 0) {
                int dataCount = 0;
                dataSize = 0;
                for (String element : this.stringArg) {
                    if (element != null) {
                        dataCount++;
                        dataSize += CodedOutputByteBufferNano.computeStringSizeNoTag(element);
                    }
                }
                size = (size + dataSize) + (dataCount * 1);
            }
            if (this.longArg != null && this.longArg.length > 0) {
                dataSize = 0;
                for (long element2 : this.longArg) {
                    dataSize += CodedOutputByteBufferNano.computeInt64SizeNoTag(element2);
                }
                size = (size + dataSize) + (this.longArg.length * 1);
            }
            if (this.doubleArg != null && this.doubleArg.length > 0) {
                size = (size + (this.doubleArg.length * 8)) + (this.doubleArg.length * 1);
            }
            if (this.subrule != null && this.subrule.length > 0) {
                for (Rule element3 : this.subrule) {
                    if (element3 != null) {
                        size += CodedOutputByteBufferNano.computeMessageSize(7, element3);
                    }
                }
            }
            if (this.responseCode != 1 || this.hasResponseCode) {
                size += CodedOutputByteBufferNano.computeInt32Size(8, this.responseCode);
            }
            if (this.hasComment || !this.comment.equals("")) {
                size += CodedOutputByteBufferNano.computeStringSize(9, this.comment);
            }
            if (this.stringArgHash != null && this.stringArgHash.length > 0) {
                size = (size + (this.stringArgHash.length * 8)) + (this.stringArgHash.length * 1);
            }
            if (this.constArg != null && this.constArg.length > 0) {
                dataSize = 0;
                for (int element4 : this.constArg) {
                    dataSize += CodedOutputByteBufferNano.computeInt32SizeNoTag(element4);
                }
                size = (size + dataSize) + (this.constArg.length * 1);
            }
            if (this.availabilityProblemType != 1 || this.hasAvailabilityProblemType) {
                size += CodedOutputByteBufferNano.computeInt32Size(12, this.availabilityProblemType);
            }
            if (this.hasIncludeMissingValues || this.includeMissingValues) {
                return size + CodedOutputByteBufferNano.computeBoolSize(13, this.includeMissingValues);
            }
            return size;
        }

        public Rule mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                int value;
                int arrayLength;
                int i;
                long[] newArray;
                int limit;
                int startPos;
                double[] newArray2;
                int length;
                int[] newArray3;
                switch (tag) {
                    case R.styleable.WalletImButtonBar_capitalizeButtonText /*0*/:
                        break;
                    case R.styleable.WalletFragmentStyle_maskedWalletDetailsButtonBackground /*8*/:
                        this.negate = input.readBool();
                        this.hasNegate = true;
                        continue;
                    case com.google.android.play.R.styleable.Toolbar_maxButtonHeight /*16*/:
                        value = input.readInt32();
                        switch (value) {
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
                            case R.styleable.MapAttrs_useViewLifecycle /*13*/:
                            case R.styleable.MapAttrs_zOrderOnTop /*14*/:
                            case R.styleable.MapAttrs_uiMapToolbar /*15*/:
                            case com.google.android.play.R.styleable.Toolbar_maxButtonHeight /*16*/:
                                this.operator = value;
                                this.hasOperator = true;
                                break;
                            default:
                                continue;
                        }
                    case com.google.android.play.R.styleable.Theme_actionModeStyle /*24*/:
                        value = input.readInt32();
                        switch (value) {
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
                            case R.styleable.MapAttrs_useViewLifecycle /*13*/:
                            case R.styleable.MapAttrs_zOrderOnTop /*14*/:
                            case R.styleable.MapAttrs_uiMapToolbar /*15*/:
                            case com.google.android.play.R.styleable.Toolbar_maxButtonHeight /*16*/:
                            case com.google.android.play.R.styleable.Toolbar_theme /*17*/:
                            case com.google.android.play.R.styleable.Toolbar_collapseIcon /*18*/:
                            case com.google.android.play.R.styleable.Toolbar_collapseContentDescription /*19*/:
                            case com.google.android.play.R.styleable.Toolbar_navigationIcon /*20*/:
                            case com.google.android.play.R.styleable.Toolbar_navigationContentDescription /*21*/:
                            case com.google.android.play.R.styleable.Theme_actionMenuTextAppearance /*22*/:
                            case com.google.android.play.R.styleable.Theme_actionMenuTextColor /*23*/:
                            case com.google.android.play.R.styleable.Theme_actionModeCloseButtonStyle /*25*/:
                            case com.google.android.play.R.styleable.Theme_actionModeBackground /*26*/:
                            case com.google.android.play.R.styleable.Theme_actionModeSplitBackground /*27*/:
                            case com.google.android.play.R.styleable.Theme_actionModeCloseDrawable /*28*/:
                            case com.google.android.play.R.styleable.Theme_actionModeCutDrawable /*29*/:
                            case com.google.android.play.R.styleable.Theme_actionModeCopyDrawable /*30*/:
                            case com.google.android.play.R.styleable.Theme_actionModePasteDrawable /*31*/:
                            case com.google.android.play.R.styleable.Theme_actionModeSelectAllDrawable /*32*/:
                            case com.google.android.play.R.styleable.Theme_actionModeShareDrawable /*33*/:
                            case com.google.android.play.R.styleable.Theme_actionModeFindDrawable /*34*/:
                            case com.google.android.play.R.styleable.Theme_actionModeWebSearchDrawable /*35*/:
                                this.key = value;
                                this.hasKey = true;
                                break;
                            default:
                                continue;
                        }
                    case com.google.android.play.R.styleable.Theme_actionModeFindDrawable /*34*/:
                        arrayLength = WireFormatNano.getRepeatedFieldArrayLength(input, 34);
                        i = this.stringArg == null ? 0 : this.stringArg.length;
                        String[] newArray4 = new String[(i + arrayLength)];
                        if (i != 0) {
                            System.arraycopy(this.stringArg, 0, newArray4, 0, i);
                        }
                        while (i < newArray4.length - 1) {
                            newArray4[i] = input.readString();
                            input.readTag();
                            i++;
                        }
                        newArray4[i] = input.readString();
                        this.stringArg = newArray4;
                        continue;
                    case com.google.android.play.R.styleable.Theme_dropdownListPreferredItemHeight /*40*/:
                        arrayLength = WireFormatNano.getRepeatedFieldArrayLength(input, 40);
                        i = this.longArg == null ? 0 : this.longArg.length;
                        newArray = new long[(i + arrayLength)];
                        if (i != 0) {
                            System.arraycopy(this.longArg, 0, newArray, 0, i);
                        }
                        while (i < newArray.length - 1) {
                            newArray[i] = input.readInt64();
                            input.readTag();
                            i++;
                        }
                        newArray[i] = input.readInt64();
                        this.longArg = newArray;
                        continue;
                    case com.google.android.play.R.styleable.Theme_spinnerDropDownItemStyle /*42*/:
                        limit = input.pushLimit(input.readRawVarint32());
                        arrayLength = 0;
                        startPos = input.getPosition();
                        while (input.getBytesUntilLimit() > 0) {
                            input.readInt64();
                            arrayLength++;
                        }
                        input.rewindToPosition(startPos);
                        i = this.longArg == null ? 0 : this.longArg.length;
                        newArray = new long[(i + arrayLength)];
                        if (i != 0) {
                            System.arraycopy(this.longArg, 0, newArray, 0, i);
                        }
                        while (i < newArray.length) {
                            newArray[i] = input.readInt64();
                            i++;
                        }
                        this.longArg = newArray;
                        input.popLimit(limit);
                        continue;
                    case com.google.android.play.R.styleable.Theme_dividerVertical /*49*/:
                        arrayLength = WireFormatNano.getRepeatedFieldArrayLength(input, 49);
                        i = this.doubleArg == null ? 0 : this.doubleArg.length;
                        newArray2 = new double[(i + arrayLength)];
                        if (i != 0) {
                            System.arraycopy(this.doubleArg, 0, newArray2, 0, i);
                        }
                        while (i < newArray2.length - 1) {
                            newArray2[i] = input.readDouble();
                            input.readTag();
                            i++;
                        }
                        newArray2[i] = input.readDouble();
                        this.doubleArg = newArray2;
                        continue;
                    case com.google.android.play.R.styleable.Theme_dividerHorizontal /*50*/:
                        length = input.readRawVarint32();
                        limit = input.pushLimit(length);
                        arrayLength = length / 8;
                        i = this.doubleArg == null ? 0 : this.doubleArg.length;
                        newArray2 = new double[(i + arrayLength)];
                        if (i != 0) {
                            System.arraycopy(this.doubleArg, 0, newArray2, 0, i);
                        }
                        while (i < newArray2.length) {
                            newArray2[i] = input.readDouble();
                            i++;
                        }
                        this.doubleArg = newArray2;
                        input.popLimit(limit);
                        continue;
                    case com.google.android.play.R.styleable.Theme_switchStyle /*58*/:
                        arrayLength = WireFormatNano.getRepeatedFieldArrayLength(input, 58);
                        if (this.subrule == null) {
                            i = 0;
                        } else {
                            i = this.subrule.length;
                        }
                        Rule[] newArray5 = new Rule[(i + arrayLength)];
                        if (i != 0) {
                            System.arraycopy(this.subrule, 0, newArray5, 0, i);
                        }
                        while (i < newArray5.length - 1) {
                            newArray5[i] = new Rule();
                            input.readMessage(newArray5[i]);
                            input.readTag();
                            i++;
                        }
                        newArray5[i] = new Rule();
                        input.readMessage(newArray5[i]);
                        this.subrule = newArray5;
                        continue;
                    case com.google.android.play.R.styleable.Theme_listPreferredItemHeightSmall /*64*/:
                        value = input.readInt32();
                        switch (value) {
                            case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                            case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                            case R.styleable.WalletFragmentStyle_maskedWalletDetailsBackground /*6*/:
                            case R.styleable.WalletFragmentStyle_maskedWalletDetailsButtonTextAppearance /*7*/:
                            case R.styleable.WalletFragmentStyle_maskedWalletDetailsButtonBackground /*8*/:
                            case R.styleable.WalletFragmentStyle_maskedWalletDetailsLogoTextColor /*9*/:
                            case R.styleable.WalletFragmentStyle_maskedWalletDetailsLogoImageType /*10*/:
                            case R.styleable.MapAttrs_uiZoomControls /*11*/:
                            case R.styleable.MapAttrs_uiZoomGestures /*12*/:
                            case R.styleable.MapAttrs_useViewLifecycle /*13*/:
                            case R.styleable.MapAttrs_zOrderOnTop /*14*/:
                            case R.styleable.MapAttrs_uiMapToolbar /*15*/:
                            case com.google.android.play.R.styleable.Toolbar_maxButtonHeight /*16*/:
                            case com.google.android.play.R.styleable.Toolbar_theme /*17*/:
                            case com.google.android.play.R.styleable.Toolbar_collapseIcon /*18*/:
                            case com.google.android.play.R.styleable.Toolbar_navigationIcon /*20*/:
                            case com.google.android.play.R.styleable.Toolbar_navigationContentDescription /*21*/:
                            case com.google.android.play.R.styleable.Theme_actionMenuTextAppearance /*22*/:
                                this.responseCode = value;
                                this.hasResponseCode = true;
                                break;
                            default:
                                continue;
                        }
                    case com.google.android.play.R.styleable.Theme_panelMenuListTheme /*74*/:
                        this.comment = input.readString();
                        this.hasComment = true;
                        continue;
                    case com.google.android.play.R.styleable.Theme_colorControlHighlight /*81*/:
                        arrayLength = WireFormatNano.getRepeatedFieldArrayLength(input, 81);
                        i = this.stringArgHash == null ? 0 : this.stringArgHash.length;
                        newArray = new long[(i + arrayLength)];
                        if (i != 0) {
                            System.arraycopy(this.stringArgHash, 0, newArray, 0, i);
                        }
                        while (i < newArray.length - 1) {
                            newArray[i] = input.readFixed64();
                            input.readTag();
                            i++;
                        }
                        newArray[i] = input.readFixed64();
                        this.stringArgHash = newArray;
                        continue;
                    case com.google.android.play.R.styleable.Theme_colorButtonNormal /*82*/:
                        length = input.readRawVarint32();
                        limit = input.pushLimit(length);
                        arrayLength = length / 8;
                        i = this.stringArgHash == null ? 0 : this.stringArgHash.length;
                        newArray = new long[(i + arrayLength)];
                        if (i != 0) {
                            System.arraycopy(this.stringArgHash, 0, newArray, 0, i);
                        }
                        while (i < newArray.length) {
                            newArray[i] = input.readFixed64();
                            i++;
                        }
                        this.stringArgHash = newArray;
                        input.popLimit(limit);
                        continue;
                    case 88:
                        length = WireFormatNano.getRepeatedFieldArrayLength(input, 88);
                        int[] validValues = new int[length];
                        i = 0;
                        int validCount = 0;
                        while (i < length) {
                            int validCount2;
                            if (i != 0) {
                                input.readTag();
                            }
                            value = input.readInt32();
                            switch (value) {
                                case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                                case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                                case R.styleable.WalletImFormEditText_requiredErrorString /*3*/:
                                case R.styleable.WalletImFormEditText_required /*4*/:
                                    validCount2 = validCount + 1;
                                    validValues[validCount] = value;
                                    break;
                                default:
                                    validCount2 = validCount;
                                    break;
                            }
                            i++;
                            validCount = validCount2;
                        }
                        if (validCount != 0) {
                            i = this.constArg == null ? 0 : this.constArg.length;
                            if (i != 0 || validCount != validValues.length) {
                                newArray3 = new int[(i + validCount)];
                                if (i != 0) {
                                    System.arraycopy(this.constArg, 0, newArray3, 0, i);
                                }
                                System.arraycopy(validValues, 0, newArray3, i, validCount);
                                this.constArg = newArray3;
                                break;
                            }
                            this.constArg = validValues;
                            break;
                        }
                        continue;
                    case 90:
                        limit = input.pushLimit(input.readRawVarint32());
                        arrayLength = 0;
                        startPos = input.getPosition();
                        while (input.getBytesUntilLimit() > 0) {
                            switch (input.readInt32()) {
                                case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                                case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                                case R.styleable.WalletImFormEditText_requiredErrorString /*3*/:
                                case R.styleable.WalletImFormEditText_required /*4*/:
                                    arrayLength++;
                                    break;
                                default:
                                    break;
                            }
                        }
                        if (arrayLength != 0) {
                            input.rewindToPosition(startPos);
                            if (this.constArg == null) {
                                i = 0;
                            } else {
                                i = this.constArg.length;
                            }
                            newArray3 = new int[(i + arrayLength)];
                            if (i != 0) {
                                System.arraycopy(this.constArg, 0, newArray3, 0, i);
                            }
                            while (input.getBytesUntilLimit() > 0) {
                                value = input.readInt32();
                                switch (value) {
                                    case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                                    case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                                    case R.styleable.WalletImFormEditText_requiredErrorString /*3*/:
                                    case R.styleable.WalletImFormEditText_required /*4*/:
                                        int i2 = i + 1;
                                        newArray3[i] = value;
                                        i = i2;
                                        break;
                                    default:
                                        break;
                                }
                            }
                            this.constArg = newArray3;
                        }
                        input.popLimit(limit);
                        continue;
                    case 96:
                        value = input.readInt32();
                        switch (value) {
                            case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                            case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                            case R.styleable.WalletImFormEditText_requiredErrorString /*3*/:
                            case R.styleable.WalletImFormEditText_required /*4*/:
                            case R.styleable.WalletImFormEditText_validateWhenNotVisible /*5*/:
                            case R.styleable.WalletFragmentStyle_maskedWalletDetailsBackground /*6*/:
                            case R.styleable.WalletFragmentStyle_maskedWalletDetailsButtonTextAppearance /*7*/:
                            case R.styleable.WalletFragmentStyle_maskedWalletDetailsButtonBackground /*8*/:
                                this.availabilityProblemType = value;
                                this.hasAvailabilityProblemType = true;
                                break;
                            default:
                                continue;
                        }
                    case 104:
                        this.includeMissingValues = input.readBool();
                        this.hasIncludeMissingValues = true;
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

    public static final class RuleEvaluation extends MessageNano {
        private static volatile RuleEvaluation[] _emptyArray;
        public boolean[] actualBoolValue;
        public double[] actualDoubleValue;
        public long[] actualLongValue;
        public String[] actualStringValue;
        public Rule rule;

        public static RuleEvaluation[] emptyArray() {
            if (_emptyArray == null) {
                synchronized (InternalNano.LAZY_INIT_LOCK) {
                    if (_emptyArray == null) {
                        _emptyArray = new RuleEvaluation[0];
                    }
                }
            }
            return _emptyArray;
        }

        public RuleEvaluation() {
            clear();
        }

        public RuleEvaluation clear() {
            this.rule = null;
            this.actualStringValue = WireFormatNano.EMPTY_STRING_ARRAY;
            this.actualLongValue = WireFormatNano.EMPTY_LONG_ARRAY;
            this.actualBoolValue = WireFormatNano.EMPTY_BOOLEAN_ARRAY;
            this.actualDoubleValue = WireFormatNano.EMPTY_DOUBLE_ARRAY;
            this.cachedSize = -1;
            return this;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.rule != null) {
                output.writeMessage(1, this.rule);
            }
            if (this.actualStringValue != null && this.actualStringValue.length > 0) {
                for (String element : this.actualStringValue) {
                    if (element != null) {
                        output.writeString(2, element);
                    }
                }
            }
            if (this.actualLongValue != null && this.actualLongValue.length > 0) {
                for (long writeInt64 : this.actualLongValue) {
                    output.writeInt64(3, writeInt64);
                }
            }
            if (this.actualBoolValue != null && this.actualBoolValue.length > 0) {
                for (boolean writeBool : this.actualBoolValue) {
                    output.writeBool(4, writeBool);
                }
            }
            if (this.actualDoubleValue != null && this.actualDoubleValue.length > 0) {
                for (double writeDouble : this.actualDoubleValue) {
                    output.writeDouble(5, writeDouble);
                }
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int dataSize;
            int size = super.computeSerializedSize();
            if (this.rule != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(1, this.rule);
            }
            if (this.actualStringValue != null && this.actualStringValue.length > 0) {
                int dataCount = 0;
                dataSize = 0;
                for (String element : this.actualStringValue) {
                    if (element != null) {
                        dataCount++;
                        dataSize += CodedOutputByteBufferNano.computeStringSizeNoTag(element);
                    }
                }
                size = (size + dataSize) + (dataCount * 1);
            }
            if (this.actualLongValue != null && this.actualLongValue.length > 0) {
                dataSize = 0;
                for (long element2 : this.actualLongValue) {
                    dataSize += CodedOutputByteBufferNano.computeInt64SizeNoTag(element2);
                }
                size = (size + dataSize) + (this.actualLongValue.length * 1);
            }
            if (this.actualBoolValue != null && this.actualBoolValue.length > 0) {
                size = (size + (this.actualBoolValue.length * 1)) + (this.actualBoolValue.length * 1);
            }
            if (this.actualDoubleValue == null || this.actualDoubleValue.length <= 0) {
                return size;
            }
            return (size + (this.actualDoubleValue.length * 8)) + (this.actualDoubleValue.length * 1);
        }

        public RuleEvaluation mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                int arrayLength;
                int i;
                long[] newArray;
                int limit;
                int startPos;
                boolean[] newArray2;
                double[] newArray3;
                switch (tag) {
                    case R.styleable.WalletImButtonBar_capitalizeButtonText /*0*/:
                        break;
                    case R.styleable.WalletFragmentStyle_maskedWalletDetailsLogoImageType /*10*/:
                        if (this.rule == null) {
                            this.rule = new Rule();
                        }
                        input.readMessage(this.rule);
                        continue;
                    case com.google.android.play.R.styleable.Toolbar_collapseIcon /*18*/:
                        arrayLength = WireFormatNano.getRepeatedFieldArrayLength(input, 18);
                        i = this.actualStringValue == null ? 0 : this.actualStringValue.length;
                        String[] newArray4 = new String[(i + arrayLength)];
                        if (i != 0) {
                            System.arraycopy(this.actualStringValue, 0, newArray4, 0, i);
                        }
                        while (i < newArray4.length - 1) {
                            newArray4[i] = input.readString();
                            input.readTag();
                            i++;
                        }
                        newArray4[i] = input.readString();
                        this.actualStringValue = newArray4;
                        continue;
                    case com.google.android.play.R.styleable.Theme_actionModeStyle /*24*/:
                        arrayLength = WireFormatNano.getRepeatedFieldArrayLength(input, 24);
                        i = this.actualLongValue == null ? 0 : this.actualLongValue.length;
                        newArray = new long[(i + arrayLength)];
                        if (i != 0) {
                            System.arraycopy(this.actualLongValue, 0, newArray, 0, i);
                        }
                        while (i < newArray.length - 1) {
                            newArray[i] = input.readInt64();
                            input.readTag();
                            i++;
                        }
                        newArray[i] = input.readInt64();
                        this.actualLongValue = newArray;
                        continue;
                    case com.google.android.play.R.styleable.Theme_actionModeBackground /*26*/:
                        limit = input.pushLimit(input.readRawVarint32());
                        arrayLength = 0;
                        startPos = input.getPosition();
                        while (input.getBytesUntilLimit() > 0) {
                            input.readInt64();
                            arrayLength++;
                        }
                        input.rewindToPosition(startPos);
                        i = this.actualLongValue == null ? 0 : this.actualLongValue.length;
                        newArray = new long[(i + arrayLength)];
                        if (i != 0) {
                            System.arraycopy(this.actualLongValue, 0, newArray, 0, i);
                        }
                        while (i < newArray.length) {
                            newArray[i] = input.readInt64();
                            i++;
                        }
                        this.actualLongValue = newArray;
                        input.popLimit(limit);
                        continue;
                    case com.google.android.play.R.styleable.Theme_actionModeSelectAllDrawable /*32*/:
                        arrayLength = WireFormatNano.getRepeatedFieldArrayLength(input, 32);
                        i = this.actualBoolValue == null ? 0 : this.actualBoolValue.length;
                        newArray2 = new boolean[(i + arrayLength)];
                        if (i != 0) {
                            System.arraycopy(this.actualBoolValue, 0, newArray2, 0, i);
                        }
                        while (i < newArray2.length - 1) {
                            newArray2[i] = input.readBool();
                            input.readTag();
                            i++;
                        }
                        newArray2[i] = input.readBool();
                        this.actualBoolValue = newArray2;
                        continue;
                    case com.google.android.play.R.styleable.Theme_actionModeFindDrawable /*34*/:
                        limit = input.pushLimit(input.readRawVarint32());
                        arrayLength = 0;
                        startPos = input.getPosition();
                        while (input.getBytesUntilLimit() > 0) {
                            input.readBool();
                            arrayLength++;
                        }
                        input.rewindToPosition(startPos);
                        i = this.actualBoolValue == null ? 0 : this.actualBoolValue.length;
                        newArray2 = new boolean[(i + arrayLength)];
                        if (i != 0) {
                            System.arraycopy(this.actualBoolValue, 0, newArray2, 0, i);
                        }
                        while (i < newArray2.length) {
                            newArray2[i] = input.readBool();
                            i++;
                        }
                        this.actualBoolValue = newArray2;
                        input.popLimit(limit);
                        continue;
                    case com.google.android.play.R.styleable.Theme_spinnerStyle /*41*/:
                        arrayLength = WireFormatNano.getRepeatedFieldArrayLength(input, 41);
                        i = this.actualDoubleValue == null ? 0 : this.actualDoubleValue.length;
                        newArray3 = new double[(i + arrayLength)];
                        if (i != 0) {
                            System.arraycopy(this.actualDoubleValue, 0, newArray3, 0, i);
                        }
                        while (i < newArray3.length - 1) {
                            newArray3[i] = input.readDouble();
                            input.readTag();
                            i++;
                        }
                        newArray3[i] = input.readDouble();
                        this.actualDoubleValue = newArray3;
                        continue;
                    case com.google.android.play.R.styleable.Theme_spinnerDropDownItemStyle /*42*/:
                        int length = input.readRawVarint32();
                        limit = input.pushLimit(length);
                        arrayLength = length / 8;
                        i = this.actualDoubleValue == null ? 0 : this.actualDoubleValue.length;
                        newArray3 = new double[(i + arrayLength)];
                        if (i != 0) {
                            System.arraycopy(this.actualDoubleValue, 0, newArray3, 0, i);
                        }
                        while (i < newArray3.length) {
                            newArray3[i] = input.readDouble();
                            i++;
                        }
                        this.actualDoubleValue = newArray3;
                        input.popLimit(limit);
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
