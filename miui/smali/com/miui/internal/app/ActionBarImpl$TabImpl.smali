.class public Lcom/miui/internal/app/ActionBarImpl$TabImpl;
.super Landroid/app/ActionBar$Tab;
.source "SourceFile"


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lcom/miui/internal/app/ActionBarImpl;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x1
    name = "TabImpl"
.end annotation


# instance fields
.field private Qv:I

.field private RL:Landroid/app/ActionBar$TabListener;

.field private RM:Landroid/app/ActionBar$TabListener;

.field private RN:Ljava/lang/Object;

.field private RP:Ljava/lang/CharSequence;

.field private hB:Landroid/graphics/drawable/Drawable;

.field private o:Landroid/view/View;

.field private px:Ljava/lang/CharSequence;

.field final synthetic qO:Lcom/miui/internal/app/ActionBarImpl;


# direct methods
.method public constructor <init>(Lcom/miui/internal/app/ActionBarImpl;)V
    .locals 1

    .prologue
    .line 1154
    iput-object p1, p0, Lcom/miui/internal/app/ActionBarImpl$TabImpl;->qO:Lcom/miui/internal/app/ActionBarImpl;

    invoke-direct {p0}, Landroid/app/ActionBar$Tab;-><init>()V

    .line 1168
    const/4 v0, -0x1

    iput v0, p0, Lcom/miui/internal/app/ActionBarImpl$TabImpl;->Qv:I

    return-void
.end method

.method static synthetic a(Lcom/miui/internal/app/ActionBarImpl$TabImpl;)Landroid/app/ActionBar$TabListener;
    .locals 1

    .prologue
    .line 1154
    iget-object v0, p0, Lcom/miui/internal/app/ActionBarImpl$TabImpl;->RM:Landroid/app/ActionBar$TabListener;

    return-object v0
.end method

.method static synthetic b(Lcom/miui/internal/app/ActionBarImpl$TabImpl;)Landroid/app/ActionBar$TabListener;
    .locals 1

    .prologue
    .line 1154
    iget-object v0, p0, Lcom/miui/internal/app/ActionBarImpl$TabImpl;->RL:Landroid/app/ActionBar$TabListener;

    return-object v0
.end method


# virtual methods
.method public getCallback()Landroid/app/ActionBar$TabListener;
    .locals 1

    .prologue
    .line 1184
    invoke-static {}, Lcom/miui/internal/app/ActionBarImpl;->er()Landroid/app/ActionBar$TabListener;

    move-result-object v0

    return-object v0
.end method

.method public getContentDescription()Ljava/lang/CharSequence;
    .locals 1

    .prologue
    .line 1287
    iget-object v0, p0, Lcom/miui/internal/app/ActionBarImpl$TabImpl;->RP:Ljava/lang/CharSequence;

    return-object v0
.end method

.method public getCustomView()Landroid/view/View;
    .locals 1

    .prologue
    .line 1200
    iget-object v0, p0, Lcom/miui/internal/app/ActionBarImpl$TabImpl;->o:Landroid/view/View;

    return-object v0
.end method

.method public getIcon()Landroid/graphics/drawable/Drawable;
    .locals 1

    .prologue
    .line 1220
    iget-object v0, p0, Lcom/miui/internal/app/ActionBarImpl$TabImpl;->hB:Landroid/graphics/drawable/Drawable;

    return-object v0
.end method

.method public getPosition()I
    .locals 1

    .prologue
    .line 1225
    iget v0, p0, Lcom/miui/internal/app/ActionBarImpl$TabImpl;->Qv:I

    return v0
.end method

.method public getTag()Ljava/lang/Object;
    .locals 1

    .prologue
    .line 1174
    iget-object v0, p0, Lcom/miui/internal/app/ActionBarImpl$TabImpl;->RN:Ljava/lang/Object;

    return-object v0
.end method

.method public getText()Ljava/lang/CharSequence;
    .locals 1

    .prologue
    .line 1234
    iget-object v0, p0, Lcom/miui/internal/app/ActionBarImpl$TabImpl;->px:Ljava/lang/CharSequence;

    return-object v0
.end method

.method public select()V
    .locals 1

    .prologue
    .line 1267
    iget-object v0, p0, Lcom/miui/internal/app/ActionBarImpl$TabImpl;->qO:Lcom/miui/internal/app/ActionBarImpl;

    invoke-virtual {v0, p0}, Lcom/miui/internal/app/ActionBarImpl;->selectTab(Landroid/app/ActionBar$Tab;)V

    .line 1268
    return-void
.end method

