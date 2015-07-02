package com.google.android.gms.games.event;

import android.net.Uri;
import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.games.Player;
import com.google.android.gms.games.PlayerEntity;
import com.google.android.gms.internal.kl;

public final class EventEntity implements SafeParcelable, Event {
    public static final EventEntityCreator CREATOR;
    private final Uri aiK;
    private final String aiV;
    private final PlayerEntity ajJ;
    private final String akj;
    private final long akk;
    private final String akl;
    private final boolean akm;
    private final String mDescription;
    private final String mName;
    private final int mVersionCode;

    static {
        CREATOR = new EventEntityCreator();
    }

    EventEntity(int versionCode, String eventId, String name, String description, Uri iconImageUri, String iconImageUrl, Player player, long value, String formattedValue, boolean isVisible) {
        this.mVersionCode = versionCode;
        this.akj = eventId;
        this.mName = name;
        this.mDescription = description;
        this.aiK = iconImageUri;
        this.aiV = iconImageUrl;
        this.ajJ = new PlayerEntity(player);
        this.akk = value;
        this.akl = formattedValue;
        this.akm = isVisible;
    }

    public EventEntity(Event event) {
        this.mVersionCode = 1;
        this.akj = event.getEventId();
        this.mName = event.getName();
        this.mDescription = event.getDescription();
        this.aiK = event.getIconImageUri();
        this.aiV = event.getIconImageUrl();
        this.ajJ = (PlayerEntity) event.getPlayer().freeze();
        this.akk = event.getValue();
        this.akl = event.getFormattedValue();
        this.akm = event.isVisible();
    }

    static int a(Event event) {
        return kl.hashCode(event.getEventId(), event.getName(), event.getDescription(), event.getIconImageUri(), event.getIconImageUrl(), event.getPlayer(), Long.valueOf(event.getValue()), event.getFormattedValue(), Boolean.valueOf(event.isVisible()));
    }

    static boolean a(Event event, Object obj) {
        if (!(obj instanceof Event)) {
            return false;
        }
        if (event == obj) {
            return true;
        }
        Event event2 = (Event) obj;
        return kl.equal(event2.getEventId(), event.getEventId()) && kl.equal(event2.getName(), event.getName()) && kl.equal(event2.getDescription(), event.getDescription()) && kl.equal(event2.getIconImageUri(), event.getIconImageUri()) && kl.equal(event2.getIconImageUrl(), event.getIconImageUrl()) && kl.equal(event2.getPlayer(), event.getPlayer()) && kl.equal(Long.valueOf(event2.getValue()), Long.valueOf(event.getValue())) && kl.equal(event2.getFormattedValue(), event.getFormattedValue()) && kl.equal(Boolean.valueOf(event2.isVisible()), Boolean.valueOf(event.isVisible()));
    }

    static String b(Event event) {
        return kl.j(event).a("Id", event.getEventId()).a("Name", event.getName()).a("Description", event.getDescription()).a("IconImageUri", event.getIconImageUri()).a("IconImageUrl", event.getIconImageUrl()).a("Player", event.getPlayer()).a("Value", Long.valueOf(event.getValue())).a("FormattedValue", event.getFormattedValue()).a("isVisible", Boolean.valueOf(event.isVisible())).toString();
    }

    public int describeContents() {
        return 0;
    }

    public boolean equals(Object obj) {
        return a(this, obj);
    }

    public Event freeze() {
        return this;
    }

    public String getDescription() {
        return this.mDescription;
    }

    public String getEventId() {
        return this.akj;
    }

    public String getFormattedValue() {
        return this.akl;
    }

    public Uri getIconImageUri() {
        return this.aiK;
    }

    public String getIconImageUrl() {
        return this.aiV;
    }

    public String getName() {
        return this.mName;
    }

    public Player getPlayer() {
        return this.ajJ;
    }

    public long getValue() {
        return this.akk;
    }

    public int getVersionCode() {
        return this.mVersionCode;
    }

    public int hashCode() {
        return a(this);
    }

    public boolean isVisible() {
        return this.akm;
    }

    public String toString() {
        return b(this);
    }

    public void writeToParcel(Parcel out, int flags) {
        EventEntityCreator.a(this, out, flags);
    }
}
