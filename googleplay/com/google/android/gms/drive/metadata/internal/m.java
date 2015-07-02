package com.google.android.gms.drive.metadata.internal;

import com.google.android.gms.drive.UserMetadata;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

public class m extends j<UserMetadata> {
    public m(String str, int i) {
        super(str, bz(str), Collections.emptyList(), i);
    }

    private static Collection<String> bz(String str) {
        return Arrays.asList(new String[]{z(str, "permissionId"), z(str, "displayName"), z(str, "picture"), z(str, "isAuthenticatedUser"), z(str, "emailAddress")});
    }

    private static String z(String str, String str2) {
        return "." + str2;
    }
}
