.class public abstract Lcom/miui/internal/variable/Android_View_View_class;
.super Lcom/miui/internal/util/ClassProxy;
.source "SourceFile"

# interfaces
.implements Lcom/miui/internal/variable/IManagedClassProxy;


# annotations
.annotation system Ldalvik/annotation/MemberClasses;
    value = {
        Lcom/miui/internal/variable/Android_View_View_class$1;,
        Lcom/miui/internal/variable/Android_View_View_class$ValuedSparseArray;,
        Lcom/miui/internal/variable/Android_View_View_class$Factory;
    }
.end annotation

.annotation system Ldalvik/annotation/Signature;
    value = {
        "Lcom/miui/internal/util/ClassProxy",
        "<",
        "Landroid/view/View;",
        ">;",
        "Lcom/miui/internal/variable/IManagedClassProxy;"
    }
.end annotation


# static fields
.field private static qP:Landroid/util/SparseIntArray;

.field private static qQ:Landroid/util/SparseArray;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Landroid/util/SparseArray",
            "<[I>;"
        }
    .end annotation
.end field


# direct methods
.method static constructor <clinit>()V
    .locals 4

    .prologue
    const/4 v3, 0x1

    .line 19
    new-instance v0, Landroid/util/SparseIntArray;

    invoke-direct {v0}, Landroid/util/SparseIntArray;-><init>()V

    sput-object v0, Lcom/miui/internal/variable/Android_View_View_class;->qP:Landroid/util/SparseIntArray;

    .line 21
    new-instance v0, Landroid/util/SparseArray;

    invoke-direct {v0}, Landroid/util/SparseArray;-><init>()V

    sput-object v0, Lcom/miui/internal/variable/Android_View_View_class;->qQ:Landroid/util/SparseArray;

    .line 24
    sget-object v0, Lcom/miui/internal/variable/Android_View_View_class;->qP:Landroid/util/SparseIntArray;

    const v1, 0x101009c

    invoke-virtual {v0, v1, v3}, Landroid/util/SparseIntArray;->put(II)V

    .line 25
    sget-object v0, Lcom/miui/internal/variable/Android_View_View_class;->qP:Landroid/util/SparseIntArray;

    const v1, 0x101009e

    sget-object v2, Lcom/miui/internal/variable/Android_View_View_class;->qP:Landroid/util/SparseIntArray;

    invoke-virtual {v2}, Landroid/util/SparseIntArray;->size()I

    move-result v2

    shl-int v2, v3, v2

    invoke-virtual {v0, v1, v2}, Landroid/util/SparseIntArray;->put(II)V

    .line 26
    sget-object v0, Lcom/miui/internal/variable/Android_View_View_class;->qP:Landroid/util/SparseIntArray;

    const v1, 0x101009f

    sget-object v2, Lcom/miui/internal/variable/Android_View_View_class;->qP:Landroid/util/SparseIntArray;

    invoke-virtual {v2}, Landroid/util/SparseIntArray;->size()I

    move-result v2

    shl-int v2, v3, v2

    invoke-virtual {v0, v1, v2}, Landroid/util/SparseIntArray;->put(II)V

    .line 27
    sget-object v0, Lcom/miui/internal/variable/Android_View_View_class;->qP:Landroid/util/SparseIntArray;

    const v1, 0x10100a0

    sget-object v2, Lcom/miui/internal/variable/Android_View_View_class;->qP:Landroid/util/SparseIntArray;

    invoke-virtual {v2}, Landroid/util/SparseIntArray;->size()I

    move-result v2

    shl-int v2, v3, v2

    invoke-virtual {v0, v1, v2}, Landroid/util/SparseIntArray;->put(II)V

    .line 28
    sget-object v0, Lcom/miui/internal/variable/Android_View_View_class;->qP:Landroid/util/SparseIntArray;

    const v1, 0x10100a1

    sget-object v2, Lcom/miui/internal/variable/Android_View_View_class;->qP:Landroid/util/SparseIntArray;

    invoke-virtual {v2}, Landroid/util/SparseIntArray;->size()I

    move-result v2

    shl-int v2, v3, v2

    invoke-virtual {v0, v1, v2}, Landroid/util/SparseIntArray;->put(II)V

    .line 29
    sget-object v0, Lcom/miui/internal/variable/Android_View_View_class;->qP:Landroid/util/SparseIntArray;

    const v1, 0x10100a2

    sget-object v2, Lcom/miui/internal/variable/Android_View_View_class;->qP:Landroid/util/SparseIntArray;

    invoke-virtual {v2}, Landroid/util/SparseIntArray;->size()I

    move-result v2

    shl-int v2, v3, v2

    invoke-virtual {v0, v1, v2}, Landroid/util/SparseIntArray;->put(II)V

    .line 30
    sget-object v0, Lcom/miui/internal/variable/Android_View_View_class;->qP:Landroid/util/SparseIntArray;

    const v1, 0x10100a3

    sget-object v2, Lcom/miui/internal/variable/Android_View_View_class;->qP:Landroid/util/SparseIntArray;

    invoke-virtual {v2}, Landroid/util/SparseIntArray;->size()I

    move-result v2

    shl-int v2, v3, v2

    invoke-virtual {v0, v1, v2}, Landroid/util/SparseIntArray;->put(II)V

    .line 31
    sget-object v0, Lcom/miui/internal/variable/Android_View_View_class;->qP:Landroid/util/SparseIntArray;

    const v1, 0x10100a4

    sget-object v2, Lcom/miui/internal/variable/Android_View_View_class;->qP:Landroid/util/SparseIntArray;

    invoke-virtual {v2}, Landroid/util/SparseIntArray;->size()I

    move-result v2

    shl-int v2, v3, v2

    invoke-virtual {v0, v1, v2}, Landroid/util/SparseIntArray;->put(II)V

    .line 32
    sget-object v0, Lcom/miui/internal/variable/Android_View_View_class;->qP:Landroid/util/SparseIntArray;

    const v1, 0x10100a5

    sget-object v2, Lcom/miui/internal/variable/Android_View_View_class;->qP:Landroid/util/SparseIntArray;

    invoke-virtual {v2}, Landroid/util/SparseIntArray;->size()I

    move-result v2

    shl-int v2, v3, v2

    invoke-virtual {v0, v1, v2}, Landroid/util/SparseIntArray;->put(II)V

    .line 33
    sget-object v0, Lcom/miui/internal/variable/Android_View_View_class;->qP:Landroid/util/SparseIntArray;

    const v1, 0x10100a6

    sget-object v2, Lcom/miui/internal/variable/Android_View_View_class;->qP:Landroid/util/SparseIntArray;

    invoke-virtual {v2}, Landroid/util/SparseIntArray;->size()I

    move-result v2

    shl-int v2, v3, v2

    invoke-virtual {v0, v1, v2}, Landroid/util/SparseIntArray;->put(II)V

    .line 34
    sget-object v0, Lcom/miui/internal/variable/Android_View_View_class;->qP:Landroid/util/SparseIntArray;

    const v1, 0x10100a7

    sget-object v2, Lcom/miui/internal/variable/Android_View_View_class;->qP:Landroid/util/SparseIntArray;

    invoke-virtual {v2}, Landroid/util/SparseIntArray;->size()I

    move-result v2

    shl-int v2, v3, v2

    invoke-virtual {v0, v1, v2}, Landroid/util/SparseIntArray;->put(II)V

    .line 35
    sget-object v0, Lcom/miui/internal/variable/Android_View_View_class;->qP:Landroid/util/SparseIntArray;

    const v1, 0x10100a9

    sget-object v2, Lcom/miui/internal/variable/Android_View_View_class;->qP:Landroid/util/SparseIntArray;

    invoke-virtual {v2}, Landroid/util/SparseIntArray;->size()I

    move-result v2

    shl-int v2, v3, v2

    invoke-virtual {v0, v1, v2}, Landroid/util/SparseIntArray;->put(II)V

    .line 36
    sget-object v0, Lcom/miui/internal/variable/Android_View_View_class;->qP:Landroid/util/SparseIntArray;

    const v1, 0x10102fe

    sget-object v2, Lcom/miui/internal/variable/Android_View_View_class;->qP:Landroid/util/SparseIntArray;

    invoke-virtual {v2}, Landroid/util/SparseIntArray;->size()I

    move-result v2

    shl-int v2, v3, v2

    invoke-virtual {v0, v1, v2}, Landroid/util/SparseIntArray;->put(II)V

    .line 37
    sget-object v0, Lcom/miui/internal/variable/Android_View_View_class;->qP:Landroid/util/SparseIntArray;

    sget v1, Lmiui/R$attr;->state_first_h:I

    sget-object v2, Lcom/miui/internal/variable/Android_View_View_class;->qP:Landroid/util/SparseIntArray;

    invoke-virtual {v2}, Landroid/util/SparseIntArray;->size()I

    move-result v2

    shl-int v2, v3, v2

    invoke-virtual {v0, v1, v2}, Landroid/util/SparseIntArray;->put(II)V

    .line 38
    sget-object v0, Lcom/miui/internal/variable/Android_View_View_class;->qP:Landroid/util/SparseIntArray;

    sget v1, Lmiui/R$attr;->state_middle_h:I

    sget-object v2, Lcom/miui/internal/variable/Android_View_View_class;->qP:Landroid/util/SparseIntArray;

    invoke-virtual {v2}, Landroid/util/SparseIntArray;->size()I

    move-result v2

    shl-int v2, v3, v2

    invoke-virtual {v0, v1, v2}, Landroid/util/SparseIntArray;->put(II)V

    .line 39
    sget-object v0, Lcom/miui/internal/variable/Android_View_View_class;->qP:Landroid/util/SparseIntArray;

    sget v1, Lmiui/R$attr;->state_last_h:I

    sget-object v2, Lcom/miui/internal/variable/Android_View_View_class;->qP:Landroid/util/SparseIntArray;

    invoke-virtual {v2}, Landroid/util/SparseIntArray;->size()I

    move-result v2

    shl-int v2, v3, v2

    invoke-virtual {v0, v1, v2}, Landroid/util/SparseIntArray;->put(II)V

    .line 40
    sget-object v0, Lcom/miui/internal/variable/Android_View_View_class;->qP:Landroid/util/SparseIntArray;

    sget v1, Lmiui/R$attr;->state_single_h:I

    sget-object v2, Lcom/miui/internal/variable/Android_View_View_class;->qP:Landroid/util/SparseIntArray;

    invoke-virtual {v2}, Landroid/util/SparseIntArray;->size()I

    move-result v2

    shl-int v2, v3, v2

    invoke-virtual {v0, v1, v2}, Landroid/util/SparseIntArray;->put(II)V

    .line 41
    sget-object v0, Lcom/miui/internal/variable/Android_View_View_class;->qP:Landroid/util/SparseIntArray;

    sget v1, Lmiui/R$attr;->state_first_v:I

    sget-object v2, Lcom/miui/internal/variable/Android_View_View_class;->qP:Landroid/util/SparseIntArray;

    invoke-virtual {v2}, Landroid/util/SparseIntArray;->size()I

    move-result v2

    shl-int v2, v3, v2

    invoke-virtual {v0, v1, v2}, Landroid/util/SparseIntArray;->put(II)V

    .line 42
    sget-object v0, Lcom/miui/internal/variable/Android_View_View_class;->qP:Landroid/util/SparseIntArray;

    sget v1, Lmiui/R$attr;->state_middle_v:I

    sget-object v2, Lcom/miui/internal/variable/Android_View_View_class;->qP:Landroid/util/SparseIntArray;

    invoke-virtual {v2}, Landroid/util/SparseIntArray;->size()I

    move-result v2

    shl-int v2, v3, v2

    invoke-virtual {v0, v1, v2}, Landroid/util/SparseIntArray;->put(II)V

    .line 43
    sget-object v0, Lcom/miui/internal/variable/Android_View_View_class;->qP:Landroid/util/SparseIntArray;

    sget v1, Lmiui/R$attr;->state_last_v:I

    sget-object v2, Lcom/miui/internal/variable/Android_View_View_class;->qP:Landroid/util/SparseIntArray;

    invoke-virtual {v2}, Landroid/util/SparseIntArray;->size()I

    move-result v2

    shl-int v2, v3, v2

    invoke-virtual {v0, v1, v2}, Landroid/util/SparseIntArray;->put(II)V

    .line 44
    sget-object v0, Lcom/miui/internal/variable/Android_View_View_class;->qP:Landroid/util/SparseIntArray;

    sget v1, Lmiui/R$attr;->state_single_v:I

    sget-object v2, Lcom/miui/internal/variable/Android_View_View_class;->qP:Landroid/util/SparseIntArray;

    invoke-virtual {v2}, Landroid/util/SparseIntArray;->size()I

    move-result v2

    shl-int v2, v3, v2

    invoke-virtual {v0, v1, v2}, Landroid/util/SparseIntArray;->put(II)V

    .line 45
    return-void
