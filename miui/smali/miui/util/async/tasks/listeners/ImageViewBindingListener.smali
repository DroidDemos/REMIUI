.class public Lmiui/util/async/tasks/listeners/ImageViewBindingListener;
.super Lmiui/util/async/tasks/listeners/BaseTaskListener;
.source "SourceFile"


# static fields
.field private static final uG:Ljava/util/LinkedHashSet;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Ljava/util/LinkedHashSet",
            "<",
            "Lmiui/util/async/tasks/listeners/ImageViewBindingListener;",
            ">;"
        }
    .end annotation
.end field


# instance fields
.field private final uH:Ljava/lang/ref/WeakReference;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Ljava/lang/ref/WeakReference",
            "<",
            "Landroid/widget/ImageView;",
            ">;"
        }
    .end annotation
.end field

.field private uI:Ljava/lang/ref/WeakReference;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Ljava/lang/ref/WeakReference",
            "<",
            "Lmiui/util/async/Task",
            "<*>;>;"
        }
    .end annotation
.end field

.field private uJ:Landroid/graphics/drawable/Drawable;

.field private uK:Landroid/graphics/drawable/Drawable;


# direct methods
.method static constructor <clinit>()V
    .locals 1

    .prologue
    .line 35
    new-instance v0, Ljava/util/LinkedHashSet;

    invoke-direct {v0}, Ljava/util/LinkedHashSet;-><init>()V

    sput-object v0, Lmiui/util/async/tasks/listeners/ImageViewBindingListener;->uG:Ljava/util/LinkedHashSet;

    return-void
.end method

.method public constructor <init>(Landroid/widget/ImageView;)V
    .locals 1

    .prologue
    .line 63
    invoke-direct {p0}, Lmiui/util/async/tasks/listeners/BaseTaskListener;-><init>()V

    .line 64
    new-instance v0, Ljava/lang/ref/WeakReference;

    invoke-direct {v0, p1}, Ljava/lang/ref/WeakReference;-><init>(Ljava/lang/Object;)V

    iput-object v0, p0, Lmiui/util/async/tasks/listeners/ImageViewBindingListener;->uH:Ljava/lang/ref/WeakReference;

    .line 65
    return-void
.end method

.method private a(Lmiui/util/async/tasks/listeners/ImageViewBindingListener;)Z
    .locals 2

    .prologue
    .line 99
    iget-object v0, p0, Lmiui/util/async/tasks/listeners/ImageViewBindingListener;->uH:Ljava/lang/ref/WeakReference;

    invoke-virtual {v0}, Ljava/lang/ref/WeakReference;->get()Ljava/lang/Object;

    move-result-object v0

    iget-object v1, p1, Lmiui/util/async/tasks/listeners/ImageViewBindingListener;->uH:Ljava/lang/ref/WeakReference;

    invoke-virtual {v1}, Ljava/lang/ref/WeakReference;->get()Ljava/lang/Object;

    move-result-object v1

    if-ne v0, v1, :cond_0

    const/4 v0, 0x1

    :goto_0
    return v0

    :cond_0
    const/4 v0, 0x0

    goto :goto_0
.end method


# virtual methods
.method public final getImageView()Landroid/widget/ImageView;
    .locals 1

    .prologue
    .line 106
    iget-object v0, p0, Lmiui/util/async/tasks/listeners/ImageViewBindingListener;->uH:Ljava/lang/ref/WeakReference;

    invoke-virtual {v0}, Ljava/lang/ref/WeakReference;->get()Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Landroid/widget/ImageView;

    return-object v0
.end method

.method public onException(Lmiui/util/async/TaskManager;Lmiui/util/async/Task;Ljava/lang/Exception;)V
    .locals 2
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Lmiui/util/async/TaskManager;",
            "Lmiui/util/async/Task",
            "<*>;",
            "Ljava/lang/Exception;",
            ")V"
        }
    .end annotation

    .prologue
    .line 174
    iget-object v0, p0, Lmiui/util/async/tasks/listeners/ImageViewBindingListener;->uK:Landroid/graphics/drawable/Drawable;

    if-eqz v0, :cond_0

    .line 175
    iget-object v0, p0, Lmiui/util/async/tasks/listeners/ImageViewBindingListener;->uH:Ljava/lang/ref/WeakReference;

    invoke-virtual {v0}, Ljava/lang/ref/WeakReference;->get()Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Landroid/widget/ImageView;

    .line 176
    if-eqz v0, :cond_0

    .line 177
    iget-object v1, p0, Lmiui/util/async/tasks/listeners/ImageViewBindingListener;->uK:Landroid/graphics/drawable/Drawable;

    invoke-virtual {v0, v1}, Landroid/widget/ImageView;->setImageDrawable(Landroid/graphics/drawable/Drawable;)V

    .line 180
    :cond_0
    return-void
