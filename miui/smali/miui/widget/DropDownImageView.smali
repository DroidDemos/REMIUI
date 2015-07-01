.class public Lmiui/widget/DropDownImageView;
.super Landroid/widget/ImageView;
.source "SourceFile"


# instance fields
.field private B:Landroid/animation/AnimatorSet;

.field private C:Landroid/animation/AnimatorSet;

.field private mContext:Landroid/content/Context;


# direct methods
.method public constructor <init>(Landroid/content/Context;)V
    .locals 1

    .prologue
    .line 26
    const/4 v0, 0x0

    invoke-direct {p0, p1, v0}, Lmiui/widget/DropDownImageView;-><init>(Landroid/content/Context;Landroid/util/AttributeSet;)V

    .line 27
    return-void
.end method

.method public constructor <init>(Landroid/content/Context;Landroid/util/AttributeSet;)V
    .locals 1

    .prologue
    .line 30
    const/4 v0, 0x0

    invoke-direct {p0, p1, p2, v0}, Lmiui/widget/DropDownImageView;-><init>(Landroid/content/Context;Landroid/util/AttributeSet;I)V

    .line 31
    return-void
.end method

.method public constructor <init>(Landroid/content/Context;Landroid/util/AttributeSet;I)V
    .locals 2

    .prologue
    const/4 v1, 0x0

    .line 34
    sget v0, Lcom/miui/internal/R$attr;->dropdownImageViewStyle:I

    invoke-direct {p0, p1, p2, v0}, Landroid/widget/ImageView;-><init>(Landroid/content/Context;Landroid/util/AttributeSet;I)V

    .line 21
    iput-object v1, p0, Lmiui/widget/DropDownImageView;->B:Landroid/animation/AnimatorSet;

    .line 23
    iput-object v1, p0, Lmiui/widget/DropDownImageView;->C:Landroid/animation/AnimatorSet;

    .line 36
    iput-object p1, p0, Lmiui/widget/DropDownImageView;->mContext:Landroid/content/Context;

    .line 37
    invoke-direct {p0}, Lmiui/widget/DropDownImageView;->d()V

    .line 38
    return-void
.end method

