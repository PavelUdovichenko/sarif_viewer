package org.example.sarif_viewer.notifier;

import com.intellij.notification.NotificationType;
import com.intellij.openapi.project.Project;
import org.jetbrains.annotations.NotNull;

public class ShowNotificationActivity {
    public static void notifyError(@NotNull Project project, String content) {
        NotificationConfigBuilder builder = NotificationConfigBuilder.create(project);

        builder.setNotificationType(NotificationType.INFORMATION);
        builder.setTitle("Unable to find");
        builder.setSubtitle(content);
        builder.setContent("Source: sarif viewer (Extension)");
        builder.addDefaultActions();
        builder.build().notify(project);
    }
}