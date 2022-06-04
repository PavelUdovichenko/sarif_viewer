// Copyright 2000-2022 JetBrains s.r.o. and other contributors. Use of this source code is governed by the Apache 2.0
// license that can be found in the LICENSE file.

package org.example.sarif_viewer.toolWindow;

import com.intellij.icons.AllIcons;
import com.intellij.openapi.wm.ToolWindow;
import com.intellij.ui.JBColor;
import org.example.sarif_viewer.fileChooser.FileOpen;
import org.example.sarif_viewer.parser.JsonParse;
import org.example.sarif_viewer.parser.jsonKeys.MainKeys;
import org.example.sarif_viewer.psi.FileWithPsiElement;
import org.example.sarif_viewer.psi.PSIMouseListener;

import javax.swing.*;
import javax.swing.text.Position;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Objects;

import static org.example.sarif_viewer.fileChooser.FileOpen.pathFile;

public class SarifViewerToolWindow {
    private JPanel myToolWindowContent;

    private JToolBar toolBar;
    private JTabbedPane tabbedPanelUp;
    private JTabbedPane tabbedPanelDown;
    private JButton openFile;
    private JButton openFileMain;

    private JScrollPane scrollPaneLocations;
    private JTree treeLocations;

    private JScrollPane scrollPaneInfo;
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

    private JScrollPane scrollPaneAnalysisSteps;
    private JPanel messageAnalysisSteps;
    private JList<String> listSteps;

    private MainKeys mainKeys;
    private int checkSelectError = 0;

    ArrayList<Integer> position = new ArrayList<>();

