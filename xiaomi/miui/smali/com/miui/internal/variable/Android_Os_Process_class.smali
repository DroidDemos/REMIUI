.class public abstract Lcom/miui/internal/variable/Android_Os_Process_class;
.super Ljava/lang/Object;
.source "SourceFile"


# annotations
.annotation system Ldalvik/annotation/MemberClasses;
    value = {
        Lcom/miui/internal/variable/Android_Os_Process_class$1;,
        Lcom/miui/internal/variable/Android_Os_Process_class$Factory;
    }
.end annotation


# direct methods
.method public constructor <init>()V
    .locals 0

    .prologue
    .line 12
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    .line 18
    return-void
.end method


# virtual methods
.method public abstract getFreeMemory()J
.end method

.method public abstract getTotalMemory()J
.end method
