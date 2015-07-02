.class Lmiui/widget/TimePicker$2;
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
    .line 129
    iput-object p1, p0, Lmiui/widget/TimePicker$2;->TS:Lmiui/widget/TimePicker;

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method


# virtual methods
.method public onValueChange(Lmiui/widget/NumberPicker;II)V
    .locals 1

    .prologue
    .line 131
    iget-object v0, p0, Lmiui/widget/TimePicker$2;->TS:Lmiui/widget/TimePicker;

    invoke-static {v0}, Lmiui/widget/TimePicker;->c(Lmiui/widget/TimePicker;)V

    .line 132
    return-void
.end method
