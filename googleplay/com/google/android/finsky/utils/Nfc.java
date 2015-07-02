package com.google.android.finsky.utils;

import android.app.Activity;
import android.nfc.NdefMessage;
import android.nfc.NdefRecord;
import android.nfc.NfcAdapter;
import android.nfc.NfcAdapter.CreateNdefMessageCallback;
import android.nfc.NfcEvent;
import android.os.Build.VERSION;
import com.google.android.finsky.activities.DetailsDataBasedFragment;
import com.google.android.finsky.api.model.Document;
import java.io.UnsupportedEncodingException;

public class Nfc {

    public interface NfcHandler {
        void onDataChanged();

        void onPause();

        void onResume();
    }

    private static abstract class BaseNfcHandler implements NfcHandler {
        protected final DetailsDataBasedFragment mFragment;
        protected final NfcAdapter mNfcAdapter;

        private BaseNfcHandler(DetailsDataBasedFragment fragment) {
            this.mFragment = fragment;
            this.mNfcAdapter = NfcAdapter.getDefaultAdapter(fragment.getActivity());
        }

        protected final NdefMessage createMessage() {
            Document doc = this.mFragment.getDocument();
            if (doc == null) {
                return null;
            }
            byte[] pkgBytes;
            String shareUrl = doc.getShareUrl();
            try {
                pkgBytes = shareUrl.getBytes("UTF-8");
            } catch (UnsupportedEncodingException e) {
                pkgBytes = shareUrl.getBytes();
            }
            byte[] payloadData = new byte[(pkgBytes.length + 1)];
            System.arraycopy(pkgBytes, 0, payloadData, 1, pkgBytes.length);
            NdefRecord record = new NdefRecord((short) 1, new byte[]{(byte) 85}, new byte[0], payloadData);
            return new NdefMessage(new NdefRecord[]{record});
        }
    }

    private static class GingerbreadMr1NfcHandler extends BaseNfcHandler {
        private boolean mEnabled;

        private GingerbreadMr1NfcHandler(DetailsDataBasedFragment fragment) {
            super(fragment);
            this.mEnabled = false;
        }

        public void onPause() {
            if (this.mEnabled) {
                this.mNfcAdapter.disableForegroundNdefPush(this.mFragment.getActivity());
                this.mEnabled = false;
            }
        }

        public void onResume() {
            setPushMessage();
        }

        public void onDataChanged() {
            setPushMessage();
        }

        private void setPushMessage() {
            if (!this.mEnabled && this.mFragment.isResumed()) {
                NdefMessage message = createMessage();
                if (message != null) {
                    this.mNfcAdapter.enableForegroundNdefPush(this.mFragment.getActivity(), message);
                    this.mEnabled = true;
                }
            }
        }
    }

    private static class IcsNfcHandler extends BaseNfcHandler implements CreateNdefMessageCallback {
        private IcsNfcHandler(DetailsDataBasedFragment fragment) {
            super(fragment);
        }

        public NdefMessage createNdefMessage(NfcEvent event) {
            return createMessage();
        }

        public void onPause() {
            setCallback(null);
        }

        public void onResume() {
            setCallback(this);
        }

        public void onDataChanged() {
            setCallback(this);
        }

        private void setCallback(CreateNdefMessageCallback callback) {
            if (this.mFragment.canChangeFragmentManagerState() && this.mNfcAdapter != null) {
                this.mNfcAdapter.setNdefPushMessageCallback(callback, this.mFragment.getActivity(), new Activity[0]);
            }
        }
    }

    private static class NoopNfcHandler implements NfcHandler {
        private NoopNfcHandler() {
        }

        public void onPause() {
        }

        public void onResume() {
        }

        public void onDataChanged() {
        }
    }

    public static NfcHandler getHandler(DetailsDataBasedFragment fragment) {
        boolean hasNfc = fragment.getActivity().getPackageManager().hasSystemFeature("android.hardware.nfc");
        if (hasNfc && VERSION.SDK_INT >= 14) {
            return new IcsNfcHandler(fragment);
        }
        if (!hasNfc || VERSION.SDK_INT < 10) {
            return new NoopNfcHandler();
        }
        return new GingerbreadMr1NfcHandler(fragment);
    }
}
