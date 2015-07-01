.class public abstract Lcom/miui/internal/core/SdkManager;
.super Ljava/lang/Object;
.source "SourceFile"


# static fields
.field public static final RETURN_CODE_LOW_SDK_VERSION:I = 0x1

.field public static final RETURN_CODE_SUCCESS:I = 0x0

.field private static final TAG:Ljava/lang/String; = "miuisdk"

.field private static final yw:Ljava/lang/String; = "com.miui.internal.server.CoreService"


# direct methods
.method private constructor <init>()V
    .locals 0

    .prologue
    .line 62
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    .line 64
    return-void
.end method

.method private static a(Landroid/content/Context;Lcom/miui/internal/core/Manifest;)Z
    .locals 1

    .prologue
    .line 160
    new-instance v0, Lcom/miui/internal/core/CompatibleManager;

    invoke-direct {v0, p0, p1}, Lcom/miui/internal/core/CompatibleManager;-><init>(Landroid/content/Context;Lcom/miui/internal/core/Manifest;)V

    .line 161
    invoke-virtual {v0}, Lcom/miui/internal/core/CompatibleManager;->isCompatible()Z

    move-result v0

    return v0
.end method

.method private static b(Lcom/miui/internal/core/Manifest;)V
    .locals 0

    .prologue
    .line 165
    invoke-static {p0}, Lcom/miui/internal/component/ComponentManager;->load(Lcom/miui/internal/core/Manifest;)V

    .line 166
    return-void
.end method

