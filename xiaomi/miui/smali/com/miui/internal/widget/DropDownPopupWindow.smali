.class public Lcom/miui/internal/widget/DropDownPopupWindow;
.super Ljava/lang/Object;
.source "SourceFile"


# annotations
.annotation system Ldalvik/annotation/MemberClasses;
    value = {
        Lcom/miui/internal/widget/DropDownPopupWindow$ListController;,
        Lcom/miui/internal/widget/DropDownPopupWindow$ViewContentController;,
        Lcom/miui/internal/widget/DropDownPopupWindow$DefaultContainerController;,
        Lcom/miui/internal/widget/DropDownPopupWindow$ContentController;,
        Lcom/miui/internal/widget/DropDownPopupWindow$ContainerController;,
        Lcom/miui/internal/widget/DropDownPopupWindow$Controller;,
        Lcom/miui/internal/widget/DropDownPopupWindow$a;
    }
.end annotation


# instance fields
.field private kA:I

.field private kB:I

.field private kC:Landroid/animation/ValueAnimator;

.field private kD:Z

.field private kE:Landroid/animation/ValueAnimator$AnimatorUpdateListener;

.field private kF:Landroid/animation/Animator$AnimatorListener;

.field private ku:Landroid/widget/PopupWindow;

.field private kv:Lcom/miui/internal/widget/DropDownPopupWindow$a;

.field private kw:Landroid/view/View;

.field private kx:Lcom/miui/internal/widget/DropDownPopupWindow$ContentController;

.field private ky:Lcom/miui/internal/widget/DropDownPopupWindow$ContainerController;

.field private kz:Lcom/miui/internal/widget/DropDownPopupWindow$Controller;

.field private mContentView:Landroid/view/View;

.field private mContext:Landroid/content/Context;


# direct methods
.method public constructor <init>(Landroid/content/Context;Landroid/util/AttributeSet;I)V
    .locals 2

    .prologue
    const/16 v0, 0x12c

    const/4 v1, 0x0

    .line 241
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    .line 32
    iput v0, p0, Lcom/miui/internal/widget/DropDownPopupWindow;->kA:I

    .line 33
    iput v0, p0, Lcom/miui/internal/widget/DropDownPopupWindow;->kB:I

    .line 36
    new-instance v0, Lcom/miui/internal/widget/h;

    invoke-direct {v0, p0}, Lcom/miui/internal/widget/h;-><init>(Lcom/miui/internal/widget/DropDownPopupWindow;)V

    iput-object v0, p0, Lcom/miui/internal/widget/DropDownPopupWindow;->kE:Landroid/animation/ValueAnimator$AnimatorUpdateListener;

    .line 48
    new-instance v0, Lcom/miui/internal/widget/i;

    invoke-direct {v0, p0}, Lcom/miui/internal/widget/i;-><init>(Lcom/miui/internal/widget/DropDownPopupWindow;)V

    iput-object v0, p0, Lcom/miui/internal/widget/DropDownPopupWindow;->kF:Landroid/animation/Animator$AnimatorListener;

    .line 242
    iput-object p1, p0, Lcom/miui/internal/widget/DropDownPopupWindow;->mContext:Landroid/content/Context;

    .line 243
    new-instance v0, Landroid/widget/PopupWindow;

    invoke-direct {v0, p1, p2, v1, p3}, Landroid/widget/PopupWindow;-><init>(Landroid/content/Context;Landroid/util/AttributeSet;II)V

    iput-object v0, p0, Lcom/miui/internal/widget/DropDownPopupWindow;->ku:Landroid/widget/PopupWindow;

    .line 244
    new-instance v0, Lcom/miui/internal/widget/DropDownPopupWindow$a;

    invoke-direct {v0, p0, p1, p2, p3}, Lcom/miui/internal/widget/DropDownPopupWindow$a;-><init>(Lcom/miui/internal/widget/DropDownPopupWindow;Landroid/content/Context;Landroid/util/AttributeSet;I)V

    iput-object v0, p0, Lcom/miui/internal/widget/DropDownPopupWindow;->kv:Lcom/miui/internal/widget/DropDownPopupWindow$a;

    .line 245
    iget-object v0, p0, Lcom/miui/internal/widget/DropDownPopupWindow;->ku:Landroid/widget/PopupWindow;

    invoke-virtual {v0, v1}, Landroid/widget/PopupWindow;->setAnimationStyle(I)V

    .line 246
    invoke-direct {p0}, Lcom/miui/internal/widget/DropDownPopupWindow;->aC()V

    .line 247
    return-void
