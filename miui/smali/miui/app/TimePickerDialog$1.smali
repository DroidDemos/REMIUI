.class Lmiui/app/TimePickerDialog$1;
.super Ljava/lang/Object;
.source "SourceFile"

# interfaces
.implements Landroid/content/DialogInterface$OnClickListener;


# annotations
.annotation system Ldalvik/annotation/EnclosingMethod;
    value = Lmiui/app/TimePickerDialog;-><init>(Landroid/content/Context;ILmiui/app/TimePickerDialog$OnTimeSetListener;IIZ)V
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x0
    name = null
.end annotation


# instance fields
.field final synthetic Dr:Lmiui/app/TimePickerDialog;


# direct methods
.method constructor <init>(Lmiui/app/TimePickerDialog;)V
    .locals 0

    .prologue
    .line 81
    iput-object p1, p0, Lmiui/app/TimePickerDialog$1;->Dr:Lmiui/app/TimePickerDialog;

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method


# virtual methods
.method public onClick(Landroid/content/DialogInterface;I)V
    .locals 1

    .prologue
    .line 84
    iget-object v0, p0, Lmiui/app/TimePickerDialog$1;->Dr:Lmiui/app/TimePickerDialog;

    invoke-static {v0}, Lmiui/app/TimePickerDialog;->a(Lmiui/app/TimePickerDialog;)V

    .line 85
    return-void
.end method
