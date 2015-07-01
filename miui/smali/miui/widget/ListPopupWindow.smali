.class public Lmiui/widget/ListPopupWindow;
.super Ljava/lang/Object;
.source "SourceFile"


# annotations
.annotation system Ldalvik/annotation/MemberClasses;
    value = {
        Lmiui/widget/ListPopupWindow$a;,
        Lmiui/widget/ListPopupWindow$c;,
        Lmiui/widget/ListPopupWindow$f;,
        Lmiui/widget/ListPopupWindow$d;,
        Lmiui/widget/ListPopupWindow$b;,
        Lmiui/widget/ListPopupWindow$e;
    }
.end annotation


# static fields
.field private static final DEBUG:Z = false

.field public static final INPUT_METHOD_FROM_FOCUSABLE:I = 0x0

.field public static final INPUT_METHOD_NEEDED:I = 0x1

.field public static final INPUT_METHOD_NOT_NEEDED:I = 0x2

.field public static final MATCH_PARENT:I = -0x1

.field public static final POSITION_PROMPT_ABOVE:I = 0x0

.field public static final POSITION_PROMPT_BELOW:I = 0x1

.field private static final Se:I = 0xfa

.field private static final TAG:Ljava/lang/String; = "ListPopupWindow"

.field public static final WRAP_CONTENT:I = -0x2


# instance fields
.field private SF:Ljava/lang/Runnable;

.field private SJ:Z

.field private final Sf:Lmiui/widget/ListPopupWindow$f;

.field private final Sg:Lmiui/widget/ListPopupWindow$c;

.field private final Sh:Lmiui/widget/ListPopupWindow$a;

.field private final Si:Lmiui/widget/ListPopupWindow$d;

.field Sj:I

.field private Sk:Lmiui/widget/ArrowPopupWindow;

.field private Sl:Lmiui/widget/ListPopupWindow$e;

.field private Sm:I

.field private Sn:I

.field private So:I

.field private Sp:I

.field private Sq:Z

.field private Sr:Z

.field private Ss:Z

.field private St:Landroid/view/View;

.field private Su:I

.field private Sv:Landroid/database/DataSetObserver;

.field private Sw:Landroid/view/View;

.field private Sx:Landroid/graphics/drawable/Drawable;

.field private Sy:Landroid/widget/AdapterView$OnItemClickListener;

.field private Sz:Landroid/widget/AdapterView$OnItemSelectedListener;

.field private mAdapter:Landroid/widget/ListAdapter;

.field private mContext:Landroid/content/Context;

.field private mHandler:Landroid/os/Handler;

.field private wY:Landroid/graphics/Rect;


# direct methods
.method public constructor <init>(Landroid/content/Context;)V
    .locals 2

    .prologue
    .line 164
    const/4 v0, 0x0

    const v1, 0x10102ff

    invoke-direct {p0, p1, v0, v1}, Lmiui/widget/ListPopupWindow;-><init>(Landroid/content/Context;Landroid/util/AttributeSet;I)V

    .line 165
    return-void
.end method

.method public constructor <init>(Landroid/content/Context;Landroid/util/AttributeSet;)V
    .locals 1

    .prologue
    .line 175
    const v0, 0x10102ff

    invoke-direct {p0, p1, p2, v0}, Lmiui/widget/ListPopupWindow;-><init>(Landroid/content/Context;Landroid/util/AttributeSet;I)V

    .line 176
    return-void
.end method

.method public constructor <init>(Landroid/content/Context;Landroid/util/AttributeSet;I)V
    .locals 4

    .prologue
    const/4 v3, -0x2

    const/4 v2, 0x0

    const/4 v1, 0x0

    .line 186
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    .line 103
    new-instance v0, Lmiui/widget/ListPopupWindow$f;

    invoke-direct {v0, p0, v1}, Lmiui/widget/ListPopupWindow$f;-><init>(Lmiui/widget/ListPopupWindow;Lmiui/widget/ListPopupWindow$2;)V

    iput-object v0, p0, Lmiui/widget/ListPopupWindow;->Sf:Lmiui/widget/ListPopupWindow$f;

    .line 105
    new-instance v0, Lmiui/widget/ListPopupWindow$c;

    invoke-direct {v0, p0, v1}, Lmiui/widget/ListPopupWindow$c;-><init>(Lmiui/widget/ListPopupWindow;Lmiui/widget/ListPopupWindow$2;)V

    iput-object v0, p0, Lmiui/widget/ListPopupWindow;->Sg:Lmiui/widget/ListPopupWindow$c;

    .line 107
    new-instance v0, Lmiui/widget/ListPopupWindow$a;

    invoke-direct {v0, p0, v1}, Lmiui/widget/ListPopupWindow$a;-><init>(Lmiui/widget/ListPopupWindow;Lmiui/widget/ListPopupWindow$2;)V

    iput-object v0, p0, Lmiui/widget/ListPopupWindow;->Sh:Lmiui/widget/ListPopupWindow$a;

    .line 109
    new-instance v0, Lmiui/widget/ListPopupWindow$d;

    invoke-direct {v0, p0, v1}, Lmiui/widget/ListPopupWindow$d;-><init>(Lmiui/widget/ListPopupWindow;Lmiui/widget/ListPopupWindow$2;)V

    iput-object v0, p0, Lmiui/widget/ListPopupWindow;->Si:Lmiui/widget/ListPopupWindow$d;

    .line 111
    const v0, 0x7fffffff

    iput v0, p0, Lmiui/widget/ListPopupWindow;->Sj:I

    .line 121
    iput v3, p0, Lmiui/widget/ListPopupWindow;->Sm:I

    .line 123
    iput v3, p0, Lmiui/widget/ListPopupWindow;->Sn:I

    .line 131
    iput-boolean v2, p0, Lmiui/widget/ListPopupWindow;->Sr:Z

    .line 133
    iput-boolean v2, p0, Lmiui/widget/ListPopupWindow;->Ss:Z

    .line 137
    iput v2, p0, Lmiui/widget/ListPopupWindow;->Su:I

    .line 151
    new-instance v0, Landroid/os/Handler;

    invoke-direct {v0}, Landroid/os/Handler;-><init>()V

    iput-object v0, p0, Lmiui/widget/ListPopupWindow;->mHandler:Landroid/os/Handler;

    .line 153
    new-instance v0, Landroid/graphics/Rect;

    invoke-direct {v0}, Landroid/graphics/Rect;-><init>()V

    iput-object v0, p0, Lmiui/widget/ListPopupWindow;->wY:Landroid/graphics/Rect;

    .line 187
    iput-object p1, p0, Lmiui/widget/ListPopupWindow;->mContext:Landroid/content/Context;

    .line 188
    new-instance v0, Lmiui/widget/ArrowPopupWindow;

    invoke-direct {v0, p1, p2, p3}, Lmiui/widget/ArrowPopupWindow;-><init>(Landroid/content/Context;Landroid/util/AttributeSet;I)V

    iput-object v0, p0, Lmiui/widget/ListPopupWindow;->Sk:Lmiui/widget/ArrowPopupWindow;

    .line 189
    return-void
.end method

.method static synthetic a(Lmiui/widget/ListPopupWindow;)Lmiui/widget/ListPopupWindow$e;
    .locals 1

    .prologue
    .line 38
    iget-object v0, p0, Lmiui/widget/ListPopupWindow;->Sl:Lmiui/widget/ListPopupWindow$e;

    return-object v0
.end method

.method static synthetic b(Lmiui/widget/ListPopupWindow;)Lmiui/widget/ArrowPopupWindow;
    .locals 1

    .prologue
    .line 38
    iget-object v0, p0, Lmiui/widget/ListPopupWindow;->Sk:Lmiui/widget/ArrowPopupWindow;

    return-object v0
.end method

.method static synthetic c(Lmiui/widget/ListPopupWindow;)Lmiui/widget/ListPopupWindow$f;
    .locals 1

    .prologue
    .line 38
    iget-object v0, p0, Lmiui/widget/ListPopupWindow;->Sf:Lmiui/widget/ListPopupWindow$f;

    return-object v0
.end method

.method static synthetic d(Lmiui/widget/ListPopupWindow;)Landroid/os/Handler;
    .locals 1

    .prologue
    .line 38
    iget-object v0, p0, Lmiui/widget/ListPopupWindow;->mHandler:Landroid/os/Handler;

    return-object v0
.end method

