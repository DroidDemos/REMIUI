.class public Lcom/miui/internal/widget/ProgressBarDelegate;
.super Ljava/lang/Object;
.source "SourceFile"


# static fields
.field private static final He:Lmiui/reflect/Method;

.field private static final Hf:Lmiui/reflect/Method;

.field private static final Hg:Lmiui/reflect/Method;

.field private static final Hh:Lmiui/reflect/Method;

.field static final TAG:Ljava/lang/String; = "ProgressBarDelegate"

.field private static final pe:Lcom/miui/internal/variable/Android_Graphics_Drawable_AnimatedRotateDrawable_class;


# instance fields
.field private final Hi:Landroid/widget/ProgressBar;

.field private final Hj:Ljava/lang/Class;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Ljava/lang/Class",
            "<+",
            "Landroid/widget/ProgressBar;",
            ">;"
        }
    .end annotation
.end field

.field private Hk:Z

.field private Hl:Landroid/graphics/drawable/Drawable;

.field private Hm:Landroid/graphics/drawable/Drawable;

.field private Hn:Z

.field private Ho:Landroid/graphics/drawable/Drawable;

.field private Hp:Z


# direct methods
.method static constructor <clinit>()V
    .locals 3

    .prologue
    .line 29
    const-class v0, Landroid/widget/ProgressBar;

    const-string v1, "setIndeterminate"

    const-string v2, "(Z)V"

    invoke-static {v0, v1, v2}, Lmiui/reflect/Method;->of(Ljava/lang/Class;Ljava/lang/String;Ljava/lang/String;)Lmiui/reflect/Method;

    move-result-object v0

    sput-object v0, Lcom/miui/internal/widget/ProgressBarDelegate;->He:Lmiui/reflect/Method;

    .line 31
    const-class v0, Landroid/widget/ProgressBar;

    const-string v1, "setIndeterminateDrawable"

    const-string v2, "(Landroid/graphics/drawable/Drawable;)V"

    invoke-static {v0, v1, v2}, Lmiui/reflect/Method;->of(Ljava/lang/Class;Ljava/lang/String;Ljava/lang/String;)Lmiui/reflect/Method;

    move-result-object v0

    sput-object v0, Lcom/miui/internal/widget/ProgressBarDelegate;->Hf:Lmiui/reflect/Method;

    .line 34
    const-class v0, Landroid/widget/ProgressBar;

    const-string v1, "setProgressDrawable"

    const-string v2, "(Landroid/graphics/drawable/Drawable;)V"

    invoke-static {v0, v1, v2}, Lmiui/reflect/Method;->of(Ljava/lang/Class;Ljava/lang/String;Ljava/lang/String;)Lmiui/reflect/Method;

    move-result-object v0

    sput-object v0, Lcom/miui/internal/widget/ProgressBarDelegate;->Hg:Lmiui/reflect/Method;

    .line 37
    const-class v0, Landroid/widget/ProgressBar;

    const-string v1, "onSizeChanged"

    const-string v2, "(IIII)V"

    invoke-static {v0, v1, v2}, Lmiui/reflect/Method;->of(Ljava/lang/Class;Ljava/lang/String;Ljava/lang/String;)Lmiui/reflect/Method;

    move-result-object v0

    sput-object v0, Lcom/miui/internal/widget/ProgressBarDelegate;->Hh:Lmiui/reflect/Method;

    .line 39
    invoke-static {}, Lcom/miui/internal/variable/Android_Graphics_Drawable_AnimatedRotateDrawable_class$Factory;->getInstance()Lcom/miui/internal/variable/Android_Graphics_Drawable_AnimatedRotateDrawable_class$Factory;

    move-result-object v0

    invoke-virtual {v0}, Lcom/miui/internal/variable/Android_Graphics_Drawable_AnimatedRotateDrawable_class$Factory;->get()Lcom/miui/internal/variable/Android_Graphics_Drawable_AnimatedRotateDrawable_class;

    move-result-object v0

    sput-object v0, Lcom/miui/internal/widget/ProgressBarDelegate;->pe:Lcom/miui/internal/variable/Android_Graphics_Drawable_AnimatedRotateDrawable_class;

    return-void
.end method

