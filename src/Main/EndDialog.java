package Main;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;

import Main.Game;

public class EndDialog extends JFrame {
    private JLabel scoreLabel;
    private JButton returnButton;

    public EndDialog(int score) {
        setTitle("Гра завершена");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        Font font = new Font("Verdana", Font.ITALIC, 17);
        try {
            BufferedImage icon = ImageIO.read(getClass().getResource("/Resource/endIcon.png")); // Замініть "/path/to/your/icon.png" на шлях до вашої іконки
            setIconImage(icon);
        } catch (IOException e) {
            e.printStackTrace();
        }

        JPanel messagePanel = new JPanel();
        messagePanel.setLayout(new FlowLayout());
        JLabel messageLabel = new JLabel("Гра завершена! Вільних ходів не має.");
        messageLabel.setFont(font);
        scoreLabel = new JLabel("Ваш рахунок: " + score);
        scoreLabel.setFont(font);
        messagePanel.add(messageLabel);
        messagePanel.add(scoreLabel);
        add(messagePanel, BorderLayout.CENTER);

        returnButton = new JButton("Повернутися у головне меню");
        returnButton.setFont(new Font("Verdana", Font.ITALIC, 17));
        returnButton.setBorder(new LineBorder(Color.white, 2));
        returnButton.setBackground(new Color(255,229,153));
        returnButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                returnToMainMenu();
            }
        });
        add(returnButton, BorderLayout.SOUTH);
        setLocationRelativeTo(null);
    }

    public void returnToMainMenu() {
        Menu menu = new Menu();
        menu.setVisible(true);
        dispose();// Закриття поточного вікна
    }
}
