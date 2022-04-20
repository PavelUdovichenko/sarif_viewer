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


public class MyPSI {
    //public static PsiFile psiFile;
    //public  static Project project;
    //public static PsiFile whatPsi;
    static Project project;

    public static Project getProject(final String repoBaseDirectory) {
        final Project[] openProjects = ProjectManager.getInstance().getOpenProjects();
        for (final Project project : openProjects) {
             return project;
        }
        return null;
    }
    public static PsiFile whatPsi(String fPath) {
        File file = new File(fPath);//создаём файл из того что имеем
        String fName = file.getName();
        Path filePath = Paths.get(fPath);
        System.out.println("filePath:" + filePath);
        System.out.println("fPath:" + fPath);
        String AbsfPath = filePath.toAbsolutePath().toString();
        System.out.println("AbsfPath:"+AbsfPath);
        VirtualFile vFile = LocalFileSystem.getInstance().findFileByPath(AbsfPath);
        Project project = getProject(fPath);// получаем проект в котором этот файл существует
        System.out.println("project:" + project);
        System.out.println("vFile:" + vFile);
        PsiFile psiFile = PsiManager.getInstance(project).findFile(vFile);
        System.out.println(psiFile);

        if (vFile != null) {                                                                          // для дальшейшей работы делаем из него виртулаьлный, но он почыему-то пустой видимоя  где-то косячу с psi ебал я корчое это всё
        OpenFileDescriptor openFileDescriptor =
                new OpenFileDescriptor(project, vFile, 0, 0);
        openFileDescriptor.navigate(true);
        }
        else {
            System.out.println("vFile is null");
        }
        return psiFile;
    }
}