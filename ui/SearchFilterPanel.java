package com.calllog.ui;

import com.calllog.model.Call;
import com.calllog.util.CallType;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * Advanced Search and Filter Panel
 * Demonstrates real-time filtering, document listeners, and dynamic table
 * updates
 */
public class SearchFilterPanel extends JPanel {

    private JTextField searchField;
    private JComboBox<CallType> callTypeFilter;
    private JCheckBox showIncomingCheckBox;
    private JCheckBox showOutgoingCheckBox;
    private JCheckBox showMissedCheckBox;
    private DefaultTableModel tableModel;
    private List<Call> allCalls;

    public SearchFilterPanel(DefaultTableModel tableModel) {
        this.tableModel = tableModel;
        this.allCalls = new ArrayList<>();
        initializePanel();
    }

    private void initializePanel() {
        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createTitledBorder("Search & Filter"));
        setBackground(new Color(240, 240, 240));

        // Search Panel
        JPanel searchPanel = createSearchPanel();
        add(searchPanel, BorderLayout.NORTH);

        // Filter Panel
        JPanel filterPanel = createFilterPanel();
        add(filterPanel, BorderLayout.CENTER);
    }

    /**
     * Create search input panel
     */
    private JPanel createSearchPanel() {
        JPanel panel = new JPanel(new BorderLayout(5, 5));
        panel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        JLabel searchLabel = new JLabel("Search Phone Number:");
        searchField = new JTextField(20);
        searchField.setFont(new Font("Arial", Font.PLAIN, 12));

        // Add document listener for real-time search
        searchField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                performSearch();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                performSearch();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                performSearch();
            }
        });

        JButton clearButton = new JButton("Clear");
        clearButton.addActionListener(e -> {
            searchField.setText("");
            performSearch();
        });

        panel.add(searchLabel, BorderLayout.WEST);
        panel.add(searchField, BorderLayout.CENTER);
        panel.add(clearButton, BorderLayout.EAST);

        return panel;
    }

    /**
     * Create filter options panel
     */
    private JPanel createFilterPanel() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 5));
        panel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        // Call Type Filter
        JLabel typeLabel = new JLabel("Filter by Type:");
        callTypeFilter = new JComboBox<>();
        callTypeFilter.addItem(null); // Show all
        callTypeFilter.addItem(CallType.INCOMING);
        callTypeFilter.addItem(CallType.OUTGOING);
        callTypeFilter.addItem(CallType.MISSED);
        callTypeFilter.addActionListener(e -> performSearch());

        // Checkboxes for multi-select filtering
        showIncomingCheckBox = new JCheckBox("Incoming", true);
        showIncomingCheckBox.addActionListener(e -> performSearch());

        showOutgoingCheckBox = new JCheckBox("Outgoing", true);
        showOutgoingCheckBox.addActionListener(e -> performSearch());

        showMissedCheckBox = new JCheckBox("Missed", true);
        showMissedCheckBox.addActionListener(e -> performSearch());

        JButton applyButton = new JButton("Apply Filters");
        applyButton.addActionListener(e -> performSearch());

        panel.add(typeLabel);
        panel.add(callTypeFilter);
        panel.add(new JSeparator(JSeparator.VERTICAL));
        panel.add(showIncomingCheckBox);
        panel.add(showOutgoingCheckBox);
        panel.add(showMissedCheckBox);
        panel.add(applyButton);

        return panel;
    }

    /**
     * Perform real-time search and filtering
     */
    private void performSearch() {
        tableModel.setRowCount(0);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");

        String searchText = searchField.getText().toLowerCase().trim();
        CallType selectedType = (CallType) callTypeFilter.getSelectedItem();

        // Apply filters
        List<Call> filteredCalls = new ArrayList<>();
        for (Call call : allCalls) {
            // Check call type filters
            boolean typeMatch = false;
            if (call.getType() == CallType.INCOMING && showIncomingCheckBox.isSelected()) {
                typeMatch = true;
            } else if (call.getType() == CallType.OUTGOING && showOutgoingCheckBox.isSelected()) {
                typeMatch = true;
            } else if (call.getType() == CallType.MISSED && showMissedCheckBox.isSelected()) {
                typeMatch = true;
            }

            // Check dropdown filter
            if (selectedType != null && call.getType() != selectedType) {
                typeMatch = false;
            }

            // Check search text
            boolean searchMatch = searchText.isEmpty() ||
                    call.getPhoneNumber().toLowerCase().contains(searchText);

            if (typeMatch && searchMatch) {
                filteredCalls.add(call);
            }
        }

        // Add filtered calls to table
        for (Call call : filteredCalls) {
            Object[] row = {
                    call.getPhoneNumber(),
                    call.getType(),
                    call.getDuration(),
                    call.getTime().format(formatter)
            };
            tableModel.addRow(row);
        }
    }

    /**
     * Update all calls for filtering
     */
    public void setAllCalls(List<Call> calls) {
        this.allCalls = new ArrayList<>(calls);
        performSearch();
    }

    /**
     * Clear all search and filters
     */
    public void resetFilters() {
        searchField.setText("");
        callTypeFilter.setSelectedIndex(0);
        showIncomingCheckBox.setSelected(true);
        showOutgoingCheckBox.setSelected(true);
        showMissedCheckBox.setSelected(true);
        performSearch();
    }
}
