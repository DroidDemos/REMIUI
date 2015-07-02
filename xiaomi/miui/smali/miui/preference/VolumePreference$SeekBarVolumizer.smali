.class public Lmiui/preference/VolumePreference$SeekBarVolumizer;
.super Ljava/lang/Object;
.source "SourceFile"

# interfaces
.implements Landroid/os/Handler$Callback;
.implements Landroid/widget/SeekBar$OnSeekBarChangeListener;


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lmiui/preference/VolumePreference;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x1
    name = "SeekBarVolumizer"
.end annotation


# static fields
.field private static final rc:I = 0x0

.field private static final rd:I = 0x1

.field private static final re:I = 0x2

.field private static final rf:I = 0x3e8


# instance fields
.field private mContext:Landroid/content/Context;

.field private mHandler:Landroid/os/Handler;

.field private qV:Landroid/media/AudioManager;

.field private qW:I

.field private qX:I

.field private qY:Landroid/media/Ringtone;

.field private qZ:I

.field private ra:Landroid/widget/SeekBar;

.field private rb:I

.field private rg:Landroid/database/ContentObserver;

.field final synthetic rh:Lmiui/preference/VolumePreference;


# direct methods
.method public constructor <init>(Lmiui/preference/VolumePreference;Landroid/content/Context;Landroid/widget/SeekBar;I)V
    .locals 6

    .prologue
    .line 279
    const/4 v5, 0x0

    move-object v0, p0

    move-object v1, p1

    move-object v2, p2

    move-object v3, p3

    move v4, p4

    invoke-direct/range {v0 .. v5}, Lmiui/preference/VolumePreference$SeekBarVolumizer;-><init>(Lmiui/preference/VolumePreference;Landroid/content/Context;Landroid/widget/SeekBar;ILandroid/net/Uri;)V

    .line 280
    return-void
.end method

.method public constructor <init>(Lmiui/preference/VolumePreference;Landroid/content/Context;Landroid/widget/SeekBar;ILandroid/net/Uri;)V
    .locals 2

    .prologue
    const/4 v0, -0x1

    .line 282
    iput-object p1, p0, Lmiui/preference/VolumePreference$SeekBarVolumizer;->rh:Lmiui/preference/VolumePreference;

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    .line 258
    iput v0, p0, Lmiui/preference/VolumePreference$SeekBarVolumizer;->qZ:I

    .line 260
    iput v0, p0, Lmiui/preference/VolumePreference$SeekBarVolumizer;->rb:I

    .line 267
    new-instance v0, Lmiui/preference/b;

    iget-object v1, p0, Lmiui/preference/VolumePreference$SeekBarVolumizer;->mHandler:Landroid/os/Handler;

    invoke-direct {v0, p0, v1}, Lmiui/preference/b;-><init>(Lmiui/preference/VolumePreference$SeekBarVolumizer;Landroid/os/Handler;)V

    iput-object v0, p0, Lmiui/preference/VolumePreference$SeekBarVolumizer;->rg:Landroid/database/ContentObserver;

    .line 283
    iput-object p2, p0, Lmiui/preference/VolumePreference$SeekBarVolumizer;->mContext:Landroid/content/Context;

    .line 284
    const-string v0, "audio"

    invoke-virtual {p2, v0}, Landroid/content/Context;->getSystemService(Ljava/lang/String;)Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Landroid/media/AudioManager;

    iput-object v0, p0, Lmiui/preference/VolumePreference$SeekBarVolumizer;->qV:Landroid/media/AudioManager;

    .line 285
    iput p4, p0, Lmiui/preference/VolumePreference$SeekBarVolumizer;->qW:I

    .line 286
    iput-object p3, p0, Lmiui/preference/VolumePreference$SeekBarVolumizer;->ra:Landroid/widget/SeekBar;

    .line 288
    new-instance v0, Landroid/os/HandlerThread;

    const-string v1, "VolumePreference.CallbackHandler"

    invoke-direct {v0, v1}, Landroid/os/HandlerThread;-><init>(Ljava/lang/String;)V

    .line 289
    invoke-virtual {v0}, Landroid/os/HandlerThread;->start()V

    .line 290
    new-instance v1, Landroid/os/Handler;

    invoke-virtual {v0}, Landroid/os/HandlerThread;->getLooper()Landroid/os/Looper;

    move-result-object v0

    invoke-direct {v1, v0, p0}, Landroid/os/Handler;-><init>(Landroid/os/Looper;Landroid/os/Handler$Callback;)V

    iput-object v1, p0, Lmiui/preference/VolumePreference$SeekBarVolumizer;->mHandler:Landroid/os/Handler;

    .line 292
    invoke-direct {p0, p3, p5}, Lmiui/preference/VolumePreference$SeekBarVolumizer;->a(Landroid/widget/SeekBar;Landroid/net/Uri;)V

    .line 293
    return-void
