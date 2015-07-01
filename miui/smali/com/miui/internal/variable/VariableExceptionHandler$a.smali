.class Lcom/miui/internal/variable/VariableExceptionHandler$a;
.super Ljava/lang/Object;
.source "SourceFile"


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lcom/miui/internal/variable/VariableExceptionHandler;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0xa
    name = "a"
.end annotation


# static fields
.field static final ql:Lcom/miui/internal/variable/VariableExceptionHandler;


# direct methods
.method static constructor <clinit>()V
    .locals 1

    .prologue
    .line 17
    new-instance v0, Lcom/miui/internal/variable/VariableExceptionHandler;

    invoke-direct {v0}, Lcom/miui/internal/variable/VariableExceptionHandler;-><init>()V

    sput-object v0, Lcom/miui/internal/variable/VariableExceptionHandler$a;->ql:Lcom/miui/internal/variable/VariableExceptionHandler;

    return-void
.end method

.method private constructor <init>()V
    .locals 0

    .prologue
    .line 16
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method
