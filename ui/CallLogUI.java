package com.calllog.ui;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import com.calllog.model.Call;
import com.calllog.service.CallLogManager;
import com.calllog.util.CallType;

/**
 * Main UI Class for Call Log Management System
 * Uses MVC pattern with Swing components
 */
public class CallLogUI extends JFrame {

    private CallLogManager manager;
    private JTable callTable;
    private DefaultTableModel tableModel;
    private JLabel totalCallsLabel;
    private JLabel incomingLabel;
    private JLabel outgoingLabel;
    private JLabel missedLabel;

    public CallLogUI() {
        manager = new CallLogManager();
        initializeUI();
    }

    private void initializeUI() {
        setTitle("Call Log Management System");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 600);
        setLocationRelativeTo(null);
        setResizable(true);

        // Create Menu Bar
        createMenuBar();

        // Create Main Panel
        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Top Panel - Statistics
        mainPanel.add(createStatisticsPanel(), BorderLayout.NORTH);

        // Center Panel - Table
        mainPanel.add(createTablePanel(), BorderLayout.CENTER);

        // Bottom Panel - Buttons
        mainPanel.add(createButtonPanel(), BorderLayout.SOUTH);

        add(mainPanel);
        setVisible(true);
    }

    private void createMenuBar() {
        JMenuBar menuBar = new JMenuBar();

        // File Menu
        JMenu fileMenu = new JMenu("File");
        JMenuItem exitItem = new JMenuItem("Exit");
        exitItem.addActionListener(e -> System.exit(0));
        fileMenu.add(exitItem);

        // Call Menu
        JMenu callMenu = new JMenu("Call");
        JMenuItem addCallItem = new JMenuItem("Add Call");
        addCallItem.addActionListener(e -> showAddCallDialog());
        callMenu.add(addCallItem);

        // View Menu
        JMenu viewMenu = new JMenu("View");
        JMenuItem allCallsItem = new JMenuItem("All Calls");
        allCallsItem.addActionListener(e -> displayAllCalls());
        JMenuItem incomingItem = new JMenuItem("Incoming Calls");
        incomingItem.addActionListener(e -> displayCallsByType(CallType.INCOMING));
        JMenuItem outgoingItem = new JMenuItem("Outgoing Calls");
        outgoingItem.addActionListener(e -> displayCallsByType(CallType.OUTGOING));
        JMenuItem missedItem = new JMenuItem("Missed Calls");
        missedItem.addActionListener(e -> displayCallsByType(CallType.MISSED));

        viewMenu.add(allCallsItem);
        viewMenu.addSeparator();
        viewMenu.add(incomingItem);
        viewMenu.add(outgoingItem);
        viewMenu.add(missedItem);

        menuBar.add(fileMenu);
        menuBar.add(callMenu);
        menuBar.add(viewMenu);
        setJMenuBar(menuBar);
    }

    private JPanel createStatisticsPanel() {
        JPanel statsPanel = new JPanel(new GridLayout(1, 4, 10, 10));
        statsPanel.setBorder(BorderFactory.createTitledBorder("Statistics"));
        statsPanel.setBackground(new Color(240, 240, 240));

        totalCallsLabel = createStatLabel("Total Calls: 0", Color.BLUE);
        incomingLabel = createStatLabel("Incoming: 0", new Color(0, 128, 0));
        outgoingLabel = createStatLabel("Outgoing: 0", new Color(255, 140, 0));
        missedLabel = createStatLabel("Missed: 0", Color.RED);

        statsPanel.add(totalCallsLabel);
        statsPanel.add(incomingLabel);
        statsPanel.add(outgoingLabel);
        statsPanel.add(missedLabel);

        return statsPanel;
    }

    private JLabel createStatLabel(String text, Color color) {
        JLabel label = new JLabel(text);
        label.setFont(new Font("Arial", Font.BOLD, 14));
        label.setForeground(color);
        label.setHorizontalAlignment(SwingConstants.CENTER);
        return label;
    }

    private JPanel createTablePanel() {
        JPanel tablePanel = new JPanel(new BorderLayout());
        tablePanel.setBorder(BorderFactory.createTitledBorder("Call History"));

        // Create Table Model
        String[] columnNames = { "Phone Number", "Type", "Duration (sec)", "Date & Time" };
        tableModel = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        callTable = new JTable(tableModel);
        callTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        callTable.setRowHeight(25);
        callTable.setFont(new Font("Arial", Font.PLAIN, 12));
        callTable.getTableHeader().setFont(new Font("Arial", Font.BOLD, 13));
        callTable.getTableHeader().setBackground(new Color(200, 200, 200));

        // Set Column Widths
        callTable.getColumnModel().getColumn(0).setPreferredWidth(150);
        callTable.getColumnModel().getColumn(1).setPreferredWidth(100);
        callTable.getColumnModel().getColumn(2).setPreferredWidth(150);
        callTable.getColumnModel().getColumn(3).setPreferredWidth(250);

        // Add custom cell renderer
        callTable.getColumnModel().getColumn(1).setCellRenderer(new CallTypeRenderer());

        JScrollPane scrollPane = new JScrollPane(callTable);
        tablePanel.add(scrollPane, BorderLayout.CENTER);

        return tablePanel;
    }

    private JPanel createButtonPanel() {
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));

        JButton addButton = createButton("Add Call", Color.GREEN);
        addButton.addActionListener(e -> showAddCallDialog());

        JButton deleteButton = createButton("Delete Call", Color.RED);
        deleteButton.addActionListener(e -> deleteSelectedCall());

        JButton clearButton = createButton("Clear All", new Color(255, 100, 100));
        clearButton.addActionListener(e -> clearAllCalls());

        JButton refreshButton = createButton("Refresh", Color.BLUE);
        refreshButton.addActionListener(e -> displayAllCalls());

        buttonPanel.add(addButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(clearButton);
        buttonPanel.add(refreshButton);

        return buttonPanel;
    }

    private JButton createButton(String text, Color color) {
        JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.BOLD, 12));
        button.setBackground(color);
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setPreferredSize(new Dimension(120, 35));
        return button;
    }

    private void showAddCallDialog() {
        JDialog addCallDialog = new JDialog(this, "Add New Call", true);
        addCallDialog.setSize(400, 300);
        addCallDialog.setLocationRelativeTo(this);

        JPanel panel = new JPanel(new GridLayout(5, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        JLabel numberLabel = new JLabel("Phone Number:");
        JTextField numberField = new JTextField();

        JLabel typeLabel = new JLabel("Call Type:");
        JComboBox<CallType> typeCombo = new JComboBox<>(CallType.values());

        JLabel durationLabel = new JLabel("Duration (seconds):");
        JTextField durationField = new JTextField();
        durationField.setToolTipText("Optional for MISSED calls, required for others");

        JLabel timeLabel = new JLabel("Time:");
        JLabel timeValue = new JLabel(LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss")));

        JButton saveButton = new JButton("Save");
        JButton cancelButton = new JButton("Cancel");

        saveButton.addActionListener(e -> {
            try {
                String phoneNumber = numberField.getText().trim();
                if (phoneNumber.isEmpty()) {
                    JOptionPane.showMessageDialog(addCallDialog, "Phone number cannot be empty!", "Error",
                            JOptionPane.ERROR_MESSAGE);
                    return;
                }

                CallType type = (CallType) typeCombo.getSelectedItem();
                String durationText = durationField.getText().trim();
                int duration = 0;

                // For MISSED calls, duration is optional (defaults to 0)
                // For INCOMING and OUTGOING, duration is required
                if (type == CallType.MISSED) {
                    if (!durationText.isEmpty()) {
                        try {
                            duration = Integer.parseInt(durationText);
                        } catch (NumberFormatException ex) {
                            JOptionPane.showMessageDialog(addCallDialog, "Duration must be a valid number!", "Error",
                                    JOptionPane.ERROR_MESSAGE);
                            return;
                        }
                    }
                } else {
                    if (durationText.isEmpty()) {
                        JOptionPane.showMessageDialog(addCallDialog, "Duration is required for " + type + " calls!",
                                "Error",
                                JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    try {
                        duration = Integer.parseInt(durationText);
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(addCallDialog, "Duration must be a valid number!", "Error",
                                JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                }

                Call newCall = new Call(phoneNumber, duration, type, LocalDateTime.now());
                manager.addCall(newCall);

                JOptionPane.showMessageDialog(addCallDialog, "Call added successfully!", "Success",
                        JOptionPane.INFORMATION_MESSAGE);
                addCallDialog.dispose();
                displayAllCalls();
                updateStatistics();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(addCallDialog, "Error: " + ex.getMessage(), "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        });

        cancelButton.addActionListener(e -> addCallDialog.dispose());

        panel.add(numberLabel);
        panel.add(numberField);
        panel.add(typeLabel);
        panel.add(typeCombo);
        panel.add(durationLabel);
        panel.add(durationField);
        panel.add(timeLabel);
        panel.add(timeValue);
        panel.add(saveButton);
        panel.add(cancelButton);

        addCallDialog.add(panel);
        addCallDialog.setVisible(true);
    }

    private void displayAllCalls() {
        tableModel.setRowCount(0);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");

        for (java.lang.reflect.Field field : CallLogManager.class.getDeclaredFields()) {
            field.setAccessible(true);
            try {
                @SuppressWarnings("unchecked")
                java.util.ArrayList<Call> callList = (java.util.ArrayList<Call>) field.get(manager);
                for (Call call : callList) {
                    Object[] row = {
                            call.getPhoneNumber(),
                            call.getType(),
                            call.getDuration(),
                            call.getTime().format(formatter)
                    };
                    tableModel.addRow(row);
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        updateStatistics();
    }

    private void displayCallsByType(CallType type) {
        tableModel.setRowCount(0);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");

        for (java.lang.reflect.Field field : CallLogManager.class.getDeclaredFields()) {
            field.setAccessible(true);
            try {
                @SuppressWarnings("unchecked")
                java.util.ArrayList<Call> callList = (java.util.ArrayList<Call>) field.get(manager);
                for (Call call : callList) {
                    if (call.getType() == type) {
                        Object[] row = {
                                call.getPhoneNumber(),
                                call.getType(),
                                call.getDuration(),
                                call.getTime().format(formatter)
                        };
                        tableModel.addRow(row);
                    }
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }

    private void deleteSelectedCall() {
        int selectedRow = callTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Please select a call to delete!", "Warning",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }
        tableModel.removeRow(selectedRow);
        updateStatistics();
    }

    private void clearAllCalls() {
        int confirm = JOptionPane.showConfirmDialog(this, "Are you sure you want to clear all calls?", "Confirm",
                JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            tableModel.setRowCount(0);
            try {
                for (java.lang.reflect.Field field : CallLogManager.class.getDeclaredFields()) {
                    field.setAccessible(true);
                    @SuppressWarnings("unchecked")
                    java.util.ArrayList<Call> callList = (java.util.ArrayList<Call>) field.get(manager);
                    callList.clear();
                }
                updateStatistics();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }

    private void updateStatistics() {
        try {
            for (java.lang.reflect.Field field : CallLogManager.class.getDeclaredFields()) {
                field.setAccessible(true);
                @SuppressWarnings("unchecked")
                java.util.ArrayList<Call> callList = (java.util.ArrayList<Call>) field.get(manager);

                int total = callList.size();
                int incoming = 0, outgoing = 0, missed = 0;

                for (Call call : callList) {
                    if (call.getType() == CallType.INCOMING)
                        incoming++;
                    else if (call.getType() == CallType.OUTGOING)
                        outgoing++;
                    else if (call.getType() == CallType.MISSED)
                        missed++;
                }

                totalCallsLabel.setText("Total Calls: " + total);
                incomingLabel.setText("Incoming: " + incoming);
                outgoingLabel.setText("Outgoing: " + outgoing);
                missedLabel.setText("Missed: " + missed);
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new CallLogUI());
    }
}
