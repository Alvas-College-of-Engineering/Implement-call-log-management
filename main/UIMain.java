package com.calllog.main;

import com.calllog.ui.CallLogUI;

/**
 * Entry point for the Swing-based Call Log UI
 */
public class UIMain {
    public static void main(String[] args) {
        // Launch the Swing GUI
        javax.swing.SwingUtilities.invokeLater(() -> new CallLogUI());
    }
}
