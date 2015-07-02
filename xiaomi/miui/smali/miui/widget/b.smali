.class Lmiui/widget/b;
.super Ljava/lang/Object;
.source "SourceFile"

# interfaces
.implements Ljava/lang/Runnable;


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lmiui/widget/AlphabetFastIndexer;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x0
    name = null
.end annotation


# instance fields
.field final synthetic ar:Lmiui/widget/AlphabetFastIndexer;


# direct methods
.method constructor <init>(Lmiui/widget/AlphabetFastIndexer;)V
    .locals 0

    .prologue
    .line 370
    iput-object p1, p0, Lmiui/widget/b;->ar:Lmiui/widget/AlphabetFastIndexer;

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method


# virtual methods
.method public run()V
    .locals 1

    .prologue
    .line 374
    iget-object v0, p0, Lmiui/widget/b;->ar:Lmiui/widget/AlphabetFastIndexer;

    invoke-static {v0}, Lmiui/widget/AlphabetFastIndexer;->b(Lmiui/widget/AlphabetFastIndexer;)V

    .line 375
    return-void
.end method
