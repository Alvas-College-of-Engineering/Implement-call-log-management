package com.calllog.ui;

import com.calllog.model.Call;
import com.calllog.service.CallLogManager;
import com.calllog.util.CallType;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Advanced Swing Patterns and Best Practices
 * 
 * This class demonstrates:
 * 1. Custom components
 * 2. Model-View separation
 * 3. Action/Change listeners
 * 4. Keyboard shortcuts
 * 5. Look and Feel customization
 * 6. Resource management
 * 7. Component reusability
 */
public class AdvancedSwingPatterns {

    /**
     * Example 1: Custom Button with Enhanced Features
     */
    public static class EnhancedButton extends JButton {
        private Color defaultColor;
        private Color hoverColor;
        private Color pressedColor;

        public EnhancedButton(String text, Color defaultColor, Color hoverColor) {
            super(text);
            this.defaultColor = defaultColor;
            this.hoverColor = hoverColor;
            this.pressedColor = new Color(
                    hoverColor.getRed() - 30,
                    hoverColor.getGreen() - 30,
                    hoverColor.getBlue() - 30);

            initializeButton();
        }

        private void initializeButton() {
            setFont(new Font("Arial", Font.BOLD, 12));
            setForeground(Color.WHITE);
            setBackground(defaultColor);
            setFocusPainted(false);
            setBorderPainted(false);
            setOpaque(true);

            addMouseListener(new java.awt.event.MouseAdapter() {
                @Override
                public void mouseEntered(java.awt.event.MouseEvent evt) {
                    setBackground(hoverColor);
                }

                @Override
                public void mouseExited(java.awt.event.MouseEvent evt) {
                    setBackground(defaultColor);
                }

                @Override
                public void mousePressed(java.awt.event.MouseEvent evt) {
                    setBackground(pressedColor);
                }

                @Override
                public void mouseReleased(java.awt.event.MouseEvent evt) {
                    setBackground(hoverColor);
                }
            });
        }
    }

    /**
     * Example 2: Custom Dialog with Validation
     */
    public static class ValidatedDialog extends JDialog {
        private JTextField phoneField;
        private JTextField durationField;
        private JComboBox<CallType> typeCombo;
        private boolean isConfirmed = false;
        private Call resultCall;

        public ValidatedDialog(Frame parent, String title) {
            super(parent, title, true);
            initializeDialog();
        }

        private void initializeDialog() {
            setDefaultCloseOperation(DISPOSE_ON_CLOSE);
            setSize(400, 300);
            setLocationRelativeTo(getParent());

            JPanel mainPanel = new JPanel(new GridBagLayout());
            mainPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.insets = new Insets(5, 5, 5, 5);
            gbc.anchor = GridBagConstraints.WEST;

            // Phone Number
            gbc.gridx = 0;
            gbc.gridy = 0;
            mainPanel.add(new JLabel("Phone Number:"), gbc);
            gbc.gridx = 1;
            phoneField = new JTextField(20);
            mainPanel.add(phoneField, gbc);

            // Call Type
            gbc.gridx = 0;
            gbc.gridy = 1;
            mainPanel.add(new JLabel("Call Type:"), gbc);
            gbc.gridx = 1;
            typeCombo = new JComboBox<>(CallType.values());
            mainPanel.add(typeCombo, gbc);

            // Duration
            gbc.gridx = 0;
            gbc.gridy = 2;
            mainPanel.add(new JLabel("Duration (sec):"), gbc);
            gbc.gridx = 1;
            durationField = new JTextField(20);
            mainPanel.add(durationField, gbc);

            // Buttons
            gbc.gridx = 0;
            gbc.gridy = 3;
            gbc.gridwidth = 2;
            gbc.anchor = GridBagConstraints.CENTER;

            JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 0));
            JButton saveBtn = new JButton("Save");
            JButton cancelBtn = new JButton("Cancel");

            saveBtn.addActionListener(e -> onSave());
            cancelBtn.addActionListener(e -> dispose());

            buttonPanel.add(saveBtn);
            buttonPanel.add(cancelBtn);
            mainPanel.add(buttonPanel, gbc);

