.class Lcom/miui/internal/graphics/gif/a;
.super Landroid/os/Handler;
.source "SourceFile"


# static fields
.field private static final FT:I = 0x1

.field protected static final TAG:Ljava/lang/String; = "DecodeGifFrames"


# instance fields
.field private FU:Landroid/os/Handler;

.field FV:Lcom/miui/internal/graphics/gif/DecodeGifImageHelper$GifDecodeResult;

.field FW:Landroid/os/HandlerThread;

.field private mGifSource:Lmiui/io/ResettableInputStream;

.field private mMaxDecodeSize:J


# direct methods
.method public constructor <init>(Landroid/os/HandlerThread;Lmiui/io/ResettableInputStream;JLandroid/os/Handler;)V
    .locals 1

    .prologue
    .line 67
    invoke-virtual {p1}, Landroid/os/HandlerThread;->getLooper()Landroid/os/Looper;

    move-result-object v0

    invoke-direct {p0, v0}, Landroid/os/Handler;-><init>(Landroid/os/Looper;)V

    .line 69
    iput-object p1, p0, Lcom/miui/internal/graphics/gif/a;->FW:Landroid/os/HandlerThread;

    .line 71
    iput-wide p3, p0, Lcom/miui/internal/graphics/gif/a;->mMaxDecodeSize:J

    .line 72
    iput-object p2, p0, Lcom/miui/internal/graphics/gif/a;->mGifSource:Lmiui/io/ResettableInputStream;

    .line 73
    iput-object p5, p0, Lcom/miui/internal/graphics/gif/a;->FU:Landroid/os/Handler;

    .line 74
    return-void
.end method

.method public static a(Lmiui/io/ResettableInputStream;JLandroid/os/Handler;)Lcom/miui/internal/graphics/gif/a;
    .locals 6

    .prologue
    .line 55
    new-instance v1, Landroid/os/HandlerThread;

    const-string v0, "handler thread to decode GIF frames"

    invoke-direct {v1, v0}, Landroid/os/HandlerThread;-><init>(Ljava/lang/String;)V

    .line 56
    invoke-virtual {v1}, Landroid/os/HandlerThread;->start()V

    .line 57
    new-instance v0, Lcom/miui/internal/graphics/gif/a;

    move-object v2, p0

    move-wide v3, p1

    move-object v5, p3

    invoke-direct/range {v0 .. v5}, Lcom/miui/internal/graphics/gif/a;-><init>(Landroid/os/HandlerThread;Lmiui/io/ResettableInputStream;JLandroid/os/Handler;)V

    return-object v0
.end method


# virtual methods
.method public ae(I)V
    .locals 2

    .prologue
    .line 94
    iget-object v0, p0, Lcom/miui/internal/graphics/gif/a;->FV:Lcom/miui/internal/graphics/gif/DecodeGifImageHelper$GifDecodeResult;

    if-eqz v0, :cond_0

    .line 102
    :goto_0
    return-void

    .line 98
    :cond_0
    new-instance v0, Lcom/miui/internal/graphics/gif/DecodeGifImageHelper$GifDecodeResult;

    invoke-direct {v0}, Lcom/miui/internal/graphics/gif/DecodeGifImageHelper$GifDecodeResult;-><init>()V

    iput-object v0, p0, Lcom/miui/internal/graphics/gif/a;->FV:Lcom/miui/internal/graphics/gif/DecodeGifImageHelper$GifDecodeResult;

    .line 100
    const/4 v0, 0x1

    const/4 v1, 0x0

    invoke-virtual {p0, v0, p1, v1}, Lcom/miui/internal/graphics/gif/a;->obtainMessage(III)Landroid/os/Message;

    move-result-object v0

    .line 101
    invoke-virtual {p0, v0}, Lcom/miui/internal/graphics/gif/a;->sendMessage(Landroid/os/Message;)Z

    goto :goto_0
