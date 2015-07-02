package com.google.android.finsky.protos;

import com.google.android.wallet.instrumentmanager.R;
import com.google.protobuf.nano.CodedInputByteBufferNano;
import com.google.protobuf.nano.CodedOutputByteBufferNano;
import com.google.protobuf.nano.InternalNano;
import com.google.protobuf.nano.MessageNano;
import com.google.protobuf.nano.WireFormatNano;
import java.io.IOException;

public interface Log {

    public static final class ClickLogEvent extends MessageNano {
        private static volatile ClickLogEvent[] _emptyArray;
        public long eventTime;
        public boolean hasEventTime;
        public boolean hasListId;
        public boolean hasReferrerListId;
        public boolean hasReferrerUrl;
        public boolean hasUrl;
        public String listId;
        public String referrerListId;
        public String referrerUrl;
        public String url;

        public static ClickLogEvent[] emptyArray() {
            if (_emptyArray == null) {
                synchronized (InternalNano.LAZY_INIT_LOCK) {
                    if (_emptyArray == null) {
                        _emptyArray = new ClickLogEvent[0];
                    }
                }
            }
            return _emptyArray;
        }

        public ClickLogEvent() {
            clear();
        }

        public ClickLogEvent clear() {
            this.eventTime = 0;
            this.hasEventTime = false;
            this.url = "";
            this.hasUrl = false;
            this.listId = "";
            this.hasListId = false;
            this.referrerUrl = "";
            this.hasReferrerUrl = false;
            this.referrerListId = "";
            this.hasReferrerListId = false;
            this.cachedSize = -1;
            return this;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.hasEventTime || this.eventTime != 0) {
                output.writeInt64(1, this.eventTime);
            }
            if (this.hasUrl || !this.url.equals("")) {
                output.writeString(2, this.url);
            }
            if (this.hasListId || !this.listId.equals("")) {
                output.writeString(3, this.listId);
            }
            if (this.hasReferrerUrl || !this.referrerUrl.equals("")) {
                output.writeString(4, this.referrerUrl);
            }
            if (this.hasReferrerListId || !this.referrerListId.equals("")) {
                output.writeString(5, this.referrerListId);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.hasEventTime || this.eventTime != 0) {
                size += CodedOutputByteBufferNano.computeInt64Size(1, this.eventTime);
            }
            if (this.hasUrl || !this.url.equals("")) {
                size += CodedOutputByteBufferNano.computeStringSize(2, this.url);
            }
            if (this.hasListId || !this.listId.equals("")) {
                size += CodedOutputByteBufferNano.computeStringSize(3, this.listId);
            }
            if (this.hasReferrerUrl || !this.referrerUrl.equals("")) {
                size += CodedOutputByteBufferNano.computeStringSize(4, this.referrerUrl);
            }
            if (this.hasReferrerListId || !this.referrerListId.equals("")) {
                return size + CodedOutputByteBufferNano.computeStringSize(5, this.referrerListId);
            }
            return size;
        }

        public ClickLogEvent mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case R.styleable.WalletImButtonBar_capitalizeButtonText /*0*/:
                        break;
                    case R.styleable.WalletFragmentStyle_maskedWalletDetailsButtonBackground /*8*/:
                        this.eventTime = input.readInt64();
                        this.hasEventTime = true;
                        continue;
                    case com.google.android.play.R.styleable.Toolbar_collapseIcon /*18*/:
                        this.url = input.readString();
                        this.hasUrl = true;
                        continue;
                    case com.google.android.play.R.styleable.Theme_actionModeBackground /*26*/:
                        this.listId = input.readString();
                        this.hasListId = true;
                        continue;
                    case com.google.android.play.R.styleable.Theme_actionModeFindDrawable /*34*/:
                        this.referrerUrl = input.readString();
                        this.hasReferrerUrl = true;
                        continue;
                    case com.google.android.play.R.styleable.Theme_spinnerDropDownItemStyle /*42*/:
                        this.referrerListId = input.readString();
                        this.hasReferrerListId = true;
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

    public static final class LogRequest extends MessageNano {
        public ClickLogEvent[] clickEvent;

        public LogRequest() {
            clear();
        }

        public LogRequest clear() {
            this.clickEvent = ClickLogEvent.emptyArray();
            this.cachedSize = -1;
            return this;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.clickEvent != null && this.clickEvent.length > 0) {
                for (ClickLogEvent element : this.clickEvent) {
                    if (element != null) {
                        output.writeMessage(1, element);
                    }
                }
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.clickEvent != null && this.clickEvent.length > 0) {
                for (ClickLogEvent element : this.clickEvent) {
                    if (element != null) {
                        size += CodedOutputByteBufferNano.computeMessageSize(1, element);
                    }
                }
            }
            return size;
        }

        public LogRequest mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case R.styleable.WalletImButtonBar_capitalizeButtonText /*0*/:
                        break;
                    case R.styleable.WalletFragmentStyle_maskedWalletDetailsLogoImageType /*10*/:
                        int i;
                        int arrayLength = WireFormatNano.getRepeatedFieldArrayLength(input, 10);
                        if (this.clickEvent == null) {
                            i = 0;
                        } else {
                            i = this.clickEvent.length;
                        }
                        ClickLogEvent[] newArray = new ClickLogEvent[(i + arrayLength)];
                        if (i != 0) {
                            System.arraycopy(this.clickEvent, 0, newArray, 0, i);
                        }
                        while (i < newArray.length - 1) {
                            newArray[i] = new ClickLogEvent();
                            input.readMessage(newArray[i]);
                            input.readTag();
                            i++;
                        }
                        newArray[i] = new ClickLogEvent();
                        input.readMessage(newArray[i]);
                        this.clickEvent = newArray;
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

    public static final class LogResponse extends MessageNano {
        public LogResponse() {
            clear();
        }

        public LogResponse clear() {
            this.cachedSize = -1;
            return this;
        }

        public LogResponse mergeFrom(CodedInputByteBufferNano input) throws IOException {
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
}
