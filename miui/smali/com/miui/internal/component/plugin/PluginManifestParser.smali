.class public Lcom/miui/internal/component/plugin/PluginManifestParser;
.super Ljava/lang/Object;
.source "SourceFile"


# static fields
.field protected static final ATTR_GROUP:Ljava/lang/String; = "group"

.field protected static final ATTR_LEVEL:Ljava/lang/String; = "level"

.field protected static final ATTR_LOCATION:Ljava/lang/String; = "location"

.field protected static final ATTR_MIN_CAPATIBLE_LEVEL:Ljava/lang/String; = "minCapatibleLevel"

.field protected static final ATTR_MIN_LEVEL:Ljava/lang/String; = "minLevel"

.field protected static final ATTR_NAME:Ljava/lang/String; = "name"

.field protected static final ATTR_OPTIONAL:Ljava/lang/String; = "optional"

.field protected static final ATTR_RESOURCES:Ljava/lang/String; = "resources"

.field protected static final ATTR_TARGET_LEVEL:Ljava/lang/String; = "targetLevel"

.field protected static final ATTR_VERSION_CODE:Ljava/lang/String; = "versionCode"

.field protected static final ATTR_VERSION_NAME:Ljava/lang/String; = "versionName"

.field protected static final ATTR_VISIBILITY:Ljava/lang/String; = "visibility"

.field protected static final ELEMENT_ACTIVITY_EXTENSION:Ljava/lang/String; = "activity-extension"

.field protected static final ELEMENT_ACTIVITY_OUTLET:Ljava/lang/String; = "activity-outlet"

.field protected static final ELEMENT_DEPENDENCIES:Ljava/lang/String; = "dependencies"

.field protected static final ELEMENT_DEPENDENCY:Ljava/lang/String; = "dependency"

.field protected static final ELEMENT_EXTENSIONS:Ljava/lang/String; = "extensions"

.field protected static final ELEMENT_FUNCTION_EXTENSION:Ljava/lang/String; = "function-extension"

.field protected static final ELEMENT_FUNCTION_OUTLET:Ljava/lang/String; = "function-outlet"

.field protected static final ELEMENT_OUTLETS:Ljava/lang/String; = "outlets"

.field protected static final ELEMENT_PLUGIN:Ljava/lang/String; = "plugin"


# direct methods
.method public constructor <init>()V
    .locals 0

    .prologue
    .line 22
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method

