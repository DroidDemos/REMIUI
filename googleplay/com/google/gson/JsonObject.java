package com.google.gson;

import com.google.gson.internal.C$Gson$Preconditions;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public final class JsonObject extends JsonElement {
    private final Map<String, JsonElement> members;

    public JsonObject() {
        this.members = new LinkedHashMap();
    }

    public void add(String property, JsonElement value) {
        if (value == null) {
            value = JsonNull.INSTANCE;
        }
        this.members.put(C$Gson$Preconditions.checkNotNull(property), value);
    }

    public Set<Entry<String, JsonElement>> entrySet() {
        return this.members.entrySet();
    }

    public boolean equals(Object o) {
        return o == this || ((o instanceof JsonObject) && ((JsonObject) o).members.equals(this.members));
    }

    public int hashCode() {
        return this.members.hashCode();
    }
}
