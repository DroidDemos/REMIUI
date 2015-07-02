package org.keyczar;

import java.util.ArrayList;
import java.util.List;
import org.keyczar.enums.KeyPurpose;
import org.keyczar.enums.KeyStatus;
import org.keyczar.interfaces.KeyczarReader;

public class ImportedKeyReader implements KeyczarReader {
    private final List<KeyczarKey> keys;
    private final KeyMetadata metadata;

    ImportedKeyReader(AesKey key) {
        this.metadata = new KeyMetadata("Imported AES", KeyPurpose.DECRYPT_AND_ENCRYPT, DefaultKeyType.AES);
        this.metadata.addVersion(new KeyVersion(0, KeyStatus.PRIMARY, false));
        this.keys = new ArrayList();
        this.keys.add(key);
    }

    public String getKey(int version) {
        return ((KeyczarKey) this.keys.get(version)).toString();
    }

    public String getMetadata() {
        return this.metadata.toString();
    }
}