.method private constructor <init>(Landroid/widget/ProgressBar;Ljava/lang/Class;)V
    .locals 1
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Landroid/widget/ProgressBar;",
            "Ljava/lang/Class",
            "<+",
            "Landroid/widget/ProgressBar;",
            ">;)V"
        }
    .end annotation

    .prologue
    .line 53
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    .line 45
    const/4 v0, 0x0

    iput-boolean v0, p0, Lcom/miui/internal/widget/ProgressBarDelegate;->Hk:Z

    .line 54
    iput-object p1, p0, Lcom/miui/internal/widget/ProgressBarDelegate;->Hi:Landroid/widget/ProgressBar;

    .line 55
    iput-object p2, p0, Lcom/miui/internal/widget/ProgressBarDelegate;->Hj:Ljava/lang/Class;

    .line 56
    return-void
.end method

.method private bj()V
    .locals 7

    .prologue
    const/4 v5, 0x1

    const/4 v6, 0x0

    .line 129
    iget-boolean v0, p0, Lcom/miui/internal/widget/ProgressBarDelegate;->Hk:Z

    if-nez v0, :cond_1

    .line 160
    :cond_0
    :goto_0
    return-void

    .line 133
    :cond_1
    iget-object v1, p0, Lcom/miui/internal/widget/ProgressBarDelegate;->Hi:Landroid/widget/ProgressBar;

    .line 134
    invoke-virtual {v1}, Landroid/widget/ProgressBar;->isIndeterminate()Z

    move-result v2

    .line 135
    invoke-virtual {v1}, Landroid/widget/ProgressBar;->getHeight()I

    move-result v0

    invoke-virtual {v1}, Landroid/widget/ProgressBar;->getPaddingTop()I

    move-result v3

    sub-int/2addr v0, v3

    invoke-virtual {v1}, Landroid/widget/ProgressBar;->getPaddingBottom()I

    move-result v3

    sub-int v4, v0, v3

    .line 136
    invoke-virtual {v1}, Landroid/widget/ProgressBar;->getWidth()I

    move-result v0

    invoke-virtual {v1}, Landroid/widget/ProgressBar;->getPaddingLeft()I

    move-result v3

    sub-int/2addr v0, v3

    invoke-virtual {v1}, Landroid/widget/ProgressBar;->getPaddingRight()I

    move-result v3

    sub-int/2addr v0, v3

    .line 139
    if-ge v4, v0, :cond_4

    move v3, v0

    .line 145
    :goto_1
    iget-object v0, p0, Lcom/miui/internal/widget/ProgressBarDelegate;->Hl:Landroid/graphics/drawable/Drawable;

    if-eqz v0, :cond_2

    .line 146
    iget-object v0, p0, Lcom/miui/internal/widget/ProgressBarDelegate;->Hl:Landroid/graphics/drawable/Drawable;

    invoke-virtual {v0, v6, v6, v3, v4}, Landroid/graphics/drawable/Drawable;->setBounds(IIII)V

    .line 149
    :cond_2
    if-eqz v2, :cond_3

    iget-boolean v0, p0, Lcom/miui/internal/widget/ProgressBarDelegate;->Hp:Z

    if-eqz v0, :cond_3

    .line 150
    iput-boolean v5, p0, Lcom/miui/internal/widget/ProgressBarDelegate;->Hp:Z

    .line 151
    invoke-virtual {v1}, Landroid/widget/ProgressBar;->getResources()Landroid/content/res/Resources;

    move-result-object v0

    iget-object v1, p0, Lcom/miui/internal/widget/ProgressBarDelegate;->Ho:Landroid/graphics/drawable/Drawable;

    iget-object v2, p0, Lcom/miui/internal/widget/ProgressBarDelegate;->Hl:Landroid/graphics/drawable/Drawable;

    invoke-static/range {v0 .. v5}, Lmiui/util/ViewUtils;->tileifyForProgressBar(Landroid/content/res/Resources;Landroid/graphics/drawable/Drawable;Landroid/graphics/drawable/Drawable;IIZ)Landroid/graphics/drawable/Drawable;

    move-result-object v0

    .line 153
    invoke-direct {p0, v0}, Lcom/miui/internal/widget/ProgressBarDelegate;->d(Landroid/graphics/drawable/Drawable;)V

    goto :goto_0

    .line 154
    :cond_3
    if-nez v2, :cond_0

    iget-boolean v0, p0, Lcom/miui/internal/widget/ProgressBarDelegate;->Hn:Z

    if-eqz v0, :cond_0

    .line 155
    iput-boolean v5, p0, Lcom/miui/internal/widget/ProgressBarDelegate;->Hn:Z

    .line 156
    invoke-virtual {v1}, Landroid/widget/ProgressBar;->getResources()Landroid/content/res/Resources;

    move-result-object v0

    iget-object v1, p0, Lcom/miui/internal/widget/ProgressBarDelegate;->Hm:Landroid/graphics/drawable/Drawable;

    iget-object v2, p0, Lcom/miui/internal/widget/ProgressBarDelegate;->Hl:Landroid/graphics/drawable/Drawable;

    move v5, v6

    invoke-static/range {v0 .. v5}, Lmiui/util/ViewUtils;->tileifyForProgressBar(Landroid/content/res/Resources;Landroid/graphics/drawable/Drawable;Landroid/graphics/drawable/Drawable;IIZ)Landroid/graphics/drawable/Drawable;

    move-result-object v0

    .line 158
    invoke-direct {p0, v0}, Lcom/miui/internal/widget/ProgressBarDelegate;->e(Landroid/graphics/drawable/Drawable;)V

    goto :goto_0

    :cond_4
    move v3, v4

    move v4, v0

    goto :goto_1