    public SarifViewerToolWindow(ToolWindow toolWindow) {
        getStyles();

        openFile.addActionListener(e -> {
            FileOpen.showDlg("sarif", "SARIF-Files (*.sarif)", false);

            if (!Objects.equals(pathFile, "")) {
                mainKeys = JsonParse.parseJson();

                tabLocations();
                tabRules();
            }
        });

        openFileMain.addActionListener(e -> {
            FileOpen.showDlg("sarif", "SARIF-Files (*.sarif)", false);
            if (!Objects.equals(pathFile, "")) {
                mainKeys = JsonParse.parseJson();

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
        openFileMain.setIcon(AllIcons.Actions.MenuOpen);
        openFile.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        openFile.setIcon(AllIcons.Actions.MenuOpen);

        treeLocations.setRootVisible(false);
        treeLocations.setBorder(BorderFactory.createEmptyBorder());

        scrollPaneLocations.setBorder(BorderFactory.createEmptyBorder());
        scrollPaneInfo.setBorder(BorderFactory.createEmptyBorder());
        scrollPaneAnalysisSteps.setBorder(BorderFactory.createEmptyBorder());
        tabbedPanelUp.setVisible(false);
        tabbedPanelDown.setVisible(false);
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

        messageAnalysisSteps.setVisible(false);
        scrollPaneAnalysisSteps.setVisible(false);
    }

    private void tabLocations() {
        getModelJTree(treeLocations);

        treeLocations.getSelectionModel().addTreeSelectionListener(e -> {
            DefaultMutableTreeNode node = (DefaultMutableTreeNode) e.getPath().getLastPathComponent();

            //if (node.isLeaf()) {
            for (int i = 0; i < mainKeys.getRuns().get(0).getResults().size(); i++) {
                if (mainKeys.getRuns().get(0).getResults().get(i).getMessage().getText().equals(node.toString())) {
                    tabInfoClear();
                    tabInfo(i);
                    tabAnalisysSteps(i);

                    FileWithPsiElement.psiElement(mainKeys.getRuns().get(0).getResults().get(i).getLocations().get(0).getPhysicalLocation().getArtifactLocation().getUri(),
                            getPosition(mainKeys.getRuns().get(0).getResults().get(i).getLocations().get(0).getPhysicalLocation().getRegion().getStartLine(),
                                    mainKeys.getRuns().get(0).getResults().get(i).getLocations().get(0).getPhysicalLocation().getRegion().getStartColumn(),
                                    mainKeys.getRuns().get(0).getResults().get(i).getLocations().get(0).getPhysicalLocation().getRegion().getEndLine(),
                                    mainKeys.getRuns().get(0).getResults().get(i).getLocations().get(0).getPhysicalLocation().getRegion().getEndColumn()));
                    break;
                }

                if (mainKeys.getRuns().get(0).getResults().get(i).getLocations().get(0).getPhysicalLocation().getArtifactLocation().getUri().contains(node.toString())) {
                    FileWithPsiElement.psiElement(mainKeys.getRuns().get(0).getResults().get(i).getLocations().get(0).getPhysicalLocation().getArtifactLocation().getUri(),
                            getPosition(mainKeys.getRuns().get(0).getResults().get(i).getLocations().get(0).getPhysicalLocation().getRegion().getStartLine(),
                                    mainKeys.getRuns().get(0).getResults().get(i).getLocations().get(0).getPhysicalLocation().getRegion().getStartColumn(),
                                    mainKeys.getRuns().get(0).getResults().get(i).getLocations().get(0).getPhysicalLocation().getRegion().getEndLine(),
                                    mainKeys.getRuns().get(0).getResults().get(i).getLocations().get(0).getPhysicalLocation().getRegion().getEndColumn()));
                    break;
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
                    for (int i = 0; i < mainKeys.getRuns().get(0).getResults().size(); i++) {
                        if (mainKeys.getRuns().get(0).getResults().get(i).getMessage().getText().contains(obj.toString())) {
                            setIcon(getIconNode(mainKeys.getRuns().get(0).getResults().get(i).getLevel()));
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
        ArrayList<String> checkParentNodeName = new ArrayList<>();
        DefaultMutableTreeNode parentRoot = new DefaultMutableTreeNode("sarif_view");

        DefaultMutableTreeNode errorNameFile = null;

        for (int parentsNode = 0; parentsNode < mainKeys.getRuns().get(0).getResults().size(); parentsNode++) {
            String[] uri = mainKeys.getRuns().get(0).getResults().get(parentsNode).getLocations().get(0).getPhysicalLocation().getArtifactLocation().getUri().split("/");

            if (checkParentNodeName.isEmpty()) {
                errorNameFile = new DefaultMutableTreeNode(uri[uri.length - 1]);
                parentRoot.add(errorNameFile);
                checkParentNodeName.add(uri[uri.length - 1]);
            } else {
                for (String s : checkParentNodeName) {
                    if (!s.equals(uri[uri.length - 1])) {
                        errorNameFile = new DefaultMutableTreeNode(uri[uri.length - 1]);
                        parentRoot.add(errorNameFile);
                    }
                }
            }

            errorNameFile.add(new DefaultMutableTreeNode(mainKeys.getRuns().get(0).getResults().get(parentsNode).getMessage().getText()));

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
        ArrayList<Integer> pos = getPosition(mainKeys.getRuns().get(0).getResults().get(indexResult).getLocations().get(0).getPhysicalLocation().getRegion().getStartLine(),
                mainKeys.getRuns().get(0).getResults().get(indexResult).getLocations().get(0).getPhysicalLocation().getRegion().getStartColumn(),
                mainKeys.getRuns().get(0).getResults().get(indexResult).getLocations().get(0).getPhysicalLocation().getRegion().getEndLine(),
                mainKeys.getRuns().get(0).getResults().get(indexResult).getLocations().get(0).getPhysicalLocation().getRegion().getEndColumn());

        String txtMessage = mainKeys.getRuns().get(0).getResults().get(indexResult).getMessage().getText();
        lblTxtMessage.setVisible(true);
        lblTxtMessage.setText(txtMessage);

        String ruleId = mainKeys.getRuns().get(0).getResults().get(indexResult).getRuleId();
        ruleIdLabel.setVisible(true);
        lblRulId.setVisible(true);
        lblRulId.setText(Objects.requireNonNullElse(ruleId, "-"));

        String ruleName = mainKeys.getRuns().get(0).getTool().getDriver().getRules().get(0).getName();
        ruleNameLabel.setVisible(true);
        lblRulName.setVisible(true);
        lblRulName.setText(Objects.requireNonNullElse(ruleName, "-"));

        String ruleDescription = mainKeys.getRuns().get(0).getTool().getDriver().getRules().get(0).getShortDescription().getText();
        ruleDescriptionLabel.setVisible(true);
        lblRulDes.setVisible(true);
        lblRulDes.setText(Objects.requireNonNullElse(ruleDescription, "-"));

        String level = mainKeys.getRuns().get(0).getResults().get(indexResult).getLevel();
        levelLabel.setVisible(true);
        lblLvl.setVisible(true);
        lblLvl.setText(Objects.requireNonNullElse(level, "-"));

        String[] uri = mainKeys.getRuns().get(0).getResults().get(indexResult).getLocations().get(0).getPhysicalLocation().getArtifactLocation().getUri().split("/");
        locationLabel.setVisible(true);
        lblLoc.setVisible(true);
        lblLoc.setText("<html><u>" + uri[uri.length - 1] + "</u></html>");
        lblLoc.addMouseListener(new PSIMouseListener(mainKeys.getRuns().get(0).getResults().get(indexResult).getLocations().get(0).getPhysicalLocation().getArtifactLocation().getUri(), pos));

        logLabel.setVisible(true);
        lblLog.setVisible(true);
        lblLog.setText("<html><u>" + FileOpen.openFile + "</u></html>");
        lblLog.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        lblLog.addMouseListener(new PSIMouseListener(FileOpen.pathFile, null));
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

    private void tabAnalisysSteps(int indexResult) {
        messageAnalysisSteps.setVisible(false);
        scrollPaneAnalysisSteps.setVisible(false);
        checkSelectError = indexResult;

        DefaultListModel<String> defaultListModel = new DefaultListModel<>();

        if (mainKeys.getRuns().get(0).getResults().get(indexResult).getCodeFlows() != null) {
            scrollPaneAnalysisSteps.setVisible(true);
            for (int analisysStep = 0; analisysStep < mainKeys.getRuns().get(0).getResults().get(indexResult).getCodeFlows().get(0).getThreadFlows().get(0).getLocations().size(); analisysStep++) {
                if (mainKeys.getRuns().get(0).getResults().get(indexResult).getCodeFlows().get(0).getThreadFlows().get(0).getLocations().get(analisysStep).getLocation().getMessage() != null)
                    defaultListModel.addElement(mainKeys.getRuns().get(0).getResults().get(indexResult).getCodeFlows().get(0).getThreadFlows().get(0).getLocations().get(analisysStep).getLocation().getMessage().getText());
                else
                    defaultListModel.addElement("-");
            }
        } else {
            messageAnalysisSteps.setVisible(true);
        }

        listSteps.setModel(defaultListModel);

        listSteps.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int selected = listSteps.locationToIndex(e.getPoint());

                if (indexResult == checkSelectError)
                    FileWithPsiElement.psiElement(
                        mainKeys.getRuns().get(0).getResults().get(indexResult).getCodeFlows().get(0).getThreadFlows().get(0).getLocations().get(selected).getLocation().getPhysicalLocation().getArtifactLocation().getUri(),
                        getPosition(mainKeys.getRuns().get(0).getResults().get(indexResult).getCodeFlows().get(0).getThreadFlows().get(0).getLocations().get(selected).getLocation().getPhysicalLocation().getRegion().getStartLine(),
                                mainKeys.getRuns().get(0).getResults().get(indexResult).getCodeFlows().get(0).getThreadFlows().get(0).getLocations().get(selected).getLocation().getPhysicalLocation().getRegion().getStartColumn(),
                                mainKeys.getRuns().get(0).getResults().get(indexResult).getCodeFlows().get(0).getThreadFlows().get(0).getLocations().get(selected).getLocation().getPhysicalLocation().getRegion().getEndLine(),
                                mainKeys.getRuns().get(0).getResults().get(indexResult).getCodeFlows().get(0).getThreadFlows().get(0).getLocations().get(selected).getLocation().getPhysicalLocation().getRegion().getEndColumn()));
            }
        });
    }

    private ArrayList<Integer> getPosition(Integer startLine, Integer startColumn, Integer endLine, Integer endColumn) {
        position.clear();
        position.add(0, startLine);
        position.add(1, startColumn);
        position.add(2, endLine);
        position.add(3, endColumn);
        // если ничего не надо то просто открываем на начале файла
        /*if (position.get(0) == null) {
            position.set(0, 0);
            position.set(1, 0);
            position.set(2, 0);
            position.set(3, 0);
        }*/
        // если есть только нач. строка то начнём с начала строки и закончим в конце (выделим всю строку)
        if (position.get(1) == null) {
            position.set(1, 0);
            position.set(2, position.get(0) + 1);
            position.set(3, 0);
        }

        // если есть начальные строка и колонна но нет ничего остального, всё остальное повторим со стартовым
        if (position.get(2) == null)
            position.set(2, position.get(0));

        if (position.get(3) == null)
            if (position.get(1) != null)
                position.set(3, position.get(1) + 1);

        return position;
    }

    public JPanel getContent() {
        return myToolWindowContent;
    }
}