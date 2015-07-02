.class public Lmiui/app/ProgressDialog;
.super Lmiui/app/AlertDialog;
.source "SourceFile"


# static fields
.field public static final STYLE_HORIZONTAL:I = 0x1

.field public static final STYLE_SPINNER:I


# instance fields
.field private kS:Z

.field private kU:I

.field private lL:Landroid/widget/ProgressBar;

.field private lM:Landroid/widget/TextView;

.field private lN:Ljava/lang/String;

.field private lO:Ljava/text/NumberFormat;

.field private lP:I

.field private lQ:I

.field private lR:I

.field private lS:I

.field private lT:I

.field private lU:Landroid/graphics/drawable/Drawable;

.field private lV:Landroid/graphics/drawable/Drawable;

.field private lW:Z

.field private lX:Landroid/os/Handler;

.field private mMessage:Ljava/lang/CharSequence;


# direct methods
.method public constructor <init>(Landroid/content/Context;)V
    .locals 1

    .prologue
    .line 71
    invoke-direct {p0, p1}, Lmiui/app/AlertDialog;-><init>(Landroid/content/Context;)V

    .line 46
    const/4 v0, 0x0

    iput v0, p0, Lmiui/app/ProgressDialog;->kU:I

    .line 72
    invoke-direct {p0}, Lmiui/app/ProgressDialog;->aJ()V

    .line 73
    return-void
.end method

.method public constructor <init>(Landroid/content/Context;I)V
    .locals 1

    .prologue
    .line 88
    invoke-direct {p0, p1, p2}, Lmiui/app/AlertDialog;-><init>(Landroid/content/Context;I)V

    .line 46
    const/4 v0, 0x0

    iput v0, p0, Lmiui/app/ProgressDialog;->kU:I

    .line 89
    invoke-direct {p0}, Lmiui/app/ProgressDialog;->aJ()V

    .line 90
    return-void
.end method

.method static synthetic a(Lmiui/app/ProgressDialog;)Landroid/widget/ProgressBar;
    .locals 1

    .prologue
    .line 32
    iget-object v0, p0, Lmiui/app/ProgressDialog;->lL:Landroid/widget/ProgressBar;

    return-object v0
.end method

.method static synthetic a(Lmiui/app/ProgressDialog;Ljava/lang/CharSequence;)V
    .locals 0

    .prologue
    .line 32
    invoke-super {p0, p1}, Lmiui/app/AlertDialog;->setMessage(Ljava/lang/CharSequence;)V

    return-void
.end method

.method private aJ()V
    .locals 2

    .prologue
    .line 93
    const-string v0, "%1d/%2d"

    iput-object v0, p0, Lmiui/app/ProgressDialog;->lN:Ljava/lang/String;

    .line 94
    invoke-static {}, Ljava/text/NumberFormat;->getPercentInstance()Ljava/text/NumberFormat;

    move-result-object v0

    iput-object v0, p0, Lmiui/app/ProgressDialog;->lO:Ljava/text/NumberFormat;

    .line 95
    iget-object v0, p0, Lmiui/app/ProgressDialog;->lO:Ljava/text/NumberFormat;

    const/4 v1, 0x0

    invoke-virtual {v0, v1}, Ljava/text/NumberFormat;->setMaximumFractionDigits(I)V

    .line 96
    return-void
.end method

.method static synthetic b(Lmiui/app/ProgressDialog;)Ljava/text/NumberFormat;
    .locals 1

    .prologue
    .line 32
    iget-object v0, p0, Lmiui/app/ProgressDialog;->lO:Ljava/text/NumberFormat;

    return-object v0
.end method

.method static synthetic c(Lmiui/app/ProgressDialog;)Ljava/lang/CharSequence;
    .locals 1

    .prologue
    .line 32
    iget-object v0, p0, Lmiui/app/ProgressDialog;->mMessage:Ljava/lang/CharSequence;

    return-object v0
.end method

