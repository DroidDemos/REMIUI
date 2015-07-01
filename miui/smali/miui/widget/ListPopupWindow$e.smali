.class Lmiui/widget/ListPopupWindow$e;
.super Landroid/widget/ListView;
.source "SourceFile"


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lmiui/widget/ListPopupWindow;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0xa
    name = "e"
.end annotation


# static fields
.field static final Bg:I = -0x1

.field public static final INVALID_POSITION:I = -0x1


# instance fields
.field private Bh:Z

.field private Bi:Z


# direct methods
.method public constructor <init>(Landroid/content/Context;Z)V
    .locals 2

    .prologue
    .line 1153
    const/4 v0, 0x0

    const v1, 0x101006d

    invoke-direct {p0, p1, v0, v1}, Landroid/widget/ListView;-><init>(Landroid/content/Context;Landroid/util/AttributeSet;I)V

    .line 1154
    iput-boolean p2, p0, Lmiui/widget/ListPopupWindow$e;->Bi:Z

    .line 1155
    const/4 v0, 0x0

    invoke-virtual {p0, v0}, Lmiui/widget/ListPopupWindow$e;->setCacheColorHint(I)V

    .line 1156
    return-void
.end method

.method static synthetic a(Lmiui/widget/ListPopupWindow$e;IZ)I
    .locals 1

    .prologue
    .line 1106
    invoke-direct {p0, p1, p2}, Lmiui/widget/ListPopupWindow$e;->e(IZ)I

    move-result v0

    return v0
.end method

.method static synthetic a(Lmiui/widget/ListPopupWindow$e;Z)Z
    .locals 0

    .prologue
    .line 1106
    iput-boolean p1, p0, Lmiui/widget/ListPopupWindow$e;->Bh:Z

    return p1
.end method

.method private e(IZ)I
    .locals 4

    .prologue
    const/4 v0, -0x1

    .line 1168
    invoke-virtual {p0}, Lmiui/widget/ListPopupWindow$e;->getAdapter()Landroid/widget/ListAdapter;

    move-result-object v1

    .line 1169
    if-eqz v1, :cond_0

    invoke-virtual {p0}, Lmiui/widget/ListPopupWindow$e;->isInTouchMode()Z

    move-result v2

    if-eqz v2, :cond_2

    :cond_0
    move p1, v0

    .line 1195
    :cond_1
    :goto_0
    return p1

    .line 1173
    :cond_2
    invoke-interface {v1}, Landroid/widget/ListAdapter;->getCount()I

    move-result v2

    .line 1174
    invoke-virtual {p0}, Lmiui/widget/ListPopupWindow$e;->getAdapter()Landroid/widget/ListAdapter;

    move-result-object v3

    invoke-interface {v3}, Landroid/widget/ListAdapter;->areAllItemsEnabled()Z

    move-result v3

    if-nez v3, :cond_6

    .line 1175
    if-eqz p2, :cond_3

    .line 1176
    const/4 v3, 0x0

    invoke-static {v3, p1}, Ljava/lang/Math;->max(II)I

    move-result p1

    .line 1177
    :goto_1
    if-ge p1, v2, :cond_4

    invoke-interface {v1, p1}, Landroid/widget/ListAdapter;->isEnabled(I)Z

    move-result v3

    if-nez v3, :cond_4

    .line 1178
    add-int/lit8 p1, p1, 0x1

    goto :goto_1

    .line 1181
    :cond_3
    add-int/lit8 v3, v2, -0x1

    invoke-static {p1, v3}, Ljava/lang/Math;->min(II)I

    move-result p1

    .line 1182
    :goto_2
    if-ltz p1, :cond_4

    invoke-interface {v1, p1}, Landroid/widget/ListAdapter;->isEnabled(I)Z

    move-result v3

    if-nez v3, :cond_4

    .line 1183
    add-int/lit8 p1, p1, -0x1

    goto :goto_2

    .line 1187
    :cond_4
    if-ltz p1, :cond_5

    if-lt p1, v2, :cond_1

    :cond_5
    move p1, v0

    .line 1188
    goto :goto_0

    .line 1192
    :cond_6
    if-ltz p1, :cond_7

    if-lt p1, v2, :cond_1

    :cond_7
    move p1, v0

    .line 1193
    goto :goto_0
.end method


