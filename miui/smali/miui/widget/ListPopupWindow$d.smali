.class Lmiui/widget/ListPopupWindow$d;
.super Ljava/lang/Object;
.source "SourceFile"

# interfaces
.implements Ljava/lang/Runnable;


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lmiui/widget/ListPopupWindow;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x2
    name = "d"
.end annotation


# instance fields
.field final synthetic D:Lmiui/widget/ListPopupWindow;


# direct methods
.method private constructor <init>(Lmiui/widget/ListPopupWindow;)V
    .locals 0

    .prologue
    .line 1354
    iput-object p1, p0, Lmiui/widget/ListPopupWindow$d;->D:Lmiui/widget/ListPopupWindow;

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method

.method synthetic constructor <init>(Lmiui/widget/ListPopupWindow;Lmiui/widget/ListPopupWindow$2;)V
    .locals 0

    .prologue
    .line 1354
    invoke-direct {p0, p1}, Lmiui/widget/ListPopupWindow$d;-><init>(Lmiui/widget/ListPopupWindow;)V

    return-void
.end method


# virtual methods
.method public run()V
    .locals 1

    .prologue
    .line 1357
    iget-object v0, p0, Lmiui/widget/ListPopupWindow$d;->D:Lmiui/widget/ListPopupWindow;

    invoke-virtual {v0}, Lmiui/widget/ListPopupWindow;->clearListSelection()V

    .line 1358
    return-void
.end method
