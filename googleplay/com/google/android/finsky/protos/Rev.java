package com.google.android.finsky.protos;

import com.google.android.finsky.protos.Common.Image;
import com.google.android.finsky.protos.DocAnnotations.Link;
import com.google.android.finsky.protos.DocumentV2.Review;
import com.google.android.wallet.instrumentmanager.R;
import com.google.protobuf.nano.CodedInputByteBufferNano;
import com.google.protobuf.nano.CodedOutputByteBufferNano;
import com.google.protobuf.nano.MessageNano;
import com.google.protobuf.nano.WireFormatNano;
import java.io.IOException;

public interface Rev {

    public static final class CriticReviewsResponse extends MessageNano {
        public Image aggregateSentiment;
        public boolean hasPercentFavorable;
        public boolean hasSourceText;
        public boolean hasTitle;
        public boolean hasTotalNumReviews;
        public int percentFavorable;
        public Review[] review;
        public Link source;
        public String sourceText;
        public String title;
        public int totalNumReviews;

        public CriticReviewsResponse() {
            clear();
        }

        public CriticReviewsResponse clear() {
            this.title = "";
            this.hasTitle = false;
            this.aggregateSentiment = null;
            this.totalNumReviews = 0;
            this.hasTotalNumReviews = false;
            this.percentFavorable = 0;
            this.hasPercentFavorable = false;
            this.sourceText = "";
            this.hasSourceText = false;
            this.source = null;
            this.review = Review.emptyArray();
            this.cachedSize = -1;
            return this;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.hasTitle || !this.title.equals("")) {
                output.writeString(1, this.title);
            }
            if (this.aggregateSentiment != null) {
                output.writeMessage(2, this.aggregateSentiment);
            }
            if (this.hasTotalNumReviews || this.totalNumReviews != 0) {
                output.writeUInt32(3, this.totalNumReviews);
            }
            if (this.hasPercentFavorable || this.percentFavorable != 0) {
                output.writeUInt32(4, this.percentFavorable);
            }
            if (this.hasSourceText || !this.sourceText.equals("")) {
                output.writeString(5, this.sourceText);
            }
            if (this.source != null) {
                output.writeMessage(6, this.source);
            }
            if (this.review != null && this.review.length > 0) {
                for (Review element : this.review) {
                    if (element != null) {
                        output.writeMessage(7, element);
                    }
                }
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.hasTitle || !this.title.equals("")) {
                size += CodedOutputByteBufferNano.computeStringSize(1, this.title);
            }
            if (this.aggregateSentiment != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(2, this.aggregateSentiment);
            }
            if (this.hasTotalNumReviews || this.totalNumReviews != 0) {
                size += CodedOutputByteBufferNano.computeUInt32Size(3, this.totalNumReviews);
            }
            if (this.hasPercentFavorable || this.percentFavorable != 0) {
                size += CodedOutputByteBufferNano.computeUInt32Size(4, this.percentFavorable);
            }
            if (this.hasSourceText || !this.sourceText.equals("")) {
                size += CodedOutputByteBufferNano.computeStringSize(5, this.sourceText);
            }
            if (this.source != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(6, this.source);
            }
            if (this.review != null && this.review.length > 0) {
                for (Review element : this.review) {
                    if (element != null) {
                        size += CodedOutputByteBufferNano.computeMessageSize(7, element);
                    }
                }
            }
            return size;
        }

        public CriticReviewsResponse mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case R.styleable.WalletImButtonBar_capitalizeButtonText /*0*/:
                        break;
                    case R.styleable.WalletFragmentStyle_maskedWalletDetailsLogoImageType /*10*/:
                        this.title = input.readString();
                        this.hasTitle = true;
                        continue;
                    case com.google.android.play.R.styleable.Toolbar_collapseIcon /*18*/:
                        if (this.aggregateSentiment == null) {
                            this.aggregateSentiment = new Image();
                        }
                        input.readMessage(this.aggregateSentiment);
                        continue;
                    case com.google.android.play.R.styleable.Theme_actionModeStyle /*24*/:
                        this.totalNumReviews = input.readUInt32();
                        this.hasTotalNumReviews = true;
                        continue;
                    case com.google.android.play.R.styleable.Theme_actionModeSelectAllDrawable /*32*/:
                        this.percentFavorable = input.readUInt32();
                        this.hasPercentFavorable = true;
                        continue;
                    case com.google.android.play.R.styleable.Theme_spinnerDropDownItemStyle /*42*/:
                        this.sourceText = input.readString();
                        this.hasSourceText = true;
                        continue;
                    case com.google.android.play.R.styleable.Theme_dividerHorizontal /*50*/:
                        if (this.source == null) {
                            this.source = new Link();
                        }
                        input.readMessage(this.source);
                        continue;
                    case com.google.android.play.R.styleable.Theme_switchStyle /*58*/:
                        int i;
                        int arrayLength = WireFormatNano.getRepeatedFieldArrayLength(input, 58);
                        if (this.review == null) {
                            i = 0;
                        } else {
                            i = this.review.length;
                        }
                        Review[] newArray = new Review[(i + arrayLength)];
                        if (i != 0) {
                            System.arraycopy(this.review, 0, newArray, 0, i);
                        }
                        while (i < newArray.length - 1) {
                            newArray[i] = new Review();
                            input.readMessage(newArray[i]);
                            input.readTag();
                            i++;
                        }
                        newArray[i] = new Review();
                        input.readMessage(newArray[i]);
                        this.review = newArray;
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