# virtual methods
.method final a(IIIII)I
    .locals 12

    .prologue
    .line 1266
    invoke-virtual {p0}, Lmiui/widget/ListPopupWindow$e;->getListPaddingTop()I

    move-result v2

    .line 1267
    invoke-virtual {p0}, Lmiui/widget/ListPopupWindow$e;->getListPaddingBottom()I

    move-result v3

    .line 1268
    invoke-virtual {p0}, Lmiui/widget/ListPopupWindow$e;->getDividerHeight()I

    move-result v1

    .line 1269
    invoke-virtual {p0}, Lmiui/widget/ListPopupWindow$e;->getDivider()Landroid/graphics/drawable/Drawable;

    move-result-object v4

    .line 1271
    invoke-virtual {p0}, Lmiui/widget/ListPopupWindow$e;->getAdapter()Landroid/widget/ListAdapter;

    move-result-object v8

    .line 1273
    if-nez v8, :cond_1

    .line 1274
    add-int p4, v2, v3

    .line 1333
    :cond_0
    :goto_0
    return p4

    .line 1278
    :cond_1
    add-int/2addr v3, v2

    .line 1279
    if-lez v1, :cond_2

    if-eqz v4, :cond_2

    .line 1284
    :goto_1
    const/4 v4, 0x0

    .line 1286
    const/4 v6, 0x0

    .line 1287
    const/4 v5, 0x0

    .line 1288
    invoke-interface {v8}, Landroid/widget/ListAdapter;->getCount()I

    move-result v9

    .line 1289
    const/4 v2, 0x0

    move v7, v2

    :goto_2
    if-ge v7, v9, :cond_6

    .line 1290
    invoke-interface {v8, v7}, Landroid/widget/ListAdapter;->getItemViewType(I)I

    move-result v2

    .line 1291
    if-eq v2, v5, :cond_7

    .line 1292
    const/4 v5, 0x0

    move v11, v2

    move-object v2, v5

    move v5, v11

    .line 1295
    :goto_3
    invoke-interface {v8, v7, v2, p0}, Landroid/widget/ListAdapter;->getView(ILandroid/view/View;Landroid/view/ViewGroup;)Landroid/view/View;

    move-result-object v6

    .line 1299
    invoke-virtual {v6}, Landroid/view/View;->getLayoutParams()Landroid/view/ViewGroup$LayoutParams;

    move-result-object v2

    iget v2, v2, Landroid/view/ViewGroup$LayoutParams;->height:I

    .line 1300
    if-lez v2, :cond_3

    .line 1301
    const/high16 v10, 0x40000000

    invoke-static {v2, v10}, Landroid/view/View$MeasureSpec;->makeMeasureSpec(II)I

    move-result v2

    .line 1306
    :goto_4
    invoke-virtual {v6, p1, v2}, Landroid/view/View;->measure(II)V

    .line 1308
    if-lez v7, :cond_8

    .line 1310
    add-int v2, v3, v1

    .line 1313
    :goto_5
    invoke-virtual {v6}, Landroid/view/View;->getMeasuredHeight()I

    move-result v3

    add-int/2addr v2, v3

    .line 1315
    move/from16 v0, p4

    if-lt v2, v0, :cond_4

    .line 1318
    if-ltz p5, :cond_0

    move/from16 v0, p5

    if-le v7, v0, :cond_0

    if-lez v4, :cond_0

    move/from16 v0, p4

    if-eq v2, v0, :cond_0

    move/from16 p4, v4

    goto :goto_0

    .line 1279
    :cond_2
    const/4 v1, 0x0

    goto :goto_1

    .line 1304
    :cond_3
    const/4 v2, 0x0

    const/4 v10, 0x0

    invoke-static {v2, v10}, Landroid/view/View$MeasureSpec;->makeMeasureSpec(II)I

    move-result v2

    goto :goto_4

    .line 1326
    :cond_4
    if-ltz p5, :cond_5

    move/from16 v0, p5

    if-lt v7, v0, :cond_5

    move v4, v2

    .line 1289
    :cond_5
    add-int/lit8 v3, v7, 0x1

    move v7, v3

    move v3, v2

    goto :goto_2

    :cond_6
    move/from16 p4, v3

    .line 1333
    goto :goto_0

    :cond_7
    move-object v2, v6

    goto :goto_3

    :cond_8
    move v2, v3

    goto :goto_5
.end method

.method public hasFocus()Z
    .locals 1

    .prologue
    .line 1232
    iget-boolean v0, p0, Lmiui/widget/ListPopupWindow$e;->Bi:Z

    if-nez v0, :cond_0

    invoke-super {p0}, Landroid/widget/ListView;->hasFocus()Z

    move-result v0

    if-eqz v0, :cond_1

    :cond_0
    const/4 v0, 0x1

    :goto_0
    return v0

    :cond_1
    const/4 v0, 0x0

    goto :goto_0
.end method

.method public hasWindowFocus()Z
    .locals 1

    .prologue
    .line 1212
    iget-boolean v0, p0, Lmiui/widget/ListPopupWindow$e;->Bi:Z

    if-nez v0, :cond_0

    invoke-super {p0}, Landroid/widget/ListView;->hasWindowFocus()Z

    move-result v0

    if-eqz v0, :cond_1

    :cond_0
    const/4 v0, 0x1

    :goto_0
    return v0

    :cond_1
    const/4 v0, 0x0

    goto :goto_0
.end method

.method public isFocused()Z
    .locals 1

    .prologue
    .line 1222
    iget-boolean v0, p0, Lmiui/widget/ListPopupWindow$e;->Bi:Z

    if-nez v0, :cond_0

    invoke-super {p0}, Landroid/widget/ListView;->isFocused()Z

    move-result v0

    if-eqz v0, :cond_1

    :cond_0
    const/4 v0, 0x1

    :goto_0
    return v0

    :cond_1
    const/4 v0, 0x0

    goto :goto_0
.end method

.method public isInTouchMode()Z
    .locals 1

    .prologue
    .line 1202
    iget-boolean v0, p0, Lmiui/widget/ListPopupWindow$e;->Bi:Z

    if-eqz v0, :cond_0

    iget-boolean v0, p0, Lmiui/widget/ListPopupWindow$e;->Bh:Z

    if-nez v0, :cond_1

    :cond_0
    invoke-super {p0}, Landroid/widget/ListView;->isInTouchMode()Z

    move-result v0

    if-eqz v0, :cond_2

    :cond_1
    const/4 v0, 0x1

    :goto_0
    return v0

    :cond_2
    const/4 v0, 0x0

    goto :goto_0
.end method
