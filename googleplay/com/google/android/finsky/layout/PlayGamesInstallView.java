package com.google.android.finsky.layout;

import android.content.Context;
import android.os.Bundle;
import android.text.Html;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.android.vending.R;
import com.google.android.finsky.activities.SimpleAlertDialog.ConfigurableView;

public class PlayGamesInstallView extends LinearLayout implements ConfigurableView {
    public PlayGamesInstallView(Context context) {
        super(context);
    }

    public PlayGamesInstallView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public PlayGamesInstallView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public void configureView(Bundle arguments) {
        ((TextView) findViewById(R.id.bullet_list)).setText(Html.fromHtml(getContext().getString(R.string.play_games_install_suggestion_bullets)));
    }

    public Bundle getResult() {
        return null;
    }
}
