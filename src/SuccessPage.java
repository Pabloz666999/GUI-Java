import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class SuccessPage extends JPanel {
    private JLabel codeLabel;

    public SuccessPage(Main mainApp) {
        setLayout(new BorderLayout());
        setBackground(Main.DARK_BACKGROUND);

        add(createHeaderPanel(), BorderLayout.NORTH);

        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
        contentPanel.setOpaque(false);
        contentPanel.setBorder(new EmptyBorder(100, 100, 100, 100));

        JLabel titleLabel = new JLabel("Kode Anda", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Roboto", Font.PLAIN, 24));
        titleLabel.setForeground(Color.LIGHT_GRAY);
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        contentPanel.add(titleLabel);
        contentPanel.add(Box.createRigidArea(new Dimension(0, 30)));

        codeLabel = new JLabel("", SwingConstants.CENTER);
        codeLabel.setFont(new Font("Roboto", Font.BOLD, 72));
        codeLabel.setForeground(Color.WHITE);
        codeLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        contentPanel.add(codeLabel);
        contentPanel.add(Box.createRigidArea(new Dimension(0, 80)));
        
        JButton finishButton = new RoundedButton("Selesai", 15);
        finishButton.setFont(new Font("Roboto", Font.BOLD, 20));
        finishButton.setPreferredSize(new Dimension(250, 55));
        finishButton.setMaximumSize(new Dimension(250, 55));
        finishButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        finishButton.setBackground(Main.PRIMARY_COLOR);
        finishButton.setForeground(Color.WHITE);
        finishButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        finishButton.setFocusPainted(false);
        finishButton.setOpaque(false);
        finishButton.setContentAreaFilled(false);
        finishButton.setBorderPainted(false);

        finishButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                mainApp.showPage("LANDING");
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                finishButton.setBackground(Main.PRIMARY_COLOR.brighter());
            }

            @Override
            public void mouseExited(MouseEvent e) {
                finishButton.setBackground(Main.PRIMARY_COLOR);
            }
        });

        contentPanel.add(finishButton);

        add(contentPanel, BorderLayout.CENTER);
    }

    private JLayeredPane createHeaderPanel() {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int screenWidth = screenSize.width;
        int headerHeight = 150;

        ImageIcon headerIcon = new ImageIcon("assets/images/img-header.png");
        Image scaledHeader = headerIcon.getImage().getScaledInstance(screenWidth, headerHeight, Image.SCALE_SMOOTH);
        JLabel headerLabel = new JLabel(new ImageIcon(scaledHeader), SwingConstants.CENTER);
        headerLabel.setBounds(0, 0, screenWidth, headerHeight);

        JLabel titleLabel = new JLabel("RENTAL PS RIJAL", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Roboto", Font.BOLD, 40));
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setBounds(0, 0, screenWidth, headerHeight);

        JLayeredPane headerPane = new JLayeredPane();
        headerPane.setPreferredSize(new Dimension(screenWidth, headerHeight));
        headerPane.add(headerLabel, Integer.valueOf(0));
        headerPane.add(titleLabel, Integer.valueOf(1));

        return headerPane;
    }

    public void setBookingCode(String code) {
        codeLabel.setText(code);
    }

}