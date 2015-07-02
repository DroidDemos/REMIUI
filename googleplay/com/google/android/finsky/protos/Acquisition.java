package com.google.android.finsky.protos;

import com.google.android.finsky.protos.Common.Image;
import com.google.android.finsky.protos.DocAnnotations.Link;
import com.google.android.finsky.protos.DocumentV2.DocV2;
import com.google.android.finsky.protos.LibraryUpdateProto.LibraryUpdate;
import com.google.android.wallet.instrumentmanager.R;
import com.google.protobuf.nano.CodedInputByteBufferNano;
import com.google.protobuf.nano.CodedOutputByteBufferNano;
import com.google.protobuf.nano.MessageNano;
import com.google.protobuf.nano.WireFormatNano;
import java.io.IOException;

public interface Acquisition {

    public static final class InstallAppAction extends MessageNano {
        public DocV2 doc;

        public InstallAppAction() {
            clear();
        }

        public InstallAppAction clear() {
            this.doc = null;
            this.cachedSize = -1;
            return this;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.doc != null) {
                output.writeMessage(1, this.doc);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.doc != null) {
                return size + CodedOutputByteBufferNano.computeMessageSize(1, this.doc);
            }
            return size;
        }

        public InstallAppAction mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case R.styleable.WalletImButtonBar_capitalizeButtonText /*0*/:
                        break;
                    case R.styleable.WalletFragmentStyle_maskedWalletDetailsLogoImageType /*10*/:
                        if (this.doc == null) {
                            this.doc = new DocV2();
                        }
                        input.readMessage(this.doc);
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

    public static final class OpenContainerDocumentAction extends MessageNano {
        public Link link;

        public OpenContainerDocumentAction() {
            clear();
        }

        public OpenContainerDocumentAction clear() {
            this.link = null;
            this.cachedSize = -1;
            return this;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.link != null) {
                output.writeMessage(1, this.link);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.link != null) {
                return size + CodedOutputByteBufferNano.computeMessageSize(1, this.link);
            }
            return size;
        }

        public OpenContainerDocumentAction mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case R.styleable.WalletImButtonBar_capitalizeButtonText /*0*/:
                        break;
                    case R.styleable.WalletFragmentStyle_maskedWalletDetailsLogoImageType /*10*/:
                        if (this.link == null) {
                            this.link = new Link();
                        }
                        input.readMessage(this.link);
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

    public static final class OpenDocAction extends MessageNano {
        public DocV2 doc;

        public OpenDocAction() {
            clear();
        }

        public OpenDocAction clear() {
            this.doc = null;
            this.cachedSize = -1;
            return this;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.doc != null) {
                output.writeMessage(1, this.doc);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.doc != null) {
                return size + CodedOutputByteBufferNano.computeMessageSize(1, this.doc);
            }
            return size;
        }

        public OpenDocAction mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case R.styleable.WalletImButtonBar_capitalizeButtonText /*0*/:
                        break;
                    case R.styleable.WalletFragmentStyle_maskedWalletDetailsLogoImageType /*10*/:
                        if (this.doc == null) {
                            this.doc = new DocV2();
                        }
                        input.readMessage(this.doc);
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

    public static final class OpenHomeAction extends MessageNano {
        public OpenHomeAction() {
            clear();
        }

        public OpenHomeAction clear() {
            this.cachedSize = -1;
            return this;
        }

        public OpenHomeAction mergeFrom(CodedInputByteBufferNano input) throws IOException {
            int tag;
            do {
                tag = input.readTag();
                switch (tag) {
                    case R.styleable.WalletImButtonBar_capitalizeButtonText /*0*/:
                        break;
                    default:
                        break;
                }
                return this;
            } while (WireFormatNano.parseUnknownField(input, tag));
            return this;
        }
    }

    public static final class PostSuccessAction extends MessageNano {
        public InstallAppAction installApp;
        public OpenContainerDocumentAction openContainer;
        public OpenDocAction openDoc;
        public OpenHomeAction openHome;
        public PurchaseFlowAction purchaseFlow;

        public PostSuccessAction() {
            clear();
        }