    public static final class GetReviewsResponse extends MessageNano {
        public boolean hasMatchingCount;
        public long matchingCount;
        public Review[] review;

        public GetReviewsResponse() {
            clear();
        }

        public GetReviewsResponse clear() {
            this.review = Review.emptyArray();
            this.matchingCount = 0;
            this.hasMatchingCount = false;
            this.cachedSize = -1;
            return this;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.review != null && this.review.length > 0) {
                for (Review element : this.review) {
                    if (element != null) {
                        output.writeMessage(1, element);
                    }
                }
            }
            if (this.hasMatchingCount || this.matchingCount != 0) {
                output.writeInt64(2, this.matchingCount);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.review != null && this.review.length > 0) {
                for (Review element : this.review) {
                    if (element != null) {
                        size += CodedOutputByteBufferNano.computeMessageSize(1, element);
                    }
                }
            }
            if (this.hasMatchingCount || this.matchingCount != 0) {
                return size + CodedOutputByteBufferNano.computeInt64Size(2, this.matchingCount);
            }
            return size;
        }

        public GetReviewsResponse mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case R.styleable.WalletImButtonBar_capitalizeButtonText /*0*/:
                        break;
                    case R.styleable.WalletFragmentStyle_maskedWalletDetailsLogoImageType /*10*/:
                        int i;
                        int arrayLength = WireFormatNano.getRepeatedFieldArrayLength(input, 10);
                        if (this.review == null) {
                            i = 0;
                        } else {
                            i = this.review.length;
                        }
                        Review[] newArray = new Review[(i + arrayLength)];
                        if (i != 0) {
                            System.arraycopy(this.review, 0, newArray, 0, i);
                        }
                        while (i < newArray.length - 1) {
                            newArray[i] = new Review();
                            input.readMessage(newArray[i]);
                            input.readTag();
                            i++;
                        }
                        newArray[i] = new Review();
                        input.readMessage(newArray[i]);
                        this.review = newArray;
                        continue;
                    case com.google.android.play.R.styleable.Toolbar_maxButtonHeight /*16*/:
                        this.matchingCount = input.readInt64();
                        this.hasMatchingCount = true;
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

    public static final class ReviewResponse extends MessageNano {
        public CriticReviewsResponse criticReviewsResponse;
        public GetReviewsResponse getResponse;
        public boolean hasNextPageUrl;
        public boolean hasSuggestionsListUrl;
        public String nextPageUrl;
        public String suggestionsListUrl;
        public Review updatedReview;

        public ReviewResponse() {
            clear();
        }

        public ReviewResponse clear() {
            this.getResponse = null;
            this.criticReviewsResponse = null;
            this.nextPageUrl = "";
            this.hasNextPageUrl = false;
            this.updatedReview = null;
            this.suggestionsListUrl = "";
            this.hasSuggestionsListUrl = false;
            this.cachedSize = -1;
            return this;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.getResponse != null) {
                output.writeMessage(1, this.getResponse);
            }
            if (this.hasNextPageUrl || !this.nextPageUrl.equals("")) {
                output.writeString(2, this.nextPageUrl);
            }
            if (this.updatedReview != null) {
                output.writeMessage(3, this.updatedReview);
            }
            if (this.hasSuggestionsListUrl || !this.suggestionsListUrl.equals("")) {
                output.writeString(4, this.suggestionsListUrl);
            }
            if (this.criticReviewsResponse != null) {
                output.writeMessage(5, this.criticReviewsResponse);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.getResponse != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(1, this.getResponse);
            }
            if (this.hasNextPageUrl || !this.nextPageUrl.equals("")) {
                size += CodedOutputByteBufferNano.computeStringSize(2, this.nextPageUrl);
            }
            if (this.updatedReview != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(3, this.updatedReview);
            }
            if (this.hasSuggestionsListUrl || !this.suggestionsListUrl.equals("")) {
                size += CodedOutputByteBufferNano.computeStringSize(4, this.suggestionsListUrl);
            }
            if (this.criticReviewsResponse != null) {
                return size + CodedOutputByteBufferNano.computeMessageSize(5, this.criticReviewsResponse);
            }
            return size;
        }

        public ReviewResponse mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case R.styleable.WalletImButtonBar_capitalizeButtonText /*0*/:
                        break;
                    case R.styleable.WalletFragmentStyle_maskedWalletDetailsLogoImageType /*10*/:
                        if (this.getResponse == null) {
                            this.getResponse = new GetReviewsResponse();
                        }
                        input.readMessage(this.getResponse);
                        continue;
                    case com.google.android.play.R.styleable.Toolbar_collapseIcon /*18*/:
                        this.nextPageUrl = input.readString();
                        this.hasNextPageUrl = true;
                        continue;
                    case com.google.android.play.R.styleable.Theme_actionModeBackground /*26*/:
                        if (this.updatedReview == null) {
                            this.updatedReview = new Review();
                        }
                        input.readMessage(this.updatedReview);
                        continue;
                    case com.google.android.play.R.styleable.Theme_actionModeFindDrawable /*34*/:
                        this.suggestionsListUrl = input.readString();
                        this.hasSuggestionsListUrl = true;
                        continue;
                    case com.google.android.play.R.styleable.Theme_spinnerDropDownItemStyle /*42*/:
                        if (this.criticReviewsResponse == null) {
                            this.criticReviewsResponse = new CriticReviewsResponse();
                        }
                        input.readMessage(this.criticReviewsResponse);
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
