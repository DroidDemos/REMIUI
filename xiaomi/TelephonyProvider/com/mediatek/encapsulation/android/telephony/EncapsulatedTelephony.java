package com.mediatek.encapsulation.android.telephony;

import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SqliteWrapper;
import android.net.Uri;
import android.net.Uri.Builder;
import android.provider.BaseColumns;
import android.telephony.SmsCbMessage;
import android.util.Log;
import java.util.HashSet;
import java.util.Set;

public class EncapsulatedTelephony {
    private static final String TAG = "EncapsulatedTelephony";

    public interface BaseMmsColumns {
        public static final String SERVICE_CENTER = "service_center";
        public static final String SIM_ID = "sim_id";
    }

    public static final class Carriers implements BaseColumns {
        public static final Uri CONTENT_URI_2;
        public static final Uri CONTENT_URI_DM;
        public static final String CSD_NUM = "csdnum";
        public static final String IMSI = "imsi";
        public static final String NAPID = "napid";
        public static final String OMACPID = "omacpid";
        public static final String PROXYID = "proxyid";
        public static final String SOURCE_TYPE = "sourcetype";
        public static final String SPN = "spn";

        public static final class GeminiCarriers {
            public static final Uri CONTENT_URI;
            public static final Uri CONTENT_URI_DM;

            static {
                CONTENT_URI = android.provider.Telephony.Carriers.GeminiCarriers.CONTENT_URI;
                CONTENT_URI_DM = android.provider.Telephony.Carriers.GeminiCarriers.CONTENT_URI_DM;
            }
        }

        public static final class SIM1Carriers {
            public static final Uri CONTENT_URI;

            static {
                CONTENT_URI = android.provider.Telephony.Carriers.SIM1Carriers.CONTENT_URI;
            }
        }

        public static final class SIM2Carriers {
            public static final Uri CONTENT_URI;

            static {
                CONTENT_URI = android.provider.Telephony.Carriers.SIM2Carriers.CONTENT_URI;
            }
        }

        static {
            CONTENT_URI_DM = android.provider.Telephony.Carriers.CONTENT_URI_DM;
            CONTENT_URI_2 = android.provider.Telephony.Carriers.CONTENT_URI_2;
        }
    }

    public static final class GprsInfo implements BaseColumns {
        public static final Uri CONTENT_URI;
        public static final String GPRS_IN = "gprs_in";
        public static final String GPRS_OUT = "gprs_out";
        public static final String SIM_ID = "sim_id";

        static {
            CONTENT_URI = android.provider.Telephony.GprsInfo.CONTENT_URI;
        }
    }

    public static final class Mms implements BaseMmsColumns {

        public static final class ScrapSpace {
            public static final Uri CONTENT_URI;
            public static final String SCRAP_FILE_PATH = "/sdcard/mms/scrapSpace/.temp.jpg";

            static {
                CONTENT_URI = android.provider.Telephony.Mms.ScrapSpace.CONTENT_URI;
            }
        }
    }

    public static final class MmsSms {
        public static final Uri CONTENT_URI_QUICKTEXT;

        public static final class PendingMessages {
            public static final String SIM_ID = "pending_sim_id";
        }

        static {
            CONTENT_URI_QUICKTEXT = android.provider.Telephony.MmsSms.CONTENT_URI_QUICKTEXT;
        }
    }

    public interface TextBasedSmsColumns {
        public static final String SIM_ID = "sim_id";
    }

    public static final class Sms implements BaseColumns, TextBasedSmsColumns {
        public static final String IPMSG_ID = "ipmsg_id";

        public static Uri addMessageToUri(ContentResolver resolver, Uri uri, String address, String body, String subject, Long date, boolean read, boolean deliveryReport, long threadId) {
            return android.provider.Telephony.Sms.addMessageToUri(resolver, uri, address, body, subject, date, read, deliveryReport, threadId, -1);
        }

