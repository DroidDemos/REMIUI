.class public abstract Lcom/miui/internal/variable/Android_View_ViewGroup_class;
.super Lcom/miui/internal/util/ClassProxy;
.source "SourceFile"

# interfaces
.implements Lcom/miui/internal/variable/IManagedClassProxy;


# annotations
.annotation system Ldalvik/annotation/MemberClasses;
    value = {
        Lcom/miui/internal/variable/Android_View_ViewGroup_class$1;,
        Lcom/miui/internal/variable/Android_View_ViewGroup_class$Factory;
    }
.end annotation

.annotation system Ldalvik/annotation/Signature;
    value = {
        "Lcom/miui/internal/util/ClassProxy",
        "<",
        "Landroid/view/ViewGroup;",
        ">;",
        "Lcom/miui/internal/variable/IManagedClassProxy;"
    }
.end annotation


# direct methods
.method public constructor <init>()V
    .locals 1

    .prologue
    .line 18
    const-class v0, Landroid/view/ViewGroup;

    invoke-direct {p0, v0}, Lcom/miui/internal/util/ClassProxy;-><init>(Ljava/lang/Class;)V

    .line 19
    return-void
.end method


# virtual methods
.method public getTagChildrenSequenceState(Landroid/view/ViewGroup;)Z
    .locals 1

    .prologue
    .line 27
    invoke-static {}, Lcom/miui/internal/variable/Android_View_View_class$Factory;->getInstance()Lcom/miui/internal/variable/Android_View_View_class$Factory;

    move-result-object v0

    invoke-virtual {v0}, Lcom/miui/internal/variable/Android_View_View_class$Factory;->get()Lcom/miui/internal/variable/Android_View_View_class;

    move-result-object v0

    invoke-virtual {v0, p1}, Lcom/miui/internal/variable/Android_View_View_class;->getCustomizedValue(Landroid/view/View;)Lcom/miui/internal/variable/Android_View_View_class$ValuedSparseArray;

    move-result-object v0

    iget-boolean v0, v0, Lcom/miui/internal/variable/Android_View_View_class$ValuedSparseArray;->mTagChildrenSequenceState:Z

    return v0
.end method

.method public setTagChildrenSequenceState(Landroid/view/ViewGroup;Z)V
    .locals 1

    .prologue
    .line 22
    invoke-static {}, Lcom/miui/internal/variable/Android_View_View_class$Factory;->getInstance()Lcom/miui/internal/variable/Android_View_View_class$Factory;

    move-result-object v0

    invoke-virtual {v0}, Lcom/miui/internal/variable/Android_View_View_class$Factory;->get()Lcom/miui/internal/variable/Android_View_View_class;

    move-result-object v0

    invoke-virtual {v0, p1}, Lcom/miui/internal/variable/Android_View_View_class;->getCustomizedValue(Landroid/view/View;)Lcom/miui/internal/variable/Android_View_View_class$ValuedSparseArray;

    move-result-object v0

    iput-boolean p2, v0, Lcom/miui/internal/variable/Android_View_View_class$ValuedSparseArray;->mTagChildrenSequenceState:Z

    .line 24
    return-void
.end method
