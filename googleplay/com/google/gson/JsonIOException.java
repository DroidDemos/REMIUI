package com.google.gson;

public final class JsonIOException extends JsonParseException {
    public JsonIOException(String msg) {
        super(msg);
    }

    public JsonIOException(Throwable cause) {
        super(cause);
    }
}
