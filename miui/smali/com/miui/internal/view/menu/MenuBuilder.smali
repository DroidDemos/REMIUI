.class public Lcom/miui/internal/view/menu/MenuBuilder;
.super Ljava/lang/Object;
.source "SourceFile"

# interfaces
.implements Landroid/view/Menu;


# annotations
.annotation system Ldalvik/annotation/MemberClasses;
    value = {
        Lcom/miui/internal/view/menu/MenuBuilder$ItemInvoker;,
        Lcom/miui/internal/view/menu/MenuBuilder$Callback;
    }
.end annotation


# static fields
.field public static final CATEGORY_MASK:I = -0x10000

.field public static final CATEGORY_SHIFT:I = 0x10

.field private static final F:Ljava/lang/String; = "android:menu:presenters"

.field private static final G:Ljava/lang/String; = "android:menu:actionviewstates"

.field private static final H:Ljava/lang/String; = "android:menu:expandedactionview"

.field private static final I:[I

.field public static final USER_MASK:I = 0xffff


# instance fields
.field private final J:Landroid/content/res/Resources;

.field private K:Z

.field private L:Z

.field private M:Lcom/miui/internal/view/menu/MenuBuilder$Callback;

.field private N:Ljava/util/ArrayList;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Ljava/util/ArrayList",
            "<",
            "Lcom/miui/internal/view/menu/MenuItemImpl;",
            ">;"
        }
    .end annotation
.end field

.field private O:Ljava/util/ArrayList;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Ljava/util/ArrayList",
            "<",
            "Lcom/miui/internal/view/menu/MenuItemImpl;",
            ">;"
        }
    .end annotation
.end field

.field private P:Z

.field private Q:Ljava/util/ArrayList;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Ljava/util/ArrayList",
            "<",
            "Lcom/miui/internal/view/menu/MenuItemImpl;",
            ">;"
        }
    .end annotation
.end field

.field private R:Z

.field private S:I

.field private T:Landroid/view/ContextMenu$ContextMenuInfo;

.field U:Ljava/lang/CharSequence;

.field V:Landroid/graphics/drawable/Drawable;

.field W:Landroid/view/View;

.field private X:Z

.field private Y:Z

.field private Z:Z

.field private aa:Z

.field private ab:Ljava/util/ArrayList;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Ljava/util/ArrayList",
            "<",
            "Lcom/miui/internal/view/menu/MenuItemImpl;",
            ">;"
        }
    .end annotation
.end field

.field private ac:Ljava/util/concurrent/CopyOnWriteArrayList;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Ljava/util/concurrent/CopyOnWriteArrayList",
            "<",
            "Ljava/lang/ref/WeakReference",
            "<",
            "Lcom/miui/internal/view/menu/MenuPresenter;",
            ">;>;"
        }
    .end annotation
.end field

.field private ad:Lcom/miui/internal/view/menu/MenuItemImpl;

.field private mActionItems:Ljava/util/ArrayList;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Ljava/util/ArrayList",
            "<",
            "Lcom/miui/internal/view/menu/MenuItemImpl;",
            ">;"
        }
    .end annotation
.end field

.field private final mContext:Landroid/content/Context;


