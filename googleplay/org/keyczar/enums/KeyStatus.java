package org.keyczar.enums;

public enum KeyStatus {
    PRIMARY(0, "primary"),
    ACTIVE(1, "active"),
    INACTIVE(2, "inactive");
    
    private String name;
    private int value;

    private KeyStatus(int v, String s) {
        this.value = v;
        this.name = s;
    }
}
