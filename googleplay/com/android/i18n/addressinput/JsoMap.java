package com.android.i18n.addressinput;

import java.util.ArrayList;
import java.util.Iterator;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

class JsoMap extends JSONObject {
    static JsoMap buildJsoMap(String json) throws JSONException {
        return new JsoMap(new JSONTokener(json));
    }

    static JsoMap createEmptyJsoMap() {
        return new JsoMap();
    }

    protected JsoMap() {
    }

    private JsoMap(JSONTokener readFrom) throws JSONException {
        super(readFrom);
    }

    private JsoMap(JSONObject copyFrom, String[] names) throws JSONException {
        super(copyFrom, names);
    }

    public String get(String key) {
        try {
            Object o = super.get(key);
            if (o instanceof String) {
                return (String) o;
            }
            if (o instanceof Integer) {
                throw new IllegalArgumentException();
            }
            throw new ClassCastException();
        } catch (JSONException e) {
            return null;
        }
    }

    private Object getObject(String name) throws JSONException {
        return super.get(name);
    }

    public int getInt(String key) {
        try {
            Object o = super.get(key);
            if (o instanceof Integer) {
                return ((Integer) o).intValue();
            }
            throw new RuntimeException("Something other than an int was returned");
        } catch (JSONException e) {
            return -1;
        }
    }

    JSONArray getKeys() {
        return super.names();
    }

    JsoMap getObj(String key) throws ClassCastException, IllegalArgumentException {
        try {
            Object o = super.get(key);
            if (o instanceof JSONObject) {
                JSONObject value = (JSONObject) o;
                ArrayList<String> keys = new ArrayList(value.length());
                Iterator<String> it = value.keys();
                while (it.hasNext()) {
                    keys.add(it.next());
                }
                return new JsoMap(value, (String[]) keys.toArray(new String[keys.size()]));
            } else if (o instanceof Integer) {
                throw new IllegalArgumentException();
            } else {
                throw new ClassCastException();
            }
        } catch (JSONException e) {
            return null;
        }
    }

    boolean containsKey(String key) {
        return super.has(key);
    }

    void mergeData(JsoMap obj) {
        if (obj != null) {
            JSONArray names = obj.names();
            if (names != null) {
                for (int i = 0; i < names.length(); i++) {
                    try {
                        String name = names.getString(i);
                        if (!super.has(name)) {
                            super.put(name, obj.getObject(name));
                        }
                    } catch (JSONException e) {
                        throw new RuntimeException(e);
                    } catch (JSONException e2) {
                    }
                }
            }
        }
    }

    void putObj(String key, JSONObject value) {
        try {
            super.put(key, value);
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
    }
}
