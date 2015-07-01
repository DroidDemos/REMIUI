.class public Lmiui/widget/GuidePopupWindow;
.super Lmiui/widget/ArrowPopupWindow;
.source "SourceFile"


# static fields
.field public static final ARROW_BOTTOM_LEFT_MODE:I = 0x0

.field public static final ARROW_BOTTOM_MODE:I = 0x0

.field public static final ARROW_BOTTOM_RIGHT_MODE:I = 0x0

.field public static final ARROW_LEFT_MODE:I = 0x3

.field public static final ARROW_RIGHT_MODE:I = 0x2

.field public static final ARROW_TOP_LEFT_MODE:I = 0x1

.field public static final ARROW_TOP_MODE:I = 0x1

.field public static final ARROW_TOP_RIGHT_MODE:I = 0x1

.field private static final Rj:I = 0x1388


# instance fields
.field private Ok:Ljava/lang/Runnable;

.field private Rh:Landroid/widget/LinearLayout;

.field private Ri:Landroid/content/Context;

.field private kA:I


# direct methods
.method public constructor <init>(Landroid/content/Context;)V
    .locals 1

    .prologue
    .line 93
    invoke-direct {p0, p1}, Lmiui/widget/ArrowPopupWindow;-><init>(Landroid/content/Context;)V

    .line 236
    new-instance v0, Lmiui/widget/e;

    invoke-direct {v0, p0}, Lmiui/widget/e;-><init>(Lmiui/widget/GuidePopupWindow;)V

    iput-object v0, p0, Lmiui/widget/GuidePopupWindow;->Ok:Ljava/lang/Runnable;

    .line 94
    return-void
.end method

.method public constructor <init>(Landroid/content/Context;Landroid/util/AttributeSet;)V
    .locals 1

    .prologue
    .line 103
    invoke-direct {p0, p1, p2}, Lmiui/widget/ArrowPopupWindow;-><init>(Landroid/content/Context;Landroid/util/AttributeSet;)V

    .line 236
    new-instance v0, Lmiui/widget/e;

    invoke-direct {v0, p0}, Lmiui/widget/e;-><init>(Lmiui/widget/GuidePopupWindow;)V

    iput-object v0, p0, Lmiui/widget/GuidePopupWindow;->Ok:Ljava/lang/Runnable;

    .line 104
    return-void
.end method

.method public constructor <init>(Landroid/content/Context;Landroid/util/AttributeSet;I)V
    .locals 1

    .prologue
    .line 114
    invoke-direct {p0, p1, p2, p3}, Lmiui/widget/ArrowPopupWindow;-><init>(Landroid/content/Context;Landroid/util/AttributeSet;I)V

    .line 236
    new-instance v0, Lmiui/widget/e;

    invoke-direct {v0, p0}, Lmiui/widget/e;-><init>(Lmiui/widget/GuidePopupWindow;)V

    iput-object v0, p0, Lmiui/widget/GuidePopupWindow;->Ok:Ljava/lang/Runnable;

    .line 115
    return-void
.end method

.method public constructor <init>(Landroid/content/Context;Landroid/util/AttributeSet;II)V
    .locals 1

    .prologue
    .line 126
    invoke-direct {p0, p1, p2, p3, p4}, Lmiui/widget/ArrowPopupWindow;-><init>(Landroid/content/Context;Landroid/util/AttributeSet;II)V

    .line 236
    new-instance v0, Lmiui/widget/e;

    invoke-direct {v0, p0}, Lmiui/widget/e;-><init>(Lmiui/widget/GuidePopupWindow;)V

    iput-object v0, p0, Lmiui/widget/GuidePopupWindow;->Ok:Ljava/lang/Runnable;

    .line 127
    return-void
.end method

