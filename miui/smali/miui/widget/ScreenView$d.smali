.class Lmiui/widget/ScreenView$d;
.super Landroid/widget/FrameLayout;
.source "SourceFile"

# interfaces
.implements Lmiui/widget/ScreenView$c;


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lmiui/widget/ScreenView;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x2
    name = "d"
.end annotation


# instance fields
.field final synthetic bW:Lmiui/widget/ScreenView;

.field private sc:Landroid/graphics/Bitmap;

.field private sd:Landroid/graphics/NinePatch;

.field private se:Landroid/graphics/Rect;

.field private sf:Landroid/graphics/Rect;


# direct methods
.method public constructor <init>(Lmiui/widget/ScreenView;Landroid/content/Context;II)V
    .locals 5

    .prologue
    .line 2155
    iput-object p1, p0, Lmiui/widget/ScreenView$d;->bW:Lmiui/widget/ScreenView;

    .line 2156
    invoke-direct {p0, p2}, Landroid/widget/FrameLayout;-><init>(Landroid/content/Context;)V

    .line 2150
    new-instance v0, Landroid/graphics/Rect;

    invoke-direct {v0}, Landroid/graphics/Rect;-><init>()V

    iput-object v0, p0, Lmiui/widget/ScreenView$d;->se:Landroid/graphics/Rect;

    .line 2152
    new-instance v0, Landroid/graphics/Rect;

    invoke-direct {v0}, Landroid/graphics/Rect;-><init>()V

    iput-object v0, p0, Lmiui/widget/ScreenView$d;->sf:Landroid/graphics/Rect;

    .line 2157
    invoke-virtual {p0}, Lmiui/widget/ScreenView$d;->getResources()Landroid/content/res/Resources;

    move-result-object v0

    invoke-static {v0, p3}, Landroid/graphics/BitmapFactory;->decodeResource(Landroid/content/res/Resources;I)Landroid/graphics/Bitmap;

    move-result-object v0

    iput-object v0, p0, Lmiui/widget/ScreenView$d;->sc:Landroid/graphics/Bitmap;

    .line 2159
    iget-object v0, p0, Lmiui/widget/ScreenView$d;->sc:Landroid/graphics/Bitmap;

    if-nez v0, :cond_1

    .line 2181
    :cond_0
    :goto_0
    return-void

    .line 2162
    :cond_1
    iget-object v0, p0, Lmiui/widget/ScreenView$d;->sc:Landroid/graphics/Bitmap;

    invoke-virtual {v0}, Landroid/graphics/Bitmap;->getNinePatchChunk()[B

    move-result-object v0

    .line 2163
    if-eqz v0, :cond_0

    .line 2164
    new-instance v1, Landroid/graphics/NinePatch;

    iget-object v2, p0, Lmiui/widget/ScreenView$d;->sc:Landroid/graphics/Bitmap;

    const/4 v3, 0x0

    invoke-direct {v1, v2, v0, v3}, Landroid/graphics/NinePatch;-><init>(Landroid/graphics/Bitmap;[BLjava/lang/String;)V

    iput-object v1, p0, Lmiui/widget/ScreenView$d;->sd:Landroid/graphics/NinePatch;

    .line 2168
    new-instance v0, Landroid/widget/FrameLayout;

    invoke-virtual {p0}, Lmiui/widget/ScreenView$d;->getContext()Landroid/content/Context;

    move-result-object v1

    invoke-direct {v0, v1}, Landroid/widget/FrameLayout;-><init>(Landroid/content/Context;)V

    .line 2169
    invoke-virtual {v0, p4}, Landroid/widget/FrameLayout;->setBackgroundResource(I)V

    .line 2170
    new-instance v1, Landroid/widget/FrameLayout$LayoutParams;

    const/4 v2, -0x1

    const/4 v3, -0x2

    const/16 v4, 0x50

    invoke-direct {v1, v2, v3, v4}, Landroid/widget/FrameLayout$LayoutParams;-><init>(III)V

    .line 2174
    invoke-virtual {p0, v0, v1}, Lmiui/widget/ScreenView$d;->addView(Landroid/view/View;Landroid/view/ViewGroup$LayoutParams;)V

    .line 2175
    iget-object v1, p0, Lmiui/widget/ScreenView$d;->sf:Landroid/graphics/Rect;

    invoke-virtual {v0}, Landroid/widget/FrameLayout;->getPaddingLeft()I

    move-result v2

    iput v2, v1, Landroid/graphics/Rect;->left:I

    .line 2176
    iget-object v1, p0, Lmiui/widget/ScreenView$d;->sf:Landroid/graphics/Rect;

    invoke-virtual {v0}, Landroid/widget/FrameLayout;->getPaddingTop()I

    move-result v2

    iput v2, v1, Landroid/graphics/Rect;->top:I

    .line 2177
    iget-object v1, p0, Lmiui/widget/ScreenView$d;->sf:Landroid/graphics/Rect;

    invoke-virtual {v0}, Landroid/widget/FrameLayout;->getPaddingRight()I

    move-result v2

    iput v2, v1, Landroid/graphics/Rect;->right:I

    .line 2178
    iget-object v1, p0, Lmiui/widget/ScreenView$d;->sf:Landroid/graphics/Rect;

    invoke-virtual {v0}, Landroid/widget/FrameLayout;->getPaddingBottom()I

    move-result v0

    iput v0, v1, Landroid/graphics/Rect;->bottom:I

    .line 2179
    iget-object v0, p0, Lmiui/widget/ScreenView$d;->se:Landroid/graphics/Rect;

    iget-object v1, p0, Lmiui/widget/ScreenView$d;->sf:Landroid/graphics/Rect;

    iget v1, v1, Landroid/graphics/Rect;->top:I

    iput v1, v0, Landroid/graphics/Rect;->top:I

    .line 2180
    iget-object v0, p0, Lmiui/widget/ScreenView$d;->se:Landroid/graphics/Rect;

    iget-object v1, p0, Lmiui/widget/ScreenView$d;->se:Landroid/graphics/Rect;

    iget v1, v1, Landroid/graphics/Rect;->top:I

    iget-object v2, p0, Lmiui/widget/ScreenView$d;->sc:Landroid/graphics/Bitmap;

    invoke-virtual {v2}, Landroid/graphics/Bitmap;->getHeight()I

    move-result v2

    add-int/2addr v1, v2

    iput v1, v0, Landroid/graphics/Rect;->bottom:I

    goto :goto_0
.end method


# virtual methods
.method public bD()I
    .locals 2

    .prologue
    .line 2213
    invoke-virtual {p0}, Lmiui/widget/ScreenView$d;->getMeasuredWidth()I

    move-result v0

    iget-object v1, p0, Lmiui/widget/ScreenView$d;->sf:Landroid/graphics/Rect;

    iget v1, v1, Landroid/graphics/Rect;->left:I

    sub-int/2addr v0, v1

    iget-object v1, p0, Lmiui/widget/ScreenView$d;->sf:Landroid/graphics/Rect;

    iget v1, v1, Landroid/graphics/Rect;->right:I

    sub-int/2addr v0, v1

    return v0
.end method

.method protected dispatchDraw(Landroid/graphics/Canvas;)V
    .locals 2

    .prologue
    .line 2191
    invoke-super {p0, p1}, Landroid/widget/FrameLayout;->dispatchDraw(Landroid/graphics/Canvas;)V

    .line 2192
    iget-object v0, p0, Lmiui/widget/ScreenView$d;->sd:Landroid/graphics/NinePatch;

    if-eqz v0, :cond_0

    .line 2193
    iget-object v0, p0, Lmiui/widget/ScreenView$d;->sd:Landroid/graphics/NinePatch;

    iget-object v1, p0, Lmiui/widget/ScreenView$d;->se:Landroid/graphics/Rect;

    invoke-virtual {v0, p1, v1}, Landroid/graphics/NinePatch;->draw(Landroid/graphics/Canvas;Landroid/graphics/Rect;)V

    .line 2195
    :cond_0
    return-void
.end method

.method public f(II)V
    .locals 2

    .prologue
    .line 2208
    iget-object v0, p0, Lmiui/widget/ScreenView$d;->se:Landroid/graphics/Rect;

    iget-object v1, p0, Lmiui/widget/ScreenView$d;->sf:Landroid/graphics/Rect;

    iget v1, v1, Landroid/graphics/Rect;->left:I

    add-int/2addr v1, p1

    iput v1, v0, Landroid/graphics/Rect;->left:I

    .line 2209
    iget-object v0, p0, Lmiui/widget/ScreenView$d;->se:Landroid/graphics/Rect;

    iget-object v1, p0, Lmiui/widget/ScreenView$d;->sf:Landroid/graphics/Rect;

    iget v1, v1, Landroid/graphics/Rect;->left:I

    add-int/2addr v1, p2

    iput v1, v0, Landroid/graphics/Rect;->right:I

    .line 2210
    return-void
.end method

.method protected getSuggestedMinimumHeight()I
    .locals 2

    .prologue
    .line 2185
    iget-object v0, p0, Lmiui/widget/ScreenView$d;->sc:Landroid/graphics/Bitmap;

    invoke-virtual {v0}, Landroid/graphics/Bitmap;->getHeight()I

    move-result v0

    invoke-super {p0}, Landroid/widget/FrameLayout;->getSuggestedMinimumHeight()I

    move-result v1

    invoke-static {v0, v1}, Ljava/lang/Math;->max(II)I

    move-result v0

    return v0
.end method

.method protected onLayout(ZIIII)V
    .locals 3

    .prologue
    .line 2200
    invoke-super/range {p0 .. p5}, Landroid/widget/FrameLayout;->onLayout(ZIIII)V

    .line 2201
    iget-object v0, p0, Lmiui/widget/ScreenView$d;->sd:Landroid/graphics/NinePatch;

    if-eqz v0, :cond_0

    .line 2202
    iget-object v0, p0, Lmiui/widget/ScreenView$d;->se:Landroid/graphics/Rect;

    sub-int v1, p5, p3

    iget-object v2, p0, Lmiui/widget/ScreenView$d;->sf:Landroid/graphics/Rect;

    iget v2, v2, Landroid/graphics/Rect;->bottom:I

    sub-int/2addr v1, v2

    iput v1, v0, Landroid/graphics/Rect;->bottom:I

    .line 2203
    iget-object v0, p0, Lmiui/widget/ScreenView$d;->se:Landroid/graphics/Rect;

    iget-object v1, p0, Lmiui/widget/ScreenView$d;->se:Landroid/graphics/Rect;

    iget v1, v1, Landroid/graphics/Rect;->bottom:I

    iget-object v2, p0, Lmiui/widget/ScreenView$d;->sd:Landroid/graphics/NinePatch;

    invoke-virtual {v2}, Landroid/graphics/NinePatch;->getHeight()I

    move-result v2

    sub-int/2addr v1, v2

    iput v1, v0, Landroid/graphics/Rect;->top:I

    .line 2205
    :cond_0
    return-void
.end method

.method public u(I)Z
    .locals 3

    .prologue
    .line 2218
    invoke-virtual {p0}, Lmiui/widget/ScreenView$d;->getLeft()I

    move-result v0

    if-eq v0, p1, :cond_0

    .line 2219
    invoke-static {}, Lmiui/widget/ScreenView;->z()Lcom/miui/internal/variable/Android_View_View_class;

    move-result-object v0

    invoke-virtual {p0}, Lmiui/widget/ScreenView$d;->getRight()I

    move-result v1

    add-int/2addr v1, p1

    invoke-virtual {p0}, Lmiui/widget/ScreenView$d;->getLeft()I

    move-result v2

    sub-int/2addr v1, v2

    invoke-virtual {v0, p0, v1}, Lcom/miui/internal/variable/Android_View_View_class;->setRightDirectly(Landroid/view/View;I)V

    .line 2220
    invoke-static {}, Lmiui/widget/ScreenView;->z()Lcom/miui/internal/variable/Android_View_View_class;

    move-result-object v0

    invoke-virtual {v0, p0, p1}, Lcom/miui/internal/variable/Android_View_View_class;->setLeftDirectly(Landroid/view/View;I)V

    .line 2221
    const/4 v0, 0x1

    .line 2223
    :goto_0
    return v0

    :cond_0
    const/4 v0, 0x0

    goto :goto_0
.end method
