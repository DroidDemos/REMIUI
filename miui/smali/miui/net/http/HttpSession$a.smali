.class Lmiui/net/http/HttpSession$a;
.super Ljava/io/FilterInputStream;
.source "SourceFile"


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lmiui/net/http/HttpSession;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0xa
    name = "a"
.end annotation


# instance fields
.field private t:Lmiui/net/http/HttpSession$ProgressListener;

.field private u:J

.field private v:J

.field private w:J

.field private x:Lorg/apache/http/HttpEntity;


# direct methods
.method public constructor <init>(Lorg/apache/http/HttpEntity;Ljava/lang/String;Lmiui/net/http/HttpSession$ProgressListener;)V
    .locals 6
    .annotation system Ldalvik/annotation/Throws;
        value = {
            Ljava/io/IOException;
        }
    .end annotation

    .prologue
    const-wide/16 v4, 0x0

    const/4 v3, 0x3

    .line 677
    invoke-interface {p1}, Lorg/apache/http/HttpEntity;->getContent()Ljava/io/InputStream;

    move-result-object v0

    invoke-direct {p0, v0}, Ljava/io/FilterInputStream;-><init>(Ljava/io/InputStream;)V

    .line 678
    iput-object p1, p0, Lmiui/net/http/HttpSession$a;->x:Lorg/apache/http/HttpEntity;

    .line 679
    invoke-interface {p1}, Lorg/apache/http/HttpEntity;->getContentLength()J

    move-result-wide v0

    iput-wide v0, p0, Lmiui/net/http/HttpSession$a;->u:J

    .line 680
    iput-object p3, p0, Lmiui/net/http/HttpSession$a;->t:Lmiui/net/http/HttpSession$ProgressListener;

    .line 681
    iput-wide v4, p0, Lmiui/net/http/HttpSession$a;->v:J

    .line 682
    iput-wide v4, p0, Lmiui/net/http/HttpSession$a;->w:J

    .line 684
    if-eqz p2, :cond_0

    invoke-virtual {p2}, Ljava/lang/String;->length()I

    move-result v0

    if-lez v0, :cond_0

    .line 685
    const-string v0, "bytes\\s+(\\d+)-(\\d+)/(\\d+)"

    invoke-static {v0}, Ljava/util/regex/Pattern;->compile(Ljava/lang/String;)Ljava/util/regex/Pattern;

    move-result-object v0

    .line 686
    invoke-virtual {v0, p2}, Ljava/util/regex/Pattern;->matcher(Ljava/lang/CharSequence;)Ljava/util/regex/Matcher;

    move-result-object v0

    .line 687
    invoke-virtual {v0}, Ljava/util/regex/Matcher;->matches()Z

    move-result v1

    if-eqz v1, :cond_0

    invoke-virtual {v0}, Ljava/util/regex/Matcher;->groupCount()I

    move-result v1

    if-ne v1, v3, :cond_0

    .line 688
    const/4 v1, 0x1

    invoke-virtual {v0, v1}, Ljava/util/regex/Matcher;->group(I)Ljava/lang/String;

    move-result-object v1

    invoke-static {v1}, Ljava/lang/Long;->parseLong(Ljava/lang/String;)J

    move-result-wide v1

    iput-wide v1, p0, Lmiui/net/http/HttpSession$a;->v:J

    .line 689
    invoke-virtual {v0, v3}, Ljava/util/regex/Matcher;->group(I)Ljava/lang/String;

    move-result-object v0

    invoke-static {v0}, Ljava/lang/Long;->parseLong(Ljava/lang/String;)J

    move-result-wide v0

    iput-wide v0, p0, Lmiui/net/http/HttpSession$a;->u:J

    .line 692
    :cond_0
    return-void
.end method