.method private d()V
    .locals 6

    .prologue
    const-wide/16 v4, 0x96

    const/4 v3, 0x2

    .line 42
    new-instance v0, Landroid/animation/AnimatorSet;

    invoke-direct {v0}, Landroid/animation/AnimatorSet;-><init>()V

    iput-object v0, p0, Lmiui/widget/DropDownImageView;->C:Landroid/animation/AnimatorSet;

    .line 43
    iget-object v0, p0, Lmiui/widget/DropDownImageView;->C:Landroid/animation/AnimatorSet;

    invoke-virtual {v0, v4, v5}, Landroid/animation/AnimatorSet;->setDuration(J)Landroid/animation/AnimatorSet;

    .line 45
    const-string v0, "rotationX"

    new-array v1, v3, [F

    fill-array-data v1, :array_0

    invoke-static {p0, v0, v1}, Landroid/animation/ObjectAnimator;->ofFloat(Ljava/lang/Object;Ljava/lang/String;[F)Landroid/animation/ObjectAnimator;

    move-result-object v0

    .line 46
    new-instance v1, Lmiui/widget/DropDownImageView$1;

    invoke-direct {v1, p0}, Lmiui/widget/DropDownImageView$1;-><init>(Lmiui/widget/DropDownImageView;)V

    invoke-virtual {v0, v1}, Landroid/animation/ObjectAnimator;->addListener(Landroid/animation/Animator$AnimatorListener;)V

    .line 55
    const-string v1, "rotationX"

    new-array v2, v3, [F

    fill-array-data v2, :array_1

    invoke-static {p0, v1, v2}, Landroid/animation/ObjectAnimator;->ofFloat(Ljava/lang/Object;Ljava/lang/String;[F)Landroid/animation/ObjectAnimator;

    move-result-object v1

    .line 56
    new-instance v2, Lmiui/widget/DropDownImageView$2;

    invoke-direct {v2, p0}, Lmiui/widget/DropDownImageView$2;-><init>(Lmiui/widget/DropDownImageView;)V

    invoke-virtual {v1, v2}, Landroid/animation/ObjectAnimator;->addListener(Landroid/animation/Animator$AnimatorListener;)V

    .line 62
    iget-object v2, p0, Lmiui/widget/DropDownImageView;->C:Landroid/animation/AnimatorSet;

    invoke-virtual {v2, v0}, Landroid/animation/AnimatorSet;->play(Landroid/animation/Animator;)Landroid/animation/AnimatorSet$Builder;

    move-result-object v0

    invoke-virtual {v0, v1}, Landroid/animation/AnimatorSet$Builder;->before(Landroid/animation/Animator;)Landroid/animation/AnimatorSet$Builder;

    .line 63
    iget-object v0, p0, Lmiui/widget/DropDownImageView;->C:Landroid/animation/AnimatorSet;

    invoke-virtual {v0, v4, v5}, Landroid/animation/AnimatorSet;->setDuration(J)Landroid/animation/AnimatorSet;

    .line 66
    new-instance v0, Landroid/animation/AnimatorSet;

    invoke-direct {v0}, Landroid/animation/AnimatorSet;-><init>()V

    iput-object v0, p0, Lmiui/widget/DropDownImageView;->B:Landroid/animation/AnimatorSet;

    .line 68
    const-string v0, "rotationX"

    new-array v1, v3, [F

    fill-array-data v1, :array_2

    invoke-static {p0, v0, v1}, Landroid/animation/ObjectAnimator;->ofFloat(Ljava/lang/Object;Ljava/lang/String;[F)Landroid/animation/ObjectAnimator;

    move-result-object v0

    .line 70
    new-instance v1, Lmiui/widget/DropDownImageView$3;

    invoke-direct {v1, p0}, Lmiui/widget/DropDownImageView$3;-><init>(Lmiui/widget/DropDownImageView;)V

    invoke-virtual {v0, v1}, Landroid/animation/ObjectAnimator;->addListener(Landroid/animation/Animator$AnimatorListener;)V

    .line 79
    const-string v1, "rotationX"

    new-array v2, v3, [F

    fill-array-data v2, :array_3

    invoke-static {p0, v1, v2}, Landroid/animation/ObjectAnimator;->ofFloat(Ljava/lang/Object;Ljava/lang/String;[F)Landroid/animation/ObjectAnimator;

    move-result-object v1

    .line 81
    new-instance v2, Lmiui/widget/DropDownImageView$4;

    invoke-direct {v2, p0}, Lmiui/widget/DropDownImageView$4;-><init>(Lmiui/widget/DropDownImageView;)V

    invoke-virtual {v1, v2}, Landroid/animation/ObjectAnimator;->addListener(Landroid/animation/Animator$AnimatorListener;)V

    .line 87
    iget-object v2, p0, Lmiui/widget/DropDownImageView;->B:Landroid/animation/AnimatorSet;

    invoke-virtual {v2, v0}, Landroid/animation/AnimatorSet;->play(Landroid/animation/Animator;)Landroid/animation/AnimatorSet$Builder;

    move-result-object v0

    invoke-virtual {v0, v1}, Landroid/animation/AnimatorSet$Builder;->before(Landroid/animation/Animator;)Landroid/animation/AnimatorSet$Builder;

    .line 88
    return-void

    .line 45
    :array_0
    .array-data 4
        0x0
        -0x3d4c0000
    .end array-data

    .line 55
    :array_1
    .array-data 4
        0x42b40000
        0x0
    .end array-data

    .line 68
    :array_2
    .array-data 4
        0x0
        0x42b40000
    .end array-data

    .line 79
    :array_3
    .array-data 4
        -0x3d4c0000
        0x0
    .end array-data
.end method


# virtual methods
.method public collapse()V
    .locals 1

    .prologue
    .line 91
    iget-object v0, p0, Lmiui/widget/DropDownImageView;->B:Landroid/animation/AnimatorSet;

    invoke-virtual {v0}, Landroid/animation/AnimatorSet;->cancel()V

    .line 92
    iget-object v0, p0, Lmiui/widget/DropDownImageView;->C:Landroid/animation/AnimatorSet;

    invoke-virtual {v0}, Landroid/animation/AnimatorSet;->start()V

    .line 93
    return-void
.end method

.method public expand()V
    .locals 1

    .prologue
    .line 96
    iget-object v0, p0, Lmiui/widget/DropDownImageView;->C:Landroid/animation/AnimatorSet;

    invoke-virtual {v0}, Landroid/animation/AnimatorSet;->cancel()V

    .line 97
    iget-object v0, p0, Lmiui/widget/DropDownImageView;->B:Landroid/animation/AnimatorSet;

    invoke-virtual {v0}, Landroid/animation/AnimatorSet;->start()V

    .line 98
    return-void
.end method