.end method

.method public constructor <init>()V
    .locals 1

    .prologue
    .line 48
    const-class v0, Landroid/view/View;

    invoke-direct {p0, v0}, Lcom/miui/internal/util/ClassProxy;-><init>(Ljava/lang/Class;)V

    .line 49
    return-void
.end method

.method private static I(I)I
    .locals 2

    .prologue
    const/4 v0, 0x0

    .line 80
    .line 81
    if-eqz p0, :cond_1

    .line 82
    sget-object v1, Lcom/miui/internal/variable/Android_View_View_class;->qP:Landroid/util/SparseIntArray;

    invoke-virtual {v1, p0, v0}, Landroid/util/SparseIntArray;->get(II)I

    move-result v0

    .line 83
    if-nez v0, :cond_1

    .line 84
    sget-object v0, Lcom/miui/internal/variable/Android_View_View_class;->qP:Landroid/util/SparseIntArray;

    invoke-virtual {v0}, Landroid/util/SparseIntArray;->size()I

    move-result v0

    const/16 v1, 0x20

    if-lt v0, v1, :cond_0

    .line 85
    new-instance v0, Ljava/lang/IllegalArgumentException;

    const-string v1, "State attribute cannot exceed 32!"

    invoke-direct {v0, v1}, Ljava/lang/IllegalArgumentException;-><init>(Ljava/lang/String;)V

    throw v0

    .line 87
    :cond_0
    const/4 v0, 0x1

    sget-object v1, Lcom/miui/internal/variable/Android_View_View_class;->qP:Landroid/util/SparseIntArray;

    invoke-virtual {v1}, Landroid/util/SparseIntArray;->size()I

    move-result v1

    shl-int/2addr v0, v1

    .line 88
    sget-object v1, Lcom/miui/internal/variable/Android_View_View_class;->qP:Landroid/util/SparseIntArray;

    invoke-virtual {v1, p0, v0}, Landroid/util/SparseIntArray;->put(II)V

    .line 93
    :cond_1
    return v0
