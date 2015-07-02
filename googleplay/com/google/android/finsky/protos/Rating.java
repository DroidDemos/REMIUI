package com.google.android.finsky.protos;

import com.google.android.wallet.instrumentmanager.R;
import com.google.protobuf.nano.CodedInputByteBufferNano;
import com.google.protobuf.nano.CodedOutputByteBufferNano;
import com.google.protobuf.nano.MessageNano;
import com.google.protobuf.nano.WireFormatNano;
import java.io.IOException;

public interface Rating {

    public static final class AggregateRating extends MessageNano {
        public double bayesianMeanRating;
        public long commentCount;
        public long fiveStarRatings;
        public long fourStarRatings;
        public boolean hasBayesianMeanRating;
        public boolean hasCommentCount;
        public boolean hasFiveStarRatings;
        public boolean hasFourStarRatings;
        public boolean hasOneStarRatings;
        public boolean hasRatingsCount;
        public boolean hasStarRating;
        public boolean hasThreeStarRatings;
        public boolean hasThumbsDownCount;
        public boolean hasThumbsUpCount;
        public boolean hasTwoStarRatings;
        public boolean hasType;
        public long oneStarRatings;
        public long ratingsCount;
        public float starRating;
        public long threeStarRatings;
        public long thumbsDownCount;
        public long thumbsUpCount;
        public long twoStarRatings;
        public int type;

        public AggregateRating() {
            clear();
        }

        public AggregateRating clear() {
            this.type = 1;
            this.hasType = false;
            this.starRating = 0.0f;
            this.hasStarRating = false;
            this.ratingsCount = 0;
            this.hasRatingsCount = false;
            this.commentCount = 0;
            this.hasCommentCount = false;
            this.oneStarRatings = 0;
            this.hasOneStarRatings = false;
            this.twoStarRatings = 0;
            this.hasTwoStarRatings = false;
            this.threeStarRatings = 0;
            this.hasThreeStarRatings = false;
            this.fourStarRatings = 0;
            this.hasFourStarRatings = false;
            this.fiveStarRatings = 0;
            this.hasFiveStarRatings = false;
            this.bayesianMeanRating = 0.0d;
            this.hasBayesianMeanRating = false;
            this.thumbsUpCount = 0;
            this.hasThumbsUpCount = false;
            this.thumbsDownCount = 0;
            this.hasThumbsDownCount = false;
            this.cachedSize = -1;
            return this;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.type != 1 || this.hasType) {
                output.writeInt32(1, this.type);
            }
            if (this.hasStarRating || Float.floatToIntBits(this.starRating) != Float.floatToIntBits(0.0f)) {
                output.writeFloat(2, this.starRating);
            }
            if (this.hasRatingsCount || this.ratingsCount != 0) {
                output.writeUInt64(3, this.ratingsCount);
            }
            if (this.hasOneStarRatings || this.oneStarRatings != 0) {
                output.writeUInt64(4, this.oneStarRatings);
            }
            if (this.hasTwoStarRatings || this.twoStarRatings != 0) {
                output.writeUInt64(5, this.twoStarRatings);
            }
            if (this.hasThreeStarRatings || this.threeStarRatings != 0) {
                output.writeUInt64(6, this.threeStarRatings);
            }
            if (this.hasFourStarRatings || this.fourStarRatings != 0) {
                output.writeUInt64(7, this.fourStarRatings);
            }
            if (this.hasFiveStarRatings || this.fiveStarRatings != 0) {
                output.writeUInt64(8, this.fiveStarRatings);
            }
            if (this.hasThumbsUpCount || this.thumbsUpCount != 0) {
                output.writeUInt64(9, this.thumbsUpCount);
            }
            if (this.hasThumbsDownCount || this.thumbsDownCount != 0) {
                output.writeUInt64(10, this.thumbsDownCount);
            }
            if (this.hasCommentCount || this.commentCount != 0) {
                output.writeUInt64(11, this.commentCount);
            }
            if (this.hasBayesianMeanRating || Double.doubleToLongBits(this.bayesianMeanRating) != Double.doubleToLongBits(0.0d)) {
                output.writeDouble(12, this.bayesianMeanRating);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.type != 1 || this.hasType) {
                size += CodedOutputByteBufferNano.computeInt32Size(1, this.type);
            }
            if (this.hasStarRating || Float.floatToIntBits(this.starRating) != Float.floatToIntBits(0.0f)) {
                size += CodedOutputByteBufferNano.computeFloatSize(2, this.starRating);
            }
            if (this.hasRatingsCount || this.ratingsCount != 0) {
                size += CodedOutputByteBufferNano.computeUInt64Size(3, this.ratingsCount);
            }
            if (this.hasOneStarRatings || this.oneStarRatings != 0) {
                size += CodedOutputByteBufferNano.computeUInt64Size(4, this.oneStarRatings);
            }
            if (this.hasTwoStarRatings || this.twoStarRatings != 0) {
                size += CodedOutputByteBufferNano.computeUInt64Size(5, this.twoStarRatings);
            }
            if (this.hasThreeStarRatings || this.threeStarRatings != 0) {
                size += CodedOutputByteBufferNano.computeUInt64Size(6, this.threeStarRatings);
            }
            if (this.hasFourStarRatings || this.fourStarRatings != 0) {
                size += CodedOutputByteBufferNano.computeUInt64Size(7, this.fourStarRatings);
            }
            if (this.hasFiveStarRatings || this.fiveStarRatings != 0) {
                size += CodedOutputByteBufferNano.computeUInt64Size(8, this.fiveStarRatings);
            }
            if (this.hasThumbsUpCount || this.thumbsUpCount != 0) {
                size += CodedOutputByteBufferNano.computeUInt64Size(9, this.thumbsUpCount);
            }
            if (this.hasThumbsDownCount || this.thumbsDownCount != 0) {
                size += CodedOutputByteBufferNano.computeUInt64Size(10, this.thumbsDownCount);
            }
            if (this.hasCommentCount || this.commentCount != 0) {
                size += CodedOutputByteBufferNano.computeUInt64Size(11, this.commentCount);
            }
            if (this.hasBayesianMeanRating || Double.doubleToLongBits(this.bayesianMeanRating) != Double.doubleToLongBits(0.0d)) {
                return size + CodedOutputByteBufferNano.computeDoubleSize(12, this.bayesianMeanRating);
            }
            return size;
        }

