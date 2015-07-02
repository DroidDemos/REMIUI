package com.google.android.finsky.protos;

import com.google.android.wallet.instrumentmanager.R;
import com.google.protobuf.nano.CodedInputByteBufferNano;
import com.google.protobuf.nano.CodedOutputByteBufferNano;
import com.google.protobuf.nano.MessageNano;
import com.google.protobuf.nano.WireFormatNano;
import java.io.IOException;

public interface BillingAddress {

    public static final class Address extends MessageNano {
        public String addressLine1;
        public String addressLine2;
        public String city;
        public String dependentLocality;
        public boolean deprecatedIsReduced;
        public String email;
        public String firstName;
        public boolean hasAddressLine1;
        public boolean hasAddressLine2;
        public boolean hasCity;
        public boolean hasDependentLocality;
        public boolean hasDeprecatedIsReduced;
        public boolean hasEmail;
        public boolean hasFirstName;
        public boolean hasLanguageCode;
        public boolean hasLastName;
        public boolean hasName;
        public boolean hasPhoneNumber;
        public boolean hasPostalCode;
        public boolean hasPostalCountry;
        public boolean hasSortingCode;
        public boolean hasState;
        public String languageCode;
        public String lastName;
        public String name;
        public String phoneNumber;
        public String postalCode;
        public String postalCountry;
        public String sortingCode;
        public String state;

        public Address() {
            clear();
        }

        public Address clear() {
            this.name = "";
            this.hasName = false;
            this.firstName = "";
            this.hasFirstName = false;
            this.lastName = "";
            this.hasLastName = false;
            this.addressLine1 = "";
            this.hasAddressLine1 = false;
            this.addressLine2 = "";
            this.hasAddressLine2 = false;
            this.city = "";
            this.hasCity = false;
            this.state = "";
            this.hasState = false;
            this.postalCode = "";
            this.hasPostalCode = false;
            this.postalCountry = "";
            this.hasPostalCountry = false;
            this.dependentLocality = "";
            this.hasDependentLocality = false;
            this.sortingCode = "";
            this.hasSortingCode = false;
            this.languageCode = "";
            this.hasLanguageCode = false;
            this.phoneNumber = "";
            this.hasPhoneNumber = false;
            this.email = "";
            this.hasEmail = false;
            this.deprecatedIsReduced = false;
            this.hasDeprecatedIsReduced = false;
            this.cachedSize = -1;
            return this;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.hasName || !this.name.equals("")) {
                output.writeString(1, this.name);
            }
            if (this.hasAddressLine1 || !this.addressLine1.equals("")) {
                output.writeString(2, this.addressLine1);
            }
            if (this.hasAddressLine2 || !this.addressLine2.equals("")) {
                output.writeString(3, this.addressLine2);
            }
            if (this.hasCity || !this.city.equals("")) {
                output.writeString(4, this.city);
            }
            if (this.hasState || !this.state.equals("")) {
                output.writeString(5, this.state);
            }
            if (this.hasPostalCode || !this.postalCode.equals("")) {
                output.writeString(6, this.postalCode);
            }
            if (this.hasPostalCountry || !this.postalCountry.equals("")) {
                output.writeString(7, this.postalCountry);
            }
            if (this.hasDependentLocality || !this.dependentLocality.equals("")) {
                output.writeString(8, this.dependentLocality);
            }
            if (this.hasSortingCode || !this.sortingCode.equals("")) {
                output.writeString(9, this.sortingCode);
            }
            if (this.hasLanguageCode || !this.languageCode.equals("")) {
                output.writeString(10, this.languageCode);
            }
            if (this.hasPhoneNumber || !this.phoneNumber.equals("")) {
                output.writeString(11, this.phoneNumber);
            }
            if (this.hasDeprecatedIsReduced || this.deprecatedIsReduced) {
                output.writeBool(12, this.deprecatedIsReduced);
            }
            if (this.hasFirstName || !this.firstName.equals("")) {
                output.writeString(13, this.firstName);
            }
            if (this.hasLastName || !this.lastName.equals("")) {
                output.writeString(14, this.lastName);
            }
            if (this.hasEmail || !this.email.equals("")) {
                output.writeString(15, this.email);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.hasName || !this.name.equals("")) {
                size += CodedOutputByteBufferNano.computeStringSize(1, this.name);
            }
            if (this.hasAddressLine1 || !this.addressLine1.equals("")) {
                size += CodedOutputByteBufferNano.computeStringSize(2, this.addressLine1);
            }
            if (this.hasAddressLine2 || !this.addressLine2.equals("")) {
                size += CodedOutputByteBufferNano.computeStringSize(3, this.addressLine2);
            }
            if (this.hasCity || !this.city.equals("")) {
                size += CodedOutputByteBufferNano.computeStringSize(4, this.city);
            }
            if (this.hasState || !this.state.equals("")) {
                size += CodedOutputByteBufferNano.computeStringSize(5, this.state);
            }
            if (this.hasPostalCode || !this.postalCode.equals("")) {
                size += CodedOutputByteBufferNano.computeStringSize(6, this.postalCode);
            }
            if (this.hasPostalCountry || !this.postalCountry.equals("")) {
                size += CodedOutputByteBufferNano.computeStringSize(7, this.postalCountry);
            }
            if (this.hasDependentLocality || !this.dependentLocality.equals("")) {
                size += CodedOutputByteBufferNano.computeStringSize(8, this.dependentLocality);
            }
            if (this.hasSortingCode || !this.sortingCode.equals("")) {
                size += CodedOutputByteBufferNano.computeStringSize(9, this.sortingCode);
            }
            if (this.hasLanguageCode || !this.languageCode.equals("")) {
                size += CodedOutputByteBufferNano.computeStringSize(10, this.languageCode);
            }
            if (this.hasPhoneNumber || !this.phoneNumber.equals("")) {
                size += CodedOutputByteBufferNano.computeStringSize(11, this.phoneNumber);
            }
            if (this.hasDeprecatedIsReduced || this.deprecatedIsReduced) {
                size += CodedOutputByteBufferNano.computeBoolSize(12, this.deprecatedIsReduced);
            }
            if (this.hasFirstName || !this.firstName.equals("")) {
                size += CodedOutputByteBufferNano.computeStringSize(13, this.firstName);
            }
            if (this.hasLastName || !this.lastName.equals("")) {
                size += CodedOutputByteBufferNano.computeStringSize(14, this.lastName);
            }
            if (this.hasEmail || !this.email.equals("")) {
                return size + CodedOutputByteBufferNano.computeStringSize(15, this.email);
            }
            return size;
        }

