// Copyright 2000-2022 JetBrains s.r.o. and other contributors. Use of this source code is governed by the Apache 2.0
// license that can be found in the LICENSE file.

package org.example.sarif_viewer.toolWindow;

import com.intellij.icons.AllIcons;
import com.intellij.openapi.wm.ToolWindow;
import com.intellij.ui.JBColor;
import org.example.sarif_viewer.fileChooser.FileOpen;
import org.example.sarif_viewer.fileChooser.GetPathProject;
import org.example.sarif_viewer.notifier.ShowNotificationActivity;
import org.example.sarif_viewer.parser.JsonParse;
import org.example.sarif_viewer.parser.jsonKeys.MainKeys;
import org.example.sarif_viewer.psi.FileWithPsiElement;

import javax.swing.*;
import javax.swing.text.Position;
import javax.swing.tree.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Objects;
import java.util.regex.Pattern;

import static org.example.sarif_viewer.fileChooser.FileOpen.pathFile;

public class SarifViewerToolWindow {
    private JPanel myToolWindowContent;

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

    private JPanel openLogPanel;
    private JSplitPane splitPanel;
    private JPanel mainPanel;
    private JToolBar toolBar;

    private MainKeys mainKeys;
    private int checkSelectRunsIndex = 0, checkSelectResultsIndex = 0;

    ArrayList<Integer> position = new ArrayList<>();
    ArrayList<Integer> indexesSelectedNode = new ArrayList<>();

    public SarifViewerToolWindow(ToolWindow toolWindow) {
        getStyles();
        openFileMain.addActionListener(e -> openLog(0));
        openFile.addActionListener(e -> openLog(1));
    }

    private void getStyles() {
        openFileMain.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        openFileMain.setIcon(AllIcons.Actions.MenuOpen);
        openFile.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        openFile.setIcon(AllIcons.Actions.MenuOpen);

        openLogPanel.setVisible(true);
        mainPanel.setVisible(false);
        toolBar.setVisible(false);
        toolBar.setOpaque(false);
        toolBar.setBorder(BorderFactory.createEmptyBorder());
        splitPanel.setBorder(BorderFactory.createEmptyBorder());

        treeLocations.setRootVisible(false);
        treeLocations.setBorder(BorderFactory.createEmptyBorder());

        scrollPaneLocations.setBorder(BorderFactory.createEmptyBorder());
        scrollPaneInfo.setBorder(BorderFactory.createEmptyBorder());
        scrollPaneAnalysisSteps.setBorder(BorderFactory.createEmptyBorder());

        lblTxtMessage.setVisible(false);
        lblTxtMessage.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
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

        listSteps.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        messageAnalysisSteps.setVisible(false);
        scrollPaneAnalysisSteps.setVisible(false);
    }

    private void openLog(int checkButton) {
        FileOpen.showDlg("sarif", "SARIF-Files (*.sarif)", false);
        String[] uriPathFile = pathFile.split(Pattern.quote("\\"));

        if (checkButton == 0) {
            if (!Objects.equals(pathFile, "")) {
                mainKeys = JsonParse.parseJson();

                if (mainKeys.get$schema() != null) {
                    openLogPanel.setVisible(false);
                    mainPanel.setVisible(true);
                    toolBar.setVisible(true);

                    tabLocations();
                } else {
                    ShowNotificationActivity.notifyNotSarifOpenFile(Objects.requireNonNull(GetPathProject.getProject()), uriPathFile[uriPathFile.length - 1]);
                    openLog(0);
                }
            }
        } else if (checkButton == 1) {
            if (!Objects.equals(pathFile, "")) {
                mainKeys = JsonParse.parseJson();

                if (mainKeys.get$schema() != null)
                    tabLocations();
                else {
                    ShowNotificationActivity.notifyNotSarifOpenFile(Objects.requireNonNull(GetPathProject.getProject()), uriPathFile[uriPathFile.length -1]);
                    openLog(1);
                }
            }
        }
    }

