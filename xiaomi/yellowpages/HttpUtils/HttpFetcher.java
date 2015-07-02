package HttpUtils;

import android.content.Context;
import android.util.Base64;
import android.util.Log;
import com.alipay.mobilesecuritysdk.constant.ConfigConstant;
import com.alipay.mobilesecuritysdk.util.CommonUtils;
import java.util.ArrayList;
import java.util.List;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;

public class HttpFetcher {
    public HttpClient getHttpClient() {
        HttpParams basicHttpParams = new BasicHttpParams();
        HttpConnectionParams.setConnectionTimeout(basicHttpParams, ConfigConstant.SO_TIMEOUT);
        HttpConnectionParams.setSoTimeout(basicHttpParams, ConfigConstant.SO_TIMEOUT);
        return new DefaultHttpClient(basicHttpParams);
    }

    public HttpResponse uploadCollectedData(Context context, String str, String str2, String str3, String str4, boolean z) {
        HttpResponse httpResponse = null;
        if (str3 != null) {
            try {
                HttpUriRequest httpPost = new HttpPost(str);
                List paramBuilder = paramBuilder(str2, str3, str4, z);
                if (paramBuilder != null) {
                    httpPost.setEntity(new UrlEncodedFormEntity(paramBuilder, "UTF-8"));
                    httpResponse = getHttpClient().execute(httpPost);
                }
            } catch (Exception e) {
                Log.i(ConfigConstant.LOG_TAG, e.getMessage());
            }
        }
        return httpResponse;
    }

    private List paramBuilder(String str, String str2, String str3, boolean z) {
        if (str2 == null) {
            return null;
        }
        if (z) {
            try {
                String textCompress = CommonUtils.textCompress(str2);
            } catch (Exception e) {
                Log.i(ConfigConstant.LOG_TAG, e.getMessage());
                return null;
            }
        }
        textCompress = Base64.encodeToString(str2.getBytes(), 8);
        if (CommonUtils.isBlank(textCompress)) {
            return null;
        }
        List arrayList = new ArrayList();
        StringBuilder stringBuilder = new StringBuilder();
        if (!CommonUtils.isBlank(str)) {
            arrayList.add(new BasicNameValuePair("serviceId", str));
            stringBuilder.append("serviceId=");
            stringBuilder.append(str);
            stringBuilder.append("&");
        }
        if (!CommonUtils.isBlank(str3)) {
            arrayList.add(new BasicNameValuePair("version", str3));
            stringBuilder.append("version=");
            stringBuilder.append(str3);
            stringBuilder.append("&");
        }
        arrayList.add(new BasicNameValuePair("data", textCompress));
        stringBuilder.append("data=");
        stringBuilder.append(textCompress);
        stringBuilder.append(ConfigConstant.MD5_SALT);
        arrayList.add(new BasicNameValuePair("sign", CommonUtils.MD5(stringBuilder.toString())));
        Log.i(ConfigConstant.LOG_TAG, stringBuilder.toString() + arrayList.toString());
        return arrayList;
    }
}
