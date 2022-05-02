package org.example.sarif_viewer.Notifier;

import com.intellij.notification.Notification;
import com.intellij.notification.NotificationDisplayType;
import com.intellij.notification.NotificationGroup;
import com.intellij.notification.NotificationType;
import com.intellij.openapi.project.Project;
import com.sun.istack.Nullable;

public class MyNotifier {
    public static final NotificationGroup BALLOON_GROUP =
            new NotificationGroup("demo.notifications.balloon",
            NotificationDisplayType.BALLOON, true);


    public static void notifyError(@Nullable Project project,
                                   String content) {
        Notification msg =
                BALLOON_GROUP.createNotification(
                        "Title",
                        "Subtitle",
                        "Content",
                        NotificationType.ERROR);
        msg.notify(project);
    }

}