package com.calllog.ui;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;
import com.calllog.util.CallType;

/**
 * Custom Cell Renderer for Call Type Column
 * Uses different colors based on call type
 */
public class CallTypeRenderer extends DefaultTableCellRenderer {

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
            int row, int column) {
        super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

        if (value instanceof CallType) {
            CallType type = (CallType) value;
            setText(type.toString());
            setHorizontalAlignment(CENTER);
            setFont(new Font("Arial", Font.BOLD, 12));

            switch (type) {
                case INCOMING:
                    setBackground(new Color(144, 238, 144)); // Light Green
                    setForeground(Color.BLACK);
                    break;
                case OUTGOING:
                    setBackground(new Color(255, 218, 185)); // Light Orange
                    setForeground(Color.BLACK);
                    break;
                case MISSED:
                    setBackground(new Color(255, 182, 193)); // Light Red
                    setForeground(Color.BLACK);
                    break;
            }
        } else {
            setBackground(Color.WHITE);
        }

        if (isSelected) {
            setBackground(table.getSelectionBackground());
            setForeground(table.getSelectionForeground());
        }

        return this;
    }
}
