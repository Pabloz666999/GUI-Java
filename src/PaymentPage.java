import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class PaymentPage extends JPanel {
    private Main mainApp;
    private Station selectedStation;
    private JLabel bookingInfoLabel;

    public PaymentPage(Main mainApp) {
        this.mainApp = mainApp;
        setLayout(new BorderLayout());
        setBackground(Main.DARK_BACKGROUND);

        add(createHeaderPanel(), BorderLayout.NORTH);

        JPanel contentPanel = new JPanel(new BorderLayout());
        contentPanel.setOpaque(false);
        contentPanel.setBorder(new EmptyBorder(30, 100, 50, 100));

        JPanel topSection = new JPanel(new BorderLayout());
        topSection.setOpaque(false);

        JButton backButton = createBackButton();
        JPanel backPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        backPanel.setOpaque(false);
        backPanel.add(backButton);
        topSection.add(backPanel, BorderLayout.WEST);

        contentPanel.add(topSection, BorderLayout.NORTH);

        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
        centerPanel.setOpaque(false);

        JLabel title = new JLabel("Proses Pembayaran Anda", SwingConstants.CENTER);
        title.setFont(new Font("Roboto", Font.BOLD, 36));
        title.setForeground(Color.WHITE);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        centerPanel.add(title);
        centerPanel.add(Box.createRigidArea(new Dimension(0, 15)));

        bookingInfoLabel = new JLabel("", SwingConstants.CENTER);
        bookingInfoLabel.setFont(new Font("Roboto", Font.PLAIN, 16));
        bookingInfoLabel.setForeground(Color.LIGHT_GRAY);
        bookingInfoLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        centerPanel.add(bookingInfoLabel);
        centerPanel.add(Box.createRigidArea(new Dimension(0, 30)));

        JPanel qrisPanel = new JPanel(new GridBagLayout());
        qrisPanel.setOpaque(false);

        JLabel qrisLabel;
        try {
            ImageIcon originalIcon = new ImageIcon("assets/images/img-qr.png");

            if (originalIcon.getImageLoadStatus() != MediaTracker.COMPLETE) {
                throw new Exception("Gagal memuat aset gambar QR");
            }

            Image originalImage = originalIcon.getImage();
            Image scaledImg = originalImage.getScaledInstance(300, 300, Image.SCALE_SMOOTH);
            qrisLabel = new JLabel(new ImageIcon(scaledImg));

        } catch (Exception e) {
            qrisLabel = new JLabel("[ QR Code Error ]");
            qrisLabel.setPreferredSize(new Dimension(300, 300));
            qrisLabel.setForeground(Color.WHITE);
            qrisLabel.setHorizontalAlignment(SwingConstants.CENTER);
            qrisLabel.setBorder(BorderFactory.createLineBorder(Color.RED, 2));
        }

        qrisPanel.add(qrisLabel);
        qrisPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 350));
        centerPanel.add(qrisPanel);

        contentPanel.add(centerPanel, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.setOpaque(false);
        buttonPanel.setBorder(new EmptyBorder(30, 0, 20, 0));

        JButton finishButton = new RoundedButton("Selesai", 20);
        finishButton.setFont(new Font("Roboto", Font.BOLD, 20));
        finishButton.setPreferredSize(new Dimension(250, 55));
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
                if (selectedStation != null) {
                    mainApp.completePayment(selectedStation);
                }
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

        buttonPanel.add(finishButton);
        contentPanel.add(buttonPanel, BorderLayout.SOUTH);

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

    private JButton createBackButton() {
        JButton backButton = new JButton();

        try {
            ImageIcon originalIcon = new ImageIcon("assets/icons/ic-backArrow.png");

            if (originalIcon.getImageLoadStatus() == MediaTracker.COMPLETE) {
                Image originalImage = originalIcon.getImage();
                Image scaledImg = originalImage.getScaledInstance(40, 40, Image.SCALE_SMOOTH);

                backButton.setIcon(new ImageIcon(scaledImg));
                backButton.setPreferredSize(new Dimension(50, 50));

                backButton.setBorderPainted(false);
                backButton.setContentAreaFilled(false);
            } else {
                throw new Exception("Aset ic-backArrow.png tidak ditemukan");
            }
        } catch (Exception e) {
            backButton.setText("← Kembali");
            styleButton(backButton, Main.PRIMARY_COLOR);
        }

        backButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        backButton.setFocusPainted(false);

        backButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                mainApp.showPage("STATION");
            }
        });

        return backButton;
    }

    public void setSelectedStation(Station station) {
        this.selectedStation = station;
        updateBookingInfo();
    }

    private void updateBookingInfo() {
        if (selectedStation != null && mainApp != null && mainApp.getSelectedPaket() != null) {
            Paket paket = mainApp.getSelectedPaket();
            String info = String.format("Station %d • %s • %d Jam",
                    selectedStation.getStationNumber(),
                    paket.getName(),
                    paket.getDurationInHours());
            bookingInfoLabel.setText(info);
        } else if (bookingInfoLabel != null) {
            bookingInfoLabel.setText("");
        }
    }

    private void styleButton(JButton button, Color color) {
        button.setBackground(color);
        button.setForeground(Color.WHITE);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));

        button.setOpaque(true);
        button.setBorderPainted(false);

        button.setBorder(new EmptyBorder(12, 25, 12, 25));
        button.setFocusPainted(false);

        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                button.setBackground(color.brighter());
            }

            @Override
            public void mouseExited(MouseEvent e) {
                button.setBackground(color);
            }
        });
    }
}