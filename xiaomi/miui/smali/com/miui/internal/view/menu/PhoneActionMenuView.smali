.class public Lcom/miui/internal/view/menu/PhoneActionMenuView;
.super Lcom/miui/internal/view/menu/ActionMenuView;
.source "SourceFile"


# annotations
.annotation system Ldalvik/annotation/MemberClasses;
    value = {
        Lcom/miui/internal/view/menu/PhoneActionMenuView$1;,
        Lcom/miui/internal/view/menu/PhoneActionMenuView$a;,
        Lcom/miui/internal/view/menu/PhoneActionMenuView$OverflowMenuState;
    }
.end annotation


# static fields
.field private static final Kj:I = 0x2

.field private static final Kk:[I


# instance fields
.field private Kl:Landroid/view/View;

.field private Km:Lcom/miui/internal/view/menu/PhoneActionMenuView$a;

.field private Kn:Lcom/miui/internal/view/menu/PhoneActionMenuView$OverflowMenuState;

.field private Ko:Landroid/graphics/drawable/Drawable;

.field private Kp:Landroid/graphics/drawable/Drawable;

.field private Kq:Ljava/lang/ref/WeakReference;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Ljava/lang/ref/WeakReference",
            "<",
            "Landroid/graphics/Bitmap;",
            ">;"
        }
    .end annotation
.end field

.field private Kr:Landroid/graphics/drawable/Drawable;

.field private Ks:I

.field private Kt:I

.field private Ku:I

.field private Kv:Landroid/graphics/Rect;

.field private Kw:I

.field private Kx:Z


