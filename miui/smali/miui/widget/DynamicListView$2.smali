.class Lmiui/widget/DynamicListView$2;
.super Landroid/animation/AnimatorListenerAdapter;
.source "SourceFile"


# annotations
.annotation system Ldalvik/annotation/EnclosingMethod;
    value = Lmiui/widget/DynamicListView;->removeItems(Ljava/util/List;Ljava/util/List;)V
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x0
    name = null
.end annotation


# instance fields
.field final synthetic hp:Ljava/util/List;

.field final synthetic hq:Lmiui/widget/DynamicListView;

.field final synthetic hr:Ljava/util/List;


# direct methods
.method constructor <init>(Lmiui/widget/DynamicListView;Ljava/util/List;Ljava/util/List;)V
    .locals 0

    .prologue
    .line 906
    iput-object p1, p0, Lmiui/widget/DynamicListView$2;->hq:Lmiui/widget/DynamicListView;

    iput-object p2, p0, Lmiui/widget/DynamicListView$2;->hr:Ljava/util/List;

    iput-object p3, p0, Lmiui/widget/DynamicListView$2;->hp:Ljava/util/List;

    invoke-direct {p0}, Landroid/animation/AnimatorListenerAdapter;-><init>()V

    return-void
.end method


# virtual methods
.method public onAnimationEnd(Landroid/animation/Animator;)V
    .locals 3

    .prologue
    .line 909
    invoke-super {p0, p1}, Landroid/animation/AnimatorListenerAdapter;->onAnimationEnd(Landroid/animation/Animator;)V

    .line 910
    iget-object v0, p0, Lmiui/widget/DynamicListView$2;->hq:Lmiui/widget/DynamicListView;

    iget-object v1, p0, Lmiui/widget/DynamicListView$2;->hr:Ljava/util/List;

    iget-object v2, p0, Lmiui/widget/DynamicListView$2;->hp:Ljava/util/List;

    invoke-static {v0, v1, v2}, Lmiui/widget/DynamicListView;->a(Lmiui/widget/DynamicListView;Ljava/util/List;Ljava/util/List;)V

    .line 911
    return-void
.end method
