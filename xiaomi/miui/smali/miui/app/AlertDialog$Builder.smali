.class public Lmiui/app/AlertDialog$Builder;
.super Ljava/lang/Object;
.source "SourceFile"


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lmiui/app/AlertDialog;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x9
    name = "Builder"
.end annotation


# instance fields
.field private kP:I

.field private pj:Lcom/miui/internal/variable/AlertControllerWrapper$AlertParams;


# direct methods
.method public constructor <init>(Landroid/content/Context;)V
    .locals 1

    .prologue
    .line 279
    const/4 v0, 0x0

    invoke-static {p1, v0}, Lmiui/app/AlertDialog;->a(Landroid/content/Context;I)I

    move-result v0

    invoke-direct {p0, p1, v0}, Lmiui/app/AlertDialog$Builder;-><init>(Landroid/content/Context;I)V

    .line 280
    return-void
.end method

.method public constructor <init>(Landroid/content/Context;I)V
    .locals 3

    .prologue
    .line 293
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    .line 294
    new-instance v0, Lcom/miui/internal/variable/AlertControllerWrapper$AlertParams;

    new-instance v1, Landroid/view/ContextThemeWrapper;

    invoke-static {p1, p2}, Lmiui/app/AlertDialog;->a(Landroid/content/Context;I)I

    move-result v2

    invoke-direct {v1, p1, v2}, Landroid/view/ContextThemeWrapper;-><init>(Landroid/content/Context;I)V

    invoke-direct {v0, v1}, Lcom/miui/internal/variable/AlertControllerWrapper$AlertParams;-><init>(Landroid/content/Context;)V

    iput-object v0, p0, Lmiui/app/AlertDialog$Builder;->pj:Lcom/miui/internal/variable/AlertControllerWrapper$AlertParams;

    .line 295
    iget-object v1, p0, Lmiui/app/AlertDialog$Builder;->pj:Lcom/miui/internal/variable/AlertControllerWrapper$AlertParams;

    const/4 v0, 0x4

    if-lt p2, v0, :cond_0

    const/4 v0, 0x1

    :goto_0
    iput-boolean v0, v1, Lcom/miui/internal/variable/AlertControllerWrapper$AlertParams;->mEditMode:Z

    .line 296
    iput p2, p0, Lmiui/app/AlertDialog$Builder;->kP:I

    .line 297
    return-void

    .line 295
    :cond_0
    const/4 v0, 0x0

    goto :goto_0
.end method


# virtual methods
.method public addActionItem(III)Lmiui/app/AlertDialog$Builder;
    .locals 1

    .prologue
    .line 534
    iget-object v0, p0, Lmiui/app/AlertDialog$Builder;->pj:Lcom/miui/internal/variable/AlertControllerWrapper$AlertParams;

    iget-object v0, v0, Lcom/miui/internal/variable/AlertControllerWrapper$AlertParams;->mContext:Landroid/content/Context;

    invoke-virtual {v0, p1}, Landroid/content/Context;->getText(I)Ljava/lang/CharSequence;

    move-result-object v0

    invoke-virtual {p0, v0, p2, p3}, Lmiui/app/AlertDialog$Builder;->addActionItem(Ljava/lang/CharSequence;II)Lmiui/app/AlertDialog$Builder;

    move-result-object v0

    return-object v0
.end method

.method public addActionItem(Ljava/lang/CharSequence;II)Lmiui/app/AlertDialog$Builder;
    .locals 2

    .prologue
    .line 518
    iget-object v0, p0, Lmiui/app/AlertDialog$Builder;->pj:Lcom/miui/internal/variable/AlertControllerWrapper$AlertParams;

    iget-object v0, v0, Lcom/miui/internal/variable/AlertControllerWrapper$AlertParams;->mActionItems:Ljava/util/ArrayList;

    if-nez v0, :cond_0

    .line 519
    iget-object v0, p0, Lmiui/app/AlertDialog$Builder;->pj:Lcom/miui/internal/variable/AlertControllerWrapper$AlertParams;

    new-instance v1, Ljava/util/ArrayList;

    invoke-direct {v1}, Ljava/util/ArrayList;-><init>()V

    iput-object v1, v0, Lcom/miui/internal/variable/AlertControllerWrapper$AlertParams;->mActionItems:Ljava/util/ArrayList;

    .line 522
    :cond_0
    iget-object v0, p0, Lmiui/app/AlertDialog$Builder;->pj:Lcom/miui/internal/variable/AlertControllerWrapper$AlertParams;

    iget-object v0, v0, Lcom/miui/internal/variable/AlertControllerWrapper$AlertParams;->mActionItems:Ljava/util/ArrayList;

    new-instance v1, Lcom/miui/internal/variable/AlertControllerWrapper$AlertParams$ActionItem;

    invoke-direct {v1, p1, p2, p3}, Lcom/miui/internal/variable/AlertControllerWrapper$AlertParams$ActionItem;-><init>(Ljava/lang/CharSequence;II)V

    invoke-virtual {v0, v1}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    .line 523
    return-object p0
.end method