.end method

.method private a(Landroid/widget/SeekBar;Landroid/net/Uri;)V
    .locals 4

    .prologue
    .line 296
    iget-object v0, p0, Lmiui/preference/VolumePreference$SeekBarVolumizer;->qV:Landroid/media/AudioManager;

    iget v1, p0, Lmiui/preference/VolumePreference$SeekBarVolumizer;->qW:I

    invoke-virtual {v0, v1}, Landroid/media/AudioManager;->getStreamMaxVolume(I)I

    move-result v0

    invoke-virtual {p1, v0}, Landroid/widget/SeekBar;->setMax(I)V

    .line 297
    iget-object v0, p0, Lmiui/preference/VolumePreference$SeekBarVolumizer;->qV:Landroid/media/AudioManager;

    iget v1, p0, Lmiui/preference/VolumePreference$SeekBarVolumizer;->qW:I

    invoke-virtual {v0, v1}, Landroid/media/AudioManager;->getStreamVolume(I)I

    move-result v0

    iput v0, p0, Lmiui/preference/VolumePreference$SeekBarVolumizer;->qX:I

    .line 298
    iget v0, p0, Lmiui/preference/VolumePreference$SeekBarVolumizer;->qX:I

    invoke-virtual {p1, v0}, Landroid/widget/SeekBar;->setProgress(I)V

    .line 299
    invoke-virtual {p1, p0}, Landroid/widget/SeekBar;->setOnSeekBarChangeListener(Landroid/widget/SeekBar$OnSeekBarChangeListener;)V

    .line 301
    iget-object v0, p0, Lmiui/preference/VolumePreference$SeekBarVolumizer;->mContext:Landroid/content/Context;

    invoke-virtual {v0}, Landroid/content/Context;->getContentResolver()Landroid/content/ContentResolver;

    move-result-object v0

    sget-object v1, Landroid/provider/Settings$System;->VOLUME_SETTINGS:[Ljava/lang/String;

    iget v2, p0, Lmiui/preference/VolumePreference$SeekBarVolumizer;->qW:I

    aget-object v1, v1, v2

    invoke-static {v1}, Landroid/provider/Settings$System;->getUriFor(Ljava/lang/String;)Landroid/net/Uri;

    move-result-object v1

    const/4 v2, 0x0

    iget-object v3, p0, Lmiui/preference/VolumePreference$SeekBarVolumizer;->rg:Landroid/database/ContentObserver;

    invoke-virtual {v0, v1, v2, v3}, Landroid/content/ContentResolver;->registerContentObserver(Landroid/net/Uri;ZLandroid/database/ContentObserver;)V

    .line 305
    if-nez p2, :cond_0

    .line 306
    iget v0, p0, Lmiui/preference/VolumePreference$SeekBarVolumizer;->qW:I

    const/4 v1, 0x2

    if-ne v0, v1, :cond_2

    .line 307
    sget-object p2, Landroid/provider/Settings$System;->DEFAULT_RINGTONE_URI:Landroid/net/Uri;

    .line 315
    :cond_0
    :goto_0
    iget-object v0, p0, Lmiui/preference/VolumePreference$SeekBarVolumizer;->mContext:Landroid/content/Context;

    invoke-static {v0, p2}, Landroid/media/RingtoneManager;->getRingtone(Landroid/content/Context;Landroid/net/Uri;)Landroid/media/Ringtone;

    move-result-object v0

    iput-object v0, p0, Lmiui/preference/VolumePreference$SeekBarVolumizer;->qY:Landroid/media/Ringtone;

    .line 317
    iget-object v0, p0, Lmiui/preference/VolumePreference$SeekBarVolumizer;->qY:Landroid/media/Ringtone;

    if-eqz v0, :cond_1

    .line 318
    iget-object v0, p0, Lmiui/preference/VolumePreference$SeekBarVolumizer;->qY:Landroid/media/Ringtone;

    iget v1, p0, Lmiui/preference/VolumePreference$SeekBarVolumizer;->qW:I

    invoke-virtual {v0, v1}, Landroid/media/Ringtone;->setStreamType(I)V

    .line 320
    :cond_1
    return-void

    .line 308
    :cond_2
    iget v0, p0, Lmiui/preference/VolumePreference$SeekBarVolumizer;->qW:I

    const/4 v1, 0x5

    if-ne v0, v1, :cond_3

    .line 309
    sget-object p2, Landroid/provider/Settings$System;->DEFAULT_NOTIFICATION_URI:Landroid/net/Uri;

    goto :goto_0

    .line 311
    :cond_3
    sget-object p2, Landroid/provider/Settings$System;->DEFAULT_ALARM_ALERT_URI:Landroid/net/Uri;

    goto :goto_0
.end method

.method static synthetic a(Lmiui/preference/VolumePreference$SeekBarVolumizer;)V
    .locals 0

    .prologue
    .line 248
    invoke-direct {p0}, Lmiui/preference/VolumePreference$SeekBarVolumizer;->bu()V

    return-void
.end method

.method static synthetic b(Lmiui/preference/VolumePreference$SeekBarVolumizer;)Landroid/widget/SeekBar;
    .locals 1

    .prologue
    .line 248
    iget-object v0, p0, Lmiui/preference/VolumePreference$SeekBarVolumizer;->ra:Landroid/widget/SeekBar;

    return-object v0
.end method

.method private bs()V
    .locals 4

    .prologue
    const/4 v1, 0x1

    .line 341
    iget-object v0, p0, Lmiui/preference/VolumePreference$SeekBarVolumizer;->mHandler:Landroid/os/Handler;

    invoke-virtual {v0, v1}, Landroid/os/Handler;->removeMessages(I)V

    .line 342
    iget-object v2, p0, Lmiui/preference/VolumePreference$SeekBarVolumizer;->mHandler:Landroid/os/Handler;

    iget-object v0, p0, Lmiui/preference/VolumePreference$SeekBarVolumizer;->mHandler:Landroid/os/Handler;

    invoke-virtual {v0, v1}, Landroid/os/Handler;->obtainMessage(I)Landroid/os/Message;

    move-result-object v3

    invoke-virtual {p0}, Lmiui/preference/VolumePreference$SeekBarVolumizer;->isSamplePlaying()Z

    move-result v0

    if-eqz v0, :cond_0

    const-wide/16 v0, 0x3e8

    :goto_0
    invoke-virtual {v2, v3, v0, v1}, Landroid/os/Handler;->sendMessageDelayed(Landroid/os/Message;J)Z

    .line 344
    return-void

    .line 342
    :cond_0
    const-wide/16 v0, 0x0

    goto :goto_0
.end method

.method private bt()V
    .locals 1

    .prologue
    .line 347
    invoke-virtual {p0}, Lmiui/preference/VolumePreference$SeekBarVolumizer;->isSamplePlaying()Z

    move-result v0

    if-nez v0, :cond_0

    .line 348
    iget-object v0, p0, Lmiui/preference/VolumePreference$SeekBarVolumizer;->rh:Lmiui/preference/VolumePreference;

    invoke-virtual {v0, p0}, Lmiui/preference/VolumePreference;->onSampleStarting(Lmiui/preference/VolumePreference$SeekBarVolumizer;)V

    .line 349
    iget-object v0, p0, Lmiui/preference/VolumePreference$SeekBarVolumizer;->qY:Landroid/media/Ringtone;

    if-eqz v0, :cond_0

    .line 350
    iget-object v0, p0, Lmiui/preference/VolumePreference$SeekBarVolumizer;->qY:Landroid/media/Ringtone;

    invoke-virtual {v0}, Landroid/media/Ringtone;->play()V

    .line 353
    :cond_0
    return-void
.end method

.method private bu()V
    .locals 3

    .prologue
    const/4 v2, 0x2

    .line 357
    iget-object v0, p0, Lmiui/preference/VolumePreference$SeekBarVolumizer;->mHandler:Landroid/os/Handler;

    const/4 v1, 0x1

    invoke-virtual {v0, v1}, Landroid/os/Handler;->removeMessages(I)V

    .line 358
    iget-object v0, p0, Lmiui/preference/VolumePreference$SeekBarVolumizer;->mHandler:Landroid/os/Handler;

    invoke-virtual {v0, v2}, Landroid/os/Handler;->removeMessages(I)V

    .line 359
    iget-object v0, p0, Lmiui/preference/VolumePreference$SeekBarVolumizer;->mHandler:Landroid/os/Handler;

    iget-object v1, p0, Lmiui/preference/VolumePreference$SeekBarVolumizer;->mHandler:Landroid/os/Handler;

    invoke-virtual {v1, v2}, Landroid/os/Handler;->obtainMessage(I)Landroid/os/Message;

    move-result-object v1

    invoke-virtual {v0, v1}, Landroid/os/Handler;->sendMessage(Landroid/os/Message;)Z

    .line 360
    return-void
.end method

.method private bv()V
    .locals 1

    .prologue
    .line 363
    iget-object v0, p0, Lmiui/preference/VolumePreference$SeekBarVolumizer;->qY:Landroid/media/Ringtone;

    if-eqz v0, :cond_0

    .line 364
    iget-object v0, p0, Lmiui/preference/VolumePreference$SeekBarVolumizer;->qY:Landroid/media/Ringtone;

    invoke-virtual {v0}, Landroid/media/Ringtone;->stop()V

    .line 366
    :cond_0
    return-void
.end method

.method static synthetic c(Lmiui/preference/VolumePreference$SeekBarVolumizer;)Landroid/media/AudioManager;
    .locals 1

    .prologue
    .line 248
    iget-object v0, p0, Lmiui/preference/VolumePreference$SeekBarVolumizer;->qV:Landroid/media/AudioManager;

    return-object v0
.end method

.method static synthetic d(Lmiui/preference/VolumePreference$SeekBarVolumizer;)I
    .locals 1

    .prologue
    .line 248
    iget v0, p0, Lmiui/preference/VolumePreference$SeekBarVolumizer;->qW:I

    return v0
.end method


# virtual methods
.method J(I)V
    .locals 3

    .prologue
    const/4 v2, 0x0

    .line 389
    iput p1, p0, Lmiui/preference/VolumePreference$SeekBarVolumizer;->qZ:I

    .line 390
    iget-object v0, p0, Lmiui/preference/VolumePreference$SeekBarVolumizer;->mHandler:Landroid/os/Handler;

    invoke-virtual {v0, v2}, Landroid/os/Handler;->removeMessages(I)V

    .line 391
    iget-object v0, p0, Lmiui/preference/VolumePreference$SeekBarVolumizer;->mHandler:Landroid/os/Handler;

    iget-object v1, p0, Lmiui/preference/VolumePreference$SeekBarVolumizer;->mHandler:Landroid/os/Handler;

    invoke-virtual {v1, v2}, Landroid/os/Handler;->obtainMessage(I)Landroid/os/Message;

    move-result-object v1

    invoke-virtual {v0, v1}, Landroid/os/Handler;->sendMessage(Landroid/os/Message;)Z

    .line 392
    return-void
.end method

.method public changeVolumeBy(I)V
    .locals 1

    .prologue
    .line 418
    iget-object v0, p0, Lmiui/preference/VolumePreference$SeekBarVolumizer;->ra:Landroid/widget/SeekBar;

    invoke-virtual {v0, p1}, Landroid/widget/SeekBar;->incrementProgressBy(I)V

    .line 419
    iget-object v0, p0, Lmiui/preference/VolumePreference$SeekBarVolumizer;->ra:Landroid/widget/SeekBar;

    invoke-virtual {v0}, Landroid/widget/SeekBar;->getProgress()I

    move-result v0

    invoke-virtual {p0, v0}, Lmiui/preference/VolumePreference$SeekBarVolumizer;->J(I)V

    .line 420
    invoke-direct {p0}, Lmiui/preference/VolumePreference$SeekBarVolumizer;->bs()V

    .line 421
    const/4 v0, -0x1

    iput v0, p0, Lmiui/preference/VolumePreference$SeekBarVolumizer;->rb:I

    .line 422
    return-void
.end method

.method public getSeekBar()Landroid/widget/SeekBar;
    .locals 1

    .prologue
    .line 414
    iget-object v0, p0, Lmiui/preference/VolumePreference$SeekBarVolumizer;->ra:Landroid/widget/SeekBar;

    return-object v0
.end method

.method public handleMessage(Landroid/os/Message;)Z
    .locals 4

    .prologue
    .line 324
    iget v0, p1, Landroid/os/Message;->what:I

    packed-switch v0, :pswitch_data_0

    .line 335
    const-string v0, "VolumePreference"

    new-instance v1, Ljava/lang/StringBuilder;

    invoke-direct {v1}, Ljava/lang/StringBuilder;-><init>()V

    const-string v2, "invalid SeekBarVolumizer message: "

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v1

    iget v2, p1, Landroid/os/Message;->what:I

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    move-result-object v1

    invoke-virtual {v1}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v1

    invoke-static {v0, v1}, Landroid/util/Log;->e(Ljava/lang/String;Ljava/lang/String;)I

    .line 337
    :goto_0
    const/4 v0, 0x1

    return v0

    .line 326
    :pswitch_0
    iget-object v0, p0, Lmiui/preference/VolumePreference$SeekBarVolumizer;->qV:Landroid/media/AudioManager;

    iget v1, p0, Lmiui/preference/VolumePreference$SeekBarVolumizer;->qW:I

    iget v2, p0, Lmiui/preference/VolumePreference$SeekBarVolumizer;->qZ:I

    const/4 v3, 0x0

    invoke-virtual {v0, v1, v2, v3}, Landroid/media/AudioManager;->setStreamVolume(III)V

    goto :goto_0

    .line 329
    :pswitch_1
    invoke-direct {p0}, Lmiui/preference/VolumePreference$SeekBarVolumizer;->bt()V

    goto :goto_0

    .line 332
    :pswitch_2
    invoke-direct {p0}, Lmiui/preference/VolumePreference$SeekBarVolumizer;->bv()V

    goto :goto_0

    .line 324
    :pswitch_data_0
    .packed-switch 0x0
        :pswitch_0
        :pswitch_1
        :pswitch_2
    .end packed-switch
.end method

.method public isSamplePlaying()Z
    .locals 1

    .prologue
    .line 402
    iget-object v0, p0, Lmiui/preference/VolumePreference$SeekBarVolumizer;->qY:Landroid/media/Ringtone;

    if-eqz v0, :cond_0

    iget-object v0, p0, Lmiui/preference/VolumePreference$SeekBarVolumizer;->qY:Landroid/media/Ringtone;

    invoke-virtual {v0}, Landroid/media/Ringtone;->isPlaying()Z

    move-result v0

    if-eqz v0, :cond_0

    const/4 v0, 0x1

    :goto_0
    return v0

    :cond_0
    const/4 v0, 0x0

    goto :goto_0
.end method

.method public muteVolume()V
    .locals 3

    .prologue
    const/4 v1, 0x0

    const/4 v2, -0x1

    .line 425
    iget v0, p0, Lmiui/preference/VolumePreference$SeekBarVolumizer;->rb:I

    if-eq v0, v2, :cond_0

    .line 426
    iget-object v0, p0, Lmiui/preference/VolumePreference$SeekBarVolumizer;->ra:Landroid/widget/SeekBar;

    iget v1, p0, Lmiui/preference/VolumePreference$SeekBarVolumizer;->rb:I

    invoke-virtual {v0, v1}, Landroid/widget/SeekBar;->setProgress(I)V

    .line 427
    iget v0, p0, Lmiui/preference/VolumePreference$SeekBarVolumizer;->rb:I

    invoke-virtual {p0, v0}, Lmiui/preference/VolumePreference$SeekBarVolumizer;->J(I)V

    .line 428
    invoke-direct {p0}, Lmiui/preference/VolumePreference$SeekBarVolumizer;->bs()V

    .line 429
    iput v2, p0, Lmiui/preference/VolumePreference$SeekBarVolumizer;->rb:I

    .line 436
    :goto_0
    return-void

    .line 431
    :cond_0
    iget-object v0, p0, Lmiui/preference/VolumePreference$SeekBarVolumizer;->ra:Landroid/widget/SeekBar;

    invoke-virtual {v0}, Landroid/widget/SeekBar;->getProgress()I

    move-result v0

    iput v0, p0, Lmiui/preference/VolumePreference$SeekBarVolumizer;->rb:I

    .line 432
    iget-object v0, p0, Lmiui/preference/VolumePreference$SeekBarVolumizer;->ra:Landroid/widget/SeekBar;

    invoke-virtual {v0, v1}, Landroid/widget/SeekBar;->setProgress(I)V

    .line 433
    invoke-direct {p0}, Lmiui/preference/VolumePreference$SeekBarVolumizer;->bu()V

    .line 434
    invoke-virtual {p0, v1}, Lmiui/preference/VolumePreference$SeekBarVolumizer;->J(I)V

    goto :goto_0
.end method

.method public onProgressChanged(Landroid/widget/SeekBar;IZ)V
    .locals 0

    .prologue
    .line 380
    if-nez p3, :cond_0

    .line 385
    :goto_0
    return-void

    .line 384
    :cond_0
    invoke-virtual {p0, p2}, Lmiui/preference/VolumePreference$SeekBarVolumizer;->J(I)V

    goto :goto_0
.end method

.method public onRestoreInstanceState(Lmiui/preference/VolumePreference$VolumeStore;)V
    .locals 2

    .prologue
    .line 446
    iget v0, p1, Lmiui/preference/VolumePreference$VolumeStore;->volume:I

    const/4 v1, -0x1

    if-eq v0, v1, :cond_0

    .line 447
    iget v0, p1, Lmiui/preference/VolumePreference$VolumeStore;->originalVolume:I

    iput v0, p0, Lmiui/preference/VolumePreference$SeekBarVolumizer;->qX:I

    .line 448
    iget v0, p1, Lmiui/preference/VolumePreference$VolumeStore;->volume:I

    iput v0, p0, Lmiui/preference/VolumePreference$SeekBarVolumizer;->qZ:I

    .line 449
    iget v0, p0, Lmiui/preference/VolumePreference$SeekBarVolumizer;->qZ:I

    invoke-virtual {p0, v0}, Lmiui/preference/VolumePreference$SeekBarVolumizer;->J(I)V

    .line 451
    :cond_0
    return-void
.end method

.method public onSaveInstanceState(Lmiui/preference/VolumePreference$VolumeStore;)V
    .locals 1

    .prologue
    .line 439
    iget v0, p0, Lmiui/preference/VolumePreference$SeekBarVolumizer;->qZ:I

    if-ltz v0, :cond_0

    .line 440
    iget v0, p0, Lmiui/preference/VolumePreference$SeekBarVolumizer;->qZ:I

    iput v0, p1, Lmiui/preference/VolumePreference$VolumeStore;->volume:I

    .line 441
    iget v0, p0, Lmiui/preference/VolumePreference$SeekBarVolumizer;->qX:I

    iput v0, p1, Lmiui/preference/VolumePreference$VolumeStore;->originalVolume:I

    .line 443
    :cond_0
    return-void
.end method

.method public onStartTrackingTouch(Landroid/widget/SeekBar;)V
    .locals 0

    .prologue
    .line 395
    return-void
.end method

.method public onStopTrackingTouch(Landroid/widget/SeekBar;)V
    .locals 0

    .prologue
    .line 398
    invoke-direct {p0}, Lmiui/preference/VolumePreference$SeekBarVolumizer;->bs()V

    .line 399
    return-void
.end method

.method public revertVolume()V
    .locals 4

    .prologue
    .line 375
    iget-object v0, p0, Lmiui/preference/VolumePreference$SeekBarVolumizer;->qV:Landroid/media/AudioManager;

    iget v1, p0, Lmiui/preference/VolumePreference$SeekBarVolumizer;->qW:I

    iget v2, p0, Lmiui/preference/VolumePreference$SeekBarVolumizer;->qX:I

    const/4 v3, 0x0

    invoke-virtual {v0, v1, v2, v3}, Landroid/media/AudioManager;->setStreamVolume(III)V

    .line 376
    return-void
.end method

.method public startSample()V
    .locals 0

    .prologue
    .line 406
    invoke-direct {p0}, Lmiui/preference/VolumePreference$SeekBarVolumizer;->bs()V

    .line 407
    return-void
.end method

.method public stop()V
    .locals 2

    .prologue
    .line 369
    invoke-direct {p0}, Lmiui/preference/VolumePreference$SeekBarVolumizer;->bu()V

    .line 370
    iget-object v0, p0, Lmiui/preference/VolumePreference$SeekBarVolumizer;->mContext:Landroid/content/Context;

    invoke-virtual {v0}, Landroid/content/Context;->getContentResolver()Landroid/content/ContentResolver;

    move-result-object v0

    iget-object v1, p0, Lmiui/preference/VolumePreference$SeekBarVolumizer;->rg:Landroid/database/ContentObserver;

    invoke-virtual {v0, v1}, Landroid/content/ContentResolver;->unregisterContentObserver(Landroid/database/ContentObserver;)V

    .line 371
    iget-object v0, p0, Lmiui/preference/VolumePreference$SeekBarVolumizer;->ra:Landroid/widget/SeekBar;

    const/4 v1, 0x0

    invoke-virtual {v0, v1}, Landroid/widget/SeekBar;->setOnSeekBarChangeListener(Landroid/widget/SeekBar$OnSeekBarChangeListener;)V

    .line 372
    return-void
.end method

.method public stopSample()V
    .locals 0

    .prologue
    .line 410
    invoke-direct {p0}, Lmiui/preference/VolumePreference$SeekBarVolumizer;->bu()V

    .line 411
    return-void
.end method