.method private onProgressChanged()V
    .locals 3

    .prologue
    const/4 v2, 0x0

    .line 470
    iget v0, p0, Lmiui/app/ProgressDialog;->kU:I

    const/4 v1, 0x1

    if-ne v0, v1, :cond_0

    .line 471
    iget-object v0, p0, Lmiui/app/ProgressDialog;->lX:Landroid/os/Handler;

    if-eqz v0, :cond_0

    iget-object v0, p0, Lmiui/app/ProgressDialog;->lX:Landroid/os/Handler;

    invoke-virtual {v0, v2}, Landroid/os/Handler;->hasMessages(I)Z

    move-result v0

    if-nez v0, :cond_0

    .line 472
    iget-object v0, p0, Lmiui/app/ProgressDialog;->lX:Landroid/os/Handler;

    invoke-virtual {v0, v2}, Landroid/os/Handler;->sendEmptyMessage(I)Z

    .line 475
    :cond_0
    return-void
.end method

.method public static show(Landroid/content/Context;Ljava/lang/CharSequence;Ljava/lang/CharSequence;)Lmiui/app/ProgressDialog;
    .locals 1

    .prologue
    .line 108
    const/4 v0, 0x0

    invoke-static {p0, p1, p2, v0}, Lmiui/app/ProgressDialog;->show(Landroid/content/Context;Ljava/lang/CharSequence;Ljava/lang/CharSequence;Z)Lmiui/app/ProgressDialog;

    move-result-object v0

    return-object v0
.end method

.method public static show(Landroid/content/Context;Ljava/lang/CharSequence;Ljava/lang/CharSequence;Z)Lmiui/app/ProgressDialog;
    .locals 6

    .prologue
    .line 122
    const/4 v4, 0x0

    const/4 v5, 0x0

    move-object v0, p0

    move-object v1, p1

    move-object v2, p2

    move v3, p3

    invoke-static/range {v0 .. v5}, Lmiui/app/ProgressDialog;->show(Landroid/content/Context;Ljava/lang/CharSequence;Ljava/lang/CharSequence;ZZLandroid/content/DialogInterface$OnCancelListener;)Lmiui/app/ProgressDialog;

    move-result-object v0

    return-object v0
.end method

.method public static show(Landroid/content/Context;Ljava/lang/CharSequence;Ljava/lang/CharSequence;ZZ)Lmiui/app/ProgressDialog;
    .locals 6

    .prologue
    .line 137
    const/4 v5, 0x0

    move-object v0, p0

    move-object v1, p1

    move-object v2, p2

    move v3, p3

    move v4, p4

    invoke-static/range {v0 .. v5}, Lmiui/app/ProgressDialog;->show(Landroid/content/Context;Ljava/lang/CharSequence;Ljava/lang/CharSequence;ZZLandroid/content/DialogInterface$OnCancelListener;)Lmiui/app/ProgressDialog;

    move-result-object v0

    return-object v0
.end method

.method public static show(Landroid/content/Context;Ljava/lang/CharSequence;Ljava/lang/CharSequence;ZZLandroid/content/DialogInterface$OnCancelListener;)Lmiui/app/ProgressDialog;
    .locals 1

    .prologue
    .line 154
    new-instance v0, Lmiui/app/ProgressDialog;

    invoke-direct {v0, p0}, Lmiui/app/ProgressDialog;-><init>(Landroid/content/Context;)V

    .line 155
    invoke-virtual {v0, p1}, Lmiui/app/ProgressDialog;->setTitle(Ljava/lang/CharSequence;)V

    .line 156
    invoke-virtual {v0, p2}, Lmiui/app/ProgressDialog;->setMessage(Ljava/lang/CharSequence;)V

    .line 157
    invoke-virtual {v0, p3}, Lmiui/app/ProgressDialog;->setIndeterminate(Z)V

    .line 158
    invoke-virtual {v0, p4}, Lmiui/app/ProgressDialog;->setCancelable(Z)V

    .line 159
    invoke-virtual {v0, p5}, Lmiui/app/ProgressDialog;->setOnCancelListener(Landroid/content/DialogInterface$OnCancelListener;)V

    .line 160
    invoke-virtual {v0}, Lmiui/app/ProgressDialog;->show()V

    .line 161
    return-object v0