.method private eX()V
    .locals 2

    .prologue
    .line 598
    iget-object v0, p0, Lmiui/widget/ListPopupWindow;->St:Landroid/view/View;

    if-eqz v0, :cond_0

    .line 599
    iget-object v0, p0, Lmiui/widget/ListPopupWindow;->St:Landroid/view/View;

    invoke-virtual {v0}, Landroid/view/View;->getParent()Landroid/view/ViewParent;

    move-result-object v0

    .line 600
    instance-of v1, v0, Landroid/view/ViewGroup;

    if-eqz v1, :cond_0

    .line 601
    check-cast v0, Landroid/view/ViewGroup;

    .line 602
    iget-object v1, p0, Lmiui/widget/ListPopupWindow;->St:Landroid/view/View;

    invoke-virtual {v0, v1}, Landroid/view/ViewGroup;->removeView(Landroid/view/View;)V

    .line 605
    :cond_0
    return-void
.end method

.method private eY()I
    .locals 10

    .prologue
    const/high16 v9, 0x40000000

    const/high16 v8, -0x80000000

    const/4 v3, -0x1

    const/4 v1, 0x1

    const/4 v2, 0x0

    .line 900
    .line 902
    iget-object v0, p0, Lmiui/widget/ListPopupWindow;->Sl:Lmiui/widget/ListPopupWindow$e;

    if-nez v0, :cond_4

    .line 903
    iget-object v5, p0, Lmiui/widget/ListPopupWindow;->mContext:Landroid/content/Context;

    .line 911
    new-instance v0, Lmiui/widget/ListPopupWindow$2;

    invoke-direct {v0, p0}, Lmiui/widget/ListPopupWindow$2;-><init>(Lmiui/widget/ListPopupWindow;)V

    iput-object v0, p0, Lmiui/widget/ListPopupWindow;->SF:Ljava/lang/Runnable;

    .line 921
    new-instance v4, Lmiui/widget/ListPopupWindow$e;

    iget-boolean v0, p0, Lmiui/widget/ListPopupWindow;->SJ:Z

    if-nez v0, :cond_3

    move v0, v1

    :goto_0
    invoke-direct {v4, v5, v0}, Lmiui/widget/ListPopupWindow$e;-><init>(Landroid/content/Context;Z)V

    iput-object v4, p0, Lmiui/widget/ListPopupWindow;->Sl:Lmiui/widget/ListPopupWindow$e;

    .line 922
    iget-object v0, p0, Lmiui/widget/ListPopupWindow;->Sx:Landroid/graphics/drawable/Drawable;

    if-eqz v0, :cond_0

    .line 923
    iget-object v0, p0, Lmiui/widget/ListPopupWindow;->Sl:Lmiui/widget/ListPopupWindow$e;

    iget-object v4, p0, Lmiui/widget/ListPopupWindow;->Sx:Landroid/graphics/drawable/Drawable;

    invoke-virtual {v0, v4}, Lmiui/widget/ListPopupWindow$e;->setSelector(Landroid/graphics/drawable/Drawable;)V

    .line 925
    :cond_0
    iget-object v0, p0, Lmiui/widget/ListPopupWindow;->Sl:Lmiui/widget/ListPopupWindow$e;

    iget-object v4, p0, Lmiui/widget/ListPopupWindow;->mAdapter:Landroid/widget/ListAdapter;

    invoke-virtual {v0, v4}, Lmiui/widget/ListPopupWindow$e;->setAdapter(Landroid/widget/ListAdapter;)V

    .line 926
    iget-object v0, p0, Lmiui/widget/ListPopupWindow;->Sl:Lmiui/widget/ListPopupWindow$e;

    iget-object v4, p0, Lmiui/widget/ListPopupWindow;->Sy:Landroid/widget/AdapterView$OnItemClickListener;

    invoke-virtual {v0, v4}, Lmiui/widget/ListPopupWindow$e;->setOnItemClickListener(Landroid/widget/AdapterView$OnItemClickListener;)V

    .line 927
    iget-object v0, p0, Lmiui/widget/ListPopupWindow;->Sl:Lmiui/widget/ListPopupWindow$e;

    invoke-virtual {v0, v1}, Lmiui/widget/ListPopupWindow$e;->setFocusable(Z)V

    .line 928
    iget-object v0, p0, Lmiui/widget/ListPopupWindow;->Sl:Lmiui/widget/ListPopupWindow$e;

    invoke-virtual {v0, v1}, Lmiui/widget/ListPopupWindow$e;->setFocusableInTouchMode(Z)V

    .line 929
    iget-object v0, p0, Lmiui/widget/ListPopupWindow;->Sl:Lmiui/widget/ListPopupWindow$e;

    new-instance v4, Lmiui/widget/ListPopupWindow$1;

    invoke-direct {v4, p0}, Lmiui/widget/ListPopupWindow$1;-><init>(Lmiui/widget/ListPopupWindow;)V

    invoke-virtual {v0, v4}, Lmiui/widget/ListPopupWindow$e;->setOnItemSelectedListener(Landroid/widget/AdapterView$OnItemSelectedListener;)V

    .line 945
    iget-object v0, p0, Lmiui/widget/ListPopupWindow;->Sl:Lmiui/widget/ListPopupWindow$e;

    iget-object v4, p0, Lmiui/widget/ListPopupWindow;->Sh:Lmiui/widget/ListPopupWindow$a;

    invoke-virtual {v0, v4}, Lmiui/widget/ListPopupWindow$e;->setOnScrollListener(Landroid/widget/AbsListView$OnScrollListener;)V

    .line 947
    iget-object v0, p0, Lmiui/widget/ListPopupWindow;->Sz:Landroid/widget/AdapterView$OnItemSelectedListener;

    if-eqz v0, :cond_1

    .line 948
    iget-object v0, p0, Lmiui/widget/ListPopupWindow;->Sl:Lmiui/widget/ListPopupWindow$e;

    iget-object v4, p0, Lmiui/widget/ListPopupWindow;->Sz:Landroid/widget/AdapterView$OnItemSelectedListener;

    invoke-virtual {v0, v4}, Lmiui/widget/ListPopupWindow$e;->setOnItemSelectedListener(Landroid/widget/AdapterView$OnItemSelectedListener;)V

    .line 951
    :cond_1
    iget-object v0, p0, Lmiui/widget/ListPopupWindow;->Sl:Lmiui/widget/ListPopupWindow$e;

    .line 953
    iget-object v6, p0, Lmiui/widget/ListPopupWindow;->St:Landroid/view/View;

    .line 954
    if-eqz v6, :cond_b

    .line 957
    new-instance v4, Landroid/widget/LinearLayout;

    invoke-direct {v4, v5}, Landroid/widget/LinearLayout;-><init>(Landroid/content/Context;)V

    .line 958
    invoke-virtual {v4, v1}, Landroid/widget/LinearLayout;->setOrientation(I)V

    .line 960
    new-instance v5, Landroid/widget/LinearLayout$LayoutParams;

    const/high16 v7, 0x3f800000

    invoke-direct {v5, v3, v2, v7}, Landroid/widget/LinearLayout$LayoutParams;-><init>(IIF)V

    .line 962
    iget v7, p0, Lmiui/widget/ListPopupWindow;->Su:I

    packed-switch v7, :pswitch_data_0

    .line 974
    const-string v0, "ListPopupWindow"

    new-instance v5, Ljava/lang/StringBuilder;

    invoke-direct {v5}, Ljava/lang/StringBuilder;-><init>()V

    const-string v7, "Invalid hint position "

    invoke-virtual {v5, v7}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v5

    iget v7, p0, Lmiui/widget/ListPopupWindow;->Su:I

    invoke-virtual {v5, v7}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    move-result-object v5

    invoke-virtual {v5}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v5

    invoke-static {v0, v5}, Landroid/util/Log;->e(Ljava/lang/String;Ljava/lang/String;)I

    .line 980
    :goto_1
    iget v0, p0, Lmiui/widget/ListPopupWindow;->Sn:I

    invoke-static {v0, v8}, Landroid/view/View$MeasureSpec;->makeMeasureSpec(II)I

    move-result v0

    .line 982
    invoke-virtual {v6, v0, v2}, Landroid/view/View;->measure(II)V

    .line 984
    invoke-virtual {v6}, Landroid/view/View;->getLayoutParams()Landroid/view/ViewGroup$LayoutParams;

    move-result-object v0

    check-cast v0, Landroid/widget/LinearLayout$LayoutParams;

    .line 985
    invoke-virtual {v6}, Landroid/view/View;->getMeasuredHeight()I

    move-result v5

    iget v6, v0, Landroid/widget/LinearLayout$LayoutParams;->topMargin:I

    add-int/2addr v5, v6

    iget v0, v0, Landroid/widget/LinearLayout$LayoutParams;->bottomMargin:I

    add-int/2addr v0, v5

    .line 991
    :goto_2
    iget-object v5, p0, Lmiui/widget/ListPopupWindow;->Sk:Lmiui/widget/ArrowPopupWindow;

    invoke-virtual {v5, v4}, Lmiui/widget/ArrowPopupWindow;->setContentView(Landroid/view/View;)V

    move v6, v0

    .line 1005
    :goto_3
    iget-object v0, p0, Lmiui/widget/ListPopupWindow;->Sk:Lmiui/widget/ArrowPopupWindow;

    invoke-virtual {v0}, Lmiui/widget/ArrowPopupWindow;->getBackground()Landroid/graphics/drawable/Drawable;

    move-result-object v0

    .line 1006
    if-eqz v0, :cond_5

    .line 1007
    iget-object v4, p0, Lmiui/widget/ListPopupWindow;->wY:Landroid/graphics/Rect;

    invoke-virtual {v0, v4}, Landroid/graphics/drawable/Drawable;->getPadding(Landroid/graphics/Rect;)Z

    .line 1008
    iget-object v0, p0, Lmiui/widget/ListPopupWindow;->wY:Landroid/graphics/Rect;

    iget v0, v0, Landroid/graphics/Rect;->top:I

    iget-object v4, p0, Lmiui/widget/ListPopupWindow;->wY:Landroid/graphics/Rect;

    iget v4, v4, Landroid/graphics/Rect;->bottom:I

    add-int/2addr v0, v4

    .line 1012
    iget-boolean v4, p0, Lmiui/widget/ListPopupWindow;->Sq:Z

    if-nez v4, :cond_9

    .line 1013
    iget-object v4, p0, Lmiui/widget/ListPopupWindow;->wY:Landroid/graphics/Rect;

    iget v4, v4, Landroid/graphics/Rect;->top:I

    neg-int v4, v4

    iput v4, p0, Lmiui/widget/ListPopupWindow;->Sp:I

    move v7, v0

    .line 1020
    :goto_4
    iget-object v0, p0, Lmiui/widget/ListPopupWindow;->Sk:Lmiui/widget/ArrowPopupWindow;

    invoke-virtual {v0}, Lmiui/widget/ArrowPopupWindow;->getInputMethodMode()I

    move-result v0

    const/4 v4, 0x2

    if-ne v0, v4, :cond_6

    .line 1022
    :goto_5
    invoke-virtual {p0}, Lmiui/widget/ListPopupWindow;->getAnchorView()Landroid/view/View;

    move-result-object v0

    iget v4, p0, Lmiui/widget/ListPopupWindow;->Sp:I

    invoke-virtual {p0, v0, v4, v1}, Lmiui/widget/ListPopupWindow;->getMaxAvailableHeight(Landroid/view/View;IZ)I

    move-result v4

    .line 1025
    iget-boolean v0, p0, Lmiui/widget/ListPopupWindow;->Sr:Z

    if-nez v0, :cond_2

    iget v0, p0, Lmiui/widget/ListPopupWindow;->Sm:I

    if-ne v0, v3, :cond_7

    .line 1026
    :cond_2
    add-int v0, v4, v7

    .line 1056
    :goto_6
    return v0

    :cond_3
    move v0, v2

    .line 921
    goto/16 :goto_0

    .line 964
    :pswitch_0
    invoke-virtual {v4, v0, v5}, Landroid/widget/LinearLayout;->addView(Landroid/view/View;Landroid/view/ViewGroup$LayoutParams;)V

    .line 965
    invoke-virtual {v4, v6}, Landroid/widget/LinearLayout;->addView(Landroid/view/View;)V

    goto :goto_1

    .line 969
    :pswitch_1
    invoke-virtual {v4, v6}, Landroid/widget/LinearLayout;->addView(Landroid/view/View;)V

    .line 970
    invoke-virtual {v4, v0, v5}, Landroid/widget/LinearLayout;->addView(Landroid/view/View;Landroid/view/ViewGroup$LayoutParams;)V

    goto :goto_1

    .line 993
    :cond_4
    iget-object v4, p0, Lmiui/widget/ListPopupWindow;->St:Landroid/view/View;

    .line 994
    if-eqz v4, :cond_a

    .line 995
    invoke-virtual {v4}, Landroid/view/View;->getLayoutParams()Landroid/view/ViewGroup$LayoutParams;

    move-result-object v0

    check-cast v0, Landroid/widget/LinearLayout$LayoutParams;

    .line 997
    invoke-virtual {v4}, Landroid/view/View;->getMeasuredHeight()I

    move-result v4

    iget v5, v0, Landroid/widget/LinearLayout$LayoutParams;->topMargin:I

    add-int/2addr v4, v5

    iget v0, v0, Landroid/widget/LinearLayout$LayoutParams;->bottomMargin:I

    add-int/2addr v0, v4

    move v6, v0

    goto :goto_3

    .line 1016
    :cond_5
    iget-object v0, p0, Lmiui/widget/ListPopupWindow;->wY:Landroid/graphics/Rect;

    invoke-virtual {v0}, Landroid/graphics/Rect;->setEmpty()V

    move v7, v2

    goto :goto_4

    :cond_6
    move v1, v2

    .line 1020
    goto :goto_5

    .line 1030
    :cond_7
    iget v0, p0, Lmiui/widget/ListPopupWindow;->Sn:I

    packed-switch v0, :pswitch_data_1

    .line 1044
    iget v0, p0, Lmiui/widget/ListPopupWindow;->Sn:I

    invoke-static {v0, v9}, Landroid/view/View$MeasureSpec;->makeMeasureSpec(II)I

    move-result v1

    .line 1048
    :goto_7
    iget-object v0, p0, Lmiui/widget/ListPopupWindow;->Sl:Lmiui/widget/ListPopupWindow$e;

    sub-int/2addr v4, v6

    move v5, v3

    invoke-virtual/range {v0 .. v5}, Lmiui/widget/ListPopupWindow$e;->a(IIIII)I

    move-result v0

    .line 1052
    if-lez v0, :cond_8

    .line 1053
    add-int/2addr v6, v7

    .line 1056
    :cond_8
    add-int/2addr v0, v6

    goto :goto_6

    .line 1032
    :pswitch_2
    iget-object v0, p0, Lmiui/widget/ListPopupWindow;->mContext:Landroid/content/Context;

    invoke-virtual {v0}, Landroid/content/Context;->getResources()Landroid/content/res/Resources;

    move-result-object v0

    invoke-virtual {v0}, Landroid/content/res/Resources;->getDisplayMetrics()Landroid/util/DisplayMetrics;

    move-result-object v0

    iget v0, v0, Landroid/util/DisplayMetrics;->widthPixels:I

    iget-object v1, p0, Lmiui/widget/ListPopupWindow;->wY:Landroid/graphics/Rect;

    iget v1, v1, Landroid/graphics/Rect;->left:I

    iget-object v5, p0, Lmiui/widget/ListPopupWindow;->wY:Landroid/graphics/Rect;

    iget v5, v5, Landroid/graphics/Rect;->right:I

    add-int/2addr v1, v5

    sub-int/2addr v0, v1

    invoke-static {v0, v8}, Landroid/view/View$MeasureSpec;->makeMeasureSpec(II)I

    move-result v1

    goto :goto_7

    .line 1038
    :pswitch_3
    iget-object v0, p0, Lmiui/widget/ListPopupWindow;->mContext:Landroid/content/Context;

    invoke-virtual {v0}, Landroid/content/Context;->getResources()Landroid/content/res/Resources;

    move-result-object v0

    invoke-virtual {v0}, Landroid/content/res/Resources;->getDisplayMetrics()Landroid/util/DisplayMetrics;

    move-result-object v0

    iget v0, v0, Landroid/util/DisplayMetrics;->widthPixels:I

    iget-object v1, p0, Lmiui/widget/ListPopupWindow;->wY:Landroid/graphics/Rect;

    iget v1, v1, Landroid/graphics/Rect;->left:I

    iget-object v5, p0, Lmiui/widget/ListPopupWindow;->wY:Landroid/graphics/Rect;

    iget v5, v5, Landroid/graphics/Rect;->right:I

    add-int/2addr v1, v5

    sub-int/2addr v0, v1

    invoke-static {v0, v9}, Landroid/view/View$MeasureSpec;->makeMeasureSpec(II)I

    move-result v1

    goto :goto_7

    :cond_9
    move v7, v0

    goto/16 :goto_4

    :cond_a
    move v6, v2

    goto/16 :goto_3

    :cond_b
    move-object v4, v0

    move v0, v2

    goto/16 :goto_2

    .line 962
    :pswitch_data_0
    .packed-switch 0x0
        :pswitch_1
        :pswitch_0
    .end packed-switch

    .line 1030
    :pswitch_data_1
    .packed-switch -0x2
        :pswitch_2
        :pswitch_3
    .end packed-switch
