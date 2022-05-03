package org.example.sarif_viewer.psi;

import com.intellij.openapi.fileEditor.OpenFileDescriptor;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.LocalFileSystem;
import com.intellij.openapi.vfs.VirtualFile;
import org.example.sarif_viewer.Notifier.MyNotifier;
import org.example.sarif_viewer.fileChooser.GetPathProject;

import java.util.ArrayList;

public class fileWithPsiElement {
    public static void psiElement(String fName, ArrayList<Integer> position) {
        Project project = GetPathProject.getProject(); // получаем проект в котором этот файл существует
//        String dirPath = Objects.requireNonNull(project).getBasePath(); // получаем путь к текущему проекту

        VirtualFile vFile = LocalFileSystem.getInstance().findFileByPath(fName); // создаём виртуалочку нашего файла

//        PsiFile psiFile = PsiManager.getInstance(project).findFile(Objects.requireNonNull(vFile)); // создаём psi нашего виратульного

        // открываем файл в редакторе
        OpenFileDescriptor openFileDescriptor;
        if (project != null) {
            if (vFile != null) {
                openFileDescriptor = new OpenFileDescriptor(project, vFile, position.get(0), position.get(1));
                openFileDescriptor.navigate(true);
            } else {
                MyNotifier.notifyError(project, "fName");
            }
        } else {
            MyNotifier.notifyError(null, fName);
        }
    }
}