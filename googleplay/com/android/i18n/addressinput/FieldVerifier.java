package com.android.i18n.addressinput;

import com.google.android.wallet.instrumentmanager.R;
import java.util.EnumSet;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.regex.Pattern;

public class FieldVerifier {
    private Map<String, String> mCandidateValues;
    private DataSource mDataSource;
    private Pattern mFormat;
    private String mId;
    private String[] mKeys;
    private String[] mLatinNames;
    private String[] mLocalNames;
    private Pattern mMatch;
    private Set<AddressField> mPossibleFields;
    private Set<AddressField> mRequired;

    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$android$i18n$addressinput$AddressProblemType;

        static {
            $SwitchMap$com$android$i18n$addressinput$AddressProblemType = new int[AddressProblemType.values().length];
            try {
                $SwitchMap$com$android$i18n$addressinput$AddressProblemType[AddressProblemType.USING_UNUSED_FIELD.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                $SwitchMap$com$android$i18n$addressinput$AddressProblemType[AddressProblemType.MISSING_REQUIRED_FIELD.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                $SwitchMap$com$android$i18n$addressinput$AddressProblemType[AddressProblemType.UNKNOWN_VALUE.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                $SwitchMap$com$android$i18n$addressinput$AddressProblemType[AddressProblemType.UNRECOGNIZED_FORMAT.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            try {
                $SwitchMap$com$android$i18n$addressinput$AddressProblemType[AddressProblemType.MISMATCHING_VALUE.ordinal()] = 5;
            } catch (NoSuchFieldError e5) {
            }
        }
    }

    public FieldVerifier(DataSource dataSource) {
        this.mDataSource = dataSource;
        populateRootVerifier();
    }

    private FieldVerifier(FieldVerifier parent, AddressVerificationNodeData nodeData) {
        this.mPossibleFields = parent.mPossibleFields;
        this.mRequired = parent.mRequired;
        this.mDataSource = parent.mDataSource;
        this.mFormat = parent.mFormat;
        this.mMatch = parent.mMatch;
        populate(nodeData);
        this.mCandidateValues = Util.buildNameToKeyMap(this.mKeys, this.mLocalNames, this.mLatinNames);
    }

    private void populateRootVerifier() {
        this.mId = "data";
        AddressVerificationNodeData rootNode = this.mDataSource.getDefaultData("data");
        if (rootNode.containsKey(AddressDataKey.COUNTRIES)) {
            this.mKeys = rootNode.get(AddressDataKey.COUNTRIES).split("~");
        }
        this.mCandidateValues = Util.buildNameToKeyMap(this.mKeys, null, null);
        AddressVerificationNodeData defaultZZ = this.mDataSource.getDefaultData("data/ZZ");
        this.mPossibleFields = new HashSet();
        if (defaultZZ.containsKey(AddressDataKey.FMT)) {
            this.mPossibleFields = parseAddressFields(defaultZZ.get(AddressDataKey.FMT));
        }
        this.mRequired = new HashSet();
        if (defaultZZ.containsKey(AddressDataKey.REQUIRE)) {
            this.mRequired = parseRequireString(defaultZZ.get(AddressDataKey.REQUIRE));
        }
    }

    private void populate(AddressVerificationNodeData nodeData) {
        if (nodeData != null) {
            if (nodeData.containsKey(AddressDataKey.ID)) {
                this.mId = nodeData.get(AddressDataKey.ID);
            }
            if (nodeData.containsKey(AddressDataKey.SUB_KEYS)) {
                this.mKeys = nodeData.get(AddressDataKey.SUB_KEYS).split("~");
            }
            if (nodeData.containsKey(AddressDataKey.SUB_LNAMES)) {
                this.mLatinNames = nodeData.get(AddressDataKey.SUB_LNAMES).split("~");
            }
            if (nodeData.containsKey(AddressDataKey.SUB_NAMES)) {
                this.mLocalNames = nodeData.get(AddressDataKey.SUB_NAMES).split("~");
            }
            if (nodeData.containsKey(AddressDataKey.FMT)) {
                this.mPossibleFields = parseAddressFields(nodeData.get(AddressDataKey.FMT));
            }
            if (nodeData.containsKey(AddressDataKey.REQUIRE)) {
                this.mRequired = parseRequireString(nodeData.get(AddressDataKey.REQUIRE));
            }
            if (nodeData.containsKey(AddressDataKey.XZIP)) {
                this.mFormat = Pattern.compile(nodeData.get(AddressDataKey.XZIP), 2);
            }
            if (nodeData.containsKey(AddressDataKey.ZIP)) {
                if (isCountryKey()) {
                    this.mFormat = Pattern.compile(nodeData.get(AddressDataKey.ZIP), 2);
                } else {
                    this.mMatch = Pattern.compile(nodeData.get(AddressDataKey.ZIP), 2);
                }
            }
            if (this.mKeys != null && this.mLocalNames == null && this.mLatinNames != null && this.mKeys.length == this.mLatinNames.length) {
                this.mLocalNames = this.mKeys;
            }
        }
    }

    FieldVerifier refineVerifier(String sublevel) {
        if (Util.trimToNull(sublevel) == null) {
            return new FieldVerifier(this, null);
        }
        AddressVerificationNodeData nodeData = this.mDataSource.get(this.mId + "/" + sublevel);
        if (nodeData != null) {
            return new FieldVerifier(this, nodeData);
        }
        if (this.mLatinNames == null) {
            return new FieldVerifier(this, null);
        }
        for (int n = 0; n < this.mLatinNames.length; n++) {
            if (this.mLatinNames[n].equalsIgnoreCase(sublevel)) {
                nodeData = this.mDataSource.get(this.mId + "/" + this.mLocalNames[n]);
                if (nodeData != null) {
                    return new FieldVerifier(this, nodeData);
                }
            }
        }
        return new FieldVerifier(this, null);
    }

    public String toString() {
        return this.mId;
    }

    protected boolean check(ScriptType script, AddressProblemType problem, AddressField field, String value, AddressProblems problems) {
        boolean problemFound = false;
        String trimmedValue = Util.trimToNull(value);
        switch (AnonymousClass1.$SwitchMap$com$android$i18n$addressinput$AddressProblemType[problem.ordinal()]) {
            case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                if (!(trimmedValue == null || this.mPossibleFields.contains(field))) {
                    problemFound = true;
                    break;
                }
            case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                if (this.mRequired.contains(field) && trimmedValue == null) {
                    problemFound = true;
                    break;
                }
            case R.styleable.WalletImFormEditText_requiredErrorString /*3*/:
                if (trimmedValue != null) {
                    if (isKnownInScript(script, trimmedValue)) {
                        problemFound = false;
                    } else {
                        problemFound = true;
                    }
                    break;
                }
                break;
            case R.styleable.WalletImFormEditText_required /*4*/:
                if (!(trimmedValue == null || this.mFormat == null || this.mFormat.matcher(trimmedValue).matches())) {
                    problemFound = true;
                    break;
                }
            case R.styleable.WalletImFormEditText_validateWhenNotVisible /*5*/:
                if (!(trimmedValue == null || this.mMatch == null || this.mMatch.matcher(trimmedValue).lookingAt())) {
                    problemFound = true;
                    break;
                }
            default:
                throw new RuntimeException("Unknown problem: " + problem);
        }
        if (problemFound) {
            problems.add(field, problem);
        }
        if (problemFound) {
            return false;
        }
        return true;
    }

    private boolean isKnownInScript(ScriptType script, String value) {
        String trimmedValue = Util.trimToNull(value);
        Util.checkNotNull(trimmedValue);
        if (script != null) {
            String[] namesToConsider = script == ScriptType.LATIN ? this.mLatinNames : this.mLocalNames;
            Set<String> candidates = new HashSet();
            if (namesToConsider != null) {
                for (String name : namesToConsider) {
                    candidates.add(name.toLowerCase());
                }
            }
            if (this.mKeys != null) {
                for (String name2 : this.mKeys) {
                    candidates.add(name2.toLowerCase());
                }
            }
            if (candidates.size() == 0 || trimmedValue == null) {
                return true;
            }
            return candidates.contains(value.toLowerCase());
        } else if (this.mCandidateValues == null || this.mCandidateValues.containsKey(trimmedValue.toLowerCase())) {
            return true;
        } else {
            return false;
        }
    }

    private static Set<AddressField> parseAddressFields(String value) {
        EnumSet<AddressField> result = EnumSet.of(AddressField.COUNTRY);
        boolean escaped = false;
        for (char c : value.toCharArray()) {
            if (escaped) {
                escaped = false;
                if (c == 'n') {
                    continue;
                } else {
                    AddressField f = AddressField.of(c);
                    if (f == null) {
                        throw new RuntimeException("Unrecognized character '" + c + "' in format pattern: " + value);
                    }
                    result.add(f);
                }
            } else if (c == '%') {
                escaped = true;
            }
        }
        result.remove(AddressField.ADDRESS_LINE_1);
        result.remove(AddressField.ADDRESS_LINE_2);
        return result;
    }

    private static Set<AddressField> parseRequireString(String value) {
        EnumSet<AddressField> result = EnumSet.of(AddressField.COUNTRY);
        for (char c : value.toCharArray()) {
            AddressField f = AddressField.of(c);
            if (f == null) {
                throw new RuntimeException("Unrecognized character '" + c + "' in require pattern: " + value);
            }
            result.add(f);
        }
        result.remove(AddressField.ADDRESS_LINE_1);
        result.remove(AddressField.ADDRESS_LINE_2);
        return result;
    }

    private boolean isCountryKey() {
        Util.checkNotNull(this.mId, "Cannot use null as key");
        return this.mId.split("/").length == 2;
    }
}