    private void tabLocations() {
        getModelJTree(treeLocations);

        treeLocations.getSelectionModel().addTreeSelectionListener(e -> {
            DefaultMutableTreeNode node = (DefaultMutableTreeNode) e.getPath().getLastPathComponent();

            indexesSelectedNode.clear();
            getNodeIndex(treeLocations, node, 0);

            if (indexesSelectedNode.size() == 1) {
                FileWithPsiElement.psiElement(mainKeys.getRuns().get(indexesSelectedNode.get(0)).getResults().get(0).getLocations().get(0).getPhysicalLocation().getArtifactLocation().getUri(), null);
            } else {
                tabInfoClear();
                tabInfo(indexesSelectedNode.get(1), indexesSelectedNode.get(0));
                tabAnalisysSteps(indexesSelectedNode.get(1), indexesSelectedNode.get(0));

                FileWithPsiElement.psiElement(mainKeys.getRuns().get(indexesSelectedNode.get(1)).getResults().get(indexesSelectedNode.get(0)).getLocations().get(0).getPhysicalLocation().getArtifactLocation().getUri(),
                        getPosition(mainKeys.getRuns().get(indexesSelectedNode.get(1)).getResults().get(indexesSelectedNode.get(0)).getLocations().get(0).getPhysicalLocation().getRegion().getStartLine(),
                                mainKeys.getRuns().get(indexesSelectedNode.get(1)).getResults().get(indexesSelectedNode.get(0)).getLocations().get(0).getPhysicalLocation().getRegion().getStartColumn(),
                                mainKeys.getRuns().get(indexesSelectedNode.get(1)).getResults().get(indexesSelectedNode.get(0)).getLocations().get(0).getPhysicalLocation().getRegion().getEndLine(),
                                mainKeys.getRuns().get(indexesSelectedNode.get(1)).getResults().get(indexesSelectedNode.get(0)).getLocations().get(0).getPhysicalLocation().getRegion().getEndColumn()));
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
                    for (int runsIndex = 0; runsIndex < mainKeys.getRuns().size(); runsIndex++) {
                        for (int resultsIndex = 0; resultsIndex < mainKeys.getRuns().get(runsIndex).getResults().size(); resultsIndex++) {
                            if (mainKeys.getRuns().get(runsIndex).getResults().get(resultsIndex).getMessage().getText().contains(obj.toString())) {
                                setIcon(getIconNode(mainKeys.getRuns().get(runsIndex).getResults().get(resultsIndex).getLevel()));
                                break;
                            }
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

    private ArrayList<Integer> getNodeIndex(JTree tree, DefaultMutableTreeNode node, int indx) {
        TreeNode root = (TreeNode) tree.getModel().getRoot();
        if (node == root)
            return null;

        TreeNode parent = node.getParent();
        if (parent == null)
            return null;
        else
            indexesSelectedNode.add(indx, parent.getIndex(node));

        ArrayList<Integer> parentIndex = getNodeIndex(tree, (DefaultMutableTreeNode) parent, 1);
        if (parentIndex == null)
            return null;

        return indexesSelectedNode;
    }

    private void getModelJTree(JTree jTree) {
        DefaultMutableTreeNode parentRoot = new DefaultMutableTreeNode("sarif_view");
        DefaultMutableTreeNode errorNameFile;

        for (int runsIndex = 0; runsIndex < mainKeys.getRuns().size(); runsIndex++) {
            String[] uri = mainKeys.getRuns().get(runsIndex).getResults().get(0).getLocations().get(0).getPhysicalLocation().getArtifactLocation().getUri().split("/");
            errorNameFile = new DefaultMutableTreeNode(uri[uri.length - 1]);
            parentRoot.add(errorNameFile);

            for (int resultsIndex = 0; resultsIndex < mainKeys.getRuns().get(runsIndex).getResults().size(); resultsIndex++) {
                errorNameFile.add(new DefaultMutableTreeNode(mainKeys.getRuns().get(runsIndex).getResults().get(resultsIndex).getMessage().getText().split(" {2}")[0]));

                DefaultTreeModel model = (DefaultTreeModel) this.treeLocations.getModel();
                model.setRoot(parentRoot);

                jTree.setModel(model);
            }
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

    private void tabInfo(int runsIndex, int resultsIndex) {
        String[] txtMessage = mainKeys.getRuns().get(runsIndex).getResults().get(resultsIndex).getMessage().getText().split(" {2}");
        StringBuilder txtMess = new StringBuilder();
        lblTxtMessage.setVisible(true);

        checkSelectRunsIndex = runsIndex;
        checkSelectResultsIndex = resultsIndex;

        for (int i = 0; i < txtMessage.length; i++) {
            if (txtMessage[i].contains("](1)")) {
                txtMessage[i] = editTxtMessage(txtMessage[i], "](1)");
            }
            if (txtMessage[i].contains("](2)")) {
                txtMessage[i] = editTxtMessage(txtMessage[i], "](2)");
            }
            if (txtMessage[i].contains("](3)")) {
                txtMessage[i] = editTxtMessage(txtMessage[i], "](3)");
            }

            if (i == 0)
                txtMess.append(txtMessage[i]);
            else
                txtMess.append("<br>").append(txtMessage[i]);
        }

        lblTxtMessage.setText("<html>" + txtMess + "</html>");

        lblTxtMessage.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (resultsIndex == checkSelectResultsIndex && runsIndex == checkSelectRunsIndex)
                    FileWithPsiElement.psiElement(mainKeys.getRuns().get(runsIndex).getResults().get(resultsIndex).getRelatedLocations().get(0).getPhysicalLocation().getArtifactLocation().getUri(),
                            getPosition(mainKeys.getRuns().get(runsIndex).getResults().get(resultsIndex).getRelatedLocations().get(0).getPhysicalLocation().getRegion().getStartLine(),
                                    mainKeys.getRuns().get(runsIndex).getResults().get(resultsIndex).getRelatedLocations().get(0).getPhysicalLocation().getRegion().getStartColumn(),
                                    mainKeys.getRuns().get(runsIndex).getResults().get(resultsIndex).getRelatedLocations().get(0).getPhysicalLocation().getRegion().getEndLine(),
                                    mainKeys.getRuns().get(runsIndex).getResults().get(resultsIndex).getRelatedLocations().get(0).getPhysicalLocation().getRegion().getEndColumn()));
            }
        });

        String ruleId = mainKeys.getRuns().get(runsIndex).getResults().get(resultsIndex).getRuleId();
        ruleIdLabel.setVisible(true);
        lblRulId.setVisible(true);
        lblRulId.setText(Objects.requireNonNullElse(ruleId, "-"));

        String ruleName = mainKeys.getRuns().get(runsIndex).getTool().getDriver().getRules().get(0).getName();
        ruleNameLabel.setVisible(true);
        lblRulName.setVisible(true);
        lblRulName.setText(Objects.requireNonNullElse(ruleName, "-"));

        String ruleDescription = mainKeys.getRuns().get(runsIndex).getTool().getDriver().getRules().get(0).getShortDescription().getText();
        ruleDescriptionLabel.setVisible(true);
        lblRulDes.setVisible(true);
        lblRulDes.setText(Objects.requireNonNullElse(ruleDescription, "-"));

        String level = mainKeys.getRuns().get(runsIndex).getResults().get(resultsIndex).getLevel();
        levelLabel.setVisible(true);
        lblLvl.setVisible(true);
        lblLvl.setText(Objects.requireNonNullElse(level, "-"));

        String[] uri = mainKeys.getRuns().get(runsIndex).getResults().get(resultsIndex).getLocations().get(0).getPhysicalLocation().getArtifactLocation().getUri().split("/");
        locationLabel.setVisible(true);
        lblLoc.setVisible(true);
        lblLoc.setText("<html><u>" + uri[uri.length - 1] + "</u></html>");
        lblLoc.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (resultsIndex == checkSelectResultsIndex && runsIndex == checkSelectRunsIndex)
                    FileWithPsiElement.psiElement(mainKeys.getRuns().get(runsIndex).getResults().get(resultsIndex).getLocations().get(0).getPhysicalLocation().getArtifactLocation().getUri(),
                            getPosition(mainKeys.getRuns().get(runsIndex).getResults().get(resultsIndex).getLocations().get(0).getPhysicalLocation().getRegion().getStartLine(),
                                    mainKeys.getRuns().get(runsIndex).getResults().get(resultsIndex).getLocations().get(0).getPhysicalLocation().getRegion().getStartColumn(),
                                    mainKeys.getRuns().get(runsIndex).getResults().get(resultsIndex).getLocations().get(0).getPhysicalLocation().getRegion().getEndLine(),
                                    mainKeys.getRuns().get(runsIndex).getResults().get(resultsIndex).getLocations().get(0).getPhysicalLocation().getRegion().getEndColumn()));
            }
        });

        logLabel.setVisible(true);
        lblLog.setVisible(true);
        lblLog.setText("<html><u>" + FileOpen.openFile + "</u></html>");
        lblLog.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        lblLog.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                FileWithPsiElement.psiElement(FileOpen.pathFile, null);
            }
        });
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

