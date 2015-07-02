package com.google.android.gms.drive.metadata;

import android.os.Bundle;

public interface MetadataField<T> {
    String getName();

    T j(Bundle bundle);
}