.method public setContentDescription(I)Landroid/app/ActionBar$Tab;
    .locals 1

    .prologue
    .line 1272
    iget-object v0, p0, Lcom/miui/internal/app/ActionBarImpl$TabImpl;->qO:Lcom/miui/internal/app/ActionBarImpl;

    invoke-static {v0}, Lcom/miui/internal/app/ActionBarImpl;->c(Lcom/miui/internal/app/ActionBarImpl;)Landroid/content/Context;

    move-result-object v0

    invoke-virtual {v0}, Landroid/content/Context;->getResources()Landroid/content/res/Resources;

    move-result-object v0

    invoke-virtual {v0, p1}, Landroid/content/res/Resources;->getText(I)Ljava/lang/CharSequence;

    move-result-object v0

    invoke-virtual {p0, v0}, Lcom/miui/internal/app/ActionBarImpl$TabImpl;->setContentDescription(Ljava/lang/CharSequence;)Landroid/app/ActionBar$Tab;

    move-result-object v0

    return-object v0
.end method

.method public setContentDescription(Ljava/lang/CharSequence;)Landroid/app/ActionBar$Tab;
    .locals 2

    .prologue
    .line 1278
    iput-object p1, p0, Lcom/miui/internal/app/ActionBarImpl$TabImpl;->RP:Ljava/lang/CharSequence;

    .line 1279
    iget v0, p0, Lcom/miui/internal/app/ActionBarImpl$TabImpl;->Qv:I

    if-ltz v0, :cond_0

    .line 1280
    iget-object v0, p0, Lcom/miui/internal/app/ActionBarImpl$TabImpl;->qO:Lcom/miui/internal/app/ActionBarImpl;

    invoke-static {v0}, Lcom/miui/internal/app/ActionBarImpl;->b(Lcom/miui/internal/app/ActionBarImpl;)Lcom/miui/internal/widget/ScrollingTabContainerView;

    move-result-object v0

    iget v1, p0, Lcom/miui/internal/app/ActionBarImpl$TabImpl;->Qv:I

    invoke-virtual {v0, v1}, Lcom/miui/internal/widget/ScrollingTabContainerView;->updateTab(I)V

    .line 1282
    :cond_0
    return-object p0
.end method

.method public setCustomView(I)Landroid/app/ActionBar$Tab;
    .locals 2

    .prologue
    .line 1214
    iget-object v0, p0, Lcom/miui/internal/app/ActionBarImpl$TabImpl;->qO:Lcom/miui/internal/app/ActionBarImpl;

    invoke-virtual {v0}, Lcom/miui/internal/app/ActionBarImpl;->getThemedContext()Landroid/content/Context;

    move-result-object v0

    invoke-static {v0}, Landroid/view/LayoutInflater;->from(Landroid/content/Context;)Landroid/view/LayoutInflater;

    move-result-object v0

    const/4 v1, 0x0

    invoke-virtual {v0, p1, v1}, Landroid/view/LayoutInflater;->inflate(ILandroid/view/ViewGroup;)Landroid/view/View;

    move-result-object v0

    invoke-virtual {p0, v0}, Lcom/miui/internal/app/ActionBarImpl$TabImpl;->setCustomView(Landroid/view/View;)Landroid/app/ActionBar$Tab;

    move-result-object v0

    return-object v0
.end method

.method public setCustomView(Landroid/view/View;)Landroid/app/ActionBar$Tab;
    .locals 2

    .prologue
    .line 1205
    iput-object p1, p0, Lcom/miui/internal/app/ActionBarImpl$TabImpl;->o:Landroid/view/View;

    .line 1206
    iget v0, p0, Lcom/miui/internal/app/ActionBarImpl$TabImpl;->Qv:I

    if-ltz v0, :cond_0

    .line 1207
    iget-object v0, p0, Lcom/miui/internal/app/ActionBarImpl$TabImpl;->qO:Lcom/miui/internal/app/ActionBarImpl;

    invoke-static {v0}, Lcom/miui/internal/app/ActionBarImpl;->b(Lcom/miui/internal/app/ActionBarImpl;)Lcom/miui/internal/widget/ScrollingTabContainerView;

    move-result-object v0

    iget v1, p0, Lcom/miui/internal/app/ActionBarImpl$TabImpl;->Qv:I

    invoke-virtual {v0, v1}, Lcom/miui/internal/widget/ScrollingTabContainerView;->updateTab(I)V

    .line 1209
    :cond_0
    return-object p0
.end method

.method public setIcon(I)Landroid/app/ActionBar$Tab;
    .locals 1

    .prologue
    .line 1248
    iget-object v0, p0, Lcom/miui/internal/app/ActionBarImpl$TabImpl;->qO:Lcom/miui/internal/app/ActionBarImpl;

    invoke-static {v0}, Lcom/miui/internal/app/ActionBarImpl;->c(Lcom/miui/internal/app/ActionBarImpl;)Landroid/content/Context;

    move-result-object v0

    invoke-virtual {v0}, Landroid/content/Context;->getResources()Landroid/content/res/Resources;

    move-result-object v0

    invoke-virtual {v0, p1}, Landroid/content/res/Resources;->getDrawable(I)Landroid/graphics/drawable/Drawable;

    move-result-object v0

    invoke-virtual {p0, v0}, Lcom/miui/internal/app/ActionBarImpl$TabImpl;->setIcon(Landroid/graphics/drawable/Drawable;)Landroid/app/ActionBar$Tab;

    move-result-object v0

    return-object v0
