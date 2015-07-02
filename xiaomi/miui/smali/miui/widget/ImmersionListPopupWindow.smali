.class public Lmiui/widget/ImmersionListPopupWindow;
.super Landroid/widget/PopupWindow;
.source "SourceFile"


# annotations
.annotation system Ldalvik/annotation/MemberClasses;
    value = {
        Lmiui/widget/ImmersionListPopupWindow$b;,
        Lmiui/widget/ImmersionListPopupWindow$a;
    }
.end annotation


# static fields
.field private static final Kj:I = 0x3

.field private static Oe:Ljava/lang/ref/WeakReference; = null
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Ljava/lang/ref/WeakReference",
            "<",
            "Landroid/graphics/Bitmap;",
            ">;"
        }
    .end annotation
.end field

.field private static final TAG:Ljava/lang/String; = "ImmersionListPopupWindow"


# instance fields
.field private Of:Landroid/widget/FrameLayout;

.field private Og:Landroid/widget/AdapterView$OnItemClickListener;

.field private Oh:Landroid/view/animation/LayoutAnimationController;

.field private Oi:Landroid/view/animation/LayoutAnimationController;

.field private Oj:Lmiui/widget/ImmersionListPopupWindow$b;

.field private Ok:Ljava/lang/Runnable;

.field private W:Landroid/view/View;

.field private mAdapter:Landroid/widget/ListAdapter;

.field private mContentView:Landroid/view/View;

.field private mContext:Landroid/content/Context;

.field private xX:Landroid/widget/ListView;


# direct methods
.method public constructor <init>(Landroid/content/Context;)V
    .locals 4

    .prologue
    const/4 v3, -0x1

    const/4 v2, 0x1

    .line 66
    invoke-direct {p0, p1}, Landroid/widget/PopupWindow;-><init>(Landroid/content/Context;)V

    .line 67
    iput-object p1, p0, Lmiui/widget/ImmersionListPopupWindow;->mContext:Landroid/content/Context;

    .line 68
    new-instance v0, Lmiui/widget/ImmersionListPopupWindow$b;

    const/4 v1, 0x0

    invoke-direct {v0, p0, v1}, Lmiui/widget/ImmersionListPopupWindow$b;-><init>(Lmiui/widget/ImmersionListPopupWindow;Lmiui/widget/ImmersionListPopupWindow$4;)V

    iput-object v0, p0, Lmiui/widget/ImmersionListPopupWindow;->Oj:Lmiui/widget/ImmersionListPopupWindow$b;

    .line 70
    invoke-virtual {p0, v2}, Lmiui/widget/ImmersionListPopupWindow;->setFocusable(Z)V

    .line 71
    invoke-virtual {p0, v3, v3}, Lmiui/widget/ImmersionListPopupWindow;->setWindowLayoutMode(II)V

    .line 73
    new-instance v0, Landroid/widget/FrameLayout;

    invoke-direct {v0, p1}, Landroid/widget/FrameLayout;-><init>(Landroid/content/Context;)V

    iput-object v0, p0, Lmiui/widget/ImmersionListPopupWindow;->Of:Landroid/widget/FrameLayout;

    .line 74
    iget-object v0, p0, Lmiui/widget/ImmersionListPopupWindow;->Of:Landroid/widget/FrameLayout;

    new-instance v1, Lmiui/widget/ImmersionListPopupWindow$4;

    invoke-direct {v1, p0}, Lmiui/widget/ImmersionListPopupWindow$4;-><init>(Lmiui/widget/ImmersionListPopupWindow;)V

    invoke-virtual {v0, v1}, Landroid/widget/FrameLayout;->setOnClickListener(Landroid/view/View$OnClickListener;)V

    .line 80
    iget-object v0, p0, Lmiui/widget/ImmersionListPopupWindow;->Of:Landroid/widget/FrameLayout;

    invoke-super {p0, v0}, Landroid/widget/PopupWindow;->setContentView(Landroid/view/View;)V

    .line 82
    invoke-static {}, Lcom/miui/internal/variable/Android_Widget_PopupWindow_class$Factory;->getInstance()Lcom/miui/internal/variable/Android_Widget_PopupWindow_class$Factory;

    move-result-object v0

    invoke-virtual {v0}, Lcom/miui/internal/variable/Android_Widget_PopupWindow_class$Factory;->get()Lcom/miui/internal/variable/Android_Widget_PopupWindow_class;

    move-result-object v0

    .line 84
    invoke-virtual {v0, p0, v2}, Lcom/miui/internal/variable/Android_Widget_PopupWindow_class;->setLayoutInScreenEnabled(Landroid/widget/PopupWindow;Z)V

    .line 85
    invoke-virtual {v0, p0, v2}, Lcom/miui/internal/variable/Android_Widget_PopupWindow_class;->setLayoutInsetDecor(Landroid/widget/PopupWindow;Z)V

    .line 86
    return-void
.end method

