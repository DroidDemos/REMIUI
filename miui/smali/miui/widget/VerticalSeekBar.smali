.class public Lmiui/widget/VerticalSeekBar;
.super Lmiui/widget/SeekBar;
.source "SourceFile"


# instance fields
.field private final kJ:Landroid/graphics/Rect;


# direct methods
.method public constructor <init>(Landroid/content/Context;)V
    .locals 1

    .prologue
    .line 22
    const/4 v0, 0x0

    invoke-direct {p0, p1, v0}, Lmiui/widget/VerticalSeekBar;-><init>(Landroid/content/Context;Landroid/util/AttributeSet;)V

    .line 23
    return-void
.end method

.method public constructor <init>(Landroid/content/Context;Landroid/util/AttributeSet;)V
    .locals 1

    .prologue
    .line 26
    const v0, 0x101007b

    invoke-direct {p0, p1, p2, v0}, Lmiui/widget/VerticalSeekBar;-><init>(Landroid/content/Context;Landroid/util/AttributeSet;I)V

    .line 27
    return-void
.end method

.method public constructor <init>(Landroid/content/Context;Landroid/util/AttributeSet;I)V
    .locals 1

    .prologue
    .line 30
    invoke-direct {p0, p1, p2, p3}, Lmiui/widget/SeekBar;-><init>(Landroid/content/Context;Landroid/util/AttributeSet;I)V

    .line 19
    new-instance v0, Landroid/graphics/Rect;

    invoke-direct {v0}, Landroid/graphics/Rect;-><init>()V

    iput-object v0, p0, Lmiui/widget/VerticalSeekBar;->kJ:Landroid/graphics/Rect;

    .line 31
    return-void
.end method

.method private a(Landroid/graphics/Canvas;)V
    .locals 7

    .prologue
    .line 40
    invoke-virtual {p0}, Lmiui/widget/VerticalSeekBar;->getProgressDrawable()Landroid/graphics/drawable/Drawable;

    move-result-object v0

    .line 41
    if-nez v0, :cond_0

    .line 56
    :goto_0
    return-void

    .line 45
    :cond_0
    invoke-virtual {p1}, Landroid/graphics/Canvas;->save()I

    .line 46
    const/high16 v1, -0x3d4c0000

    invoke-virtual {p0}, Lmiui/widget/VerticalSeekBar;->getWidth()I

    move-result v2

    div-int/lit8 v2, v2, 0x2

    int-to-float v2, v2

    invoke-virtual {p0}, Lmiui/widget/VerticalSeekBar;->getHeight()I

    move-result v3

    div-int/lit8 v3, v3, 0x2

    int-to-float v3, v3

    invoke-virtual {p1, v1, v2, v3}, Landroid/graphics/Canvas;->rotate(FFF)V

    .line 47
    invoke-virtual {p0}, Lmiui/widget/VerticalSeekBar;->getWidth()I

    move-result v1

    .line 48
    invoke-virtual {p0}, Lmiui/widget/VerticalSeekBar;->getHeight()I

    move-result v2

    .line 49
    sub-int v3, v2, v1

    neg-int v3, v3

    div-int/lit8 v3, v3, 0x2

    invoke-virtual {p0}, Lmiui/widget/VerticalSeekBar;->getPaddingBottom()I

    move-result v4

    add-int/2addr v3, v4

    .line 50
    sub-int v4, v2, v1

    div-int/lit8 v4, v4, 0x2

    invoke-virtual {p0}, Lmiui/widget/VerticalSeekBar;->getPaddingLeft()I

    move-result v5

    add-int/2addr v4, v5

    .line 51
    add-int v5, v2, v1

    div-int/lit8 v5, v5, 0x2

    invoke-virtual {p0}, Lmiui/widget/VerticalSeekBar;->getPaddingTop()I

    move-result v6

    sub-int/2addr v5, v6

    .line 52
    add-int/2addr v1, v2

    div-int/lit8 v1, v1, 0x2

    invoke-virtual {p0}, Lmiui/widget/VerticalSeekBar;->getPaddingRight()I

    move-result v2

    sub-int/2addr v1, v2

    .line 53
    invoke-virtual {v0, v3, v4, v5, v1}, Landroid/graphics/drawable/Drawable;->setBounds(IIII)V

    .line 54
    invoke-virtual {v0, p1}, Landroid/graphics/drawable/Drawable;->draw(Landroid/graphics/Canvas;)V

    .line 55
    invoke-virtual {p1}, Landroid/graphics/Canvas;->restore()V

    goto :goto_0
.end method

