package com.google.android.finsky.protos;

import com.google.android.finsky.protos.Common.Image;
import com.google.android.wallet.instrumentmanager.R;
import com.google.protobuf.nano.CodedInputByteBufferNano;
import com.google.protobuf.nano.CodedOutputByteBufferNano;
import com.google.protobuf.nano.InternalNano;
import com.google.protobuf.nano.MessageNano;
import com.google.protobuf.nano.WireFormatNano;
import java.io.IOException;

public interface PlusData {

    public static final class OBSOLETE_PlusProfile extends MessageNano {
        private static volatile OBSOLETE_PlusProfile[] _emptyArray;
        public String displayName;
        public boolean hasDisplayName;
        public boolean hasProfileImageUrl;
        public Image profileImage;
        public String profileImageUrl;

        public static OBSOLETE_PlusProfile[] emptyArray() {
            if (_emptyArray == null) {
                synchronized (InternalNano.LAZY_INIT_LOCK) {
                    if (_emptyArray == null) {
                        _emptyArray = new OBSOLETE_PlusProfile[0];
                    }
                }
            }
            return _emptyArray;
        }

        public OBSOLETE_PlusProfile() {
            clear();
        }

        public OBSOLETE_PlusProfile clear() {
            this.displayName = "";
            this.hasDisplayName = false;
            this.profileImageUrl = "";
            this.hasProfileImageUrl = false;
            this.profileImage = null;
            this.cachedSize = -1;
            return this;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.hasDisplayName || !this.displayName.equals("")) {
                output.writeString(2, this.displayName);
            }
            if (this.hasProfileImageUrl || !this.profileImageUrl.equals("")) {
                output.writeString(4, this.profileImageUrl);
            }
            if (this.profileImage != null) {
                output.writeMessage(5, this.profileImage);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.hasDisplayName || !this.displayName.equals("")) {
                size += CodedOutputByteBufferNano.computeStringSize(2, this.displayName);
            }
            if (this.hasProfileImageUrl || !this.profileImageUrl.equals("")) {
                size += CodedOutputByteBufferNano.computeStringSize(4, this.profileImageUrl);
            }
            if (this.profileImage != null) {
                return size + CodedOutputByteBufferNano.computeMessageSize(5, this.profileImage);
            }
            return size;
        }

        public OBSOLETE_PlusProfile mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case R.styleable.WalletImButtonBar_capitalizeButtonText /*0*/:
                        break;
                    case com.google.android.play.R.styleable.Toolbar_collapseIcon /*18*/:
                        this.displayName = input.readString();
                        this.hasDisplayName = true;
                        continue;
                    case com.google.android.play.R.styleable.Theme_actionModeFindDrawable /*34*/:
                        this.profileImageUrl = input.readString();
                        this.hasProfileImageUrl = true;
                        continue;
                    case com.google.android.play.R.styleable.Theme_spinnerDropDownItemStyle /*42*/:
                        if (this.profileImage == null) {
                            this.profileImage = new Image();
                        }
                        input.readMessage(this.profileImage);
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
