package org.example.sarif_viewer.Psi;

import com.intellij.openapi.fileEditor.OpenFileDescriptor;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.project.ProjectManager;
import com.intellij.openapi.vfs.LocalFileSystem;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.PsiFile;
import com.intellij.psi.PsiManager;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

public class fileWithPsiElement {
    public static Project getProject(final String repoBaseDirectory) {
        final Project[] openProjects = ProjectManager.getInstance().getOpenProjects();
        for (final Project project : openProjects) {
            return project;
        }
        return null;
    }

    public static void psiElement(String fName) {
        Project project = getProject(fName); // получаем проект в котором этот файл существует
//        String dirPath = Objects.requireNonNull(project).getBasePath(); // получаем путь к текущему проекту

        VirtualFile vFile = LocalFileSystem.getInstance().findFileByPath(fName); // создаём виртуалочку нашего файла
//        PsiFile psiFile = PsiManager.getInstance(project).findFile(Objects.requireNonNull(vFile)); // создаём psi нашего виратульного

        // открываем файл в редакторе
        OpenFileDescriptor openFileDescriptor = new OpenFileDescriptor(Objects.requireNonNull(project), Objects.requireNonNull(vFile), 0, 0);
        openFileDescriptor.navigate(true);
    }
}