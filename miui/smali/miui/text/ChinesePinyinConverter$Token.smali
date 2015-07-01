.class public Lmiui/text/ChinesePinyinConverter$Token;
.super Ljava/lang/Object;
.source "SourceFile"


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lmiui/text/ChinesePinyinConverter;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x9
    name = "Token"
.end annotation


# static fields
.field private static final JV:C = ' '

.field public static final LATIN:I = 0x1

.field public static final PINYIN:I = 0x2

.field public static final UNKNOWN:I = 0x3


# instance fields
.field public polyPhones:[Ljava/lang/String;

.field public source:Ljava/lang/String;

.field public target:Ljava/lang/String;

.field public type:I


# direct methods
.method public constructor <init>()V
    .locals 0

    .prologue
    .line 102
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    .line 103
    return-void
.end method

.method public constructor <init>(ILjava/lang/String;Ljava/lang/String;)V
    .locals 0

    .prologue
    .line 105
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    .line 106
    iput p1, p0, Lmiui/text/ChinesePinyinConverter$Token;->type:I

    .line 107
    iput-object p2, p0, Lmiui/text/ChinesePinyinConverter$Token;->source:Ljava/lang/String;

    .line 108
    iput-object p3, p0, Lmiui/text/ChinesePinyinConverter$Token;->target:Ljava/lang/String;

    .line 109
    return-void
.end method
