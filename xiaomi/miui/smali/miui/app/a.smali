.class Lmiui/app/a;
.super Ljava/lang/Object;
.source "SourceFile"

# interfaces
.implements Lmiui/widget/DatePicker$OnDateChangedListener;


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lmiui/app/DatePickerDialog;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x0
    name = null
.end annotation


# instance fields
.field final synthetic Hz:Lmiui/app/DatePickerDialog;


# direct methods
.method constructor <init>(Lmiui/app/DatePickerDialog;)V
    .locals 0

    .prologue
    .line 49
    iput-object p1, p0, Lmiui/app/a;->Hz:Lmiui/app/DatePickerDialog;

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method


# virtual methods
.method public onDateChanged(Lmiui/widget/DatePicker;IIIZ)V
    .locals 1

    .prologue
    .line 52
    iget-object v0, p0, Lmiui/app/a;->Hz:Lmiui/app/DatePickerDialog;

    invoke-static {v0, p2, p3, p4}, Lmiui/app/DatePickerDialog;->a(Lmiui/app/DatePickerDialog;III)V

    .line 53
    return-void
.end method
