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

    static boolean flag;
    static String fPath;

    public static Project getProject(final String repoBaseDirectory) {
        final Project[] openProjects = ProjectManager.getInstance().getOpenProjects();
        for (final Project project : openProjects) {
            return project;
        }
        return null;
    }

    public static PsiFile whatPsi(String fName) {
        File file = new File(fName);//создаём файл из того что имеем
        Path filePath = Paths.get(fName);
        //System.out.println("filePath:" + filePath);
        //System.out.println("fName:" + fName);
        Project project = getProject(fName);// получаем проект в котором этот файл существует
        //System.out.println("project:" + project);
        String dirPath = project.getBasePath();//поулчаем путь к текущему проекту
        //S//ystem.out.println(dirPath);
        getPath(dirPath, fName);//ищем в нашем проекте фаил с таким именем(не продумно если в разных директориях одинаковые файлы)
        //System.out.println("fPath:"+ fPath);
        //System.out.println("Needed path:"+ FileOpen.openFile);
        VirtualFile vFile = LocalFileSystem.getInstance().findFileByPath(fPath);//создаём виртуалочку нашего файла
        //System.out.println("vFile:" + vFile);
        PsiFile psiFile = PsiManager.getInstance(project).findFile(vFile);//создаём psi нашего виратульного
        //System.out.println(psiFile);

        if (vFile != null) {  //если файл успешно получен то открываем его в редакторе
            OpenFileDescriptor openFileDescriptor =
                    new OpenFileDescriptor(project, vFile, 0, 0);
            openFileDescriptor.navigate(true);
        } else {
            System.out.println("vFile is null");
        }
        return psiFile;
    }

    static void getPath(String path, String find) {
        File f = new File(path);
        String[] list = f.list();     //список файлов в текущей папке
        for (String file : list) {      //проверка на совпадение
            if (find.equals(file)) {
                flag = true;
                //System.out.println(path + "\\" + file + " !!!!!!!!!!!!!!!!!!");  //если найден, то выход
                fPath = (path + "\\" + file);
                return ;

            }
            if (!path.endsWith("\\")) {
                path += "\\";
            }
            File tempfile = new File(path, file);
            //System.out.println(path + file);
            if (!file.equals(".") && !file.equals("..")) {        //!!!
                if (tempfile.isDirectory()) {      //иначе проверяем, если это папка
                    //path += file;
                    getPath(path + file, find);               //то рекурсивный вызов этой функции
                    if (flag) return;
                }
            }
        }

    }
}