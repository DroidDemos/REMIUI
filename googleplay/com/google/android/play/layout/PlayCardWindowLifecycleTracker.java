package com.google.android.play.layout;

import java.util.ArrayList;
import java.util.List;

public class PlayCardWindowLifecycleTracker {
    private static PlayCardWindowLifecycleTracker INSTANCE;
    private List<CardLifecycleListener> mListenerList;

    public interface CardLifecycleListener {
        void onCardAttachedToWindow(PlayCardViewBase playCardViewBase);

        void onCardDetachedFromWindow(PlayCardViewBase playCardViewBase);
    }

    static {
        INSTANCE = new PlayCardWindowLifecycleTracker();
    }

    private PlayCardWindowLifecycleTracker() {
        this.mListenerList = new ArrayList();
    }

    public static PlayCardWindowLifecycleTracker getInstance() {
        return INSTANCE;
    }

    public synchronized void registerListener(CardLifecycleListener listener) {
        this.mListenerList.add(listener);
    }

    synchronized void cardAttachedToWindow(PlayCardViewBase card) {
        for (int i = this.mListenerList.size() - 1; i >= 0; i--) {
            ((CardLifecycleListener) this.mListenerList.get(i)).onCardAttachedToWindow(card);
        }
    }

    synchronized void cardDetachedFromWindow(PlayCardViewBase card) {
        for (int i = this.mListenerList.size() - 1; i >= 0; i--) {
            ((CardLifecycleListener) this.mListenerList.get(i)).onCardDetachedFromWindow(card);
        }
    }
}
