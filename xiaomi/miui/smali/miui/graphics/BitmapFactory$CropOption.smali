.class public Lmiui/graphics/BitmapFactory$CropOption;
.super Ljava/lang/Object;
.source "SourceFile"


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lmiui/graphics/BitmapFactory;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x9
    name = "CropOption"
.end annotation


# instance fields
.field public borderColor:I

.field public borderWidth:I

.field public rx:I

.field public ry:I

.field public srcBmpDrawingArea:Landroid/graphics/Rect;


# direct methods
.method public constructor <init>()V
    .locals 0

    .prologue
    .line 679
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    .line 680
    return-void
.end method

.method public constructor <init>(IIII)V
    .locals 0

    .prologue
    .line 682
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    .line 683
    iput p1, p0, Lmiui/graphics/BitmapFactory$CropOption;->rx:I

    .line 684
    iput p2, p0, Lmiui/graphics/BitmapFactory$CropOption;->ry:I

    .line 685
    iput p3, p0, Lmiui/graphics/BitmapFactory$CropOption;->borderWidth:I

    .line 686
    iput p4, p0, Lmiui/graphics/BitmapFactory$CropOption;->borderColor:I

    .line 687
    return-void
.end method

.method public constructor <init>(Lmiui/graphics/BitmapFactory$CropOption;)V
    .locals 1

    .prologue
    .line 689
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    .line 690
    iget v0, p1, Lmiui/graphics/BitmapFactory$CropOption;->rx:I

    iput v0, p0, Lmiui/graphics/BitmapFactory$CropOption;->rx:I

    .line 691
    iget v0, p1, Lmiui/graphics/BitmapFactory$CropOption;->ry:I

    iput v0, p0, Lmiui/graphics/BitmapFactory$CropOption;->ry:I

    .line 692
    iget v0, p1, Lmiui/graphics/BitmapFactory$CropOption;->borderWidth:I

    iput v0, p0, Lmiui/graphics/BitmapFactory$CropOption;->borderWidth:I

    .line 693
    iget v0, p1, Lmiui/graphics/BitmapFactory$CropOption;->borderColor:I

    iput v0, p0, Lmiui/graphics/BitmapFactory$CropOption;->borderColor:I

    .line 694
    iget-object v0, p1, Lmiui/graphics/BitmapFactory$CropOption;->srcBmpDrawingArea:Landroid/graphics/Rect;

    iput-object v0, p0, Lmiui/graphics/BitmapFactory$CropOption;->srcBmpDrawingArea:Landroid/graphics/Rect;

    .line 695
    return-void
.end method
