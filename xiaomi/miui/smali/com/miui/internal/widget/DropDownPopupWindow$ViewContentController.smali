.class public Lcom/miui/internal/widget/DropDownPopupWindow$ViewContentController;
.super Ljava/lang/Object;
.source "SourceFile"

# interfaces
.implements Lcom/miui/internal/widget/DropDownPopupWindow$ContentController;


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lcom/miui/internal/widget/DropDownPopupWindow;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x9
    name = "ViewContentController"
.end annotation


# instance fields
.field private GK:I

.field private mContext:Landroid/content/Context;

.field private nM:Landroid/view/View;


# direct methods
.method public constructor <init>(Landroid/content/Context;I)V
    .locals 0

    .prologue
    .line 170
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    .line 171
    iput-object p1, p0, Lcom/miui/internal/widget/DropDownPopupWindow$ViewContentController;->mContext:Landroid/content/Context;

    .line 172
    iput p2, p0, Lcom/miui/internal/widget/DropDownPopupWindow$ViewContentController;->GK:I

    .line 173
    return-void
.end method

.method public constructor <init>(Lcom/miui/internal/widget/DropDownPopupWindow;I)V
    .locals 1

    .prologue
    .line 166
    invoke-virtual {p1}, Lcom/miui/internal/widget/DropDownPopupWindow;->getContext()Landroid/content/Context;

    move-result-object v0

    invoke-direct {p0, v0, p2}, Lcom/miui/internal/widget/DropDownPopupWindow$ViewContentController;-><init>(Landroid/content/Context;I)V

    .line 167
    invoke-virtual {p1, p0}, Lcom/miui/internal/widget/DropDownPopupWindow;->setContentController(Lcom/miui/internal/widget/DropDownPopupWindow$ContentController;)V

    .line 168
    return-void
.end method


# virtual methods
.method public getContentView()Landroid/view/View;
    .locals 1

    .prologue
    .line 205
    invoke-virtual {p0}, Lcom/miui/internal/widget/DropDownPopupWindow$ViewContentController;->initContent()V

    .line 206
    iget-object v0, p0, Lcom/miui/internal/widget/DropDownPopupWindow$ViewContentController;->nM:Landroid/view/View;

    return-object v0
.end method

.method protected initContent()V
    .locals 3

    .prologue
    .line 176
    iget-object v0, p0, Lcom/miui/internal/widget/DropDownPopupWindow$ViewContentController;->nM:Landroid/view/View;

    if-nez v0, :cond_0

    .line 177
    iget-object v0, p0, Lcom/miui/internal/widget/DropDownPopupWindow$ViewContentController;->mContext:Landroid/content/Context;

    invoke-static {v0}, Landroid/view/LayoutInflater;->from(Landroid/content/Context;)Landroid/view/LayoutInflater;

    move-result-object v0

    .line 178
    iget v1, p0, Lcom/miui/internal/widget/DropDownPopupWindow$ViewContentController;->GK:I

    const/4 v2, 0x0

    invoke-virtual {v0, v1, v2}, Landroid/view/LayoutInflater;->inflate(ILandroid/view/ViewGroup;)Landroid/view/View;

    move-result-object v0

    iput-object v0, p0, Lcom/miui/internal/widget/DropDownPopupWindow$ViewContentController;->nM:Landroid/view/View;

    .line 179
    iget-object v0, p0, Lcom/miui/internal/widget/DropDownPopupWindow$ViewContentController;->nM:Landroid/view/View;

    invoke-virtual {p0, v0}, Lcom/miui/internal/widget/DropDownPopupWindow$ViewContentController;->onContentInit(Landroid/view/View;)V

    .line 181
    :cond_0
    return-void
.end method

.method public onAniamtionUpdate(Landroid/view/View;F)V
    .locals 2

    .prologue
    .line 197
    if-eqz p1, :cond_0

    .line 198
    invoke-virtual {p1}, Landroid/view/View;->getHeight()I

    move-result v0

    neg-int v0, v0

    int-to-float v0, v0

    const/high16 v1, 0x3f800000

    sub-float/2addr v1, p2

    mul-float/2addr v0, v1

    .line 199
    invoke-virtual {p1, v0}, Landroid/view/View;->setTranslationY(F)V

    .line 201
    :cond_0
    return-void
.end method

.method protected onContentInit(Landroid/view/View;)V
    .locals 0

    .prologue
    .line 183
    return-void
.end method

.method public onDismiss()V
    .locals 0

    .prologue
    .line 193
    return-void
.end method

.method public onShow()V
    .locals 0

    .prologue
    .line 188
    return-void
.end method
