.class public Lmiui/io/ResettableInputStream;
.super Ljava/io/InputStream;
.source "SourceFile"


# annotations
.annotation system Ldalvik/annotation/MemberClasses;
    value = {
        Lmiui/io/ResettableInputStream$1;,
        Lmiui/io/ResettableInputStream$Type;
    }
.end annotation


# instance fields
.field private PB:Ljava/lang/Throwable;

.field private final Pt:Lmiui/io/ResettableInputStream$Type;

.field private final Pu:Ljava/lang/String;

.field private final Pv:Landroid/content/res/AssetManager;

.field private final Pw:Ljava/lang/String;

.field private final Px:[B

.field private Py:Ljava/io/InputStream;

.field private Pz:Ljava/io/IOException;

.field private final eC:Ljava/lang/Object;

.field private final mContext:Landroid/content/Context;

.field private final wQ:Landroid/net/Uri;


# direct methods
.method public constructor <init>(Landroid/content/Context;Landroid/net/Uri;)V
    .locals 3

    .prologue
    const/4 v2, 0x0

    .line 81
    invoke-direct {p0}, Ljava/io/InputStream;-><init>()V

    .line 29
    new-instance v0, Lmiui/io/a;

    invoke-direct {v0, p0}, Lmiui/io/a;-><init>(Lmiui/io/ResettableInputStream;)V

    iput-object v0, p0, Lmiui/io/ResettableInputStream;->eC:Ljava/lang/Object;

    .line 82
    const-string v0, "file"

    invoke-virtual {p2}, Landroid/net/Uri;->getScheme()Ljava/lang/String;

    move-result-object v1

    invoke-virtual {v0, v1}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v0

    if-eqz v0, :cond_0

    .line 83
    sget-object v0, Lmiui/io/ResettableInputStream$Type;->yQ:Lmiui/io/ResettableInputStream$Type;

    iput-object v0, p0, Lmiui/io/ResettableInputStream;->Pt:Lmiui/io/ResettableInputStream$Type;

    .line 84
    invoke-virtual {p2}, Landroid/net/Uri;->getPath()Ljava/lang/String;

    move-result-object v0

    iput-object v0, p0, Lmiui/io/ResettableInputStream;->Pu:Ljava/lang/String;

    .line 85
    iput-object v2, p0, Lmiui/io/ResettableInputStream;->mContext:Landroid/content/Context;

    .line 86
    iput-object v2, p0, Lmiui/io/ResettableInputStream;->wQ:Landroid/net/Uri;

    .line 87
    iput-object v2, p0, Lmiui/io/ResettableInputStream;->Pv:Landroid/content/res/AssetManager;

    .line 88
    iput-object v2, p0, Lmiui/io/ResettableInputStream;->Pw:Ljava/lang/String;

    .line 89
    iput-object v2, p0, Lmiui/io/ResettableInputStream;->Px:[B

    .line 99
    :goto_0
    return-void

    .line 91
    :cond_0
    sget-object v0, Lmiui/io/ResettableInputStream$Type;->yR:Lmiui/io/ResettableInputStream$Type;

    iput-object v0, p0, Lmiui/io/ResettableInputStream;->Pt:Lmiui/io/ResettableInputStream$Type;

    .line 92
    iput-object p1, p0, Lmiui/io/ResettableInputStream;->mContext:Landroid/content/Context;

    .line 93
    iput-object p2, p0, Lmiui/io/ResettableInputStream;->wQ:Landroid/net/Uri;

    .line 94
    iput-object v2, p0, Lmiui/io/ResettableInputStream;->Pu:Ljava/lang/String;

    .line 95
    iput-object v2, p0, Lmiui/io/ResettableInputStream;->Pv:Landroid/content/res/AssetManager;

    .line 96
    iput-object v2, p0, Lmiui/io/ResettableInputStream;->Pw:Ljava/lang/String;

    .line 97
    iput-object v2, p0, Lmiui/io/ResettableInputStream;->Px:[B

    goto :goto_0
.end method

.method public constructor <init>(Landroid/content/res/AssetManager;Ljava/lang/String;)V
    .locals 2

    .prologue
    const/4 v1, 0x0

    .line 105
    invoke-direct {p0}, Ljava/io/InputStream;-><init>()V

    .line 29
    new-instance v0, Lmiui/io/a;

    invoke-direct {v0, p0}, Lmiui/io/a;-><init>(Lmiui/io/ResettableInputStream;)V

    iput-object v0, p0, Lmiui/io/ResettableInputStream;->eC:Ljava/lang/Object;

    .line 106
    sget-object v0, Lmiui/io/ResettableInputStream$Type;->yS:Lmiui/io/ResettableInputStream$Type;

    iput-object v0, p0, Lmiui/io/ResettableInputStream;->Pt:Lmiui/io/ResettableInputStream$Type;

    .line 107
    iput-object p1, p0, Lmiui/io/ResettableInputStream;->Pv:Landroid/content/res/AssetManager;

    .line 108
    iput-object p2, p0, Lmiui/io/ResettableInputStream;->Pw:Ljava/lang/String;

    .line 109
    iput-object v1, p0, Lmiui/io/ResettableInputStream;->Pu:Ljava/lang/String;

    .line 110
    iput-object v1, p0, Lmiui/io/ResettableInputStream;->mContext:Landroid/content/Context;

    .line 111
    iput-object v1, p0, Lmiui/io/ResettableInputStream;->wQ:Landroid/net/Uri;

    .line 112
    iput-object v1, p0, Lmiui/io/ResettableInputStream;->Px:[B

    .line 113
    return-void
.end method

.method public constructor <init>(Ljava/lang/String;)V
    .locals 2

    .prologue
    const/4 v1, 0x0

    .line 67
    invoke-direct {p0}, Ljava/io/InputStream;-><init>()V

    .line 29
    new-instance v0, Lmiui/io/a;

    invoke-direct {v0, p0}, Lmiui/io/a;-><init>(Lmiui/io/ResettableInputStream;)V

    iput-object v0, p0, Lmiui/io/ResettableInputStream;->eC:Ljava/lang/Object;

    .line 68
    sget-object v0, Lmiui/io/ResettableInputStream$Type;->yQ:Lmiui/io/ResettableInputStream$Type;

    iput-object v0, p0, Lmiui/io/ResettableInputStream;->Pt:Lmiui/io/ResettableInputStream$Type;

    .line 69
    iput-object p1, p0, Lmiui/io/ResettableInputStream;->Pu:Ljava/lang/String;

    .line 70
    iput-object v1, p0, Lmiui/io/ResettableInputStream;->mContext:Landroid/content/Context;

    .line 71
    iput-object v1, p0, Lmiui/io/ResettableInputStream;->wQ:Landroid/net/Uri;

    .line 72
    iput-object v1, p0, Lmiui/io/ResettableInputStream;->Pv:Landroid/content/res/AssetManager;

    .line 73
    iput-object v1, p0, Lmiui/io/ResettableInputStream;->Pw:Ljava/lang/String;

    .line 74
    iput-object v1, p0, Lmiui/io/ResettableInputStream;->Px:[B

    .line 75
    return-void
.end method

.method public constructor <init>([B)V
    .locals 2

    .prologue
    const/4 v1, 0x0

    .line 118
    invoke-direct {p0}, Ljava/io/InputStream;-><init>()V

    .line 29
    new-instance v0, Lmiui/io/a;

    invoke-direct {v0, p0}, Lmiui/io/a;-><init>(Lmiui/io/ResettableInputStream;)V

    iput-object v0, p0, Lmiui/io/ResettableInputStream;->eC:Ljava/lang/Object;

    .line 119
    sget-object v0, Lmiui/io/ResettableInputStream$Type;->yT:Lmiui/io/ResettableInputStream$Type;

    iput-object v0, p0, Lmiui/io/ResettableInputStream;->Pt:Lmiui/io/ResettableInputStream$Type;

    .line 120
    iput-object p1, p0, Lmiui/io/ResettableInputStream;->Px:[B

    .line 121
    iput-object v1, p0, Lmiui/io/ResettableInputStream;->Pu:Ljava/lang/String;

    .line 122
    iput-object v1, p0, Lmiui/io/ResettableInputStream;->mContext:Landroid/content/Context;

    .line 123
    iput-object v1, p0, Lmiui/io/ResettableInputStream;->wQ:Landroid/net/Uri;

    .line 124
    iput-object v1, p0, Lmiui/io/ResettableInputStream;->Pv:Landroid/content/res/AssetManager;

    .line 125
    iput-object v1, p0, Lmiui/io/ResettableInputStream;->Pw:Ljava/lang/String;

    .line 126
    return-void
.end method

.method static synthetic a(Lmiui/io/ResettableInputStream;)Ljava/lang/Throwable;
    .locals 1

    .prologue
    .line 21
    iget-object v0, p0, Lmiui/io/ResettableInputStream;->PB:Ljava/lang/Throwable;

    return-object v0
.end method

.method private ey()V
    .locals 4
    .annotation system Ldalvik/annotation/Throws;
        value = {
            Ljava/io/IOException;
        }
    .end annotation

    .prologue
    .line 129
    iget-object v0, p0, Lmiui/io/ResettableInputStream;->Pz:Ljava/io/IOException;

    if-eqz v0, :cond_0

    .line 130
    iget-object v0, p0, Lmiui/io/ResettableInputStream;->Pz:Ljava/io/IOException;

    throw v0

    .line 133
    :cond_0
    iget-object v0, p0, Lmiui/io/ResettableInputStream;->Py:Ljava/io/InputStream;

    if-eqz v0, :cond_1

    .line 157
    :goto_0
    return-void

    .line 137
    :cond_1
    iget-object v1, p0, Lmiui/io/ResettableInputStream;->eC:Ljava/lang/Object;

    monitor-enter v1

    .line 138
    :try_start_0
    sget-object v0, Lmiui/io/ResettableInputStream$1;->Om:[I

    iget-object v2, p0, Lmiui/io/ResettableInputStream;->Pt:Lmiui/io/ResettableInputStream$Type;

    invoke-virtual {v2}, Lmiui/io/ResettableInputStream$Type;->ordinal()I

    move-result v2

    aget v0, v0, v2

    packed-switch v0, :pswitch_data_0

    .line 152
    new-instance v0, Ljava/lang/IllegalStateException;

    new-instance v2, Ljava/lang/StringBuilder;

    invoke-direct {v2}, Ljava/lang/StringBuilder;-><init>()V

    const-string v3, "Unkown type "

    invoke-virtual {v2, v3}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v2

    iget-object v3, p0, Lmiui/io/ResettableInputStream;->Pt:Lmiui/io/ResettableInputStream$Type;

    invoke-virtual {v2, v3}, Ljava/lang/StringBuilder;->append(Ljava/lang/Object;)Ljava/lang/StringBuilder;

    move-result-object v2

    invoke-virtual {v2}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v2

    invoke-direct {v0, v2}, Ljava/lang/IllegalStateException;-><init>(Ljava/lang/String;)V

    throw v0

    .line 156
    :catchall_0
    move-exception v0

    monitor-exit v1
    :try_end_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    throw v0

    .line 140
    :pswitch_0
    :try_start_1
    iget-object v0, p0, Lmiui/io/ResettableInputStream;->mContext:Landroid/content/Context;

    invoke-virtual {v0}, Landroid/content/Context;->getContentResolver()Landroid/content/ContentResolver;

    move-result-object v0

    iget-object v2, p0, Lmiui/io/ResettableInputStream;->wQ:Landroid/net/Uri;

    invoke-virtual {v0, v2}, Landroid/content/ContentResolver;->openInputStream(Landroid/net/Uri;)Ljava/io/InputStream;

    move-result-object v0

    iput-object v0, p0, Lmiui/io/ResettableInputStream;->Py:Ljava/io/InputStream;

    .line 155
    :goto_1
    new-instance v0, Ljava/lang/Throwable;

    invoke-direct {v0}, Ljava/lang/Throwable;-><init>()V

    iput-object v0, p0, Lmiui/io/ResettableInputStream;->PB:Ljava/lang/Throwable;

    .line 156
    monitor-exit v1

    goto :goto_0

    .line 143
    :pswitch_1
    new-instance v0, Ljava/io/FileInputStream;

    iget-object v2, p0, Lmiui/io/ResettableInputStream;->Pu:Ljava/lang/String;

    invoke-direct {v0, v2}, Ljava/io/FileInputStream;-><init>(Ljava/lang/String;)V

    iput-object v0, p0, Lmiui/io/ResettableInputStream;->Py:Ljava/io/InputStream;

    goto :goto_1

    .line 146
    :pswitch_2
    iget-object v0, p0, Lmiui/io/ResettableInputStream;->Pv:Landroid/content/res/AssetManager;

    iget-object v2, p0, Lmiui/io/ResettableInputStream;->Pw:Ljava/lang/String;

    invoke-virtual {v0, v2}, Landroid/content/res/AssetManager;->open(Ljava/lang/String;)Ljava/io/InputStream;

    move-result-object v0

    iput-object v0, p0, Lmiui/io/ResettableInputStream;->Py:Ljava/io/InputStream;

    goto :goto_1

    .line 149
    :pswitch_3
    new-instance v0, Ljava/io/ByteArrayInputStream;

    iget-object v2, p0, Lmiui/io/ResettableInputStream;->Px:[B

    invoke-direct {v0, v2}, Ljava/io/ByteArrayInputStream;-><init>([B)V

    iput-object v0, p0, Lmiui/io/ResettableInputStream;->Py:Ljava/io/InputStream;
    :try_end_1
    .catchall {:try_start_1 .. :try_end_1} :catchall_0

    goto :goto_1

    .line 138
    :pswitch_data_0
    .packed-switch 0x1
        :pswitch_0
        :pswitch_1
        :pswitch_2
        :pswitch_3
    .end packed-switch
.end method


# virtual methods
.method public available()I
    .locals 1
    .annotation system Ldalvik/annotation/Throws;
        value = {
            Ljava/io/IOException;
        }
    .end annotation

    .prologue
    .line 165
    invoke-direct {p0}, Lmiui/io/ResettableInputStream;->ey()V

    .line 166
    iget-object v0, p0, Lmiui/io/ResettableInputStream;->Py:Ljava/io/InputStream;

    invoke-virtual {v0}, Ljava/io/InputStream;->available()I

    move-result v0

    return v0
.end method

.method public close()V
    .locals 3
    .annotation system Ldalvik/annotation/Throws;
        value = {
            Ljava/io/IOException;
        }
    .end annotation

    .prologue
    .line 175
    iget-object v0, p0, Lmiui/io/ResettableInputStream;->Py:Ljava/io/InputStream;

    if-nez v0, :cond_0

    .line 188
    :goto_0
    return-void

    .line 179
    :cond_0
    iget-object v1, p0, Lmiui/io/ResettableInputStream;->eC:Ljava/lang/Object;

    monitor-enter v1

    .line 181
    :try_start_0
    iget-object v0, p0, Lmiui/io/ResettableInputStream;->Py:Ljava/io/InputStream;

    invoke-virtual {v0}, Ljava/io/InputStream;->close()V
    :try_end_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_1

    .line 183
    const/4 v0, 0x0

    :try_start_1
    iput-object v0, p0, Lmiui/io/ResettableInputStream;->PB:Ljava/lang/Throwable;

    .line 184
    const/4 v0, 0x0

    iput-object v0, p0, Lmiui/io/ResettableInputStream;->Py:Ljava/io/InputStream;

    .line 185
    const/4 v0, 0x0

    iput-object v0, p0, Lmiui/io/ResettableInputStream;->Pz:Ljava/io/IOException;

    .line 187
    monitor-exit v1

    goto :goto_0

    :catchall_0
    move-exception v0

    monitor-exit v1
    :try_end_1
    .catchall {:try_start_1 .. :try_end_1} :catchall_0

    throw v0

    .line 183
    :catchall_1
    move-exception v0

    const/4 v2, 0x0

    :try_start_2
    iput-object v2, p0, Lmiui/io/ResettableInputStream;->PB:Ljava/lang/Throwable;

    .line 184
    const/4 v2, 0x0

    iput-object v2, p0, Lmiui/io/ResettableInputStream;->Py:Ljava/io/InputStream;

    .line 185
    const/4 v2, 0x0

    iput-object v2, p0, Lmiui/io/ResettableInputStream;->Pz:Ljava/io/IOException;

    throw v0
    :try_end_2
    .catchall {:try_start_2 .. :try_end_2} :catchall_0
.end method

.method public mark(I)V
    .locals 1

    .prologue
    .line 197
    :try_start_0
    invoke-direct {p0}, Lmiui/io/ResettableInputStream;->ey()V

    .line 198
    iget-object v0, p0, Lmiui/io/ResettableInputStream;->Py:Ljava/io/InputStream;

    invoke-virtual {v0, p1}, Ljava/io/InputStream;->mark(I)V
    :try_end_0
    .catch Ljava/io/IOException; {:try_start_0 .. :try_end_0} :catch_0

    .line 202
    :goto_0
    return-void

    .line 199
    :catch_0
    move-exception v0

    .line 200
    iput-object v0, p0, Lmiui/io/ResettableInputStream;->Pz:Ljava/io/IOException;

    goto :goto_0
.end method

.method public markSupported()Z
    .locals 1

    .prologue
    .line 211
    :try_start_0
    invoke-direct {p0}, Lmiui/io/ResettableInputStream;->ey()V

    .line 212
    iget-object v0, p0, Lmiui/io/ResettableInputStream;->Py:Ljava/io/InputStream;

    invoke-virtual {v0}, Ljava/io/InputStream;->markSupported()Z
    :try_end_0
    .catch Ljava/io/IOException; {:try_start_0 .. :try_end_0} :catch_0

    move-result v0

    .line 216
    :goto_0
    return v0

    .line 213
    :catch_0
    move-exception v0

    .line 214
    iput-object v0, p0, Lmiui/io/ResettableInputStream;->Pz:Ljava/io/IOException;

    .line 216
    invoke-super {p0}, Ljava/io/InputStream;->markSupported()Z

    move-result v0

    goto :goto_0
.end method

.method public read()I
    .locals 1
    .annotation system Ldalvik/annotation/Throws;
        value = {
            Ljava/io/IOException;
        }
    .end annotation

    .prologue
    .line 225
    invoke-direct {p0}, Lmiui/io/ResettableInputStream;->ey()V

    .line 226
    iget-object v0, p0, Lmiui/io/ResettableInputStream;->Py:Ljava/io/InputStream;

    invoke-virtual {v0}, Ljava/io/InputStream;->read()I

    move-result v0

    return v0
.end method

.method public read([B)I
    .locals 1
    .annotation system Ldalvik/annotation/Throws;
        value = {
            Ljava/io/IOException;
        }
    .end annotation

    .prologue
    .line 235
    invoke-direct {p0}, Lmiui/io/ResettableInputStream;->ey()V

    .line 236
    iget-object v0, p0, Lmiui/io/ResettableInputStream;->Py:Ljava/io/InputStream;

    invoke-virtual {v0, p1}, Ljava/io/InputStream;->read([B)I

    move-result v0

    return v0
.end method

.method public read([BII)I
    .locals 1
    .annotation system Ldalvik/annotation/Throws;
        value = {
            Ljava/io/IOException;
        }
    .end annotation

    .prologue
    .line 245
    invoke-direct {p0}, Lmiui/io/ResettableInputStream;->ey()V

    .line 246
    iget-object v0, p0, Lmiui/io/ResettableInputStream;->Py:Ljava/io/InputStream;

    invoke-virtual {v0, p1, p2, p3}, Ljava/io/InputStream;->read([BII)I

    move-result v0

    return v0
.end method

.method public declared-synchronized reset()V
    .locals 3
    .annotation system Ldalvik/annotation/Throws;
        value = {
            Ljava/io/IOException;
        }
    .end annotation

    .prologue
    .line 254
    monitor-enter p0

    :try_start_0
    iget-object v0, p0, Lmiui/io/ResettableInputStream;->Py:Ljava/io/InputStream;

    if-eqz v0, :cond_0

    .line 255
    iget-object v0, p0, Lmiui/io/ResettableInputStream;->Py:Ljava/io/InputStream;

    instance-of v0, v0, Ljava/io/FileInputStream;

    if-eqz v0, :cond_1

    .line 256
    iget-object v0, p0, Lmiui/io/ResettableInputStream;->Py:Ljava/io/InputStream;

    check-cast v0, Ljava/io/FileInputStream;

    invoke-virtual {v0}, Ljava/io/FileInputStream;->getChannel()Ljava/nio/channels/FileChannel;

    move-result-object v0

    const-wide/16 v1, 0x0

    invoke-virtual {v0, v1, v2}, Ljava/nio/channels/FileChannel;->position(J)Ljava/nio/channels/FileChannel;
    :try_end_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    .line 265
    :cond_0
    :goto_0
    monitor-exit p0

    return-void

    .line 258
    :cond_1
    :try_start_1
    iget-object v0, p0, Lmiui/io/ResettableInputStream;->Py:Ljava/io/InputStream;

    instance-of v0, v0, Landroid/content/res/AssetManager$AssetInputStream;

    if-nez v0, :cond_2

    iget-object v0, p0, Lmiui/io/ResettableInputStream;->Py:Ljava/io/InputStream;

    instance-of v0, v0, Ljava/io/ByteArrayInputStream;

    if-eqz v0, :cond_3

    .line 260
    :cond_2
    iget-object v0, p0, Lmiui/io/ResettableInputStream;->Py:Ljava/io/InputStream;

    invoke-virtual {v0}, Ljava/io/InputStream;->reset()V
    :try_end_1
    .catchall {:try_start_1 .. :try_end_1} :catchall_0

    goto :goto_0

    .line 254
    :catchall_0
    move-exception v0

    monitor-exit p0

    throw v0

    .line 263
    :cond_3
    :try_start_2
    invoke-virtual {p0}, Lmiui/io/ResettableInputStream;->close()V
    :try_end_2
    .catchall {:try_start_2 .. :try_end_2} :catchall_0

    goto :goto_0
.end method

.method public skip(J)J
    .locals 2
    .annotation system Ldalvik/annotation/Throws;
        value = {
            Ljava/io/IOException;
        }
    .end annotation

    .prologue
    .line 272
    invoke-direct {p0}, Lmiui/io/ResettableInputStream;->ey()V

    .line 273
    iget-object v0, p0, Lmiui/io/ResettableInputStream;->Py:Ljava/io/InputStream;

    invoke-virtual {v0, p1, p2}, Ljava/io/InputStream;->skip(J)J

    move-result-wide v0

    return-wide v0
.end method
