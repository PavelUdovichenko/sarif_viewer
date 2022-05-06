package org.example.sarif_viewer.psi;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

public class PSIMouseListener implements MouseListener {
    String fileName;
    ArrayList<Integer> position;

    public PSIMouseListener(String fName, ArrayList<Integer> pos) {
        fileName = fName;
        position = pos;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        FileWithPsiElement.psiElement(fileName, position);
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}