.class Lmiui/widget/NumberPicker$e;
.super Ljava/lang/Object;
.source "SourceFile"

# interfaces
.implements Ljava/lang/Runnable;


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lmiui/widget/NumberPicker;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x0
    name = "e"
.end annotation


# static fields
.field public static final Me:I = 0x1

.field public static final Mf:I = 0x2


# instance fields
.field private final Mg:I

.field private final Mh:I

.field private Mi:I

.field private Mj:I

.field final synthetic aC:Lmiui/widget/NumberPicker;


# direct methods
.method constructor <init>(Lmiui/widget/NumberPicker;)V
    .locals 1

    .prologue
    .line 1933
    iput-object p1, p0, Lmiui/widget/NumberPicker$e;->aC:Lmiui/widget/NumberPicker;

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    .line 1937
    const/4 v0, 0x1

    iput v0, p0, Lmiui/widget/NumberPicker$e;->Mg:I

    .line 1938
    const/4 v0, 0x2

    iput v0, p0, Lmiui/widget/NumberPicker$e;->Mh:I

    return-void
.end method


# virtual methods
.method public ak(I)V
    .locals 3

    .prologue
    .line 1958
    invoke-virtual {p0}, Lmiui/widget/NumberPicker$e;->cancel()V

    .line 1959
    const/4 v0, 0x1

    iput v0, p0, Lmiui/widget/NumberPicker$e;->Mj:I

    .line 1960
    iput p1, p0, Lmiui/widget/NumberPicker$e;->Mi:I

    .line 1961
    iget-object v0, p0, Lmiui/widget/NumberPicker$e;->aC:Lmiui/widget/NumberPicker;

    invoke-static {}, Landroid/view/ViewConfiguration;->getTapTimeout()I

    move-result v1

    int-to-long v1, v1

    invoke-virtual {v0, p0, v1, v2}, Lmiui/widget/NumberPicker;->postDelayed(Ljava/lang/Runnable;J)Z

    .line 1962
    return-void
.end method

.method public al(I)V
    .locals 1

    .prologue
    .line 1965
    invoke-virtual {p0}, Lmiui/widget/NumberPicker$e;->cancel()V

    .line 1966
    const/4 v0, 0x2

    iput v0, p0, Lmiui/widget/NumberPicker$e;->Mj:I

    .line 1967
    iput p1, p0, Lmiui/widget/NumberPicker$e;->Mi:I

    .line 1968
    iget-object v0, p0, Lmiui/widget/NumberPicker$e;->aC:Lmiui/widget/NumberPicker;

    invoke-virtual {v0, p0}, Lmiui/widget/NumberPicker;->post(Ljava/lang/Runnable;)Z

    .line 1969
    return-void
.end method

.method public cancel()V
    .locals 5

    .prologue
    const/4 v4, 0x0

    .line 1944
    iput v4, p0, Lmiui/widget/NumberPicker$e;->Mj:I

    .line 1945
    iput v4, p0, Lmiui/widget/NumberPicker$e;->Mi:I

    .line 1946
    iget-object v0, p0, Lmiui/widget/NumberPicker$e;->aC:Lmiui/widget/NumberPicker;

    invoke-virtual {v0, p0}, Lmiui/widget/NumberPicker;->removeCallbacks(Ljava/lang/Runnable;)Z

    .line 1947
    iget-object v0, p0, Lmiui/widget/NumberPicker$e;->aC:Lmiui/widget/NumberPicker;

    invoke-static {v0}, Lmiui/widget/NumberPicker;->d(Lmiui/widget/NumberPicker;)Z

    move-result v0

    if-eqz v0, :cond_0

    .line 1948
    iget-object v0, p0, Lmiui/widget/NumberPicker$e;->aC:Lmiui/widget/NumberPicker;

    invoke-static {v0, v4}, Lmiui/widget/NumberPicker;->a(Lmiui/widget/NumberPicker;Z)Z

    .line 1949
    iget-object v0, p0, Lmiui/widget/NumberPicker$e;->aC:Lmiui/widget/NumberPicker;

    iget-object v1, p0, Lmiui/widget/NumberPicker$e;->aC:Lmiui/widget/NumberPicker;

    invoke-static {v1}, Lmiui/widget/NumberPicker;->e(Lmiui/widget/NumberPicker;)I

    move-result v1

    iget-object v2, p0, Lmiui/widget/NumberPicker$e;->aC:Lmiui/widget/NumberPicker;

    invoke-virtual {v2}, Lmiui/widget/NumberPicker;->getRight()I

    move-result v2

    iget-object v3, p0, Lmiui/widget/NumberPicker$e;->aC:Lmiui/widget/NumberPicker;

    invoke-virtual {v3}, Lmiui/widget/NumberPicker;->getBottom()I

    move-result v3

    invoke-virtual {v0, v4, v1, v2, v3}, Lmiui/widget/NumberPicker;->invalidate(IIII)V

    .line 1951
    :cond_0
    iget-object v0, p0, Lmiui/widget/NumberPicker$e;->aC:Lmiui/widget/NumberPicker;

    invoke-static {v0}, Lmiui/widget/NumberPicker;->f(Lmiui/widget/NumberPicker;)Z

    move-result v0

    if-eqz v0, :cond_1

    .line 1952
    iget-object v0, p0, Lmiui/widget/NumberPicker$e;->aC:Lmiui/widget/NumberPicker;

    invoke-static {v0, v4}, Lmiui/widget/NumberPicker;->b(Lmiui/widget/NumberPicker;Z)Z

    .line 1953
    iget-object v0, p0, Lmiui/widget/NumberPicker$e;->aC:Lmiui/widget/NumberPicker;

    iget-object v1, p0, Lmiui/widget/NumberPicker$e;->aC:Lmiui/widget/NumberPicker;

    invoke-virtual {v1}, Lmiui/widget/NumberPicker;->getRight()I

    move-result v1

    iget-object v2, p0, Lmiui/widget/NumberPicker$e;->aC:Lmiui/widget/NumberPicker;

    invoke-static {v2}, Lmiui/widget/NumberPicker;->g(Lmiui/widget/NumberPicker;)I

    move-result v2

    invoke-virtual {v0, v4, v4, v1, v2}, Lmiui/widget/NumberPicker;->invalidate(IIII)V

    .line 1955
    :cond_1
    return-void