        public PostSuccessAction clear() {
            this.openDoc = null;
            this.openHome = null;
            this.installApp = null;
            this.purchaseFlow = null;
            this.openContainer = null;
            this.cachedSize = -1;
            return this;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.openDoc != null) {
                output.writeMessage(1, this.openDoc);
            }
            if (this.openHome != null) {
                output.writeMessage(2, this.openHome);
            }
            if (this.installApp != null) {
                output.writeMessage(3, this.installApp);
            }
            if (this.purchaseFlow != null) {
                output.writeMessage(4, this.purchaseFlow);
            }
            if (this.openContainer != null) {
                output.writeMessage(5, this.openContainer);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.openDoc != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(1, this.openDoc);
            }
            if (this.openHome != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(2, this.openHome);
            }
            if (this.installApp != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(3, this.installApp);
            }
            if (this.purchaseFlow != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(4, this.purchaseFlow);
            }
            if (this.openContainer != null) {
                return size + CodedOutputByteBufferNano.computeMessageSize(5, this.openContainer);
            }
            return size;
        }

        public PostSuccessAction mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case R.styleable.WalletImButtonBar_capitalizeButtonText /*0*/:
                        break;
                    case R.styleable.WalletFragmentStyle_maskedWalletDetailsLogoImageType /*10*/:
                        if (this.openDoc == null) {
                            this.openDoc = new OpenDocAction();
                        }
                        input.readMessage(this.openDoc);
                        continue;
                    case com.google.android.play.R.styleable.Toolbar_collapseIcon /*18*/:
                        if (this.openHome == null) {
                            this.openHome = new OpenHomeAction();
                        }
                        input.readMessage(this.openHome);
                        continue;
                    case com.google.android.play.R.styleable.Theme_actionModeBackground /*26*/:
                        if (this.installApp == null) {
                            this.installApp = new InstallAppAction();
                        }
                        input.readMessage(this.installApp);
                        continue;
                    case com.google.android.play.R.styleable.Theme_actionModeFindDrawable /*34*/:
                        if (this.purchaseFlow == null) {
                            this.purchaseFlow = new PurchaseFlowAction();
                        }
                        input.readMessage(this.purchaseFlow);
                        continue;
                    case com.google.android.play.R.styleable.Theme_spinnerDropDownItemStyle /*42*/:
                        if (this.openContainer == null) {
                            this.openContainer = new OpenContainerDocumentAction();
                        }
                        input.readMessage(this.openContainer);
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

    public static final class PurchaseFlowAction extends MessageNano {
        public boolean hasPurchaseOfferType;
        public DocV2 purchaseDoc;
        public int purchaseOfferType;

        public PurchaseFlowAction() {
            clear();
        }

        public PurchaseFlowAction clear() {
            this.purchaseDoc = null;
            this.purchaseOfferType = 1;
            this.hasPurchaseOfferType = false;
            this.cachedSize = -1;
            return this;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.purchaseDoc != null) {
                output.writeMessage(1, this.purchaseDoc);
            }
            if (this.purchaseOfferType != 1 || this.hasPurchaseOfferType) {
                output.writeInt32(2, this.purchaseOfferType);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.purchaseDoc != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(1, this.purchaseDoc);
            }
            if (this.purchaseOfferType != 1 || this.hasPurchaseOfferType) {
                return size + CodedOutputByteBufferNano.computeInt32Size(2, this.purchaseOfferType);
            }
            return size;
        }

