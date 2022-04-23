package org.example.sarif_viewer.psi;

import com.intellij.openapi.fileEditor.OpenFileDescriptor;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.project.ProjectManager;
import com.intellij.openapi.vfs.LocalFileSystem;
import com.intellij.openapi.vfs.VirtualFile;

import java.util.Objects;

public class fileWithPsiElement {
    public static Project getProject() {
        final Project[] openProjects = ProjectManager.getInstance().getOpenProjects();

        for (final Project project : openProjects) {
            return project;
        }

        return null;
    }

    public static void psiElement(String fName) {
        Project project = getProject(); // получаем проект в котором этот файл существует
//        String dirPath = Objects.requireNonNull(project).getBasePath(); // получаем путь к текущему проекту

        VirtualFile vFile = LocalFileSystem.getInstance().findFileByPath(fName); // создаём виртуалочку нашего файла
//        PsiFile psiFile = PsiManager.getInstance(project).findFile(Objects.requireNonNull(vFile)); // создаём psi нашего виратульного

        // открываем файл в редакторе
        OpenFileDescriptor openFileDescriptor = null;
        if (project != null) {
            if (vFile != null) {
                openFileDescriptor = new OpenFileDescriptor(project, vFile, 0, 0);
                openFileDescriptor.navigate(true);
            } else {
                System.out.println("file not found"); // сделать красивые уведомления (алерыт внутри idea)
            }
        } else {
            System.out.println("project not found"); // сделать красивые уведомления (алерыт внутри idea)
        }

    }
}