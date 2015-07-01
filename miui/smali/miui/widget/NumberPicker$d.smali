.class Lmiui/widget/NumberPicker$d;
.super Landroid/view/accessibility/AccessibilityNodeProvider;
.source "SourceFile"


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lmiui/widget/NumberPicker;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x0
    name = "d"
.end annotation


# static fields
.field private static final UNDEFINED:I = -0x80000000

.field private static final wV:I = 0x1

.field private static final wW:I = 0x2

.field private static final wX:I = 0x3


# instance fields
.field final synthetic aC:Lmiui/widget/NumberPicker;

.field private final wY:Landroid/graphics/Rect;

.field private final wZ:[I

.field private xa:I


# direct methods
.method constructor <init>(Lmiui/widget/NumberPicker;)V
    .locals 1

    .prologue
    .line 2072
    iput-object p1, p0, Lmiui/widget/NumberPicker$d;->aC:Lmiui/widget/NumberPicker;

    invoke-direct {p0}, Landroid/view/accessibility/AccessibilityNodeProvider;-><init>()V

    .line 2081
    new-instance v0, Landroid/graphics/Rect;

    invoke-direct {v0}, Landroid/graphics/Rect;-><init>()V

    iput-object v0, p0, Lmiui/widget/NumberPicker$d;->wY:Landroid/graphics/Rect;

    .line 2083
    const/4 v0, 0x2

    new-array v0, v0, [I

    iput-object v0, p0, Lmiui/widget/NumberPicker$d;->wZ:[I

    .line 2085
    const/high16 v0, -0x80000000

    iput v0, p0, Lmiui/widget/NumberPicker$d;->xa:I

    return-void
.end method

.method private Q(I)V
    .locals 3

    .prologue
    .line 2304
    iget-object v0, p0, Lmiui/widget/NumberPicker$d;->aC:Lmiui/widget/NumberPicker;

    invoke-virtual {v0}, Lmiui/widget/NumberPicker;->getContext()Landroid/content/Context;

    move-result-object v0

    const-string v1, "accessibility"

    invoke-virtual {v0, v1}, Landroid/content/Context;->getSystemService(Ljava/lang/String;)Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Landroid/view/accessibility/AccessibilityManager;

    .line 2305
    invoke-virtual {v0}, Landroid/view/accessibility/AccessibilityManager;->isEnabled()Z

    move-result v0

    if-eqz v0, :cond_0

    .line 2306
    invoke-static {p1}, Landroid/view/accessibility/AccessibilityEvent;->obtain(I)Landroid/view/accessibility/AccessibilityEvent;

    move-result-object v0

    .line 2307
    iget-object v1, p0, Lmiui/widget/NumberPicker$d;->aC:Lmiui/widget/NumberPicker;

    invoke-static {v1}, Lmiui/widget/NumberPicker;->a(Lmiui/widget/NumberPicker;)Landroid/widget/EditText;

    move-result-object v1

    invoke-virtual {v1, v0}, Landroid/widget/EditText;->onInitializeAccessibilityEvent(Landroid/view/accessibility/AccessibilityEvent;)V

    .line 2308
    iget-object v1, p0, Lmiui/widget/NumberPicker$d;->aC:Lmiui/widget/NumberPicker;

    invoke-static {v1}, Lmiui/widget/NumberPicker;->a(Lmiui/widget/NumberPicker;)Landroid/widget/EditText;

    move-result-object v1

    invoke-virtual {v1, v0}, Landroid/widget/EditText;->onPopulateAccessibilityEvent(Landroid/view/accessibility/AccessibilityEvent;)V

    .line 2309
    iget-object v1, p0, Lmiui/widget/NumberPicker$d;->aC:Lmiui/widget/NumberPicker;

    const/4 v2, 0x2

    invoke-virtual {v0, v1, v2}, Landroid/view/accessibility/AccessibilityEvent;->setSource(Landroid/view/View;I)V

    .line 2310
    iget-object v1, p0, Lmiui/widget/NumberPicker$d;->aC:Lmiui/widget/NumberPicker;

    iget-object v2, p0, Lmiui/widget/NumberPicker$d;->aC:Lmiui/widget/NumberPicker;

    invoke-virtual {v1, v2, v0}, Lmiui/widget/NumberPicker;->requestSendAccessibilityEvent(Landroid/view/View;Landroid/view/accessibility/AccessibilityEvent;)Z

    .line 2312
    :cond_0
    return-void
.end method

.method private a(IIII)Landroid/view/accessibility/AccessibilityNodeInfo;
    .locals 6

    .prologue
    const/4 v1, 0x1

    const/4 v2, 0x0

    const/4 v4, 0x2

    .line 2363
    iget-object v0, p0, Lmiui/widget/NumberPicker$d;->aC:Lmiui/widget/NumberPicker;

    invoke-static {v0}, Lmiui/widget/NumberPicker;->a(Lmiui/widget/NumberPicker;)Landroid/widget/EditText;

    move-result-object v0

    invoke-virtual {v0}, Landroid/widget/EditText;->createAccessibilityNodeInfo()Landroid/view/accessibility/AccessibilityNodeInfo;

    move-result-object v3

    .line 2364
    iget-object v0, p0, Lmiui/widget/NumberPicker$d;->aC:Lmiui/widget/NumberPicker;

    invoke-virtual {v3, v0, v4}, Landroid/view/accessibility/AccessibilityNodeInfo;->setSource(Landroid/view/View;I)V

    .line 2365
    iget v0, p0, Lmiui/widget/NumberPicker$d;->xa:I

    if-eq v0, v4, :cond_0

    .line 2366
    const/16 v0, 0x40

    invoke-virtual {v3, v0}, Landroid/view/accessibility/AccessibilityNodeInfo;->addAction(I)V

    .line 2368
    :cond_0
    iget v0, p0, Lmiui/widget/NumberPicker$d;->xa:I

    if-ne v0, v4, :cond_1

    .line 2369
    const/16 v0, 0x80

    invoke-virtual {v3, v0}, Landroid/view/accessibility/AccessibilityNodeInfo;->addAction(I)V

    .line 2371
    :cond_1
    iget-object v4, p0, Lmiui/widget/NumberPicker$d;->wY:Landroid/graphics/Rect;

    .line 2372
    invoke-virtual {v4, p1, p2, p3, p4}, Landroid/graphics/Rect;->set(IIII)V

    .line 2373
    iget-object v0, p0, Lmiui/widget/NumberPicker$d;->aC:Lmiui/widget/NumberPicker;

    invoke-virtual {v0}, Lmiui/widget/NumberPicker;->getVisibility()I

    move-result v0

    if-nez v0, :cond_2

    move v0, v1

    :goto_0
    invoke-virtual {v3, v0}, Landroid/view/accessibility/AccessibilityNodeInfo;->setVisibleToUser(Z)V

    .line 2374
    invoke-virtual {v3, v4}, Landroid/view/accessibility/AccessibilityNodeInfo;->setBoundsInParent(Landroid/graphics/Rect;)V

    .line 2376
    iget-object v0, p0, Lmiui/widget/NumberPicker$d;->wZ:[I

    .line 2377
    iget-object v5, p0, Lmiui/widget/NumberPicker$d;->aC:Lmiui/widget/NumberPicker;

    invoke-virtual {v5, v0}, Lmiui/widget/NumberPicker;->getLocationOnScreen([I)V

    .line 2378
    aget v2, v0, v2

    aget v0, v0, v1

    invoke-virtual {v4, v2, v0}, Landroid/graphics/Rect;->offset(II)V

    .line 2379
    invoke-virtual {v3, v4}, Landroid/view/accessibility/AccessibilityNodeInfo;->setBoundsInScreen(Landroid/graphics/Rect;)V

    .line 2380
    return-object v3

    :cond_2
    move v0, v2

    .line 2373
    goto :goto_0
.end method

.method private a(ILjava/lang/String;IIII)Landroid/view/accessibility/AccessibilityNodeInfo;
    .locals 6

    .prologue
    const/4 v2, 0x0

    const/4 v1, 0x1

    .line 2385
    invoke-static {}, Landroid/view/accessibility/AccessibilityNodeInfo;->obtain()Landroid/view/accessibility/AccessibilityNodeInfo;

    move-result-object v3

    .line 2386
    const-class v0, Landroid/widget/Button;

    invoke-virtual {v0}, Ljava/lang/Class;->getName()Ljava/lang/String;

    move-result-object v0

    invoke-virtual {v3, v0}, Landroid/view/accessibility/AccessibilityNodeInfo;->setClassName(Ljava/lang/CharSequence;)V

    .line 2387
    iget-object v0, p0, Lmiui/widget/NumberPicker$d;->aC:Lmiui/widget/NumberPicker;

    invoke-virtual {v0}, Lmiui/widget/NumberPicker;->getContext()Landroid/content/Context;

    move-result-object v0

    invoke-virtual {v0}, Landroid/content/Context;->getPackageName()Ljava/lang/String;

    move-result-object v0

    invoke-virtual {v3, v0}, Landroid/view/accessibility/AccessibilityNodeInfo;->setPackageName(Ljava/lang/CharSequence;)V

    .line 2388
    iget-object v0, p0, Lmiui/widget/NumberPicker$d;->aC:Lmiui/widget/NumberPicker;

    invoke-virtual {v3, v0, p1}, Landroid/view/accessibility/AccessibilityNodeInfo;->setSource(Landroid/view/View;I)V

    .line 2389
    iget-object v0, p0, Lmiui/widget/NumberPicker$d;->aC:Lmiui/widget/NumberPicker;

    invoke-virtual {v3, v0}, Landroid/view/accessibility/AccessibilityNodeInfo;->setParent(Landroid/view/View;)V

    .line 2390
    invoke-virtual {v3, p2}, Landroid/view/accessibility/AccessibilityNodeInfo;->setText(Ljava/lang/CharSequence;)V

    .line 2391
    invoke-virtual {v3, v1}, Landroid/view/accessibility/AccessibilityNodeInfo;->setClickable(Z)V

    .line 2392
    invoke-virtual {v3, v1}, Landroid/view/accessibility/AccessibilityNodeInfo;->setLongClickable(Z)V

    .line 2393
    iget-object v0, p0, Lmiui/widget/NumberPicker$d;->aC:Lmiui/widget/NumberPicker;

    invoke-virtual {v0}, Lmiui/widget/NumberPicker;->isEnabled()Z

    move-result v0

    invoke-virtual {v3, v0}, Landroid/view/accessibility/AccessibilityNodeInfo;->setEnabled(Z)V

    .line 2394
    iget-object v4, p0, Lmiui/widget/NumberPicker$d;->wY:Landroid/graphics/Rect;

    .line 2395
    invoke-virtual {v4, p3, p4, p5, p6}, Landroid/graphics/Rect;->set(IIII)V

    .line 2396
    iget-object v0, p0, Lmiui/widget/NumberPicker$d;->aC:Lmiui/widget/NumberPicker;

    invoke-virtual {v0}, Lmiui/widget/NumberPicker;->getVisibility()I

    move-result v0

    if-nez v0, :cond_3

    move v0, v1

    :goto_0
    invoke-virtual {v3, v0}, Landroid/view/accessibility/AccessibilityNodeInfo;->setVisibleToUser(Z)V

    .line 2397
    invoke-virtual {v3, v4}, Landroid/view/accessibility/AccessibilityNodeInfo;->setBoundsInParent(Landroid/graphics/Rect;)V

    .line 2399
    iget-object v0, p0, Lmiui/widget/NumberPicker$d;->wZ:[I

    .line 2400
    iget-object v5, p0, Lmiui/widget/NumberPicker$d;->aC:Lmiui/widget/NumberPicker;

    invoke-virtual {v5, v0}, Lmiui/widget/NumberPicker;->getLocationOnScreen([I)V

    .line 2401
    aget v2, v0, v2

    aget v0, v0, v1

    invoke-virtual {v4, v2, v0}, Landroid/graphics/Rect;->offset(II)V

    .line 2402
    invoke-virtual {v3, v4}, Landroid/view/accessibility/AccessibilityNodeInfo;->setBoundsInScreen(Landroid/graphics/Rect;)V

    .line 2404
    iget v0, p0, Lmiui/widget/NumberPicker$d;->xa:I

    if-eq v0, p1, :cond_0

    .line 2405
    const/16 v0, 0x40

    invoke-virtual {v3, v0}, Landroid/view/accessibility/AccessibilityNodeInfo;->addAction(I)V

    .line 2407
    :cond_0
    iget v0, p0, Lmiui/widget/NumberPicker$d;->xa:I

    if-ne v0, p1, :cond_1

    .line 2408
    const/16 v0, 0x80

    invoke-virtual {v3, v0}, Landroid/view/accessibility/AccessibilityNodeInfo;->addAction(I)V

    .line 2410
    :cond_1
    iget-object v0, p0, Lmiui/widget/NumberPicker$d;->aC:Lmiui/widget/NumberPicker;

    invoke-virtual {v0}, Lmiui/widget/NumberPicker;->isEnabled()Z

    move-result v0

    if-eqz v0, :cond_2

    .line 2411
    const/16 v0, 0x10

    invoke-virtual {v3, v0}, Landroid/view/accessibility/AccessibilityNodeInfo;->addAction(I)V

    .line 2414
    :cond_2
    return-object v3

    :cond_3
    move v0, v2

    .line 2396
    goto :goto_0
.end method

.method private a(IILjava/lang/String;)V
    .locals 3

    .prologue
    .line 2315
    iget-object v0, p0, Lmiui/widget/NumberPicker$d;->aC:Lmiui/widget/NumberPicker;

    invoke-virtual {v0}, Lmiui/widget/NumberPicker;->getContext()Landroid/content/Context;

    move-result-object v0

    const-string v1, "accessibility"

    invoke-virtual {v0, v1}, Landroid/content/Context;->getSystemService(Ljava/lang/String;)Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Landroid/view/accessibility/AccessibilityManager;

    .line 2316
    invoke-virtual {v0}, Landroid/view/accessibility/AccessibilityManager;->isEnabled()Z

    move-result v0

    if-eqz v0, :cond_0

    .line 2317
    invoke-static {p2}, Landroid/view/accessibility/AccessibilityEvent;->obtain(I)Landroid/view/accessibility/AccessibilityEvent;

    move-result-object v0

    .line 2318
    const-class v1, Landroid/widget/Button;

    invoke-virtual {v1}, Ljava/lang/Class;->getName()Ljava/lang/String;

    move-result-object v1

    invoke-virtual {v0, v1}, Landroid/view/accessibility/AccessibilityEvent;->setClassName(Ljava/lang/CharSequence;)V

    .line 2319
    iget-object v1, p0, Lmiui/widget/NumberPicker$d;->aC:Lmiui/widget/NumberPicker;

    invoke-virtual {v1}, Lmiui/widget/NumberPicker;->getContext()Landroid/content/Context;

    move-result-object v1

    invoke-virtual {v1}, Landroid/content/Context;->getPackageName()Ljava/lang/String;

    move-result-object v1

    invoke-virtual {v0, v1}, Landroid/view/accessibility/AccessibilityEvent;->setPackageName(Ljava/lang/CharSequence;)V

    .line 2320
    invoke-virtual {v0}, Landroid/view/accessibility/AccessibilityEvent;->getText()Ljava/util/List;

    move-result-object v1

    invoke-interface {v1, p3}, Ljava/util/List;->add(Ljava/lang/Object;)Z

    .line 2321
    iget-object v1, p0, Lmiui/widget/NumberPicker$d;->aC:Lmiui/widget/NumberPicker;

    invoke-virtual {v1}, Lmiui/widget/NumberPicker;->isEnabled()Z

    move-result v1

    invoke-virtual {v0, v1}, Landroid/view/accessibility/AccessibilityEvent;->setEnabled(Z)V

    .line 2322
    iget-object v1, p0, Lmiui/widget/NumberPicker$d;->aC:Lmiui/widget/NumberPicker;

    invoke-virtual {v0, v1, p1}, Landroid/view/accessibility/AccessibilityEvent;->setSource(Landroid/view/View;I)V

    .line 2323
    iget-object v1, p0, Lmiui/widget/NumberPicker$d;->aC:Lmiui/widget/NumberPicker;

    iget-object v2, p0, Lmiui/widget/NumberPicker$d;->aC:Lmiui/widget/NumberPicker;

    invoke-virtual {v1, v2, v0}, Lmiui/widget/NumberPicker;->requestSendAccessibilityEvent(Landroid/view/View;Landroid/view/accessibility/AccessibilityEvent;)Z

    .line 2325
    :cond_0
    return-void
.end method

.method private a(Ljava/lang/String;ILjava/util/List;)V
    .locals 3
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Ljava/lang/String;",
            "I",
            "Ljava/util/List",
            "<",
            "Landroid/view/accessibility/AccessibilityNodeInfo;",
            ">;)V"
        }
    .end annotation

    .prologue
    const/4 v2, 0x2

    .line 2329
    packed-switch p2, :pswitch_data_0

    .line 2359
    :cond_0
    :goto_0
    return-void

    .line 2331
    :pswitch_0
    invoke-direct {p0}, Lmiui/widget/NumberPicker$d;->cm()Ljava/lang/String;

    move-result-object v0

    .line 2332
    invoke-static {v0}, Landroid/text/TextUtils;->isEmpty(Ljava/lang/CharSequence;)Z

    move-result v1

    if-nez v1, :cond_0

    invoke-virtual {v0}, Ljava/lang/String;->toString()Ljava/lang/String;

    move-result-object v0

    invoke-virtual {v0}, Ljava/lang/String;->toLowerCase()Ljava/lang/String;

    move-result-object v0

    invoke-virtual {v0, p1}, Ljava/lang/String;->contains(Ljava/lang/CharSequence;)Z

    move-result v0

    if-eqz v0, :cond_0

    .line 2334
    const/4 v0, 0x3

    invoke-virtual {p0, v0}, Lmiui/widget/NumberPicker$d;->createAccessibilityNodeInfo(I)Landroid/view/accessibility/AccessibilityNodeInfo;

    move-result-object v0

    invoke-interface {p3, v0}, Ljava/util/List;->add(Ljava/lang/Object;)Z

    goto :goto_0

    .line 2338
    :pswitch_1
    iget-object v0, p0, Lmiui/widget/NumberPicker$d;->aC:Lmiui/widget/NumberPicker;

    invoke-static {v0}, Lmiui/widget/NumberPicker;->a(Lmiui/widget/NumberPicker;)Landroid/widget/EditText;

    move-result-object v0

    invoke-virtual {v0}, Landroid/widget/EditText;->getText()Landroid/text/Editable;

    move-result-object v0

    .line 2339
    invoke-static {v0}, Landroid/text/TextUtils;->isEmpty(Ljava/lang/CharSequence;)Z

    move-result v1

    if-nez v1, :cond_1

    invoke-virtual {v0}, Ljava/lang/Object;->toString()Ljava/lang/String;

    move-result-object v0

    invoke-virtual {v0}, Ljava/lang/String;->toLowerCase()Ljava/lang/String;

    move-result-object v0

    invoke-virtual {v0, p1}, Ljava/lang/String;->contains(Ljava/lang/CharSequence;)Z

    move-result v0

    if-eqz v0, :cond_1

    .line 2341
    invoke-virtual {p0, v2}, Lmiui/widget/NumberPicker$d;->createAccessibilityNodeInfo(I)Landroid/view/accessibility/AccessibilityNodeInfo;

    move-result-object v0

    invoke-interface {p3, v0}, Ljava/util/List;->add(Ljava/lang/Object;)Z

    goto :goto_0

    .line 2344
    :cond_1
    iget-object v0, p0, Lmiui/widget/NumberPicker$d;->aC:Lmiui/widget/NumberPicker;

    invoke-static {v0}, Lmiui/widget/NumberPicker;->a(Lmiui/widget/NumberPicker;)Landroid/widget/EditText;

    move-result-object v0

    invoke-virtual {v0}, Landroid/widget/EditText;->getText()Landroid/text/Editable;

    move-result-object v0

    .line 2345
    invoke-static {v0}, Landroid/text/TextUtils;->isEmpty(Ljava/lang/CharSequence;)Z

    move-result v1

    if-nez v1, :cond_0

    invoke-virtual {v0}, Ljava/lang/Object;->toString()Ljava/lang/String;

    move-result-object v0

    invoke-virtual {v0}, Ljava/lang/String;->toLowerCase()Ljava/lang/String;

    move-result-object v0

    invoke-virtual {v0, p1}, Ljava/lang/String;->contains(Ljava/lang/CharSequence;)Z

    move-result v0

    if-eqz v0, :cond_0

    .line 2347
    invoke-virtual {p0, v2}, Lmiui/widget/NumberPicker$d;->createAccessibilityNodeInfo(I)Landroid/view/accessibility/AccessibilityNodeInfo;

    move-result-object v0

    invoke-interface {p3, v0}, Ljava/util/List;->add(Ljava/lang/Object;)Z

    goto :goto_0

    .line 2352
    :pswitch_2
    invoke-direct {p0}, Lmiui/widget/NumberPicker$d;->cn()Ljava/lang/String;

    move-result-object v0

    .line 2353
    invoke-static {v0}, Landroid/text/TextUtils;->isEmpty(Ljava/lang/CharSequence;)Z

    move-result v1

    if-nez v1, :cond_0

    invoke-virtual {v0}, Ljava/lang/String;->toString()Ljava/lang/String;

    move-result-object v0

    invoke-virtual {v0}, Ljava/lang/String;->toLowerCase()Ljava/lang/String;

    move-result-object v0

    invoke-virtual {v0, p1}, Ljava/lang/String;->contains(Ljava/lang/CharSequence;)Z

    move-result v0

    if-eqz v0, :cond_0

    .line 2355
    const/4 v0, 0x1

    invoke-virtual {p0, v0}, Lmiui/widget/NumberPicker$d;->createAccessibilityNodeInfo(I)Landroid/view/accessibility/AccessibilityNodeInfo;

    move-result-object v0

    invoke-interface {p3, v0}, Ljava/util/List;->add(Ljava/lang/Object;)Z

    goto/16 :goto_0

    .line 2329
    :pswitch_data_0
    .packed-switch 0x1
        :pswitch_2
        :pswitch_1
        :pswitch_0
    .end packed-switch
.end method

.method private b(IIII)Landroid/view/accessibility/AccessibilityNodeInfo;
    .locals 7

    .prologue
    const/4 v2, 0x0

    const/4 v6, -0x1

    const/4 v1, 0x1

    .line 2419
    invoke-static {}, Landroid/view/accessibility/AccessibilityNodeInfo;->obtain()Landroid/view/accessibility/AccessibilityNodeInfo;

    move-result-object v3

    .line 2420
    const-class v0, Lmiui/widget/NumberPicker;

    invoke-virtual {v0}, Ljava/lang/Class;->getName()Ljava/lang/String;

    move-result-object v0

    invoke-virtual {v3, v0}, Landroid/view/accessibility/AccessibilityNodeInfo;->setClassName(Ljava/lang/CharSequence;)V

    .line 2421
    iget-object v0, p0, Lmiui/widget/NumberPicker$d;->aC:Lmiui/widget/NumberPicker;

    invoke-virtual {v0}, Lmiui/widget/NumberPicker;->getContext()Landroid/content/Context;

    move-result-object v0

    invoke-virtual {v0}, Landroid/content/Context;->getPackageName()Ljava/lang/String;

    move-result-object v0

    invoke-virtual {v3, v0}, Landroid/view/accessibility/AccessibilityNodeInfo;->setPackageName(Ljava/lang/CharSequence;)V

    .line 2422
    iget-object v0, p0, Lmiui/widget/NumberPicker$d;->aC:Lmiui/widget/NumberPicker;

    invoke-virtual {v3, v0}, Landroid/view/accessibility/AccessibilityNodeInfo;->setSource(Landroid/view/View;)V

    .line 2424
    invoke-direct {p0}, Lmiui/widget/NumberPicker$d;->ck()Z

    move-result v0

    if-eqz v0, :cond_0

    .line 2425
    iget-object v0, p0, Lmiui/widget/NumberPicker$d;->aC:Lmiui/widget/NumberPicker;

    const/4 v4, 0x3

    invoke-virtual {v3, v0, v4}, Landroid/view/accessibility/AccessibilityNodeInfo;->addChild(Landroid/view/View;I)V

    .line 2427
    :cond_0
    iget-object v0, p0, Lmiui/widget/NumberPicker$d;->aC:Lmiui/widget/NumberPicker;

    const/4 v4, 0x2

    invoke-virtual {v3, v0, v4}, Landroid/view/accessibility/AccessibilityNodeInfo;->addChild(Landroid/view/View;I)V

    .line 2428
    invoke-direct {p0}, Lmiui/widget/NumberPicker$d;->cl()Z

    move-result v0

    if-eqz v0, :cond_1

    .line 2429
    iget-object v0, p0, Lmiui/widget/NumberPicker$d;->aC:Lmiui/widget/NumberPicker;

    invoke-virtual {v3, v0, v1}, Landroid/view/accessibility/AccessibilityNodeInfo;->addChild(Landroid/view/View;I)V

    .line 2432
    :cond_1
    iget-object v0, p0, Lmiui/widget/NumberPicker$d;->aC:Lmiui/widget/NumberPicker;

    invoke-virtual {v0}, Lmiui/widget/NumberPicker;->getParentForAccessibility()Landroid/view/ViewParent;

    move-result-object v0

    check-cast v0, Landroid/view/View;

    invoke-virtual {v3, v0}, Landroid/view/accessibility/AccessibilityNodeInfo;->setParent(Landroid/view/View;)V

    .line 2433
    iget-object v0, p0, Lmiui/widget/NumberPicker$d;->aC:Lmiui/widget/NumberPicker;

    invoke-virtual {v0}, Lmiui/widget/NumberPicker;->isEnabled()Z

    move-result v0

    invoke-virtual {v3, v0}, Landroid/view/accessibility/AccessibilityNodeInfo;->setEnabled(Z)V

    .line 2434
    invoke-virtual {v3, v1}, Landroid/view/accessibility/AccessibilityNodeInfo;->setScrollable(Z)V

    .line 2436
    iget-object v4, p0, Lmiui/widget/NumberPicker$d;->wY:Landroid/graphics/Rect;

    .line 2438
    invoke-virtual {v4, p1, p2, p3, p4}, Landroid/graphics/Rect;->set(IIII)V

    .line 2439
    invoke-virtual {v3, v4}, Landroid/view/accessibility/AccessibilityNodeInfo;->setBoundsInParent(Landroid/graphics/Rect;)V

    .line 2441
    iget-object v0, p0, Lmiui/widget/NumberPicker$d;->aC:Lmiui/widget/NumberPicker;

    invoke-virtual {v0}, Lmiui/widget/NumberPicker;->getVisibility()I

    move-result v0

    if-nez v0, :cond_8

    move v0, v1

    :goto_0
    invoke-virtual {v3, v0}, Landroid/view/accessibility/AccessibilityNodeInfo;->setVisibleToUser(Z)V

    .line 2444
    iget-object v0, p0, Lmiui/widget/NumberPicker$d;->wZ:[I

    .line 2445
    iget-object v5, p0, Lmiui/widget/NumberPicker$d;->aC:Lmiui/widget/NumberPicker;

    invoke-virtual {v5, v0}, Lmiui/widget/NumberPicker;->getLocationOnScreen([I)V

    .line 2446
    aget v2, v0, v2

    aget v0, v0, v1

    invoke-virtual {v4, v2, v0}, Landroid/graphics/Rect;->offset(II)V

    .line 2447
    invoke-virtual {v3, v4}, Landroid/view/accessibility/AccessibilityNodeInfo;->setBoundsInScreen(Landroid/graphics/Rect;)V

    .line 2449
    iget v0, p0, Lmiui/widget/NumberPicker$d;->xa:I

    if-eq v0, v6, :cond_2

    .line 2450
    const/16 v0, 0x40

    invoke-virtual {v3, v0}, Landroid/view/accessibility/AccessibilityNodeInfo;->addAction(I)V

    .line 2452
    :cond_2
    iget v0, p0, Lmiui/widget/NumberPicker$d;->xa:I

    if-ne v0, v6, :cond_3

    .line 2453
    const/16 v0, 0x80

    invoke-virtual {v3, v0}, Landroid/view/accessibility/AccessibilityNodeInfo;->addAction(I)V

    .line 2455
    :cond_3
    iget-object v0, p0, Lmiui/widget/NumberPicker$d;->aC:Lmiui/widget/NumberPicker;

    invoke-virtual {v0}, Lmiui/widget/NumberPicker;->isEnabled()Z

    move-result v0

    if-eqz v0, :cond_7

    .line 2456
    iget-object v0, p0, Lmiui/widget/NumberPicker$d;->aC:Lmiui/widget/NumberPicker;

    invoke-virtual {v0}, Lmiui/widget/NumberPicker;->getWrapSelectorWheel()Z

    move-result v0

    if-nez v0, :cond_4

    iget-object v0, p0, Lmiui/widget/NumberPicker$d;->aC:Lmiui/widget/NumberPicker;

    invoke-virtual {v0}, Lmiui/widget/NumberPicker;->getValue()I

    move-result v0

    iget-object v1, p0, Lmiui/widget/NumberPicker$d;->aC:Lmiui/widget/NumberPicker;

    invoke-virtual {v1}, Lmiui/widget/NumberPicker;->getMaxValue()I

    move-result v1

    if-ge v0, v1, :cond_5

    .line 2457
    :cond_4
    const/16 v0, 0x1000

    invoke-virtual {v3, v0}, Landroid/view/accessibility/AccessibilityNodeInfo;->addAction(I)V

    .line 2459
    :cond_5
    iget-object v0, p0, Lmiui/widget/NumberPicker$d;->aC:Lmiui/widget/NumberPicker;

    invoke-virtual {v0}, Lmiui/widget/NumberPicker;->getWrapSelectorWheel()Z

    move-result v0

    if-nez v0, :cond_6

    iget-object v0, p0, Lmiui/widget/NumberPicker$d;->aC:Lmiui/widget/NumberPicker;

    invoke-virtual {v0}, Lmiui/widget/NumberPicker;->getValue()I

    move-result v0

    iget-object v1, p0, Lmiui/widget/NumberPicker$d;->aC:Lmiui/widget/NumberPicker;

    invoke-virtual {v1}, Lmiui/widget/NumberPicker;->getMinValue()I

    move-result v1

    if-le v0, v1, :cond_7

    .line 2460
    :cond_6
    const/16 v0, 0x2000

    invoke-virtual {v3, v0}, Landroid/view/accessibility/AccessibilityNodeInfo;->addAction(I)V

    .line 2464
    :cond_7
    return-object v3

    :cond_8
    move v0, v2

    .line 2441
    goto :goto_0
.end method

.method private ck()Z
    .locals 2

    .prologue
    .line 2468
    iget-object v0, p0, Lmiui/widget/NumberPicker$d;->aC:Lmiui/widget/NumberPicker;

    invoke-virtual {v0}, Lmiui/widget/NumberPicker;->getWrapSelectorWheel()Z

    move-result v0

    if-nez v0, :cond_0

    iget-object v0, p0, Lmiui/widget/NumberPicker$d;->aC:Lmiui/widget/NumberPicker;

    invoke-virtual {v0}, Lmiui/widget/NumberPicker;->getValue()I

    move-result v0

    iget-object v1, p0, Lmiui/widget/NumberPicker$d;->aC:Lmiui/widget/NumberPicker;

    invoke-virtual {v1}, Lmiui/widget/NumberPicker;->getMinValue()I

    move-result v1

    if-le v0, v1, :cond_1

    :cond_0
    const/4 v0, 0x1

    :goto_0
    return v0

    :cond_1
    const/4 v0, 0x0

    goto :goto_0
.end method

.method private cl()Z
    .locals 2

    .prologue
    .line 2472
    iget-object v0, p0, Lmiui/widget/NumberPicker$d;->aC:Lmiui/widget/NumberPicker;

    invoke-virtual {v0}, Lmiui/widget/NumberPicker;->getWrapSelectorWheel()Z

    move-result v0

    if-nez v0, :cond_0

    iget-object v0, p0, Lmiui/widget/NumberPicker$d;->aC:Lmiui/widget/NumberPicker;

    invoke-virtual {v0}, Lmiui/widget/NumberPicker;->getValue()I

    move-result v0

    iget-object v1, p0, Lmiui/widget/NumberPicker$d;->aC:Lmiui/widget/NumberPicker;

    invoke-virtual {v1}, Lmiui/widget/NumberPicker;->getMaxValue()I

    move-result v1

    if-ge v0, v1, :cond_1

    :cond_0
    const/4 v0, 0x1

    :goto_0
    return v0

    :cond_1
    const/4 v0, 0x0

    goto :goto_0
.end method

.method private cm()Ljava/lang/String;
    .locals 3

    .prologue
    .line 2476
    iget-object v0, p0, Lmiui/widget/NumberPicker$d;->aC:Lmiui/widget/NumberPicker;

    invoke-static {v0}, Lmiui/widget/NumberPicker;->j(Lmiui/widget/NumberPicker;)I

    move-result v0

    add-int/lit8 v0, v0, -0x1

    .line 2477
    iget-object v1, p0, Lmiui/widget/NumberPicker$d;->aC:Lmiui/widget/NumberPicker;

    invoke-static {v1}, Lmiui/widget/NumberPicker;->k(Lmiui/widget/NumberPicker;)Z

    move-result v1

    if-eqz v1, :cond_0

    .line 2478
    iget-object v1, p0, Lmiui/widget/NumberPicker$d;->aC:Lmiui/widget/NumberPicker;

    invoke-static {v1, v0}, Lmiui/widget/NumberPicker;->c(Lmiui/widget/NumberPicker;I)I

    move-result v0

    .line 2480
    :cond_0
    iget-object v1, p0, Lmiui/widget/NumberPicker$d;->aC:Lmiui/widget/NumberPicker;

    invoke-static {v1}, Lmiui/widget/NumberPicker;->l(Lmiui/widget/NumberPicker;)I

    move-result v1

    if-lt v0, v1, :cond_2

    .line 2481
    iget-object v1, p0, Lmiui/widget/NumberPicker$d;->aC:Lmiui/widget/NumberPicker;

    invoke-static {v1}, Lmiui/widget/NumberPicker;->b(Lmiui/widget/NumberPicker;)[Ljava/lang/String;

    move-result-object v1

    if-nez v1, :cond_1

    iget-object v1, p0, Lmiui/widget/NumberPicker$d;->aC:Lmiui/widget/NumberPicker;

    invoke-static {v1, v0}, Lmiui/widget/NumberPicker;->d(Lmiui/widget/NumberPicker;I)Ljava/lang/String;

    move-result-object v0

    .line 2484
    :goto_0
    return-object v0

    .line 2481
    :cond_1
    iget-object v1, p0, Lmiui/widget/NumberPicker$d;->aC:Lmiui/widget/NumberPicker;

    invoke-static {v1}, Lmiui/widget/NumberPicker;->b(Lmiui/widget/NumberPicker;)[Ljava/lang/String;

    move-result-object v1

    iget-object v2, p0, Lmiui/widget/NumberPicker$d;->aC:Lmiui/widget/NumberPicker;

    invoke-static {v2}, Lmiui/widget/NumberPicker;->l(Lmiui/widget/NumberPicker;)I

    move-result v2

    sub-int/2addr v0, v2

    aget-object v0, v1, v0

    goto :goto_0

    .line 2484
    :cond_2
    const/4 v0, 0x0

    goto :goto_0
.end method

.method private cn()Ljava/lang/String;
    .locals 3

    .prologue
    .line 2488
    iget-object v0, p0, Lmiui/widget/NumberPicker$d;->aC:Lmiui/widget/NumberPicker;

    invoke-static {v0}, Lmiui/widget/NumberPicker;->j(Lmiui/widget/NumberPicker;)I

    move-result v0

    add-int/lit8 v0, v0, 0x1

    .line 2489
    iget-object v1, p0, Lmiui/widget/NumberPicker$d;->aC:Lmiui/widget/NumberPicker;

    invoke-static {v1}, Lmiui/widget/NumberPicker;->k(Lmiui/widget/NumberPicker;)Z

    move-result v1

    if-eqz v1, :cond_0

    .line 2490
    iget-object v1, p0, Lmiui/widget/NumberPicker$d;->aC:Lmiui/widget/NumberPicker;

    invoke-static {v1, v0}, Lmiui/widget/NumberPicker;->c(Lmiui/widget/NumberPicker;I)I

    move-result v0

    .line 2492
    :cond_0
    iget-object v1, p0, Lmiui/widget/NumberPicker$d;->aC:Lmiui/widget/NumberPicker;

    invoke-static {v1}, Lmiui/widget/NumberPicker;->c(Lmiui/widget/NumberPicker;)I

    move-result v1

    if-gt v0, v1, :cond_2

    .line 2493
    iget-object v1, p0, Lmiui/widget/NumberPicker$d;->aC:Lmiui/widget/NumberPicker;

    invoke-static {v1}, Lmiui/widget/NumberPicker;->b(Lmiui/widget/NumberPicker;)[Ljava/lang/String;

    move-result-object v1

    if-nez v1, :cond_1

    iget-object v1, p0, Lmiui/widget/NumberPicker$d;->aC:Lmiui/widget/NumberPicker;

    invoke-static {v1, v0}, Lmiui/widget/NumberPicker;->d(Lmiui/widget/NumberPicker;I)Ljava/lang/String;

    move-result-object v0

    .line 2496
    :goto_0
    return-object v0

    .line 2493
    :cond_1
    iget-object v1, p0, Lmiui/widget/NumberPicker$d;->aC:Lmiui/widget/NumberPicker;

    invoke-static {v1}, Lmiui/widget/NumberPicker;->b(Lmiui/widget/NumberPicker;)[Ljava/lang/String;

    move-result-object v1

    iget-object v2, p0, Lmiui/widget/NumberPicker$d;->aC:Lmiui/widget/NumberPicker;

    invoke-static {v2}, Lmiui/widget/NumberPicker;->l(Lmiui/widget/NumberPicker;)I

    move-result v2

    sub-int/2addr v0, v2

    aget-object v0, v1, v0

    goto :goto_0

    .line 2496
    :cond_2
    const/4 v0, 0x0

    goto :goto_0
.end method


# virtual methods
.method public createAccessibilityNodeInfo(I)Landroid/view/accessibility/AccessibilityNodeInfo;
    .locals 8

    .prologue
    .line 2089
    packed-switch p1, :pswitch_data_0

    .line 2109
    :pswitch_0
    invoke-super {p0, p1}, Landroid/view/accessibility/AccessibilityNodeProvider;->createAccessibilityNodeInfo(I)Landroid/view/accessibility/AccessibilityNodeInfo;

    move-result-object v0

    :goto_0
    return-object v0

    .line 2091
    :pswitch_1
    iget-object v0, p0, Lmiui/widget/NumberPicker$d;->aC:Lmiui/widget/NumberPicker;

    invoke-virtual {v0}, Lmiui/widget/NumberPicker;->getScrollX()I

    move-result v0

    iget-object v1, p0, Lmiui/widget/NumberPicker$d;->aC:Lmiui/widget/NumberPicker;

    invoke-virtual {v1}, Lmiui/widget/NumberPicker;->getScrollY()I

    move-result v1

    iget-object v2, p0, Lmiui/widget/NumberPicker$d;->aC:Lmiui/widget/NumberPicker;

    invoke-virtual {v2}, Lmiui/widget/NumberPicker;->getScrollX()I

    move-result v2

    iget-object v3, p0, Lmiui/widget/NumberPicker$d;->aC:Lmiui/widget/NumberPicker;

    invoke-virtual {v3}, Lmiui/widget/NumberPicker;->getRight()I

    move-result v3

    iget-object v4, p0, Lmiui/widget/NumberPicker$d;->aC:Lmiui/widget/NumberPicker;

    invoke-virtual {v4}, Lmiui/widget/NumberPicker;->getLeft()I

    move-result v4

    sub-int/2addr v3, v4

    add-int/2addr v2, v3

    iget-object v3, p0, Lmiui/widget/NumberPicker$d;->aC:Lmiui/widget/NumberPicker;

    invoke-virtual {v3}, Lmiui/widget/NumberPicker;->getScrollY()I

    move-result v3

    iget-object v4, p0, Lmiui/widget/NumberPicker$d;->aC:Lmiui/widget/NumberPicker;

    invoke-virtual {v4}, Lmiui/widget/NumberPicker;->getBottom()I

    move-result v4

    iget-object v5, p0, Lmiui/widget/NumberPicker$d;->aC:Lmiui/widget/NumberPicker;

    invoke-virtual {v5}, Lmiui/widget/NumberPicker;->getTop()I

    move-result v5

    sub-int/2addr v4, v5

    add-int/2addr v3, v4

    invoke-direct {p0, v0, v1, v2, v3}, Lmiui/widget/NumberPicker$d;->b(IIII)Landroid/view/accessibility/AccessibilityNodeInfo;

    move-result-object v0

    goto :goto_0

    .line 2094
    :pswitch_2
    const/4 v1, 0x3

    invoke-direct {p0}, Lmiui/widget/NumberPicker$d;->cm()Ljava/lang/String;

    move-result-object v2

    iget-object v0, p0, Lmiui/widget/NumberPicker$d;->aC:Lmiui/widget/NumberPicker;

    invoke-virtual {v0}, Lmiui/widget/NumberPicker;->getScrollX()I

    move-result v3

    iget-object v0, p0, Lmiui/widget/NumberPicker$d;->aC:Lmiui/widget/NumberPicker;

    invoke-virtual {v0}, Lmiui/widget/NumberPicker;->getScrollY()I

    move-result v4

    iget-object v0, p0, Lmiui/widget/NumberPicker$d;->aC:Lmiui/widget/NumberPicker;

    invoke-virtual {v0}, Lmiui/widget/NumberPicker;->getScrollX()I

    move-result v0

    iget-object v5, p0, Lmiui/widget/NumberPicker$d;->aC:Lmiui/widget/NumberPicker;

    invoke-virtual {v5}, Lmiui/widget/NumberPicker;->getRight()I

    move-result v5

    iget-object v6, p0, Lmiui/widget/NumberPicker$d;->aC:Lmiui/widget/NumberPicker;

    invoke-virtual {v6}, Lmiui/widget/NumberPicker;->getLeft()I

    move-result v6

    sub-int/2addr v5, v6

    add-int/2addr v5, v0

    iget-object v0, p0, Lmiui/widget/NumberPicker$d;->aC:Lmiui/widget/NumberPicker;

    invoke-static {v0}, Lmiui/widget/NumberPicker;->g(Lmiui/widget/NumberPicker;)I

    move-result v0

    iget-object v6, p0, Lmiui/widget/NumberPicker$d;->aC:Lmiui/widget/NumberPicker;

    invoke-static {v6}, Lmiui/widget/NumberPicker;->i(Lmiui/widget/NumberPicker;)I

    move-result v6

    add-int/2addr v6, v0

    move-object v0, p0

    invoke-direct/range {v0 .. v6}, Lmiui/widget/NumberPicker$d;->a(ILjava/lang/String;IIII)Landroid/view/accessibility/AccessibilityNodeInfo;

    move-result-object v0

    goto :goto_0

    .line 2099
    :pswitch_3
    iget-object v0, p0, Lmiui/widget/NumberPicker$d;->aC:Lmiui/widget/NumberPicker;

    invoke-virtual {v0}, Lmiui/widget/NumberPicker;->getScrollX()I

    move-result v0

    iget-object v1, p0, Lmiui/widget/NumberPicker$d;->aC:Lmiui/widget/NumberPicker;

    invoke-static {v1}, Lmiui/widget/NumberPicker;->g(Lmiui/widget/NumberPicker;)I

    move-result v1

    iget-object v2, p0, Lmiui/widget/NumberPicker$d;->aC:Lmiui/widget/NumberPicker;

    invoke-static {v2}, Lmiui/widget/NumberPicker;->i(Lmiui/widget/NumberPicker;)I

    move-result v2

    add-int/2addr v1, v2

    iget-object v2, p0, Lmiui/widget/NumberPicker$d;->aC:Lmiui/widget/NumberPicker;

    invoke-virtual {v2}, Lmiui/widget/NumberPicker;->getScrollX()I

    move-result v2

    iget-object v3, p0, Lmiui/widget/NumberPicker$d;->aC:Lmiui/widget/NumberPicker;

    invoke-virtual {v3}, Lmiui/widget/NumberPicker;->getRight()I

    move-result v3

    iget-object v4, p0, Lmiui/widget/NumberPicker$d;->aC:Lmiui/widget/NumberPicker;

    invoke-virtual {v4}, Lmiui/widget/NumberPicker;->getLeft()I

    move-result v4

    sub-int/2addr v3, v4

    add-int/2addr v2, v3

    iget-object v3, p0, Lmiui/widget/NumberPicker$d;->aC:Lmiui/widget/NumberPicker;

    invoke-static {v3}, Lmiui/widget/NumberPicker;->e(Lmiui/widget/NumberPicker;)I

    move-result v3

    iget-object v4, p0, Lmiui/widget/NumberPicker$d;->aC:Lmiui/widget/NumberPicker;

    invoke-static {v4}, Lmiui/widget/NumberPicker;->i(Lmiui/widget/NumberPicker;)I

    move-result v4

    sub-int/2addr v3, v4

    invoke-direct {p0, v0, v1, v2, v3}, Lmiui/widget/NumberPicker$d;->a(IIII)Landroid/view/accessibility/AccessibilityNodeInfo;

    move-result-object v0

    goto/16 :goto_0

    .line 2104
    :pswitch_4
    const/4 v1, 0x1

    invoke-direct {p0}, Lmiui/widget/NumberPicker$d;->cn()Ljava/lang/String;

    move-result-object v2

    iget-object v0, p0, Lmiui/widget/NumberPicker$d;->aC:Lmiui/widget/NumberPicker;

    invoke-virtual {v0}, Lmiui/widget/NumberPicker;->getScrollX()I

    move-result v3

    iget-object v0, p0, Lmiui/widget/NumberPicker$d;->aC:Lmiui/widget/NumberPicker;

    invoke-static {v0}, Lmiui/widget/NumberPicker;->e(Lmiui/widget/NumberPicker;)I

    move-result v0

    iget-object v4, p0, Lmiui/widget/NumberPicker$d;->aC:Lmiui/widget/NumberPicker;

    invoke-static {v4}, Lmiui/widget/NumberPicker;->i(Lmiui/widget/NumberPicker;)I

    move-result v4

    sub-int v4, v0, v4

    iget-object v0, p0, Lmiui/widget/NumberPicker$d;->aC:Lmiui/widget/NumberPicker;

    invoke-virtual {v0}, Lmiui/widget/NumberPicker;->getScrollX()I

    move-result v0

    iget-object v5, p0, Lmiui/widget/NumberPicker$d;->aC:Lmiui/widget/NumberPicker;

    invoke-virtual {v5}, Lmiui/widget/NumberPicker;->getRight()I

    move-result v5

    iget-object v6, p0, Lmiui/widget/NumberPicker$d;->aC:Lmiui/widget/NumberPicker;

    invoke-virtual {v6}, Lmiui/widget/NumberPicker;->getLeft()I

    move-result v6

    sub-int/2addr v5, v6

    add-int/2addr v5, v0

    iget-object v0, p0, Lmiui/widget/NumberPicker$d;->aC:Lmiui/widget/NumberPicker;

    invoke-virtual {v0}, Lmiui/widget/NumberPicker;->getScrollY()I

    move-result v0

    iget-object v6, p0, Lmiui/widget/NumberPicker$d;->aC:Lmiui/widget/NumberPicker;

    invoke-virtual {v6}, Lmiui/widget/NumberPicker;->getBottom()I

    move-result v6

    iget-object v7, p0, Lmiui/widget/NumberPicker$d;->aC:Lmiui/widget/NumberPicker;

    invoke-virtual {v7}, Lmiui/widget/NumberPicker;->getTop()I

    move-result v7

    sub-int/2addr v6, v7

    add-int/2addr v6, v0

    move-object v0, p0

    invoke-direct/range {v0 .. v6}, Lmiui/widget/NumberPicker$d;->a(ILjava/lang/String;IIII)Landroid/view/accessibility/AccessibilityNodeInfo;

    move-result-object v0

    goto/16 :goto_0

    .line 2089
    nop

    :pswitch_data_0
    .packed-switch -0x1
        :pswitch_1
        :pswitch_0
        :pswitch_4
        :pswitch_3
        :pswitch_2
    .end packed-switch
.end method

.method public findAccessibilityNodeInfosByText(Ljava/lang/String;I)Ljava/util/List;
    .locals 3
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Ljava/lang/String;",
            "I)",
            "Ljava/util/List",
            "<",
            "Landroid/view/accessibility/AccessibilityNodeInfo;",
            ">;"
        }
    .end annotation

    .prologue
    .line 2115
    invoke-static {p1}, Landroid/text/TextUtils;->isEmpty(Ljava/lang/CharSequence;)Z

    move-result v0

    if-eqz v0, :cond_0

    .line 2116
    invoke-static {}, Ljava/util/Collections;->emptyList()Ljava/util/List;

    move-result-object v0

    .line 2138
    :goto_0
    return-object v0

    .line 2118
    :cond_0
    invoke-virtual {p1}, Ljava/lang/String;->toLowerCase()Ljava/lang/String;

    move-result-object v1

    .line 2119
    new-instance v0, Ljava/util/ArrayList;

    invoke-direct {v0}, Ljava/util/ArrayList;-><init>()V

    .line 2120
    packed-switch p2, :pswitch_data_0

    .line 2138
    :pswitch_0
    invoke-super {p0, p1, p2}, Landroid/view/accessibility/AccessibilityNodeProvider;->findAccessibilityNodeInfosByText(Ljava/lang/String;I)Ljava/util/List;

    move-result-object v0

    goto :goto_0

    .line 2122
    :pswitch_1
    const/4 v2, 0x3

    invoke-direct {p0, v1, v2, v0}, Lmiui/widget/NumberPicker$d;->a(Ljava/lang/String;ILjava/util/List;)V

    .line 2124
    const/4 v2, 0x2

    invoke-direct {p0, v1, v2, v0}, Lmiui/widget/NumberPicker$d;->a(Ljava/lang/String;ILjava/util/List;)V

    .line 2126
    const/4 v2, 0x1

    invoke-direct {p0, v1, v2, v0}, Lmiui/widget/NumberPicker$d;->a(Ljava/lang/String;ILjava/util/List;)V

    goto :goto_0

    .line 2133
    :pswitch_2
    invoke-direct {p0, v1, p2, v0}, Lmiui/widget/NumberPicker$d;->a(Ljava/lang/String;ILjava/util/List;)V

    goto :goto_0

    .line 2120
    nop

    :pswitch_data_0
    .packed-switch -0x1
        :pswitch_1
        :pswitch_0
        :pswitch_2
        :pswitch_2
        :pswitch_2
    .end packed-switch
.end method

.method public h(II)V
    .locals 1

    .prologue
    .line 2284
    packed-switch p1, :pswitch_data_0

    .line 2301
    :cond_0
    :goto_0
    return-void

    .line 2286
    :pswitch_0
    invoke-direct {p0}, Lmiui/widget/NumberPicker$d;->ck()Z

    move-result v0

    if-eqz v0, :cond_0

    .line 2287
    invoke-direct {p0}, Lmiui/widget/NumberPicker$d;->cm()Ljava/lang/String;

    move-result-object v0

    invoke-direct {p0, p1, p2, v0}, Lmiui/widget/NumberPicker$d;->a(IILjava/lang/String;)V

    goto :goto_0

    .line 2292
    :pswitch_1
    invoke-direct {p0, p2}, Lmiui/widget/NumberPicker$d;->Q(I)V

    goto :goto_0

    .line 2295
    :pswitch_2
    invoke-direct {p0}, Lmiui/widget/NumberPicker$d;->cl()Z

    move-result v0

    if-eqz v0, :cond_0

    .line 2296
    invoke-direct {p0}, Lmiui/widget/NumberPicker$d;->cn()Ljava/lang/String;

    move-result-object v0

    invoke-direct {p0, p1, p2, v0}, Lmiui/widget/NumberPicker$d;->a(IILjava/lang/String;)V

    goto :goto_0

    .line 2284
    :pswitch_data_0
    .packed-switch 0x1
        :pswitch_2
        :pswitch_1
        :pswitch_0
    .end packed-switch
.end method

.method public performAction(IILandroid/os/Bundle;)Z
    .locals 6

    .prologue
    const/high16 v5, 0x10000

    const v4, 0x8000

    const/high16 v3, -0x80000000

    const/4 v1, 0x1

    const/4 v0, 0x0

    .line 2143
    packed-switch p1, :pswitch_data_0

    .line 2280
    :goto_0
    :pswitch_0
    invoke-super {p0, p1, p2, p3}, Landroid/view/accessibility/AccessibilityNodeProvider;->performAction(IILandroid/os/Bundle;)Z

    move-result v0

    :cond_0
    :goto_1
    return v0

    .line 2145
    :pswitch_1
    sparse-switch p2, :sswitch_data_0

    goto :goto_0

    .line 2147
    :sswitch_0
    iget v2, p0, Lmiui/widget/NumberPicker$d;->xa:I

    if-eq v2, p1, :cond_0

    .line 2148
    iput p1, p0, Lmiui/widget/NumberPicker$d;->xa:I

    move v0, v1

    .line 2149
    goto :goto_1

    .line 2153
    :sswitch_1
    iget v2, p0, Lmiui/widget/NumberPicker$d;->xa:I

    if-ne v2, p1, :cond_0

    .line 2154
    iput v3, p0, Lmiui/widget/NumberPicker$d;->xa:I

    move v0, v1

    .line 2155
    goto :goto_1

    .line 2160
    :sswitch_2
    iget-object v2, p0, Lmiui/widget/NumberPicker$d;->aC:Lmiui/widget/NumberPicker;

    invoke-virtual {v2}, Lmiui/widget/NumberPicker;->isEnabled()Z

    move-result v2

    if-eqz v2, :cond_0

    iget-object v2, p0, Lmiui/widget/NumberPicker$d;->aC:Lmiui/widget/NumberPicker;

    invoke-virtual {v2}, Lmiui/widget/NumberPicker;->getWrapSelectorWheel()Z

    move-result v2

    if-nez v2, :cond_1

    iget-object v2, p0, Lmiui/widget/NumberPicker$d;->aC:Lmiui/widget/NumberPicker;

    invoke-virtual {v2}, Lmiui/widget/NumberPicker;->getValue()I

    move-result v2

    iget-object v3, p0, Lmiui/widget/NumberPicker$d;->aC:Lmiui/widget/NumberPicker;

    invoke-virtual {v3}, Lmiui/widget/NumberPicker;->getMaxValue()I

    move-result v3

    if-ge v2, v3, :cond_0

    .line 2162
    :cond_1
    iget-object v0, p0, Lmiui/widget/NumberPicker$d;->aC:Lmiui/widget/NumberPicker;

    invoke-static {v0, v1}, Lmiui/widget/NumberPicker;->c(Lmiui/widget/NumberPicker;Z)V

    move v0, v1

    .line 2163
    goto :goto_1

    .line 2167
    :sswitch_3
    iget-object v2, p0, Lmiui/widget/NumberPicker$d;->aC:Lmiui/widget/NumberPicker;

    invoke-virtual {v2}, Lmiui/widget/NumberPicker;->isEnabled()Z

    move-result v2

    if-eqz v2, :cond_0

    iget-object v2, p0, Lmiui/widget/NumberPicker$d;->aC:Lmiui/widget/NumberPicker;

    invoke-virtual {v2}, Lmiui/widget/NumberPicker;->getWrapSelectorWheel()Z

    move-result v2

    if-nez v2, :cond_2

    iget-object v2, p0, Lmiui/widget/NumberPicker$d;->aC:Lmiui/widget/NumberPicker;

    invoke-virtual {v2}, Lmiui/widget/NumberPicker;->getValue()I

    move-result v2

    iget-object v3, p0, Lmiui/widget/NumberPicker$d;->aC:Lmiui/widget/NumberPicker;

    invoke-virtual {v3}, Lmiui/widget/NumberPicker;->getMinValue()I

    move-result v3

    if-le v2, v3, :cond_0

    .line 2169
    :cond_2
    iget-object v2, p0, Lmiui/widget/NumberPicker$d;->aC:Lmiui/widget/NumberPicker;

    invoke-static {v2, v0}, Lmiui/widget/NumberPicker;->c(Lmiui/widget/NumberPicker;Z)V

    move v0, v1

    .line 2170
    goto :goto_1

    .line 2176
    :pswitch_2
    sparse-switch p2, :sswitch_data_1

    .line 2214
    iget-object v0, p0, Lmiui/widget/NumberPicker$d;->aC:Lmiui/widget/NumberPicker;

    invoke-static {v0}, Lmiui/widget/NumberPicker;->a(Lmiui/widget/NumberPicker;)Landroid/widget/EditText;

    move-result-object v0

    invoke-virtual {v0, p2, p3}, Landroid/widget/EditText;->performAccessibilityAction(ILandroid/os/Bundle;)Z

    move-result v0

    goto :goto_1

    .line 2178
    :sswitch_4
    iget-object v1, p0, Lmiui/widget/NumberPicker$d;->aC:Lmiui/widget/NumberPicker;

    invoke-virtual {v1}, Lmiui/widget/NumberPicker;->isEnabled()Z

    move-result v1

    if-eqz v1, :cond_0

    iget-object v1, p0, Lmiui/widget/NumberPicker$d;->aC:Lmiui/widget/NumberPicker;

    invoke-static {v1}, Lmiui/widget/NumberPicker;->a(Lmiui/widget/NumberPicker;)Landroid/widget/EditText;

    move-result-object v1

    invoke-virtual {v1}, Landroid/widget/EditText;->isFocused()Z

    move-result v1

    if-nez v1, :cond_0

    .line 2179
    iget-object v0, p0, Lmiui/widget/NumberPicker$d;->aC:Lmiui/widget/NumberPicker;

    invoke-static {v0}, Lmiui/widget/NumberPicker;->a(Lmiui/widget/NumberPicker;)Landroid/widget/EditText;

    move-result-object v0

    invoke-virtual {v0}, Landroid/widget/EditText;->requestFocus()Z

    move-result v0

    goto/16 :goto_1

    .line 2183
    :sswitch_5
    iget-object v2, p0, Lmiui/widget/NumberPicker$d;->aC:Lmiui/widget/NumberPicker;

    invoke-virtual {v2}, Lmiui/widget/NumberPicker;->isEnabled()Z

    move-result v2

    if-eqz v2, :cond_0

    iget-object v2, p0, Lmiui/widget/NumberPicker$d;->aC:Lmiui/widget/NumberPicker;

    invoke-static {v2}, Lmiui/widget/NumberPicker;->a(Lmiui/widget/NumberPicker;)Landroid/widget/EditText;

    move-result-object v2

    invoke-virtual {v2}, Landroid/widget/EditText;->isFocused()Z

    move-result v2

    if-eqz v2, :cond_0

    .line 2184
    iget-object v0, p0, Lmiui/widget/NumberPicker$d;->aC:Lmiui/widget/NumberPicker;

    invoke-static {v0}, Lmiui/widget/NumberPicker;->a(Lmiui/widget/NumberPicker;)Landroid/widget/EditText;

    move-result-object v0

    invoke-virtual {v0}, Landroid/widget/EditText;->clearFocus()V

    move v0, v1

    .line 2185
    goto/16 :goto_1

    .line 2190
    :sswitch_6
    iget-object v2, p0, Lmiui/widget/NumberPicker$d;->aC:Lmiui/widget/NumberPicker;

    invoke-virtual {v2}, Lmiui/widget/NumberPicker;->isEnabled()Z

    move-result v2

    if-eqz v2, :cond_0

    move v0, v1

    .line 2191
    goto/16 :goto_1

    .line 2196
    :sswitch_7
    iget v2, p0, Lmiui/widget/NumberPicker$d;->xa:I

    if-eq v2, p1, :cond_0

    .line 2197
    iput p1, p0, Lmiui/widget/NumberPicker$d;->xa:I

    .line 2198
    invoke-virtual {p0, p1, v4}, Lmiui/widget/NumberPicker$d;->h(II)V

    .line 2200
    iget-object v0, p0, Lmiui/widget/NumberPicker$d;->aC:Lmiui/widget/NumberPicker;

    invoke-static {v0}, Lmiui/widget/NumberPicker;->a(Lmiui/widget/NumberPicker;)Landroid/widget/EditText;

    move-result-object v0

    invoke-virtual {v0}, Landroid/widget/EditText;->invalidate()V

    move v0, v1

    .line 2201
    goto/16 :goto_1

    .line 2205
    :sswitch_8
    iget v2, p0, Lmiui/widget/NumberPicker$d;->xa:I

    if-ne v2, p1, :cond_0

    .line 2206
    iput v3, p0, Lmiui/widget/NumberPicker$d;->xa:I

    .line 2207
    invoke-virtual {p0, p1, v5}, Lmiui/widget/NumberPicker$d;->h(II)V

    .line 2209
    iget-object v0, p0, Lmiui/widget/NumberPicker$d;->aC:Lmiui/widget/NumberPicker;

    invoke-static {v0}, Lmiui/widget/NumberPicker;->a(Lmiui/widget/NumberPicker;)Landroid/widget/EditText;

    move-result-object v0

    invoke-virtual {v0}, Landroid/widget/EditText;->invalidate()V

    move v0, v1

    .line 2210
    goto/16 :goto_1

    .line 2219
    :pswitch_3
    sparse-switch p2, :sswitch_data_2

    goto/16 :goto_1

    .line 2221
    :sswitch_9
    iget-object v2, p0, Lmiui/widget/NumberPicker$d;->aC:Lmiui/widget/NumberPicker;

    invoke-virtual {v2}, Lmiui/widget/NumberPicker;->isEnabled()Z

    move-result v2

    if-eqz v2, :cond_0

    .line 2222
    iget-object v0, p0, Lmiui/widget/NumberPicker$d;->aC:Lmiui/widget/NumberPicker;

    invoke-static {v0, v1}, Lmiui/widget/NumberPicker;->c(Lmiui/widget/NumberPicker;Z)V

    .line 2223
    invoke-virtual {p0, p1, v1}, Lmiui/widget/NumberPicker$d;->h(II)V

    move v0, v1

    .line 2225
    goto/16 :goto_1

    .line 2229
    :sswitch_a
    iget v2, p0, Lmiui/widget/NumberPicker$d;->xa:I

    if-eq v2, p1, :cond_0

    .line 2230
    iput p1, p0, Lmiui/widget/NumberPicker$d;->xa:I

    .line 2231
    invoke-virtual {p0, p1, v4}, Lmiui/widget/NumberPicker$d;->h(II)V

    .line 2233
    iget-object v2, p0, Lmiui/widget/NumberPicker$d;->aC:Lmiui/widget/NumberPicker;

    iget-object v3, p0, Lmiui/widget/NumberPicker$d;->aC:Lmiui/widget/NumberPicker;

    invoke-static {v3}, Lmiui/widget/NumberPicker;->e(Lmiui/widget/NumberPicker;)I

    move-result v3

    iget-object v4, p0, Lmiui/widget/NumberPicker$d;->aC:Lmiui/widget/NumberPicker;

    invoke-virtual {v4}, Lmiui/widget/NumberPicker;->getRight()I

    move-result v4

    iget-object v5, p0, Lmiui/widget/NumberPicker$d;->aC:Lmiui/widget/NumberPicker;

    invoke-virtual {v5}, Lmiui/widget/NumberPicker;->getBottom()I

    move-result v5

    invoke-virtual {v2, v0, v3, v4, v5}, Lmiui/widget/NumberPicker;->invalidate(IIII)V

    move v0, v1

    .line 2234
    goto/16 :goto_1

    .line 2238
    :sswitch_b
    iget v2, p0, Lmiui/widget/NumberPicker$d;->xa:I

    if-ne v2, p1, :cond_0

    .line 2239
    iput v3, p0, Lmiui/widget/NumberPicker$d;->xa:I

    .line 2240
    invoke-virtual {p0, p1, v5}, Lmiui/widget/NumberPicker$d;->h(II)V

    .line 2242
    iget-object v2, p0, Lmiui/widget/NumberPicker$d;->aC:Lmiui/widget/NumberPicker;

    iget-object v3, p0, Lmiui/widget/NumberPicker$d;->aC:Lmiui/widget/NumberPicker;

    invoke-static {v3}, Lmiui/widget/NumberPicker;->e(Lmiui/widget/NumberPicker;)I

    move-result v3

    iget-object v4, p0, Lmiui/widget/NumberPicker$d;->aC:Lmiui/widget/NumberPicker;

    invoke-virtual {v4}, Lmiui/widget/NumberPicker;->getRight()I

    move-result v4

    iget-object v5, p0, Lmiui/widget/NumberPicker$d;->aC:Lmiui/widget/NumberPicker;

    invoke-virtual {v5}, Lmiui/widget/NumberPicker;->getBottom()I

    move-result v5

    invoke-virtual {v2, v0, v3, v4, v5}, Lmiui/widget/NumberPicker;->invalidate(IIII)V

    move v0, v1

    .line 2243
    goto/16 :goto_1

    .line 2249
    :pswitch_4
    sparse-switch p2, :sswitch_data_3

    goto/16 :goto_1

    .line 2251
    :sswitch_c
    iget-object v2, p0, Lmiui/widget/NumberPicker$d;->aC:Lmiui/widget/NumberPicker;

    invoke-virtual {v2}, Lmiui/widget/NumberPicker;->isEnabled()Z

    move-result v2

    if-eqz v2, :cond_0

    .line 2252
    if-ne p1, v1, :cond_3

    move v0, v1

    .line 2253
    :cond_3
    iget-object v2, p0, Lmiui/widget/NumberPicker$d;->aC:Lmiui/widget/NumberPicker;

    invoke-static {v2, v0}, Lmiui/widget/NumberPicker;->c(Lmiui/widget/NumberPicker;Z)V

    .line 2254
    invoke-virtual {p0, p1, v1}, Lmiui/widget/NumberPicker$d;->h(II)V

    move v0, v1

    .line 2256
    goto/16 :goto_1

    .line 2260
    :sswitch_d
    iget v2, p0, Lmiui/widget/NumberPicker$d;->xa:I

    if-eq v2, p1, :cond_0

    .line 2261
    iput p1, p0, Lmiui/widget/NumberPicker$d;->xa:I

    .line 2262
    invoke-virtual {p0, p1, v4}, Lmiui/widget/NumberPicker$d;->h(II)V

    .line 2264
    iget-object v2, p0, Lmiui/widget/NumberPicker$d;->aC:Lmiui/widget/NumberPicker;

    iget-object v3, p0, Lmiui/widget/NumberPicker$d;->aC:Lmiui/widget/NumberPicker;

    invoke-virtual {v3}, Lmiui/widget/NumberPicker;->getRight()I

    move-result v3

    iget-object v4, p0, Lmiui/widget/NumberPicker$d;->aC:Lmiui/widget/NumberPicker;

    invoke-static {v4}, Lmiui/widget/NumberPicker;->g(Lmiui/widget/NumberPicker;)I

    move-result v4

    invoke-virtual {v2, v0, v0, v3, v4}, Lmiui/widget/NumberPicker;->invalidate(IIII)V

    move v0, v1

    .line 2265
    goto/16 :goto_1

    .line 2269
    :sswitch_e
    iget v2, p0, Lmiui/widget/NumberPicker$d;->xa:I

    if-ne v2, p1, :cond_0

    .line 2270
    iput v3, p0, Lmiui/widget/NumberPicker$d;->xa:I

    .line 2271
    invoke-virtual {p0, p1, v5}, Lmiui/widget/NumberPicker$d;->h(II)V

    .line 2273
    iget-object v2, p0, Lmiui/widget/NumberPicker$d;->aC:Lmiui/widget/NumberPicker;

    iget-object v3, p0, Lmiui/widget/NumberPicker$d;->aC:Lmiui/widget/NumberPicker;

    invoke-virtual {v3}, Lmiui/widget/NumberPicker;->getRight()I

    move-result v3

    iget-object v4, p0, Lmiui/widget/NumberPicker$d;->aC:Lmiui/widget/NumberPicker;

    invoke-static {v4}, Lmiui/widget/NumberPicker;->g(Lmiui/widget/NumberPicker;)I

    move-result v4

    invoke-virtual {v2, v0, v0, v3, v4}, Lmiui/widget/NumberPicker;->invalidate(IIII)V

    move v0, v1

    .line 2274
    goto/16 :goto_1

    .line 2143
    nop

    :pswitch_data_0
    .packed-switch -0x1
        :pswitch_1
        :pswitch_0
        :pswitch_3
        :pswitch_2
        :pswitch_4
    .end packed-switch

    .line 2145
    :sswitch_data_0
    .sparse-switch
        0x40 -> :sswitch_0
        0x80 -> :sswitch_1
        0x1000 -> :sswitch_2
        0x2000 -> :sswitch_3
    .end sparse-switch

    .line 2176
    :sswitch_data_1
    .sparse-switch
        0x1 -> :sswitch_4
        0x2 -> :sswitch_5
        0x10 -> :sswitch_6
        0x40 -> :sswitch_7
        0x80 -> :sswitch_8
    .end sparse-switch

    .line 2219
    :sswitch_data_2
    .sparse-switch
        0x10 -> :sswitch_9
        0x40 -> :sswitch_a
        0x80 -> :sswitch_b
    .end sparse-switch

    .line 2249
    :sswitch_data_3
    .sparse-switch
        0x10 -> :sswitch_c
        0x40 -> :sswitch_d
        0x80 -> :sswitch_e
    .end sparse-switch
.end method
