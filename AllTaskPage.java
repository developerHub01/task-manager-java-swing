import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class AllTaskPage extends JFrame {
  Container containerBox;

  AllTaskPage() {
    this.getContentPane().setLayout(null);
    setTitle("All Task List...");
    setDefaultCloseOperation(EXIT_ON_CLOSE);
    setSize(500, 500);
    setResizable(false);
    setLocationRelativeTo(null);

    JLabel heading = new JLabel("All taks List", JLabel.CENTER);
    heading.setFont(new Font("Poppins", Font.BOLD, 27));
    heading.setForeground(Color.decode(ThemeConstant.THEME_BG_COLOR));
    heading.setBounds(134, 20, 200, 33);

    ArrayList<String[]> allTaskList = HandleDataFile.readFromCSVFile();

    String[] columnNames = { "Task Title", "Id" };
    DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);

    for (String[] record : allTaskList) {
      tableModel.addRow(new Object[] {
          record[0],
          record[2]
      });
    }
    JTable taskTable = new JTable(tableModel);
    taskTable.setFillsViewportHeight(true);
    taskTable.setRowHeight(25);

    taskTable.addMouseListener(new MouseAdapter() {
      public void mouseClicked(MouseEvent e) {
        try {
          if (e.getClickCount() == 1) {
            JTable target = (JTable) e.getSource();
            int row = target.getSelectedRow();
            String taskId = (String) target.getValueAt(row, 1);

            String[] options = { "View", "Edit", "Delete", "Cancel" };
            int choice = JOptionPane.showOptionDialog(
                null, "Select operation for taskId = " + taskId, "Select operation",
                JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE,
                null, options, options[0]);

            switch (choice) {
              case 0:
                dispose();
                new PreviewTaskPage(taskId);
                break;
              case 1:
                dispose();
                new UpdateTaskPage(taskId);
                break;
              case 2:
                HandleDataFile.deleteTask(taskId);
                dispose();
                new AllTaskPage();
                break;
              default:
                taskTable.clearSelection();
                break;
            }
          }
        } catch (Exception error) {
          System.out.println(error.getMessage());
        }
      }
    });

    JScrollPane allTaskWrapperScroller = new JScrollPane(taskTable);
    allTaskWrapperScroller.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
    allTaskWrapperScroller.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

    allTaskWrapperScroller.setBounds(54, 70, 392, 280);

    JButton createTaskButton = new JButton("Create New Task");
    createTaskButton.setBounds(54, 380, 392, 40);
    createTaskButton.setBackground(Color.decode(ThemeConstant.THEME_BG_COLOR));
    createTaskButton.setForeground(Color.decode(ThemeConstant.THEME_FG_COLOR));
    createTaskButton.setFont(new Font("Poppins", Font.BOLD, 16));
    createTaskButton.setCursor(new Cursor(Cursor.HAND_CURSOR));

    createTaskButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        dispose();
        new CreateTaskPage();
      }
    });

    add(heading);
    add(allTaskWrapperScroller, BorderLayout.CENTER);
    add(createTaskButton);

    setVisible(true);
  }

  public static void main(String[] args) {
    new AllTaskPage();
  }
}
