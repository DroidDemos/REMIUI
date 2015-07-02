package com.xiaomi.mipush.sdk;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import java.util.concurrent.ConcurrentLinkedQueue;
import org.apache.thrift.transport.TTransportException;

public class MessageHandleService extends IntentService {
    private static ConcurrentLinkedQueue<MessageHandleJob> jobQueue;

    public static class MessageHandleJob {
        private Intent intent;
        private PushMessageReceiver receiver;

        public MessageHandleJob(Intent intent, PushMessageReceiver receiver) {
            this.receiver = receiver;
            this.intent = intent;
        }

        public PushMessageReceiver getReceiver() {
            return this.receiver;
        }

        public Intent getIntent() {
            return this.intent;
        }
    }

    public interface ReceiverCallback {
        void onCommandResult(Context context, MiPushCommandMessage miPushCommandMessage);

        void onReceiveMessage(Context context, MiPushMessage miPushMessage);
    }

    static {
        jobQueue = new ConcurrentLinkedQueue();
    }

    public static void addJob(MessageHandleJob job) {
        if (job != null) {
            jobQueue.add(job);
        }
    }

    public MessageHandleService() {
        super("MessageHandleThread");
    }

    protected void onHandleIntent(Intent jobIntent) {
        if (jobIntent != null) {
            Log.i("lh", "jobIntent is not null");
            MessageHandleJob job = (MessageHandleJob) jobQueue.poll();
            if (job == null) {
                Log.i("lh", "empty job from jobQueue");
                return;
            }
            PushMessageReceiver receiver = job.getReceiver();
            Intent intent = job.getIntent();
            switch (intent.getIntExtra(PushMessageHelper.MESSAGE_TYPE, 1)) {
                case TTransportException.NOT_OPEN /*1*/:
                    PushMessageInterface message = PushMessageProcessor.getInstance(this).proccessIntent(intent);
                    if (message == null) {
                        return;
                    }
                    if (message instanceof MiPushMessage) {
                        receiver.onReceiveMessage(this, (MiPushMessage) message);
                        return;
                    } else if (message instanceof MiPushCommandMessage) {
                        receiver.onCommandResult(this, (MiPushCommandMessage) message);
                        return;
                    } else {
                        return;
                    }
                case TTransportException.ALREADY_OPEN /*2*/:
                    receiver.onReceiveMessage(this, (MiPushMessage) intent.getSerializableExtra(PushMessageHelper.KEY_MESSAGE));
                    return;
                case TTransportException.TIMED_OUT /*3*/:
                    receiver.onCommandResult(this, (MiPushCommandMessage) intent.getSerializableExtra(PushMessageHelper.KEY_COMMAND));
                    return;
                case TTransportException.END_OF_FILE /*4*/:
                    return;
                default:
                    return;
            }
        }
    }
}
