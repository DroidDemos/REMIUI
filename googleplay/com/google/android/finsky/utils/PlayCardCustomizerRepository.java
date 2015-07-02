package com.google.android.finsky.utils;

import com.google.android.play.layout.PlayCardViewBase;

public class PlayCardCustomizerRepository<T extends PlayCardViewBase> {
    private static PlayCardCustomizerRepository<PlayCardViewBase> sInstance;
    private PlayCardCustomizer<? extends T>[] mCustomizers;
    private final PlayCardCustomizer<? extends T> mDefaultCustomizer;

    static {
        sInstance = new PlayCardCustomizerRepository();
    }

    public static PlayCardCustomizerRepository<PlayCardViewBase> getInstance() {
        return sInstance;
    }

    private PlayCardCustomizerRepository() {
        this.mCustomizers = new PlayCardCustomizer[16];
        this.mDefaultCustomizer = new PlayCardCustomizer();
    }

    public void registerCardCustomizer(int cardType, PlayCardCustomizer<? extends T> customizer) {
        if (customizer == null) {
            throw new IllegalArgumentException("Can't pass a null card customizer");
        }
        this.mCustomizers[cardType] = customizer;
    }

    public PlayCardCustomizer<? extends T> getCardCustomizer(PlayCardViewBase card) {
        PlayCardCustomizer<? extends T> result = this.mCustomizers[card.getCardType()];
        if (result == null) {
            return this.mDefaultCustomizer;
        }
        return result;
    }
}
