.class public Lmiui/widget/Rotation3DLayout$LayoutParams;
.super Landroid/widget/FrameLayout$LayoutParams;
.source "SourceFile"


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lmiui/widget/Rotation3DLayout;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x9
    name = "LayoutParams"
.end annotation


# instance fields
.field public zdistance:I


# direct methods
.method public constructor <init>(II)V
    .locals 1

    .prologue
    .line 495
    invoke-direct {p0, p1, p2}, Landroid/widget/FrameLayout$LayoutParams;-><init>(II)V

    .line 486
    const/4 v0, 0x0

    iput v0, p0, Lmiui/widget/Rotation3DLayout$LayoutParams;->zdistance:I

    .line 496
    return-void
.end method

.method public constructor <init>(III)V
    .locals 1

    .prologue
    .line 499
    invoke-direct {p0, p1, p2, p3}, Landroid/widget/FrameLayout$LayoutParams;-><init>(III)V

    .line 486
    const/4 v0, 0x0

    iput v0, p0, Lmiui/widget/Rotation3DLayout$LayoutParams;->zdistance:I

    .line 500
    return-void
.end method

.method public constructor <init>(IIII)V
    .locals 1

    .prologue
    .line 503
    invoke-direct {p0, p1, p2, p3}, Landroid/widget/FrameLayout$LayoutParams;-><init>(III)V

    .line 486
    const/4 v0, 0x0

    iput v0, p0, Lmiui/widget/Rotation3DLayout$LayoutParams;->zdistance:I

    .line 504
    iput p4, p0, Lmiui/widget/Rotation3DLayout$LayoutParams;->zdistance:I

    .line 505
    return-void
.end method

.method public constructor <init>(Landroid/content/Context;Landroid/util/AttributeSet;)V
    .locals 3

    .prologue
    .line 488
    invoke-direct {p0, p1, p2}, Landroid/widget/FrameLayout$LayoutParams;-><init>(Landroid/content/Context;Landroid/util/AttributeSet;)V

    .line 486
    const/4 v0, 0x0

    iput v0, p0, Lmiui/widget/Rotation3DLayout$LayoutParams;->zdistance:I

    .line 489
    sget-object v0, Lcom/miui/internal/R$styleable;->Rotation3DLayout_Layout:[I

    invoke-virtual {p1, p2, v0}, Landroid/content/Context;->obtainStyledAttributes(Landroid/util/AttributeSet;[I)Landroid/content/res/TypedArray;

    move-result-object v0

    .line 490
    const/4 v1, 0x1

    iget v2, p0, Lmiui/widget/Rotation3DLayout$LayoutParams;->zdistance:I

    invoke-virtual {v0, v1, v2}, Landroid/content/res/TypedArray;->getInt(II)I

    move-result v1

    iput v1, p0, Lmiui/widget/Rotation3DLayout$LayoutParams;->zdistance:I

    .line 491
    invoke-virtual {v0}, Landroid/content/res/TypedArray;->recycle()V

    .line 492
    return-void
.end method

.method public constructor <init>(Landroid/view/ViewGroup$LayoutParams;)V
    .locals 1

    .prologue
    .line 508
    invoke-direct {p0, p1}, Landroid/widget/FrameLayout$LayoutParams;-><init>(Landroid/view/ViewGroup$LayoutParams;)V

    .line 486
    const/4 v0, 0x0

    iput v0, p0, Lmiui/widget/Rotation3DLayout$LayoutParams;->zdistance:I

    .line 509
    return-void
.end method

.method public constructor <init>(Landroid/view/ViewGroup$MarginLayoutParams;)V
    .locals 1

    .prologue
    .line 512
    invoke-direct {p0, p1}, Landroid/widget/FrameLayout$LayoutParams;-><init>(Landroid/view/ViewGroup$MarginLayoutParams;)V

    .line 486
    const/4 v0, 0x0

    iput v0, p0, Lmiui/widget/Rotation3DLayout$LayoutParams;->zdistance:I

    .line 513
    return-void
.end method

.method public constructor <init>(Landroid/widget/FrameLayout$LayoutParams;)V
    .locals 1

    .prologue
    .line 516
    invoke-direct {p0, p1}, Landroid/widget/FrameLayout$LayoutParams;-><init>(Landroid/view/ViewGroup$MarginLayoutParams;)V

    .line 486
    const/4 v0, 0x0

    iput v0, p0, Lmiui/widget/Rotation3DLayout$LayoutParams;->zdistance:I

    .line 517
    return-void
.end method
