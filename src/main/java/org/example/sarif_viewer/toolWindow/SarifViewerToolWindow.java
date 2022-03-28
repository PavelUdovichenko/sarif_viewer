// Copyright 2000-2022 JetBrains s.r.o. and other contributors. Use of this source code is governed by the Apache 2.0
// license that can be found in the LICENSE file.

package org.example.sarif_viewer.toolWindow;

import com.intellij.openapi.wm.ToolWindow;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.Calendar;

public class SarifViewerToolWindow {
    private JPanel myToolWindowContent;
    private JTabbedPane tabbedPanelUp;
    private JTabbedPane tabbedPanelDown;
    private JTable table1;
    private JTree tree1;

    public SarifViewerToolWindow(ToolWindow toolWindow) {
        //hideToolWindowButton.addActionListener(e -> toolWindow.hide(null));
        //refreshToolWindowButton.addActionListener(e -> currentDateTime());

        //this.currentDateTime();
        createTable();
    }

    private void createTable() {
        table1.setModel(new DefaultTableModel(null, new String[]{"Title", "info"}));
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