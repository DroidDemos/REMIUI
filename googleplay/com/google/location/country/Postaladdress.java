package com.google.location.country;

import com.google.android.wallet.instrumentmanager.R;
import com.google.protobuf.nano.CodedInputByteBufferNano;
import com.google.protobuf.nano.CodedOutputByteBufferNano;
import com.google.protobuf.nano.MessageNano;
import com.google.protobuf.nano.WireFormatNano;
import java.io.IOException;

public interface Postaladdress {

    public static final class PostalAddress extends MessageNano {
        public String[] addressLine;
        public String administrativeAreaName;
        public String countryName;
        public String countryNameCode;
        public String dependentLocalityName;
        public String dependentThoroughfareName;
        public String firmName;
        public String languageCode;
        public String localityName;
        public String postBoxNumber;
        public String postalCodeNumber;
        public String postalCodeNumberExtension;
        public String premiseName;
        public String recipientName;
        public String sortingCode;
        public String subAdministrativeAreaName;
        public String subPremiseName;
        public String thoroughfareName;
        public String thoroughfareNumber;

        public PostalAddress() {
            clear();
        }

        public PostalAddress clear() {
            this.countryNameCode = "";
            this.countryName = "";
            this.languageCode = "";
            this.administrativeAreaName = "";
            this.subAdministrativeAreaName = "";
            this.localityName = "";
            this.dependentLocalityName = "";
            this.thoroughfareName = "";
            this.thoroughfareNumber = "";
            this.dependentThoroughfareName = "";
            this.postalCodeNumber = "";
            this.postalCodeNumberExtension = "";
            this.sortingCode = "";
            this.postBoxNumber = "";
            this.premiseName = "";
            this.subPremiseName = "";
            this.addressLine = WireFormatNano.EMPTY_STRING_ARRAY;
            this.firmName = "";
            this.recipientName = "";
            this.cachedSize = -1;
            return this;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (!this.countryNameCode.equals("")) {
                output.writeString(1, this.countryNameCode);
            }
            if (!this.countryName.equals("")) {
                output.writeString(2, this.countryName);
            }
            if (!this.administrativeAreaName.equals("")) {
                output.writeString(3, this.administrativeAreaName);
            }
            if (!this.subAdministrativeAreaName.equals("")) {
                output.writeString(4, this.subAdministrativeAreaName);
            }
            if (!this.localityName.equals("")) {
                output.writeString(5, this.localityName);
            }
            if (!this.thoroughfareName.equals("")) {
                output.writeString(6, this.thoroughfareName);
            }
            if (!this.thoroughfareNumber.equals("")) {
                output.writeString(11, this.thoroughfareNumber);
            }
            if (!this.postalCodeNumber.equals("")) {
                output.writeString(12, this.postalCodeNumber);
            }
            if (!this.postalCodeNumberExtension.equals("")) {
                output.writeString(13, this.postalCodeNumberExtension);
            }
            if (this.addressLine != null && this.addressLine.length > 0) {
                for (String element : this.addressLine) {
                    if (element != null) {
                        output.writeString(14, element);
                    }
                }
            }
            if (!this.premiseName.equals("")) {
                output.writeString(15, this.premiseName);
            }
            if (!this.subPremiseName.equals("")) {
                output.writeString(16, this.subPremiseName);
            }
            if (!this.dependentLocalityName.equals("")) {
                output.writeString(17, this.dependentLocalityName);
            }
            if (!this.dependentThoroughfareName.equals("")) {
                output.writeString(21, this.dependentThoroughfareName);
            }
            if (!this.languageCode.equals("")) {
                output.writeString(26, this.languageCode);
            }
            if (!this.firmName.equals("")) {
                output.writeString(27, this.firmName);
            }
            if (!this.recipientName.equals("")) {
                output.writeString(28, this.recipientName);
            }
            if (!this.sortingCode.equals("")) {
                output.writeString(29, this.sortingCode);
            }
            if (!this.postBoxNumber.equals("")) {
                output.writeString(30, this.postBoxNumber);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (!this.countryNameCode.equals("")) {
                size += CodedOutputByteBufferNano.computeStringSize(1, this.countryNameCode);
            }
            if (!this.countryName.equals("")) {
                size += CodedOutputByteBufferNano.computeStringSize(2, this.countryName);
            }
            if (!this.administrativeAreaName.equals("")) {
                size += CodedOutputByteBufferNano.computeStringSize(3, this.administrativeAreaName);
            }
            if (!this.subAdministrativeAreaName.equals("")) {
                size += CodedOutputByteBufferNano.computeStringSize(4, this.subAdministrativeAreaName);
            }
            if (!this.localityName.equals("")) {
                size += CodedOutputByteBufferNano.computeStringSize(5, this.localityName);
            }
            if (!this.thoroughfareName.equals("")) {
                size += CodedOutputByteBufferNano.computeStringSize(6, this.thoroughfareName);
            }
            if (!this.thoroughfareNumber.equals("")) {
                size += CodedOutputByteBufferNano.computeStringSize(11, this.thoroughfareNumber);
            }
            if (!this.postalCodeNumber.equals("")) {
                size += CodedOutputByteBufferNano.computeStringSize(12, this.postalCodeNumber);
            }
            if (!this.postalCodeNumberExtension.equals("")) {
                size += CodedOutputByteBufferNano.computeStringSize(13, this.postalCodeNumberExtension);
            }
            if (this.addressLine != null && this.addressLine.length > 0) {
                int dataCount = 0;
                int dataSize = 0;
                for (String element : this.addressLine) {
                    if (element != null) {
                        dataCount++;
                        dataSize += CodedOutputByteBufferNano.computeStringSizeNoTag(element);
                    }
                }
                size = (size + dataSize) + (dataCount * 1);
            }
            if (!this.premiseName.equals("")) {
                size += CodedOutputByteBufferNano.computeStringSize(15, this.premiseName);
            }
            if (!this.subPremiseName.equals("")) {
                size += CodedOutputByteBufferNano.computeStringSize(16, this.subPremiseName);
            }
            if (!this.dependentLocalityName.equals("")) {
                size += CodedOutputByteBufferNano.computeStringSize(17, this.dependentLocalityName);
            }
            if (!this.dependentThoroughfareName.equals("")) {
                size += CodedOutputByteBufferNano.computeStringSize(21, this.dependentThoroughfareName);
            }
            if (!this.languageCode.equals("")) {
                size += CodedOutputByteBufferNano.computeStringSize(26, this.languageCode);
            }
            if (!this.firmName.equals("")) {
                size += CodedOutputByteBufferNano.computeStringSize(27, this.firmName);
            }
            if (!this.recipientName.equals("")) {
                size += CodedOutputByteBufferNano.computeStringSize(28, this.recipientName);
            }
            if (!this.sortingCode.equals("")) {
                size += CodedOutputByteBufferNano.computeStringSize(29, this.sortingCode);
            }
            if (this.postBoxNumber.equals("")) {
                return size;
            }
            return size + CodedOutputByteBufferNano.computeStringSize(30, this.postBoxNumber);
        }

        public PostalAddress mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case R.styleable.WalletImButtonBar_capitalizeButtonText /*0*/:
                        break;
                    case R.styleable.WalletFragmentStyle_maskedWalletDetailsLogoImageType /*10*/:
                        this.countryNameCode = input.readString();
                        continue;
                    case com.google.android.play.R.styleable.Toolbar_collapseIcon /*18*/:
                        this.countryName = input.readString();
                        continue;
                    case com.google.android.play.R.styleable.Theme_actionModeBackground /*26*/:
                        this.administrativeAreaName = input.readString();
                        continue;
                    case com.google.android.play.R.styleable.Theme_actionModeFindDrawable /*34*/:
                        this.subAdministrativeAreaName = input.readString();
                        continue;
                    case com.google.android.play.R.styleable.Theme_spinnerDropDownItemStyle /*42*/:
                        this.localityName = input.readString();
                        continue;
                    case com.google.android.play.R.styleable.Theme_dividerHorizontal /*50*/:
                        this.thoroughfareName = input.readString();
                        continue;
                    case 90:
                        this.thoroughfareNumber = input.readString();
                        continue;
                    case 98:
                        this.postalCodeNumber = input.readString();
                        continue;
                    case 106:
                        this.postalCodeNumberExtension = input.readString();
                        continue;
                    case 114:
                        int arrayLength = WireFormatNano.getRepeatedFieldArrayLength(input, 114);
                        int i = this.addressLine == null ? 0 : this.addressLine.length;
                        String[] newArray = new String[(i + arrayLength)];
                        if (i != 0) {
                            System.arraycopy(this.addressLine, 0, newArray, 0, i);
                        }
                        while (i < newArray.length - 1) {
                            newArray[i] = input.readString();
                            input.readTag();
                            i++;
                        }
                        newArray[i] = input.readString();
                        this.addressLine = newArray;
                        continue;
                    case 122:
                        this.premiseName = input.readString();
                        continue;
                    case 130:
                        this.subPremiseName = input.readString();
                        continue;
                    case 138:
                        this.dependentLocalityName = input.readString();
                        continue;
                    case 170:
                        this.dependentThoroughfareName = input.readString();
                        continue;
                    case 210:
                        this.languageCode = input.readString();
                        continue;
                    case 218:
                        this.firmName = input.readString();
                        continue;
                    case 226:
                        this.recipientName = input.readString();
                        continue;
                    case 234:
                        this.sortingCode = input.readString();
                        continue;
                    case 242:
                        this.postBoxNumber = input.readString();
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
