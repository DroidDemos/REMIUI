.class public Lmiui/preference/VolumePreference$VolumeStore;
.super Ljava/lang/Object;
.source "SourceFile"


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lmiui/preference/VolumePreference;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x9
    name = "VolumeStore"
.end annotation


# instance fields
.field public originalVolume:I

.field public volume:I


# direct methods
.method public constructor <init>()V
    .locals 1

    .prologue
    const/4 v0, -0x1

    .line 204
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    .line 205
    iput v0, p0, Lmiui/preference/VolumePreference$VolumeStore;->volume:I

    .line 206
    iput v0, p0, Lmiui/preference/VolumePreference$VolumeStore;->originalVolume:I

    return-void
.end method
