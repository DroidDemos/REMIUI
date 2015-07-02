package com.google.android.finsky.protos;

import com.google.android.finsky.protos.Common.Docid;
import com.google.android.finsky.protos.Common.Image;
import com.google.android.wallet.instrumentmanager.R;
import com.google.protobuf.nano.CodedInputByteBufferNano;
import com.google.protobuf.nano.CodedOutputByteBufferNano;
import com.google.protobuf.nano.InternalNano;
import com.google.protobuf.nano.MessageNano;
import com.google.protobuf.nano.WireFormatNano;
import java.io.IOException;

public interface Preloads {

    public static final class Preload extends MessageNano {
        private static volatile Preload[] _emptyArray;
        public String deliveryToken;
        public Docid docid;
        public boolean hasDeliveryToken;
        public boolean hasTitle;
        public boolean hasVersionCode;
        public Image icon;
        public String title;
        public int versionCode;

        public static Preload[] emptyArray() {
            if (_emptyArray == null) {
                synchronized (InternalNano.LAZY_INIT_LOCK) {
                    if (_emptyArray == null) {
                        _emptyArray = new Preload[0];
                    }
                }
            }
            return _emptyArray;
        }

        public Preload() {
            clear();
        }

        public Preload clear() {
            this.docid = null;
            this.versionCode = 0;
            this.hasVersionCode = false;
            this.title = "";
            this.hasTitle = false;
            this.icon = null;
            this.deliveryToken = "";
            this.hasDeliveryToken = false;
            this.cachedSize = -1;
            return this;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.docid != null) {
                output.writeMessage(1, this.docid);
            }
            if (this.hasVersionCode || this.versionCode != 0) {
                output.writeInt32(2, this.versionCode);
            }
            if (this.hasTitle || !this.title.equals("")) {
                output.writeString(3, this.title);
            }
            if (this.icon != null) {
                output.writeMessage(4, this.icon);
            }
            if (this.hasDeliveryToken || !this.deliveryToken.equals("")) {
                output.writeString(5, this.deliveryToken);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.docid != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(1, this.docid);
            }
            if (this.hasVersionCode || this.versionCode != 0) {
                size += CodedOutputByteBufferNano.computeInt32Size(2, this.versionCode);
            }
            if (this.hasTitle || !this.title.equals("")) {
                size += CodedOutputByteBufferNano.computeStringSize(3, this.title);
            }
            if (this.icon != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(4, this.icon);
            }
            if (this.hasDeliveryToken || !this.deliveryToken.equals("")) {
                return size + CodedOutputByteBufferNano.computeStringSize(5, this.deliveryToken);
            }
            return size;
        }

        public Preload mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case R.styleable.WalletImButtonBar_capitalizeButtonText /*0*/:
                        break;
                    case R.styleable.WalletFragmentStyle_maskedWalletDetailsLogoImageType /*10*/:
                        if (this.docid == null) {
                            this.docid = new Docid();
                        }
                        input.readMessage(this.docid);
                        continue;
                    case com.google.android.play.R.styleable.Toolbar_maxButtonHeight /*16*/:
                        this.versionCode = input.readInt32();
                        this.hasVersionCode = true;
                        continue;
                    case com.google.android.play.R.styleable.Theme_actionModeBackground /*26*/:
                        this.title = input.readString();
                        this.hasTitle = true;
                        continue;
                    case com.google.android.play.R.styleable.Theme_actionModeFindDrawable /*34*/:
                        if (this.icon == null) {
                            this.icon = new Image();
                        }
                        input.readMessage(this.icon);
                        continue;
                    case com.google.android.play.R.styleable.Theme_spinnerDropDownItemStyle /*42*/:
                        this.deliveryToken = input.readString();
                        this.hasDeliveryToken = true;
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

    public static final class PreloadsResponse extends MessageNano {
        public Preload[] appPreload;
        public Preload configPreload;

        public PreloadsResponse() {
            clear();
        }

        public PreloadsResponse clear() {
            this.configPreload = null;
            this.appPreload = Preload.emptyArray();
            this.cachedSize = -1;
            return this;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.configPreload != null) {
                output.writeMessage(1, this.configPreload);
            }
            if (this.appPreload != null && this.appPreload.length > 0) {
                for (Preload element : this.appPreload) {
                    if (element != null) {
                        output.writeMessage(2, element);
                    }
                }
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.configPreload != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(1, this.configPreload);
            }
            if (this.appPreload != null && this.appPreload.length > 0) {
                for (Preload element : this.appPreload) {
                    if (element != null) {
                        size += CodedOutputByteBufferNano.computeMessageSize(2, element);
                    }
                }
            }
            return size;
        }

        public PreloadsResponse mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case R.styleable.WalletImButtonBar_capitalizeButtonText /*0*/:
                        break;
                    case R.styleable.WalletFragmentStyle_maskedWalletDetailsLogoImageType /*10*/:
                        if (this.configPreload == null) {
                            this.configPreload = new Preload();
                        }
                        input.readMessage(this.configPreload);
                        continue;
                    case com.google.android.play.R.styleable.Toolbar_collapseIcon /*18*/:
                        int i;
                        int arrayLength = WireFormatNano.getRepeatedFieldArrayLength(input, 18);
                        if (this.appPreload == null) {
                            i = 0;
                        } else {
                            i = this.appPreload.length;
                        }
                        Preload[] newArray = new Preload[(i + arrayLength)];
                        if (i != 0) {
                            System.arraycopy(this.appPreload, 0, newArray, 0, i);
                        }
                        while (i < newArray.length - 1) {
                            newArray[i] = new Preload();
                            input.readMessage(newArray[i]);
                            input.readTag();
                            i++;
                        }
                        newArray[i] = new Preload();
                        input.readMessage(newArray[i]);
                        this.appPreload = newArray;
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