.end method

.method public setIcon(Landroid/graphics/drawable/Drawable;)Landroid/app/ActionBar$Tab;
    .locals 2

    .prologue
    .line 1239
    iput-object p1, p0, Lcom/miui/internal/app/ActionBarImpl$TabImpl;->hB:Landroid/graphics/drawable/Drawable;

    .line 1240
    iget v0, p0, Lcom/miui/internal/app/ActionBarImpl$TabImpl;->Qv:I

    if-ltz v0, :cond_0

    .line 1241
    iget-object v0, p0, Lcom/miui/internal/app/ActionBarImpl$TabImpl;->qO:Lcom/miui/internal/app/ActionBarImpl;

    invoke-static {v0}, Lcom/miui/internal/app/ActionBarImpl;->b(Lcom/miui/internal/app/ActionBarImpl;)Lcom/miui/internal/widget/ScrollingTabContainerView;

    move-result-object v0

    iget v1, p0, Lcom/miui/internal/app/ActionBarImpl$TabImpl;->Qv:I

    invoke-virtual {v0, v1}, Lcom/miui/internal/widget/ScrollingTabContainerView;->updateTab(I)V

    .line 1243
    :cond_0
    return-object p0
.end method

.method public setInternalTabListener(Landroid/app/ActionBar$TabListener;)Landroid/app/ActionBar$Tab;
    .locals 0

    .prologue
    .line 1194
    iput-object p1, p0, Lcom/miui/internal/app/ActionBarImpl$TabImpl;->RM:Landroid/app/ActionBar$TabListener;

    .line 1195
    return-object p0
.end method

.method public setPosition(I)V
    .locals 0

    .prologue
    .line 1229
    iput p1, p0, Lcom/miui/internal/app/ActionBarImpl$TabImpl;->Qv:I

    .line 1230
    return-void
.end method

.method public setTabListener(Landroid/app/ActionBar$TabListener;)Landroid/app/ActionBar$Tab;
    .locals 0

    .prologue
    .line 1189
    iput-object p1, p0, Lcom/miui/internal/app/ActionBarImpl$TabImpl;->RL:Landroid/app/ActionBar$TabListener;

    .line 1190
    return-object p0
.end method

.method public setTag(Ljava/lang/Object;)Landroid/app/ActionBar$Tab;
    .locals 0

    .prologue
    .line 1179
    iput-object p1, p0, Lcom/miui/internal/app/ActionBarImpl$TabImpl;->RN:Ljava/lang/Object;

    .line 1180
    return-object p0
.end method

.method public setText(I)Landroid/app/ActionBar$Tab;
    .locals 1

    .prologue
    .line 1262
    iget-object v0, p0, Lcom/miui/internal/app/ActionBarImpl$TabImpl;->qO:Lcom/miui/internal/app/ActionBarImpl;

    invoke-static {v0}, Lcom/miui/internal/app/ActionBarImpl;->c(Lcom/miui/internal/app/ActionBarImpl;)Landroid/content/Context;

    move-result-object v0

    invoke-virtual {v0}, Landroid/content/Context;->getResources()Landroid/content/res/Resources;

    move-result-object v0

    invoke-virtual {v0, p1}, Landroid/content/res/Resources;->getText(I)Ljava/lang/CharSequence;

    move-result-object v0

    invoke-virtual {p0, v0}, Lcom/miui/internal/app/ActionBarImpl$TabImpl;->setText(Ljava/lang/CharSequence;)Landroid/app/ActionBar$Tab;

    move-result-object v0

    return-object v0
.end method

.method public setText(Ljava/lang/CharSequence;)Landroid/app/ActionBar$Tab;
    .locals 2

    .prologue
    .line 1253
    iput-object p1, p0, Lcom/miui/internal/app/ActionBarImpl$TabImpl;->px:Ljava/lang/CharSequence;

    .line 1254
    iget v0, p0, Lcom/miui/internal/app/ActionBarImpl$TabImpl;->Qv:I

    if-ltz v0, :cond_0

    .line 1255
    iget-object v0, p0, Lcom/miui/internal/app/ActionBarImpl$TabImpl;->qO:Lcom/miui/internal/app/ActionBarImpl;

    invoke-static {v0}, Lcom/miui/internal/app/ActionBarImpl;->b(Lcom/miui/internal/app/ActionBarImpl;)Lcom/miui/internal/widget/ScrollingTabContainerView;

    move-result-object v0

    iget v1, p0, Lcom/miui/internal/app/ActionBarImpl$TabImpl;->Qv:I

    invoke-virtual {v0, v1}, Lcom/miui/internal/widget/ScrollingTabContainerView;->updateTab(I)V

    .line 1257
    :cond_0
    return-object p0
.end method
