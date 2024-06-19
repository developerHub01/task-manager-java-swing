import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

public class CreateTaskPage extends JFrame {
  Container containerBox;

  String taskHeadingValue = "";
  String taskDescriptionValue = "";

  CreateTaskPage() {
    this.getContentPane().setLayout(null);
    setTitle("Create Task");
    setDefaultCloseOperation(EXIT_ON_CLOSE);
    setSize(500, 500);
    setResizable(false);
    setLocationRelativeTo(null);

    JLabel heading = new JLabel("Create A New Task", JLabel.CENTER);
    heading.setFont(new Font("Poppins", Font.BOLD, 27));
    heading.setForeground(Color.decode(ThemeConstant.THEME_BG_COLOR));
    heading.setBounds(125, 25, 250, 33);

    JTextField taskHeading = new JTextField();
    taskHeading.setBounds(54, 70, 392, 40);
    taskHeading.setBorder(new LineBorder(Color.decode(ThemeConstant.THEME_BG_COLOR), 3));
    taskHeading.setFont(new Font("Poppins", Font.PLAIN, 15));

    JTextArea taskDescription = new JTextArea();
    taskDescription.setFont(new Font("Poppins", Font.PLAIN, 15));
    taskDescription.setLineWrap(true);
    taskDescription.setWrapStyleWord(true);

    JScrollPane taskDescriptionWrapper = new JScrollPane(taskDescription);
    taskDescriptionWrapper.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
    taskDescriptionWrapper.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

    taskDescriptionWrapper.setBounds(54, 125, 392, 250);
    taskDescriptionWrapper.setBorder(new LineBorder(Color.decode(ThemeConstant.THEME_BG_COLOR), 3));

    JButton saveButton = new JButton("Save");
    saveButton.setBounds(54, 390, 185, 40);
    saveButton.setBackground(Color.decode(ThemeConstant.THEME_DISABLED_COLOR));
    saveButton.setForeground(Color.decode(ThemeConstant.THEME_FG_COLOR));
    saveButton.setFont(new Font("Poppins", Font.BOLD, 16));
    saveButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
    saveButton.setEnabled(false);

    JButton cancelButton = new JButton("Discard");
    cancelButton.setBounds(261, 390, 185, 40);
    cancelButton.setBackground(Color.decode(ThemeConstant.THEME_BG_COLOR));
    cancelButton.setForeground(Color.decode(ThemeConstant.THEME_FG_COLOR));
    cancelButton.setFont(new Font("Poppins", Font.BOLD, 16));
    cancelButton.setCursor(new Cursor(Cursor.HAND_CURSOR));

    cancelButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        dispose();
        new AllTaskPage();
      }
    });

    taskHeading.addKeyListener(new KeyAdapter() {
      public void keyPressed(KeyEvent e) {
        Boolean isEnabledSaveButton = !taskHeading.getText().isEmpty() && !taskDescription.getText().isEmpty();
        saveButton.setEnabled(isEnabledSaveButton);
        saveButton.setBackground(
            Color.decode(isEnabledSaveButton ? ThemeConstant.THEME_BG_COLOR : ThemeConstant.THEME_DISABLED_COLOR));
      }
    });
    taskDescription.addKeyListener(new KeyAdapter() {
      public void keyPressed(KeyEvent e) {
        Boolean isEnabledSaveButton = !taskHeading.getText().isEmpty() && !taskDescription.getText().isEmpty();
        saveButton.setEnabled(isEnabledSaveButton);
        saveButton.setBackground(
            Color.decode(isEnabledSaveButton ? ThemeConstant.THEME_BG_COLOR : ThemeConstant.THEME_DISABLED_COLOR));
      }
    });

    saveButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        HandleDataFile.saveToCSVFile(taskHeading.getText(), taskDescription.getText());
        new AllTaskPage();
        dispose();
      }
    });

    add(heading);
    add(taskHeading);
    add(taskDescriptionWrapper, BorderLayout.CENTER);
    add(saveButton);
    add(cancelButton);

    setVisible(true);
  }

  public static void main(String[] args) {
    new CreateTaskPage();
  }
}
