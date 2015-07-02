package org.keyczar.interfaces;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;
import org.keyczar.DefaultKeyType;
import org.keyczar.KeyczarKey;
import org.keyczar.exceptions.KeyczarException;

public interface KeyType {

    public interface Builder {
        KeyczarKey read(String str) throws KeyczarException;
    }

    public static class KeyTypeDeserializer implements JsonDeserializer<KeyType> {
        private static Map<String, KeyType> typeMap;

        static {
            typeMap = new HashMap();
            for (DefaultKeyType key : DefaultKeyType.values()) {
                registerType(key);
            }
        }

        public static void registerType(KeyType keyType) {
            String name = keyType.getName();
            if (typeMap.containsKey(name)) {
                throw new IllegalArgumentException("Attempt to map two key types to the same name " + name);
            }
            typeMap.put(name, keyType);
        }

        public KeyType deserialize(JsonElement json, Type type, JsonDeserializationContext context) throws JsonParseException {
            String keyName = json.getAsJsonPrimitive().getAsString();
            if (typeMap.containsKey(keyName)) {
                return (KeyType) typeMap.get(keyName);
            }
            throw new IllegalArgumentException("Cannot deserialize " + keyName + " no such key has been registered.");
        }
    }

    public static class KeyTypeSerializer implements JsonSerializer<KeyType> {
        public JsonElement serialize(KeyType src, Type type, JsonSerializationContext context) {
            return new JsonPrimitive(src.getName());
        }
    }

    Builder getBuilder();

    String getName();

    int getOutputSize();

    int getOutputSize(int i);

    boolean isAcceptableSize(int i);
}
