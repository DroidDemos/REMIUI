.class public Lmiui/reflect/Field;
.super Ljava/lang/Object;
.source "SourceFile"


# static fields
.field public static final BOOLEAN_SIGNATURE_PRIMITIVE:Ljava/lang/String; = "Z"

.field public static final BYTE_SIGNATURE_PRIMITIVE:Ljava/lang/String; = "B"

.field public static final CHAR_SIGNATURE_PRIMITIVE:Ljava/lang/String; = "C"

.field public static final DOUBLE_SIGNATURE_PRIMITIVE:Ljava/lang/String; = "D"

.field public static final FLOAT_SIGNATURE_PRIMITIVE:Ljava/lang/String; = "F"

.field public static final INT_SIGNATURE_PRIMITIVE:Ljava/lang/String; = "I"

.field public static final LONG_SIGNATURE_PRIMITIVE:Ljava/lang/String; = "J"

.field public static final SHORT_SIGNATURE_PRIMITIVE:Ljava/lang/String; = "S"

.field public static final VOID_SIGNATURE_PRIMITIVE:Ljava/lang/String; = "V"


# instance fields
.field private mPtr:J


# direct methods
.method private constructor <init>()V
    .locals 2

    .prologue
    .line 46
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    .line 42
    const-wide/16 v0, 0x0

    iput-wide v0, p0, Lmiui/reflect/Field;->mPtr:J

    .line 48
    return-void
.end method

.method public static of(Ljava/lang/Class;Ljava/lang/String;Ljava/lang/Class;)Lmiui/reflect/Field;
    .locals 1
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Ljava/lang/Class",
            "<*>;",
            "Ljava/lang/String;",
            "Ljava/lang/Class",
            "<*>;)",
            "Lmiui/reflect/Field;"
        }
    .end annotation

    .annotation system Ldalvik/annotation/Throws;
        value = {
            Lmiui/reflect/NoSuchFieldException;
        }
    .end annotation

    .prologue
    .line 71
    invoke-static {p2}, Lmiui/reflect/ReflectUtils;->getSignature(Ljava/lang/Class;)Ljava/lang/String;

    move-result-object v0

    invoke-static {p0, p1, v0}, Lcom/miui/internal/os/Native;->getField(Ljava/lang/Class;Ljava/lang/String;Ljava/lang/String;)Lmiui/reflect/Field;

    move-result-object v0

    return-object v0
.end method

.method public static of(Ljava/lang/Class;Ljava/lang/String;Ljava/lang/String;)Lmiui/reflect/Field;
    .locals 1
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Ljava/lang/Class",
            "<*>;",
            "Ljava/lang/String;",
            "Ljava/lang/String;",
            ")",
            "Lmiui/reflect/Field;"
        }
    .end annotation

    .annotation system Ldalvik/annotation/Throws;
        value = {
            Lmiui/reflect/NoSuchFieldException;
        }
    .end annotation

    .prologue
    .line 59
    invoke-static {p0, p1, p2}, Lcom/miui/internal/os/Native;->getField(Ljava/lang/Class;Ljava/lang/String;Ljava/lang/String;)Lmiui/reflect/Field;

    move-result-object v0

    return-object v0
.end method