        public PurchaseFlowAction mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case R.styleable.WalletImButtonBar_capitalizeButtonText /*0*/:
                        break;
                    case R.styleable.WalletFragmentStyle_maskedWalletDetailsLogoImageType /*10*/:
                        if (this.purchaseDoc == null) {
                            this.purchaseDoc = new DocV2();
                        }
                        input.readMessage(this.purchaseDoc);
                        continue;
                    case com.google.android.play.R.styleable.Toolbar_maxButtonHeight /*16*/:
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
                            case R.styleable.WalletFragmentStyle_maskedWalletDetailsLogoTextColor /*9*/:
                            case R.styleable.WalletFragmentStyle_maskedWalletDetailsLogoImageType /*10*/:
                            case R.styleable.MapAttrs_uiZoomControls /*11*/:
                            case R.styleable.MapAttrs_uiZoomGestures /*12*/:
                                this.purchaseOfferType = value;
                                this.hasPurchaseOfferType = true;
                                break;
                            default:
                                continue;
                        }
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

    public static final class SuccessInfo extends MessageNano {
        public String buttonLabel;
        public boolean hasButtonLabel;
        public boolean hasMessageHtml;
        public boolean hasTitle;
        public boolean hasTitleBylineHtml;
        public LibraryUpdate libraryUpdate;
        public String messageHtml;
        public PostSuccessAction postSuccessAction;
        public Image thumbnailImage;
        public String title;
        public String titleBylineHtml;

        public SuccessInfo() {
            clear();
        }

        public SuccessInfo clear() {
            this.messageHtml = "";
            this.hasMessageHtml = false;
            this.buttonLabel = "";
            this.hasButtonLabel = false;
            this.postSuccessAction = null;
            this.libraryUpdate = null;
            this.title = "";
            this.hasTitle = false;
            this.titleBylineHtml = "";
            this.hasTitleBylineHtml = false;
            this.thumbnailImage = null;
            this.cachedSize = -1;
            return this;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.hasMessageHtml || !this.messageHtml.equals("")) {
                output.writeString(1, this.messageHtml);
            }
            if (this.hasButtonLabel || !this.buttonLabel.equals("")) {
                output.writeString(2, this.buttonLabel);
            }
            if (this.postSuccessAction != null) {
                output.writeMessage(3, this.postSuccessAction);
            }
            if (this.libraryUpdate != null) {
                output.writeMessage(4, this.libraryUpdate);
            }
            if (this.hasTitle || !this.title.equals("")) {
                output.writeString(5, this.title);
            }
            if (this.hasTitleBylineHtml || !this.titleBylineHtml.equals("")) {
                output.writeString(6, this.titleBylineHtml);
            }
            if (this.thumbnailImage != null) {
                output.writeMessage(7, this.thumbnailImage);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.hasMessageHtml || !this.messageHtml.equals("")) {
                size += CodedOutputByteBufferNano.computeStringSize(1, this.messageHtml);
            }
            if (this.hasButtonLabel || !this.buttonLabel.equals("")) {
                size += CodedOutputByteBufferNano.computeStringSize(2, this.buttonLabel);
            }
            if (this.postSuccessAction != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(3, this.postSuccessAction);
            }
            if (this.libraryUpdate != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(4, this.libraryUpdate);
            }
            if (this.hasTitle || !this.title.equals("")) {
                size += CodedOutputByteBufferNano.computeStringSize(5, this.title);
            }
            if (this.hasTitleBylineHtml || !this.titleBylineHtml.equals("")) {
                size += CodedOutputByteBufferNano.computeStringSize(6, this.titleBylineHtml);
            }
            if (this.thumbnailImage != null) {
                return size + CodedOutputByteBufferNano.computeMessageSize(7, this.thumbnailImage);
            }
            return size;
        }

        public SuccessInfo mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case R.styleable.WalletImButtonBar_capitalizeButtonText /*0*/:
                        break;
                    case R.styleable.WalletFragmentStyle_maskedWalletDetailsLogoImageType /*10*/:
                        this.messageHtml = input.readString();
                        this.hasMessageHtml = true;
                        continue;
                    case com.google.android.play.R.styleable.Toolbar_collapseIcon /*18*/:
                        this.buttonLabel = input.readString();
                        this.hasButtonLabel = true;
                        continue;
                    case com.google.android.play.R.styleable.Theme_actionModeBackground /*26*/:
                        if (this.postSuccessAction == null) {
                            this.postSuccessAction = new PostSuccessAction();
                        }
                        input.readMessage(this.postSuccessAction);
                        continue;
                    case com.google.android.play.R.styleable.Theme_actionModeFindDrawable /*34*/:
                        if (this.libraryUpdate == null) {
                            this.libraryUpdate = new LibraryUpdate();
                        }
                        input.readMessage(this.libraryUpdate);
                        continue;
                    case com.google.android.play.R.styleable.Theme_spinnerDropDownItemStyle /*42*/:
                        this.title = input.readString();
                        this.hasTitle = true;
                        continue;
                    case com.google.android.play.R.styleable.Theme_dividerHorizontal /*50*/:
                        this.titleBylineHtml = input.readString();
                        this.hasTitleBylineHtml = true;
                        continue;
                    case com.google.android.play.R.styleable.Theme_switchStyle /*58*/:
                        if (this.thumbnailImage == null) {
                            this.thumbnailImage = new Image();
                        }
                        input.readMessage(this.thumbnailImage);
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
