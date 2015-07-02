package com.google.gson.internal.bind;

import com.google.android.wallet.instrumentmanager.R;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonIOException;
import com.google.gson.JsonNull;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSyntaxException;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.annotations.SerializedName;
import com.google.gson.internal.LazilyParsedNumber;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;
import java.net.InetAddress;
import java.net.URI;
import java.net.URL;
import java.sql.Timestamp;
import java.util.BitSet;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import java.util.StringTokenizer;
import java.util.UUID;

public final class TypeAdapters {
    public static final TypeAdapter<BitSet> BIT_SET;
    public static final TypeAdapterFactory BIT_SET_FACTORY;
    public static final TypeAdapter<Boolean> BOOLEAN;
    public static final TypeAdapter<Boolean> BOOLEAN_AS_STRING;
    public static final TypeAdapterFactory BOOLEAN_FACTORY;
    public static final TypeAdapter<Number> BYTE;
    public static final TypeAdapterFactory BYTE_FACTORY;
    public static final TypeAdapter<Calendar> CALENDAR;
    public static final TypeAdapterFactory CALENDAR_FACTORY;
    public static final TypeAdapter<Character> CHARACTER;
    public static final TypeAdapterFactory CHARACTER_FACTORY;
    public static final TypeAdapter<Class> CLASS;
    public static final TypeAdapterFactory CLASS_FACTORY;
    public static final TypeAdapter<Number> DOUBLE;
    public static final TypeAdapterFactory ENUM_FACTORY;
    public static final TypeAdapter<Number> FLOAT;
    public static final TypeAdapter<InetAddress> INET_ADDRESS;
    public static final TypeAdapterFactory INET_ADDRESS_FACTORY;
    public static final TypeAdapter<Number> INTEGER;
    public static final TypeAdapterFactory INTEGER_FACTORY;
    public static final TypeAdapter<JsonElement> JSON_ELEMENT;
    public static final TypeAdapterFactory JSON_ELEMENT_FACTORY;
    public static final TypeAdapter<Locale> LOCALE;
    public static final TypeAdapterFactory LOCALE_FACTORY;
    public static final TypeAdapter<Number> LONG;
    public static final TypeAdapter<Number> NUMBER;
    public static final TypeAdapterFactory NUMBER_FACTORY;
    public static final TypeAdapter<Number> SHORT;
    public static final TypeAdapterFactory SHORT_FACTORY;
    public static final TypeAdapter<String> STRING;
    public static final TypeAdapter<StringBuffer> STRING_BUFFER;
    public static final TypeAdapterFactory STRING_BUFFER_FACTORY;
    public static final TypeAdapter<StringBuilder> STRING_BUILDER;
    public static final TypeAdapterFactory STRING_BUILDER_FACTORY;
    public static final TypeAdapterFactory STRING_FACTORY;
    public static final TypeAdapterFactory TIMESTAMP_FACTORY;
    public static final TypeAdapter<URI> URI;
    public static final TypeAdapterFactory URI_FACTORY;
    public static final TypeAdapter<URL> URL;
    public static final TypeAdapterFactory URL_FACTORY;
    public static final TypeAdapter<UUID> UUID;
    public static final TypeAdapterFactory UUID_FACTORY;

