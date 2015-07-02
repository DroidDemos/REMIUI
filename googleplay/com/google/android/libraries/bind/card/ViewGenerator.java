package com.google.android.libraries.bind.card;

import android.view.View;
import android.view.ViewGroup;
import com.google.android.libraries.bind.view.ViewHeap;
import java.util.List;

public interface ViewGenerator {
    List<Object> getCardIds();

    View makeView(View view, ViewGroup viewGroup, ViewHeap viewHeap);
}
