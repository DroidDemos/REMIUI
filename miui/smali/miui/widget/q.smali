.class Lmiui/widget/q;
.super Ljava/lang/Object;
.source "SourceFile"

# interfaces
.implements Ljava/lang/Runnable;


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lmiui/widget/DynamicListView;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x0
    name = null
.end annotation


# instance fields
.field final synthetic hq:Lmiui/widget/DynamicListView;


# direct methods
.method constructor <init>(Lmiui/widget/DynamicListView;)V
    .locals 0

    .prologue
    .line 181
    iput-object p1, p0, Lmiui/widget/q;->hq:Lmiui/widget/DynamicListView;

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method


# virtual methods
.method public run()V
    .locals 3

    .prologue
    .line 184
    iget-object v0, p0, Lmiui/widget/q;->hq:Lmiui/widget/DynamicListView;

    invoke-static {v0}, Lmiui/widget/DynamicListView;->c(Lmiui/widget/DynamicListView;)Z

    move-result v0

    if-eqz v0, :cond_0

    .line 185
    iget-object v0, p0, Lmiui/widget/q;->hq:Lmiui/widget/DynamicListView;

    iget-object v1, p0, Lmiui/widget/q;->hq:Lmiui/widget/DynamicListView;

    invoke-static {v1}, Lmiui/widget/DynamicListView;->d(Lmiui/widget/DynamicListView;)I

    move-result v1

    const/16 v2, 0xa

    invoke-virtual {v0, v1, v2}, Lmiui/widget/DynamicListView;->smoothScrollBy(II)V

    .line 186
    iget-object v0, p0, Lmiui/widget/q;->hq:Lmiui/widget/DynamicListView;

    invoke-virtual {v0, p0}, Lmiui/widget/DynamicListView;->removeCallbacks(Ljava/lang/Runnable;)Z

    .line 187
    iget-object v0, p0, Lmiui/widget/q;->hq:Lmiui/widget/DynamicListView;

    const-wide/16 v1, 0x5

    invoke-virtual {v0, p0, v1, v2}, Lmiui/widget/DynamicListView;->postDelayed(Ljava/lang/Runnable;J)Z

    .line 189
    :cond_0
    return-void
.end method
