.class Lmiui/widget/DateTimePicker$a;
.super Ljava/lang/Object;
.source "SourceFile"

# interfaces
.implements Lmiui/widget/NumberPicker$OnValueChangeListener;


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lmiui/widget/DateTimePicker;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x2
    name = "a"
.end annotation


# instance fields
.field final synthetic eT:Lmiui/widget/DateTimePicker;


# direct methods
.method private constructor <init>(Lmiui/widget/DateTimePicker;)V
    .locals 0

    .prologue
    .line 514
    iput-object p1, p0, Lmiui/widget/DateTimePicker$a;->eT:Lmiui/widget/DateTimePicker;

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method

.method synthetic constructor <init>(Lmiui/widget/DateTimePicker;Lmiui/widget/DateTimePicker$1;)V
    .locals 0

    .prologue
    .line 514
    invoke-direct {p0, p1}, Lmiui/widget/DateTimePicker$a;-><init>(Lmiui/widget/DateTimePicker;)V

    return-void
.end method

.method private a(Lmiui/widget/DateTimePicker;)V
    .locals 3

    .prologue
    .line 535
    iget-object v0, p0, Lmiui/widget/DateTimePicker$a;->eT:Lmiui/widget/DateTimePicker;

    invoke-static {v0}, Lmiui/widget/DateTimePicker;->l(Lmiui/widget/DateTimePicker;)Lmiui/widget/DateTimePicker$OnDateTimeChangedListener;

    move-result-object v0

    if-eqz v0, :cond_0

    .line 536
    iget-object v0, p0, Lmiui/widget/DateTimePicker$a;->eT:Lmiui/widget/DateTimePicker;

    invoke-static {v0}, Lmiui/widget/DateTimePicker;->l(Lmiui/widget/DateTimePicker;)Lmiui/widget/DateTimePicker$OnDateTimeChangedListener;

    move-result-object v0

    iget-object v1, p0, Lmiui/widget/DateTimePicker$a;->eT:Lmiui/widget/DateTimePicker;

    invoke-virtual {v1}, Lmiui/widget/DateTimePicker;->getTimeInMillis()J

    move-result-wide v1

    invoke-interface {v0, p1, v1, v2}, Lmiui/widget/DateTimePicker$OnDateTimeChangedListener;->onDateTimeChanged(Lmiui/widget/DateTimePicker;J)V

    .line 538
    :cond_0
    return-void
.end method


