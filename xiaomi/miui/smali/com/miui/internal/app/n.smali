.class Lcom/miui/internal/app/n;
.super Lmiui/view/PagerAdapter;
.source "SourceFile"


# annotations
.annotation system Ldalvik/annotation/MemberClasses;
    value = {
        Lcom/miui/internal/app/n$a;
    }
.end annotation


# instance fields
.field private kO:Landroid/app/FragmentManager;

.field private mContext:Landroid/content/Context;

.field private yJ:Ljava/util/ArrayList;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Ljava/util/ArrayList",
            "<",
            "Lcom/miui/internal/app/n$a;",
            ">;"
        }
    .end annotation
.end field

.field private yK:Landroid/app/FragmentTransaction;

.field private yL:Landroid/app/Fragment;


# direct methods
.method public constructor <init>(Landroid/content/Context;Landroid/app/FragmentManager;Lmiui/view/ViewPager;)V
    .locals 2

    .prologue
    const/4 v1, 0x0

    .line 254
    invoke-direct {p0}, Lmiui/view/PagerAdapter;-><init>()V

    .line 250
    new-instance v0, Ljava/util/ArrayList;

    invoke-direct {v0}, Ljava/util/ArrayList;-><init>()V

    iput-object v0, p0, Lcom/miui/internal/app/n;->yJ:Ljava/util/ArrayList;

    .line 251
    iput-object v1, p0, Lcom/miui/internal/app/n;->yK:Landroid/app/FragmentTransaction;

    .line 252
    iput-object v1, p0, Lcom/miui/internal/app/n;->yL:Landroid/app/Fragment;

    .line 255
    iput-object p1, p0, Lcom/miui/internal/app/n;->mContext:Landroid/content/Context;

    .line 256
    iput-object p2, p0, Lcom/miui/internal/app/n;->kO:Landroid/app/FragmentManager;

    .line 257
    invoke-virtual {p3, p0}, Lmiui/view/ViewPager;->setAdapter(Lmiui/view/PagerAdapter;)V

    .line 258
    return-void
.end method

.method private c(Landroid/app/Fragment;)V
    .locals 2

    .prologue
    .line 442
    if-eqz p1, :cond_0

    .line 443
    invoke-virtual {p1}, Landroid/app/Fragment;->getFragmentManager()Landroid/app/FragmentManager;

    move-result-object v0

    .line 444
    if-eqz v0, :cond_0

    .line 445
    invoke-virtual {v0}, Landroid/app/FragmentManager;->beginTransaction()Landroid/app/FragmentTransaction;

    move-result-object v1

    .line 446
    invoke-virtual {v1, p1}, Landroid/app/FragmentTransaction;->remove(Landroid/app/Fragment;)Landroid/app/FragmentTransaction;

    .line 447
    invoke-virtual {v1}, Landroid/app/FragmentTransaction;->commitAllowingStateLoss()I

    .line 448
    invoke-virtual {v0}, Landroid/app/FragmentManager;->executePendingTransactions()Z

    .line 451
    :cond_0
    return-void
.end method

.method private cA()V
    .locals 5

    .prologue
    const/4 v1, 0x0

    .line 431
    iget-object v0, p0, Lcom/miui/internal/app/n;->kO:Landroid/app/FragmentManager;

    invoke-virtual {v0}, Landroid/app/FragmentManager;->beginTransaction()Landroid/app/FragmentTransaction;

    move-result-object v2

    .line 432
    iget-object v0, p0, Lcom/miui/internal/app/n;->yJ:Ljava/util/ArrayList;

    invoke-virtual {v0}, Ljava/util/ArrayList;->size()I

    move-result v3

    move v0, v1

    .line 433
    :goto_0
    if-ge v0, v3, :cond_0

    .line 434
    invoke-virtual {p0, v0, v1}, Lcom/miui/internal/app/n;->d(IZ)Landroid/app/Fragment;

    move-result-object v4

    .line 435
    invoke-virtual {v2, v4}, Landroid/app/FragmentTransaction;->remove(Landroid/app/Fragment;)Landroid/app/FragmentTransaction;

    .line 433
    add-int/lit8 v0, v0, 0x1

    goto :goto_0

    .line 437
    :cond_0
    invoke-virtual {v2}, Landroid/app/FragmentTransaction;->commitAllowingStateLoss()I

    .line 438
    iget-object v0, p0, Lcom/miui/internal/app/n;->kO:Landroid/app/FragmentManager;

    invoke-virtual {v0}, Landroid/app/FragmentManager;->executePendingTransactions()Z

    .line 439
    return-void
