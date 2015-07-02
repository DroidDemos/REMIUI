package com.android.i18n.addressinput;

import com.google.android.finsky.utils.BackgroundThreadFactory;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class StandardAddressVerifier {
    private static final VerifierRefiner DEFAULT_REFINER;
    protected final Map<AddressField, List<AddressProblemType>> mProblemMap;
    protected final VerifierRefiner mRefiner;
    protected final FieldVerifier mRootVerifier;

    private class Verifier implements Runnable {
        private AddressData mAddress;
        private DataLoadListener mListener;
        private AddressProblems mProblems;

        Verifier(AddressData address, AddressProblems problems, DataLoadListener listener) {
            this.mAddress = address;
            this.mProblems = problems;
            this.mListener = listener;
        }

        public void run() {
            this.mListener.dataLoadingBegin();
            FieldVerifier v = StandardAddressVerifier.this.mRootVerifier;
            ScriptType script = null;
            if (this.mAddress.getLanguageCode() != null) {
                if (Util.isExplicitLatinScript(this.mAddress.getLanguageCode())) {
                    script = ScriptType.LATIN;
                } else {
                    script = ScriptType.LOCAL;
                }
            }
            StandardAddressVerifier.this.verifyField(script, v, AddressField.COUNTRY, this.mAddress.getPostalCountry(), this.mProblems);
            if (this.mProblems.isEmpty()) {
                v = v.refineVerifier(this.mAddress.getPostalCountry());
                StandardAddressVerifier.this.verifyField(script, v, AddressField.ADMIN_AREA, this.mAddress.getAdministrativeArea(), this.mProblems);
                if (this.mProblems.isEmpty()) {
                    v = v.refineVerifier(this.mAddress.getAdministrativeArea());
                    StandardAddressVerifier.this.verifyField(script, v, AddressField.LOCALITY, this.mAddress.getLocality(), this.mProblems);
                    if (this.mProblems.isEmpty()) {
                        v = v.refineVerifier(this.mAddress.getLocality());
                        StandardAddressVerifier.this.verifyField(script, v, AddressField.DEPENDENT_LOCALITY, this.mAddress.getDependentLocality(), this.mProblems);
                        if (this.mProblems.isEmpty()) {
                            v = v.refineVerifier(this.mAddress.getDependentLocality());
                        }
                    }
                }
            }
            String street = Util.joinAndSkipNulls("\n", this.mAddress.getAddressLine1(), this.mAddress.getAddressLine2());
            StandardAddressVerifier.this.verifyField(script, v, AddressField.POSTAL_CODE, this.mAddress.getPostalCode(), this.mProblems);
            StandardAddressVerifier.this.verifyField(script, v, AddressField.STREET_ADDRESS, street, this.mProblems);
            StandardAddressVerifier.this.verifyField(script, v, AddressField.SORTING_CODE, this.mAddress.getSortingCode(), this.mProblems);
            StandardAddressVerifier.this.verifyField(script, v, AddressField.ORGANIZATION, this.mAddress.getOrganization(), this.mProblems);
            StandardAddressVerifier.this.verifyField(script, v, AddressField.RECIPIENT, this.mAddress.getRecipient(), this.mProblems);
            StandardAddressVerifier.this.postVerify(v, this.mAddress, this.mProblems);
            this.mListener.dataLoadingEnd();
        }
    }

    public static class VerifierRefiner {
    }

    public StandardAddressVerifier(FieldVerifier rootVerifier) {
        this(rootVerifier, DEFAULT_REFINER, StandardChecks.PROBLEM_MAP);
    }

    public StandardAddressVerifier(FieldVerifier rootVerifier, VerifierRefiner refiner, Map<AddressField, List<AddressProblemType>> map) {
        this.mRootVerifier = rootVerifier;
        this.mRefiner = refiner;
        this.mProblemMap = StandardChecks.PROBLEM_MAP;
    }

    public void verify(AddressData address, AddressProblems problems) {
        NotifyingListener listener = new NotifyingListener(this);
        verifyAsync(address, problems, listener);
        try {
            listener.waitLoadingEnd();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void verifyAsync(AddressData address, AddressProblems problems, DataLoadListener listener) {
        BackgroundThreadFactory.createThread(new Verifier(address, problems, listener)).start();
    }

    protected void postVerify(FieldVerifier verifier, AddressData address, AddressProblems problems) {
    }

    protected boolean verifyField(ScriptType script, FieldVerifier verifier, AddressField field, String value, AddressProblems problems) {
        Iterator<AddressProblemType> iter = getProblemIterator(field);
        while (iter.hasNext()) {
            if (!verifyProblemField(script, verifier, (AddressProblemType) iter.next(), field, value, problems)) {
                return false;
            }
        }
        return true;
    }

    protected Iterator<AddressProblemType> getProblemIterator(AddressField field) {
        List<AddressProblemType> list = (List) this.mProblemMap.get(field);
        if (list == null) {
            list = Collections.emptyList();
        }
        return list.iterator();
    }

    protected boolean verifyProblemField(ScriptType script, FieldVerifier verifier, AddressProblemType problem, AddressField field, String datum, AddressProblems problems) {
        return verifier.check(script, problem, field, datum, problems);
    }

    static {
        DEFAULT_REFINER = new VerifierRefiner();
    }
}
