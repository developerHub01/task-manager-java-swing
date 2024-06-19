import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

public class PreviewTaskPage extends JFrame {
  Container containerBox;

  String taskHeadingValue = "";
  String taskDescriptionValue = "";

  PreviewTaskPage(String taskId) {
    String[] taskData = HandleDataFile.readByIdFromCSVFile(taskId);

    this.getContentPane().setLayout(null);
    setTitle("Task Details");
    setDefaultCloseOperation(EXIT_ON_CLOSE);
    setSize(500, 500);
    setResizable(false);
    setLocationRelativeTo(null);

    JLabel heading = new JLabel("Task Details", JLabel.CENTER);
    heading.setFont(new Font("Poppins", Font.BOLD, 27));
    heading.setForeground(Color.decode(ThemeConstant.THEME_BG_COLOR));
    heading.setBounds(160, 25, 190, 33);

    JTextField taskHeading = new JTextField();
    taskHeading.setText(taskData[0]);
    taskHeading.setEditable(false);
    taskHeading.setBounds(54, 70, 392, 40);
    taskHeading.setBorder(new LineBorder(Color.decode(ThemeConstant.THEME_DISABLED_COLOR), 3));
    taskHeading.setFont(new Font("Poppins", Font.PLAIN, 15));

    JTextArea taskDescription = new JTextArea();
    taskDescription.setText(taskData[1]);
    taskDescription.setEditable(false);
    taskDescription.setFont(new Font("Poppins", Font.PLAIN, 15));
    taskDescription.setLineWrap(true);
    taskDescription.setWrapStyleWord(true);

    JScrollPane taskDescriptionWrapper = new JScrollPane(taskDescription);
    taskDescriptionWrapper.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
    taskDescriptionWrapper.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

    taskDescriptionWrapper.setBounds(54, 125, 392, 250);
    taskDescriptionWrapper.setBorder(new LineBorder(Color.decode(ThemeConstant.THEME_DISABLED_COLOR), 3));

    JButton editButton = new JButton("Edit");
    editButton.setBounds(54, 390, 185, 40);
    editButton.setBackground(Color.decode(ThemeConstant.THEME_BG_COLOR));
    editButton.setForeground(Color.decode(ThemeConstant.THEME_FG_COLOR));
    editButton.setFont(new Font("Poppins", Font.BOLD, 16));
    editButton.setCursor(new Cursor(Cursor.HAND_CURSOR));

    JButton backButton = new JButton("Back");
    backButton.setBounds(261, 390, 185, 40);
    backButton.setBackground(Color.decode(ThemeConstant.THEME_BG_COLOR));
    backButton.setForeground(Color.decode(ThemeConstant.THEME_FG_COLOR));
    backButton.setFont(new Font("Poppins", Font.BOLD, 16));
    backButton.setCursor(new Cursor(Cursor.HAND_CURSOR));

    editButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        dispose();
        new UpdateTaskPage(taskId);
      }
    });

    backButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        dispose();
        new AllTaskPage();
      }
    });

    add(heading);
    add(taskHeading);
    add(taskDescriptionWrapper, BorderLayout.CENTER);
    add(editButton);
    add(backButton);

    setVisible(true);
  }

  public static void main(String[] args) {
    new CreateTaskPage();
  }
}