            add(mainPanel);
            setVisible(true);
        }

        private void onSave() {
            if (!validateInput()) {
                JOptionPane.showMessageDialog(this,
                        "Please fill all fields with valid data",
                        "Validation Error",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }

            resultCall = new Call(
                    phoneField.getText(),
                    Integer.parseInt(durationField.getText()),
                    (CallType) typeCombo.getSelectedItem(),
                    LocalDateTime.now());
            isConfirmed = true;
            dispose();
        }

        private boolean validateInput() {
            String phone = phoneField.getText().trim();
            String duration = durationField.getText().trim();

            if (phone.isEmpty() || duration.isEmpty())
                return false;

            try {
                Integer.parseInt(duration);
                return phone.matches("\\d{10}");
            } catch (NumberFormatException e) {
                return false;
            }
        }

        public Call getResult() {
            return resultCall;
        }

        public boolean isConfirmed() {
            return isConfirmed;
        }
    }

    /**
     * Example 3: Custom Panel with Live Data Updates
     */
    public static class LiveStatisticsPanel extends JPanel implements ChangeListener {
        private JLabel callCountLabel;
        private JLabel totalDurationLabel;
        private JProgressBar totalCallsProgress;
        private CallLogManager manager;

        public LiveStatisticsPanel(CallLogManager manager) {
            this.manager = manager;
            initializePanel();
        }

        private void initializePanel() {
            setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
            setBorder(BorderFactory.createTitledBorder("Live Statistics"));

            // Call Count
            callCountLabel = new JLabel("Total Calls: 0");
            callCountLabel.setFont(new Font("Arial", Font.BOLD, 14));
            add(callCountLabel);

            // Progress Bar
            totalCallsProgress = new JProgressBar(0, 100);
            totalCallsProgress.setStringPainted(true);
            totalCallsProgress.setString("0 calls");
            add(totalCallsProgress);

            // Total Duration
            totalDurationLabel = new JLabel("Total Duration: 0 seconds");
            totalDurationLabel.setFont(new Font("Arial", Font.PLAIN, 12));
            add(totalDurationLabel);

            add(Box.createVerticalGlue());
        }

        public void updateStatistics() {
            SwingUtilities.invokeLater(() -> {
                try {
                    java.lang.reflect.Field field = CallLogManager.class.getDeclaredField("callList");
                    field.setAccessible(true);
                    @SuppressWarnings("unchecked")
                    java.util.ArrayList<Call> calls = (java.util.ArrayList<Call>) field.get(manager);

                    int count = calls.size();
                    int duration = 0;
                    for (Call call : calls) {
                        duration += call.getDuration();
                    }

                    callCountLabel.setText("Total Calls: " + count);
                    totalCallsProgress.setValue(Math.min(count, 100));
                    totalCallsProgress.setString(count + " calls");
                    totalDurationLabel.setText("Total Duration: " + duration + " seconds");

                } catch (NoSuchFieldException | IllegalAccessException e) {
                    e.printStackTrace();
                }
            });
        }

        @Override
        public void stateChanged(ChangeEvent e) {
            updateStatistics();
        }
    }

    /**
     * Example 4: Singleton Pattern for Theme Management
     */
    public static class ThemeManager {
        private static ThemeManager instance;
        private Color primaryColor = new Color(66, 133, 244);
        private Color secondaryColor = new Color(52, 168, 83);
        private Font defaultFont = new Font("Arial", Font.PLAIN, 12);

        private ThemeManager() {
        }

        public static ThemeManager getInstance() {
            if (instance == null) {
                instance = new ThemeManager();
            }
            return instance;
        }

        public void applyTheme(JFrame frame) {
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            // Apply theme styling
        }

        public Color getPrimaryColor() {
            return primaryColor;
        }

        public Color getSecondaryColor() {
            return secondaryColor;
        }

        public Font getDefaultFont() {
            return defaultFont;
        }

        public void setColors(Color primary, Color secondary) {
            this.primaryColor = primary;
            this.secondaryColor = secondary;
        }
    }

    /**
     * Example 5: Action with Keyboard Shortcut
     */
    public static class ShortcutAction extends AbstractAction {
        private Runnable action;

        public ShortcutAction(String name, Runnable action, KeyStroke keyStroke) {
            super(name);
            this.action = action;
            putValue(ACCELERATOR_KEY, keyStroke);
        }

        @Override
        public void actionPerformed(java.awt.event.ActionEvent e) {
            action.run();
        }
    }

    /**
     * Example 6: Demo Frame showing all patterns
     */
    public static class DemoFrame extends JFrame {
        public DemoFrame() {
            setTitle("Advanced Swing Patterns Demo");
            setDefaultCloseOperation(EXIT_ON_CLOSE);
            setSize(600, 400);
            setLocationRelativeTo(null);

            JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
            mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

            // Enhanced Button Example
            EnhancedButton addBtn = new EnhancedButton("Add Call",
                    new Color(76, 175, 80),
                    new Color(56, 155, 60));
            addBtn.addActionListener(e -> {
                JOptionPane.showMessageDialog(this, "Enhanced button clicked!");
            });

            // Keyboard shortcut
            getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW)
                    .put(KeyStroke.getKeyStroke(KeyEvent.VK_N, java.awt.event.InputEvent.CTRL_DOWN_MASK), "addCall");
            getRootPane().getActionMap().put("addCall", new ShortcutAction("Add Call", () -> {
                JOptionPane.showMessageDialog(this, "Ctrl+N shortcut works!");
            }, KeyStroke.getKeyStroke(KeyEvent.VK_N, java.awt.event.InputEvent.CTRL_DOWN_MASK)));

            mainPanel.add(addBtn, BorderLayout.NORTH);

            JPanel centerPanel = new JPanel();
            centerPanel.add(new JLabel("Advanced Swing Patterns Demonstrated:"));
            centerPanel.add(new JLabel(
                    "1. Custom Components (EnhancedButton)\n2. Validated Dialogs\n3. Live Updates\n4. Singleton Theme\n5. Keyboard Shortcuts"));
            mainPanel.add(centerPanel, BorderLayout.CENTER);

            add(mainPanel);
            setVisible(true);
        }
    }

    /**
     * Main method to demonstrate patterns
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new DemoFrame());
    }
}
