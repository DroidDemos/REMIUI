package com.xiaomi.account.data;

import java.util.Calendar;

public class XiaomiUserProfile {
    private Calendar mBirthday;
    private Gender mGender;
    private String mUserId;

    public enum Gender {
        MALE("m"),
        FEMALE("f");
        
        private String mGender;

        private Gender(String gender) {
            this.mGender = gender;
        }

        public String getType() {
            return this.mGender;
        }
    }

    public XiaomiUserProfile(String userId) {
        this.mUserId = userId;
    }

    public String getUserId() {
        return this.mUserId;
    }

    public Gender getGender() {
        return this.mGender;
    }

    public void setGender(Gender gender) {
        this.mGender = gender;
    }

    public Calendar getBirthday() {
        return this.mBirthday;
    }

    public void setBirthday(Calendar birthday) {
        this.mBirthday = birthday;
    }
}
