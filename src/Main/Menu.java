package Main;
import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.*;

public class Menu extends JFrame implements ActionListener {
    JButton playButton;
    JButton exitButton;

    public Menu() {
        setTitle("Меню");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ImageIcon icon = new ImageIcon(getClass().getResource("/Resource/iconMenu.png"));
        setIconImage(icon.getImage());

        JPanel panel = new JPanel((null));

        playButton = new JButton("Розпочати гру");
        playButton.setBounds(95, 80, 200, 70);
        playButton.setFocusPainted(false);
        playButton.setFont(new Font("Verdana", Font.ITALIC, 21));
        playButton.setBorder(new LineBorder(Color.white, 2));
        playButton.setBackground(new Color(255,229,153));
        playButton.addActionListener(this);
        panel.add(playButton);

        exitButton = new JButton("Вихід");
        exitButton.setBounds(95, 190, 200, 70);
        exitButton.setFont(new Font("Verdana", Font.ITALIC, 21));
        exitButton.setBorder(new LineBorder(Color.white, 2));
        exitButton.setBackground(new Color(255,229,153));
        exitButton.addActionListener(this);
        panel.add(exitButton);

        add(panel);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == playButton) {
            dispose();
            Main m = new Main();
            m.frame.setResizable(false);
            m.frame.setTitle("2048");
            m.frame.add(m);
            m.frame.pack();
            m.frame.setVisible(true);
            m.frame.setLocationRelativeTo(null);
            m.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            m.frame.setAlwaysOnTop(true);
            m.start();
        } else if (e.getSource() == exitButton) {
                System.exit(0);
            }
        }

}
