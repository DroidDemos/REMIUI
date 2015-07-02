package com.google.android.finsky.utils;

import android.text.Html;

public abstract class FastHtmlParser {
    public static CharSequence fromHtml(String s) {
        StringBuilder b = new StringBuilder(s);
        replace(b, "<p>", "\n\n");
        replace(b, "<br>", "\n");
        if (b.indexOf("<") != -1) {
            FinskyLog.d("Doing slow HTML parse due to unexpected tag %s", b.substring(b.indexOf("<"), Math.min(b.indexOf("<") + 10, b.length())));
            return Html.fromHtml(s);
        }
        int pos = 0;
        while (true) {
            pos = b.indexOf("&", pos);
            if (pos == -1) {
                return b;
            }
            int endPos = b.indexOf(";", pos);
            if (endPos == -1) {
                return b;
            }
            String entity = b.substring(pos + 1, endPos);
            b.delete(pos + 1, endPos + 1);
            if (entity.charAt(0) == '#') {
                try {
                    b.setCharAt(pos, (char) Integer.parseInt(entity.substring(1)));
                } catch (NumberFormatException e) {
                    FinskyLog.d("Doing slow HTML parse due to unexpected &# escape %s", entity);
                    return Html.fromHtml(s);
                }
            } else if ("quot".equals(entity)) {
                b.setCharAt(pos, '\"');
            } else if ("apos".equals(entity)) {
                b.setCharAt(pos, '\'');
            } else if ("amp".equals(entity)) {
                b.setCharAt(pos, '&');
            } else if ("lt".equals(entity)) {
                b.setCharAt(pos, '<');
            } else if ("gt".equals(entity)) {
                b.setCharAt(pos, '>');
            } else {
                FinskyLog.d("Doing slow HTML parse due to unexpected & escape %s", entity);
                return Html.fromHtml(s);
            }
            pos++;
        }
    }

    private static void replace(StringBuilder b, String orig, String replacement) {
        int len = orig.length();
        int replacementLen = replacement.length();
        int pos = 0;
        while (true) {
            pos = b.indexOf(orig, pos);
            if (pos != -1) {
                b.replace(pos, pos + len, replacement);
                pos += replacementLen;
            } else {
                return;
            }
        }
    }
}
