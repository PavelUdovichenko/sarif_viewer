// Copyright 2000-2022 JetBrains s.r.o. and other contributors. Use of this source code is governed by the Apache 2.0
// license that can be found in the LICENSE file.

package org.example.sarif_viewer.toolWindow;

import com.intellij.icons.AllIcons;
import com.intellij.openapi.wm.ToolWindow;
import org.example.sarif_viewer.Psi.psiMouseListener;
import org.example.sarif_viewer.fileChooser.FileOpen;
import org.example.sarif_viewer.parser.JsonParse;

import javax.swing.*;
import javax.swing.text.Position;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;
import java.awt.*;
import java.util.Objects;

import static org.example.sarif_viewer.fileChooser.FileOpen.clickBtn;
import static org.example.sarif_viewer.fileChooser.FileOpen.pathFile;

public class SarifViewerToolWindow {
    private JPanel myToolWindowContent;

    private JToolBar toolBar;
    private JTabbedPane tabbedPanelUp;
    private JTabbedPane tabbedPanelDown;
    private JTree treeLocations;
    private JComboBox filterSV;
    private JButton openFile;
    private JButton openFileMain;

    private JLabel lblTxtMessage;
    private JLabel lblRulId;
    private JLabel lblRulName;
    private JLabel lblRulDes;
    private JLabel lblLvl;
    private JLabel lblLoc;
    private JLabel lblLog;
    private JScrollPane scrollPaneLocaations;

    public SarifViewerToolWindow(ToolWindow toolWindow) {
        openFileMain.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        openFile.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        openFile.setIcon(AllIcons.Actions.MenuOpen);
        treeLocations.setBorder(BorderFactory.createEmptyBorder());

        scrollPaneLocaations.setBorder(BorderFactory.createEmptyBorder());
        tabbedPanelUp.setEnabled(false);
        tabbedPanelUp.setVisible(false);
        tabbedPanelDown.setEnabled(false);
        tabbedPanelDown.setVisible(false);
        toolBar.setEnabled(false);
        toolBar.setVisible(false);

        openFile.addActionListener(e -> {
            clickBtn();

            if (!Objects.equals(pathFile, "")) {
                tabLocations();
                tabLogs();
                tabInfo(); // изменяем по клику (выбору узла) в дереве
            }
        });

        openFileMain.addActionListener(e -> {
            clickBtn();

            if (!Objects.equals(pathFile, "")) {
                tabbedPanelUp.setEnabled(true);
                tabbedPanelUp.setVisible(true);
                tabbedPanelDown.setEnabled(true);
                tabbedPanelDown.setVisible(true);
                toolBar.setEnabled(true);
                toolBar.setVisible(true);
                openFileMain.setEnabled(false);
                openFileMain.setVisible(false);

                tabLogs();
                tabLocations();
                tabInfo(); // изменяем по клику (выбору узла) в дереве
            }
        });
    }

    private void tabLocations() {
        String[] uri = JsonParse.parseJson().getRuns().get(0).getResults().get(0).getLocations().get(0).getPhysicalLocation().getArtifactLocation().getUri().split("/");

        DefaultMutableTreeNode errorFile = new DefaultMutableTreeNode(uri[uri.length - 1]);
        errorFile.add(new DefaultMutableTreeNode(JsonParse.parseJson().getRuns().get(0).getResults().get(0).getMessage().getText()));

        DefaultTreeModel model = (DefaultTreeModel) treeLocations.getModel();
        model.setRoot(errorFile);
        treeLocations.setModel(model);
        DefaultTreeCellRenderer renderer = (DefaultTreeCellRenderer) treeLocations.getCellRenderer();

        Icon leafIcon;

        if (JsonParse.parseJson().getRuns().get(0).getResults().get(0).getLevel().equals("error"))
            leafIcon = AllIcons.General.Error;
        else if (JsonParse.parseJson().getRuns().get(0).getResults().get(0).getLevel().equals("warning"))
            leafIcon = AllIcons.General.Warning;
        else if (JsonParse.parseJson().getRuns().get(0).getResults().get(0).getLevel().equals("note"))
            leafIcon = AllIcons.General.Note;
        else
            leafIcon = AllIcons.General.Warning;

        renderer.setLeafIcon(leafIcon);
        renderer.setClosedIcon(null);
        renderer.setOpenIcon(null);

        final TreePath treePath = treeLocations.getNextMatch(uri[uri.length - 1], 0, Position.Bias.Forward);
        if (treePath != null)
            treeLocations.collapsePath(treePath);
    }

    private void tabRules() {

    }

    private void tabLogs() {
//        listLogs.setModel();
    }

    private void tabInfo() {
        String[] uri = JsonParse.parseJson().getRuns().get(0).getResults().get(0).getLocations().get(0).getPhysicalLocation().getArtifactLocation().getUri().split("/");

        lblTxtMessage.setText(JsonParse.parseJson().getRuns().get(0).getResults().get(0).getMessage().getText());
        lblRulId.setText(JsonParse.parseJson().getRuns().get(0).getResults().get(0).getRuleId());
        lblRulName.setText(JsonParse.parseJson().getRuns().get(0).getTool().getDriver().getRules().get(0).getName());
        lblRulDes.setText(JsonParse.parseJson().getRuns().get(0).getTool().getDriver().getRules().get(0).getShortDescription().getText());
        lblLvl.setText(JsonParse.parseJson().getRuns().get(0).getResults().get(0).getLevel());
        lblLoc.setText(uri[uri.length - 1]);
        lblLoc.addMouseListener( new psiMouseListener(uri[uri.length - 1]));
        lblLog.setText(FileOpen.openFile);
        lblLog.addMouseListener( new psiMouseListener(lblLog.getText()));

    }

    public JPanel getContent() {
        return myToolWindowContent;
    }






}