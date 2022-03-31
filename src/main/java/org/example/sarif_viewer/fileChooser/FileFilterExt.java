package org.example.sarif_viewer.fileChooser;

public class FileFilterExt extends javax.swing.filechooser.FileFilter {
    String extension  ;  // расширение файла
    String description;  // описание типа файлов

    public FileFilterExt(String extension, String descr)
    {
        this.extension = extension;
        this.description = descr;
    }

    @Override
    public boolean accept(java.io.File file)
    {
        if(file != null) {
            if (file.isDirectory())
                return true;

            if(extension == null)
                return (extension.length() == 0);

            return file.getName().endsWith(extension);
        }
        return false;
    }

    // Функция описания типов файлов
    @Override
    public String getDescription() {
        return description;
    }
}