.method private a(Landroid/content/Context;Landroid/view/View;)Landroid/graphics/drawable/Drawable;
    .locals 6

    .prologue
    const/4 v5, 0x0

    .line 317
    sget v0, Lcom/miui/internal/R$attr;->immersionBlurMask:I

    invoke-static {p1, v0}, Lmiui/util/AttributeResolver;->resolveDrawable(Landroid/content/Context;I)Landroid/graphics/drawable/Drawable;

    move-result-object v2

    .line 319
    invoke-virtual {p2}, Landroid/view/View;->getRootView()Landroid/view/View;

    move-result-object v1

    .line 320
    if-nez v1, :cond_0

    move-object v0, v2

    .line 357
    :goto_0
    return-object v0

    .line 324
    :cond_0
    const/4 v0, 0x0

    .line 325
    sget-object v3, Lmiui/widget/ImmersionListPopupWindow;->Oe:Ljava/lang/ref/WeakReference;

    if-eqz v3, :cond_1

    .line 326
    sget-object v0, Lmiui/widget/ImmersionListPopupWindow;->Oe:Ljava/lang/ref/WeakReference;

    invoke-virtual {v0}, Ljava/lang/ref/WeakReference;->get()Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Landroid/graphics/Bitmap;

    .line 328
    :cond_1
    if-eqz v0, :cond_2

    invoke-virtual {v0}, Landroid/graphics/Bitmap;->getWidth()I

    move-result v3

    invoke-virtual {v1}, Landroid/view/View;->getWidth()I

    move-result v4

    if-ne v3, v4, :cond_2

    invoke-virtual {v0}, Landroid/graphics/Bitmap;->getHeight()I

    move-result v3

    invoke-virtual {v1}, Landroid/view/View;->getHeight()I

    move-result v4

    if-eq v3, v4, :cond_4

    .line 330
    :cond_2
    if-eqz v0, :cond_3

    .line 331
    invoke-virtual {v0}, Landroid/graphics/Bitmap;->recycle()V

    .line 334
    :cond_3
    :try_start_0
    invoke-virtual {v1}, Landroid/view/View;->getWidth()I

    move-result v0

    invoke-virtual {v1}, Landroid/view/View;->getHeight()I

    move-result v3

    sget-object v4, Landroid/graphics/Bitmap$Config;->ARGB_8888:Landroid/graphics/Bitmap$Config;

    invoke-static {v0, v3, v4}, Landroid/graphics/Bitmap;->createBitmap(IILandroid/graphics/Bitmap$Config;)Landroid/graphics/Bitmap;

    move-result-object v0

    .line 335
    new-instance v3, Ljava/lang/ref/WeakReference;

    invoke-direct {v3, v0}, Ljava/lang/ref/WeakReference;-><init>(Ljava/lang/Object;)V

    sput-object v3, Lmiui/widget/ImmersionListPopupWindow;->Oe:Ljava/lang/ref/WeakReference;
    :try_end_0
    .catch Ljava/lang/OutOfMemoryError; {:try_start_0 .. :try_end_0} :catch_0

    .line 342
    :cond_4
    new-instance v3, Landroid/graphics/Canvas;

    invoke-direct {v3, v0}, Landroid/graphics/Canvas;-><init>(Landroid/graphics/Bitmap;)V

    .line 343
    invoke-virtual {v1, v3}, Landroid/view/View;->draw(Landroid/graphics/Canvas;)V

    .line 344
    invoke-virtual {v0}, Landroid/graphics/Bitmap;->getWidth()I

    move-result v1

    div-int/lit8 v1, v1, 0x3

    invoke-virtual {v0}, Landroid/graphics/Bitmap;->getHeight()I

    move-result v3

    div-int/lit8 v3, v3, 0x3

    invoke-static {v0, v1, v3, v5}, Landroid/graphics/Bitmap;->createScaledBitmap(Landroid/graphics/Bitmap;IIZ)Landroid/graphics/Bitmap;

    move-result-object v1

    .line 346
    if-eq v1, v0, :cond_5

    move-object v0, v1

    .line 350
    :cond_5
    invoke-virtual {p1}, Landroid/content/Context;->getResources()Landroid/content/res/Resources;

    move-result-object v1

    sget v3, Lcom/miui/internal/R$dimen;->screenshot_blur_radius:I

    invoke-virtual {v1, v3}, Landroid/content/res/Resources;->getDimensionPixelSize(I)I

    move-result v1

    .line 352
    invoke-static {v0, v1}, Lmiui/graphics/BitmapFactory;->fastBlur(Landroid/graphics/Bitmap;I)Landroid/graphics/Bitmap;

    move-result-object v1

    .line 353
    new-instance v0, Landroid/graphics/Canvas;

    invoke-direct {v0, v1}, Landroid/graphics/Canvas;-><init>(Landroid/graphics/Bitmap;)V

    .line 354
    invoke-virtual {v1}, Landroid/graphics/Bitmap;->getWidth()I

    move-result v3

    invoke-virtual {v1}, Landroid/graphics/Bitmap;->getHeight()I

    move-result v4

    invoke-virtual {v2, v5, v5, v3, v4}, Landroid/graphics/drawable/Drawable;->setBounds(IIII)V

    .line 355
    invoke-virtual {v2, v0}, Landroid/graphics/drawable/Drawable;->draw(Landroid/graphics/Canvas;)V

    .line 357
    new-instance v0, Landroid/graphics/drawable/BitmapDrawable;

    invoke-virtual {p1}, Landroid/content/Context;->getResources()Landroid/content/res/Resources;

    move-result-object v2

    invoke-direct {v0, v2, v1}, Landroid/graphics/drawable/BitmapDrawable;-><init>(Landroid/content/res/Resources;Landroid/graphics/Bitmap;)V

    goto/16 :goto_0

    .line 336
    :catch_0
    move-exception v0

    .line 337
    const-string v1, "ImmersionListPopupWindow"

    const-string v3, "OOM occurs while createBitmap"

    invoke-static {v1, v3, v0}, Landroid/util/Log;->e(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I

    move-object v0, v2

    .line 338
    goto/16 :goto_0
.end method

.method static synthetic a(Lmiui/widget/ImmersionListPopupWindow;)Landroid/widget/ListView;
    .locals 1

    .prologue
    .line 45
    iget-object v0, p0, Lmiui/widget/ImmersionListPopupWindow;->xX:Landroid/widget/ListView;

    return-object v0
.end method

.method private a(Landroid/view/View;Landroid/view/ViewGroup;)V
    .locals 7

    .prologue
    const/4 v2, 0x2

    const/4 v6, 0x1

    const/4 v5, 0x0

    .line 231
    iget-object v0, p0, Lmiui/widget/ImmersionListPopupWindow;->W:Landroid/view/View;

    invoke-virtual {v0}, Landroid/view/View;->getLayoutParams()Landroid/view/ViewGroup$LayoutParams;

    move-result-object v0

    invoke-virtual {p2}, Landroid/view/ViewGroup;->getHeight()I

    move-result v1

    iput v1, v0, Landroid/view/ViewGroup$LayoutParams;->height:I

    .line 234
    new-array v0, v2, [I

    .line 235
    invoke-virtual {p1, v0}, Landroid/view/View;->getLocationOnScreen([I)V

    .line 236
    new-array v1, v2, [I

    .line 237
    invoke-virtual {p2, v1}, Landroid/view/ViewGroup;->getLocationOnScreen([I)V

    .line 238
    aget v2, v1, v5

    invoke-virtual {p2}, Landroid/view/ViewGroup;->getWidth()I

    move-result v3

    add-int/2addr v2, v3

    aget v3, v0, v5

    invoke-virtual {p1}, Landroid/view/View;->getWidth()I

    move-result v4

    add-int/2addr v3, v4

    sub-int/2addr v2, v3

    .line 239
    aget v1, v1, v6

    invoke-virtual {p2}, Landroid/view/ViewGroup;->getHeight()I

    move-result v3

    add-int/2addr v1, v3

    aget v0, v0, v6

    invoke-virtual {p1}, Landroid/view/View;->getHeight()I

    move-result v3

    add-int/2addr v0, v3

    sub-int v0, v1, v0

    .line 240
    iget-object v1, p0, Lmiui/widget/ImmersionListPopupWindow;->W:Landroid/view/View;

    invoke-virtual {v1, v5, v5, v2, v0}, Landroid/view/View;->setPadding(IIII)V

    .line 241
    return-void
.end method

.method static synthetic b(Lmiui/widget/ImmersionListPopupWindow;)Landroid/widget/ListAdapter;
    .locals 1

    .prologue
    .line 45
    iget-object v0, p0, Lmiui/widget/ImmersionListPopupWindow;->mAdapter:Landroid/widget/ListAdapter;

    return-object v0
.end method

.method static synthetic c(Lmiui/widget/ImmersionListPopupWindow;)Landroid/widget/AdapterView$OnItemClickListener;
    .locals 1

    .prologue
    .line 45
    iget-object v0, p0, Lmiui/widget/ImmersionListPopupWindow;->Og:Landroid/widget/AdapterView$OnItemClickListener;

    return-object v0
.end method

.method static synthetic d(Lmiui/widget/ImmersionListPopupWindow;)Landroid/content/Context;
    .locals 1

    .prologue
    .line 45
    iget-object v0, p0, Lmiui/widget/ImmersionListPopupWindow;->mContext:Landroid/content/Context;

    return-object v0
.end method

.method static synthetic e(Lmiui/widget/ImmersionListPopupWindow;)Landroid/view/View;
    .locals 1

    .prologue
    .line 45
    iget-object v0, p0, Lmiui/widget/ImmersionListPopupWindow;->mContentView:Landroid/view/View;

    return-object v0
.end method

.method private es()V
    .locals 1

    .prologue
    .line 308
    iget-object v0, p0, Lmiui/widget/ImmersionListPopupWindow;->Ok:Ljava/lang/Runnable;

    if-eqz v0, :cond_0

    .line 309
    iget-object v0, p0, Lmiui/widget/ImmersionListPopupWindow;->Ok:Ljava/lang/Runnable;

    invoke-interface {v0}, Ljava/lang/Runnable;->run()V

    .line 310
    const/4 v0, 0x0

    iput-object v0, p0, Lmiui/widget/ImmersionListPopupWindow;->Ok:Ljava/lang/Runnable;

    .line 313
    :cond_0
    invoke-super {p0}, Landroid/widget/PopupWindow;->dismiss()V

    .line 314
    return-void
.end method

.method static synthetic f(Lmiui/widget/ImmersionListPopupWindow;)Landroid/widget/FrameLayout;
    .locals 1

    .prologue
    .line 45
    iget-object v0, p0, Lmiui/widget/ImmersionListPopupWindow;->Of:Landroid/widget/FrameLayout;

    return-object v0
.end method

.method static synthetic g(Lmiui/widget/ImmersionListPopupWindow;)Landroid/view/animation/LayoutAnimationController;
    .locals 1

    .prologue
    .line 45
    iget-object v0, p0, Lmiui/widget/ImmersionListPopupWindow;->Oh:Landroid/view/animation/LayoutAnimationController;

    return-object v0
.end method

.method static synthetic h(Lmiui/widget/ImmersionListPopupWindow;)Landroid/view/animation/LayoutAnimationController;
    .locals 1

    .prologue
    .line 45
    iget-object v0, p0, Lmiui/widget/ImmersionListPopupWindow;->Oi:Landroid/view/animation/LayoutAnimationController;

    return-object v0
.end method

.method static synthetic i(Lmiui/widget/ImmersionListPopupWindow;)V
    .locals 0

    .prologue
    .line 45
    invoke-direct {p0}, Lmiui/widget/ImmersionListPopupWindow;->es()V

    return-void
.end method

.method private p(Landroid/view/View;)V
    .locals 5

    .prologue
    .line 163
    invoke-virtual {p0}, Lmiui/widget/ImmersionListPopupWindow;->isShowing()Z

    move-result v0

    if-eqz v0, :cond_0

    .line 186
    :goto_0
    return-void

    .line 167
    :cond_0
    invoke-virtual {p1}, Landroid/view/View;->getRootView()Landroid/view/View;

    move-result-object v0

    invoke-virtual {v0}, Landroid/view/View;->getContext()Landroid/content/Context;

    move-result-object v1

    .line 168
    instance-of v0, v1, Lcom/miui/internal/app/IActivity;

    if-nez v0, :cond_1

    .line 169
    const-string v0, "ImmersionListPopupWindow"

    new-instance v2, Ljava/lang/StringBuilder;

    invoke-direct {v2}, Ljava/lang/StringBuilder;-><init>()V

    const-string v3, "Fail to setup translucent status for unknown context: "

    invoke-virtual {v2, v3}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v2

    invoke-virtual {v1}, Ljava/lang/Object;->getClass()Ljava/lang/Class;

    move-result-object v1

    invoke-virtual {v1}, Ljava/lang/Class;->getName()Ljava/lang/String;

    move-result-object v1

    invoke-virtual {v2, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v1

    invoke-virtual {v1}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v1

    invoke-static {v0, v1}, Landroid/util/Log;->w(Ljava/lang/String;Ljava/lang/String;)I

    .line 170
    const/4 v0, 0x0

    iput-object v0, p0, Lmiui/widget/ImmersionListPopupWindow;->Ok:Ljava/lang/Runnable;

    goto :goto_0

    :cond_1
    move-object v0, v1

    .line 174
    check-cast v0, Lcom/miui/internal/app/IActivity;

    .line 175
    invoke-interface {v0}, Lcom/miui/internal/app/IActivity;->getTranslucentStatus()I

    move-result v2

    .line 176
    sget v3, Lcom/miui/internal/R$attr;->immersionStatusBarStyle:I

    const/4 v4, 0x0

    invoke-static {v1, v3, v4}, Lmiui/util/AttributeResolver;->resolveInt(Landroid/content/Context;II)I

    move-result v1

    .line 179
    invoke-interface {v0, v1}, Lcom/miui/internal/app/IActivity;->setTranslucentStatus(I)V

    .line 180
    new-instance v1, Lmiui/widget/ImmersionListPopupWindow$2;

    invoke-direct {v1, p0, v0, v2}, Lmiui/widget/ImmersionListPopupWindow$2;-><init>(Lmiui/widget/ImmersionListPopupWindow;Lcom/miui/internal/app/IActivity;I)V

    iput-object v1, p0, Lmiui/widget/ImmersionListPopupWindow;->Ok:Ljava/lang/Runnable;

    goto :goto_0
.end method

.method private q(Landroid/view/View;)V
    .locals 3

    .prologue
    const/4 v2, 0x0

    .line 223
    iget-object v0, p0, Lmiui/widget/ImmersionListPopupWindow;->mContext:Landroid/content/Context;

    const v1, 0x10102eb

    invoke-static {v0, v1}, Lmiui/util/AttributeResolver;->resolveDimensionPixelSize(Landroid/content/Context;I)I

    move-result v0

    .line 224
    iget-object v1, p0, Lmiui/widget/ImmersionListPopupWindow;->W:Landroid/view/View;

    invoke-virtual {v1}, Landroid/view/View;->getLayoutParams()Landroid/view/ViewGroup$LayoutParams;

    move-result-object v1

    iput v0, v1, Landroid/view/ViewGroup$LayoutParams;->height:I

    .line 225
    invoke-virtual {p1, v2, v2}, Landroid/view/View;->measure(II)V

    .line 226
    invoke-virtual {p1}, Landroid/view/View;->getMeasuredHeight()I

    move-result v1

    sub-int/2addr v0, v1

    div-int/lit8 v0, v0, 0x2

    .line 227
    iget-object v1, p0, Lmiui/widget/ImmersionListPopupWindow;->W:Landroid/view/View;

    invoke-virtual {v1, v2, v2, v2, v0}, Landroid/view/View;->setPadding(IIII)V

    .line 228
    return-void
.end method


# virtual methods
.method protected createDefaultFadeInAnimation()Landroid/view/animation/LayoutAnimationController;
    .locals 2

    .prologue
    .line 244
    iget-object v0, p0, Lmiui/widget/ImmersionListPopupWindow;->mContext:Landroid/content/Context;

    sget v1, Lcom/miui/internal/R$anim;->immersion_layout_fade_in:I

    invoke-static {v0, v1}, Landroid/view/animation/AnimationUtils;->loadLayoutAnimation(Landroid/content/Context;I)Landroid/view/animation/LayoutAnimationController;

    move-result-object v0

    return-object v0
.end method

.method protected createDefaultFadeOutAnimation()Landroid/view/animation/LayoutAnimationController;
    .locals 2

    .prologue
    .line 248
    iget-object v0, p0, Lmiui/widget/ImmersionListPopupWindow;->mContext:Landroid/content/Context;

    sget v1, Lcom/miui/internal/R$anim;->immersion_layout_fade_out:I

    invoke-static {v0, v1}, Landroid/view/animation/AnimationUtils;->loadLayoutAnimation(Landroid/content/Context;I)Landroid/view/animation/LayoutAnimationController;

    move-result-object v0

    return-object v0
.end method

.method public dismiss()V
    .locals 2

    .prologue
    .line 274
    invoke-virtual {p0}, Lmiui/widget/ImmersionListPopupWindow;->isShowing()Z

    move-result v0

    if-eqz v0, :cond_4

    .line 276
    iget-object v0, p0, Lmiui/widget/ImmersionListPopupWindow;->xX:Landroid/widget/ListView;

    invoke-virtual {v0}, Landroid/widget/ListView;->getLayoutAnimation()Landroid/view/animation/LayoutAnimationController;

    move-result-object v0

    if-eqz v0, :cond_1

    .line 296
    :cond_0
    :goto_0
    return-void

    .line 279
    :cond_1
    iget-object v0, p0, Lmiui/widget/ImmersionListPopupWindow;->Oi:Landroid/view/animation/LayoutAnimationController;

    if-nez v0, :cond_2

    .line 280
    invoke-virtual {p0}, Lmiui/widget/ImmersionListPopupWindow;->createDefaultFadeOutAnimation()Landroid/view/animation/LayoutAnimationController;

    move-result-object v0

    iput-object v0, p0, Lmiui/widget/ImmersionListPopupWindow;->Oi:Landroid/view/animation/LayoutAnimationController;

    .line 282
    :cond_2
    iget-object v0, p0, Lmiui/widget/ImmersionListPopupWindow;->Oi:Landroid/view/animation/LayoutAnimationController;

    if-eqz v0, :cond_3

    .line 283
    iget-object v0, p0, Lmiui/widget/ImmersionListPopupWindow;->xX:Landroid/widget/ListView;

    iget-object v1, p0, Lmiui/widget/ImmersionListPopupWindow;->Oi:Landroid/view/animation/LayoutAnimationController;

    invoke-virtual {v0, v1}, Landroid/widget/ListView;->setLayoutAnimation(Landroid/view/animation/LayoutAnimationController;)V

    .line 284
    iget-object v0, p0, Lmiui/widget/ImmersionListPopupWindow;->xX:Landroid/widget/ListView;

    iget-object v1, p0, Lmiui/widget/ImmersionListPopupWindow;->Oj:Lmiui/widget/ImmersionListPopupWindow$b;

    invoke-virtual {v0, v1}, Landroid/widget/ListView;->setLayoutAnimationListener(Landroid/view/animation/Animation$AnimationListener;)V

    .line 285
    iget-object v0, p0, Lmiui/widget/ImmersionListPopupWindow;->Oj:Lmiui/widget/ImmersionListPopupWindow$b;

    const/4 v1, 0x0

    invoke-virtual {v0, v1}, Lmiui/widget/ImmersionListPopupWindow$b;->g(Z)V

    .line 286
    iget-object v0, p0, Lmiui/widget/ImmersionListPopupWindow;->xX:Landroid/widget/ListView;

    invoke-virtual {v0}, Landroid/widget/ListView;->startLayoutAnimation()V

    .line 289
    :cond_3
    iget-object v0, p0, Lmiui/widget/ImmersionListPopupWindow;->Ok:Ljava/lang/Runnable;

    if-eqz v0, :cond_0

    .line 290
    iget-object v0, p0, Lmiui/widget/ImmersionListPopupWindow;->Ok:Ljava/lang/Runnable;

    invoke-interface {v0}, Ljava/lang/Runnable;->run()V

    .line 291
    const/4 v0, 0x0

    iput-object v0, p0, Lmiui/widget/ImmersionListPopupWindow;->Ok:Ljava/lang/Runnable;

    goto :goto_0

    .line 294
    :cond_4
    invoke-direct {p0}, Lmiui/widget/ImmersionListPopupWindow;->es()V

    goto :goto_0
.end method

.method public dismiss(Z)V
    .locals 0

    .prologue
    .line 299
    if-eqz p1, :cond_0

    .line 300
    invoke-virtual {p0}, Lmiui/widget/ImmersionListPopupWindow;->dismiss()V

    .line 304
    :goto_0
    return-void

    .line 302
    :cond_0
    invoke-direct {p0}, Lmiui/widget/ImmersionListPopupWindow;->es()V

    goto :goto_0
.end method

.method public fastShow(Landroid/view/View;Landroid/view/ViewGroup;)V
    .locals 3

    .prologue
    const/4 v2, 0x0

    .line 195
    iget-object v0, p0, Lmiui/widget/ImmersionListPopupWindow;->xX:Landroid/widget/ListView;

    iget-object v1, p0, Lmiui/widget/ImmersionListPopupWindow;->Oh:Landroid/view/animation/LayoutAnimationController;

    invoke-virtual {v0, v1}, Landroid/widget/ListView;->setLayoutAnimation(Landroid/view/animation/LayoutAnimationController;)V

    .line 196
    iget-object v0, p0, Lmiui/widget/ImmersionListPopupWindow;->Oj:Lmiui/widget/ImmersionListPopupWindow$b;

    const/4 v1, 0x1

    invoke-virtual {v0, v1}, Lmiui/widget/ImmersionListPopupWindow$b;->g(Z)V

    .line 197
    invoke-virtual {p0, p1, v2, v2, v2}, Lmiui/widget/ImmersionListPopupWindow;->showAtLocation(Landroid/view/View;III)V

    .line 198
    return-void
.end method

.method protected getBackgroundAnimator(Landroid/view/animation/LayoutAnimationController;Z)Landroid/animation/Animator;
    .locals 7

    .prologue
    const-wide/16 v0, 0x0

    const/4 v4, 0x2

    .line 254
    if-eqz p2, :cond_0

    .line 255
    invoke-virtual {p0}, Lmiui/widget/ImmersionListPopupWindow;->getBackground()Landroid/graphics/drawable/Drawable;

    move-result-object v2

    const-string v3, "rangeRatio"

    new-array v4, v4, [F

    fill-array-data v4, :array_0

    invoke-static {v2, v3, v4}, Landroid/animation/ObjectAnimator;->ofFloat(Ljava/lang/Object;Ljava/lang/String;[F)Landroid/animation/ObjectAnimator;

    move-result-object v4

    .line 256
    new-instance v2, Landroid/view/animation/DecelerateInterpolator;

    invoke-direct {v2}, Landroid/view/animation/DecelerateInterpolator;-><init>()V

    invoke-virtual {v4, v2}, Landroid/animation/Animator;->setInterpolator(Landroid/animation/TimeInterpolator;)V

    .line 257
    invoke-virtual {p1}, Landroid/view/animation/LayoutAnimationController;->getAnimation()Landroid/view/animation/Animation;

    move-result-object v2

    invoke-virtual {v2}, Landroid/view/animation/Animation;->getDuration()J

    move-result-wide v2

    .line 267
    :goto_0
    invoke-virtual {v4, v2, v3}, Landroid/animation/Animator;->setDuration(J)Landroid/animation/Animator;

    .line 268
    invoke-virtual {v4, v0, v1}, Landroid/animation/Animator;->setStartDelay(J)V

    .line 269
    return-object v4

    .line 260
    :cond_0
    invoke-virtual {p0}, Lmiui/widget/ImmersionListPopupWindow;->getBackground()Landroid/graphics/drawable/Drawable;

    move-result-object v2

    const-string v3, "rangeRatio"

    new-array v4, v4, [F

    fill-array-data v4, :array_1

    invoke-static {v2, v3, v4}, Landroid/animation/ObjectAnimator;->ofFloat(Ljava/lang/Object;Ljava/lang/String;[F)Landroid/animation/ObjectAnimator;

    move-result-object v4

    .line 261
    new-instance v2, Landroid/view/animation/AccelerateInterpolator;

    invoke-direct {v2}, Landroid/view/animation/AccelerateInterpolator;-><init>()V

    invoke-virtual {v4, v2}, Landroid/animation/Animator;->setInterpolator(Landroid/animation/TimeInterpolator;)V

    .line 262
    invoke-virtual {p1}, Landroid/view/animation/LayoutAnimationController;->getAnimation()Landroid/view/animation/Animation;

    move-result-object v2

    invoke-virtual {v2}, Landroid/view/animation/Animation;->getDuration()J

    move-result-wide v2

    long-to-float v2, v2

    const/high16 v3, 0x3f800000

    invoke-virtual {p1}, Landroid/view/animation/LayoutAnimationController;->getDelay()F

    move-result v5

    iget-object v6, p0, Lmiui/widget/ImmersionListPopupWindow;->xX:Landroid/widget/ListView;

    invoke-virtual {v6}, Landroid/widget/ListView;->getAdapter()Landroid/widget/ListAdapter;

    move-result-object v6

    invoke-interface {v6}, Landroid/widget/ListAdapter;->getCount()I

    move-result v6

    add-int/lit8 v6, v6, -0x1

    int-to-float v6, v6

    mul-float/2addr v5, v6

    add-float/2addr v3, v5

    mul-float/2addr v2, v3

    float-to-long v5, v2

    .line 264
    invoke-virtual {p1}, Landroid/view/animation/LayoutAnimationController;->getAnimation()Landroid/view/animation/Animation;

    move-result-object v2

    invoke-virtual {v2}, Landroid/view/animation/Animation;->getDuration()J

    move-result-wide v2

    .line 265
    sub-long/2addr v5, v2

    invoke-static {v5, v6, v0, v1}, Ljava/lang/Math;->max(JJ)J

    move-result-wide v0

    goto :goto_0

    .line 255
    :array_0
    .array-data 4
        0x0
        0x3f800000
    .end array-data

    .line 260
    :array_1
    .array-data 4
        0x3f800000
        0x0
    .end array-data
.end method

.method public getContentView()Landroid/view/View;
    .locals 1

    .prologue
    .line 101
    iget-object v0, p0, Lmiui/widget/ImmersionListPopupWindow;->mContentView:Landroid/view/View;

    return-object v0
.end method

.method protected installHeaderView(Landroid/view/View;Landroid/view/ViewGroup;)V
    .locals 4

    .prologue
    .line 201
    iget-object v0, p0, Lmiui/widget/ImmersionListPopupWindow;->mContext:Landroid/content/Context;

    invoke-static {v0}, Landroid/view/LayoutInflater;->from(Landroid/content/Context;)Landroid/view/LayoutInflater;

    move-result-object v0

    sget v1, Lcom/miui/internal/R$layout;->list_immersion_header:I

    iget-object v2, p0, Lmiui/widget/ImmersionListPopupWindow;->xX:Landroid/widget/ListView;

    const/4 v3, 0x0

    invoke-virtual {v0, v1, v2, v3}, Landroid/view/LayoutInflater;->inflate(ILandroid/view/ViewGroup;Z)Landroid/view/View;

    move-result-object v0

    iput-object v0, p0, Lmiui/widget/ImmersionListPopupWindow;->W:Landroid/view/View;

    .line 202
    iget-object v0, p0, Lmiui/widget/ImmersionListPopupWindow;->xX:Landroid/widget/ListView;

    iget-object v1, p0, Lmiui/widget/ImmersionListPopupWindow;->W:Landroid/view/View;

    invoke-virtual {v0, v1}, Landroid/widget/ListView;->addHeaderView(Landroid/view/View;)V

    .line 204
    iget-object v0, p0, Lmiui/widget/ImmersionListPopupWindow;->W:Landroid/view/View;

    sget v1, Lcom/miui/internal/R$id;->close:I

    invoke-virtual {v0, v1}, Landroid/view/View;->findViewById(I)Landroid/view/View;

    move-result-object v0

    .line 205
    if-nez v0, :cond_0

    .line 220
    :goto_0
    return-void

    .line 208
    :cond_0
    new-instance v1, Lmiui/widget/ImmersionListPopupWindow$1;

    invoke-direct {v1, p0}, Lmiui/widget/ImmersionListPopupWindow$1;-><init>(Lmiui/widget/ImmersionListPopupWindow;)V

    invoke-virtual {v0, v1}, Landroid/view/View;->setOnClickListener(Landroid/view/View$OnClickListener;)V

    .line 215
    if-nez p2, :cond_1

    .line 216
    invoke-direct {p0, v0}, Lmiui/widget/ImmersionListPopupWindow;->q(Landroid/view/View;)V

    goto :goto_0

    .line 218
    :cond_1
    invoke-direct {p0, p1, p2}, Lmiui/widget/ImmersionListPopupWindow;->a(Landroid/view/View;Landroid/view/ViewGroup;)V

    goto :goto_0
.end method

.method public setAdapter(Landroid/widget/ListAdapter;)V
    .locals 0

    .prologue
    .line 89
    iput-object p1, p0, Lmiui/widget/ImmersionListPopupWindow;->mAdapter:Landroid/widget/ListAdapter;

    .line 90
    return-void
.end method

.method public setContentView(Landroid/view/View;)V
    .locals 0

    .prologue
    .line 97
    iput-object p1, p0, Lmiui/widget/ImmersionListPopupWindow;->mContentView:Landroid/view/View;

    .line 98
    return-void
.end method

.method public setOnItemClickListener(Landroid/widget/AdapterView$OnItemClickListener;)V
    .locals 0

    .prologue
    .line 93
    iput-object p1, p0, Lmiui/widget/ImmersionListPopupWindow;->Og:Landroid/widget/AdapterView$OnItemClickListener;

    .line 94
    return-void
.end method

.method public show(Landroid/view/View;Landroid/view/ViewGroup;)V
    .locals 5

    .prologue
    const v4, 0x102000a

    const/4 v3, 0x1

    const/4 v2, 0x0

    .line 105
    if-nez p1, :cond_0

    .line 106
    const-string v0, "ImmersionListPopupWindow"

    const-string v1, "show: anchor is null"

    invoke-static {v0, v1}, Landroid/util/Log;->e(Ljava/lang/String;Ljava/lang/String;)I

    .line 160
    :goto_0
    return-void

    .line 110
    :cond_0
    iget-object v0, p0, Lmiui/widget/ImmersionListPopupWindow;->mContentView:Landroid/view/View;

    if-nez v0, :cond_1

    .line 111
    new-instance v0, Landroid/widget/ListView;

    iget-object v1, p0, Lmiui/widget/ImmersionListPopupWindow;->mContext:Landroid/content/Context;

    invoke-direct {v0, v1}, Landroid/widget/ListView;-><init>(Landroid/content/Context;)V

    iput-object v0, p0, Lmiui/widget/ImmersionListPopupWindow;->mContentView:Landroid/view/View;

    .line 112
    iget-object v0, p0, Lmiui/widget/ImmersionListPopupWindow;->mContentView:Landroid/view/View;

    invoke-virtual {v0, v4}, Landroid/view/View;->setId(I)V

    .line 113
    iget-object v0, p0, Lmiui/widget/ImmersionListPopupWindow;->mContext:Landroid/content/Context;

    invoke-virtual {v0}, Landroid/content/Context;->getResources()Landroid/content/res/Resources;

    move-result-object v0

    sget v1, Lcom/miui/internal/R$dimen;->immersion_list_padding_bottom:I

    invoke-virtual {v0, v1}, Landroid/content/res/Resources;->getDimensionPixelSize(I)I

    move-result v0

    .line 115
    iget-object v1, p0, Lmiui/widget/ImmersionListPopupWindow;->mContentView:Landroid/view/View;

    invoke-virtual {v1, v2, v2, v2, v0}, Landroid/view/View;->setPadding(IIII)V

    .line 118
    :cond_1
    iget-object v0, p0, Lmiui/widget/ImmersionListPopupWindow;->Of:Landroid/widget/FrameLayout;

    invoke-virtual {v0}, Landroid/widget/FrameLayout;->getChildCount()I

    move-result v0

    if-ne v0, v3, :cond_2

    iget-object v0, p0, Lmiui/widget/ImmersionListPopupWindow;->Of:Landroid/widget/FrameLayout;

    invoke-virtual {v0, v2}, Landroid/widget/FrameLayout;->getChildAt(I)Landroid/view/View;

    move-result-object v0

    iget-object v1, p0, Lmiui/widget/ImmersionListPopupWindow;->mContentView:Landroid/view/View;

    if-eq v0, v1, :cond_3

    .line 119
    :cond_2
    iget-object v0, p0, Lmiui/widget/ImmersionListPopupWindow;->Of:Landroid/widget/FrameLayout;

    invoke-virtual {v0}, Landroid/widget/FrameLayout;->removeAllViews()V

    .line 120
    iget-object v0, p0, Lmiui/widget/ImmersionListPopupWindow;->Of:Landroid/widget/FrameLayout;

    iget-object v1, p0, Lmiui/widget/ImmersionListPopupWindow;->mContentView:Landroid/view/View;

    invoke-virtual {v0, v1}, Landroid/widget/FrameLayout;->addView(Landroid/view/View;)V

    .line 121
    iget-object v0, p0, Lmiui/widget/ImmersionListPopupWindow;->mContentView:Landroid/view/View;

    invoke-virtual {v0}, Landroid/view/View;->getLayoutParams()Landroid/view/ViewGroup$LayoutParams;

    move-result-object v0

    .line 122
    const/4 v1, -0x1

    iput v1, v0, Landroid/view/ViewGroup$LayoutParams;->width:I

    .line 123
    const/4 v1, -0x2

    iput v1, v0, Landroid/view/ViewGroup$LayoutParams;->height:I

    .line 126
    :cond_3
    iget-object v0, p0, Lmiui/widget/ImmersionListPopupWindow;->Oh:Landroid/view/animation/LayoutAnimationController;

    if-nez v0, :cond_4

    .line 127
    invoke-virtual {p0}, Lmiui/widget/ImmersionListPopupWindow;->createDefaultFadeInAnimation()Landroid/view/animation/LayoutAnimationController;

    move-result-object v0

    iput-object v0, p0, Lmiui/widget/ImmersionListPopupWindow;->Oh:Landroid/view/animation/LayoutAnimationController;

    .line 130
    :cond_4
    iget-object v0, p0, Lmiui/widget/ImmersionListPopupWindow;->mContentView:Landroid/view/View;

    invoke-virtual {v0, v4}, Landroid/view/View;->findViewById(I)Landroid/view/View;

    move-result-object v0

    check-cast v0, Landroid/widget/ListView;

    iput-object v0, p0, Lmiui/widget/ImmersionListPopupWindow;->xX:Landroid/widget/ListView;

    .line 131
    iget-object v0, p0, Lmiui/widget/ImmersionListPopupWindow;->xX:Landroid/widget/ListView;

    if-nez v0, :cond_5

    .line 132
    const-string v0, "ImmersionListPopupWindow"

    const-string v1, "list not found"

    invoke-static {v0, v1}, Landroid/util/Log;->e(Ljava/lang/String;Ljava/lang/String;)I

    goto :goto_0

    .line 136
    :cond_5
    iget-object v0, p0, Lmiui/widget/ImmersionListPopupWindow;->xX:Landroid/widget/ListView;

    new-instance v1, Lmiui/widget/ImmersionListPopupWindow$3;

    invoke-direct {v1, p0}, Lmiui/widget/ImmersionListPopupWindow$3;-><init>(Lmiui/widget/ImmersionListPopupWindow;)V

    invoke-virtual {v0, v1}, Landroid/widget/ListView;->setOnItemClickListener(Landroid/widget/AdapterView$OnItemClickListener;)V

    .line 145
    iget-object v0, p0, Lmiui/widget/ImmersionListPopupWindow;->xX:Landroid/widget/ListView;

    iget-object v1, p0, Lmiui/widget/ImmersionListPopupWindow;->Oh:Landroid/view/animation/LayoutAnimationController;

    invoke-virtual {v0, v1}, Landroid/widget/ListView;->setLayoutAnimation(Landroid/view/animation/LayoutAnimationController;)V

    .line 146
    iget-object v0, p0, Lmiui/widget/ImmersionListPopupWindow;->xX:Landroid/widget/ListView;

    iget-object v1, p0, Lmiui/widget/ImmersionListPopupWindow;->Oj:Lmiui/widget/ImmersionListPopupWindow$b;

    invoke-virtual {v0, v1}, Landroid/widget/ListView;->setLayoutAnimationListener(Landroid/view/animation/Animation$AnimationListener;)V

    .line 147
    iget-object v0, p0, Lmiui/widget/ImmersionListPopupWindow;->Oj:Lmiui/widget/ImmersionListPopupWindow$b;

    invoke-virtual {v0, v3}, Lmiui/widget/ImmersionListPopupWindow$b;->g(Z)V

    .line 149
    iget-object v0, p0, Lmiui/widget/ImmersionListPopupWindow;->W:Landroid/view/View;

    if-nez v0, :cond_6

    .line 150
    invoke-virtual {p0, p1, p2}, Lmiui/widget/ImmersionListPopupWindow;->installHeaderView(Landroid/view/View;Landroid/view/ViewGroup;)V

    .line 152
    :cond_6
    iget-object v0, p0, Lmiui/widget/ImmersionListPopupWindow;->xX:Landroid/widget/ListView;

    iget-object v1, p0, Lmiui/widget/ImmersionListPopupWindow;->mAdapter:Landroid/widget/ListAdapter;

    invoke-virtual {v0, v1}, Landroid/widget/ListView;->setAdapter(Landroid/widget/ListAdapter;)V

    .line 153
    new-instance v0, Lmiui/widget/ImmersionListPopupWindow$a;

    iget-object v1, p0, Lmiui/widget/ImmersionListPopupWindow;->mContext:Landroid/content/Context;

    invoke-direct {p0, v1, p1}, Lmiui/widget/ImmersionListPopupWindow;->a(Landroid/content/Context;Landroid/view/View;)Landroid/graphics/drawable/Drawable;

    move-result-object v1

    invoke-direct {v0, p0, v1}, Lmiui/widget/ImmersionListPopupWindow$a;-><init>(Lmiui/widget/ImmersionListPopupWindow;Landroid/graphics/drawable/Drawable;)V

    .line 154
    const/4 v1, 0x0

    invoke-virtual {v0, v1}, Lmiui/widget/ImmersionListPopupWindow$a;->setRangeRatio(F)V

    .line 155
    invoke-virtual {p0, v0}, Lmiui/widget/ImmersionListPopupWindow;->setBackgroundDrawable(Landroid/graphics/drawable/Drawable;)V

    .line 157
    invoke-static {}, Lmiui/view/inputmethod/InputMethodHelper;->getInstance()Lmiui/view/inputmethod/InputMethodHelper;

    move-result-object v0

    invoke-virtual {v0}, Lmiui/view/inputmethod/InputMethodHelper;->getManager()Landroid/view/inputmethod/InputMethodManager;

    move-result-object v0

    invoke-virtual {p1}, Landroid/view/View;->getWindowToken()Landroid/os/IBinder;

    move-result-object v1

    invoke-virtual {v0, v1, v2}, Landroid/view/inputmethod/InputMethodManager;->hideSoftInputFromWindow(Landroid/os/IBinder;I)Z

    .line 158
    invoke-direct {p0, p1}, Lmiui/widget/ImmersionListPopupWindow;->p(Landroid/view/View;)V

    .line 159
    invoke-virtual {p0, p1, v2, v2, v2}, Lmiui/widget/ImmersionListPopupWindow;->showAtLocation(Landroid/view/View;III)V

    goto/16 :goto_0
.end method