# direct methods
.method static constructor <clinit>()V
    .locals 3

    .prologue
    .line 34
    const/4 v0, 0x2

    new-array v0, v0, [I

    const/4 v1, 0x0

    sget v2, Lcom/miui/internal/R$attr;->expandBackground:I

    aput v2, v0, v1

    const/4 v1, 0x1

    sget v2, Lcom/miui/internal/R$attr;->splitActionBarOverlayHeight:I

    aput v2, v0, v1

    sput-object v0, Lcom/miui/internal/view/menu/PhoneActionMenuView;->Kk:[I

    return-void
.end method

.method public constructor <init>(Landroid/content/Context;)V
    .locals 1

    .prologue
    .line 73
    const/4 v0, 0x0

    invoke-direct {p0, p1, v0}, Lcom/miui/internal/view/menu/PhoneActionMenuView;-><init>(Landroid/content/Context;Landroid/util/AttributeSet;)V

    .line 74
    return-void
.end method

.method public constructor <init>(Landroid/content/Context;Landroid/util/AttributeSet;)V
    .locals 3

    .prologue
    const/4 v2, 0x0

    .line 77
    invoke-direct {p0, p1, p2}, Lcom/miui/internal/view/menu/ActionMenuView;-><init>(Landroid/content/Context;Landroid/util/AttributeSet;)V

    .line 50
    sget-object v0, Lcom/miui/internal/view/menu/PhoneActionMenuView$OverflowMenuState;->As:Lcom/miui/internal/view/menu/PhoneActionMenuView$OverflowMenuState;

    iput-object v0, p0, Lcom/miui/internal/view/menu/PhoneActionMenuView;->Kn:Lcom/miui/internal/view/menu/PhoneActionMenuView$OverflowMenuState;

    .line 78
    invoke-virtual {p0}, Lcom/miui/internal/view/menu/PhoneActionMenuView;->getBackground()Landroid/graphics/drawable/Drawable;

    move-result-object v0

    iput-object v0, p0, Lcom/miui/internal/view/menu/PhoneActionMenuView;->Kr:Landroid/graphics/drawable/Drawable;

    .line 79
    sget-object v0, Lcom/miui/internal/view/menu/PhoneActionMenuView;->Kk:[I

    invoke-virtual {p1, p2, v0}, Landroid/content/Context;->obtainStyledAttributes(Landroid/util/AttributeSet;[I)Landroid/content/res/TypedArray;

    move-result-object v0

    .line 80
    invoke-virtual {v0, v2}, Landroid/content/res/TypedArray;->getDrawable(I)Landroid/graphics/drawable/Drawable;

    move-result-object v1

    iput-object v1, p0, Lcom/miui/internal/view/menu/PhoneActionMenuView;->Ko:Landroid/graphics/drawable/Drawable;

    .line 81
    const/4 v1, 0x1

    invoke-virtual {v0, v1, v2}, Landroid/content/res/TypedArray;->getDimensionPixelSize(II)I

    move-result v1

    iput v1, p0, Lcom/miui/internal/view/menu/PhoneActionMenuView;->Kw:I

    .line 82
    invoke-virtual {v0}, Landroid/content/res/TypedArray;->recycle()V

    .line 84
    sget v0, Lcom/miui/internal/R$attr;->actionMenuBlurEnabled:I

    invoke-static {p1, v0, v2}, Lmiui/util/AttributeResolver;->resolveBoolean(Landroid/content/Context;IZ)Z

    move-result v0

    iput-boolean v0, p0, Lcom/miui/internal/view/menu/PhoneActionMenuView;->Kx:Z

    .line 86
    invoke-direct {p0}, Lcom/miui/internal/view/menu/PhoneActionMenuView;->ea()V

    .line 87
    return-void
.end method

.method static synthetic a(Lcom/miui/internal/view/menu/PhoneActionMenuView;)Landroid/view/View;
    .locals 1

    .prologue
    .line 30
    iget-object v0, p0, Lcom/miui/internal/view/menu/PhoneActionMenuView;->Kl:Landroid/view/View;

    return-object v0
.end method

.method static synthetic a(Lcom/miui/internal/view/menu/PhoneActionMenuView;Lcom/miui/internal/view/menu/PhoneActionMenuView$OverflowMenuState;)Lcom/miui/internal/view/menu/PhoneActionMenuView$OverflowMenuState;
    .locals 0

    .prologue
    .line 30
    iput-object p1, p0, Lcom/miui/internal/view/menu/PhoneActionMenuView;->Kn:Lcom/miui/internal/view/menu/PhoneActionMenuView$OverflowMenuState;

    return-object p1
.end method

.method static synthetic b(Lcom/miui/internal/view/menu/PhoneActionMenuView;)Lcom/miui/internal/view/menu/PhoneActionMenuView$OverflowMenuState;
    .locals 1

    .prologue
    .line 30
    iget-object v0, p0, Lcom/miui/internal/view/menu/PhoneActionMenuView;->Kn:Lcom/miui/internal/view/menu/PhoneActionMenuView$OverflowMenuState;

    return-object v0
.end method

.method private dX()V
    .locals 7

    .prologue
    .line 152
    iget-boolean v0, p0, Lcom/miui/internal/view/menu/PhoneActionMenuView;->Kx:Z

    if-nez v0, :cond_1

    .line 198
    :cond_0
    :goto_0
    return-void

    .line 156
    :cond_1
    invoke-virtual {p0}, Lcom/miui/internal/view/menu/PhoneActionMenuView;->getContext()Landroid/content/Context;

    move-result-object v0

    sget v1, Lcom/miui/internal/R$attr;->immersionBlurMask:I

    invoke-static {v0, v1}, Lmiui/util/AttributeResolver;->resolveDrawable(Landroid/content/Context;I)Landroid/graphics/drawable/Drawable;

    move-result-object v1

    .line 158
    invoke-virtual {p0}, Lcom/miui/internal/view/menu/PhoneActionMenuView;->getRootView()Landroid/view/View;

    move-result-object v2

    .line 159
    if-eqz v2, :cond_0

    .line 163
    const/4 v0, 0x0

    .line 165
    :try_start_0
    invoke-direct {p0}, Lcom/miui/internal/view/menu/PhoneActionMenuView;->dY()I

    move-result v3

    .line 166
    const/4 v4, -0x1

    if-eq v3, v4, :cond_0

    .line 169
    iget-object v3, p0, Lcom/miui/internal/view/menu/PhoneActionMenuView;->Kq:Ljava/lang/ref/WeakReference;

    if-eqz v3, :cond_2

    .line 170
    iget-object v0, p0, Lcom/miui/internal/view/menu/PhoneActionMenuView;->Kq:Ljava/lang/ref/WeakReference;

    invoke-virtual {v0}, Ljava/lang/ref/WeakReference;->get()Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Landroid/graphics/Bitmap;

    .line 172
    :cond_2
    if-eqz v0, :cond_3

    invoke-virtual {v0}, Landroid/graphics/Bitmap;->getWidth()I

    move-result v3

    invoke-virtual {p0}, Lcom/miui/internal/view/menu/PhoneActionMenuView;->getWidth()I

    move-result v4

    if-ne v3, v4, :cond_3

    invoke-virtual {v0}, Landroid/graphics/Bitmap;->getHeight()I

    move-result v3

    invoke-virtual {p0}, Lcom/miui/internal/view/menu/PhoneActionMenuView;->getHeight()I

    move-result v4

    if-eq v3, v4, :cond_5

    .line 174
    :cond_3
    if-eqz v0, :cond_4

    .line 175
    invoke-virtual {v0}, Landroid/graphics/Bitmap;->recycle()V

    .line 178
    :cond_4
    invoke-virtual {p0}, Lcom/miui/internal/view/menu/PhoneActionMenuView;->getWidth()I

    move-result v0

    invoke-virtual {p0}, Lcom/miui/internal/view/menu/PhoneActionMenuView;->getHeight()I

    move-result v3

    sget-object v4, Landroid/graphics/Bitmap$Config;->ARGB_8888:Landroid/graphics/Bitmap$Config;

    invoke-static {v0, v3, v4}, Landroid/graphics/Bitmap;->createBitmap(IILandroid/graphics/Bitmap$Config;)Landroid/graphics/Bitmap;

    move-result-object v0

    .line 179
    new-instance v3, Ljava/lang/ref/WeakReference;

    invoke-direct {v3, v0}, Ljava/lang/ref/WeakReference;-><init>(Ljava/lang/Object;)V

    iput-object v3, p0, Lcom/miui/internal/view/menu/PhoneActionMenuView;->Kq:Ljava/lang/ref/WeakReference;

    .line 181
    :cond_5
    new-instance v3, Landroid/graphics/Canvas;

    invoke-direct {v3, v0}, Landroid/graphics/Canvas;-><init>(Landroid/graphics/Bitmap;)V

    .line 182
    const/4 v4, 0x0

    invoke-direct {p0}, Lcom/miui/internal/view/menu/PhoneActionMenuView;->dY()I

    move-result v5

    neg-int v5, v5

    int-to-float v5, v5

    invoke-virtual {v3, v4, v5}, Landroid/graphics/Canvas;->translate(FF)V

    .line 183
    invoke-virtual {v2, v3}, Landroid/view/View;->draw(Landroid/graphics/Canvas;)V

    .line 184
    invoke-virtual {v0}, Landroid/graphics/Bitmap;->getWidth()I

    move-result v2

    .line 185
    invoke-virtual {v0}, Landroid/graphics/Bitmap;->getHeight()I

    move-result v3

    .line 186
    div-int/lit8 v4, v2, 0x2

    div-int/lit8 v5, v3, 0x2

    const/4 v6, 0x0

    invoke-static {v0, v4, v5, v6}, Landroid/graphics/Bitmap;->createScaledBitmap(Landroid/graphics/Bitmap;IIZ)Landroid/graphics/Bitmap;

    move-result-object v0

    .line 187
    invoke-virtual {p0}, Lcom/miui/internal/view/menu/PhoneActionMenuView;->getContext()Landroid/content/Context;

    move-result-object v4

    invoke-virtual {v4}, Landroid/content/Context;->getResources()Landroid/content/res/Resources;

    move-result-object v4

    sget v5, Lcom/miui/internal/R$dimen;->screenshot_blur_radius:I

    invoke-virtual {v4, v5}, Landroid/content/res/Resources;->getDimensionPixelSize(I)I

    move-result v4

    .line 189
    invoke-static {v0, v4}, Lmiui/graphics/BitmapFactory;->fastBlur(Landroid/graphics/Bitmap;I)Landroid/graphics/Bitmap;

    move-result-object v0

    .line 190
    const/4 v4, 0x0

    invoke-static {v0, v2, v3, v4}, Landroid/graphics/Bitmap;->createScaledBitmap(Landroid/graphics/Bitmap;IIZ)Landroid/graphics/Bitmap;

    move-result-object v0

    .line 191
    new-instance v2, Landroid/graphics/Canvas;

    invoke-direct {v2, v0}, Landroid/graphics/Canvas;-><init>(Landroid/graphics/Bitmap;)V

    .line 192
    const/4 v3, 0x0

    const/4 v4, 0x0

    invoke-virtual {v0}, Landroid/graphics/Bitmap;->getWidth()I

    move-result v5

    invoke-virtual {v0}, Landroid/graphics/Bitmap;->getHeight()I

    move-result v6

    invoke-virtual {v1, v3, v4, v5, v6}, Landroid/graphics/drawable/Drawable;->setBounds(IIII)V

    .line 193
    invoke-virtual {v1, v2}, Landroid/graphics/drawable/Drawable;->draw(Landroid/graphics/Canvas;)V

    .line 194
    new-instance v1, Landroid/graphics/drawable/BitmapDrawable;

    invoke-virtual {p0}, Lcom/miui/internal/view/menu/PhoneActionMenuView;->getContext()Landroid/content/Context;

    move-result-object v2

    invoke-virtual {v2}, Landroid/content/Context;->getResources()Landroid/content/res/Resources;

    move-result-object v2

    invoke-direct {v1, v2, v0}, Landroid/graphics/drawable/BitmapDrawable;-><init>(Landroid/content/res/Resources;Landroid/graphics/Bitmap;)V

    iput-object v1, p0, Lcom/miui/internal/view/menu/PhoneActionMenuView;->Kp:Landroid/graphics/drawable/Drawable;
    :try_end_0
    .catch Ljava/lang/OutOfMemoryError; {:try_start_0 .. :try_end_0} :catch_0

    goto/16 :goto_0

    .line 195
    :catch_0
    move-exception v0

    goto/16 :goto_0
.end method

.method private dY()I
    .locals 4

    .prologue
    .line 201
    invoke-virtual {p0}, Lcom/miui/internal/view/menu/PhoneActionMenuView;->getTop()I

    move-result v1

    .line 202
    invoke-virtual {p0}, Lcom/miui/internal/view/menu/PhoneActionMenuView;->getRootView()Landroid/view/View;

    move-result-object v2

    .line 203
    if-nez v2, :cond_0

    .line 204
    const/4 v0, -0x1

    .line 212
    :goto_0
    return v0

    .line 206
    :cond_0
    invoke-virtual {p0}, Lcom/miui/internal/view/menu/PhoneActionMenuView;->getParent()Landroid/view/ViewParent;

    move-result-object v0

    .line 207
    :goto_1
    if-eqz v0, :cond_1

    instance-of v3, v0, Landroid/view/View;

    if-eqz v3, :cond_1

    if-eq v0, v2, :cond_1

    .line 208
    check-cast v0, Landroid/view/View;

    .line 209
    invoke-virtual {v0}, Landroid/view/View;->getTop()I

    move-result v3

    add-int/2addr v1, v3

    .line 210
    invoke-virtual {v0}, Landroid/view/View;->getParent()Landroid/view/ViewParent;

    move-result-object v0

    goto :goto_1

    :cond_1
    move v0, v1

    .line 212
    goto :goto_0
.end method

.method private dZ()Lcom/miui/internal/view/menu/PhoneActionMenuView$a;
    .locals 2

    .prologue
    .line 236
    iget-object v0, p0, Lcom/miui/internal/view/menu/PhoneActionMenuView;->Km:Lcom/miui/internal/view/menu/PhoneActionMenuView$a;

    if-nez v0, :cond_0

    .line 237
    new-instance v0, Lcom/miui/internal/view/menu/PhoneActionMenuView$a;

    const/4 v1, 0x0

    invoke-direct {v0, p0, v1}, Lcom/miui/internal/view/menu/PhoneActionMenuView$a;-><init>(Lcom/miui/internal/view/menu/PhoneActionMenuView;Lcom/miui/internal/view/menu/PhoneActionMenuView$1;)V

    iput-object v0, p0, Lcom/miui/internal/view/menu/PhoneActionMenuView;->Km:Lcom/miui/internal/view/menu/PhoneActionMenuView$a;

    .line 239
    :cond_0
    iget-object v0, p0, Lcom/miui/internal/view/menu/PhoneActionMenuView;->Km:Lcom/miui/internal/view/menu/PhoneActionMenuView$a;

    return-object v0
.end method

.method private ea()V
    .locals 3

    .prologue
    const/4 v2, 0x0

    .line 359
    iget-object v0, p0, Lcom/miui/internal/view/menu/PhoneActionMenuView;->Kl:Landroid/view/View;

    if-nez v0, :cond_1

    iget-object v0, p0, Lcom/miui/internal/view/menu/PhoneActionMenuView;->Kr:Landroid/graphics/drawable/Drawable;

    .line 360
    :goto_0
    if-nez v0, :cond_2

    .line 361
    const/4 v0, 0x0

    iput-object v0, p0, Lcom/miui/internal/view/menu/PhoneActionMenuView;->Kv:Landroid/graphics/Rect;

    .line 362
    iput v2, p0, Lcom/miui/internal/view/menu/PhoneActionMenuView;->Ku:I

    .line 374
    :cond_0
    :goto_1
    return-void

    .line 359
    :cond_1
    iget-object v0, p0, Lcom/miui/internal/view/menu/PhoneActionMenuView;->Ko:Landroid/graphics/drawable/Drawable;

    goto :goto_0

    .line 365
    :cond_2
    iget-object v1, p0, Lcom/miui/internal/view/menu/PhoneActionMenuView;->Kv:Landroid/graphics/Rect;

    if-nez v1, :cond_3

    .line 366
    new-instance v1, Landroid/graphics/Rect;

    invoke-direct {v1}, Landroid/graphics/Rect;-><init>()V

    iput-object v1, p0, Lcom/miui/internal/view/menu/PhoneActionMenuView;->Kv:Landroid/graphics/Rect;

    .line 368
    :cond_3
    iget-object v1, p0, Lcom/miui/internal/view/menu/PhoneActionMenuView;->Kv:Landroid/graphics/Rect;

    invoke-virtual {v0, v1}, Landroid/graphics/drawable/Drawable;->getPadding(Landroid/graphics/Rect;)Z

    .line 370
    invoke-virtual {v0}, Landroid/graphics/drawable/Drawable;->getIntrinsicHeight()I

    move-result v0

    iput v0, p0, Lcom/miui/internal/view/menu/PhoneActionMenuView;->Ku:I

    .line 371
    iget v0, p0, Lcom/miui/internal/view/menu/PhoneActionMenuView;->Ku:I

    const/4 v1, -0x1

    if-ne v0, v1, :cond_0

    .line 372
    iput v2, p0, Lcom/miui/internal/view/menu/PhoneActionMenuView;->Ku:I

    goto :goto_1
.end method


# virtual methods
.method public filterLeftoverView(I)Z
    .locals 4

    .prologue
    const/4 v2, 0x1

    const/4 v1, 0x0

    .line 101
    invoke-virtual {p0, p1}, Lcom/miui/internal/view/menu/PhoneActionMenuView;->getChildAt(I)Landroid/view/View;

    move-result-object v0

    .line 102
    iget-object v3, p0, Lcom/miui/internal/view/menu/PhoneActionMenuView;->Kl:Landroid/view/View;

    if-ne v0, v3, :cond_0

    move v0, v1

    .line 108
    :goto_0
    if-eqz v0, :cond_3

    invoke-super {p0, p1}, Lcom/miui/internal/view/menu/ActionMenuView;->filterLeftoverView(I)Z

    move-result v0

    if-eqz v0, :cond_3

    :goto_1
    return v2

    .line 105
    :cond_0
    invoke-virtual {v0}, Landroid/view/View;->getLayoutParams()Landroid/view/ViewGroup$LayoutParams;

    move-result-object v0

    check-cast v0, Lcom/miui/internal/view/menu/ActionMenuView$LayoutParams;

    .line 106
    if-eqz v0, :cond_1

    iget-boolean v0, v0, Lcom/miui/internal/view/menu/ActionMenuView$LayoutParams;->isOverflowButton:Z

    if-nez v0, :cond_2

    :cond_1
    move v0, v2

    goto :goto_0

    :cond_2
    move v0, v1

    goto :goto_0

    :cond_3
    move v2, v1

    .line 108
    goto :goto_1
.end method

.method public getCollapsedHeight()I
    .locals 2

    .prologue
    .line 244
    iget v0, p0, Lcom/miui/internal/view/menu/PhoneActionMenuView;->Kt:I

    if-nez v0, :cond_0

    .line 245
    const/4 v0, 0x0

    .line 247
    :goto_0
    return v0

    :cond_0
    iget v0, p0, Lcom/miui/internal/view/menu/PhoneActionMenuView;->Kt:I

    iget v1, p0, Lcom/miui/internal/view/menu/PhoneActionMenuView;->Ku:I

    invoke-static {v0, v1}, Ljava/lang/Math;->max(II)I

    move-result v0

    iget v1, p0, Lcom/miui/internal/view/menu/PhoneActionMenuView;->Kw:I

    sub-int/2addr v0, v1

    goto :goto_0
.end method

.method public hideOverflowMenu()Z
    .locals 3

    .prologue
    .line 220
    iget-object v0, p0, Lcom/miui/internal/view/menu/PhoneActionMenuView;->Kn:Lcom/miui/internal/view/menu/PhoneActionMenuView$OverflowMenuState;

    .line 221
    sget-object v1, Lcom/miui/internal/view/menu/PhoneActionMenuView$OverflowMenuState;->Av:Lcom/miui/internal/view/menu/PhoneActionMenuView$OverflowMenuState;

    if-eq v0, v1, :cond_0

    sget-object v1, Lcom/miui/internal/view/menu/PhoneActionMenuView$OverflowMenuState;->As:Lcom/miui/internal/view/menu/PhoneActionMenuView$OverflowMenuState;

    if-ne v0, v1, :cond_1

    .line 222
    :cond_0
    const/4 v0, 0x0

    .line 232
    :goto_0
    return v0

    .line 225
    :cond_1
    invoke-direct {p0}, Lcom/miui/internal/view/menu/PhoneActionMenuView;->dZ()Lcom/miui/internal/view/menu/PhoneActionMenuView$a;

    move-result-object v1

    .line 226
    sget-object v2, Lcom/miui/internal/view/menu/PhoneActionMenuView$OverflowMenuState;->Au:Lcom/miui/internal/view/menu/PhoneActionMenuView$OverflowMenuState;

    if-ne v0, v2, :cond_3

    .line 227
    sget-object v0, Lcom/miui/internal/view/menu/PhoneActionMenuView$OverflowMenuState;->Av:Lcom/miui/internal/view/menu/PhoneActionMenuView$OverflowMenuState;

    iput-object v0, p0, Lcom/miui/internal/view/menu/PhoneActionMenuView;->Kn:Lcom/miui/internal/view/menu/PhoneActionMenuView$OverflowMenuState;

    .line 228
    invoke-virtual {v1}, Lcom/miui/internal/view/menu/PhoneActionMenuView$a;->hide()V

    .line 232
    :cond_2
    :goto_1
    const/4 v0, 0x1

    goto :goto_0

    .line 229
    :cond_3
    sget-object v2, Lcom/miui/internal/view/menu/PhoneActionMenuView$OverflowMenuState;->At:Lcom/miui/internal/view/menu/PhoneActionMenuView$OverflowMenuState;

    if-ne v0, v2, :cond_2

    .line 230
    invoke-virtual {v1}, Lcom/miui/internal/view/menu/PhoneActionMenuView$a;->reverse()V

    goto :goto_1
.end method

.method public isOverflowMenuShowing()Z
    .locals 2

    .prologue
    .line 216
    iget-object v0, p0, Lcom/miui/internal/view/menu/PhoneActionMenuView;->Kn:Lcom/miui/internal/view/menu/PhoneActionMenuView$OverflowMenuState;

    sget-object v1, Lcom/miui/internal/view/menu/PhoneActionMenuView$OverflowMenuState;->At:Lcom/miui/internal/view/menu/PhoneActionMenuView$OverflowMenuState;

    if-eq v0, v1, :cond_0

    iget-object v0, p0, Lcom/miui/internal/view/menu/PhoneActionMenuView;->Kn:Lcom/miui/internal/view/menu/PhoneActionMenuView$OverflowMenuState;

    sget-object v1, Lcom/miui/internal/view/menu/PhoneActionMenuView$OverflowMenuState;->Au:Lcom/miui/internal/view/menu/PhoneActionMenuView$OverflowMenuState;

    if-ne v0, v1, :cond_1

    :cond_0
    const/4 v0, 0x1

    :goto_0
    return v0

    :cond_1
    const/4 v0, 0x0

    goto :goto_0
.end method

.method protected onDraw(Landroid/graphics/Canvas;)V
    .locals 5

    .prologue
    const/4 v1, 0x0

    .line 325
    iget-object v0, p0, Lcom/miui/internal/view/menu/PhoneActionMenuView;->Kn:Lcom/miui/internal/view/menu/PhoneActionMenuView$OverflowMenuState;

    sget-object v2, Lcom/miui/internal/view/menu/PhoneActionMenuView$OverflowMenuState;->Au:Lcom/miui/internal/view/menu/PhoneActionMenuView$OverflowMenuState;

    if-eq v0, v2, :cond_0

    iget-object v0, p0, Lcom/miui/internal/view/menu/PhoneActionMenuView;->Kn:Lcom/miui/internal/view/menu/PhoneActionMenuView$OverflowMenuState;

    sget-object v2, Lcom/miui/internal/view/menu/PhoneActionMenuView$OverflowMenuState;->At:Lcom/miui/internal/view/menu/PhoneActionMenuView$OverflowMenuState;

    if-eq v0, v2, :cond_0

    iget-object v0, p0, Lcom/miui/internal/view/menu/PhoneActionMenuView;->Kn:Lcom/miui/internal/view/menu/PhoneActionMenuView$OverflowMenuState;

    sget-object v2, Lcom/miui/internal/view/menu/PhoneActionMenuView$OverflowMenuState;->Av:Lcom/miui/internal/view/menu/PhoneActionMenuView$OverflowMenuState;

    if-ne v0, v2, :cond_3

    :cond_0
    const/4 v0, 0x1

    .line 327
    :goto_0
    if-eqz v0, :cond_1

    iget-object v2, p0, Lcom/miui/internal/view/menu/PhoneActionMenuView;->Kp:Landroid/graphics/drawable/Drawable;

    if-eqz v2, :cond_1

    .line 328
    iget-object v2, p0, Lcom/miui/internal/view/menu/PhoneActionMenuView;->Kp:Landroid/graphics/drawable/Drawable;

    invoke-virtual {p0}, Lcom/miui/internal/view/menu/PhoneActionMenuView;->getWidth()I

    move-result v3

    invoke-virtual {p0}, Lcom/miui/internal/view/menu/PhoneActionMenuView;->getHeight()I

    move-result v4

    invoke-virtual {v2, v1, v1, v3, v4}, Landroid/graphics/drawable/Drawable;->setBounds(IIII)V

    .line 329
    iget-object v2, p0, Lcom/miui/internal/view/menu/PhoneActionMenuView;->Kp:Landroid/graphics/drawable/Drawable;

    invoke-virtual {v2, p1}, Landroid/graphics/drawable/Drawable;->draw(Landroid/graphics/Canvas;)V

    .line 331
    :cond_1
    if-eqz v0, :cond_4

    iget-object v0, p0, Lcom/miui/internal/view/menu/PhoneActionMenuView;->Ko:Landroid/graphics/drawable/Drawable;

    .line 332
    :goto_1
    if-eqz v0, :cond_2

    .line 333
    invoke-virtual {p0}, Lcom/miui/internal/view/menu/PhoneActionMenuView;->getWidth()I

    move-result v2

    iget v3, p0, Lcom/miui/internal/view/menu/PhoneActionMenuView;->Kt:I

    iget v4, p0, Lcom/miui/internal/view/menu/PhoneActionMenuView;->Ku:I

    invoke-static {v3, v4}, Ljava/lang/Math;->max(II)I

    move-result v3

    invoke-virtual {v0, v1, v1, v2, v3}, Landroid/graphics/drawable/Drawable;->setBounds(IIII)V

    .line 334
    invoke-virtual {v0, p1}, Landroid/graphics/drawable/Drawable;->draw(Landroid/graphics/Canvas;)V

    .line 336
    :cond_2
    return-void

    :cond_3
    move v0, v1

    .line 325
    goto :goto_0

    .line 331
    :cond_4
    iget-object v0, p0, Lcom/miui/internal/view/menu/PhoneActionMenuView;->Kr:Landroid/graphics/drawable/Drawable;

    goto :goto_1
.end method

.method protected onLayout(ZIIII)V
    .locals 10

    .prologue
    const/4 v2, 0x0

    .line 297
    sub-int v5, p4, p2

    .line 298
    sub-int v6, p5, p3

    .line 300
    iget v0, p0, Lcom/miui/internal/view/menu/PhoneActionMenuView;->Ks:I

    sub-int v0, v5, v0

    shr-int/lit8 v3, v0, 0x1

    .line 302
    iget v0, p0, Lcom/miui/internal/view/menu/PhoneActionMenuView;->Kt:I

    .line 303
    iget-object v1, p0, Lcom/miui/internal/view/menu/PhoneActionMenuView;->Kv:Landroid/graphics/Rect;

    if-eqz v1, :cond_3

    .line 304
    iget-object v0, p0, Lcom/miui/internal/view/menu/PhoneActionMenuView;->Kv:Landroid/graphics/Rect;

    iget v1, v0, Landroid/graphics/Rect;->top:I

    .line 305
    iget v0, p0, Lcom/miui/internal/view/menu/PhoneActionMenuView;->Kt:I

    add-int/2addr v0, v1

    iget v4, p0, Lcom/miui/internal/view/menu/PhoneActionMenuView;->Ku:I

    iget v7, p0, Lcom/miui/internal/view/menu/PhoneActionMenuView;->Kt:I

    invoke-static {v4, v7}, Ljava/lang/Math;->max(II)I

    move-result v4

    invoke-static {v0, v4}, Ljava/lang/Math;->min(II)I

    move-result v0

    .line 307
    :goto_0
    invoke-virtual {p0}, Lcom/miui/internal/view/menu/PhoneActionMenuView;->getChildCount()I

    move-result v7

    move v4, v2

    .line 308
    :goto_1
    if-ge v4, v7, :cond_1

    .line 309
    invoke-virtual {p0, v4}, Lcom/miui/internal/view/menu/PhoneActionMenuView;->getChildAt(I)Landroid/view/View;

    move-result-object v8

    .line 310
    iget-object v9, p0, Lcom/miui/internal/view/menu/PhoneActionMenuView;->Kl:Landroid/view/View;

    if-ne v8, v9, :cond_0

    .line 308
    :goto_2
    add-int/lit8 v4, v4, 0x1

    goto :goto_1

    .line 314
    :cond_0
    invoke-virtual {v8}, Landroid/view/View;->getMeasuredWidth()I

    move-result v9

    add-int/2addr v9, v3

    invoke-virtual {v8, v3, v1, v9, v0}, Landroid/view/View;->layout(IIII)V

    .line 315
    invoke-virtual {v8}, Landroid/view/View;->getMeasuredWidth()I

    move-result v8

    add-int/2addr v3, v8

    goto :goto_2

    .line 318
    :cond_1
    iget-object v1, p0, Lcom/miui/internal/view/menu/PhoneActionMenuView;->Kl:Landroid/view/View;

    if-eqz v1, :cond_2

    .line 319
    iget-object v1, p0, Lcom/miui/internal/view/menu/PhoneActionMenuView;->Kl:Landroid/view/View;

    iget v3, p0, Lcom/miui/internal/view/menu/PhoneActionMenuView;->Ku:I

    invoke-static {v0, v3}, Ljava/lang/Math;->max(II)I

    move-result v0

    invoke-virtual {v1, v2, v0, v5, v6}, Landroid/view/View;->layout(IIII)V

    .line 321
    :cond_2
    return-void

    :cond_3
    move v1, v2

    goto :goto_0
.end method

.method protected onMeasure(II)V
    .locals 11

    .prologue
    const/4 v8, 0x0

    const/4 v3, 0x0

    .line 252
    .line 256
    invoke-virtual {p0}, Lcom/miui/internal/view/menu/PhoneActionMenuView;->getChildCount()I

    move-result v10

    .line 257
    if-nez v10, :cond_0

    .line 258
    iput v3, p0, Lcom/miui/internal/view/menu/PhoneActionMenuView;->Kt:I

    .line 259
    invoke-virtual {p0, v3, v3}, Lcom/miui/internal/view/menu/PhoneActionMenuView;->setMeasuredDimension(II)V

    .line 293
    :goto_0
    return-void

    :cond_0
    move v9, v3

    move v6, v3

    move v7, v3

    .line 263
    :goto_1
    if-ge v9, v10, :cond_2

    .line 264
    invoke-virtual {p0, v9}, Lcom/miui/internal/view/menu/PhoneActionMenuView;->getChildAt(I)Landroid/view/View;

    move-result-object v1

    .line 265
    iget-object v0, p0, Lcom/miui/internal/view/menu/PhoneActionMenuView;->Kl:Landroid/view/View;

    if-ne v1, v0, :cond_1

    move v0, v6

    move v1, v7

    .line 263
    :goto_2
    add-int/lit8 v2, v9, 0x1

    move v9, v2

    move v6, v0

    move v7, v1

    goto :goto_1

    :cond_1
    move-object v0, p0

    move v2, p1

    move v4, p2

    move v5, v3

    .line 269
    invoke-virtual/range {v0 .. v5}, Lcom/miui/internal/view/menu/PhoneActionMenuView;->measureChildWithMargins(Landroid/view/View;IIII)V

    .line 270
    invoke-virtual {v1}, Landroid/view/View;->getMeasuredWidth()I

    move-result v0

    add-int v2, v7, v0

    .line 271
    invoke-virtual {v1}, Landroid/view/View;->getMeasuredHeight()I

    move-result v0

    invoke-static {v6, v0}, Ljava/lang/Math;->max(II)I

    move-result v0

    move v1, v2

    goto :goto_2

    .line 273
    :cond_2
    iput v7, p0, Lcom/miui/internal/view/menu/PhoneActionMenuView;->Ks:I

    .line 274
    iput v6, p0, Lcom/miui/internal/view/menu/PhoneActionMenuView;->Kt:I

    .line 276
    iget-object v0, p0, Lcom/miui/internal/view/menu/PhoneActionMenuView;->Kl:Landroid/view/View;

    if-eqz v0, :cond_6

    .line 277
    iget-object v1, p0, Lcom/miui/internal/view/menu/PhoneActionMenuView;->Kl:Landroid/view/View;

    move-object v0, p0

    move v2, p1

    move v4, p2

    move v5, v3

    invoke-virtual/range {v0 .. v5}, Lcom/miui/internal/view/menu/PhoneActionMenuView;->measureChildWithMargins(Landroid/view/View;IIII)V

    .line 278
    iget-object v0, p0, Lcom/miui/internal/view/menu/PhoneActionMenuView;->Kl:Landroid/view/View;

    invoke-virtual {v0}, Landroid/view/View;->getMeasuredWidth()I

    move-result v0

    invoke-static {v7, v0}, Ljava/lang/Math;->max(II)I

    move-result v7

    .line 279
    iget-object v0, p0, Lcom/miui/internal/view/menu/PhoneActionMenuView;->Kl:Landroid/view/View;

    invoke-virtual {v0}, Landroid/view/View;->getMeasuredHeight()I

    move-result v0

    add-int/2addr v6, v0

    .line 281
    iget-object v0, p0, Lcom/miui/internal/view/menu/PhoneActionMenuView;->Kn:Lcom/miui/internal/view/menu/PhoneActionMenuView$OverflowMenuState;

    sget-object v1, Lcom/miui/internal/view/menu/PhoneActionMenuView$OverflowMenuState;->Av:Lcom/miui/internal/view/menu/PhoneActionMenuView$OverflowMenuState;

    if-eq v0, v1, :cond_3

    .line 282
    iget-object v0, p0, Lcom/miui/internal/view/menu/PhoneActionMenuView;->Kn:Lcom/miui/internal/view/menu/PhoneActionMenuView$OverflowMenuState;

    sget-object v1, Lcom/miui/internal/view/menu/PhoneActionMenuView$OverflowMenuState;->Au:Lcom/miui/internal/view/menu/PhoneActionMenuView$OverflowMenuState;

    if-ne v0, v1, :cond_5

    move v0, v8

    :goto_3
    invoke-virtual {p0, v0}, Lcom/miui/internal/view/menu/PhoneActionMenuView;->setTranslationY(F)V

    .line 288
    :cond_3
    :goto_4
    iget v0, p0, Lcom/miui/internal/view/menu/PhoneActionMenuView;->Ku:I

    iget v1, p0, Lcom/miui/internal/view/menu/PhoneActionMenuView;->Kt:I

    if-le v0, v1, :cond_4

    .line 289
    iget v0, p0, Lcom/miui/internal/view/menu/PhoneActionMenuView;->Ku:I

    iget v1, p0, Lcom/miui/internal/view/menu/PhoneActionMenuView;->Kt:I

    sub-int/2addr v0, v1

    add-int/2addr v6, v0

    .line 291
    :cond_4
    invoke-static {p1}, Landroid/view/View$MeasureSpec;->getSize(I)I

    move-result v0

    invoke-static {v7, v0}, Ljava/lang/Math;->max(II)I

    move-result v0

    .line 292
    invoke-virtual {p0, v0, v6}, Lcom/miui/internal/view/menu/PhoneActionMenuView;->setMeasuredDimension(II)V

    goto :goto_0

    .line 282
    :cond_5
    iget-object v0, p0, Lcom/miui/internal/view/menu/PhoneActionMenuView;->Kl:Landroid/view/View;

    invoke-virtual {v0}, Landroid/view/View;->getMeasuredHeight()I

    move-result v0

    int-to-float v0, v0

    goto :goto_3

    .line 285
    :cond_6
    invoke-virtual {p0, v8}, Lcom/miui/internal/view/menu/PhoneActionMenuView;->setTranslationY(F)V

    goto :goto_4
.end method

.method public onPageScrolled(IFZZ)V
    .locals 4

    .prologue
    .line 346
    invoke-virtual {p0, p2, p3, p4}, Lcom/miui/internal/view/menu/PhoneActionMenuView;->computeAlpha(FZZ)F

    move-result v0

    invoke-virtual {p0, v0}, Lcom/miui/internal/view/menu/PhoneActionMenuView;->setAlpha(F)V

    .line 348
    invoke-virtual {p0, p2, p3, p4}, Lcom/miui/internal/view/menu/PhoneActionMenuView;->computeTranslationY(FZZ)F

    move-result v1

    .line 349
    const/4 v0, 0x0

    :goto_0
    invoke-virtual {p0}, Lcom/miui/internal/view/menu/PhoneActionMenuView;->getChildCount()I

    move-result v2

    if-ge v0, v2, :cond_1

    .line 350
    invoke-virtual {p0, v0}, Lcom/miui/internal/view/menu/PhoneActionMenuView;->getChildAt(I)Landroid/view/View;

    move-result-object v2

    .line 351
    iget-object v3, p0, Lcom/miui/internal/view/menu/PhoneActionMenuView;->Kl:Landroid/view/View;

    if-ne v2, v3, :cond_0

    .line 349
    :goto_1
    add-int/lit8 v0, v0, 0x1

    goto :goto_0

    .line 354
    :cond_0
    invoke-virtual {v2, v1}, Landroid/view/View;->setTranslationY(F)V

    goto :goto_1

    .line 356
    :cond_1
    return-void
.end method

.method public onTouchEvent(Landroid/view/MotionEvent;)Z
    .locals 2

    .prologue
    .line 341
    invoke-virtual {p1}, Landroid/view/MotionEvent;->getY()F

    move-result v0

    invoke-virtual {p0}, Lcom/miui/internal/view/menu/PhoneActionMenuView;->getTranslationY()F

    move-result v1

    cmpl-float v0, v0, v1

    if-gtz v0, :cond_0

    invoke-super {p0, p1}, Lcom/miui/internal/view/menu/ActionMenuView;->onTouchEvent(Landroid/view/MotionEvent;)Z

    move-result v0

    if-eqz v0, :cond_1

    :cond_0
    const/4 v0, 0x1

    :goto_0
    return v0

    :cond_1
    const/4 v0, 0x0

    goto :goto_0
.end method

.method public setBackground(Landroid/graphics/drawable/Drawable;)V
    .locals 1

    .prologue
    .line 91
    invoke-super {p0, p1}, Lcom/miui/internal/view/menu/ActionMenuView;->setBackground(Landroid/graphics/drawable/Drawable;)V

    .line 92
    iget-object v0, p0, Lcom/miui/internal/view/menu/PhoneActionMenuView;->Kr:Landroid/graphics/drawable/Drawable;

    if-eq v0, p1, :cond_0

    .line 93
    iput-object p1, p0, Lcom/miui/internal/view/menu/PhoneActionMenuView;->Kr:Landroid/graphics/drawable/Drawable;

    .line 94
    invoke-direct {p0}, Lcom/miui/internal/view/menu/PhoneActionMenuView;->ea()V

    .line 96
    :cond_0
    return-void
.end method

.method public setOverflowMenuView(Landroid/view/View;)V
    .locals 1

    .prologue
    .line 112
    iget-object v0, p0, Lcom/miui/internal/view/menu/PhoneActionMenuView;->Kl:Landroid/view/View;

    if-eq v0, p1, :cond_3

    .line 113
    iget-object v0, p0, Lcom/miui/internal/view/menu/PhoneActionMenuView;->Kl:Landroid/view/View;

    if-eqz v0, :cond_1

    .line 114
    iget-object v0, p0, Lcom/miui/internal/view/menu/PhoneActionMenuView;->Kl:Landroid/view/View;

    invoke-virtual {v0}, Landroid/view/View;->getAnimation()Landroid/view/animation/Animation;

    move-result-object v0

    if-eqz v0, :cond_0

    .line 115
    iget-object v0, p0, Lcom/miui/internal/view/menu/PhoneActionMenuView;->Kl:Landroid/view/View;

    invoke-virtual {v0}, Landroid/view/View;->clearAnimation()V

    .line 117
    :cond_0
    iget-object v0, p0, Lcom/miui/internal/view/menu/PhoneActionMenuView;->Kl:Landroid/view/View;

    invoke-virtual {p0, v0}, Lcom/miui/internal/view/menu/PhoneActionMenuView;->removeView(Landroid/view/View;)V

    .line 120
    :cond_1
    if-eqz p1, :cond_2

    .line 121
    invoke-virtual {p0, p1}, Lcom/miui/internal/view/menu/PhoneActionMenuView;->addView(Landroid/view/View;)V

    .line 123
    :cond_2
    iput-object p1, p0, Lcom/miui/internal/view/menu/PhoneActionMenuView;->Kl:Landroid/view/View;

    .line 124
    invoke-direct {p0}, Lcom/miui/internal/view/menu/PhoneActionMenuView;->ea()V

    .line 126
    :cond_3
    return-void
.end method

.method public setValue(F)V
    .locals 1

    .prologue
    .line 377
    iget-object v0, p0, Lcom/miui/internal/view/menu/PhoneActionMenuView;->Kl:Landroid/view/View;

    if-nez v0, :cond_0

    .line 378
    const/4 v0, 0x0

    invoke-virtual {p0, v0}, Lcom/miui/internal/view/menu/PhoneActionMenuView;->setTranslationY(F)V

    .line 382
    :goto_0
    return-void

    .line 381
    :cond_0
    iget-object v0, p0, Lcom/miui/internal/view/menu/PhoneActionMenuView;->Kl:Landroid/view/View;

    invoke-virtual {v0}, Landroid/view/View;->getHeight()I

    move-result v0

    int-to-float v0, v0

    mul-float/2addr v0, p1

    invoke-virtual {p0, v0}, Lcom/miui/internal/view/menu/PhoneActionMenuView;->setTranslationY(F)V

    goto :goto_0
.end method

.method public showOverflowMenu()Z
    .locals 3

    .prologue
    .line 129
    iget-object v0, p0, Lcom/miui/internal/view/menu/PhoneActionMenuView;->Kn:Lcom/miui/internal/view/menu/PhoneActionMenuView$OverflowMenuState;

    .line 130
    sget-object v1, Lcom/miui/internal/view/menu/PhoneActionMenuView$OverflowMenuState;->At:Lcom/miui/internal/view/menu/PhoneActionMenuView$OverflowMenuState;

    if-eq v0, v1, :cond_0

    sget-object v1, Lcom/miui/internal/view/menu/PhoneActionMenuView$OverflowMenuState;->Au:Lcom/miui/internal/view/menu/PhoneActionMenuView$OverflowMenuState;

    if-eq v0, v1, :cond_0

    iget-object v1, p0, Lcom/miui/internal/view/menu/PhoneActionMenuView;->Kl:Landroid/view/View;

    if-nez v1, :cond_1

    .line 131
    :cond_0
    const/4 v0, 0x0

    .line 145
    :goto_0
    return v0

    .line 134
    :cond_1
    invoke-direct {p0}, Lcom/miui/internal/view/menu/PhoneActionMenuView;->dZ()Lcom/miui/internal/view/menu/PhoneActionMenuView$a;

    move-result-object v1

    .line 135
    sget-object v2, Lcom/miui/internal/view/menu/PhoneActionMenuView$OverflowMenuState;->As:Lcom/miui/internal/view/menu/PhoneActionMenuView$OverflowMenuState;

    if-ne v0, v2, :cond_3

    .line 136
    invoke-direct {p0}, Lcom/miui/internal/view/menu/PhoneActionMenuView;->dX()V

    .line 137
    sget-object v0, Lcom/miui/internal/view/menu/PhoneActionMenuView$OverflowMenuState;->At:Lcom/miui/internal/view/menu/PhoneActionMenuView$OverflowMenuState;

    iput-object v0, p0, Lcom/miui/internal/view/menu/PhoneActionMenuView;->Kn:Lcom/miui/internal/view/menu/PhoneActionMenuView$OverflowMenuState;

    .line 138
    invoke-virtual {v1}, Lcom/miui/internal/view/menu/PhoneActionMenuView$a;->show()V

    .line 144
    :cond_2
    :goto_1
    invoke-virtual {p0}, Lcom/miui/internal/view/menu/PhoneActionMenuView;->postInvalidateOnAnimation()V

    .line 145
    const/4 v0, 0x1

    goto :goto_0

    .line 139
    :cond_3
    sget-object v2, Lcom/miui/internal/view/menu/PhoneActionMenuView$OverflowMenuState;->Av:Lcom/miui/internal/view/menu/PhoneActionMenuView$OverflowMenuState;

    if-ne v0, v2, :cond_2

    .line 140
    invoke-direct {p0}, Lcom/miui/internal/view/menu/PhoneActionMenuView;->dX()V

    .line 141
    invoke-virtual {v1}, Lcom/miui/internal/view/menu/PhoneActionMenuView$a;->reverse()V

    goto :goto_1
.end method
