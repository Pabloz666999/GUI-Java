import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class StationPage extends JPanel {
    private Main mainApp;
    private JPanel stationGridPanel;

    public StationPage(Main mainApp) {
        this.mainApp = mainApp;
        setLayout(new BorderLayout());
        setBackground(Main.DARK_BACKGROUND);

        add(createHeaderPanel(), BorderLayout.NORTH);

        JPanel centerContentPanel = new JPanel(new BorderLayout());
        centerContentPanel.setOpaque(false);
        centerContentPanel.setBorder(new EmptyBorder(30, 50, 30, 50));

        JPanel topSection = new JPanel(new BorderLayout());
        topSection.setOpaque(false);

        JButton backButton = createBackButton();
        JPanel backPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        backPanel.setOpaque(false);
        backPanel.add(backButton);
        topSection.add(backPanel, BorderLayout.WEST);

        JLabel titleLabel = new JLabel("Daftar Station", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Roboto", Font.BOLD, 36));
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setBorder(new EmptyBorder(10, 0, 30, 0));
        topSection.add(titleLabel, BorderLayout.CENTER);

        centerContentPanel.add(topSection, BorderLayout.NORTH);

        stationGridPanel = new JPanel(new GridLayout(3, 4, 25, 25));
        stationGridPanel.setOpaque(false);
        stationGridPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 50, 0));
        centerContentPanel.add(stationGridPanel, BorderLayout.CENTER);

        add(centerContentPanel, BorderLayout.CENTER);

        updateStationView();
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
                mainApp.showPage("LANDING");
            }
        });

        return backButton;
    }

    public void updateStationView() {
        stationGridPanel.removeAll();
        for (Station station : mainApp.getStationList()) {
            stationGridPanel.add(createStationCell(station));
        }
        stationGridPanel.revalidate();
        stationGridPanel.repaint();
    }

    private JPanel createStationCell(Station station) {
        JPanel cellPanel = new JPanel();
        cellPanel.setLayout(new BoxLayout(cellPanel, BoxLayout.Y_AXIS));
        cellPanel.setOpaque(false);
        cellPanel.setCursor(new Cursor(Cursor.HAND_CURSOR));

        JPanel numberBox = new JPanel(new BorderLayout());
        numberBox.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
        numberBox.setPreferredSize(new Dimension(250, 180));
        numberBox.setMaximumSize(new Dimension(Integer.MAX_VALUE, 200));
        numberBox.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel numberLabel = new JLabel(String.valueOf(station.getStationNumber()), SwingConstants.CENTER);
        numberLabel.setFont(new Font("Roboto", Font.BOLD, 80));
        numberBox.add(numberLabel, BorderLayout.CENTER);

        JLabel statusLabel = new JLabel();
        statusLabel.setFont(new Font("Roboto", Font.BOLD, 20));
        statusLabel.setForeground(Color.WHITE);
        statusLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel timeLabel = new JLabel();
        timeLabel.setFont(new Font("Roboto", Font.PLAIN, 18));
        timeLabel.setForeground(Color.WHITE);
        timeLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        if (station.isAvailable()) {
            numberBox.setBackground(Main.STATION_AVAILABLE);
            numberLabel.setForeground(Color.BLACK); 
            statusLabel.setText("Available");
            timeLabel.setText("00:00:00");
        } else {
            numberBox.setBackground(Main.STATION_UNAVAILABLE);
            numberLabel.setForeground(Color.WHITE); 
            statusLabel.setText("Unavailable");
            timeLabel.setText(station.getFormattedDuration());
        }

        cellPanel.add(numberBox);
        cellPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        cellPanel.add(statusLabel);
        cellPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        cellPanel.add(timeLabel);

        cellPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                System.out.println("Station " + station.getStationNumber() + " clicked!");
                System.out.println("Is available: " + station.isAvailable());
                
                if (station.isAvailable()) {
                    System.out.println("Navigating to payment page...");
                    mainApp.selectStationAndShowPayment(station);
                } else {
                    System.out.println("Station Occupied");
                    JOptionPane.showMessageDialog(mainApp.getFrame(),
                        "Station " + station.getStationNumber() + " masih occupied, tidak bisa dipilih",
                        "Station occupied",
                        JOptionPane.WARNING_MESSAGE);
                }
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                numberBox.setBorder(BorderFactory.createLineBorder(Main.PRIMARY_COLOR, 4));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                numberBox.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
            }
        });

        return cellPanel;
    }

    private void styleButton(JButton button, Color color) {
        button.setFont(new Font("Roboto", Font.BOLD, 14));
        button.setBackground(color);
        button.setForeground(Color.WHITE);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        button.setOpaque(true);
        button.setBorderPainted(false);
        
        button.setBorder(new EmptyBorder(10, 20, 10, 20));
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