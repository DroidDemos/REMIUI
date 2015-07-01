.class Lmiui/widget/TimePicker$4;
.super Ljava/lang/Object;
.source "SourceFile"

# interfaces
.implements Lmiui/widget/NumberPicker$OnValueChangeListener;


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
    .line 156
    iput-object p1, p0, Lmiui/widget/TimePicker$4;->TS:Lmiui/widget/TimePicker;

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method


# virtual methods
.method public onValueChange(Lmiui/widget/NumberPicker;II)V
    .locals 2

    .prologue
    .line 158
    invoke-virtual {p1}, Lmiui/widget/NumberPicker;->requestFocus()Z

    .line 159
    iget-object v1, p0, Lmiui/widget/TimePicker$4;->TS:Lmiui/widget/TimePicker;

    iget-object v0, p0, Lmiui/widget/TimePicker$4;->TS:Lmiui/widget/TimePicker;

    invoke-static {v0}, Lmiui/widget/TimePicker;->a(Lmiui/widget/TimePicker;)Z

    move-result v0

    if-nez v0, :cond_0

    const/4 v0, 0x1

    :goto_0
    invoke-static {v1, v0}, Lmiui/widget/TimePicker;->a(Lmiui/widget/TimePicker;Z)Z

    .line 160
    iget-object v0, p0, Lmiui/widget/TimePicker$4;->TS:Lmiui/widget/TimePicker;

    invoke-static {v0}, Lmiui/widget/TimePicker;->b(Lmiui/widget/TimePicker;)V

    .line 161
    iget-object v0, p0, Lmiui/widget/TimePicker$4;->TS:Lmiui/widget/TimePicker;

    invoke-static {v0}, Lmiui/widget/TimePicker;->c(Lmiui/widget/TimePicker;)V

    .line 162
    return-void

    .line 159
    :cond_0
    const/4 v0, 0x0

    goto :goto_0
.end method