.method private b(Landroid/graphics/Canvas;)V
    .locals 8

    .prologue
    .line 59
    invoke-virtual {p0}, Lmiui/widget/VerticalSeekBar;->getThumb()Landroid/graphics/drawable/Drawable;

    move-result-object v1

    .line 60
    if-nez v1, :cond_0

    .line 86
    :goto_0
    return-void

    .line 64
    :cond_0
    iget-object v0, p0, Lmiui/widget/VerticalSeekBar;->kJ:Landroid/graphics/Rect;

    invoke-virtual {v1, v0}, Landroid/graphics/drawable/Drawable;->copyBounds(Landroid/graphics/Rect;)V

    .line 65
    invoke-virtual {v1}, Landroid/graphics/drawable/Drawable;->getIntrinsicWidth()I

    move-result v2

    .line 66
    invoke-virtual {v1}, Landroid/graphics/drawable/Drawable;->getIntrinsicHeight()I

    move-result v3

    .line 67
    invoke-virtual {p0}, Lmiui/widget/VerticalSeekBar;->getWidth()I

    move-result v0

    invoke-virtual {p0}, Lmiui/widget/VerticalSeekBar;->getPaddingLeft()I

    move-result v4

    sub-int/2addr v0, v4

    invoke-virtual {p0}, Lmiui/widget/VerticalSeekBar;->getPaddingRight()I

    move-result v4

    sub-int v4, v0, v4

    .line 68
    invoke-virtual {p0}, Lmiui/widget/VerticalSeekBar;->getHeight()I

    move-result v0

    invoke-virtual {p0}, Lmiui/widget/VerticalSeekBar;->getPaddingTop()I

    move-result v5

    sub-int/2addr v0, v5

    invoke-virtual {p0}, Lmiui/widget/VerticalSeekBar;->getPaddingBottom()I

    move-result v5

    sub-int/2addr v0, v5

    .line 69
    sub-int/2addr v0, v3

    .line 72
    invoke-virtual {p0}, Lmiui/widget/VerticalSeekBar;->getThumbOffset()I

    move-result v5

    mul-int/lit8 v5, v5, 0x2

    add-int/2addr v5, v0

    .line 74
    invoke-virtual {p0}, Lmiui/widget/VerticalSeekBar;->getMax()I

    move-result v0

    .line 75
    if-lez v0, :cond_1

    const/high16 v6, 0x3f800000

    invoke-virtual {p0}, Lmiui/widget/VerticalSeekBar;->getProgress()I

    move-result v7

    int-to-float v7, v7

    int-to-float v0, v0

    div-float v0, v7, v0

    sub-float v0, v6, v0

    .line 76
    :goto_1
    int-to-float v5, v5

    mul-float/2addr v0, v5

    float-to-int v0, v0

    .line 77
    sub-int/2addr v4, v2

    div-int/lit8 v4, v4, 0x2

    invoke-virtual {p0}, Lmiui/widget/VerticalSeekBar;->getPaddingLeft()I

    move-result v5

    add-int/2addr v4, v5

    .line 79
    add-int/2addr v2, v4

    add-int/2addr v3, v0

    invoke-virtual {v1, v4, v0, v2, v3}, Landroid/graphics/drawable/Drawable;->setBounds(IIII)V

    .line 84
    invoke-virtual {v1, p1}, Landroid/graphics/drawable/Drawable;->draw(Landroid/graphics/Canvas;)V

    .line 85
    iget-object v0, p0, Lmiui/widget/VerticalSeekBar;->kJ:Landroid/graphics/Rect;

    invoke-virtual {v1, v0}, Landroid/graphics/drawable/Drawable;->setBounds(Landroid/graphics/Rect;)V

    goto :goto_0

    .line 75
    :cond_1
    const/4 v0, 0x0

    goto :goto_1
.end method


# virtual methods
.method protected declared-synchronized onDraw(Landroid/graphics/Canvas;)V
    .locals 1

    .prologue
    .line 35
    monitor-enter p0

    :try_start_0
    invoke-direct {p0, p1}, Lmiui/widget/VerticalSeekBar;->a(Landroid/graphics/Canvas;)V

    .line 36
    invoke-direct {p0, p1}, Lmiui/widget/VerticalSeekBar;->b(Landroid/graphics/Canvas;)V
    :try_end_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    .line 37
    monitor-exit p0

    return-void

    .line 35
    :catchall_0
    move-exception v0

    monitor-exit p0

    throw v0
.end method

.method public onTouchEvent(Landroid/view/MotionEvent;)Z
    .locals 4

    .prologue
    .line 90
    invoke-virtual {p0}, Lmiui/widget/VerticalSeekBar;->getHeight()I

    move-result v0

    int-to-float v0, v0

    invoke-virtual {p1}, Landroid/view/MotionEvent;->getY()F

    move-result v1

    sub-float/2addr v0, v1

    invoke-virtual {p0}, Lmiui/widget/VerticalSeekBar;->getPaddingBottom()I

    move-result v1

    int-to-float v1, v1

    sub-float/2addr v0, v1

    .line 91
    invoke-virtual {p0}, Lmiui/widget/VerticalSeekBar;->getHeight()I

    move-result v1

    invoke-virtual {p0}, Lmiui/widget/VerticalSeekBar;->getPaddingTop()I

    move-result v2

    sub-int/2addr v1, v2

    invoke-virtual {p0}, Lmiui/widget/VerticalSeekBar;->getPaddingBottom()I

    move-result v2

    sub-int/2addr v1, v2

    int-to-float v1, v1

    .line 92
    invoke-virtual {p0}, Lmiui/widget/VerticalSeekBar;->getWidth()I

    move-result v2

    invoke-virtual {p0}, Lmiui/widget/VerticalSeekBar;->getPaddingLeft()I

    move-result v3

    sub-int/2addr v2, v3

    invoke-virtual {p0}, Lmiui/widget/VerticalSeekBar;->getPaddingRight()I

    move-result v3

    sub-int/2addr v2, v3

    int-to-float v2, v2

    .line 93
    div-float/2addr v0, v1

    mul-float/2addr v0, v2

    invoke-virtual {p0}, Lmiui/widget/VerticalSeekBar;->getPaddingLeft()I

    move-result v1

    int-to-float v1, v1

    add-float/2addr v0, v1

    invoke-virtual {p1}, Landroid/view/MotionEvent;->getY()F

    move-result v1

    invoke-virtual {p1, v0, v1}, Landroid/view/MotionEvent;->setLocation(FF)V

    .line 94
    invoke-super {p0, p1}, Lmiui/widget/SeekBar;->onTouchEvent(Landroid/view/MotionEvent;)Z

    move-result v0

    return v0
.end method