.method private b(I)V
    .locals 5

    .prologue
    .line 726
    iget-wide v0, p0, Lmiui/net/http/HttpSession$a;->u:J

    const-wide/16 v2, 0x0

    cmp-long v0, v0, v2

    if-lez v0, :cond_1

    iget-object v0, p0, Lmiui/net/http/HttpSession$a;->t:Lmiui/net/http/HttpSession$ProgressListener;

    if-eqz v0, :cond_1

    .line 727
    iget-wide v0, p0, Lmiui/net/http/HttpSession$a;->v:J

    const-wide/16 v2, 0xa

    mul-long/2addr v0, v2

    iget-wide v2, p0, Lmiui/net/http/HttpSession$a;->u:J

    div-long/2addr v0, v2

    .line 728
    iget-wide v2, p0, Lmiui/net/http/HttpSession$a;->w:J

    cmp-long v2, v2, v0

    if-nez v2, :cond_0

    const/16 v2, 0x400

    if-le p1, v2, :cond_1

    .line 729
    :cond_0
    iput-wide v0, p0, Lmiui/net/http/HttpSession$a;->w:J

    .line 730
    iget-object v0, p0, Lmiui/net/http/HttpSession$a;->t:Lmiui/net/http/HttpSession$ProgressListener;

    iget-wide v1, p0, Lmiui/net/http/HttpSession$a;->u:J

    iget-wide v3, p0, Lmiui/net/http/HttpSession$a;->v:J

    invoke-interface {v0, v1, v2, v3, v4}, Lmiui/net/http/HttpSession$ProgressListener;->onProgress(JJ)V

    .line 733
    :cond_1
    return-void
.end method


# virtual methods
.method public close()V
    .locals 1
    .annotation system Ldalvik/annotation/Throws;
        value = {
            Ljava/io/IOException;
        }
    .end annotation

    .prologue
    .line 721
    iget-object v0, p0, Lmiui/net/http/HttpSession$a;->x:Lorg/apache/http/HttpEntity;

    invoke-interface {v0}, Lorg/apache/http/HttpEntity;->consumeContent()V

    .line 722
    invoke-super {p0}, Ljava/io/FilterInputStream;->close()V

    .line 723
    return-void
.end method

.method public read()I
    .locals 5
    .annotation system Ldalvik/annotation/Throws;
        value = {
            Ljava/io/IOException;
        }
    .end annotation

    .prologue
    .line 711
    invoke-super {p0}, Ljava/io/FilterInputStream;->read()I

    move-result v0

    .line 712
    if-lez v0, :cond_0

    .line 713
    iget-wide v1, p0, Lmiui/net/http/HttpSession$a;->v:J

    const-wide/16 v3, 0x1

    add-long/2addr v1, v3

    iput-wide v1, p0, Lmiui/net/http/HttpSession$a;->v:J

    .line 714
    const/4 v1, 0x1

    invoke-direct {p0, v1}, Lmiui/net/http/HttpSession$a;->b(I)V

    .line 716
    :cond_0
    return v0
.end method

.method public read([B)I
    .locals 2
    .annotation system Ldalvik/annotation/Throws;
        value = {
            Ljava/io/IOException;
        }
    .end annotation

    .prologue
    .line 696
    const/4 v0, 0x0

    array-length v1, p1

    invoke-virtual {p0, p1, v0, v1}, Lmiui/net/http/HttpSession$a;->read([BII)I

    move-result v0

    return v0
.end method

.method public read([BII)I
    .locals 5
    .annotation system Ldalvik/annotation/Throws;
        value = {
            Ljava/io/IOException;
        }
    .end annotation

    .prologue
    .line 701
    invoke-super {p0, p1, p2, p3}, Ljava/io/FilterInputStream;->read([BII)I

    move-result v0

    .line 702
    if-lez v0, :cond_0

    .line 703
    iget-wide v1, p0, Lmiui/net/http/HttpSession$a;->v:J

    int-to-long v3, v0

    add-long/2addr v1, v3

    iput-wide v1, p0, Lmiui/net/http/HttpSession$a;->v:J

    .line 704
    invoke-direct {p0, v0}, Lmiui/net/http/HttpSession$a;->b(I)V

    .line 706
    :cond_0
    return v0
.end method
