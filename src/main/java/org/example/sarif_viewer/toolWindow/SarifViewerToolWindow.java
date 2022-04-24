// Copyright 2000-2022 JetBrains s.r.o. and other contributors. Use of this source code is governed by the Apache 2.0
// license that can be found in the LICENSE file.

package org.example.sarif_viewer.toolWindow;

import com.intellij.icons.AllIcons;
import com.intellij.openapi.wm.ToolWindow;
import com.intellij.ui.JBColor;
import org.example.sarif_viewer.psi.fileWithPsiElement;
import org.example.sarif_viewer.psi.psiMouseListener;
import org.example.sarif_viewer.fileChooser.FileOpen;
import org.example.sarif_viewer.parser.JsonParse;

import javax.swing.*;
import javax.swing.text.Position;
import javax.swing.tree.*;
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
    private JLabel ruleIdLabel;
    private JLabel lblRulId;
    private JLabel ruleNameLabel;
    private JLabel lblRulName;
    private JLabel ruleDescriptionLabel;
    private JLabel lblRulDes;
    private JLabel levelLabel;
    private JLabel lblLvl;
    private JLabel locationLabel;
    private JLabel lblLoc;
    private JLabel logLabel;
    private JLabel lblLog;
    private JScrollPane scrollPaneLocaations;


    public SarifViewerToolWindow(ToolWindow toolWindow) {
        getStyles();

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

    private void getStyles() {
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

        lblTxtMessage.setVisible(false);
        ruleIdLabel.setVisible(false);
        lblRulId.setVisible(false);
        ruleNameLabel.setVisible(false);
        lblRulName.setVisible(false);
        ruleDescriptionLabel.setVisible(false);
        lblRulDes.setVisible(false);
        levelLabel.setVisible(false);
        lblLvl.setVisible(false);
        locationLabel.setVisible(false);
        lblLoc.setVisible(false);
        lblLoc.setForeground(JBColor.BLUE);
        lblLoc.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        logLabel.setVisible(false);
        lblLog.setVisible(false);
        lblLog.setForeground(JBColor.BLUE);
        lblLog.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }

    private void tabLocations() {
        getModelJTree(treeLocations);

        treeLocations.getSelectionModel().addTreeSelectionListener(e -> {
            DefaultMutableTreeNode node = (DefaultMutableTreeNode) e.getPath().getLastPathComponent();

            if (node.isLeaf()) {
                for (int i = 0; i < JsonParse.parseJson().getRuns().get(0).getResults().size(); i++) {
                    if (JsonParse.parseJson().getRuns().get(0).getResults().get(i).getMessage().getText().equals(node.toString())) {
                        tabInfo(i);
                        break;
                    }
                }
            } else {
                tabInfoClear();
                for (int i = 0; i < JsonParse.parseJson().getRuns().get(0).getResults().size(); i++) {
                    if (JsonParse.parseJson().getRuns().get(0).getResults().get(i).getLocations().get(0).getPhysicalLocation().getArtifactLocation().getUri().contains(node.toString())) {
                        fileWithPsiElement.psiElement(JsonParse.parseJson().getRuns().get(0).getResults().get(i).getLocations().get(0).getPhysicalLocation().getArtifactLocation().getUri());
                        break;
                    }
                }
            }
        });

        treeLocations.setCellRenderer(new DefaultTreeCellRenderer() {
            @Override
            public Component getTreeCellRendererComponent(JTree tree,
                                                          Object value, boolean selected, boolean expanded,
                                                          boolean leaf, int row, boolean focused) {
                Component c = super.getTreeCellRendererComponent(tree, value, selected, expanded, leaf, row, focused);
                Object obj = ((DefaultMutableTreeNode) value).getUserObject();

                if (leaf) {
                    for (int i = 0; i < JsonParse.parseJson().getRuns().get(0).getResults().size(); i++) {
                        if (JsonParse.parseJson().getRuns().get(0).getResults().get(i).getMessage().getText().contains(obj.toString())) {
                            setIcon(getIconNode(JsonParse.parseJson().getRuns().get(0).getResults().get(i).getLevel()));
                            break;
                        }
                    }
                } else {
                    setClosedIcon(null);
                    setOpenIcon(null);
                }
                return c;
            }
        });

        final TreePath treePath = treeLocations.getNextMatch("", 0, Position.Bias.Forward);
        if (treePath != null)
            treeLocations.collapsePath(treePath);
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

    private Icon getIconNode(String levelError) {
        if (levelError != null) {
            switch (levelError) {
                case "error":
                    return AllIcons.General.Error;
                case "note":
                    return AllIcons.General.Note;
                default:
                    return AllIcons.General.Warning;
            }
        } else {
            return AllIcons.General.Warning;
        }
    }

    private void tabRules() {

    }

    private void tabInfo(int indexResult) {
        String txtMessage = JsonParse.parseJson().getRuns().get(0).getResults().get(indexResult).getMessage().getText();
        lblTxtMessage.setVisible(true);
        lblTxtMessage.setText(txtMessage);

        String ruleId = JsonParse.parseJson().getRuns().get(0).getResults().get(indexResult).getRuleId();
        ruleIdLabel.setVisible(true);
        lblRulId.setVisible(true);
        lblRulId.setText(Objects.requireNonNullElse(ruleId, "-"));

        String ruleName = JsonParse.parseJson().getRuns().get(0).getTool().getDriver().getRules().get(0).getName();
        ruleNameLabel.setVisible(true);
        lblRulName.setVisible(true);
        lblRulName.setText(Objects.requireNonNullElse(ruleName, "-"));

        String ruleDescription = JsonParse.parseJson().getRuns().get(0).getTool().getDriver().getRules().get(0).getShortDescription().getText();
        ruleDescriptionLabel.setVisible(true);
        lblRulDes.setVisible(true);
        lblRulDes.setText(Objects.requireNonNullElse(ruleDescription, "-"));

        String level = JsonParse.parseJson().getRuns().get(0).getResults().get(indexResult).getLevel();
        levelLabel.setVisible(true);
        lblLvl.setVisible(true);
        lblLvl.setText(Objects.requireNonNullElse(level, "-"));

        String[] uri = JsonParse.parseJson().getRuns().get(0).getResults().get(indexResult).getLocations().get(0).getPhysicalLocation().getArtifactLocation().getUri().split("/");
        locationLabel.setVisible(true);
        lblLoc.setVisible(true);
        lblLoc.setText("<html><u>" + uri[uri.length - 1] + "</u></html>");
        lblLoc.addMouseListener(new psiMouseListener(JsonParse.parseJson().getRuns().get(0).getResults().get(indexResult).getLocations().get(0).getPhysicalLocation().getArtifactLocation().getUri()));

        logLabel.setVisible(true);
        lblLog.setVisible(true);
        lblLog.setText("<html><u>" + FileOpen.openFile + "</u></html>");
        lblLog.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        lblLog.addMouseListener(new psiMouseListener(FileOpen.pathFile));
    }

    private void tabInfoClear() {
        lblTxtMessage.setText("");
        lblTxtMessage.setVisible(false);
        ruleIdLabel.setVisible(false);
        lblRulId.setVisible(false);
        lblRulId.setText("");
        ruleNameLabel.setVisible(false);
        lblRulName.setVisible(false);
        lblRulName.setText("");
        ruleDescriptionLabel.setVisible(false);
        lblRulDes.setVisible(false);
        lblRulDes.setText("");
        levelLabel.setVisible(false);
        lblLvl.setVisible(false);
        lblLvl.setText("");
        locationLabel.setVisible(false);
        lblLoc.setVisible(false);
        lblLoc.setText("");
        logLabel.setVisible(false);
        lblLog.setVisible(false);
        lblLog.setText("");
    }

    public JPanel getContent() {
        return myToolWindowContent;
    }
}