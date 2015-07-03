package com.android.providers.telephony;

import android.app.Service;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.net.Uri;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IBinder;
import android.provider.Telephony.Mms;
import android.provider.Telephony.MmsSms;
import android.text.TextUtils;
import android.util.Log;
import com.google.android.mms.pdu.EncodedStringValue;
import com.google.android.mms.pdu.PduPersister;
import com.mediatek.encapsulation.android.telephony.EncapsulatedTelephony.SmsCb.Conversations;
import com.mediatek.encapsulation.android.telephony.EncapsulatedTelephony.ThreadSettings;
import com.mediatek.encapsulation.android.telephony.EncapsulatedTelephony.WapPush;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import miui.provider.yellowpage.YellowPageUtils.AntispamNumber;
import miui.provider.yellowpage.YellowPageUtils.Provider;
import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class MakeMmsPreviewService extends Service {
    private static final int BACKGROUND_OP_INTERVAL = 500;
    private static final String CID_PREFIX = "cid:";
    private static final int COLUMN_CONTENT_ID = 2;
    private static final int COLUMN_CONTENT_LOCATION = 5;
    private static final int COLUMN_CONTENT_TYPE = 1;
    private static final int COLUMN_DATA = 7;
    private static final int COLUMN_FILENAME = 4;
    private static final int COLUMN_ID = 0;
    private static final int COLUMN_NAME = 3;
    private static final int COLUMN_TEXT = 6;
    private static final boolean LOGV = false;
    private static final int MAX_IMAGE_HEIGHT = 3200;
    private static final int MAX_IMAGE_WIDTH = 3200;
    private static final String[] PROJECTION;
    private static final String TAG = "MakeMmsPreviewService";
    private SQLiteDatabase mDb;
    private Handler mHandler;
    private HandlerThread mHandlerThread;
    private int mPreviewDimension;
    private final Runnable mProcessNextMessage;
    private final Runnable mPushAllMessages;
    private final UniqueValuedStack<Long> mStack;

    private class SmilHandler extends DefaultHandler {
        int mPartId;
        String mPreviewSrc;
        int mPreviewType;
        String mSnippetSrc;

        private SmilHandler() {
            this.mPartId = MakeMmsPreviewService.COLUMN_ID;
            this.mSnippetSrc = null;
            this.mPreviewType = MakeMmsPreviewService.COLUMN_CONTENT_TYPE;
            this.mPreviewSrc = null;
        }

        public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
            if (qName.equalsIgnoreCase("par")) {
                this.mPartId += MakeMmsPreviewService.COLUMN_CONTENT_TYPE;
                if (this.mPartId == MakeMmsPreviewService.COLUMN_CONTENT_ID) {
                    this.mPreviewType = MakeMmsPreviewService.COLUMN_TEXT;
                }
            } else if (this.mPartId != MakeMmsPreviewService.COLUMN_CONTENT_TYPE) {
            } else {
                if (qName.equalsIgnoreCase(WapPush.TEXT)) {
                    this.mSnippetSrc = attributes.getValue("src");
                } else if (qName.equalsIgnoreCase("img")) {
                    this.mPreviewType = MakeMmsPreviewService.COLUMN_CONTENT_ID;
                    this.mPreviewSrc = attributes.getValue("src");
                } else if (qName.equalsIgnoreCase("video")) {
                    this.mPreviewType = MakeMmsPreviewService.COLUMN_FILENAME;
                } else if (qName.equalsIgnoreCase("audio")) {
                    this.mPreviewType = MakeMmsPreviewService.COLUMN_NAME;
                }
            }
        }
    }

    public MakeMmsPreviewService() {
        this.mStack = new UniqueValuedStack();
        this.mPreviewDimension = COLUMN_ID;
        this.mProcessNextMessage = new Runnable() {
            public void run() {
                while (!MakeMmsPreviewService.this.mHandler.hasMessages(MakeMmsPreviewService.COLUMN_ID)) {
                    try {
                        Long msgId = (Long) MakeMmsPreviewService.this.mStack.pop();
                        if (msgId != null) {
                            long interval = System.currentTimeMillis() - MmsSmsUtils.getPrevousOpTime();
                            if (interval < 500) {
                                Thread.sleep(500 - interval);
                            }
                            MakeMmsPreviewService.this.makePreview(msgId.longValue());
                        } else {
                            return;
                        }
                    } catch (IOException e) {
                        Log.e(MakeMmsPreviewService.TAG, "Failed to make preview.", e);
                        return;
                    } catch (InterruptedException e2) {
                        Log.e(MakeMmsPreviewService.TAG, "Interrupted.", e2);
                        return;
                    }
                }
            }
        };
        this.mPushAllMessages = new Runnable() {
            public void run() {
                String[] strArr = new String[MakeMmsPreviewService.COLUMN_CONTENT_TYPE];
                strArr[MakeMmsPreviewService.COLUMN_ID] = "_id";
                Cursor c = MakeMmsPreviewService.this.mDb.query("pdu", strArr, "need_download=0 AND preview_type=0 AND msg_box!=3 AND (m_type=132 OR m_type=128)", null, null, null, "date,date_ms_part");
                if (c == null) {
                    Log.e(MakeMmsPreviewService.TAG, "Cannot read database");
                    return;
                }
                try {
                    Log.v(MakeMmsPreviewService.TAG, "Got " + c.getCount() + " messages to process");
                    c.moveToPosition(-1);
                    while (c.moveToNext()) {
                        MakeMmsPreviewService.this.mStack.push(Long.valueOf(c.getLong(MakeMmsPreviewService.COLUMN_ID)));
                    }
                    MakeMmsPreviewService.this.mHandler.post(MakeMmsPreviewService.this.mProcessNextMessage);
                } finally {
                    c.close();
                }
            }
        };
    }

    public void onCreate() {
        this.mHandlerThread = new HandlerThread(TAG, 19);
        this.mHandlerThread.start();
        this.mHandler = new Handler(this.mHandlerThread.getLooper());
        this.mDb = MmsSmsDatabaseHelper.getInstance(this).getWritableDatabase();
        this.mPreviewDimension = getResources().getDimensionPixelSize(R.dimen.preview_dimension);
    }

    public void onDestroy() {
        this.mHandlerThread.quit();
    }

    public IBinder onBind(Intent intent) {
        return null;
    }

    public int onStartCommand(Intent intent, int flags, int startId) {
        if (intent != null) {
            if ("android.provider.Telephony.MAKE_MMS_PREVIEW".equals(intent.getAction())) {
                if (intent.getExtras() == null) {
                    this.mHandler.post(this.mPushAllMessages);
                } else {
                    final Object id = intent.getExtras().get("_id");
                    if (id == null) {
                        this.mHandler.post(this.mPushAllMessages);
                    } else if (id instanceof Long) {
                        this.mHandler.post(new Runnable() {
                            public void run() {
                                MakeMmsPreviewService.this.mStack.push((Long) id);
                                MakeMmsPreviewService.this.mHandler.post(MakeMmsPreviewService.this.mProcessNextMessage);
                            }
                        });
                    } else if (id instanceof long[]) {
                        final long[] ids = (long[]) id;
                        this.mHandler.post(new Runnable() {
                            public void run() {
                                for (int i = MakeMmsPreviewService.COLUMN_ID; i < ids.length; i += MakeMmsPreviewService.COLUMN_CONTENT_TYPE) {
                                    MakeMmsPreviewService.this.mStack.push(Long.valueOf(ids[i]));
                                }
                                MakeMmsPreviewService.this.mHandler.post(MakeMmsPreviewService.this.mProcessNextMessage);
                            }
                        });
                    }
                }
            }
        }
        return COLUMN_CONTENT_ID;
    }

    static {
        PROJECTION = new String[]{"_id", "ct", AntispamNumber.CID, Provider.NAME, "fn", "cl", WapPush.TEXT, ThreadSettings.WALLPAPER};
    }

    private void makePreview(long msgId) throws IOException {
        Reader stringReader;
        if (needMakePreview(msgId)) {
            Cursor c = this.mDb.query("part", PROJECTION, "mid=" + msgId, null, null, null, null);
            if (c == null) {
                throw new IOException("Cannot read database");
            }
            try {
                c.moveToPosition(-1);
                while (c.moveToNext()) {
                    if ("application/smil".equals(c.getString(COLUMN_CONTENT_TYPE))) {
                        break;
                    }
                }
                if (c.isAfterLast()) {
                    Log.v(TAG, "No smil doc for msg " + msgId + ", use raw make.");
                    makePreviewWithoutSmilDoc(c, msgId);
                    c.close();
                    return;
                }
                String doc = c.getString(COLUMN_TEXT);
                if (doc == null) {
                    Log.v(TAG, "No text field for smil doc, use raw make.");
                    makePreviewWithoutSmilDoc(c, msgId);
                    c.close();
                    return;
                }
                SAXParser parser = SAXParserFactory.newInstance().newSAXParser();
                MakeMmsPreviewService makeMmsPreviewService = this;
                DefaultHandler smilHandler = new SmilHandler();
                stringReader = new StringReader(doc);
                parser.parse(new InputSource(stringReader), smilHandler);
                stringReader.close();
                int previewType = smilHandler.mPreviewType;
                String previewSrc = smilHandler.mPreviewSrc;
                String snippetSrc = smilHandler.mSnippetSrc;
                int attachmentCount = COLUMN_ID;
                c.moveToPosition(-1);
                String contentType = null;
                while (c.moveToNext()) {
                    if (!TextUtils.isEmpty(c.getString(COLUMN_DATA))) {
                        attachmentCount += COLUMN_CONTENT_TYPE;
                        contentType = c.getString(COLUMN_CONTENT_TYPE);
                    }
                }
                if (attachmentCount == COLUMN_CONTENT_TYPE) {
                    c.moveToFirst();
                    if ("text/x-vCard".equals(contentType)) {
                        previewType = COLUMN_CONTENT_LOCATION;
                        previewSrc = null;
                    }
                } else if (attachmentCount > COLUMN_CONTENT_TYPE) {
                    previewType = COLUMN_TEXT;
                }
                makePreviewWithSmilDoc(c, msgId, previewType, snippetSrc, previewSrc);
                c.close();
            } catch (ParserConfigurationException e) {
                try {
                    e.printStackTrace();
                } finally {
                    c.close();
                }
            } catch (SAXException e2) {
                e2.printStackTrace();
                c.close();
            } catch (IOException e3) {
                e3.printStackTrace();
                c.close();
            } catch (Throwable th) {
                stringReader.close();
            }
        }
    }

    private boolean needMakePreview(long msgId) {
        String[] strArr = new String[COLUMN_CONTENT_TYPE];
        strArr[COLUMN_ID] = "preview_type";
        Cursor c = this.mDb.query("pdu", strArr, "_id=" + msgId, null, null, null, null);
        if (c == null) {
            return LOGV;
        }
        try {
            if (c.moveToFirst() && c.getInt(COLUMN_ID) == 0) {
                return true;
            }
            c.close();
            return LOGV;
        } finally {
            c.close();
        }
    }

    private static String unescapeXML(String str) {
        return str.replaceAll("&lt;", "<").replaceAll("&gt;", ">").replaceAll("&quot;", "\"").replaceAll("&apos;", "'").replaceAll("&amp;", "&");
    }

    private void locatePart(Cursor c, String src) {
        src = unescapeXML(src);
        boolean isCid = src.startsWith(CID_PREFIX);
        if (isCid) {
            src = src.substring(CID_PREFIX.length());
        }
        int lastDot = src.lastIndexOf(46);
        String strippedSrc = null;
        if (lastDot != -1) {
            strippedSrc = src.substring(COLUMN_ID, lastDot);
        }
        c.moveToPosition(-1);
        while (c.moveToNext()) {
            String value;
            if (isCid) {
                value = fromIsoString(c.getString(COLUMN_CONTENT_ID));
                if (!TextUtils.isEmpty(value) && value.charAt(COLUMN_ID) == '<' && value.charAt(value.length() - 1) == '>') {
                    value = value.substring(COLUMN_CONTENT_TYPE, value.length() - 1);
                    if (!TextUtils.isEmpty(value) && (TextUtils.equals(value, src) || TextUtils.equals(value, strippedSrc))) {
                        return;
                    }
                }
            }
            value = fromIsoString(c.getString(COLUMN_NAME));
            if (TextUtils.isEmpty(value) || !(TextUtils.equals(value, src) || TextUtils.equals(value, strippedSrc))) {
                value = fromIsoString(c.getString(COLUMN_FILENAME));
                if (TextUtils.isEmpty(value) || !(TextUtils.equals(value, src) || TextUtils.equals(value, strippedSrc))) {
                    value = fromIsoString(c.getString(COLUMN_CONTENT_LOCATION));
                    if (TextUtils.isEmpty(value) || !(TextUtils.equals(value, src) || TextUtils.equals(value, strippedSrc))) {
                        value = fromIsoString(c.getString(COLUMN_CONTENT_ID));
                        if (!TextUtils.isEmpty(value) && value.charAt(COLUMN_ID) == '<' && value.charAt(value.length() - 1) == '>') {
                            value = value.substring(COLUMN_CONTENT_TYPE, value.length() - 1);
                        }
                        if (TextUtils.isEmpty(value)) {
                            continue;
                        } else if (!TextUtils.equals(value, src)) {
                            if (TextUtils.equals(value, strippedSrc)) {
                                return;
                            }
                        } else {
                            return;
                        }
                    }
                    return;
                }
                return;
            }
            return;
        }
    }

    private static String fromIsoString(String iso) {
        return TextUtils.isEmpty(iso) ? iso : new EncodedStringValue(106, PduPersister.getBytes(iso)).getString();
    }

    private void makePreviewWithSmilDoc(Cursor c, long msgId, int previewType, String snippetSrc, String previewSrc) throws IOException {
        String snippet = null;
        if (snippetSrc != null) {
            locatePart(c, snippetSrc);
            if (c.isAfterLast()) {
                Log.v(TAG, "Text part " + snippetSrc + " for msg " + msgId + " missing.");
                return;
            }
            snippet = c.getString(COLUMN_TEXT);
            if (snippet == null) {
                Log.w(TAG, "Text field of part " + c.getLong(COLUMN_ID) + " not filled. Bail out.");
                return;
            }
        }
        String previewDataPath = null;
        if (previewSrc != null) {
            locatePart(c, previewSrc);
            if (c.isAfterLast()) {
                Log.v(TAG, "Image part " + snippetSrc + " for msg " + msgId + " missing.");
                return;
            }
            previewDataPath = c.getString(COLUMN_DATA);
            if (previewDataPath == null) {
                Log.v(TAG, "Data field of part " + c.getLong(COLUMN_ID) + " not filled. Bail out.");
                return;
            }
        }
        setPreview(msgId, previewType, snippet, previewDataPath);
    }

    private void makePreviewWithoutSmilDoc(Cursor c, long msgId) throws IOException {
        boolean metImage = LOGV;
        boolean metVideo = LOGV;
        boolean metAudio = LOGV;
        boolean metText = LOGV;
        String snippet = null;
        String previewDataPath = null;
        int previewType = COLUMN_CONTENT_TYPE;
        c.moveToPosition(-1);
        while (c.moveToNext()) {
            String ct = c.getString(COLUMN_CONTENT_TYPE);
            if (ct != null) {
                if (ct.equals("text/x-vCard")) {
                    if (metAudio || metVideo || COLUMN_ID != null || metImage) {
                        previewType = COLUMN_TEXT;
                        break;
                    } else {
                        metAudio = true;
                        previewType = COLUMN_CONTENT_LOCATION;
                    }
                } else if (ct.startsWith("audio/")) {
                    if (metAudio || metVideo || COLUMN_ID != null) {
                        previewType = COLUMN_TEXT;
                        break;
                    } else {
                        metAudio = true;
                        previewType = COLUMN_NAME;
                    }
                } else if (ct.startsWith("video/")) {
                    if (metAudio || metVideo || COLUMN_ID != null || metImage) {
                        previewType = COLUMN_TEXT;
                        break;
                    } else {
                        metVideo = true;
                        previewType = COLUMN_FILENAME;
                    }
                } else if (ct.startsWith("image/")) {
                    if (metVideo || COLUMN_ID != null || metImage) {
                        previewType = COLUMN_TEXT;
                        break;
                    }
                    metImage = true;
                    previewType = COLUMN_CONTENT_ID;
                    previewDataPath = c.getString(COLUMN_DATA);
                } else if (!ct.startsWith("text/")) {
                    continue;
                } else if (metText) {
                    previewType = COLUMN_TEXT;
                    break;
                } else {
                    metText = true;
                    snippet = c.getString(COLUMN_TEXT);
                }
            }
        }
        setPreview(msgId, previewType, snippet, previewDataPath);
    }

    private void setPreview(long msgId, int previewType, String snippet, String previewDataPath) {
        byte[] previewData = null;
        if (previewDataPath != null) {
            Options ops = new Options();
            ops.inJustDecodeBounds = true;
            BitmapFactory.decodeFile(previewDataPath, ops);
            if (ops.outWidth >= MAX_IMAGE_WIDTH || ops.outHeight >= MAX_IMAGE_WIDTH) {
                Log.v(TAG, "Bitmap is too large");
            } else {
                int width = ops.outWidth;
                int height = ops.outHeight;
                int sampleSize = COLUMN_CONTENT_TYPE;
                if (width > this.mPreviewDimension) {
                    sampleSize = width / this.mPreviewDimension;
                }
                if (height > this.mPreviewDimension) {
                    sampleSize = Math.max(sampleSize, height / this.mPreviewDimension);
                }
                ops = new Options();
                ops.inSampleSize = sampleSize;
                Bitmap bmp = BitmapFactory.decodeFile(previewDataPath, ops);
                if (bmp == null) {
                    Log.v(TAG, "Bitmap file " + previewDataPath + " doesn't exist. Bail out");
                } else {
                    width = bmp.getWidth();
                    height = bmp.getHeight();
                    if (width > this.mPreviewDimension || height > this.mPreviewDimension) {
                        if (width > height) {
                            height = Math.max(COLUMN_CONTENT_TYPE, (this.mPreviewDimension * height) / width);
                            width = this.mPreviewDimension;
                        } else {
                            width = Math.max(COLUMN_CONTENT_TYPE, (this.mPreviewDimension * width) / height);
                            height = this.mPreviewDimension;
                        }
                    }
                    Bitmap scaledBmp = Bitmap.createScaledBitmap(bmp, width, height, LOGV);
                    ByteArrayOutputStream os = new ByteArrayOutputStream();
                    try {
                        scaledBmp.compress(CompressFormat.JPEG, 90, os);
                        scaledBmp.recycle();
                        bmp.recycle();
                        previewData = os.toByteArray();
                    } finally {
                        try {
                            os.close();
                        } catch (IOException e) {
                        }
                    }
                }
            }
        }
        ContentValues v = new ContentValues();
        v.put("preview_type", Integer.valueOf(previewType));
        v.put(Conversations.SNIPPET, snippet);
        v.put("preview_data", previewData);
        v.put("preview_data_ts", Long.valueOf(previewData == null ? 0 : System.currentTimeMillis()));
        this.mDb.update("pdu", v, "_id=" + msgId, null);
        ContentResolver cr = getContentResolver();
        cr.notifyChange(Uri.withAppendedPath(Mms.CONTENT_URI, String.valueOf(msgId)), null);
        cr.notifyChange(MmsSms.CONTENT_URI, null);
        Log.v(TAG, "Created preview for msg " + msgId);
    }
}
