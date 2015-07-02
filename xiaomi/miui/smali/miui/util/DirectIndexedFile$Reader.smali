.class public Lmiui/util/DirectIndexedFile$Reader;
.super Ljava/lang/Object;
.source "SourceFile"


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lmiui/util/DirectIndexedFile;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x9
    name = "Reader"
.end annotation

.annotation system Ldalvik/annotation/MemberClasses;
    value = {
        Lmiui/util/DirectIndexedFile$Reader$a;
    }
.end annotation


# instance fields
.field private RY:Lmiui/util/DirectIndexedFile$c;

.field private RZ:Lmiui/util/DirectIndexedFile$d;

.field private Sa:[Lmiui/util/DirectIndexedFile$Reader$a;


# direct methods
.method private constructor <init>(Ljava/io/InputStream;)V
    .locals 1
    .annotation system Ldalvik/annotation/Throws;
        value = {
            Ljava/io/IOException;
        }
    .end annotation

    .prologue
    .line 850
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    .line 851
    new-instance v0, Lmiui/util/DirectIndexedFile$e;

    invoke-direct {v0, p1}, Lmiui/util/DirectIndexedFile$e;-><init>(Ljava/io/InputStream;)V

    iput-object v0, p0, Lmiui/util/DirectIndexedFile$Reader;->RY:Lmiui/util/DirectIndexedFile$c;

    .line 852
    const-string v0, "assets"

    invoke-direct {p0, v0}, Lmiui/util/DirectIndexedFile$Reader;->H(Ljava/lang/String;)V

    .line 853
    return-void
.end method

.method synthetic constructor <init>(Ljava/io/InputStream;Lmiui/util/DirectIndexedFile$1;)V
    .locals 0
    .annotation system Ldalvik/annotation/Throws;
        value = {
            Ljava/io/IOException;
        }
    .end annotation

    .prologue
    .line 838
    invoke-direct {p0, p1}, Lmiui/util/DirectIndexedFile$Reader;-><init>(Ljava/io/InputStream;)V

    return-void
.end method

.method private constructor <init>(Ljava/lang/String;)V
    .locals 3
    .annotation system Ldalvik/annotation/Throws;
        value = {
            Ljava/io/IOException;
        }
    .end annotation

    .prologue
    .line 855
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    .line 856
    new-instance v0, Lmiui/util/DirectIndexedFile$f;

    new-instance v1, Ljava/io/RandomAccessFile;

    const-string v2, "r"

    invoke-direct {v1, p1, v2}, Ljava/io/RandomAccessFile;-><init>(Ljava/lang/String;Ljava/lang/String;)V

    invoke-direct {v0, v1}, Lmiui/util/DirectIndexedFile$f;-><init>(Ljava/io/RandomAccessFile;)V

    iput-object v0, p0, Lmiui/util/DirectIndexedFile$Reader;->RY:Lmiui/util/DirectIndexedFile$c;

    .line 857
    invoke-direct {p0, p1}, Lmiui/util/DirectIndexedFile$Reader;->H(Ljava/lang/String;)V

    .line 858
    return-void
.end method

.method synthetic constructor <init>(Ljava/lang/String;Lmiui/util/DirectIndexedFile$1;)V
    .locals 0
    .annotation system Ldalvik/annotation/Throws;
        value = {
            Ljava/io/IOException;
        }
    .end annotation

    .prologue
    .line 838
    invoke-direct {p0, p1}, Lmiui/util/DirectIndexedFile$Reader;-><init>(Ljava/lang/String;)V

    return-void
.end method

.method private H(Ljava/lang/String;)V
    .locals 7
    .annotation system Ldalvik/annotation/Throws;
        value = {
            Ljava/io/IOException;
        }
    .end annotation

    .prologue
    const/4 v1, 0x0

    .line 861
    invoke-static {}, Ljava/lang/System;->currentTimeMillis()J

    .line 863
    :try_start_0
    iget-object v0, p0, Lmiui/util/DirectIndexedFile$Reader;->RY:Lmiui/util/DirectIndexedFile$c;

    const-wide/16 v2, 0x0

    invoke-interface {v0, v2, v3}, Lmiui/util/DirectIndexedFile$c;->seek(J)V

    .line 864
    iget-object v0, p0, Lmiui/util/DirectIndexedFile$Reader;->RY:Lmiui/util/DirectIndexedFile$c;

    invoke-static {v0}, Lmiui/util/DirectIndexedFile$d;->f(Ljava/io/DataInput;)Lmiui/util/DirectIndexedFile$d;

    move-result-object v0

    iput-object v0, p0, Lmiui/util/DirectIndexedFile$Reader;->RZ:Lmiui/util/DirectIndexedFile$d;

    .line 866
    iget-object v0, p0, Lmiui/util/DirectIndexedFile$Reader;->RZ:Lmiui/util/DirectIndexedFile$d;

    invoke-static {v0}, Lmiui/util/DirectIndexedFile$d;->a(Lmiui/util/DirectIndexedFile$d;)[Lmiui/util/DirectIndexedFile$b;

    move-result-object v0

    array-length v0, v0

    new-array v0, v0, [Lmiui/util/DirectIndexedFile$Reader$a;

    iput-object v0, p0, Lmiui/util/DirectIndexedFile$Reader;->Sa:[Lmiui/util/DirectIndexedFile$Reader$a;

    move v2, v1

    .line 867
    :goto_0
    iget-object v0, p0, Lmiui/util/DirectIndexedFile$Reader;->RZ:Lmiui/util/DirectIndexedFile$d;

    invoke-static {v0}, Lmiui/util/DirectIndexedFile$d;->a(Lmiui/util/DirectIndexedFile$d;)[Lmiui/util/DirectIndexedFile$b;

    move-result-object v0

    array-length v0, v0

    if-ge v2, v0, :cond_3

    .line 868
    iget-object v0, p0, Lmiui/util/DirectIndexedFile$Reader;->Sa:[Lmiui/util/DirectIndexedFile$Reader$a;

    new-instance v3, Lmiui/util/DirectIndexedFile$Reader$a;

    const/4 v4, 0x0

    invoke-direct {v3, v4}, Lmiui/util/DirectIndexedFile$Reader$a;-><init>(Lmiui/util/DirectIndexedFile$1;)V

    aput-object v3, v0, v2

    .line 869
    iget-object v0, p0, Lmiui/util/DirectIndexedFile$Reader;->RY:Lmiui/util/DirectIndexedFile$c;

    iget-object v3, p0, Lmiui/util/DirectIndexedFile$Reader;->RZ:Lmiui/util/DirectIndexedFile$d;

    invoke-static {v3}, Lmiui/util/DirectIndexedFile$d;->a(Lmiui/util/DirectIndexedFile$d;)[Lmiui/util/DirectIndexedFile$b;

    move-result-object v3

    aget-object v3, v3, v2

    invoke-static {v3}, Lmiui/util/DirectIndexedFile$b;->a(Lmiui/util/DirectIndexedFile$b;)J

    move-result-wide v3

    invoke-interface {v0, v3, v4}, Lmiui/util/DirectIndexedFile$c;->seek(J)V

    .line 870
    iget-object v0, p0, Lmiui/util/DirectIndexedFile$Reader;->RY:Lmiui/util/DirectIndexedFile$c;

    invoke-interface {v0}, Lmiui/util/DirectIndexedFile$c;->readInt()I

    move-result v3

    .line 871
    iget-object v0, p0, Lmiui/util/DirectIndexedFile$Reader;->Sa:[Lmiui/util/DirectIndexedFile$Reader$a;

    aget-object v0, v0, v2

    new-array v4, v3, [Lmiui/util/DirectIndexedFile$a;

    invoke-static {v0, v4}, Lmiui/util/DirectIndexedFile$Reader$a;->a(Lmiui/util/DirectIndexedFile$Reader$a;[Lmiui/util/DirectIndexedFile$a;)[Lmiui/util/DirectIndexedFile$a;

    move v0, v1

    .line 872
    :goto_1
    if-ge v0, v3, :cond_0

    .line 873
    iget-object v4, p0, Lmiui/util/DirectIndexedFile$Reader;->Sa:[Lmiui/util/DirectIndexedFile$Reader$a;

    aget-object v4, v4, v2

    invoke-static {v4}, Lmiui/util/DirectIndexedFile$Reader$a;->a(Lmiui/util/DirectIndexedFile$Reader$a;)[Lmiui/util/DirectIndexedFile$a;

    move-result-object v4

    iget-object v5, p0, Lmiui/util/DirectIndexedFile$Reader;->RY:Lmiui/util/DirectIndexedFile$c;

    invoke-static {v5}, Lmiui/util/DirectIndexedFile$a;->b(Ljava/io/DataInput;)Lmiui/util/DirectIndexedFile$a;

    move-result-object v5

    aput-object v5, v4, v0

    .line 872
    add-int/lit8 v0, v0, 0x1

    goto :goto_1

    .line 876
    :cond_0
    iget-object v0, p0, Lmiui/util/DirectIndexedFile$Reader;->RY:Lmiui/util/DirectIndexedFile$c;

    iget-object v3, p0, Lmiui/util/DirectIndexedFile$Reader;->RZ:Lmiui/util/DirectIndexedFile$d;

    invoke-static {v3}, Lmiui/util/DirectIndexedFile$d;->a(Lmiui/util/DirectIndexedFile$d;)[Lmiui/util/DirectIndexedFile$b;

    move-result-object v3

    aget-object v3, v3, v2

    invoke-static {v3}, Lmiui/util/DirectIndexedFile$b;->b(Lmiui/util/DirectIndexedFile$b;)J

    move-result-wide v3

    invoke-interface {v0, v3, v4}, Lmiui/util/DirectIndexedFile$c;->seek(J)V

    .line 877
    iget-object v0, p0, Lmiui/util/DirectIndexedFile$Reader;->RY:Lmiui/util/DirectIndexedFile$c;

    invoke-interface {v0}, Lmiui/util/DirectIndexedFile$c;->readInt()I

    move-result v3

    .line 878
    iget-object v0, p0, Lmiui/util/DirectIndexedFile$Reader;->Sa:[Lmiui/util/DirectIndexedFile$Reader$a;

    aget-object v0, v0, v2

    const/4 v4, 0x0

    invoke-static {v0, v4}, Lmiui/util/DirectIndexedFile$Reader$a;->a(Lmiui/util/DirectIndexedFile$Reader$a;I)I

    .line 879
    iget-object v0, p0, Lmiui/util/DirectIndexedFile$Reader;->Sa:[Lmiui/util/DirectIndexedFile$Reader$a;

    aget-object v0, v0, v2

    new-array v4, v3, [Lmiui/util/DirectIndexedFile$DataItemDescriptor;

    invoke-static {v0, v4}, Lmiui/util/DirectIndexedFile$Reader$a;->a(Lmiui/util/DirectIndexedFile$Reader$a;[Lmiui/util/DirectIndexedFile$DataItemDescriptor;)[Lmiui/util/DirectIndexedFile$DataItemDescriptor;

    move v0, v1

    .line 880
    :goto_2
    if-ge v0, v3, :cond_1

    .line 881
    iget-object v4, p0, Lmiui/util/DirectIndexedFile$Reader;->Sa:[Lmiui/util/DirectIndexedFile$Reader$a;

    aget-object v4, v4, v2

    invoke-static {v4}, Lmiui/util/DirectIndexedFile$Reader$a;->b(Lmiui/util/DirectIndexedFile$Reader$a;)[Lmiui/util/DirectIndexedFile$DataItemDescriptor;

    move-result-object v4

    iget-object v5, p0, Lmiui/util/DirectIndexedFile$Reader;->RY:Lmiui/util/DirectIndexedFile$c;

    invoke-static {v5}, Lmiui/util/DirectIndexedFile$DataItemDescriptor;->h(Ljava/io/DataInput;)Lmiui/util/DirectIndexedFile$DataItemDescriptor;

    move-result-object v5

    aput-object v5, v4, v0

    .line 882
    iget-object v4, p0, Lmiui/util/DirectIndexedFile$Reader;->Sa:[Lmiui/util/DirectIndexedFile$Reader$a;

    aget-object v4, v4, v2

    iget-object v5, p0, Lmiui/util/DirectIndexedFile$Reader;->Sa:[Lmiui/util/DirectIndexedFile$Reader$a;

    aget-object v5, v5, v2

    invoke-static {v5}, Lmiui/util/DirectIndexedFile$Reader$a;->b(Lmiui/util/DirectIndexedFile$Reader$a;)[Lmiui/util/DirectIndexedFile$DataItemDescriptor;

    move-result-object v5

    aget-object v5, v5, v0

    invoke-static {v5}, Lmiui/util/DirectIndexedFile$DataItemDescriptor;->a(Lmiui/util/DirectIndexedFile$DataItemDescriptor;)B

    move-result v5

    invoke-static {v4, v5}, Lmiui/util/DirectIndexedFile$Reader$a;->b(Lmiui/util/DirectIndexedFile$Reader$a;I)I

    .line 880
    add-int/lit8 v0, v0, 0x1

    goto :goto_2

    .line 885
    :cond_1
    iget-object v0, p0, Lmiui/util/DirectIndexedFile$Reader;->Sa:[Lmiui/util/DirectIndexedFile$Reader$a;

    aget-object v0, v0, v2

    new-array v4, v3, [[Ljava/lang/Object;

    invoke-static {v0, v4}, Lmiui/util/DirectIndexedFile$Reader$a;->a(Lmiui/util/DirectIndexedFile$Reader$a;[[Ljava/lang/Object;)[[Ljava/lang/Object;

    move v0, v1

    .line 886
    :goto_3
    if-ge v0, v3, :cond_2

    .line 887
    iget-object v4, p0, Lmiui/util/DirectIndexedFile$Reader;->RY:Lmiui/util/DirectIndexedFile$c;

    iget-object v5, p0, Lmiui/util/DirectIndexedFile$Reader;->Sa:[Lmiui/util/DirectIndexedFile$Reader$a;

    aget-object v5, v5, v2

    invoke-static {v5}, Lmiui/util/DirectIndexedFile$Reader$a;->b(Lmiui/util/DirectIndexedFile$Reader$a;)[Lmiui/util/DirectIndexedFile$DataItemDescriptor;

    move-result-object v5

    aget-object v5, v5, v0

    invoke-static {v5}, Lmiui/util/DirectIndexedFile$DataItemDescriptor;->b(Lmiui/util/DirectIndexedFile$DataItemDescriptor;)J

    move-result-wide v5

    invoke-interface {v4, v5, v6}, Lmiui/util/DirectIndexedFile$c;->seek(J)V

    .line 888
    iget-object v4, p0, Lmiui/util/DirectIndexedFile$Reader;->Sa:[Lmiui/util/DirectIndexedFile$Reader$a;

    aget-object v4, v4, v2

    invoke-static {v4}, Lmiui/util/DirectIndexedFile$Reader$a;->c(Lmiui/util/DirectIndexedFile$Reader$a;)[[Ljava/lang/Object;

    move-result-object v4

    iget-object v5, p0, Lmiui/util/DirectIndexedFile$Reader;->Sa:[Lmiui/util/DirectIndexedFile$Reader$a;

    aget-object v5, v5, v2

    invoke-static {v5}, Lmiui/util/DirectIndexedFile$Reader$a;->b(Lmiui/util/DirectIndexedFile$Reader$a;)[Lmiui/util/DirectIndexedFile$DataItemDescriptor;

    move-result-object v5

    aget-object v5, v5, v0

    iget-object v6, p0, Lmiui/util/DirectIndexedFile$Reader;->RY:Lmiui/util/DirectIndexedFile$c;

    invoke-static {v5, v6}, Lmiui/util/DirectIndexedFile$DataItemDescriptor;->a(Lmiui/util/DirectIndexedFile$DataItemDescriptor;Lmiui/util/DirectIndexedFile$c;)[Ljava/lang/Object;

    move-result-object v5

    aput-object v5, v4, v0
    :try_end_0
    .catch Ljava/io/IOException; {:try_start_0 .. :try_end_0} :catch_0

    .line 886
    add-int/lit8 v0, v0, 0x1

    goto :goto_3

    .line 867
    :cond_2
    add-int/lit8 v0, v2, 0x1

    move v2, v0

    goto/16 :goto_0

    .line 891
    :catch_0
    move-exception v0

    .line 892
    invoke-virtual {p0}, Lmiui/util/DirectIndexedFile$Reader;->close()V

    .line 893
    throw v0

    .line 899
    :cond_3
    return-void
.end method

.method private j(III)Ljava/lang/Object;
    .locals 5
    .annotation system Ldalvik/annotation/Throws;
        value = {
            Ljava/io/IOException;
        }
    .end annotation

    .prologue
    .line 1078
    iget-object v0, p0, Lmiui/util/DirectIndexedFile$Reader;->Sa:[Lmiui/util/DirectIndexedFile$Reader$a;

    aget-object v0, v0, p1

    invoke-static {v0}, Lmiui/util/DirectIndexedFile$Reader$a;->c(Lmiui/util/DirectIndexedFile$Reader$a;)[[Ljava/lang/Object;

    move-result-object v0

    aget-object v0, v0, p2

    aget-object v0, v0, p3

    if-nez v0, :cond_0

    .line 1079
    iget-object v0, p0, Lmiui/util/DirectIndexedFile$Reader;->RY:Lmiui/util/DirectIndexedFile$c;

    iget-object v1, p0, Lmiui/util/DirectIndexedFile$Reader;->Sa:[Lmiui/util/DirectIndexedFile$Reader$a;

    aget-object v1, v1, p1

    invoke-static {v1}, Lmiui/util/DirectIndexedFile$Reader$a;->b(Lmiui/util/DirectIndexedFile$Reader$a;)[Lmiui/util/DirectIndexedFile$DataItemDescriptor;

    move-result-object v1

    aget-object v1, v1, p2

    invoke-static {v1}, Lmiui/util/DirectIndexedFile$DataItemDescriptor;->b(Lmiui/util/DirectIndexedFile$DataItemDescriptor;)J

    move-result-wide v1

    const-wide/16 v3, 0x4

    add-long/2addr v1, v3

    invoke-interface {v0, v1, v2}, Lmiui/util/DirectIndexedFile$c;->seek(J)V

    .line 1080
    iget-object v0, p0, Lmiui/util/DirectIndexedFile$Reader;->Sa:[Lmiui/util/DirectIndexedFile$Reader$a;

    aget-object v0, v0, p1

    invoke-static {v0}, Lmiui/util/DirectIndexedFile$Reader$a;->c(Lmiui/util/DirectIndexedFile$Reader$a;)[[Ljava/lang/Object;

    move-result-object v0

    aget-object v0, v0, p2

    iget-object v1, p0, Lmiui/util/DirectIndexedFile$Reader;->Sa:[Lmiui/util/DirectIndexedFile$Reader$a;

    aget-object v1, v1, p1

    invoke-static {v1}, Lmiui/util/DirectIndexedFile$Reader$a;->b(Lmiui/util/DirectIndexedFile$Reader$a;)[Lmiui/util/DirectIndexedFile$DataItemDescriptor;

    move-result-object v1

    aget-object v1, v1, p2

    iget-object v2, p0, Lmiui/util/DirectIndexedFile$Reader;->RY:Lmiui/util/DirectIndexedFile$c;

    invoke-static {v1, v2, p3}, Lmiui/util/DirectIndexedFile$DataItemDescriptor;->a(Lmiui/util/DirectIndexedFile$DataItemDescriptor;Lmiui/util/DirectIndexedFile$c;I)Ljava/lang/Object;

    move-result-object v1

    aput-object v1, v0, p3

    .line 1083
    :cond_0
    iget-object v0, p0, Lmiui/util/DirectIndexedFile$Reader;->Sa:[Lmiui/util/DirectIndexedFile$Reader$a;

    aget-object v0, v0, p1

    invoke-static {v0}, Lmiui/util/DirectIndexedFile$Reader$a;->c(Lmiui/util/DirectIndexedFile$Reader$a;)[[Ljava/lang/Object;

    move-result-object v0

    aget-object v0, v0, p2

    aget-object v0, v0, p3

    return-object v0
.end method

.method private m(II)J
    .locals 6

    .prologue
    .line 1087
    const/4 v3, 0x0

    .line 1088
    const/4 v2, 0x0

    iget-object v0, p0, Lmiui/util/DirectIndexedFile$Reader;->Sa:[Lmiui/util/DirectIndexedFile$Reader$a;

    aget-object v0, v0, p1

    invoke-static {v0}, Lmiui/util/DirectIndexedFile$Reader$a;->a(Lmiui/util/DirectIndexedFile$Reader$a;)[Lmiui/util/DirectIndexedFile$a;

    move-result-object v0

    array-length v1, v0

    .line 1089
    :goto_0
    if-ge v2, v1, :cond_3

    .line 1090
    add-int v0, v1, v2

    div-int/lit8 v0, v0, 0x2

    .line 1091
    iget-object v4, p0, Lmiui/util/DirectIndexedFile$Reader;->Sa:[Lmiui/util/DirectIndexedFile$Reader$a;

    aget-object v4, v4, p1

    invoke-static {v4}, Lmiui/util/DirectIndexedFile$Reader$a;->a(Lmiui/util/DirectIndexedFile$Reader$a;)[Lmiui/util/DirectIndexedFile$a;

    move-result-object v4

    aget-object v4, v4, v0

    iget v4, v4, Lmiui/util/DirectIndexedFile$a;->fI:I

    if-le v4, p2, :cond_0

    move v1, v2

    :goto_1
    move v2, v1

    move v1, v0

    .line 1099
    goto :goto_0

    .line 1093
    :cond_0
    iget-object v2, p0, Lmiui/util/DirectIndexedFile$Reader;->Sa:[Lmiui/util/DirectIndexedFile$Reader$a;

    aget-object v2, v2, p1

    invoke-static {v2}, Lmiui/util/DirectIndexedFile$Reader$a;->a(Lmiui/util/DirectIndexedFile$Reader$a;)[Lmiui/util/DirectIndexedFile$a;

    move-result-object v2

    aget-object v2, v2, v0

    iget v2, v2, Lmiui/util/DirectIndexedFile$a;->fJ:I

    if-gt v2, p2, :cond_1

    .line 1094
    add-int/lit8 v0, v0, 0x1

    move v5, v1

    move v1, v0

    move v0, v5

    goto :goto_1

    .line 1096
    :cond_1
    iget-object v1, p0, Lmiui/util/DirectIndexedFile$Reader;->Sa:[Lmiui/util/DirectIndexedFile$Reader$a;

    aget-object v1, v1, p1

    invoke-static {v1}, Lmiui/util/DirectIndexedFile$Reader$a;->a(Lmiui/util/DirectIndexedFile$Reader$a;)[Lmiui/util/DirectIndexedFile$a;

    move-result-object v1

    aget-object v0, v1, v0

    move-object v2, v0

    .line 1101
    :goto_2
    const-wide/16 v0, -0x1

    .line 1102
    if-eqz v2, :cond_2

    .line 1103
    iget-wide v0, v2, Lmiui/util/DirectIndexedFile$a;->fK:J

    iget v2, v2, Lmiui/util/DirectIndexedFile$a;->fI:I

    sub-int v2, p2, v2

    iget-object v3, p0, Lmiui/util/DirectIndexedFile$Reader;->Sa:[Lmiui/util/DirectIndexedFile$Reader$a;

    aget-object v3, v3, p1

    invoke-static {v3}, Lmiui/util/DirectIndexedFile$Reader$a;->d(Lmiui/util/DirectIndexedFile$Reader$a;)I

    move-result v3

    mul-int/2addr v2, v3

    int-to-long v2, v2

    add-long/2addr v0, v2

    .line 1105
    :cond_2
    return-wide v0

    :cond_3
    move-object v2, v3

    goto :goto_2
.end method


# virtual methods
.method public declared-synchronized close()V
    .locals 1

    .prologue
    .line 1064
    monitor-enter p0

    :try_start_0
    iget-object v0, p0, Lmiui/util/DirectIndexedFile$Reader;->RY:Lmiui/util/DirectIndexedFile$c;
    :try_end_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    if-eqz v0, :cond_0

    .line 1066
    :try_start_1
    iget-object v0, p0, Lmiui/util/DirectIndexedFile$Reader;->RY:Lmiui/util/DirectIndexedFile$c;

    invoke-interface {v0}, Lmiui/util/DirectIndexedFile$c;->close()V
    :try_end_1
    .catch Ljava/io/IOException; {:try_start_1 .. :try_end_1} :catch_0
    .catchall {:try_start_1 .. :try_end_1} :catchall_0

    .line 1072
    :cond_0
    :goto_0
    const/4 v0, 0x0

    :try_start_2
    iput-object v0, p0, Lmiui/util/DirectIndexedFile$Reader;->RY:Lmiui/util/DirectIndexedFile$c;

    .line 1073
    const/4 v0, 0x0

    iput-object v0, p0, Lmiui/util/DirectIndexedFile$Reader;->RZ:Lmiui/util/DirectIndexedFile$d;

    .line 1074
    const/4 v0, 0x0

    iput-object v0, p0, Lmiui/util/DirectIndexedFile$Reader;->Sa:[Lmiui/util/DirectIndexedFile$Reader$a;
    :try_end_2
    .catchall {:try_start_2 .. :try_end_2} :catchall_0

    .line 1075
    monitor-exit p0

    return-void

    .line 1064
    :catchall_0
    move-exception v0

    monitor-exit p0

    throw v0

    .line 1067
    :catch_0
    move-exception v0

    goto :goto_0
.end method

.method public declared-synchronized get(III)Ljava/lang/Object;
    .locals 7

    .prologue
    const/4 v0, 0x0

    .line 920
    monitor-enter p0

    :try_start_0
    iget-object v1, p0, Lmiui/util/DirectIndexedFile$Reader;->RY:Lmiui/util/DirectIndexedFile$c;

    if-nez v1, :cond_0

    .line 921
    new-instance v0, Ljava/lang/IllegalStateException;

    const-string v1, "Get data from a corrupt file"

    invoke-direct {v0, v1}, Ljava/lang/IllegalStateException;-><init>(Ljava/lang/String;)V

    throw v0
    :try_end_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    .line 920
    :catchall_0
    move-exception v0

    monitor-exit p0

    throw v0

    .line 923
    :cond_0
    if-ltz p1, :cond_1

    :try_start_1
    iget-object v1, p0, Lmiui/util/DirectIndexedFile$Reader;->Sa:[Lmiui/util/DirectIndexedFile$Reader$a;

    array-length v1, v1

    if-lt p1, v1, :cond_2

    .line 924
    :cond_1
    new-instance v0, Ljava/lang/IllegalArgumentException;

    new-instance v1, Ljava/lang/StringBuilder;

    invoke-direct {v1}, Ljava/lang/StringBuilder;-><init>()V

    const-string v2, "Kind "

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v1

    invoke-virtual {v1, p1}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    move-result-object v1

    const-string v2, " out of range[0, "

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v1

    iget-object v2, p0, Lmiui/util/DirectIndexedFile$Reader;->Sa:[Lmiui/util/DirectIndexedFile$Reader$a;

    array-length v2, v2

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    move-result-object v1

    const-string v2, ")"

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v1

    invoke-virtual {v1}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v1

    invoke-direct {v0, v1}, Ljava/lang/IllegalArgumentException;-><init>(Ljava/lang/String;)V

    throw v0

    .line 926
    :cond_2
    if-ltz p3, :cond_3

    iget-object v1, p0, Lmiui/util/DirectIndexedFile$Reader;->Sa:[Lmiui/util/DirectIndexedFile$Reader$a;

    aget-object v1, v1, p1

    invoke-static {v1}, Lmiui/util/DirectIndexedFile$Reader$a;->b(Lmiui/util/DirectIndexedFile$Reader$a;)[Lmiui/util/DirectIndexedFile$DataItemDescriptor;

    move-result-object v1

    array-length v1, v1

    if-lt p3, v1, :cond_4

    .line 927
    :cond_3
    new-instance v0, Ljava/lang/IllegalArgumentException;

    new-instance v1, Ljava/lang/StringBuilder;

    invoke-direct {v1}, Ljava/lang/StringBuilder;-><init>()V

    const-string v2, "DataIndex "

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v1

    invoke-virtual {v1, p3}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    move-result-object v1

    const-string v2, " out of range[0, "

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v1

    iget-object v2, p0, Lmiui/util/DirectIndexedFile$Reader;->Sa:[Lmiui/util/DirectIndexedFile$Reader$a;

    aget-object v2, v2, p1

    invoke-static {v2}, Lmiui/util/DirectIndexedFile$Reader$a;->b(Lmiui/util/DirectIndexedFile$Reader$a;)[Lmiui/util/DirectIndexedFile$DataItemDescriptor;

    move-result-object v2

    array-length v2, v2

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    move-result-object v1

    const-string v2, ")"

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v1

    invoke-virtual {v1}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v1

    invoke-direct {v0, v1}, Ljava/lang/IllegalArgumentException;-><init>(Ljava/lang/String;)V

    throw v0

    .line 931
    :cond_4
    invoke-static {}, Ljava/lang/System;->currentTimeMillis()J

    .line 933
    invoke-direct {p0, p1, p2}, Lmiui/util/DirectIndexedFile$Reader;->m(II)J

    move-result-wide v2

    .line 934
    const/4 v1, 0x0

    .line 935
    const-wide/16 v4, 0x0

    cmp-long v4, v2, v4

    if-gez v4, :cond_6

    .line 936
    iget-object v0, p0, Lmiui/util/DirectIndexedFile$Reader;->Sa:[Lmiui/util/DirectIndexedFile$Reader$a;

    aget-object v0, v0, p1

    invoke-static {v0}, Lmiui/util/DirectIndexedFile$Reader$a;->c(Lmiui/util/DirectIndexedFile$Reader$a;)[[Ljava/lang/Object;

    move-result-object v0

    aget-object v0, v0, p3

    const/4 v1, 0x0

    aget-object v0, v0, v1
    :try_end_1
    .catchall {:try_start_1 .. :try_end_1} :catchall_0

    .line 985
    :cond_5
    monitor-exit p0

    return-object v0

    .line 939
    :cond_6
    :try_start_2
    iget-object v4, p0, Lmiui/util/DirectIndexedFile$Reader;->RY:Lmiui/util/DirectIndexedFile$c;

    invoke-interface {v4, v2, v3}, Lmiui/util/DirectIndexedFile$c;->seek(J)V

    move v6, v0

    move-object v0, v1

    move v1, v6

    .line 940
    :goto_0
    if-gt v1, p3, :cond_5

    .line 941
    sget-object v2, Lmiui/util/DirectIndexedFile$1;->FF:[I

    iget-object v3, p0, Lmiui/util/DirectIndexedFile$Reader;->Sa:[Lmiui/util/DirectIndexedFile$Reader$a;

    aget-object v3, v3, p1

    invoke-static {v3}, Lmiui/util/DirectIndexedFile$Reader$a;->b(Lmiui/util/DirectIndexedFile$Reader$a;)[Lmiui/util/DirectIndexedFile$DataItemDescriptor;

    move-result-object v3

    aget-object v3, v3, v1

    invoke-static {v3}, Lmiui/util/DirectIndexedFile$DataItemDescriptor;->c(Lmiui/util/DirectIndexedFile$DataItemDescriptor;)Lmiui/util/DirectIndexedFile$DataItemDescriptor$Type;

    move-result-object v3

    invoke-virtual {v3}, Lmiui/util/DirectIndexedFile$DataItemDescriptor$Type;->ordinal()I

    move-result v3

    aget v2, v2, v3

    packed-switch v2, :pswitch_data_0

    .line 971
    new-instance v0, Ljava/lang/IllegalStateException;

    new-instance v2, Ljava/lang/StringBuilder;

    invoke-direct {v2}, Ljava/lang/StringBuilder;-><init>()V

    const-string v3, "Unknown type "

    invoke-virtual {v2, v3}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v2

    iget-object v3, p0, Lmiui/util/DirectIndexedFile$Reader;->Sa:[Lmiui/util/DirectIndexedFile$Reader$a;

    aget-object v3, v3, p1

    invoke-static {v3}, Lmiui/util/DirectIndexedFile$Reader$a;->b(Lmiui/util/DirectIndexedFile$Reader$a;)[Lmiui/util/DirectIndexedFile$DataItemDescriptor;

    move-result-object v3

    aget-object v1, v3, v1

    invoke-static {v1}, Lmiui/util/DirectIndexedFile$DataItemDescriptor;->c(Lmiui/util/DirectIndexedFile$DataItemDescriptor;)Lmiui/util/DirectIndexedFile$DataItemDescriptor$Type;

    move-result-object v1

    invoke-virtual {v2, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/Object;)Ljava/lang/StringBuilder;

    move-result-object v1

    invoke-virtual {v1}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v1

    invoke-direct {v0, v1}, Ljava/lang/IllegalStateException;-><init>(Ljava/lang/String;)V

    throw v0
    :try_end_2
    .catch Ljava/io/IOException; {:try_start_2 .. :try_end_2} :catch_0
    .catchall {:try_start_2 .. :try_end_2} :catchall_0

    .line 975
    :catch_0
    move-exception v0

    .line 976
    :try_start_3
    new-instance v1, Ljava/lang/IllegalStateException;

    const-string v2, "Seek data from a corrupt file"

    invoke-direct {v1, v2, v0}, Ljava/lang/IllegalStateException;-><init>(Ljava/lang/String;Ljava/lang/Throwable;)V

    throw v1
    :try_end_3
    .catchall {:try_start_3 .. :try_end_3} :catchall_0

    .line 943
    :pswitch_0
    :try_start_4
    iget-object v0, p0, Lmiui/util/DirectIndexedFile$Reader;->RY:Lmiui/util/DirectIndexedFile$c;

    invoke-interface {v0}, Lmiui/util/DirectIndexedFile$c;->readByte()B

    move-result v0

    invoke-static {v0}, Ljava/lang/Byte;->valueOf(B)Ljava/lang/Byte;

    move-result-object v0

    .line 940
    :cond_7
    :goto_1
    add-int/lit8 v1, v1, 0x1

    goto :goto_0

    .line 946
    :pswitch_1
    iget-object v0, p0, Lmiui/util/DirectIndexedFile$Reader;->RY:Lmiui/util/DirectIndexedFile$c;

    invoke-interface {v0}, Lmiui/util/DirectIndexedFile$c;->readShort()S

    move-result v0

    invoke-static {v0}, Ljava/lang/Short;->valueOf(S)Ljava/lang/Short;

    move-result-object v0

    goto :goto_1

    .line 949
    :pswitch_2
    iget-object v0, p0, Lmiui/util/DirectIndexedFile$Reader;->RY:Lmiui/util/DirectIndexedFile$c;

    invoke-interface {v0}, Lmiui/util/DirectIndexedFile$c;->readInt()I

    move-result v0

    invoke-static {v0}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object v0

    goto :goto_1

    .line 952
    :pswitch_3
    iget-object v0, p0, Lmiui/util/DirectIndexedFile$Reader;->RY:Lmiui/util/DirectIndexedFile$c;

    invoke-interface {v0}, Lmiui/util/DirectIndexedFile$c;->readLong()J

    move-result-wide v2

    invoke-static {v2, v3}, Ljava/lang/Long;->valueOf(J)Ljava/lang/Long;
    :try_end_4
    .catch Ljava/io/IOException; {:try_start_4 .. :try_end_4} :catch_0
    .catchall {:try_start_4 .. :try_end_4} :catchall_0

    move-result-object v0

    goto :goto_1

    .line 960
    :pswitch_4
    :try_start_5
    iget-object v2, p0, Lmiui/util/DirectIndexedFile$Reader;->RY:Lmiui/util/DirectIndexedFile$c;

    iget-object v3, p0, Lmiui/util/DirectIndexedFile$Reader;->Sa:[Lmiui/util/DirectIndexedFile$Reader$a;

    aget-object v3, v3, p1

    invoke-static {v3}, Lmiui/util/DirectIndexedFile$Reader$a;->b(Lmiui/util/DirectIndexedFile$Reader$a;)[Lmiui/util/DirectIndexedFile$DataItemDescriptor;

    move-result-object v3

    aget-object v3, v3, v1

    invoke-static {v3}, Lmiui/util/DirectIndexedFile$DataItemDescriptor;->a(Lmiui/util/DirectIndexedFile$DataItemDescriptor;)B

    move-result v3

    invoke-static {v2, v3}, Lmiui/util/DirectIndexedFile$DataItemDescriptor;->b(Ljava/io/DataInput;I)J

    move-result-wide v2

    long-to-int v2, v2

    .line 962
    if-ne v1, p3, :cond_7

    .line 963
    invoke-direct {p0, p1, p3, v2}, Lmiui/util/DirectIndexedFile$Reader;->j(III)Ljava/lang/Object;
    :try_end_5
    .catch Ljava/io/IOException; {:try_start_5 .. :try_end_5} :catch_1
    .catchall {:try_start_5 .. :try_end_5} :catchall_0

    move-result-object v0

    goto :goto_1

    .line 965
    :catch_1
    move-exception v0

    .line 966
    :try_start_6
    new-instance v1, Ljava/lang/IllegalStateException;

    const-string v2, "File may be corrupt due to invalid data index size"

    invoke-direct {v1, v2, v0}, Ljava/lang/IllegalStateException;-><init>(Ljava/lang/String;Ljava/lang/Throwable;)V

    throw v1
    :try_end_6
    .catch Ljava/io/IOException; {:try_start_6 .. :try_end_6} :catch_0
    .catchall {:try_start_6 .. :try_end_6} :catchall_0

    .line 941
    :pswitch_data_0
    .packed-switch 0x1
        :pswitch_4
        :pswitch_4
        :pswitch_4
        :pswitch_4
        :pswitch_4
        :pswitch_0
        :pswitch_1
        :pswitch_2
        :pswitch_3
    .end packed-switch
.end method

.method public declared-synchronized get(II)[Ljava/lang/Object;
    .locals 6

    .prologue
    const/4 v1, 0x0

    .line 996
    monitor-enter p0

    :try_start_0
    iget-object v0, p0, Lmiui/util/DirectIndexedFile$Reader;->RY:Lmiui/util/DirectIndexedFile$c;

    if-nez v0, :cond_0

    .line 997
    new-instance v0, Ljava/lang/IllegalStateException;

    const-string v1, "Get data from a corrupt file"

    invoke-direct {v0, v1}, Ljava/lang/IllegalStateException;-><init>(Ljava/lang/String;)V

    throw v0
    :try_end_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    .line 996
    :catchall_0
    move-exception v0

    monitor-exit p0

    throw v0

    .line 999
    :cond_0
    if-ltz p1, :cond_1

    :try_start_1
    iget-object v0, p0, Lmiui/util/DirectIndexedFile$Reader;->Sa:[Lmiui/util/DirectIndexedFile$Reader$a;

    array-length v0, v0

    if-lt p1, v0, :cond_2

    .line 1000
    :cond_1
    new-instance v0, Ljava/lang/IllegalArgumentException;

    new-instance v1, Ljava/lang/StringBuilder;

    invoke-direct {v1}, Ljava/lang/StringBuilder;-><init>()V

    const-string v2, "Cannot get data kind "

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v1

    invoke-virtual {v1, p1}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    move-result-object v1

    invoke-virtual {v1}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v1

    invoke-direct {v0, v1}, Ljava/lang/IllegalArgumentException;-><init>(Ljava/lang/String;)V

    throw v0

    .line 1003
    :cond_2
    invoke-static {}, Ljava/lang/System;->currentTimeMillis()J

    .line 1005
    invoke-direct {p0, p1, p2}, Lmiui/util/DirectIndexedFile$Reader;->m(II)J

    move-result-wide v2

    .line 1006
    iget-object v0, p0, Lmiui/util/DirectIndexedFile$Reader;->Sa:[Lmiui/util/DirectIndexedFile$Reader$a;

    aget-object v0, v0, p1

    invoke-static {v0}, Lmiui/util/DirectIndexedFile$Reader$a;->b(Lmiui/util/DirectIndexedFile$Reader$a;)[Lmiui/util/DirectIndexedFile$DataItemDescriptor;

    move-result-object v0

    array-length v0, v0

    new-array v0, v0, [Ljava/lang/Object;

    .line 1007
    const-wide/16 v4, 0x0

    cmp-long v4, v2, v4

    if-gez v4, :cond_3

    .line 1008
    :goto_0
    array-length v2, v0

    if-ge v1, v2, :cond_4

    .line 1009
    iget-object v2, p0, Lmiui/util/DirectIndexedFile$Reader;->Sa:[Lmiui/util/DirectIndexedFile$Reader$a;

    aget-object v2, v2, p1

    invoke-static {v2}, Lmiui/util/DirectIndexedFile$Reader$a;->c(Lmiui/util/DirectIndexedFile$Reader$a;)[[Ljava/lang/Object;

    move-result-object v2

    aget-object v2, v2, v1

    const/4 v3, 0x0

    aget-object v2, v2, v3

    aput-object v2, v0, v1
    :try_end_1
    .catchall {:try_start_1 .. :try_end_1} :catchall_0

    .line 1008
    add-int/lit8 v1, v1, 0x1

    goto :goto_0

    .line 1014
    :cond_3
    :try_start_2
    iget-object v4, p0, Lmiui/util/DirectIndexedFile$Reader;->RY:Lmiui/util/DirectIndexedFile$c;

    invoke-interface {v4, v2, v3}, Lmiui/util/DirectIndexedFile$c;->seek(J)V

    .line 1015
    :goto_1
    array-length v2, v0

    if-ge v1, v2, :cond_4

    .line 1016
    sget-object v2, Lmiui/util/DirectIndexedFile$1;->FF:[I

    iget-object v3, p0, Lmiui/util/DirectIndexedFile$Reader;->Sa:[Lmiui/util/DirectIndexedFile$Reader$a;

    aget-object v3, v3, p1

    invoke-static {v3}, Lmiui/util/DirectIndexedFile$Reader$a;->b(Lmiui/util/DirectIndexedFile$Reader$a;)[Lmiui/util/DirectIndexedFile$DataItemDescriptor;

    move-result-object v3

    aget-object v3, v3, v1

    invoke-static {v3}, Lmiui/util/DirectIndexedFile$DataItemDescriptor;->c(Lmiui/util/DirectIndexedFile$DataItemDescriptor;)Lmiui/util/DirectIndexedFile$DataItemDescriptor$Type;

    move-result-object v3

    invoke-virtual {v3}, Lmiui/util/DirectIndexedFile$DataItemDescriptor$Type;->ordinal()I

    move-result v3

    aget v2, v2, v3

    packed-switch v2, :pswitch_data_0

    .line 1046
    new-instance v0, Ljava/lang/IllegalStateException;

    new-instance v2, Ljava/lang/StringBuilder;

    invoke-direct {v2}, Ljava/lang/StringBuilder;-><init>()V

    const-string v3, "Unknown type "

    invoke-virtual {v2, v3}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v2

    iget-object v3, p0, Lmiui/util/DirectIndexedFile$Reader;->Sa:[Lmiui/util/DirectIndexedFile$Reader$a;

    aget-object v3, v3, p1

    invoke-static {v3}, Lmiui/util/DirectIndexedFile$Reader$a;->b(Lmiui/util/DirectIndexedFile$Reader$a;)[Lmiui/util/DirectIndexedFile$DataItemDescriptor;

    move-result-object v3

    aget-object v1, v3, v1

    invoke-static {v1}, Lmiui/util/DirectIndexedFile$DataItemDescriptor;->c(Lmiui/util/DirectIndexedFile$DataItemDescriptor;)Lmiui/util/DirectIndexedFile$DataItemDescriptor$Type;

    move-result-object v1

    invoke-virtual {v2, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/Object;)Ljava/lang/StringBuilder;

    move-result-object v1

    invoke-virtual {v1}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v1

    invoke-direct {v0, v1}, Ljava/lang/IllegalStateException;-><init>(Ljava/lang/String;)V

    throw v0
    :try_end_2
    .catch Ljava/io/IOException; {:try_start_2 .. :try_end_2} :catch_0
    .catchall {:try_start_2 .. :try_end_2} :catchall_0

    .line 1050
    :catch_0
    move-exception v0

    .line 1051
    :try_start_3
    new-instance v1, Ljava/lang/IllegalStateException;

    const-string v2, "Seek data from a corrupt file"

    invoke-direct {v1, v2, v0}, Ljava/lang/IllegalStateException;-><init>(Ljava/lang/String;Ljava/lang/Throwable;)V

    throw v1
    :try_end_3
    .catchall {:try_start_3 .. :try_end_3} :catchall_0

    .line 1018
    :pswitch_0
    :try_start_4
    iget-object v2, p0, Lmiui/util/DirectIndexedFile$Reader;->RY:Lmiui/util/DirectIndexedFile$c;

    invoke-interface {v2}, Lmiui/util/DirectIndexedFile$c;->readByte()B

    move-result v2

    invoke-static {v2}, Ljava/lang/Byte;->valueOf(B)Ljava/lang/Byte;

    move-result-object v2

    aput-object v2, v0, v1

    .line 1015
    :goto_2
    add-int/lit8 v1, v1, 0x1

    goto :goto_1

    .line 1021
    :pswitch_1
    iget-object v2, p0, Lmiui/util/DirectIndexedFile$Reader;->RY:Lmiui/util/DirectIndexedFile$c;

    invoke-interface {v2}, Lmiui/util/DirectIndexedFile$c;->readShort()S

    move-result v2

    invoke-static {v2}, Ljava/lang/Short;->valueOf(S)Ljava/lang/Short;

    move-result-object v2

    aput-object v2, v0, v1

    goto :goto_2

    .line 1024
    :pswitch_2
    iget-object v2, p0, Lmiui/util/DirectIndexedFile$Reader;->RY:Lmiui/util/DirectIndexedFile$c;

    invoke-interface {v2}, Lmiui/util/DirectIndexedFile$c;->readInt()I

    move-result v2

    invoke-static {v2}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object v2

    aput-object v2, v0, v1

    goto :goto_2

    .line 1027
    :pswitch_3
    iget-object v2, p0, Lmiui/util/DirectIndexedFile$Reader;->RY:Lmiui/util/DirectIndexedFile$c;

    invoke-interface {v2}, Lmiui/util/DirectIndexedFile$c;->readLong()J

    move-result-wide v2

    invoke-static {v2, v3}, Ljava/lang/Long;->valueOf(J)Ljava/lang/Long;

    move-result-object v2

    aput-object v2, v0, v1
    :try_end_4
    .catch Ljava/io/IOException; {:try_start_4 .. :try_end_4} :catch_0
    .catchall {:try_start_4 .. :try_end_4} :catchall_0

    goto :goto_2

    .line 1035
    :pswitch_4
    :try_start_5
    iget-object v2, p0, Lmiui/util/DirectIndexedFile$Reader;->RY:Lmiui/util/DirectIndexedFile$c;

    iget-object v3, p0, Lmiui/util/DirectIndexedFile$Reader;->Sa:[Lmiui/util/DirectIndexedFile$Reader$a;

    aget-object v3, v3, p1

    invoke-static {v3}, Lmiui/util/DirectIndexedFile$Reader$a;->b(Lmiui/util/DirectIndexedFile$Reader$a;)[Lmiui/util/DirectIndexedFile$DataItemDescriptor;

    move-result-object v3

    aget-object v3, v3, v1

    invoke-static {v3}, Lmiui/util/DirectIndexedFile$DataItemDescriptor;->a(Lmiui/util/DirectIndexedFile$DataItemDescriptor;)B

    move-result v3

    invoke-static {v2, v3}, Lmiui/util/DirectIndexedFile$DataItemDescriptor;->b(Ljava/io/DataInput;I)J

    move-result-wide v2

    long-to-int v2, v2

    .line 1037
    iget-object v3, p0, Lmiui/util/DirectIndexedFile$Reader;->RY:Lmiui/util/DirectIndexedFile$c;

    invoke-interface {v3}, Lmiui/util/DirectIndexedFile$c;->getFilePointer()J

    move-result-wide v3

    .line 1038
    invoke-direct {p0, p1, v1, v2}, Lmiui/util/DirectIndexedFile$Reader;->j(III)Ljava/lang/Object;

    move-result-object v2

    aput-object v2, v0, v1

    .line 1039
    iget-object v2, p0, Lmiui/util/DirectIndexedFile$Reader;->RY:Lmiui/util/DirectIndexedFile$c;

    invoke-interface {v2, v3, v4}, Lmiui/util/DirectIndexedFile$c;->seek(J)V
    :try_end_5
    .catch Ljava/io/IOException; {:try_start_5 .. :try_end_5} :catch_1
    .catchall {:try_start_5 .. :try_end_5} :catchall_0

    goto :goto_2

    .line 1040
    :catch_1
    move-exception v0

    .line 1041
    :try_start_6
    new-instance v1, Ljava/lang/IllegalStateException;

    const-string v2, "File may be corrupt due to invalid data index size"

    invoke-direct {v1, v2, v0}, Ljava/lang/IllegalStateException;-><init>(Ljava/lang/String;Ljava/lang/Throwable;)V

    throw v1
    :try_end_6
    .catch Ljava/io/IOException; {:try_start_6 .. :try_end_6} :catch_0
    .catchall {:try_start_6 .. :try_end_6} :catchall_0

    .line 1059
    :cond_4
    monitor-exit p0

    return-object v0

    .line 1016
    nop

    :pswitch_data_0
    .packed-switch 0x1
        :pswitch_4
        :pswitch_4
        :pswitch_4
        :pswitch_4
        :pswitch_4
        :pswitch_0
        :pswitch_1
        :pswitch_2
        :pswitch_3
    .end packed-switch
.end method

.method public getDataVersion()I
    .locals 1

    .prologue
    .line 902
    iget-object v0, p0, Lmiui/util/DirectIndexedFile$Reader;->RZ:Lmiui/util/DirectIndexedFile$d;

    if-nez v0, :cond_0

    .line 903
    const/4 v0, -0x1

    .line 906
    :goto_0
    return v0

    :cond_0
    iget-object v0, p0, Lmiui/util/DirectIndexedFile$Reader;->RZ:Lmiui/util/DirectIndexedFile$d;

    invoke-static {v0}, Lmiui/util/DirectIndexedFile$d;->b(Lmiui/util/DirectIndexedFile$d;)I

    move-result v0

    goto :goto_0
.end method
