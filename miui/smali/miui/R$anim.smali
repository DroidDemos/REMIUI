.class public final Lmiui/R$anim;
.super Ljava/lang/Object;
.source "SourceFile"


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lmiui/R;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x19
    name = "anim"
.end annotation


# static fields
.field public static action_bar_slide_in_bottom:I

.field public static action_bar_slide_in_top:I

.field public static action_bar_slide_out_bottom:I

.field public static action_bar_slide_out_top:I

.field public static dialog_exit:I


# direct methods
.method static constructor <clinit>()V
    .locals 1

    .prologue
    .line 12
    const v0, 0x8040001

    sput v0, Lmiui/R$anim;->action_bar_slide_in_bottom:I

    .line 13
    const v0, 0x8040002

    sput v0, Lmiui/R$anim;->action_bar_slide_in_top:I

    .line 14
    const v0, 0x8040003

    sput v0, Lmiui/R$anim;->action_bar_slide_out_bottom:I

    .line 15
    const v0, 0x8040004

    sput v0, Lmiui/R$anim;->action_bar_slide_out_top:I

    .line 16
    const/high16 v0, 0x8040000

    sput v0, Lmiui/R$anim;->dialog_exit:I

    return-void
.end method

.method public constructor <init>()V
    .locals 0

    .prologue
    .line 11
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method
