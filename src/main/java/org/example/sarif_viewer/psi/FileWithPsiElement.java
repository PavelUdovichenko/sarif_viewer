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
                    if (position.get(2) != null || position.get(3) != null)
                        selectedText(project, virtualFile, position);
                } else {
                    openFileDescriptor = new OpenFileDescriptor(project, virtualFile);
                    openFileDescriptor.navigate(false);
                }
            } else
                ShowNotificationActivity.notifyError(project, fName);
        } else
            ShowNotificationActivity.notifyError(project, fName);
    }

    private static void selectedText(Project project, VirtualFile virtualFile, ArrayList<Integer> position) {
        TextAttributes textattributes = new TextAttributes(null, null, JBColor.RED, EffectType.WAVE_UNDERSCORE, Font.PLAIN);
        FileEditor[] editors = FileEditorManager.getInstance(project).getEditors(virtualFile);

        for (FileEditor editor : editors) {
            MarkupModel markupModel = ((TextEditor) editor).getEditor().getMarkupModel();
            ArrayList<Integer> offSetPosition = getPositionHighlighter(markupModel.getDocument().getText().split("\n"), position);
            markupModel.addRangeHighlighter(offSetPosition.get(0), offSetPosition.get(1), HighlighterLayer.ELEMENT_UNDER_CARET, textattributes, HighlighterTargetArea.EXACT_RANGE);
        }
    }

    private static ArrayList<Integer> getPositionHighlighter(String[] markMdl, ArrayList<Integer> position) {
        ArrayList<Integer> offSet = new ArrayList<>();
        int startOffSet = 0, endOffSet = 0;

        for (int i = 0; i < markMdl.length; i++) {
            if (i + 1 < position.get(0)) {
                startOffSet += markMdl[i].length() + 1;
                endOffSet = startOffSet;
            } else if (i + 1 < position.get(2))
                endOffSet += markMdl[i].length() + 1;
            else {
                startOffSet += position.get(1) - 1;

                endOffSet += Math.min(markMdl[i].length(), position.get(3) - 1);

                break;
            }
        }

        offSet.add(0, startOffSet);
        offSet.add(1, endOffSet);

        return offSet;
    }
}