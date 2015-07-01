.class Lmiui/widget/p;
.super Ljava/lang/Object;
.source "SourceFile"

# interfaces
.implements Landroid/widget/AbsListView$OnScrollListener;


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lmiui/widget/DynamicListView;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x0
    name = null
.end annotation


# instance fields
.field private PU:I

.field private PV:I

.field private PX:I

.field private PZ:I

.field private Qa:I

.field final synthetic hq:Lmiui/widget/DynamicListView;


# direct methods
.method constructor <init>(Lmiui/widget/DynamicListView;)V
    .locals 1

    .prologue
    const/4 v0, -0x1

    .line 200
    iput-object p1, p0, Lmiui/widget/p;->hq:Lmiui/widget/DynamicListView;

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    .line 202
    iput v0, p0, Lmiui/widget/p;->PU:I

    .line 204
    iput v0, p0, Lmiui/widget/p;->PV:I

    return-void
.end method

.method private eB()V
    .locals 1

    .prologue
    .line 253
    iget v0, p0, Lmiui/widget/p;->PZ:I

    if-lez v0, :cond_0

    iget v0, p0, Lmiui/widget/p;->Qa:I

    if-nez v0, :cond_0

    .line 255
    iget-object v0, p0, Lmiui/widget/p;->hq:Lmiui/widget/DynamicListView;

    invoke-static {v0}, Lmiui/widget/DynamicListView;->f(Lmiui/widget/DynamicListView;)Z

    move-result v0

    if-eqz v0, :cond_1

    iget-object v0, p0, Lmiui/widget/p;->hq:Lmiui/widget/DynamicListView;

    invoke-static {v0}, Lmiui/widget/DynamicListView;->c(Lmiui/widget/DynamicListView;)Z

    move-result v0

    if-eqz v0, :cond_1

    .line 256
    iget-object v0, p0, Lmiui/widget/p;->hq:Lmiui/widget/DynamicListView;

    invoke-static {v0}, Lmiui/widget/DynamicListView;->g(Lmiui/widget/DynamicListView;)V

    .line 261
    :cond_0
    :goto_0
    return-void

    .line 257
    :cond_1
    iget-object v0, p0, Lmiui/widget/p;->hq:Lmiui/widget/DynamicListView;

    invoke-static {v0}, Lmiui/widget/DynamicListView;->h(Lmiui/widget/DynamicListView;)Z

    move-result v0

    if-eqz v0, :cond_0

    .line 258
    iget-object v0, p0, Lmiui/widget/p;->hq:Lmiui/widget/DynamicListView;

    invoke-static {v0}, Lmiui/widget/DynamicListView;->i(Lmiui/widget/DynamicListView;)V

    goto :goto_0
.end method


# virtual methods
.method public eC()V
    .locals 4

    .prologue
    .line 269
    iget v0, p0, Lmiui/widget/p;->PX:I

    iget v1, p0, Lmiui/widget/p;->PU:I

    if-eq v0, v1, :cond_0

    .line 270
    iget-object v0, p0, Lmiui/widget/p;->hq:Lmiui/widget/DynamicListView;

    invoke-static {v0}, Lmiui/widget/DynamicListView;->f(Lmiui/widget/DynamicListView;)Z

    move-result v0

    if-eqz v0, :cond_0

    iget-object v0, p0, Lmiui/widget/p;->hq:Lmiui/widget/DynamicListView;

    invoke-static {v0}, Lmiui/widget/DynamicListView;->a(Lmiui/widget/DynamicListView;)J

    move-result-wide v0

    const-wide/16 v2, -0x1

    cmp-long v0, v0, v2

    if-eqz v0, :cond_0

    .line 271
    iget-object v0, p0, Lmiui/widget/p;->hq:Lmiui/widget/DynamicListView;

    iget-object v1, p0, Lmiui/widget/p;->hq:Lmiui/widget/DynamicListView;

    invoke-static {v1}, Lmiui/widget/DynamicListView;->a(Lmiui/widget/DynamicListView;)J

    move-result-wide v1

    invoke-static {v0, v1, v2}, Lmiui/widget/DynamicListView;->d(Lmiui/widget/DynamicListView;J)V

    .line 272
    iget-object v0, p0, Lmiui/widget/p;->hq:Lmiui/widget/DynamicListView;

    invoke-static {v0}, Lmiui/widget/DynamicListView;->j(Lmiui/widget/DynamicListView;)V

    .line 275
    :cond_0
    return-void
.end method

