.class public Lcom/miui/internal/widget/AnimatedImageView;
.super Landroid/widget/ImageView;
.source "SourceFile"


# static fields
.field private static final pe:Lcom/miui/internal/variable/Android_Graphics_Drawable_AnimatedRotateDrawable_class;

.field private static final pf:I = 0x38

.field private static final pg:I = 0x20


# instance fields
.field private ph:Landroid/graphics/drawable/Drawable;

.field private pi:Z


# direct methods
.method static constructor <clinit>()V
    .locals 1

    .prologue
    .line 16
    invoke-static {}, Lcom/miui/internal/variable/Android_Graphics_Drawable_AnimatedRotateDrawable_class$Factory;->getInstance()Lcom/miui/internal/variable/Android_Graphics_Drawable_AnimatedRotateDrawable_class$Factory;

    move-result-object v0

    invoke-virtual {v0}, Lcom/miui/internal/variable/Android_Graphics_Drawable_AnimatedRotateDrawable_class$Factory;->get()Lcom/miui/internal/variable/Android_Graphics_Drawable_AnimatedRotateDrawable_class;

    move-result-object v0

    sput-object v0, Lcom/miui/internal/widget/AnimatedImageView;->pe:Lcom/miui/internal/variable/Android_Graphics_Drawable_AnimatedRotateDrawable_class;

    return-void
.end method

.method public constructor <init>(Landroid/content/Context;)V
    .locals 0

    .prologue
    .line 27
    invoke-direct {p0, p1}, Landroid/widget/ImageView;-><init>(Landroid/content/Context;)V

    .line 28
    return-void
.end method

.method public constructor <init>(Landroid/content/Context;Landroid/util/AttributeSet;)V
    .locals 0

    .prologue
    .line 31
    invoke-direct {p0, p1, p2}, Landroid/widget/ImageView;-><init>(Landroid/content/Context;Landroid/util/AttributeSet;)V

    .line 32
    return-void
.end method

.method private bj()V
    .locals 3

    .prologue
    .line 35
    invoke-virtual {p0}, Lcom/miui/internal/widget/AnimatedImageView;->isShown()Z

    move-result v0

    if-eqz v0, :cond_0

    .line 36
    invoke-direct {p0}, Lcom/miui/internal/widget/AnimatedImageView;->stop()V

    .line 38
    :cond_0
    invoke-virtual {p0}, Lcom/miui/internal/widget/AnimatedImageView;->getDrawable()Landroid/graphics/drawable/Drawable;

    move-result-object v0

    .line 39
    invoke-virtual {v0}, Ljava/lang/Object;->getClass()Ljava/lang/Class;

    move-result-object v1

    invoke-virtual {v1}, Ljava/lang/Class;->getName()Ljava/lang/String;

    move-result-object v1

    const-string v2, "android.graphics.drawable.AnimatedRotateDrawable"

    invoke-virtual {v1, v2}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v1

    if-eqz v1, :cond_1

    .line 40
    iput-object v0, p0, Lcom/miui/internal/widget/AnimatedImageView;->ph:Landroid/graphics/drawable/Drawable;

    .line 42
    sget-object v1, Lcom/miui/internal/widget/AnimatedImageView;->pe:Lcom/miui/internal/variable/Android_Graphics_Drawable_AnimatedRotateDrawable_class;

    const/16 v2, 0x38

    invoke-virtual {v1, v0, v2}, Lcom/miui/internal/variable/Android_Graphics_Drawable_AnimatedRotateDrawable_class;->setFramesCount(Landroid/graphics/drawable/Drawable;I)V

    .line 43
    sget-object v1, Lcom/miui/internal/widget/AnimatedImageView;->pe:Lcom/miui/internal/variable/Android_Graphics_Drawable_AnimatedRotateDrawable_class;

    const/16 v2, 0x20

    invoke-virtual {v1, v0, v2}, Lcom/miui/internal/variable/Android_Graphics_Drawable_AnimatedRotateDrawable_class;->setFramesDuration(Landroid/graphics/drawable/Drawable;I)V

    .line 47
    :goto_0
    invoke-direct {p0}, Lcom/miui/internal/widget/AnimatedImageView;->bk()V

    .line 48
    return-void

    .line 45
    :cond_1
    const/4 v0, 0x0

    iput-object v0, p0, Lcom/miui/internal/widget/AnimatedImageView;->ph:Landroid/graphics/drawable/Drawable;

    goto :goto_0
.end method

.method private bk()V
    .locals 1

    .prologue
    .line 51
    iget-object v0, p0, Lcom/miui/internal/widget/AnimatedImageView;->ph:Landroid/graphics/drawable/Drawable;

    if-eqz v0, :cond_0

    .line 52
    invoke-virtual {p0}, Lcom/miui/internal/widget/AnimatedImageView;->isShown()Z

    move-result v0

    if-eqz v0, :cond_1

    iget-boolean v0, p0, Lcom/miui/internal/widget/AnimatedImageView;->pi:Z

    if-eqz v0, :cond_1

    .line 53
    invoke-direct {p0}, Lcom/miui/internal/widget/AnimatedImageView;->start()V

    .line 58
    :cond_0
    :goto_0
    return-void

    .line 55
    :cond_1
    invoke-direct {p0}, Lcom/miui/internal/widget/AnimatedImageView;->stop()V

    goto :goto_0
