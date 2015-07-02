.class Lcom/miui/internal/app/n$a;
.super Ljava/lang/Object;
.source "SourceFile"


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lcom/miui/internal/app/n;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x0
    name = "a"
.end annotation


# instance fields
.field IA:Landroid/os/Bundle;

.field IB:Landroid/app/ActionBar$Tab;

.field final synthetic IC:Lcom/miui/internal/app/n;

.field Iy:Ljava/lang/Class;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Ljava/lang/Class",
            "<+",
            "Landroid/app/Fragment;",
            ">;"
        }
    .end annotation
.end field

.field Iz:Landroid/app/Fragment;

.field lh:Z

.field tag:Ljava/lang/String;


# direct methods
.method constructor <init>(Lcom/miui/internal/app/n;Ljava/lang/String;Ljava/lang/Class;Landroid/os/Bundle;Landroid/app/ActionBar$Tab;Z)V
    .locals 1
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Ljava/lang/String;",
            "Ljava/lang/Class",
            "<+",
            "Landroid/app/Fragment;",
            ">;",
            "Landroid/os/Bundle;",
            "Landroid/app/ActionBar$Tab;",
            "Z)V"
        }
    .end annotation

    .prologue
    .line 230
    iput-object p1, p0, Lcom/miui/internal/app/n$a;->IC:Lcom/miui/internal/app/n;

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    .line 231
    iput-object p2, p0, Lcom/miui/internal/app/n$a;->tag:Ljava/lang/String;

    .line 232
    iput-object p3, p0, Lcom/miui/internal/app/n$a;->Iy:Ljava/lang/Class;

    .line 233
    const/4 v0, 0x0

    iput-object v0, p0, Lcom/miui/internal/app/n$a;->Iz:Landroid/app/Fragment;

    .line 234
    iput-object p4, p0, Lcom/miui/internal/app/n$a;->IA:Landroid/os/Bundle;

    .line 235
    iput-object p5, p0, Lcom/miui/internal/app/n$a;->IB:Landroid/app/ActionBar$Tab;

    .line 236
    iput-boolean p6, p0, Lcom/miui/internal/app/n$a;->lh:Z

    .line 237
    return-void
.end method