.method public create()Lmiui/app/AlertDialog;
    .locals 3

    .prologue
    .line 848
    new-instance v0, Lmiui/app/AlertDialog;

    iget-object v1, p0, Lmiui/app/AlertDialog$Builder;->pj:Lcom/miui/internal/variable/AlertControllerWrapper$AlertParams;

    iget-object v1, v1, Lcom/miui/internal/variable/AlertControllerWrapper$AlertParams;->mContext:Landroid/content/Context;

    iget v2, p0, Lmiui/app/AlertDialog$Builder;->kP:I

    invoke-direct {v0, v1, v2}, Lmiui/app/AlertDialog;-><init>(Landroid/content/Context;I)V

    .line 849
    iget-object v1, p0, Lmiui/app/AlertDialog$Builder;->pj:Lcom/miui/internal/variable/AlertControllerWrapper$AlertParams;

    invoke-static {v0}, Lmiui/app/AlertDialog;->a(Lmiui/app/AlertDialog;)Lcom/miui/internal/variable/AlertControllerWrapper;

    move-result-object v2

    invoke-virtual {v1, v2}, Lcom/miui/internal/variable/AlertControllerWrapper$AlertParams;->apply(Lcom/android/internal/app/AlertController;)V

    .line 850
    iget-object v1, p0, Lmiui/app/AlertDialog$Builder;->pj:Lcom/miui/internal/variable/AlertControllerWrapper$AlertParams;

    iget-boolean v1, v1, Lcom/miui/internal/variable/AlertControllerWrapper$AlertParams;->mCancelable:Z

    invoke-virtual {v0, v1}, Lmiui/app/AlertDialog;->setCancelable(Z)V

    .line 851
    iget-object v1, p0, Lmiui/app/AlertDialog$Builder;->pj:Lcom/miui/internal/variable/AlertControllerWrapper$AlertParams;

    iget-boolean v1, v1, Lcom/miui/internal/variable/AlertControllerWrapper$AlertParams;->mCancelable:Z

    if-eqz v1, :cond_0

    .line 852
    const/4 v1, 0x1

    invoke-virtual {v0, v1}, Lmiui/app/AlertDialog;->setCanceledOnTouchOutside(Z)V

    .line 854
    :cond_0
    iget-object v1, p0, Lmiui/app/AlertDialog$Builder;->pj:Lcom/miui/internal/variable/AlertControllerWrapper$AlertParams;

    iget-object v1, v1, Lcom/miui/internal/variable/AlertControllerWrapper$AlertParams;->mOnCancelListener:Landroid/content/DialogInterface$OnCancelListener;

    invoke-virtual {v0, v1}, Lmiui/app/AlertDialog;->setOnCancelListener(Landroid/content/DialogInterface$OnCancelListener;)V

    .line 855
    iget-object v1, p0, Lmiui/app/AlertDialog$Builder;->pj:Lcom/miui/internal/variable/AlertControllerWrapper$AlertParams;

    iget-object v1, v1, Lcom/miui/internal/variable/AlertControllerWrapper$AlertParams;->mOnDismissListener:Landroid/content/DialogInterface$OnDismissListener;

    invoke-virtual {v0, v1}, Lmiui/app/AlertDialog;->setOnDismissListener(Landroid/content/DialogInterface$OnDismissListener;)V

    .line 856
    iget-object v1, p0, Lmiui/app/AlertDialog$Builder;->pj:Lcom/miui/internal/variable/AlertControllerWrapper$AlertParams;

    iget-object v1, v1, Lcom/miui/internal/variable/AlertControllerWrapper$AlertParams;->mOnShowListener:Landroid/content/DialogInterface$OnShowListener;

    invoke-virtual {v0, v1}, Lmiui/app/AlertDialog;->setOnShowListener(Landroid/content/DialogInterface$OnShowListener;)V

    .line 857
    iget-object v1, p0, Lmiui/app/AlertDialog$Builder;->pj:Lcom/miui/internal/variable/AlertControllerWrapper$AlertParams;

    iget-object v1, v1, Lcom/miui/internal/variable/AlertControllerWrapper$AlertParams;->mOnKeyListener:Landroid/content/DialogInterface$OnKeyListener;

    if-eqz v1, :cond_1

    .line 858
    iget-object v1, p0, Lmiui/app/AlertDialog$Builder;->pj:Lcom/miui/internal/variable/AlertControllerWrapper$AlertParams;

    iget-object v1, v1, Lcom/miui/internal/variable/AlertControllerWrapper$AlertParams;->mOnKeyListener:Landroid/content/DialogInterface$OnKeyListener;

    invoke-virtual {v0, v1}, Lmiui/app/AlertDialog;->setOnKeyListener(Landroid/content/DialogInterface$OnKeyListener;)V

    .line 860
    :cond_1
    return-object v0
.end method

.method public getContext()Landroid/content/Context;
    .locals 1

    .prologue
    .line 308
    iget-object v0, p0, Lmiui/app/AlertDialog$Builder;->pj:Lcom/miui/internal/variable/AlertControllerWrapper$AlertParams;

    iget-object v0, v0, Lcom/miui/internal/variable/AlertControllerWrapper$AlertParams;->mContext:Landroid/content/Context;

    return-object v0
.end method

.method public setAdapter(Landroid/widget/ListAdapter;Landroid/content/DialogInterface$OnClickListener;)Lmiui/app/AlertDialog$Builder;
    .locals 1

    .prologue
    .line 583
    iget-object v0, p0, Lmiui/app/AlertDialog$Builder;->pj:Lcom/miui/internal/variable/AlertControllerWrapper$AlertParams;

    iput-object p1, v0, Lcom/miui/internal/variable/AlertControllerWrapper$AlertParams;->mAdapter:Landroid/widget/ListAdapter;

    .line 584
    iget-object v0, p0, Lmiui/app/AlertDialog$Builder;->pj:Lcom/miui/internal/variable/AlertControllerWrapper$AlertParams;

    iput-object p2, v0, Lcom/miui/internal/variable/AlertControllerWrapper$AlertParams;->mOnClickListener:Landroid/content/DialogInterface$OnClickListener;

    .line 585
    return-object p0
.end method

.method public setCancelable(Z)Lmiui/app/AlertDialog$Builder;
    .locals 1

    .prologue
    .line 461
    iget-object v0, p0, Lmiui/app/AlertDialog$Builder;->pj:Lcom/miui/internal/variable/AlertControllerWrapper$AlertParams;

    iput-boolean p1, v0, Lcom/miui/internal/variable/AlertControllerWrapper$AlertParams;->mCancelable:Z

    .line 462
    return-object p0
.end method