.method private G(Ljava/lang/String;)V
    .locals 7

    .prologue
    .line 176
    invoke-static {p1}, Landroid/text/TextUtils;->isEmpty(Ljava/lang/CharSequence;)Z

    move-result v0

    if-eqz v0, :cond_1

    .line 189
    :cond_0
    return-void

    .line 179
    :cond_1
    const-string v0, "\n"

    invoke-virtual {p1, v0}, Ljava/lang/String;->split(Ljava/lang/String;)[Ljava/lang/String;

    move-result-object v2

    .line 180
    if-eqz v2, :cond_0

    array-length v0, v2

    if-eqz v0, :cond_0

    .line 183
    invoke-virtual {p0}, Lmiui/widget/GuidePopupWindow;->getLayoutInflater()Landroid/view/LayoutInflater;

    move-result-object v3

    .line 184
    array-length v4, v2

    const/4 v0, 0x0

    move v1, v0

    :goto_0
    if-ge v1, v4, :cond_0

    aget-object v5, v2, v1

    .line 185
    sget v0, Lcom/miui/internal/R$layout;->guide_popup_text_view:I

    const/4 v6, 0x0

    invoke-virtual {v3, v0, v6}, Landroid/view/LayoutInflater;->inflate(ILandroid/view/ViewGroup;)Landroid/view/View;

    move-result-object v0

    check-cast v0, Landroid/widget/TextView;

    .line 186
    invoke-virtual {v0, v5}, Landroid/widget/TextView;->setText(Ljava/lang/CharSequence;)V

    .line 187
    iget-object v5, p0, Lmiui/widget/GuidePopupWindow;->Rh:Landroid/widget/LinearLayout;

    invoke-virtual {v5, v0}, Landroid/widget/LinearLayout;->addView(Landroid/view/View;)V

    .line 184
    add-int/lit8 v0, v1, 0x1

    move v1, v0

    goto :goto_0
.end method


# virtual methods
.method protected getLayoutInflater()Landroid/view/LayoutInflater;
    .locals 3

    .prologue
    .line 149
    iget-object v0, p0, Lmiui/widget/GuidePopupWindow;->Ri:Landroid/content/Context;

    if-nez v0, :cond_0

    .line 150
    new-instance v0, Landroid/view/ContextThemeWrapper;

    invoke-virtual {p0}, Lmiui/widget/GuidePopupWindow;->getContext()Landroid/content/Context;

    move-result-object v1

    sget v2, Lcom/miui/internal/R$style;->Theme_Light_Guide:I

    invoke-direct {v0, v1, v2}, Landroid/view/ContextThemeWrapper;-><init>(Landroid/content/Context;I)V

    iput-object v0, p0, Lmiui/widget/GuidePopupWindow;->Ri:Landroid/content/Context;

    .line 152
    :cond_0
    iget-object v0, p0, Lmiui/widget/GuidePopupWindow;->Ri:Landroid/content/Context;

    invoke-static {v0}, Landroid/view/LayoutInflater;->from(Landroid/content/Context;)Landroid/view/LayoutInflater;

    move-result-object v0

    .line 153
    return-object v0
.end method

.method protected onPrepareWindow()V
    .locals 5

    .prologue
    const/4 v4, 0x1

    .line 131
    invoke-super {p0}, Lmiui/widget/ArrowPopupWindow;->onPrepareWindow()V

    .line 133
    const/16 v0, 0x1388

    iput v0, p0, Lmiui/widget/GuidePopupWindow;->kA:I

    .line 134
    invoke-virtual {p0, v4}, Lmiui/widget/GuidePopupWindow;->setFocusable(Z)V

    .line 136
    invoke-virtual {p0}, Lmiui/widget/GuidePopupWindow;->getLayoutInflater()Landroid/view/LayoutInflater;

    move-result-object v0

    .line 137
    sget v1, Lcom/miui/internal/R$layout;->guide_popup_content_view:I

    const/4 v2, 0x0

    const/4 v3, 0x0

    invoke-virtual {v0, v1, v2, v3}, Landroid/view/LayoutInflater;->inflate(ILandroid/view/ViewGroup;Z)Landroid/view/View;

    move-result-object v0

    check-cast v0, Landroid/widget/LinearLayout;

    iput-object v0, p0, Lmiui/widget/GuidePopupWindow;->Rh:Landroid/widget/LinearLayout;

    .line 138
    iget-object v0, p0, Lmiui/widget/GuidePopupWindow;->Rh:Landroid/widget/LinearLayout;

    invoke-virtual {p0, v0}, Lmiui/widget/GuidePopupWindow;->setContentView(Landroid/view/View;)V

    .line 140
    iget-object v0, p0, Lmiui/widget/GuidePopupWindow;->mArrowPopupView:Lcom/miui/internal/widget/ArrowPopupView;

    invoke-virtual {v0, v4}, Lcom/miui/internal/widget/ArrowPopupView;->enableShowingAnimation(Z)V

    .line 141
    return-void
.end method

.method public setGuideText(I)V
    .locals 1

    .prologue
    .line 171
    iget-object v0, p0, Lmiui/widget/GuidePopupWindow;->Ri:Landroid/content/Context;

    invoke-virtual {v0, p1}, Landroid/content/Context;->getString(I)Ljava/lang/String;

    move-result-object v0

    .line 172
    invoke-virtual {p0, v0}, Lmiui/widget/GuidePopupWindow;->setGuideText(Ljava/lang/String;)V

    .line 173
    return-void
.end method

.method public setGuideText(Ljava/lang/String;)V
    .locals 0

    .prologue
    .line 162
    invoke-direct {p0, p1}, Lmiui/widget/GuidePopupWindow;->G(Ljava/lang/String;)V

    .line 163
    return-void
.end method

.method public setOffset(II)V
    .locals 1

    .prologue
    .line 225
    iget-object v0, p0, Lmiui/widget/GuidePopupWindow;->mArrowPopupView:Lcom/miui/internal/widget/ArrowPopupView;

    invoke-virtual {v0, p1, p2}, Lcom/miui/internal/widget/ArrowPopupView;->setOffset(II)V

    .line 226
    return-void
.end method

.method public setShowDuration(I)V
    .locals 0

    .prologue
    .line 233
    iput p1, p0, Lmiui/widget/GuidePopupWindow;->kA:I

    .line 234
    return-void
.end method

.method public show(Landroid/view/View;IIZ)V
    .locals 4

    .prologue
    .line 213
    invoke-virtual {p0, p1, p2, p3}, Lmiui/widget/GuidePopupWindow;->show(Landroid/view/View;II)V

    .line 214
    if-eqz p4, :cond_0

    .line 215
    iget-object v0, p0, Lmiui/widget/GuidePopupWindow;->mArrowPopupView:Lcom/miui/internal/widget/ArrowPopupView;

    iget-object v1, p0, Lmiui/widget/GuidePopupWindow;->Ok:Ljava/lang/Runnable;

    iget v2, p0, Lmiui/widget/GuidePopupWindow;->kA:I

    int-to-long v2, v2

    invoke-virtual {v0, v1, v2, v3}, Lcom/miui/internal/widget/ArrowPopupView;->postDelayed(Ljava/lang/Runnable;J)Z

    .line 217
    :cond_0
    return-void
.end method

.method public show(Landroid/view/View;Z)V
    .locals 1

    .prologue
    const/4 v0, 0x0

    .line 199
    invoke-virtual {p0, p1, v0, v0, p2}, Lmiui/widget/GuidePopupWindow;->show(Landroid/view/View;IIZ)V

    .line 200
    return-void
.end method
