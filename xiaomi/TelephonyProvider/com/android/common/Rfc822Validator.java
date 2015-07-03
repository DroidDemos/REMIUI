package com.android.common;

import android.text.TextUtils;
import android.text.util.Rfc822Token;
import android.text.util.Rfc822Tokenizer;
import android.widget.AutoCompleteTextView.Validator;
import com.android.common.speech.LoggingEvents;
import java.util.regex.Pattern;

@Deprecated
public class Rfc822Validator implements Validator {
    private static final Pattern EMAIL_ADDRESS_PATTERN;
    private String mDomain;
    private boolean mRemoveInvalid;

    static {
        EMAIL_ADDRESS_PATTERN = Pattern.compile("[^\\s@]+@([^\\s@\\.]+\\.)+[a-zA-z][a-zA-Z][a-zA-Z]*");
    }

    public Rfc822Validator(String domain) {
        this.mRemoveInvalid = false;
        this.mDomain = domain;
    }

    public boolean isValid(CharSequence text) {
        Rfc822Token[] tokens = Rfc822Tokenizer.tokenize(text);
        if (tokens.length == 1 && EMAIL_ADDRESS_PATTERN.matcher(tokens[0].getAddress()).matches()) {
            return true;
        }
        return false;
    }

    public void setRemoveInvalid(boolean remove) {
        this.mRemoveInvalid = remove;
    }

    private String removeIllegalCharacters(String s) {
        StringBuilder result = new StringBuilder();
        int length = s.length();
        for (int i = 0; i < length; i++) {
            char c = s.charAt(i);
            if (!(c <= ' ' || c > '~' || c == '(' || c == ')' || c == '<' || c == '>' || c == '@' || c == ',' || c == ';' || c == ':' || c == '\\' || c == '\"' || c == '[' || c == ']')) {
                result.append(c);
            }
        }
        return result.toString();
    }

    public CharSequence fixText(CharSequence cs) {
        if (TextUtils.getTrimmedLength(cs) == 0) {
            return LoggingEvents.EXTRA_CALLING_APP_NAME;
        }
        Rfc822Token[] tokens = Rfc822Tokenizer.tokenize(cs);
        CharSequence sb = new StringBuilder();
        for (int i = 0; i < tokens.length; i++) {
            String text = tokens[i].getAddress();
            if (!this.mRemoveInvalid || isValid(text)) {
                int index = text.indexOf(64);
                if (index >= 0) {
                    String fix = removeIllegalCharacters(text.substring(0, index));
                    if (!TextUtils.isEmpty(fix)) {
                        boolean emptyDomain;
                        String domain = removeIllegalCharacters(text.substring(index + 1));
                        if (domain.length() == 0) {
                            emptyDomain = true;
                        } else {
                            emptyDomain = false;
                        }
                        if (!(emptyDomain && this.mDomain == null)) {
                            Rfc822Token rfc822Token = tokens[i];
                            StringBuilder append = new StringBuilder().append(fix).append("@");
                            if (emptyDomain) {
                                domain = this.mDomain;
                            }
                            rfc822Token.setAddress(append.append(domain).toString());
                        }
                    }
                } else if (this.mDomain != null) {
                    tokens[i].setAddress(removeIllegalCharacters(text) + "@" + this.mDomain);
                }
                sb.append(tokens[i].toString());
                if (i + 1 < tokens.length) {
                    sb.append(", ");
                }
            }
        }
        return sb;
    }
}