.end method


# virtual methods
.method public getMax()I
    .locals 1

    .prologue
    .line 316
    iget-object v0, p0, Lmiui/app/ProgressDialog;->lL:Landroid/widget/ProgressBar;

    if-eqz v0, :cond_0

    .line 317
    iget-object v0, p0, Lmiui/app/ProgressDialog;->lL:Landroid/widget/ProgressBar;

    invoke-virtual {v0}, Landroid/widget/ProgressBar;->getMax()I

    move-result v0

    .line 319
    :goto_0
    return v0

    :cond_0
    iget v0, p0, Lmiui/app/ProgressDialog;->lP:I

    goto :goto_0
.end method

.method public getProgress()I
    .locals 1

    .prologue
    .line 292
    iget-object v0, p0, Lmiui/app/ProgressDialog;->lL:Landroid/widget/ProgressBar;

    if-eqz v0, :cond_0

    .line 293
    iget-object v0, p0, Lmiui/app/ProgressDialog;->lL:Landroid/widget/ProgressBar;

    invoke-virtual {v0}, Landroid/widget/ProgressBar;->getProgress()I

    move-result v0

    .line 295
    :goto_0
    return v0

    :cond_0
    iget v0, p0, Lmiui/app/ProgressDialog;->lQ:I

    goto :goto_0
.end method

.method public getSecondaryProgress()I
    .locals 1

    .prologue
    .line 304
    iget-object v0, p0, Lmiui/app/ProgressDialog;->lL:Landroid/widget/ProgressBar;

    if-eqz v0, :cond_0

    .line 305
    iget-object v0, p0, Lmiui/app/ProgressDialog;->lL:Landroid/widget/ProgressBar;

    invoke-virtual {v0}, Landroid/widget/ProgressBar;->getSecondaryProgress()I

    move-result v0

    .line 307
    :goto_0
    return v0

    :cond_0
    iget v0, p0, Lmiui/app/ProgressDialog;->lR:I

    goto :goto_0
.end method

.method public incrementProgressBy(I)V
    .locals 1

    .prologue
    .line 342
    iget-object v0, p0, Lmiui/app/ProgressDialog;->lL:Landroid/widget/ProgressBar;

    if-eqz v0, :cond_0

    .line 343
    iget-object v0, p0, Lmiui/app/ProgressDialog;->lL:Landroid/widget/ProgressBar;

    invoke-virtual {v0, p1}, Landroid/widget/ProgressBar;->incrementProgressBy(I)V

    .line 344
    invoke-direct {p0}, Lmiui/app/ProgressDialog;->onProgressChanged()V

    .line 348
    :goto_0
    return-void

    .line 346
    :cond_0
    iget v0, p0, Lmiui/app/ProgressDialog;->lS:I

    add-int/2addr v0, p1

    iput v0, p0, Lmiui/app/ProgressDialog;->lS:I

    goto :goto_0
.end method

.method public incrementSecondaryProgressBy(I)V
    .locals 1

    .prologue
    .line 356
    iget-object v0, p0, Lmiui/app/ProgressDialog;->lL:Landroid/widget/ProgressBar;

    if-eqz v0, :cond_0

    .line 357
    iget-object v0, p0, Lmiui/app/ProgressDialog;->lL:Landroid/widget/ProgressBar;

    invoke-virtual {v0, p1}, Landroid/widget/ProgressBar;->incrementSecondaryProgressBy(I)V

    .line 358
    invoke-direct {p0}, Lmiui/app/ProgressDialog;->onProgressChanged()V

    .line 362
    :goto_0
    return-void

    .line 360
    :cond_0
    iget v0, p0, Lmiui/app/ProgressDialog;->lT:I

    add-int/2addr v0, p1

    iput v0, p0, Lmiui/app/ProgressDialog;->lT:I

    goto :goto_0
