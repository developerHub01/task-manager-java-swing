import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

public class CreateTaskPage extends JFrame {
  Container containerBox;
  final String THEME_BG_COLOR = "#212121";
  final String THEME_DISABLED_COLOR = "#5F5F5F";
  final String THEME_FG_COLOR = "#FFFFFF";
  String taskHeadingValue = "";
  String taskDescriptionValue = "";

  CreateTaskPage() {
    this.getContentPane().setLayout(null);
    setTitle("Create Task");
    setDefaultCloseOperation(EXIT_ON_CLOSE);
    setSize(500, 500);
    setResizable(false);
    setLocationRelativeTo(null);

    JLabel heading = new JLabel("Task Manager", JLabel.CENTER);
    heading.setFont(new Font("Poppins", Font.BOLD, 27));
    heading.setForeground(Color.decode(THEME_BG_COLOR));
    heading.setBounds(160, 25, 190, 33);

    JTextField taskHeading = new JTextField();
    taskHeading.setBounds(54, 70, 392, 40);
    taskHeading.setBorder(new LineBorder(Color.decode(THEME_BG_COLOR), 3));
    taskHeading.setFont(new Font("Poppins", Font.PLAIN, 15));

    JTextArea taskDescription = new JTextArea();
    taskDescription.setFont(new Font("Poppins", Font.PLAIN, 15));
    taskDescription.setLineWrap(true);
    taskDescription.setWrapStyleWord(true);

    JScrollPane taskDescriptionWrapper = new JScrollPane(taskDescription);
    taskDescriptionWrapper.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
    taskDescriptionWrapper.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

    taskDescriptionWrapper.setBounds(54, 125, 392, 250);
    taskDescriptionWrapper.setBorder(new LineBorder(Color.decode(THEME_BG_COLOR), 3));

    JButton saveButton = new JButton("Save");
    saveButton.setBounds(54, 390, 185, 40);
    saveButton.setBackground(Color.decode(THEME_DISABLED_COLOR));
    saveButton.setForeground(Color.decode(THEME_FG_COLOR));
    saveButton.setFont(new Font("Poppins", Font.BOLD, 16));
    saveButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
    saveButton.setEnabled(false);

    JButton cancelButton = new JButton("Discard");
    cancelButton.setBounds(261, 390, 185, 40);
    cancelButton.setBackground(Color.decode(THEME_BG_COLOR));
    cancelButton.setForeground(Color.decode(THEME_FG_COLOR));
    cancelButton.setFont(new Font("Poppins", Font.BOLD, 16));
    cancelButton.setCursor(new Cursor(Cursor.HAND_CURSOR));

    cancelButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        dispose();
      }
    });

    taskHeading.addKeyListener(new KeyAdapter() {
      public void keyPressed(KeyEvent e) {
        Boolean isEnabledSaveButton = !taskHeading.getText().isEmpty() && !taskDescription.getText().isEmpty();
        saveButton.setEnabled(isEnabledSaveButton);
        saveButton.setBackground(Color.decode(isEnabledSaveButton ? THEME_BG_COLOR : THEME_DISABLED_COLOR));
      }
    });
    taskDescription.addKeyListener(new KeyAdapter() {
      public void keyPressed(KeyEvent e) {
        Boolean isEnabledSaveButton = !taskHeading.getText().isEmpty() && !taskDescription.getText().isEmpty();
        saveButton.setEnabled(isEnabledSaveButton);
        saveButton.setBackground(Color.decode(isEnabledSaveButton ? THEME_BG_COLOR : THEME_DISABLED_COLOR));
      }
    });

    saveButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        HandleDataFile.saveToCSVFile(taskHeading.getText(), taskDescription.getText());
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
