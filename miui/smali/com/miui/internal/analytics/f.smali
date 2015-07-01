.class Lcom/miui/internal/analytics/f;
.super Ljava/lang/Object;
.source "SourceFile"

# interfaces
.implements Lcom/miui/internal/analytics/h;


# static fields
.field public static final TAG:Ljava/lang/String; = "ANALYTICS.SQLITESTORE"


# instance fields
.field private Lu:Lcom/miui/internal/analytics/a;


# direct methods
.method constructor <init>()V
    .locals 0

    .prologue
    .line 10
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method


# virtual methods
.method public D(Ljava/lang/String;)Landroid/database/Cursor;
    .locals 1

    .prologue
    .line 33
    iget-object v0, p0, Lcom/miui/internal/analytics/f;->Lu:Lcom/miui/internal/analytics/a;

    if-eqz v0, :cond_0

    .line 34
    iget-object v0, p0, Lcom/miui/internal/analytics/f;->Lu:Lcom/miui/internal/analytics/a;

    invoke-virtual {v0, p1}, Lcom/miui/internal/analytics/a;->a(Ljava/lang/String;)Landroid/database/Cursor;

    move-result-object v0

    .line 36
    :goto_0
    return-object v0

    :cond_0
    const/4 v0, 0x0

    goto :goto_0
.end method

.method public b(Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V
    .locals 7

    .prologue
    .line 26
    iget-object v0, p0, Lcom/miui/internal/analytics/f;->Lu:Lcom/miui/internal/analytics/a;

    if-eqz v0, :cond_0

    .line 27
    iget-object v0, p0, Lcom/miui/internal/analytics/f;->Lu:Lcom/miui/internal/analytics/a;

    move-object v1, p1

    move-object v2, p2

    move-object v3, p3

    move-object v4, p4

    move-object v5, p5

    move-object v6, p6

    invoke-virtual/range {v0 .. v6}, Lcom/miui/internal/analytics/a;->a(Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V

    .line 29
    :cond_0
    return-void
.end method

.method public close()V
    .locals 0

    .prologue
    .line 22
    return-void
.end method

.method public o(Landroid/content/Context;)V
    .locals 1

    .prologue
    .line 17
    invoke-static {}, Lcom/miui/internal/analytics/a;->l()Lcom/miui/internal/analytics/a;

    move-result-object v0

    iput-object v0, p0, Lcom/miui/internal/analytics/f;->Lu:Lcom/miui/internal/analytics/a;

    .line 18
    return-void
.end method