.end method

.method public destroy()V
    .locals 1

    .prologue
    .line 77
    iget-object v0, p0, Lcom/miui/internal/graphics/gif/a;->FW:Landroid/os/HandlerThread;

    invoke-virtual {v0}, Landroid/os/HandlerThread;->quit()Z

    .line 78
    return-void
.end method

.method public dq()Lcom/miui/internal/graphics/gif/DecodeGifImageHelper$GifDecodeResult;
    .locals 2

    .prologue
    .line 110
    iget-object v0, p0, Lcom/miui/internal/graphics/gif/a;->FV:Lcom/miui/internal/graphics/gif/DecodeGifImageHelper$GifDecodeResult;

    .line 111
    const/4 v1, 0x0

    iput-object v1, p0, Lcom/miui/internal/graphics/gif/a;->FV:Lcom/miui/internal/graphics/gif/DecodeGifImageHelper$GifDecodeResult;

    .line 112
    return-object v0
.end method

.method protected finalize()V
    .locals 1
    .annotation system Ldalvik/annotation/Throws;
        value = {
            Ljava/lang/Throwable;
        }
    .end annotation

    .prologue
    .line 83
    iget-object v0, p0, Lcom/miui/internal/graphics/gif/a;->FW:Landroid/os/HandlerThread;

    invoke-virtual {v0}, Landroid/os/HandlerThread;->quit()Z

    .line 85
    invoke-super {p0}, Ljava/lang/Object;->finalize()V

    .line 86
    return-void
.end method

.method public handleMessage(Landroid/os/Message;)V
    .locals 5

    .prologue
    const/4 v4, 0x1

    .line 117
    iget v0, p1, Landroid/os/Message;->what:I

    if-ne v0, v4, :cond_0

    .line 118
    iget v0, p1, Landroid/os/Message;->arg1:I

    .line 119
    iget-object v1, p0, Lcom/miui/internal/graphics/gif/a;->mGifSource:Lmiui/io/ResettableInputStream;

    iget-wide v2, p0, Lcom/miui/internal/graphics/gif/a;->mMaxDecodeSize:J

    invoke-static {v1, v2, v3, v0}, Lcom/miui/internal/graphics/gif/DecodeGifImageHelper;->decode(Lmiui/io/ResettableInputStream;JI)Lcom/miui/internal/graphics/gif/DecodeGifImageHelper$GifDecodeResult;

    move-result-object v0

    .line 123
    iget-object v1, p0, Lcom/miui/internal/graphics/gif/a;->FV:Lcom/miui/internal/graphics/gif/DecodeGifImageHelper$GifDecodeResult;

    iget-object v2, v0, Lcom/miui/internal/graphics/gif/DecodeGifImageHelper$GifDecodeResult;->mGifDecoder:Lcom/miui/internal/graphics/gif/GifDecoder;

    iput-object v2, v1, Lcom/miui/internal/graphics/gif/DecodeGifImageHelper$GifDecodeResult;->mGifDecoder:Lcom/miui/internal/graphics/gif/GifDecoder;

    .line 124
    iget-object v1, p0, Lcom/miui/internal/graphics/gif/a;->FV:Lcom/miui/internal/graphics/gif/DecodeGifImageHelper$GifDecodeResult;

    iget-boolean v0, v0, Lcom/miui/internal/graphics/gif/DecodeGifImageHelper$GifDecodeResult;->mIsDecodeOk:Z

    iput-boolean v0, v1, Lcom/miui/internal/graphics/gif/DecodeGifImageHelper$GifDecodeResult;->mIsDecodeOk:Z

    .line 127
    iget-object v0, p0, Lcom/miui/internal/graphics/gif/a;->FU:Landroid/os/Handler;

    invoke-virtual {v0, v4}, Landroid/os/Handler;->sendEmptyMessage(I)Z

    .line 129
    :cond_0
    return-void
.end method