.method private static buildProxy()V
    .locals 1

    .prologue
    .line 97
    invoke-static {}, Lcom/miui/internal/variable/Android_Content_Res_Resources_class$Factory;->getInstance()Lcom/miui/internal/variable/Android_Content_Res_Resources_class$Factory;

    move-result-object v0

    invoke-virtual {v0}, Lcom/miui/internal/variable/Android_Content_Res_Resources_class$Factory;->get()Lcom/miui/internal/variable/Android_Content_Res_Resources_class;

    move-result-object v0

    invoke-virtual {v0}, Lcom/miui/internal/variable/Android_Content_Res_Resources_class;->buildProxy()V

    .line 99
    invoke-static {}, Lcom/miui/internal/variable/Android_App_AlertDialog_class$Factory;->getInstance()Lcom/miui/internal/variable/Android_App_AlertDialog_class$Factory;

    move-result-object v0

    invoke-virtual {v0}, Lcom/miui/internal/variable/Android_App_AlertDialog_class$Factory;->get()Lcom/miui/internal/variable/Android_App_AlertDialog_class;

    move-result-object v0

    invoke-virtual {v0}, Lcom/miui/internal/variable/Android_App_AlertDialog_class;->buildProxy()V

    .line 100
    invoke-static {}, Lcom/miui/internal/variable/Android_App_AlertDialog_Builder_class$Factory;->getInstance()Lcom/miui/internal/variable/Android_App_AlertDialog_Builder_class$Factory;

    move-result-object v0

    invoke-virtual {v0}, Lcom/miui/internal/variable/Android_App_AlertDialog_Builder_class$Factory;->get()Lcom/miui/internal/variable/Android_App_AlertDialog_Builder_class;

    move-result-object v0

    invoke-virtual {v0}, Lcom/miui/internal/variable/Android_App_AlertDialog_Builder_class;->buildProxy()V

    .line 101
    invoke-static {}, Lcom/miui/internal/variable/Android_App_Activity_class$Factory;->getInstance()Lcom/miui/internal/variable/Android_App_Activity_class$Factory;

    move-result-object v0

    invoke-virtual {v0}, Lcom/miui/internal/variable/Android_App_Activity_class$Factory;->get()Lcom/miui/internal/variable/Android_App_Activity_class;

    move-result-object v0

    invoke-virtual {v0}, Lcom/miui/internal/variable/Android_App_Activity_class;->buildProxy()V

    .line 103
    invoke-static {}, Lcom/miui/internal/variable/Android_View_View_class$Factory;->getInstance()Lcom/miui/internal/variable/Android_View_View_class$Factory;

    move-result-object v0

    invoke-virtual {v0}, Lcom/miui/internal/variable/Android_View_View_class$Factory;->get()Lcom/miui/internal/variable/Android_View_View_class;

    move-result-object v0

    invoke-virtual {v0}, Lcom/miui/internal/variable/Android_View_View_class;->buildProxy()V

    .line 104
    invoke-static {}, Lcom/miui/internal/variable/Android_View_ViewGroup_class$Factory;->getInstance()Lcom/miui/internal/variable/Android_View_ViewGroup_class$Factory;

    move-result-object v0

    invoke-virtual {v0}, Lcom/miui/internal/variable/Android_View_ViewGroup_class$Factory;->get()Lcom/miui/internal/variable/Android_View_ViewGroup_class;

    move-result-object v0

    invoke-virtual {v0}, Lcom/miui/internal/variable/Android_View_ViewGroup_class;->buildProxy()V

    .line 106
    invoke-static {}, Lcom/miui/internal/variable/Android_Preference_Preference_class$Factory;->getInstance()Lcom/miui/internal/variable/Android_Preference_Preference_class$Factory;

    move-result-object v0

    invoke-virtual {v0}, Lcom/miui/internal/variable/Android_Preference_Preference_class$Factory;->get()Lcom/miui/internal/variable/Android_Preference_Preference_class;

    move-result-object v0

    invoke-virtual {v0}, Lcom/miui/internal/variable/Android_Preference_Preference_class;->buildProxy()V

    .line 107
    invoke-static {}, Lcom/miui/internal/variable/Android_Preference_PreferenceScreen_class$Factory;->getInstance()Lcom/miui/internal/variable/Android_Preference_PreferenceScreen_class$Factory;

    move-result-object v0

    invoke-virtual {v0}, Lcom/miui/internal/variable/Android_Preference_PreferenceScreen_class$Factory;->get()Lcom/miui/internal/variable/Android_Preference_PreferenceScreen_class;

    move-result-object v0

    invoke-virtual {v0}, Lcom/miui/internal/variable/Android_Preference_PreferenceScreen_class;->buildProxy()V

    .line 108
    invoke-static {}, Lcom/miui/internal/variable/Android_Preference_EditTextPreference_class$Factory;->getInstance()Lcom/miui/internal/variable/Android_Preference_EditTextPreference_class$Factory;

    move-result-object v0

    invoke-virtual {v0}, Lcom/miui/internal/variable/Android_Preference_EditTextPreference_class$Factory;->get()Lcom/miui/internal/variable/Android_Preference_EditTextPreference_class;

    move-result-object v0

    invoke-virtual {v0}, Lcom/miui/internal/variable/Android_Preference_EditTextPreference_class;->buildProxy()V

    .line 110
    invoke-static {}, Lcom/miui/internal/variable/Android_Widget_RemoteViews_class$Factory;->getInstance()Lcom/miui/internal/variable/Android_Widget_RemoteViews_class$Factory;

    move-result-object v0

    invoke-virtual {v0}, Lcom/miui/internal/variable/Android_Widget_RemoteViews_class$Factory;->get()Lcom/miui/internal/variable/Android_Widget_RemoteViews_class;

    move-result-object v0

    invoke-virtual {v0}, Lcom/miui/internal/variable/Android_Widget_RemoteViews_class;->buildProxy()V

    .line 111
    invoke-static {}, Lcom/miui/internal/variable/Android_Widget_AbsListView_class$Factory;->getInstance()Lcom/miui/internal/variable/Android_Widget_AbsListView_class$Factory;

    move-result-object v0

    invoke-virtual {v0}, Lcom/miui/internal/variable/Android_Widget_AbsListView_class$Factory;->get()Lcom/miui/internal/variable/Android_Widget_AbsListView_class;

    move-result-object v0

    invoke-virtual {v0}, Lcom/miui/internal/variable/Android_Widget_AbsListView_class;->buildProxy()V

    .line 112
    invoke-static {}, Lcom/miui/internal/variable/Android_Widget_ListView_class$Factory;->getInstance()Lcom/miui/internal/variable/Android_Widget_ListView_class$Factory;

    move-result-object v0

    invoke-virtual {v0}, Lcom/miui/internal/variable/Android_Widget_ListView_class$Factory;->get()Lcom/miui/internal/variable/Android_Widget_ListView_class;

    move-result-object v0

    invoke-virtual {v0}, Lcom/miui/internal/variable/Android_Widget_ListView_class;->buildProxy()V

    .line 113
    invoke-static {}, Lcom/miui/internal/variable/Android_Widget_GridView_class$Factory;->getInstance()Lcom/miui/internal/variable/Android_Widget_GridView_class$Factory;

    move-result-object v0

    invoke-virtual {v0}, Lcom/miui/internal/variable/Android_Widget_GridView_class$Factory;->get()Lcom/miui/internal/variable/Android_Widget_GridView_class;

    move-result-object v0

    invoke-virtual {v0}, Lcom/miui/internal/variable/Android_Widget_GridView_class;->buildProxy()V

    .line 114
    invoke-static {}, Lcom/miui/internal/variable/Android_Widget_ListPopupWindow_class$Factory;->getInstance()Lcom/miui/internal/variable/Android_Widget_ListPopupWindow_class$Factory;

    move-result-object v0

    invoke-virtual {v0}, Lcom/miui/internal/variable/Android_Widget_ListPopupWindow_class$Factory;->get()Lcom/miui/internal/variable/Android_Widget_ListPopupWindow_class;

    move-result-object v0

    invoke-virtual {v0}, Lcom/miui/internal/variable/Android_Widget_ListPopupWindow_class;->buildProxy()V

    .line 115
    invoke-static {}, Lcom/miui/internal/variable/Android_Widget_AbsSpinner_class$Factory;->getInstance()Lcom/miui/internal/variable/Android_Widget_AbsSpinner_class$Factory;

    move-result-object v0

    invoke-virtual {v0}, Lcom/miui/internal/variable/Android_Widget_AbsSpinner_class$Factory;->get()Lcom/miui/internal/variable/Android_Widget_AbsSpinner_class;

    move-result-object v0

    invoke-virtual {v0}, Lcom/miui/internal/variable/Android_Widget_AbsSpinner_class;->buildProxy()V

    .line 116
    invoke-static {}, Lcom/miui/internal/variable/Android_Widget_Spinner_class$Factory;->getInstance()Lcom/miui/internal/variable/Android_Widget_Spinner_class$Factory;

    move-result-object v0

    invoke-virtual {v0}, Lcom/miui/internal/variable/Android_Widget_Spinner_class$Factory;->get()Lcom/miui/internal/variable/Android_Widget_Spinner_class;

    move-result-object v0

    invoke-virtual {v0}, Lcom/miui/internal/variable/Android_Widget_Spinner_class;->buildProxy()V

    .line 117
    invoke-static {}, Lcom/miui/internal/variable/Android_Widget_PopupWindow_class$Factory;->getInstance()Lcom/miui/internal/variable/Android_Widget_PopupWindow_class$Factory;

    move-result-object v0

    invoke-virtual {v0}, Lcom/miui/internal/variable/Android_Widget_PopupWindow_class$Factory;->get()Lcom/miui/internal/variable/Android_Widget_PopupWindow_class;

    move-result-object v0

    invoke-virtual {v0}, Lcom/miui/internal/variable/Android_Widget_PopupWindow_class;->buildProxy()V

    .line 118
    invoke-static {}, Lcom/miui/internal/variable/Android_Graphics_Drawable_Drawable_class$Factory;->getInstance()Lcom/miui/internal/variable/Android_Graphics_Drawable_Drawable_class$Factory;

    move-result-object v0

    invoke-virtual {v0}, Lcom/miui/internal/variable/Android_Graphics_Drawable_Drawable_class$Factory;->get()Lcom/miui/internal/variable/Android_Graphics_Drawable_Drawable_class;

    move-result-object v0

    invoke-virtual {v0}, Lcom/miui/internal/variable/Android_Graphics_Drawable_Drawable_class;->buildProxy()V

    .line 120
    invoke-static {}, Lcom/miui/internal/variable/Android_Text_Util_Linkify_class$Factory;->getInstance()Lcom/miui/internal/variable/Android_Text_Util_Linkify_class$Factory;

    move-result-object v0

    invoke-virtual {v0}, Lcom/miui/internal/variable/Android_Text_Util_Linkify_class$Factory;->get()Lcom/miui/internal/variable/Android_Text_Util_Linkify_class;

    move-result-object v0

    invoke-virtual {v0}, Lcom/miui/internal/variable/Android_Text_Util_Linkify_class;->buildProxy()V

    .line 122
    invoke-static {}, Lcom/miui/internal/variable/Internal_Policy_Impl_PhoneWindow_class$Factory;->getInstance()Lcom/miui/internal/variable/Internal_Policy_Impl_PhoneWindow_class$Factory;

    move-result-object v0

    invoke-virtual {v0}, Lcom/miui/internal/variable/Internal_Policy_Impl_PhoneWindow_class$Factory;->get()Lcom/miui/internal/variable/Internal_Policy_Impl_PhoneWindow_class;

    move-result-object v0

    invoke-virtual {v0}, Lcom/miui/internal/variable/Internal_Policy_Impl_PhoneWindow_class;->buildProxy()V

    .line 123
    return-void
