.class public Lcom/miui/internal/analytics/AnalyticsService;
.super Landroid/app/Service;
.source "SourceFile"


# instance fields
.field private final gD:Lcom/miui/internal/analytics/IAnalytics$Stub;


# direct methods
.method public constructor <init>()V
    .locals 1

    .prologue
    .line 18
    invoke-direct {p0}, Landroid/app/Service;-><init>()V

    .line 20
    new-instance v0, Lcom/miui/internal/analytics/g;

    invoke-direct {v0, p0}, Lcom/miui/internal/analytics/g;-><init>(Lcom/miui/internal/analytics/AnalyticsService;)V

    iput-object v0, p0, Lcom/miui/internal/analytics/AnalyticsService;->gD:Lcom/miui/internal/analytics/IAnalytics$Stub;

    return-void
.end method


# virtual methods
.method public onBind(Landroid/content/Intent;)Landroid/os/IBinder;
    .locals 1

    .prologue
    .line 43
    iget-object v0, p0, Lcom/miui/internal/analytics/AnalyticsService;->gD:Lcom/miui/internal/analytics/IAnalytics$Stub;

    return-object v0
.end method
