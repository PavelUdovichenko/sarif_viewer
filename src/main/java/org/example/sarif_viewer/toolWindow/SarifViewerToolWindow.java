// Copyright 2000-2022 JetBrains s.r.o. and other contributors. Use of this source code is governed by the Apache 2.0
// license that can be found in the LICENSE file.

package org.example.sarif_viewer.toolWindow;

import com.intellij.icons.AllIcons;
import com.intellij.openapi.wm.ToolWindow;
import com.intellij.ui.JBColor;
import org.example.sarif_viewer.psi.psiMouseListener;
import org.example.sarif_viewer.fileChooser.FileOpen;
import org.example.sarif_viewer.parser.JsonParse;

import javax.swing.*;
import javax.swing.text.Position;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;
import java.awt.*;
import java.util.ArrayList;
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

        treeLocations.setRootVisible(false);
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
                tabRules();
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

                tabLocations();
                tabRules();
            }
        });
    }

    private void tabLocations() {
        String[] uri = JsonParse.parseJson().getRuns().get(0).getResults().get(0).getLocations().get(0).getPhysicalLocation().getArtifactLocation().getUri().split("/");

        getModelJTree(treeLocations);

        treeLocations.getSelectionModel().addTreeSelectionListener(e -> {
            DefaultMutableTreeNode node = (DefaultMutableTreeNode) e.getPath().getLastPathComponent();

            if (node.isLeaf()) {
                System.out.println(node);
                tabInfo();
            } else {
                System.out.println(node);
//                new psiMouseListener(JsonParse.parseJson().getRuns().get(0).getResults().get(0).getLocations().get(0).getPhysicalLocation().getArtifactLocation().getUri());
            }
        });

        final TreePath treePath = treeLocations.getNextMatch(uri[uri.length - 1], 0, Position.Bias.Forward);
        if (treePath != null)
            treeLocations.collapsePath(treePath);

        treeLocations.setCellRenderer(new DefaultTreeCellRenderer() {
            @Override
            public Component getTreeCellRendererComponent(JTree tree,
                                                          Object value, boolean selected, boolean expanded,
                                                          boolean isLeaf, int row, boolean focused) {
                Component c = super.getTreeCellRendererComponent(tree, value, selected, expanded, isLeaf, row, focused);
                Object obj = ((DefaultMutableTreeNode) value).getUserObject();

                if (isLeaf) {
                    System.out.println(obj);
//                    System.out.println(((DefaultMutableTreeNode) value).getParent());
                    setIcon(AllIcons.General.Error);
                } else {
                    setClosedIcon(null);
                    setOpenIcon(null);
                }
//                    if (levelError != null) {
//                        switch (levelError) {
//                            case "error":
//                                setIcon(AllIcons.General.Error);
//                                break;
//                            case "note":
//                                setIcon(AllIcons.General.Note);
//                                break;
//                            default:
//                                setIcon(AllIcons.General.Warning);
//                                break;
//                        }
//                    } else
//                        setIcon(AllIcons.General.Warning);
//                }
                return c;
            }
        });
    }

    private void getModelJTree(JTree jTree) {
        DefaultMutableTreeNode parentRoot = new DefaultMutableTreeNode("sarif_view");

        for (int parentsNode = 0; parentsNode < JsonParse.parseJson().getRuns().get(0).getResults().size(); parentsNode++) {
            String[] uri = JsonParse.parseJson().getRuns().get(0).getResults().get(parentsNode).getLocations().get(0).getPhysicalLocation().getArtifactLocation().getUri().split("/");

            DefaultMutableTreeNode errorNameFile = new DefaultMutableTreeNode(uri[uri.length - 1]);
            parentRoot.add(errorNameFile);
            errorNameFile.add(new DefaultMutableTreeNode(JsonParse.parseJson().getRuns().get(0).getResults().get(parentsNode).getMessage().getText()));

            DefaultTreeModel model = (DefaultTreeModel) this.treeLocations.getModel();
            model.setRoot(parentRoot);

            jTree.setModel(model);
        }
    }

    private void tabRules() {

    }

    private void tabInfo() {
        String[] uri = JsonParse.parseJson().getRuns().get(0).getResults().get(0).getLocations().get(0).getPhysicalLocation().getArtifactLocation().getUri().split("/");

        lblTxtMessage.setText(JsonParse.parseJson().getRuns().get(0).getResults().get(0).getMessage().getText());
        lblRulId.setText(JsonParse.parseJson().getRuns().get(0).getResults().get(0).getRuleId());
        lblRulName.setText(JsonParse.parseJson().getRuns().get(0).getTool().getDriver().getRules().get(0).getName());
        lblRulDes.setText(JsonParse.parseJson().getRuns().get(0).getTool().getDriver().getRules().get(0).getShortDescription().getText());
        lblLvl.setText(JsonParse.parseJson().getRuns().get(0).getResults().get(0).getLevel());

        lblLoc.setText("<html><u>" + uri[uri.length - 1] + "</u></html>");
        lblLoc.setForeground(JBColor.BLUE);
        lblLoc.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        lblLoc.addMouseListener(new psiMouseListener(JsonParse.parseJson().getRuns().get(0).getResults().get(0).getLocations().get(0).getPhysicalLocation().getArtifactLocation().getUri()));

        lblLog.setText("<html><u>" + FileOpen.openFile + "</u></html>");
        lblLog.setForeground(JBColor.BLUE);
        lblLog.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        lblLog.addMouseListener(new psiMouseListener(FileOpen.pathFile));
    }

    public JPanel getContent() {
        return myToolWindowContent;
    }
}