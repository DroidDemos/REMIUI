.class Lmiui/widget/TimePicker$3;
.super Ljava/lang/Object;
.source "SourceFile"

# interfaces
.implements Landroid/view/View$OnClickListener;


# annotations
.annotation system Ldalvik/annotation/EnclosingMethod;
    value = Lmiui/widget/TimePicker;-><init>(Landroid/content/Context;Landroid/util/AttributeSet;I)V
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x0
    name = null
.end annotation


# instance fields
.field final synthetic TS:Lmiui/widget/TimePicker;


# direct methods
.method constructor <init>(Lmiui/widget/TimePicker;)V
    .locals 0

    .prologue
    .line 142
    iput-object p1, p0, Lmiui/widget/TimePicker$3;->TS:Lmiui/widget/TimePicker;

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method


# virtual methods
.method public onClick(Landroid/view/View;)V
    .locals 2

    .prologue
    .line 144
    invoke-virtual {p1}, Landroid/view/View;->requestFocus()Z

    .line 145
    iget-object v1, p0, Lmiui/widget/TimePicker$3;->TS:Lmiui/widget/TimePicker;

    iget-object v0, p0, Lmiui/widget/TimePicker$3;->TS:Lmiui/widget/TimePicker;

    invoke-static {v0}, Lmiui/widget/TimePicker;->a(Lmiui/widget/TimePicker;)Z

    move-result v0

    if-nez v0, :cond_0

    const/4 v0, 0x1

    :goto_0
    invoke-static {v1, v0}, Lmiui/widget/TimePicker;->a(Lmiui/widget/TimePicker;Z)Z

    .line 146
    iget-object v0, p0, Lmiui/widget/TimePicker$3;->TS:Lmiui/widget/TimePicker;

    invoke-static {v0}, Lmiui/widget/TimePicker;->b(Lmiui/widget/TimePicker;)V

    .line 147
    iget-object v0, p0, Lmiui/widget/TimePicker$3;->TS:Lmiui/widget/TimePicker;

    invoke-static {v0}, Lmiui/widget/TimePicker;->c(Lmiui/widget/TimePicker;)V

    .line 148
    return-void

    .line 145
    :cond_0
    const/4 v0, 0x0

    goto :goto_0
.end method
