package org.example.sarif_viewer.fileChooser;

import com.intellij.psi.PsiFile;

import javax.swing.*;
import java.io.File;

public class FileOpen {
    public static String pathFile = "";
    public static String openFile = "";
    private static PsiFile containingFile;
    public static File gFile;

    public static void clickBtn() {
        JFileChooser jFileChooser = new JFileChooser();
        jFileChooser.setDragEnabled(true);
        jFileChooser.setMultiSelectionEnabled(true);
        jFileChooser.setDialogTitle("Open file");

        // Определяем фильтры типов файлов
        FileFilterExt ff = new FileFilterExt("sarif", "SARIF-Files (*.sarif)");
        jFileChooser.setAcceptAllFileFilterUsed(false);
        jFileChooser.setFileFilter(ff);


        // Определение режима - только файл
        jFileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);

        int result = jFileChooser.showDialog(null, "Open file");

        // Если файл выбран, покажем его в сообщении
        if (result == JFileChooser.APPROVE_OPTION ) {
            pathFile = jFileChooser.getSelectedFile().toString();
            openFile = jFileChooser.getSelectedFile().getName();
            gFile = jFileChooser.getSelectedFile();
        }
    }




}
