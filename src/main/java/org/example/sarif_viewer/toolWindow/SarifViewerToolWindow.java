// Copyright 2000-2022 JetBrains s.r.o. and other contributors. Use of this source code is governed by the Apache 2.0
// license that can be found in the LICENSE file.

package org.example.sarif_viewer.toolWindow;

import com.intellij.icons.AllIcons;
import com.intellij.openapi.wm.ToolWindow;
import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.DefaultTreeModel;

import org.example.sarif_viewer.fileChooser.FileOpen;
import org.example.sarif_viewer.parser.SarifParser;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

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

        openFile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FileOpen.clickBtn();

                if (!Objects.equals(FileOpen.pathFile, "")) {
                    tabLocations();
                    tabInfo(); // изменяем по клику (выбору узла) в дереве
                }
            }
        });

        openFileMain.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FileOpen.clickBtn();

                if (!Objects.equals(FileOpen.pathFile, "")) {
                    tabbedPanelUp.setEnabled(true);
                    tabbedPanelUp.setVisible(true);
                    tabbedPanelDown.setEnabled(true);
                    tabbedPanelDown.setVisible(true);
                    toolBar.setEnabled(true);
                    toolBar.setVisible(true);
                    openFileMain.setEnabled(false);
                    openFileMain.setVisible(false);

                    tabLocations();
                    tabInfo(); // изменяем по клику (выбору узла) в дереве
                }
            }
        });
    }

    private void tabLocations() {
        DefaultTreeModel model;
        DefaultMutableTreeNode errorFile = new DefaultMutableTreeNode("file with error");
        errorFile.add(new DefaultMutableTreeNode("the error description"));
        model = (DefaultTreeModel) treeLocations.getModel();
        model.setRoot(errorFile);
        treeLocations.setModel(model);
        DefaultTreeCellRenderer renderer = (DefaultTreeCellRenderer) treeLocations.getCellRenderer();

        Icon leafIcon = AllIcons.General.Error;
//        Icon openIcon = AllIcons.General.Warning;
//        Icon closedIcon = AllIcons.General.Warning;
        renderer.setLeafIcon(leafIcon);
        renderer.setClosedIcon(null);
        renderer.setOpenIcon(null);
    }

    private void tabInfo() {
        lblTxtMessage.setText(SarifParser.getInfo("$schema"));
        lblRulId.setText(SarifParser.getInfo("version"));
        lblRulName.setText(SarifParser.getInfo("version"));
        lblRulDes.setText(SarifParser.getInfo("version"));
        lblLvl.setText(SarifParser.getInfo("version"));
        lblLoc.setText(SarifParser.getInfo("version"));
        lblLog.setText(FileOpen.openFile);
    }

    public JPanel getContent() {
        return myToolWindowContent;
    }
}