package org.example.sarif_viewer.fileChooser;

import org.example.sarif_viewer.psi.FileWithPsiElement;

import javax.swing.*;
import java.io.File;
import java.util.Objects;

public class FileOpen {
    public static String pathFile = "";
    public static String openFile = "";

    public static void showDlg(String extension, String description, boolean checkNotification) {
        String basePathProject = Objects.requireNonNull(GetPathProject.getProject()).getBasePath();

        JFileChooser jFileChooser;
        if (basePathProject != null)
            jFileChooser = new JFileChooser(new File(basePathProject));
        else
            jFileChooser = new JFileChooser();

        jFileChooser.setDragEnabled(true);
        jFileChooser.setMultiSelectionEnabled(true);
        jFileChooser.setDialogTitle("Open file");

        // Определяем фильтры типов файлов
        FileFilterExt ff = new FileFilterExt(extension, description);
        jFileChooser.setAcceptAllFileFilterUsed(false);
        jFileChooser.setFileFilter(ff);

        // Определение режима - только файл
        jFileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);

        int result = jFileChooser.showDialog(null, "Open file");

        // Если файл выбран, покажем его в сообщении
        if (result == JFileChooser.APPROVE_OPTION ) {
            if (checkNotification)
                FileWithPsiElement.psiElement(jFileChooser.getSelectedFile().toString(), null);
            else {
                pathFile = jFileChooser.getSelectedFile().toString();
                openFile = jFileChooser.getSelectedFile().getName();
            }
        }
    }
}