package com.google.gson.stream;

import com.google.android.wallet.instrumentmanager.R;
import java.io.Closeable;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

public class JsonWriter implements Closeable {
    private String deferredName;
    private boolean htmlSafe;
    private String indent;
    private boolean lenient;
    private final Writer out;
    private String separator;
    private boolean serializeNulls;
    private final List<JsonScope> stack;

    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$google$gson$stream$JsonScope;

        static {
            $SwitchMap$com$google$gson$stream$JsonScope = new int[JsonScope.values().length];
            try {
                $SwitchMap$com$google$gson$stream$JsonScope[JsonScope.EMPTY_DOCUMENT.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                $SwitchMap$com$google$gson$stream$JsonScope[JsonScope.EMPTY_ARRAY.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                $SwitchMap$com$google$gson$stream$JsonScope[JsonScope.NONEMPTY_ARRAY.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                $SwitchMap$com$google$gson$stream$JsonScope[JsonScope.DANGLING_NAME.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            try {
                $SwitchMap$com$google$gson$stream$JsonScope[JsonScope.NONEMPTY_DOCUMENT.ordinal()] = 5;
            } catch (NoSuchFieldError e5) {
            }
        }
    }

    public JsonWriter(Writer out) {
        this.stack = new ArrayList();
        this.stack.add(JsonScope.EMPTY_DOCUMENT);
        this.separator = ":";
        this.serializeNulls = true;
        if (out == null) {
            throw new NullPointerException("out == null");
        }
        this.out = out;
    }

    public final void setIndent(String indent) {
        if (indent.length() == 0) {
            this.indent = null;
            this.separator = ":";
            return;
        }
        this.indent = indent;
        this.separator = ": ";
    }

    public final void setLenient(boolean lenient) {
        this.lenient = lenient;
    }

    public boolean isLenient() {
        return this.lenient;
    }

    public final void setHtmlSafe(boolean htmlSafe) {
        this.htmlSafe = htmlSafe;
    }

    public final boolean isHtmlSafe() {
        return this.htmlSafe;
    }

    public final void setSerializeNulls(boolean serializeNulls) {
        this.serializeNulls = serializeNulls;
    }

    public final boolean getSerializeNulls() {
        return this.serializeNulls;
    }

    public JsonWriter beginArray() throws IOException {
        writeDeferredName();
        return open(JsonScope.EMPTY_ARRAY, "[");
    }

    public JsonWriter endArray() throws IOException {
        return close(JsonScope.EMPTY_ARRAY, JsonScope.NONEMPTY_ARRAY, "]");
    }

    public JsonWriter beginObject() throws IOException {
        writeDeferredName();
        return open(JsonScope.EMPTY_OBJECT, "{");
    }

    public JsonWriter endObject() throws IOException {
        return close(JsonScope.EMPTY_OBJECT, JsonScope.NONEMPTY_OBJECT, "}");
    }

    private JsonWriter open(JsonScope empty, String openBracket) throws IOException {
        beforeValue(true);
        this.stack.add(empty);
        this.out.write(openBracket);
        return this;
    }

    private JsonWriter close(JsonScope empty, JsonScope nonempty, String closeBracket) throws IOException {
        JsonScope context = peek();
        if (context != nonempty && context != empty) {
            throw new IllegalStateException("Nesting problem: " + this.stack);
        } else if (this.deferredName != null) {
            throw new IllegalStateException("Dangling name: " + this.deferredName);
        } else {
            this.stack.remove(this.stack.size() - 1);
            if (context == nonempty) {
                newline();
            }
            this.out.write(closeBracket);
            return this;
        }
    }

    private JsonScope peek() {
        return (JsonScope) this.stack.get(this.stack.size() - 1);
    }

    private void replaceTop(JsonScope topOfStack) {
        this.stack.set(this.stack.size() - 1, topOfStack);
    }

    public JsonWriter name(String name) throws IOException {
        if (name == null) {
            throw new NullPointerException("name == null");
        } else if (this.deferredName != null) {
            throw new IllegalStateException();
        } else {
            this.deferredName = name;
            return this;
        }
    }

    private void writeDeferredName() throws IOException {
        if (this.deferredName != null) {
            beforeName();
            string(this.deferredName);
            this.deferredName = null;
        }
    }

    public JsonWriter value(String value) throws IOException {
        if (value == null) {
            return nullValue();
        }
        writeDeferredName();
        beforeValue(false);
        string(value);
        return this;
    }

    public JsonWriter nullValue() throws IOException {
        if (this.deferredName != null) {
            if (this.serializeNulls) {
                writeDeferredName();
            } else {
                this.deferredName = null;
                return this;
            }
        }
        beforeValue(false);
        this.out.write("null");
        return this;
    }

    public JsonWriter value(boolean value) throws IOException {
        writeDeferredName();
        beforeValue(false);
        this.out.write(value ? "true" : "false");
        return this;
    }

    public JsonWriter value(long value) throws IOException {
        writeDeferredName();
        beforeValue(false);
        this.out.write(Long.toString(value));
        return this;
    }

    public JsonWriter value(Number value) throws IOException {
        if (value == null) {
            return nullValue();
        }
        writeDeferredName();
        String string = value.toString();
        if (this.lenient || !(string.equals("-Infinity") || string.equals("Infinity") || string.equals("NaN"))) {
            beforeValue(false);
            this.out.append(string);
            return this;
        }
        throw new IllegalArgumentException("Numeric values must be finite, but was " + value);
    }

    public void close() throws IOException {
        this.out.close();
        if (peek() != JsonScope.NONEMPTY_DOCUMENT) {
            throw new IOException("Incomplete document");
        }
    }

    private void string(String value) throws IOException {
        this.out.write("\"");
        int length = value.length();
        for (int i = 0; i < length; i++) {
            char c = value.charAt(i);
            switch (c) {
                case R.styleable.WalletFragmentStyle_maskedWalletDetailsButtonBackground /*8*/:
                    this.out.write("\\b");
                    break;
                case R.styleable.WalletFragmentStyle_maskedWalletDetailsLogoTextColor /*9*/:
                    this.out.write("\\t");
                    break;
                case R.styleable.WalletFragmentStyle_maskedWalletDetailsLogoImageType /*10*/:
                    this.out.write("\\n");
                    break;
                case R.styleable.MapAttrs_uiZoomGestures /*12*/:
                    this.out.write("\\f");
                    break;
                case R.styleable.MapAttrs_useViewLifecycle /*13*/:
                    this.out.write("\\r");
                    break;
                case com.google.android.play.R.styleable.Theme_actionModeFindDrawable /*34*/:
                case '\\':
                    this.out.write(92);
                    this.out.write(c);
                    break;
                case com.google.android.play.R.styleable.Theme_textAppearanceSmallPopupMenu /*38*/:
                case com.google.android.play.R.styleable.Theme_actionDropDownStyle /*39*/:
                case com.google.android.play.R.styleable.Theme_textAppearanceSearchResultSubtitle /*60*/:
                case com.google.android.play.R.styleable.Theme_textColorSearchUrl /*61*/:
                case com.google.android.play.R.styleable.Theme_searchViewStyle /*62*/:
                    if (!this.htmlSafe) {
                        this.out.write(c);
                        break;
                    }
                    this.out.write(String.format("\\u%04x", new Object[]{Integer.valueOf(c)}));
                    break;
                case '\u2028':
                case '\u2029':
                    this.out.write(String.format("\\u%04x", new Object[]{Integer.valueOf(c)}));
                    break;
                default:
                    if (c > '\u001f') {
                        this.out.write(c);
                        break;
                    }
                    this.out.write(String.format("\\u%04x", new Object[]{Integer.valueOf(c)}));
                    break;
            }
        }
        this.out.write("\"");
    }

    private void newline() throws IOException {
        if (this.indent != null) {
            this.out.write("\n");
            for (int i = 1; i < this.stack.size(); i++) {
                this.out.write(this.indent);
            }
        }
    }

    private void beforeName() throws IOException {
        JsonScope context = peek();
        if (context == JsonScope.NONEMPTY_OBJECT) {
            this.out.write(44);
        } else if (context != JsonScope.EMPTY_OBJECT) {
            throw new IllegalStateException("Nesting problem: " + this.stack);
        }
        newline();
        replaceTop(JsonScope.DANGLING_NAME);
    }

    private void beforeValue(boolean root) throws IOException {
        switch (AnonymousClass1.$SwitchMap$com$google$gson$stream$JsonScope[peek().ordinal()]) {
            case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                if (this.lenient || root) {
                    replaceTop(JsonScope.NONEMPTY_DOCUMENT);
                    return;
                }
                throw new IllegalStateException("JSON must start with an array or an object.");
            case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                replaceTop(JsonScope.NONEMPTY_ARRAY);
                newline();
                return;
            case R.styleable.WalletImFormEditText_requiredErrorString /*3*/:
                this.out.append(',');
                newline();
                return;
            case R.styleable.WalletImFormEditText_required /*4*/:
                this.out.append(this.separator);
                replaceTop(JsonScope.NONEMPTY_OBJECT);
                return;
            case R.styleable.WalletImFormEditText_validateWhenNotVisible /*5*/:
                throw new IllegalStateException("JSON must have only one top-level value.");
            default:
                throw new IllegalStateException("Nesting problem: " + this.stack);
        }
    }
}
