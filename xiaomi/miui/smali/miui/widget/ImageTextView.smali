.class public Lmiui/widget/ImageTextView;
.super Landroid/widget/LinearLayout;
.source "SourceFile"


# static fields
.field private static final pt:[I

.field private static final pu:I = 0x0

.field private static final pv:I = 0x1


# instance fields
.field private final ps:Landroid/widget/LinearLayout$LayoutParams;

.field private pw:Ljava/util/HashMap;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Ljava/util/HashMap",
            "<",
            "Ljava/lang/Character;",
            "Ljava/lang/Integer;",
            ">;"
        }
    .end annotation
.end field

.field private px:Ljava/lang/CharSequence;


# direct methods
.method static constructor <clinit>()V
    .locals 1

    .prologue
    .line 26
    const/4 v0, 0x2

    new-array v0, v0, [I

    fill-array-data v0, :array_0

    sput-object v0, Lmiui/widget/ImageTextView;->pt:[I

    return-void

    nop

    :array_0
    .array-data 4
        0x10100b2
        0x10101f8
    .end array-data
.end method

.method public constructor <init>(Landroid/content/Context;)V
    .locals 1

    .prologue
    .line 37
    const/4 v0, 0x0

    invoke-direct {p0, p1, v0}, Lmiui/widget/ImageTextView;-><init>(Landroid/content/Context;Landroid/util/AttributeSet;)V

    .line 38
    return-void
.end method

.method public constructor <init>(Landroid/content/Context;Landroid/util/AttributeSet;)V
    .locals 1

    .prologue
    .line 41
    const/4 v0, 0x0

    invoke-direct {p0, p1, p2, v0}, Lmiui/widget/ImageTextView;-><init>(Landroid/content/Context;Landroid/util/AttributeSet;I)V

    .line 42
    return-void
.end method

.method public constructor <init>(Landroid/content/Context;Landroid/util/AttributeSet;I)V
    .locals 8

    .prologue
    const/4 v2, -0x2

    const/4 v1, 0x0

    .line 45
    invoke-direct {p0, p1, p2, p3}, Landroid/widget/LinearLayout;-><init>(Landroid/content/Context;Landroid/util/AttributeSet;I)V

    .line 23
    new-instance v0, Landroid/widget/LinearLayout$LayoutParams;

    invoke-direct {v0, v2, v2}, Landroid/widget/LinearLayout$LayoutParams;-><init>(II)V

    iput-object v0, p0, Lmiui/widget/ImageTextView;->ps:Landroid/widget/LinearLayout$LayoutParams;

    .line 46
    invoke-virtual {p0, v1}, Lmiui/widget/ImageTextView;->setOrientation(I)V

    .line 48
    sget-object v0, Lmiui/widget/ImageTextView;->pt:[I

    invoke-virtual {p1, p2, v0, p3, v1}, Landroid/content/Context;->obtainStyledAttributes(Landroid/util/AttributeSet;[III)Landroid/content/res/TypedArray;

    move-result-object v2

    .line 49
    invoke-virtual {v2, v1}, Landroid/content/res/TypedArray;->getTextArray(I)[Ljava/lang/CharSequence;

    move-result-object v3

    .line 50
    invoke-direct {p0, v2}, Lmiui/widget/ImageTextView;->a(Landroid/content/res/TypedArray;)[Ljava/lang/Integer;

    move-result-object v4

    .line 52
    if-eqz v3, :cond_0

    if-eqz v4, :cond_0

    .line 53
    array-length v0, v3

    array-length v5, v4

    if-ne v0, v5, :cond_0

    .line 54
    new-instance v0, Ljava/util/HashMap;

    invoke-direct {v0}, Ljava/util/HashMap;-><init>()V

    iput-object v0, p0, Lmiui/widget/ImageTextView;->pw:Ljava/util/HashMap;

    move v0, v1

    .line 55
    :goto_0
    array-length v5, v3

    if-ge v0, v5, :cond_0

    .line 56
    iget-object v5, p0, Lmiui/widget/ImageTextView;->pw:Ljava/util/HashMap;

    aget-object v6, v3, v0

    invoke-interface {v6, v1}, Ljava/lang/CharSequence;->charAt(I)C

    move-result v6

    invoke-static {v6}, Ljava/lang/Character;->valueOf(C)Ljava/lang/Character;

    move-result-object v6

    aget-object v7, v4, v0

    invoke-virtual {v5, v6, v7}, Ljava/util/HashMap;->put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    .line 55
    add-int/lit8 v0, v0, 0x1

    goto :goto_0

    .line 60
    :cond_0
    invoke-virtual {v2}, Landroid/content/res/TypedArray;->recycle()V

    .line 61
    return-void
.end method

.method private a(Landroid/content/res/TypedArray;)[Ljava/lang/Integer;
    .locals 4

    .prologue
    .line 64
    new-instance v1, Landroid/util/TypedValue;

    invoke-direct {v1}, Landroid/util/TypedValue;-><init>()V

    .line 65
    const/4 v0, 0x0

    .line 66
    const/4 v2, 0x1

    invoke-virtual {p1, v2, v1}, Landroid/content/res/TypedArray;->getValue(ILandroid/util/TypedValue;)Z

    move-result v2

    if-eqz v2, :cond_1

    .line 67
    invoke-virtual {p0}, Lmiui/widget/ImageTextView;->getContext()Landroid/content/Context;

    move-result-object v0

    invoke-virtual {v0}, Landroid/content/Context;->getResources()Landroid/content/res/Resources;

    move-result-object v0

    .line 68
    iget v1, v1, Landroid/util/TypedValue;->resourceId:I

    invoke-virtual {v0, v1}, Landroid/content/res/Resources;->obtainTypedArray(I)Landroid/content/res/TypedArray;

    move-result-object v2

    .line 69
    invoke-virtual {v2}, Landroid/content/res/TypedArray;->length()I

    move-result v0

    new-array v1, v0, [Ljava/lang/Integer;

    .line 70
    const/4 v0, 0x0

    :goto_0
    invoke-virtual {v2}, Landroid/content/res/TypedArray;->length()I

    move-result v3

    if-ge v0, v3, :cond_0

    .line 71
    invoke-virtual {v2, v0}, Landroid/content/res/TypedArray;->peekValue(I)Landroid/util/TypedValue;

    move-result-object v3

    iget v3, v3, Landroid/util/TypedValue;->resourceId:I

    invoke-static {v3}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object v3

    aput-object v3, v1, v0

    .line 70
    add-int/lit8 v0, v0, 0x1

    goto :goto_0

    :cond_0
    move-object v0, v1

    .line 74
    :cond_1
    return-object v0
.end method


# virtual methods
.method public getText()Ljava/lang/CharSequence;
    .locals 1

    .prologue
    .line 92
    iget-object v0, p0, Lmiui/widget/ImageTextView;->px:Ljava/lang/CharSequence;

    if-nez v0, :cond_0

    const-string v0, ""

    :goto_0
    return-object v0

    :cond_0
    iget-object v0, p0, Lmiui/widget/ImageTextView;->px:Ljava/lang/CharSequence;

    goto :goto_0
.end method

.method public setCharMap(Ljava/util/HashMap;)V
    .locals 0
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Ljava/util/HashMap",
            "<",
            "Ljava/lang/Character;",
            "Ljava/lang/Integer;",
            ">;)V"
        }
    .end annotation

    .prologue
    .line 83
    iput-object p1, p0, Lmiui/widget/ImageTextView;->pw:Ljava/util/HashMap;

    .line 84
    return-void
.end method

.method public setText(Ljava/lang/CharSequence;)V
    .locals 4

    .prologue
    .line 101
    iget-object v0, p0, Lmiui/widget/ImageTextView;->pw:Ljava/util/HashMap;

    if-eqz v0, :cond_0

    if-eqz p1, :cond_0

    iget-object v0, p0, Lmiui/widget/ImageTextView;->px:Ljava/lang/CharSequence;

    invoke-virtual {p1, v0}, Ljava/lang/Object;->equals(Ljava/lang/Object;)Z

    move-result v0

    if-eqz v0, :cond_1

    .line 123
    :cond_0
    return-void

    .line 105
    :cond_1
    iput-object p1, p0, Lmiui/widget/ImageTextView;->px:Ljava/lang/CharSequence;

    .line 106
    const/4 v0, 0x0

    move v1, v0

    :goto_0
    invoke-interface {p1}, Ljava/lang/CharSequence;->length()I

    move-result v0

    if-ge v1, v0, :cond_4

    .line 108
    invoke-virtual {p0}, Lmiui/widget/ImageTextView;->getChildCount()I

    move-result v0

    if-ge v1, v0, :cond_3

    .line 109
    invoke-virtual {p0, v1}, Lmiui/widget/ImageTextView;->getChildAt(I)Landroid/view/View;

    move-result-object v0

    move-object v2, v0

    .line 115
    :goto_1
    iget-object v0, p0, Lmiui/widget/ImageTextView;->pw:Ljava/util/HashMap;

    invoke-interface {p1, v1}, Ljava/lang/CharSequence;->charAt(I)C

    move-result v3

    invoke-static {v3}, Ljava/lang/Character;->valueOf(C)Ljava/lang/Character;

    move-result-object v3

    invoke-virtual {v0, v3}, Ljava/util/HashMap;->get(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Ljava/lang/Integer;

    .line 116
    if-eqz v0, :cond_2

    .line 117
    invoke-virtual {p0}, Lmiui/widget/ImageTextView;->getResources()Landroid/content/res/Resources;

    move-result-object v3

    invoke-virtual {v0}, Ljava/lang/Integer;->intValue()I

    move-result v0

    invoke-virtual {v3, v0}, Landroid/content/res/Resources;->getDrawable(I)Landroid/graphics/drawable/Drawable;

    move-result-object v0

    invoke-virtual {v2, v0}, Landroid/view/View;->setBackground(Landroid/graphics/drawable/Drawable;)V

    .line 106
    :cond_2
    add-int/lit8 v0, v1, 0x1

    move v1, v0

    goto :goto_0

    .line 111
    :cond_3
    new-instance v0, Landroid/widget/ImageView;

    invoke-virtual {p0}, Lmiui/widget/ImageTextView;->getContext()Landroid/content/Context;

    move-result-object v2

    invoke-direct {v0, v2}, Landroid/widget/ImageView;-><init>(Landroid/content/Context;)V

    .line 112
    iget-object v2, p0, Lmiui/widget/ImageTextView;->ps:Landroid/widget/LinearLayout$LayoutParams;

    invoke-virtual {v0, v2}, Landroid/view/View;->setLayoutParams(Landroid/view/ViewGroup$LayoutParams;)V

    .line 113
    invoke-virtual {p0, v0}, Lmiui/widget/ImageTextView;->addView(Landroid/view/View;)V

    move-object v2, v0

    goto :goto_1

    .line 120
    :cond_4
    invoke-virtual {p0}, Lmiui/widget/ImageTextView;->getChildCount()I

    move-result v0

    :goto_2
    invoke-interface {p1}, Ljava/lang/CharSequence;->length()I

    move-result v1

    if-le v0, v1, :cond_0

    .line 121
    add-int/lit8 v1, v0, -0x1

    invoke-virtual {p0, v1}, Lmiui/widget/ImageTextView;->removeViewAt(I)V

    .line 120
    add-int/lit8 v0, v0, -0x1

    goto :goto_2
.end method