.end method


# virtual methods
.method A(I)V
    .locals 1

    .prologue
    .line 373
    const/4 v0, 0x0

    invoke-virtual {p0, p1, v0}, Lcom/miui/internal/app/n;->d(IZ)Landroid/app/Fragment;

    move-result-object v0

    invoke-direct {p0, v0}, Lcom/miui/internal/app/n;->c(Landroid/app/Fragment;)V

    .line 374
    iget-object v0, p0, Lcom/miui/internal/app/n;->yJ:Ljava/util/ArrayList;

    invoke-virtual {v0, p1}, Ljava/util/ArrayList;->remove(I)Ljava/lang/Object;

    .line 375
    invoke-virtual {p0}, Lcom/miui/internal/app/n;->notifyDataSetChanged()V

    .line 376
    return-void
.end method

.method a(Landroid/app/ActionBar$Tab;)I
    .locals 4

    .prologue
    .line 390
    iget-object v0, p0, Lcom/miui/internal/app/n;->yJ:Ljava/util/ArrayList;

    invoke-virtual {v0}, Ljava/util/ArrayList;->size()I

    move-result v2

    .line 391
    const/4 v1, 0x0

    :goto_0
    if-ge v1, v2, :cond_1

    .line 392
    iget-object v0, p0, Lcom/miui/internal/app/n;->yJ:Ljava/util/ArrayList;

    invoke-virtual {v0, v1}, Ljava/util/ArrayList;->get(I)Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Lcom/miui/internal/app/n$a;

    .line 393
    iget-object v3, v0, Lcom/miui/internal/app/n$a;->IB:Landroid/app/ActionBar$Tab;

    if-ne v3, p1, :cond_0

    .line 394
    iget-object v0, v0, Lcom/miui/internal/app/n$a;->Iz:Landroid/app/Fragment;

    invoke-direct {p0, v0}, Lcom/miui/internal/app/n;->c(Landroid/app/Fragment;)V

    .line 395
    iget-object v0, p0, Lcom/miui/internal/app/n;->yJ:Ljava/util/ArrayList;

    invoke-virtual {v0, v1}, Ljava/util/ArrayList;->remove(I)Ljava/lang/Object;

    .line 396
    invoke-virtual {p0}, Lcom/miui/internal/app/n;->notifyDataSetChanged()V

    move v0, v1

    .line 400
    :goto_1
    return v0

    .line 391
    :cond_0
    add-int/lit8 v1, v1, 0x1

    goto :goto_0

    .line 400
    :cond_1
    const/4 v0, -0x1

    goto :goto_1
.end method

.method a(Ljava/lang/String;ILjava/lang/Class;Landroid/os/Bundle;Landroid/app/ActionBar$Tab;Z)I
    .locals 8
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Ljava/lang/String;",
            "I",
            "Ljava/lang/Class",
            "<+",
            "Landroid/app/Fragment;",
            ">;",
            "Landroid/os/Bundle;",
            "Landroid/app/ActionBar$Tab;",
            "Z)I"
        }
    .end annotation

    .prologue
    .line 367
    iget-object v7, p0, Lcom/miui/internal/app/n;->yJ:Ljava/util/ArrayList;

    new-instance v0, Lcom/miui/internal/app/n$a;

    move-object v1, p0

    move-object v2, p1

    move-object v3, p3

    move-object v4, p4

    move-object v5, p5

    move v6, p6

    invoke-direct/range {v0 .. v6}, Lcom/miui/internal/app/n$a;-><init>(Lcom/miui/internal/app/n;Ljava/lang/String;Ljava/lang/Class;Landroid/os/Bundle;Landroid/app/ActionBar$Tab;Z)V

    invoke-virtual {v7, p2, v0}, Ljava/util/ArrayList;->add(ILjava/lang/Object;)V

    .line 368
    invoke-virtual {p0}, Lcom/miui/internal/app/n;->notifyDataSetChanged()V

    .line 369
    return p2
.end method

