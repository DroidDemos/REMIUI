.class Lmiui/app/ProgressDialog$1;
.super Landroid/os/Handler;
.source "SourceFile"


# annotations
.annotation system Ldalvik/annotation/EnclosingMethod;
    value = Lmiui/app/ProgressDialog;->onCreate(Landroid/os/Bundle;)V
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x0
    name = null
.end annotation


# instance fields
.field final synthetic aj:Lmiui/app/ProgressDialog;


# direct methods
.method constructor <init>(Lmiui/app/ProgressDialog;)V
    .locals 0

    .prologue
    .line 175
    iput-object p1, p0, Lmiui/app/ProgressDialog$1;->aj:Lmiui/app/ProgressDialog;

    invoke-direct {p0}, Landroid/os/Handler;-><init>()V

    return-void
.end method


# virtual methods
.method public handleMessage(Landroid/os/Message;)V
    .locals 6

    .prologue
    .line 178
    invoke-super {p0, p1}, Landroid/os/Handler;->handleMessage(Landroid/os/Message;)V

    .line 180
    iget-object v0, p0, Lmiui/app/ProgressDialog$1;->aj:Lmiui/app/ProgressDialog;

    invoke-static {v0}, Lmiui/app/ProgressDialog;->a(Lmiui/app/ProgressDialog;)Landroid/widget/ProgressBar;

    move-result-object v0

    invoke-virtual {v0}, Landroid/widget/ProgressBar;->getProgress()I

    move-result v0

    .line 181
    iget-object v1, p0, Lmiui/app/ProgressDialog$1;->aj:Lmiui/app/ProgressDialog;

    invoke-static {v1}, Lmiui/app/ProgressDialog;->a(Lmiui/app/ProgressDialog;)Landroid/widget/ProgressBar;

    move-result-object v1

    invoke-virtual {v1}, Landroid/widget/ProgressBar;->getMax()I

    move-result v1

    .line 183
    iget-object v2, p0, Lmiui/app/ProgressDialog$1;->aj:Lmiui/app/ProgressDialog;

    invoke-static {v2}, Lmiui/app/ProgressDialog;->b(Lmiui/app/ProgressDialog;)Ljava/text/NumberFormat;

    move-result-object v2

    if-eqz v2, :cond_1

    .line 184
    int-to-double v2, v0

    int-to-double v0, v1

    div-double v1, v2, v0

    .line 185
    const/4 v0, 0x0

    .line 186
    new-instance v3, Landroid/text/SpannableStringBuilder;

    invoke-direct {v3}, Landroid/text/SpannableStringBuilder;-><init>()V

    .line 187
    iget-object v4, p0, Lmiui/app/ProgressDialog$1;->aj:Lmiui/app/ProgressDialog;

    invoke-static {v4}, Lmiui/app/ProgressDialog;->c(Lmiui/app/ProgressDialog;)Ljava/lang/CharSequence;

    move-result-object v4

    invoke-static {v4}, Landroid/text/TextUtils;->isEmpty(Ljava/lang/CharSequence;)Z

    move-result v4

    if-nez v4, :cond_0

    .line 188
    iget-object v0, p0, Lmiui/app/ProgressDialog$1;->aj:Lmiui/app/ProgressDialog;

    invoke-static {v0}, Lmiui/app/ProgressDialog;->c(Lmiui/app/ProgressDialog;)Ljava/lang/CharSequence;

    move-result-object v0

    invoke-interface {v0}, Ljava/lang/CharSequence;->length()I

    move-result v0

    .line 189
    iget-object v4, p0, Lmiui/app/ProgressDialog$1;->aj:Lmiui/app/ProgressDialog;

    invoke-static {v4}, Lmiui/app/ProgressDialog;->c(Lmiui/app/ProgressDialog;)Ljava/lang/CharSequence;

    move-result-object v4

    invoke-virtual {v3, v4}, Landroid/text/SpannableStringBuilder;->append(Ljava/lang/CharSequence;)Landroid/text/SpannableStringBuilder;

    .line 191
    :cond_0
    iget-object v4, p0, Lmiui/app/ProgressDialog$1;->aj:Lmiui/app/ProgressDialog;

    invoke-static {v4}, Lmiui/app/ProgressDialog;->b(Lmiui/app/ProgressDialog;)Ljava/text/NumberFormat;

    move-result-object v4

    invoke-virtual {v4, v1, v2}, Ljava/text/NumberFormat;->format(D)Ljava/lang/String;

    move-result-object v1

    .line 192
    invoke-virtual {v3, v1}, Landroid/text/SpannableStringBuilder;->append(Ljava/lang/CharSequence;)Landroid/text/SpannableStringBuilder;

    .line 193
    new-instance v2, Landroid/text/style/ForegroundColorSpan;

    iget-object v4, p0, Lmiui/app/ProgressDialog$1;->aj:Lmiui/app/ProgressDialog;

    invoke-virtual {v4}, Lmiui/app/ProgressDialog;->getContext()Landroid/content/Context;

    move-result-object v4

    invoke-virtual {v4}, Landroid/content/Context;->getResources()Landroid/content/res/Resources;

    move-result-object v4

    sget v5, Lcom/miui/internal/R$color;->progress_percent_color:I

    invoke-virtual {v4, v5}, Landroid/content/res/Resources;->getColor(I)I

    move-result v4

    invoke-direct {v2, v4}, Landroid/text/style/ForegroundColorSpan;-><init>(I)V

    invoke-virtual {v1}, Ljava/lang/String;->length()I

    move-result v1

    add-int/2addr v1, v0

    const/16 v4, 0x22

    invoke-virtual {v3, v2, v0, v1, v4}, Landroid/text/SpannableStringBuilder;->setSpan(Ljava/lang/Object;III)V

    .line 199
    iget-object v0, p0, Lmiui/app/ProgressDialog$1;->aj:Lmiui/app/ProgressDialog;

    invoke-static {v0, v3}, Lmiui/app/ProgressDialog;->a(Lmiui/app/ProgressDialog;Ljava/lang/CharSequence;)V

    .line 201
    :cond_1
    return-void
.end method
