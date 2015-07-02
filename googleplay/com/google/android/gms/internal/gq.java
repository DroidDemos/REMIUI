package com.google.android.gms.internal;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.net.Uri;
import android.net.Uri.Builder;
import android.text.TextUtils;
import android.view.MotionEvent;
import java.util.Map;

@fd
public final class gq {
    private final Context mContext;
    private int mState;
    private final float rA;
    private String wG;
    private float wH;
    private float wI;
    private float wJ;

    public gq(Context context) {
        this.mState = 0;
        this.mContext = context;
        this.rA = context.getResources().getDisplayMetrics().density;
    }

    public gq(Context context, String str) {
        this(context);
        this.wG = str;
    }

    private void a(int i, float f, float f2) {
        if (i == 0) {
            this.mState = 0;
            this.wH = f;
            this.wI = f2;
            this.wJ = f2;
        } else if (this.mState == -1) {
        } else {
            if (i == 2) {
                if (f2 > this.wI) {
                    this.wI = f2;
                } else if (f2 < this.wJ) {
                    this.wJ = f2;
                }
                if (this.wI - this.wJ > 30.0f * this.rA) {
                    this.mState = -1;
                    return;
                }
                if (this.mState == 0 || this.mState == 2) {
                    if (f - this.wH >= 50.0f * this.rA) {
                        this.wH = f;
                        this.mState++;
                    }
                } else if ((this.mState == 1 || this.mState == 3) && f - this.wH <= -50.0f * this.rA) {
                    this.wH = f;
                    this.mState++;
                }
                if (this.mState == 1 || this.mState == 3) {
                    if (f > this.wH) {
                        this.wH = f;
                    }
                } else if (this.mState == 2 && f < this.wH) {
                    this.wH = f;
                }
            } else if (i == 1 && this.mState == 4) {
                showDialog();
            }
        }
    }

    private void showDialog() {
        Object obj;
        if (TextUtils.isEmpty(this.wG)) {
            obj = "No debug information";
        } else {
            Uri build = new Builder().encodedQuery(this.wG).build();
            StringBuilder stringBuilder = new StringBuilder();
            Map c = gn.c(build);
            for (String str : c.keySet()) {
                stringBuilder.append(str).append(" = ").append((String) c.get(str)).append("\n\n");
            }
            obj = stringBuilder.toString().trim();
            if (TextUtils.isEmpty(obj)) {
                obj = "No debug information";
            }
        }
        AlertDialog.Builder builder = new AlertDialog.Builder(this.mContext);
        builder.setMessage(obj);
        builder.setTitle("Ad Information");
        builder.setPositiveButton("Share", new OnClickListener(this) {
            final /* synthetic */ gq wL;

            public void onClick(DialogInterface dialog, int which) {
                this.wL.mContext.startActivity(Intent.createChooser(new Intent("android.intent.action.SEND").setType("text/plain").putExtra("android.intent.extra.TEXT", obj), "Share via"));
            }
        });
        builder.setNegativeButton("Close", new OnClickListener(this) {
            final /* synthetic */ gq wL;

            {
                this.wL = r1;
            }

            public void onClick(DialogInterface dialog, int which) {
            }
        });
        builder.create().show();
    }

    public void c(MotionEvent motionEvent) {
        int historySize = motionEvent.getHistorySize();
        for (int i = 0; i < historySize; i++) {
            a(motionEvent.getActionMasked(), motionEvent.getHistoricalX(0, i), motionEvent.getHistoricalY(0, i));
        }
        a(motionEvent.getActionMasked(), motionEvent.getX(), motionEvent.getY());
    }
}
