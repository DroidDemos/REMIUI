package com.google.android.finsky.previews;

import android.app.ActivityManager;
import android.media.AudioManager;
import android.media.AudioManager.OnAudioFocusChangeListener;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.media.MediaPlayer.OnPreparedListener;
import android.net.Uri;
import android.text.TextUtils;
import com.android.volley.RequestQueue;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.google.android.finsky.FinskyApp;
import com.google.android.finsky.api.SkyjamJsonObjectRequest;
import com.google.android.finsky.api.model.Document;
import com.google.android.finsky.protos.DocDetails.SongDetails;
import com.google.android.finsky.utils.FinskyLog;
import com.google.android.finsky.utils.Lists;
import com.google.android.finsky.utils.Utils;
import com.google.android.wallet.instrumentmanager.R;
import java.io.IOException;
import java.net.URI;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import org.apache.http.NameValuePair;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

public class PreviewPlayer {
    private final OnAudioFocusChangeListener mAudioFocusChangeListener;
    private int mAudioFocusState;
    private AudioManager mAudioManager;
    private final OnCompletionListener mCompletionListener;
    private SongDetails mCurrentTrack;
    private JsonObjectRequest mCurrentUnrollRequest;
    private final Listener<JSONObject> mJsonListener;
    private final StatusListener mListenerProxy;
    private final MediaPlayerWrapper mPlayer;
    private final OnPreparedListener mPreparedListener;
    private final Queue<SongDetails> mQueue;
    private RequestQueue mRequestQueue;
    private final List<StatusListener> mStatusListeners;
    private final ErrorListener mUnrollErrorListener;

    public PreviewPlayer() {
        this.mListenerProxy = new StatusListener() {
            public void playing() {
                for (StatusListener listener : PreviewPlayer.this.mStatusListeners) {
                    listener.playing();
                }
            }

            public void paused() {
                for (StatusListener listener : PreviewPlayer.this.mStatusListeners) {
                    listener.paused();
                }
            }

            public void completed() {
                for (StatusListener listener : PreviewPlayer.this.mStatusListeners) {
                    listener.completed();
                }
            }

            public void preparing() {
                for (StatusListener listener : PreviewPlayer.this.mStatusListeners) {
                    listener.preparing();
                }
            }

            public void prepared() {
                for (StatusListener listener : PreviewPlayer.this.mStatusListeners) {
                    listener.prepared();
                }
            }

            public void unrolling() {
                for (StatusListener listener : PreviewPlayer.this.mStatusListeners) {
                    listener.unrolling();
                }
            }

            public void error() {
                for (StatusListener listener : PreviewPlayer.this.mStatusListeners) {
                    listener.error();
                }
            }

            public void queueChanged(int newSize) {
                for (StatusListener listener : PreviewPlayer.this.mStatusListeners) {
                    listener.queueChanged(newSize);
                }
            }

            public void reset() {
                for (StatusListener listener : PreviewPlayer.this.mStatusListeners) {
                    listener.reset();
                }
            }
        };
        this.mPlayer = new MediaPlayerWrapper(this.mListenerProxy);
        this.mQueue = new LinkedList();
        this.mStatusListeners = Lists.newArrayList();
        this.mCurrentTrack = null;
        this.mRequestQueue = null;
        this.mCurrentUnrollRequest = null;
        this.mAudioManager = null;
        this.mAudioFocusState = -1;
        this.mUnrollErrorListener = new ErrorListener() {
            public void onErrorResponse(VolleyError error) {
                FinskyLog.e("Unable to load JSON: %s", error.getCause());
                PreviewPlayer.this.mCurrentUnrollRequest = null;
                PreviewPlayer.this.notifyError();
            }
        };
        this.mJsonListener = new Listener<JSONObject>() {
            public void onResponse(JSONObject response) {
                try {
                    PreviewPlayer.this.mCurrentUnrollRequest = null;
                    try {
                        PreviewPlayer.this.mPlayer.setDataSource(PreviewPlayer.this.setModeToStreaming(response.getString("url")));
                        PreviewPlayer.this.mPlayer.prepareAsync();
                        PreviewPlayer.this.mListenerProxy.preparing();
                    } catch (IllegalStateException e) {
                        FinskyLog.e("Illegal state while preparing audio. Is Monkey=%b.", Boolean.valueOf(ActivityManager.isUserAMonkey()));
                    }
                } catch (IOException e2) {
                    FinskyLog.e("IOException: %s", e2.getMessage());
                    PreviewPlayer.this.notifyError();
                } catch (JSONException e3) {
                    FinskyLog.e("Unable to decode JSON response", new Object[0]);
                    PreviewPlayer.this.notifyError();
                }
            }
        };
        this.mAudioFocusChangeListener = new OnAudioFocusChangeListener() {
            public void onAudioFocusChange(int focusChange) {
                PreviewPlayer.this.mAudioFocusState = focusChange;
            }
        };
        this.mPreparedListener = new OnPreparedListener() {
            public void onPrepared(MediaPlayer mp) {
                PreviewPlayer.this.mPlayer.start();
            }
        };
        this.mCompletionListener = new OnCompletionListener() {
            public void onCompletion(MediaPlayer mp) {
                PreviewPlayer.this.mListenerProxy.completed();
                PreviewPlayer.this.mStatusListeners.remove(PreviewPlayer.this.mCurrentTrack);
                if (PreviewPlayer.this.playNextTrack()) {
                    PreviewPlayer.this.mPlayer.setBetweenTrackState();
                }
            }
        };
    }

