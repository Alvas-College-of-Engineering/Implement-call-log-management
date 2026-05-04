package com.calllog.ui;

import com.calllog.model.Call;
import com.calllog.service.CallLogManager;
import com.calllog.util.CallType;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Advanced Statistics Panel with Custom Painting
 * Demonstrates custom painting and graphics rendering
 */
public class CallStatisticsPanel extends JPanel {

    private CallLogManager manager;
    private int incomingCount = 0;
    private int outgoingCount = 0;
    private int missedCount = 0;
    private int totalDuration = 0;

    public CallStatisticsPanel(CallLogManager manager) {
        this.manager = manager;
        setPreferredSize(new Dimension(500, 200));
        setBorder(BorderFactory.createTitledBorder("Call Statistics"));
        updateStatistics();
    }

    /**
     * Update statistics from call list
     */
    public void updateStatistics() {
        try {
            java.lang.reflect.Field field = CallLogManager.class.getDeclaredField("callList");
            field.setAccessible(true);
            @SuppressWarnings("unchecked")
            ArrayList<Call> callList = (ArrayList<Call>) field.get(manager);

            incomingCount = 0;
            outgoingCount = 0;
            missedCount = 0;
            totalDuration = 0;

            for (Call call : callList) {
                totalDuration += call.getDuration();
                if (call.getType() == CallType.INCOMING) {
                    incomingCount++;
                } else if (call.getType() == CallType.OUTGOING) {
                    outgoingCount++;
                } else if (call.getType() == CallType.MISSED) {
                    missedCount++;
                }
            }
            repaint();
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        int width = getWidth();
        int height = getHeight();
        int padding = 20;

        // Draw title
        g2d.setFont(new Font("Arial", Font.BOLD, 16));
        g2d.drawString("Call Statistics", padding, 30);

        // Draw pie chart
        drawPieChart(g2d, width / 2 - 100, 60, 150, 150);

        // Draw statistics text
        drawStatisticsText(g2d, width / 2 + 30, 60);
    }

    /**
     * Draw a pie chart showing call type distribution
     */
    private void drawPieChart(Graphics2D g2d, int x, int y, int width, int height) {
        int total = incomingCount + outgoingCount + missedCount;

        if (total == 0) {
            g2d.setFont(new Font("Arial", Font.PLAIN, 12));
            g2d.drawString("No calls available", x + 30, y + height / 2);
            return;
        }

        // Calculate angles
        int incomingAngle = (int) ((incomingCount * 360.0) / total);
        int outgoingAngle = (int) ((outgoingCount * 360.0) / total);

        // Draw Incoming (Green)
        g2d.setColor(new Color(144, 238, 144));
        g2d.fillArc(x, y, width, height, 0, incomingAngle);
        g2d.setColor(Color.BLACK);
        g2d.drawArc(x, y, width, height, 0, incomingAngle);

        // Draw Outgoing (Orange)
        g2d.setColor(new Color(255, 218, 185));
        g2d.fillArc(x, y, width, height, incomingAngle, outgoingAngle);
        g2d.setColor(Color.BLACK);
        g2d.drawArc(x, y, width, height, incomingAngle, outgoingAngle);

        // Draw Missed (Red)
        g2d.setColor(new Color(255, 182, 193));
        g2d.fillArc(x, y, width, height, incomingAngle + outgoingAngle, 360 - incomingAngle - outgoingAngle);
        g2d.setColor(Color.BLACK);
        g2d.drawArc(x, y, width, height, incomingAngle + outgoingAngle, 360 - incomingAngle - outgoingAngle);

        // Draw border
        g2d.setStroke(new BasicStroke(2));
        g2d.drawOval(x, y, width, height);
    }

    /**
     * Draw statistics text
     */
    private void drawStatisticsText(Graphics2D g2d, int x, int y) {
        g2d.setFont(new Font("Arial", Font.BOLD, 12));
        int lineHeight = 20;
        int currentY = y;

        int total = incomingCount + outgoingCount + missedCount;
        double avgDuration = total > 0 ? totalDuration / (double) total : 0;

        // Total Calls
        g2d.setColor(Color.BLUE);
        g2d.drawString("Total Calls: " + total, x, currentY);
        currentY += lineHeight;

        // Incoming
        g2d.setColor(new Color(0, 128, 0));
        g2d.drawString("Incoming: " + incomingCount, x, currentY);
        currentY += lineHeight;

        // Outgoing
        g2d.setColor(new Color(255, 140, 0));
        g2d.drawString("Outgoing: " + outgoingCount, x, currentY);
        currentY += lineHeight;

        // Missed
        g2d.setColor(Color.RED);
        g2d.drawString("Missed: " + missedCount, x, currentY);
        currentY += lineHeight + 10;

        // Total Duration
        g2d.setColor(Color.BLACK);
        g2d.setFont(new Font("Arial", Font.PLAIN, 11));
        g2d.drawString("Total Duration: " + totalDuration + " sec", x, currentY);
        currentY += 15;

        // Average Duration
        g2d.drawString(String.format("Avg Duration: %.2f sec", avgDuration), x, currentY);
    }
}