.method public static of(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Lmiui/reflect/Field;
    .locals 1
    .annotation system Ldalvik/annotation/Throws;
        value = {
            Lmiui/reflect/NoSuchFieldException;,
            Lmiui/reflect/NoSuchClassException;
        }
    .end annotation

    .prologue
    .line 84
    invoke-static {p0, p1, p2}, Lcom/miui/internal/os/Native;->getField(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Lmiui/reflect/Field;

    move-result-object v0

    return-object v0
.end method

.method public static of(Ljava/lang/reflect/Field;)Lmiui/reflect/Field;
    .locals 1

    .prologue
    .line 93
    invoke-static {p0}, Lcom/miui/internal/os/Native;->getField(Ljava/lang/reflect/Field;)Lmiui/reflect/Field;

    move-result-object v0

    return-object v0
.end method


# virtual methods
.method public get(Ljava/lang/Object;)Ljava/lang/Object;
    .locals 1
    .annotation system Ldalvik/annotation/Throws;
        value = {
            Lmiui/reflect/IllegalArgumentException;
        }
    .end annotation

    .prologue
    .line 518
    invoke-static {p0, p1}, Lcom/miui/internal/os/Native;->getObjectFieldValue(Lmiui/reflect/Field;Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object v0

    return-object v0
.end method

.method public getBoolean(Ljava/lang/Object;)Z
    .locals 1
    .annotation system Ldalvik/annotation/Throws;
        value = {
            Lmiui/reflect/IllegalArgumentException;
        }
    .end annotation

    .prologue
    .line 347
    invoke-static {p0, p1}, Lcom/miui/internal/os/Native;->getBooleanFieldValue(Lmiui/reflect/Field;Ljava/lang/Object;)Z

    move-result v0

    return v0
.end method

.method public getByte(Ljava/lang/Object;)B
    .locals 1
    .annotation system Ldalvik/annotation/Throws;
        value = {
            Lmiui/reflect/IllegalArgumentException;
        }
    .end annotation

    .prologue
    .line 368
    invoke-static {p0, p1}, Lcom/miui/internal/os/Native;->getByteFieldValue(Lmiui/reflect/Field;Ljava/lang/Object;)B

    move-result v0

    return v0
.end method

.method public getChar(Ljava/lang/Object;)C
    .locals 1
    .annotation system Ldalvik/annotation/Throws;
        value = {
            Lmiui/reflect/IllegalArgumentException;
        }
    .end annotation

    .prologue
    .line 389
    invoke-static {p0, p1}, Lcom/miui/internal/os/Native;->getCharFieldValue(Lmiui/reflect/Field;Ljava/lang/Object;)C

    move-result v0

    return v0
.end method

.method public getDouble(Ljava/lang/Object;)D
    .locals 2
    .annotation system Ldalvik/annotation/Throws;
        value = {
            Lmiui/reflect/IllegalArgumentException;
        }
    .end annotation

    .prologue
    .line 494
    invoke-static {p0, p1}, Lcom/miui/internal/os/Native;->getDoubleFieldValue(Lmiui/reflect/Field;Ljava/lang/Object;)D

    move-result-wide v0

    return-wide v0
.end method

.method public getFloat(Ljava/lang/Object;)F
    .locals 1
    .annotation system Ldalvik/annotation/Throws;
        value = {
            Lmiui/reflect/IllegalArgumentException;
        }
    .end annotation

    .prologue
    .line 473
    invoke-static {p0, p1}, Lcom/miui/internal/os/Native;->getFloatFieldValue(Lmiui/reflect/Field;Ljava/lang/Object;)F

    move-result v0

    return v0
.end method

.method public getInt(Ljava/lang/Object;)I
    .locals 1
    .annotation system Ldalvik/annotation/Throws;
        value = {
            Lmiui/reflect/IllegalArgumentException;
        }
    .end annotation

    .prologue
    .line 431
    invoke-static {p0, p1}, Lcom/miui/internal/os/Native;->getIntFieldValue(Lmiui/reflect/Field;Ljava/lang/Object;)I

    move-result v0

    return v0
.end method

.method public getLong(Ljava/lang/Object;)J
    .locals 2
    .annotation system Ldalvik/annotation/Throws;
        value = {
            Lmiui/reflect/IllegalArgumentException;
        }
    .end annotation

    .prologue
    .line 452
    invoke-static {p0, p1}, Lcom/miui/internal/os/Native;->getLongFieldValue(Lmiui/reflect/Field;Ljava/lang/Object;)J

    move-result-wide v0

    return-wide v0
.end method

.method public getShort(Ljava/lang/Object;)S
    .locals 1
    .annotation system Ldalvik/annotation/Throws;
        value = {
            Lmiui/reflect/IllegalArgumentException;
        }
    .end annotation

    .prologue
    .line 410
    invoke-static {p0, p1}, Lcom/miui/internal/os/Native;->getShortFieldValue(Lmiui/reflect/Field;Ljava/lang/Object;)S

    move-result v0

    return v0
.end method

.method public set(Ljava/lang/Object;B)V
    .locals 0
    .annotation system Ldalvik/annotation/Throws;
        value = {
            Lmiui/reflect/IllegalArgumentException;
        }
    .end annotation

    .prologue
    .line 151
    invoke-static {p0, p1, p2}, Lcom/miui/internal/os/Native;->setFieldValue(Lmiui/reflect/Field;Ljava/lang/Object;B)V

    .line 152
    return-void
.end method

.method public set(Ljava/lang/Object;C)V
    .locals 0
    .annotation system Ldalvik/annotation/Throws;
        value = {
            Lmiui/reflect/IllegalArgumentException;
        }
    .end annotation

    .prologue
    .line 176
    invoke-static {p0, p1, p2}, Lcom/miui/internal/os/Native;->setFieldValue(Lmiui/reflect/Field;Ljava/lang/Object;C)V

    .line 177
    return-void
.end method

.method public set(Ljava/lang/Object;D)V
    .locals 0
    .annotation system Ldalvik/annotation/Throws;
        value = {
            Lmiui/reflect/IllegalArgumentException;
        }
    .end annotation

    .prologue
    .line 301
    invoke-static {p0, p1, p2, p3}, Lcom/miui/internal/os/Native;->setFieldValue(Lmiui/reflect/Field;Ljava/lang/Object;D)V

    .line 302
    return-void
.end method

.method public set(Ljava/lang/Object;F)V
    .locals 0
    .annotation system Ldalvik/annotation/Throws;
        value = {
            Lmiui/reflect/IllegalArgumentException;
        }
    .end annotation

    .prologue
    .line 276
    invoke-static {p0, p1, p2}, Lcom/miui/internal/os/Native;->setFieldValue(Lmiui/reflect/Field;Ljava/lang/Object;F)V

    .line 277
    return-void
.end method

.method public set(Ljava/lang/Object;I)V
    .locals 0
    .annotation system Ldalvik/annotation/Throws;
        value = {
            Lmiui/reflect/IllegalArgumentException;
        }
    .end annotation

    .prologue
    .line 226
    invoke-static {p0, p1, p2}, Lcom/miui/internal/os/Native;->setFieldValue(Lmiui/reflect/Field;Ljava/lang/Object;I)V

    .line 227
    return-void
.end method

.method public set(Ljava/lang/Object;J)V
    .locals 0
    .annotation system Ldalvik/annotation/Throws;
        value = {
            Lmiui/reflect/IllegalArgumentException;
        }
    .end annotation

    .prologue
    .line 251
    invoke-static {p0, p1, p2, p3}, Lcom/miui/internal/os/Native;->setFieldValue(Lmiui/reflect/Field;Ljava/lang/Object;J)V

    .line 252
    return-void
.end method

.method public set(Ljava/lang/Object;Ljava/lang/Object;)V
    .locals 0
    .annotation system Ldalvik/annotation/Throws;
        value = {
            Lmiui/reflect/IllegalArgumentException;
        }
    .end annotation

    .prologue
    .line 326
    invoke-static {p0, p1, p2}, Lcom/miui/internal/os/Native;->setFieldValue(Lmiui/reflect/Field;Ljava/lang/Object;Ljava/lang/Object;)V

    .line 327
    return-void
.end method

.method public set(Ljava/lang/Object;S)V
    .locals 0
    .annotation system Ldalvik/annotation/Throws;
        value = {
            Lmiui/reflect/IllegalArgumentException;
        }
    .end annotation

    .prologue
    .line 201
    invoke-static {p0, p1, p2}, Lcom/miui/internal/os/Native;->setFieldValue(Lmiui/reflect/Field;Ljava/lang/Object;S)V

    .line 202
    return-void
.end method

.method public set(Ljava/lang/Object;Z)V
    .locals 0
    .annotation system Ldalvik/annotation/Throws;
        value = {
            Lmiui/reflect/IllegalArgumentException;
        }
    .end annotation

    .prologue
    .line 126
    invoke-static {p0, p1, p2}, Lcom/miui/internal/os/Native;->setFieldValue(Lmiui/reflect/Field;Ljava/lang/Object;Z)V

    .line 127
    return-void
.end method

.method public toReflect()Ljava/lang/reflect/Field;
    .locals 1

    .prologue
    .line 101
    invoke-static {p0}, Lcom/miui/internal/os/Native;->getReflectField(Lmiui/reflect/Field;)Ljava/lang/reflect/Field;

    move-result-object v0

    return-object v0
.end method