# direct methods
.method static constructor <clinit>()V
    .locals 1

    .prologue
    .line 63
    const/4 v0, 0x6

    new-array v0, v0, [I

    fill-array-data v0, :array_0

    sput-object v0, Lcom/miui/internal/view/menu/MenuBuilder;->I:[I

    return-void

    nop

    :array_0
    .array-data 4
        0x1
        0x4
        0x5
        0x3
        0x2
        0x0
    .end array-data
.end method

.method public constructor <init>(Landroid/content/Context;)V
    .locals 2

    .prologue
    const/4 v1, 0x1

    const/4 v0, 0x0

    .line 208
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    .line 130
    iput v0, p0, Lcom/miui/internal/view/menu/MenuBuilder;->S:I

    .line 158
    iput-boolean v0, p0, Lcom/miui/internal/view/menu/MenuBuilder;->X:Z

    .line 160
    iput-boolean v0, p0, Lcom/miui/internal/view/menu/MenuBuilder;->Y:Z

    .line 162
    iput-boolean v0, p0, Lcom/miui/internal/view/menu/MenuBuilder;->Z:Z

    .line 164
    iput-boolean v0, p0, Lcom/miui/internal/view/menu/MenuBuilder;->aa:Z

    .line 166
    new-instance v0, Ljava/util/ArrayList;

    invoke-direct {v0}, Ljava/util/ArrayList;-><init>()V

    iput-object v0, p0, Lcom/miui/internal/view/menu/MenuBuilder;->ab:Ljava/util/ArrayList;

    .line 168
    new-instance v0, Ljava/util/concurrent/CopyOnWriteArrayList;

    invoke-direct {v0}, Ljava/util/concurrent/CopyOnWriteArrayList;-><init>()V

    iput-object v0, p0, Lcom/miui/internal/view/menu/MenuBuilder;->ac:Ljava/util/concurrent/CopyOnWriteArrayList;

    .line 209
    iput-object p1, p0, Lcom/miui/internal/view/menu/MenuBuilder;->mContext:Landroid/content/Context;

    .line 210
    invoke-virtual {p1}, Landroid/content/Context;->getResources()Landroid/content/res/Resources;

    move-result-object v0

    iput-object v0, p0, Lcom/miui/internal/view/menu/MenuBuilder;->J:Landroid/content/res/Resources;

    .line 212
    new-instance v0, Ljava/util/ArrayList;

    invoke-direct {v0}, Ljava/util/ArrayList;-><init>()V

    iput-object v0, p0, Lcom/miui/internal/view/menu/MenuBuilder;->N:Ljava/util/ArrayList;

    .line 214
    new-instance v0, Ljava/util/ArrayList;

    invoke-direct {v0}, Ljava/util/ArrayList;-><init>()V

    iput-object v0, p0, Lcom/miui/internal/view/menu/MenuBuilder;->O:Ljava/util/ArrayList;

    .line 215
    iput-boolean v1, p0, Lcom/miui/internal/view/menu/MenuBuilder;->P:Z

    .line 217
    new-instance v0, Ljava/util/ArrayList;

    invoke-direct {v0}, Ljava/util/ArrayList;-><init>()V

    iput-object v0, p0, Lcom/miui/internal/view/menu/MenuBuilder;->mActionItems:Ljava/util/ArrayList;

    .line 218
    new-instance v0, Ljava/util/ArrayList;

    invoke-direct {v0}, Ljava/util/ArrayList;-><init>()V

    iput-object v0, p0, Lcom/miui/internal/view/menu/MenuBuilder;->Q:Ljava/util/ArrayList;

    .line 219
    iput-boolean v1, p0, Lcom/miui/internal/view/menu/MenuBuilder;->R:Z

    .line 221
    invoke-direct {p0, v1}, Lcom/miui/internal/view/menu/MenuBuilder;->b(Z)V

    .line 222
    return-void
.end method

.method private static a(Ljava/util/ArrayList;I)I
    .locals 2
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Ljava/util/ArrayList",
            "<",
            "Lcom/miui/internal/view/menu/MenuItemImpl;",
            ">;I)I"
        }
    .end annotation

    .prologue
    .line 795
    invoke-virtual {p0}, Ljava/util/ArrayList;->size()I

    move-result v0

    add-int/lit8 v0, v0, -0x1

    move v1, v0

    :goto_0
    if-ltz v1, :cond_1

    .line 796
    invoke-virtual {p0, v1}, Ljava/util/ArrayList;->get(I)Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Lcom/miui/internal/view/menu/MenuItemImpl;

    .line 797
    invoke-virtual {v0}, Lcom/miui/internal/view/menu/MenuItemImpl;->getOrdering()I

    move-result v0

    if-gt v0, p1, :cond_0

    .line 798
    add-int/lit8 v0, v1, 0x1

    .line 802
    :goto_1
    return v0

    .line 795
    :cond_0
    add-int/lit8 v0, v1, -0x1

    move v1, v0

    goto :goto_0

    .line 802
    :cond_1
    const/4 v0, 0x0

    goto :goto_1
.end method

.method private a(IIILjava/lang/CharSequence;)Landroid/view/MenuItem;
    .locals 8

    .prologue
    .line 416
    invoke-static {p3}, Lcom/miui/internal/view/menu/MenuBuilder;->c(I)I

    move-result v5

    .line 418
    new-instance v0, Lcom/miui/internal/view/menu/MenuItemImpl;

    iget v7, p0, Lcom/miui/internal/view/menu/MenuBuilder;->S:I

    move-object v1, p0

    move v2, p1

    move v3, p2

    move v4, p3

    move-object v6, p4

    invoke-direct/range {v0 .. v7}, Lcom/miui/internal/view/menu/MenuItemImpl;-><init>(Lcom/miui/internal/view/menu/MenuBuilder;IIIILjava/lang/CharSequence;I)V

    .line 421
    iget-object v1, p0, Lcom/miui/internal/view/menu/MenuBuilder;->T:Landroid/view/ContextMenu$ContextMenuInfo;

    if-eqz v1, :cond_0

    .line 423
    iget-object v1, p0, Lcom/miui/internal/view/menu/MenuBuilder;->T:Landroid/view/ContextMenu$ContextMenuInfo;

    invoke-virtual {v0, v1}, Lcom/miui/internal/view/menu/MenuItemImpl;->a(Landroid/view/ContextMenu$ContextMenuInfo;)V

    .line 426
    :cond_0
    iget-object v1, p0, Lcom/miui/internal/view/menu/MenuBuilder;->N:Ljava/util/ArrayList;

    iget-object v2, p0, Lcom/miui/internal/view/menu/MenuBuilder;->N:Ljava/util/ArrayList;

    invoke-static {v2, v5}, Lcom/miui/internal/view/menu/MenuBuilder;->a(Ljava/util/ArrayList;I)I

    move-result v2

    invoke-virtual {v1, v2, v0}, Ljava/util/ArrayList;->add(ILjava/lang/Object;)V

    .line 427
    const/4 v1, 0x1

    invoke-virtual {p0, v1}, Lcom/miui/internal/view/menu/MenuBuilder;->d(Z)V

    .line 429
    return-object v0
.end method

.method private a(ILjava/lang/CharSequence;ILandroid/graphics/drawable/Drawable;Landroid/view/View;)V
    .locals 3

    .prologue
    const/4 v2, 0x0

    .line 1151
    invoke-virtual {p0}, Lcom/miui/internal/view/menu/MenuBuilder;->getResources()Landroid/content/res/Resources;

    move-result-object v0

    .line 1153
    if-eqz p5, :cond_0

    .line 1154
    iput-object p5, p0, Lcom/miui/internal/view/menu/MenuBuilder;->W:Landroid/view/View;

    .line 1157
    iput-object v2, p0, Lcom/miui/internal/view/menu/MenuBuilder;->U:Ljava/lang/CharSequence;

    .line 1158
    iput-object v2, p0, Lcom/miui/internal/view/menu/MenuBuilder;->V:Landroid/graphics/drawable/Drawable;

    .line 1177
    :goto_0
    const/4 v0, 0x0

    invoke-virtual {p0, v0}, Lcom/miui/internal/view/menu/MenuBuilder;->d(Z)V

    .line 1178
    return-void

    .line 1160
    :cond_0
    if-lez p1, :cond_3

    .line 1161
    invoke-virtual {v0, p1}, Landroid/content/res/Resources;->getText(I)Ljava/lang/CharSequence;

    move-result-object v1

    iput-object v1, p0, Lcom/miui/internal/view/menu/MenuBuilder;->U:Ljava/lang/CharSequence;

    .line 1166
    :cond_1
    :goto_1
    if-lez p3, :cond_4

    .line 1167
    invoke-virtual {v0, p3}, Landroid/content/res/Resources;->getDrawable(I)Landroid/graphics/drawable/Drawable;

    move-result-object v0

    iput-object v0, p0, Lcom/miui/internal/view/menu/MenuBuilder;->V:Landroid/graphics/drawable/Drawable;

    .line 1173
    :cond_2
    :goto_2
    iput-object v2, p0, Lcom/miui/internal/view/menu/MenuBuilder;->W:Landroid/view/View;

    goto :goto_0

    .line 1162
    :cond_3
    if-eqz p2, :cond_1

    .line 1163
    iput-object p2, p0, Lcom/miui/internal/view/menu/MenuBuilder;->U:Ljava/lang/CharSequence;

    goto :goto_1

    .line 1168
    :cond_4
    if-eqz p4, :cond_2

    .line 1169
    iput-object p4, p0, Lcom/miui/internal/view/menu/MenuBuilder;->V:Landroid/graphics/drawable/Drawable;

    goto :goto_2
.end method

.method private a(IZ)V
    .locals 1

    .prologue
    .line 537
    if-ltz p1, :cond_0

    iget-object v0, p0, Lcom/miui/internal/view/menu/MenuBuilder;->N:Ljava/util/ArrayList;

    invoke-virtual {v0}, Ljava/util/ArrayList;->size()I

    move-result v0

    if-lt p1, v0, :cond_1

    .line 546
    :cond_0
    :goto_0
    return-void

    .line 541
    :cond_1
    iget-object v0, p0, Lcom/miui/internal/view/menu/MenuBuilder;->N:Ljava/util/ArrayList;

    invoke-virtual {v0, p1}, Ljava/util/ArrayList;->remove(I)Ljava/lang/Object;

    .line 543
    if-eqz p2, :cond_0

    .line 544
    const/4 v0, 0x1

    invoke-virtual {p0, v0}, Lcom/miui/internal/view/menu/MenuBuilder;->d(Z)V

    goto :goto_0
.end method

.method private a(Z)V
    .locals 3

    .prologue
    .line 257
    iget-object v0, p0, Lcom/miui/internal/view/menu/MenuBuilder;->ac:Ljava/util/concurrent/CopyOnWriteArrayList;

    invoke-virtual {v0}, Ljava/util/concurrent/CopyOnWriteArrayList;->isEmpty()Z

    move-result v0

    if-eqz v0, :cond_0

    .line 271
    :goto_0
    return-void

    .line 261
    :cond_0
    invoke-virtual {p0}, Lcom/miui/internal/view/menu/MenuBuilder;->stopDispatchingItemsChanged()V

    .line 262
    iget-object v0, p0, Lcom/miui/internal/view/menu/MenuBuilder;->ac:Ljava/util/concurrent/CopyOnWriteArrayList;

    invoke-virtual {v0}, Ljava/util/concurrent/CopyOnWriteArrayList;->iterator()Ljava/util/Iterator;

    move-result-object v2

    :goto_1
    invoke-interface {v2}, Ljava/util/Iterator;->hasNext()Z

    move-result v0

    if-eqz v0, :cond_2

    invoke-interface {v2}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Ljava/lang/ref/WeakReference;

    .line 263
    invoke-virtual {v0}, Ljava/lang/ref/WeakReference;->get()Ljava/lang/Object;

    move-result-object v1

    check-cast v1, Lcom/miui/internal/view/menu/MenuPresenter;

    .line 264
    if-nez v1, :cond_1

    .line 265
    iget-object v1, p0, Lcom/miui/internal/view/menu/MenuBuilder;->ac:Ljava/util/concurrent/CopyOnWriteArrayList;

    invoke-virtual {v1, v0}, Ljava/util/concurrent/CopyOnWriteArrayList;->remove(Ljava/lang/Object;)Z

    goto :goto_1

    .line 267
    :cond_1
    invoke-interface {v1, p1}, Lcom/miui/internal/view/menu/MenuPresenter;->updateMenuView(Z)V

    goto :goto_1

    .line 270
    :cond_2
    invoke-virtual {p0}, Lcom/miui/internal/view/menu/MenuBuilder;->startDispatchingItemsChanged()V

    goto :goto_0
.end method

.method private a(Lcom/miui/internal/view/menu/SubMenuBuilder;)Z
    .locals 4

    .prologue
    const/4 v0, 0x0

    .line 274
    iget-object v1, p0, Lcom/miui/internal/view/menu/MenuBuilder;->ac:Ljava/util/concurrent/CopyOnWriteArrayList;

    invoke-virtual {v1}, Ljava/util/concurrent/CopyOnWriteArrayList;->isEmpty()Z

    move-result v1

    if-eqz v1, :cond_0

    .line 288
    :goto_0
    return v0

    .line 280
    :cond_0
    iget-object v1, p0, Lcom/miui/internal/view/menu/MenuBuilder;->ac:Ljava/util/concurrent/CopyOnWriteArrayList;

    invoke-virtual {v1}, Ljava/util/concurrent/CopyOnWriteArrayList;->iterator()Ljava/util/Iterator;

    move-result-object v3

    move v2, v0

    :goto_1
    invoke-interface {v3}, Ljava/util/Iterator;->hasNext()Z

    move-result v0

    if-eqz v0, :cond_2

    invoke-interface {v3}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Ljava/lang/ref/WeakReference;

    .line 281
    invoke-virtual {v0}, Ljava/lang/ref/WeakReference;->get()Ljava/lang/Object;

    move-result-object v1

    check-cast v1, Lcom/miui/internal/view/menu/MenuPresenter;

    .line 282
    if-nez v1, :cond_1

    .line 283
    iget-object v1, p0, Lcom/miui/internal/view/menu/MenuBuilder;->ac:Ljava/util/concurrent/CopyOnWriteArrayList;

    invoke-virtual {v1, v0}, Ljava/util/concurrent/CopyOnWriteArrayList;->remove(Ljava/lang/Object;)Z

    move v0, v2

    :goto_2
    move v2, v0

    .line 287
    goto :goto_1

    .line 284
    :cond_1
    if-nez v2, :cond_3

    .line 285
    invoke-interface {v1, p1}, Lcom/miui/internal/view/menu/MenuPresenter;->onSubMenuSelected(Lcom/miui/internal/view/menu/SubMenuBuilder;)Z

    move-result v2

    move v0, v2

    goto :goto_2

    :cond_2
    move v0, v2

    .line 288
    goto :goto_0

    :cond_3
    move v0, v2

    goto :goto_2
.end method

.method private b(Z)V
    .locals 3

    .prologue
    const/4 v0, 0x1

    .line 761
    if-eqz p1, :cond_0

    iget-object v1, p0, Lcom/miui/internal/view/menu/MenuBuilder;->J:Landroid/content/res/Resources;

    invoke-virtual {v1}, Landroid/content/res/Resources;->getConfiguration()Landroid/content/res/Configuration;

    move-result-object v1

    iget v1, v1, Landroid/content/res/Configuration;->keyboard:I

    if-eq v1, v0, :cond_0

    iget-object v1, p0, Lcom/miui/internal/view/menu/MenuBuilder;->J:Landroid/content/res/Resources;

    sget v2, Lcom/miui/internal/R$bool;->abc_config_showMenuShortcutsWhenKeyboardPresent:I

    invoke-virtual {v1, v2}, Landroid/content/res/Resources;->getBoolean(I)Z

    move-result v1

    if-eqz v1, :cond_0

    :goto_0
    iput-boolean v0, p0, Lcom/miui/internal/view/menu/MenuBuilder;->L:Z

    .line 764
    return-void

    .line 761
    :cond_0
    const/4 v0, 0x0

    goto :goto_0
.end method

.method private static c(I)I
    .locals 2

    .prologue
    .line 728
    const/high16 v0, -0x10000

    and-int/2addr v0, p0

    shr-int/lit8 v0, v0, 0x10

    .line 730
    if-ltz v0, :cond_0

    sget-object v1, Lcom/miui/internal/view/menu/MenuBuilder;->I:[I

    array-length v1, v1

    if-lt v0, v1, :cond_1

    .line 731
    :cond_0
    new-instance v0, Ljava/lang/IllegalArgumentException;

    const-string v1, "order does not contain a valid category."

    invoke-direct {v0, v1}, Ljava/lang/IllegalArgumentException;-><init>(Ljava/lang/String;)V

    throw v0

    .line 734
    :cond_1
    sget-object v1, Lcom/miui/internal/view/menu/MenuBuilder;->I:[I

    aget v0, v1, v0

    shl-int/lit8 v0, v0, 0x10

    const v1, 0xffff

    and-int/2addr v1, p0

    or-int/2addr v0, v1

    return v0
.end method

.method private dispatchRestoreInstanceState(Landroid/os/Bundle;)V
    .locals 4

    .prologue
    .line 317
    const-string v0, "android:menu:presenters"

    invoke-virtual {p1, v0}, Landroid/os/Bundle;->getSparseParcelableArray(Ljava/lang/String;)Landroid/util/SparseArray;

    move-result-object v2

    .line 319
    if-eqz v2, :cond_0

    iget-object v0, p0, Lcom/miui/internal/view/menu/MenuBuilder;->ac:Ljava/util/concurrent/CopyOnWriteArrayList;

    invoke-virtual {v0}, Ljava/util/concurrent/CopyOnWriteArrayList;->isEmpty()Z

    move-result v0

    if-eqz v0, :cond_1

    .line 337
    :cond_0
    return-void

    .line 323
    :cond_1
    iget-object v0, p0, Lcom/miui/internal/view/menu/MenuBuilder;->ac:Ljava/util/concurrent/CopyOnWriteArrayList;

    invoke-virtual {v0}, Ljava/util/concurrent/CopyOnWriteArrayList;->iterator()Ljava/util/Iterator;

    move-result-object v3

    :cond_2
    :goto_0
    invoke-interface {v3}, Ljava/util/Iterator;->hasNext()Z

    move-result v0

    if-eqz v0, :cond_0

    invoke-interface {v3}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Ljava/lang/ref/WeakReference;

    .line 324
    invoke-virtual {v0}, Ljava/lang/ref/WeakReference;->get()Ljava/lang/Object;

    move-result-object v1

    check-cast v1, Lcom/miui/internal/view/menu/MenuPresenter;

    .line 325
    if-nez v1, :cond_3

    .line 326
    iget-object v1, p0, Lcom/miui/internal/view/menu/MenuBuilder;->ac:Ljava/util/concurrent/CopyOnWriteArrayList;

    invoke-virtual {v1, v0}, Ljava/util/concurrent/CopyOnWriteArrayList;->remove(Ljava/lang/Object;)Z

    goto :goto_0

    .line 328
    :cond_3
    invoke-interface {v1}, Lcom/miui/internal/view/menu/MenuPresenter;->getId()I

    move-result v0

    .line 329
    if-lez v0, :cond_2

    .line 330
    invoke-virtual {v2, v0}, Landroid/util/SparseArray;->get(I)Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Landroid/os/Parcelable;

    .line 331
    if-eqz v0, :cond_2

    .line 332
    invoke-interface {v1, v0}, Lcom/miui/internal/view/menu/MenuPresenter;->onRestoreInstanceState(Landroid/os/Parcelable;)V

    goto :goto_0
.end method

.method private dispatchSaveInstanceState(Landroid/os/Bundle;)V
    .locals 4

    .prologue
    .line 292
    iget-object v0, p0, Lcom/miui/internal/view/menu/MenuBuilder;->ac:Ljava/util/concurrent/CopyOnWriteArrayList;

    invoke-virtual {v0}, Ljava/util/concurrent/CopyOnWriteArrayList;->isEmpty()Z

    move-result v0

    if-eqz v0, :cond_0

    .line 314
    :goto_0
    return-void

    .line 296
    :cond_0
    new-instance v2, Landroid/util/SparseArray;

    invoke-direct {v2}, Landroid/util/SparseArray;-><init>()V

    .line 298
    iget-object v0, p0, Lcom/miui/internal/view/menu/MenuBuilder;->ac:Ljava/util/concurrent/CopyOnWriteArrayList;

    invoke-virtual {v0}, Ljava/util/concurrent/CopyOnWriteArrayList;->iterator()Ljava/util/Iterator;

    move-result-object v3

    :cond_1
    :goto_1
    invoke-interface {v3}, Ljava/util/Iterator;->hasNext()Z

    move-result v0

    if-eqz v0, :cond_3

    invoke-interface {v3}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Ljava/lang/ref/WeakReference;

    .line 299
    invoke-virtual {v0}, Ljava/lang/ref/WeakReference;->get()Ljava/lang/Object;

    move-result-object v1

    check-cast v1, Lcom/miui/internal/view/menu/MenuPresenter;

    .line 300
    if-nez v1, :cond_2

    .line 301
    iget-object v1, p0, Lcom/miui/internal/view/menu/MenuBuilder;->ac:Ljava/util/concurrent/CopyOnWriteArrayList;

    invoke-virtual {v1, v0}, Ljava/util/concurrent/CopyOnWriteArrayList;->remove(Ljava/lang/Object;)Z

    goto :goto_1

    .line 303
    :cond_2
    invoke-interface {v1}, Lcom/miui/internal/view/menu/MenuPresenter;->getId()I

    move-result v0

    .line 304
    if-lez v0, :cond_1

    .line 305
    invoke-interface {v1}, Lcom/miui/internal/view/menu/MenuPresenter;->onSaveInstanceState()Landroid/os/Parcelable;

    move-result-object v1

    .line 306
    if-eqz v1, :cond_1

    .line 307
    invoke-virtual {v2, v0, v1}, Landroid/util/SparseArray;->put(ILjava/lang/Object;)V

    goto :goto_1

    .line 313
    :cond_3
    const-string v0, "android:menu:presenters"

    invoke-virtual {p1, v0, v2}, Landroid/os/Bundle;->putSparseParcelableArray(Ljava/lang/String;Landroid/util/SparseArray;)V

    goto :goto_0
.end method


# virtual methods
.method a(ILandroid/view/KeyEvent;)Lcom/miui/internal/view/menu/MenuItemImpl;
    .locals 10

    .prologue
    const/4 v2, 0x0

    const/4 v9, 0x0

    .line 874
    iget-object v0, p0, Lcom/miui/internal/view/menu/MenuBuilder;->ab:Ljava/util/ArrayList;

    .line 875
    invoke-virtual {v0}, Ljava/util/ArrayList;->clear()V

    .line 876
    invoke-virtual {p0, v0, p1, p2}, Lcom/miui/internal/view/menu/MenuBuilder;->a(Ljava/util/List;ILandroid/view/KeyEvent;)V

    .line 878
    invoke-virtual {v0}, Ljava/util/ArrayList;->isEmpty()Z

    move-result v1

    if-eqz v1, :cond_1

    move-object v0, v2

    .line 908
    :cond_0
    :goto_0
    return-object v0

    .line 882
    :cond_1
    invoke-virtual {p2}, Landroid/view/KeyEvent;->getMetaState()I

    move-result v3

    .line 883
    new-instance v4, Landroid/view/KeyCharacterMap$KeyData;

    invoke-direct {v4}, Landroid/view/KeyCharacterMap$KeyData;-><init>()V

    .line 885
    invoke-virtual {p2, v4}, Landroid/view/KeyEvent;->getKeyData(Landroid/view/KeyCharacterMap$KeyData;)Z

    .line 888
    invoke-virtual {v0}, Ljava/util/ArrayList;->size()I

    move-result v1

    .line 889
    const/4 v5, 0x1

    if-ne v1, v5, :cond_2

    .line 890
    invoke-virtual {v0, v9}, Ljava/util/ArrayList;->get(I)Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Lcom/miui/internal/view/menu/MenuItemImpl;

    goto :goto_0

    .line 893
    :cond_2
    invoke-virtual {p0}, Lcom/miui/internal/view/menu/MenuBuilder;->isQwertyMode()Z

    move-result v5

    .line 896
    invoke-virtual {v0}, Ljava/util/ArrayList;->iterator()Ljava/util/Iterator;

    move-result-object v6

    :cond_3
    invoke-interface {v6}, Ljava/util/Iterator;->hasNext()Z

    move-result v0

    if-eqz v0, :cond_7

    invoke-interface {v6}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Lcom/miui/internal/view/menu/MenuItemImpl;

    .line 897
    if-eqz v5, :cond_6

    invoke-virtual {v0}, Lcom/miui/internal/view/menu/MenuItemImpl;->getAlphabeticShortcut()C

    move-result v1

    .line 899
    :goto_1
    iget-object v7, v4, Landroid/view/KeyCharacterMap$KeyData;->meta:[C

    aget-char v7, v7, v9

    if-ne v1, v7, :cond_4

    and-int/lit8 v7, v3, 0x2

    if-eqz v7, :cond_0

    :cond_4
    iget-object v7, v4, Landroid/view/KeyCharacterMap$KeyData;->meta:[C

    const/4 v8, 0x2

    aget-char v7, v7, v8

    if-ne v1, v7, :cond_5

    and-int/lit8 v7, v3, 0x2

    if-nez v7, :cond_0

    :cond_5
    if-eqz v5, :cond_3

    const/16 v7, 0x8

    if-ne v1, v7, :cond_3

    const/16 v1, 0x43

    if-ne p1, v1, :cond_3

    goto :goto_0

    .line 897
    :cond_6
    invoke-virtual {v0}, Lcom/miui/internal/view/menu/MenuItemImpl;->getNumericShortcut()C

    move-result v1

    goto :goto_1

    :cond_7
    move-object v0, v2

    .line 908
    goto :goto_0
.end method

.method a(Landroid/view/MenuItem;)V
    .locals 4

    .prologue
    .line 572
    invoke-interface {p1}, Landroid/view/MenuItem;->getGroupId()I

    move-result v2

    .line 574
    iget-object v0, p0, Lcom/miui/internal/view/menu/MenuBuilder;->N:Ljava/util/ArrayList;

    invoke-virtual {v0}, Ljava/util/ArrayList;->iterator()Ljava/util/Iterator;

    move-result-object v3

    :cond_0
    :goto_0
    invoke-interface {v3}, Ljava/util/Iterator;->hasNext()Z

    move-result v0

    if-eqz v0, :cond_2

    invoke-interface {v3}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Lcom/miui/internal/view/menu/MenuItemImpl;

    .line 575
    invoke-virtual {v0}, Lcom/miui/internal/view/menu/MenuItemImpl;->getGroupId()I

    move-result v1

    if-ne v1, v2, :cond_0

    .line 576
    invoke-virtual {v0}, Lcom/miui/internal/view/menu/MenuItemImpl;->isExclusiveCheckable()Z

    move-result v1

    if-eqz v1, :cond_0

    .line 579
    invoke-virtual {v0}, Lcom/miui/internal/view/menu/MenuItemImpl;->isCheckable()Z

    move-result v1

    if-eqz v1, :cond_0

    .line 584
    if-ne v0, p1, :cond_1

    const/4 v1, 0x1

    :goto_1
    invoke-virtual {v0, v1}, Lcom/miui/internal/view/menu/MenuItemImpl;->v(Z)V

    goto :goto_0

    :cond_1
    const/4 v1, 0x0

    goto :goto_1

    .line 587
    :cond_2
    return-void
.end method

.method a(Lcom/miui/internal/view/menu/MenuItemImpl;)V
    .locals 1

    .prologue
    const/4 v0, 0x1

    .line 1035
    iput-boolean v0, p0, Lcom/miui/internal/view/menu/MenuBuilder;->P:Z

    .line 1036
    invoke-virtual {p0, v0}, Lcom/miui/internal/view/menu/MenuBuilder;->d(Z)V

    .line 1037
    return-void
.end method

.method a(Ljava/util/List;ILandroid/view/KeyEvent;)V
    .locals 9
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Ljava/util/List",
            "<",
            "Lcom/miui/internal/view/menu/MenuItemImpl;",
            ">;I",
            "Landroid/view/KeyEvent;",
            ")V"
        }
    .end annotation

    .prologue
    const/16 v8, 0x43

    .line 830
    invoke-virtual {p0}, Lcom/miui/internal/view/menu/MenuBuilder;->isQwertyMode()Z

    move-result v2

    .line 831
    invoke-virtual {p3}, Landroid/view/KeyEvent;->getMetaState()I

    move-result v3

    .line 832
    new-instance v4, Landroid/view/KeyCharacterMap$KeyData;

    invoke-direct {v4}, Landroid/view/KeyCharacterMap$KeyData;-><init>()V

    .line 834
    invoke-virtual {p3, v4}, Landroid/view/KeyEvent;->getKeyData(Landroid/view/KeyCharacterMap$KeyData;)Z

    move-result v0

    .line 836
    if-nez v0, :cond_1

    if-eq p2, v8, :cond_1

    .line 858
    :cond_0
    return-void

    .line 841
    :cond_1
    iget-object v0, p0, Lcom/miui/internal/view/menu/MenuBuilder;->N:Ljava/util/ArrayList;

    invoke-virtual {v0}, Ljava/util/ArrayList;->iterator()Ljava/util/Iterator;

    move-result-object v5

    :cond_2
    :goto_0
    invoke-interface {v5}, Ljava/util/Iterator;->hasNext()Z

    move-result v0

    if-eqz v0, :cond_0

    invoke-interface {v5}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Lcom/miui/internal/view/menu/MenuItemImpl;

    .line 842
    invoke-virtual {v0}, Lcom/miui/internal/view/menu/MenuItemImpl;->hasSubMenu()Z

    move-result v1

    if-eqz v1, :cond_3

    .line 843
    invoke-virtual {v0}, Lcom/miui/internal/view/menu/MenuItemImpl;->getSubMenu()Landroid/view/SubMenu;

    move-result-object v1

    check-cast v1, Lcom/miui/internal/view/menu/MenuBuilder;

    invoke-virtual {v1, p1, p2, p3}, Lcom/miui/internal/view/menu/MenuBuilder;->a(Ljava/util/List;ILandroid/view/KeyEvent;)V

    .line 846
    :cond_3
    if-eqz v2, :cond_5

    invoke-virtual {v0}, Lcom/miui/internal/view/menu/MenuItemImpl;->getAlphabeticShortcut()C

    move-result v1

    .line 848
    :goto_1
    and-int/lit8 v6, v3, 0x5

    if-nez v6, :cond_2

    if-eqz v1, :cond_2

    iget-object v6, v4, Landroid/view/KeyCharacterMap$KeyData;->meta:[C

    const/4 v7, 0x0

    aget-char v6, v6, v7

    if-eq v1, v6, :cond_4

    iget-object v6, v4, Landroid/view/KeyCharacterMap$KeyData;->meta:[C

    const/4 v7, 0x2

    aget-char v6, v6, v7

    if-eq v1, v6, :cond_4

    if-eqz v2, :cond_2

    const/16 v6, 0x8

    if-ne v1, v6, :cond_2

    if-ne p2, v8, :cond_2

    :cond_4
    invoke-virtual {v0}, Lcom/miui/internal/view/menu/MenuItemImpl;->isEnabled()Z

    move-result v1

    if-eqz v1, :cond_2

    .line 855
    invoke-interface {p1, v0}, Ljava/util/List;->add(Ljava/lang/Object;)Z

    goto :goto_0

    .line 846
    :cond_5
    invoke-virtual {v0}, Lcom/miui/internal/view/menu/MenuItemImpl;->getNumericShortcut()C

    move-result v1

    goto :goto_1
.end method

.method public add(I)Landroid/view/MenuItem;
    .locals 2

    .prologue
    const/4 v1, 0x0

    .line 439
    iget-object v0, p0, Lcom/miui/internal/view/menu/MenuBuilder;->J:Landroid/content/res/Resources;

    invoke-virtual {v0, p1}, Landroid/content/res/Resources;->getString(I)Ljava/lang/String;

    move-result-object v0

    invoke-direct {p0, v1, v1, v1, v0}, Lcom/miui/internal/view/menu/MenuBuilder;->a(IIILjava/lang/CharSequence;)Landroid/view/MenuItem;

    move-result-object v0

    return-object v0
.end method

.method public add(IIII)Landroid/view/MenuItem;
    .locals 1

    .prologue
    .line 449
    iget-object v0, p0, Lcom/miui/internal/view/menu/MenuBuilder;->J:Landroid/content/res/Resources;

    invoke-virtual {v0, p4}, Landroid/content/res/Resources;->getString(I)Ljava/lang/String;

    move-result-object v0

    invoke-direct {p0, p1, p2, p3, v0}, Lcom/miui/internal/view/menu/MenuBuilder;->a(IIILjava/lang/CharSequence;)Landroid/view/MenuItem;

    move-result-object v0

    return-object v0
.end method

.method public add(IIILjava/lang/CharSequence;)Landroid/view/MenuItem;
    .locals 1

    .prologue
    .line 444
    invoke-direct {p0, p1, p2, p3, p4}, Lcom/miui/internal/view/menu/MenuBuilder;->a(IIILjava/lang/CharSequence;)Landroid/view/MenuItem;

    move-result-object v0

    return-object v0
.end method

.method public add(Ljava/lang/CharSequence;)Landroid/view/MenuItem;
    .locals 1

    .prologue
    const/4 v0, 0x0

    .line 434
    invoke-direct {p0, v0, v0, v0, p1}, Lcom/miui/internal/view/menu/MenuBuilder;->a(IIILjava/lang/CharSequence;)Landroid/view/MenuItem;

    move-result-object v0

    return-object v0
.end method

.method public addIntentOptions(IIILandroid/content/ComponentName;[Landroid/content/Intent;Landroid/content/Intent;I[Landroid/view/MenuItem;)I
    .locals 9

    .prologue
    .line 479
    iget-object v0, p0, Lcom/miui/internal/view/menu/MenuBuilder;->mContext:Landroid/content/Context;

    invoke-virtual {v0}, Landroid/content/Context;->getPackageManager()Landroid/content/pm/PackageManager;

    move-result-object v4

    .line 480
    const/4 v0, 0x0

    invoke-virtual {v4, p4, p5, p6, v0}, Landroid/content/pm/PackageManager;->queryIntentActivityOptions(Landroid/content/ComponentName;[Landroid/content/Intent;Landroid/content/Intent;I)Ljava/util/List;

    move-result-object v5

    .line 482
    if-eqz v5, :cond_2

    invoke-interface {v5}, Ljava/util/List;->size()I

    move-result v0

    move v3, v0

    .line 484
    :goto_0
    and-int/lit8 v0, p7, 0x1

    if-nez v0, :cond_0

    .line 485
    invoke-virtual {p0, p1}, Lcom/miui/internal/view/menu/MenuBuilder;->removeGroup(I)V

    .line 488
    :cond_0
    const/4 v0, 0x0

    move v2, v0

    :goto_1
    if-ge v2, v3, :cond_4

    .line 489
    invoke-interface {v5, v2}, Ljava/util/List;->get(I)Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Landroid/content/pm/ResolveInfo;

    .line 490
    new-instance v6, Landroid/content/Intent;

    iget v1, v0, Landroid/content/pm/ResolveInfo;->specificIndex:I

    if-gez v1, :cond_3

    move-object v1, p6

    :goto_2
    invoke-direct {v6, v1}, Landroid/content/Intent;-><init>(Landroid/content/Intent;)V

    .line 492
    new-instance v1, Landroid/content/ComponentName;

    iget-object v7, v0, Landroid/content/pm/ResolveInfo;->activityInfo:Landroid/content/pm/ActivityInfo;

    iget-object v7, v7, Landroid/content/pm/ActivityInfo;->applicationInfo:Landroid/content/pm/ApplicationInfo;

    iget-object v7, v7, Landroid/content/pm/ApplicationInfo;->packageName:Ljava/lang/String;

    iget-object v8, v0, Landroid/content/pm/ResolveInfo;->activityInfo:Landroid/content/pm/ActivityInfo;

    iget-object v8, v8, Landroid/content/pm/ActivityInfo;->name:Ljava/lang/String;

    invoke-direct {v1, v7, v8}, Landroid/content/ComponentName;-><init>(Ljava/lang/String;Ljava/lang/String;)V

    invoke-virtual {v6, v1}, Landroid/content/Intent;->setComponent(Landroid/content/ComponentName;)Landroid/content/Intent;

    .line 495
    invoke-virtual {v0, v4}, Landroid/content/pm/ResolveInfo;->loadLabel(Landroid/content/pm/PackageManager;)Ljava/lang/CharSequence;

    move-result-object v1

    invoke-virtual {p0, p1, p2, p3, v1}, Lcom/miui/internal/view/menu/MenuBuilder;->add(IIILjava/lang/CharSequence;)Landroid/view/MenuItem;

    move-result-object v1

    invoke-virtual {v0, v4}, Landroid/content/pm/ResolveInfo;->loadIcon(Landroid/content/pm/PackageManager;)Landroid/graphics/drawable/Drawable;

    move-result-object v7

    invoke-interface {v1, v7}, Landroid/view/MenuItem;->setIcon(Landroid/graphics/drawable/Drawable;)Landroid/view/MenuItem;

    move-result-object v1

    invoke-interface {v1, v6}, Landroid/view/MenuItem;->setIntent(Landroid/content/Intent;)Landroid/view/MenuItem;

    move-result-object v1

    .line 498
    if-eqz p8, :cond_1

    iget v6, v0, Landroid/content/pm/ResolveInfo;->specificIndex:I

    if-ltz v6, :cond_1

    .line 499
    iget v0, v0, Landroid/content/pm/ResolveInfo;->specificIndex:I

    aput-object v1, p8, v0

    .line 488
    :cond_1
    add-int/lit8 v0, v2, 0x1

    move v2, v0

    goto :goto_1

    .line 482
    :cond_2
    const/4 v0, 0x0

    move v3, v0

    goto :goto_0

    .line 490
    :cond_3
    iget v1, v0, Landroid/content/pm/ResolveInfo;->specificIndex:I

    aget-object v1, p5, v1

    goto :goto_2

    .line 503
    :cond_4
    return v3
.end method

.method public addMenuPresenter(Lcom/miui/internal/view/menu/MenuPresenter;)V
    .locals 2

    .prologue
    .line 236
    iget-object v0, p0, Lcom/miui/internal/view/menu/MenuBuilder;->ac:Ljava/util/concurrent/CopyOnWriteArrayList;

    new-instance v1, Ljava/lang/ref/WeakReference;

    invoke-direct {v1, p1}, Ljava/lang/ref/WeakReference;-><init>(Ljava/lang/Object;)V

    invoke-virtual {v0, v1}, Ljava/util/concurrent/CopyOnWriteArrayList;->add(Ljava/lang/Object;)Z

    .line 237
    iget-object v0, p0, Lcom/miui/internal/view/menu/MenuBuilder;->mContext:Landroid/content/Context;

    invoke-interface {p1, v0, p0}, Lcom/miui/internal/view/menu/MenuPresenter;->initForMenu(Landroid/content/Context;Lcom/miui/internal/view/menu/MenuBuilder;)V

    .line 238
    const/4 v0, 0x1

    iput-boolean v0, p0, Lcom/miui/internal/view/menu/MenuBuilder;->R:Z

    .line 239
    return-void
.end method

.method public addSubMenu(I)Landroid/view/SubMenu;
    .locals 2

    .prologue
    const/4 v1, 0x0

    .line 459
    iget-object v0, p0, Lcom/miui/internal/view/menu/MenuBuilder;->J:Landroid/content/res/Resources;

    invoke-virtual {v0, p1}, Landroid/content/res/Resources;->getString(I)Ljava/lang/String;

    move-result-object v0

    invoke-virtual {p0, v1, v1, v1, v0}, Lcom/miui/internal/view/menu/MenuBuilder;->addSubMenu(IIILjava/lang/CharSequence;)Landroid/view/SubMenu;

    move-result-object v0

    return-object v0
.end method

.method public addSubMenu(IIII)Landroid/view/SubMenu;
    .locals 1

    .prologue
    .line 473
    iget-object v0, p0, Lcom/miui/internal/view/menu/MenuBuilder;->J:Landroid/content/res/Resources;

    invoke-virtual {v0, p4}, Landroid/content/res/Resources;->getString(I)Ljava/lang/String;

    move-result-object v0

    invoke-virtual {p0, p1, p2, p3, v0}, Lcom/miui/internal/view/menu/MenuBuilder;->addSubMenu(IIILjava/lang/CharSequence;)Landroid/view/SubMenu;

    move-result-object v0

    return-object v0
.end method

.method public addSubMenu(IIILjava/lang/CharSequence;)Landroid/view/SubMenu;
    .locals 3

    .prologue
    .line 464
    invoke-direct {p0, p1, p2, p3, p4}, Lcom/miui/internal/view/menu/MenuBuilder;->a(IIILjava/lang/CharSequence;)Landroid/view/MenuItem;

    move-result-object v0

    check-cast v0, Lcom/miui/internal/view/menu/MenuItemImpl;

    .line 465
    new-instance v1, Lcom/miui/internal/view/menu/SubMenuBuilder;

    iget-object v2, p0, Lcom/miui/internal/view/menu/MenuBuilder;->mContext:Landroid/content/Context;

    invoke-direct {v1, v2, p0, v0}, Lcom/miui/internal/view/menu/SubMenuBuilder;-><init>(Landroid/content/Context;Lcom/miui/internal/view/menu/MenuBuilder;Lcom/miui/internal/view/menu/MenuItemImpl;)V

    .line 466
    invoke-virtual {v0, v1}, Lcom/miui/internal/view/menu/MenuItemImpl;->b(Lcom/miui/internal/view/menu/SubMenuBuilder;)V

    .line 468
    return-object v1
.end method

.method public addSubMenu(Ljava/lang/CharSequence;)Landroid/view/SubMenu;
    .locals 1

    .prologue
    const/4 v0, 0x0

    .line 454
    invoke-virtual {p0, v0, v0, v0, p1}, Lcom/miui/internal/view/menu/MenuBuilder;->addSubMenu(IIILjava/lang/CharSequence;)Landroid/view/SubMenu;

    move-result-object v0

    return-object v0
.end method

.method b(Lcom/miui/internal/view/menu/MenuItemImpl;)V
    .locals 1

    .prologue
    const/4 v0, 0x1

    .line 1046
    iput-boolean v0, p0, Lcom/miui/internal/view/menu/MenuBuilder;->R:Z

    .line 1047
    invoke-virtual {p0, v0}, Lcom/miui/internal/view/menu/MenuBuilder;->d(Z)V

    .line 1048
    return-void
.end method

.method final c(Z)V
    .locals 3

    .prologue
    .line 966
    iget-boolean v0, p0, Lcom/miui/internal/view/menu/MenuBuilder;->aa:Z

    if-eqz v0, :cond_0

    .line 980
    :goto_0
    return-void

    .line 970
    :cond_0
    const/4 v0, 0x1

    iput-boolean v0, p0, Lcom/miui/internal/view/menu/MenuBuilder;->aa:Z

    .line 971
    iget-object v0, p0, Lcom/miui/internal/view/menu/MenuBuilder;->ac:Ljava/util/concurrent/CopyOnWriteArrayList;

    invoke-virtual {v0}, Ljava/util/concurrent/CopyOnWriteArrayList;->iterator()Ljava/util/Iterator;

    move-result-object v2

    :goto_1
    invoke-interface {v2}, Ljava/util/Iterator;->hasNext()Z

    move-result v0

    if-eqz v0, :cond_2

    invoke-interface {v2}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Ljava/lang/ref/WeakReference;

    .line 972
    invoke-virtual {v0}, Ljava/lang/ref/WeakReference;->get()Ljava/lang/Object;

    move-result-object v1

    check-cast v1, Lcom/miui/internal/view/menu/MenuPresenter;

    .line 973
    if-nez v1, :cond_1

    .line 974
    iget-object v1, p0, Lcom/miui/internal/view/menu/MenuBuilder;->ac:Ljava/util/concurrent/CopyOnWriteArrayList;

    invoke-virtual {v1, v0}, Ljava/util/concurrent/CopyOnWriteArrayList;->remove(Ljava/lang/Object;)Z

    goto :goto_1

    .line 976
    :cond_1
    invoke-interface {v1, p0, p1}, Lcom/miui/internal/view/menu/MenuPresenter;->onCloseMenu(Lcom/miui/internal/view/menu/MenuBuilder;Z)V

    goto :goto_1

    .line 979
    :cond_2
    const/4 v0, 0x0

    iput-boolean v0, p0, Lcom/miui/internal/view/menu/MenuBuilder;->aa:Z

    goto :goto_0
.end method

.method public changeMenuMode()V
    .locals 1

    .prologue
    .line 789
    iget-object v0, p0, Lcom/miui/internal/view/menu/MenuBuilder;->M:Lcom/miui/internal/view/menu/MenuBuilder$Callback;

    if-eqz v0, :cond_0

    .line 790
    iget-object v0, p0, Lcom/miui/internal/view/menu/MenuBuilder;->M:Lcom/miui/internal/view/menu/MenuBuilder$Callback;

    invoke-interface {v0, p0}, Lcom/miui/internal/view/menu/MenuBuilder$Callback;->onMenuModeChange(Lcom/miui/internal/view/menu/MenuBuilder;)V

    .line 792
    :cond_0
    return-void
.end method

.method public clear()V
    .locals 1

    .prologue
    .line 563
    iget-object v0, p0, Lcom/miui/internal/view/menu/MenuBuilder;->ad:Lcom/miui/internal/view/menu/MenuItemImpl;

    if-eqz v0, :cond_0

    .line 564
    iget-object v0, p0, Lcom/miui/internal/view/menu/MenuBuilder;->ad:Lcom/miui/internal/view/menu/MenuItemImpl;

    invoke-virtual {p0, v0}, Lcom/miui/internal/view/menu/MenuBuilder;->collapseItemActionView(Lcom/miui/internal/view/menu/MenuItemImpl;)Z

    .line 566
    :cond_0
    iget-object v0, p0, Lcom/miui/internal/view/menu/MenuBuilder;->N:Ljava/util/ArrayList;

    invoke-virtual {v0}, Ljava/util/ArrayList;->clear()V

    .line 568
    const/4 v0, 0x1

    invoke-virtual {p0, v0}, Lcom/miui/internal/view/menu/MenuBuilder;->d(Z)V

    .line 569
    return-void
.end method

.method public clearAll()V
    .locals 2

    .prologue
    const/4 v1, 0x1

    const/4 v0, 0x0

    .line 553
    iput-boolean v1, p0, Lcom/miui/internal/view/menu/MenuBuilder;->X:Z

    .line 554
    invoke-virtual {p0}, Lcom/miui/internal/view/menu/MenuBuilder;->clear()V

    .line 555
    invoke-virtual {p0}, Lcom/miui/internal/view/menu/MenuBuilder;->clearHeader()V

    .line 556
    iput-boolean v0, p0, Lcom/miui/internal/view/menu/MenuBuilder;->X:Z

    .line 557
    iput-boolean v0, p0, Lcom/miui/internal/view/menu/MenuBuilder;->Y:Z

    .line 558
    invoke-virtual {p0, v1}, Lcom/miui/internal/view/menu/MenuBuilder;->d(Z)V

    .line 559
    return-void
.end method

.method public clearHeader()V
    .locals 1

    .prologue
    const/4 v0, 0x0

    .line 1142
    iput-object v0, p0, Lcom/miui/internal/view/menu/MenuBuilder;->V:Landroid/graphics/drawable/Drawable;

    .line 1143
    iput-object v0, p0, Lcom/miui/internal/view/menu/MenuBuilder;->U:Ljava/lang/CharSequence;

    .line 1144
    iput-object v0, p0, Lcom/miui/internal/view/menu/MenuBuilder;->W:Landroid/view/View;

    .line 1146
    const/4 v0, 0x0

    invoke-virtual {p0, v0}, Lcom/miui/internal/view/menu/MenuBuilder;->d(Z)V

    .line 1147
    return-void
.end method

.method public close()V
    .locals 1

    .prologue
    .line 984
    const/4 v0, 0x1

    invoke-virtual {p0, v0}, Lcom/miui/internal/view/menu/MenuBuilder;->c(Z)V

    .line 985
    return-void
.end method

.method public collapseItemActionView(Lcom/miui/internal/view/menu/MenuItemImpl;)Z
    .locals 4

    .prologue
    const/4 v0, 0x0

    .line 1305
    iget-object v1, p0, Lcom/miui/internal/view/menu/MenuBuilder;->ac:Ljava/util/concurrent/CopyOnWriteArrayList;

    invoke-virtual {v1}, Ljava/util/concurrent/CopyOnWriteArrayList;->isEmpty()Z

    move-result v1

    if-nez v1, :cond_0

    iget-object v1, p0, Lcom/miui/internal/view/menu/MenuBuilder;->ad:Lcom/miui/internal/view/menu/MenuItemImpl;

    if-eq v1, p1, :cond_1

    .line 1325
    :cond_0
    :goto_0
    return v0

    .line 1311
    :cond_1
    invoke-virtual {p0}, Lcom/miui/internal/view/menu/MenuBuilder;->stopDispatchingItemsChanged()V

    .line 1312
    iget-object v1, p0, Lcom/miui/internal/view/menu/MenuBuilder;->ac:Ljava/util/concurrent/CopyOnWriteArrayList;

    invoke-virtual {v1}, Ljava/util/concurrent/CopyOnWriteArrayList;->iterator()Ljava/util/Iterator;

    move-result-object v3

    move v2, v0

    :goto_1
    invoke-interface {v3}, Ljava/util/Iterator;->hasNext()Z

    move-result v0

    if-eqz v0, :cond_4

    invoke-interface {v3}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Ljava/lang/ref/WeakReference;

    .line 1313
    invoke-virtual {v0}, Ljava/lang/ref/WeakReference;->get()Ljava/lang/Object;

    move-result-object v1

    check-cast v1, Lcom/miui/internal/view/menu/MenuPresenter;

    .line 1314
    if-nez v1, :cond_3

    .line 1315
    iget-object v1, p0, Lcom/miui/internal/view/menu/MenuBuilder;->ac:Ljava/util/concurrent/CopyOnWriteArrayList;

    invoke-virtual {v1, v0}, Ljava/util/concurrent/CopyOnWriteArrayList;->remove(Ljava/lang/Object;)Z

    move v0, v2

    :cond_2
    move v2, v0

    .line 1319
    goto :goto_1

    .line 1316
    :cond_3
    invoke-interface {v1, p0, p1}, Lcom/miui/internal/view/menu/MenuPresenter;->collapseItemActionView(Lcom/miui/internal/view/menu/MenuBuilder;Lcom/miui/internal/view/menu/MenuItemImpl;)Z

    move-result v0

    if-eqz v0, :cond_2

    .line 1320
    :goto_2
    invoke-virtual {p0}, Lcom/miui/internal/view/menu/MenuBuilder;->startDispatchingItemsChanged()V

    .line 1322
    if-eqz v0, :cond_0

    .line 1323
    const/4 v1, 0x0

    iput-object v1, p0, Lcom/miui/internal/view/menu/MenuBuilder;->ad:Lcom/miui/internal/view/menu/MenuItemImpl;

    goto :goto_0

    :cond_4
    move v0, v2

    goto :goto_2
.end method

.method d(Z)V
    .locals 2

    .prologue
    const/4 v1, 0x1

    .line 995
    iget-boolean v0, p0, Lcom/miui/internal/view/menu/MenuBuilder;->X:Z

    if-nez v0, :cond_1

    .line 996
    if-eqz p1, :cond_0

    .line 997
    iput-boolean v1, p0, Lcom/miui/internal/view/menu/MenuBuilder;->P:Z

    .line 998
    iput-boolean v1, p0, Lcom/miui/internal/view/menu/MenuBuilder;->R:Z

    .line 1001
    :cond_0
    invoke-direct {p0, p1}, Lcom/miui/internal/view/menu/MenuBuilder;->a(Z)V

    .line 1005
    :goto_0
    return-void

    .line 1003
    :cond_1
    iput-boolean v1, p0, Lcom/miui/internal/view/menu/MenuBuilder;->Y:Z

    goto :goto_0
.end method

.method dispatchMenuItemSelected(Lcom/miui/internal/view/menu/MenuBuilder;Landroid/view/MenuItem;)Z
    .locals 1

    .prologue
    .line 782
    iget-object v0, p0, Lcom/miui/internal/view/menu/MenuBuilder;->M:Lcom/miui/internal/view/menu/MenuBuilder$Callback;

    if-eqz v0, :cond_0

    iget-object v0, p0, Lcom/miui/internal/view/menu/MenuBuilder;->M:Lcom/miui/internal/view/menu/MenuBuilder$Callback;

    invoke-interface {v0, p1, p2}, Lcom/miui/internal/view/menu/MenuBuilder$Callback;->onMenuItemSelected(Lcom/miui/internal/view/menu/MenuBuilder;Landroid/view/MenuItem;)Z

    move-result v0

    if-eqz v0, :cond_0

    const/4 v0, 0x1

    :goto_0
    return v0

    :cond_0
    const/4 v0, 0x0

    goto :goto_0
.end method

.method e(Z)V
    .locals 0

    .prologue
    .line 1273
    iput-boolean p1, p0, Lcom/miui/internal/view/menu/MenuBuilder;->Z:Z

    .line 1274
    return-void
.end method

.method e()Z
    .locals 1

    .prologue
    .line 1277
    iget-boolean v0, p0, Lcom/miui/internal/view/menu/MenuBuilder;->Z:Z

    return v0
.end method

.method public expandItemActionView(Lcom/miui/internal/view/menu/MenuItemImpl;)Z
    .locals 4

    .prologue
    const/4 v0, 0x0

    .line 1281
    iget-object v1, p0, Lcom/miui/internal/view/menu/MenuBuilder;->ac:Ljava/util/concurrent/CopyOnWriteArrayList;

    invoke-virtual {v1}, Ljava/util/concurrent/CopyOnWriteArrayList;->isEmpty()Z

    move-result v1

    if-eqz v1, :cond_1

    .line 1301
    :cond_0
    :goto_0
    return v0

    .line 1287
    :cond_1
    invoke-virtual {p0}, Lcom/miui/internal/view/menu/MenuBuilder;->stopDispatchingItemsChanged()V

    .line 1288
    iget-object v1, p0, Lcom/miui/internal/view/menu/MenuBuilder;->ac:Ljava/util/concurrent/CopyOnWriteArrayList;

    invoke-virtual {v1}, Ljava/util/concurrent/CopyOnWriteArrayList;->iterator()Ljava/util/Iterator;

    move-result-object v3

    move v2, v0

    :goto_1
    invoke-interface {v3}, Ljava/util/Iterator;->hasNext()Z

    move-result v0

    if-eqz v0, :cond_4

    invoke-interface {v3}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Ljava/lang/ref/WeakReference;

    .line 1289
    invoke-virtual {v0}, Ljava/lang/ref/WeakReference;->get()Ljava/lang/Object;

    move-result-object v1

    check-cast v1, Lcom/miui/internal/view/menu/MenuPresenter;

    .line 1290
    if-nez v1, :cond_3

    .line 1291
    iget-object v1, p0, Lcom/miui/internal/view/menu/MenuBuilder;->ac:Ljava/util/concurrent/CopyOnWriteArrayList;

    invoke-virtual {v1, v0}, Ljava/util/concurrent/CopyOnWriteArrayList;->remove(Ljava/lang/Object;)Z

    move v0, v2

    :cond_2
    move v2, v0

    .line 1295
    goto :goto_1

    .line 1292
    :cond_3
    invoke-interface {v1, p0, p1}, Lcom/miui/internal/view/menu/MenuPresenter;->expandItemActionView(Lcom/miui/internal/view/menu/MenuBuilder;Lcom/miui/internal/view/menu/MenuItemImpl;)Z

    move-result v0

    if-eqz v0, :cond_2

    .line 1296
    :goto_2
    invoke-virtual {p0}, Lcom/miui/internal/view/menu/MenuBuilder;->startDispatchingItemsChanged()V

    .line 1298
    if-eqz v0, :cond_0

    .line 1299
    iput-object p1, p0, Lcom/miui/internal/view/menu/MenuBuilder;->ad:Lcom/miui/internal/view/menu/MenuItemImpl;

    goto :goto_0

    :cond_4
    move v0, v2

    goto :goto_2
.end method

.method public findGroupIndex(I)I
    .locals 1

    .prologue
    .line 674
    const/4 v0, 0x0

    invoke-virtual {p0, p1, v0}, Lcom/miui/internal/view/menu/MenuBuilder;->findGroupIndex(II)I

    move-result v0

    return v0
.end method

.method public findGroupIndex(II)I
    .locals 3

    .prologue
    .line 678
    invoke-virtual {p0}, Lcom/miui/internal/view/menu/MenuBuilder;->size()I

    move-result v2

    .line 680
    if-gez p2, :cond_0

    .line 681
    const/4 p2, 0x0

    :cond_0
    move v1, p2

    .line 684
    :goto_0
    if-ge v1, v2, :cond_2

    .line 685
    iget-object v0, p0, Lcom/miui/internal/view/menu/MenuBuilder;->N:Ljava/util/ArrayList;

    invoke-virtual {v0, v1}, Ljava/util/ArrayList;->get(I)Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Lcom/miui/internal/view/menu/MenuItemImpl;

    .line 687
    invoke-virtual {v0}, Lcom/miui/internal/view/menu/MenuItemImpl;->getGroupId()I

    move-result v0

    if-ne v0, p1, :cond_1

    move v0, v1

    .line 692
    :goto_1
    return v0

    .line 684
    :cond_1
    add-int/lit8 v1, v1, 0x1

    goto :goto_0

    .line 692
    :cond_2
    const/4 v0, -0x1

    goto :goto_1
.end method

.method public findItem(I)Landroid/view/MenuItem;
    .locals 4

    .prologue
    .line 643
    invoke-virtual {p0}, Lcom/miui/internal/view/menu/MenuBuilder;->size()I

    move-result v2

    .line 644
    const/4 v0, 0x0

    move v1, v0

    :goto_0
    if-ge v1, v2, :cond_3

    .line 645
    iget-object v0, p0, Lcom/miui/internal/view/menu/MenuBuilder;->N:Ljava/util/ArrayList;

    invoke-virtual {v0, v1}, Ljava/util/ArrayList;->get(I)Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Lcom/miui/internal/view/menu/MenuItemImpl;

    .line 646
    invoke-virtual {v0}, Lcom/miui/internal/view/menu/MenuItemImpl;->getItemId()I

    move-result v3

    if-ne v3, p1, :cond_1

    .line 657
    :cond_0
    :goto_1
    return-object v0

    .line 648
    :cond_1
    invoke-virtual {v0}, Lcom/miui/internal/view/menu/MenuItemImpl;->hasSubMenu()Z

    move-result v3

    if-eqz v3, :cond_2

    .line 649
    invoke-virtual {v0}, Lcom/miui/internal/view/menu/MenuItemImpl;->getSubMenu()Landroid/view/SubMenu;

    move-result-object v0

    invoke-interface {v0, p1}, Landroid/view/SubMenu;->findItem(I)Landroid/view/MenuItem;

    move-result-object v0

    .line 651
    if-nez v0, :cond_0

    .line 644
    :cond_2
    add-int/lit8 v0, v1, 0x1

    move v1, v0

    goto :goto_0

    .line 657
    :cond_3
    const/4 v0, 0x0

    goto :goto_1
.end method

.method public findItemIndex(I)I
    .locals 3

    .prologue
    .line 661
    invoke-virtual {p0}, Lcom/miui/internal/view/menu/MenuBuilder;->size()I

    move-result v2

    .line 663
    const/4 v1, 0x0

    :goto_0
    if-ge v1, v2, :cond_1

    .line 664
    iget-object v0, p0, Lcom/miui/internal/view/menu/MenuBuilder;->N:Ljava/util/ArrayList;

    invoke-virtual {v0, v1}, Ljava/util/ArrayList;->get(I)Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Lcom/miui/internal/view/menu/MenuItemImpl;

    .line 665
    invoke-virtual {v0}, Lcom/miui/internal/view/menu/MenuItemImpl;->getItemId()I

    move-result v0

    if-ne v0, p1, :cond_0

    move v0, v1

    .line 670
    :goto_1
    return v0

    .line 663
    :cond_0
    add-int/lit8 v1, v1, 0x1

    goto :goto_0

    .line 670
    :cond_1
    const/4 v0, -0x1

    goto :goto_1
.end method

.method public flagActionItems()V
    .locals 5

    .prologue
    const/4 v3, 0x0

    .line 1095
    iget-boolean v0, p0, Lcom/miui/internal/view/menu/MenuBuilder;->R:Z

    if-nez v0, :cond_0

    .line 1129
    :goto_0
    return-void

    .line 1101
    :cond_0
    iget-object v0, p0, Lcom/miui/internal/view/menu/MenuBuilder;->ac:Ljava/util/concurrent/CopyOnWriteArrayList;

    invoke-virtual {v0}, Ljava/util/concurrent/CopyOnWriteArrayList;->iterator()Ljava/util/Iterator;

    move-result-object v4

    move v2, v3

    :goto_1
    invoke-interface {v4}, Ljava/util/Iterator;->hasNext()Z

    move-result v0

    if-eqz v0, :cond_2

    invoke-interface {v4}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Ljava/lang/ref/WeakReference;

    .line 1102
    invoke-virtual {v0}, Ljava/lang/ref/WeakReference;->get()Ljava/lang/Object;

    move-result-object v1

    check-cast v1, Lcom/miui/internal/view/menu/MenuPresenter;

    .line 1103
    if-nez v1, :cond_1

    .line 1104
    iget-object v1, p0, Lcom/miui/internal/view/menu/MenuBuilder;->ac:Ljava/util/concurrent/CopyOnWriteArrayList;

    invoke-virtual {v1, v0}, Ljava/util/concurrent/CopyOnWriteArrayList;->remove(Ljava/lang/Object;)Z

    move v0, v2

    :goto_2
    move v2, v0

    .line 1108
    goto :goto_1

    .line 1106
    :cond_1
    invoke-interface {v1}, Lcom/miui/internal/view/menu/MenuPresenter;->flagActionItems()Z

    move-result v0

    or-int/2addr v0, v2

    goto :goto_2

    .line 1110
    :cond_2
    if-eqz v2, :cond_4

    .line 1111
    iget-object v0, p0, Lcom/miui/internal/view/menu/MenuBuilder;->mActionItems:Ljava/util/ArrayList;

    invoke-virtual {v0}, Ljava/util/ArrayList;->clear()V

    .line 1112
    iget-object v0, p0, Lcom/miui/internal/view/menu/MenuBuilder;->Q:Ljava/util/ArrayList;

    invoke-virtual {v0}, Ljava/util/ArrayList;->clear()V

    .line 1113
    invoke-virtual {p0}, Lcom/miui/internal/view/menu/MenuBuilder;->getVisibleItems()Ljava/util/ArrayList;

    move-result-object v0

    .line 1114
    invoke-virtual {v0}, Ljava/util/ArrayList;->iterator()Ljava/util/Iterator;

    move-result-object v1

    :goto_3
    invoke-interface {v1}, Ljava/util/Iterator;->hasNext()Z

    move-result v0

    if-eqz v0, :cond_5

    invoke-interface {v1}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Lcom/miui/internal/view/menu/MenuItemImpl;

    .line 1115
    invoke-virtual {v0}, Lcom/miui/internal/view/menu/MenuItemImpl;->isActionButton()Z

    move-result v2

    if-eqz v2, :cond_3

    .line 1116
    iget-object v2, p0, Lcom/miui/internal/view/menu/MenuBuilder;->mActionItems:Ljava/util/ArrayList;

    invoke-virtual {v2, v0}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    goto :goto_3

    .line 1118
    :cond_3
    iget-object v2, p0, Lcom/miui/internal/view/menu/MenuBuilder;->Q:Ljava/util/ArrayList;

    invoke-virtual {v2, v0}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    goto :goto_3

    .line 1124
    :cond_4
    iget-object v0, p0, Lcom/miui/internal/view/menu/MenuBuilder;->mActionItems:Ljava/util/ArrayList;

    invoke-virtual {v0}, Ljava/util/ArrayList;->clear()V

    .line 1125
    iget-object v0, p0, Lcom/miui/internal/view/menu/MenuBuilder;->Q:Ljava/util/ArrayList;

    invoke-virtual {v0}, Ljava/util/ArrayList;->clear()V

    .line 1126
    iget-object v0, p0, Lcom/miui/internal/view/menu/MenuBuilder;->Q:Ljava/util/ArrayList;

    invoke-virtual {p0}, Lcom/miui/internal/view/menu/MenuBuilder;->getVisibleItems()Ljava/util/ArrayList;

    move-result-object v1

    invoke-virtual {v0, v1}, Ljava/util/ArrayList;->addAll(Ljava/util/Collection;)Z

    .line 1128
    :cond_5
    iput-boolean v3, p0, Lcom/miui/internal/view/menu/MenuBuilder;->R:Z

    goto :goto_0
.end method

.method public getActionItems()Ljava/util/ArrayList;
    .locals 1
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "()",
            "Ljava/util/ArrayList",
            "<",
            "Lcom/miui/internal/view/menu/MenuItemImpl;",
            ">;"
        }
    .end annotation

    .prologue
    .line 1132
    invoke-virtual {p0}, Lcom/miui/internal/view/menu/MenuBuilder;->flagActionItems()V

    .line 1133
    iget-object v0, p0, Lcom/miui/internal/view/menu/MenuBuilder;->mActionItems:Ljava/util/ArrayList;

    return-object v0
.end method

.method protected getActionViewStatesKey()Ljava/lang/String;
    .locals 1

    .prologue
    .line 405
    const-string v0, "android:menu:actionviewstates"

    return-object v0
.end method

.method public getContext()Landroid/content/Context;
    .locals 1

    .prologue
    .line 778
    iget-object v0, p0, Lcom/miui/internal/view/menu/MenuBuilder;->mContext:Landroid/content/Context;

    return-object v0
.end method

.method public getExpandedItem()Lcom/miui/internal/view/menu/MenuItemImpl;
    .locals 1

    .prologue
    .line 1329
    iget-object v0, p0, Lcom/miui/internal/view/menu/MenuBuilder;->ad:Lcom/miui/internal/view/menu/MenuItemImpl;

    return-object v0
.end method

.method public getHeaderIcon()Landroid/graphics/drawable/Drawable;
    .locals 1

    .prologue
    .line 1245
    iget-object v0, p0, Lcom/miui/internal/view/menu/MenuBuilder;->V:Landroid/graphics/drawable/Drawable;

    return-object v0
.end method

.method public getHeaderTitle()Ljava/lang/CharSequence;
    .locals 1

    .prologue
    .line 1241
    iget-object v0, p0, Lcom/miui/internal/view/menu/MenuBuilder;->U:Ljava/lang/CharSequence;

    return-object v0
.end method

.method public getHeaderView()Landroid/view/View;
    .locals 1

    .prologue
    .line 1249
    iget-object v0, p0, Lcom/miui/internal/view/menu/MenuBuilder;->W:Landroid/view/View;

    return-object v0
.end method

.method public getItem(I)Landroid/view/MenuItem;
    .locals 1

    .prologue
    .line 702
    iget-object v0, p0, Lcom/miui/internal/view/menu/MenuBuilder;->N:Ljava/util/ArrayList;

    invoke-virtual {v0, p1}, Ljava/util/ArrayList;->get(I)Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Landroid/view/MenuItem;

    return-object v0
.end method

.method public getNonActionItems()Ljava/util/ArrayList;
    .locals 1
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "()",
            "Ljava/util/ArrayList",
            "<",
            "Lcom/miui/internal/view/menu/MenuItemImpl;",
            ">;"
        }
    .end annotation

    .prologue
    .line 1137
    invoke-virtual {p0}, Lcom/miui/internal/view/menu/MenuBuilder;->flagActionItems()V

    .line 1138
    iget-object v0, p0, Lcom/miui/internal/view/menu/MenuBuilder;->Q:Ljava/util/ArrayList;

    return-object v0
.end method

.method getResources()Landroid/content/res/Resources;
    .locals 1

    .prologue
    .line 774
    iget-object v0, p0, Lcom/miui/internal/view/menu/MenuBuilder;->J:Landroid/content/res/Resources;

    return-object v0
.end method

.method public getRootMenu()Lcom/miui/internal/view/menu/MenuBuilder;
    .locals 0

    .prologue
    .line 1258
    return-object p0
.end method

.method public getVisibleItems()Ljava/util/ArrayList;
    .locals 3
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "()",
            "Ljava/util/ArrayList",
            "<",
            "Lcom/miui/internal/view/menu/MenuItemImpl;",
            ">;"
        }
    .end annotation

    .prologue
    .line 1051
    iget-boolean v0, p0, Lcom/miui/internal/view/menu/MenuBuilder;->P:Z

    if-nez v0, :cond_0

    .line 1052
    iget-object v0, p0, Lcom/miui/internal/view/menu/MenuBuilder;->O:Ljava/util/ArrayList;

    .line 1067
    :goto_0
    return-object v0

    .line 1056
    :cond_0
    iget-object v0, p0, Lcom/miui/internal/view/menu/MenuBuilder;->O:Ljava/util/ArrayList;

    invoke-virtual {v0}, Ljava/util/ArrayList;->clear()V

    .line 1058
    iget-object v0, p0, Lcom/miui/internal/view/menu/MenuBuilder;->N:Ljava/util/ArrayList;

    invoke-virtual {v0}, Ljava/util/ArrayList;->iterator()Ljava/util/Iterator;

    move-result-object v1

    :cond_1
    :goto_1
    invoke-interface {v1}, Ljava/util/Iterator;->hasNext()Z

    move-result v0

    if-eqz v0, :cond_2

    invoke-interface {v1}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Lcom/miui/internal/view/menu/MenuItemImpl;

    .line 1059
    invoke-virtual {v0}, Lcom/miui/internal/view/menu/MenuItemImpl;->isVisible()Z

    move-result v2

    if-eqz v2, :cond_1

    .line 1060
    iget-object v2, p0, Lcom/miui/internal/view/menu/MenuBuilder;->O:Ljava/util/ArrayList;

    invoke-virtual {v2, v0}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    goto :goto_1

    .line 1064
    :cond_2
    const/4 v0, 0x0

    iput-boolean v0, p0, Lcom/miui/internal/view/menu/MenuBuilder;->P:Z

    .line 1065
    const/4 v0, 0x1

    iput-boolean v0, p0, Lcom/miui/internal/view/menu/MenuBuilder;->R:Z

    .line 1067
    iget-object v0, p0, Lcom/miui/internal/view/menu/MenuBuilder;->O:Ljava/util/ArrayList;

    goto :goto_0
.end method

.method public hasVisibleItems()Z
    .locals 4

    .prologue
    const/4 v1, 0x0

    .line 629
    invoke-virtual {p0}, Lcom/miui/internal/view/menu/MenuBuilder;->size()I

    move-result v3

    move v2, v1

    .line 631
    :goto_0
    if-ge v2, v3, :cond_1

    .line 632
    iget-object v0, p0, Lcom/miui/internal/view/menu/MenuBuilder;->N:Ljava/util/ArrayList;

    invoke-virtual {v0, v2}, Ljava/util/ArrayList;->get(I)Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Lcom/miui/internal/view/menu/MenuItemImpl;

    .line 633
    invoke-virtual {v0}, Lcom/miui/internal/view/menu/MenuItemImpl;->isVisible()Z

    move-result v0

    if-eqz v0, :cond_0

    .line 634
    const/4 v0, 0x1

    .line 638
    :goto_1
    return v0

    .line 631
    :cond_0
    add-int/lit8 v0, v2, 0x1

    move v2, v0

    goto :goto_0

    :cond_1
    move v0, v1

    .line 638
    goto :goto_1
.end method

.method isQwertyMode()Z
    .locals 1

    .prologue
    .line 741
    iget-boolean v0, p0, Lcom/miui/internal/view/menu/MenuBuilder;->K:Z

    return v0
.end method

.method public isShortcutKey(ILandroid/view/KeyEvent;)Z
    .locals 1

    .prologue
    .line 707
    invoke-virtual {p0, p1, p2}, Lcom/miui/internal/view/menu/MenuBuilder;->a(ILandroid/view/KeyEvent;)Lcom/miui/internal/view/menu/MenuItemImpl;

    move-result-object v0

    if-eqz v0, :cond_0

    const/4 v0, 0x1

    :goto_0
    return v0

    :cond_0
    const/4 v0, 0x0

    goto :goto_0
.end method

.method public isShortcutsVisible()Z
    .locals 1

    .prologue
    .line 770
    iget-boolean v0, p0, Lcom/miui/internal/view/menu/MenuBuilder;->L:Z

    return v0
.end method

.method public performIdentifierAction(II)Z
    .locals 1

    .prologue
    .line 914
    invoke-virtual {p0, p1}, Lcom/miui/internal/view/menu/MenuBuilder;->findItem(I)Landroid/view/MenuItem;

    move-result-object v0

    invoke-virtual {p0, v0, p2}, Lcom/miui/internal/view/menu/MenuBuilder;->performItemAction(Landroid/view/MenuItem;I)Z

    move-result v0

    return v0
.end method

.method public performItemAction(Landroid/view/MenuItem;I)Z
    .locals 6

    .prologue
    const/4 v0, 0x0

    const/4 v2, 0x1

    .line 918
    check-cast p1, Lcom/miui/internal/view/menu/MenuItemImpl;

    .line 920
    if-eqz p1, :cond_0

    invoke-virtual {p1}, Lcom/miui/internal/view/menu/MenuItemImpl;->isEnabled()Z

    move-result v1

    if-nez v1, :cond_1

    .line 954
    :cond_0
    :goto_0
    return v0

    .line 924
    :cond_1
    invoke-virtual {p1}, Lcom/miui/internal/view/menu/MenuItemImpl;->invoke()Z

    move-result v3

    .line 926
    invoke-virtual {p1}, Lcom/miui/internal/view/menu/MenuItemImpl;->getSupportActionProvider()Landroid/view/ActionProvider;

    move-result-object v4

    .line 927
    if-eqz v4, :cond_2

    invoke-virtual {v4}, Landroid/view/ActionProvider;->hasSubMenu()Z

    move-result v1

    if-eqz v1, :cond_2

    move v1, v2

    .line 928
    :goto_1
    invoke-virtual {p1}, Lcom/miui/internal/view/menu/MenuItemImpl;->hasCollapsibleActionView()Z

    move-result v5

    if-eqz v5, :cond_3

    .line 929
    invoke-virtual {p1}, Lcom/miui/internal/view/menu/MenuItemImpl;->expandActionView()Z

    move-result v0

    or-int/2addr v0, v3

    .line 930
    if-eqz v0, :cond_0

    .line 931
    invoke-virtual {p0, v2}, Lcom/miui/internal/view/menu/MenuBuilder;->c(Z)V

    goto :goto_0

    :cond_2
    move v1, v0

    .line 927
    goto :goto_1

    .line 933
    :cond_3
    invoke-virtual {p1}, Lcom/miui/internal/view/menu/MenuItemImpl;->hasSubMenu()Z

    move-result v5

    if-nez v5, :cond_4

    if-eqz v1, :cond_7

    .line 934
    :cond_4
    invoke-virtual {p0, v0}, Lcom/miui/internal/view/menu/MenuBuilder;->c(Z)V

    .line 936
    invoke-virtual {p1}, Lcom/miui/internal/view/menu/MenuItemImpl;->hasSubMenu()Z

    move-result v0

    if-nez v0, :cond_5

    .line 937
    new-instance v0, Lcom/miui/internal/view/menu/SubMenuBuilder;

    invoke-virtual {p0}, Lcom/miui/internal/view/menu/MenuBuilder;->getContext()Landroid/content/Context;

    move-result-object v5

    invoke-direct {v0, v5, p0, p1}, Lcom/miui/internal/view/menu/SubMenuBuilder;-><init>(Landroid/content/Context;Lcom/miui/internal/view/menu/MenuBuilder;Lcom/miui/internal/view/menu/MenuItemImpl;)V

    invoke-virtual {p1, v0}, Lcom/miui/internal/view/menu/MenuItemImpl;->b(Lcom/miui/internal/view/menu/SubMenuBuilder;)V

    .line 940
    :cond_5
    invoke-virtual {p1}, Lcom/miui/internal/view/menu/MenuItemImpl;->getSubMenu()Landroid/view/SubMenu;

    move-result-object v0

    check-cast v0, Lcom/miui/internal/view/menu/SubMenuBuilder;

    .line 941
    if-eqz v1, :cond_6

    .line 942
    invoke-virtual {v4, v0}, Landroid/view/ActionProvider;->onPrepareSubMenu(Landroid/view/SubMenu;)V

    .line 944
    :cond_6
    invoke-direct {p0, v0}, Lcom/miui/internal/view/menu/MenuBuilder;->a(Lcom/miui/internal/view/menu/SubMenuBuilder;)Z

    move-result v0

    or-int/2addr v0, v3

    .line 945
    if-nez v0, :cond_0

    .line 946
    invoke-virtual {p0, v2}, Lcom/miui/internal/view/menu/MenuBuilder;->c(Z)V

    goto :goto_0

    .line 949
    :cond_7
    and-int/lit8 v0, p2, 0x1

    if-nez v0, :cond_8

    .line 950
    invoke-virtual {p0, v2}, Lcom/miui/internal/view/menu/MenuBuilder;->c(Z)V

    :cond_8
    move v0, v3

    goto :goto_0
.end method

.method public performShortcut(ILandroid/view/KeyEvent;I)Z
    .locals 2

    .prologue
    .line 807
    invoke-virtual {p0, p1, p2}, Lcom/miui/internal/view/menu/MenuBuilder;->a(ILandroid/view/KeyEvent;)Lcom/miui/internal/view/menu/MenuItemImpl;

    move-result-object v1

    .line 809
    const/4 v0, 0x0

    .line 811
    if-eqz v1, :cond_0

    .line 812
    invoke-virtual {p0, v1, p3}, Lcom/miui/internal/view/menu/MenuBuilder;->performItemAction(Landroid/view/MenuItem;I)Z

    move-result v0

    .line 815
    :cond_0
    and-int/lit8 v1, p3, 0x2

    if-eqz v1, :cond_1

    .line 816
    const/4 v1, 0x1

    invoke-virtual {p0, v1}, Lcom/miui/internal/view/menu/MenuBuilder;->c(Z)V

    .line 819
    :cond_1
    return v0
.end method

.method public removeGroup(I)V
    .locals 5

    .prologue
    const/4 v1, 0x0

    .line 513
    invoke-virtual {p0, p1}, Lcom/miui/internal/view/menu/MenuBuilder;->findGroupIndex(I)I

    move-result v3

    .line 515
    if-ltz v3, :cond_1

    .line 516
    iget-object v0, p0, Lcom/miui/internal/view/menu/MenuBuilder;->N:Ljava/util/ArrayList;

    invoke-virtual {v0}, Ljava/util/ArrayList;->size()I

    move-result v0

    sub-int v4, v0, v3

    move v0, v1

    .line 518
    :goto_0
    add-int/lit8 v2, v0, 0x1

    if-ge v0, v4, :cond_0

    iget-object v0, p0, Lcom/miui/internal/view/menu/MenuBuilder;->N:Ljava/util/ArrayList;

    invoke-virtual {v0, v3}, Ljava/util/ArrayList;->get(I)Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Lcom/miui/internal/view/menu/MenuItemImpl;

    invoke-virtual {v0}, Lcom/miui/internal/view/menu/MenuItemImpl;->getGroupId()I

    move-result v0

    if-ne v0, p1, :cond_0

    .line 520
    invoke-direct {p0, v3, v1}, Lcom/miui/internal/view/menu/MenuBuilder;->a(IZ)V

    move v0, v2

    goto :goto_0

    .line 524
    :cond_0
    const/4 v0, 0x1

    invoke-virtual {p0, v0}, Lcom/miui/internal/view/menu/MenuBuilder;->d(Z)V

    .line 526
    :cond_1
    return-void
.end method

.method public removeItem(I)V
    .locals 2

    .prologue
    .line 508
    invoke-virtual {p0, p1}, Lcom/miui/internal/view/menu/MenuBuilder;->findItemIndex(I)I

    move-result v0

    const/4 v1, 0x1

    invoke-direct {p0, v0, v1}, Lcom/miui/internal/view/menu/MenuBuilder;->a(IZ)V

    .line 509
    return-void
.end method

.method public removeItemAt(I)V
    .locals 1

    .prologue
    .line 549
    const/4 v0, 0x1

    invoke-direct {p0, p1, v0}, Lcom/miui/internal/view/menu/MenuBuilder;->a(IZ)V

    .line 550
    return-void
.end method

.method public removeMenuPresenter(Lcom/miui/internal/view/menu/MenuPresenter;)V
    .locals 3

    .prologue
    .line 248
    iget-object v0, p0, Lcom/miui/internal/view/menu/MenuBuilder;->ac:Ljava/util/concurrent/CopyOnWriteArrayList;

    invoke-virtual {v0}, Ljava/util/concurrent/CopyOnWriteArrayList;->iterator()Ljava/util/Iterator;

    move-result-object v2

    :cond_0
    :goto_0
    invoke-interface {v2}, Ljava/util/Iterator;->hasNext()Z

    move-result v0

    if-eqz v0, :cond_2

    invoke-interface {v2}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Ljava/lang/ref/WeakReference;

    .line 249
    invoke-virtual {v0}, Ljava/lang/ref/WeakReference;->get()Ljava/lang/Object;

    move-result-object v1

    check-cast v1, Lcom/miui/internal/view/menu/MenuPresenter;

    .line 250
    if-eqz v1, :cond_1

    if-ne v1, p1, :cond_0

    .line 251
    :cond_1
    iget-object v1, p0, Lcom/miui/internal/view/menu/MenuBuilder;->ac:Ljava/util/concurrent/CopyOnWriteArrayList;

    invoke-virtual {v1, v0}, Ljava/util/concurrent/CopyOnWriteArrayList;->remove(Ljava/lang/Object;)Z

    goto :goto_0

    .line 254
    :cond_2
    return-void
.end method

.method public restoreActionViewStates(Landroid/os/Bundle;)V
    .locals 7

    .prologue
    .line 375
    if-nez p1, :cond_1

    .line 402
    :cond_0
    :goto_0
    return-void

    .line 379
    :cond_1
    invoke-virtual {p0}, Lcom/miui/internal/view/menu/MenuBuilder;->getActionViewStatesKey()Ljava/lang/String;

    move-result-object v0

    invoke-virtual {p1, v0}, Landroid/os/Bundle;->getSparseParcelableArray(Ljava/lang/String;)Landroid/util/SparseArray;

    move-result-object v2

    .line 382
    invoke-virtual {p0}, Lcom/miui/internal/view/menu/MenuBuilder;->size()I

    move-result v3

    .line 383
    const/4 v0, 0x0

    move v1, v0

    :goto_1
    if-ge v1, v3, :cond_4

    .line 384
    invoke-virtual {p0, v1}, Lcom/miui/internal/view/menu/MenuBuilder;->getItem(I)Landroid/view/MenuItem;

    move-result-object v0

    .line 385
    invoke-interface {v0}, Landroid/view/MenuItem;->getActionView()Landroid/view/View;

    move-result-object v4

    .line 386
    if-eqz v4, :cond_2

    invoke-virtual {v4}, Landroid/view/View;->getId()I

    move-result v5

    const/4 v6, -0x1

    if-eq v5, v6, :cond_2

    .line 387
    invoke-virtual {v4, v2}, Landroid/view/View;->restoreHierarchyState(Landroid/util/SparseArray;)V

    .line 389
    :cond_2
    invoke-interface {v0}, Landroid/view/MenuItem;->hasSubMenu()Z

    move-result v4

    if-eqz v4, :cond_3

    .line 390
    invoke-interface {v0}, Landroid/view/MenuItem;->getSubMenu()Landroid/view/SubMenu;

    move-result-object v0

    check-cast v0, Lcom/miui/internal/view/menu/SubMenuBuilder;

    .line 391
    invoke-virtual {v0, p1}, Lcom/miui/internal/view/menu/SubMenuBuilder;->restoreActionViewStates(Landroid/os/Bundle;)V

    .line 383
    :cond_3
    add-int/lit8 v0, v1, 0x1

    move v1, v0

    goto :goto_1

    .line 395
    :cond_4
    const-string v0, "android:menu:expandedactionview"

    invoke-virtual {p1, v0}, Landroid/os/Bundle;->getInt(Ljava/lang/String;)I

    move-result v0

    .line 396
    if-lez v0, :cond_0

    .line 397
    invoke-virtual {p0, v0}, Lcom/miui/internal/view/menu/MenuBuilder;->findItem(I)Landroid/view/MenuItem;

    move-result-object v0

    .line 398
    if-eqz v0, :cond_0

    .line 399
    invoke-interface {v0}, Landroid/view/MenuItem;->expandActionView()Z

    goto :goto_0
.end method

.method public restorePresenterStates(Landroid/os/Bundle;)V
    .locals 0

    .prologue
    .line 344
    invoke-direct {p0, p1}, Lcom/miui/internal/view/menu/MenuBuilder;->dispatchRestoreInstanceState(Landroid/os/Bundle;)V

    .line 345
    return-void
.end method

.method public saveActionViewStates(Landroid/os/Bundle;)V
    .locals 7

    .prologue
    .line 348
    const/4 v1, 0x0

    .line 350
    invoke-virtual {p0}, Lcom/miui/internal/view/menu/MenuBuilder;->size()I

    move-result v3

    .line 351
    const/4 v0, 0x0

    move v2, v0

    move-object v0, v1

    :goto_0
    if-ge v2, v3, :cond_3

    .line 352
    invoke-virtual {p0, v2}, Lcom/miui/internal/view/menu/MenuBuilder;->getItem(I)Landroid/view/MenuItem;

    move-result-object v4

    .line 353
    invoke-interface {v4}, Landroid/view/MenuItem;->getActionView()Landroid/view/View;

    move-result-object v1

    .line 354
    if-eqz v1, :cond_1

    invoke-virtual {v1}, Landroid/view/View;->getId()I

    move-result v5

    const/4 v6, -0x1

    if-eq v5, v6, :cond_1

    .line 355
    if-nez v0, :cond_0

    .line 356
    new-instance v0, Landroid/util/SparseArray;

    invoke-direct {v0}, Landroid/util/SparseArray;-><init>()V

    .line 358
    :cond_0
    invoke-virtual {v1, v0}, Landroid/view/View;->saveHierarchyState(Landroid/util/SparseArray;)V

    .line 359
    invoke-interface {v4}, Landroid/view/MenuItem;->isActionViewExpanded()Z

    move-result v1

    if-eqz v1, :cond_1

    .line 360
    const-string v1, "android:menu:expandedactionview"

    invoke-interface {v4}, Landroid/view/MenuItem;->getItemId()I

    move-result v5

    invoke-virtual {p1, v1, v5}, Landroid/os/Bundle;->putInt(Ljava/lang/String;I)V

    :cond_1
    move-object v1, v0

    .line 363
    invoke-interface {v4}, Landroid/view/MenuItem;->hasSubMenu()Z

    move-result v0

    if-eqz v0, :cond_2

    .line 364
    invoke-interface {v4}, Landroid/view/MenuItem;->getSubMenu()Landroid/view/SubMenu;

    move-result-object v0

    check-cast v0, Lcom/miui/internal/view/menu/SubMenuBuilder;

    .line 365
    invoke-virtual {v0, p1}, Lcom/miui/internal/view/menu/SubMenuBuilder;->saveActionViewStates(Landroid/os/Bundle;)V

    .line 351
    :cond_2
    add-int/lit8 v0, v2, 0x1

    move v2, v0

    move-object v0, v1

    goto :goto_0

    .line 369
    :cond_3
    if-eqz v0, :cond_4

    .line 370
    invoke-virtual {p0}, Lcom/miui/internal/view/menu/MenuBuilder;->getActionViewStatesKey()Ljava/lang/String;

    move-result-object v1

    invoke-virtual {p1, v1, v0}, Landroid/os/Bundle;->putSparseParcelableArray(Ljava/lang/String;Landroid/util/SparseArray;)V

    .line 372
    :cond_4
    return-void
.end method

.method public savePresenterStates(Landroid/os/Bundle;)V
    .locals 0

    .prologue
    .line 340
    invoke-direct {p0, p1}, Lcom/miui/internal/view/menu/MenuBuilder;->dispatchSaveInstanceState(Landroid/os/Bundle;)V

    .line 341
    return-void
.end method

.method public setCallback(Lcom/miui/internal/view/menu/MenuBuilder$Callback;)V
    .locals 0

    .prologue
    .line 409
    iput-object p1, p0, Lcom/miui/internal/view/menu/MenuBuilder;->M:Lcom/miui/internal/view/menu/MenuBuilder$Callback;

    .line 410
    return-void
.end method

.method public setCurrentMenuInfo(Landroid/view/ContextMenu$ContextMenuInfo;)V
    .locals 0

    .prologue
    .line 1269
    iput-object p1, p0, Lcom/miui/internal/view/menu/MenuBuilder;->T:Landroid/view/ContextMenu$ContextMenuInfo;

    .line 1270
    return-void
.end method

.method public setDefaultShowAsAction(I)Lcom/miui/internal/view/menu/MenuBuilder;
    .locals 0

    .prologue
    .line 225
    iput p1, p0, Lcom/miui/internal/view/menu/MenuBuilder;->S:I

    .line 226
    return-object p0
.end method

.method public setGroupCheckable(IZZ)V
    .locals 3

    .prologue
    .line 591
    iget-object v0, p0, Lcom/miui/internal/view/menu/MenuBuilder;->N:Ljava/util/ArrayList;

    invoke-virtual {v0}, Ljava/util/ArrayList;->iterator()Ljava/util/Iterator;

    move-result-object v1

    :cond_0
    :goto_0
    invoke-interface {v1}, Ljava/util/Iterator;->hasNext()Z

    move-result v0

    if-eqz v0, :cond_1

    invoke-interface {v1}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Lcom/miui/internal/view/menu/MenuItemImpl;

    .line 592
    invoke-virtual {v0}, Lcom/miui/internal/view/menu/MenuItemImpl;->getGroupId()I

    move-result v2

    if-ne v2, p1, :cond_0

    .line 593
    invoke-virtual {v0, p3}, Lcom/miui/internal/view/menu/MenuItemImpl;->setExclusiveCheckable(Z)V

    .line 594
    invoke-virtual {v0, p2}, Lcom/miui/internal/view/menu/MenuItemImpl;->setCheckable(Z)Landroid/view/MenuItem;

    goto :goto_0

    .line 597
    :cond_1
    return-void
.end method

.method public setGroupEnabled(IZ)V
    .locals 3

    .prologue
    .line 620
    iget-object v0, p0, Lcom/miui/internal/view/menu/MenuBuilder;->N:Ljava/util/ArrayList;

    invoke-virtual {v0}, Ljava/util/ArrayList;->iterator()Ljava/util/Iterator;

    move-result-object v1

    :cond_0
    :goto_0
    invoke-interface {v1}, Ljava/util/Iterator;->hasNext()Z

    move-result v0

    if-eqz v0, :cond_1

    invoke-interface {v1}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Lcom/miui/internal/view/menu/MenuItemImpl;

    .line 621
    invoke-virtual {v0}, Lcom/miui/internal/view/menu/MenuItemImpl;->getGroupId()I

    move-result v2

    if-ne v2, p1, :cond_0

    .line 622
    invoke-virtual {v0, p2}, Lcom/miui/internal/view/menu/MenuItemImpl;->setEnabled(Z)Landroid/view/MenuItem;

    goto :goto_0

    .line 625
    :cond_1
    return-void
.end method

.method public setGroupVisible(IZ)V
    .locals 5

    .prologue
    const/4 v2, 0x1

    .line 604
    const/4 v0, 0x0

    .line 605
    iget-object v1, p0, Lcom/miui/internal/view/menu/MenuBuilder;->N:Ljava/util/ArrayList;

    invoke-virtual {v1}, Ljava/util/ArrayList;->iterator()Ljava/util/Iterator;

    move-result-object v3

    move v1, v0

    :goto_0
    invoke-interface {v3}, Ljava/util/Iterator;->hasNext()Z

    move-result v0

    if-eqz v0, :cond_0

    invoke-interface {v3}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Lcom/miui/internal/view/menu/MenuItemImpl;

    .line 606
    invoke-virtual {v0}, Lcom/miui/internal/view/menu/MenuItemImpl;->getGroupId()I

    move-result v4

    if-ne v4, p1, :cond_2

    .line 607
    invoke-virtual {v0, p2}, Lcom/miui/internal/view/menu/MenuItemImpl;->w(Z)Z

    move-result v0

    if-eqz v0, :cond_2

    move v0, v2

    :goto_1
    move v1, v0

    .line 608
    goto :goto_0

    .line 613
    :cond_0
    if-eqz v1, :cond_1

    .line 614
    invoke-virtual {p0, v2}, Lcom/miui/internal/view/menu/MenuBuilder;->d(Z)V

    .line 616
    :cond_1
    return-void

    :cond_2
    move v0, v1

    goto :goto_1
.end method

.method protected setHeaderIconInt(I)Lcom/miui/internal/view/menu/MenuBuilder;
    .locals 6

    .prologue
    const/4 v2, 0x0

    .line 1224
    const/4 v1, 0x0

    move-object v0, p0

    move v3, p1

    move-object v4, v2

    move-object v5, v2

    invoke-direct/range {v0 .. v5}, Lcom/miui/internal/view/menu/MenuBuilder;->a(ILjava/lang/CharSequence;ILandroid/graphics/drawable/Drawable;Landroid/view/View;)V

    .line 1225
    return-object p0
.end method

.method protected setHeaderIconInt(Landroid/graphics/drawable/Drawable;)Lcom/miui/internal/view/menu/MenuBuilder;
    .locals 6

    .prologue
    const/4 v2, 0x0

    const/4 v1, 0x0

    .line 1212
    move-object v0, p0

    move v3, v1

    move-object v4, p1

    move-object v5, v2

    invoke-direct/range {v0 .. v5}, Lcom/miui/internal/view/menu/MenuBuilder;->a(ILjava/lang/CharSequence;ILandroid/graphics/drawable/Drawable;Landroid/view/View;)V

    .line 1213
    return-object p0
.end method

.method protected setHeaderTitleInt(I)Lcom/miui/internal/view/menu/MenuBuilder;
    .locals 6

    .prologue
    const/4 v2, 0x0

    .line 1200
    const/4 v3, 0x0

    move-object v0, p0

    move v1, p1

    move-object v4, v2

    move-object v5, v2

    invoke-direct/range {v0 .. v5}, Lcom/miui/internal/view/menu/MenuBuilder;->a(ILjava/lang/CharSequence;ILandroid/graphics/drawable/Drawable;Landroid/view/View;)V

    .line 1201
    return-object p0
.end method

.method protected setHeaderTitleInt(Ljava/lang/CharSequence;)Lcom/miui/internal/view/menu/MenuBuilder;
    .locals 6

    .prologue
    const/4 v4, 0x0

    const/4 v1, 0x0

    .line 1188
    move-object v0, p0

    move-object v2, p1

    move v3, v1

    move-object v5, v4

    invoke-direct/range {v0 .. v5}, Lcom/miui/internal/view/menu/MenuBuilder;->a(ILjava/lang/CharSequence;ILandroid/graphics/drawable/Drawable;Landroid/view/View;)V

    .line 1189
    return-object p0
.end method

.method protected setHeaderViewInt(Landroid/view/View;)Lcom/miui/internal/view/menu/MenuBuilder;
    .locals 6

    .prologue
    const/4 v2, 0x0

    const/4 v1, 0x0

    .line 1236
    move-object v0, p0

    move v3, v1

    move-object v4, v2

    move-object v5, p1

    invoke-direct/range {v0 .. v5}, Lcom/miui/internal/view/menu/MenuBuilder;->a(ILjava/lang/CharSequence;ILandroid/graphics/drawable/Drawable;Landroid/view/View;)V

    .line 1237
    return-object p0
.end method

.method public setQwertyMode(Z)V
    .locals 1

    .prologue
    .line 712
    iput-boolean p1, p0, Lcom/miui/internal/view/menu/MenuBuilder;->K:Z

    .line 714
    const/4 v0, 0x0

    invoke-virtual {p0, v0}, Lcom/miui/internal/view/menu/MenuBuilder;->d(Z)V

    .line 715
    return-void
.end method

.method public setShortcutsVisible(Z)V
    .locals 1

    .prologue
    .line 752
    iget-boolean v0, p0, Lcom/miui/internal/view/menu/MenuBuilder;->L:Z

    if-ne v0, p1, :cond_0

    .line 758
    :goto_0
    return-void

    .line 756
    :cond_0
    invoke-direct {p0, p1}, Lcom/miui/internal/view/menu/MenuBuilder;->b(Z)V

    .line 757
    const/4 v0, 0x0

    invoke-virtual {p0, v0}, Lcom/miui/internal/view/menu/MenuBuilder;->d(Z)V

    goto :goto_0
.end method

.method public size()I
    .locals 1

    .prologue
    .line 697
    iget-object v0, p0, Lcom/miui/internal/view/menu/MenuBuilder;->N:Ljava/util/ArrayList;

    invoke-virtual {v0}, Ljava/util/ArrayList;->size()I

    move-result v0

    return v0
.end method

.method public startDispatchingItemsChanged()V
    .locals 2

    .prologue
    const/4 v1, 0x0

    .line 1020
    iput-boolean v1, p0, Lcom/miui/internal/view/menu/MenuBuilder;->X:Z

    .line 1022
    iget-boolean v0, p0, Lcom/miui/internal/view/menu/MenuBuilder;->Y:Z

    if-eqz v0, :cond_0

    .line 1023
    iput-boolean v1, p0, Lcom/miui/internal/view/menu/MenuBuilder;->Y:Z

    .line 1024
    const/4 v0, 0x1

    invoke-virtual {p0, v0}, Lcom/miui/internal/view/menu/MenuBuilder;->d(Z)V

    .line 1026
    :cond_0
    return-void
.end method

.method public stopDispatchingItemsChanged()V
    .locals 1

    .prologue
    .line 1013
    iget-boolean v0, p0, Lcom/miui/internal/view/menu/MenuBuilder;->X:Z

    if-nez v0, :cond_0

    .line 1014
    const/4 v0, 0x1

    iput-boolean v0, p0, Lcom/miui/internal/view/menu/MenuBuilder;->X:Z

    .line 1015
    const/4 v0, 0x0

    iput-boolean v0, p0, Lcom/miui/internal/view/menu/MenuBuilder;->Y:Z

    .line 1017
    :cond_0
    return-void
.end method
