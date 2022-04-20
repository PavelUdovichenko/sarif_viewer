package org.example.sarif_viewer.Psi;

import com.intellij.openapi.fileEditor.OpenFileDescriptor;
import com.intellij.openapi.fileTypes.FileType;
import com.intellij.openapi.fileTypes.FileTypeManager;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.project.ProjectManager;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.PsiFile;
import com.intellij.psi.PsiFileFactory;

import java.io.File;



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
        FileType fType = FileTypeManager.getInstance().getFileTypeByFileName(fName);
        Project project = getProject(fPath);// получаем проект в котором этот файл существует
        String fCont = file.toString();
        PsiFile psiFile = PsiFileFactory.getInstance(project).createFileFromText(fName, fType, fCont);// создаём psifile
        VirtualFile vFile = psiFile.getVirtualFile();                                                 // на основе открытого нами файла
        if (vFile != null) {                                                                          // для дальшейшей работы делаем из него виртулаьлный, но он почыему-то пустой видимоя  где-то косячу с psi ебал я корчое это всё
        OpenFileDescriptor openFileDescriptor =
                new OpenFileDescriptor(project, vFile, 0, 0);
        openFileDescriptor.navigate(true);
        }
        else {
            System.out.println("blya");
        }
        return psiFile;
    }
}