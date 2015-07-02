.class Lmiui/app/DatePickerDialog$1;
.super Ljava/lang/Object;
.source "SourceFile"

# interfaces
.implements Landroid/content/DialogInterface$OnClickListener;


# annotations
.annotation system Ldalvik/annotation/EnclosingMethod;
    value = Lmiui/app/DatePickerDialog;-><init>(Landroid/content/Context;ILmiui/app/DatePickerDialog$OnDateSetListener;III)V
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
    .line 92
    iput-object p1, p0, Lmiui/app/DatePickerDialog$1;->Hz:Lmiui/app/DatePickerDialog;

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method


# virtual methods
.method public onClick(Landroid/content/DialogInterface;I)V
    .locals 1

    .prologue
    .line 95
    iget-object v0, p0, Lmiui/app/DatePickerDialog$1;->Hz:Lmiui/app/DatePickerDialog;

    invoke-static {v0}, Lmiui/app/DatePickerDialog;->a(Lmiui/app/DatePickerDialog;)V

    .line 96
    return-void
.end method
