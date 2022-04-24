package org.example.sarif_viewer.fileChooser;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.project.ProjectManager;

public class GetPathProject {
    public static Project getProject() {
        final Project[] openProjects = ProjectManager.getInstance().getOpenProjects();

        for (final Project project : openProjects) {
            return project;
        }
        return null;
    }
}
