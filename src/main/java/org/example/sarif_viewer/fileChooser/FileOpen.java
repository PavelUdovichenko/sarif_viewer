package org.example.sarif_viewer.fileChooser;

import javax.swing.*;

public class FileOpen {
    public static String pathFile = "";
    public static String openFile = "";

    public static void clickBtn() {
        JFileChooser jFileChooser = new JFileChooser();

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
        }
    }
}