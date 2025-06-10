import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.text.*;
import java.util.*;

public class App extends JFrame {
    private JTextField reminderField;
    private JSpinner dateSpinner;
    private JTable reminderTable;
    private DefaultTableModel tableModel;

    private java.util.List<Reminder> reminders = new ArrayList<>();

    public App() {
        setTitle("Reminder App");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 400);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Top panel: input
        JPanel inputPanel = new JPanel(new GridLayout(3, 2, 5, 5));

        inputPanel.add(new JLabel("Reminder Message:"));
        reminderField = new JTextField();
        inputPanel.add(reminderField);

        inputPanel.add(new JLabel("Date and Time:"));
        SpinnerDateModel dateModel = new SpinnerDateModel(new Date(), null, null, Calendar.MINUTE);
        dateSpinner = new JSpinner(dateModel);
        JSpinner.DateEditor timeEditor = new JSpinner.DateEditor(dateSpinner, "yyyy-MM-dd HH:mm");
        dateSpinner.setEditor(timeEditor);
        inputPanel.add(dateSpinner);

        JButton addButton = new JButton("Add Reminder");
        inputPanel.add(addButton);

        add(inputPanel, BorderLayout.NORTH);

        // Center panel: table
        tableModel = new DefaultTableModel(new Object[]{"Message", "Date & Time"}, 0);
        reminderTable = new JTable(tableModel);
        add(new JScrollPane(reminderTable), BorderLayout.CENTER);

        // Add button action
        addButton.addActionListener(e -> addReminder());

        setVisible(true);
    }

    private void addReminder() {
        String message = reminderField.getText();
        Date date = (Date) dateSpinner.getValue();

        if (message.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Message cannot be empty!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Reminder reminder = new Reminder(message, date);
        reminders.add(reminder);
        tableModel.addRow(new Object[]{message, new SimpleDateFormat("yyyy-MM-dd HH:mm").format(date)});
        reminderField.setText("");

        // Optional: you can start a background thread to show popup when time is reached
    }

    // Reminder class
    static class Reminder {
        String message;
        Date time;

        Reminder(String message, Date time) {
            this.message = message;
            this.time = time;
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(App::new);
    }
}
