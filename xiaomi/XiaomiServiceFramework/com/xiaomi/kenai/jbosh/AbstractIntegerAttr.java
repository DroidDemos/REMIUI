package com.xiaomi.kenai.jbosh;

abstract class AbstractIntegerAttr extends AbstractAttr<Integer> {
    protected AbstractIntegerAttr(int val) throws BOSHException {
        super(Integer.valueOf(val));
    }

    protected AbstractIntegerAttr(String val) throws BOSHException {
        super(Integer.valueOf(parseInt(val)));
    }

    protected final void checkMinValue(int minVal) throws BOSHException {
        int intVal = ((Integer) getValue()).intValue();
        if (intVal < minVal) {
            throw new BOSHException("Illegal attribute value '" + intVal + "' provided.  " + "Must be >= " + minVal);
        }
    }

    private static int parseInt(String str) throws BOSHException {
        try {
            return Integer.parseInt(str);
        } catch (NumberFormatException nfx) {
            throw new BOSHException("Could not parse an integer from the value provided: " + str, nfx);
        }
    }

    public int intValue() {
        return ((Integer) getValue()).intValue();
    }
}
