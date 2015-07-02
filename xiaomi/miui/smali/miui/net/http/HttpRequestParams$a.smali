.class Lmiui/net/http/HttpRequestParams$a;
.super Ljava/lang/Object;
.source "SourceFile"


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lmiui/net/http/HttpRequestParams;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x8
    name = "a"
.end annotation


# instance fields
.field public Dx:Ljava/io/InputStream;

.field public Dy:J

.field public Dz:Ljava/lang/String;

.field public contentType:Ljava/lang/String;


# direct methods
.method public constructor <init>(Ljava/io/InputStream;JLjava/lang/String;Ljava/lang/String;)V
    .locals 0

    .prologue
    .line 300
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    .line 301
    iput-object p1, p0, Lmiui/net/http/HttpRequestParams$a;->Dx:Ljava/io/InputStream;

    .line 302
    iput-wide p2, p0, Lmiui/net/http/HttpRequestParams$a;->Dy:J

    .line 303
    if-nez p4, :cond_0

    const-string p4, "nofilename"

    :cond_0
    iput-object p4, p0, Lmiui/net/http/HttpRequestParams$a;->Dz:Ljava/lang/String;

    .line 304
    iput-object p5, p0, Lmiui/net/http/HttpRequestParams$a;->contentType:Ljava/lang/String;

    .line 305
    return-void
.end method
