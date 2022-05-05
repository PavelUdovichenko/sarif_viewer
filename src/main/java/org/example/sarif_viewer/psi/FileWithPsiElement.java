package org.example.sarif_viewer.psi;

import com.intellij.openapi.editor.markup.*;
import com.intellij.openapi.fileEditor.*;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.LocalFileSystem;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.ui.JBColor;
import org.example.sarif_viewer.fileChooser.GetPathProject;
import org.example.sarif_viewer.notifier.ShowNotificationActivity;

import java.awt.*;
import java.util.ArrayList;

public class FileWithPsiElement {
    public static void psiElement(String fName, ArrayList<Integer> position) {
        Project project = GetPathProject.getProject(); // получаем проект в котором этот файл существует
//        String dirPath = Objects.requireNonNull(project).getBasePath(); // получаем путь к текущему проекту

        VirtualFile virtualFile = LocalFileSystem.getInstance().findFileByPath(fName); // создаём виртуалочку нашего файла

//        PsiFile psiFile = PsiManager.getInstance(project).findFile(Objects.requireNonNull(vFile)); // создаём psi нашего виратульного

        // открываем файл в редакторе
        if (project != null) {
            if (virtualFile != null) {
                OpenFileDescriptor openFileDescriptor;

                if (position != null) {
                    openFileDescriptor = new OpenFileDescriptor(project, virtualFile, position.get(0), position.get(1));
                    openFileDescriptor.navigate(true);
                    selectedText(project, virtualFile, position);
                } else {
                    openFileDescriptor = new OpenFileDescriptor(project, virtualFile);
                    openFileDescriptor.navigate(true);
                }
            } else {
                ShowNotificationActivity.notifyError(project, fName);
            }
        } else {
            ShowNotificationActivity.notifyError(project, fName);
        }
    }

    private static void selectedText(Project project, VirtualFile virtualFile, ArrayList<Integer> position) {
        TextAttributes textattributes = new TextAttributes(null, JBColor.ORANGE, null, EffectType.LINE_UNDERSCORE, Font.PLAIN);
        FileEditor[] editors = FileEditorManager.getInstance(project).getEditors(virtualFile);

        // придумать как выделять диапозон строк (от столбца в строке, до другого столбца в другой строке)
        for (FileEditor editor : editors) {
            MarkupModel markupModel = ((TextEditor) editor).getEditor().getMarkupModel();
            // пример как выглядит выделение
            markupModel.addRangeHighlighter(position.get(1), position.get(3), HighlighterLayer.WARNING, textattributes, HighlighterTargetArea.EXACT_RANGE);
            markupModel.addLineHighlighter(position.get(0), HighlighterLayer.CARET_ROW, textattributes);
        }
    }
}