.end method

.method private c(Landroid/graphics/drawable/Drawable;)V
    .locals 3

    .prologue
    .line 163
    const/4 v0, 0x0

    .line 164
    instance-of v1, p1, Landroid/graphics/drawable/NinePatchDrawable;

    if-eqz v1, :cond_1

    move-object v0, p1

    .line 165
    check-cast v0, Landroid/graphics/drawable/NinePatchDrawable;

    invoke-virtual {v0}, Landroid/graphics/drawable/NinePatchDrawable;->getPaint()Landroid/graphics/Paint;

    move-result-object v0

    .line 170
    :cond_0
    :goto_0
    if-eqz v0, :cond_2

    .line 171
    new-instance v1, Landroid/graphics/PorterDuffXfermode;

    sget-object v2, Landroid/graphics/PorterDuff$Mode;->DST_IN:Landroid/graphics/PorterDuff$Mode;

    invoke-direct {v1, v2}, Landroid/graphics/PorterDuffXfermode;-><init>(Landroid/graphics/PorterDuff$Mode;)V

    invoke-virtual {v0, v1}, Landroid/graphics/Paint;->setXfermode(Landroid/graphics/Xfermode;)Landroid/graphics/Xfermode;

    .line 175
    :goto_1
    return-void

    .line 166
    :cond_1
    instance-of v1, p1, Landroid/graphics/drawable/BitmapDrawable;

    if-eqz v1, :cond_0

    move-object v0, p1

    .line 167
    check-cast v0, Landroid/graphics/drawable/BitmapDrawable;

    invoke-virtual {v0}, Landroid/graphics/drawable/BitmapDrawable;->getPaint()Landroid/graphics/Paint;

    move-result-object v0

    goto :goto_0

    .line 173
    :cond_2
    const-string v0, "ProgressBarDelegate"

    new-instance v1, Ljava/lang/StringBuilder;

    invoke-direct {v1}, Ljava/lang/StringBuilder;-><init>()V

    const-string v2, "The drawable should be NinePatchDrawable or BitmapDrawable. drawable="

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v1

    invoke-virtual {v1, p1}, Ljava/lang/StringBuilder;->append(Ljava/lang/Object;)Ljava/lang/StringBuilder;

    move-result-object v1

    invoke-virtual {v1}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v1

    invoke-static {v0, v1}, Landroid/util/Log;->w(Ljava/lang/String;Ljava/lang/String;)I

    goto :goto_1
.end method

.method public static create(Landroid/widget/ProgressBar;Ljava/lang/Class;)Lcom/miui/internal/widget/ProgressBarDelegate;
    .locals 1
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Landroid/widget/ProgressBar;",
            "Ljava/lang/Class",
            "<+",
            "Landroid/widget/ProgressBar;",
            ">;)",
            "Lcom/miui/internal/widget/ProgressBarDelegate;"
        }
    .end annotation

    .prologue
    .line 178
    new-instance v0, Lcom/miui/internal/widget/ProgressBarDelegate;

    invoke-direct {v0, p0, p1}, Lcom/miui/internal/widget/ProgressBarDelegate;-><init>(Landroid/widget/ProgressBar;Ljava/lang/Class;)V

    return-object v0