.end method

.method public run()V
    .locals 5

    .prologue
    const/4 v3, 0x1

    const/4 v4, 0x0

    .line 1973
    iget v0, p0, Lmiui/widget/NumberPicker$e;->Mj:I

    packed-switch v0, :pswitch_data_0

    .line 2007
    :goto_0
    return-void

    .line 1975
    :pswitch_0
    iget v0, p0, Lmiui/widget/NumberPicker$e;->Mi:I

    packed-switch v0, :pswitch_data_1

    goto :goto_0

    .line 1977
    :pswitch_1
    iget-object v0, p0, Lmiui/widget/NumberPicker$e;->aC:Lmiui/widget/NumberPicker;

    invoke-static {v0, v3}, Lmiui/widget/NumberPicker;->a(Lmiui/widget/NumberPicker;Z)Z

    .line 1978
    iget-object v0, p0, Lmiui/widget/NumberPicker$e;->aC:Lmiui/widget/NumberPicker;

    iget-object v1, p0, Lmiui/widget/NumberPicker$e;->aC:Lmiui/widget/NumberPicker;

    invoke-static {v1}, Lmiui/widget/NumberPicker;->e(Lmiui/widget/NumberPicker;)I

    move-result v1

    iget-object v2, p0, Lmiui/widget/NumberPicker$e;->aC:Lmiui/widget/NumberPicker;

    invoke-virtual {v2}, Lmiui/widget/NumberPicker;->getRight()I

    move-result v2

    iget-object v3, p0, Lmiui/widget/NumberPicker$e;->aC:Lmiui/widget/NumberPicker;

    invoke-virtual {v3}, Lmiui/widget/NumberPicker;->getBottom()I

    move-result v3

    invoke-virtual {v0, v4, v1, v2, v3}, Lmiui/widget/NumberPicker;->invalidate(IIII)V

    goto :goto_0

    .line 1981
    :pswitch_2
    iget-object v0, p0, Lmiui/widget/NumberPicker$e;->aC:Lmiui/widget/NumberPicker;

    invoke-static {v0, v3}, Lmiui/widget/NumberPicker;->b(Lmiui/widget/NumberPicker;Z)Z

    .line 1982
    iget-object v0, p0, Lmiui/widget/NumberPicker$e;->aC:Lmiui/widget/NumberPicker;

    iget-object v1, p0, Lmiui/widget/NumberPicker$e;->aC:Lmiui/widget/NumberPicker;

    invoke-virtual {v1}, Lmiui/widget/NumberPicker;->getRight()I

    move-result v1

    iget-object v2, p0, Lmiui/widget/NumberPicker$e;->aC:Lmiui/widget/NumberPicker;

    invoke-static {v2}, Lmiui/widget/NumberPicker;->g(Lmiui/widget/NumberPicker;)I

    move-result v2

    invoke-virtual {v0, v4, v4, v1, v2}, Lmiui/widget/NumberPicker;->invalidate(IIII)V

    goto :goto_0

    .line 1987
    :pswitch_3
    iget v0, p0, Lmiui/widget/NumberPicker$e;->Mi:I

    packed-switch v0, :pswitch_data_2

    goto :goto_0

    .line 1989
    :pswitch_4
    iget-object v0, p0, Lmiui/widget/NumberPicker$e;->aC:Lmiui/widget/NumberPicker;

    invoke-static {v0}, Lmiui/widget/NumberPicker;->d(Lmiui/widget/NumberPicker;)Z

    move-result v0

    if-nez v0, :cond_0

    .line 1990
    iget-object v0, p0, Lmiui/widget/NumberPicker$e;->aC:Lmiui/widget/NumberPicker;

    invoke-static {}, Landroid/view/ViewConfiguration;->getPressedStateDuration()I

    move-result v1

    int-to-long v1, v1

    invoke-virtual {v0, p0, v1, v2}, Lmiui/widget/NumberPicker;->postDelayed(Ljava/lang/Runnable;J)Z

    .line 1993
    :cond_0
    iget-object v0, p0, Lmiui/widget/NumberPicker$e;->aC:Lmiui/widget/NumberPicker;

    invoke-static {v0, v3}, Lmiui/widget/NumberPicker;->a(Lmiui/widget/NumberPicker;I)Z

    .line 1994
    iget-object v0, p0, Lmiui/widget/NumberPicker$e;->aC:Lmiui/widget/NumberPicker;

    iget-object v1, p0, Lmiui/widget/NumberPicker$e;->aC:Lmiui/widget/NumberPicker;

    invoke-static {v1}, Lmiui/widget/NumberPicker;->e(Lmiui/widget/NumberPicker;)I

    move-result v1

    iget-object v2, p0, Lmiui/widget/NumberPicker$e;->aC:Lmiui/widget/NumberPicker;

    invoke-virtual {v2}, Lmiui/widget/NumberPicker;->getRight()I

    move-result v2

    iget-object v3, p0, Lmiui/widget/NumberPicker$e;->aC:Lmiui/widget/NumberPicker;

    invoke-virtual {v3}, Lmiui/widget/NumberPicker;->getBottom()I

    move-result v3

    invoke-virtual {v0, v4, v1, v2, v3}, Lmiui/widget/NumberPicker;->invalidate(IIII)V

    goto :goto_0

    .line 1997
    :pswitch_5
    iget-object v0, p0, Lmiui/widget/NumberPicker$e;->aC:Lmiui/widget/NumberPicker;

    invoke-static {v0}, Lmiui/widget/NumberPicker;->f(Lmiui/widget/NumberPicker;)Z

    move-result v0

    if-nez v0, :cond_1

    .line 1998
    iget-object v0, p0, Lmiui/widget/NumberPicker$e;->aC:Lmiui/widget/NumberPicker;

    invoke-static {}, Landroid/view/ViewConfiguration;->getPressedStateDuration()I

    move-result v1

    int-to-long v1, v1

    invoke-virtual {v0, p0, v1, v2}, Lmiui/widget/NumberPicker;->postDelayed(Ljava/lang/Runnable;J)Z

    .line 2001
    :cond_1
    iget-object v0, p0, Lmiui/widget/NumberPicker$e;->aC:Lmiui/widget/NumberPicker;

    invoke-static {v0, v3}, Lmiui/widget/NumberPicker;->b(Lmiui/widget/NumberPicker;I)Z

    .line 2002
    iget-object v0, p0, Lmiui/widget/NumberPicker$e;->aC:Lmiui/widget/NumberPicker;

    iget-object v1, p0, Lmiui/widget/NumberPicker$e;->aC:Lmiui/widget/NumberPicker;

    invoke-virtual {v1}, Lmiui/widget/NumberPicker;->getRight()I

    move-result v1

    iget-object v2, p0, Lmiui/widget/NumberPicker$e;->aC:Lmiui/widget/NumberPicker;

    invoke-static {v2}, Lmiui/widget/NumberPicker;->g(Lmiui/widget/NumberPicker;)I

    move-result v2

    invoke-virtual {v0, v4, v4, v1, v2}, Lmiui/widget/NumberPicker;->invalidate(IIII)V

    goto/16 :goto_0

    .line 1973
    nop

    :pswitch_data_0
    .packed-switch 0x1
        :pswitch_0
        :pswitch_3
    .end packed-switch

    .line 1975
    :pswitch_data_1
    .packed-switch 0x1
        :pswitch_1
        :pswitch_2
    .end packed-switch

    .line 1987
    :pswitch_data_2
    .packed-switch 0x1
        :pswitch_4
        :pswitch_5
    .end packed-switch
.end method
