.class public Lcom/miui/internal/variable/v19/Android_View_Window_class;
.super Lcom/miui/internal/variable/v16/Android_View_Window_class;
.source "SourceFile"


# static fields
.field private static final yV:I = 0x4000000


# direct methods
.method public constructor <init>()V
    .locals 0

    .prologue
    .line 11
    invoke-direct {p0}, Lcom/miui/internal/variable/v16/Android_View_Window_class;-><init>()V

    return-void
.end method


# virtual methods
.method public setTranslucentStatus(Landroid/view/Window;I)Z
    .locals 1

    .prologue
    const/high16 v0, 0x4000000

    .line 17
    if-nez p2, :cond_0

    .line 18
    invoke-virtual {p1, v0}, Landroid/view/Window;->clearFlags(I)V

    .line 23
    :goto_0
    invoke-super {p0, p1, p2}, Lcom/miui/internal/variable/v16/Android_View_Window_class;->setTranslucentStatus(Landroid/view/Window;I)Z

    .line 24
    const/4 v0, 0x1

    return v0

    .line 20
    :cond_0
    invoke-virtual {p1, v0, v0}, Landroid/view/Window;->setFlags(II)V

    goto :goto_0
.end method
