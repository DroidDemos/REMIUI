.class Lmiui/widget/NumberPicker$f;
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
    name = "f"
.end annotation


# instance fields
.field private QD:Z

.field final synthetic aC:Lmiui/widget/NumberPicker;


# direct methods
.method constructor <init>(Lmiui/widget/NumberPicker;)V
    .locals 0

    .prologue
    .line 2026
    iput-object p1, p0, Lmiui/widget/NumberPicker$f;->aC:Lmiui/widget/NumberPicker;

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method

.method static synthetic a(Lmiui/widget/NumberPicker$f;Z)V
    .locals 0

    .prologue
    .line 2026
    invoke-direct {p0, p1}, Lmiui/widget/NumberPicker$f;->u(Z)V

    return-void
.end method

.method private u(Z)V
    .locals 0

    .prologue
    .line 2030
    iput-boolean p1, p0, Lmiui/widget/NumberPicker$f;->QD:Z

    .line 2031
    return-void
.end method


# virtual methods
.method public run()V
    .locals 3

    .prologue
    .line 2035
    iget-object v0, p0, Lmiui/widget/NumberPicker$f;->aC:Lmiui/widget/NumberPicker;

    iget-boolean v1, p0, Lmiui/widget/NumberPicker$f;->QD:Z

    invoke-static {v0, v1}, Lmiui/widget/NumberPicker;->c(Lmiui/widget/NumberPicker;Z)V

    .line 2036
    iget-object v0, p0, Lmiui/widget/NumberPicker$f;->aC:Lmiui/widget/NumberPicker;

    iget-object v1, p0, Lmiui/widget/NumberPicker$f;->aC:Lmiui/widget/NumberPicker;

    invoke-static {v1}, Lmiui/widget/NumberPicker;->h(Lmiui/widget/NumberPicker;)J

    move-result-wide v1

    invoke-virtual {v0, p0, v1, v2}, Lmiui/widget/NumberPicker;->postDelayed(Ljava/lang/Runnable;J)Z

    .line 2037
    return-void
.end method