.end method

.method public isIndeterminate()Z
    .locals 1

    .prologue
    .line 412
    iget-object v0, p0, Lmiui/app/ProgressDialog;->lL:Landroid/widget/ProgressBar;

    if-eqz v0, :cond_0

    .line 413
    iget-object v0, p0, Lmiui/app/ProgressDialog;->lL:Landroid/widget/ProgressBar;

    invoke-virtual {v0}, Landroid/widget/ProgressBar;->isIndeterminate()Z

    move-result v0

    .line 415
    :goto_0
    return v0

    :cond_0
    iget-boolean v0, p0, Lmiui/app/ProgressDialog;->kS:Z

    goto :goto_0
.end method

.method protected onCreate(Landroid/os/Bundle;)V
    .locals 7

    .prologue
    const v6, 0x102000d

    const/4 v5, 0x0

    .line 166
    invoke-virtual {p0}, Lmiui/app/ProgressDialog;->getContext()Landroid/content/Context;

    move-result-object v0

    invoke-static {v0}, Landroid/view/LayoutInflater;->from(Landroid/content/Context;)Landroid/view/LayoutInflater;

    move-result-object v0

    .line 167
    invoke-virtual {p0}, Lmiui/app/ProgressDialog;->getContext()Landroid/content/Context;

    move-result-object v1

    sget-object v2, Lcom/miui/internal/R$styleable;->AlertDialog:[I

    const v3, 0x101005d

    const/4 v4, 0x0

    invoke-virtual {v1, v5, v2, v3, v4}, Landroid/content/Context;->obtainStyledAttributes(Landroid/util/AttributeSet;[III)Landroid/content/res/TypedArray;

    move-result-object v1

    .line 170
    iget v2, p0, Lmiui/app/ProgressDialog;->kU:I

    const/4 v3, 0x1

    if-ne v2, v3, :cond_8

    .line 175
    new-instance v2, Lmiui/app/ProgressDialog$1;

    invoke-direct {v2, p0}, Lmiui/app/ProgressDialog$1;-><init>(Lmiui/app/ProgressDialog;)V

    iput-object v2, p0, Lmiui/app/ProgressDialog;->lX:Landroid/os/Handler;

    .line 203
    const/4 v2, 0x6

    sget v3, Lcom/miui/internal/R$layout;->alert_dialog_progress:I

    invoke-virtual {v1, v2, v3}, Landroid/content/res/TypedArray;->getResourceId(II)I

    move-result v2

    invoke-virtual {v0, v2, v5}, Landroid/view/LayoutInflater;->inflate(ILandroid/view/ViewGroup;)Landroid/view/View;

    move-result-object v2

    .line 206
    invoke-virtual {v2, v6}, Landroid/view/View;->findViewById(I)Landroid/view/View;

    move-result-object v0

    check-cast v0, Landroid/widget/ProgressBar;

    iput-object v0, p0, Lmiui/app/ProgressDialog;->lL:Landroid/widget/ProgressBar;

    .line 207
    invoke-virtual {p0, v2}, Lmiui/app/ProgressDialog;->setView(Landroid/view/View;)V

    .line 216
    :goto_0
    invoke-virtual {v1}, Landroid/content/res/TypedArray;->recycle()V

    .line 217
    iget v0, p0, Lmiui/app/ProgressDialog;->lP:I

    if-lez v0, :cond_0

    .line 218
    iget v0, p0, Lmiui/app/ProgressDialog;->lP:I

    invoke-virtual {p0, v0}, Lmiui/app/ProgressDialog;->setMax(I)V

    .line 220
    :cond_0
    iget v0, p0, Lmiui/app/ProgressDialog;->lQ:I

    if-lez v0, :cond_1

    .line 221
    iget v0, p0, Lmiui/app/ProgressDialog;->lQ:I

    invoke-virtual {p0, v0}, Lmiui/app/ProgressDialog;->setProgress(I)V

    .line 223
    :cond_1
    iget v0, p0, Lmiui/app/ProgressDialog;->lR:I

    if-lez v0, :cond_2

    .line 224
    iget v0, p0, Lmiui/app/ProgressDialog;->lR:I

    invoke-virtual {p0, v0}, Lmiui/app/ProgressDialog;->setSecondaryProgress(I)V

    .line 226
    :cond_2
    iget v0, p0, Lmiui/app/ProgressDialog;->lS:I

    if-lez v0, :cond_3

    .line 227
    iget v0, p0, Lmiui/app/ProgressDialog;->lS:I

    invoke-virtual {p0, v0}, Lmiui/app/ProgressDialog;->incrementProgressBy(I)V

    .line 229
    :cond_3
    iget v0, p0, Lmiui/app/ProgressDialog;->lT:I

    if-lez v0, :cond_4

    .line 230
    iget v0, p0, Lmiui/app/ProgressDialog;->lT:I

    invoke-virtual {p0, v0}, Lmiui/app/ProgressDialog;->incrementSecondaryProgressBy(I)V

    .line 232
    :cond_4
    iget-object v0, p0, Lmiui/app/ProgressDialog;->lU:Landroid/graphics/drawable/Drawable;

    if-eqz v0, :cond_5

    .line 233
    iget-object v0, p0, Lmiui/app/ProgressDialog;->lU:Landroid/graphics/drawable/Drawable;

    invoke-virtual {p0, v0}, Lmiui/app/ProgressDialog;->setProgressDrawable(Landroid/graphics/drawable/Drawable;)V

    .line 235
    :cond_5
    iget-object v0, p0, Lmiui/app/ProgressDialog;->lV:Landroid/graphics/drawable/Drawable;

    if-eqz v0, :cond_6

    .line 236
    iget-object v0, p0, Lmiui/app/ProgressDialog;->lV:Landroid/graphics/drawable/Drawable;

    invoke-virtual {p0, v0}, Lmiui/app/ProgressDialog;->setIndeterminateDrawable(Landroid/graphics/drawable/Drawable;)V

    .line 238
    :cond_6
    iget-object v0, p0, Lmiui/app/ProgressDialog;->mMessage:Ljava/lang/CharSequence;

    if-eqz v0, :cond_7

    .line 239
    iget-object v0, p0, Lmiui/app/ProgressDialog;->mMessage:Ljava/lang/CharSequence;

    invoke-virtual {p0, v0}, Lmiui/app/ProgressDialog;->setMessage(Ljava/lang/CharSequence;)V

    .line 241
    :cond_7
    iget-boolean v0, p0, Lmiui/app/ProgressDialog;->kS:Z

    invoke-virtual {p0, v0}, Lmiui/app/ProgressDialog;->setIndeterminate(Z)V

    .line 242
    invoke-direct {p0}, Lmiui/app/ProgressDialog;->onProgressChanged()V

    .line 243
    invoke-super {p0, p1}, Lmiui/app/AlertDialog;->onCreate(Landroid/os/Bundle;)V

    .line 244
    return-void

    .line 209
    :cond_8
    const/4 v2, 0x5

    sget v3, Lcom/miui/internal/R$layout;->progress_dialog:I

    invoke-virtual {v1, v2, v3}, Landroid/content/res/TypedArray;->getResourceId(II)I

    move-result v2

    invoke-virtual {v0, v2, v5}, Landroid/view/LayoutInflater;->inflate(ILandroid/view/ViewGroup;)Landroid/view/View;

    move-result-object v2

    .line 212
    invoke-virtual {v2, v6}, Landroid/view/View;->findViewById(I)Landroid/view/View;

    move-result-object v0

    check-cast v0, Landroid/widget/ProgressBar;

    iput-object v0, p0, Lmiui/app/ProgressDialog;->lL:Landroid/widget/ProgressBar;

    .line 213
    sget v0, Lcom/miui/internal/R$id;->message:I

    invoke-virtual {v2, v0}, Landroid/view/View;->findViewById(I)Landroid/view/View;

    move-result-object v0

    check-cast v0, Landroid/widget/TextView;

    iput-object v0, p0, Lmiui/app/ProgressDialog;->lM:Landroid/widget/TextView;

    .line 214
    invoke-virtual {p0, v2}, Lmiui/app/ProgressDialog;->setView(Landroid/view/View;)V

    goto :goto_0
.end method

.method public onStart()V
    .locals 1

    .prologue
    .line 248
    invoke-super {p0}, Lmiui/app/AlertDialog;->onStart()V

    .line 249
    const/4 v0, 0x1

    iput-boolean v0, p0, Lmiui/app/ProgressDialog;->lW:Z

    .line 250
    return-void
.end method

.method protected onStop()V
    .locals 1

    .prologue
    .line 254
    invoke-super {p0}, Lmiui/app/AlertDialog;->onStop()V

    .line 255
    const/4 v0, 0x0

    iput-boolean v0, p0, Lmiui/app/ProgressDialog;->lW:Z

    .line 256
    return-void
.end method

.method public setIndeterminate(Z)V
    .locals 1

    .prologue
    .line 399
    iget-object v0, p0, Lmiui/app/ProgressDialog;->lL:Landroid/widget/ProgressBar;

    if-eqz v0, :cond_0

    .line 400
    iget-object v0, p0, Lmiui/app/ProgressDialog;->lL:Landroid/widget/ProgressBar;

    invoke-virtual {v0, p1}, Landroid/widget/ProgressBar;->setIndeterminate(Z)V

    .line 404
    :goto_0
    return-void

    .line 402
    :cond_0
    iput-boolean p1, p0, Lmiui/app/ProgressDialog;->kS:Z

    goto :goto_0
.end method

.method public setIndeterminateDrawable(Landroid/graphics/drawable/Drawable;)V
    .locals 1

    .prologue
    .line 385
    iget-object v0, p0, Lmiui/app/ProgressDialog;->lL:Landroid/widget/ProgressBar;

    if-eqz v0, :cond_0

    .line 386
    iget-object v0, p0, Lmiui/app/ProgressDialog;->lL:Landroid/widget/ProgressBar;

    invoke-virtual {v0, p1}, Landroid/widget/ProgressBar;->setIndeterminateDrawable(Landroid/graphics/drawable/Drawable;)V

    .line 390
    :goto_0
    return-void

    .line 388
    :cond_0
    iput-object p1, p0, Lmiui/app/ProgressDialog;->lV:Landroid/graphics/drawable/Drawable;

    goto :goto_0
.end method

.method public setMax(I)V
    .locals 1

    .prologue
    .line 328
    iget-object v0, p0, Lmiui/app/ProgressDialog;->lL:Landroid/widget/ProgressBar;

    if-eqz v0, :cond_0

    .line 329
    iget-object v0, p0, Lmiui/app/ProgressDialog;->lL:Landroid/widget/ProgressBar;

    invoke-virtual {v0, p1}, Landroid/widget/ProgressBar;->setMax(I)V

    .line 330
    invoke-direct {p0}, Lmiui/app/ProgressDialog;->onProgressChanged()V

    .line 334
    :goto_0
    return-void

    .line 332
    :cond_0
    iput p1, p0, Lmiui/app/ProgressDialog;->lP:I

    goto :goto_0
.end method

.method public setMessage(Ljava/lang/CharSequence;)V
    .locals 2

    .prologue
    .line 420
    iget-object v0, p0, Lmiui/app/ProgressDialog;->lL:Landroid/widget/ProgressBar;

    if-eqz v0, :cond_1

    .line 421
    iget v0, p0, Lmiui/app/ProgressDialog;->kU:I

    const/4 v1, 0x1

    if-ne v0, v1, :cond_0

    .line 422
    invoke-super {p0, p1}, Lmiui/app/AlertDialog;->setMessage(Ljava/lang/CharSequence;)V

    .line 423
    iput-object p1, p0, Lmiui/app/ProgressDialog;->mMessage:Ljava/lang/CharSequence;

    .line 430
    :goto_0
    return-void

    .line 425
    :cond_0
    iget-object v0, p0, Lmiui/app/ProgressDialog;->lM:Landroid/widget/TextView;

    invoke-virtual {v0, p1}, Landroid/widget/TextView;->setText(Ljava/lang/CharSequence;)V

    goto :goto_0

    .line 428
    :cond_1
    iput-object p1, p0, Lmiui/app/ProgressDialog;->mMessage:Ljava/lang/CharSequence;

    goto :goto_0
.end method

.method public setProgress(I)V
    .locals 1

    .prologue
    .line 264
    iget-boolean v0, p0, Lmiui/app/ProgressDialog;->lW:Z

    if-eqz v0, :cond_0

    .line 265
    iget-object v0, p0, Lmiui/app/ProgressDialog;->lL:Landroid/widget/ProgressBar;

    invoke-virtual {v0, p1}, Landroid/widget/ProgressBar;->setProgress(I)V

    .line 266
    invoke-direct {p0}, Lmiui/app/ProgressDialog;->onProgressChanged()V

    .line 270
    :goto_0
    return-void

    .line 268
    :cond_0
    iput p1, p0, Lmiui/app/ProgressDialog;->lQ:I

    goto :goto_0
.end method

.method public setProgressDrawable(Landroid/graphics/drawable/Drawable;)V
    .locals 1

    .prologue
    .line 371
    iget-object v0, p0, Lmiui/app/ProgressDialog;->lL:Landroid/widget/ProgressBar;

    if-eqz v0, :cond_0

    .line 372
    iget-object v0, p0, Lmiui/app/ProgressDialog;->lL:Landroid/widget/ProgressBar;

    invoke-virtual {v0, p1}, Landroid/widget/ProgressBar;->setProgressDrawable(Landroid/graphics/drawable/Drawable;)V

    .line 376
    :goto_0
    return-void

    .line 374
    :cond_0
    iput-object p1, p0, Lmiui/app/ProgressDialog;->lU:Landroid/graphics/drawable/Drawable;

    goto :goto_0
.end method

.method public setProgressNumberFormat(Ljava/lang/String;)V
    .locals 0

    .prologue
    .line 452
    iput-object p1, p0, Lmiui/app/ProgressDialog;->lN:Ljava/lang/String;

    .line 453
    invoke-direct {p0}, Lmiui/app/ProgressDialog;->onProgressChanged()V

    .line 454
    return-void
.end method

.method public setProgressPercentFormat(Ljava/text/NumberFormat;)V
    .locals 0

    .prologue
    .line 465
    iput-object p1, p0, Lmiui/app/ProgressDialog;->lO:Ljava/text/NumberFormat;

    .line 466
    invoke-direct {p0}, Lmiui/app/ProgressDialog;->onProgressChanged()V

    .line 467
    return-void
.end method

.method public setProgressStyle(I)V
    .locals 0

    .prologue
    .line 440
    iput p1, p0, Lmiui/app/ProgressDialog;->kU:I

    .line 441
    return-void
.end method

.method public setSecondaryProgress(I)V
    .locals 1

    .prologue
    .line 278
    iget-object v0, p0, Lmiui/app/ProgressDialog;->lL:Landroid/widget/ProgressBar;

    if-eqz v0, :cond_0

    .line 279
    iget-object v0, p0, Lmiui/app/ProgressDialog;->lL:Landroid/widget/ProgressBar;

    invoke-virtual {v0, p1}, Landroid/widget/ProgressBar;->setSecondaryProgress(I)V

    .line 280
    invoke-direct {p0}, Lmiui/app/ProgressDialog;->onProgressChanged()V

    .line 284
    :goto_0
    return-void

    .line 282
    :cond_0
    iput p1, p0, Lmiui/app/ProgressDialog;->lR:I

    goto :goto_0
.end method