.end method

.method private static cw()V
    .locals 2

    .prologue
    .line 169
    new-instance v0, Landroid/os/Handler;

    invoke-direct {v0}, Landroid/os/Handler;-><init>()V

    new-instance v1, Lcom/miui/internal/core/SdkManager$1;

    invoke-direct {v1}, Lcom/miui/internal/core/SdkManager$1;-><init>()V

    invoke-virtual {v0, v1}, Landroid/os/Handler;->post(Ljava/lang/Runnable;)Z

    .line 177
    return-void
.end method

.method public static initialize(Landroid/app/Application;Ljava/util/Map;)I
    .locals 4
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Landroid/app/Application;",
            "Ljava/util/Map",
            "<",
            "Ljava/lang/String;",
            "Ljava/lang/Object;",
            ">;)I"
        }
    .end annotation

    .prologue
    const/4 v3, 0x0

    const/4 v2, 0x2

    .line 75
    const-class v1, Lcom/miui/internal/core/SdkManager;

    monitor-enter v1

    .line 76
    :try_start_0
    sget v0, Lcom/miui/internal/util/PackageConstants;->sSdkStatus:I

    if-lt v0, v2, :cond_0

    .line 77
    const-string v0, "miuisdk"

    const-string v2, "sdk already initialized"

    invoke-static {v0, v2}, Landroid/util/Log;->d(Ljava/lang/String;Ljava/lang/String;)I

    .line 78
    monitor-exit v1

    .line 92
    :goto_0
    return v3

    .line 81
    :cond_0
    sput-object p0, Lcom/miui/internal/util/PackageConstants;->sApplication:Landroid/app/Application;

    .line 83
    invoke-static {}, Lcom/miui/internal/util/PackageHelper;->isMiuiSystem()Z

    move-result v0

    if-nez v0, :cond_1

    sget-object v0, Lcom/miui/internal/util/PackageConstants;->sApplication:Landroid/app/Application;

    instance-of v0, v0, Lcom/miui/internal/app/MiuiApplication;

    if-nez v0, :cond_1

    .line 84
    sget-object v0, Lcom/miui/internal/util/PackageConstants;->RESOURCE_PATH:Ljava/lang/String;

    invoke-static {v0}, Lcom/miui/internal/component/ComponentManager;->loadResource(Ljava/lang/String;)V

    .line 87
    :cond_1
    invoke-static {}, Lcom/miui/internal/core/SdkManager;->buildProxy()V

    .line 89
    sget v0, Lcom/miui/internal/util/PackageConstants;->sSdkStatus:I

    if-ge v0, v2, :cond_2

    .line 90
    const/4 v0, 0x2

    sput v0, Lcom/miui/internal/util/PackageConstants;->sSdkStatus:I

    .line 92
    :cond_2
    monitor-exit v1

    goto :goto_0

    .line 93
    :catchall_0
    move-exception v0

    monitor-exit v1
    :try_end_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    throw v0