.method public setCheckBox(ZLjava/lang/CharSequence;)Lmiui/app/AlertDialog$Builder;
    .locals 1

    .prologue
    .line 372
    iget-object v0, p0, Lmiui/app/AlertDialog$Builder;->pj:Lcom/miui/internal/variable/AlertControllerWrapper$AlertParams;

    iput-boolean p1, v0, Lcom/miui/internal/variable/AlertControllerWrapper$AlertParams;->mIsChecked:Z

    .line 373
    iget-object v0, p0, Lmiui/app/AlertDialog$Builder;->pj:Lcom/miui/internal/variable/AlertControllerWrapper$AlertParams;

    iput-object p2, v0, Lcom/miui/internal/variable/AlertControllerWrapper$AlertParams;->mCheckBoxMessage:Ljava/lang/CharSequence;

    .line 374
    return-object p0
.end method

.method public setCursor(Landroid/database/Cursor;Landroid/content/DialogInterface$OnClickListener;Ljava/lang/String;)Lmiui/app/AlertDialog$Builder;
    .locals 1

    .prologue
    .line 601
    iget-object v0, p0, Lmiui/app/AlertDialog$Builder;->pj:Lcom/miui/internal/variable/AlertControllerWrapper$AlertParams;

    iput-object p1, v0, Lcom/miui/internal/variable/AlertControllerWrapper$AlertParams;->mCursor:Landroid/database/Cursor;

    .line 602
    iget-object v0, p0, Lmiui/app/AlertDialog$Builder;->pj:Lcom/miui/internal/variable/AlertControllerWrapper$AlertParams;

    iput-object p3, v0, Lcom/miui/internal/variable/AlertControllerWrapper$AlertParams;->mLabelColumn:Ljava/lang/String;

    .line 603
    iget-object v0, p0, Lmiui/app/AlertDialog$Builder;->pj:Lcom/miui/internal/variable/AlertControllerWrapper$AlertParams;

    iput-object p2, v0, Lcom/miui/internal/variable/AlertControllerWrapper$AlertParams;->mOnClickListener:Landroid/content/DialogInterface$OnClickListener;

    .line 604
    return-object p0
.end method

.method public setCustomTitle(Landroid/view/View;)Lmiui/app/AlertDialog$Builder;
    .locals 1

    .prologue
    .line 342
    iget-object v0, p0, Lmiui/app/AlertDialog$Builder;->pj:Lcom/miui/internal/variable/AlertControllerWrapper$AlertParams;

    iput-object p1, v0, Lcom/miui/internal/variable/AlertControllerWrapper$AlertParams;->mCustomTitleView:Landroid/view/View;

    .line 343
    return-object p0
.end method