        public static Uri addMessageToUri(ContentResolver resolver, Uri uri, String address, String body, String subject, Long date, boolean read, boolean deliveryReport, long threadId, int simId) {
            return android.provider.Telephony.Sms.addMessageToUri(resolver, uri, address, body, subject, null, date, read, deliveryReport, threadId, simId);
        }

        public static Uri addMessageToUri(ContentResolver resolver, Uri uri, String address, String body, String subject, String sc, Long date, boolean read, boolean deliveryReport, long threadId, int simId) {
            return android.provider.Telephony.Sms.addMessageToUri(resolver, uri, address, body, subject, sc, date, read, deliveryReport, threadId, simId);
        }
    }

    public interface TextBasedSmsCbColumns {
        public static final String BODY = "body";
        public static final String CHANNEL_ID = "channel_id";
        public static final String DATE = "date";
        public static final String LOCKED = "locked";
        public static final String READ = "read";
        public static final String SEEN = "seen";
        public static final String SIM_ID = "sim_id";
        public static final String THREAD_ID = "thread_id";
    }

    public static final class SmsCb implements BaseColumns, TextBasedSmsCbColumns {
        public static final Uri ADDRESS_URI;
        public static final Uri CONTENT_URI;
        public static final String DEFAULT_SORT_ORDER = "date DESC";

        public interface CanonicalAddressesColumns extends BaseColumns {
            public static final String ADDRESS = "address";
        }

        public static final class CbChannel implements BaseColumns {
            public static final Uri CONTENT_URI;
            public static final String ENABLE = "enable";
            public static final String NAME = "name";
            public static final String NUMBER = "number";

            static {
                CONTENT_URI = android.provider.Telephony.SmsCb.CbChannel.CONTENT_URI;
            }
        }

        public static final class Conversations implements BaseColumns, TextBasedSmsCbColumns {
            public static final String ADDRESS_ID = "address_id";
            public static final Uri CONTENT_URI;
            public static final String DEFAULT_SORT_ORDER = "date DESC";
            public static final String MESSAGE_COUNT = "msg_count";
            public static final String SNIPPET = "snippet";

            static {
                CONTENT_URI = android.provider.Telephony.SmsCb.Conversations.CONTENT_URI;
            }
        }

        public static final class Intents {
            public static final SmsCbMessage[] getMessagesFromIntent(Intent intent) {
                return android.provider.Telephony.SmsCb.Intents.getMessagesFromIntent(intent);
            }
        }

        public static final Cursor query(ContentResolver cr, String[] projection) {
            return android.provider.Telephony.SmsCb.query(cr, projection);
        }

        public static final Cursor query(ContentResolver cr, String[] projection, String where, String orderBy) {
            return android.provider.Telephony.SmsCb.query(cr, projection, where, orderBy);
        }

        static {
            CONTENT_URI = android.provider.Telephony.SmsCb.CONTENT_URI;
            ADDRESS_URI = android.provider.Telephony.SmsCb.ADDRESS_URI;
        }

        public static Uri addMessageToUri(ContentResolver resolver, Uri uri, int sim_id, int channel_id, long date, boolean read, String body) {
            return android.provider.Telephony.SmsCb.addMessageToUri(resolver, uri, sim_id, channel_id, date, read, body);
        }
    }

    public static final class ThreadSettings implements BaseColumns {
        public static final Uri CONTENT_URI;
        public static final String MUTE = "mute";
        public static final String MUTE_START = "mute_start";
        public static final String NOTIFICATION_ENABLE = "notification_enable";
        public static final String RINGTONE = "ringtone";
        public static final String SPAM = "spam";
        public static final String THREAD_ID = "thread_id";
        public static final String VIBRATE = "vibrate";
        public static final String WALLPAPER = "_data";

        static {
            CONTENT_URI = Uri.withAppendedPath(android.provider.Telephony.MmsSms.CONTENT_URI, "thread_settings");
        }
    }

