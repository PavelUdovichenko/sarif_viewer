package org.example.sarif_viewer.psi;

import com.intellij.openapi.editor.markup.*;
import com.intellij.openapi.fileEditor.FileEditor;
import com.intellij.openapi.fileEditor.FileEditorManager;
import com.intellij.openapi.fileEditor.OpenFileDescriptor;
import com.intellij.openapi.fileEditor.TextEditor;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.LocalFileSystem;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.ui.JBColor;
import org.example.sarif_viewer.fileChooser.GetPathProject;
import org.example.sarif_viewer.notifier.ShowNotificationActivity;

import java.awt.*;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Objects;

public class FileWithPsiElement {
    public static void psiElement(String fName, ArrayList<Integer> position) {
        Project project = GetPathProject.getProject(); // получаем проект в котором этот файл существует

        String newPath = fName.replace("file:///",""); // временная перменаная под путь
        Path usedPath = Path.of(newPath);

        if (!usedPath.isAbsolute()) // если он не абсолютный, то прибавляем путь проекта
            newPath = (Objects.requireNonNull(project).getBasePath() +"/"+ newPath);

        VirtualFile virtualFile = LocalFileSystem.getInstance().findFileByPath(newPath);// создаём виртуалочку нашего файла;

        if (project != null) { // открываем файл в редакторе
            if (virtualFile != null) {
                OpenFileDescriptor openFileDescriptor;

                if (position != null) {
                    openFileDescriptor = new OpenFileDescriptor(project, virtualFile, position.get(0) - 1, position.get(1));
                    openFileDescriptor.navigate(true);
                    if (position.get(2) != null || position.get(3) != null)
                        selectedText(project, virtualFile, position);
                } else {
                    openFileDescriptor = new OpenFileDescriptor(project, virtualFile);
                    openFileDescriptor.navigate(false);
                }
            } else
                ShowNotificationActivity.notifyError(project, newPath);
        }
    }

    private static void selectedText(Project project, VirtualFile virtualFile, ArrayList<Integer> position) {
        TextAttributes textattributes = new TextAttributes(null, null, JBColor.RED, EffectType.WAVE_UNDERSCORE, Font.PLAIN);

        FileEditor[] editors = FileEditorManager.getInstance(project).getEditors(virtualFile);

        for (FileEditor editor : editors) {
            MarkupModel markupModel = ((TextEditor) editor).getEditor().getMarkupModel();
            markupModel.removeAllHighlighters();
            ArrayList<Integer> offSetPosition = getPositionHighlighter(markupModel.getDocument().getText().split("\n"), position);
            markupModel.addRangeHighlighter(offSetPosition.get(0), offSetPosition.get(1), HighlighterLayer.ELEMENT_UNDER_CARET, textattributes, HighlighterTargetArea.EXACT_RANGE);
        }
    }

    private static ArrayList<Integer> getPositionHighlighter(String[] markMdl, ArrayList<Integer> position) {
        ArrayList<Integer> offSet = new ArrayList<>();
        int startOffSet = 0, endOffSet = 0, j = 0;

        for (int i = 0; i < markMdl.length; i++) {
            if (i + 1 < position.get(0)) {
                startOffSet += markMdl[i].length() + 1;
                endOffSet = startOffSet;
                j = i;
            } else if (i + 1 < position.get(2))
                endOffSet += markMdl[i].length() + 1;
            else {
                if (position.get(1) != 0)
                    startOffSet += position.get(1) - 1;

                endOffSet += Math.min(markMdl[i].length(), position.get(3) - 1);

                break;
            }
        }

        if (position.get(1) == 0) {
            for (int k = 0; k < markMdl[j + 1].length(); k++) {
                if (markMdl[j + 1].charAt(k) == ' ')
                    startOffSet += 1;
                else
                    break;
            }
        }

        offSet.add(0, startOffSet);
        offSet.add(1, endOffSet);
        return offSet;
    }
}