.method private a(Lorg/w3c/dom/Element;Ljava/lang/String;I)I
    .locals 1
    .annotation system Ldalvik/annotation/Throws;
        value = {
            Lcom/miui/internal/component/plugin/PluginParseException;
        }
    .end annotation

    .prologue
    .line 107
    invoke-static {p3}, Ljava/lang/String;->valueOf(I)Ljava/lang/String;

    move-result-object v0

    invoke-direct {p0, p1, p2, v0}, Lcom/miui/internal/component/plugin/PluginManifestParser;->a(Lorg/w3c/dom/Element;Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;

    move-result-object v0

    invoke-static {v0}, Ljava/lang/Integer;->parseInt(Ljava/lang/String;)I

    move-result v0

    return v0
.end method

.method private a(Lorg/w3c/dom/Element;Ljava/lang/String;Lcom/miui/internal/component/plugin/AccessPermission;)Lcom/miui/internal/component/plugin/AccessPermission;
    .locals 1
    .annotation system Ldalvik/annotation/Throws;
        value = {
            Lcom/miui/internal/component/plugin/PluginParseException;
        }
    .end annotation

    .prologue
    .line 116
    invoke-virtual {p3}, Lcom/miui/internal/component/plugin/AccessPermission;->name()Ljava/lang/String;

    move-result-object v0

    invoke-direct {p0, p1, p2, v0}, Lcom/miui/internal/component/plugin/PluginManifestParser;->a(Lorg/w3c/dom/Element;Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;

    move-result-object v0

    invoke-virtual {v0}, Ljava/lang/String;->toUpperCase()Ljava/lang/String;

    move-result-object v0

    invoke-static {v0}, Lcom/miui/internal/component/plugin/AccessPermission;->valueOf(Ljava/lang/String;)Lcom/miui/internal/component/plugin/AccessPermission;

    move-result-object v0

    return-object v0
.end method

.method private a(Lorg/w3c/dom/Element;Ljava/lang/String;Lcom/miui/internal/component/plugin/Demand;)Lcom/miui/internal/component/plugin/Demand;
    .locals 1
    .annotation system Ldalvik/annotation/Throws;
        value = {
            Lcom/miui/internal/component/plugin/PluginParseException;
        }
    .end annotation

    .prologue
    .line 111
    invoke-virtual {p3}, Lcom/miui/internal/component/plugin/Demand;->name()Ljava/lang/String;

    move-result-object v0

    invoke-direct {p0, p1, p2, v0}, Lcom/miui/internal/component/plugin/PluginManifestParser;->a(Lorg/w3c/dom/Element;Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;

    move-result-object v0

    invoke-virtual {v0}, Ljava/lang/String;->toUpperCase()Ljava/lang/String;

    move-result-object v0

    invoke-static {v0}, Lcom/miui/internal/component/plugin/Demand;->valueOf(Ljava/lang/String;)Lcom/miui/internal/component/plugin/Demand;

    move-result-object v0

    return-object v0
.end method

.method private a(Lorg/w3c/dom/Element;Ljava/lang/String;)Ljava/lang/String;
    .locals 2
    .annotation system Ldalvik/annotation/Throws;
        value = {
            Lcom/miui/internal/component/plugin/PluginParseException;
        }
    .end annotation

    .prologue
    .line 95
    const/4 v0, 0x1

    const/4 v1, 0x0

    invoke-direct {p0, p1, p2, v0, v1}, Lcom/miui/internal/component/plugin/PluginManifestParser;->a(Lorg/w3c/dom/Element;Ljava/lang/String;ZLjava/lang/String;)Ljava/lang/String;

    move-result-object v0

    return-object v0
.end method

.method private a(Lorg/w3c/dom/Element;Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
    .locals 1
    .annotation system Ldalvik/annotation/Throws;
        value = {
            Lcom/miui/internal/component/plugin/PluginParseException;
        }
    .end annotation

    .prologue
    .line 99
    const/4 v0, 0x0

    invoke-direct {p0, p1, p2, v0, p3}, Lcom/miui/internal/component/plugin/PluginManifestParser;->a(Lorg/w3c/dom/Element;Ljava/lang/String;ZLjava/lang/String;)Ljava/lang/String;

    move-result-object v0

    return-object v0
.end method

.method private a(Lorg/w3c/dom/Element;Ljava/lang/String;ZLjava/lang/String;)Ljava/lang/String;
    .locals 3
    .annotation system Ldalvik/annotation/Throws;
        value = {
            Lcom/miui/internal/component/plugin/PluginParseException;
        }
    .end annotation

    .prologue
    .line 83
    invoke-interface {p1, p2}, Lorg/w3c/dom/Element;->getAttribute(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v0

    .line 84
    if-eqz v0, :cond_0

    const-string v1, ""

    invoke-virtual {v1, v0}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v1

    if-eqz v1, :cond_1

    .line 85
    :cond_0
    if-eqz p3, :cond_2

    .line 86
    new-instance v0, Lcom/miui/internal/component/plugin/PluginParseException;

    new-instance v1, Ljava/lang/StringBuilder;

    invoke-direct {v1}, Ljava/lang/StringBuilder;-><init>()V

    const-string v2, "missing attribute "

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v1

    invoke-virtual {v1, p2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v1

    const-string v2, " in element "

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v1

    invoke-interface {p1}, Lorg/w3c/dom/Element;->getTagName()Ljava/lang/String;

    move-result-object v2

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v1

    invoke-virtual {v1}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v1

    invoke-direct {v0, v1}, Lcom/miui/internal/component/plugin/PluginParseException;-><init>(Ljava/lang/String;)V

    throw v0

    :cond_1
    move-object p4, v0

    .line 91
    :cond_2
    return-object p4
.end method

.method private a(Lorg/w3c/dom/Element;Lcom/miui/internal/component/plugin/Plugin;)V
    .locals 5
    .annotation system Ldalvik/annotation/Throws;
        value = {
            Lcom/miui/internal/component/plugin/PluginParseException;
        }
    .end annotation

    .prologue
    const/4 v0, 0x0

    .line 120
    const-string v1, "name"

    invoke-direct {p0, p1, v1}, Lcom/miui/internal/component/plugin/PluginManifestParser;->a(Lorg/w3c/dom/Element;Ljava/lang/String;)Ljava/lang/String;

    move-result-object v1

    invoke-virtual {p2, v1}, Lcom/miui/internal/component/plugin/Plugin;->setName(Ljava/lang/String;)V

    .line 121
    const-string v1, "group"

    invoke-virtual {p2}, Lcom/miui/internal/component/plugin/Plugin;->getName()Ljava/lang/String;

    move-result-object v2

    invoke-direct {p0, p1, v1, v2}, Lcom/miui/internal/component/plugin/PluginManifestParser;->a(Lorg/w3c/dom/Element;Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;

    move-result-object v1

    invoke-virtual {p2, v1}, Lcom/miui/internal/component/plugin/Plugin;->setGroup(Ljava/lang/String;)V

    .line 122
    const-string v1, "versionCode"

    invoke-direct {p0, p1, v1, v0}, Lcom/miui/internal/component/plugin/PluginManifestParser;->a(Lorg/w3c/dom/Element;Ljava/lang/String;I)I

    move-result v1

    invoke-virtual {p2, v1}, Lcom/miui/internal/component/plugin/Plugin;->setVersionCode(I)V

    .line 123
    const-string v1, "versionName"

    invoke-virtual {p2}, Lcom/miui/internal/component/plugin/Plugin;->getVersionCode()I

    move-result v2

    invoke-static {v2}, Ljava/lang/String;->valueOf(I)Ljava/lang/String;

    move-result-object v2

    invoke-direct {p0, p1, v1, v2}, Lcom/miui/internal/component/plugin/PluginManifestParser;->a(Lorg/w3c/dom/Element;Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;

    move-result-object v1

    invoke-virtual {p2, v1}, Lcom/miui/internal/component/plugin/Plugin;->setVersionName(Ljava/lang/String;)V

    .line 124
    const-string v1, "level"

    invoke-virtual {p2}, Lcom/miui/internal/component/plugin/Plugin;->getVersionCode()I

    move-result v2

    invoke-direct {p0, p1, v1, v2}, Lcom/miui/internal/component/plugin/PluginManifestParser;->a(Lorg/w3c/dom/Element;Ljava/lang/String;I)I

    move-result v1

    invoke-virtual {p2, v1}, Lcom/miui/internal/component/plugin/Plugin;->setLevel(I)V

    .line 125
    const-string v1, "minCapatibleLevel"

    invoke-virtual {p2}, Lcom/miui/internal/component/plugin/Plugin;->getLevel()I

    move-result v2

    invoke-direct {p0, p1, v1, v2}, Lcom/miui/internal/component/plugin/PluginManifestParser;->a(Lorg/w3c/dom/Element;Ljava/lang/String;I)I

    move-result v1

    invoke-virtual {p2, v1}, Lcom/miui/internal/component/plugin/Plugin;->setMinCapatibleLevel(I)V

    .line 126
    const-string v1, "resources"

    sget-object v2, Lcom/miui/internal/component/plugin/AccessPermission;->PRIVATE:Lcom/miui/internal/component/plugin/AccessPermission;

    invoke-direct {p0, p1, v1, v2}, Lcom/miui/internal/component/plugin/PluginManifestParser;->a(Lorg/w3c/dom/Element;Ljava/lang/String;Lcom/miui/internal/component/plugin/AccessPermission;)Lcom/miui/internal/component/plugin/AccessPermission;

    move-result-object v1

    invoke-virtual {p2, v1}, Lcom/miui/internal/component/plugin/Plugin;->setResources(Lcom/miui/internal/component/plugin/AccessPermission;)V

    .line 127
    invoke-interface {p1}, Lorg/w3c/dom/Element;->getChildNodes()Lorg/w3c/dom/NodeList;

    move-result-object v2

    move v1, v0

    .line 128
    :goto_0
    invoke-interface {v2}, Lorg/w3c/dom/NodeList;->getLength()I

    move-result v0

    if-ge v1, v0, :cond_3

    .line 129
    invoke-interface {v2, v1}, Lorg/w3c/dom/NodeList;->item(I)Lorg/w3c/dom/Node;

    move-result-object v0

    .line 130
    invoke-interface {v0}, Lorg/w3c/dom/Node;->getNodeType()S

    move-result v3

    const/4 v4, 0x1

    if-ne v3, v4, :cond_0

    .line 131
    check-cast v0, Lorg/w3c/dom/Element;

    .line 132
    invoke-interface {v0}, Lorg/w3c/dom/Element;->getTagName()Ljava/lang/String;

    move-result-object v3

    .line 133
    const-string v4, "dependencies"

    invoke-virtual {v4, v3}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v4

    if-eqz v4, :cond_1

    .line 134
    invoke-direct {p0, v0, p2}, Lcom/miui/internal/component/plugin/PluginManifestParser;->b(Lorg/w3c/dom/Element;Lcom/miui/internal/component/plugin/Plugin;)V

    .line 128
    :cond_0
    :goto_1
    add-int/lit8 v0, v1, 0x1

    move v1, v0

    goto :goto_0

    .line 135
    :cond_1
    const-string v4, "extensions"

    invoke-virtual {v4, v3}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v4

    if-eqz v4, :cond_2

    .line 136
    invoke-direct {p0, v0, p2}, Lcom/miui/internal/component/plugin/PluginManifestParser;->c(Lorg/w3c/dom/Element;Lcom/miui/internal/component/plugin/Plugin;)V

    goto :goto_1

    .line 137
    :cond_2
    const-string v4, "outlets"

    invoke-virtual {v4, v3}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v3

    if-eqz v3, :cond_0

    .line 138
    invoke-direct {p0, v0, p2}, Lcom/miui/internal/component/plugin/PluginManifestParser;->d(Lorg/w3c/dom/Element;Lcom/miui/internal/component/plugin/Plugin;)V

    goto :goto_1

    .line 142
    :cond_3
    return-void
.end method

.method private a(Lorg/w3c/dom/Element;Ljava/lang/String;Z)Z
    .locals 1
    .annotation system Ldalvik/annotation/Throws;
        value = {
            Lcom/miui/internal/component/plugin/PluginParseException;
        }
    .end annotation

    .prologue
    .line 103
    invoke-static {p3}, Ljava/lang/String;->valueOf(Z)Ljava/lang/String;

    move-result-object v0

    invoke-direct {p0, p1, p2, v0}, Lcom/miui/internal/component/plugin/PluginManifestParser;->a(Lorg/w3c/dom/Element;Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;

    move-result-object v0

    invoke-static {v0}, Ljava/lang/Boolean;->parseBoolean(Ljava/lang/String;)Z

    move-result v0

    return v0
.end method

.method private b(Lorg/w3c/dom/Element;Lcom/miui/internal/component/plugin/Plugin;)V
    .locals 8
    .annotation system Ldalvik/annotation/Throws;
        value = {
            Lcom/miui/internal/component/plugin/PluginParseException;
        }
    .end annotation

    .prologue
    const/4 v4, 0x1

    const/4 v2, 0x0

    .line 145
    invoke-interface {p1}, Lorg/w3c/dom/Element;->getChildNodes()Lorg/w3c/dom/NodeList;

    move-result-object v5

    move v1, v2

    .line 146
    :goto_0
    invoke-interface {v5}, Lorg/w3c/dom/NodeList;->getLength()I

    move-result v0

    if-ge v1, v0, :cond_2

    .line 147
    invoke-interface {v5, v1}, Lorg/w3c/dom/NodeList;->item(I)Lorg/w3c/dom/Node;

    move-result-object v0

    .line 148
    invoke-interface {v0}, Lorg/w3c/dom/Node;->getNodeType()S

    move-result v3

    if-ne v3, v4, :cond_0

    .line 149
    check-cast v0, Lorg/w3c/dom/Element;

    .line 150
    invoke-interface {v0}, Lorg/w3c/dom/Element;->getTagName()Ljava/lang/String;

    move-result-object v3

    .line 151
    const-string v6, "dependency"

    invoke-virtual {v6, v3}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v3

    if-eqz v3, :cond_0

    .line 152
    new-instance v6, Lcom/miui/internal/component/plugin/Dependency;

    invoke-direct {v6}, Lcom/miui/internal/component/plugin/Dependency;-><init>()V

    .line 153
    const-string v3, "name"

    invoke-direct {p0, v0, v3}, Lcom/miui/internal/component/plugin/PluginManifestParser;->a(Lorg/w3c/dom/Element;Ljava/lang/String;)Ljava/lang/String;

    move-result-object v3

    invoke-virtual {v6, v3}, Lcom/miui/internal/component/plugin/Dependency;->setName(Ljava/lang/String;)V

    .line 154
    const-string v3, "resources"

    sget-object v7, Lcom/miui/internal/component/plugin/Demand;->PROHIBITED:Lcom/miui/internal/component/plugin/Demand;

    invoke-direct {p0, v0, v3, v7}, Lcom/miui/internal/component/plugin/PluginManifestParser;->a(Lorg/w3c/dom/Element;Ljava/lang/String;Lcom/miui/internal/component/plugin/Demand;)Lcom/miui/internal/component/plugin/Demand;

    move-result-object v3

    sget-object v7, Lcom/miui/internal/component/plugin/Demand;->REQUIRED:Lcom/miui/internal/component/plugin/Demand;

    if-ne v3, v7, :cond_1

    move v3, v4

    :goto_1
    invoke-virtual {v6, v3}, Lcom/miui/internal/component/plugin/Dependency;->setResourcesRequired(Z)V

    .line 155
    const-string v3, "optional"

    invoke-direct {p0, v0, v3, v2}, Lcom/miui/internal/component/plugin/PluginManifestParser;->a(Lorg/w3c/dom/Element;Ljava/lang/String;Z)Z

    move-result v3

    invoke-virtual {v6, v3}, Lcom/miui/internal/component/plugin/Dependency;->setOptional(Z)V

    .line 156
    const-string v3, "minLevel"

    invoke-direct {p0, v0, v3, v2}, Lcom/miui/internal/component/plugin/PluginManifestParser;->a(Lorg/w3c/dom/Element;Ljava/lang/String;I)I

    move-result v3

    invoke-virtual {v6, v3}, Lcom/miui/internal/component/plugin/Dependency;->setMinLevel(I)V

    .line 157
    const-string v3, "targetLevel"

    invoke-direct {p0, v0, v3, v2}, Lcom/miui/internal/component/plugin/PluginManifestParser;->a(Lorg/w3c/dom/Element;Ljava/lang/String;I)I

    move-result v0

    invoke-virtual {v6, v0}, Lcom/miui/internal/component/plugin/Dependency;->setTargetLevel(I)V

    .line 158
    invoke-virtual {p2, v6}, Lcom/miui/internal/component/plugin/Plugin;->addDependency(Lcom/miui/internal/component/plugin/Dependency;)V

    .line 146
    :cond_0
    add-int/lit8 v0, v1, 0x1

    move v1, v0

    goto :goto_0

    :cond_1
    move v3, v2

    .line 154
    goto :goto_1

    .line 162
    :cond_2
    return-void
.end method

.method private c(Lorg/w3c/dom/Element;Lcom/miui/internal/component/plugin/Plugin;)V
    .locals 5
    .annotation system Ldalvik/annotation/Throws;
        value = {
            Lcom/miui/internal/component/plugin/PluginParseException;
        }
    .end annotation

    .prologue
    .line 165
    invoke-interface {p1}, Lorg/w3c/dom/Element;->getChildNodes()Lorg/w3c/dom/NodeList;

    move-result-object v2

    .line 166
    const/4 v0, 0x0

    move v1, v0

    :goto_0
    invoke-interface {v2}, Lorg/w3c/dom/NodeList;->getLength()I

    move-result v0

    if-ge v1, v0, :cond_2

    .line 167
    invoke-interface {v2, v1}, Lorg/w3c/dom/NodeList;->item(I)Lorg/w3c/dom/Node;

    move-result-object v0

    .line 168
    invoke-interface {v0}, Lorg/w3c/dom/Node;->getNodeType()S

    move-result v3

    const/4 v4, 0x1

    if-ne v3, v4, :cond_1

    .line 169
    check-cast v0, Lorg/w3c/dom/Element;

    .line 170
    invoke-interface {v0}, Lorg/w3c/dom/Element;->getTagName()Ljava/lang/String;

    move-result-object v3

    .line 171
    const-string v4, "activity-extension"

    invoke-virtual {v4, v3}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v4

    if-nez v4, :cond_0

    const-string v4, "function-extension"

    invoke-virtual {v4, v3}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v3

    if-eqz v3, :cond_1

    .line 172
    :cond_0
    new-instance v3, Lcom/miui/internal/component/plugin/Extension;

    invoke-direct {v3}, Lcom/miui/internal/component/plugin/Extension;-><init>()V

    .line 173
    const-string v4, "name"

    invoke-direct {p0, v0, v4}, Lcom/miui/internal/component/plugin/PluginManifestParser;->a(Lorg/w3c/dom/Element;Ljava/lang/String;)Ljava/lang/String;

    move-result-object v4

    invoke-virtual {v3, v4}, Lcom/miui/internal/component/plugin/Extension;->setName(Ljava/lang/String;)V

    .line 174
    const-string v4, "location"

    invoke-direct {p0, v0, v4}, Lcom/miui/internal/component/plugin/PluginManifestParser;->a(Lorg/w3c/dom/Element;Ljava/lang/String;)Ljava/lang/String;

    move-result-object v0

    invoke-virtual {v3, v0}, Lcom/miui/internal/component/plugin/Extension;->setLocation(Ljava/lang/String;)V

    .line 175
    invoke-virtual {p2, v3}, Lcom/miui/internal/component/plugin/Plugin;->addExtension(Lcom/miui/internal/component/plugin/Extension;)V

    .line 166
    :cond_1
    add-int/lit8 v0, v1, 0x1

    move v1, v0

    goto :goto_0

    .line 179
    :cond_2
    return-void
.end method

.method private d(Lorg/w3c/dom/Element;Lcom/miui/internal/component/plugin/Plugin;)V
    .locals 5
    .annotation system Ldalvik/annotation/Throws;
        value = {
            Lcom/miui/internal/component/plugin/PluginParseException;
        }
    .end annotation

    .prologue
    .line 182
    invoke-interface {p1}, Lorg/w3c/dom/Element;->getChildNodes()Lorg/w3c/dom/NodeList;

    move-result-object v2

    .line 183
    const/4 v0, 0x0

    move v1, v0

    :goto_0
    invoke-interface {v2}, Lorg/w3c/dom/NodeList;->getLength()I

    move-result v0

    if-ge v1, v0, :cond_2

    .line 184
    invoke-interface {v2, v1}, Lorg/w3c/dom/NodeList;->item(I)Lorg/w3c/dom/Node;

    move-result-object v0

    .line 185
    invoke-interface {v0}, Lorg/w3c/dom/Node;->getNodeType()S

    move-result v3

    const/4 v4, 0x1

    if-ne v3, v4, :cond_1

    .line 186
    check-cast v0, Lorg/w3c/dom/Element;

    .line 187
    invoke-interface {v0}, Lorg/w3c/dom/Element;->getTagName()Ljava/lang/String;

    move-result-object v3

    .line 188
    const-string v4, "activity-outlet"

    invoke-virtual {v4, v3}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v4

    if-nez v4, :cond_0

    const-string v4, "function-outlet"

    invoke-virtual {v4, v3}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v3

    if-eqz v3, :cond_1

    .line 189
    :cond_0
    new-instance v3, Lcom/miui/internal/component/plugin/Outlet;

    invoke-direct {v3}, Lcom/miui/internal/component/plugin/Outlet;-><init>()V

    .line 190
    const-string v4, "name"

    invoke-direct {p0, v0, v4}, Lcom/miui/internal/component/plugin/PluginManifestParser;->a(Lorg/w3c/dom/Element;Ljava/lang/String;)Ljava/lang/String;

    move-result-object v0

    invoke-virtual {v3, v0}, Lcom/miui/internal/component/plugin/Outlet;->setName(Ljava/lang/String;)V

    .line 191
    const-string v0, "visibility"

    sget-object v4, Lcom/miui/internal/component/plugin/AccessPermission;->PUBLIC:Lcom/miui/internal/component/plugin/AccessPermission;

    invoke-direct {p0, p1, v0, v4}, Lcom/miui/internal/component/plugin/PluginManifestParser;->a(Lorg/w3c/dom/Element;Ljava/lang/String;Lcom/miui/internal/component/plugin/AccessPermission;)Lcom/miui/internal/component/plugin/AccessPermission;

    move-result-object v0

    invoke-virtual {v3, v0}, Lcom/miui/internal/component/plugin/Outlet;->setVisibility(Lcom/miui/internal/component/plugin/AccessPermission;)V

    .line 192
    invoke-virtual {p2, v3}, Lcom/miui/internal/component/plugin/Plugin;->addOutlet(Lcom/miui/internal/component/plugin/Outlet;)V

    .line 183
    :cond_1
    add-int/lit8 v0, v1, 0x1

    move v1, v0

    goto :goto_0

    .line 196
    :cond_2
    return-void
.end method


# virtual methods
.method public parsePlugin(Ljava/io/File;)Lcom/miui/internal/component/plugin/Plugin;
    .locals 1
    .annotation system Ldalvik/annotation/Throws;
        value = {
            Ljava/io/IOException;,
            Lcom/miui/internal/component/plugin/PluginParseException;
        }
    .end annotation

    .prologue
    .line 58
    new-instance v0, Ljava/io/FileInputStream;

    invoke-direct {v0, p1}, Ljava/io/FileInputStream;-><init>(Ljava/io/File;)V

    invoke-virtual {p0, v0}, Lcom/miui/internal/component/plugin/PluginManifestParser;->parsePlugin(Ljava/io/InputStream;)Lcom/miui/internal/component/plugin/Plugin;

    move-result-object v0

    return-object v0
.end method

.method public parsePlugin(Ljava/io/InputStream;)Lcom/miui/internal/component/plugin/Plugin;
    .locals 2
    .annotation system Ldalvik/annotation/Throws;
        value = {
            Ljava/io/IOException;,
            Lcom/miui/internal/component/plugin/PluginParseException;
        }
    .end annotation

    .prologue
    .line 62
    new-instance v0, Lcom/miui/internal/component/plugin/Plugin;

    invoke-direct {v0}, Lcom/miui/internal/component/plugin/Plugin;-><init>()V

    .line 64
    :try_start_0
    invoke-static {}, Ljavax/xml/parsers/DocumentBuilderFactory;->newInstance()Ljavax/xml/parsers/DocumentBuilderFactory;

    move-result-object v1

    .line 65
    invoke-virtual {v1}, Ljavax/xml/parsers/DocumentBuilderFactory;->newDocumentBuilder()Ljavax/xml/parsers/DocumentBuilder;

    move-result-object v1

    .line 66
    invoke-virtual {v1, p1}, Ljavax/xml/parsers/DocumentBuilder;->parse(Ljava/io/InputStream;)Lorg/w3c/dom/Document;

    move-result-object v1

    .line 67
    invoke-interface {v1}, Lorg/w3c/dom/Document;->getDocumentElement()Lorg/w3c/dom/Element;

    move-result-object v1

    invoke-direct {p0, v1, v0}, Lcom/miui/internal/component/plugin/PluginManifestParser;->a(Lorg/w3c/dom/Element;Lcom/miui/internal/component/plugin/Plugin;)V
    :try_end_0
    .catch Ljavax/xml/parsers/ParserConfigurationException; {:try_start_0 .. :try_end_0} :catch_0
    .catch Lorg/xml/sax/SAXException; {:try_start_0 .. :try_end_0} :catch_1
    .catch Ljava/lang/Exception; {:try_start_0 .. :try_end_0} :catch_2

    .line 78
    return-object v0

    .line 68
    :catch_0
    move-exception v0

    .line 69
    invoke-virtual {v0}, Ljavax/xml/parsers/ParserConfigurationException;->printStackTrace()V

    .line 70
    new-instance v0, Lcom/miui/internal/component/plugin/PluginParseException;

    invoke-direct {v0}, Lcom/miui/internal/component/plugin/PluginParseException;-><init>()V

    throw v0

    .line 71
    :catch_1
    move-exception v0

    .line 72
    invoke-virtual {v0}, Lorg/xml/sax/SAXException;->printStackTrace()V

    .line 73
    new-instance v0, Lcom/miui/internal/component/plugin/PluginParseException;

    invoke-direct {v0}, Lcom/miui/internal/component/plugin/PluginParseException;-><init>()V

    throw v0

    .line 74
    :catch_2
    move-exception v0

    .line 75
    invoke-virtual {v0}, Ljava/lang/Exception;->printStackTrace()V

    .line 76
    new-instance v0, Lcom/miui/internal/component/plugin/PluginParseException;

    invoke-direct {v0}, Lcom/miui/internal/component/plugin/PluginParseException;-><init>()V

    throw v0
.end method
