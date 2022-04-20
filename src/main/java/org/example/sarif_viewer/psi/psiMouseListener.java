package org.example.sarif_viewer.psi;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class psiMouseListener implements MouseListener {
    String fileName;

    public psiMouseListener(String fName) {
        fileName = fName;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        fileWithPsiElement.psiElement(fileName);
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