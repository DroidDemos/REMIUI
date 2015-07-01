.class public Lmiui/graphics/BitmapFactory;
.super Landroid/graphics/BitmapFactory;
.source "SourceFile"


# annotations
.annotation system Ldalvik/annotation/MemberClasses;
    value = {
        Lmiui/graphics/BitmapFactory$CropOption;
    }
.end annotation


# static fields
.field public static final BITMAP_COLOR_MODE_DARK:I = 0x0

.field public static final BITMAP_COLOR_MODE_LIGHT:I = 0x2

.field public static final BITMAP_COLOR_MODE_MEDIUM:I = 0x1

.field private static tf:[B

.field private static final tg:Ljava/lang/ThreadLocal;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Ljava/lang/ThreadLocal",
            "<",
            "Landroid/graphics/Canvas;",
            ">;"
        }
    .end annotation
.end field

.field private static final th:Landroid/graphics/Paint;

.field private static final ti:Ljava/util/regex/Pattern;

.field private static final tj:[Ljava/lang/String;


# direct methods
.method static constructor <clinit>()V
    .locals 5

    .prologue
    const/16 v4, 0x8

    const/4 v3, 0x1

    .line 480
    new-array v0, v4, [B

    fill-array-data v0, :array_0

    sput-object v0, Lmiui/graphics/BitmapFactory;->tf:[B

    .line 649
    const-string v0, "miuiimageutilities"

    invoke-static {v0}, Ljava/lang/System;->loadLibrary(Ljava/lang/String;)V

    .line 698
    new-instance v0, Ljava/lang/ThreadLocal;

    invoke-direct {v0}, Ljava/lang/ThreadLocal;-><init>()V

    sput-object v0, Lmiui/graphics/BitmapFactory;->tg:Ljava/lang/ThreadLocal;

    .line 699
    new-instance v0, Landroid/graphics/Paint;

    invoke-direct {v0, v3}, Landroid/graphics/Paint;-><init>(I)V

    sput-object v0, Lmiui/graphics/BitmapFactory;->th:Landroid/graphics/Paint;

    .line 701
    sget-object v0, Lmiui/graphics/BitmapFactory;->th:Landroid/graphics/Paint;

    invoke-virtual {v0, v3}, Landroid/graphics/Paint;->setFilterBitmap(Z)V

    .line 702
    sget-object v0, Lmiui/graphics/BitmapFactory;->th:Landroid/graphics/Paint;

    new-instance v1, Landroid/graphics/PorterDuffXfermode;

    sget-object v2, Landroid/graphics/PorterDuff$Mode;->SRC_IN:Landroid/graphics/PorterDuff$Mode;

    invoke-direct {v1, v2}, Landroid/graphics/PorterDuffXfermode;-><init>(Landroid/graphics/PorterDuff$Mode;)V

    invoke-virtual {v0, v1}, Landroid/graphics/Paint;->setXfermode(Landroid/graphics/Xfermode;)Landroid/graphics/Xfermode;

    .line 959
    const-string v0, "[\u3100-\u312d\u31a0-\u31ba\u4e00-\u9fcc\u3400-\u4db5\uf900-\ufad9\u2f00-\u2fd5\u2e80-\u2ef3\u31c0-\u31e3\u1100-\u11ff\ua960-\ua97c\ud7b0-\ud7fb\u3131-\u318e\uac00-\ud7a3\u3040-\u309f\u30a0-\u30ff\u31f0-\u31ff\u3190-\u319f\ua000-\ua48c\ua490-\ua4c6]"

    invoke-static {v0}, Ljava/util/regex/Pattern;->compile(Ljava/lang/String;)Ljava/util/regex/Pattern;

    move-result-object v0

    sput-object v0, Lmiui/graphics/BitmapFactory;->ti:Ljava/util/regex/Pattern;

    .line 981
    const/16 v0, 0x28

    new-array v0, v0, [Ljava/lang/String;

    const/4 v1, 0x0

    const-string v2, "\u8001\u5e08"

    aput-object v2, v0, v1

    const-string v1, "\u5148\u751f"

    aput-object v1, v0, v3

    const/4 v1, 0x2

    const-string v2, "\u8001\u677f"

    aput-object v2, v0, v1

    const/4 v1, 0x3

    const-string v2, "\u4ed4"

    aput-object v2, v0, v1

    const/4 v1, 0x4

    const-string v2, "\u624b\u673a"

    aput-object v2, v0, v1

    const/4 v1, 0x5

    const-string v2, "\u53d4"

    aput-object v2, v0, v1

    const/4 v1, 0x6

    const-string v2, "\u963f\u59e8"

    aput-object v2, v0, v1

    const/4 v1, 0x7

    const-string v2, "\u5b85"

    aput-object v2, v0, v1

    const-string v1, "\u4f2f"

    aput-object v1, v0, v4

    const/16 v1, 0x9

    const-string v2, "\u4f2f\u6bcd"

    aput-object v2, v0, v1

    const/16 v1, 0xa

    const-string v2, "\u4f2f\u7236"

    aput-object v2, v0, v1

    const/16 v1, 0xb

    const-string v2, "\u54e5"

    aput-object v2, v0, v1

    const/16 v1, 0xc

    const-string v2, "\u59d0"

    aput-object v2, v0, v1

    const/16 v1, 0xd

    const-string v2, "\u5f1f"

    aput-object v2, v0, v1

    const/16 v1, 0xe

    const-string v2, "\u59b9"

    aput-object v2, v0, v1

    const/16 v1, 0xf

    const-string v2, "\u8205"

    aput-object v2, v0, v1

    const/16 v1, 0x10

    const-string v2, "\u59d1"

    aput-object v2, v0, v1

    const/16 v1, 0x11

    const-string v2, "\u7236"

    aput-object v2, v0, v1

    const/16 v1, 0x12

    const-string v2, "\u4e3b\u4efb"

    aput-object v2, v0, v1

    const/16 v1, 0x13

    const-string v2, "\u7ecf\u7406"

    aput-object v2, v0, v1

    const/16 v1, 0x14

    const-string v2, "\u5de5\u4f5c"

    aput-object v2, v0, v1

    const/16 v1, 0x15

    const-string v2, "\u540c\u4e8b"

    aput-object v2, v0, v1

    const/16 v1, 0x16

    const-string v2, "\u5f8b\u5e08"

    aput-object v2, v0, v1

    const/16 v1, 0x17

    const-string v2, "\u53f8\u673a"

    aput-object v2, v0, v1

    const/16 v1, 0x18

    const-string v2, "\u5e08\u5085"

    aput-object v2, v0, v1

    const/16 v1, 0x19

    const-string v2, "\u5e08\u7236"

    aput-object v2, v0, v1

    const/16 v1, 0x1a

    const-string v2, "\u7237"

    aput-object v2, v0, v1

    const/16 v1, 0x1b

    const-string v2, "\u5976"

    aput-object v2, v0, v1

    const/16 v1, 0x1c

    const-string v2, "\u4e2d\u4ecb"

    aput-object v2, v0, v1

    const/16 v1, 0x1d

    const-string v2, "\u8463"

    aput-object v2, v0, v1

    const/16 v1, 0x1e

    const-string v2, "\u603b"

    aput-object v2, v0, v1

    const/16 v1, 0x1f

    const-string v2, "\u592a\u592a"

    aput-object v2, v0, v1

    const/16 v1, 0x20

    const-string v2, "\u4fdd\u59c6"

    aput-object v2, v0, v1

    const/16 v1, 0x21

    const-string v2, "\u67d0"

    aput-object v2, v0, v1

    const/16 v1, 0x22

    const-string v2, "\u79d8\u4e66"

    aput-object v2, v0, v1

    const/16 v1, 0x23

    const-string v2, "\u5904\u957f"

    aput-object v2, v0, v1

    const/16 v1, 0x24

    const-string v2, "\u5c40\u957f"

    aput-object v2, v0, v1

    const/16 v1, 0x25

    const-string v2, "\u73ed\u957f"

    aput-object v2, v0, v1

    const/16 v1, 0x26

    const-string v2, "\u5144"

    aput-object v2, v0, v1

    const/16 v1, 0x27

    const-string v2, "\u52a9\u7406"

    aput-object v2, v0, v1

    sput-object v0, Lmiui/graphics/BitmapFactory;->tj:[Ljava/lang/String;

    return-void

    .line 480
    :array_0
    .array-data 1
        -0x77t
        0x50t
        0x4et
        0x47t
        0xdt
        0xat
        0x1at
        0xat
    .end array-data
.end method

.method protected constructor <init>()V
    .locals 2
    .annotation system Ldalvik/annotation/Throws;
        value = {
            Ljava/lang/InstantiationException;
        }
    .end annotation

    .prologue
    .line 46
    invoke-direct {p0}, Landroid/graphics/BitmapFactory;-><init>()V

    .line 47
    new-instance v0, Ljava/lang/InstantiationException;

    const-string v1, "Cannot instantiate utility class"

    invoke-direct {v0, v1}, Ljava/lang/InstantiationException;-><init>(Ljava/lang/String;)V

    throw v0
.end method

.method private static a(Lmiui/io/ResettableInputStream;I)I
    .locals 5

    .prologue
    .line 51
    const/4 v0, 0x1

    .line 52
    if-lez p1, :cond_0

    .line 53
    invoke-static {p0}, Lmiui/graphics/BitmapFactory;->getBitmapSize(Lmiui/io/ResettableInputStream;)Landroid/graphics/BitmapFactory$Options;

    move-result-object v0

    .line 54
    iget v1, v0, Landroid/graphics/BitmapFactory$Options;->outWidth:I

    int-to-double v1, v1

    iget v0, v0, Landroid/graphics/BitmapFactory$Options;->outHeight:I

    int-to-double v3, v0

    mul-double v0, v1, v3

    int-to-double v2, p1

    div-double/2addr v0, v2

    invoke-static {v0, v1}, Ljava/lang/Math;->sqrt(D)D

    move-result-wide v0

    double-to-int v0, v0

    .line 56
    :cond_0
    return v0
.end method

.method private static a(Landroid/graphics/Bitmap;)Landroid/graphics/Bitmap;
    .locals 3

    .prologue
    .line 545
    sget-object v0, Landroid/graphics/Bitmap$Config;->ARGB_8888:Landroid/graphics/Bitmap$Config;

    .line 546
    invoke-virtual {p0}, Landroid/graphics/Bitmap;->getConfig()Landroid/graphics/Bitmap$Config;

    move-result-object v1

    if-eqz v1, :cond_0

    .line 547
    invoke-virtual {p0}, Landroid/graphics/Bitmap;->getConfig()Landroid/graphics/Bitmap$Config;

    move-result-object v0

    .line 549
    :cond_0
    invoke-virtual {p0}, Landroid/graphics/Bitmap;->getWidth()I

    move-result v1

    invoke-virtual {p0}, Landroid/graphics/Bitmap;->getHeight()I

    move-result v2

    invoke-static {v1, v2, v0}, Landroid/graphics/Bitmap;->createBitmap(IILandroid/graphics/Bitmap$Config;)Landroid/graphics/Bitmap;

    move-result-object v0

    return-object v0
.end method

.method private static a(Landroid/graphics/Bitmap;Landroid/graphics/Bitmap;I)Landroid/graphics/Bitmap;
    .locals 8

    .prologue
    const/4 v4, 0x1

    .line 592
    move v3, v4

    .line 593
    :goto_0
    const/16 v0, 0x19

    if-le p2, v0, :cond_0

    .line 596
    mul-int/lit8 v0, v3, 0x2

    .line 597
    div-int/lit8 p2, p2, 0x2

    move v3, v0

    goto :goto_0

    .line 600
    :cond_0
    invoke-virtual {p0}, Landroid/graphics/Bitmap;->getWidth()I

    move-result v0

    .line 601
    invoke-virtual {p0}, Landroid/graphics/Bitmap;->getHeight()I

    move-result v1

    .line 602
    if-ne v3, v4, :cond_4

    move-object v0, p0

    .line 608
    :goto_1
    invoke-static {}, Lcom/miui/internal/util/PackageConstants;->getCurrentApplication()Landroid/app/Application;

    move-result-object v2

    .line 609
    invoke-virtual {v2}, Landroid/content/Context;->getApplicationContext()Landroid/content/Context;

    move-result-object v1

    if-nez v1, :cond_6

    .line 610
    new-instance v1, Lmiui/graphics/BitmapFactory$1;

    invoke-direct {v1, v2}, Lmiui/graphics/BitmapFactory$1;-><init>(Landroid/content/Context;)V

    .line 617
    :goto_2
    invoke-static {v1}, Landroid/renderscript/RenderScript;->create(Landroid/content/Context;)Landroid/renderscript/RenderScript;

    move-result-object v2

    .line 618
    invoke-static {v2, v0}, Landroid/renderscript/Allocation;->createFromBitmap(Landroid/renderscript/RenderScript;Landroid/graphics/Bitmap;)Landroid/renderscript/Allocation;

    move-result-object v1

    .line 619
    invoke-virtual {v1}, Landroid/renderscript/Allocation;->getType()Landroid/renderscript/Type;

    move-result-object v5

    invoke-static {v2, v5}, Landroid/renderscript/Allocation;->createTyped(Landroid/renderscript/RenderScript;Landroid/renderscript/Type;)Landroid/renderscript/Allocation;

    move-result-object v5

    .line 620
    invoke-static {v2}, Landroid/renderscript/Element;->U8_4(Landroid/renderscript/RenderScript;)Landroid/renderscript/Element;

    move-result-object v6

    invoke-static {v2, v6}, Landroid/renderscript/ScriptIntrinsicBlur;->create(Landroid/renderscript/RenderScript;Landroid/renderscript/Element;)Landroid/renderscript/ScriptIntrinsicBlur;

    move-result-object v6

    .line 621
    int-to-float v7, p2

    invoke-virtual {v6, v7}, Landroid/renderscript/ScriptIntrinsicBlur;->setRadius(F)V

    .line 622
    invoke-virtual {v6, v1}, Landroid/renderscript/ScriptIntrinsicBlur;->setInput(Landroid/renderscript/Allocation;)V

    .line 623
    invoke-virtual {v6, v5}, Landroid/renderscript/ScriptIntrinsicBlur;->forEach(Landroid/renderscript/Allocation;)V

    .line 626
    if-ne v3, v4, :cond_5

    move-object v1, p1

    .line 631
    :goto_3
    invoke-virtual {v5, v1}, Landroid/renderscript/Allocation;->copyTo(Landroid/graphics/Bitmap;)V

    .line 633
    if-eq v1, p1, :cond_1

    .line 634
    invoke-static {v1, p1}, Lmiui/graphics/BitmapFactory;->scaleBitmap(Landroid/graphics/Bitmap;Landroid/graphics/Bitmap;)Landroid/graphics/Bitmap;

    .line 636
    :cond_1
    if-eq v0, p0, :cond_2

    .line 637
    invoke-virtual {v0}, Landroid/graphics/Bitmap;->recycle()V

    .line 639
    :cond_2
    if-eq v1, p1, :cond_3

    .line 640
    invoke-virtual {v1}, Landroid/graphics/Bitmap;->recycle()V

    .line 643
    :cond_3
    invoke-virtual {v2}, Landroid/renderscript/RenderScript;->destroy()V

    .line 645
    return-object p1

    .line 605
    :cond_4
    div-int/2addr v0, v3

    div-int/2addr v1, v3

    invoke-static {p0, v0, v1}, Lmiui/graphics/BitmapFactory;->scaleBitmap(Landroid/graphics/Bitmap;II)Landroid/graphics/Bitmap;

    move-result-object v0

    goto :goto_1

    :cond_5
    move-object v1, v0

    .line 629
    goto :goto_3

    :cond_6
    move-object v1, v2

    goto :goto_2
.end method

.method private static b([B)Z
    .locals 4

    .prologue
    const/4 v1, 0x0

    .line 533
    if-eqz p0, :cond_0

    array-length v0, p0

    sget-object v2, Lmiui/graphics/BitmapFactory;->tf:[B

    array-length v2, v2

    if-ge v0, v2, :cond_1

    .line 541
    :cond_0
    :goto_0
    return v1

    :cond_1
    move v0, v1

    .line 536
    :goto_1
    sget-object v2, Lmiui/graphics/BitmapFactory;->tf:[B

    array-length v2, v2

    if-ge v0, v2, :cond_2

    .line 537
    aget-byte v2, p0, v0

    sget-object v3, Lmiui/graphics/BitmapFactory;->tf:[B

    aget-byte v3, v3, v0

    if-ne v2, v3, :cond_0

    .line 536
    add-int/lit8 v0, v0, 0x1

    goto :goto_1

    .line 541
    :cond_2
    const/4 v1, 0x1

    goto :goto_0
.end method

.method private static bO()Landroid/graphics/Canvas;
    .locals 2

    .prologue
    .line 706
    sget-object v0, Lmiui/graphics/BitmapFactory;->tg:Ljava/lang/ThreadLocal;

    invoke-virtual {v0}, Ljava/lang/ThreadLocal;->get()Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Landroid/graphics/Canvas;

    .line 707
    if-nez v0, :cond_0

    .line 708
    new-instance v0, Landroid/graphics/Canvas;

    invoke-direct {v0}, Landroid/graphics/Canvas;-><init>()V

    .line 709
    sget-object v1, Lmiui/graphics/BitmapFactory;->tg:Ljava/lang/ThreadLocal;

    invoke-virtual {v1, v0}, Ljava/lang/ThreadLocal;->set(Ljava/lang/Object;)V

    .line 711
    :cond_0
    return-object v0
.end method

.method public static composeBitmap(Landroid/graphics/Bitmap;Landroid/graphics/Bitmap;Landroid/graphics/drawable/Drawable;Landroid/graphics/drawable/Drawable;Landroid/graphics/drawable/Drawable;)Landroid/graphics/Bitmap;
    .locals 7

    .prologue
    const/4 v2, 0x0

    .line 753
    new-instance v5, Landroid/graphics/Rect;

    invoke-virtual {p0}, Landroid/graphics/Bitmap;->getWidth()I

    move-result v0

    invoke-virtual {p0}, Landroid/graphics/Bitmap;->getHeight()I

    move-result v1

    invoke-direct {v5, v2, v2, v0, v1}, Landroid/graphics/Rect;-><init>(IIII)V

    .line 755
    if-eqz p1, :cond_0

    .line 756
    new-instance v6, Landroid/graphics/Rect;

    invoke-virtual {p1}, Landroid/graphics/Bitmap;->getWidth()I

    move-result v0

    invoke-virtual {p1}, Landroid/graphics/Bitmap;->getHeight()I

    move-result v1

    invoke-direct {v6, v2, v2, v0, v1}, Landroid/graphics/Rect;-><init>(IIII)V

    :goto_0
    move-object v0, p0

    move-object v1, p1

    move-object v2, p2

    move-object v3, p3

    move-object v4, p4

    .line 760
    invoke-static/range {v0 .. v6}, Lmiui/graphics/BitmapFactory;->composeBitmap(Landroid/graphics/Bitmap;Landroid/graphics/Bitmap;Landroid/graphics/drawable/Drawable;Landroid/graphics/drawable/Drawable;Landroid/graphics/drawable/Drawable;Landroid/graphics/Rect;Landroid/graphics/Rect;)Landroid/graphics/Bitmap;

    move-result-object v0

    return-object v0

    .line 758
    :cond_0
    new-instance v6, Landroid/graphics/Rect;

    invoke-virtual {p0}, Landroid/graphics/Bitmap;->getWidth()I

    move-result v0

    invoke-virtual {p0}, Landroid/graphics/Bitmap;->getHeight()I

    move-result v1

    invoke-direct {v6, v2, v2, v0, v1}, Landroid/graphics/Rect;-><init>(IIII)V

    goto :goto_0
.end method

.method public static composeBitmap(Landroid/graphics/Bitmap;Landroid/graphics/Bitmap;Landroid/graphics/drawable/Drawable;Landroid/graphics/drawable/Drawable;Landroid/graphics/drawable/Drawable;I)Landroid/graphics/Bitmap;
    .locals 7

    .prologue
    const/4 v0, 0x0

    .line 777
    const/4 v5, 0x0

    new-instance v6, Landroid/graphics/Rect;

    invoke-direct {v6, v0, v0, p5, p5}, Landroid/graphics/Rect;-><init>(IIII)V

    move-object v0, p0

    move-object v1, p1

    move-object v2, p2

    move-object v3, p3

    move-object v4, p4

    invoke-static/range {v0 .. v6}, Lmiui/graphics/BitmapFactory;->composeBitmap(Landroid/graphics/Bitmap;Landroid/graphics/Bitmap;Landroid/graphics/drawable/Drawable;Landroid/graphics/drawable/Drawable;Landroid/graphics/drawable/Drawable;Landroid/graphics/Rect;Landroid/graphics/Rect;)Landroid/graphics/Bitmap;

    move-result-object v0

    return-object v0
.end method

.method public static composeBitmap(Landroid/graphics/Bitmap;Landroid/graphics/Bitmap;Landroid/graphics/drawable/Drawable;Landroid/graphics/drawable/Drawable;Landroid/graphics/drawable/Drawable;Landroid/graphics/Rect;Landroid/graphics/Rect;)Landroid/graphics/Bitmap;
    .locals 4

    .prologue
    const/4 v3, 0x0

    const/4 v0, 0x0

    .line 794
    if-nez p1, :cond_1

    if-nez p6, :cond_1

    move-object p1, v0

    .line 822
    :cond_0
    :goto_0
    return-object p1

    .line 798
    :cond_1
    if-nez p1, :cond_6

    .line 799
    invoke-virtual {p6}, Landroid/graphics/Rect;->height()I

    move-result v1

    if-lez v1, :cond_2

    invoke-virtual {p6}, Landroid/graphics/Rect;->width()I

    move-result v1

    if-gtz v1, :cond_3

    :cond_2
    move-object p1, v0

    .line 800
    goto :goto_0

    .line 802
    :cond_3
    invoke-virtual {p6}, Landroid/graphics/Rect;->width()I

    move-result v1

    invoke-virtual {p6}, Landroid/graphics/Rect;->height()I

    move-result v2

    sget-object v3, Landroid/graphics/Bitmap$Config;->ARGB_8888:Landroid/graphics/Bitmap$Config;

    invoke-static {v1, v2, v3}, Landroid/graphics/Bitmap;->createBitmap(IILandroid/graphics/Bitmap$Config;)Landroid/graphics/Bitmap;

    move-result-object p1

    .line 807
    :cond_4
    :goto_1
    invoke-static {p0, p2, v0, p5, p6}, Lmiui/graphics/BitmapFactory;->maskOutBitmap(Landroid/graphics/Bitmap;Landroid/graphics/drawable/Drawable;Landroid/graphics/Bitmap;Landroid/graphics/Rect;Landroid/graphics/Rect;)Landroid/graphics/Bitmap;

    move-result-object v1

    .line 809
    invoke-static {}, Lmiui/graphics/BitmapFactory;->bO()Landroid/graphics/Canvas;

    move-result-object v2

    .line 810
    invoke-virtual {v2, p1}, Landroid/graphics/Canvas;->setBitmap(Landroid/graphics/Bitmap;)V

    .line 811
    if-eqz p4, :cond_5

    .line 812
    invoke-virtual {p4, p6}, Landroid/graphics/drawable/Drawable;->setBounds(Landroid/graphics/Rect;)V

    .line 813
    invoke-virtual {p4, v2}, Landroid/graphics/drawable/Drawable;->draw(Landroid/graphics/Canvas;)V

    .line 815
    :cond_5
    invoke-virtual {v2, v1, p6, p6, v0}, Landroid/graphics/Canvas;->drawBitmap(Landroid/graphics/Bitmap;Landroid/graphics/Rect;Landroid/graphics/Rect;Landroid/graphics/Paint;)V

    .line 816
    invoke-virtual {v1}, Landroid/graphics/Bitmap;->recycle()V

    .line 817
    if-eqz p3, :cond_0

    .line 818
    invoke-virtual {p3, p6}, Landroid/graphics/drawable/Drawable;->setBounds(Landroid/graphics/Rect;)V

    .line 819
    invoke-virtual {p3, v2}, Landroid/graphics/drawable/Drawable;->draw(Landroid/graphics/Canvas;)V

    goto :goto_0

    .line 803
    :cond_6
    if-nez p6, :cond_4

    .line 804
    new-instance p6, Landroid/graphics/Rect;

    invoke-virtual {p1}, Landroid/graphics/Bitmap;->getWidth()I

    move-result v1

    invoke-virtual {p1}, Landroid/graphics/Bitmap;->getHeight()I

    move-result v2

    invoke-direct {p6, v3, v3, v1, v2}, Landroid/graphics/Rect;-><init>(IIII)V

    goto :goto_1
.end method

.method public static createNameBitmap(Landroid/content/Context;Ljava/lang/String;I)Landroid/graphics/Bitmap;
    .locals 1

    .prologue
    const/4 v0, 0x0

    .line 892
    invoke-static {p0, p1, p2, v0, v0}, Lmiui/graphics/BitmapFactory;->createNameBitmap(Landroid/content/Context;Ljava/lang/String;III)Landroid/graphics/Bitmap;

    move-result-object v0

    return-object v0
.end method

.method public static createNameBitmap(Landroid/content/Context;Ljava/lang/String;III)Landroid/graphics/Bitmap;
    .locals 10

    .prologue
    const/4 v0, 0x0

    const/4 v4, 0x1

    const-wide/high16 v8, 0x3fe0000000000000L

    const/4 v6, 0x0

    .line 905
    if-nez p1, :cond_1

    .line 939
    :cond_0
    :goto_0
    return-object v0

    .line 908
    :cond_1
    invoke-virtual {p1}, Ljava/lang/String;->trim()Ljava/lang/String;

    move-result-object v1

    invoke-static {v1}, Lmiui/graphics/BitmapFactory;->r(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v1

    .line 909
    invoke-static {v1}, Landroid/text/TextUtils;->isEmpty(Ljava/lang/CharSequence;)Z

    move-result v2

    if-nez v2, :cond_0

    .line 913
    if-nez p3, :cond_2

    .line 914
    sget p3, Lcom/miui/internal/R$drawable;->word_photo_bg:I

    .line 916
    :cond_2
    invoke-virtual {p0}, Landroid/content/Context;->getResources()Landroid/content/res/Resources;

    move-result-object v0

    invoke-virtual {v0, p3}, Landroid/content/res/Resources;->getDrawable(I)Landroid/graphics/drawable/Drawable;

    move-result-object v2

    .line 917
    new-instance v0, Landroid/graphics/Rect;

    invoke-direct {v0, v6, v6, p2, p2}, Landroid/graphics/Rect;-><init>(IIII)V

    invoke-virtual {v2, v0}, Landroid/graphics/drawable/Drawable;->setBounds(Landroid/graphics/Rect;)V

    .line 919
    if-nez p4, :cond_3

    .line 920
    sget p4, Lmiui/R$color;->word_photo_color:I

    .line 923
    :cond_3
    sget-object v0, Landroid/graphics/Bitmap$Config;->ARGB_8888:Landroid/graphics/Bitmap$Config;

    invoke-static {p2, p2, v0}, Landroid/graphics/Bitmap;->createBitmap(IILandroid/graphics/Bitmap$Config;)Landroid/graphics/Bitmap;

    move-result-object v0

    .line 924
    new-instance v3, Landroid/graphics/Canvas;

    invoke-direct {v3, v0}, Landroid/graphics/Canvas;-><init>(Landroid/graphics/Bitmap;)V

    .line 925
    invoke-virtual {v2, v3}, Landroid/graphics/drawable/Drawable;->draw(Landroid/graphics/Canvas;)V

    .line 927
    new-instance v2, Landroid/graphics/Paint;

    invoke-direct {v2, v4}, Landroid/graphics/Paint;-><init>(I)V

    .line 928
    invoke-virtual {v2, v4}, Landroid/graphics/Paint;->setFilterBitmap(Z)V

    .line 929
    invoke-virtual {p0}, Landroid/content/Context;->getResources()Landroid/content/res/Resources;

    move-result-object v4

    invoke-virtual {v4, p4}, Landroid/content/res/Resources;->getColor(I)I

    move-result v4

    invoke-virtual {v2, v4}, Landroid/graphics/Paint;->setColor(I)V

    .line 930
    int-to-float v4, p2

    const v5, 0x3f19999a

    mul-float/2addr v4, v5

    invoke-virtual {v2, v4}, Landroid/graphics/Paint;->setTextSize(F)V

    .line 932
    new-instance v4, Landroid/graphics/Rect;

    invoke-direct {v4}, Landroid/graphics/Rect;-><init>()V

    .line 933
    invoke-virtual {v1}, Ljava/lang/String;->length()I

    move-result v5

    invoke-virtual {v2, v1, v6, v5, v4}, Landroid/graphics/Paint;->getTextBounds(Ljava/lang/String;IILandroid/graphics/Rect;)V

    .line 935
    iget v5, v4, Landroid/graphics/Rect;->right:I

    iget v6, v4, Landroid/graphics/Rect;->left:I

    add-int/2addr v5, v6

    sub-int v5, p2, v5

    int-to-double v5, v5

    mul-double/2addr v5, v8

    double-to-int v5, v5

    .line 936
    iget v6, v4, Landroid/graphics/Rect;->top:I

    iget v4, v4, Landroid/graphics/Rect;->bottom:I

    add-int/2addr v4, v6

    sub-int v4, p2, v4

    int-to-double v6, v4

    mul-double/2addr v6, v8

    double-to-int v4, v6

    .line 937
    int-to-float v5, v5

    int-to-float v4, v4

    invoke-virtual {v3, v1, v5, v4, v2}, Landroid/graphics/Canvas;->drawText(Ljava/lang/String;FFLandroid/graphics/Paint;)V

    goto :goto_0
.end method

.method public static createPhoto(Landroid/content/Context;Landroid/graphics/Bitmap;)Landroid/graphics/Bitmap;
    .locals 2

    .prologue
    .line 721
    invoke-virtual {p0}, Landroid/content/Context;->getResources()Landroid/content/res/Resources;

    move-result-object v0

    sget v1, Lcom/miui/internal/R$dimen;->contact_photo_width:I

    invoke-virtual {v0, v1}, Landroid/content/res/Resources;->getDimensionPixelSize(I)I

    move-result v0

    .line 722
    invoke-static {p0, p1, v0}, Lmiui/graphics/BitmapFactory;->createPhoto(Landroid/content/Context;Landroid/graphics/Bitmap;I)Landroid/graphics/Bitmap;

    move-result-object v0

    return-object v0
.end method

.method public static createPhoto(Landroid/content/Context;Landroid/graphics/Bitmap;I)Landroid/graphics/Bitmap;
    .locals 6

    .prologue
    .line 733
    invoke-virtual {p0}, Landroid/content/Context;->getResources()Landroid/content/res/Resources;

    move-result-object v0

    .line 734
    sget v1, Lcom/miui/internal/R$drawable;->ic_contact_photo_fg:I

    invoke-virtual {v0, v1}, Landroid/content/res/Resources;->getDrawable(I)Landroid/graphics/drawable/Drawable;

    move-result-object v3

    .line 735
    sget v1, Lcom/miui/internal/R$drawable;->ic_contact_photo_bg:I

    invoke-virtual {v0, v1}, Landroid/content/res/Resources;->getDrawable(I)Landroid/graphics/drawable/Drawable;

    move-result-object v4

    .line 736
    sget v1, Lcom/miui/internal/R$drawable;->ic_contact_photo_mask:I

    invoke-virtual {v0, v1}, Landroid/content/res/Resources;->getDrawable(I)Landroid/graphics/drawable/Drawable;

    move-result-object v2

    .line 737
    const/4 v1, 0x0

    move-object v0, p1

    move v5, p2

    invoke-static/range {v0 .. v5}, Lmiui/graphics/BitmapFactory;->composeBitmap(Landroid/graphics/Bitmap;Landroid/graphics/Bitmap;Landroid/graphics/drawable/Drawable;Landroid/graphics/drawable/Drawable;Landroid/graphics/drawable/Drawable;I)Landroid/graphics/Bitmap;

    move-result-object v0

    return-object v0
.end method

.method public static cropBitmap(Landroid/graphics/Bitmap;Landroid/graphics/Bitmap;Lmiui/graphics/BitmapFactory$CropOption;)Landroid/graphics/Bitmap;
    .locals 16

    .prologue
    .line 384
    if-eqz p0, :cond_0

    if-nez p1, :cond_2

    .line 385
    :cond_0
    const/16 p1, 0x0

    .line 438
    :cond_1
    :goto_0
    return-object p1

    .line 387
    :cond_2
    if-nez p2, :cond_3

    .line 388
    new-instance p2, Lmiui/graphics/BitmapFactory$CropOption;

    invoke-direct/range {p2 .. p2}, Lmiui/graphics/BitmapFactory$CropOption;-><init>()V

    .line 391
    :cond_3
    move-object/from16 v0, p2

    iget-object v1, v0, Lmiui/graphics/BitmapFactory$CropOption;->srcBmpDrawingArea:Landroid/graphics/Rect;

    .line 392
    if-nez v1, :cond_4

    .line 393
    new-instance v1, Landroid/graphics/Rect;

    const/4 v2, 0x0

    const/4 v3, 0x0

    invoke-virtual/range {p0 .. p0}, Landroid/graphics/Bitmap;->getWidth()I

    move-result v4

    invoke-virtual/range {p0 .. p0}, Landroid/graphics/Bitmap;->getHeight()I

    move-result v5

    invoke-direct {v1, v2, v3, v4, v5}, Landroid/graphics/Rect;-><init>(IIII)V

    .line 396
    :cond_4
    const/4 v2, 0x0

    invoke-virtual/range {p0 .. p0}, Landroid/graphics/Bitmap;->getWidth()I

    move-result v3

    add-int/lit8 v3, v3, -0x1

    iget v4, v1, Landroid/graphics/Rect;->left:I

    invoke-static {v2, v3, v4}, Lmiui/graphics/BitmapFactory;->e(III)I

    move-result v2

    .line 397
    invoke-virtual/range {p0 .. p0}, Landroid/graphics/Bitmap;->getWidth()I

    move-result v3

    iget v4, v1, Landroid/graphics/Rect;->right:I

    invoke-static {v2, v3, v4}, Lmiui/graphics/BitmapFactory;->e(III)I

    move-result v3

    .line 398
    const/4 v4, 0x0

    invoke-virtual/range {p0 .. p0}, Landroid/graphics/Bitmap;->getHeight()I

    move-result v5

    add-int/lit8 v5, v5, -0x1

    iget v6, v1, Landroid/graphics/Rect;->top:I

    invoke-static {v4, v5, v6}, Lmiui/graphics/BitmapFactory;->e(III)I

    move-result v4

    .line 399
    invoke-virtual/range {p0 .. p0}, Landroid/graphics/Bitmap;->getHeight()I

    move-result v5

    iget v1, v1, Landroid/graphics/Rect;->bottom:I

    invoke-static {v4, v5, v1}, Lmiui/graphics/BitmapFactory;->e(III)I

    move-result v1

    .line 400
    sub-int v5, v3, v2

    .line 401
    sub-int v6, v1, v4

    .line 402
    invoke-virtual/range {p1 .. p1}, Landroid/graphics/Bitmap;->getWidth()I

    move-result v7

    .line 403
    invoke-virtual/range {p1 .. p1}, Landroid/graphics/Bitmap;->getHeight()I

    move-result v8

    .line 405
    const/4 v9, 0x0

    invoke-static {v7, v8}, Ljava/lang/Math;->min(II)I

    move-result v10

    div-int/lit8 v10, v10, 0x2

    move-object/from16 v0, p2

    iget v11, v0, Lmiui/graphics/BitmapFactory$CropOption;->borderWidth:I

    invoke-static {v9, v10, v11}, Lmiui/graphics/BitmapFactory;->e(III)I

    move-result v9

    move-object/from16 v0, p2

    iput v9, v0, Lmiui/graphics/BitmapFactory$CropOption;->borderWidth:I

    .line 406
    const/4 v9, 0x0

    div-int/lit8 v10, v7, 0x2

    move-object/from16 v0, p2

    iget v11, v0, Lmiui/graphics/BitmapFactory$CropOption;->rx:I

    invoke-static {v9, v10, v11}, Lmiui/graphics/BitmapFactory;->e(III)I

    move-result v9

    move-object/from16 v0, p2

    iput v9, v0, Lmiui/graphics/BitmapFactory$CropOption;->rx:I

    .line 407
    const/4 v9, 0x0

    div-int/lit8 v10, v8, 0x2

    move-object/from16 v0, p2

    iget v11, v0, Lmiui/graphics/BitmapFactory$CropOption;->ry:I

    invoke-static {v9, v10, v11}, Lmiui/graphics/BitmapFactory;->e(III)I

    move-result v9

    move-object/from16 v0, p2

    iput v9, v0, Lmiui/graphics/BitmapFactory$CropOption;->ry:I

    .line 409
    new-instance v9, Landroid/graphics/Paint;

    invoke-direct {v9}, Landroid/graphics/Paint;-><init>()V

    .line 410
    const/4 v10, 0x1

    invoke-virtual {v9, v10}, Landroid/graphics/Paint;->setFilterBitmap(Z)V

    .line 411
    const/4 v10, 0x1

    invoke-virtual {v9, v10}, Landroid/graphics/Paint;->setAntiAlias(Z)V

    .line 412
    const/4 v10, 0x1

    invoke-virtual {v9, v10}, Landroid/graphics/Paint;->setDither(Z)V

    .line 414
    new-instance v10, Landroid/graphics/Canvas;

    move-object/from16 v0, p1

    invoke-direct {v10, v0}, Landroid/graphics/Canvas;-><init>(Landroid/graphics/Bitmap;)V

    .line 415
    const/4 v11, 0x0

    const/4 v12, 0x0

    const/4 v13, 0x0

    const/4 v14, 0x0

    invoke-virtual {v10, v11, v12, v13, v14}, Landroid/graphics/Canvas;->drawARGB(IIII)V

    .line 417
    move-object/from16 v0, p2

    iget v11, v0, Lmiui/graphics/BitmapFactory$CropOption;->rx:I

    move-object/from16 v0, p2

    iget v12, v0, Lmiui/graphics/BitmapFactory$CropOption;->borderWidth:I

    sub-int/2addr v11, v12

    if-lez v11, :cond_5

    move-object/from16 v0, p2

    iget v11, v0, Lmiui/graphics/BitmapFactory$CropOption;->ry:I

    move-object/from16 v0, p2

    iget v12, v0, Lmiui/graphics/BitmapFactory$CropOption;->borderWidth:I

    sub-int/2addr v11, v12

    if-lez v11, :cond_5

    .line 418
    new-instance v11, Landroid/graphics/RectF;

    move-object/from16 v0, p2

    iget v12, v0, Lmiui/graphics/BitmapFactory$CropOption;->borderWidth:I

    int-to-float v12, v12

    move-object/from16 v0, p2

    iget v13, v0, Lmiui/graphics/BitmapFactory$CropOption;->borderWidth:I

    int-to-float v13, v13

    move-object/from16 v0, p2

    iget v14, v0, Lmiui/graphics/BitmapFactory$CropOption;->borderWidth:I

    sub-int v14, v7, v14

    int-to-float v14, v14

    move-object/from16 v0, p2

    iget v15, v0, Lmiui/graphics/BitmapFactory$CropOption;->borderWidth:I

    sub-int v15, v8, v15

    int-to-float v15, v15

    invoke-direct {v11, v12, v13, v14, v15}, Landroid/graphics/RectF;-><init>(FFFF)V

    move-object/from16 v0, p2

    iget v12, v0, Lmiui/graphics/BitmapFactory$CropOption;->rx:I

    move-object/from16 v0, p2

    iget v13, v0, Lmiui/graphics/BitmapFactory$CropOption;->borderWidth:I

    sub-int/2addr v12, v13

    int-to-float v12, v12

    move-object/from16 v0, p2

    iget v13, v0, Lmiui/graphics/BitmapFactory$CropOption;->ry:I

    move-object/from16 v0, p2

    iget v14, v0, Lmiui/graphics/BitmapFactory$CropOption;->borderWidth:I

    sub-int/2addr v13, v14

    int-to-float v13, v13

    invoke-virtual {v10, v11, v12, v13, v9}, Landroid/graphics/Canvas;->drawRoundRect(Landroid/graphics/RectF;FFLandroid/graphics/Paint;)V

    .line 420
    new-instance v11, Landroid/graphics/PorterDuffXfermode;

    sget-object v12, Landroid/graphics/PorterDuff$Mode;->SRC_IN:Landroid/graphics/PorterDuff$Mode;

    invoke-direct {v11, v12}, Landroid/graphics/PorterDuffXfermode;-><init>(Landroid/graphics/PorterDuff$Mode;)V

    invoke-virtual {v9, v11}, Landroid/graphics/Paint;->setXfermode(Landroid/graphics/Xfermode;)Landroid/graphics/Xfermode;

    .line 423
    :cond_5
    move-object/from16 v0, p2

    iget v11, v0, Lmiui/graphics/BitmapFactory$CropOption;->borderWidth:I

    mul-int/lit8 v11, v11, 0x2

    sub-int v11, v7, v11

    .line 424
    move-object/from16 v0, p2

    iget v12, v0, Lmiui/graphics/BitmapFactory$CropOption;->borderWidth:I

    mul-int/lit8 v12, v12, 0x2

    sub-int v12, v8, v12

    .line 425
    const/high16 v13, 0x3f800000

    int-to-float v14, v5

    mul-float/2addr v13, v14

    int-to-float v14, v11

    div-float/2addr v13, v14

    const/high16 v14, 0x3f800000

    int-to-float v15, v6

    mul-float/2addr v14, v15

    int-to-float v15, v12

    div-float/2addr v14, v15

    invoke-static {v13, v14}, Ljava/lang/Math;->min(FF)F

    move-result v13

    .line 426
    int-to-float v5, v5

    int-to-float v11, v11

    mul-float/2addr v11, v13

    sub-float/2addr v5, v11

    const/high16 v11, 0x40000000

    div-float/2addr v5, v11

    float-to-int v5, v5

    .line 427
    int-to-float v6, v6

    int-to-float v11, v12

    mul-float/2addr v11, v13

    sub-float/2addr v6, v11

    const/high16 v11, 0x40000000

    div-float/2addr v6, v11

    float-to-int v6, v6

    .line 428
    new-instance v11, Landroid/graphics/Rect;

    add-int/2addr v2, v5

    add-int/2addr v4, v6

    sub-int/2addr v3, v5

    sub-int/2addr v1, v6

    invoke-direct {v11, v2, v4, v3, v1}, Landroid/graphics/Rect;-><init>(IIII)V

    .line 430
    new-instance v1, Landroid/graphics/Rect;

    move-object/from16 v0, p2

    iget v2, v0, Lmiui/graphics/BitmapFactory$CropOption;->borderWidth:I

    move-object/from16 v0, p2

    iget v3, v0, Lmiui/graphics/BitmapFactory$CropOption;->borderWidth:I

    move-object/from16 v0, p2

    iget v4, v0, Lmiui/graphics/BitmapFactory$CropOption;->borderWidth:I

    sub-int v4, v7, v4

    move-object/from16 v0, p2

    iget v5, v0, Lmiui/graphics/BitmapFactory$CropOption;->borderWidth:I

    sub-int v5, v8, v5

    invoke-direct {v1, v2, v3, v4, v5}, Landroid/graphics/Rect;-><init>(IIII)V

    .line 431
    move-object/from16 v0, p0

    invoke-virtual {v10, v0, v11, v1, v9}, Landroid/graphics/Canvas;->drawBitmap(Landroid/graphics/Bitmap;Landroid/graphics/Rect;Landroid/graphics/Rect;Landroid/graphics/Paint;)V

    .line 433
    move-object/from16 v0, p2

    iget v1, v0, Lmiui/graphics/BitmapFactory$CropOption;->borderWidth:I

    if-lez v1, :cond_1

    move-object/from16 v0, p2

    iget v1, v0, Lmiui/graphics/BitmapFactory$CropOption;->borderColor:I

    ushr-int/lit8 v1, v1, 0x18

    if-eqz v1, :cond_1

    .line 434
    move-object/from16 v0, p2

    iget v1, v0, Lmiui/graphics/BitmapFactory$CropOption;->borderColor:I

    invoke-virtual {v9, v1}, Landroid/graphics/Paint;->setColor(I)V

    .line 435
    new-instance v1, Landroid/graphics/PorterDuffXfermode;

    sget-object v2, Landroid/graphics/PorterDuff$Mode;->DST_OVER:Landroid/graphics/PorterDuff$Mode;

    invoke-direct {v1, v2}, Landroid/graphics/PorterDuffXfermode;-><init>(Landroid/graphics/PorterDuff$Mode;)V

    invoke-virtual {v9, v1}, Landroid/graphics/Paint;->setXfermode(Landroid/graphics/Xfermode;)Landroid/graphics/Xfermode;

    .line 436
    new-instance v1, Landroid/graphics/RectF;

    const/4 v2, 0x0

    const/4 v3, 0x0

    int-to-float v4, v7

    int-to-float v5, v8

    invoke-direct {v1, v2, v3, v4, v5}, Landroid/graphics/RectF;-><init>(FFFF)V

    move-object/from16 v0, p2

    iget v2, v0, Lmiui/graphics/BitmapFactory$CropOption;->rx:I

    int-to-float v2, v2

    move-object/from16 v0, p2

    iget v3, v0, Lmiui/graphics/BitmapFactory$CropOption;->ry:I

    int-to-float v3, v3

    invoke-virtual {v10, v1, v2, v3, v9}, Landroid/graphics/Canvas;->drawRoundRect(Landroid/graphics/RectF;FFLandroid/graphics/Paint;)V

    goto/16 :goto_0
.end method

.method public static cropBitmap(Landroid/graphics/Bitmap;Lmiui/graphics/BitmapFactory$CropOption;)Landroid/graphics/Bitmap;
    .locals 1

    .prologue
    .line 369
    if-eqz p0, :cond_0

    .line 370
    invoke-static {p0}, Lmiui/graphics/BitmapFactory;->a(Landroid/graphics/Bitmap;)Landroid/graphics/Bitmap;

    move-result-object v0

    .line 371
    invoke-static {p0, v0, p1}, Lmiui/graphics/BitmapFactory;->cropBitmap(Landroid/graphics/Bitmap;Landroid/graphics/Bitmap;Lmiui/graphics/BitmapFactory$CropOption;)Landroid/graphics/Bitmap;

    move-result-object v0

    .line 373
    :goto_0
    return-object v0

    :cond_0
    const/4 v0, 0x0

    goto :goto_0
.end method

.method public static decodeBitmap(Landroid/content/Context;Landroid/net/Uri;IIZ)Landroid/graphics/Bitmap;
    .locals 3
    .annotation system Ldalvik/annotation/Throws;
        value = {
            Ljava/io/IOException;
        }
    .end annotation

    .prologue
    .line 186
    const/4 v2, 0x0

    .line 188
    :try_start_0
    new-instance v1, Lmiui/io/ResettableInputStream;

    invoke-direct {v1, p0, p1}, Lmiui/io/ResettableInputStream;-><init>(Landroid/content/Context;Landroid/net/Uri;)V
    :try_end_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    .line 189
    :try_start_1
    invoke-static {v1, p2, p3, p4}, Lmiui/graphics/BitmapFactory;->decodeBitmap(Lmiui/io/ResettableInputStream;IIZ)Landroid/graphics/Bitmap;
    :try_end_1
    .catchall {:try_start_1 .. :try_end_1} :catchall_1

    move-result-object v0

    .line 191
    if-eqz v1, :cond_0

    .line 192
    invoke-virtual {v1}, Lmiui/io/ResettableInputStream;->close()V

    :cond_0
    return-object v0

    .line 191
    :catchall_0
    move-exception v0

    move-object v1, v2

    :goto_0
    if-eqz v1, :cond_1

    .line 192
    invoke-virtual {v1}, Lmiui/io/ResettableInputStream;->close()V

    :cond_1
    throw v0

    .line 191
    :catchall_1
    move-exception v0

    goto :goto_0
.end method

.method public static decodeBitmap(Landroid/content/Context;Landroid/net/Uri;IZ)Landroid/graphics/Bitmap;
    .locals 3
    .annotation system Ldalvik/annotation/Throws;
        value = {
            Ljava/io/IOException;
        }
    .end annotation

    .prologue
    .line 229
    const/4 v2, 0x0

    .line 231
    :try_start_0
    new-instance v1, Lmiui/io/ResettableInputStream;

    invoke-direct {v1, p0, p1}, Lmiui/io/ResettableInputStream;-><init>(Landroid/content/Context;Landroid/net/Uri;)V
    :try_end_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    .line 232
    :try_start_1
    invoke-static {v1, p2, p3}, Lmiui/graphics/BitmapFactory;->decodeBitmap(Lmiui/io/ResettableInputStream;IZ)Landroid/graphics/Bitmap;
    :try_end_1
    .catchall {:try_start_1 .. :try_end_1} :catchall_1

    move-result-object v0

    .line 234
    if-eqz v1, :cond_0

    .line 235
    invoke-virtual {v1}, Lmiui/io/ResettableInputStream;->close()V

    :cond_0
    return-object v0

    .line 234
    :catchall_0
    move-exception v0

    move-object v1, v2

    :goto_0
    if-eqz v1, :cond_1

    .line 235
    invoke-virtual {v1}, Lmiui/io/ResettableInputStream;->close()V

    :cond_1
    throw v0

    .line 234
    :catchall_1
    move-exception v0

    goto :goto_0
.end method

.method public static decodeBitmap(Landroid/content/Context;Landroid/net/Uri;Z)Landroid/graphics/Bitmap;
    .locals 3
    .annotation system Ldalvik/annotation/Throws;
        value = {
            Ljava/io/IOException;
        }
    .end annotation

    .prologue
    .line 162
    const/4 v2, 0x0

    .line 164
    :try_start_0
    new-instance v1, Lmiui/io/ResettableInputStream;

    invoke-direct {v1, p0, p1}, Lmiui/io/ResettableInputStream;-><init>(Landroid/content/Context;Landroid/net/Uri;)V
    :try_end_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    .line 165
    const/4 v0, -0x1

    :try_start_1
    invoke-static {v1, v0, p2}, Lmiui/graphics/BitmapFactory;->decodeBitmap(Lmiui/io/ResettableInputStream;IZ)Landroid/graphics/Bitmap;
    :try_end_1
    .catchall {:try_start_1 .. :try_end_1} :catchall_1

    move-result-object v0

    .line 167
    if-eqz v1, :cond_0

    .line 168
    invoke-virtual {v1}, Lmiui/io/ResettableInputStream;->close()V

    :cond_0
    return-object v0

    .line 167
    :catchall_0
    move-exception v0

    move-object v1, v2

    :goto_0
    if-eqz v1, :cond_1

    .line 168
    invoke-virtual {v1}, Lmiui/io/ResettableInputStream;->close()V

    :cond_1
    throw v0

    .line 167
    :catchall_1
    move-exception v0

    goto :goto_0
.end method

.method public static decodeBitmap(Ljava/lang/String;IIZ)Landroid/graphics/Bitmap;
    .locals 3
    .annotation system Ldalvik/annotation/Throws;
        value = {
            Ljava/io/IOException;
        }
    .end annotation

    .prologue
    .line 141
    const/4 v2, 0x0

    .line 143
    :try_start_0
    new-instance v1, Lmiui/io/ResettableInputStream;

    invoke-direct {v1, p0}, Lmiui/io/ResettableInputStream;-><init>(Ljava/lang/String;)V
    :try_end_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    .line 144
    :try_start_1
    invoke-static {v1, p1, p2, p3}, Lmiui/graphics/BitmapFactory;->decodeBitmap(Lmiui/io/ResettableInputStream;IIZ)Landroid/graphics/Bitmap;
    :try_end_1
    .catchall {:try_start_1 .. :try_end_1} :catchall_1

    move-result-object v0

    .line 146
    if-eqz v1, :cond_0

    .line 147
    invoke-virtual {v1}, Lmiui/io/ResettableInputStream;->close()V

    :cond_0
    return-object v0

    .line 146
    :catchall_0
    move-exception v0

    move-object v1, v2

    :goto_0
    if-eqz v1, :cond_1

    .line 147
    invoke-virtual {v1}, Lmiui/io/ResettableInputStream;->close()V

    :cond_1
    throw v0

    .line 146
    :catchall_1
    move-exception v0

    goto :goto_0
.end method

.method public static decodeBitmap(Ljava/lang/String;IZ)Landroid/graphics/Bitmap;
    .locals 3
    .annotation system Ldalvik/annotation/Throws;
        value = {
            Ljava/io/IOException;
        }
    .end annotation

    .prologue
    .line 207
    const/4 v2, 0x0

    .line 209
    :try_start_0
    new-instance v1, Lmiui/io/ResettableInputStream;

    invoke-direct {v1, p0}, Lmiui/io/ResettableInputStream;-><init>(Ljava/lang/String;)V
    :try_end_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    .line 210
    :try_start_1
    invoke-static {v1, p1, p2}, Lmiui/graphics/BitmapFactory;->decodeBitmap(Lmiui/io/ResettableInputStream;IZ)Landroid/graphics/Bitmap;
    :try_end_1
    .catchall {:try_start_1 .. :try_end_1} :catchall_1

    move-result-object v0

    .line 212
    if-eqz v1, :cond_0

    .line 213
    invoke-virtual {v1}, Lmiui/io/ResettableInputStream;->close()V

    :cond_0
    return-object v0

    .line 212
    :catchall_0
    move-exception v0

    move-object v1, v2

    :goto_0
    if-eqz v1, :cond_1

    .line 213
    invoke-virtual {v1}, Lmiui/io/ResettableInputStream;->close()V

    :cond_1
    throw v0

    .line 212
    :catchall_1
    move-exception v0

    goto :goto_0
.end method

.method public static decodeBitmap(Ljava/lang/String;Z)Landroid/graphics/Bitmap;
    .locals 3
    .annotation system Ldalvik/annotation/Throws;
        value = {
            Ljava/io/IOException;
        }
    .end annotation

    .prologue
    .line 118
    const/4 v2, 0x0

    .line 120
    :try_start_0
    new-instance v1, Lmiui/io/ResettableInputStream;

    invoke-direct {v1, p0}, Lmiui/io/ResettableInputStream;-><init>(Ljava/lang/String;)V
    :try_end_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    .line 121
    const/4 v0, -0x1

    :try_start_1
    invoke-static {v1, v0, p1}, Lmiui/graphics/BitmapFactory;->decodeBitmap(Lmiui/io/ResettableInputStream;IZ)Landroid/graphics/Bitmap;
    :try_end_1
    .catchall {:try_start_1 .. :try_end_1} :catchall_1

    move-result-object v0

    .line 123
    if-eqz v1, :cond_0

    .line 124
    invoke-virtual {v1}, Lmiui/io/ResettableInputStream;->close()V

    :cond_0
    return-object v0

    .line 123
    :catchall_0
    move-exception v0

    move-object v1, v2

    :goto_0
    if-eqz v1, :cond_1

    .line 124
    invoke-virtual {v1}, Lmiui/io/ResettableInputStream;->close()V

    :cond_1
    throw v0

    .line 123
    :catchall_1
    move-exception v0

    goto :goto_0
.end method

.method public static decodeBitmap(Lmiui/io/ResettableInputStream;IIZ)Landroid/graphics/Bitmap;
    .locals 2
    .annotation system Ldalvik/annotation/Throws;
        value = {
            Ljava/io/IOException;
        }
    .end annotation

    .prologue
    .line 286
    mul-int v0, p1, p2

    .line 287
    if-lez p1, :cond_0

    if-gtz p2, :cond_1

    .line 288
    :cond_0
    const/4 v0, -0x1

    .line 290
    :cond_1
    invoke-static {p0, v0, p3}, Lmiui/graphics/BitmapFactory;->decodeBitmap(Lmiui/io/ResettableInputStream;IZ)Landroid/graphics/Bitmap;

    move-result-object v1

    .line 291
    if-nez v1, :cond_3

    .line 292
    const/4 v0, 0x0

    .line 301
    :cond_2
    :goto_0
    return-object v0

    .line 294
    :cond_3
    if-lez v0, :cond_4

    .line 296
    invoke-static {v1, p1, p2}, Lmiui/graphics/BitmapFactory;->scaleBitmap(Landroid/graphics/Bitmap;II)Landroid/graphics/Bitmap;

    move-result-object v0

    .line 297
    if-eq v1, v0, :cond_2

    .line 298
    invoke-virtual {v1}, Landroid/graphics/Bitmap;->recycle()V

    goto :goto_0

    :cond_4
    move-object v0, v1

    goto :goto_0
.end method

.method public static decodeBitmap(Lmiui/io/ResettableInputStream;IZ)Landroid/graphics/Bitmap;
    .locals 5
    .annotation system Ldalvik/annotation/Throws;
        value = {
            Ljava/io/IOException;
        }
    .end annotation

    .prologue
    const/4 v0, 0x0

    const/4 v1, 0x0

    .line 251
    new-instance v3, Landroid/graphics/BitmapFactory$Options;

    invoke-direct {v3}, Landroid/graphics/BitmapFactory$Options;-><init>()V

    .line 252
    const/4 v2, 0x1

    iput v2, v3, Landroid/graphics/BitmapFactory$Options;->inSampleSize:I

    .line 253
    iput-boolean v1, v3, Landroid/graphics/BitmapFactory$Options;->inScaled:Z

    .line 254
    invoke-static {p0, p1}, Lmiui/graphics/BitmapFactory;->a(Lmiui/io/ResettableInputStream;I)I

    move-result v2

    iput v2, v3, Landroid/graphics/BitmapFactory$Options;->inSampleSize:I

    .line 258
    :goto_0
    add-int/lit8 v2, v1, 0x1

    const/4 v4, 0x3

    if-ge v1, v4, :cond_0

    .line 260
    :try_start_0
    invoke-virtual {p0}, Lmiui/io/ResettableInputStream;->reset()V

    .line 261
    const/4 v1, 0x0

    invoke-static {p0, v1, v3}, Lmiui/graphics/BitmapFactory;->decodeStream(Ljava/io/InputStream;Landroid/graphics/Rect;Landroid/graphics/BitmapFactory$Options;)Landroid/graphics/Bitmap;
    :try_end_0
    .catch Ljava/lang/OutOfMemoryError; {:try_start_0 .. :try_end_0} :catch_0

    move-result-object v0

    .line 271
    :cond_0
    return-object v0

    .line 263
    :catch_0
    move-exception v1

    .line 264
    if-eqz p2, :cond_1

    .line 265
    iget v1, v3, Landroid/graphics/BitmapFactory$Options;->inSampleSize:I

    mul-int/lit8 v1, v1, 0x2

    iput v1, v3, Landroid/graphics/BitmapFactory$Options;->inSampleSize:I

    move v1, v2

    .line 269
    goto :goto_0

    .line 267
    :cond_1
    throw v1
.end method

.method private static e(III)I
    .locals 1

    .prologue
    .line 442
    invoke-static {p0, p2}, Ljava/lang/Math;->max(II)I

    move-result v0

    invoke-static {p1, v0}, Ljava/lang/Math;->min(II)I

    move-result v0

    return v0
.end method

.method public static fastBlur(Landroid/graphics/Bitmap;I)Landroid/graphics/Bitmap;
    .locals 1

    .prologue
    .line 560
    invoke-static {p0}, Lmiui/graphics/BitmapFactory;->a(Landroid/graphics/Bitmap;)Landroid/graphics/Bitmap;

    move-result-object v0

    .line 561
    invoke-static {p0, v0, p1}, Lmiui/graphics/BitmapFactory;->fastBlur(Landroid/graphics/Bitmap;Landroid/graphics/Bitmap;I)Landroid/graphics/Bitmap;

    .line 562
    return-object v0
.end method

.method public static fastBlur(Landroid/graphics/Bitmap;Landroid/graphics/Bitmap;I)Landroid/graphics/Bitmap;
    .locals 2

    .prologue
    .line 575
    if-nez p0, :cond_0

    .line 576
    const/4 p1, 0x0

    .line 586
    :goto_0
    return-object p1

    .line 578
    :cond_0
    if-eqz p1, :cond_1

    invoke-virtual {p0}, Landroid/graphics/Bitmap;->getWidth()I

    move-result v0

    invoke-virtual {p1}, Landroid/graphics/Bitmap;->getWidth()I

    move-result v1

    if-ne v0, v1, :cond_1

    invoke-virtual {p0}, Landroid/graphics/Bitmap;->getHeight()I

    move-result v0

    invoke-virtual {p1}, Landroid/graphics/Bitmap;->getHeight()I

    move-result v1

    if-eq v0, v1, :cond_2

    .line 579
    :cond_1
    invoke-static {p0}, Lmiui/graphics/BitmapFactory;->a(Landroid/graphics/Bitmap;)Landroid/graphics/Bitmap;

    move-result-object p1

    .line 581
    :cond_2
    sget v0, Landroid/os/Build$VERSION;->SDK_INT:I

    const/16 v1, 0x11

    if-lt v0, v1, :cond_3

    .line 582
    invoke-static {p0, p1, p2}, Lmiui/graphics/BitmapFactory;->a(Landroid/graphics/Bitmap;Landroid/graphics/Bitmap;I)Landroid/graphics/Bitmap;

    goto :goto_0

    .line 584
    :cond_3
    invoke-static {p0, p1, p2}, Lmiui/graphics/BitmapFactory;->native_fastBlur(Landroid/graphics/Bitmap;Landroid/graphics/Bitmap;I)V

    goto :goto_0
.end method

.method public static getBitmapColorMode(Landroid/graphics/Bitmap;I)I
    .locals 17

    .prologue
    .line 1051
    const/4 v3, 0x2

    .line 1052
    invoke-virtual/range {p0 .. p0}, Landroid/graphics/Bitmap;->getHeight()I

    move-result v1

    div-int v5, v1, p1

    .line 1053
    invoke-virtual/range {p0 .. p0}, Landroid/graphics/Bitmap;->getWidth()I

    move-result v1

    div-int v6, v1, p1

    .line 1054
    mul-int v1, v6, v5

    div-int/lit8 v7, v1, 0x5

    .line 1055
    move-object/from16 v0, p0

    invoke-static {v0, v6, v5}, Lmiui/graphics/BitmapFactory;->scaleBitmap(Landroid/graphics/Bitmap;II)Landroid/graphics/Bitmap;

    move-result-object v8

    .line 1057
    const/4 v2, 0x0

    .line 1058
    const/4 v1, 0x0

    move v4, v1

    :goto_0
    if-ge v4, v6, :cond_3

    .line 1059
    const/4 v1, 0x0

    move/from16 v16, v1

    move v1, v2

    move v2, v3

    move/from16 v3, v16

    :goto_1
    if-ge v3, v5, :cond_1

    .line 1060
    invoke-virtual {v8, v4, v3}, Landroid/graphics/Bitmap;->getPixel(II)I

    move-result v9

    .line 1061
    const/high16 v10, 0xff0000

    and-int/2addr v10, v9

    shr-int/lit8 v10, v10, 0x10

    .line 1062
    const v11, 0xff00

    and-int/2addr v11, v9

    shr-int/lit8 v11, v11, 0x8

    .line 1063
    and-int/lit16 v9, v9, 0xff

    .line 1064
    int-to-float v10, v10

    float-to-double v12, v10

    const-wide v14, 0x3fd3333333333333L

    mul-double/2addr v12, v14

    int-to-float v10, v11

    float-to-double v10, v10

    const-wide v14, 0x3fe2e147ae147ae1L

    mul-double/2addr v10, v14

    add-double/2addr v10, v12

    int-to-float v9, v9

    float-to-double v12, v9

    const-wide v14, 0x3fbc28f5c28f5c29L

    mul-double/2addr v12, v14

    add-double v9, v10, v12

    double-to-int v9, v9

    .line 1065
    const/16 v10, 0xb4

    if-ge v9, v10, :cond_2

    .line 1066
    add-int/lit8 v1, v1, 0x1

    .line 1067
    if-le v1, v7, :cond_0

    .line 1068
    const/4 v2, 0x1

    .line 1070
    :cond_0
    mul-int/lit8 v9, v7, 0x2

    if-le v1, v9, :cond_2

    .line 1071
    const/4 v2, 0x0

    .line 1058
    :cond_1
    add-int/lit8 v3, v4, 0x1

    move v4, v3

    move v3, v2

    move v2, v1

    goto :goto_0

    .line 1059
    :cond_2
    add-int/lit8 v3, v3, 0x1

    goto :goto_1

    .line 1078
    :cond_3
    move-object/from16 v0, p0

    if-eq v8, v0, :cond_4

    .line 1079
    invoke-virtual {v8}, Landroid/graphics/Bitmap;->recycle()V

    .line 1082
    :cond_4
    return v3
.end method

.method public static getBitmapSize(Landroid/content/Context;Landroid/net/Uri;)Landroid/graphics/BitmapFactory$Options;
    .locals 3
    .annotation system Ldalvik/annotation/Throws;
        value = {
            Ljava/io/IOException;
        }
    .end annotation

    .prologue
    .line 98
    const/4 v2, 0x0

    .line 100
    :try_start_0
    new-instance v1, Lmiui/io/ResettableInputStream;

    invoke-direct {v1, p0, p1}, Lmiui/io/ResettableInputStream;-><init>(Landroid/content/Context;Landroid/net/Uri;)V
    :try_end_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    .line 101
    :try_start_1
    invoke-static {v1}, Lmiui/graphics/BitmapFactory;->getBitmapSize(Lmiui/io/ResettableInputStream;)Landroid/graphics/BitmapFactory$Options;
    :try_end_1
    .catchall {:try_start_1 .. :try_end_1} :catchall_1

    move-result-object v0

    .line 103
    if-eqz v1, :cond_0

    .line 104
    invoke-virtual {v1}, Lmiui/io/ResettableInputStream;->close()V

    :cond_0
    return-object v0

    .line 103
    :catchall_0
    move-exception v0

    move-object v1, v2

    :goto_0
    if-eqz v1, :cond_1

    .line 104
    invoke-virtual {v1}, Lmiui/io/ResettableInputStream;->close()V

    :cond_1
    throw v0

    .line 103
    :catchall_1
    move-exception v0

    goto :goto_0
.end method

.method public static getBitmapSize(Ljava/lang/String;)Landroid/graphics/BitmapFactory$Options;
    .locals 3
    .annotation system Ldalvik/annotation/Throws;
        value = {
            Ljava/io/IOException;
        }
    .end annotation

    .prologue
    .line 79
    const/4 v2, 0x0

    .line 81
    :try_start_0
    new-instance v1, Lmiui/io/ResettableInputStream;

    invoke-direct {v1, p0}, Lmiui/io/ResettableInputStream;-><init>(Ljava/lang/String;)V
    :try_end_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    .line 82
    :try_start_1
    invoke-static {v1}, Lmiui/graphics/BitmapFactory;->getBitmapSize(Lmiui/io/ResettableInputStream;)Landroid/graphics/BitmapFactory$Options;
    :try_end_1
    .catchall {:try_start_1 .. :try_end_1} :catchall_1

    move-result-object v0

    .line 84
    if-eqz v1, :cond_0

    .line 85
    invoke-virtual {v1}, Lmiui/io/ResettableInputStream;->close()V

    :cond_0
    return-object v0

    .line 84
    :catchall_0
    move-exception v0

    move-object v1, v2

    :goto_0
    if-eqz v1, :cond_1

    .line 85
    invoke-virtual {v1}, Lmiui/io/ResettableInputStream;->close()V

    :cond_1
    throw v0

    .line 84
    :catchall_1
    move-exception v0

    goto :goto_0
.end method

.method public static getBitmapSize(Lmiui/io/ResettableInputStream;)Landroid/graphics/BitmapFactory$Options;
    .locals 2

    .prologue
    .line 66
    new-instance v0, Landroid/graphics/BitmapFactory$Options;

    invoke-direct {v0}, Landroid/graphics/BitmapFactory$Options;-><init>()V

    .line 67
    const/4 v1, 0x1

    iput-boolean v1, v0, Landroid/graphics/BitmapFactory$Options;->inJustDecodeBounds:Z

    .line 68
    const/4 v1, 0x0

    invoke-static {p0, v1, v0}, Lmiui/graphics/BitmapFactory;->decodeStream(Ljava/io/InputStream;Landroid/graphics/Rect;Landroid/graphics/BitmapFactory$Options;)Landroid/graphics/Bitmap;

    .line 69
    return-object v0
.end method

.method public static isPngFormat(Landroid/content/Context;Landroid/net/Uri;)Z
    .locals 3
    .annotation system Ldalvik/annotation/Throws;
        value = {
            Ljava/io/IOException;
        }
    .end annotation

    .prologue
    .line 489
    const/4 v2, 0x0

    .line 491
    :try_start_0
    new-instance v1, Lmiui/io/ResettableInputStream;

    invoke-direct {v1, p0, p1}, Lmiui/io/ResettableInputStream;-><init>(Landroid/content/Context;Landroid/net/Uri;)V
    :try_end_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    .line 492
    :try_start_1
    invoke-static {v1}, Lmiui/graphics/BitmapFactory;->isPngFormat(Lmiui/io/ResettableInputStream;)Z
    :try_end_1
    .catchall {:try_start_1 .. :try_end_1} :catchall_1

    move-result v0

    .line 494
    if-eqz v1, :cond_0

    .line 495
    invoke-virtual {v1}, Lmiui/io/ResettableInputStream;->close()V

    :cond_0
    return v0

    .line 494
    :catchall_0
    move-exception v0

    move-object v1, v2

    :goto_0
    if-eqz v1, :cond_1

    .line 495
    invoke-virtual {v1}, Lmiui/io/ResettableInputStream;->close()V

    :cond_1
    throw v0

    .line 494
    :catchall_1
    move-exception v0

    goto :goto_0
.end method

.method public static isPngFormat(Ljava/lang/String;)Z
    .locals 3
    .annotation system Ldalvik/annotation/Throws;
        value = {
            Ljava/io/IOException;
        }
    .end annotation

    .prologue
    .line 506
    const/4 v2, 0x0

    .line 508
    :try_start_0
    new-instance v1, Lmiui/io/ResettableInputStream;

    invoke-direct {v1, p0}, Lmiui/io/ResettableInputStream;-><init>(Ljava/lang/String;)V
    :try_end_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    .line 509
    :try_start_1
    invoke-static {v1}, Lmiui/graphics/BitmapFactory;->isPngFormat(Lmiui/io/ResettableInputStream;)Z
    :try_end_1
    .catchall {:try_start_1 .. :try_end_1} :catchall_1

    move-result v0

    .line 511
    if-eqz v1, :cond_0

    .line 512
    invoke-virtual {v1}, Lmiui/io/ResettableInputStream;->close()V

    :cond_0
    return v0

    .line 511
    :catchall_0
    move-exception v0

    move-object v1, v2

    :goto_0
    if-eqz v1, :cond_1

    .line 512
    invoke-virtual {v1}, Lmiui/io/ResettableInputStream;->close()V

    :cond_1
    throw v0

    .line 511
    :catchall_1
    move-exception v0

    goto :goto_0
.end method

.method public static isPngFormat(Lmiui/io/ResettableInputStream;)Z
    .locals 4
    .annotation system Ldalvik/annotation/Throws;
        value = {
            Ljava/io/IOException;
        }
    .end annotation

    .prologue
    .line 523
    const/4 v0, 0x0

    .line 524
    sget-object v1, Lmiui/graphics/BitmapFactory;->tf:[B

    array-length v1, v1

    new-array v1, v1, [B

    .line 525
    invoke-virtual {p0, v1}, Lmiui/io/ResettableInputStream;->read([B)I

    move-result v2

    .line 526
    array-length v3, v1

    if-lt v2, v3, :cond_0

    .line 527
    invoke-static {v1}, Lmiui/graphics/BitmapFactory;->b([B)Z

    move-result v0

    .line 529
    :cond_0
    return v0
.end method

.method public static maskOutBitmap(Landroid/graphics/Bitmap;Landroid/graphics/drawable/Drawable;Landroid/graphics/Bitmap;Landroid/graphics/Rect;Landroid/graphics/Rect;)Landroid/graphics/Bitmap;
    .locals 9

    .prologue
    const/4 v1, 0x0

    const/4 v0, 0x0

    .line 837
    if-nez p2, :cond_0

    if-nez p4, :cond_0

    move-object p2, v1

    .line 881
    :goto_0
    return-object p2

    .line 841
    :cond_0
    if-nez p2, :cond_6

    .line 842
    invoke-virtual {p4}, Landroid/graphics/Rect;->height()I

    move-result v2

    if-lez v2, :cond_1

    invoke-virtual {p4}, Landroid/graphics/Rect;->width()I

    move-result v2

    if-gtz v2, :cond_2

    :cond_1
    move-object p2, v1

    .line 843
    goto :goto_0

    .line 845
    :cond_2
    invoke-virtual {p4}, Landroid/graphics/Rect;->width()I

    move-result v1

    invoke-virtual {p4}, Landroid/graphics/Rect;->height()I

    move-result v2

    sget-object v3, Landroid/graphics/Bitmap$Config;->ARGB_8888:Landroid/graphics/Bitmap$Config;

    invoke-static {v1, v2, v3}, Landroid/graphics/Bitmap;->createBitmap(IILandroid/graphics/Bitmap$Config;)Landroid/graphics/Bitmap;

    move-result-object p2

    .line 850
    :cond_3
    :goto_1
    invoke-static {}, Lmiui/graphics/BitmapFactory;->bO()Landroid/graphics/Canvas;

    move-result-object v5

    .line 851
    invoke-virtual {v5, p2}, Landroid/graphics/Canvas;->setBitmap(Landroid/graphics/Bitmap;)V

    .line 852
    sget-object v1, Landroid/graphics/PorterDuff$Mode;->CLEAR:Landroid/graphics/PorterDuff$Mode;

    invoke-virtual {v5, v0, v1}, Landroid/graphics/Canvas;->drawColor(ILandroid/graphics/PorterDuff$Mode;)V

    .line 854
    if-eqz p1, :cond_4

    .line 855
    invoke-virtual {p1, p4}, Landroid/graphics/drawable/Drawable;->setBounds(Landroid/graphics/Rect;)V

    .line 856
    invoke-virtual {p1, v5}, Landroid/graphics/drawable/Drawable;->draw(Landroid/graphics/Canvas;)V

    .line 859
    :cond_4
    if-nez p3, :cond_5

    .line 860
    invoke-virtual {p0}, Landroid/graphics/Bitmap;->getWidth()I

    move-result v4

    .line 861
    invoke-virtual {p0}, Landroid/graphics/Bitmap;->getHeight()I

    move-result v2

    .line 862
    invoke-virtual {p4}, Landroid/graphics/Rect;->width()I

    move-result v1

    .line 863
    invoke-virtual {p4}, Landroid/graphics/Rect;->height()I

    move-result v3

    .line 865
    int-to-float v6, v3

    int-to-float v7, v1

    div-float/2addr v6, v7

    .line 866
    int-to-float v7, v4

    int-to-float v1, v1

    div-float v1, v7, v1

    .line 867
    int-to-float v7, v2

    int-to-float v3, v3

    div-float v3, v7, v3

    .line 868
    cmpl-float v7, v1, v3

    if-lez v7, :cond_7

    .line 869
    int-to-float v1, v2

    div-float/2addr v1, v6

    float-to-int v3, v1

    .line 870
    sub-int v1, v4, v3

    div-int/lit8 v1, v1, 0x2

    .line 877
    :goto_2
    new-instance p3, Landroid/graphics/Rect;

    add-int/2addr v3, v1

    add-int/2addr v2, v0

    invoke-direct {p3, v1, v0, v3, v2}, Landroid/graphics/Rect;-><init>(IIII)V

    .line 880
    :cond_5
    sget-object v0, Lmiui/graphics/BitmapFactory;->th:Landroid/graphics/Paint;

    invoke-virtual {v5, p0, p3, p4, v0}, Landroid/graphics/Canvas;->drawBitmap(Landroid/graphics/Bitmap;Landroid/graphics/Rect;Landroid/graphics/Rect;Landroid/graphics/Paint;)V

    goto :goto_0

    .line 846
    :cond_6
    if-nez p4, :cond_3

    .line 847
    new-instance p4, Landroid/graphics/Rect;

    invoke-virtual {p2}, Landroid/graphics/Bitmap;->getWidth()I

    move-result v1

    invoke-virtual {p2}, Landroid/graphics/Bitmap;->getHeight()I

    move-result v2

    invoke-direct {p4, v0, v0, v1, v2}, Landroid/graphics/Rect;-><init>(IIII)V

    goto :goto_1

    .line 872
    :cond_7
    cmpg-float v1, v1, v3

    if-gez v1, :cond_8

    .line 873
    int-to-float v1, v4

    mul-float/2addr v1, v6

    float-to-int v3, v1

    .line 874
    sub-int v1, v2, v3

    div-int/lit8 v1, v1, 0x2

    move v2, v3

    move v3, v4

    move v8, v1

    move v1, v0

    move v0, v8

    .line 875
    goto :goto_2

    :cond_8
    move v1, v0

    move v3, v4

    goto :goto_2
.end method

.method private static native native_fastBlur(Landroid/graphics/Bitmap;Landroid/graphics/Bitmap;I)V
.end method

.method private static r(Ljava/lang/String;)Ljava/lang/String;
    .locals 3

    .prologue
    const/4 v0, 0x0

    .line 943
    invoke-static {p0}, Landroid/text/TextUtils;->isEmpty(Ljava/lang/CharSequence;)Z

    move-result v1

    if-eqz v1, :cond_1

    .line 953
    :cond_0
    :goto_0
    return-object v0

    .line 946
    :cond_1
    invoke-static {p0}, Lmiui/graphics/BitmapFactory;->s(Ljava/lang/String;)Z

    move-result v1

    if-eqz v1, :cond_2

    .line 947
    invoke-static {p0}, Lmiui/graphics/BitmapFactory;->t(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v1

    .line 948
    invoke-static {v1}, Landroid/text/TextUtils;->isEmpty(Ljava/lang/CharSequence;)Z

    move-result v2

    if-nez v2, :cond_0

    .line 950
    invoke-virtual {v1}, Ljava/lang/String;->length()I

    move-result v0

    .line 951
    add-int/lit8 v2, v0, -0x1

    invoke-virtual {v1, v2, v0}, Ljava/lang/String;->substring(II)Ljava/lang/String;

    move-result-object v0

    invoke-virtual {v0}, Ljava/lang/String;->trim()Ljava/lang/String;

    move-result-object v0

    goto :goto_0

    .line 953
    :cond_2
    const/4 v0, 0x0

    const/4 v1, 0x1

    invoke-virtual {p0, v0, v1}, Ljava/lang/String;->substring(II)Ljava/lang/String;

    move-result-object v0

    invoke-virtual {v0}, Ljava/lang/String;->toUpperCase()Ljava/lang/String;

    move-result-object v0

    goto :goto_0
.end method

.method private static s(Ljava/lang/String;)Z
    .locals 1

    .prologue
    .line 988
    sget-object v0, Lmiui/graphics/BitmapFactory;->ti:Ljava/util/regex/Pattern;

    invoke-virtual {v0, p0}, Ljava/util/regex/Pattern;->matcher(Ljava/lang/CharSequence;)Ljava/util/regex/Matcher;

    move-result-object v0

    invoke-virtual {v0}, Ljava/util/regex/Matcher;->find()Z

    move-result v0

    return v0
.end method

.method public static saveToFile(Landroid/graphics/Bitmap;Ljava/lang/String;)Z
    .locals 1
    .annotation system Ldalvik/annotation/Throws;
        value = {
            Ljava/io/IOException;
        }
    .end annotation

    .prologue
    .line 452
    const/4 v0, 0x0

    invoke-static {p0, p1, v0}, Lmiui/graphics/BitmapFactory;->saveToFile(Landroid/graphics/Bitmap;Ljava/lang/String;Z)Z

    move-result v0

    return v0
.end method

.method public static saveToFile(Landroid/graphics/Bitmap;Ljava/lang/String;Z)Z
    .locals 3
    .annotation system Ldalvik/annotation/Throws;
        value = {
            Ljava/io/IOException;
        }
    .end annotation

    .prologue
    .line 465
    if-eqz p0, :cond_3

    .line 466
    const/4 v2, 0x0

    .line 468
    :try_start_0
    new-instance v1, Ljava/io/FileOutputStream;

    invoke-direct {v1, p1}, Ljava/io/FileOutputStream;-><init>(Ljava/lang/String;)V
    :try_end_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    .line 469
    if-eqz p2, :cond_1

    :try_start_1
    sget-object v0, Landroid/graphics/Bitmap$CompressFormat;->PNG:Landroid/graphics/Bitmap$CompressFormat;

    :goto_0
    const/16 v2, 0x64

    invoke-virtual {p0, v0, v2, v1}, Landroid/graphics/Bitmap;->compress(Landroid/graphics/Bitmap$CompressFormat;ILjava/io/OutputStream;)Z
    :try_end_1
    .catchall {:try_start_1 .. :try_end_1} :catchall_1

    .line 471
    if-eqz v1, :cond_0

    .line 472
    invoke-virtual {v1}, Ljava/io/FileOutputStream;->close()V

    .line 475
    :cond_0
    const/4 v0, 0x1

    .line 477
    :goto_1
    return v0

    .line 469
    :cond_1
    :try_start_2
    sget-object v0, Landroid/graphics/Bitmap$CompressFormat;->JPEG:Landroid/graphics/Bitmap$CompressFormat;
    :try_end_2
    .catchall {:try_start_2 .. :try_end_2} :catchall_1

    goto :goto_0

    .line 471
    :catchall_0
    move-exception v0

    move-object v1, v2

    :goto_2
    if-eqz v1, :cond_2

    .line 472
    invoke-virtual {v1}, Ljava/io/FileOutputStream;->close()V

    :cond_2
    throw v0

    .line 477
    :cond_3
    const/4 v0, 0x0

    goto :goto_1

    .line 471
    :catchall_1
    move-exception v0

    goto :goto_2
.end method

.method public static scaleBitmap(Landroid/graphics/Bitmap;II)Landroid/graphics/Bitmap;
    .locals 2

    .prologue
    .line 313
    if-nez p0, :cond_1

    .line 314
    const/4 p0, 0x0

    .line 330
    :cond_0
    :goto_0
    return-object p0

    .line 318
    :cond_1
    invoke-virtual {p0}, Landroid/graphics/Bitmap;->getWidth()I

    move-result v0

    if-ne v0, p1, :cond_2

    invoke-virtual {p0}, Landroid/graphics/Bitmap;->getHeight()I

    move-result v0

    if-eq v0, p2, :cond_0

    .line 321
    :cond_2
    sget-object v0, Landroid/graphics/Bitmap$Config;->ARGB_8888:Landroid/graphics/Bitmap$Config;

    .line 322
    invoke-virtual {p0}, Landroid/graphics/Bitmap;->getConfig()Landroid/graphics/Bitmap$Config;

    move-result-object v1

    if-eqz v1, :cond_3

    .line 323
    invoke-virtual {p0}, Landroid/graphics/Bitmap;->getConfig()Landroid/graphics/Bitmap$Config;

    move-result-object v0

    .line 325
    :cond_3
    invoke-static {p1, p2, v0}, Landroid/graphics/Bitmap;->createBitmap(IILandroid/graphics/Bitmap$Config;)Landroid/graphics/Bitmap;

    move-result-object v0

    .line 327
    invoke-static {p0, v0}, Lmiui/graphics/BitmapFactory;->scaleBitmap(Landroid/graphics/Bitmap;Landroid/graphics/Bitmap;)Landroid/graphics/Bitmap;

    move-object p0, v0

    goto :goto_0
.end method

.method public static scaleBitmap(Landroid/graphics/Bitmap;Landroid/graphics/Bitmap;)Landroid/graphics/Bitmap;
    .locals 7

    .prologue
    const/4 v2, 0x1

    const/4 v6, 0x0

    .line 342
    if-eqz p0, :cond_0

    if-nez p1, :cond_2

    .line 343
    :cond_0
    const/4 p0, 0x0

    .line 359
    :cond_1
    :goto_0
    return-object p0

    .line 345
    :cond_2
    invoke-virtual {p0}, Landroid/graphics/Bitmap;->getWidth()I

    move-result v0

    invoke-virtual {p1}, Landroid/graphics/Bitmap;->getWidth()I

    move-result v1

    if-ne v0, v1, :cond_3

    invoke-virtual {p0}, Landroid/graphics/Bitmap;->getHeight()I

    move-result v0

    invoke-virtual {p1}, Landroid/graphics/Bitmap;->getHeight()I

    move-result v1

    if-eq v0, v1, :cond_1

    .line 348
    :cond_3
    new-instance v0, Landroid/graphics/Canvas;

    invoke-direct {v0, p1}, Landroid/graphics/Canvas;-><init>(Landroid/graphics/Bitmap;)V

    .line 349
    invoke-virtual {v0, v6, v6, v6, v6}, Landroid/graphics/Canvas;->drawARGB(IIII)V

    .line 351
    new-instance v1, Landroid/graphics/Paint;

    invoke-direct {v1}, Landroid/graphics/Paint;-><init>()V

    .line 352
    invoke-virtual {v1, v2}, Landroid/graphics/Paint;->setFilterBitmap(Z)V

    .line 353
    invoke-virtual {v1, v2}, Landroid/graphics/Paint;->setAntiAlias(Z)V

    .line 354
    invoke-virtual {v1, v2}, Landroid/graphics/Paint;->setDither(Z)V

    .line 356
    new-instance v2, Landroid/graphics/Rect;

    invoke-virtual {p0}, Landroid/graphics/Bitmap;->getWidth()I

    move-result v3

    invoke-virtual {p0}, Landroid/graphics/Bitmap;->getHeight()I

    move-result v4

    invoke-direct {v2, v6, v6, v3, v4}, Landroid/graphics/Rect;-><init>(IIII)V

    new-instance v3, Landroid/graphics/Rect;

    invoke-virtual {p1}, Landroid/graphics/Bitmap;->getWidth()I

    move-result v4

    invoke-virtual {p1}, Landroid/graphics/Bitmap;->getHeight()I

    move-result v5

    invoke-direct {v3, v6, v6, v4, v5}, Landroid/graphics/Rect;-><init>(IIII)V

    invoke-virtual {v0, p0, v2, v3, v1}, Landroid/graphics/Canvas;->drawBitmap(Landroid/graphics/Bitmap;Landroid/graphics/Rect;Landroid/graphics/Rect;Landroid/graphics/Paint;)V

    move-object p0, p1

    goto :goto_0
.end method

.method private static t(Ljava/lang/String;)Ljava/lang/String;
    .locals 6

    .prologue
    const/4 v4, 0x1

    const/4 v1, 0x0

    .line 992
    .line 993
    invoke-static {p0}, Landroid/text/TextUtils;->isEmpty(Ljava/lang/CharSequence;)Z

    move-result v0

    if-eqz v0, :cond_1

    .line 994
    const/4 v0, 0x0

    .line 1021
    :cond_0
    :goto_0
    return-object v0

    :cond_1
    move-object v0, p0

    :cond_2
    move v2, v1

    move-object v3, v0

    move v0, v1

    .line 998
    :goto_1
    sget-object v5, Lmiui/graphics/BitmapFactory;->tj:[Ljava/lang/String;

    array-length v5, v5

    if-ge v0, v5, :cond_8

    .line 999
    sget-object v5, Lmiui/graphics/BitmapFactory;->tj:[Ljava/lang/String;

    aget-object v5, v5, v0

    invoke-virtual {v3, v5}, Ljava/lang/String;->endsWith(Ljava/lang/String;)Z

    move-result v5

    if-eqz v5, :cond_6

    .line 1001
    invoke-virtual {v3}, Ljava/lang/String;->length()I

    move-result v2

    sget-object v5, Lmiui/graphics/BitmapFactory;->tj:[Ljava/lang/String;

    aget-object v5, v5, v0

    invoke-virtual {v5}, Ljava/lang/String;->length()I

    move-result v5

    sub-int/2addr v2, v5

    invoke-virtual {v3, v1, v2}, Ljava/lang/String;->substring(II)Ljava/lang/String;

    move-result-object v3

    move v2, v4

    .line 1010
    :cond_3
    :goto_2
    invoke-static {v3}, Landroid/text/TextUtils;->isEmpty(Ljava/lang/CharSequence;)Z

    move-result v5

    if-eqz v5, :cond_7

    move-object v0, v3

    .line 1013
    :goto_3
    if-eqz v2, :cond_4

    invoke-static {v0}, Landroid/text/TextUtils;->isEmpty(Ljava/lang/CharSequence;)Z

    move-result v2

    if-eqz v2, :cond_2

    .line 1014
    :cond_4
    if-eqz v0, :cond_5

    .line 1015
    invoke-virtual {v0}, Ljava/lang/String;->trim()Ljava/lang/String;

    move-result-object v0

    .line 1018
    :cond_5
    invoke-static {v0}, Landroid/text/TextUtils;->isEmpty(Ljava/lang/CharSequence;)Z

    move-result v1

    if-eqz v1, :cond_0

    .line 1019
    invoke-virtual {p0}, Ljava/lang/String;->length()I

    move-result v0

    add-int/lit8 v0, v0, -0x1

    invoke-virtual {p0, v0}, Ljava/lang/String;->substring(I)Ljava/lang/String;

    move-result-object v0

    goto :goto_0

    .line 1003
    :cond_6
    invoke-virtual {v3}, Ljava/lang/String;->length()I

    move-result v5

    add-int/lit8 v5, v5, -0x1

    invoke-virtual {v3, v5}, Ljava/lang/String;->charAt(I)C

    move-result v5

    .line 1005
    invoke-static {v5}, Ljava/lang/String;->valueOf(C)Ljava/lang/String;

    move-result-object v5

    invoke-static {v5}, Lmiui/graphics/BitmapFactory;->s(Ljava/lang/String;)Z

    move-result v5

    if-nez v5, :cond_3

    .line 1007
    invoke-virtual {v3}, Ljava/lang/String;->length()I

    move-result v2

    add-int/lit8 v2, v2, -0x1

    invoke-virtual {v3, v1, v2}, Ljava/lang/String;->substring(II)Ljava/lang/String;

    move-result-object v3

    move v2, v4

    goto :goto_2

    .line 998
    :cond_7
    add-int/lit8 v0, v0, 0x1

    goto :goto_1

    :cond_8
    move-object v0, v3

    goto :goto_3
.end method
