.class Lmiui/widget/PictureClock$a;
.super Ljava/lang/Object;
.source "SourceFile"


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lmiui/widget/PictureClock;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x8
    name = "a"
.end annotation


# instance fields
.field private Ih:Landroid/widget/TextView;

.field private Ii:Ljava/lang/String;

.field private Ij:Ljava/lang/String;


# direct methods
.method constructor <init>(Landroid/view/View;)V
    .locals 2

    .prologue
    .line 139
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    .line 140
    sget v0, Lcom/miui/internal/R$id;->am_pm:I

    invoke-virtual {p1, v0}, Landroid/view/View;->findViewById(I)Landroid/view/View;

    move-result-object v0

    check-cast v0, Landroid/widget/TextView;

    iput-object v0, p0, Lmiui/widget/PictureClock$a;->Ih:Landroid/widget/TextView;

    .line 142
    invoke-static {}, Lmiui/date/CalendarFormatSymbols;->getDefault()Lmiui/date/CalendarFormatSymbols;

    move-result-object v0

    invoke-virtual {v0}, Lmiui/date/CalendarFormatSymbols;->getAmPms()[Ljava/lang/String;

    move-result-object v0

    .line 143
    const/4 v1, 0x0

    aget-object v1, v0, v1

    iput-object v1, p0, Lmiui/widget/PictureClock$a;->Ii:Ljava/lang/String;

    .line 144
    const/4 v1, 0x1

    aget-object v0, v0, v1

    iput-object v0, p0, Lmiui/widget/PictureClock$a;->Ij:Ljava/lang/String;

    .line 145
    return-void
.end method


# virtual methods
.method m(Z)V
    .locals 2

    .prologue
    .line 148
    iget-object v0, p0, Lmiui/widget/PictureClock$a;->Ih:Landroid/widget/TextView;

    if-eqz v0, :cond_0

    .line 149
    iget-object v1, p0, Lmiui/widget/PictureClock$a;->Ih:Landroid/widget/TextView;

    if-eqz p1, :cond_1

    const/4 v0, 0x0

    :goto_0
    invoke-virtual {v1, v0}, Landroid/widget/TextView;->setVisibility(I)V

    .line 151
    :cond_0
    return-void

    .line 149
    :cond_1
    const/16 v0, 0x8

    goto :goto_0
.end method

.method n(Z)V
    .locals 2

    .prologue
    .line 154
    iget-object v0, p0, Lmiui/widget/PictureClock$a;->Ih:Landroid/widget/TextView;

    if-eqz v0, :cond_0

    .line 155
    iget-object v1, p0, Lmiui/widget/PictureClock$a;->Ih:Landroid/widget/TextView;

    if-eqz p1, :cond_1

    iget-object v0, p0, Lmiui/widget/PictureClock$a;->Ii:Ljava/lang/String;

    :goto_0
    invoke-virtual {v1, v0}, Landroid/widget/TextView;->setText(Ljava/lang/CharSequence;)V

    .line 157
    :cond_0
    return-void

    .line 155
    :cond_1
    iget-object v0, p0, Lmiui/widget/PictureClock$a;->Ij:Ljava/lang/String;

    goto :goto_0
.end method
