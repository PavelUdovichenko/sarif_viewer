// Copyright 2000-2022 JetBrains s.r.o. and other contributors. Use of this source code is governed by the Apache 2.0
// license that can be found in the LICENSE file.

package org.example.sarif_viewer.toolWindow;

import com.intellij.openapi.wm.ToolWindow;
import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.DefaultTreeModel;

import org.example.sarif_viewer.fileChooser.FileOpen;
import org.example.sarif_viewer.parser.SarifParser;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

import static com.intellij.util.IconUtil.createImageIcon;

public class SarifViewerToolWindow {
    private JPanel myToolWindowContent;

    private JToolBar toolBar;
    private JTabbedPane tabbedPanelUp;
    private JTabbedPane tabbedPanelDown;
    private JTree tree1;
    private JComboBox filterSV;
    private JButton openFile;
    private JButton openFile1;


    private JLabel lblTxtMessage;
    private JLabel lblRulId;
    private JLabel lblRulName;
    private JLabel lblRulDes;
    private JLabel lblLvl;
    private JLabel lblLoc;
    private JLabel lblLog;



    public SarifViewerToolWindow(ToolWindow toolWindow) {
        tabbedPanelUp.setEnabled(false);
        tabbedPanelUp.setVisible(false);
        tabbedPanelDown.setEnabled(false);
        tabbedPanelDown.setVisible(false);
        toolBar.setEnabled(false);
        toolBar.setVisible(false);

        //hideToolWindowButton.addActionListener(e -> toolWindow.hide(null));

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

        openFile1.addActionListener(new ActionListener() {
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
                    openFile1.setEnabled(false);
                    openFile1.setVisible(false);

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
        model = (DefaultTreeModel) tree1.getModel();
        model.setRoot(errorFile);
        tree1.setModel(model);
        DefaultTreeCellRenderer renderer = (DefaultTreeCellRenderer) tree1.getCellRenderer();
        Icon leafIcon = new ImageIcon("D:\\Programs\\idea\\IdeaProjects\\sarif_viewer\\src\\main\\resources\\ico\\balloonError_dark.svg");
        Icon openIcon = new ImageIcon("D:\\Programs\\idea\\IdeaProjects\\sarif_viewer\\src\\main\\resources\\ico\\balloonError_dark.svg");
        Icon closedIcon = new ImageIcon("D:\\Programs\\idea\\IdeaProjects\\sarif_viewer\\src\\main\\resources\\ico\\balloonError_dark.svg");

        renderer.setLeafIcon(leafIcon);
        renderer.setClosedIcon(closedIcon);
        renderer.setOpenIcon(openIcon);
        }



    private void tabInfo() {
        lblTxtMessage.setText(SarifParser.getInfo("$schema"));
        lblRulId.setText(SarifParser.getInfo("version"));
        lblRulName.setText(SarifParser.getInfo("version"));
        lblRulDes.setText(SarifParser.getInfo("version"));
        lblLvl.setText(SarifParser.getInfo("version"));
        lblLoc.setText(SarifParser.getInfo("version"));
        lblLog.setText(SarifParser.getInfo("version"));
    }

//    public void currentDateTime() {
//
//        // Get current date and time
//        Calendar instance = Calendar.getInstance();
//        currentDate.setText(
//                instance.get(Calendar.DAY_OF_MONTH) + "/"
//                        + (instance.get(Calendar.MONTH) + 1) + "/"
//                        + instance.get(Calendar.YEAR)
//        );
//        currentDate.setIcon(new ImageIcon(Objects.requireNonNull(getClass().getResource("/ico/1.png"))));
//        int min = instance.get(Calendar.MINUTE);
//        String strMin = min < 10 ? "0" + min : String.valueOf(min);
//        currentTime.setText(instance.get(Calendar.HOUR_OF_DAY) + ":" + strMin);
//        currentTime.setIcon(new ImageIcon(Objects.requireNonNull(getClass().getResource("/ico/1.png"))));
//        // Get time zone
//        long gmt_Offset = instance.get(Calendar.ZONE_OFFSET); // offset from GMT in milliseconds
//        String str_gmt_Offset = String.valueOf(gmt_Offset / 3600000);
//        str_gmt_Offset = (gmt_Offset > 0) ? "GMT + " + str_gmt_Offset : "GMT - " + str_gmt_Offset;
//        timeZone.setText(str_gmt_Offset);
//        timeZone.setIcon(new ImageIcon(Objects.requireNonNull(getClass().getResource("/ico/1.png"))));
//
//    }

    public JPanel getContent() {
        return myToolWindowContent;
    }
}