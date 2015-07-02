.class public Lcom/miui/internal/app/AlertControllerImpl;
.super Ljava/lang/Object;
.source "SourceFile"

# interfaces
.implements Lcom/miui/internal/view/menu/MenuBuilder$Callback;


# annotations
.annotation system Ldalvik/annotation/MemberClasses;
    value = {
        Lcom/miui/internal/app/AlertControllerImpl$a;
    }
.end annotation


# instance fields
.field private hJ:Landroid/widget/TextView;

.field private ij:Landroid/view/Window$Callback;

.field private jc:Lmiui/app/ActionBar;

.field private final kk:Ljava/lang/Runnable;

.field private lM:Landroid/widget/TextView;

.field private mActionBarView:Lcom/miui/internal/widget/ActionBarView;

.field private mActionItems:Ljava/util/ArrayList;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Ljava/util/ArrayList",
            "<",
            "Lcom/miui/internal/variable/AlertControllerWrapper$AlertParams$ActionItem;",
            ">;"
        }
    .end annotation
.end field

.field private mAdapter:Landroid/widget/ListAdapter;

.field private mCheckBoxMessage:Ljava/lang/CharSequence;

.field private mCheckedItem:I

.field private mCheckedItems:[Z

.field private mContext:Landroid/content/Context;

.field private mCustomTitleView:Landroid/view/View;

.field private mHandler:Landroid/os/Handler;

.field private mIsChecked:Z

.field private mMenu:Lcom/miui/internal/view/menu/MenuBuilder;

.field private mMessage:Ljava/lang/CharSequence;

.field private mOnActionItemClickListener:Landroid/content/DialogInterface$OnClickListener;

.field private mTitle:Ljava/lang/CharSequence;

.field private mView:Landroid/view/View;

.field private final xQ:I

.field private final xR:I

.field private final xS:I

.field private final xT:I

.field private final xU:I

.field private final xV:Landroid/view/Window;

.field private xW:Landroid/view/ViewGroup;

.field private xX:Landroid/widget/ListView;

.field private xY:Lmiui/widget/EditableListViewWrapper;

.field private xZ:Landroid/widget/Button;

.field private ya:Landroid/widget/Button;

.field private yb:Ljava/lang/CharSequence;

.field private yc:Landroid/os/Message;

.field private yd:Landroid/widget/Button;

.field private ye:Ljava/lang/CharSequence;

.field private yf:Landroid/os/Message;

.field private yg:Landroid/widget/Button;

.field private yh:Ljava/lang/CharSequence;

.field private yi:Landroid/os/Message;

.field private yj:Landroid/widget/ScrollView;

.field private yk:Landroid/content/DialogInterface;

.field private yl:Landroid/view/View$OnClickListener;

.field private ym:Lcom/miui/internal/view/menu/MenuPresenter$Callback;


# direct methods
.method public constructor <init>(Landroid/content/Context;Landroid/content/DialogInterface;Landroid/view/Window;)V
    .locals 4

    .prologue
    const/4 v3, 0x0

    .line 183
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    .line 130
    new-instance v0, Lcom/miui/internal/app/d;

    invoke-direct {v0, p0}, Lcom/miui/internal/app/d;-><init>(Lcom/miui/internal/app/AlertControllerImpl;)V

    iput-object v0, p0, Lcom/miui/internal/app/AlertControllerImpl;->yl:Landroid/view/View$OnClickListener;

    .line 150
    const/4 v0, -0x1

    iput v0, p0, Lcom/miui/internal/app/AlertControllerImpl;->mCheckedItem:I

    .line 290
    new-instance v0, Lcom/miui/internal/app/e;

    invoke-direct {v0, p0}, Lcom/miui/internal/app/e;-><init>(Lcom/miui/internal/app/AlertControllerImpl;)V

    iput-object v0, p0, Lcom/miui/internal/app/AlertControllerImpl;->kk:Ljava/lang/Runnable;

    .line 788
    new-instance v0, Lcom/miui/internal/app/b;

    invoke-direct {v0, p0}, Lcom/miui/internal/app/b;-><init>(Lcom/miui/internal/app/AlertControllerImpl;)V

    iput-object v0, p0, Lcom/miui/internal/app/AlertControllerImpl;->ym:Lcom/miui/internal/view/menu/MenuPresenter$Callback;

    .line 801
    new-instance v0, Lcom/miui/internal/app/c;

    invoke-direct {v0, p0}, Lcom/miui/internal/app/c;-><init>(Lcom/miui/internal/app/AlertControllerImpl;)V

    iput-object v0, p0, Lcom/miui/internal/app/AlertControllerImpl;->ij:Landroid/view/Window$Callback;

    .line 185
    iput-object p1, p0, Lcom/miui/internal/app/AlertControllerImpl;->mContext:Landroid/content/Context;

    .line 186
    iput-object p2, p0, Lcom/miui/internal/app/AlertControllerImpl;->yk:Landroid/content/DialogInterface;

    .line 187
    iput-object p3, p0, Lcom/miui/internal/app/AlertControllerImpl;->xV:Landroid/view/Window;

    .line 188
    new-instance v0, Lcom/miui/internal/app/AlertControllerImpl$a;

    invoke-direct {v0, p2}, Lcom/miui/internal/app/AlertControllerImpl$a;-><init>(Landroid/content/DialogInterface;)V

    iput-object v0, p0, Lcom/miui/internal/app/AlertControllerImpl;->mHandler:Landroid/os/Handler;

    .line 190
    const/4 v0, 0x0

    sget-object v1, Lcom/miui/internal/R$styleable;->AlertDialog:[I

    const v2, 0x101005d

    invoke-virtual {p1, v0, v1, v2, v3}, Landroid/content/Context;->obtainStyledAttributes(Landroid/util/AttributeSet;[III)Landroid/content/res/TypedArray;

    move-result-object v0

    .line 194
    sget v1, Lcom/miui/internal/R$layout;->alert_dialog:I

    invoke-virtual {v0, v3, v1}, Landroid/content/res/TypedArray;->getResourceId(II)I

    move-result v1

    iput v1, p0, Lcom/miui/internal/app/AlertControllerImpl;->xU:I

    .line 196
    const/4 v1, 0x1

    sget v2, Lcom/miui/internal/R$layout;->select_dialog:I

    invoke-virtual {v0, v1, v2}, Landroid/content/res/TypedArray;->getResourceId(II)I

    move-result v1

    iput v1, p0, Lcom/miui/internal/app/AlertControllerImpl;->xQ:I

    .line 199
    const/4 v1, 0x2

    sget v2, Lcom/miui/internal/R$layout;->select_dialog_multichoice:I

    invoke-virtual {v0, v1, v2}, Landroid/content/res/TypedArray;->getResourceId(II)I

    move-result v1

    iput v1, p0, Lcom/miui/internal/app/AlertControllerImpl;->xR:I

    .line 202
    const/4 v1, 0x3

    sget v2, Lcom/miui/internal/R$layout;->select_dialog_singlechoice:I

    invoke-virtual {v0, v1, v2}, Landroid/content/res/TypedArray;->getResourceId(II)I

    move-result v1

    iput v1, p0, Lcom/miui/internal/app/AlertControllerImpl;->xS:I

    .line 205
    const/4 v1, 0x4

    sget v2, Lcom/miui/internal/R$layout;->select_dialog_item:I

    invoke-virtual {v0, v1, v2}, Landroid/content/res/TypedArray;->getResourceId(II)I

    move-result v1

    iput v1, p0, Lcom/miui/internal/app/AlertControllerImpl;->xT:I

    .line 209
    invoke-virtual {v0}, Landroid/content/res/TypedArray;->recycle()V

    .line 210
    return-void
.end method

.method static synthetic a(Lcom/miui/internal/app/AlertControllerImpl;)Landroid/widget/Button;
    .locals 1

    .prologue
    .line 56
    iget-object v0, p0, Lcom/miui/internal/app/AlertControllerImpl;->ya:Landroid/widget/Button;

    return-object v0
.end method

.method private a(Landroid/view/ViewGroup;)V
    .locals 5

    .prologue
    const/4 v3, 0x0

    .line 519
    iget-object v0, p0, Lcom/miui/internal/app/AlertControllerImpl;->mCustomTitleView:Landroid/view/View;

    if-eqz v0, :cond_3

    .line 521
    new-instance v0, Landroid/widget/LinearLayout$LayoutParams;

    const/4 v1, -0x1

    const/4 v2, -0x2

    invoke-direct {v0, v1, v2}, Landroid/widget/LinearLayout$LayoutParams;-><init>(II)V

    .line 523
    iget-object v1, p0, Lcom/miui/internal/app/AlertControllerImpl;->mCustomTitleView:Landroid/view/View;

    invoke-virtual {p1, v1, v3, v0}, Landroid/view/ViewGroup;->addView(Landroid/view/View;ILandroid/view/ViewGroup$LayoutParams;)V

    .line 526
    iget-object v0, p0, Lcom/miui/internal/app/AlertControllerImpl;->mContext:Landroid/content/Context;

    invoke-virtual {v0}, Landroid/content/Context;->getResources()Landroid/content/res/Resources;

    move-result-object v0

    sget v1, Lcom/miui/internal/R$dimen;->dialog_title_vertical_padding:I

    invoke-virtual {v0, v1}, Landroid/content/res/Resources;->getDimensionPixelSize(I)I

    move-result v0

    .line 528
    iget-object v1, p0, Lcom/miui/internal/app/AlertControllerImpl;->mCustomTitleView:Landroid/view/View;

    invoke-virtual {v1}, Landroid/view/View;->getPaddingTop()I

    move-result v1

    if-eqz v1, :cond_0

    iget-object v0, p0, Lcom/miui/internal/app/AlertControllerImpl;->mCustomTitleView:Landroid/view/View;

    invoke-virtual {v0}, Landroid/view/View;->getPaddingTop()I

    move-result v0

    .line 531
    :cond_0
    iget-object v1, p0, Lcom/miui/internal/app/AlertControllerImpl;->mContext:Landroid/content/Context;

    invoke-virtual {v1}, Landroid/content/Context;->getResources()Landroid/content/res/Resources;

    move-result-object v1

    sget v2, Lcom/miui/internal/R$dimen;->dialog_title_horizontal_padding:I

    invoke-virtual {v1, v2}, Landroid/content/res/Resources;->getDimensionPixelSize(I)I

    move-result v2

    .line 534
    iget-object v1, p0, Lcom/miui/internal/app/AlertControllerImpl;->mCustomTitleView:Landroid/view/View;

    invoke-virtual {v1}, Landroid/view/View;->getPaddingLeft()I

    move-result v1

    if-eqz v1, :cond_2

    iget-object v1, p0, Lcom/miui/internal/app/AlertControllerImpl;->mCustomTitleView:Landroid/view/View;

    invoke-virtual {v1}, Landroid/view/View;->getPaddingLeft()I

    move-result v1

    .line 537
    :goto_0
    iget-object v4, p0, Lcom/miui/internal/app/AlertControllerImpl;->mCustomTitleView:Landroid/view/View;

    invoke-virtual {v4}, Landroid/view/View;->getPaddingRight()I

    move-result v4

    if-eqz v4, :cond_1

    iget-object v2, p0, Lcom/miui/internal/app/AlertControllerImpl;->mCustomTitleView:Landroid/view/View;

    invoke-virtual {v2}, Landroid/view/View;->getPaddingRight()I

    move-result v2

    .line 540
    :cond_1
    iget-object v4, p0, Lcom/miui/internal/app/AlertControllerImpl;->mCustomTitleView:Landroid/view/View;

    invoke-virtual {v4, v1, v0, v2, v3}, Landroid/view/View;->setPadding(IIII)V

    .line 543
    iget-object v0, p0, Lcom/miui/internal/app/AlertControllerImpl;->xW:Landroid/view/ViewGroup;

    sget v1, Lcom/miui/internal/R$id;->alertTitle:I

    invoke-virtual {v0, v1}, Landroid/view/ViewGroup;->findViewById(I)Landroid/view/View;

    move-result-object v0

    .line 545
    invoke-virtual {p1, v0}, Landroid/view/ViewGroup;->removeView(Landroid/view/View;)V

    .line 556
    :goto_1
    return-void

    :cond_2
    move v1, v2

    .line 534
    goto :goto_0

    .line 547
    :cond_3
    iget-object v0, p0, Lcom/miui/internal/app/AlertControllerImpl;->mTitle:Ljava/lang/CharSequence;

    invoke-static {v0}, Landroid/text/TextUtils;->isEmpty(Ljava/lang/CharSequence;)Z

    move-result v0

    if-nez v0, :cond_4

    const/4 v0, 0x1

    .line 548
    :goto_2
    if-eqz v0, :cond_5

    .line 549
    sget v0, Lcom/miui/internal/R$id;->alertTitle:I

    invoke-virtual {p1, v0}, Landroid/view/ViewGroup;->findViewById(I)Landroid/view/View;

    move-result-object v0

    check-cast v0, Landroid/widget/TextView;

    iput-object v0, p0, Lcom/miui/internal/app/AlertControllerImpl;->hJ:Landroid/widget/TextView;

    .line 551
    iget-object v0, p0, Lcom/miui/internal/app/AlertControllerImpl;->hJ:Landroid/widget/TextView;

    iget-object v1, p0, Lcom/miui/internal/app/AlertControllerImpl;->mTitle:Ljava/lang/CharSequence;

    invoke-virtual {v0, v1}, Landroid/widget/TextView;->setText(Ljava/lang/CharSequence;)V

    goto :goto_1

    :cond_4
    move v0, v3

    .line 547
    goto :goto_2

    .line 553
    :cond_5
    const/16 v0, 0x8

    invoke-virtual {p1, v0}, Landroid/view/ViewGroup;->setVisibility(I)V

    goto :goto_1
.end method

.method private a(Landroid/widget/FrameLayout;)V
    .locals 6

    .prologue
    const/4 v3, -0x1

    const/4 v5, 0x0

    .line 684
    iget-object v0, p0, Lcom/miui/internal/app/AlertControllerImpl;->mView:Landroid/view/View;

    if-eqz v0, :cond_6

    .line 685
    iget-object v0, p0, Lcom/miui/internal/app/AlertControllerImpl;->xW:Landroid/view/ViewGroup;

    const v1, 0x102002b

    invoke-virtual {v0, v1}, Landroid/view/ViewGroup;->findViewById(I)Landroid/view/View;

    move-result-object v0

    check-cast v0, Landroid/widget/FrameLayout;

    .line 687
    iget-object v1, p0, Lcom/miui/internal/app/AlertControllerImpl;->mView:Landroid/view/View;

    new-instance v2, Landroid/view/ViewGroup$LayoutParams;

    invoke-direct {v2, v3, v3}, Landroid/view/ViewGroup$LayoutParams;-><init>(II)V

    invoke-virtual {v0, v1, v2}, Landroid/widget/FrameLayout;->addView(Landroid/view/View;Landroid/view/ViewGroup$LayoutParams;)V

    .line 688
    iget-object v0, p0, Lcom/miui/internal/app/AlertControllerImpl;->xX:Landroid/widget/ListView;

    if-eqz v0, :cond_0

    .line 689
    invoke-virtual {p1}, Landroid/widget/FrameLayout;->getLayoutParams()Landroid/view/ViewGroup$LayoutParams;

    move-result-object v0

    check-cast v0, Landroid/widget/LinearLayout$LayoutParams;

    const/4 v1, 0x0

    iput v1, v0, Landroid/widget/LinearLayout$LayoutParams;->weight:F

    .line 692
    :cond_0
    iget-object v0, p0, Lcom/miui/internal/app/AlertControllerImpl;->mView:Landroid/view/View;

    instance-of v0, v0, Landroid/view/ViewGroup;

    if-eqz v0, :cond_3

    .line 693
    iget-object v0, p0, Lcom/miui/internal/app/AlertControllerImpl;->mView:Landroid/view/View;

    check-cast v0, Landroid/view/ViewGroup;

    .line 695
    iget-object v1, p0, Lcom/miui/internal/app/AlertControllerImpl;->mContext:Landroid/content/Context;

    invoke-virtual {v1}, Landroid/content/Context;->getResources()Landroid/content/res/Resources;

    move-result-object v1

    sget v2, Lcom/miui/internal/R$dimen;->dialog_custom_vertical_padding:I

    invoke-virtual {v1, v2}, Landroid/content/res/Resources;->getDimensionPixelSize(I)I

    move-result v1

    .line 698
    invoke-virtual {v0}, Landroid/view/ViewGroup;->getPaddingTop()I

    move-result v2

    if-eqz v2, :cond_1

    invoke-virtual {v0}, Landroid/view/ViewGroup;->getPaddingTop()I

    move-result v1

    .line 701
    :cond_1
    iget-object v2, p0, Lcom/miui/internal/app/AlertControllerImpl;->mContext:Landroid/content/Context;

    invoke-virtual {v2}, Landroid/content/Context;->getResources()Landroid/content/res/Resources;

    move-result-object v2

    sget v3, Lcom/miui/internal/R$dimen;->dialog_custom_horizontal_padding:I

    invoke-virtual {v2, v3}, Landroid/content/res/Resources;->getDimensionPixelSize(I)I

    move-result v3

    .line 704
    invoke-virtual {v0}, Landroid/view/ViewGroup;->getPaddingLeft()I

    move-result v2

    if-eqz v2, :cond_4

    invoke-virtual {v0}, Landroid/view/ViewGroup;->getPaddingLeft()I

    move-result v2

    .line 707
    :goto_0
    invoke-virtual {v0}, Landroid/view/ViewGroup;->getPaddingRight()I

    move-result v4

    if-eqz v4, :cond_2

    invoke-virtual {v0}, Landroid/view/ViewGroup;->getPaddingRight()I

    move-result v3

    .line 710
    :cond_2
    const v4, 0x102000d

    invoke-virtual {v0, v4}, Landroid/view/ViewGroup;->findViewById(I)Landroid/view/View;

    move-result-object v4

    .line 711
    if-eqz v4, :cond_5

    instance-of v4, v4, Lmiui/widget/ProgressBar;

    if-nez v4, :cond_5

    .line 712
    invoke-virtual {p1, v5, v5, v5, v5}, Landroid/widget/FrameLayout;->setPadding(IIII)V

    .line 722
    :cond_3
    :goto_1
    return-void

    :cond_4
    move v2, v3

    .line 704
    goto :goto_0

    .line 714
    :cond_5
    invoke-virtual {v0}, Landroid/view/ViewGroup;->getPaddingBottom()I

    move-result v4

    invoke-virtual {v0, v2, v1, v3, v4}, Landroid/view/ViewGroup;->setPadding(IIII)V

    .line 716
    invoke-virtual {p1, v5, v5, v5, v5}, Landroid/widget/FrameLayout;->setPadding(IIII)V

    goto :goto_1

    .line 720
    :cond_6
    const/16 v0, 0x8

    invoke-virtual {p1, v0}, Landroid/widget/FrameLayout;->setVisibility(I)V

    goto :goto_1
.end method

.method static synthetic a(Lcom/miui/internal/app/AlertControllerImpl;Lcom/miui/internal/view/menu/MenuBuilder;)Z
    .locals 1

    .prologue
    .line 56
    invoke-direct {p0, p1}, Lcom/miui/internal/app/AlertControllerImpl;->f(Lcom/miui/internal/view/menu/MenuBuilder;)Z

    move-result v0

    return v0
.end method

.method static synthetic b(Lcom/miui/internal/app/AlertControllerImpl;)Landroid/os/Message;
    .locals 1

    .prologue
    .line 56
    iget-object v0, p0, Lcom/miui/internal/app/AlertControllerImpl;->yc:Landroid/os/Message;

    return-object v0
.end method

.method private b(Landroid/view/ViewGroup;)V
    .locals 5

    .prologue
    const/16 v4, 0x8

    const/4 v2, -0x1

    const/4 v3, 0x0

    .line 559
    iget-object v0, p0, Lcom/miui/internal/app/AlertControllerImpl;->xW:Landroid/view/ViewGroup;

    sget v1, Lcom/miui/internal/R$id;->scrollView:I

    invoke-virtual {v0, v1}, Landroid/view/ViewGroup;->findViewById(I)Landroid/view/View;

    move-result-object v0

    check-cast v0, Landroid/widget/ScrollView;

    iput-object v0, p0, Lcom/miui/internal/app/AlertControllerImpl;->yj:Landroid/widget/ScrollView;

    .line 561
    iget-object v0, p0, Lcom/miui/internal/app/AlertControllerImpl;->yj:Landroid/widget/ScrollView;

    invoke-virtual {v0, v3}, Landroid/widget/ScrollView;->setFocusable(Z)V

    .line 564
    iget-object v0, p0, Lcom/miui/internal/app/AlertControllerImpl;->xW:Landroid/view/ViewGroup;

    sget v1, Lcom/miui/internal/R$id;->message:I

    invoke-virtual {v0, v1}, Landroid/view/ViewGroup;->findViewById(I)Landroid/view/View;

    move-result-object v0

    check-cast v0, Landroid/widget/TextView;

    iput-object v0, p0, Lcom/miui/internal/app/AlertControllerImpl;->lM:Landroid/widget/TextView;

    .line 565
    iget-object v0, p0, Lcom/miui/internal/app/AlertControllerImpl;->lM:Landroid/widget/TextView;

    if-nez v0, :cond_1

    .line 617
    :cond_0
    :goto_0
    return-void

    .line 569
    :cond_1
    iget-object v0, p0, Lcom/miui/internal/app/AlertControllerImpl;->mMessage:Ljava/lang/CharSequence;

    if-eqz v0, :cond_2

    .line 570
    iget-object v0, p0, Lcom/miui/internal/app/AlertControllerImpl;->lM:Landroid/widget/TextView;

    iget-object v1, p0, Lcom/miui/internal/app/AlertControllerImpl;->mMessage:Ljava/lang/CharSequence;

    invoke-virtual {v0, v1}, Landroid/widget/TextView;->setText(Ljava/lang/CharSequence;)V

    .line 571
    iget-object v0, p0, Lcom/miui/internal/app/AlertControllerImpl;->xW:Landroid/view/ViewGroup;

    sget v1, Lcom/miui/internal/R$id;->topPanel:I

    invoke-virtual {v0, v1}, Landroid/view/ViewGroup;->findViewById(I)Landroid/view/View;

    move-result-object v0

    .line 575
    if-eqz v0, :cond_0

    invoke-virtual {v0}, Landroid/view/View;->getVisibility()I

    move-result v0

    if-ne v0, v4, :cond_0

    iget-object v0, p0, Lcom/miui/internal/app/AlertControllerImpl;->mView:Landroid/view/View;

    if-nez v0, :cond_0

    .line 576
    iget-object v0, p0, Lcom/miui/internal/app/AlertControllerImpl;->mContext:Landroid/content/Context;

    invoke-virtual {v0}, Landroid/content/Context;->getResources()Landroid/content/res/Resources;

    move-result-object v0

    sget v1, Lcom/miui/internal/R$dimen;->dialog_title_vertical_padding:I

    invoke-virtual {v0, v1}, Landroid/content/res/Resources;->getDimensionPixelSize(I)I

    move-result v0

    .line 579
    invoke-virtual {p1}, Landroid/view/ViewGroup;->getPaddingLeft()I

    move-result v1

    invoke-virtual {p1}, Landroid/view/ViewGroup;->getRight()I

    move-result v2

    invoke-virtual {p1}, Landroid/view/ViewGroup;->getPaddingBottom()I

    move-result v3

    invoke-virtual {p1, v1, v0, v2, v3}, Landroid/view/ViewGroup;->setPadding(IIII)V

    goto :goto_0

    .line 583
    :cond_2
    iget-object v0, p0, Lcom/miui/internal/app/AlertControllerImpl;->lM:Landroid/widget/TextView;

    invoke-virtual {v0, v4}, Landroid/widget/TextView;->setVisibility(I)V

    .line 584
    iget-object v0, p0, Lcom/miui/internal/app/AlertControllerImpl;->yj:Landroid/widget/ScrollView;

    iget-object v1, p0, Lcom/miui/internal/app/AlertControllerImpl;->lM:Landroid/widget/TextView;

    invoke-virtual {v0, v1}, Landroid/widget/ScrollView;->removeView(Landroid/view/View;)V

    .line 586
    iget-object v0, p0, Lcom/miui/internal/app/AlertControllerImpl;->xX:Landroid/widget/ListView;

    if-eqz v0, :cond_4

    .line 587
    invoke-direct {p0}, Lcom/miui/internal/app/AlertControllerImpl;->cv()V

    .line 589
    iget-object v0, p0, Lcom/miui/internal/app/AlertControllerImpl;->xW:Landroid/view/ViewGroup;

    sget v1, Lcom/miui/internal/R$id;->scrollView:I

    invoke-virtual {v0, v1}, Landroid/view/ViewGroup;->findViewById(I)Landroid/view/View;

    move-result-object v0

    invoke-virtual {p1, v0}, Landroid/view/ViewGroup;->removeView(Landroid/view/View;)V

    .line 590
    iget-object v0, p0, Lcom/miui/internal/app/AlertControllerImpl;->xX:Landroid/widget/ListView;

    new-instance v1, Landroid/widget/LinearLayout$LayoutParams;

    invoke-direct {v1, v2, v2}, Landroid/widget/LinearLayout$LayoutParams;-><init>(II)V

    invoke-virtual {p1, v0, v1}, Landroid/view/ViewGroup;->addView(Landroid/view/View;Landroid/view/ViewGroup$LayoutParams;)V

    .line 592
    new-instance v0, Landroid/widget/LinearLayout$LayoutParams;

    const/high16 v1, 0x3f800000

    invoke-direct {v0, v2, v3, v1}, Landroid/widget/LinearLayout$LayoutParams;-><init>(IIF)V

    invoke-virtual {p1, v0}, Landroid/view/ViewGroup;->setLayoutParams(Landroid/view/ViewGroup$LayoutParams;)V

    .line 595
    iget-object v0, p0, Lcom/miui/internal/app/AlertControllerImpl;->mCustomTitleView:Landroid/view/View;

    if-nez v0, :cond_3

    iget-object v0, p0, Lcom/miui/internal/app/AlertControllerImpl;->hJ:Landroid/widget/TextView;

    if-nez v0, :cond_3

    .line 596
    invoke-virtual {p1, v3, v3, v3, v3}, Landroid/view/ViewGroup;->setPadding(IIII)V

    .line 600
    :cond_3
    sget-boolean v0, Lmiui/os/Build;->IS_TABLET:Z

    if-eqz v0, :cond_0

    iget-object v0, p0, Lcom/miui/internal/app/AlertControllerImpl;->mCustomTitleView:Landroid/view/View;

    if-nez v0, :cond_0

    iget-object v0, p0, Lcom/miui/internal/app/AlertControllerImpl;->hJ:Landroid/widget/TextView;

    if-eqz v0, :cond_0

    .line 601
    iget-object v0, p0, Lcom/miui/internal/app/AlertControllerImpl;->hJ:Landroid/widget/TextView;

    invoke-virtual {v0, v3, v3, v3, v3}, Landroid/widget/TextView;->setPadding(IIII)V

    .line 602
    iget-object v0, p0, Lcom/miui/internal/app/AlertControllerImpl;->xW:Landroid/view/ViewGroup;

    sget v1, Lcom/miui/internal/R$id;->topPanel:I

    invoke-virtual {v0, v1}, Landroid/view/ViewGroup;->findViewById(I)Landroid/view/View;

    move-result-object v0

    .line 604
    :try_start_0
    iget-object v1, p0, Lcom/miui/internal/app/AlertControllerImpl;->mContext:Landroid/content/Context;

    invoke-virtual {v1}, Landroid/content/Context;->getResources()Landroid/content/res/Resources;

    move-result-object v1

    sget v2, Lcom/miui/internal/R$drawable;->dialog_title_bg_light:I

    invoke-virtual {v1, v2}, Landroid/content/res/Resources;->getDrawable(I)Landroid/graphics/drawable/Drawable;

    move-result-object v1

    invoke-virtual {v0, v1}, Landroid/view/View;->setBackground(Landroid/graphics/drawable/Drawable;)V
    :try_end_0
    .catch Landroid/content/res/Resources$NotFoundException; {:try_start_0 .. :try_end_0} :catch_0

    .line 610
    :goto_1
    invoke-virtual {p1}, Landroid/view/ViewGroup;->getPaddingLeft()I

    move-result v0

    invoke-virtual {p1}, Landroid/view/ViewGroup;->getPaddingRight()I

    move-result v1

    invoke-virtual {p1}, Landroid/view/ViewGroup;->getPaddingBottom()I

    move-result v2

    invoke-virtual {p1, v0, v3, v1, v2}, Landroid/view/ViewGroup;->setPadding(IIII)V

    goto/16 :goto_0

    .line 614
    :cond_4
    invoke-virtual {p1, v4}, Landroid/view/ViewGroup;->setVisibility(I)V

    goto/16 :goto_0

    .line 606
    :catch_0
    move-exception v0

    goto :goto_1
.end method

.method private b(Landroid/widget/FrameLayout;)V
    .locals 2

    .prologue
    .line 725
    iget-object v0, p0, Lcom/miui/internal/app/AlertControllerImpl;->mCheckBoxMessage:Ljava/lang/CharSequence;

    if-eqz v0, :cond_0

    .line 726
    const/4 v0, 0x0

    invoke-virtual {p1, v0}, Landroid/widget/FrameLayout;->setVisibility(I)V

    .line 727
    const v0, 0x1020001

    invoke-virtual {p1, v0}, Landroid/widget/FrameLayout;->findViewById(I)Landroid/view/View;

    move-result-object v0

    check-cast v0, Landroid/widget/CheckBox;

    .line 728
    iget-boolean v1, p0, Lcom/miui/internal/app/AlertControllerImpl;->mIsChecked:Z

    invoke-virtual {v0, v1}, Landroid/widget/CheckBox;->setChecked(Z)V

    .line 729
    iget-object v1, p0, Lcom/miui/internal/app/AlertControllerImpl;->mCheckBoxMessage:Ljava/lang/CharSequence;

    invoke-virtual {v0, v1}, Landroid/widget/CheckBox;->setText(Ljava/lang/CharSequence;)V

    .line 733
    :goto_0
    return-void

    .line 731
    :cond_0
    const/16 v0, 0x8

    invoke-virtual {p1, v0}, Landroid/widget/FrameLayout;->setVisibility(I)V

    goto :goto_0
.end method

.method static synthetic b(Lcom/miui/internal/app/AlertControllerImpl;Lcom/miui/internal/view/menu/MenuBuilder;)Z
    .locals 1

    .prologue
    .line 56
    invoke-direct {p0, p1}, Lcom/miui/internal/app/AlertControllerImpl;->g(Lcom/miui/internal/view/menu/MenuBuilder;)Z

    move-result v0

    return v0
.end method

.method static synthetic c(Lcom/miui/internal/app/AlertControllerImpl;)Landroid/widget/Button;
    .locals 1

    .prologue
    .line 56
    iget-object v0, p0, Lcom/miui/internal/app/AlertControllerImpl;->yd:Landroid/widget/Button;

    return-object v0
.end method

.method private c(Landroid/view/ViewGroup;)V
    .locals 6

    .prologue
    const/16 v5, 0x8

    const/4 v3, 0x1

    const/4 v2, 0x0

    .line 736
    .line 738
    const v0, 0x1020019

    invoke-virtual {p1, v0}, Landroid/view/ViewGroup;->findViewById(I)Landroid/view/View;

    move-result-object v0

    check-cast v0, Landroid/widget/Button;

    iput-object v0, p0, Lcom/miui/internal/app/AlertControllerImpl;->ya:Landroid/widget/Button;

    .line 739
    iget-object v0, p0, Lcom/miui/internal/app/AlertControllerImpl;->ya:Landroid/widget/Button;

    if-eqz v0, :cond_7

    .line 740
    iget-object v0, p0, Lcom/miui/internal/app/AlertControllerImpl;->ya:Landroid/widget/Button;

    iget-object v1, p0, Lcom/miui/internal/app/AlertControllerImpl;->yl:Landroid/view/View$OnClickListener;

    invoke-virtual {v0, v1}, Landroid/widget/Button;->setOnClickListener(Landroid/view/View$OnClickListener;)V

    .line 741
    iget-object v0, p0, Lcom/miui/internal/app/AlertControllerImpl;->yb:Ljava/lang/CharSequence;

    invoke-static {v0}, Landroid/text/TextUtils;->isEmpty(Ljava/lang/CharSequence;)Z

    move-result v0

    if-eqz v0, :cond_3

    .line 742
    iget-object v0, p0, Lcom/miui/internal/app/AlertControllerImpl;->ya:Landroid/widget/Button;

    invoke-virtual {v0, v5}, Landroid/widget/Button;->setVisibility(I)V

    move v1, v2

    .line 750
    :goto_0
    const v0, 0x102001a

    invoke-virtual {p1, v0}, Landroid/view/ViewGroup;->findViewById(I)Landroid/view/View;

    move-result-object v0

    check-cast v0, Landroid/widget/Button;

    iput-object v0, p0, Lcom/miui/internal/app/AlertControllerImpl;->yd:Landroid/widget/Button;

    .line 751
    iget-object v0, p0, Lcom/miui/internal/app/AlertControllerImpl;->yd:Landroid/widget/Button;

    if-eqz v0, :cond_0

    .line 752
    iget-object v0, p0, Lcom/miui/internal/app/AlertControllerImpl;->yd:Landroid/widget/Button;

    iget-object v4, p0, Lcom/miui/internal/app/AlertControllerImpl;->yl:Landroid/view/View$OnClickListener;

    invoke-virtual {v0, v4}, Landroid/widget/Button;->setOnClickListener(Landroid/view/View$OnClickListener;)V

    .line 753
    iget-object v0, p0, Lcom/miui/internal/app/AlertControllerImpl;->ye:Ljava/lang/CharSequence;

    invoke-static {v0}, Landroid/text/TextUtils;->isEmpty(Ljava/lang/CharSequence;)Z

    move-result v0

    if-eqz v0, :cond_4

    .line 754
    iget-object v0, p0, Lcom/miui/internal/app/AlertControllerImpl;->yd:Landroid/widget/Button;

    invoke-virtual {v0, v5}, Landroid/widget/Button;->setVisibility(I)V

    .line 762
    :cond_0
    :goto_1
    const v0, 0x102001b

    invoke-virtual {p1, v0}, Landroid/view/ViewGroup;->findViewById(I)Landroid/view/View;

    move-result-object v0

    check-cast v0, Landroid/widget/Button;

    iput-object v0, p0, Lcom/miui/internal/app/AlertControllerImpl;->yg:Landroid/widget/Button;

    .line 763
    iget-object v0, p0, Lcom/miui/internal/app/AlertControllerImpl;->yg:Landroid/widget/Button;

    if-eqz v0, :cond_1

    .line 764
    iget-object v0, p0, Lcom/miui/internal/app/AlertControllerImpl;->yg:Landroid/widget/Button;

    iget-object v4, p0, Lcom/miui/internal/app/AlertControllerImpl;->yl:Landroid/view/View$OnClickListener;

    invoke-virtual {v0, v4}, Landroid/widget/Button;->setOnClickListener(Landroid/view/View$OnClickListener;)V

    .line 765
    iget-object v0, p0, Lcom/miui/internal/app/AlertControllerImpl;->yh:Ljava/lang/CharSequence;

    invoke-static {v0}, Landroid/text/TextUtils;->isEmpty(Ljava/lang/CharSequence;)Z

    move-result v0

    if-eqz v0, :cond_5

    .line 766
    iget-object v0, p0, Lcom/miui/internal/app/AlertControllerImpl;->yg:Landroid/widget/Button;

    invoke-virtual {v0, v5}, Landroid/widget/Button;->setVisibility(I)V

    .line 774
    :cond_1
    :goto_2
    if-eqz v1, :cond_6

    .line 776
    sget-boolean v0, Lmiui/os/Build;->IS_TABLET:Z

    if-eqz v0, :cond_2

    iget-object v0, p0, Lcom/miui/internal/app/AlertControllerImpl;->mMessage:Ljava/lang/CharSequence;

    if-nez v0, :cond_2

    iget-object v0, p0, Lcom/miui/internal/app/AlertControllerImpl;->xX:Landroid/widget/ListView;

    if-eqz v0, :cond_2

    invoke-virtual {p1}, Landroid/view/ViewGroup;->getChildCount()I

    move-result v0

    if-ne v0, v3, :cond_2

    .line 779
    invoke-virtual {p1, v2}, Landroid/view/ViewGroup;->getChildAt(I)Landroid/view/View;

    move-result-object v0

    invoke-virtual {p1}, Landroid/view/ViewGroup;->getPaddingLeft()I

    move-result v1

    invoke-virtual {p1}, Landroid/view/ViewGroup;->getPaddingRight()I

    move-result v3

    invoke-virtual {p1}, Landroid/view/ViewGroup;->getPaddingBottom()I

    move-result v4

    invoke-virtual {v0, v1, v2, v3, v4}, Landroid/view/View;->setPadding(IIII)V

    .line 786
    :cond_2
    :goto_3
    return-void

    .line 744
    :cond_3
    iget-object v0, p0, Lcom/miui/internal/app/AlertControllerImpl;->ya:Landroid/widget/Button;

    iget-object v1, p0, Lcom/miui/internal/app/AlertControllerImpl;->yb:Ljava/lang/CharSequence;

    invoke-virtual {v0, v1}, Landroid/widget/Button;->setText(Ljava/lang/CharSequence;)V

    .line 745
    iget-object v0, p0, Lcom/miui/internal/app/AlertControllerImpl;->ya:Landroid/widget/Button;

    invoke-virtual {v0, v2}, Landroid/widget/Button;->setVisibility(I)V

    move v1, v3

    .line 746
    goto :goto_0

    .line 756
    :cond_4
    iget-object v0, p0, Lcom/miui/internal/app/AlertControllerImpl;->yd:Landroid/widget/Button;

    iget-object v1, p0, Lcom/miui/internal/app/AlertControllerImpl;->ye:Ljava/lang/CharSequence;

    invoke-virtual {v0, v1}, Landroid/widget/Button;->setText(Ljava/lang/CharSequence;)V

    .line 757
    iget-object v0, p0, Lcom/miui/internal/app/AlertControllerImpl;->yd:Landroid/widget/Button;

    invoke-virtual {v0, v2}, Landroid/widget/Button;->setVisibility(I)V

    move v1, v3

    .line 758
    goto :goto_1

    .line 768
    :cond_5
    iget-object v0, p0, Lcom/miui/internal/app/AlertControllerImpl;->yg:Landroid/widget/Button;

    iget-object v1, p0, Lcom/miui/internal/app/AlertControllerImpl;->yh:Ljava/lang/CharSequence;

    invoke-virtual {v0, v1}, Landroid/widget/Button;->setText(Ljava/lang/CharSequence;)V

    .line 769
    iget-object v0, p0, Lcom/miui/internal/app/AlertControllerImpl;->yg:Landroid/widget/Button;

    invoke-virtual {v0, v2}, Landroid/widget/Button;->setVisibility(I)V

    move v1, v3

    .line 770
    goto :goto_2

    .line 784
    :cond_6
    invoke-virtual {p1, v5}, Landroid/view/ViewGroup;->setVisibility(I)V

    goto :goto_3

    :cond_7
    move v1, v2

    goto/16 :goto_0
.end method

.method static synthetic c(Lcom/miui/internal/app/AlertControllerImpl;Lcom/miui/internal/view/menu/MenuBuilder;)V
    .locals 0

    .prologue
    .line 56
    invoke-direct {p0, p1}, Lcom/miui/internal/app/AlertControllerImpl;->setMenu(Lcom/miui/internal/view/menu/MenuBuilder;)V

    return-void
.end method

.method private ct()V
    .locals 5

    .prologue
    const/4 v4, 0x1

    .line 248
    iget-object v0, p0, Lcom/miui/internal/app/AlertControllerImpl;->mActionItems:Ljava/util/ArrayList;

    if-eqz v0, :cond_2

    .line 249
    iget-object v0, p0, Lcom/miui/internal/app/AlertControllerImpl;->mContext:Landroid/content/Context;

    sget v1, Lcom/miui/internal/R$layout;->screen_action_bar:I

    const/4 v2, 0x0

    invoke-static {v0, v1, v2}, Landroid/view/View;->inflate(Landroid/content/Context;ILandroid/view/ViewGroup;)Landroid/view/View;

    move-result-object v2

    .line 250
    sget v0, Lcom/miui/internal/R$id;->action_bar:I

    invoke-virtual {v2, v0}, Landroid/view/View;->findViewById(I)Landroid/view/View;

    move-result-object v0

    check-cast v0, Lcom/miui/internal/widget/ActionBarView;

    iput-object v0, p0, Lcom/miui/internal/app/AlertControllerImpl;->mActionBarView:Lcom/miui/internal/widget/ActionBarView;

    .line 251
    iget-object v0, p0, Lcom/miui/internal/app/AlertControllerImpl;->mActionBarView:Lcom/miui/internal/widget/ActionBarView;

    iget-object v1, p0, Lcom/miui/internal/app/AlertControllerImpl;->ij:Landroid/view/Window$Callback;

    invoke-virtual {v0, v1}, Lcom/miui/internal/widget/ActionBarView;->setWindowCallback(Landroid/view/Window$Callback;)V

    .line 254
    sget v0, Lcom/miui/internal/R$id;->split_action_bar:I

    invoke-virtual {v2, v0}, Landroid/view/View;->findViewById(I)Landroid/view/View;

    move-result-object v0

    check-cast v0, Lcom/miui/internal/widget/ActionBarContainer;

    .line 256
    if-eqz v0, :cond_0

    .line 257
    iget-object v1, p0, Lcom/miui/internal/app/AlertControllerImpl;->mActionBarView:Lcom/miui/internal/widget/ActionBarView;

    invoke-virtual {v1, v0}, Lcom/miui/internal/widget/ActionBarView;->setSplitView(Lcom/miui/internal/widget/ActionBarContainer;)V

    .line 258
    iget-object v1, p0, Lcom/miui/internal/app/AlertControllerImpl;->mActionBarView:Lcom/miui/internal/widget/ActionBarView;

    invoke-virtual {v1, v4}, Lcom/miui/internal/widget/ActionBarView;->setSplitActionBar(Z)V

    .line 259
    iget-object v1, p0, Lcom/miui/internal/app/AlertControllerImpl;->mActionBarView:Lcom/miui/internal/widget/ActionBarView;

    invoke-virtual {v1, v4}, Lcom/miui/internal/widget/ActionBarView;->setSplitWhenNarrow(Z)V

    .line 261
    sget v1, Lcom/miui/internal/R$id;->action_context_bar:I

    invoke-virtual {v2, v1}, Landroid/view/View;->findViewById(I)Landroid/view/View;

    move-result-object v1

    check-cast v1, Lcom/miui/internal/widget/ActionBarContextView;

    .line 263
    invoke-virtual {v1, v0}, Lcom/miui/internal/widget/ActionBarContextView;->setSplitView(Lcom/miui/internal/widget/ActionBarContainer;)V

    .line 264
    invoke-virtual {v1, v4}, Lcom/miui/internal/widget/ActionBarContextView;->setSplitActionBar(Z)V

    .line 265
    invoke-virtual {v1, v4}, Lcom/miui/internal/widget/ActionBarContextView;->setSplitWhenNarrow(Z)V

    .line 269
    :cond_0
    const v0, 0x1020002

    invoke-virtual {v2, v0}, Landroid/view/View;->findViewById(I)Landroid/view/View;

    move-result-object v0

    check-cast v0, Landroid/view/ViewGroup;

    .line 270
    iget-object v1, p0, Lcom/miui/internal/app/AlertControllerImpl;->mContext:Landroid/content/Context;

    iget v3, p0, Lcom/miui/internal/app/AlertControllerImpl;->xU:I

    invoke-static {v1, v3, v0}, Landroid/view/View;->inflate(Landroid/content/Context;ILandroid/view/ViewGroup;)Landroid/view/View;

    .line 272
    iget-object v0, p0, Lcom/miui/internal/app/AlertControllerImpl;->xV:Landroid/view/Window;

    invoke-virtual {v0, v2}, Landroid/view/Window;->setContentView(Landroid/view/View;)V

    .line 273
    iget-object v0, p0, Lcom/miui/internal/app/AlertControllerImpl;->xV:Landroid/view/Window;

    invoke-virtual {v0}, Landroid/view/Window;->getDecorView()Landroid/view/View;

    move-result-object v0

    iget-object v1, p0, Lcom/miui/internal/app/AlertControllerImpl;->kk:Ljava/lang/Runnable;

    invoke-virtual {v0, v1}, Landroid/view/View;->post(Ljava/lang/Runnable;)Z

    .line 276
    new-instance v1, Lcom/miui/internal/app/ActionBarImpl;

    iget-object v0, p0, Lcom/miui/internal/app/AlertControllerImpl;->yk:Landroid/content/DialogInterface;

    check-cast v0, Landroid/app/Dialog;

    invoke-direct {v1, v0}, Lcom/miui/internal/app/ActionBarImpl;-><init>(Landroid/app/Dialog;)V

    iput-object v1, p0, Lcom/miui/internal/app/AlertControllerImpl;->jc:Lmiui/app/ActionBar;

    .line 277
    iget-object v0, p0, Lcom/miui/internal/app/AlertControllerImpl;->jc:Lmiui/app/ActionBar;

    const/4 v1, 0x0

    invoke-virtual {v0, v1}, Lmiui/app/ActionBar;->setDisplayOptions(I)V

    .line 279
    iget-object v0, p0, Lcom/miui/internal/app/AlertControllerImpl;->mActionBarView:Lcom/miui/internal/widget/ActionBarView;

    invoke-virtual {v0, v4}, Lcom/miui/internal/widget/ActionBarView;->setCollapsable(Z)V

    .line 288
    :cond_1
    :goto_0
    return-void

    .line 281
    :cond_2
    iget-object v0, p0, Lcom/miui/internal/app/AlertControllerImpl;->xV:Landroid/view/Window;

    iget v1, p0, Lcom/miui/internal/app/AlertControllerImpl;->xU:I

    invoke-virtual {v0, v1}, Landroid/view/Window;->setContentView(I)V

    .line 283
    sget-boolean v0, Lmiui/os/Build;->IS_TABLET:Z

    if-nez v0, :cond_1

    .line 284
    iget-object v0, p0, Lcom/miui/internal/app/AlertControllerImpl;->xV:Landroid/view/Window;

    const/16 v1, 0x50

    invoke-virtual {v0, v1}, Landroid/view/Window;->setGravity(I)V

    .line 285
    iget-object v0, p0, Lcom/miui/internal/app/AlertControllerImpl;->xV:Landroid/view/Window;

    const/4 v1, -0x1

    const/4 v2, -0x2

    invoke-virtual {v0, v1, v2}, Landroid/view/Window;->setLayout(II)V

    goto :goto_0
.end method

.method private cu()V
    .locals 2

    .prologue
    .line 483
    iget-object v0, p0, Lcom/miui/internal/app/AlertControllerImpl;->xW:Landroid/view/ViewGroup;

    sget v1, Lcom/miui/internal/R$id;->topPanel:I

    invoke-virtual {v0, v1}, Landroid/view/ViewGroup;->findViewById(I)Landroid/view/View;

    move-result-object v0

    check-cast v0, Landroid/view/ViewGroup;

    .line 485
    if-eqz v0, :cond_0

    .line 486
    invoke-direct {p0, v0}, Lcom/miui/internal/app/AlertControllerImpl;->a(Landroid/view/ViewGroup;)V

    .line 490
    :cond_0
    iget-object v0, p0, Lcom/miui/internal/app/AlertControllerImpl;->xW:Landroid/view/ViewGroup;

    sget v1, Lcom/miui/internal/R$id;->contentPanel:I

    invoke-virtual {v0, v1}, Landroid/view/ViewGroup;->findViewById(I)Landroid/view/View;

    move-result-object v0

    check-cast v0, Landroid/view/ViewGroup;

    .line 492
    if-eqz v0, :cond_1

    .line 493
    invoke-direct {p0, v0}, Lcom/miui/internal/app/AlertControllerImpl;->b(Landroid/view/ViewGroup;)V

    .line 497
    :cond_1
    iget-object v0, p0, Lcom/miui/internal/app/AlertControllerImpl;->xW:Landroid/view/ViewGroup;

    sget v1, Lcom/miui/internal/R$id;->customPanel:I

    invoke-virtual {v0, v1}, Landroid/view/ViewGroup;->findViewById(I)Landroid/view/View;

    move-result-object v0

    check-cast v0, Landroid/widget/FrameLayout;

    .line 499
    if-eqz v0, :cond_2

    .line 500
    invoke-direct {p0, v0}, Lcom/miui/internal/app/AlertControllerImpl;->a(Landroid/widget/FrameLayout;)V

    .line 504
    :cond_2
    iget-object v0, p0, Lcom/miui/internal/app/AlertControllerImpl;->xW:Landroid/view/ViewGroup;

    sget v1, Lcom/miui/internal/R$id;->checkboxPanel:I

    invoke-virtual {v0, v1}, Landroid/view/ViewGroup;->findViewById(I)Landroid/view/View;

    move-result-object v0

    check-cast v0, Landroid/widget/FrameLayout;

    .line 506
    if-eqz v0, :cond_3

    .line 507
    invoke-direct {p0, v0}, Lcom/miui/internal/app/AlertControllerImpl;->b(Landroid/widget/FrameLayout;)V

    .line 511
    :cond_3
    iget-object v0, p0, Lcom/miui/internal/app/AlertControllerImpl;->xW:Landroid/view/ViewGroup;

    sget v1, Lcom/miui/internal/R$id;->buttonPanel:I

    invoke-virtual {v0, v1}, Landroid/view/ViewGroup;->findViewById(I)Landroid/view/View;

    move-result-object v0

    check-cast v0, Landroid/view/ViewGroup;

    .line 513
    if-eqz v0, :cond_4

    .line 514
    invoke-direct {p0, v0}, Lcom/miui/internal/app/AlertControllerImpl;->c(Landroid/view/ViewGroup;)V

    .line 516
    :cond_4
    return-void
.end method

.method private cv()V
    .locals 3

    .prologue
    .line 620
    iget-object v0, p0, Lcom/miui/internal/app/AlertControllerImpl;->xX:Landroid/widget/ListView;

    invoke-virtual {v0}, Landroid/widget/ListView;->getChoiceMode()I

    move-result v1

    .line 621
    iget-object v0, p0, Lcom/miui/internal/app/AlertControllerImpl;->mAdapter:Landroid/widget/ListAdapter;

    if-eqz v0, :cond_3

    .line 622
    const/4 v0, 0x2

    if-ne v1, v0, :cond_7

    .line 623
    iget-object v0, p0, Lcom/miui/internal/app/AlertControllerImpl;->xW:Landroid/view/ViewGroup;

    sget v2, Lcom/miui/internal/R$id;->cancel:I

    invoke-virtual {v0, v2}, Landroid/view/ViewGroup;->findViewById(I)Landroid/view/View;

    move-result-object v0

    check-cast v0, Landroid/widget/Button;

    .line 624
    if-eqz v0, :cond_0

    .line 625
    new-instance v2, Lcom/miui/internal/app/AlertControllerImpl$1;

    invoke-direct {v2, p0}, Lcom/miui/internal/app/AlertControllerImpl$1;-><init>(Lcom/miui/internal/app/AlertControllerImpl;)V

    invoke-virtual {v0, v2}, Landroid/widget/Button;->setOnClickListener(Landroid/view/View$OnClickListener;)V

    .line 633
    :cond_0
    iget-object v0, p0, Lcom/miui/internal/app/AlertControllerImpl;->xW:Landroid/view/ViewGroup;

    sget v2, Lcom/miui/internal/R$id;->select:I

    invoke-virtual {v0, v2}, Landroid/view/ViewGroup;->findViewById(I)Landroid/view/View;

    move-result-object v0

    check-cast v0, Landroid/widget/Button;

    .line 634
    if-eqz v0, :cond_1

    .line 635
    iput-object v0, p0, Lcom/miui/internal/app/AlertControllerImpl;->xZ:Landroid/widget/Button;

    .line 636
    new-instance v2, Lcom/miui/internal/app/AlertControllerImpl$2;

    invoke-direct {v2, p0, v0}, Lcom/miui/internal/app/AlertControllerImpl$2;-><init>(Lcom/miui/internal/app/AlertControllerImpl;Landroid/widget/Button;)V

    invoke-virtual {v0, v2}, Landroid/widget/Button;->setOnClickListener(Landroid/view/View$OnClickListener;)V

    .line 647
    :cond_1
    iget-object v0, p0, Lcom/miui/internal/app/AlertControllerImpl;->xZ:Landroid/widget/Button;

    if-eqz v0, :cond_6

    .line 648
    new-instance v0, Lmiui/widget/EditableListViewWrapper;

    iget-object v2, p0, Lcom/miui/internal/app/AlertControllerImpl;->xX:Landroid/widget/ListView;

    invoke-direct {v0, v2}, Lmiui/widget/EditableListViewWrapper;-><init>(Landroid/widget/AbsListView;)V

    iput-object v0, p0, Lcom/miui/internal/app/AlertControllerImpl;->xY:Lmiui/widget/EditableListViewWrapper;

    .line 649
    iget-object v0, p0, Lcom/miui/internal/app/AlertControllerImpl;->xX:Landroid/widget/ListView;

    invoke-virtual {v0, v1}, Landroid/widget/ListView;->setChoiceMode(I)V

    .line 650
    iget-object v0, p0, Lcom/miui/internal/app/AlertControllerImpl;->xY:Lmiui/widget/EditableListViewWrapper;

    iget-object v1, p0, Lcom/miui/internal/app/AlertControllerImpl;->mAdapter:Landroid/widget/ListAdapter;

    invoke-virtual {v0, v1}, Lmiui/widget/EditableListViewWrapper;->setAdapter(Landroid/widget/ListAdapter;)V

    .line 651
    iget-object v0, p0, Lcom/miui/internal/app/AlertControllerImpl;->xX:Landroid/widget/ListView;

    invoke-virtual {v0}, Landroid/widget/ListView;->getOnItemClickListener()Landroid/widget/AdapterView$OnItemClickListener;

    move-result-object v0

    .line 653
    iget-object v1, p0, Lcom/miui/internal/app/AlertControllerImpl;->xX:Landroid/widget/ListView;

    new-instance v2, Lcom/miui/internal/app/AlertControllerImpl$3;

    invoke-direct {v2, p0, v0}, Lcom/miui/internal/app/AlertControllerImpl$3;-><init>(Lcom/miui/internal/app/AlertControllerImpl;Landroid/widget/AdapterView$OnItemClickListener;)V

    invoke-virtual {v1, v2}, Landroid/widget/ListView;->setOnItemClickListener(Landroid/widget/AdapterView$OnItemClickListener;)V

    .line 662
    iget-object v0, p0, Lcom/miui/internal/app/AlertControllerImpl;->mCheckedItems:[Z

    if-eqz v0, :cond_2

    .line 663
    const/4 v0, 0x0

    :goto_0
    iget-object v1, p0, Lcom/miui/internal/app/AlertControllerImpl;->mCheckedItems:[Z

    array-length v1, v1

    if-ge v0, v1, :cond_2

    .line 664
    iget-object v1, p0, Lcom/miui/internal/app/AlertControllerImpl;->xY:Lmiui/widget/EditableListViewWrapper;

    iget-object v2, p0, Lcom/miui/internal/app/AlertControllerImpl;->mCheckedItems:[Z

    aget-boolean v2, v2, v0

    invoke-virtual {v1, v0, v2}, Lmiui/widget/EditableListViewWrapper;->setItemChecked(IZ)V

    .line 663
    add-int/lit8 v0, v0, 0x1

    goto :goto_0

    .line 667
    :cond_2
    iget-object v1, p0, Lcom/miui/internal/app/AlertControllerImpl;->xZ:Landroid/widget/Button;

    iget-object v0, p0, Lcom/miui/internal/app/AlertControllerImpl;->xY:Lmiui/widget/EditableListViewWrapper;

    invoke-virtual {v0}, Lmiui/widget/EditableListViewWrapper;->isAllItemsChecked()Z

    move-result v0

    if-eqz v0, :cond_5

    sget v0, Lcom/miui/internal/R$string;->deselect_all:I

    :goto_1
    invoke-virtual {v1, v0}, Landroid/widget/Button;->setText(I)V

    .line 677
    :cond_3
    :goto_2
    iget v0, p0, Lcom/miui/internal/app/AlertControllerImpl;->mCheckedItem:I

    const/4 v1, -0x1

    if-le v0, v1, :cond_4

    .line 678
    iget-object v0, p0, Lcom/miui/internal/app/AlertControllerImpl;->xX:Landroid/widget/ListView;

    iget v1, p0, Lcom/miui/internal/app/AlertControllerImpl;->mCheckedItem:I

    const/4 v2, 0x1

    invoke-virtual {v0, v1, v2}, Landroid/widget/ListView;->setItemChecked(IZ)V

    .line 679
    iget-object v0, p0, Lcom/miui/internal/app/AlertControllerImpl;->xX:Landroid/widget/ListView;

    iget v1, p0, Lcom/miui/internal/app/AlertControllerImpl;->mCheckedItem:I

    invoke-virtual {v0, v1}, Landroid/widget/ListView;->setSelection(I)V

    .line 681
    :cond_4
    return-void

    .line 667
    :cond_5
    sget v0, Lcom/miui/internal/R$string;->select_all:I

    goto :goto_1

    .line 670
    :cond_6
    iget-object v0, p0, Lcom/miui/internal/app/AlertControllerImpl;->xX:Landroid/widget/ListView;

    iget-object v1, p0, Lcom/miui/internal/app/AlertControllerImpl;->mAdapter:Landroid/widget/ListAdapter;

    invoke-virtual {v0, v1}, Landroid/widget/ListView;->setAdapter(Landroid/widget/ListAdapter;)V

    goto :goto_2

    .line 673
    :cond_7
    iget-object v0, p0, Lcom/miui/internal/app/AlertControllerImpl;->xX:Landroid/widget/ListView;

    iget-object v1, p0, Lcom/miui/internal/app/AlertControllerImpl;->mAdapter:Landroid/widget/ListAdapter;

    invoke-virtual {v0, v1}, Landroid/widget/ListView;->setAdapter(Landroid/widget/ListAdapter;)V

    goto :goto_2
.end method

.method static synthetic d(Lcom/miui/internal/app/AlertControllerImpl;)Landroid/os/Message;
    .locals 1

    .prologue
    .line 56
    iget-object v0, p0, Lcom/miui/internal/app/AlertControllerImpl;->yf:Landroid/os/Message;

    return-object v0
.end method

.method static synthetic e(Lcom/miui/internal/app/AlertControllerImpl;)Landroid/widget/Button;
    .locals 1

    .prologue
    .line 56
    iget-object v0, p0, Lcom/miui/internal/app/AlertControllerImpl;->yg:Landroid/widget/Button;

    return-object v0
.end method

.method static synthetic f(Lcom/miui/internal/app/AlertControllerImpl;)Landroid/os/Message;
    .locals 1

    .prologue
    .line 56
    iget-object v0, p0, Lcom/miui/internal/app/AlertControllerImpl;->yi:Landroid/os/Message;

    return-object v0
.end method

.method static f(Landroid/view/View;)Z
    .locals 4

    .prologue
    const/4 v0, 0x1

    const/4 v1, 0x0

    .line 213
    invoke-virtual {p0}, Landroid/view/View;->onCheckIsTextEditor()Z

    move-result v2

    if-eqz v2, :cond_0

    .line 231
    :goto_0
    return v0

    .line 217
    :cond_0
    instance-of v2, p0, Landroid/view/ViewGroup;

    if-nez v2, :cond_1

    move v0, v1

    .line 218
    goto :goto_0

    .line 221
    :cond_1
    check-cast p0, Landroid/view/ViewGroup;

    .line 222
    invoke-virtual {p0}, Landroid/view/ViewGroup;->getChildCount()I

    move-result v2

    .line 223
    :cond_2
    if-lez v2, :cond_3

    .line 224
    add-int/lit8 v2, v2, -0x1

    .line 225
    invoke-virtual {p0, v2}, Landroid/view/ViewGroup;->getChildAt(I)Landroid/view/View;

    move-result-object v3

    .line 226
    invoke-static {v3}, Lcom/miui/internal/app/AlertControllerImpl;->f(Landroid/view/View;)Z

    move-result v3

    if-eqz v3, :cond_2

    goto :goto_0

    :cond_3
    move v0, v1

    .line 231
    goto :goto_0
.end method

.method private f(Lcom/miui/internal/view/menu/MenuBuilder;)Z
    .locals 5

    .prologue
    const/4 v4, 0x0

    .line 320
    iget-object v0, p0, Lcom/miui/internal/app/AlertControllerImpl;->mActionItems:Ljava/util/ArrayList;

    invoke-virtual {v0}, Ljava/util/ArrayList;->iterator()Ljava/util/Iterator;

    move-result-object v1

    :goto_0
    invoke-interface {v1}, Ljava/util/Iterator;->hasNext()Z

    move-result v0

    if-eqz v0, :cond_0

    invoke-interface {v1}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Lcom/miui/internal/variable/AlertControllerWrapper$AlertParams$ActionItem;

    .line 321
    iget v2, v0, Lcom/miui/internal/variable/AlertControllerWrapper$AlertParams$ActionItem;->id:I

    iget-object v3, v0, Lcom/miui/internal/variable/AlertControllerWrapper$AlertParams$ActionItem;->label:Ljava/lang/CharSequence;

    invoke-virtual {p1, v4, v2, v4, v3}, Lcom/miui/internal/view/menu/MenuBuilder;->add(IIILjava/lang/CharSequence;)Landroid/view/MenuItem;

    move-result-object v2

    iget v0, v0, Lcom/miui/internal/variable/AlertControllerWrapper$AlertParams$ActionItem;->icon:I

    invoke-interface {v2, v0}, Landroid/view/MenuItem;->setIcon(I)Landroid/view/MenuItem;

    move-result-object v0

    const/4 v2, 0x2

    invoke-interface {v0, v2}, Landroid/view/MenuItem;->setShowAsAction(I)V

    goto :goto_0

    .line 324
    :cond_0
    const/4 v0, 0x1

    return v0
.end method

.method static synthetic g(Lcom/miui/internal/app/AlertControllerImpl;)Landroid/content/DialogInterface;
    .locals 1

    .prologue
    .line 56
    iget-object v0, p0, Lcom/miui/internal/app/AlertControllerImpl;->yk:Landroid/content/DialogInterface;

    return-object v0
.end method

.method private g(Lcom/miui/internal/view/menu/MenuBuilder;)Z
    .locals 1

    .prologue
    .line 329
    const/4 v0, 0x1

    return v0
.end method

.method static synthetic h(Lcom/miui/internal/app/AlertControllerImpl;)Landroid/os/Handler;
    .locals 1

    .prologue
    .line 56
    iget-object v0, p0, Lcom/miui/internal/app/AlertControllerImpl;->mHandler:Landroid/os/Handler;

    return-object v0
.end method

.method static synthetic i(Lcom/miui/internal/app/AlertControllerImpl;)Lmiui/widget/EditableListViewWrapper;
    .locals 1

    .prologue
    .line 56
    iget-object v0, p0, Lcom/miui/internal/app/AlertControllerImpl;->xY:Lmiui/widget/EditableListViewWrapper;

    return-object v0
.end method

.method static synthetic j(Lcom/miui/internal/app/AlertControllerImpl;)Landroid/widget/Button;
    .locals 1

    .prologue
    .line 56
    iget-object v0, p0, Lcom/miui/internal/app/AlertControllerImpl;->xZ:Landroid/widget/Button;

    return-object v0
.end method

.method private setMenu(Lcom/miui/internal/view/menu/MenuBuilder;)V
    .locals 2

    .prologue
    .line 303
    iget-object v0, p0, Lcom/miui/internal/app/AlertControllerImpl;->mMenu:Lcom/miui/internal/view/menu/MenuBuilder;

    if-ne p1, v0, :cond_1

    .line 311
    :cond_0
    :goto_0
    return-void

    .line 307
    :cond_1
    iput-object p1, p0, Lcom/miui/internal/app/AlertControllerImpl;->mMenu:Lcom/miui/internal/view/menu/MenuBuilder;

    .line 308
    iget-object v0, p0, Lcom/miui/internal/app/AlertControllerImpl;->mActionBarView:Lcom/miui/internal/widget/ActionBarView;

    if-eqz v0, :cond_0

    .line 309
    iget-object v0, p0, Lcom/miui/internal/app/AlertControllerImpl;->mActionBarView:Lcom/miui/internal/widget/ActionBarView;

    iget-object v1, p0, Lcom/miui/internal/app/AlertControllerImpl;->ym:Lcom/miui/internal/view/menu/MenuPresenter$Callback;

    invoke-virtual {v0, p1, v1}, Lcom/miui/internal/widget/ActionBarView;->setMenu(Landroid/view/Menu;Lcom/miui/internal/view/menu/MenuPresenter$Callback;)V

    goto :goto_0
.end method


# virtual methods
.method createMenu()Lcom/miui/internal/view/menu/MenuBuilder;
    .locals 2

    .prologue
    .line 314
    new-instance v0, Lcom/miui/internal/view/menu/MenuBuilder;

    iget-object v1, p0, Lcom/miui/internal/app/AlertControllerImpl;->mContext:Landroid/content/Context;

    invoke-direct {v0, v1}, Lcom/miui/internal/view/menu/MenuBuilder;-><init>(Landroid/content/Context;)V

    .line 315
    invoke-virtual {v0, p0}, Lcom/miui/internal/view/menu/MenuBuilder;->setCallback(Lcom/miui/internal/view/menu/MenuBuilder$Callback;)V

    .line 316
    return-object v0
.end method

.method public getActionBar()Lmiui/app/ActionBar;
    .locals 1

    .prologue
    .line 460
    iget-object v0, p0, Lcom/miui/internal/app/AlertControllerImpl;->jc:Lmiui/app/ActionBar;

    return-object v0
.end method

.method public getButton(I)Landroid/widget/Button;
    .locals 1

    .prologue
    .line 447
    packed-switch p1, :pswitch_data_0

    .line 455
    const/4 v0, 0x0

    :goto_0
    return-object v0

    .line 449
    :pswitch_0
    iget-object v0, p0, Lcom/miui/internal/app/AlertControllerImpl;->ya:Landroid/widget/Button;

    goto :goto_0

    .line 451
    :pswitch_1
    iget-object v0, p0, Lcom/miui/internal/app/AlertControllerImpl;->yd:Landroid/widget/Button;

    goto :goto_0

    .line 453
    :pswitch_2
    iget-object v0, p0, Lcom/miui/internal/app/AlertControllerImpl;->yg:Landroid/widget/Button;

    goto :goto_0

    .line 447
    :pswitch_data_0
    .packed-switch -0x3
        :pswitch_2
        :pswitch_1
        :pswitch_0
    .end packed-switch
.end method

.method public getCheckedItems()[Z
    .locals 1

    .prologue
    .line 435
    iget-object v0, p0, Lcom/miui/internal/app/AlertControllerImpl;->mCheckedItems:[Z

    return-object v0
.end method

.method public getDialogInterface()Landroid/content/DialogInterface;
    .locals 1

    .prologue
    .line 443
    iget-object v0, p0, Lcom/miui/internal/app/AlertControllerImpl;->yk:Landroid/content/DialogInterface;

    return-object v0
.end method

.method public getListItemLayout()I
    .locals 1

    .prologue
    .line 415
    iget v0, p0, Lcom/miui/internal/app/AlertControllerImpl;->xT:I

    return v0
.end method

.method public getListLayout()I
    .locals 1

    .prologue
    .line 411
    iget v0, p0, Lcom/miui/internal/app/AlertControllerImpl;->xQ:I

    return v0
.end method

.method public getListView()Landroid/widget/ListView;
    .locals 1

    .prologue
    .line 403
    iget-object v0, p0, Lcom/miui/internal/app/AlertControllerImpl;->xX:Landroid/widget/ListView;

    return-object v0
.end method

.method public getMessageView()Landroid/widget/TextView;
    .locals 1

    .prologue
    .line 427
    iget-object v0, p0, Lcom/miui/internal/app/AlertControllerImpl;->lM:Landroid/widget/TextView;

    return-object v0
.end method

.method public getMultiChoiceItemLayout()I
    .locals 1

    .prologue
    .line 423
    iget v0, p0, Lcom/miui/internal/app/AlertControllerImpl;->xR:I

    return v0
.end method

.method public getSingleChoiceItemLayout()I
    .locals 1

    .prologue
    .line 419
    iget v0, p0, Lcom/miui/internal/app/AlertControllerImpl;->xS:I

    return v0
.end method

.method public installContent()V
    .locals 3

    .prologue
    const/high16 v2, 0x20000

    .line 235
    iget-object v0, p0, Lcom/miui/internal/app/AlertControllerImpl;->xV:Landroid/view/Window;

    const/4 v1, 0x1

    invoke-virtual {v0, v1}, Landroid/view/Window;->requestFeature(I)Z

    .line 237
    iget-object v0, p0, Lcom/miui/internal/app/AlertControllerImpl;->mView:Landroid/view/View;

    if-eqz v0, :cond_0

    iget-object v0, p0, Lcom/miui/internal/app/AlertControllerImpl;->mView:Landroid/view/View;

    invoke-static {v0}, Lcom/miui/internal/app/AlertControllerImpl;->f(Landroid/view/View;)Z

    move-result v0

    if-nez v0, :cond_1

    .line 238
    :cond_0
    iget-object v0, p0, Lcom/miui/internal/app/AlertControllerImpl;->xV:Landroid/view/Window;

    invoke-virtual {v0, v2, v2}, Landroid/view/Window;->setFlags(II)V

    .line 242
    :cond_1
    invoke-direct {p0}, Lcom/miui/internal/app/AlertControllerImpl;->ct()V

    .line 243
    iget-object v0, p0, Lcom/miui/internal/app/AlertControllerImpl;->xV:Landroid/view/Window;

    sget v1, Lcom/miui/internal/R$id;->parentPanel:I

    invoke-virtual {v0, v1}, Landroid/view/Window;->findViewById(I)Landroid/view/View;

    move-result-object v0

    check-cast v0, Landroid/view/ViewGroup;

    iput-object v0, p0, Lcom/miui/internal/app/AlertControllerImpl;->xW:Landroid/view/ViewGroup;

    .line 244
    invoke-direct {p0}, Lcom/miui/internal/app/AlertControllerImpl;->cu()V

    .line 245
    return-void
.end method

.method public isChecked()Z
    .locals 2

    .prologue
    .line 439
    iget-object v0, p0, Lcom/miui/internal/app/AlertControllerImpl;->xW:Landroid/view/ViewGroup;

    const v1, 0x1020001

    invoke-virtual {v0, v1}, Landroid/view/ViewGroup;->findViewById(I)Landroid/view/View;

    move-result-object v0

    check-cast v0, Landroid/widget/CheckBox;

    invoke-virtual {v0}, Landroid/widget/CheckBox;->isChecked()Z

    move-result v0

    iput-boolean v0, p0, Lcom/miui/internal/app/AlertControllerImpl;->mIsChecked:Z

    return v0
.end method

.method public onKeyDown(ILandroid/view/KeyEvent;)Z
    .locals 1

    .prologue
    .line 473
    iget-object v0, p0, Lcom/miui/internal/app/AlertControllerImpl;->yj:Landroid/widget/ScrollView;

    if-eqz v0, :cond_0

    iget-object v0, p0, Lcom/miui/internal/app/AlertControllerImpl;->yj:Landroid/widget/ScrollView;

    invoke-virtual {v0, p2}, Landroid/widget/ScrollView;->executeKeyEvent(Landroid/view/KeyEvent;)Z

    move-result v0

    if-eqz v0, :cond_0

    const/4 v0, 0x1

    :goto_0
    return v0

    :cond_0
    const/4 v0, 0x0

    goto :goto_0
.end method

.method public onKeyUp(ILandroid/view/KeyEvent;)Z
    .locals 1

    .prologue
    .line 478
    iget-object v0, p0, Lcom/miui/internal/app/AlertControllerImpl;->yj:Landroid/widget/ScrollView;

    if-eqz v0, :cond_0

    iget-object v0, p0, Lcom/miui/internal/app/AlertControllerImpl;->yj:Landroid/widget/ScrollView;

    invoke-virtual {v0, p2}, Landroid/widget/ScrollView;->executeKeyEvent(Landroid/view/KeyEvent;)Z

    move-result v0

    if-eqz v0, :cond_0

    const/4 v0, 0x1

    :goto_0
    return v0

    :cond_0
    const/4 v0, 0x0

    goto :goto_0
.end method

.method public onMenuItemSelected(Lcom/miui/internal/view/menu/MenuBuilder;Landroid/view/MenuItem;)Z
    .locals 3

    .prologue
    .line 334
    iget-object v0, p0, Lcom/miui/internal/app/AlertControllerImpl;->mOnActionItemClickListener:Landroid/content/DialogInterface$OnClickListener;

    if-eqz v0, :cond_0

    .line 335
    iget-object v0, p0, Lcom/miui/internal/app/AlertControllerImpl;->mOnActionItemClickListener:Landroid/content/DialogInterface$OnClickListener;

    iget-object v1, p0, Lcom/miui/internal/app/AlertControllerImpl;->yk:Landroid/content/DialogInterface;

    invoke-interface {p2}, Landroid/view/MenuItem;->getItemId()I

    move-result v2

    invoke-interface {v0, v1, v2}, Landroid/content/DialogInterface$OnClickListener;->onClick(Landroid/content/DialogInterface;I)V

    .line 337
    :cond_0
    const/4 v0, 0x1

    return v0
.end method

.method public onMenuModeChange(Lcom/miui/internal/view/menu/MenuBuilder;)V
    .locals 0

    .prologue
    .line 342
    return-void
.end method

.method public setActionItems(Ljava/util/ArrayList;Landroid/content/DialogInterface$OnClickListener;)V
    .locals 0
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Ljava/util/ArrayList",
            "<",
            "Lcom/miui/internal/variable/AlertControllerWrapper$AlertParams$ActionItem;",
            ">;",
            "Landroid/content/DialogInterface$OnClickListener;",
            ")V"
        }
    .end annotation

    .prologue
    .line 398
    iput-object p1, p0, Lcom/miui/internal/app/AlertControllerImpl;->mActionItems:Ljava/util/ArrayList;

    .line 399
    iput-object p2, p0, Lcom/miui/internal/app/AlertControllerImpl;->mOnActionItemClickListener:Landroid/content/DialogInterface$OnClickListener;

    .line 400
    return-void
.end method

.method public setAdapter(Landroid/widget/ListAdapter;)V
    .locals 0

    .prologue
    .line 464
    iput-object p1, p0, Lcom/miui/internal/app/AlertControllerImpl;->mAdapter:Landroid/widget/ListAdapter;

    .line 465
    return-void
.end method

.method public setButton(ILjava/lang/CharSequence;Landroid/content/DialogInterface$OnClickListener;Landroid/os/Message;)V
    .locals 2

    .prologue
    .line 372
    if-nez p4, :cond_0

    if-eqz p3, :cond_0

    .line 373
    iget-object v0, p0, Lcom/miui/internal/app/AlertControllerImpl;->mHandler:Landroid/os/Handler;

    invoke-virtual {v0, p1, p3}, Landroid/os/Handler;->obtainMessage(ILjava/lang/Object;)Landroid/os/Message;

    move-result-object p4

    .line 376
    :cond_0
    packed-switch p1, :pswitch_data_0

    .line 393
    new-instance v0, Ljava/lang/IllegalStateException;

    const-string v1, "Button does not exist"

    invoke-direct {v0, v1}, Ljava/lang/IllegalStateException;-><init>(Ljava/lang/String;)V

    throw v0

    .line 378
    :pswitch_0
    iput-object p2, p0, Lcom/miui/internal/app/AlertControllerImpl;->yb:Ljava/lang/CharSequence;

    .line 379
    iput-object p4, p0, Lcom/miui/internal/app/AlertControllerImpl;->yc:Landroid/os/Message;

    .line 395
    :goto_0
    return-void

    .line 383
    :pswitch_1
    iput-object p2, p0, Lcom/miui/internal/app/AlertControllerImpl;->ye:Ljava/lang/CharSequence;

    .line 384
    iput-object p4, p0, Lcom/miui/internal/app/AlertControllerImpl;->yf:Landroid/os/Message;

    goto :goto_0

    .line 388
    :pswitch_2
    iput-object p2, p0, Lcom/miui/internal/app/AlertControllerImpl;->yh:Ljava/lang/CharSequence;

    .line 389
    iput-object p4, p0, Lcom/miui/internal/app/AlertControllerImpl;->yi:Landroid/os/Message;

    goto :goto_0

    .line 376
    :pswitch_data_0
    .packed-switch -0x3
        :pswitch_2
        :pswitch_1
        :pswitch_0
    .end packed-switch
.end method

.method public setCheckBox(ZLjava/lang/CharSequence;)V
    .locals 0

    .prologue
    .line 367
    iput-boolean p1, p0, Lcom/miui/internal/app/AlertControllerImpl;->mIsChecked:Z

    .line 368
    iput-object p2, p0, Lcom/miui/internal/app/AlertControllerImpl;->mCheckBoxMessage:Ljava/lang/CharSequence;

    .line 369
    return-void
.end method

.method public setCheckedItem(I)V
    .locals 0

    .prologue
    .line 468
    iput p1, p0, Lcom/miui/internal/app/AlertControllerImpl;->mCheckedItem:I

    .line 469
    return-void
.end method

.method public setCheckedItems([Z)V
    .locals 0

    .prologue
    .line 431
    iput-object p1, p0, Lcom/miui/internal/app/AlertControllerImpl;->mCheckedItems:[Z

    .line 432
    return-void
.end method

.method public setCustomTitle(Landroid/view/View;)V
    .locals 0

    .prologue
    .line 352
    iput-object p1, p0, Lcom/miui/internal/app/AlertControllerImpl;->mCustomTitleView:Landroid/view/View;

    .line 353
    return-void
.end method

.method public setListView(Landroid/widget/ListView;)V
    .locals 0

    .prologue
    .line 407
    iput-object p1, p0, Lcom/miui/internal/app/AlertControllerImpl;->xX:Landroid/widget/ListView;

    .line 408
    return-void
.end method

.method public setMessage(Ljava/lang/CharSequence;)V
    .locals 1

    .prologue
    .line 356
    iput-object p1, p0, Lcom/miui/internal/app/AlertControllerImpl;->mMessage:Ljava/lang/CharSequence;

    .line 357
    iget-object v0, p0, Lcom/miui/internal/app/AlertControllerImpl;->lM:Landroid/widget/TextView;

    if-eqz v0, :cond_0

    .line 358
    iget-object v0, p0, Lcom/miui/internal/app/AlertControllerImpl;->lM:Landroid/widget/TextView;

    invoke-virtual {v0, p1}, Landroid/widget/TextView;->setText(Ljava/lang/CharSequence;)V

    .line 360
    :cond_0
    return-void
.end method

.method public setTitle(Ljava/lang/CharSequence;)V
    .locals 1

    .prologue
    .line 345
    iput-object p1, p0, Lcom/miui/internal/app/AlertControllerImpl;->mTitle:Ljava/lang/CharSequence;

    .line 346
    iget-object v0, p0, Lcom/miui/internal/app/AlertControllerImpl;->hJ:Landroid/widget/TextView;

    if-eqz v0, :cond_0

    .line 347
    iget-object v0, p0, Lcom/miui/internal/app/AlertControllerImpl;->hJ:Landroid/widget/TextView;

    invoke-virtual {v0, p1}, Landroid/widget/TextView;->setText(Ljava/lang/CharSequence;)V

    .line 349
    :cond_0
    return-void
.end method

.method public setView(Landroid/view/View;)V
    .locals 0

    .prologue
    .line 363
    iput-object p1, p0, Lcom/miui/internal/app/AlertControllerImpl;->mView:Landroid/view/View;

    .line 364
    return-void
.end method
