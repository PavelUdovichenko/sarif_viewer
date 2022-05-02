package org.example.sarif_viewer.psi;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class psiMouseListener implements MouseListener {
    String fileName;
    Integer[] position;

    public psiMouseListener(String fName, Integer[] pos) {
        fileName = fName;
        position = pos;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        fileWithPsiElement.psiElement(fileName, position);
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