        public AggregateRating mergeFrom(CodedInputByteBufferNano input) throws IOException {
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
                                this.type = value;
                                this.hasType = true;
                                break;
                            default:
                                continue;
                        }
                    case com.google.android.play.R.styleable.Toolbar_navigationContentDescription /*21*/:
                        this.starRating = input.readFloat();
                        this.hasStarRating = true;
                        continue;
                    case com.google.android.play.R.styleable.Theme_actionModeStyle /*24*/:
                        this.ratingsCount = input.readUInt64();
                        this.hasRatingsCount = true;
                        continue;
                    case com.google.android.play.R.styleable.Theme_actionModeSelectAllDrawable /*32*/:
                        this.oneStarRatings = input.readUInt64();
                        this.hasOneStarRatings = true;
                        continue;
                    case com.google.android.play.R.styleable.Theme_dropdownListPreferredItemHeight /*40*/:
                        this.twoStarRatings = input.readUInt64();
                        this.hasTwoStarRatings = true;
                        continue;
                    case com.google.android.play.R.styleable.Theme_selectableItemBackgroundBorderless /*48*/:
                        this.threeStarRatings = input.readUInt64();
                        this.hasThreeStarRatings = true;
                        continue;
                    case com.google.android.play.R.styleable.Theme_editTextColor /*56*/:
                        this.fourStarRatings = input.readUInt64();
                        this.hasFourStarRatings = true;
                        continue;
                    case com.google.android.play.R.styleable.Theme_listPreferredItemHeightSmall /*64*/:
                        this.fiveStarRatings = input.readUInt64();
                        this.hasFiveStarRatings = true;
                        continue;
                    case com.google.android.play.R.styleable.Theme_panelBackground /*72*/:
                        this.thumbsUpCount = input.readUInt64();
                        this.hasThumbsUpCount = true;
                        continue;
                    case com.google.android.play.R.styleable.Theme_colorControlActivated /*80*/:
                        this.thumbsDownCount = input.readUInt64();
                        this.hasThumbsDownCount = true;
                        continue;
                    case 88:
                        this.commentCount = input.readUInt64();
                        this.hasCommentCount = true;
                        continue;
                    case 97:
                        this.bayesianMeanRating = input.readDouble();
                        this.hasBayesianMeanRating = true;
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
