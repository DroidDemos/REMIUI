.class Lmiui/widget/DynamicListView$1;
.super Ljava/lang/Object;
.source "SourceFile"

# interfaces
.implements Landroid/view/ViewTreeObserver$OnPreDrawListener;


# annotations
.annotation system Ldalvik/annotation/EnclosingMethod;
    value = Lmiui/widget/DynamicListView;->a(Ljava/util/List;Ljava/util/List;)V
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x0
    name = null
.end annotation


# instance fields
.field final synthetic ho:Landroid/widget/BaseAdapter;

.field final synthetic hp:Ljava/util/List;

.field final synthetic hq:Lmiui/widget/DynamicListView;


# direct methods
.method constructor <init>(Lmiui/widget/DynamicListView;Landroid/widget/BaseAdapter;Ljava/util/List;)V
    .locals 0

    .prologue
    .line 965
    iput-object p1, p0, Lmiui/widget/DynamicListView$1;->hq:Lmiui/widget/DynamicListView;

    iput-object p2, p0, Lmiui/widget/DynamicListView$1;->ho:Landroid/widget/BaseAdapter;

    iput-object p3, p0, Lmiui/widget/DynamicListView$1;->hp:Ljava/util/List;

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method


# virtual methods
.method public onPreDraw()Z
    .locals 10

    .prologue
    const/4 v9, 0x1

    const/4 v2, 0x0

    .line 967
    iget-object v0, p0, Lmiui/widget/DynamicListView$1;->hq:Lmiui/widget/DynamicListView;

    invoke-virtual {v0}, Lmiui/widget/DynamicListView;->getViewTreeObserver()Landroid/view/ViewTreeObserver;

    move-result-object v0

    invoke-virtual {v0, p0}, Landroid/view/ViewTreeObserver;->removeOnPreDrawListener(Landroid/view/ViewTreeObserver$OnPreDrawListener;)V

    .line 968
    iget-object v0, p0, Lmiui/widget/DynamicListView$1;->hq:Lmiui/widget/DynamicListView;

    invoke-virtual {v0}, Lmiui/widget/DynamicListView;->getFirstVisiblePosition()I

    move-result v3

    .line 969
    iget-object v0, p0, Lmiui/widget/DynamicListView$1;->hq:Lmiui/widget/DynamicListView;

    invoke-virtual {v0}, Lmiui/widget/DynamicListView;->getChildCount()I

    move-result v4

    .line 970
    new-instance v5, Ljava/util/ArrayList;

    invoke-direct {v5}, Ljava/util/ArrayList;-><init>()V

    move v1, v2

    .line 971
    :goto_0
    if-ge v1, v4, :cond_2

    .line 972
    iget-object v0, p0, Lmiui/widget/DynamicListView$1;->hq:Lmiui/widget/DynamicListView;

    invoke-virtual {v0, v1}, Lmiui/widget/DynamicListView;->getChildAt(I)Landroid/view/View;

    move-result-object v6

    .line 973
    sget-object v0, Landroid/view/View;->TRANSLATION_Y:Landroid/util/Property;

    new-array v7, v9, [F

    const/4 v8, 0x0

    aput v8, v7, v2

    invoke-static {v6, v0, v7}, Landroid/animation/ObjectAnimator;->ofFloat(Ljava/lang/Object;Landroid/util/Property;[F)Landroid/animation/ObjectAnimator;

    move-result-object v0

    .line 975
    new-instance v7, Lmiui/widget/DynamicListView$1$2;

    invoke-direct {v7, p0, v6}, Lmiui/widget/DynamicListView$1$2;-><init>(Lmiui/widget/DynamicListView$1;Landroid/view/View;)V

    invoke-virtual {v0, v7}, Landroid/animation/ObjectAnimator;->addListener(Landroid/animation/Animator$AnimatorListener;)V

    .line 997
    invoke-interface {v5, v0}, Ljava/util/List;->add(Ljava/lang/Object;)Z

    .line 998
    add-int v0, v3, v1

    .line 999
    iget-object v7, p0, Lmiui/widget/DynamicListView$1;->ho:Landroid/widget/BaseAdapter;

    invoke-virtual {v7, v0}, Landroid/widget/BaseAdapter;->getItemId(I)J

    move-result-wide v7

    .line 1000
    iget-object v0, p0, Lmiui/widget/DynamicListView$1;->hq:Lmiui/widget/DynamicListView;

    invoke-static {v0}, Lmiui/widget/DynamicListView;->n(Lmiui/widget/DynamicListView;)Ljava/util/Map;

    move-result-object v0

    invoke-static {v7, v8}, Ljava/lang/Long;->valueOf(J)Ljava/lang/Long;

    move-result-object v7

    invoke-interface {v0, v7}, Ljava/util/Map;->get(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Ljava/lang/Integer;

    .line 1001
    invoke-virtual {v6}, Landroid/view/View;->getTop()I

    move-result v7

    .line 1002
    if-eqz v0, :cond_1

    .line 1003
    invoke-virtual {v0}, Ljava/lang/Integer;->intValue()I

    move-result v8

    if-eq v8, v7, :cond_0

    .line 1004
    invoke-virtual {v0}, Ljava/lang/Integer;->intValue()I

    move-result v0

    sub-int/2addr v0, v7

    .line 1005
    int-to-float v0, v0

    invoke-virtual {v6, v0}, Landroid/view/View;->setTranslationY(F)V

    .line 971
    :cond_0
    :goto_1
    add-int/lit8 v0, v1, 0x1

    move v1, v0

    goto :goto_0

    .line 1013
    :cond_1
    invoke-virtual {v6}, Landroid/view/View;->getHeight()I

    move-result v0

    iget-object v8, p0, Lmiui/widget/DynamicListView$1;->hq:Lmiui/widget/DynamicListView;

    invoke-virtual {v8}, Lmiui/widget/DynamicListView;->getDividerHeight()I

    move-result v8

    add-int/2addr v0, v8

    .line 1015
    iget-object v8, p0, Lmiui/widget/DynamicListView$1;->hp:Ljava/util/List;

    invoke-interface {v8}, Ljava/util/List;->size()I

    move-result v8

    mul-int/2addr v0, v8

    add-int/2addr v0, v7

    invoke-static {v0}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object v0

    .line 1016
    invoke-virtual {v0}, Ljava/lang/Integer;->intValue()I

    move-result v0

    sub-int/2addr v0, v7

    .line 1017
    int-to-float v0, v0

    invoke-virtual {v6, v0}, Landroid/view/View;->setTranslationY(F)V

    goto :goto_1

    .line 1020
    :cond_2
    iget-object v0, p0, Lmiui/widget/DynamicListView$1;->hq:Lmiui/widget/DynamicListView;

    invoke-static {v0}, Lmiui/widget/DynamicListView;->n(Lmiui/widget/DynamicListView;)Ljava/util/Map;

    move-result-object v0

    invoke-interface {v0}, Ljava/util/Map;->clear()V

    .line 1021
    new-instance v0, Landroid/animation/AnimatorSet;

    invoke-direct {v0}, Landroid/animation/AnimatorSet;-><init>()V

    .line 1022
    invoke-virtual {v0, v5}, Landroid/animation/AnimatorSet;->playTogether(Ljava/util/Collection;)V

    .line 1023
    new-instance v1, Lmiui/widget/DynamicListView$1$1;

    invoke-direct {v1, p0}, Lmiui/widget/DynamicListView$1$1;-><init>(Lmiui/widget/DynamicListView$1;)V

    invoke-virtual {v0, v1}, Landroid/animation/AnimatorSet;->addListener(Landroid/animation/Animator$AnimatorListener;)V

    .line 1030
    iget-object v1, p0, Lmiui/widget/DynamicListView$1;->hq:Lmiui/widget/DynamicListView;

    invoke-static {v1}, Lmiui/widget/DynamicListView;->l(Lmiui/widget/DynamicListView;)J

    move-result-wide v1

    invoke-virtual {v0, v1, v2}, Landroid/animation/AnimatorSet;->setDuration(J)Landroid/animation/AnimatorSet;

    .line 1031
    invoke-virtual {v0}, Landroid/animation/AnimatorSet;->start()V

    .line 1032
    return v9
.end method
