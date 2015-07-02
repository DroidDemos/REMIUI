package com.xiaomi.activate;

import java.util.Set;

public final class SimCardInfo {
    private final String phoneNumber;
    private String phoneTicket;
    private final String simId;

    public SimCardInfo(String imsi, String phoneNumber) {
        this.phoneTicket = "";
        this.simId = imsi;
        this.phoneNumber = phoneNumber;
    }

    public String getSimId() {
        return this.simId;
    }

    public String getPhoneNumber() {
        return this.phoneNumber;
    }

    public String getPhoneTicket() {
        return this.phoneTicket;
    }

    public void setPhoneTicket(String phoneTicket) {
        this.phoneTicket = phoneTicket;
    }

    public String toString() {
        return "SimCardInfo{simId='" + this.simId + '\'' + ", phoneNumber='" + this.phoneNumber + '\'' + ", phoneTicket=" + this.phoneTicket + '}';
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        SimCardInfo that = (SimCardInfo) o;
        if (this.phoneTicket != that.phoneTicket) {
            return false;
        }
        if (this.simId == null ? that.simId != null : !this.simId.equals(that.simId)) {
            return false;
        }
        if (this.phoneNumber != null) {
            if (this.phoneNumber.equals(that.phoneNumber)) {
                return true;
            }
        } else if (that.phoneNumber == null) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        int result;
        int hashCode;
        int i = 0;
        if (this.simId != null) {
            result = this.simId.hashCode();
        } else {
            result = 0;
        }
        int i2 = result * 31;
        if (this.phoneNumber != null) {
            hashCode = this.phoneNumber.hashCode();
        } else {
            hashCode = 0;
        }
        hashCode = (i2 + hashCode) * 31;
        if (this.phoneTicket != null) {
            i = this.phoneTicket.hashCode();
        }
        return hashCode + i;
    }

    public static boolean equal(Set<SimCardInfo> one, Set<SimCardInfo> two) {
        return (one == null || two == null || !one.equals(two)) ? false : true;
    }
}
