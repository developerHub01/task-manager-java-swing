import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import java.awt.Color;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HomePage extends JFrame {
  Container containerBox;

  HomePage() {
    this.getContentPane().setLayout(null);
    setTitle("Task Manager");
    setDefaultCloseOperation(EXIT_ON_CLOSE);
    setSize(500, 500);
    setResizable(false);
    setLocationRelativeTo(null);

    JLabel heading = new JLabel("Task Manager", JLabel.CENTER);
    heading.setFont(new Font("Poppins", Font.BOLD, 27));
    heading.setForeground(Color.decode(ThemeConstant.THEME_BG_COLOR));
    heading.setBounds(157, 160, 190, 33);

    JButton enterButton = new JButton("Enter");
    enterButton.setBounds(179, 210, 143, 35);
    enterButton.setBackground(Color.decode(ThemeConstant.THEME_BG_COLOR));
    enterButton.setForeground(Color.decode(ThemeConstant.THEME_FG_COLOR));
    enterButton.setFont(new Font("Poppins", Font.PLAIN, 16));
    enterButton.setCursor(new Cursor(Cursor.HAND_CURSOR));

    add(heading);
    add(enterButton);

    enterButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        new AllTaskPage();
        dispose();
      }
    });
    setVisible(true);
  }

  public static void main(String[] args) {
    new HomePage();
  }
}