.method a(Ljava/lang/String;Ljava/lang/Class;Landroid/os/Bundle;Landroid/app/ActionBar$Tab;Z)I
    .locals 8
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Ljava/lang/String;",
            "Ljava/lang/Class",
            "<+",
            "Landroid/app/Fragment;",
            ">;",
            "Landroid/os/Bundle;",
            "Landroid/app/ActionBar$Tab;",
            "Z)I"
        }
    .end annotation

    .prologue
    .line 361
    iget-object v7, p0, Lcom/miui/internal/app/n;->yJ:Ljava/util/ArrayList;

    new-instance v0, Lcom/miui/internal/app/n$a;

    move-object v1, p0

    move-object v2, p1

    move-object v3, p2

    move-object v4, p3

    move-object v5, p4

    move v6, p5

    invoke-direct/range {v0 .. v6}, Lcom/miui/internal/app/n$a;-><init>(Lcom/miui/internal/app/n;Ljava/lang/String;Ljava/lang/Class;Landroid/os/Bundle;Landroid/app/ActionBar$Tab;Z)V

    invoke-virtual {v7, v0}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    .line 362
    invoke-virtual {p0}, Lcom/miui/internal/app/n;->notifyDataSetChanged()V

    .line 363
    iget-object v0, p0, Lcom/miui/internal/app/n;->yJ:Ljava/util/ArrayList;

    invoke-virtual {v0}, Ljava/util/ArrayList;->size()I

    move-result v0

    add-int/lit8 v0, v0, -0x1

    return v0
.end method

.method b(Landroid/app/Fragment;)I
    .locals 4

    .prologue
    const/4 v1, 0x0

    .line 404
    iget-object v0, p0, Lcom/miui/internal/app/n;->yJ:Ljava/util/ArrayList;

    invoke-virtual {v0}, Ljava/util/ArrayList;->size()I

    move-result v2

    move v0, v1

    .line 405
    :goto_0
    if-ge v0, v2, :cond_1

    .line 406
    invoke-virtual {p0, v0, v1}, Lcom/miui/internal/app/n;->d(IZ)Landroid/app/Fragment;

    move-result-object v3

    if-ne v3, p1, :cond_0

    .line 407
    invoke-direct {p0, p1}, Lcom/miui/internal/app/n;->c(Landroid/app/Fragment;)V

    .line 408
    iget-object v1, p0, Lcom/miui/internal/app/n;->yJ:Ljava/util/ArrayList;

    invoke-virtual {v1, v0}, Ljava/util/ArrayList;->remove(I)Ljava/lang/Object;

    .line 409
    invoke-virtual {p0}, Lcom/miui/internal/app/n;->notifyDataSetChanged()V

    .line 413
    :goto_1
    return v0

    .line 405
    :cond_0
    add-int/lit8 v0, v0, 0x1

    goto :goto_0

    .line 413
    :cond_1
    const/4 v0, -0x1

    goto :goto_1
.end method

.method cz()V
    .locals 1

    .prologue
    .line 417
    invoke-direct {p0}, Lcom/miui/internal/app/n;->cA()V

    .line 418
    iget-object v0, p0, Lcom/miui/internal/app/n;->yJ:Ljava/util/ArrayList;

    invoke-virtual {v0}, Ljava/util/ArrayList;->clear()V

    .line 419
    invoke-virtual {p0}, Lcom/miui/internal/app/n;->notifyDataSetChanged()V

    .line 420
    return-void
.end method

