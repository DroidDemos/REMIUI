package org.keyczar.enums;

public enum KeyPurpose {
    DECRYPT_AND_ENCRYPT(0, "crypt"),
    ENCRYPT(1, "encrypt"),
    SIGN_AND_VERIFY(2, "sign"),
    VERIFY(3, "verify"),
    TEST(127, "test");
    
    private String name;
    private int value;

    private KeyPurpose(int v, String s) {
        this.value = v;
        this.name = s;
    }
}
