package org.example.sarif_viewer.Psi;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.nio.file.Path;
import java.nio.file.Paths;


public class psiMouseListener implements MouseListener {
    Path pathFile;
    String fileName;

    public psiMouseListener( String fName) {
        pathFile = Paths.get(fName);
        //System.out.println(fName);
        //fPath = pathFile.toAbsolutePath().toString();
        //System.out.println(fName);
        fileName = fName;
    }

    @Override
    public void mouseClicked(MouseEvent e) {

        MyPSI.whatPsi(fileName);

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