.method d(IZ)Landroid/app/Fragment;
    .locals 5

    .prologue
    const/4 v4, 0x0

    .line 347
    iget-object v0, p0, Lcom/miui/internal/app/n;->yJ:Ljava/util/ArrayList;

    invoke-virtual {v0, p1}, Ljava/util/ArrayList;->get(I)Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Lcom/miui/internal/app/n$a;

    .line 348
    iget-object v1, v0, Lcom/miui/internal/app/n$a;->Iz:Landroid/app/Fragment;

    if-nez v1, :cond_0

    .line 350
    iget-object v1, p0, Lcom/miui/internal/app/n;->kO:Landroid/app/FragmentManager;

    iget-object v2, v0, Lcom/miui/internal/app/n$a;->tag:Ljava/lang/String;

    invoke-virtual {v1, v2}, Landroid/app/FragmentManager;->findFragmentByTag(Ljava/lang/String;)Landroid/app/Fragment;

    move-result-object v1

    iput-object v1, v0, Lcom/miui/internal/app/n$a;->Iz:Landroid/app/Fragment;

    .line 351
    iget-object v1, v0, Lcom/miui/internal/app/n$a;->Iz:Landroid/app/Fragment;

    if-nez v1, :cond_0

    if-eqz p2, :cond_0

    .line 352
    iget-object v1, p0, Lcom/miui/internal/app/n;->mContext:Landroid/content/Context;

    iget-object v2, v0, Lcom/miui/internal/app/n$a;->Iy:Ljava/lang/Class;

    invoke-virtual {v2}, Ljava/lang/Class;->getName()Ljava/lang/String;

    move-result-object v2

    iget-object v3, v0, Lcom/miui/internal/app/n$a;->IA:Landroid/os/Bundle;

    invoke-static {v1, v2, v3}, Landroid/app/Fragment;->instantiate(Landroid/content/Context;Ljava/lang/String;Landroid/os/Bundle;)Landroid/app/Fragment;

    move-result-object v1

    iput-object v1, v0, Lcom/miui/internal/app/n$a;->Iz:Landroid/app/Fragment;

    .line 353
    iput-object v4, v0, Lcom/miui/internal/app/n$a;->Iy:Ljava/lang/Class;

    .line 354
    iput-object v4, v0, Lcom/miui/internal/app/n$a;->IA:Landroid/os/Bundle;

    .line 357
    :cond_0
    iget-object v0, v0, Lcom/miui/internal/app/n$a;->Iz:Landroid/app/Fragment;

    return-object v0
.end method

.method public destroyItem(Landroid/view/ViewGroup;ILjava/lang/Object;)V
    .locals 1

    .prologue
    .line 266
    iget-object v0, p0, Lcom/miui/internal/app/n;->yK:Landroid/app/FragmentTransaction;

    if-nez v0, :cond_0

    .line 267
    iget-object v0, p0, Lcom/miui/internal/app/n;->kO:Landroid/app/FragmentManager;

    invoke-virtual {v0}, Landroid/app/FragmentManager;->beginTransaction()Landroid/app/FragmentTransaction;

    move-result-object v0

    iput-object v0, p0, Lcom/miui/internal/app/n;->yK:Landroid/app/FragmentTransaction;

    .line 269
    :cond_0
    iget-object v0, p0, Lcom/miui/internal/app/n;->yK:Landroid/app/FragmentTransaction;

    check-cast p3, Landroid/app/Fragment;

    invoke-virtual {v0, p3}, Landroid/app/FragmentTransaction;->detach(Landroid/app/Fragment;)Landroid/app/FragmentTransaction;

    .line 270
    return-void
.end method

.method public finishUpdate(Landroid/view/ViewGroup;)V
    .locals 1

    .prologue
    .line 290
    iget-object v0, p0, Lcom/miui/internal/app/n;->yK:Landroid/app/FragmentTransaction;

    if-eqz v0, :cond_0

    .line 291
    iget-object v0, p0, Lcom/miui/internal/app/n;->yK:Landroid/app/FragmentTransaction;

    invoke-virtual {v0}, Landroid/app/FragmentTransaction;->commitAllowingStateLoss()I

    .line 292
    const/4 v0, 0x0

    iput-object v0, p0, Lcom/miui/internal/app/n;->yK:Landroid/app/FragmentTransaction;

    .line 293
    iget-object v0, p0, Lcom/miui/internal/app/n;->kO:Landroid/app/FragmentManager;

    invoke-virtual {v0}, Landroid/app/FragmentManager;->executePendingTransactions()Z

    .line 295
    :cond_0
    return-void
.end method

.method public getCount()I
    .locals 1

    .prologue
    .line 324
    iget-object v0, p0, Lcom/miui/internal/app/n;->yJ:Ljava/util/ArrayList;

    invoke-virtual {v0}, Ljava/util/ArrayList;->size()I

    move-result v0

    return v0
.end method