    public interface ThreadsColumns extends BaseColumns {
        public static final String LATEST_IMPORTANT_DATE = "li_date";
        public static final String LATEST_IMPORTANT_SNIPPET = "li_snippet";
        public static final String LATEST_IMPORTANT_SNIPPET_CHARSET = "li_snippet_cs";
        public static final String READCOUNT = "readcount";
    }

    public static final class Threads implements ThreadsColumns {
        public static final int CELL_BROADCAST_THREAD = 3;
        private static final String[] ID_PROJECTION;
        public static final int IP_MESSAGE_GUIDE_THREAD = 10;
        public static final String STATUS = "status";
        private static final Uri THREAD_ID_CONTENT_URI;
        public static final int WAPPUSH_THREAD = 2;

        static {
            ID_PROJECTION = new String[]{"_id"};
            THREAD_ID_CONTENT_URI = Uri.parse("content://mms-sms/threadID");
        }

        public static long getOrCreateThreadIdInternal(Context context, String recipient) {
            Set recipients = new HashSet();
            recipients.add(recipient);
            return getOrCreateThreadIdInternal(context, recipients);
        }

        public static long getOrCreateThreadIdInternal(Context context, Set<String> recipients) {
            Builder uriBuilder = THREAD_ID_CONTENT_URI.buildUpon();
            for (String recipient : recipients) {
                String recipient2;
                if (android.provider.Telephony.Mms.isEmailAddress(recipient2)) {
                    recipient2 = android.provider.Telephony.Mms.extractAddrSpec(recipient2);
                }
                uriBuilder.appendQueryParameter("recipient", recipient2);
            }
            Uri uri = uriBuilder.build();
            Cursor cursor = SqliteWrapper.query(context, context.getContentResolver(), uri, ID_PROJECTION, null, null, null);
            if (cursor != null) {
                try {
                    if (cursor.moveToFirst()) {
                        long j = cursor.getLong(0);
                        return j;
                    }
                    Log.e(EncapsulatedTelephony.TAG, "getOrCreateThreadId returned no rows!");
                    cursor.close();
                } finally {
                    cursor.close();
                }
            }
            Log.e(EncapsulatedTelephony.TAG, "getOrCreateThreadId failed with uri " + uri.toString());
            throw new IllegalArgumentException("Unable to find or allocate a thread ID.");
        }
    }

    public static final class WapPush implements BaseColumns {
        public static final String ACTION = "action";
        public static final String ADDR = "address";
        public static final Uri CONTENT_URI;
        public static final Uri CONTENT_URI_SI;
        public static final Uri CONTENT_URI_SL;
        public static final Uri CONTENT_URI_THREAD;
        public static final String CREATE = "created";
        public static final String DATE = "date";
        public static final String DEFAULT_SORT_ORDER = "date ASC";
        public static final String ERROR = "error";
        public static final String EXPIRATION = "expiration";
        public static final String LOCKED = "locked";
        public static final String READ = "read";
        public static final String SEEN = "seen";
        public static final String SERVICE_ADDR = "service_center";
        public static final String SIID = "siid";
        public static final String SIM_ID = "sim_id";
        public static final int STATUS_LOCKED = 1;
        public static final int STATUS_READ = 1;
        public static final int STATUS_SEEN = 1;
        public static final int STATUS_UNLOCKED = 0;
        public static final int STATUS_UNREAD = 0;
        public static final int STATUS_UNSEEN = 0;
        public static final String TEXT = "text";
        public static final String THREAD_ID = "thread_id";
        public static final String TYPE = "type";
        public static final int TYPE_SI = 0;
        public static final int TYPE_SL = 1;
        public static final String URL = "url";

        static {
            CONTENT_URI = android.provider.Telephony.WapPush.CONTENT_URI;
            CONTENT_URI_SI = android.provider.Telephony.WapPush.CONTENT_URI_SI;
            CONTENT_URI_SL = android.provider.Telephony.WapPush.CONTENT_URI_SL;
            CONTENT_URI_THREAD = android.provider.Telephony.WapPush.CONTENT_URI_THREAD;
        }
    }
}