.end method

.method public onFinalize(Lmiui/util/async/TaskManager;Lmiui/util/async/Task;)V
    .locals 1
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Lmiui/util/async/TaskManager;",
            "Lmiui/util/async/Task",
            "<*>;)V"
        }
    .end annotation

    .prologue
    .line 184
    sget-object v0, Lmiui/util/async/tasks/listeners/ImageViewBindingListener;->uG:Ljava/util/LinkedHashSet;

    invoke-virtual {v0, p0}, Ljava/util/LinkedHashSet;->remove(Ljava/lang/Object;)Z

    .line 185
    return-void
.end method

.method public onPrepare(Lmiui/util/async/TaskManager;Lmiui/util/async/Task;)V
    .locals 4
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Lmiui/util/async/TaskManager;",
            "Lmiui/util/async/Task",
            "<*>;)V"
        }
    .end annotation

    .prologue
    .line 111
    iget-object v0, p0, Lmiui/util/async/tasks/listeners/ImageViewBindingListener;->uI:Ljava/lang/ref/WeakReference;

    if-eqz v0, :cond_0

    .line 112
    iget-object v0, p0, Lmiui/util/async/tasks/listeners/ImageViewBindingListener;->uI:Ljava/lang/ref/WeakReference;

    invoke-virtual {v0}, Ljava/lang/ref/WeakReference;->get()Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Lmiui/util/async/Task;

    .line 113
    if-eqz v0, :cond_0

    .line 114
    invoke-virtual {v0}, Lmiui/util/async/Task;->cancel()V

    .line 118
    :cond_0
    new-instance v0, Ljava/lang/ref/WeakReference;

    invoke-direct {v0, p2}, Ljava/lang/ref/WeakReference;-><init>(Ljava/lang/Object;)V

    iput-object v0, p0, Lmiui/util/async/tasks/listeners/ImageViewBindingListener;->uI:Ljava/lang/ref/WeakReference;

    .line 119
    const/4 v1, 0x0

    .line 120
    sget-object v0, Lmiui/util/async/tasks/listeners/ImageViewBindingListener;->uG:Ljava/util/LinkedHashSet;

    invoke-virtual {v0}, Ljava/util/LinkedHashSet;->iterator()Ljava/util/Iterator;

    move-result-object v2

    :cond_1
    invoke-interface {v2}, Ljava/util/Iterator;->hasNext()Z

    move-result v0

    if-eqz v0, :cond_4

    invoke-interface {v2}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Lmiui/util/async/tasks/listeners/ImageViewBindingListener;

    .line 121
    if-eq v0, p0, :cond_1

    invoke-direct {p0, v0}, Lmiui/util/async/tasks/listeners/ImageViewBindingListener;->a(Lmiui/util/async/tasks/listeners/ImageViewBindingListener;)Z

    move-result v3

    if-eqz v3, :cond_1

    .line 122
    iget-object v0, v0, Lmiui/util/async/tasks/listeners/ImageViewBindingListener;->uI:Ljava/lang/ref/WeakReference;

    invoke-virtual {v0}, Ljava/lang/ref/WeakReference;->get()Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Lmiui/util/async/Task;

    .line 126
    :goto_0
    if-eqz v0, :cond_2

    .line 127
    invoke-virtual {v0}, Lmiui/util/async/Task;->cancel()V

    .line 130
    :cond_2
    sget-object v0, Lmiui/util/async/tasks/listeners/ImageViewBindingListener;->uG:Ljava/util/LinkedHashSet;

    invoke-virtual {v0, p0}, Ljava/util/LinkedHashSet;->add(Ljava/lang/Object;)Z

    .line 132
    iget-object v0, p0, Lmiui/util/async/tasks/listeners/ImageViewBindingListener;->uJ:Landroid/graphics/drawable/Drawable;

    if-eqz v0, :cond_3

    .line 133
    iget-object v0, p0, Lmiui/util/async/tasks/listeners/ImageViewBindingListener;->uH:Ljava/lang/ref/WeakReference;

    invoke-virtual {v0}, Ljava/lang/ref/WeakReference;->get()Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Landroid/widget/ImageView;

    .line 134
    if-eqz v0, :cond_3

    .line 135
    iget-object v1, p0, Lmiui/util/async/tasks/listeners/ImageViewBindingListener;->uJ:Landroid/graphics/drawable/Drawable;

    invoke-virtual {v0, v1}, Landroid/widget/ImageView;->setImageDrawable(Landroid/graphics/drawable/Drawable;)V

    .line 138
    :cond_3
    return-void

    :cond_4
    move-object v0, v1

    goto :goto_0
.end method