.end method

.method private start()V
    .locals 2

    .prologue
    .line 96
    iget-object v0, p0, Lcom/miui/internal/widget/AnimatedImageView;->ph:Landroid/graphics/drawable/Drawable;

    if-eqz v0, :cond_0

    .line 97
    iget-object v0, p0, Lcom/miui/internal/widget/AnimatedImageView;->ph:Landroid/graphics/drawable/Drawable;

    invoke-virtual {v0}, Ljava/lang/Object;->getClass()Ljava/lang/Class;

    move-result-object v0

    invoke-virtual {v0}, Ljava/lang/Class;->getName()Ljava/lang/String;

    move-result-object v0

    const-string v1, "android.graphics.drawable.AnimatedRotateDrawable"

    invoke-virtual {v0, v1}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v0

    if-eqz v0, :cond_0

    .line 98
    sget-object v0, Lcom/miui/internal/widget/AnimatedImageView;->pe:Lcom/miui/internal/variable/Android_Graphics_Drawable_AnimatedRotateDrawable_class;

    iget-object v1, p0, Lcom/miui/internal/widget/AnimatedImageView;->ph:Landroid/graphics/drawable/Drawable;

    invoke-virtual {v0, v1}, Lcom/miui/internal/variable/Android_Graphics_Drawable_AnimatedRotateDrawable_class;->start(Landroid/graphics/drawable/Drawable;)V

    .line 101
    :cond_0
    return-void
.end method

.method private stop()V
    .locals 2

    .prologue
    .line 104
    iget-object v0, p0, Lcom/miui/internal/widget/AnimatedImageView;->ph:Landroid/graphics/drawable/Drawable;

    if-eqz v0, :cond_0

    .line 105
    iget-object v0, p0, Lcom/miui/internal/widget/AnimatedImageView;->ph:Landroid/graphics/drawable/Drawable;

    invoke-virtual {v0}, Ljava/lang/Object;->getClass()Ljava/lang/Class;

    move-result-object v0

    invoke-virtual {v0}, Ljava/lang/Class;->getName()Ljava/lang/String;

    move-result-object v0

    const-string v1, "android.graphics.drawable.AnimatedRotateDrawable"

    invoke-virtual {v0, v1}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v0

    if-eqz v0, :cond_0

    .line 106
    sget-object v0, Lcom/miui/internal/widget/AnimatedImageView;->pe:Lcom/miui/internal/variable/Android_Graphics_Drawable_AnimatedRotateDrawable_class;

    iget-object v1, p0, Lcom/miui/internal/widget/AnimatedImageView;->ph:Landroid/graphics/drawable/Drawable;

    invoke-virtual {v0, v1}, Lcom/miui/internal/variable/Android_Graphics_Drawable_AnimatedRotateDrawable_class;->stop(Landroid/graphics/drawable/Drawable;)V

    .line 109
    :cond_0
    return-void
.end method


# virtual methods
.method public onAttachedToWindow()V
    .locals 0

    .prologue
    .line 74
    invoke-super {p0}, Landroid/widget/ImageView;->onAttachedToWindow()V

    .line 75
    invoke-direct {p0}, Lcom/miui/internal/widget/AnimatedImageView;->bk()V

    .line 76
    return-void
.end method

.method public onDetachedFromWindow()V
    .locals 0

    .prologue
    .line 80
    invoke-super {p0}, Landroid/widget/ImageView;->onDetachedFromWindow()V

    .line 81
    invoke-direct {p0}, Lcom/miui/internal/widget/AnimatedImageView;->bk()V

    .line 82
    return-void
.end method

.method protected onVisibilityChanged(Landroid/view/View;I)V
    .locals 0

    .prologue
    .line 91
    invoke-super {p0, p1, p2}, Landroid/widget/ImageView;->onVisibilityChanged(Landroid/view/View;I)V

    .line 92
    invoke-direct {p0}, Lcom/miui/internal/widget/AnimatedImageView;->bk()V

    .line 93
    return-void
.end method

.method public setAnimating(Z)V
    .locals 0

    .prologue
    .line 85
    iput-boolean p1, p0, Lcom/miui/internal/widget/AnimatedImageView;->pi:Z

    .line 86
    invoke-direct {p0}, Lcom/miui/internal/widget/AnimatedImageView;->bk()V

    .line 87
    return-void
.end method

.method public setImageDrawable(Landroid/graphics/drawable/Drawable;)V
    .locals 0

    .prologue
    .line 62
    invoke-super {p0, p1}, Landroid/widget/ImageView;->setImageDrawable(Landroid/graphics/drawable/Drawable;)V

    .line 63
    invoke-direct {p0}, Lcom/miui/internal/widget/AnimatedImageView;->bj()V

    .line 64
    return-void
.end method

.method public setImageResource(I)V
    .locals 0

    .prologue
    .line 68
    invoke-super {p0, p1}, Landroid/widget/ImageView;->setImageResource(I)V

    .line 69
    invoke-direct {p0}, Lcom/miui/internal/widget/AnimatedImageView;->bj()V

    .line 70
    return-void
.end method
