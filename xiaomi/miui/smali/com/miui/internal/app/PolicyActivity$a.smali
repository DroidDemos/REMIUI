.class Lcom/miui/internal/app/PolicyActivity$a;
.super Ljava/lang/Object;
.source "SourceFile"

# interfaces
.implements Landroid/view/ActionMode$Callback;


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lcom/miui/internal/app/PolicyActivity;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x2
    name = "a"
.end annotation


# instance fields
.field final synthetic ai:Lcom/miui/internal/app/PolicyActivity;


# direct methods
.method private constructor <init>(Lcom/miui/internal/app/PolicyActivity;)V
    .locals 0

    .prologue
    .line 271
    iput-object p1, p0, Lcom/miui/internal/app/PolicyActivity$a;->ai:Lcom/miui/internal/app/PolicyActivity;

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method

.method synthetic constructor <init>(Lcom/miui/internal/app/PolicyActivity;Lcom/miui/internal/app/PolicyActivity$1;)V
    .locals 0

    .prologue
    .line 271
    invoke-direct {p0, p1}, Lcom/miui/internal/app/PolicyActivity$a;-><init>(Lcom/miui/internal/app/PolicyActivity;)V

    return-void
.end method


# virtual methods
.method public onActionItemClicked(Landroid/view/ActionMode;Landroid/view/MenuItem;)Z
    .locals 3

    .prologue
    const/4 v2, 0x1

    .line 287
    invoke-interface {p2}, Landroid/view/MenuItem;->getItemId()I

    move-result v0

    const v1, 0x1020019

    if-ne v0, v1, :cond_1

    .line 288
    iget-object v0, p0, Lcom/miui/internal/app/PolicyActivity$a;->ai:Lcom/miui/internal/app/PolicyActivity;

    const/4 v1, 0x2

    invoke-virtual {v0, v1}, Lcom/miui/internal/app/PolicyActivity;->setResult(I)V

    .line 289
    iget-object v0, p0, Lcom/miui/internal/app/PolicyActivity$a;->ai:Lcom/miui/internal/app/PolicyActivity;

    invoke-virtual {v0}, Lcom/miui/internal/app/PolicyActivity;->finish()V

    .line 294
    :cond_0
    :goto_0
    return v2

    .line 290
    :cond_1
    invoke-interface {p2}, Landroid/view/MenuItem;->getItemId()I

    move-result v0

    const v1, 0x102001a

    if-ne v0, v1, :cond_0

    .line 291
    iget-object v0, p0, Lcom/miui/internal/app/PolicyActivity$a;->ai:Lcom/miui/internal/app/PolicyActivity;

    invoke-virtual {v0, v2}, Lcom/miui/internal/app/PolicyActivity;->setResult(I)V

    .line 292
    iget-object v0, p0, Lcom/miui/internal/app/PolicyActivity$a;->ai:Lcom/miui/internal/app/PolicyActivity;

    invoke-virtual {v0}, Lcom/miui/internal/app/PolicyActivity;->finish()V

    goto :goto_0
.end method

.method public onCreateActionMode(Landroid/view/ActionMode;Landroid/view/Menu;)Z
    .locals 3

    .prologue
    .line 274
    iget-object v0, p0, Lcom/miui/internal/app/PolicyActivity$a;->ai:Lcom/miui/internal/app/PolicyActivity;

    invoke-static {v0, p1}, Lcom/miui/internal/app/PolicyActivity;->a(Lcom/miui/internal/app/PolicyActivity;Landroid/view/ActionMode;)Landroid/view/ActionMode;

    .line 275
    iget-object v0, p0, Lcom/miui/internal/app/PolicyActivity$a;->ai:Lcom/miui/internal/app/PolicyActivity;

    invoke-static {v0}, Lcom/miui/internal/app/PolicyActivity;->b(Lcom/miui/internal/app/PolicyActivity;)Landroid/view/ActionMode;

    move-result-object v0

    sget v1, Lcom/miui/internal/R$string;->user_agreement:I

    invoke-virtual {v0, v1}, Landroid/view/ActionMode;->setTitle(I)V

    .line 276
    iget-object v0, p0, Lcom/miui/internal/app/PolicyActivity$a;->ai:Lcom/miui/internal/app/PolicyActivity;

    const v1, 0x104000a

    const/high16 v2, 0x1040000

    invoke-static {v0, v1, v2}, Lcom/miui/internal/app/PolicyActivity;->a(Lcom/miui/internal/app/PolicyActivity;II)V

    .line 277
    const/4 v0, 0x1

    return v0
.end method

.method public onDestroyActionMode(Landroid/view/ActionMode;)V
    .locals 2

    .prologue
    .line 299
    iget-object v0, p0, Lcom/miui/internal/app/PolicyActivity$a;->ai:Lcom/miui/internal/app/PolicyActivity;

    const/4 v1, 0x0

    invoke-static {v0, v1}, Lcom/miui/internal/app/PolicyActivity;->a(Lcom/miui/internal/app/PolicyActivity;Landroid/view/ActionMode;)Landroid/view/ActionMode;

    .line 300
    iget-object v0, p0, Lcom/miui/internal/app/PolicyActivity$a;->ai:Lcom/miui/internal/app/PolicyActivity;

    const/4 v1, 0x2

    invoke-virtual {v0, v1}, Lcom/miui/internal/app/PolicyActivity;->setResult(I)V

    .line 301
    iget-object v0, p0, Lcom/miui/internal/app/PolicyActivity$a;->ai:Lcom/miui/internal/app/PolicyActivity;

    invoke-virtual {v0}, Lcom/miui/internal/app/PolicyActivity;->finish()V

    .line 302
    return-void
.end method

.method public onPrepareActionMode(Landroid/view/ActionMode;Landroid/view/Menu;)Z
    .locals 1

    .prologue
    .line 282
    const/4 v0, 0x1

    return v0
.end method
