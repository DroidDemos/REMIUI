.class Lcom/miui/internal/variable/AlertControllerWrapper$AlertParams$3;
.super Landroid/widget/CursorAdapter;
.source "SourceFile"


# annotations
.annotation system Ldalvik/annotation/EnclosingMethod;
    value = Lcom/miui/internal/variable/AlertControllerWrapper$AlertParams;->a(Landroid/widget/ListView;I)Landroid/widget/ListAdapter;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x0
    name = null
.end annotation


# instance fields
.field private final dA:I

.field final synthetic dB:I

.field final synthetic dw:Landroid/widget/ListView;

.field final synthetic dy:Lcom/miui/internal/variable/AlertControllerWrapper$AlertParams;

.field private final dz:I


# direct methods
.method constructor <init>(Lcom/miui/internal/variable/AlertControllerWrapper$AlertParams;Landroid/content/Context;Landroid/database/Cursor;ZLandroid/widget/ListView;I)V
    .locals 2

    .prologue
    .line 224
    iput-object p1, p0, Lcom/miui/internal/variable/AlertControllerWrapper$AlertParams$3;->dy:Lcom/miui/internal/variable/AlertControllerWrapper$AlertParams;

    iput-object p5, p0, Lcom/miui/internal/variable/AlertControllerWrapper$AlertParams$3;->dw:Landroid/widget/ListView;

    iput p6, p0, Lcom/miui/internal/variable/AlertControllerWrapper$AlertParams$3;->dB:I

    invoke-direct {p0, p2, p3, p4}, Landroid/widget/CursorAdapter;-><init>(Landroid/content/Context;Landroid/database/Cursor;Z)V

    .line 231
    invoke-virtual {p0}, Lcom/miui/internal/variable/AlertControllerWrapper$AlertParams$3;->getCursor()Landroid/database/Cursor;

    move-result-object v0

    .line 232
    iget-object v1, p0, Lcom/miui/internal/variable/AlertControllerWrapper$AlertParams$3;->dy:Lcom/miui/internal/variable/AlertControllerWrapper$AlertParams;

    iget-object v1, v1, Lcom/miui/internal/variable/AlertControllerWrapper$AlertParams;->mLabelColumn:Ljava/lang/String;

    invoke-interface {v0, v1}, Landroid/database/Cursor;->getColumnIndexOrThrow(Ljava/lang/String;)I

    move-result v1

    iput v1, p0, Lcom/miui/internal/variable/AlertControllerWrapper$AlertParams$3;->dz:I

    .line 233
    iget-object v1, p0, Lcom/miui/internal/variable/AlertControllerWrapper$AlertParams$3;->dy:Lcom/miui/internal/variable/AlertControllerWrapper$AlertParams;

    iget-object v1, v1, Lcom/miui/internal/variable/AlertControllerWrapper$AlertParams;->mIsCheckedColumn:Ljava/lang/String;

    invoke-interface {v0, v1}, Landroid/database/Cursor;->getColumnIndexOrThrow(Ljava/lang/String;)I

    move-result v0

    iput v0, p0, Lcom/miui/internal/variable/AlertControllerWrapper$AlertParams$3;->dA:I

    .line 234
    return-void
.end method


# virtual methods
.method public bindView(Landroid/view/View;Landroid/content/Context;Landroid/database/Cursor;)V
    .locals 4

    .prologue
    const/4 v1, 0x1

    .line 238
    const v0, 0x1020014

    invoke-virtual {p1, v0}, Landroid/view/View;->findViewById(I)Landroid/view/View;

    move-result-object v0

    check-cast v0, Landroid/widget/CheckedTextView;

    .line 240
    iget v2, p0, Lcom/miui/internal/variable/AlertControllerWrapper$AlertParams$3;->dz:I

    invoke-interface {p3, v2}, Landroid/database/Cursor;->getString(I)Ljava/lang/String;

    move-result-object v2

    invoke-virtual {v0, v2}, Landroid/widget/CheckedTextView;->setText(Ljava/lang/CharSequence;)V

    .line 241
    iget-object v0, p0, Lcom/miui/internal/variable/AlertControllerWrapper$AlertParams$3;->dy:Lcom/miui/internal/variable/AlertControllerWrapper$AlertParams;

    iget-boolean v0, v0, Lcom/miui/internal/variable/AlertControllerWrapper$AlertParams;->mEditMode:Z

    if-nez v0, :cond_0

    .line 242
    iget-object v2, p0, Lcom/miui/internal/variable/AlertControllerWrapper$AlertParams$3;->dw:Landroid/widget/ListView;

    invoke-interface {p3}, Landroid/database/Cursor;->getPosition()I

    move-result v3

    iget v0, p0, Lcom/miui/internal/variable/AlertControllerWrapper$AlertParams$3;->dA:I

    invoke-interface {p3, v0}, Landroid/database/Cursor;->getInt(I)I

    move-result v0

    if-ne v0, v1, :cond_1

    move v0, v1

    :goto_0
    invoke-virtual {v2, v3, v0}, Landroid/widget/ListView;->setItemChecked(IZ)V

    .line 244
    :cond_0
    return-void

    .line 242
    :cond_1
    const/4 v0, 0x0

    goto :goto_0
.end method

.method public newView(Landroid/content/Context;Landroid/database/Cursor;Landroid/view/ViewGroup;)Landroid/view/View;
    .locals 3

    .prologue
    .line 248
    iget-object v0, p0, Lcom/miui/internal/variable/AlertControllerWrapper$AlertParams$3;->dy:Lcom/miui/internal/variable/AlertControllerWrapper$AlertParams;

    iget-object v0, v0, Lcom/miui/internal/variable/AlertControllerWrapper$AlertParams;->mInflater:Landroid/view/LayoutInflater;

    iget v1, p0, Lcom/miui/internal/variable/AlertControllerWrapper$AlertParams$3;->dB:I

    const/4 v2, 0x0

    invoke-virtual {v0, v1, v2}, Landroid/view/LayoutInflater;->inflate(ILandroid/view/ViewGroup;)Landroid/view/View;

    move-result-object v0

    return-object v0
.end method