.end method

.method static synthetic a(Lcom/miui/internal/widget/DropDownPopupWindow;)Landroid/animation/ValueAnimator;
    .locals 1

    .prologue
    .line 22
    iget-object v0, p0, Lcom/miui/internal/widget/DropDownPopupWindow;->kC:Landroid/animation/ValueAnimator;

    return-object v0
.end method

.method private a(FFI)V
    .locals 4

    .prologue
    const/4 v1, 0x2

    const/4 v3, 0x1

    const/4 v2, 0x0

    .line 289
    iget-object v0, p0, Lcom/miui/internal/widget/DropDownPopupWindow;->kC:Landroid/animation/ValueAnimator;

    if-eqz v0, :cond_0

    .line 290
    iget-object v0, p0, Lcom/miui/internal/widget/DropDownPopupWindow;->kC:Landroid/animation/ValueAnimator;

    invoke-virtual {v0}, Landroid/animation/ValueAnimator;->cancel()V

    .line 293
    :cond_0
    iget-object v0, p0, Lcom/miui/internal/widget/DropDownPopupWindow;->ky:Lcom/miui/internal/widget/DropDownPopupWindow$ContainerController;

    if-nez v0, :cond_1

    iget-object v0, p0, Lcom/miui/internal/widget/DropDownPopupWindow;->kx:Lcom/miui/internal/widget/DropDownPopupWindow$ContentController;

    if-eqz v0, :cond_2

    .line 294
    :cond_1
    iget-object v0, p0, Lcom/miui/internal/widget/DropDownPopupWindow;->kC:Landroid/animation/ValueAnimator;

    if-nez v0, :cond_3

    .line 295
    new-array v0, v1, [F

    aput p1, v0, v2

    aput p2, v0, v3

    invoke-static {v0}, Landroid/animation/ValueAnimator;->ofFloat([F)Landroid/animation/ValueAnimator;

    move-result-object v0

    iput-object v0, p0, Lcom/miui/internal/widget/DropDownPopupWindow;->kC:Landroid/animation/ValueAnimator;

    .line 299
    :goto_0
    iget-object v0, p0, Lcom/miui/internal/widget/DropDownPopupWindow;->kC:Landroid/animation/ValueAnimator;

    int-to-long v1, p3

    invoke-virtual {v0, v1, v2}, Landroid/animation/ValueAnimator;->setDuration(J)Landroid/animation/ValueAnimator;

    .line 300
    iget-object v0, p0, Lcom/miui/internal/widget/DropDownPopupWindow;->kC:Landroid/animation/ValueAnimator;

    iget-object v1, p0, Lcom/miui/internal/widget/DropDownPopupWindow;->kE:Landroid/animation/ValueAnimator$AnimatorUpdateListener;

    invoke-virtual {v0, v1}, Landroid/animation/ValueAnimator;->addUpdateListener(Landroid/animation/ValueAnimator$AnimatorUpdateListener;)V

    .line 301
    iget-object v0, p0, Lcom/miui/internal/widget/DropDownPopupWindow;->kC:Landroid/animation/ValueAnimator;

    iget-object v1, p0, Lcom/miui/internal/widget/DropDownPopupWindow;->kF:Landroid/animation/Animator$AnimatorListener;

    invoke-virtual {v0, v1}, Landroid/animation/ValueAnimator;->addListener(Landroid/animation/Animator$AnimatorListener;)V

    .line 302
    iget-object v0, p0, Lcom/miui/internal/widget/DropDownPopupWindow;->kC:Landroid/animation/ValueAnimator;

    invoke-virtual {v0}, Landroid/animation/ValueAnimator;->start()V

    .line 304
    :cond_2
    return-void

    .line 297
    :cond_3
    iget-object v0, p0, Lcom/miui/internal/widget/DropDownPopupWindow;->kC:Landroid/animation/ValueAnimator;

    new-array v1, v1, [F

    aput p1, v1, v2

    aput p2, v1, v3

    invoke-virtual {v0, v1}, Landroid/animation/ValueAnimator;->setFloatValues([F)V

    goto :goto_0
.end method

.method private aC()V
    .locals 4

    .prologue
    const/4 v3, 0x1

    const/4 v2, -0x1

    .line 250
    iget-object v0, p0, Lcom/miui/internal/widget/DropDownPopupWindow;->ku:Landroid/widget/PopupWindow;

    iget-object v1, p0, Lcom/miui/internal/widget/DropDownPopupWindow;->kv:Lcom/miui/internal/widget/DropDownPopupWindow$a;

    invoke-virtual {v0, v1}, Landroid/widget/PopupWindow;->setContentView(Landroid/view/View;)V

    .line 251
    iget-object v0, p0, Lcom/miui/internal/widget/DropDownPopupWindow;->ku:Landroid/widget/PopupWindow;

    invoke-virtual {v0, v2}, Landroid/widget/PopupWindow;->setWidth(I)V

    .line 252
    iget-object v0, p0, Lcom/miui/internal/widget/DropDownPopupWindow;->ku:Landroid/widget/PopupWindow;

    invoke-virtual {v0, v2}, Landroid/widget/PopupWindow;->setHeight(I)V

    .line 253
    iget-object v0, p0, Lcom/miui/internal/widget/DropDownPopupWindow;->ku:Landroid/widget/PopupWindow;

    const/4 v1, 0x3

    invoke-virtual {v0, v1}, Landroid/widget/PopupWindow;->setSoftInputMode(I)V

    .line 254
    iget-object v0, p0, Lcom/miui/internal/widget/DropDownPopupWindow;->ku:Landroid/widget/PopupWindow;

    const/4 v1, 0x0

    invoke-virtual {v0, v1}, Landroid/widget/PopupWindow;->setOutsideTouchable(Z)V

    .line 255
    iget-object v0, p0, Lcom/miui/internal/widget/DropDownPopupWindow;->ku:Landroid/widget/PopupWindow;

    invoke-virtual {v0, v3}, Landroid/widget/PopupWindow;->setFocusable(Z)V

    .line 256
    iget-object v0, p0, Lcom/miui/internal/widget/DropDownPopupWindow;->kv:Lcom/miui/internal/widget/DropDownPopupWindow$a;

    invoke-virtual {v0, v3}, Lcom/miui/internal/widget/DropDownPopupWindow$a;->setFocusableInTouchMode(Z)V

    .line 258
    iget-object v0, p0, Lcom/miui/internal/widget/DropDownPopupWindow;->kv:Lcom/miui/internal/widget/DropDownPopupWindow$a;

    iget-object v1, p0, Lcom/miui/internal/widget/DropDownPopupWindow;->mContext:Landroid/content/Context;

    invoke-virtual {v1}, Landroid/content/Context;->getResources()Landroid/content/res/Resources;

    move-result-object v1

    sget v2, Lcom/miui/internal/R$color;->arrow_popup_window_dim_color:I

    invoke-virtual {v1, v2}, Landroid/content/res/Resources;->getColor(I)I

    move-result v1

    invoke-virtual {v0, v1}, Lcom/miui/internal/widget/DropDownPopupWindow$a;->setBackgroundColor(I)V

    .line 259
    return-void
.end method

.method private aD()V
    .locals 1

    .prologue
    .line 307
    iget-object v0, p0, Lcom/miui/internal/widget/DropDownPopupWindow;->ku:Landroid/widget/PopupWindow;

    if-eqz v0, :cond_0

    .line 308
    iget-object v0, p0, Lcom/miui/internal/widget/DropDownPopupWindow;->ku:Landroid/widget/PopupWindow;

    invoke-virtual {v0}, Landroid/widget/PopupWindow;->dismiss()V

    .line 310
    :cond_0
    iget-object v0, p0, Lcom/miui/internal/widget/DropDownPopupWindow;->ky:Lcom/miui/internal/widget/DropDownPopupWindow$ContainerController;

    if-eqz v0, :cond_1

    .line 311
    iget-object v0, p0, Lcom/miui/internal/widget/DropDownPopupWindow;->ky:Lcom/miui/internal/widget/DropDownPopupWindow$ContainerController;

    invoke-interface {v0}, Lcom/miui/internal/widget/DropDownPopupWindow$ContainerController;->onDismiss()V

    .line 313
    :cond_1
    iget-object v0, p0, Lcom/miui/internal/widget/DropDownPopupWindow;->kx:Lcom/miui/internal/widget/DropDownPopupWindow$ContentController;

    if-eqz v0, :cond_2

    .line 314
    iget-object v0, p0, Lcom/miui/internal/widget/DropDownPopupWindow;->kx:Lcom/miui/internal/widget/DropDownPopupWindow$ContentController;

    invoke-interface {v0}, Lcom/miui/internal/widget/DropDownPopupWindow$ContentController;->onDismiss()V

    .line 316
    :cond_2
    const/4 v0, 0x0

    iput-boolean v0, p0, Lcom/miui/internal/widget/DropDownPopupWindow;->kD:Z

    .line 317
    return-void
.end method

.method static synthetic b(Lcom/miui/internal/widget/DropDownPopupWindow;)Lcom/miui/internal/widget/DropDownPopupWindow$ContainerController;
    .locals 1

    .prologue
    .line 22
    iget-object v0, p0, Lcom/miui/internal/widget/DropDownPopupWindow;->ky:Lcom/miui/internal/widget/DropDownPopupWindow$ContainerController;

    return-object v0
.end method

.method static synthetic c(Lcom/miui/internal/widget/DropDownPopupWindow;)Lcom/miui/internal/widget/DropDownPopupWindow$a;
    .locals 1

    .prologue
    .line 22
    iget-object v0, p0, Lcom/miui/internal/widget/DropDownPopupWindow;->kv:Lcom/miui/internal/widget/DropDownPopupWindow$a;

    return-object v0
.end method

.method static synthetic d(Lcom/miui/internal/widget/DropDownPopupWindow;)Lcom/miui/internal/widget/DropDownPopupWindow$ContentController;
    .locals 1

    .prologue
    .line 22
    iget-object v0, p0, Lcom/miui/internal/widget/DropDownPopupWindow;->kx:Lcom/miui/internal/widget/DropDownPopupWindow$ContentController;

    return-object v0
.end method

.method static synthetic e(Lcom/miui/internal/widget/DropDownPopupWindow;)Landroid/view/View;
    .locals 1

    .prologue
    .line 22
    iget-object v0, p0, Lcom/miui/internal/widget/DropDownPopupWindow;->mContentView:Landroid/view/View;

    return-object v0
.end method

.method static synthetic f(Lcom/miui/internal/widget/DropDownPopupWindow;)Z
    .locals 1

    .prologue
    .line 22
    iget-boolean v0, p0, Lcom/miui/internal/widget/DropDownPopupWindow;->kD:Z

    return v0
.end method

.method static synthetic g(Lcom/miui/internal/widget/DropDownPopupWindow;)V
    .locals 0

    .prologue
    .line 22
    invoke-direct {p0}, Lcom/miui/internal/widget/DropDownPopupWindow;->aD()V

    return-void
.end method

.method static synthetic h(Lcom/miui/internal/widget/DropDownPopupWindow;)Lcom/miui/internal/widget/DropDownPopupWindow$Controller;
    .locals 1

    .prologue
    .line 22
    iget-object v0, p0, Lcom/miui/internal/widget/DropDownPopupWindow;->kz:Lcom/miui/internal/widget/DropDownPopupWindow$Controller;

    return-object v0
.end method


# virtual methods
.method public dismiss()V
    .locals 3

    .prologue
    .line 320
    const/4 v0, 0x1

    iput-boolean v0, p0, Lcom/miui/internal/widget/DropDownPopupWindow;->kD:Z

    .line 321
    const/high16 v0, 0x3f800000

    const/4 v1, 0x0

    iget v2, p0, Lcom/miui/internal/widget/DropDownPopupWindow;->kB:I

    invoke-direct {p0, v0, v1, v2}, Lcom/miui/internal/widget/DropDownPopupWindow;->a(FFI)V

    .line 322
    return-void
.end method

.method public getContext()Landroid/content/Context;
    .locals 1

    .prologue
    .line 133
    iget-object v0, p0, Lcom/miui/internal/widget/DropDownPopupWindow;->mContext:Landroid/content/Context;

    return-object v0
.end method

.method public setAnchor(Landroid/view/View;)V
    .locals 0

    .prologue
    .line 262
    iput-object p1, p0, Lcom/miui/internal/widget/DropDownPopupWindow;->kw:Landroid/view/View;

    .line 263
    return-void
.end method

.method public setContainerController(Lcom/miui/internal/widget/DropDownPopupWindow$ContainerController;)V
    .locals 0

    .prologue
    .line 122
    iput-object p1, p0, Lcom/miui/internal/widget/DropDownPopupWindow;->ky:Lcom/miui/internal/widget/DropDownPopupWindow$ContainerController;

    .line 123
    return-void
.end method

.method public setContentController(Lcom/miui/internal/widget/DropDownPopupWindow$ContentController;)V
    .locals 0

    .prologue
    .line 125
    iput-object p1, p0, Lcom/miui/internal/widget/DropDownPopupWindow;->kx:Lcom/miui/internal/widget/DropDownPopupWindow$ContentController;

    .line 126
    return-void
.end method

.method public setDropDownController(Lcom/miui/internal/widget/DropDownPopupWindow$Controller;)V
    .locals 0

    .prologue
    .line 129
    iput-object p1, p0, Lcom/miui/internal/widget/DropDownPopupWindow;->kz:Lcom/miui/internal/widget/DropDownPopupWindow$Controller;

    .line 130
    return-void
.end method

.method public show()V
    .locals 6

    .prologue
    const/4 v5, 0x0

    .line 266
    iget-object v0, p0, Lcom/miui/internal/widget/DropDownPopupWindow;->ku:Landroid/widget/PopupWindow;

    invoke-virtual {v0}, Landroid/widget/PopupWindow;->isShowing()Z

    move-result v0

    if-eqz v0, :cond_0

    .line 267
    iget-object v0, p0, Lcom/miui/internal/widget/DropDownPopupWindow;->ku:Landroid/widget/PopupWindow;

    iget-object v1, p0, Lcom/miui/internal/widget/DropDownPopupWindow;->kw:Landroid/view/View;

    invoke-virtual {v0, v1, v5, v5}, Landroid/widget/PopupWindow;->update(Landroid/view/View;II)V

    .line 286
    :goto_0
    return-void

    .line 269
    :cond_0
    iget-object v0, p0, Lcom/miui/internal/widget/DropDownPopupWindow;->kx:Lcom/miui/internal/widget/DropDownPopupWindow$ContentController;

    if-eqz v0, :cond_2

    .line 270
    iget-object v0, p0, Lcom/miui/internal/widget/DropDownPopupWindow;->kx:Lcom/miui/internal/widget/DropDownPopupWindow$ContentController;

    invoke-interface {v0}, Lcom/miui/internal/widget/DropDownPopupWindow$ContentController;->getContentView()Landroid/view/View;

    move-result-object v0

    iput-object v0, p0, Lcom/miui/internal/widget/DropDownPopupWindow;->mContentView:Landroid/view/View;

    .line 271
    iget-object v0, p0, Lcom/miui/internal/widget/DropDownPopupWindow;->mContentView:Landroid/view/View;

    if-eqz v0, :cond_2

    .line 272
    iget-object v0, p0, Lcom/miui/internal/widget/DropDownPopupWindow;->ky:Lcom/miui/internal/widget/DropDownPopupWindow$ContainerController;

    if-eqz v0, :cond_1

    iget-object v0, p0, Lcom/miui/internal/widget/DropDownPopupWindow;->ky:Lcom/miui/internal/widget/DropDownPopupWindow$ContainerController;

    iget-object v1, p0, Lcom/miui/internal/widget/DropDownPopupWindow;->kv:Lcom/miui/internal/widget/DropDownPopupWindow$a;

    iget-object v2, p0, Lcom/miui/internal/widget/DropDownPopupWindow;->mContentView:Landroid/view/View;

    invoke-interface {v0, v1, v2}, Lcom/miui/internal/widget/DropDownPopupWindow$ContainerController;->onAddContent(Landroid/view/View;Landroid/view/View;)Z

    move-result v0

    if-nez v0, :cond_2

    .line 273
    :cond_1
    iget-object v0, p0, Lcom/miui/internal/widget/DropDownPopupWindow;->kv:Lcom/miui/internal/widget/DropDownPopupWindow$a;

    invoke-virtual {v0}, Lcom/miui/internal/widget/DropDownPopupWindow$a;->removeAllViews()V

    .line 274
    iget-object v0, p0, Lcom/miui/internal/widget/DropDownPopupWindow;->kv:Lcom/miui/internal/widget/DropDownPopupWindow$a;

    iget-object v1, p0, Lcom/miui/internal/widget/DropDownPopupWindow;->mContentView:Landroid/view/View;

    new-instance v2, Landroid/view/ViewGroup$LayoutParams;

    const/4 v3, -0x1

    const/4 v4, -0x2

    invoke-direct {v2, v3, v4}, Landroid/view/ViewGroup$LayoutParams;-><init>(II)V

    invoke-virtual {v0, v1, v2}, Lcom/miui/internal/widget/DropDownPopupWindow$a;->addView(Landroid/view/View;Landroid/view/ViewGroup$LayoutParams;)V

    .line 279
    :cond_2
    iget-object v0, p0, Lcom/miui/internal/widget/DropDownPopupWindow;->ky:Lcom/miui/internal/widget/DropDownPopupWindow$ContainerController;

    if-eqz v0, :cond_3

    .line 280
    iget-object v0, p0, Lcom/miui/internal/widget/DropDownPopupWindow;->ky:Lcom/miui/internal/widget/DropDownPopupWindow$ContainerController;

    invoke-interface {v0}, Lcom/miui/internal/widget/DropDownPopupWindow$ContainerController;->onShow()V

    .line 282
    :cond_3
    const/4 v0, 0x0

    const/high16 v1, 0x3f800000

    iget v2, p0, Lcom/miui/internal/widget/DropDownPopupWindow;->kA:I

    invoke-direct {p0, v0, v1, v2}, Lcom/miui/internal/widget/DropDownPopupWindow;->a(FFI)V

    .line 284
    iget-object v0, p0, Lcom/miui/internal/widget/DropDownPopupWindow;->ku:Landroid/widget/PopupWindow;

    iget-object v1, p0, Lcom/miui/internal/widget/DropDownPopupWindow;->kw:Landroid/view/View;

    invoke-virtual {v0, v1, v5, v5}, Landroid/widget/PopupWindow;->showAsDropDown(Landroid/view/View;II)V

    goto :goto_0
.end method
