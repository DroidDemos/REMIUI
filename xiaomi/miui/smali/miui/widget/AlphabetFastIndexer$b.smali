.class Lmiui/widget/AlphabetFastIndexer$b;
.super Ljava/lang/Object;
.source "SourceFile"


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lmiui/widget/AlphabetFastIndexer;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0xa
    name = "b"
.end annotation


# instance fields
.field AA:Landroid/graphics/Canvas;

.field AB:Landroid/graphics/Rect;

.field AH:Landroid/graphics/Rect;

.field AJ:Landroid/graphics/Rect;

.field AK:Landroid/graphics/Xfermode;

.field AP:Landroid/graphics/Xfermode;

.field AQ:[Ljava/lang/String;

.field AV:I

.field AX:I

.field AY:I

.field Ay:Landroid/graphics/drawable/BitmapDrawable;

.field Az:Landroid/graphics/Bitmap;

.field aP:Landroid/graphics/Paint;

.field kC:Landroid/animation/ValueAnimator;


# direct methods
.method constructor <init>(Landroid/content/Context;Landroid/content/res/TypedArray;)V
    .locals 10

    .prologue
    const/4 v9, 0x0

    const/4 v8, 0x1

    const/4 v0, 0x0

    .line 139
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    .line 124
    new-instance v1, Landroid/graphics/Paint;

    invoke-direct {v1}, Landroid/graphics/Paint;-><init>()V

    iput-object v1, p0, Lmiui/widget/AlphabetFastIndexer$b;->aP:Landroid/graphics/Paint;

    .line 140
    new-instance v1, Landroid/graphics/Rect;

    invoke-direct {v1}, Landroid/graphics/Rect;-><init>()V

    iput-object v1, p0, Lmiui/widget/AlphabetFastIndexer$b;->AB:Landroid/graphics/Rect;

    .line 141
    new-instance v1, Landroid/graphics/Rect;

    invoke-direct {v1}, Landroid/graphics/Rect;-><init>()V

    iput-object v1, p0, Lmiui/widget/AlphabetFastIndexer$b;->AJ:Landroid/graphics/Rect;

    .line 142
    invoke-virtual {p1}, Landroid/content/Context;->getResources()Landroid/content/res/Resources;

    move-result-object v3

    .line 143
    invoke-virtual {p2, v0}, Landroid/content/res/TypedArray;->getTextArray(I)[Ljava/lang/CharSequence;

    move-result-object v4

    .line 144
    if-eqz v4, :cond_0

    .line 145
    array-length v1, v4

    new-array v1, v1, [Ljava/lang/String;

    iput-object v1, p0, Lmiui/widget/AlphabetFastIndexer$b;->AQ:[Ljava/lang/String;

    .line 147
    array-length v5, v4

    move v1, v0

    :goto_0
    if-ge v0, v5, :cond_1

    aget-object v6, v4, v0

    .line 148
    iget-object v7, p0, Lmiui/widget/AlphabetFastIndexer$b;->AQ:[Ljava/lang/String;

    add-int/lit8 v2, v1, 0x1

    invoke-virtual {v6}, Ljava/lang/Object;->toString()Ljava/lang/String;

    move-result-object v6

    aput-object v6, v7, v1

    .line 147
    add-int/lit8 v0, v0, 0x1

    move v1, v2

    goto :goto_0

    .line 151
    :cond_0
    sget v0, Lcom/miui/internal/R$array;->alphabet_table:I

    invoke-virtual {v3, v0}, Landroid/content/res/Resources;->getStringArray(I)[Ljava/lang/String;

    move-result-object v0

    iput-object v0, p0, Lmiui/widget/AlphabetFastIndexer$b;->AQ:[Ljava/lang/String;

    .line 154
    :cond_1
    const/4 v0, 0x2

    sget v1, Lcom/miui/internal/R$color;->alphabet_indexer_text_color:I

    invoke-virtual {v3, v1}, Landroid/content/res/Resources;->getColor(I)I

    move-result v1

    invoke-virtual {p2, v0, v1}, Landroid/content/res/TypedArray;->getColor(II)I

    move-result v0

    iput v0, p0, Lmiui/widget/AlphabetFastIndexer$b;->AV:I

    .line 156
    const/4 v0, 0x3

    sget v1, Lcom/miui/internal/R$color;->alphabet_indexer_activated_text_color:I

    invoke-virtual {v3, v1}, Landroid/content/res/Resources;->getColor(I)I

    move-result v1

    invoke-virtual {p2, v0, v1}, Landroid/content/res/TypedArray;->getColor(II)I

    move-result v0

    iput v0, p0, Lmiui/widget/AlphabetFastIndexer$b;->AX:I

    .line 158
    const/4 v0, 0x4

    sget v1, Lcom/miui/internal/R$color;->alphabet_indexer_highlight_text_color:I

    invoke-virtual {v3, v1}, Landroid/content/res/Resources;->getColor(I)I

    move-result v1

    invoke-virtual {p2, v0, v1}, Landroid/content/res/TypedArray;->getColor(II)I

    move-result v0

    iput v0, p0, Lmiui/widget/AlphabetFastIndexer$b;->AY:I

    .line 161
    iget-object v0, p0, Lmiui/widget/AlphabetFastIndexer$b;->aP:Landroid/graphics/Paint;

    sget v1, Lcom/miui/internal/R$dimen;->alphabet_indexer_text_size:I

    invoke-virtual {v3, v1}, Landroid/content/res/Resources;->getDimension(I)F

    move-result v1

    invoke-virtual {p2, v8, v1}, Landroid/content/res/TypedArray;->getDimension(IF)F

    move-result v1

    invoke-virtual {v0, v1}, Landroid/graphics/Paint;->setTextSize(F)V

    .line 163
    iget-object v0, p0, Lmiui/widget/AlphabetFastIndexer$b;->aP:Landroid/graphics/Paint;

    invoke-virtual {v0, v8}, Landroid/graphics/Paint;->setAntiAlias(Z)V

    .line 164
    iget-object v0, p0, Lmiui/widget/AlphabetFastIndexer$b;->aP:Landroid/graphics/Paint;

    sget-object v1, Landroid/graphics/Paint$Align;->CENTER:Landroid/graphics/Paint$Align;

    invoke-virtual {v0, v1}, Landroid/graphics/Paint;->setTextAlign(Landroid/graphics/Paint$Align;)V

    .line 165
    iget-object v0, p0, Lmiui/widget/AlphabetFastIndexer$b;->aP:Landroid/graphics/Paint;

    sget-object v1, Landroid/graphics/Typeface;->DEFAULT_BOLD:Landroid/graphics/Typeface;

    invoke-virtual {v0, v1}, Landroid/graphics/Paint;->setTypeface(Landroid/graphics/Typeface;)Landroid/graphics/Typeface;

    .line 167
    const/4 v0, 0x5

    invoke-virtual {p2, v0}, Landroid/content/res/TypedArray;->getDrawable(I)Landroid/graphics/drawable/Drawable;

    move-result-object v0

    .line 168
    if-nez v0, :cond_2

    .line 169
    sget v0, Lcom/miui/internal/R$drawable;->alphabet_indexer_text_highlight_bg:I

    invoke-virtual {v3, v0}, Landroid/content/res/Resources;->getDrawable(I)Landroid/graphics/drawable/Drawable;

    move-result-object v0

    .line 171
    :cond_2
    if-eqz v0, :cond_3

    instance-of v1, v0, Landroid/graphics/drawable/BitmapDrawable;

    if-eqz v1, :cond_3

    .line 172
    check-cast v0, Landroid/graphics/drawable/BitmapDrawable;

    iput-object v0, p0, Lmiui/widget/AlphabetFastIndexer$b;->Ay:Landroid/graphics/drawable/BitmapDrawable;

    .line 174
    iget-object v0, p0, Lmiui/widget/AlphabetFastIndexer$b;->Ay:Landroid/graphics/drawable/BitmapDrawable;

    invoke-virtual {v0}, Landroid/graphics/drawable/BitmapDrawable;->getBitmap()Landroid/graphics/Bitmap;

    move-result-object v0

    .line 175
    sget-object v1, Landroid/graphics/Bitmap$Config;->ARGB_8888:Landroid/graphics/Bitmap$Config;

    invoke-virtual {v0, v1, v8}, Landroid/graphics/Bitmap;->copy(Landroid/graphics/Bitmap$Config;Z)Landroid/graphics/Bitmap;

    move-result-object v0

    iput-object v0, p0, Lmiui/widget/AlphabetFastIndexer$b;->Az:Landroid/graphics/Bitmap;

    .line 176
    new-instance v0, Landroid/graphics/Canvas;

    iget-object v1, p0, Lmiui/widget/AlphabetFastIndexer$b;->Az:Landroid/graphics/Bitmap;

    invoke-direct {v0, v1}, Landroid/graphics/Canvas;-><init>(Landroid/graphics/Bitmap;)V

    iput-object v0, p0, Lmiui/widget/AlphabetFastIndexer$b;->AA:Landroid/graphics/Canvas;

    .line 178
    new-instance v0, Landroid/graphics/Rect;

    invoke-direct {v0}, Landroid/graphics/Rect;-><init>()V

    iput-object v0, p0, Lmiui/widget/AlphabetFastIndexer$b;->AH:Landroid/graphics/Rect;

    .line 180
    new-instance v0, Landroid/graphics/PorterDuffXfermode;

    sget-object v1, Landroid/graphics/PorterDuff$Mode;->CLEAR:Landroid/graphics/PorterDuff$Mode;

    invoke-direct {v0, v1}, Landroid/graphics/PorterDuffXfermode;-><init>(Landroid/graphics/PorterDuff$Mode;)V

    iput-object v0, p0, Lmiui/widget/AlphabetFastIndexer$b;->AK:Landroid/graphics/Xfermode;

    .line 181
    new-instance v0, Landroid/graphics/PorterDuffXfermode;

    sget-object v1, Landroid/graphics/PorterDuff$Mode;->DST_IN:Landroid/graphics/PorterDuff$Mode;

    invoke-direct {v0, v1}, Landroid/graphics/PorterDuffXfermode;-><init>(Landroid/graphics/PorterDuff$Mode;)V

    iput-object v0, p0, Lmiui/widget/AlphabetFastIndexer$b;->AP:Landroid/graphics/Xfermode;

    .line 184
    :cond_3
    invoke-virtual {p0, v9, v9}, Lmiui/widget/AlphabetFastIndexer$b;->b(FF)V

    .line 185
    return-void
.end method


# virtual methods
.method a(FLandroid/animation/ValueAnimator$AnimatorUpdateListener;)V
    .locals 3

    .prologue
    .line 257
    iget-object v0, p0, Lmiui/widget/AlphabetFastIndexer$b;->kC:Landroid/animation/ValueAnimator;

    if-eqz v0, :cond_0

    .line 258
    iget-object v0, p0, Lmiui/widget/AlphabetFastIndexer$b;->kC:Landroid/animation/ValueAnimator;

    invoke-virtual {v0}, Landroid/animation/ValueAnimator;->cancel()V

    .line 263
    :cond_0
    iget-object v0, p0, Lmiui/widget/AlphabetFastIndexer$b;->AB:Landroid/graphics/Rect;

    if-nez v0, :cond_1

    move v0, p1

    .line 270
    :goto_0
    const/4 v1, 0x2

    new-array v1, v1, [F

    const/4 v2, 0x0

    aput v0, v1, v2

    const/4 v0, 0x1

    aput p1, v1, v0

    invoke-static {v1}, Landroid/animation/ValueAnimator;->ofFloat([F)Landroid/animation/ValueAnimator;

    move-result-object v0

    iput-object v0, p0, Lmiui/widget/AlphabetFastIndexer$b;->kC:Landroid/animation/ValueAnimator;

    .line 271
    iget-object v0, p0, Lmiui/widget/AlphabetFastIndexer$b;->kC:Landroid/animation/ValueAnimator;

    invoke-virtual {v0, p2}, Landroid/animation/ValueAnimator;->addUpdateListener(Landroid/animation/ValueAnimator$AnimatorUpdateListener;)V

    .line 272
    iget-object v0, p0, Lmiui/widget/AlphabetFastIndexer$b;->kC:Landroid/animation/ValueAnimator;

    const-wide/16 v1, 0xc8

    invoke-virtual {v0, v1, v2}, Landroid/animation/ValueAnimator;->setDuration(J)Landroid/animation/ValueAnimator;

    .line 273
    iget-object v0, p0, Lmiui/widget/AlphabetFastIndexer$b;->kC:Landroid/animation/ValueAnimator;

    invoke-virtual {v0}, Landroid/animation/ValueAnimator;->start()V

    .line 274
    return-void

    .line 267
    :cond_1
    iget-object v0, p0, Lmiui/widget/AlphabetFastIndexer$b;->AB:Landroid/graphics/Rect;

    iget v0, v0, Landroid/graphics/Rect;->top:I

    iget-object v1, p0, Lmiui/widget/AlphabetFastIndexer$b;->AB:Landroid/graphics/Rect;

    iget v1, v1, Landroid/graphics/Rect;->bottom:I

    add-int/2addr v0, v1

    int-to-float v0, v0

    const/high16 v1, 0x40000000

    div-float/2addr v0, v1

    goto :goto_0
.end method

.method a(Landroid/graphics/Canvas;ZIFF)V
    .locals 8

    .prologue
    const/high16 v7, 0x40000000

    .line 199
    iget-object v2, p0, Lmiui/widget/AlphabetFastIndexer$b;->aP:Landroid/graphics/Paint;

    .line 202
    iget-object v0, p0, Lmiui/widget/AlphabetFastIndexer$b;->AQ:[Ljava/lang/String;

    aget-object v0, v0, p3

    const-string v1, "!"

    invoke-static {v0, v1}, Landroid/text/TextUtils;->equals(Ljava/lang/CharSequence;Ljava/lang/CharSequence;)Z

    move-result v0

    if-eqz v0, :cond_1

    const-string v0, "\u2605"

    .line 204
    :goto_0
    const/4 v1, 0x0

    invoke-virtual {v0}, Ljava/lang/String;->length()I

    move-result v3

    iget-object v4, p0, Lmiui/widget/AlphabetFastIndexer$b;->AJ:Landroid/graphics/Rect;

    invoke-virtual {v2, v0, v1, v3, v4}, Landroid/graphics/Paint;->getTextBounds(Ljava/lang/String;IILandroid/graphics/Rect;)V

    .line 206
    iget-object v1, p0, Lmiui/widget/AlphabetFastIndexer$b;->AJ:Landroid/graphics/Rect;

    invoke-virtual {v1}, Landroid/graphics/Rect;->width()I

    move-result v1

    int-to-float v3, v1

    .line 207
    iget-object v1, p0, Lmiui/widget/AlphabetFastIndexer$b;->AJ:Landroid/graphics/Rect;

    invoke-virtual {v1}, Landroid/graphics/Rect;->height()I

    move-result v1

    int-to-float v4, v1

    .line 210
    if-eqz p2, :cond_2

    iget v1, p0, Lmiui/widget/AlphabetFastIndexer$b;->AX:I

    :goto_1
    invoke-virtual {v2, v1}, Landroid/graphics/Paint;->setColor(I)V

    .line 211
    iget-object v1, p0, Lmiui/widget/AlphabetFastIndexer$b;->AJ:Landroid/graphics/Rect;

    iget v1, v1, Landroid/graphics/Rect;->top:I

    iget-object v5, p0, Lmiui/widget/AlphabetFastIndexer$b;->AJ:Landroid/graphics/Rect;

    iget v5, v5, Landroid/graphics/Rect;->bottom:I

    add-int/2addr v1, v5

    int-to-float v1, v1

    div-float/2addr v1, v7

    sub-float v1, p5, v1

    invoke-virtual {p1, v0, p4, v1, v2}, Landroid/graphics/Canvas;->drawText(Ljava/lang/String;FFLandroid/graphics/Paint;)V

    .line 214
    iget-object v1, p0, Lmiui/widget/AlphabetFastIndexer$b;->AH:Landroid/graphics/Rect;

    div-float v5, v3, v7

    sub-float v5, p4, v5

    float-to-int v5, v5

    div-float v6, v4, v7

    sub-float v6, p5, v6

    float-to-int v6, v6

    div-float/2addr v3, v7

    add-float/2addr v3, p4

    float-to-int v3, v3

    div-float/2addr v4, v7

    add-float/2addr v4, p5

    float-to-int v4, v4

    invoke-virtual {v1, v5, v6, v3, v4}, Landroid/graphics/Rect;->intersect(IIII)Z

    move-result v1

    if-eqz v1, :cond_0

    .line 217
    iget-object v1, p0, Lmiui/widget/AlphabetFastIndexer$b;->AB:Landroid/graphics/Rect;

    iget v1, v1, Landroid/graphics/Rect;->left:I

    int-to-float v1, v1

    sub-float v1, p4, v1

    .line 218
    iget-object v3, p0, Lmiui/widget/AlphabetFastIndexer$b;->AB:Landroid/graphics/Rect;

    iget v3, v3, Landroid/graphics/Rect;->top:I

    int-to-float v3, v3

    sub-float v3, p5, v3

    .line 220
    iget v4, p0, Lmiui/widget/AlphabetFastIndexer$b;->AY:I

    invoke-virtual {v2, v4}, Landroid/graphics/Paint;->setColor(I)V

    .line 221
    iget-object v4, p0, Lmiui/widget/AlphabetFastIndexer$b;->AA:Landroid/graphics/Canvas;

    iget-object v5, p0, Lmiui/widget/AlphabetFastIndexer$b;->AJ:Landroid/graphics/Rect;

    iget v5, v5, Landroid/graphics/Rect;->top:I

    iget-object v6, p0, Lmiui/widget/AlphabetFastIndexer$b;->AJ:Landroid/graphics/Rect;

    iget v6, v6, Landroid/graphics/Rect;->bottom:I

    add-int/2addr v5, v6

    int-to-float v5, v5

    div-float/2addr v5, v7

    sub-float/2addr v3, v5

    invoke-virtual {v4, v0, v1, v3, v2}, Landroid/graphics/Canvas;->drawText(Ljava/lang/String;FFLandroid/graphics/Paint;)V

    .line 223
    iget-object v0, p0, Lmiui/widget/AlphabetFastIndexer$b;->AH:Landroid/graphics/Rect;

    iget-object v1, p0, Lmiui/widget/AlphabetFastIndexer$b;->AB:Landroid/graphics/Rect;

    invoke-virtual {v0, v1}, Landroid/graphics/Rect;->set(Landroid/graphics/Rect;)V

    .line 225
    :cond_0
    return-void

    .line 202
    :cond_1
    iget-object v0, p0, Lmiui/widget/AlphabetFastIndexer$b;->AQ:[Ljava/lang/String;

    aget-object v0, v0, p3

    goto :goto_0

    .line 210
    :cond_2
    iget v1, p0, Lmiui/widget/AlphabetFastIndexer$b;->AV:I

    goto :goto_1
.end method

.method b(FF)V
    .locals 6

    .prologue
    const/high16 v2, 0x40000000

    const/high16 v5, 0x3f800000

    .line 188
    iget-object v0, p0, Lmiui/widget/AlphabetFastIndexer$b;->Ay:Landroid/graphics/drawable/BitmapDrawable;

    invoke-virtual {v0}, Landroid/graphics/drawable/BitmapDrawable;->getIntrinsicWidth()I

    move-result v0

    int-to-float v0, v0

    div-float/2addr v0, v2

    .line 189
    iget-object v1, p0, Lmiui/widget/AlphabetFastIndexer$b;->Ay:Landroid/graphics/drawable/BitmapDrawable;

    invoke-virtual {v1}, Landroid/graphics/drawable/BitmapDrawable;->getIntrinsicHeight()I

    move-result v1

    int-to-float v1, v1

    div-float/2addr v1, v2

    .line 190
    iget-object v2, p0, Lmiui/widget/AlphabetFastIndexer$b;->AB:Landroid/graphics/Rect;

    sub-float v3, p1, v0

    add-float/2addr v3, v5

    float-to-int v3, v3

    sub-float v4, p2, v1

    float-to-int v4, v4

    add-float/2addr v0, p1

    add-float/2addr v0, v5

    float-to-int v0, v0

    add-float/2addr v1, p2

    float-to-int v1, v1

    invoke-virtual {v2, v3, v4, v0, v1}, Landroid/graphics/Rect;->set(IIII)V

    .line 194
    return-void
.end method

.method cL()V
    .locals 4

    .prologue
    const/4 v3, 0x0

    .line 228
    iget-object v0, p0, Lmiui/widget/AlphabetFastIndexer$b;->aP:Landroid/graphics/Paint;

    .line 230
    invoke-virtual {v0}, Landroid/graphics/Paint;->getXfermode()Landroid/graphics/Xfermode;

    move-result-object v1

    .line 231
    iget-object v2, p0, Lmiui/widget/AlphabetFastIndexer$b;->AK:Landroid/graphics/Xfermode;

    invoke-virtual {v0, v2}, Landroid/graphics/Paint;->setXfermode(Landroid/graphics/Xfermode;)Landroid/graphics/Xfermode;

    .line 232
    iget-object v2, p0, Lmiui/widget/AlphabetFastIndexer$b;->AA:Landroid/graphics/Canvas;

    invoke-virtual {v2, v0}, Landroid/graphics/Canvas;->drawPaint(Landroid/graphics/Paint;)V

    .line 233
    invoke-virtual {v0, v1}, Landroid/graphics/Paint;->setXfermode(Landroid/graphics/Xfermode;)Landroid/graphics/Xfermode;

    .line 236
    iget-object v0, p0, Lmiui/widget/AlphabetFastIndexer$b;->Ay:Landroid/graphics/drawable/BitmapDrawable;

    iget-object v1, p0, Lmiui/widget/AlphabetFastIndexer$b;->AB:Landroid/graphics/Rect;

    invoke-virtual {v1}, Landroid/graphics/Rect;->width()I

    move-result v1

    iget-object v2, p0, Lmiui/widget/AlphabetFastIndexer$b;->AB:Landroid/graphics/Rect;

    invoke-virtual {v2}, Landroid/graphics/Rect;->height()I

    move-result v2

    invoke-virtual {v0, v3, v3, v1, v2}, Landroid/graphics/drawable/BitmapDrawable;->setBounds(IIII)V

    .line 237
    iget-object v0, p0, Lmiui/widget/AlphabetFastIndexer$b;->Ay:Landroid/graphics/drawable/BitmapDrawable;

    iget-object v1, p0, Lmiui/widget/AlphabetFastIndexer$b;->AA:Landroid/graphics/Canvas;

    invoke-virtual {v0, v1}, Landroid/graphics/drawable/BitmapDrawable;->draw(Landroid/graphics/Canvas;)V

    .line 239
    iget-object v0, p0, Lmiui/widget/AlphabetFastIndexer$b;->AH:Landroid/graphics/Rect;

    iget-object v1, p0, Lmiui/widget/AlphabetFastIndexer$b;->AB:Landroid/graphics/Rect;

    invoke-virtual {v0, v1}, Landroid/graphics/Rect;->set(Landroid/graphics/Rect;)V

    .line 241
    return-void
.end method

.method g(Landroid/graphics/Canvas;)V
    .locals 5

    .prologue
    const/4 v4, 0x0

    .line 246
    iget-object v0, p0, Lmiui/widget/AlphabetFastIndexer$b;->Ay:Landroid/graphics/drawable/BitmapDrawable;

    invoke-virtual {v0}, Landroid/graphics/drawable/BitmapDrawable;->getPaint()Landroid/graphics/Paint;

    move-result-object v0

    .line 247
    invoke-virtual {v0}, Landroid/graphics/Paint;->getXfermode()Landroid/graphics/Xfermode;

    move-result-object v1

    .line 248
    iget-object v2, p0, Lmiui/widget/AlphabetFastIndexer$b;->AP:Landroid/graphics/Xfermode;

    invoke-virtual {v0, v2}, Landroid/graphics/Paint;->setXfermode(Landroid/graphics/Xfermode;)Landroid/graphics/Xfermode;

    .line 249
    iget-object v2, p0, Lmiui/widget/AlphabetFastIndexer$b;->Ay:Landroid/graphics/drawable/BitmapDrawable;

    iget-object v3, p0, Lmiui/widget/AlphabetFastIndexer$b;->AA:Landroid/graphics/Canvas;

    invoke-virtual {v2, v3}, Landroid/graphics/drawable/BitmapDrawable;->draw(Landroid/graphics/Canvas;)V

    .line 250
    invoke-virtual {v0, v1}, Landroid/graphics/Paint;->setXfermode(Landroid/graphics/Xfermode;)Landroid/graphics/Xfermode;

    .line 253
    iget-object v0, p0, Lmiui/widget/AlphabetFastIndexer$b;->Az:Landroid/graphics/Bitmap;

    iget-object v1, p0, Lmiui/widget/AlphabetFastIndexer$b;->AB:Landroid/graphics/Rect;

    invoke-virtual {p1, v0, v4, v1, v4}, Landroid/graphics/Canvas;->drawBitmap(Landroid/graphics/Bitmap;Landroid/graphics/Rect;Landroid/graphics/Rect;Landroid/graphics/Paint;)V

    .line 254
    return-void
.end method
