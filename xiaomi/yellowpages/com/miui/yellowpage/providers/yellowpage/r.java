package com.miui.yellowpage.providers.yellowpage;

import android.content.ContentProvider.PipeDataWriter;
import android.net.Uri;
import android.os.Bundle;
import android.os.ParcelFileDescriptor;
import android.os.ParcelFileDescriptor.AutoCloseOutputStream;
import com.miui.yellowpage.R;
import com.miui.yellowpage.base.utils.Files;
import com.miui.yellowpage.base.utils.Log;
import com.miui.yellowpage.base.utils.WebService;
import com.miui.yellowpage.utils.I;
import com.ta.utdid2.android.utils.Base64;
import java.io.IOException;
import java.io.OutputStream;

/* compiled from: YellowPageProvider */
final class r implements PipeDataWriter {
    final /* synthetic */ YellowPageProvider Ib;
    private byte[] mBytes;

    private r(YellowPageProvider yellowPageProvider) {
        this.Ib = yellowPageProvider;
        this.mBytes = null;
    }

    public void writeDataToPipe(ParcelFileDescriptor parcelFileDescriptor, Uri uri, String str, Bundle bundle, Object obj) {
        Throwable th;
        Log.d("YellowPageProvider", "[writeDataToPipe] The requested uri is " + uri);
        switch (this.Ib.g(uri)) {
            case Base64.NO_CLOSE /*16*/:
                this.mBytes = WebService.handleWebService(this.Ib.getContext(), uri, R.drawable.default_image);
                break;
            case 17:
                this.mBytes = Files.getFileBytes(I.H(this.Ib.getContext(), uri.toString()));
                break;
            default:
                Log.e("YellowPageProvider", "Not supported uri " + uri);
                return;
        }
        OutputStream outputStream = null;
        OutputStream autoCloseOutputStream;
        try {
            autoCloseOutputStream = new AutoCloseOutputStream(parcelFileDescriptor);
            if (autoCloseOutputStream != null) {
                try {
                    if (this.mBytes != null) {
                        autoCloseOutputStream.write(this.mBytes);
                    }
                } catch (IOException e) {
                    try {
                        Log.d("YellowPageProvider", "fail to download: " + uri);
                        if (autoCloseOutputStream != null) {
                            try {
                                autoCloseOutputStream.close();
                            } catch (IOException e2) {
                                e2.printStackTrace();
                                return;
                            }
                        }
                    } catch (Throwable th2) {
                        Throwable th3 = th2;
                        outputStream = autoCloseOutputStream;
                        th = th3;
                        if (outputStream != null) {
                            try {
                                outputStream.close();
                            } catch (IOException e3) {
                                e3.printStackTrace();
                            }
                        }
                        throw th;
                    }
                }
            }
            if (autoCloseOutputStream != null) {
                try {
                    autoCloseOutputStream.close();
                } catch (IOException e22) {
                    e22.printStackTrace();
                }
            }
        } catch (IOException e4) {
            autoCloseOutputStream = null;
            Log.d("YellowPageProvider", "fail to download: " + uri);
            if (autoCloseOutputStream != null) {
                autoCloseOutputStream.close();
            }
        } catch (Throwable th4) {
            th = th4;
            if (outputStream != null) {
                outputStream.close();
            }
            throw th;
        }
    }
}
