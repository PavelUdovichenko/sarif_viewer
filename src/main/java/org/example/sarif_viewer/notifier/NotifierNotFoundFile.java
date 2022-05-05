package org.example.sarif_viewer.notifier;

import com.intellij.notification.Notification;
import com.intellij.notification.NotificationDisplayType;
import com.intellij.notification.NotificationGroup;
import com.intellij.notification.NotificationType;
import com.intellij.openapi.project.Project;
import com.sun.istack.Nullable;

public class NotifierNotFoundFile {
    public static final NotificationGroup BALLOON_GROUP =
            new NotificationGroup("demo.notifications.balloon",
            NotificationDisplayType.BALLOON, true);

    public static void notifyError(@Nullable Project project,
                                   String content) {
        Notification msg =
                BALLOON_GROUP.createNotification(
                        "Unable to find",
                        content,
                        "Source: sarif viewer (Extension)",
                        NotificationType.INFORMATION);
        msg.notify(project);
    }
}