.method public eD()V
    .locals 4

    .prologue
    .line 283
    iget v0, p0, Lmiui/widget/p;->PX:I

    iget v1, p0, Lmiui/widget/p;->PZ:I

    add-int/2addr v0, v1

    .line 285
    iget v1, p0, Lmiui/widget/p;->PU:I

    iget v2, p0, Lmiui/widget/p;->PV:I

    add-int/2addr v1, v2

    .line 287
    if-eq v0, v1, :cond_0

    .line 288
    iget-object v0, p0, Lmiui/widget/p;->hq:Lmiui/widget/DynamicListView;

    invoke-static {v0}, Lmiui/widget/DynamicListView;->f(Lmiui/widget/DynamicListView;)Z

    move-result v0

    if-eqz v0, :cond_0

    iget-object v0, p0, Lmiui/widget/p;->hq:Lmiui/widget/DynamicListView;

    invoke-static {v0}, Lmiui/widget/DynamicListView;->a(Lmiui/widget/DynamicListView;)J

    move-result-wide v0

    const-wide/16 v2, -0x1

    cmp-long v0, v0, v2

    if-eqz v0, :cond_0

    .line 289
    iget-object v0, p0, Lmiui/widget/p;->hq:Lmiui/widget/DynamicListView;

    iget-object v1, p0, Lmiui/widget/p;->hq:Lmiui/widget/DynamicListView;

    invoke-static {v1}, Lmiui/widget/DynamicListView;->a(Lmiui/widget/DynamicListView;)J

    move-result-wide v1

    invoke-static {v0, v1, v2}, Lmiui/widget/DynamicListView;->d(Lmiui/widget/DynamicListView;J)V

    .line 290
    iget-object v0, p0, Lmiui/widget/p;->hq:Lmiui/widget/DynamicListView;

    invoke-static {v0}, Lmiui/widget/DynamicListView;->j(Lmiui/widget/DynamicListView;)V

    .line 293
    :cond_0
    return-void
.end method

.method public onScroll(Landroid/widget/AbsListView;III)V
    .locals 2

    .prologue
    const/4 v1, -0x1

    .line 215
    iget-object v0, p0, Lmiui/widget/p;->hq:Lmiui/widget/DynamicListView;

    invoke-static {v0}, Lmiui/widget/DynamicListView;->e(Lmiui/widget/DynamicListView;)Landroid/widget/AbsListView$OnScrollListener;

    move-result-object v0

    if-eqz v0, :cond_0

    .line 216
    iget-object v0, p0, Lmiui/widget/p;->hq:Lmiui/widget/DynamicListView;

    invoke-static {v0}, Lmiui/widget/DynamicListView;->e(Lmiui/widget/DynamicListView;)Landroid/widget/AbsListView$OnScrollListener;

    move-result-object v0

    invoke-interface {v0, p1, p2, p3, p4}, Landroid/widget/AbsListView$OnScrollListener;->onScroll(Landroid/widget/AbsListView;III)V

    .line 220
    :cond_0
    iput p2, p0, Lmiui/widget/p;->PX:I

    .line 221
    iput p3, p0, Lmiui/widget/p;->PZ:I

    .line 222
    iget v0, p0, Lmiui/widget/p;->PU:I

    if-ne v0, v1, :cond_1

    iget v0, p0, Lmiui/widget/p;->PX:I

    :goto_0
    iput v0, p0, Lmiui/widget/p;->PU:I

    .line 225
    iget v0, p0, Lmiui/widget/p;->PV:I

    if-ne v0, v1, :cond_2

    iget v0, p0, Lmiui/widget/p;->PZ:I

    :goto_1
    iput v0, p0, Lmiui/widget/p;->PV:I

    .line 228
    invoke-virtual {p0}, Lmiui/widget/p;->eC()V

    .line 229
    invoke-virtual {p0}, Lmiui/widget/p;->eD()V

    .line 230
    iget v0, p0, Lmiui/widget/p;->PX:I

    iput v0, p0, Lmiui/widget/p;->PU:I

    .line 231
    iget v0, p0, Lmiui/widget/p;->PZ:I

    iput v0, p0, Lmiui/widget/p;->PV:I

    .line 232
    return-void

    .line 222
    :cond_1
    iget v0, p0, Lmiui/widget/p;->PU:I

    goto :goto_0

    .line 225
    :cond_2
    iget v0, p0, Lmiui/widget/p;->PV:I

    goto :goto_1
.end method

.method public onScrollStateChanged(Landroid/widget/AbsListView;I)V
    .locals 1

    .prologue
    .line 236
    iget-object v0, p0, Lmiui/widget/p;->hq:Lmiui/widget/DynamicListView;

    invoke-static {v0}, Lmiui/widget/DynamicListView;->e(Lmiui/widget/DynamicListView;)Landroid/widget/AbsListView$OnScrollListener;

    move-result-object v0

    if-eqz v0, :cond_0

    .line 237
    iget-object v0, p0, Lmiui/widget/p;->hq:Lmiui/widget/DynamicListView;

    invoke-static {v0}, Lmiui/widget/DynamicListView;->e(Lmiui/widget/DynamicListView;)Landroid/widget/AbsListView$OnScrollListener;

    move-result-object v0

    invoke-interface {v0, p1, p2}, Landroid/widget/AbsListView$OnScrollListener;->onScrollStateChanged(Landroid/widget/AbsListView;I)V

    .line 239
    :cond_0
    iput p2, p0, Lmiui/widget/p;->Qa:I

    .line 240
    iget-object v0, p0, Lmiui/widget/p;->hq:Lmiui/widget/DynamicListView;

    invoke-static {v0, p2}, Lmiui/widget/DynamicListView;->a(Lmiui/widget/DynamicListView;I)I

    .line 241
    invoke-direct {p0}, Lmiui/widget/p;->eB()V

    .line 242
    return-void
.end method
