.class Lcom/miui/internal/app/h;
.super Ljava/lang/Object;
.source "SourceFile"

# interfaces
.implements Landroid/content/DialogInterface$OnClickListener;


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lcom/miui/internal/app/LicenseActivity;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x0
    name = null
.end annotation


# instance fields
.field final synthetic gE:Lcom/miui/internal/app/LicenseActivity;


# direct methods
.method constructor <init>(Lcom/miui/internal/app/LicenseActivity;)V
    .locals 0

    .prologue
    .line 101
    iput-object p1, p0, Lcom/miui/internal/app/h;->gE:Lcom/miui/internal/app/LicenseActivity;

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method


# virtual methods
.method public onClick(Landroid/content/DialogInterface;I)V
    .locals 1

    .prologue
    .line 104
    iget-object v0, p0, Lcom/miui/internal/app/h;->gE:Lcom/miui/internal/app/LicenseActivity;

    invoke-virtual {v0}, Lcom/miui/internal/app/LicenseActivity;->finish()V

    .line 105
    return-void
.end method