.end method


# virtual methods
.method aw(I)V
    .locals 0

    .prologue
    .line 760
    iput p1, p0, Lmiui/widget/ListPopupWindow;->Sj:I

    .line 761
    return-void
.end method

.method public clearListSelection()V
    .locals 2

    .prologue
    .line 651
    iget-object v0, p0, Lmiui/widget/ListPopupWindow;->Sl:Lmiui/widget/ListPopupWindow$e;

    .line 652
    if-eqz v0, :cond_0

    .line 654
    const/4 v1, 0x1

    invoke-static {v0, v1}, Lmiui/widget/ListPopupWindow$e;->a(Lmiui/widget/ListPopupWindow$e;Z)Z

    .line 656
    invoke-virtual {v0}, Lmiui/widget/ListPopupWindow$e;->requestLayout()V

    .line 658
    :cond_0
    return-void
.end method

.method public dismiss()V
    .locals 1

    .prologue
    .line 575
    const/4 v0, 0x1

    invoke-virtual {p0, v0}, Lmiui/widget/ListPopupWindow;->dismiss(Z)V

    .line 576
    return-void
.end method

.method public dismiss(Z)V
    .locals 2

    .prologue
    .line 582
    iget-object v0, p0, Lmiui/widget/ListPopupWindow;->Sk:Lmiui/widget/ArrowPopupWindow;

    invoke-virtual {v0, p1}, Lmiui/widget/ArrowPopupWindow;->dismiss(Z)V

    .line 583
    invoke-direct {p0}, Lmiui/widget/ListPopupWindow;->eX()V

    .line 584
    const/4 v0, 0x0

    iput-object v0, p0, Lmiui/widget/ListPopupWindow;->Sl:Lmiui/widget/ListPopupWindow$e;

    .line 585
    iget-object v0, p0, Lmiui/widget/ListPopupWindow;->mHandler:Landroid/os/Handler;

    iget-object v1, p0, Lmiui/widget/ListPopupWindow;->Sf:Lmiui/widget/ListPopupWindow$f;

    invoke-virtual {v0, v1}, Landroid/os/Handler;->removeCallbacks(Ljava/lang/Runnable;)V

    .line 586
    return-void
