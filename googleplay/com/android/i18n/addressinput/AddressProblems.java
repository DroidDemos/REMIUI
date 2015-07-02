package com.android.i18n.addressinput;

import java.util.HashMap;
import java.util.Map;

public class AddressProblems {
    private Map<AddressField, AddressProblemType> mProblems;

    public AddressProblems() {
        this.mProblems = new HashMap();
    }

    void add(AddressField addressField, AddressProblemType problem) {
        this.mProblems.put(addressField, problem);
    }

    public boolean isEmpty() {
        return this.mProblems.isEmpty();
    }

    public String toString() {
        return this.mProblems.toString();
    }

    public AddressProblemType getProblem(AddressField addressField) {
        return (AddressProblemType) this.mProblems.get(addressField);
    }

    public Map<AddressField, AddressProblemType> getProblems() {
        return this.mProblems;
    }
}