    static /* synthetic */ class AnonymousClass30 {
        static final /* synthetic */ int[] $SwitchMap$com$google$gson$stream$JsonToken;

        static {
            $SwitchMap$com$google$gson$stream$JsonToken = new int[JsonToken.values().length];
            try {
                $SwitchMap$com$google$gson$stream$JsonToken[JsonToken.NUMBER.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                $SwitchMap$com$google$gson$stream$JsonToken[JsonToken.BOOLEAN.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                $SwitchMap$com$google$gson$stream$JsonToken[JsonToken.STRING.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                $SwitchMap$com$google$gson$stream$JsonToken[JsonToken.NULL.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            try {
                $SwitchMap$com$google$gson$stream$JsonToken[JsonToken.BEGIN_ARRAY.ordinal()] = 5;
            } catch (NoSuchFieldError e5) {
            }
            try {
                $SwitchMap$com$google$gson$stream$JsonToken[JsonToken.BEGIN_OBJECT.ordinal()] = 6;
            } catch (NoSuchFieldError e6) {
            }
            try {
                $SwitchMap$com$google$gson$stream$JsonToken[JsonToken.END_DOCUMENT.ordinal()] = 7;
            } catch (NoSuchFieldError e7) {
            }
            try {
                $SwitchMap$com$google$gson$stream$JsonToken[JsonToken.NAME.ordinal()] = 8;
            } catch (NoSuchFieldError e8) {
            }
            try {
                $SwitchMap$com$google$gson$stream$JsonToken[JsonToken.END_OBJECT.ordinal()] = 9;
            } catch (NoSuchFieldError e9) {
            }
            try {
                $SwitchMap$com$google$gson$stream$JsonToken[JsonToken.END_ARRAY.ordinal()] = 10;
            } catch (NoSuchFieldError e10) {
            }
        }
    }

    private static final class EnumTypeAdapter<T extends Enum<T>> extends TypeAdapter<T> {
        private final Map<T, String> constantToName;
        private final Map<String, T> nameToConstant;

        public EnumTypeAdapter(Class<T> classOfT) {
            this.nameToConstant = new HashMap();
            this.constantToName = new HashMap();
            try {
                for (T constant : (Enum[]) classOfT.getEnumConstants()) {
                    String name = constant.name();
                    SerializedName annotation = (SerializedName) classOfT.getField(name).getAnnotation(SerializedName.class);
                    if (annotation != null) {
                        name = annotation.value();
                    }
                    this.nameToConstant.put(name, constant);
                    this.constantToName.put(constant, name);
                }
            } catch (NoSuchFieldException e) {
                throw new AssertionError();
            }
        }

        public T read(JsonReader in) throws IOException {
            if (in.peek() != JsonToken.NULL) {
                return (Enum) this.nameToConstant.get(in.nextString());
            }
            in.nextNull();
            return null;
        }

        public void write(JsonWriter out, T value) throws IOException {
            out.value(value == null ? null : (String) this.constantToName.get(value));
        }
    }

    static {
        CLASS = new TypeAdapter<Class>() {
            public void write(JsonWriter out, Class value) throws IOException {
                throw new UnsupportedOperationException("Attempted to serialize java.lang.Class: " + value.getName() + ". Forgot to register a type adapter?");
            }

            public Class read(JsonReader in) throws IOException {
                throw new UnsupportedOperationException("Attempted to deserialize a java.lang.Class. Forgot to register a type adapter?");
            }
        };
        CLASS_FACTORY = newFactory(Class.class, CLASS);
        BIT_SET = new TypeAdapter<BitSet>() {
            public BitSet read(JsonReader in) throws IOException {
                if (in.peek() == JsonToken.NULL) {
                    in.nextNull();
                    return null;
                }
                BitSet bitset = new BitSet();
                in.beginArray();
                int i = 0;
                JsonToken tokenType = in.peek();
                while (tokenType != JsonToken.END_ARRAY) {
                    boolean set;
                    switch (AnonymousClass30.$SwitchMap$com$google$gson$stream$JsonToken[tokenType.ordinal()]) {
                        case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                            if (in.nextInt() == 0) {
                                set = false;
                                break;
                            }
                            set = true;
                            break;
                        case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                            set = in.nextBoolean();
                            break;
                        case R.styleable.WalletImFormEditText_requiredErrorString /*3*/:
                            String stringValue = in.nextString();
                            try {
                                set = Integer.parseInt(stringValue) != 0;
                                break;
                            } catch (NumberFormatException e) {
                                throw new JsonSyntaxException("Error: Expecting: bitset number value (1, 0), Found: " + stringValue);
                            }
                        default:
                            throw new JsonSyntaxException("Invalid bitset value type: " + tokenType);
                    }
                    if (set) {
                        bitset.set(i);
                    }
                    i++;
                    tokenType = in.peek();
                }
                in.endArray();
                return bitset;
            }

            public void write(JsonWriter out, BitSet src) throws IOException {
                if (src == null) {
                    out.nullValue();
                    return;
                }
                out.beginArray();
                for (int i = 0; i < src.length(); i++) {
                    out.value((long) (src.get(i) ? 1 : 0));
                }
                out.endArray();
            }
        };
        BIT_SET_FACTORY = newFactory(BitSet.class, BIT_SET);
        BOOLEAN = new TypeAdapter<Boolean>() {
            public Boolean read(JsonReader in) throws IOException {
                if (in.peek() == JsonToken.NULL) {
                    in.nextNull();
                    return null;
                } else if (in.peek() == JsonToken.STRING) {
                    return Boolean.valueOf(Boolean.parseBoolean(in.nextString()));
                } else {
                    return Boolean.valueOf(in.nextBoolean());
                }
            }

            public void write(JsonWriter out, Boolean value) throws IOException {
                if (value == null) {
                    out.nullValue();
                } else {
                    out.value(value.booleanValue());
                }
            }
        };
        BOOLEAN_AS_STRING = new TypeAdapter<Boolean>() {
            public Boolean read(JsonReader in) throws IOException {
                if (in.peek() != JsonToken.NULL) {
                    return Boolean.valueOf(in.nextString());
                }
                in.nextNull();
                return null;
            }

            public void write(JsonWriter out, Boolean value) throws IOException {
                out.value(value == null ? "null" : value.toString());
            }
        };
        BOOLEAN_FACTORY = newFactory(Boolean.TYPE, Boolean.class, BOOLEAN);
        BYTE = new TypeAdapter<Number>() {
            public Number read(JsonReader in) throws IOException {
                if (in.peek() == JsonToken.NULL) {
                    in.nextNull();
                    return null;
                }
                try {
                    return Byte.valueOf((byte) in.nextInt());
                } catch (Throwable e) {
                    throw new JsonSyntaxException(e);
                }
            }

            public void write(JsonWriter out, Number value) throws IOException {
                out.value(value);
            }
        };
        BYTE_FACTORY = newFactory(Byte.TYPE, Byte.class, BYTE);
        SHORT = new TypeAdapter<Number>() {
            public Number read(JsonReader in) throws IOException {
                if (in.peek() == JsonToken.NULL) {
                    in.nextNull();
                    return null;
                }
                try {
                    return Short.valueOf((short) in.nextInt());
                } catch (Throwable e) {
                    throw new JsonSyntaxException(e);
                }
            }

            public void write(JsonWriter out, Number value) throws IOException {
                out.value(value);
            }
        };
        SHORT_FACTORY = newFactory(Short.TYPE, Short.class, SHORT);
        INTEGER = new TypeAdapter<Number>() {
            public Number read(JsonReader in) throws IOException {
                if (in.peek() == JsonToken.NULL) {
                    in.nextNull();
                    return null;
                }
                try {
                    return Integer.valueOf(in.nextInt());
                } catch (Throwable e) {
                    throw new JsonSyntaxException(e);
                }
            }

            public void write(JsonWriter out, Number value) throws IOException {
                out.value(value);
            }
        };
        INTEGER_FACTORY = newFactory(Integer.TYPE, Integer.class, INTEGER);
        LONG = new TypeAdapter<Number>() {
            public Number read(JsonReader in) throws IOException {
                if (in.peek() == JsonToken.NULL) {
                    in.nextNull();
                    return null;
                }
                try {
                    return Long.valueOf(in.nextLong());
                } catch (Throwable e) {
                    throw new JsonSyntaxException(e);
                }
            }

            public void write(JsonWriter out, Number value) throws IOException {
                out.value(value);
            }
        };
        FLOAT = new TypeAdapter<Number>() {
            public Number read(JsonReader in) throws IOException {
                if (in.peek() != JsonToken.NULL) {
                    return Float.valueOf((float) in.nextDouble());
                }
                in.nextNull();
                return null;
            }

            public void write(JsonWriter out, Number value) throws IOException {
                out.value(value);
            }
        };
        DOUBLE = new TypeAdapter<Number>() {
            public Number read(JsonReader in) throws IOException {
                if (in.peek() != JsonToken.NULL) {
                    return Double.valueOf(in.nextDouble());
                }
                in.nextNull();
                return null;
            }

            public void write(JsonWriter out, Number value) throws IOException {
                out.value(value);
            }
        };
        NUMBER = new TypeAdapter<Number>() {
            public Number read(JsonReader in) throws IOException {
                JsonToken jsonToken = in.peek();
                switch (AnonymousClass30.$SwitchMap$com$google$gson$stream$JsonToken[jsonToken.ordinal()]) {
                    case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                        return new LazilyParsedNumber(in.nextString());
                    case R.styleable.WalletImFormEditText_required /*4*/:
                        in.nextNull();
                        return null;
                    default:
                        throw new JsonSyntaxException("Expecting number, got: " + jsonToken);
                }
            }

            public void write(JsonWriter out, Number value) throws IOException {
                out.value(value);
            }
        };
        NUMBER_FACTORY = newFactory(Number.class, NUMBER);
        CHARACTER = new TypeAdapter<Character>() {
            public Character read(JsonReader in) throws IOException {
                if (in.peek() == JsonToken.NULL) {
                    in.nextNull();
                    return null;
                }
                String str = in.nextString();
                if (str.length() == 1) {
                    return Character.valueOf(str.charAt(0));
                }
                throw new JsonSyntaxException("Expecting character, got: " + str);
            }

            public void write(JsonWriter out, Character value) throws IOException {
                out.value(value == null ? null : String.valueOf(value));
            }
        };
        CHARACTER_FACTORY = newFactory(Character.TYPE, Character.class, CHARACTER);
        STRING = new TypeAdapter<String>() {
            public String read(JsonReader in) throws IOException {
                JsonToken peek = in.peek();
                if (peek == JsonToken.NULL) {
                    in.nextNull();
                    return null;
                } else if (peek == JsonToken.BOOLEAN) {
                    return Boolean.toString(in.nextBoolean());
                } else {
                    return in.nextString();
                }
            }

            public void write(JsonWriter out, String value) throws IOException {
                out.value(value);
            }
        };
        STRING_FACTORY = newFactory(String.class, STRING);
        STRING_BUILDER = new TypeAdapter<StringBuilder>() {
            public StringBuilder read(JsonReader in) throws IOException {
                if (in.peek() != JsonToken.NULL) {
                    return new StringBuilder(in.nextString());
                }
                in.nextNull();
                return null;
            }

            public void write(JsonWriter out, StringBuilder value) throws IOException {
                out.value(value == null ? null : value.toString());
            }
        };
        STRING_BUILDER_FACTORY = newFactory(StringBuilder.class, STRING_BUILDER);
        STRING_BUFFER = new TypeAdapter<StringBuffer>() {
            public StringBuffer read(JsonReader in) throws IOException {
                if (in.peek() != JsonToken.NULL) {
                    return new StringBuffer(in.nextString());
                }
                in.nextNull();
                return null;
            }

            public void write(JsonWriter out, StringBuffer value) throws IOException {
                out.value(value == null ? null : value.toString());
            }
        };
        STRING_BUFFER_FACTORY = newFactory(StringBuffer.class, STRING_BUFFER);
        URL = new TypeAdapter<URL>() {
            public URL read(JsonReader in) throws IOException {
                if (in.peek() == JsonToken.NULL) {
                    in.nextNull();
                    return null;
                }
                String nextString = in.nextString();
                if ("null".equals(nextString)) {
                    return null;
                }
                return new URL(nextString);
            }

            public void write(JsonWriter out, URL value) throws IOException {
                out.value(value == null ? null : value.toExternalForm());
            }
        };
        URL_FACTORY = newFactory(URL.class, URL);
        URI = new TypeAdapter<URI>() {
            public URI read(JsonReader in) throws IOException {
                URI uri = null;
                if (in.peek() == JsonToken.NULL) {
                    in.nextNull();
                } else {
                    try {
                        String nextString = in.nextString();
                        if (!"null".equals(nextString)) {
                            uri = new URI(nextString);
                        }
                    } catch (Throwable e) {
                        throw new JsonIOException(e);
                    }
                }
                return uri;
            }

            public void write(JsonWriter out, URI value) throws IOException {
                out.value(value == null ? null : value.toASCIIString());
            }
        };
        URI_FACTORY = newFactory(URI.class, URI);
        INET_ADDRESS = new TypeAdapter<InetAddress>() {
            public InetAddress read(JsonReader in) throws IOException {
                if (in.peek() != JsonToken.NULL) {
                    return InetAddress.getByName(in.nextString());
                }
                in.nextNull();
                return null;
            }

            public void write(JsonWriter out, InetAddress value) throws IOException {
                out.value(value == null ? null : value.getHostAddress());
            }
        };
        INET_ADDRESS_FACTORY = newTypeHierarchyFactory(InetAddress.class, INET_ADDRESS);
        UUID = new TypeAdapter<UUID>() {
            public UUID read(JsonReader in) throws IOException {
                if (in.peek() != JsonToken.NULL) {
                    return UUID.fromString(in.nextString());
                }
                in.nextNull();
                return null;
            }

            public void write(JsonWriter out, UUID value) throws IOException {
                out.value(value == null ? null : value.toString());
            }
        };
        UUID_FACTORY = newFactory(UUID.class, UUID);
        TIMESTAMP_FACTORY = new TypeAdapterFactory() {
            public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> typeToken) {
                if (typeToken.getRawType() != Timestamp.class) {
                    return null;
                }
                final TypeAdapter<Date> dateTypeAdapter = gson.getAdapter(Date.class);
                return new TypeAdapter<Timestamp>() {
                    public Timestamp read(JsonReader in) throws IOException {
                        Date date = (Date) dateTypeAdapter.read(in);
                        return date != null ? new Timestamp(date.getTime()) : null;
                    }

                    public void write(JsonWriter out, Timestamp value) throws IOException {
                        dateTypeAdapter.write(out, value);
                    }
                };
            }
        };
        CALENDAR = new TypeAdapter<Calendar>() {
            public Calendar read(JsonReader in) throws IOException {
                if (in.peek() == JsonToken.NULL) {
                    in.nextNull();
                    return null;
                }
                in.beginObject();
                int year = 0;
                int month = 0;
                int dayOfMonth = 0;
                int hourOfDay = 0;
                int minute = 0;
                int second = 0;
                while (in.peek() != JsonToken.END_OBJECT) {
                    String name = in.nextName();
                    int value = in.nextInt();
                    if ("year".equals(name)) {
                        year = value;
                    } else if ("month".equals(name)) {
                        month = value;
                    } else if ("dayOfMonth".equals(name)) {
                        dayOfMonth = value;
                    } else if ("hourOfDay".equals(name)) {
                        hourOfDay = value;
                    } else if ("minute".equals(name)) {
                        minute = value;
                    } else if ("second".equals(name)) {
                        second = value;
                    }
                }
                in.endObject();
                return new GregorianCalendar(year, month, dayOfMonth, hourOfDay, minute, second);
            }

            public void write(JsonWriter out, Calendar value) throws IOException {
                if (value == null) {
                    out.nullValue();
                    return;
                }
                out.beginObject();
                out.name("year");
                out.value((long) value.get(1));
                out.name("month");
                out.value((long) value.get(2));
                out.name("dayOfMonth");
                out.value((long) value.get(5));
                out.name("hourOfDay");
                out.value((long) value.get(11));
                out.name("minute");
                out.value((long) value.get(12));
                out.name("second");
                out.value((long) value.get(13));
                out.endObject();
            }
        };
        CALENDAR_FACTORY = newFactoryForMultipleTypes(Calendar.class, GregorianCalendar.class, CALENDAR);
        LOCALE = new TypeAdapter<Locale>() {
            public Locale read(JsonReader in) throws IOException {
                if (in.peek() == JsonToken.NULL) {
                    in.nextNull();
                    return null;
                }
                StringTokenizer tokenizer = new StringTokenizer(in.nextString(), "_");
                String language = null;
                String country = null;
                String variant = null;
                if (tokenizer.hasMoreElements()) {
                    language = tokenizer.nextToken();
                }
                if (tokenizer.hasMoreElements()) {
                    country = tokenizer.nextToken();
                }
                if (tokenizer.hasMoreElements()) {
                    variant = tokenizer.nextToken();
                }
                if (country == null && variant == null) {
                    return new Locale(language);
                }
                if (variant == null) {
                    return new Locale(language, country);
                }
                return new Locale(language, country, variant);
            }

            public void write(JsonWriter out, Locale value) throws IOException {
                out.value(value == null ? null : value.toString());
            }
        };
        LOCALE_FACTORY = newFactory(Locale.class, LOCALE);
        JSON_ELEMENT = new TypeAdapter<JsonElement>() {
            public JsonElement read(JsonReader in) throws IOException {
                switch (AnonymousClass30.$SwitchMap$com$google$gson$stream$JsonToken[in.peek().ordinal()]) {
                    case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                        return new JsonPrimitive(new LazilyParsedNumber(in.nextString()));
                    case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                        return new JsonPrimitive(Boolean.valueOf(in.nextBoolean()));
                    case R.styleable.WalletImFormEditText_requiredErrorString /*3*/:
                        return new JsonPrimitive(in.nextString());
                    case R.styleable.WalletImFormEditText_required /*4*/:
                        in.nextNull();
                        return JsonNull.INSTANCE;
                    case R.styleable.WalletImFormEditText_validateWhenNotVisible /*5*/:
                        JsonElement array = new JsonArray();
                        in.beginArray();
                        while (in.hasNext()) {
                            array.add(read(in));
                        }
                        in.endArray();
                        return array;
                    case R.styleable.WalletFragmentStyle_maskedWalletDetailsBackground /*6*/:
                        JsonElement object = new JsonObject();
                        in.beginObject();
                        while (in.hasNext()) {
                            object.add(in.nextName(), read(in));
                        }
                        in.endObject();
                        return object;
                    default:
                        throw new IllegalArgumentException();
                }
            }

            public void write(JsonWriter out, JsonElement value) throws IOException {
                if (value == null || value.isJsonNull()) {
                    out.nullValue();
                } else if (value.isJsonPrimitive()) {
                    JsonPrimitive primitive = value.getAsJsonPrimitive();
                    if (primitive.isNumber()) {
                        out.value(primitive.getAsNumber());
                    } else if (primitive.isBoolean()) {
                        out.value(primitive.getAsBoolean());
                    } else {
                        out.value(primitive.getAsString());
                    }
                } else if (value.isJsonArray()) {
                    out.beginArray();
                    i$ = value.getAsJsonArray().iterator();
                    while (i$.hasNext()) {
                        write(out, (JsonElement) i$.next());
                    }
                    out.endArray();
                } else if (value.isJsonObject()) {
                    out.beginObject();
                    for (Entry<String, JsonElement> e : value.getAsJsonObject().entrySet()) {
                        out.name((String) e.getKey());
                        write(out, (JsonElement) e.getValue());
                    }
                    out.endObject();
                } else {
                    throw new IllegalArgumentException("Couldn't write " + value.getClass());
                }
            }
        };
        JSON_ELEMENT_FACTORY = newFactory(JsonElement.class, JSON_ELEMENT);
        ENUM_FACTORY = newEnumTypeHierarchyFactory();
    }

    public static <TT> TypeAdapterFactory newEnumTypeHierarchyFactory() {
        return new TypeAdapterFactory() {
            public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> typeToken) {
                Class<? super T> rawType = typeToken.getRawType();
                if (!Enum.class.isAssignableFrom(rawType) || rawType == Enum.class) {
                    return null;
                }
                if (!rawType.isEnum()) {
                    rawType = rawType.getSuperclass();
                }
                return new EnumTypeAdapter(rawType);
            }
        };
    }

    public static <TT> TypeAdapterFactory newFactory(final TypeToken<TT> type, final TypeAdapter<TT> typeAdapter) {
        return new TypeAdapterFactory() {
            public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> typeToken) {
                return typeToken.equals(type) ? typeAdapter : null;
            }
        };
    }

    public static <TT> TypeAdapterFactory newFactory(final Class<TT> type, final TypeAdapter<TT> typeAdapter) {
        return new TypeAdapterFactory() {
            public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> typeToken) {
                return typeToken.getRawType() == type ? typeAdapter : null;
            }

            public String toString() {
                return "Factory[type=" + type.getName() + ",adapter=" + typeAdapter + "]";
            }
        };
    }

    public static <TT> TypeAdapterFactory newFactory(final Class<TT> unboxed, final Class<TT> boxed, final TypeAdapter<? super TT> typeAdapter) {
        return new TypeAdapterFactory() {
            public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> typeToken) {
                Class<? super T> rawType = typeToken.getRawType();
                return (rawType == unboxed || rawType == boxed) ? typeAdapter : null;
            }

            public String toString() {
                return "Factory[type=" + boxed.getName() + "+" + unboxed.getName() + ",adapter=" + typeAdapter + "]";
            }
        };
    }

    public static <TT> TypeAdapterFactory newFactoryForMultipleTypes(final Class<TT> base, final Class<? extends TT> sub, final TypeAdapter<? super TT> typeAdapter) {
        return new TypeAdapterFactory() {
            public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> typeToken) {
                Class<? super T> rawType = typeToken.getRawType();
                return (rawType == base || rawType == sub) ? typeAdapter : null;
            }

            public String toString() {
                return "Factory[type=" + base.getName() + "+" + sub.getName() + ",adapter=" + typeAdapter + "]";
            }
        };
    }

    public static <TT> TypeAdapterFactory newTypeHierarchyFactory(final Class<TT> clazz, final TypeAdapter<TT> typeAdapter) {
        return new TypeAdapterFactory() {
            public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> typeToken) {
                return clazz.isAssignableFrom(typeToken.getRawType()) ? typeAdapter : null;
            }

            public String toString() {
                return "Factory[typeHierarchy=" + clazz.getName() + ",adapter=" + typeAdapter + "]";
            }
        };
    }
}
