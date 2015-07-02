package com.google.android.gms.internal;

import com.google.android.gms.drive.DriveId;
import com.google.android.gms.drive.metadata.internal.j;
import java.util.Arrays;

public class ls extends j<DriveId> {
    public static final ls acd;

    static {
        acd = new ls();
    }

    private ls() {
        super("driveId", Arrays.asList(new String[]{"sqlId", "resourceId"}), Arrays.asList(new String[]{"dbInstanceId"}), 4100000);
    }
}