.end method

.method public getAnchorView()Landroid/view/View;
    .locals 1

    .prologue
    .line 372
    iget-object v0, p0, Lmiui/widget/ListPopupWindow;->Sw:Landroid/view/View;

    return-object v0
.end method

.method public getAnimationStyle()I
    .locals 1

    .prologue
    .line 354
    iget-object v0, p0, Lmiui/widget/ListPopupWindow;->Sk:Lmiui/widget/ArrowPopupWindow;

    invoke-virtual {v0}, Lmiui/widget/ArrowPopupWindow;->getAnimationStyle()I

    move-result v0

    return v0
.end method

.method public getBackground()Landroid/graphics/drawable/Drawable;
    .locals 1

    .prologue
    .line 336
    iget-object v0, p0, Lmiui/widget/ListPopupWindow;->Sk:Lmiui/widget/ArrowPopupWindow;

    invoke-virtual {v0}, Lmiui/widget/ArrowPopupWindow;->getBackground()Landroid/graphics/drawable/Drawable;

    move-result-object v0

    return-object v0
.end method

.method public getHeight()I
    .locals 1

    .prologue
    .line 458
    iget v0, p0, Lmiui/widget/ListPopupWindow;->Sm:I

    return v0
.end method

.method public getHorizontalOffset()I
    .locals 1

    .prologue
    .line 389
    iget v0, p0, Lmiui/widget/ListPopupWindow;->So:I

    return v0
.end method

.method public getInputMethodMode()I
    .locals 1

    .prologue
    .line 613
    iget-object v0, p0, Lmiui/widget/ListPopupWindow;->Sk:Lmiui/widget/ArrowPopupWindow;

    invoke-virtual {v0}, Lmiui/widget/ArrowPopupWindow;->getInputMethodMode()I

    move-result v0

    return v0
.end method

.method public getListView()Landroid/widget/ListView;
    .locals 1

    .prologue
    .line 750
    iget-object v0, p0, Lmiui/widget/ListPopupWindow;->Sl:Lmiui/widget/ListPopupWindow$e;

    return-object v0
.end method

