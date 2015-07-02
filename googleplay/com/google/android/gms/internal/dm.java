package com.google.android.gms.internal;

import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.provider.CalendarContract.Events;
import android.text.TextUtils;
import com.google.android.gms.R;
import java.util.Map;
import org.json.JSONObject;

@fd
public class dm {
    private final Context mContext;
    private final gz mr;
    private final Map<String, String> re;
    private String rf;
    private long rg;
    private long rh;
    private String ri;
    private String rj;

    public dm(gz gzVar, Map<String, String> map) {
        this.mr = gzVar;
        this.re = map;
        this.mContext = gzVar.dI();
        bQ();
    }

    private String F(String str) {
        return TextUtils.isEmpty((CharSequence) this.re.get(str)) ? "" : (String) this.re.get(str);
    }

    private void bQ() {
        this.rf = F("description");
        this.ri = F("summary");
        this.rg = gn.T((String) this.re.get("start"));
        this.rh = gn.T((String) this.re.get("end"));
        this.rj = F("location");
    }

    Intent createIntent() {
        Intent data = new Intent("android.intent.action.EDIT").setData(Events.CONTENT_URI);
        data.putExtra("title", this.ri);
        data.putExtra("eventLocation", this.rj);
        data.putExtra("description", this.rf);
        data.putExtra("beginTime", this.rg);
        data.putExtra("endTime", this.rh);
        data.setFlags(268435456);
        return data;
    }

    public void execute() {
        if (new bq(this.mContext).bt()) {
            Builder builder = new Builder(this.mContext);
            builder.setTitle(gf.c(R.string.create_calendar_title, "Create calendar event"));
            builder.setMessage(gf.c(R.string.create_calendar_message, "Allow Ad to create a calendar event?"));
            builder.setPositiveButton(gf.c(R.string.accept, "Accept"), new OnClickListener(this) {
                final /* synthetic */ dm rk;

                {
                    this.rk = r1;
                }

                public void onClick(DialogInterface dialog, int which) {
                    this.rk.mContext.startActivity(this.rk.createIntent());
                }
            });
            builder.setNegativeButton(gf.c(R.string.decline, "Decline"), new OnClickListener(this) {
                final /* synthetic */ dm rk;

                {
                    this.rk = r1;
                }

                public void onClick(DialogInterface dialog, int which) {
                    this.rk.mr.b("onCalendarEventCanceled", new JSONObject());
                }
            });
            builder.create().show();
            return;
        }
        gw.w("This feature is not available on this version of the device.");
    }
}