    public SongDetails getCurrentTrack() {
        return this.mCurrentTrack;
    }

    public void play(Collection<Document> tracks) {
        Utils.ensureOnMainThread();
        this.mQueue.clear();
        for (Document child : tracks) {
            SongDetails details = child.getSongDetails();
            if (!(details == null || !details.hasPreviewUrl || TextUtils.isEmpty(details.previewUrl))) {
                this.mQueue.add(details);
            }
        }
        notifyQueueChanged();
        playNextTrack();
    }

    public void play(SongDetails track) {
        Utils.ensureOnMainThread();
        this.mQueue.clear();
        this.mQueue.add(track);
        notifyQueueChanged();
        playNextTrack();
    }

    public void togglePlayback(SongDetails song) {
        if (this.mCurrentUnrollRequest != null) {
            this.mCurrentUnrollRequest.cancel();
        }
        int state = this.mPlayer.getCurrentState();
        if (!isCurrentTrack(song)) {
            play(song);
        } else if (state == 4) {
            this.mPlayer.pause();
        } else if (state == 5) {
            this.mPlayer.start();
        } else if (state == 2) {
            this.mListenerProxy.completed();
            reset();
        }
    }

    public void skip() {
        playNextTrack();
    }

    public void clear() {
        Utils.ensureOnMainThread();
        this.mQueue.clear();
    }

    public void stop() {
        switch (this.mPlayer.getCurrentState()) {
            case R.styleable.WalletImFormEditText_requiredErrorString /*3*/:
            case R.styleable.WalletImFormEditText_required /*4*/:
            case R.styleable.WalletImFormEditText_validateWhenNotVisible /*5*/:
            case R.styleable.WalletFragmentStyle_maskedWalletDetailsBackground /*6*/:
            case R.styleable.WalletFragmentStyle_maskedWalletDetailsButtonTextAppearance /*7*/:
                this.mPlayer.stop();
                return;
            default:
                return;
        }
    }

    public void reset() {
        this.mListenerProxy.reset();
        this.mPlayer.reset();
        this.mCurrentTrack = null;
    }

    public void addStatusListener(StatusListener statusListener) {
        this.mStatusListeners.add(statusListener);
    }

    public void removeStatusListener(StatusListener statusListener) {
        this.mStatusListeners.remove(statusListener);
    }