.method public onResult(Lmiui/util/async/TaskManager;Lmiui/util/async/Task;Ljava/lang/Object;)Ljava/lang/Object;
    .locals 6
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Lmiui/util/async/TaskManager;",
            "Lmiui/util/async/Task",
            "<*>;",
            "Ljava/lang/Object;",
            ")",
            "Ljava/lang/Object;"
        }
    .end annotation

    .prologue
    const/4 v3, 0x0

    const/4 v5, 0x1

    .line 142
    iget-object v0, p0, Lmiui/util/async/tasks/listeners/ImageViewBindingListener;->uH:Ljava/lang/ref/WeakReference;

    invoke-virtual {v0}, Ljava/lang/ref/WeakReference;->get()Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Landroid/widget/ImageView;

    .line 143
    if-eqz v0, :cond_0

    .line 144
    const/4 v1, 0x2

    new-array v2, v1, [Landroid/graphics/drawable/Drawable;

    invoke-virtual {v0}, Landroid/widget/ImageView;->getDrawable()Landroid/graphics/drawable/Drawable;

    move-result-object v1

    aput-object v1, v2, v3

    const/4 v1, 0x0

    aput-object v1, v2, v5

    .line 149
    aget-object v1, v2, v3

    if-nez v1, :cond_2

    .line 150
    instance-of v1, p3, Landroid/graphics/Bitmap;

    if-eqz v1, :cond_1

    move-object v1, p3

    .line 151
    check-cast v1, Landroid/graphics/Bitmap;

    invoke-virtual {v0, v1}, Landroid/widget/ImageView;->setImageBitmap(Landroid/graphics/Bitmap;)V

    .line 169
    :cond_0
    :goto_0
    return-object p3

    :cond_1
    move-object v1, p3

    .line 153
    check-cast v1, Landroid/graphics/drawable/Drawable;

    invoke-virtual {v0, v1}, Landroid/widget/ImageView;->setImageDrawable(Landroid/graphics/drawable/Drawable;)V

    goto :goto_0

    .line 156
    :cond_2
    invoke-static {}, Lcom/miui/internal/util/PackageConstants;->getCurrentApplication()Landroid/app/Application;

    move-result-object v1

    invoke-virtual {v1}, Landroid/app/Application;->getResources()Landroid/content/res/Resources;

    move-result-object v3

    .line 157
    instance-of v1, p3, Landroid/graphics/Bitmap;

    if-eqz v1, :cond_3

    .line 158
    new-instance v4, Landroid/graphics/drawable/BitmapDrawable;

    move-object v1, p3

    check-cast v1, Landroid/graphics/Bitmap;

    invoke-direct {v4, v3, v1}, Landroid/graphics/drawable/BitmapDrawable;-><init>(Landroid/content/res/Resources;Landroid/graphics/Bitmap;)V

    aput-object v4, v2, v5

    .line 163
    :goto_1
    new-instance v1, Landroid/graphics/drawable/TransitionDrawable;

    invoke-direct {v1, v2}, Landroid/graphics/drawable/TransitionDrawable;-><init>([Landroid/graphics/drawable/Drawable;)V

    .line 164
    invoke-virtual {v1, v5}, Landroid/graphics/drawable/TransitionDrawable;->setCrossFadeEnabled(Z)V

    .line 165
    invoke-virtual {v0, v1}, Landroid/widget/ImageView;->setImageDrawable(Landroid/graphics/drawable/Drawable;)V

    .line 166
    const/high16 v0, 0x10e0000

    invoke-virtual {v3, v0}, Landroid/content/res/Resources;->getInteger(I)I

    move-result v0

    invoke-virtual {v1, v0}, Landroid/graphics/drawable/TransitionDrawable;->startTransition(I)V

    goto :goto_0

    :cond_3
    move-object v1, p3

    .line 160
    check-cast v1, Landroid/graphics/drawable/Drawable;

    aput-object v1, v2, v5

    goto :goto_1
.end method

.method public setErrorDrawable(Landroid/graphics/drawable/Drawable;)Lmiui/util/async/tasks/listeners/ImageViewBindingListener;
    .locals 0

    .prologue
    .line 87
    iput-object p1, p0, Lmiui/util/async/tasks/listeners/ImageViewBindingListener;->uK:Landroid/graphics/drawable/Drawable;

    .line 88
    return-object p0
.end method

.method public setPlaceHolderDrawable(Landroid/graphics/drawable/Drawable;)Lmiui/util/async/tasks/listeners/ImageViewBindingListener;
    .locals 0

    .prologue
    .line 75
    iput-object p1, p0, Lmiui/util/async/tasks/listeners/ImageViewBindingListener;->uJ:Landroid/graphics/drawable/Drawable;

    .line 76
    return-object p0
.end method
