.class public abstract Lcom/miui/internal/variable/Android_Content_Res_Resources_class;
.super Lcom/miui/internal/util/ClassProxy;
.source "SourceFile"

# interfaces
.implements Lcom/miui/internal/variable/IManagedClassProxy;


# annotations
.annotation system Ldalvik/annotation/MemberClasses;
    value = {
        Lcom/miui/internal/variable/Android_Content_Res_Resources_class$1;,
        Lcom/miui/internal/variable/Android_Content_Res_Resources_class$Factory;
    }
.end annotation

.annotation system Ldalvik/annotation/Signature;
    value = {
        "Lcom/miui/internal/util/ClassProxy",
        "<",
        "Landroid/content/res/Resources;",
        ">;",
        "Lcom/miui/internal/variable/IManagedClassProxy;"
    }
.end annotation


# direct methods
.method public constructor <init>()V
    .locals 1

    .prologue
    .line 21
    const-class v0, Landroid/content/res/Resources;

    invoke-direct {p0, v0}, Lcom/miui/internal/util/ClassProxy;-><init>(Ljava/lang/Class;)V

    .line 22
    return-void
.end method


# virtual methods
.method public abstract setAssetManager(Landroid/content/res/Resources;Landroid/content/res/AssetManager;)V
.end method
