package org.keyczar;

import java.util.HashMap;
import org.apache.log4j.Logger;
import org.keyczar.enums.KeyPurpose;
import org.keyczar.enums.KeyStatus;
import org.keyczar.exceptions.KeyczarException;
import org.keyczar.i18n.Messages;
import org.keyczar.interfaces.EncryptedReader;
import org.keyczar.interfaces.KeyczarReader;
import org.keyczar.util.Util;

public abstract class Keyczar {
    public static final byte[] FORMAT_BYTES;
    private static final Logger LOG;
    final HashMap<KeyHash, KeyczarKey> hashMap;
    final KeyMetadata kmd;
    KeyVersion primaryVersion;
    final HashMap<KeyVersion, KeyczarKey> versionMap;

    private class KeyHash {
        private byte[] data;

        private KeyHash(byte[] d) {
            if (d.length != 4) {
                throw new IllegalArgumentException();
            }
            this.data = d;
        }

        public boolean equals(Object o) {
            return (o instanceof KeyHash) && o.hashCode() == hashCode();
        }

        public int hashCode() {
            return Util.toInt(this.data);
        }
    }

    abstract boolean isAcceptablePurpose(KeyPurpose keyPurpose);

    static {
        LOG = Logger.getLogger(Keyczar.class);
        FORMAT_BYTES = new byte[]{(byte) 0};
    }

    public Keyczar(KeyczarReader reader) throws KeyczarException {
        this.versionMap = new HashMap();
        this.hashMap = new HashMap();
        this.kmd = KeyMetadata.read(reader.getMetadata());
        if (!isAcceptablePurpose(this.kmd.getPurpose())) {
            throw new KeyczarException(Messages.getString("Keyczar.UnacceptablePurpose", this.kmd.getPurpose()));
        } else if (!this.kmd.isEncrypted() || (reader instanceof EncryptedReader)) {
            for (KeyVersion version : this.kmd.getVersions()) {
                if (version.getStatus() == KeyStatus.PRIMARY) {
                    if (this.primaryVersion != null) {
                        throw new KeyczarException(Messages.getString("Keyczar.SinglePrimary", new Object[0]));
                    }
                    this.primaryVersion = version;
                }
                KeyczarKey key = KeyczarKey.readKey(this.kmd.getType(), reader.getKey(version.getVersionNumber()));
                LOG.debug(Messages.getString("Keyczar.ReadVersion", version));
                this.hashMap.put(new KeyHash(key.hash()), key);
                this.versionMap.put(version, key);
            }
        } else {
            throw new KeyczarException(Messages.getString("Keyczar.NeedEncryptedReader", new Object[0]));
        }
    }

    public String toString() {
        return this.kmd.toString();
    }

    KeyczarKey getPrimaryKey() {
        if (this.primaryVersion == null) {
            return null;
        }
        return (KeyczarKey) this.versionMap.get(this.primaryVersion);
    }

    KeyczarKey getKey(byte[] hash) {
        return (KeyczarKey) this.hashMap.get(new KeyHash(hash));
    }
}