.method public getItemPosition(Ljava/lang/Object;)I
    .locals 3

    .prologue
    .line 333
    iget-object v0, p0, Lcom/miui/internal/app/n;->yJ:Ljava/util/ArrayList;

    invoke-virtual {v0}, Ljava/util/ArrayList;->size()I

    move-result v2

    .line 334
    const/4 v1, 0x0

    :goto_0
    if-ge v1, v2, :cond_1

    .line 335
    iget-object v0, p0, Lcom/miui/internal/app/n;->yJ:Ljava/util/ArrayList;

    invoke-virtual {v0, v1}, Ljava/util/ArrayList;->get(I)Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Lcom/miui/internal/app/n$a;

    iget-object v0, v0, Lcom/miui/internal/app/n$a;->Iz:Landroid/app/Fragment;

    if-ne p1, v0, :cond_0

    move v0, v1

    .line 339
    :goto_1
    return v0

    .line 334
    :cond_0
    add-int/lit8 v1, v1, 0x1

    goto :goto_0

    .line 339
    :cond_1
    const/4 v0, -0x2

    goto :goto_1
.end method

.method getTabAt(I)Landroid/app/ActionBar$Tab;
    .locals 1

    .prologue
    .line 343
    iget-object v0, p0, Lcom/miui/internal/app/n;->yJ:Ljava/util/ArrayList;

    invoke-virtual {v0, p1}, Ljava/util/ArrayList;->get(I)Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Lcom/miui/internal/app/n$a;

    iget-object v0, v0, Lcom/miui/internal/app/n$a;->IB:Landroid/app/ActionBar$Tab;

    return-object v0
.end method

.method public hasActionMenu(I)Z
    .locals 1

    .prologue
    .line 328
    iget-object v0, p0, Lcom/miui/internal/app/n;->yJ:Ljava/util/ArrayList;

    invoke-virtual {v0, p1}, Ljava/util/ArrayList;->get(I)Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Lcom/miui/internal/app/n$a;

    iget-boolean v0, v0, Lcom/miui/internal/app/n$a;->lh:Z

    return v0
.end method

.method public instantiateItem(Landroid/view/ViewGroup;I)Ljava/lang/Object;
    .locals 5

    .prologue
    const/4 v4, 0x0

    .line 304
    iget-object v0, p0, Lcom/miui/internal/app/n;->yK:Landroid/app/FragmentTransaction;

    if-nez v0, :cond_0

    .line 305
    iget-object v0, p0, Lcom/miui/internal/app/n;->kO:Landroid/app/FragmentManager;

    invoke-virtual {v0}, Landroid/app/FragmentManager;->beginTransaction()Landroid/app/FragmentTransaction;

    move-result-object v0

    iput-object v0, p0, Lcom/miui/internal/app/n;->yK:Landroid/app/FragmentTransaction;

    .line 308
    :cond_0
    const/4 v0, 0x1

    invoke-virtual {p0, p2, v0}, Lcom/miui/internal/app/n;->d(IZ)Landroid/app/Fragment;

    move-result-object v1

    .line 309
    invoke-virtual {v1}, Landroid/app/Fragment;->getFragmentManager()Landroid/app/FragmentManager;

    move-result-object v0

    if-eqz v0, :cond_2

    .line 310
    iget-object v0, p0, Lcom/miui/internal/app/n;->yK:Landroid/app/FragmentTransaction;

    invoke-virtual {v0, v1}, Landroid/app/FragmentTransaction;->attach(Landroid/app/Fragment;)Landroid/app/FragmentTransaction;

    .line 314
    :goto_0
    iget-object v0, p0, Lcom/miui/internal/app/n;->yL:Landroid/app/Fragment;

    if-eq v1, v0, :cond_1

    .line 315
    invoke-virtual {v1, v4}, Landroid/app/Fragment;->setMenuVisibility(Z)V

    .line 316
    invoke-virtual {v1, v4}, Landroid/app/Fragment;->setUserVisibleHint(Z)V

    .line 319
    :cond_1
    return-object v1

    .line 312
    :cond_2
    iget-object v2, p0, Lcom/miui/internal/app/n;->yK:Landroid/app/FragmentTransaction;

    invoke-virtual {p1}, Landroid/view/ViewGroup;->getId()I

    move-result v3

    iget-object v0, p0, Lcom/miui/internal/app/n;->yJ:Ljava/util/ArrayList;

    invoke-virtual {v0, p2}, Ljava/util/ArrayList;->get(I)Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Lcom/miui/internal/app/n$a;

    iget-object v0, v0, Lcom/miui/internal/app/n$a;->tag:Ljava/lang/String;

    invoke-virtual {v2, v3, v1, v0}, Landroid/app/FragmentTransaction;->add(ILandroid/app/Fragment;Ljava/lang/String;)Landroid/app/FragmentTransaction;

    goto :goto_0