    private void tabAnalisysSteps(int runsIndex, int resultsIndex) {
        messageAnalysisSteps.setVisible(false);
        scrollPaneAnalysisSteps.setVisible(false);
        checkSelectRunsIndex = runsIndex;
        checkSelectResultsIndex = resultsIndex;

        DefaultListModel<String> defaultListModel = new DefaultListModel<>();

        if (mainKeys.getRuns().get(runsIndex).getResults().get(resultsIndex).getCodeFlows() != null) {
            scrollPaneAnalysisSteps.setVisible(true);
            for (int analisysStep = 0; analisysStep < mainKeys.getRuns().get(runsIndex).getResults().get(resultsIndex).getCodeFlows().get(0).getThreadFlows().get(0).getLocations().size(); analisysStep++) {
                if (mainKeys.getRuns().get(runsIndex).getResults().get(resultsIndex).getCodeFlows().get(0).getThreadFlows().get(0).getLocations().get(analisysStep).getLocation().getMessage() != null)
                    defaultListModel.addElement(mainKeys.getRuns().get(runsIndex).getResults().get(resultsIndex).getCodeFlows().get(0).getThreadFlows().get(0).getLocations().get(analisysStep).getLocation().getMessage().getText());
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

                if (resultsIndex == checkSelectResultsIndex && runsIndex == checkSelectRunsIndex)
                    FileWithPsiElement.psiElement(
                        mainKeys.getRuns().get(runsIndex).getResults().get(resultsIndex).getCodeFlows().get(0).getThreadFlows().get(0).getLocations().get(selected).getLocation().getPhysicalLocation().getArtifactLocation().getUri(),
                        getPosition(mainKeys.getRuns().get(runsIndex).getResults().get(resultsIndex).getCodeFlows().get(0).getThreadFlows().get(0).getLocations().get(selected).getLocation().getPhysicalLocation().getRegion().getStartLine(),
                                mainKeys.getRuns().get(runsIndex).getResults().get(resultsIndex).getCodeFlows().get(0).getThreadFlows().get(0).getLocations().get(selected).getLocation().getPhysicalLocation().getRegion().getStartColumn(),
                                mainKeys.getRuns().get(runsIndex).getResults().get(resultsIndex).getCodeFlows().get(0).getThreadFlows().get(0).getLocations().get(selected).getLocation().getPhysicalLocation().getRegion().getEndLine(),
                                mainKeys.getRuns().get(runsIndex).getResults().get(resultsIndex).getCodeFlows().get(0).getThreadFlows().get(0).getLocations().get(selected).getLocation().getPhysicalLocation().getRegion().getEndColumn()));
            }
        });
    }

    private String editTxtMessage(String txtMessage, String str) {
        txtMessage = txtMessage.replace("[", "<u style='color:#589df6;'>");
        txtMessage = txtMessage.replace(str, "</u>");
        return txtMessage;
    }

    private ArrayList<Integer> getPosition(Integer startLine, Integer startColumn, Integer endLine, Integer endColumn) {
        position.clear();
        position.add(0, startLine);
        position.add(1, startColumn);
        position.add(2, endLine);
        position.add(3, endColumn);

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