    public void initialize() {
        if (this.mRequestQueue == null) {
            this.mRequestQueue = FinskyApp.get().getRequestQueue();
        }
        if (this.mAudioManager == null) {
            this.mAudioManager = (AudioManager) FinskyApp.get().getSystemService("audio");
        }
        this.mPlayer.setOnPreparedListener(this.mPreparedListener);
        this.mPlayer.setOnCompletionListener(this.mCompletionListener);
    }

    public int getCurrentQueueSize() {
        return this.mQueue.size();
    }

    private void updateAudioFocus() {
        boolean queueIsEmpty = this.mQueue.isEmpty();
        if (!queueIsEmpty && this.mAudioFocusState != 1) {
            this.mAudioManager.requestAudioFocus(this.mAudioFocusChangeListener, 3, 1);
            this.mAudioFocusState = 1;
        } else if (queueIsEmpty && this.mAudioFocusState != -1) {
            this.mAudioManager.abandonAudioFocus(this.mAudioFocusChangeListener);
            this.mAudioFocusState = -1;
        }
    }

    private void notifyQueueChanged() {
        this.mListenerProxy.queueChanged(this.mQueue.size());
    }

    private boolean playNextTrack() {
        Utils.ensureOnMainThread();
        updateAudioFocus();
        this.mPlayer.resetWhileStayingAwake();
        if (this.mQueue.isEmpty()) {
            this.mCurrentTrack = null;
            return false;
        }
        this.mListenerProxy.completed();
        if (this.mCurrentUnrollRequest != null) {
            this.mCurrentUnrollRequest.cancel();
        }
        this.mCurrentTrack = (SongDetails) this.mQueue.remove();
        notifyQueueChanged();
        this.mCurrentUnrollRequest = new SkyjamJsonObjectRequest(1, setModeToStreaming(this.mCurrentTrack.previewUrl), null, this.mJsonListener, this.mUnrollErrorListener);
        FinskyApp.drain(this.mRequestQueue);
        this.mRequestQueue.add(this.mCurrentUnrollRequest);
        this.mListenerProxy.unrolling();
        return true;
    }

    private String setModeToStreaming(String url) {
        List<NameValuePair> params = Lists.newArrayList(URLEncodedUtils.parse(URI.create(url), "UTF-8"));
        NameValuePair pair = null;
        for (int i = 0; i < params.size(); i++) {
            pair = (NameValuePair) params.get(i);
            if ("mode".equals(pair.getName())) {
                break;
            }
            pair = null;
        }
        if (pair != null) {
            params.remove(pair);
        }
        params.add(new BasicNameValuePair("mode", "streaming"));
        return Uri.parse(url).buildUpon().encodedQuery(URLEncodedUtils.format(params, "UTF-8")).build().toString();
    }

    private void notifyError() {
        this.mListenerProxy.error();
        this.mCurrentTrack = null;
        playNextTrack();
    }

    private boolean isCurrentTrack(SongDetails song) {
        if (this.mCurrentTrack == null && song == null) {
            return true;
        }
        if (this.mCurrentTrack == null || song == null) {
            return false;
        }
        return song.previewUrl.equals(this.mCurrentTrack.previewUrl);
    }

    public void notifyListener(StatusListener statusListener) {
        switch (this.mPlayer.getCurrentState()) {
            case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                statusListener.preparing();
                return;
            case R.styleable.WalletImFormEditText_requiredErrorString /*3*/:
                statusListener.prepared();
                return;
            case R.styleable.WalletImFormEditText_required /*4*/:
                statusListener.playing();
                return;
            case R.styleable.WalletImFormEditText_validateWhenNotVisible /*5*/:
                statusListener.paused();
                return;
            case R.styleable.WalletFragmentStyle_maskedWalletDetailsButtonTextAppearance /*7*/:
                statusListener.completed();
                return;
            case R.styleable.WalletFragmentStyle_maskedWalletDetailsButtonBackground /*8*/:
                statusListener.error();
                return;
            default:
                return;
        }
    }
}
