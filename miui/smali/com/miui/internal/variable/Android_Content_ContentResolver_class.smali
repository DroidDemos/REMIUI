.class public abstract Lcom/miui/internal/variable/Android_Content_ContentResolver_class;
.super Ljava/lang/Object;
.source "SourceFile"


# annotations
.annotation system Ldalvik/annotation/MemberClasses;
    value = {
        Lcom/miui/internal/variable/Android_Content_ContentResolver_class$1;,
        Lcom/miui/internal/variable/Android_Content_ContentResolver_class$Factory;
    }
.end annotation


# static fields
.field public static final NAME:Ljava/lang/String; = "android.content.ContentResolver"


# direct methods
.method public constructor <init>()V
    .locals 0

    .prologue
    .line 11
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    .line 21
    return-void
.end method


# virtual methods
.method public abstract getSyncErrorSyncAlreadyInProgressConstants()I
.end method

.method public abstract getSyncObserverTypeStatusConstants()I
.end method

.method public abstract getSyncStatus(Landroid/accounts/Account;Ljava/lang/String;)Ljava/lang/Object;
.end method
