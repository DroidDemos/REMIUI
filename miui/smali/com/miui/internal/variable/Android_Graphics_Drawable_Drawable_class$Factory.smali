.class public Lcom/miui/internal/variable/Android_Graphics_Drawable_Drawable_class$Factory;
.super Lcom/miui/internal/variable/AbsClassFactory;
.source "SourceFile"


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lcom/miui/internal/variable/Android_Graphics_Drawable_Drawable_class;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x9
    name = "Factory"
.end annotation

.annotation system Ldalvik/annotation/MemberClasses;
    value = {
        Lcom/miui/internal/variable/Android_Graphics_Drawable_Drawable_class$Factory$a;
    }
.end annotation


# instance fields
.field private eY:Lcom/miui/internal/variable/Android_Graphics_Drawable_Drawable_class;


# direct methods
.method private constructor <init>()V
    .locals 1

    .prologue
    .line 49
    invoke-direct {p0}, Lcom/miui/internal/variable/AbsClassFactory;-><init>()V

    .line 50
    const-string v0, "Android_Graphics_Drawable_Drawable_class"

    .line 51
    invoke-virtual {p0, v0}, Lcom/miui/internal/variable/Android_Graphics_Drawable_Drawable_class$Factory;->create(Ljava/lang/String;)Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Lcom/miui/internal/variable/Android_Graphics_Drawable_Drawable_class;

    iput-object v0, p0, Lcom/miui/internal/variable/Android_Graphics_Drawable_Drawable_class$Factory;->eY:Lcom/miui/internal/variable/Android_Graphics_Drawable_Drawable_class;

    .line 52
    return-void
.end method

.method synthetic constructor <init>(Lcom/miui/internal/variable/Android_Graphics_Drawable_Drawable_class$1;)V
    .locals 0

    .prologue
    .line 42
    invoke-direct {p0}, Lcom/miui/internal/variable/Android_Graphics_Drawable_Drawable_class$Factory;-><init>()V

    return-void
.end method

.method public static getInstance()Lcom/miui/internal/variable/Android_Graphics_Drawable_Drawable_class$Factory;
    .locals 1

    .prologue
    .line 55
    sget-object v0, Lcom/miui/internal/variable/Android_Graphics_Drawable_Drawable_class$Factory$a;->Ao:Lcom/miui/internal/variable/Android_Graphics_Drawable_Drawable_class$Factory;

    return-object v0
.end method


# virtual methods
.method public get()Lcom/miui/internal/variable/Android_Graphics_Drawable_Drawable_class;
    .locals 1

    .prologue
    .line 60
    iget-object v0, p0, Lcom/miui/internal/variable/Android_Graphics_Drawable_Drawable_class$Factory;->eY:Lcom/miui/internal/variable/Android_Graphics_Drawable_Drawable_class;

    return-object v0
.end method

.method public bridge synthetic get()Ljava/lang/Object;
    .locals 1

    .prologue
    .line 42
    invoke-virtual {p0}, Lcom/miui/internal/variable/Android_Graphics_Drawable_Drawable_class$Factory;->get()Lcom/miui/internal/variable/Android_Graphics_Drawable_Drawable_class;

    move-result-object v0

    return-object v0
.end method