.method public setItems(ILandroid/content/DialogInterface$OnClickListener;)Lmiui/app/AlertDialog$Builder;
    .locals 2

    .prologue
    .line 555
    iget-object v0, p0, Lmiui/app/AlertDialog$Builder;->pj:Lcom/miui/internal/variable/AlertControllerWrapper$AlertParams;

    iget-object v1, p0, Lmiui/app/AlertDialog$Builder;->pj:Lcom/miui/internal/variable/AlertControllerWrapper$AlertParams;

    iget-object v1, v1, Lcom/miui/internal/variable/AlertControllerWrapper$AlertParams;->mContext:Landroid/content/Context;

    invoke-virtual {v1}, Landroid/content/Context;->getResources()Landroid/content/res/Resources;

    move-result-object v1

    invoke-virtual {v1, p1}, Landroid/content/res/Resources;->getTextArray(I)[Ljava/lang/CharSequence;

    move-result-object v1

    iput-object v1, v0, Lcom/miui/internal/variable/AlertControllerWrapper$AlertParams;->mItems:[Ljava/lang/CharSequence;

    .line 556
    iget-object v0, p0, Lmiui/app/AlertDialog$Builder;->pj:Lcom/miui/internal/variable/AlertControllerWrapper$AlertParams;

    iput-object p2, v0, Lcom/miui/internal/variable/AlertControllerWrapper$AlertParams;->mOnClickListener:Landroid/content/DialogInterface$OnClickListener;

    .line 557
    return-object p0
.end method

.method public setItems([Ljava/lang/CharSequence;Landroid/content/DialogInterface$OnClickListener;)Lmiui/app/AlertDialog$Builder;
    .locals 1

    .prologue
    .line 567
    iget-object v0, p0, Lmiui/app/AlertDialog$Builder;->pj:Lcom/miui/internal/variable/AlertControllerWrapper$AlertParams;

    iput-object p1, v0, Lcom/miui/internal/variable/AlertControllerWrapper$AlertParams;->mItems:[Ljava/lang/CharSequence;

    .line 568
    iget-object v0, p0, Lmiui/app/AlertDialog$Builder;->pj:Lcom/miui/internal/variable/AlertControllerWrapper$AlertParams;

    iput-object p2, v0, Lcom/miui/internal/variable/AlertControllerWrapper$AlertParams;->mOnClickListener:Landroid/content/DialogInterface$OnClickListener;

    .line 569
    return-object p0
.end method

.method public setMessage(I)Lmiui/app/AlertDialog$Builder;
    .locals 2

    .prologue
    .line 352
    iget-object v0, p0, Lmiui/app/AlertDialog$Builder;->pj:Lcom/miui/internal/variable/AlertControllerWrapper$AlertParams;

    iget-object v1, p0, Lmiui/app/AlertDialog$Builder;->pj:Lcom/miui/internal/variable/AlertControllerWrapper$AlertParams;

    iget-object v1, v1, Lcom/miui/internal/variable/AlertControllerWrapper$AlertParams;->mContext:Landroid/content/Context;

    invoke-virtual {v1, p1}, Landroid/content/Context;->getText(I)Ljava/lang/CharSequence;

    move-result-object v1

    iput-object v1, v0, Lcom/miui/internal/variable/AlertControllerWrapper$AlertParams;->mMessage:Ljava/lang/CharSequence;

    .line 353
    return-object p0
.end method

.method public setMessage(Ljava/lang/CharSequence;)Lmiui/app/AlertDialog$Builder;
    .locals 1

    .prologue
    .line 362
    iget-object v0, p0, Lmiui/app/AlertDialog$Builder;->pj:Lcom/miui/internal/variable/AlertControllerWrapper$AlertParams;

    iput-object p1, v0, Lcom/miui/internal/variable/AlertControllerWrapper$AlertParams;->mMessage:Ljava/lang/CharSequence;

    .line 363
    return-object p0
.end method

.method public setMultiChoiceAdapter(Landroid/widget/ListAdapter;Landroid/content/DialogInterface$OnMultiChoiceClickListener;)Lmiui/app/AlertDialog$Builder;
    .locals 2

    .prologue
    .line 705
    iget-object v0, p0, Lmiui/app/AlertDialog$Builder;->pj:Lcom/miui/internal/variable/AlertControllerWrapper$AlertParams;

    iput-object p1, v0, Lcom/miui/internal/variable/AlertControllerWrapper$AlertParams;->mAdapter:Landroid/widget/ListAdapter;

    .line 706
    iget-object v0, p0, Lmiui/app/AlertDialog$Builder;->pj:Lcom/miui/internal/variable/AlertControllerWrapper$AlertParams;

    const/4 v1, 0x1

    iput-boolean v1, v0, Lcom/miui/internal/variable/AlertControllerWrapper$AlertParams;->mIsMultiChoice:Z

    .line 707
    iget-object v0, p0, Lmiui/app/AlertDialog$Builder;->pj:Lcom/miui/internal/variable/AlertControllerWrapper$AlertParams;

    iput-object p2, v0, Lcom/miui/internal/variable/AlertControllerWrapper$AlertParams;->mOnCheckboxClickListener:Landroid/content/DialogInterface$OnMultiChoiceClickListener;

    .line 708
    return-object p0
.end method

.method public setMultiChoiceItems(I[ZLandroid/content/DialogInterface$OnMultiChoiceClickListener;)Lmiui/app/AlertDialog$Builder;
    .locals 2

    .prologue
    .line 627
    iget-object v0, p0, Lmiui/app/AlertDialog$Builder;->pj:Lcom/miui/internal/variable/AlertControllerWrapper$AlertParams;

    iget-object v1, p0, Lmiui/app/AlertDialog$Builder;->pj:Lcom/miui/internal/variable/AlertControllerWrapper$AlertParams;

    iget-object v1, v1, Lcom/miui/internal/variable/AlertControllerWrapper$AlertParams;->mContext:Landroid/content/Context;

    invoke-virtual {v1}, Landroid/content/Context;->getResources()Landroid/content/res/Resources;

    move-result-object v1

    invoke-virtual {v1, p1}, Landroid/content/res/Resources;->getTextArray(I)[Ljava/lang/CharSequence;

    move-result-object v1

    iput-object v1, v0, Lcom/miui/internal/variable/AlertControllerWrapper$AlertParams;->mItems:[Ljava/lang/CharSequence;

    .line 628
    iget-object v0, p0, Lmiui/app/AlertDialog$Builder;->pj:Lcom/miui/internal/variable/AlertControllerWrapper$AlertParams;

    iput-object p3, v0, Lcom/miui/internal/variable/AlertControllerWrapper$AlertParams;->mOnCheckboxClickListener:Landroid/content/DialogInterface$OnMultiChoiceClickListener;

    .line 629
    iget-object v0, p0, Lmiui/app/AlertDialog$Builder;->pj:Lcom/miui/internal/variable/AlertControllerWrapper$AlertParams;

    iput-object p2, v0, Lcom/miui/internal/variable/AlertControllerWrapper$AlertParams;->mCheckedItems:[Z

    .line 630
    iget-object v0, p0, Lmiui/app/AlertDialog$Builder;->pj:Lcom/miui/internal/variable/AlertControllerWrapper$AlertParams;

    const/4 v1, 0x1

    iput-boolean v1, v0, Lcom/miui/internal/variable/AlertControllerWrapper$AlertParams;->mIsMultiChoice:Z

    .line 631
    return-object p0
.end method

.method public setMultiChoiceItems(Landroid/database/Cursor;Ljava/lang/String;Ljava/lang/String;Landroid/content/DialogInterface$OnMultiChoiceClickListener;)Lmiui/app/AlertDialog$Builder;
    .locals 2

    .prologue
    .line 681
    iget-object v0, p0, Lmiui/app/AlertDialog$Builder;->pj:Lcom/miui/internal/variable/AlertControllerWrapper$AlertParams;

    iput-object p1, v0, Lcom/miui/internal/variable/AlertControllerWrapper$AlertParams;->mCursor:Landroid/database/Cursor;

    .line 682
    iget-object v0, p0, Lmiui/app/AlertDialog$Builder;->pj:Lcom/miui/internal/variable/AlertControllerWrapper$AlertParams;

    iput-object p4, v0, Lcom/miui/internal/variable/AlertControllerWrapper$AlertParams;->mOnCheckboxClickListener:Landroid/content/DialogInterface$OnMultiChoiceClickListener;

    .line 683
    iget-object v0, p0, Lmiui/app/AlertDialog$Builder;->pj:Lcom/miui/internal/variable/AlertControllerWrapper$AlertParams;

    iput-object p2, v0, Lcom/miui/internal/variable/AlertControllerWrapper$AlertParams;->mIsCheckedColumn:Ljava/lang/String;

    .line 684
    iget-object v0, p0, Lmiui/app/AlertDialog$Builder;->pj:Lcom/miui/internal/variable/AlertControllerWrapper$AlertParams;

    iput-object p3, v0, Lcom/miui/internal/variable/AlertControllerWrapper$AlertParams;->mLabelColumn:Ljava/lang/String;

    .line 685
    iget-object v0, p0, Lmiui/app/AlertDialog$Builder;->pj:Lcom/miui/internal/variable/AlertControllerWrapper$AlertParams;

    const/4 v1, 0x1

    iput-boolean v1, v0, Lcom/miui/internal/variable/AlertControllerWrapper$AlertParams;->mIsMultiChoice:Z

    .line 686
    return-object p0
.end method

.method public setMultiChoiceItems([Ljava/lang/CharSequence;[ZLandroid/content/DialogInterface$OnMultiChoiceClickListener;)Lmiui/app/AlertDialog$Builder;
    .locals 2

    .prologue
    .line 653
    iget-object v0, p0, Lmiui/app/AlertDialog$Builder;->pj:Lcom/miui/internal/variable/AlertControllerWrapper$AlertParams;

    iput-object p1, v0, Lcom/miui/internal/variable/AlertControllerWrapper$AlertParams;->mItems:[Ljava/lang/CharSequence;

    .line 654
    iget-object v0, p0, Lmiui/app/AlertDialog$Builder;->pj:Lcom/miui/internal/variable/AlertControllerWrapper$AlertParams;

    iput-object p3, v0, Lcom/miui/internal/variable/AlertControllerWrapper$AlertParams;->mOnCheckboxClickListener:Landroid/content/DialogInterface$OnMultiChoiceClickListener;

    .line 655
    iget-object v0, p0, Lmiui/app/AlertDialog$Builder;->pj:Lcom/miui/internal/variable/AlertControllerWrapper$AlertParams;

    iput-object p2, v0, Lcom/miui/internal/variable/AlertControllerWrapper$AlertParams;->mCheckedItems:[Z

    .line 656
    iget-object v0, p0, Lmiui/app/AlertDialog$Builder;->pj:Lcom/miui/internal/variable/AlertControllerWrapper$AlertParams;

    const/4 v1, 0x1

    iput-boolean v1, v0, Lcom/miui/internal/variable/AlertControllerWrapper$AlertParams;->mIsMultiChoice:Z

    .line 657
    return-object p0
.end method

.method public setNegativeButton(ILandroid/content/DialogInterface$OnClickListener;)Lmiui/app/AlertDialog$Builder;
    .locals 2

    .prologue
    .line 411
    iget-object v0, p0, Lmiui/app/AlertDialog$Builder;->pj:Lcom/miui/internal/variable/AlertControllerWrapper$AlertParams;

    iget-object v1, p0, Lmiui/app/AlertDialog$Builder;->pj:Lcom/miui/internal/variable/AlertControllerWrapper$AlertParams;

    iget-object v1, v1, Lcom/miui/internal/variable/AlertControllerWrapper$AlertParams;->mContext:Landroid/content/Context;

    invoke-virtual {v1, p1}, Landroid/content/Context;->getText(I)Ljava/lang/CharSequence;

    move-result-object v1

    iput-object v1, v0, Lcom/miui/internal/variable/AlertControllerWrapper$AlertParams;->mNegativeButtonText:Ljava/lang/CharSequence;

    .line 412
    iget-object v0, p0, Lmiui/app/AlertDialog$Builder;->pj:Lcom/miui/internal/variable/AlertControllerWrapper$AlertParams;

    iput-object p2, v0, Lcom/miui/internal/variable/AlertControllerWrapper$AlertParams;->mNegativeButtonListener:Landroid/content/DialogInterface$OnClickListener;

    .line 413
    return-object p0
.end method

.method public setNegativeButton(Ljava/lang/CharSequence;Landroid/content/DialogInterface$OnClickListener;)Lmiui/app/AlertDialog$Builder;
    .locals 1

    .prologue
    .line 424
    iget-object v0, p0, Lmiui/app/AlertDialog$Builder;->pj:Lcom/miui/internal/variable/AlertControllerWrapper$AlertParams;

    iput-object p1, v0, Lcom/miui/internal/variable/AlertControllerWrapper$AlertParams;->mNegativeButtonText:Ljava/lang/CharSequence;

    .line 425
    iget-object v0, p0, Lmiui/app/AlertDialog$Builder;->pj:Lcom/miui/internal/variable/AlertControllerWrapper$AlertParams;

    iput-object p2, v0, Lcom/miui/internal/variable/AlertControllerWrapper$AlertParams;->mNegativeButtonListener:Landroid/content/DialogInterface$OnClickListener;

    .line 426
    return-object p0
.end method

.method public setNeutralButton(ILandroid/content/DialogInterface$OnClickListener;)Lmiui/app/AlertDialog$Builder;
    .locals 2

    .prologue
    .line 437
    iget-object v0, p0, Lmiui/app/AlertDialog$Builder;->pj:Lcom/miui/internal/variable/AlertControllerWrapper$AlertParams;

    iget-object v1, p0, Lmiui/app/AlertDialog$Builder;->pj:Lcom/miui/internal/variable/AlertControllerWrapper$AlertParams;

    iget-object v1, v1, Lcom/miui/internal/variable/AlertControllerWrapper$AlertParams;->mContext:Landroid/content/Context;

    invoke-virtual {v1, p1}, Landroid/content/Context;->getText(I)Ljava/lang/CharSequence;

    move-result-object v1

    iput-object v1, v0, Lcom/miui/internal/variable/AlertControllerWrapper$AlertParams;->mNeutralButtonText:Ljava/lang/CharSequence;

    .line 438
    iget-object v0, p0, Lmiui/app/AlertDialog$Builder;->pj:Lcom/miui/internal/variable/AlertControllerWrapper$AlertParams;

    iput-object p2, v0, Lcom/miui/internal/variable/AlertControllerWrapper$AlertParams;->mNeutralButtonListener:Landroid/content/DialogInterface$OnClickListener;

    .line 439
    return-object p0
.end method

.method public setNeutralButton(Ljava/lang/CharSequence;Landroid/content/DialogInterface$OnClickListener;)Lmiui/app/AlertDialog$Builder;
    .locals 1

    .prologue
    .line 450
    iget-object v0, p0, Lmiui/app/AlertDialog$Builder;->pj:Lcom/miui/internal/variable/AlertControllerWrapper$AlertParams;

    iput-object p1, v0, Lcom/miui/internal/variable/AlertControllerWrapper$AlertParams;->mNeutralButtonText:Ljava/lang/CharSequence;

    .line 451
    iget-object v0, p0, Lmiui/app/AlertDialog$Builder;->pj:Lcom/miui/internal/variable/AlertControllerWrapper$AlertParams;

    iput-object p2, v0, Lcom/miui/internal/variable/AlertControllerWrapper$AlertParams;->mNeutralButtonListener:Landroid/content/DialogInterface$OnClickListener;

    .line 452
    return-object p0
.end method

.method public setOnActionItemClickListener(Landroid/content/DialogInterface$OnClickListener;)Lmiui/app/AlertDialog$Builder;
    .locals 1

    .prologue
    .line 544
    iget-object v0, p0, Lmiui/app/AlertDialog$Builder;->pj:Lcom/miui/internal/variable/AlertControllerWrapper$AlertParams;

    iput-object p1, v0, Lcom/miui/internal/variable/AlertControllerWrapper$AlertParams;->mOnActionItemClickListener:Landroid/content/DialogInterface$OnClickListener;

    .line 545
    return-object p0
.end method

.method public setOnCancelListener(Landroid/content/DialogInterface$OnCancelListener;)Lmiui/app/AlertDialog$Builder;
    .locals 1

    .prologue
    .line 472
    iget-object v0, p0, Lmiui/app/AlertDialog$Builder;->pj:Lcom/miui/internal/variable/AlertControllerWrapper$AlertParams;

    iput-object p1, v0, Lcom/miui/internal/variable/AlertControllerWrapper$AlertParams;->mOnCancelListener:Landroid/content/DialogInterface$OnCancelListener;

    .line 473
    return-object p0
.end method

.method public setOnDismissListener(Landroid/content/DialogInterface$OnDismissListener;)Lmiui/app/AlertDialog$Builder;
    .locals 1

    .prologue
    .line 483
    iget-object v0, p0, Lmiui/app/AlertDialog$Builder;->pj:Lcom/miui/internal/variable/AlertControllerWrapper$AlertParams;

    iput-object p1, v0, Lcom/miui/internal/variable/AlertControllerWrapper$AlertParams;->mOnDismissListener:Landroid/content/DialogInterface$OnDismissListener;

    .line 484
    return-object p0
.end method

.method public setOnItemSelectedListener(Landroid/widget/AdapterView$OnItemSelectedListener;)Lmiui/app/AlertDialog$Builder;
    .locals 1

    .prologue
    .line 813
    iget-object v0, p0, Lmiui/app/AlertDialog$Builder;->pj:Lcom/miui/internal/variable/AlertControllerWrapper$AlertParams;

    iput-object p1, v0, Lcom/miui/internal/variable/AlertControllerWrapper$AlertParams;->mOnItemSelectedListener:Landroid/widget/AdapterView$OnItemSelectedListener;

    .line 814
    return-object p0
.end method

.method public setOnKeyListener(Landroid/content/DialogInterface$OnKeyListener;)Lmiui/app/AlertDialog$Builder;
    .locals 1

    .prologue
    .line 505
    iget-object v0, p0, Lmiui/app/AlertDialog$Builder;->pj:Lcom/miui/internal/variable/AlertControllerWrapper$AlertParams;

    iput-object p1, v0, Lcom/miui/internal/variable/AlertControllerWrapper$AlertParams;->mOnKeyListener:Landroid/content/DialogInterface$OnKeyListener;

    .line 506
    return-object p0
.end method

.method public setOnShowListener(Landroid/content/DialogInterface$OnShowListener;)Lmiui/app/AlertDialog$Builder;
    .locals 1

    .prologue
    .line 494
    iget-object v0, p0, Lmiui/app/AlertDialog$Builder;->pj:Lcom/miui/internal/variable/AlertControllerWrapper$AlertParams;

    iput-object p1, v0, Lcom/miui/internal/variable/AlertControllerWrapper$AlertParams;->mOnShowListener:Landroid/content/DialogInterface$OnShowListener;

    .line 495
    return-object p0
.end method

.method public setPositiveButton(ILandroid/content/DialogInterface$OnClickListener;)Lmiui/app/AlertDialog$Builder;
    .locals 2

    .prologue
    .line 385
    iget-object v0, p0, Lmiui/app/AlertDialog$Builder;->pj:Lcom/miui/internal/variable/AlertControllerWrapper$AlertParams;

    iget-object v1, p0, Lmiui/app/AlertDialog$Builder;->pj:Lcom/miui/internal/variable/AlertControllerWrapper$AlertParams;

    iget-object v1, v1, Lcom/miui/internal/variable/AlertControllerWrapper$AlertParams;->mContext:Landroid/content/Context;

    invoke-virtual {v1, p1}, Landroid/content/Context;->getText(I)Ljava/lang/CharSequence;

    move-result-object v1

    iput-object v1, v0, Lcom/miui/internal/variable/AlertControllerWrapper$AlertParams;->mPositiveButtonText:Ljava/lang/CharSequence;

    .line 386
    iget-object v0, p0, Lmiui/app/AlertDialog$Builder;->pj:Lcom/miui/internal/variable/AlertControllerWrapper$AlertParams;

    iput-object p2, v0, Lcom/miui/internal/variable/AlertControllerWrapper$AlertParams;->mPositiveButtonListener:Landroid/content/DialogInterface$OnClickListener;

    .line 387
    return-object p0
.end method

.method public setPositiveButton(Ljava/lang/CharSequence;Landroid/content/DialogInterface$OnClickListener;)Lmiui/app/AlertDialog$Builder;
    .locals 1

    .prologue
    .line 398
    iget-object v0, p0, Lmiui/app/AlertDialog$Builder;->pj:Lcom/miui/internal/variable/AlertControllerWrapper$AlertParams;

    iput-object p1, v0, Lcom/miui/internal/variable/AlertControllerWrapper$AlertParams;->mPositiveButtonText:Ljava/lang/CharSequence;

    .line 399
    iget-object v0, p0, Lmiui/app/AlertDialog$Builder;->pj:Lcom/miui/internal/variable/AlertControllerWrapper$AlertParams;

    iput-object p2, v0, Lcom/miui/internal/variable/AlertControllerWrapper$AlertParams;->mPositiveButtonListener:Landroid/content/DialogInterface$OnClickListener;

    .line 400
    return-object p0
.end method

.method public setSingleChoiceItems(IILandroid/content/DialogInterface$OnClickListener;)Lmiui/app/AlertDialog$Builder;
    .locals 2

    .prologue
    .line 727
    iget-object v0, p0, Lmiui/app/AlertDialog$Builder;->pj:Lcom/miui/internal/variable/AlertControllerWrapper$AlertParams;

    iget-object v1, p0, Lmiui/app/AlertDialog$Builder;->pj:Lcom/miui/internal/variable/AlertControllerWrapper$AlertParams;

    iget-object v1, v1, Lcom/miui/internal/variable/AlertControllerWrapper$AlertParams;->mContext:Landroid/content/Context;

    invoke-virtual {v1}, Landroid/content/Context;->getResources()Landroid/content/res/Resources;

    move-result-object v1

    invoke-virtual {v1, p1}, Landroid/content/res/Resources;->getTextArray(I)[Ljava/lang/CharSequence;

    move-result-object v1

    iput-object v1, v0, Lcom/miui/internal/variable/AlertControllerWrapper$AlertParams;->mItems:[Ljava/lang/CharSequence;

    .line 728
    iget-object v0, p0, Lmiui/app/AlertDialog$Builder;->pj:Lcom/miui/internal/variable/AlertControllerWrapper$AlertParams;

    iput-object p3, v0, Lcom/miui/internal/variable/AlertControllerWrapper$AlertParams;->mOnClickListener:Landroid/content/DialogInterface$OnClickListener;

    .line 729
    iget-object v0, p0, Lmiui/app/AlertDialog$Builder;->pj:Lcom/miui/internal/variable/AlertControllerWrapper$AlertParams;

    iput p2, v0, Lcom/miui/internal/variable/AlertControllerWrapper$AlertParams;->mCheckedItem:I

    .line 730
    iget-object v0, p0, Lmiui/app/AlertDialog$Builder;->pj:Lcom/miui/internal/variable/AlertControllerWrapper$AlertParams;

    const/4 v1, 0x1

    iput-boolean v1, v0, Lcom/miui/internal/variable/AlertControllerWrapper$AlertParams;->mIsSingleChoice:Z

    .line 731
    return-object p0
.end method

.method public setSingleChoiceItems(Landroid/database/Cursor;ILjava/lang/String;Landroid/content/DialogInterface$OnClickListener;)Lmiui/app/AlertDialog$Builder;
    .locals 2

    .prologue
    .line 752
    iget-object v0, p0, Lmiui/app/AlertDialog$Builder;->pj:Lcom/miui/internal/variable/AlertControllerWrapper$AlertParams;

    iput-object p1, v0, Lcom/miui/internal/variable/AlertControllerWrapper$AlertParams;->mCursor:Landroid/database/Cursor;

    .line 753
    iget-object v0, p0, Lmiui/app/AlertDialog$Builder;->pj:Lcom/miui/internal/variable/AlertControllerWrapper$AlertParams;

    iput-object p4, v0, Lcom/miui/internal/variable/AlertControllerWrapper$AlertParams;->mOnClickListener:Landroid/content/DialogInterface$OnClickListener;

    .line 754
    iget-object v0, p0, Lmiui/app/AlertDialog$Builder;->pj:Lcom/miui/internal/variable/AlertControllerWrapper$AlertParams;

    iput p2, v0, Lcom/miui/internal/variable/AlertControllerWrapper$AlertParams;->mCheckedItem:I

    .line 755
    iget-object v0, p0, Lmiui/app/AlertDialog$Builder;->pj:Lcom/miui/internal/variable/AlertControllerWrapper$AlertParams;

    iput-object p3, v0, Lcom/miui/internal/variable/AlertControllerWrapper$AlertParams;->mLabelColumn:Ljava/lang/String;

    .line 756
    iget-object v0, p0, Lmiui/app/AlertDialog$Builder;->pj:Lcom/miui/internal/variable/AlertControllerWrapper$AlertParams;

    const/4 v1, 0x1

    iput-boolean v1, v0, Lcom/miui/internal/variable/AlertControllerWrapper$AlertParams;->mIsSingleChoice:Z

    .line 757
    return-object p0
.end method

.method public setSingleChoiceItems(Landroid/widget/ListAdapter;ILandroid/content/DialogInterface$OnClickListener;)Lmiui/app/AlertDialog$Builder;
    .locals 2

    .prologue
    .line 797
    iget-object v0, p0, Lmiui/app/AlertDialog$Builder;->pj:Lcom/miui/internal/variable/AlertControllerWrapper$AlertParams;

    iput-object p1, v0, Lcom/miui/internal/variable/AlertControllerWrapper$AlertParams;->mAdapter:Landroid/widget/ListAdapter;

    .line 798
    iget-object v0, p0, Lmiui/app/AlertDialog$Builder;->pj:Lcom/miui/internal/variable/AlertControllerWrapper$AlertParams;

    iput-object p3, v0, Lcom/miui/internal/variable/AlertControllerWrapper$AlertParams;->mOnClickListener:Landroid/content/DialogInterface$OnClickListener;

    .line 799
    iget-object v0, p0, Lmiui/app/AlertDialog$Builder;->pj:Lcom/miui/internal/variable/AlertControllerWrapper$AlertParams;

    iput p2, v0, Lcom/miui/internal/variable/AlertControllerWrapper$AlertParams;->mCheckedItem:I

    .line 800
    iget-object v0, p0, Lmiui/app/AlertDialog$Builder;->pj:Lcom/miui/internal/variable/AlertControllerWrapper$AlertParams;

    const/4 v1, 0x1

    iput-boolean v1, v0, Lcom/miui/internal/variable/AlertControllerWrapper$AlertParams;->mIsSingleChoice:Z

    .line 801
    return-object p0
.end method

.method public setSingleChoiceItems([Ljava/lang/CharSequence;ILandroid/content/DialogInterface$OnClickListener;)Lmiui/app/AlertDialog$Builder;
    .locals 2

    .prologue
    .line 775
    iget-object v0, p0, Lmiui/app/AlertDialog$Builder;->pj:Lcom/miui/internal/variable/AlertControllerWrapper$AlertParams;

    iput-object p1, v0, Lcom/miui/internal/variable/AlertControllerWrapper$AlertParams;->mItems:[Ljava/lang/CharSequence;

    .line 776
    iget-object v0, p0, Lmiui/app/AlertDialog$Builder;->pj:Lcom/miui/internal/variable/AlertControllerWrapper$AlertParams;

    iput-object p3, v0, Lcom/miui/internal/variable/AlertControllerWrapper$AlertParams;->mOnClickListener:Landroid/content/DialogInterface$OnClickListener;

    .line 777
    iget-object v0, p0, Lmiui/app/AlertDialog$Builder;->pj:Lcom/miui/internal/variable/AlertControllerWrapper$AlertParams;

    iput p2, v0, Lcom/miui/internal/variable/AlertControllerWrapper$AlertParams;->mCheckedItem:I

    .line 778
    iget-object v0, p0, Lmiui/app/AlertDialog$Builder;->pj:Lcom/miui/internal/variable/AlertControllerWrapper$AlertParams;

    const/4 v1, 0x1

    iput-boolean v1, v0, Lcom/miui/internal/variable/AlertControllerWrapper$AlertParams;->mIsSingleChoice:Z

    .line 779
    return-object p0
.end method

.method public setTitle(I)Lmiui/app/AlertDialog$Builder;
    .locals 2

    .prologue
    .line 317
    iget-object v0, p0, Lmiui/app/AlertDialog$Builder;->pj:Lcom/miui/internal/variable/AlertControllerWrapper$AlertParams;

    iget-object v1, p0, Lmiui/app/AlertDialog$Builder;->pj:Lcom/miui/internal/variable/AlertControllerWrapper$AlertParams;

    iget-object v1, v1, Lcom/miui/internal/variable/AlertControllerWrapper$AlertParams;->mContext:Landroid/content/Context;

    invoke-virtual {v1, p1}, Landroid/content/Context;->getText(I)Ljava/lang/CharSequence;

    move-result-object v1

    iput-object v1, v0, Lcom/miui/internal/variable/AlertControllerWrapper$AlertParams;->mTitle:Ljava/lang/CharSequence;

    .line 318
    return-object p0
.end method

.method public setTitle(Ljava/lang/CharSequence;)Lmiui/app/AlertDialog$Builder;
    .locals 1

    .prologue
    .line 327
    iget-object v0, p0, Lmiui/app/AlertDialog$Builder;->pj:Lcom/miui/internal/variable/AlertControllerWrapper$AlertParams;

    iput-object p1, v0, Lcom/miui/internal/variable/AlertControllerWrapper$AlertParams;->mTitle:Ljava/lang/CharSequence;

    .line 328
    return-object p0
.end method

.method public setView(I)Lmiui/app/AlertDialog$Builder;
    .locals 3

    .prologue
    .line 825
    iget-object v0, p0, Lmiui/app/AlertDialog$Builder;->pj:Lcom/miui/internal/variable/AlertControllerWrapper$AlertParams;

    iget-object v1, p0, Lmiui/app/AlertDialog$Builder;->pj:Lcom/miui/internal/variable/AlertControllerWrapper$AlertParams;

    iget-object v1, v1, Lcom/miui/internal/variable/AlertControllerWrapper$AlertParams;->mInflater:Landroid/view/LayoutInflater;

    const/4 v2, 0x0

    invoke-virtual {v1, p1, v2}, Landroid/view/LayoutInflater;->inflate(ILandroid/view/ViewGroup;)Landroid/view/View;

    move-result-object v1

    iput-object v1, v0, Lcom/miui/internal/variable/AlertControllerWrapper$AlertParams;->mView:Landroid/view/View;

    .line 826
    return-object p0
.end method

.method public setView(Landroid/view/View;)Lmiui/app/AlertDialog$Builder;
    .locals 1

    .prologue
    .line 837
    iget-object v0, p0, Lmiui/app/AlertDialog$Builder;->pj:Lcom/miui/internal/variable/AlertControllerWrapper$AlertParams;

    iput-object p1, v0, Lcom/miui/internal/variable/AlertControllerWrapper$AlertParams;->mView:Landroid/view/View;

    .line 838
    return-object p0
.end method

.method public show()Lmiui/app/AlertDialog;
    .locals 1

    .prologue
    .line 868
    invoke-virtual {p0}, Lmiui/app/AlertDialog$Builder;->create()Lmiui/app/AlertDialog;

    move-result-object v0

    .line 869
    invoke-virtual {v0}, Lmiui/app/AlertDialog;->show()V

    .line 870
    return-object v0
.end method