        public Address mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case R.styleable.WalletImButtonBar_capitalizeButtonText /*0*/:
                        break;
                    case R.styleable.WalletFragmentStyle_maskedWalletDetailsLogoImageType /*10*/:
                        this.name = input.readString();
                        this.hasName = true;
                        continue;
                    case com.google.android.play.R.styleable.Toolbar_collapseIcon /*18*/:
                        this.addressLine1 = input.readString();
                        this.hasAddressLine1 = true;
                        continue;
                    case com.google.android.play.R.styleable.Theme_actionModeBackground /*26*/:
                        this.addressLine2 = input.readString();
                        this.hasAddressLine2 = true;
                        continue;
                    case com.google.android.play.R.styleable.Theme_actionModeFindDrawable /*34*/:
                        this.city = input.readString();
                        this.hasCity = true;
                        continue;
                    case com.google.android.play.R.styleable.Theme_spinnerDropDownItemStyle /*42*/:
                        this.state = input.readString();
                        this.hasState = true;
                        continue;
                    case com.google.android.play.R.styleable.Theme_dividerHorizontal /*50*/:
                        this.postalCode = input.readString();
                        this.hasPostalCode = true;
                        continue;
                    case com.google.android.play.R.styleable.Theme_switchStyle /*58*/:
                        this.postalCountry = input.readString();
                        this.hasPostalCountry = true;
                        continue;
                    case com.google.android.play.R.styleable.Theme_listPreferredItemPaddingLeft /*66*/:
                        this.dependentLocality = input.readString();
                        this.hasDependentLocality = true;
                        continue;
                    case com.google.android.play.R.styleable.Theme_panelMenuListTheme /*74*/:
                        this.sortingCode = input.readString();
                        this.hasSortingCode = true;
                        continue;
                    case com.google.android.play.R.styleable.Theme_colorButtonNormal /*82*/:
                        this.languageCode = input.readString();
                        this.hasLanguageCode = true;
                        continue;
                    case 90:
                        this.phoneNumber = input.readString();
                        this.hasPhoneNumber = true;
                        continue;
                    case 96:
                        this.deprecatedIsReduced = input.readBool();
                        this.hasDeprecatedIsReduced = true;
                        continue;
                    case 106:
                        this.firstName = input.readString();
                        this.hasFirstName = true;
                        continue;
                    case 114:
                        this.lastName = input.readString();
                        this.hasLastName = true;
                        continue;
                    case 122:
                        this.email = input.readString();
                        this.hasEmail = true;
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
