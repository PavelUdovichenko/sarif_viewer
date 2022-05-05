package org.example.sarif_viewer.notifier;

import com.intellij.notification.Notification;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.util.function.Consumer;

abstract class ConfigurableNotificationAction extends AnAction {
    ConfigurableNotificationAction(@Nullable String text, String description, Icon icon) {
        super(text, description, icon);
    }

    @NotNull
    static ConfigurableNotificationAction create(@NotNull Consumer<NotificationConfigBuilder> performAction) {
        return new ConfigurableNotificationAction("Locate...", "", com.intellij.icons.AllIcons.Actions.MenuOpen) {
            @Override
            public void actionPerformed(@NotNull AnActionEvent e) {
                ConfigurableNotification notification = (ConfigurableNotification) Notification.get(e);

                NotificationConfigBuilder builder = NotificationConfigBuilder.create(e.getProject(), notification.config);
                builder.resetActions();
                performAction.accept(builder);
                // add default actions, based on the current settings
//                builder.addDefaultActions();
//                builder.build().notify(e.getProject());
            }
        };
    }
}