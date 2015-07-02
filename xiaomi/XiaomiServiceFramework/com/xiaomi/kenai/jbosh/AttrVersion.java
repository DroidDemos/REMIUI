package com.xiaomi.kenai.jbosh;

final class AttrVersion extends AbstractAttr<String> implements Comparable {
    private static final AttrVersion DEFAULT;
    private final int major;
    private final int minor;

    static {
        try {
            DEFAULT = createFromString("1.8");
        } catch (BOSHException boshx) {
            throw new IllegalStateException(boshx);
        }
    }

    private AttrVersion(String val) throws BOSHException {
        super(val);
        int idx = val.indexOf(46);
        if (idx <= 0) {
            throw new BOSHException("Illegal ver attribute value (not in major.minor form): " + val);
        }
        String majorStr = val.substring(0, idx);
        try {
            this.major = Integer.parseInt(majorStr);
            if (this.major < 0) {
                throw new BOSHException("Major version may not be < 0");
            }
            String minorStr = val.substring(idx + 1);
            try {
                this.minor = Integer.parseInt(minorStr);
                if (this.minor < 0) {
                    throw new BOSHException("Minor version may not be < 0");
                }
            } catch (NumberFormatException nfx) {
                throw new BOSHException("Could not parse ver attribute value (minor ver): " + minorStr, nfx);
            }
        } catch (NumberFormatException nfx2) {
            throw new BOSHException("Could not parse ver attribute value (major ver): " + majorStr, nfx2);
        }
    }

    static AttrVersion getSupportedVersion() {
        return DEFAULT;
    }

    static AttrVersion createFromString(String str) throws BOSHException {
        if (str == null) {
            return null;
        }
        return new AttrVersion(str);
    }

    int getMajor() {
        return this.major;
    }

    int getMinor() {
        return this.minor;
    }

    public int compareTo(Object otherObj) {
        if (!(otherObj instanceof AttrVersion)) {
            return 0;
        }
        AttrVersion other = (AttrVersion) otherObj;
        if (this.major < other.major) {
            return -1;
        }
        if (this.major > other.major) {
            return 1;
        }
        if (this.minor < other.minor) {
            return -1;
        }
        if (this.minor > other.minor) {
            return 1;
        }
        return 0;
    }
}
