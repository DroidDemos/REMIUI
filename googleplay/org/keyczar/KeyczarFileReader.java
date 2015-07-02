package org.keyczar;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import org.keyczar.exceptions.KeyczarException;
import org.keyczar.i18n.Messages;
import org.keyczar.interfaces.KeyczarReader;

public class KeyczarFileReader implements KeyczarReader {
    private String location;

    public KeyczarFileReader(String fileLocation) {
        if (!(fileLocation == null || fileLocation.endsWith(File.separator))) {
            fileLocation = fileLocation + File.separator;
        }
        this.location = fileLocation;
    }

    public String getKey(int version) throws KeyczarException {
        return readFile(this.location + version);
    }

    public String getMetadata() throws KeyczarException {
        return readFile(this.location + "meta");
    }

    private String readFile(String filename) throws KeyczarException {
        try {
            RandomAccessFile file = new RandomAccessFile(filename, "r");
            byte[] contents = new byte[((int) file.length())];
            file.read(contents);
            file.close();
            return new String(contents);
        } catch (IOException e) {
            throw new KeyczarException(Messages.getString("KeyczarFileReader.FileError", filename), e);
        }
    }
}