.end method

.method public isViewFromObject(Landroid/view/View;Ljava/lang/Object;)Z
    .locals 1

    .prologue
    .line 299
    check-cast p2, Landroid/app/Fragment;

    invoke-virtual {p2}, Landroid/app/Fragment;->getView()Landroid/view/View;

    move-result-object v0

    if-ne v0, p1, :cond_0

    const/4 v0, 0x1

    :goto_0
    return v0

    :cond_0
    const/4 v0, 0x0

    goto :goto_0
.end method

.method setFragmentActionMenuAt(IZ)V
    .locals 2

    .prologue
    .line 423
    iget-object v0, p0, Lcom/miui/internal/app/n;->yJ:Ljava/util/ArrayList;

    invoke-virtual {v0, p1}, Ljava/util/ArrayList;->get(I)Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Lcom/miui/internal/app/n$a;

    .line 424
    iget-boolean v1, v0, Lcom/miui/internal/app/n$a;->lh:Z

    if-eq v1, p2, :cond_0

    .line 425
    iput-boolean p2, v0, Lcom/miui/internal/app/n$a;->lh:Z

    .line 426
    invoke-virtual {p0}, Lcom/miui/internal/app/n;->notifyDataSetChanged()V

    .line 428
    :cond_0
    return-void
.end method

.method public setPrimaryItem(Landroid/view/ViewGroup;ILjava/lang/Object;)V
    .locals 3

    .prologue
    const/4 v2, 0x1

    const/4 v1, 0x0

    .line 274
    check-cast p3, Landroid/app/Fragment;

    .line 275
    iget-object v0, p0, Lcom/miui/internal/app/n;->yL:Landroid/app/Fragment;

    if-eq p3, v0, :cond_2

    .line 276
    iget-object v0, p0, Lcom/miui/internal/app/n;->yL:Landroid/app/Fragment;

    if-eqz v0, :cond_0

    .line 277
    iget-object v0, p0, Lcom/miui/internal/app/n;->yL:Landroid/app/Fragment;

    invoke-virtual {v0, v1}, Landroid/app/Fragment;->setMenuVisibility(Z)V

    .line 278
    iget-object v0, p0, Lcom/miui/internal/app/n;->yL:Landroid/app/Fragment;

    invoke-virtual {v0, v1}, Landroid/app/Fragment;->setUserVisibleHint(Z)V

    .line 280
    :cond_0
    if-eqz p3, :cond_1

    .line 281
    invoke-virtual {p3, v2}, Landroid/app/Fragment;->setMenuVisibility(Z)V

    .line 282
    invoke-virtual {p3, v2}, Landroid/app/Fragment;->setUserVisibleHint(Z)V

    .line 284
    :cond_1
    iput-object p3, p0, Lcom/miui/internal/app/n;->yL:Landroid/app/Fragment;

    .line 286
    :cond_2
    return-void
.end method

.method public startUpdate(Landroid/view/ViewGroup;)V
    .locals 0

    .prologue
    .line 262
    return-void
.end method

.method w(Ljava/lang/String;)I
    .locals 3

    .prologue
    .line 379
    iget-object v0, p0, Lcom/miui/internal/app/n;->yJ:Ljava/util/ArrayList;

    invoke-virtual {v0}, Ljava/util/ArrayList;->size()I

    move-result v2

    .line 380
    const/4 v1, 0x0

    :goto_0
    if-ge v1, v2, :cond_1

    .line 381
    iget-object v0, p0, Lcom/miui/internal/app/n;->yJ:Ljava/util/ArrayList;

    invoke-virtual {v0, v1}, Ljava/util/ArrayList;->get(I)Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Lcom/miui/internal/app/n$a;

    .line 382
    iget-object v0, v0, Lcom/miui/internal/app/n$a;->tag:Ljava/lang/String;

    invoke-virtual {v0, p1}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v0

    if-eqz v0, :cond_0

    move v0, v1

    .line 386
    :goto_1
    return v0

    .line 380
    :cond_0
    add-int/lit8 v1, v1, 0x1

    goto :goto_0

    .line 386
    :cond_1
    const/4 v0, -0x1

    goto :goto_1
.end method
