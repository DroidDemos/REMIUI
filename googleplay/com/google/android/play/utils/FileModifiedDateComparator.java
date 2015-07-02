package com.google.android.play.utils;

import java.io.File;
import java.util.Comparator;

public class FileModifiedDateComparator implements Comparator<File> {
    public static final FileModifiedDateComparator INSTANCE;

    static {
        INSTANCE = new FileModifiedDateComparator();
    }

    private FileModifiedDateComparator() {
    }

    public int compare(File lhs, File rhs) {
        if (lhs.lastModified() == rhs.lastModified()) {
            return 0;
        }
        if (lhs.lastModified() < rhs.lastModified()) {
            return -1;
        }
        return 1;
    }
}