.end method

.method private static a([I)I
    .locals 4

    .prologue
    const/4 v0, 0x0

    .line 97
    .line 98
    if-eqz p0, :cond_0

    .line 99
    array-length v3, p0

    move v1, v0

    :goto_0
    if-ge v1, v3, :cond_0

    aget v2, p0, v1

    .line 100
    invoke-static {v2}, Lcom/miui/internal/variable/Android_View_View_class;->I(I)I

    move-result v2

    or-int/2addr v2, v0

    .line 99
    add-int/lit8 v0, v1, 0x1

    move v1, v0

    move v0, v2

    goto :goto_0

    .line 103
    :cond_0
    return v0
.end method


# virtual methods
.method protected abstract getCustomizedValue(Landroid/view/View;)Lcom/miui/internal/variable/Android_View_View_class$ValuedSparseArray;
.end method

.method public getHorizontalState(Landroid/view/View;)I
    .locals 1

    .prologue
    .line 64
    invoke-virtual {p0, p1}, Lcom/miui/internal/variable/Android_View_View_class;->getCustomizedValue(Landroid/view/View;)Lcom/miui/internal/variable/Android_View_View_class$ValuedSparseArray;

    move-result-object v0

    iget v0, v0, Lcom/miui/internal/variable/Android_View_View_class$ValuedSparseArray;->mHorizontalState:I

    return v0
.end method

.method public getVerticalState(Landroid/view/View;)I
    .locals 1

    .prologue
    .line 72
    invoke-virtual {p0, p1}, Lcom/miui/internal/variable/Android_View_View_class;->getCustomizedValue(Landroid/view/View;)Lcom/miui/internal/variable/Android_View_View_class$ValuedSparseArray;

    move-result-object v0

    iget v0, v0, Lcom/miui/internal/variable/Android_View_View_class$ValuedSparseArray;->mVerticalState:I

    return v0
.end method

.method protected onCreateDrawableState(Landroid/view/View;[II)[I
    .locals 6

    .prologue
    const/4 v1, 0x0

    .line 107
    .line 108
    invoke-virtual {p0, p1}, Lcom/miui/internal/variable/Android_View_View_class;->getHorizontalState(Landroid/view/View;)I

    move-result v3

    .line 109
    invoke-virtual {p0, p1}, Lcom/miui/internal/variable/Android_View_View_class;->getVerticalState(Landroid/view/View;)I

    move-result v4

    .line 111
    if-eqz v3, :cond_5

    if-eqz v4, :cond_5

    .line 112
    invoke-static {p2}, Lcom/miui/internal/variable/Android_View_View_class;->a([I)I

    move-result v0

    invoke-static {v3}, Lcom/miui/internal/variable/Android_View_View_class;->I(I)I

    move-result v2

    or-int/2addr v0, v2

    invoke-static {v4}, Lcom/miui/internal/variable/Android_View_View_class;->I(I)I

    move-result v2

    or-int v5, v0, v2

    .line 113
    sget-object v0, Lcom/miui/internal/variable/Android_View_View_class;->qQ:Landroid/util/SparseArray;

    invoke-virtual {v0, v5}, Landroid/util/SparseArray;->get(I)Ljava/lang/Object;

    move-result-object v0

    check-cast v0, [I

    .line 114
    if-nez v0, :cond_4

    .line 115
    if-eqz p2, :cond_1

    array-length v0, p2

    .line 117
    :goto_0
    add-int/lit8 v2, v0, 0x2

    new-array v2, v2, [I

    .line 118
    if-eqz v0, :cond_0

    .line 119
    invoke-static {p2, v1, v2, v1, v0}, Ljava/lang/System;->arraycopy(Ljava/lang/Object;ILjava/lang/Object;II)V

    .line 121
    :cond_0
    aput v3, v2, v0

    .line 122
    add-int/lit8 v0, v0, 0x1

    aput v4, v2, v0

    .line 123
    sget-object v0, Lcom/miui/internal/variable/Android_View_View_class;->qQ:Landroid/util/SparseArray;

    invoke-virtual {v0, v5, v2}, Landroid/util/SparseArray;->put(ILjava/lang/Object;)V

    .line 127
    :goto_1
    if-eqz p3, :cond_3

    .line 128
    if-nez v2, :cond_2

    .line 129
    new-array v0, p3, [I

    .line 137
    :goto_2
    return-object v0

    :cond_1
    move v0, v1

    .line 115
    goto :goto_0

    .line 131
    :cond_2
    array-length v0, v2

    add-int/2addr v0, p3

    new-array v0, v0, [I

    .line 132
    array-length v3, v2

    invoke-static {v2, v1, v0, v1, v3}, Ljava/lang/System;->arraycopy(Ljava/lang/Object;ILjava/lang/Object;II)V

    goto :goto_2

    :cond_3
    move-object v0, v2

    goto :goto_2

    :cond_4
    move-object v2, v0

    goto :goto_1

    :cond_5
    move-object v2, p2

    goto :goto_1
.end method

.method public abstract relayout(Landroid/view/View;)V
.end method

.method public setHorizontalState(Landroid/view/View;I)V
    .locals 1

    .prologue
    .line 68
    invoke-virtual {p0, p1}, Lcom/miui/internal/variable/Android_View_View_class;->getCustomizedValue(Landroid/view/View;)Lcom/miui/internal/variable/Android_View_View_class$ValuedSparseArray;

    move-result-object v0

    iput p2, v0, Lcom/miui/internal/variable/Android_View_View_class$ValuedSparseArray;->mHorizontalState:I

    .line 69
    return-void
.end method

.method public abstract setLeftDirectly(Landroid/view/View;I)V
.end method

.method public abstract setRightDirectly(Landroid/view/View;I)V
.end method

.method public abstract setScrollXDirectly(Landroid/view/View;I)V
.end method

.method public abstract setScrollYDirectly(Landroid/view/View;I)V
.end method

.method public setVerticalState(Landroid/view/View;I)V
    .locals 1

    .prologue
    .line 76
    invoke-virtual {p0, p1}, Lcom/miui/internal/variable/Android_View_View_class;->getCustomizedValue(Landroid/view/View;)Lcom/miui/internal/variable/Android_View_View_class$ValuedSparseArray;

    move-result-object v0

    iput p2, v0, Lcom/miui/internal/variable/Android_View_View_class$ValuedSparseArray;->mVerticalState:I

    .line 77
    return-void
.end method
