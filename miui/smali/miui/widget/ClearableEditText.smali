.class public Lmiui/widget/ClearableEditText;
.super Landroid/widget/EditText;
.source "SourceFile"


# static fields
.field private static final EMPTY_STATE_SET:[I


# instance fields
.field private Sc:Z

.field private Sd:Z

.field private ph:Landroid/graphics/drawable/Drawable;


# direct methods
.method static constructor <clinit>()V
    .locals 3

    .prologue
    .line 22
    const/4 v0, 0x1

    new-array v0, v0, [I

    const/4 v1, 0x0

    const v2, 0x10100a9

    aput v2, v0, v1

    sput-object v0, Lmiui/widget/ClearableEditText;->EMPTY_STATE_SET:[I

    return-void
.end method

.method public constructor <init>(Landroid/content/Context;)V
    .locals 1

    .prologue
    .line 33
    const/4 v0, 0x0

    invoke-direct {p0, p1, v0}, Lmiui/widget/ClearableEditText;-><init>(Landroid/content/Context;Landroid/util/AttributeSet;)V

    .line 34
    return-void
.end method

.method public constructor <init>(Landroid/content/Context;Landroid/util/AttributeSet;)V
    .locals 1

    .prologue
    .line 37
    sget v0, Lcom/miui/internal/R$attr;->editTextSearchStyle:I

    invoke-direct {p0, p1, p2, v0}, Lmiui/widget/ClearableEditText;-><init>(Landroid/content/Context;Landroid/util/AttributeSet;I)V

    .line 38
    return-void
.end method

.method public constructor <init>(Landroid/content/Context;Landroid/util/AttributeSet;I)V
    .locals 3

    .prologue
    const/4 v2, 0x2

    .line 41
    invoke-direct {p0, p1, p2, p3}, Landroid/widget/EditText;-><init>(Landroid/content/Context;Landroid/util/AttributeSet;I)V

    .line 43
    invoke-virtual {p0}, Lmiui/widget/ClearableEditText;->getCompoundDrawables()[Landroid/graphics/drawable/Drawable;

    move-result-object v0

    .line 44
    const/4 v1, 0x0

    aget-object v1, v0, v1

    if-nez v1, :cond_0

    const/4 v1, 0x1

    aget-object v1, v0, v1

    if-nez v1, :cond_0

    aget-object v1, v0, v2

    if-eqz v1, :cond_0

    const/4 v1, 0x3

    aget-object v1, v0, v1

    if-eqz v1, :cond_1

    .line 45
    :cond_0
    new-instance v0, Ljava/lang/IllegalStateException;

    const-string v1, "ClearableEditText need only drawableRight"

    invoke-direct {v0, v1}, Ljava/lang/IllegalStateException;-><init>(Ljava/lang/String;)V

    throw v0

    .line 47
    :cond_1
    aget-object v0, v0, v2

    iput-object v0, p0, Lmiui/widget/ClearableEditText;->ph:Landroid/graphics/drawable/Drawable;

    .line 49
    new-instance v0, Lmiui/widget/ClearableEditText$1;

    invoke-direct {v0, p0}, Lmiui/widget/ClearableEditText$1;-><init>(Lmiui/widget/ClearableEditText;)V

    invoke-virtual {p0, v0}, Lmiui/widget/ClearableEditText;->addTextChangedListener(Landroid/text/TextWatcher;)V

    .line 64
    return-void
.end method

.method static synthetic a(Lmiui/widget/ClearableEditText;Z)Z
    .locals 0

    .prologue
    .line 20
    iput-boolean p1, p0, Lmiui/widget/ClearableEditText;->Sd:Z

    return p1
.end method

.method private e(Landroid/view/MotionEvent;)V
    .locals 1

    .prologue
    .line 123
    invoke-virtual {p1}, Landroid/view/MotionEvent;->getAction()I

    move-result v0

    packed-switch v0, :pswitch_data_0

    .line 141
    :cond_0
    :goto_0
    :pswitch_0
    return-void

    .line 125
    :pswitch_1
    invoke-virtual {p0}, Lmiui/widget/ClearableEditText;->isEnabled()Z

    move-result v0

    if-eqz v0, :cond_0

    iget-boolean v0, p0, Lmiui/widget/ClearableEditText;->Sc:Z

    if-eqz v0, :cond_0

    .line 126
    invoke-direct {p0}, Lmiui/widget/ClearableEditText;->eW()V

    goto :goto_0

    .line 131
    :pswitch_2
    invoke-virtual {p0}, Lmiui/widget/ClearableEditText;->isEnabled()Z

    move-result v0

    if-eqz v0, :cond_0

    iget-boolean v0, p0, Lmiui/widget/ClearableEditText;->Sd:Z

    if-eqz v0, :cond_0

    .line 132
    const/4 v0, 0x1

    iput-boolean v0, p0, Lmiui/widget/ClearableEditText;->Sc:Z

    goto :goto_0

    .line 137
    :pswitch_3
    iget-boolean v0, p0, Lmiui/widget/ClearableEditText;->Sc:Z

    if-eqz v0, :cond_0

    .line 138
    const/4 v0, 0x0

    iput-boolean v0, p0, Lmiui/widget/ClearableEditText;->Sc:Z

    goto :goto_0

    .line 123
    :pswitch_data_0
    .packed-switch 0x0
        :pswitch_2
        :pswitch_1
        :pswitch_0
        :pswitch_3
    .end packed-switch
.end method

.method private eW()V
    .locals 1

    .prologue
    .line 144
    const-string v0, ""

    invoke-virtual {p0, v0}, Lmiui/widget/ClearableEditText;->setText(Ljava/lang/CharSequence;)V

    .line 145
    return-void
.end method


# virtual methods
.method public dispatchTouchEvent(Landroid/view/MotionEvent;)Z
    .locals 3

    .prologue
    .line 77
    iget-boolean v0, p0, Lmiui/widget/ClearableEditText;->Sd:Z

    if-eqz v0, :cond_0

    invoke-virtual {p1}, Landroid/view/MotionEvent;->getX()F

    move-result v0

    invoke-virtual {p0}, Lmiui/widget/ClearableEditText;->getWidth()I

    move-result v1

    invoke-virtual {p0}, Lmiui/widget/ClearableEditText;->getPaddingRight()I

    move-result v2

    sub-int/2addr v1, v2

    iget-object v2, p0, Lmiui/widget/ClearableEditText;->ph:Landroid/graphics/drawable/Drawable;

    invoke-virtual {v2}, Landroid/graphics/drawable/Drawable;->getIntrinsicWidth()I

    move-result v2

    sub-int/2addr v1, v2

    int-to-float v1, v1

    cmpl-float v0, v0, v1

    if-lez v0, :cond_0

    .line 79
    invoke-direct {p0, p1}, Lmiui/widget/ClearableEditText;->e(Landroid/view/MotionEvent;)V

    .line 80
    const/4 v0, 0x1

    .line 83
    :goto_0
    return v0

    :cond_0
    invoke-super {p0, p1}, Landroid/widget/EditText;->dispatchTouchEvent(Landroid/view/MotionEvent;)Z

    move-result v0

    goto :goto_0
.end method

.method protected drawableStateChanged()V
    .locals 2

    .prologue
    .line 97
    invoke-super {p0}, Landroid/widget/EditText;->drawableStateChanged()V

    .line 99
    iget-object v0, p0, Lmiui/widget/ClearableEditText;->ph:Landroid/graphics/drawable/Drawable;

    if-eqz v0, :cond_0

    .line 100
    invoke-virtual {p0}, Lmiui/widget/ClearableEditText;->getDrawableState()[I

    move-result-object v0

    .line 103
    iget-object v1, p0, Lmiui/widget/ClearableEditText;->ph:Landroid/graphics/drawable/Drawable;

    invoke-virtual {v1, v0}, Landroid/graphics/drawable/Drawable;->setState([I)Z

    .line 105
    invoke-virtual {p0}, Lmiui/widget/ClearableEditText;->invalidate()V

    .line 107
    :cond_0
    return-void
.end method

.method public jumpDrawablesToCurrentState()V
    .locals 1

    .prologue
    .line 116
    invoke-super {p0}, Landroid/widget/EditText;->jumpDrawablesToCurrentState()V

    .line 117
    iget-object v0, p0, Lmiui/widget/ClearableEditText;->ph:Landroid/graphics/drawable/Drawable;

    if-eqz v0, :cond_0

    .line 118
    iget-object v0, p0, Lmiui/widget/ClearableEditText;->ph:Landroid/graphics/drawable/Drawable;

    invoke-virtual {v0}, Landroid/graphics/drawable/Drawable;->jumpToCurrentState()V

    .line 120
    :cond_0
    return-void
.end method

.method protected onCreateDrawableState(I)[I
    .locals 2

    .prologue
    .line 88
    add-int/lit8 v0, p1, 0x1

    invoke-super {p0, v0}, Landroid/widget/EditText;->onCreateDrawableState(I)[I

    move-result-object v0

    .line 89
    iget-boolean v1, p0, Lmiui/widget/ClearableEditText;->Sd:Z

    if-nez v1, :cond_0

    .line 90
    sget-object v1, Lmiui/widget/ClearableEditText;->EMPTY_STATE_SET:[I

    invoke-static {v0, v1}, Lmiui/widget/ClearableEditText;->mergeDrawableStates([I[I)[I

    .line 92
    :cond_0
    return-object v0
.end method

.method public setCompoundDrawables(Landroid/graphics/drawable/Drawable;Landroid/graphics/drawable/Drawable;Landroid/graphics/drawable/Drawable;Landroid/graphics/drawable/Drawable;)V
    .locals 2

    .prologue
    const/4 v0, 0x0

    .line 68
    if-nez p1, :cond_0

    if-nez p2, :cond_0

    if-eqz p3, :cond_0

    if-eqz p4, :cond_1

    .line 69
    :cond_0
    new-instance v0, Ljava/lang/IllegalStateException;

    const-string v1, "ClearableEditText need only drawableRight"

    invoke-direct {v0, v1}, Ljava/lang/IllegalStateException;-><init>(Ljava/lang/String;)V

    throw v0

    .line 71
    :cond_1
    iput-object p3, p0, Lmiui/widget/ClearableEditText;->ph:Landroid/graphics/drawable/Drawable;

    .line 72
    invoke-super {p0, v0, v0, p3, v0}, Landroid/widget/EditText;->setCompoundDrawables(Landroid/graphics/drawable/Drawable;Landroid/graphics/drawable/Drawable;Landroid/graphics/drawable/Drawable;Landroid/graphics/drawable/Drawable;)V

    .line 73
    return-void
.end method

.method protected verifyDrawable(Landroid/graphics/drawable/Drawable;)Z
    .locals 1

    .prologue
    .line 111
    invoke-super {p0, p1}, Landroid/widget/EditText;->verifyDrawable(Landroid/graphics/drawable/Drawable;)Z

    move-result v0

    if-nez v0, :cond_0

    iget-object v0, p0, Lmiui/widget/ClearableEditText;->ph:Landroid/graphics/drawable/Drawable;

    if-ne p1, v0, :cond_1

    :cond_0
    const/4 v0, 0x1

    :goto_0
    return v0

    :cond_1
    const/4 v0, 0x0

    goto :goto_0
.end method
