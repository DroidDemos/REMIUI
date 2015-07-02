package com.google.common.hash;

import com.google.common.b.a;
import java.io.Serializable;
import java.nio.charset.Charset;

public final class Funnels {

    class StringCharsetFunnel implements Funnel, Serializable {
        private final Charset charset;

        class SerializedForm implements Serializable {
            private static final long serialVersionUID = 0;
            private final String charsetCanonicalName;

            SerializedForm(Charset charset) {
                this.charsetCanonicalName = charset.name();
            }

            private Object readResolve() {
                return Funnels.a(Charset.forName(this.charsetCanonicalName));
            }
        }

        StringCharsetFunnel(Charset charset) {
            this.charset = (Charset) a.checkNotNull(charset);
        }

        public void a(CharSequence charSequence, f fVar) {
            fVar.a(charSequence, this.charset);
        }

        public String toString() {
            return "Funnels.stringFunnel(" + this.charset.name() + ")";
        }

        public boolean equals(Object obj) {
            if (!(obj instanceof StringCharsetFunnel)) {
                return false;
            }
            return this.charset.equals(((StringCharsetFunnel) obj).charset);
        }

        public int hashCode() {
            return StringCharsetFunnel.class.hashCode() ^ this.charset.hashCode();
        }

        Object writeReplace() {
            return new SerializedForm(this.charset);
        }
    }

    private Funnels() {
    }

    public static Funnel a(Charset charset) {
        return new StringCharsetFunnel(charset);
    }
}