.end method

.method public static start(Ljava/util/Map;)I
    .locals 4
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Ljava/util/Map",
            "<",
            "Ljava/lang/String;",
            "Ljava/lang/Object;",
            ">;)I"
        }
    .end annotation

    .prologue
    const/4 v3, 0x3

    .line 134
    const-class v1, Lcom/miui/internal/core/SdkManager;

    monitor-enter v1

    .line 135
    :try_start_0
    sget v0, Lcom/miui/internal/util/PackageConstants;->sSdkStatus:I

    if-lt v0, v3, :cond_0

    .line 136
    const-string v0, "miuisdk"

    const-string v2, "sdk already started"

    invoke-static {v0, v2}, Landroid/util/Log;->d(Ljava/lang/String;Ljava/lang/String;)I

    .line 139
    :cond_0
    sget-object v0, Lcom/miui/internal/util/PackageConstants;->sApplication:Landroid/app/Application;

    invoke-static {v0}, Lcom/miui/internal/core/ManifestParser;->create(Landroid/content/Context;)Lcom/miui/internal/core/ManifestParser;

    move-result-object v0

    invoke-virtual {v0, p0}, Lcom/miui/internal/core/ManifestParser;->parse(Ljava/util/Map;)Lcom/miui/internal/core/Manifest;

    move-result-object v0

    sput-object v0, Lcom/miui/internal/util/PackageConstants;->sManifest:Lcom/miui/internal/core/Manifest;

    .line 141
    sget-object v0, Lcom/miui/internal/util/PackageConstants;->sApplication:Landroid/app/Application;

    instance-of v0, v0, Lcom/miui/internal/app/MiuiApplication;

    if-nez v0, :cond_1

    .line 142
    sget-object v0, Lcom/miui/internal/util/PackageConstants;->sApplication:Landroid/app/Application;

    sget-object v2, Lcom/miui/internal/util/PackageConstants;->sManifest:Lcom/miui/internal/core/Manifest;

    invoke-static {v0, v2}, Lcom/miui/internal/core/SdkManager;->a(Landroid/content/Context;Lcom/miui/internal/core/Manifest;)Z

    move-result v0

    .line 143
    if-nez v0, :cond_1

    .line 144
    const/4 v0, 0x1

    monitor-exit v1

    .line 155
    :goto_0
    return v0

    .line 148
    :cond_1
    sget-object v0, Lcom/miui/internal/util/PackageConstants;->sManifest:Lcom/miui/internal/core/Manifest;

    invoke-static {v0}, Lcom/miui/internal/core/SdkManager;->b(Lcom/miui/internal/core/Manifest;)V

    .line 150
    invoke-static {}, Lcom/miui/internal/core/SdkManager;->cw()V

    .line 152
    sget v0, Lcom/miui/internal/util/PackageConstants;->sSdkStatus:I

    if-ge v0, v3, :cond_2

    .line 153
    const/4 v0, 0x3

    sput v0, Lcom/miui/internal/util/PackageConstants;->sSdkStatus:I

    .line 155
    :cond_2
    const/4 v0, 0x0

    monitor-exit v1

    goto :goto_0

    .line 156
    :catchall_0
    move-exception v0

    monitor-exit v1
    :try_end_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    throw v0
.end method

.method public static supportUpdate(Ljava/util/Map;)Z
    .locals 1
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Ljava/util/Map",
            "<",
            "Ljava/lang/String;",
            "Ljava/lang/Object;",
            ">;)Z"
        }
    .end annotation

    .prologue
    .line 186
    const/4 v0, 0x0

    return v0
.end method

.method public static update(Ljava/util/Map;)Z
    .locals 1
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Ljava/util/Map",
            "<",
            "Ljava/lang/String;",
            "Ljava/lang/Object;",
            ">;)Z"
        }
    .end annotation

    .prologue
    .line 196
    const/4 v0, 0x0

    return v0
.end method
