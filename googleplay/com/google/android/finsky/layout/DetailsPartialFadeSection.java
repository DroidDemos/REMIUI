package com.google.android.finsky.layout;

import android.view.View;
import java.util.List;

public interface DetailsPartialFadeSection {
    void addParticipatingChildViewIds(List<Integer> list);

    void addParticipatingChildViews(List<View> list);
}