.method public getMaxAvailableHeight(Landroid/view/View;IZ)I
    .locals 6

    .prologue
    const/4 v5, 0x1

    .line 1077
    new-instance v1, Landroid/graphics/Rect;

    invoke-direct {v1}, Landroid/graphics/Rect;-><init>()V

    .line 1078
    invoke-virtual {p1, v1}, Landroid/view/View;->getWindowVisibleDisplayFrame(Landroid/graphics/Rect;)V

    .line 1080
    const/4 v0, 0x2

    new-array v2, v0, [I

    .line 1081
    invoke-virtual {p1, v2}, Landroid/view/View;->getLocationOnScreen([I)V

    .line 1083
    iget v0, v1, Landroid/graphics/Rect;->bottom:I

    .line 1084
    if-eqz p3, :cond_0

    .line 1085
    invoke-virtual {p1}, Landroid/view/View;->getContext()Landroid/content/Context;

    move-result-object v0

    invoke-virtual {v0}, Landroid/content/Context;->getResources()Landroid/content/res/Resources;

    move-result-object v0

    .line 1086
    invoke-virtual {v0}, Landroid/content/res/Resources;->getDisplayMetrics()Landroid/util/DisplayMetrics;

    move-result-object v0

    iget v0, v0, Landroid/util/DisplayMetrics;->heightPixels:I

    .line 1088
    :cond_0
    aget v3, v2, v5

    invoke-virtual {p1}, Landroid/view/View;->getHeight()I

    move-result v4

    add-int/2addr v3, v4

    sub-int/2addr v0, v3

    sub-int/2addr v0, p2

    .line 1089
    aget v2, v2, v5

    iget v1, v1, Landroid/graphics/Rect;->top:I

    sub-int v1, v2, v1

    add-int/2addr v1, p2

    .line 1092
    invoke-static {v0, v1}, Ljava/lang/Math;->max(II)I

    move-result v0

    .line 1093
    iget-object v1, p0, Lmiui/widget/ListPopupWindow;->Sk:Lmiui/widget/ArrowPopupWindow;

    invoke-virtual {v1}, Lmiui/widget/ArrowPopupWindow;->getBackground()Landroid/graphics/drawable/Drawable;

    move-result-object v1

    if-eqz v1, :cond_1

    .line 1094
    iget-object v1, p0, Lmiui/widget/ListPopupWindow;->Sk:Lmiui/widget/ArrowPopupWindow;

    invoke-virtual {v1}, Lmiui/widget/ArrowPopupWindow;->getBackground()Landroid/graphics/drawable/Drawable;

    move-result-object v1

    iget-object v2, p0, Lmiui/widget/ListPopupWindow;->wY:Landroid/graphics/Rect;

    invoke-virtual {v1, v2}, Landroid/graphics/drawable/Drawable;->getPadding(Landroid/graphics/Rect;)Z

    .line 1095
    iget-object v1, p0, Lmiui/widget/ListPopupWindow;->wY:Landroid/graphics/Rect;

    iget v1, v1, Landroid/graphics/Rect;->top:I

    iget-object v2, p0, Lmiui/widget/ListPopupWindow;->wY:Landroid/graphics/Rect;

    iget v2, v2, Landroid/graphics/Rect;->bottom:I

    add-int/2addr v1, v2

    sub-int/2addr v0, v1

    .line 1098
    :cond_1
    return v0
.end method

.method public getPopupWindow()Lmiui/widget/ArrowPopupWindow;
    .locals 1

    .prologue
    .line 195
    iget-object v0, p0, Lmiui/widget/ListPopupWindow;->Sk:Lmiui/widget/ArrowPopupWindow;

    return-object v0
.end method

.method public getPromptPosition()I
    .locals 1

    .prologue
    .line 227
    iget v0, p0, Lmiui/widget/ListPopupWindow;->Su:I

    return v0
.end method

.method public getSelectedItem()Ljava/lang/Object;
    .locals 1

    .prologue
    .line 700
    invoke-virtual {p0}, Lmiui/widget/ListPopupWindow;->isShowing()Z

    move-result v0

    if-nez v0, :cond_0

    .line 701
    const/4 v0, 0x0

    .line 703
    :goto_0
    return-object v0

    :cond_0
    iget-object v0, p0, Lmiui/widget/ListPopupWindow;->Sl:Lmiui/widget/ListPopupWindow$e;

    invoke-virtual {v0}, Lmiui/widget/ListPopupWindow$e;->getSelectedItem()Ljava/lang/Object;

    move-result-object v0

    goto :goto_0
.end method

.method public getSelectedItemId()J
    .locals 2

    .prologue
    .line 726
    invoke-virtual {p0}, Lmiui/widget/ListPopupWindow;->isShowing()Z

    move-result v0

    if-nez v0, :cond_0

    .line 727
    const-wide/high16 v0, -0x8000000000000000L

    .line 729
    :goto_0
    return-wide v0

    :cond_0
    iget-object v0, p0, Lmiui/widget/ListPopupWindow;->Sl:Lmiui/widget/ListPopupWindow$e;

    invoke-virtual {v0}, Lmiui/widget/ListPopupWindow$e;->getSelectedItemId()J

    move-result-wide v0

    goto :goto_0
.end method

.method public getSelectedItemPosition()I
    .locals 1

    .prologue
    .line 713
    invoke-virtual {p0}, Lmiui/widget/ListPopupWindow;->isShowing()Z

    move-result v0

    if-nez v0, :cond_0

    .line 714
    const/4 v0, -0x1

    .line 716
    :goto_0
    return v0

    :cond_0
    iget-object v0, p0, Lmiui/widget/ListPopupWindow;->Sl:Lmiui/widget/ListPopupWindow$e;

    invoke-virtual {v0}, Lmiui/widget/ListPopupWindow$e;->getSelectedItemPosition()I

    move-result v0

    goto :goto_0
.end method

.method public getSelectedView()Landroid/view/View;
    .locals 1

    .prologue
    .line 739
    invoke-virtual {p0}, Lmiui/widget/ListPopupWindow;->isShowing()Z

    move-result v0

    if-nez v0, :cond_0

    .line 740
    const/4 v0, 0x0

    .line 742
    :goto_0
    return-object v0

    :cond_0
    iget-object v0, p0, Lmiui/widget/ListPopupWindow;->Sl:Lmiui/widget/ListPopupWindow$e;

    invoke-virtual {v0}, Lmiui/widget/ListPopupWindow$e;->getSelectedView()Landroid/view/View;

    move-result-object v0

    goto :goto_0
.end method

.method public getSoftInputMode()I
    .locals 1

    .prologue
    .line 307
    iget-object v0, p0, Lmiui/widget/ListPopupWindow;->Sk:Lmiui/widget/ArrowPopupWindow;

    invoke-virtual {v0}, Lmiui/widget/ArrowPopupWindow;->getSoftInputMode()I

    move-result v0

    return v0
.end method

.method public getVerticalOffset()I
    .locals 1

    .prologue
    .line 405
    iget-boolean v0, p0, Lmiui/widget/ListPopupWindow;->Sq:Z

    if-nez v0, :cond_0

    .line 406
    const/4 v0, 0x0

    .line 408
    :goto_0
    return v0

    :cond_0
    iget v0, p0, Lmiui/widget/ListPopupWindow;->Sp:I

    goto :goto_0
.end method

.method public getWidth()I
    .locals 1

    .prologue
    .line 425
    iget v0, p0, Lmiui/widget/ListPopupWindow;->Sn:I

    return v0
.end method

.method public isDropDownAlwaysVisible()Z
    .locals 1

    .prologue
    .line 282
    iget-boolean v0, p0, Lmiui/widget/ListPopupWindow;->Sr:Z

    return v0
.end method

.method public isInputMethodNotNeeded()Z
    .locals 2

    .prologue
    .line 672
    iget-object v0, p0, Lmiui/widget/ListPopupWindow;->Sk:Lmiui/widget/ArrowPopupWindow;

    invoke-virtual {v0}, Lmiui/widget/ArrowPopupWindow;->getInputMethodMode()I

    move-result v0

    const/4 v1, 0x2

    if-ne v0, v1, :cond_0

    const/4 v0, 0x1

    :goto_0
    return v0

    :cond_0
    const/4 v0, 0x0

    goto :goto_0
.end method

.method public isModal()Z
    .locals 1

    .prologue
    .line 249
    iget-boolean v0, p0, Lmiui/widget/ListPopupWindow;->SJ:Z

    return v0
.end method

.method public isShowing()Z
    .locals 1

    .prologue
    .line 664
    iget-object v0, p0, Lmiui/widget/ListPopupWindow;->Sk:Lmiui/widget/ArrowPopupWindow;

    invoke-virtual {v0}, Lmiui/widget/ArrowPopupWindow;->isShowing()Z

    move-result v0

    return v0
.end method

.method public onKeyDown(ILandroid/view/KeyEvent;)Z
    .locals 9

    .prologue
    const/16 v8, 0x14

    const/16 v7, 0x13

    const/4 v2, 0x0

    const/4 v1, 0x1

    .line 776
    invoke-virtual {p0}, Lmiui/widget/ListPopupWindow;->isShowing()Z

    move-result v0

    if-eqz v0, :cond_8

    .line 782
    const/16 v0, 0x3e

    if-eq p1, v0, :cond_8

    iget-object v0, p0, Lmiui/widget/ListPopupWindow;->Sl:Lmiui/widget/ListPopupWindow$e;

    invoke-virtual {v0}, Lmiui/widget/ListPopupWindow$e;->getSelectedItemPosition()I

    move-result v0

    if-gez v0, :cond_0

    const/16 v0, 0x42

    if-eq p1, v0, :cond_8

    const/16 v0, 0x17

    if-eq p1, v0, :cond_8

    .line 786
    :cond_0
    iget-object v0, p0, Lmiui/widget/ListPopupWindow;->Sl:Lmiui/widget/ListPopupWindow$e;

    invoke-virtual {v0}, Lmiui/widget/ListPopupWindow$e;->getSelectedItemPosition()I

    move-result v5

    .line 789
    iget-object v0, p0, Lmiui/widget/ListPopupWindow;->Sk:Lmiui/widget/ArrowPopupWindow;

    invoke-virtual {v0}, Lmiui/widget/ArrowPopupWindow;->isAboveAnchor()Z

    move-result v0

    if-nez v0, :cond_4

    move v0, v1

    .line 791
    :goto_0
    iget-object v6, p0, Lmiui/widget/ListPopupWindow;->mAdapter:Landroid/widget/ListAdapter;

    .line 794
    const v4, 0x7fffffff

    .line 795
    const/high16 v3, -0x80000000

    .line 797
    if-eqz v6, :cond_1

    .line 798
    invoke-interface {v6}, Landroid/widget/ListAdapter;->areAllItemsEnabled()Z

    move-result v3

    .line 799
    if-eqz v3, :cond_5

    move v4, v2

    .line 801
    :goto_1
    if-eqz v3, :cond_6

    invoke-interface {v6}, Landroid/widget/ListAdapter;->getCount()I

    move-result v3

    add-int/lit8 v3, v3, -0x1

    .line 805
    :cond_1
    :goto_2
    if-eqz v0, :cond_2

    if-ne p1, v7, :cond_2

    if-le v5, v4, :cond_3

    :cond_2
    if-nez v0, :cond_7

    if-ne p1, v8, :cond_7

    if-lt v5, v3, :cond_7

    .line 809
    :cond_3
    invoke-virtual {p0}, Lmiui/widget/ListPopupWindow;->clearListSelection()V

    .line 810
    iget-object v0, p0, Lmiui/widget/ListPopupWindow;->Sk:Lmiui/widget/ArrowPopupWindow;

    invoke-virtual {v0, v1}, Lmiui/widget/ArrowPopupWindow;->setInputMethodMode(I)V

    .line 811
    invoke-virtual {p0}, Lmiui/widget/ListPopupWindow;->show()V

    .line 859
    :goto_3
    :sswitch_0
    return v1

    :cond_4
    move v0, v2

    .line 789
    goto :goto_0

    .line 799
    :cond_5
    iget-object v4, p0, Lmiui/widget/ListPopupWindow;->Sl:Lmiui/widget/ListPopupWindow$e;

    invoke-static {v4, v2, v1}, Lmiui/widget/ListPopupWindow$e;->a(Lmiui/widget/ListPopupWindow$e;IZ)I

    move-result v4

    goto :goto_1

    .line 801
    :cond_6
    iget-object v3, p0, Lmiui/widget/ListPopupWindow;->Sl:Lmiui/widget/ListPopupWindow$e;

    invoke-interface {v6}, Landroid/widget/ListAdapter;->getCount()I

    move-result v6

    add-int/lit8 v6, v6, -0x1

    invoke-static {v3, v6, v2}, Lmiui/widget/ListPopupWindow$e;->a(Lmiui/widget/ListPopupWindow$e;IZ)I

    move-result v3

    goto :goto_2

    .line 816
    :cond_7
    iget-object v6, p0, Lmiui/widget/ListPopupWindow;->Sl:Lmiui/widget/ListPopupWindow$e;

    invoke-static {v6, v2}, Lmiui/widget/ListPopupWindow$e;->a(Lmiui/widget/ListPopupWindow$e;Z)Z

    .line 819
    iget-object v6, p0, Lmiui/widget/ListPopupWindow;->Sl:Lmiui/widget/ListPopupWindow$e;

    invoke-virtual {v6, p1, p2}, Lmiui/widget/ListPopupWindow$e;->onKeyDown(ILandroid/view/KeyEvent;)Z

    move-result v6

    .line 824
    if-eqz v6, :cond_9

    .line 827
    iget-object v0, p0, Lmiui/widget/ListPopupWindow;->Sk:Lmiui/widget/ArrowPopupWindow;

    const/4 v3, 0x2

    invoke-virtual {v0, v3}, Lmiui/widget/ArrowPopupWindow;->setInputMethodMode(I)V

    .line 832
    iget-object v0, p0, Lmiui/widget/ListPopupWindow;->Sl:Lmiui/widget/ListPopupWindow$e;

    invoke-virtual {v0}, Lmiui/widget/ListPopupWindow$e;->requestFocusFromTouch()Z

    .line 833
    invoke-virtual {p0}, Lmiui/widget/ListPopupWindow;->show()V

    .line 835
    sparse-switch p1, :sswitch_data_0

    :cond_8
    move v1, v2

    .line 859
    goto :goto_3

    .line 845
    :cond_9
    if-eqz v0, :cond_a

    if-ne p1, v8, :cond_a

    .line 848
    if-ne v5, v3, :cond_8

    goto :goto_3

    .line 851
    :cond_a
    if-nez v0, :cond_8

    if-ne p1, v7, :cond_8

    if-ne v5, v4, :cond_8

    goto :goto_3

    .line 835
    nop

    :sswitch_data_0
    .sparse-switch
        0x13 -> :sswitch_0
        0x14 -> :sswitch_0
        0x17 -> :sswitch_0
        0x42 -> :sswitch_0
    .end sparse-switch
.end method

.method public onKeyUp(ILandroid/view/KeyEvent;)Z
    .locals 2

    .prologue
    .line 874
    invoke-virtual {p0}, Lmiui/widget/ListPopupWindow;->isShowing()Z

    move-result v0

    if-eqz v0, :cond_1

    iget-object v0, p0, Lmiui/widget/ListPopupWindow;->Sl:Lmiui/widget/ListPopupWindow$e;

    invoke-virtual {v0}, Lmiui/widget/ListPopupWindow$e;->getSelectedItemPosition()I

    move-result v0

    if-ltz v0, :cond_1

    .line 875
    iget-object v0, p0, Lmiui/widget/ListPopupWindow;->Sl:Lmiui/widget/ListPopupWindow$e;

    invoke-virtual {v0, p1, p2}, Lmiui/widget/ListPopupWindow$e;->onKeyUp(ILandroid/view/KeyEvent;)Z

    move-result v0

    .line 876
    if-eqz v0, :cond_0

    .line 877
    sparse-switch p1, :sswitch_data_0

    .line 889
    :cond_0
    :goto_0
    return v0

    .line 883
    :sswitch_0
    const/4 v1, 0x1

    invoke-virtual {p0, v1}, Lmiui/widget/ListPopupWindow;->dismiss(Z)V

    goto :goto_0

    .line 889
    :cond_1
    const/4 v0, 0x0

    goto :goto_0

    .line 877
    nop

    :sswitch_data_0
    .sparse-switch
        0x17 -> :sswitch_0
        0x42 -> :sswitch_0
    .end sparse-switch
.end method

.method public performItemClick(I)Z
    .locals 6

    .prologue
    .line 684
    invoke-virtual {p0}, Lmiui/widget/ListPopupWindow;->isShowing()Z

    move-result v0

    if-eqz v0, :cond_1

    .line 685
    iget-object v0, p0, Lmiui/widget/ListPopupWindow;->Sy:Landroid/widget/AdapterView$OnItemClickListener;

    if-eqz v0, :cond_0

    .line 686
    iget-object v1, p0, Lmiui/widget/ListPopupWindow;->Sl:Lmiui/widget/ListPopupWindow$e;

    .line 687
    invoke-virtual {v1}, Lmiui/widget/ListPopupWindow$e;->getFirstVisiblePosition()I

    move-result v0

    sub-int v0, p1, v0

    invoke-virtual {v1, v0}, Lmiui/widget/ListPopupWindow$e;->getChildAt(I)Landroid/view/View;

    move-result-object v2

    .line 688
    invoke-virtual {v1}, Lmiui/widget/ListPopupWindow$e;->getAdapter()Landroid/widget/ListAdapter;

    move-result-object v3

    .line 689
    iget-object v0, p0, Lmiui/widget/ListPopupWindow;->Sy:Landroid/widget/AdapterView$OnItemClickListener;

    invoke-interface {v3, p1}, Landroid/widget/ListAdapter;->getItemId(I)J

    move-result-wide v4

    move v3, p1

    invoke-interface/range {v0 .. v5}, Landroid/widget/AdapterView$OnItemClickListener;->onItemClick(Landroid/widget/AdapterView;Landroid/view/View;IJ)V

    .line 691
    :cond_0
    const/4 v0, 0x1

    .line 693
    :goto_0
    return v0

    :cond_1
    const/4 v0, 0x0

    goto :goto_0
.end method

.method public postShow()V
    .locals 2

    .prologue
    .line 513
    iget-object v0, p0, Lmiui/widget/ListPopupWindow;->mHandler:Landroid/os/Handler;

    iget-object v1, p0, Lmiui/widget/ListPopupWindow;->SF:Ljava/lang/Runnable;

    invoke-virtual {v0, v1}, Landroid/os/Handler;->post(Ljava/lang/Runnable;)Z

    .line 514
    return-void
.end method

.method public setAdapter(Landroid/widget/ListAdapter;)V
    .locals 2

    .prologue
    .line 205
    iget-object v0, p0, Lmiui/widget/ListPopupWindow;->Sv:Landroid/database/DataSetObserver;

    if-nez v0, :cond_3

    .line 206
    new-instance v0, Lmiui/widget/ListPopupWindow$b;

    const/4 v1, 0x0

    invoke-direct {v0, p0, v1}, Lmiui/widget/ListPopupWindow$b;-><init>(Lmiui/widget/ListPopupWindow;Lmiui/widget/ListPopupWindow$2;)V

    iput-object v0, p0, Lmiui/widget/ListPopupWindow;->Sv:Landroid/database/DataSetObserver;

    .line 210
    :cond_0
    :goto_0
    iput-object p1, p0, Lmiui/widget/ListPopupWindow;->mAdapter:Landroid/widget/ListAdapter;

    .line 211
    iget-object v0, p0, Lmiui/widget/ListPopupWindow;->mAdapter:Landroid/widget/ListAdapter;

    if-eqz v0, :cond_1

    .line 212
    iget-object v0, p0, Lmiui/widget/ListPopupWindow;->Sv:Landroid/database/DataSetObserver;

    invoke-interface {p1, v0}, Landroid/widget/ListAdapter;->registerDataSetObserver(Landroid/database/DataSetObserver;)V

    .line 215
    :cond_1
    iget-object v0, p0, Lmiui/widget/ListPopupWindow;->Sl:Lmiui/widget/ListPopupWindow$e;

    if-eqz v0, :cond_2

    .line 216
    iget-object v0, p0, Lmiui/widget/ListPopupWindow;->Sl:Lmiui/widget/ListPopupWindow$e;

    iget-object v1, p0, Lmiui/widget/ListPopupWindow;->mAdapter:Landroid/widget/ListAdapter;

    invoke-virtual {v0, v1}, Lmiui/widget/ListPopupWindow$e;->setAdapter(Landroid/widget/ListAdapter;)V

    .line 218
    :cond_2
    return-void

    .line 207
    :cond_3
    iget-object v0, p0, Lmiui/widget/ListPopupWindow;->mAdapter:Landroid/widget/ListAdapter;

    if-eqz v0, :cond_0

    .line 208
    iget-object v0, p0, Lmiui/widget/ListPopupWindow;->mAdapter:Landroid/widget/ListAdapter;

    iget-object v1, p0, Lmiui/widget/ListPopupWindow;->Sv:Landroid/database/DataSetObserver;

    invoke-interface {v0, v1}, Landroid/widget/ListAdapter;->unregisterDataSetObserver(Landroid/database/DataSetObserver;)V

    goto :goto_0
.end method

.method public setAnchorView(Landroid/view/View;)V
    .locals 0

    .prologue
    .line 382
    iput-object p1, p0, Lmiui/widget/ListPopupWindow;->Sw:Landroid/view/View;

    .line 383
    return-void
.end method

.method public setAnimationStyle(I)V
    .locals 1

    .prologue
    .line 363
    iget-object v0, p0, Lmiui/widget/ListPopupWindow;->Sk:Lmiui/widget/ArrowPopupWindow;

    invoke-virtual {v0, p1}, Lmiui/widget/ArrowPopupWindow;->setAnimationStyle(I)V

    .line 364
    return-void
.end method

.method public setBackgroundDrawable(Landroid/graphics/drawable/Drawable;)V
    .locals 1

    .prologue
    .line 345
    iget-object v0, p0, Lmiui/widget/ListPopupWindow;->Sk:Lmiui/widget/ArrowPopupWindow;

    invoke-virtual {v0, p1}, Lmiui/widget/ArrowPopupWindow;->setBackgroundDrawable(Landroid/graphics/drawable/Drawable;)V

    .line 346
    return-void
.end method

.method public setContentWidth(I)V
    .locals 2

    .prologue
    .line 445
    iget-object v0, p0, Lmiui/widget/ListPopupWindow;->Sk:Lmiui/widget/ArrowPopupWindow;

    invoke-virtual {v0}, Lmiui/widget/ArrowPopupWindow;->getBackground()Landroid/graphics/drawable/Drawable;

    move-result-object v0

    .line 446
    if-eqz v0, :cond_0

    .line 447
    iget-object v1, p0, Lmiui/widget/ListPopupWindow;->wY:Landroid/graphics/Rect;

    invoke-virtual {v0, v1}, Landroid/graphics/drawable/Drawable;->getPadding(Landroid/graphics/Rect;)Z

    .line 448
    iget-object v0, p0, Lmiui/widget/ListPopupWindow;->wY:Landroid/graphics/Rect;

    iget v0, v0, Landroid/graphics/Rect;->left:I

    iget-object v1, p0, Lmiui/widget/ListPopupWindow;->wY:Landroid/graphics/Rect;

    iget v1, v1, Landroid/graphics/Rect;->right:I

    add-int/2addr v0, v1

    add-int/2addr v0, p1

    iput v0, p0, Lmiui/widget/ListPopupWindow;->Sn:I

    .line 452
    :goto_0
    return-void

    .line 450
    :cond_0
    invoke-virtual {p0, p1}, Lmiui/widget/ListPopupWindow;->setWidth(I)V

    goto :goto_0
.end method

.method public setDropDownAlwaysVisible(Z)V
    .locals 0

    .prologue
    .line 297
    iput-boolean p1, p0, Lmiui/widget/ListPopupWindow;->Sr:Z

    .line 298
    return-void
.end method

.method public setForceIgnoreOutsideTouch(Z)V
    .locals 0

    .prologue
    .line 273
    iput-boolean p1, p0, Lmiui/widget/ListPopupWindow;->Ss:Z

    .line 274
    return-void
.end method

.method public setHeight(I)V
    .locals 0

    .prologue
    .line 467
    iput p1, p0, Lmiui/widget/ListPopupWindow;->Sm:I

    .line 468
    return-void
.end method

.method public setHorizontalOffset(I)V
    .locals 0

    .prologue
    .line 398
    iput p1, p0, Lmiui/widget/ListPopupWindow;->So:I

    .line 399
    return-void
.end method

.method public setInputMethodMode(I)V
    .locals 1

    .prologue
    .line 628
    iget-object v0, p0, Lmiui/widget/ListPopupWindow;->Sk:Lmiui/widget/ArrowPopupWindow;

    invoke-virtual {v0, p1}, Lmiui/widget/ArrowPopupWindow;->setInputMethodMode(I)V

    .line 629
    return-void
.end method

.method public setListSelector(Landroid/graphics/drawable/Drawable;)V
    .locals 0

    .prologue
    .line 329
    iput-object p1, p0, Lmiui/widget/ListPopupWindow;->Sx:Landroid/graphics/drawable/Drawable;

    .line 330
    return-void
.end method

.method public setModal(Z)V
    .locals 1

    .prologue
    .line 261
    const/4 v0, 0x1

    iput-boolean v0, p0, Lmiui/widget/ListPopupWindow;->SJ:Z

    .line 262
    iget-object v0, p0, Lmiui/widget/ListPopupWindow;->Sk:Lmiui/widget/ArrowPopupWindow;

    invoke-virtual {v0, p1}, Lmiui/widget/ArrowPopupWindow;->setFocusable(Z)V

    .line 263
    return-void
.end method

.method public setOnDismissListener(Landroid/widget/PopupWindow$OnDismissListener;)V
    .locals 1

    .prologue
    .line 594
    iget-object v0, p0, Lmiui/widget/ListPopupWindow;->Sk:Lmiui/widget/ArrowPopupWindow;

    invoke-virtual {v0, p1}, Lmiui/widget/ArrowPopupWindow;->setOnDismissListener(Landroid/widget/PopupWindow$OnDismissListener;)V

    .line 595
    return-void
.end method

.method public setOnItemClickListener(Landroid/widget/AdapterView$OnItemClickListener;)V
    .locals 0

    .prologue
    .line 478
    iput-object p1, p0, Lmiui/widget/ListPopupWindow;->Sy:Landroid/widget/AdapterView$OnItemClickListener;

    .line 479
    return-void
.end method

.method public setOnItemSelectedListener(Landroid/widget/AdapterView$OnItemSelectedListener;)V
    .locals 0

    .prologue
    .line 489
    iput-object p1, p0, Lmiui/widget/ListPopupWindow;->Sz:Landroid/widget/AdapterView$OnItemSelectedListener;

    .line 490
    return-void
.end method

.method public setPromptPosition(I)V
    .locals 0

    .prologue
    .line 240
    iput p1, p0, Lmiui/widget/ListPopupWindow;->Su:I

    .line 241
    return-void
.end method

.method public setPromptView(Landroid/view/View;)V
    .locals 1

    .prologue
    .line 499
    invoke-virtual {p0}, Lmiui/widget/ListPopupWindow;->isShowing()Z

    move-result v0

    .line 500
    if-eqz v0, :cond_0

    .line 501
    invoke-direct {p0}, Lmiui/widget/ListPopupWindow;->eX()V

    .line 503
    :cond_0
    iput-object p1, p0, Lmiui/widget/ListPopupWindow;->St:Landroid/view/View;

    .line 504
    if-eqz v0, :cond_1

    .line 505
    invoke-virtual {p0}, Lmiui/widget/ListPopupWindow;->show()V

    .line 507
    :cond_1
    return-void
.end method

.method public setSelection(I)V
    .locals 2

    .prologue
    .line 637
    iget-object v0, p0, Lmiui/widget/ListPopupWindow;->Sl:Lmiui/widget/ListPopupWindow$e;

    .line 638
    invoke-virtual {p0}, Lmiui/widget/ListPopupWindow;->isShowing()Z

    move-result v1

    if-eqz v1, :cond_0

    if-eqz v0, :cond_0

    .line 639
    const/4 v1, 0x0

    invoke-static {v0, v1}, Lmiui/widget/ListPopupWindow$e;->a(Lmiui/widget/ListPopupWindow$e;Z)Z

    .line 640
    invoke-virtual {v0, p1}, Lmiui/widget/ListPopupWindow$e;->setSelection(I)V

    .line 641
    invoke-virtual {v0}, Lmiui/widget/ListPopupWindow$e;->getChoiceMode()I

    move-result v1

    if-eqz v1, :cond_0

    .line 642
    const/4 v1, 0x1

    invoke-virtual {v0, p1, v1}, Lmiui/widget/ListPopupWindow$e;->setItemChecked(IZ)V

    .line 645
    :cond_0
    return-void
.end method

.method public setSoftInputMode(I)V
    .locals 1

    .prologue
    .line 320
    iget-object v0, p0, Lmiui/widget/ListPopupWindow;->Sk:Lmiui/widget/ArrowPopupWindow;

    invoke-virtual {v0, p1}, Lmiui/widget/ArrowPopupWindow;->setSoftInputMode(I)V

    .line 321
    return-void
.end method

.method public setVerticalOffset(I)V
    .locals 1

    .prologue
    .line 417
    iput p1, p0, Lmiui/widget/ListPopupWindow;->Sp:I

    .line 418
    const/4 v0, 0x1

    iput-boolean v0, p0, Lmiui/widget/ListPopupWindow;->Sq:Z

    .line 419
    return-void
.end method

.method public setWidth(I)V
    .locals 0

    .prologue
    .line 435
    iput p1, p0, Lmiui/widget/ListPopupWindow;->Sn:I

    .line 436
    return-void
.end method

.method public show()V
    .locals 7

    .prologue
    const/4 v2, 0x1

    const/4 v6, -0x2

    const/4 v1, 0x0

    const/4 v0, -0x1

    .line 521
    invoke-direct {p0}, Lmiui/widget/ListPopupWindow;->eY()I

    move-result v3

    .line 526
    iget v4, p0, Lmiui/widget/ListPopupWindow;->Sn:I

    if-ne v4, v0, :cond_2

    move v4, v0

    .line 536
    :goto_0
    iget v5, p0, Lmiui/widget/ListPopupWindow;->Sm:I

    if-ne v5, v0, :cond_4

    move v5, v0

    .line 546
    :goto_1
    iget-object v3, p0, Lmiui/widget/ListPopupWindow;->Sk:Lmiui/widget/ArrowPopupWindow;

    invoke-virtual {v3}, Lmiui/widget/ArrowPopupWindow;->isShowing()Z

    move-result v3

    if-eqz v3, :cond_6

    .line 547
    iget-object v0, p0, Lmiui/widget/ListPopupWindow;->Sk:Lmiui/widget/ArrowPopupWindow;

    iget-boolean v3, p0, Lmiui/widget/ListPopupWindow;->Ss:Z

    if-nez v3, :cond_0

    iget-boolean v3, p0, Lmiui/widget/ListPopupWindow;->Sr:Z

    if-nez v3, :cond_0

    move v1, v2

    :cond_0
    invoke-virtual {v0, v1}, Lmiui/widget/ArrowPopupWindow;->setOutsideTouchable(Z)V

    .line 549
    iget-object v0, p0, Lmiui/widget/ListPopupWindow;->Sk:Lmiui/widget/ArrowPopupWindow;

    invoke-virtual {p0}, Lmiui/widget/ListPopupWindow;->getAnchorView()Landroid/view/View;

    move-result-object v1

    iget v2, p0, Lmiui/widget/ListPopupWindow;->So:I

    iget v3, p0, Lmiui/widget/ListPopupWindow;->Sp:I

    invoke-virtual/range {v0 .. v5}, Lmiui/widget/ArrowPopupWindow;->update(Landroid/view/View;IIII)V

    .line 569
    :cond_1
    :goto_2
    return-void

    .line 529
    :cond_2
    iget v4, p0, Lmiui/widget/ListPopupWindow;->Sn:I

    if-ne v4, v6, :cond_3

    .line 530
    iget-object v4, p0, Lmiui/widget/ListPopupWindow;->Sk:Lmiui/widget/ArrowPopupWindow;

    invoke-virtual {p0}, Lmiui/widget/ListPopupWindow;->getAnchorView()Landroid/view/View;

    move-result-object v5

    invoke-virtual {v5}, Landroid/view/View;->getWidth()I

    move-result v5

    invoke-virtual {v4, v5}, Lmiui/widget/ArrowPopupWindow;->setWidth(I)V

    move v4, v1

    goto :goto_0

    .line 532
    :cond_3
    iget-object v4, p0, Lmiui/widget/ListPopupWindow;->Sk:Lmiui/widget/ArrowPopupWindow;

    iget v5, p0, Lmiui/widget/ListPopupWindow;->Sn:I

    invoke-virtual {v4, v5}, Lmiui/widget/ArrowPopupWindow;->setWidth(I)V

    move v4, v1

    goto :goto_0

    .line 539
    :cond_4
    iget v5, p0, Lmiui/widget/ListPopupWindow;->Sm:I

    if-ne v5, v6, :cond_5

    .line 540
    iget-object v5, p0, Lmiui/widget/ListPopupWindow;->Sk:Lmiui/widget/ArrowPopupWindow;

    invoke-virtual {v5, v3}, Lmiui/widget/ArrowPopupWindow;->setHeight(I)V

    move v5, v1

    goto :goto_1

    .line 542
    :cond_5
    iget-object v3, p0, Lmiui/widget/ListPopupWindow;->Sk:Lmiui/widget/ArrowPopupWindow;

    iget v5, p0, Lmiui/widget/ListPopupWindow;->Sm:I

    invoke-virtual {v3, v5}, Lmiui/widget/ArrowPopupWindow;->setHeight(I)V

    move v5, v1

    goto :goto_1

    .line 552
    :cond_6
    iget-object v3, p0, Lmiui/widget/ListPopupWindow;->Sk:Lmiui/widget/ArrowPopupWindow;

    invoke-virtual {v3, v4, v5}, Lmiui/widget/ArrowPopupWindow;->setWindowLayoutMode(II)V

    .line 556
    iget-object v3, p0, Lmiui/widget/ListPopupWindow;->Sk:Lmiui/widget/ArrowPopupWindow;

    iget-boolean v4, p0, Lmiui/widget/ListPopupWindow;->Ss:Z

    if-nez v4, :cond_9

    iget-boolean v4, p0, Lmiui/widget/ListPopupWindow;->Sr:Z

    if-nez v4, :cond_9

    :goto_3
    invoke-virtual {v3, v2}, Lmiui/widget/ArrowPopupWindow;->setOutsideTouchable(Z)V

    .line 557
    iget-object v1, p0, Lmiui/widget/ListPopupWindow;->Sk:Lmiui/widget/ArrowPopupWindow;

    iget-object v2, p0, Lmiui/widget/ListPopupWindow;->Sg:Lmiui/widget/ListPopupWindow$c;

    invoke-virtual {v1, v2}, Lmiui/widget/ArrowPopupWindow;->setTouchInterceptor(Landroid/view/View$OnTouchListener;)V

    .line 558
    iget-object v1, p0, Lmiui/widget/ListPopupWindow;->Sk:Lmiui/widget/ArrowPopupWindow;

    invoke-virtual {p0}, Lmiui/widget/ListPopupWindow;->getAnchorView()Landroid/view/View;

    move-result-object v2

    iget v3, p0, Lmiui/widget/ListPopupWindow;->So:I

    iget v4, p0, Lmiui/widget/ListPopupWindow;->Sp:I

    invoke-virtual {v1, v2, v3, v4}, Lmiui/widget/ArrowPopupWindow;->show(Landroid/view/View;II)V

    .line 560
    iget-object v1, p0, Lmiui/widget/ListPopupWindow;->Sl:Lmiui/widget/ListPopupWindow$e;

    invoke-virtual {v1, v0}, Lmiui/widget/ListPopupWindow$e;->setSelection(I)V

    .line 562
    iget-boolean v0, p0, Lmiui/widget/ListPopupWindow;->SJ:Z

    if-eqz v0, :cond_7

    iget-object v0, p0, Lmiui/widget/ListPopupWindow;->Sl:Lmiui/widget/ListPopupWindow$e;

    invoke-virtual {v0}, Lmiui/widget/ListPopupWindow$e;->isInTouchMode()Z

    move-result v0

    if-eqz v0, :cond_8

    .line 563
    :cond_7
    invoke-virtual {p0}, Lmiui/widget/ListPopupWindow;->clearListSelection()V

    .line 565
    :cond_8
    iget-boolean v0, p0, Lmiui/widget/ListPopupWindow;->SJ:Z

    if-nez v0, :cond_1

    .line 566
    iget-object v0, p0, Lmiui/widget/ListPopupWindow;->mHandler:Landroid/os/Handler;

    iget-object v1, p0, Lmiui/widget/ListPopupWindow;->Si:Lmiui/widget/ListPopupWindow$d;

    invoke-virtual {v0, v1}, Landroid/os/Handler;->post(Ljava/lang/Runnable;)Z

    goto :goto_2

    :cond_9
    move v2, v1

    .line 556
    goto :goto_3
.end method
