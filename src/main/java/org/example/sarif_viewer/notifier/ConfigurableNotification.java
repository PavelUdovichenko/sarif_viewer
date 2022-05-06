package org.example.sarif_viewer.notifier;

import com.intellij.notification.Notification;
import com.intellij.notification.NotificationListener;
import com.intellij.openapi.actionSystem.AnAction;
import org.jetbrains.annotations.NotNull;

class ConfigurableNotification extends Notification {
    @NotNull
    final NotificationConfig config;

    ConfigurableNotification(@NotNull NotificationConfig config) {
        super(config.group.getDisplayId(), config.title, config.content, config.notificationType, NotificationListener.URL_OPENING_LISTENER);
        this.config = config;

        setTitle(config.title, config.subtitle);
        setContent(config.content);
        setIcon(config.icon);

        if (config.dropdownText != null)
            setDropDownText(config.dropdownText);

        if (config.collapseDirection != null)
            setCollapseActionsDirection(config.collapseDirection);

        setContextHelpAction(config.contextHelpAction);

        for (AnAction action : config.actions) {
            addAction(action);
        }
    }
}