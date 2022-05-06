package org.example.sarif_viewer.notifier;

import com.google.common.collect.Lists;
import com.intellij.notification.Notification;
import com.intellij.notification.NotificationDisplayType;
import com.intellij.notification.NotificationGroup;
import com.intellij.notification.NotificationType;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.project.Project;
import org.example.sarif_viewer.fileChooser.FileOpen;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.util.List;

class NotificationConfigBuilder {
    private static final NotificationGroup BALLOON_GROUP =
            new NotificationGroup("demo.notifications.stickyBalloon", NotificationDisplayType.BALLOON, true);

    private final Project project;

    @NotNull
    private String title = "";
    private String subtitle;
    @NotNull
    private String content = "";
    @Nullable
    private String dropdownText;
    @Nullable
    private Notification.CollapseActionsDirection collapseDirection;
    @Nullable
    private Icon icon;
    @NotNull
    private NotificationGroup group = BALLOON_GROUP;
    @NotNull
    private NotificationType notificationType = NotificationType.INFORMATION;
    private List<AnAction> actions = Lists.newArrayList();
    @Nullable
    private AnAction contextHelpAction;

    private NotificationConfigBuilder(Project project) {
        this.project = project;
    }

    static NotificationConfigBuilder create(Project project) {
        return new NotificationConfigBuilder(project);
    }

    static NotificationConfigBuilder create(Project project, NotificationConfig config) {
        NotificationConfigBuilder b = new NotificationConfigBuilder(project);

        b.title = config.title;
        b.subtitle = config.subtitle;
        b.content = config.content;
        b.dropdownText = config.dropdownText;
        b.collapseDirection = config.collapseDirection;
        b.icon = config.icon;
        b.group = config.group;
        b.notificationType = config.notificationType;
        b.contextHelpAction = config.contextHelpAction;

        b.actions.addAll(config.actions);

        return b;
    }

    public void setTitle(@NotNull String title) {
        this.title = title;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public void setContent(@NotNull String content) {
        this.content = content;
    }

    public void setDropdownText(@Nullable String dropdownText) {
        this.dropdownText = dropdownText;
    }

    public void setCollapseDirection(@Nullable Notification.CollapseActionsDirection collapseDirection) {
        this.collapseDirection = collapseDirection;
    }

    public void setIcon(@Nullable Icon icon) {
        this.icon = icon;
    }

    public void setGroup(@NotNull NotificationGroup group) {
        this.group = group;
    }

    public void setNotificationType(@NotNull NotificationType notificationType) {
        this.notificationType = notificationType;
    }

    public void addAction(AnAction actions) {
        this.actions.add(actions);
    }

    public void setContextHelpAction(@Nullable AnAction contextHelpAction) {
        this.contextHelpAction = contextHelpAction;
    }

    Notification build() {
        NotificationConfig config = new NotificationConfig(
                title,
                subtitle,
                content,
                dropdownText,
                collapseDirection,
                icon,
                group,
                notificationType,
                actions,
                contextHelpAction
        );

        return new ConfigurableNotification(config);
    }

    public void resetActions() {
        this.actions.clear();
    }

    public void addDefaultActions() {
        if (this.group.getDisplayType() == NotificationDisplayType.TOOL_WINDOW) {
            // toolwindow notifications don't support actions
            return;
        }

        addAction(ConfigurableNotificationAction.create(
                builder -> {
                    String filterExt = subtitle.split("\\.")[1];
                    FileOpen.showDlg(filterExt, filterExt.toUpperCase() + "-Files (*." + filterExt + ")", true);
                })
        );
    }
}