.end method

.method private d(Landroid/graphics/drawable/Drawable;)V
    .locals 5

    .prologue
    .line 194
    sget-object v0, Lcom/miui/internal/widget/ProgressBarDelegate;->Hf:Lmiui/reflect/Method;

    iget-object v1, p0, Lcom/miui/internal/widget/ProgressBarDelegate;->Hj:Ljava/lang/Class;

    iget-object v2, p0, Lcom/miui/internal/widget/ProgressBarDelegate;->Hi:Landroid/widget/ProgressBar;

    const/4 v3, 0x1

    new-array v3, v3, [Ljava/lang/Object;

    const/4 v4, 0x0

    aput-object p1, v3, v4

    invoke-virtual {v0, v1, v2, v3}, Lmiui/reflect/Method;->invoke(Ljava/lang/Class;Ljava/lang/Object;[Ljava/lang/Object;)V

    .line 195
    return-void
.end method

.method private e(Landroid/graphics/drawable/Drawable;)V
    .locals 5

    .prologue
    .line 202
    sget-object v0, Lcom/miui/internal/widget/ProgressBarDelegate;->Hg:Lmiui/reflect/Method;

    iget-object v1, p0, Lcom/miui/internal/widget/ProgressBarDelegate;->Hj:Ljava/lang/Class;

    iget-object v2, p0, Lcom/miui/internal/widget/ProgressBarDelegate;->Hi:Landroid/widget/ProgressBar;

    const/4 v3, 0x1

    new-array v3, v3, [Ljava/lang/Object;

    const/4 v4, 0x0

    aput-object p1, v3, v4

    invoke-virtual {v0, v1, v2, v3}, Lmiui/reflect/Method;->invoke(Ljava/lang/Class;Ljava/lang/Object;[Ljava/lang/Object;)V

    .line 203
    return-void
.end method

.method private f(IIII)V
    .locals 6

    .prologue
    .line 213
    sget-object v0, Lcom/miui/internal/widget/ProgressBarDelegate;->Hh:Lmiui/reflect/Method;

    iget-object v1, p0, Lcom/miui/internal/widget/ProgressBarDelegate;->Hj:Ljava/lang/Class;

    iget-object v2, p0, Lcom/miui/internal/widget/ProgressBarDelegate;->Hi:Landroid/widget/ProgressBar;

    const/4 v3, 0x4

    new-array v3, v3, [Ljava/lang/Object;

    const/4 v4, 0x0

    invoke-static {p1}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object v5

    aput-object v5, v3, v4

    const/4 v4, 0x1

    invoke-static {p2}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object v5

    aput-object v5, v3, v4

    const/4 v4, 0x2

    invoke-static {p3}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object v5

    aput-object v5, v3, v4

    const/4 v4, 0x3

    invoke-static {p4}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object v5

    aput-object v5, v3, v4

    invoke-virtual {v0, v1, v2, v3}, Lmiui/reflect/Method;->invoke(Ljava/lang/Class;Ljava/lang/Object;[Ljava/lang/Object;)V

    .line 214
    return-void
.end method

.method private l(Z)V
    .locals 6

    .prologue
    .line 186
    sget-object v0, Lcom/miui/internal/widget/ProgressBarDelegate;->He:Lmiui/reflect/Method;

    iget-object v1, p0, Lcom/miui/internal/widget/ProgressBarDelegate;->Hj:Ljava/lang/Class;

    iget-object v2, p0, Lcom/miui/internal/widget/ProgressBarDelegate;->Hi:Landroid/widget/ProgressBar;

    const/4 v3, 0x1

    new-array v3, v3, [Ljava/lang/Object;

    const/4 v4, 0x0

    invoke-static {p1}, Ljava/lang/Boolean;->valueOf(Z)Ljava/lang/Boolean;

    move-result-object v5

    aput-object v5, v3, v4

    invoke-virtual {v0, v1, v2, v3}, Lmiui/reflect/Method;->invoke(Ljava/lang/Class;Ljava/lang/Object;[Ljava/lang/Object;)V

    .line 187
    return-void
.end method


# virtual methods
.method public getProgressMaskDrawable()Landroid/graphics/drawable/Drawable;
    .locals 1

    .prologue
    .line 118
    iget-object v0, p0, Lcom/miui/internal/widget/ProgressBarDelegate;->Hl:Landroid/graphics/drawable/Drawable;

    return-object v0
.end method

.method public onSizeChanged(IIII)V
    .locals 1

    .prologue
    const/4 v0, 0x1

    .line 122
    iput-boolean v0, p0, Lcom/miui/internal/widget/ProgressBarDelegate;->Hn:Z

    .line 123
    iput-boolean v0, p0, Lcom/miui/internal/widget/ProgressBarDelegate;->Hp:Z

    .line 124
    invoke-direct {p0}, Lcom/miui/internal/widget/ProgressBarDelegate;->bj()V

    .line 125
    invoke-direct {p0, p1, p2, p3, p4}, Lcom/miui/internal/widget/ProgressBarDelegate;->f(IIII)V

    .line 126
    return-void
.end method

.method public postConstruct(Landroid/util/AttributeSet;I)V
    .locals 6

    .prologue
    const/4 v5, 0x1

    const/4 v4, 0x0

    .line 59
    iget-object v0, p0, Lcom/miui/internal/widget/ProgressBarDelegate;->Hi:Landroid/widget/ProgressBar;

    invoke-virtual {v0}, Landroid/widget/ProgressBar;->getContext()Landroid/content/Context;

    move-result-object v0

    .line 60
    sget-object v1, Lmiui/R$styleable;->ProgressBar:[I

    invoke-virtual {v0, p1, v1, p2, v4}, Landroid/content/Context;->obtainStyledAttributes(Landroid/util/AttributeSet;[III)Landroid/content/res/TypedArray;

    move-result-object v0

    .line 61
    iget-object v1, p0, Lcom/miui/internal/widget/ProgressBarDelegate;->Ho:Landroid/graphics/drawable/Drawable;

    .line 62
    if-eqz v1, :cond_0

    invoke-virtual {v1}, Ljava/lang/Object;->getClass()Ljava/lang/Class;

    move-result-object v2

    invoke-virtual {v2}, Ljava/lang/Class;->getName()Ljava/lang/String;

    move-result-object v2

    const-string v3, "android.graphics.drawable.AnimatedRotateDrawable"

    invoke-virtual {v2, v3}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v2

    if-eqz v2, :cond_0

    .line 63
    const/16 v2, 0x30

    invoke-virtual {v0, v5, v2}, Landroid/content/res/TypedArray;->getInt(II)I

    move-result v2

    .line 66
    sget-object v3, Lcom/miui/internal/widget/ProgressBarDelegate;->pe:Lcom/miui/internal/variable/Android_Graphics_Drawable_AnimatedRotateDrawable_class;

    invoke-virtual {v3, v1, v2}, Lcom/miui/internal/variable/Android_Graphics_Drawable_AnimatedRotateDrawable_class;->setFramesCount(Landroid/graphics/drawable/Drawable;I)V

    .line 67
    const/4 v2, 0x2

    const/16 v3, 0x19

    invoke-virtual {v0, v2, v3}, Landroid/content/res/TypedArray;->getInt(II)I

    move-result v2

    .line 70
    sget-object v3, Lcom/miui/internal/widget/ProgressBarDelegate;->pe:Lcom/miui/internal/variable/Android_Graphics_Drawable_AnimatedRotateDrawable_class;

    invoke-virtual {v3, v1, v2}, Lcom/miui/internal/variable/Android_Graphics_Drawable_AnimatedRotateDrawable_class;->setFramesDuration(Landroid/graphics/drawable/Drawable;I)V

    .line 73
    :cond_0
    iput-boolean v5, p0, Lcom/miui/internal/widget/ProgressBarDelegate;->Hk:Z

    .line 74
    invoke-virtual {v0, v4}, Landroid/content/res/TypedArray;->getDrawable(I)Landroid/graphics/drawable/Drawable;

    move-result-object v1

    .line 75
    if-eqz v1, :cond_1

    .line 76
    invoke-direct {p0, v1}, Lcom/miui/internal/widget/ProgressBarDelegate;->c(Landroid/graphics/drawable/Drawable;)V

    .line 77
    invoke-virtual {p0, v1}, Lcom/miui/internal/widget/ProgressBarDelegate;->setProgressMaskDrawable(Landroid/graphics/drawable/Drawable;)V

    .line 81
    :goto_0
    invoke-virtual {v0}, Landroid/content/res/TypedArray;->recycle()V

    .line 82
    return-void

    .line 79
    :cond_1
    invoke-direct {p0}, Lcom/miui/internal/widget/ProgressBarDelegate;->bj()V

    goto :goto_0
.end method

.method public setIndeterminate(Z)V
    .locals 2

    .prologue
    .line 85
    iget-object v0, p0, Lcom/miui/internal/widget/ProgressBarDelegate;->Hi:Landroid/widget/ProgressBar;

    invoke-virtual {v0}, Landroid/widget/ProgressBar;->isIndeterminate()Z

    move-result v0

    .line 86
    invoke-direct {p0, p1}, Lcom/miui/internal/widget/ProgressBarDelegate;->l(Z)V

    .line 87
    iget-object v1, p0, Lcom/miui/internal/widget/ProgressBarDelegate;->Hi:Landroid/widget/ProgressBar;

    invoke-virtual {v1}, Landroid/widget/ProgressBar;->isIndeterminate()Z

    move-result v1

    if-eq v0, v1, :cond_0

    .line 88
    invoke-direct {p0}, Lcom/miui/internal/widget/ProgressBarDelegate;->bj()V

    .line 90
    :cond_0
    return-void
.end method

.method public setIndeterminateDrawable(Landroid/graphics/drawable/Drawable;)V
    .locals 1

    .prologue
    .line 101
    iget-object v0, p0, Lcom/miui/internal/widget/ProgressBarDelegate;->Ho:Landroid/graphics/drawable/Drawable;

    if-eq v0, p1, :cond_0

    .line 102
    iput-object p1, p0, Lcom/miui/internal/widget/ProgressBarDelegate;->Ho:Landroid/graphics/drawable/Drawable;

    .line 103
    const/4 v0, 0x1

    iput-boolean v0, p0, Lcom/miui/internal/widget/ProgressBarDelegate;->Hp:Z

    .line 104
    invoke-direct {p0}, Lcom/miui/internal/widget/ProgressBarDelegate;->bj()V

    .line 106
    :cond_0
    return-void
.end method

.method public setProgressDrawable(Landroid/graphics/drawable/Drawable;)V
    .locals 1

    .prologue
    .line 93
    iget-object v0, p0, Lcom/miui/internal/widget/ProgressBarDelegate;->Hm:Landroid/graphics/drawable/Drawable;

    if-eq v0, p1, :cond_0

    .line 94
    iput-object p1, p0, Lcom/miui/internal/widget/ProgressBarDelegate;->Hm:Landroid/graphics/drawable/Drawable;

    .line 95
    const/4 v0, 0x1

    iput-boolean v0, p0, Lcom/miui/internal/widget/ProgressBarDelegate;->Hn:Z

    .line 96
    invoke-direct {p0}, Lcom/miui/internal/widget/ProgressBarDelegate;->bj()V

    .line 98
    :cond_0
    return-void
.end method

.method public declared-synchronized setProgressMaskDrawable(Landroid/graphics/drawable/Drawable;)V
    .locals 1

    .prologue
    .line 109
    monitor-enter p0

    :try_start_0
    iget-object v0, p0, Lcom/miui/internal/widget/ProgressBarDelegate;->Hl:Landroid/graphics/drawable/Drawable;

    if-eq v0, p1, :cond_0

    .line 110
    iput-object p1, p0, Lcom/miui/internal/widget/ProgressBarDelegate;->Hl:Landroid/graphics/drawable/Drawable;

    .line 111
    const/4 v0, 0x1

    iput-boolean v0, p0, Lcom/miui/internal/widget/ProgressBarDelegate;->Hn:Z

    .line 112
    const/4 v0, 0x1

    iput-boolean v0, p0, Lcom/miui/internal/widget/ProgressBarDelegate;->Hp:Z

    .line 113
    invoke-direct {p0}, Lcom/miui/internal/widget/ProgressBarDelegate;->bj()V
    :try_end_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    .line 115
    :cond_0
    monitor-exit p0

    return-void

    .line 109
    :catchall_0
    move-exception v0

    monitor-exit p0

    throw v0
.end method