# virtual methods
.method public onValueChange(Lmiui/widget/NumberPicker;II)V
    .locals 4

    .prologue
    const/4 v0, 0x1

    .line 517
    iget-object v1, p0, Lmiui/widget/DateTimePicker$a;->eT:Lmiui/widget/DateTimePicker;

    invoke-static {v1}, Lmiui/widget/DateTimePicker;->b(Lmiui/widget/DateTimePicker;)Lmiui/widget/NumberPicker;

    move-result-object v1

    if-ne p1, v1, :cond_2

    .line 518
    invoke-virtual {p1}, Lmiui/widget/NumberPicker;->getValue()I

    move-result v1

    iget-object v2, p0, Lmiui/widget/DateTimePicker$a;->eT:Lmiui/widget/DateTimePicker;

    invoke-static {v2}, Lmiui/widget/DateTimePicker;->c(Lmiui/widget/DateTimePicker;)I

    move-result v2

    sub-int/2addr v1, v2

    add-int/lit8 v1, v1, 0x7

    rem-int/lit8 v1, v1, 0x7

    if-ne v1, v0, :cond_1

    .line 520
    :goto_0
    iget-object v1, p0, Lmiui/widget/DateTimePicker$a;->eT:Lmiui/widget/DateTimePicker;

    invoke-static {v1}, Lmiui/widget/DateTimePicker;->d(Lmiui/widget/DateTimePicker;)Lmiui/date/Calendar;

    move-result-object v1

    const/16 v2, 0xc

    invoke-virtual {v1, v2, v0}, Lmiui/date/Calendar;->add(II)Lmiui/date/Calendar;

    .line 521
    iget-object v0, p0, Lmiui/widget/DateTimePicker$a;->eT:Lmiui/widget/DateTimePicker;

    invoke-virtual {p1}, Lmiui/widget/NumberPicker;->getValue()I

    move-result v1

    invoke-static {v0, v1}, Lmiui/widget/DateTimePicker;->a(Lmiui/widget/DateTimePicker;I)I

    .line 527
    :cond_0
    :goto_1
    iget-object v0, p0, Lmiui/widget/DateTimePicker$a;->eT:Lmiui/widget/DateTimePicker;

    invoke-static {v0}, Lmiui/widget/DateTimePicker;->h(Lmiui/widget/DateTimePicker;)V

    .line 528
    iget-object v0, p0, Lmiui/widget/DateTimePicker$a;->eT:Lmiui/widget/DateTimePicker;

    invoke-static {v0}, Lmiui/widget/DateTimePicker;->i(Lmiui/widget/DateTimePicker;)V

    .line 529
    iget-object v0, p0, Lmiui/widget/DateTimePicker$a;->eT:Lmiui/widget/DateTimePicker;

    invoke-static {v0}, Lmiui/widget/DateTimePicker;->j(Lmiui/widget/DateTimePicker;)V

    .line 530
    iget-object v0, p0, Lmiui/widget/DateTimePicker$a;->eT:Lmiui/widget/DateTimePicker;

    invoke-static {v0}, Lmiui/widget/DateTimePicker;->k(Lmiui/widget/DateTimePicker;)V

    .line 531
    iget-object v0, p0, Lmiui/widget/DateTimePicker$a;->eT:Lmiui/widget/DateTimePicker;

    invoke-direct {p0, v0}, Lmiui/widget/DateTimePicker$a;->a(Lmiui/widget/DateTimePicker;)V

    .line 532
    return-void

    .line 518
    :cond_1
    const/4 v0, -0x1

    goto :goto_0

    .line 522
    :cond_2
    iget-object v0, p0, Lmiui/widget/DateTimePicker$a;->eT:Lmiui/widget/DateTimePicker;

    invoke-static {v0}, Lmiui/widget/DateTimePicker;->e(Lmiui/widget/DateTimePicker;)Lmiui/widget/NumberPicker;

    move-result-object v0

    if-ne p1, v0, :cond_3

    .line 523
    iget-object v0, p0, Lmiui/widget/DateTimePicker$a;->eT:Lmiui/widget/DateTimePicker;

    invoke-static {v0}, Lmiui/widget/DateTimePicker;->d(Lmiui/widget/DateTimePicker;)Lmiui/date/Calendar;

    move-result-object v0

    const/16 v1, 0x12

    iget-object v2, p0, Lmiui/widget/DateTimePicker$a;->eT:Lmiui/widget/DateTimePicker;

    invoke-static {v2}, Lmiui/widget/DateTimePicker;->e(Lmiui/widget/DateTimePicker;)Lmiui/widget/NumberPicker;

    move-result-object v2

    invoke-virtual {v2}, Lmiui/widget/NumberPicker;->getValue()I

    move-result v2

    invoke-virtual {v0, v1, v2}, Lmiui/date/Calendar;->set(II)Lmiui/date/Calendar;

    goto :goto_1

    .line 524
    :cond_3
    iget-object v0, p0, Lmiui/widget/DateTimePicker$a;->eT:Lmiui/widget/DateTimePicker;

    invoke-static {v0}, Lmiui/widget/DateTimePicker;->f(Lmiui/widget/DateTimePicker;)Lmiui/widget/NumberPicker;

    move-result-object v0

    if-ne p1, v0, :cond_0

    .line 525
    iget-object v0, p0, Lmiui/widget/DateTimePicker$a;->eT:Lmiui/widget/DateTimePicker;

    invoke-static {v0}, Lmiui/widget/DateTimePicker;->d(Lmiui/widget/DateTimePicker;)Lmiui/date/Calendar;

    move-result-object v0

    const/16 v1, 0x14

    iget-object v2, p0, Lmiui/widget/DateTimePicker$a;->eT:Lmiui/widget/DateTimePicker;

    invoke-static {v2}, Lmiui/widget/DateTimePicker;->g(Lmiui/widget/DateTimePicker;)I

    move-result v2

    iget-object v3, p0, Lmiui/widget/DateTimePicker$a;->eT:Lmiui/widget/DateTimePicker;

    invoke-static {v3}, Lmiui/widget/DateTimePicker;->f(Lmiui/widget/DateTimePicker;)Lmiui/widget/NumberPicker;

    move-result-object v3

    invoke-virtual {v3}, Lmiui/widget/NumberPicker;->getValue()I

    move-result v3

    mul-int/2addr v2, v3

    invoke-virtual {v0, v1, v2}, Lmiui/date/Calendar;->set(II)Lmiui/date/Calendar;

    goto :goto_1
.end method
