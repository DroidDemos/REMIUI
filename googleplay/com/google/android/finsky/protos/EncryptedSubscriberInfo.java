package com.google.android.finsky.protos;

import com.google.android.wallet.instrumentmanager.R;
import com.google.protobuf.nano.CodedInputByteBufferNano;
import com.google.protobuf.nano.CodedOutputByteBufferNano;
import com.google.protobuf.nano.MessageNano;
import com.google.protobuf.nano.WireFormatNano;
import java.io.IOException;

public final class EncryptedSubscriberInfo extends MessageNano {
    public int carrierKeyVersion;
    public String data;
    public String encryptedKey;
    public int googleKeyVersion;
    public boolean hasCarrierKeyVersion;
    public boolean hasData;
    public boolean hasEncryptedKey;
    public boolean hasGoogleKeyVersion;
    public boolean hasInitVector;
    public boolean hasSignature;
    public String initVector;
    public String signature;

    public EncryptedSubscriberInfo() {
        clear();
    }

    public EncryptedSubscriberInfo clear() {
        this.data = "";
        this.hasData = false;
        this.encryptedKey = "";
        this.hasEncryptedKey = false;
        this.signature = "";
        this.hasSignature = false;
        this.initVector = "";
        this.hasInitVector = false;
        this.googleKeyVersion = 0;
        this.hasGoogleKeyVersion = false;
        this.carrierKeyVersion = 0;
        this.hasCarrierKeyVersion = false;
        this.cachedSize = -1;
        return this;
    }

    public void writeTo(CodedOutputByteBufferNano output) throws IOException {
        if (this.hasData || !this.data.equals("")) {
            output.writeString(1, this.data);
        }
        if (this.hasEncryptedKey || !this.encryptedKey.equals("")) {
            output.writeString(2, this.encryptedKey);
        }
        if (this.hasSignature || !this.signature.equals("")) {
            output.writeString(3, this.signature);
        }
        if (this.hasInitVector || !this.initVector.equals("")) {
            output.writeString(4, this.initVector);
        }
        if (this.hasGoogleKeyVersion || this.googleKeyVersion != 0) {
            output.writeInt32(5, this.googleKeyVersion);
        }
        if (this.hasCarrierKeyVersion || this.carrierKeyVersion != 0) {
            output.writeInt32(6, this.carrierKeyVersion);
        }
        super.writeTo(output);
    }

    protected int computeSerializedSize() {
        int size = super.computeSerializedSize();
        if (this.hasData || !this.data.equals("")) {
            size += CodedOutputByteBufferNano.computeStringSize(1, this.data);
        }
        if (this.hasEncryptedKey || !this.encryptedKey.equals("")) {
            size += CodedOutputByteBufferNano.computeStringSize(2, this.encryptedKey);
        }
        if (this.hasSignature || !this.signature.equals("")) {
            size += CodedOutputByteBufferNano.computeStringSize(3, this.signature);
        }
        if (this.hasInitVector || !this.initVector.equals("")) {
            size += CodedOutputByteBufferNano.computeStringSize(4, this.initVector);
        }
        if (this.hasGoogleKeyVersion || this.googleKeyVersion != 0) {
            size += CodedOutputByteBufferNano.computeInt32Size(5, this.googleKeyVersion);
        }
        if (this.hasCarrierKeyVersion || this.carrierKeyVersion != 0) {
            return size + CodedOutputByteBufferNano.computeInt32Size(6, this.carrierKeyVersion);
        }
        return size;
    }

    public EncryptedSubscriberInfo mergeFrom(CodedInputByteBufferNano input) throws IOException {
        while (true) {
            int tag = input.readTag();
            switch (tag) {
                case R.styleable.WalletImButtonBar_capitalizeButtonText /*0*/:
                    break;
                case R.styleable.WalletFragmentStyle_maskedWalletDetailsLogoImageType /*10*/:
                    this.data = input.readString();
                    this.hasData = true;
                    continue;
                case com.google.android.play.R.styleable.Toolbar_collapseIcon /*18*/:
                    this.encryptedKey = input.readString();
                    this.hasEncryptedKey = true;
                    continue;
                case com.google.android.play.R.styleable.Theme_actionModeBackground /*26*/:
                    this.signature = input.readString();
                    this.hasSignature = true;
                    continue;
                case com.google.android.play.R.styleable.Theme_actionModeFindDrawable /*34*/:
                    this.initVector = input.readString();
                    this.hasInitVector = true;
                    continue;
                case com.google.android.play.R.styleable.Theme_dropdownListPreferredItemHeight /*40*/:
                    this.googleKeyVersion = input.readInt32();
                    this.hasGoogleKeyVersion = true;
                    continue;
                case com.google.android.play.R.styleable.Theme_selectableItemBackgroundBorderless /*48*/:
                    this.carrierKeyVersion = input.readInt32();
                    this.hasCarrierKeyVersion = true;
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
