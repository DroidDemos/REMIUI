package com.alipay.sdk.net;

import android.content.Context;
import android.text.TextUtils;
import com.alipay.mobilesecuritysdk.constant.ConfigConstant;
import com.alipay.sdk.data.InteractionData;
import com.alipay.sdk.exception.NetErrorException;
import com.alipay.sdk.util.LogUtils;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.zip.GZIPInputStream;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.util.CharArrayBuffer;
import org.apache.http.util.EntityUtils;

public class RequestUtils {
    private static MspClient a;

    public static HttpResponse a(Context context, String str, String str2) {
        if (a == null) {
            a = new MspClient(context, str);
        } else if (!TextUtils.equals(str, a.a())) {
            a.a(str);
        }
        return a.b(str2);
    }

    public static HttpResponse a(Context context, String str, String str2, InteractionData interactionData) {
        if (a == null) {
            a = new MspClient(context, str);
        } else if (!TextUtils.equals(str, a.a())) {
            a.a(str);
        }
        if (interactionData != null) {
            return a.a(str2, interactionData);
        }
        return a.b(str2);
    }

    public static String a(HttpResponse httpResponse) {
        StatusLine statusLine = httpResponse.getStatusLine();
        int statusCode = statusLine.getStatusCode();
        HttpEntity entity = httpResponse.getEntity();
        InputStream inputStream = null;
        try {
            inputStream = entity.getContent();
            if (statusLine.getStatusCode() != ConfigConstant.RESPONSE_CODE || inputStream == null) {
                throw new NetErrorException(statusCode + " " + statusLine.getReasonPhrase());
            }
            Header contentEncoding = entity.getContentEncoding();
            if (contentEncoding != null && contentEncoding.getValue().contains("gzip")) {
                inputStream = new GZIPInputStream(inputStream);
            }
            int contentLength = (int) entity.getContentLength();
            if (contentLength < 0) {
                statusCode = 4096;
            } else {
                statusCode = contentLength;
            }
            String contentCharSet = EntityUtils.getContentCharSet(entity);
            if (contentCharSet == null) {
                contentCharSet = "UTF-8";
            }
            Reader inputStreamReader = new InputStreamReader(inputStream, contentCharSet);
            CharArrayBuffer charArrayBuffer = new CharArrayBuffer(statusCode);
            char[] cArr = new char[1024];
            while (true) {
                int read = inputStreamReader.read(cArr);
                if (read == -1) {
                    break;
                }
                charArrayBuffer.append(cArr, 0, read);
            }
            contentCharSet = charArrayBuffer.toString();
            try {
                inputStream.close();
            } catch (Exception e) {
            }
            return contentCharSet;
        } catch (Object e2) {
            LogUtils.a(e2);
            throw new NetErrorException();
        } catch (Throwable th) {
            try {
                inputStream.close();
            } catch (Exception e3) {
            }
        }
    }

    public static void a() {
        a = null;
    }

    public static String a(String str) {
        return null;
    }

    public static String b(String str) {
        return null;
    }
}
