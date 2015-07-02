package com.xiaomi.xmsf.push.service.trace;

import com.xiaomi.xmsf.push.service.MyLog;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

class TraceLogCache {
    private StringBuilder mBuilder;
    private File mCacheFile;

    public static class CacheLine {
        public String mBase64;
        public String mMd5;
        public int mShowType;

        public CacheLine() {
            this.mShowType = -1;
            String str = "";
            this.mMd5 = str;
            this.mBase64 = str;
        }

        public CacheLine(int showType, String base64, String md5) {
            this.mShowType = showType;
            this.mBase64 = base64;
            this.mMd5 = md5;
        }
    }

    private void createOutFolderIfNeeded(String outputPath) {
        String pFolderPath;
        int index = outputPath.lastIndexOf("/");
        if (index == -1) {
            pFolderPath = outputPath;
        } else {
            pFolderPath = outputPath.substring(0, index);
        }
        File pFolder = new File(pFolderPath);
        if (!pFolder.exists()) {
            pFolder.mkdirs();
        }
    }

    public TraceLogCache(String savepath) {
        this.mBuilder = new StringBuilder();
        createOutFolderIfNeeded(savepath);
        this.mCacheFile = new File(savepath);
        if (!this.mCacheFile.exists()) {
            try {
                this.mCacheFile.createNewFile();
                if (!this.mCacheFile.exists()) {
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void appendInfo(CacheLine cell) {
        this.mBuilder.append(cell.mShowType + "\t");
        this.mBuilder.append(cell.mBase64 + "\t" + cell.mMd5);
        this.mBuilder.append("\r\n");
    }

    public void flushFile() {
        try {
            FileWriter writer = new FileWriter(this.mCacheFile, true);
            writer.write(this.mBuilder.toString());
            writer.close();
            this.mBuilder.delete(0, this.mBuilder.length());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<CacheLine> readCacheLineFromFile() {
        ArrayList<CacheLine> list = new ArrayList();
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(this.mCacheFile), "utf-8"));
            for (String str = reader.readLine(); str != null; str = reader.readLine()) {
                String[] splits = str.split("\t");
                if (splits != null && splits.length == 3) {
                    try {
                        list.add(new CacheLine(Integer.valueOf(splits[0]).intValue(), splits[1], splits[2]));
                    } catch (Exception e) {
                        MyLog.e("\u8bfb\u53d6log cache \u5931\u8d25");
                    }
                }
            }
            reader.close();
            this.mCacheFile.delete();
            this.mCacheFile.createNewFile();
        } catch (IOException e2) {
            e2.printStackTrace();
        }
        return list;
    }
}
