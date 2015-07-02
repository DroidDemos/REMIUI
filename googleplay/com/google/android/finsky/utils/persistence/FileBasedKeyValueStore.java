package com.google.android.finsky.utils.persistence;

import android.text.TextUtils;
import com.google.android.finsky.utils.FinskyLog;
import com.google.android.finsky.utils.Maps;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Iterator;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

public class FileBasedKeyValueStore implements KeyValueStore {
    private final String mDataStoreId;
    private final File mRootDirectory;

    public FileBasedKeyValueStore(File rootDirectory, String dataStoreId) {
        if (TextUtils.isEmpty(dataStoreId)) {
            throw new IllegalArgumentException("A dataStoreId must be specified");
        }
        this.mRootDirectory = rootDirectory;
        this.mDataStoreId = dataStoreId;
    }

    public void delete(String key) {
        if (!new File(this.mRootDirectory, this.mDataStoreId + key).delete()) {
            FinskyLog.e("Attempt to delete '%s' failed!", key);
        }
    }

    public Map<String, Map<String, String>> fetchAll() {
        Map<String, Map<String, String>> output = Maps.newHashMap();
        File[] files = this.mRootDirectory.listFiles();
        if (files != null) {
            for (File file : files) {
                String fileName = file.getName();
                try {
                    if (fileName.startsWith(this.mDataStoreId)) {
                        FileInputStream fis = new FileInputStream(file);
                        ObjectInputStream ois = new ObjectInputStream(fis);
                        String key = fileName.replace(this.mDataStoreId, "");
                        JSONObject jsonObject = new JSONObject(ois.readUTF());
                        fis.close();
                        output.put(key, parseMapFromJson(jsonObject));
                    }
                } catch (IOException e) {
                    FinskyLog.d("IOException when reading file '%s'. Deleting.", fileName);
                    if (!file.delete()) {
                        FinskyLog.e("Attempt to delete '%s' failed!", fileName);
                    }
                } catch (JSONException e2) {
                    FinskyLog.e("JSONException when reading file '%s'. Deleting. error=[%s]", fileName, e2.toString());
                    if (!file.delete()) {
                        FinskyLog.e("Attempt to delete '%s' failed!", fileName);
                    }
                }
            }
        }
        return output;
    }

    public Map<String, String> get(String key) {
        String name;
        File file = new File(this.mRootDirectory, this.mDataStoreId + key);
        try {
            FileInputStream fis = new FileInputStream(file);
            JSONObject jsonObject = new JSONObject(new ObjectInputStream(fis).readUTF());
            fis.close();
            return parseMapFromJson(jsonObject);
        } catch (IOException e) {
            name = file.getName();
            FinskyLog.d("IOException when reading file '%s'. Deleting.", name);
            if (!file.delete()) {
                FinskyLog.e("Attempt to delete '%s' failed!", name);
            }
            return null;
        } catch (JSONException e2) {
            name = file.getName();
            FinskyLog.e("JSONException when reading file '%s'. Deleting. error=[%s]", name, e2.toString());
            if (!file.delete()) {
                FinskyLog.e("Attempt to delete '%s' failed!", name);
            }
            return null;
        }
    }

    public void put(String key, Map<String, String> valueMap) {
        IOException e;
        Throwable th;
        FileOutputStream fos = null;
        ObjectOutputStream oos = null;
        try {
            FileOutputStream fos2 = new FileOutputStream(new File(this.mRootDirectory, this.mDataStoreId + key));
            try {
                ObjectOutputStream oos2 = new ObjectOutputStream(fos2);
                try {
                    oos2.writeUTF(new JSONObject(valueMap).toString());
                    oos2.flush();
                    if (oos2 != null) {
                        try {
                            oos2.close();
                        } catch (IOException e2) {
                            oos = oos2;
                            fos = fos2;
                            return;
                        }
                    } else if (fos2 != null) {
                        fos2.close();
                    }
                    oos = oos2;
                    fos = fos2;
                } catch (IOException e3) {
                    e = e3;
                    oos = oos2;
                    fos = fos2;
                    try {
                        FinskyLog.w("Couldn't write value store for key %s: %s", key, e);
                        if (oos == null) {
                            try {
                                oos.close();
                            } catch (IOException e4) {
                            }
                        } else if (fos == null) {
                            fos.close();
                        }
                    } catch (Throwable th2) {
                        th = th2;
                        if (oos == null) {
                            try {
                                oos.close();
                            } catch (IOException e5) {
                            }
                        } else if (fos != null) {
                            fos.close();
                        }
                        throw th;
                    }
                } catch (Throwable th3) {
                    th = th3;
                    oos = oos2;
                    fos = fos2;
                    if (oos == null) {
                        oos.close();
                    } else if (fos != null) {
                        fos.close();
                    }
                    throw th;
                }
            } catch (IOException e6) {
                e = e6;
                fos = fos2;
                FinskyLog.w("Couldn't write value store for key %s: %s", key, e);
                if (oos == null) {
                    oos.close();
                } else if (fos == null) {
                    fos.close();
                }
            } catch (Throwable th4) {
                th = th4;
                fos = fos2;
                if (oos == null) {
                    oos.close();
                } else if (fos != null) {
                    fos.close();
                }
                throw th;
            }
        } catch (IOException e7) {
            e = e7;
            FinskyLog.w("Couldn't write value store for key %s: %s", key, e);
            if (oos == null) {
                oos.close();
            } else if (fos == null) {
                fos.close();
            }
        }
    }

    private Map<String, String> parseMapFromJson(JSONObject jsonObject) throws JSONException {
        Map<String, String> outputMap = Maps.newHashMap();
        Iterator<String> keys = jsonObject.keys();
        while (keys.hasNext()) {
            String key = (String) keys.next();
            if (jsonObject.isNull(key)) {
                outputMap.put(key, null);
            } else {
                outputMap.put(key, jsonObject.getString(key));
            }
        }
        return outputMap;
    }
}
