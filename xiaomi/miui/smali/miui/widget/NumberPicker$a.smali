.class Lmiui/widget/NumberPicker$a;
.super Ljava/lang/Object;
.source "SourceFile"

# interfaces
.implements Ljava/lang/Runnable;


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lmiui/widget/NumberPicker;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x0
    name = "a"
.end annotation


# instance fields
.field private aA:I

.field private aB:I

.field final synthetic aC:Lmiui/widget/NumberPicker;


# direct methods
.method constructor <init>(Lmiui/widget/NumberPicker;)V
    .locals 0

    .prologue
    .line 2013
    iput-object p1, p0, Lmiui/widget/NumberPicker$a;->aC:Lmiui/widget/NumberPicker;

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method

.method static synthetic a(Lmiui/widget/NumberPicker$a;I)I
    .locals 0

    .prologue
    .line 2013
    iput p1, p0, Lmiui/widget/NumberPicker$a;->aA:I

    return p1
.end method

.method static synthetic b(Lmiui/widget/NumberPicker$a;I)I
    .locals 0

    .prologue
    .line 2013
    iput p1, p0, Lmiui/widget/NumberPicker$a;->aB:I

    return p1
.end method


# virtual methods
.method public run()V
    .locals 3

    .prologue
    .line 2019
    iget-object v0, p0, Lmiui/widget/NumberPicker$a;->aC:Lmiui/widget/NumberPicker;

    invoke-static {v0}, Lmiui/widget/NumberPicker;->a(Lmiui/widget/NumberPicker;)Landroid/widget/EditText;

    move-result-object v0

    iget v1, p0, Lmiui/widget/NumberPicker$a;->aA:I

    iget v2, p0, Lmiui/widget/NumberPicker$a;->aB:I

    invoke-virtual {v0, v1, v2}, Landroid/widget/EditText;->setSelection(II)V

    .line 2020
    return-void
.end method
