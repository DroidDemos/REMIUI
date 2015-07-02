.class Lmiui/widget/DatePicker$1;
.super Ljava/lang/Object;
.source "SourceFile"

# interfaces
.implements Lmiui/widget/NumberPicker$OnValueChangeListener;


# annotations
.annotation system Ldalvik/annotation/EnclosingMethod;
    value = Lmiui/widget/DatePicker;-><init>(Landroid/content/Context;Landroid/util/AttributeSet;I)V
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x0
    name = null
.end annotation


# instance fields
.field final synthetic eQ:Lmiui/widget/DatePicker;


# direct methods
.method constructor <init>(Lmiui/widget/DatePicker;)V
    .locals 0

    .prologue
    .line 157
    iput-object p1, p0, Lmiui/widget/DatePicker$1;->eQ:Lmiui/widget/DatePicker;

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method


# virtual methods
.method public onValueChange(Lmiui/widget/NumberPicker;II)V
    .locals 6

    .prologue
    const/16 v1, 0x9

    const/4 v2, 0x5

    const/4 v3, 0x1

    .line 159
    iget-object v0, p0, Lmiui/widget/DatePicker$1;->eQ:Lmiui/widget/DatePicker;

    invoke-static {v0}, Lmiui/widget/DatePicker;->b(Lmiui/widget/DatePicker;)Lmiui/date/Calendar;

    move-result-object v0

    iget-object v4, p0, Lmiui/widget/DatePicker$1;->eQ:Lmiui/widget/DatePicker;

    invoke-static {v4}, Lmiui/widget/DatePicker;->a(Lmiui/widget/DatePicker;)Lmiui/date/Calendar;

    move-result-object v4

    invoke-virtual {v4}, Lmiui/date/Calendar;->getTimeInMillis()J

    move-result-wide v4

    invoke-virtual {v0, v4, v5}, Lmiui/date/Calendar;->setTimeInMillis(J)Lmiui/date/Calendar;

    .line 161
    iget-object v0, p0, Lmiui/widget/DatePicker$1;->eQ:Lmiui/widget/DatePicker;

    invoke-static {v0}, Lmiui/widget/DatePicker;->c(Lmiui/widget/DatePicker;)Lmiui/widget/NumberPicker;

    move-result-object v0

    if-ne p1, v0, :cond_2

    .line 162
    iget-object v0, p0, Lmiui/widget/DatePicker$1;->eQ:Lmiui/widget/DatePicker;

    invoke-static {v0}, Lmiui/widget/DatePicker;->b(Lmiui/widget/DatePicker;)Lmiui/date/Calendar;

    move-result-object v4

    iget-object v0, p0, Lmiui/widget/DatePicker$1;->eQ:Lmiui/widget/DatePicker;

    invoke-static {v0}, Lmiui/widget/DatePicker;->d(Lmiui/widget/DatePicker;)Z

    move-result v0

    if-eqz v0, :cond_1

    const/16 v0, 0xa

    :goto_0
    sub-int v5, p3, p2

    invoke-virtual {v4, v0, v5}, Lmiui/date/Calendar;->add(II)Lmiui/date/Calendar;

    .line 171
    :goto_1
    iget-object v0, p0, Lmiui/widget/DatePicker$1;->eQ:Lmiui/widget/DatePicker;

    iget-object v4, p0, Lmiui/widget/DatePicker$1;->eQ:Lmiui/widget/DatePicker;

    invoke-static {v4}, Lmiui/widget/DatePicker;->b(Lmiui/widget/DatePicker;)Lmiui/date/Calendar;

    move-result-object v4

    invoke-virtual {v4, v3}, Lmiui/date/Calendar;->get(I)I

    move-result v3

    iget-object v4, p0, Lmiui/widget/DatePicker$1;->eQ:Lmiui/widget/DatePicker;

    invoke-static {v4}, Lmiui/widget/DatePicker;->b(Lmiui/widget/DatePicker;)Lmiui/date/Calendar;

    move-result-object v4

    invoke-virtual {v4, v2}, Lmiui/date/Calendar;->get(I)I

    move-result v2

    iget-object v4, p0, Lmiui/widget/DatePicker$1;->eQ:Lmiui/widget/DatePicker;

    invoke-static {v4}, Lmiui/widget/DatePicker;->b(Lmiui/widget/DatePicker;)Lmiui/date/Calendar;

    move-result-object v4

    invoke-virtual {v4, v1}, Lmiui/date/Calendar;->get(I)I

    move-result v1

    invoke-static {v0, v3, v2, v1}, Lmiui/widget/DatePicker;->a(Lmiui/widget/DatePicker;III)V

    .line 172
    iget-object v0, p0, Lmiui/widget/DatePicker$1;->eQ:Lmiui/widget/DatePicker;

    invoke-static {v0}, Lmiui/widget/DatePicker;->f(Lmiui/widget/DatePicker;)Lmiui/widget/NumberPicker;

    move-result-object v0

    if-ne p1, v0, :cond_0

    .line 173
    iget-object v0, p0, Lmiui/widget/DatePicker$1;->eQ:Lmiui/widget/DatePicker;

    invoke-static {v0}, Lmiui/widget/DatePicker;->g(Lmiui/widget/DatePicker;)V

    .line 175
    :cond_0
    iget-object v0, p0, Lmiui/widget/DatePicker$1;->eQ:Lmiui/widget/DatePicker;

    invoke-static {v0}, Lmiui/widget/DatePicker;->h(Lmiui/widget/DatePicker;)V

    .line 176
    iget-object v0, p0, Lmiui/widget/DatePicker$1;->eQ:Lmiui/widget/DatePicker;

    invoke-static {v0}, Lmiui/widget/DatePicker;->i(Lmiui/widget/DatePicker;)V

    .line 177
    return-void

    :cond_1
    move v0, v1

    .line 162
    goto :goto_0

    .line 163
    :cond_2
    iget-object v0, p0, Lmiui/widget/DatePicker$1;->eQ:Lmiui/widget/DatePicker;

    invoke-static {v0}, Lmiui/widget/DatePicker;->e(Lmiui/widget/DatePicker;)Lmiui/widget/NumberPicker;

    move-result-object v0

    if-ne p1, v0, :cond_4

    .line 164
    iget-object v0, p0, Lmiui/widget/DatePicker$1;->eQ:Lmiui/widget/DatePicker;

    invoke-static {v0}, Lmiui/widget/DatePicker;->b(Lmiui/widget/DatePicker;)Lmiui/date/Calendar;

    move-result-object v4

    iget-object v0, p0, Lmiui/widget/DatePicker$1;->eQ:Lmiui/widget/DatePicker;

    invoke-static {v0}, Lmiui/widget/DatePicker;->d(Lmiui/widget/DatePicker;)Z

    move-result v0

    if-eqz v0, :cond_3

    const/4 v0, 0x6

    :goto_2
    sub-int v5, p3, p2

    invoke-virtual {v4, v0, v5}, Lmiui/date/Calendar;->add(II)Lmiui/date/Calendar;

    goto :goto_1

    :cond_3
    move v0, v2

    goto :goto_2

    .line 165
    :cond_4
    iget-object v0, p0, Lmiui/widget/DatePicker$1;->eQ:Lmiui/widget/DatePicker;

    invoke-static {v0}, Lmiui/widget/DatePicker;->f(Lmiui/widget/DatePicker;)Lmiui/widget/NumberPicker;

    move-result-object v0

    if-ne p1, v0, :cond_6

    .line 166
    iget-object v0, p0, Lmiui/widget/DatePicker$1;->eQ:Lmiui/widget/DatePicker;

    invoke-static {v0}, Lmiui/widget/DatePicker;->b(Lmiui/widget/DatePicker;)Lmiui/date/Calendar;

    move-result-object v4

    iget-object v0, p0, Lmiui/widget/DatePicker$1;->eQ:Lmiui/widget/DatePicker;

    invoke-static {v0}, Lmiui/widget/DatePicker;->d(Lmiui/widget/DatePicker;)Z

    move-result v0

    if-eqz v0, :cond_5

    const/4 v0, 0x2

    :goto_3
    invoke-virtual {v4, v0, p3}, Lmiui/date/Calendar;->set(II)Lmiui/date/Calendar;

    goto :goto_1

    :cond_5
    move v0, v3

    goto :goto_3

    .line 168
    :cond_6
    new-instance v0, Ljava/lang/IllegalArgumentException;

    invoke-direct {v0}, Ljava/lang/IllegalArgumentException;-><init>()V

    throw v0
.end method
