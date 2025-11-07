import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class LandingPage extends JPanel {
    private Main mainApp;

    public LandingPage(Main mainApp) {
        this.mainApp = mainApp;
        setLayout(new BorderLayout());
        setBackground(Main.DARK_BACKGROUND);

        add(createHeaderPanel(), BorderLayout.NORTH);

        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
        contentPanel.setBackground(Main.DARK_BACKGROUND);
        contentPanel.setBorder(new EmptyBorder(30, 80, 30, 80));

        contentPanel.add(createHeroSection());
        contentPanel.add(Box.createRigidArea(new Dimension(0, 50)));

        JLabel paketTitle = new JLabel("Pilihan Paket");
        paketTitle.setFont(new Font("Roboto", Font.BOLD, 32));
        paketTitle.setForeground(Color.WHITE);
        paketTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
        contentPanel.add(paketTitle);
        contentPanel.add(Box.createRigidArea(new Dimension(0, 30)));

        JPanel paketWrapper = new JPanel(new GridBagLayout());
        paketWrapper.setOpaque(false);
        paketWrapper.setMaximumSize(new Dimension(Integer.MAX_VALUE, 450));

        JPanel paketSection = new JPanel(new GridLayout(1, 4, 20, 20));
        paketSection.setOpaque(false);
        paketSection.setPreferredSize(new Dimension(1300, 350));
        paketSection.setAlignmentX(Component.CENTER_ALIGNMENT);

        for (Paket paket : mainApp.getPaketList()) {
            paketSection.add(createPaketCard(paket));
        }

        paketWrapper.add(paketSection);
        contentPanel.add(paketWrapper);
        contentPanel.add(Box.createRigidArea(new Dimension(0, 50)));

        JLabel gameTitle = new JLabel("Daftar Game");
        gameTitle.setFont(new Font("Roboto", Font.BOLD, 32));
        gameTitle.setForeground(Color.WHITE);
        gameTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
        contentPanel.add(gameTitle);
        contentPanel.add(Box.createRigidArea(new Dimension(0, 30)));

        JPanel gameSection = new JPanel(new FlowLayout(FlowLayout.LEFT, 15, 20));
        gameSection.setOpaque(false);
        gameSection.setBackground(Main.DARK_BACKGROUND);
        for (Game game : mainApp.getGameList()) {
            gameSection.add(createGameCard(game));
        }

        JScrollPane gameScrollPane = new JScrollPane(gameSection);
        gameScrollPane.setOpaque(false);
        gameScrollPane.getViewport().setOpaque(false);
        gameScrollPane.setBackground(Main.DARK_BACKGROUND);
        gameScrollPane.setBorder(null);
        gameScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        gameScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);

        int gameSectionHeight = 320;
        gameScrollPane.setPreferredSize(new Dimension(10, gameSectionHeight));
        gameScrollPane.setMaximumSize(new Dimension(Integer.MAX_VALUE, gameSectionHeight));
        gameScrollPane.getHorizontalScrollBar().setUnitIncrement(16);

        contentPanel.add(gameScrollPane);
        contentPanel.add(Box.createRigidArea(new Dimension(0, 50)));

        JScrollPane scrollPane = new JScrollPane(contentPanel);
        scrollPane.setBorder(null);
        scrollPane.setBackground(Main.DARK_BACKGROUND);
        scrollPane.getViewport().setBackground(Main.DARK_BACKGROUND);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);

        add(scrollPane, BorderLayout.CENTER);
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

    private JPanel createHeroSection() {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int screenWidth = screenSize.width;

        JPanel heroPanel = new JPanel(new GridLayout(1, 2));
        heroPanel.setBackground(Main.DARK_BACKGROUND);
        heroPanel.setMaximumSize(new Dimension(screenWidth, 400));

        JPanel textPanel = new JPanel();
        textPanel.setBackground(Main.DARK_BACKGROUND);
        textPanel.setLayout(new BoxLayout(textPanel, BoxLayout.Y_AXIS));
        textPanel.setBorder(BorderFactory.createEmptyBorder(20, 50, 50, 50));

        JLabel bigText = new JLabel("Main Seru, Harga Murah, Tanpa Ribet!");
        bigText.setFont(new Font(" Roboto", Font.BOLD, 24));
        bigText.setForeground(Color.WHITE);
        bigText.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel smallText = new JLabel("Ayo main di rental ps rijal, semua game ada!");
        smallText.setFont(new Font("Roboto", Font.PLAIN, 16));
        smallText.setForeground(Color.LIGHT_GRAY);
        smallText.setAlignmentX(Component.CENTER_ALIGNMENT);
        smallText.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 117));

        textPanel.add(Box.createVerticalGlue());
        textPanel.add(bigText);
        textPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        textPanel.add(smallText);
        textPanel.add(Box.createVerticalGlue());

        JPanel imagePanelWrapper = new JPanel(new GridBagLayout());
        imagePanelWrapper.setOpaque(false);

        ImageIcon heroIcon = new ImageIcon("assets/images/img-hero-section.png");
        Image scaledHero = heroIcon.getImage().getScaledInstance(350, 350, Image.SCALE_SMOOTH);
        JLabel heroImageLabel = new JLabel(new ImageIcon(scaledHero));
        heroImageLabel.setHorizontalAlignment(SwingConstants.CENTER);

        imagePanelWrapper.add(heroImageLabel);

        heroPanel.add(textPanel);
        heroPanel.add(imagePanelWrapper);

        return heroPanel;
    }

    private JPanel createPaketCard(Paket paket) {
        RoundedPanel card = new RoundedPanel(20);

        card.setBorder(new EmptyBorder(20, 20, 20, 20));

        JLabel title = new JLabel(paket.getName());
        title.setFont(new Font("Roboto", Font.BOLD, 18));
        title.setForeground(Color.BLACK);
        title.setAlignmentX(Component.LEFT_ALIGNMENT);

        JTextArea desc = new JTextArea(paket.getDescription());
        desc.setFont(new Font("Roboto", Font.PLAIN, 13));
        desc.setForeground(new Color(100, 100, 100));
        desc.setOpaque(false);
        desc.setEditable(false);
        desc.setWrapStyleWord(true);
        desc.setLineWrap(true);
        desc.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel fasilTitle = new JLabel("📍 Fasilitas:");
        fasilTitle.setFont(new Font("Roboto", Font.BOLD, 14));
        fasilTitle.setForeground(Color.BLACK);
        fasilTitle.setAlignmentX(Component.LEFT_ALIGNMENT);

        JPanel facilitiesPanel = new JPanel();
        facilitiesPanel.setLayout(new BoxLayout(facilitiesPanel, BoxLayout.Y_AXIS));
        facilitiesPanel.setOpaque(false);
        facilitiesPanel.setAlignmentX(Component.LEFT_ALIGNMENT);

        for (String facility : paket.getFacilities()) {
            JLabel facLabel = new JLabel(" • " + facility);
            facLabel.setFont(new Font("Roboto", Font.PLAIN, 12));
            facLabel.setForeground(new Color(100, 100, 100));
            facilitiesPanel.add(facLabel);
            facilitiesPanel.add(Box.createRigidArea(new Dimension(0, 3)));
        }

        JTextArea priceNote = new JTextArea(paket.getPriceNote());
        priceNote.setFont(new Font("Roboto", Font.ITALIC, 10));
        priceNote.setForeground(new Color(120, 120, 120));
        priceNote.setOpaque(false);
        priceNote.setEditable(false);
        priceNote.setWrapStyleWord(true);
        priceNote.setLineWrap(true);
        priceNote.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel price = new JLabel("Rp " + String.format("%,d", (int) paket.getPrice()));
        price.setFont(new Font("Roboto", Font.BOLD, 15));
        price.setForeground(Color.BLACK);
        price.setAlignmentX(Component.LEFT_ALIGNMENT);

        JButton bookingButton = new RoundedButton("Booking Paket", 20);

        styleButton(bookingButton, Color.BLUE);

        bookingButton.setAlignmentX(Component.LEFT_ALIGNMENT);
        bookingButton.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));

        bookingButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                mainApp.selectPaketAndShowStations(paket);
            }
        });

        card.add(title);
        card.add(Box.createRigidArea(new Dimension(0, 12)));
        card.add(desc);
        card.add(Box.createRigidArea(new Dimension(0, 15)));
        card.add(fasilTitle);
        card.add(Box.createRigidArea(new Dimension(0, 8)));
        card.add(facilitiesPanel);
        card.add(Box.createVerticalGlue());
        card.add(Box.createRigidArea(new Dimension(0, 15)));
        card.add(priceNote);
        card.add(Box.createRigidArea(new Dimension(0, 5)));
        card.add(price);
        card.add(Box.createRigidArea(new Dimension(0, 12)));
        card.add(bookingButton);

        return card;
    }

    private JPanel createGameCard(Game game) {
        JPanel card = new JPanel(new BorderLayout(0, 8));
        card.setOpaque(false);
        card.setCursor(new Cursor(Cursor.HAND_CURSOR));

        card.setPreferredSize(new Dimension(160, 270));

        JLabel posterLabel = new JLabel();
        posterLabel.setHorizontalAlignment(SwingConstants.CENTER);
        posterLabel.setPreferredSize(new Dimension(160, 220));
        posterLabel.setBackground(new Color(40, 40, 50));
        posterLabel.setOpaque(true);

        try {
            ImageIcon originalIcon = new ImageIcon(game.getPosterPath());
            if (originalIcon.getImageLoadStatus() == MediaTracker.COMPLETE) {
                Image originalImage = originalIcon.getImage();
                int targetWidth = 160;
                int targetHeight = 220;
                int imgWidth = originalImage.getWidth(null);
                int imgHeight = originalImage.getHeight(null);
                double targetRatio = (double) targetWidth / targetHeight;
                double imageRatio = (double) imgWidth / imgHeight;
                int newWidth;
                int newHeight;
                if (imageRatio > targetRatio) {
                    newHeight = targetHeight;
                    newWidth = (int) (newHeight * imageRatio);
                } else {
                    newWidth = targetWidth;
                    newHeight = (int) (newWidth / imageRatio);
                }
                Image scaledImg = originalImage.getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH);
                posterLabel.setIcon(new ImageIcon(scaledImg));
            } else {
                throw new Exception("Gagal memuat: " + game.getPosterPath());
            }
        } catch (Exception e) {
            posterLabel.setText("No Image");
            posterLabel.setForeground(Color.GRAY);
        }

        card.add(posterLabel, BorderLayout.CENTER);

        JPanel textPanel = new JPanel();
        textPanel.setLayout(new BoxLayout(textPanel, BoxLayout.Y_AXIS));
        textPanel.setOpaque(false);

        JLabel titleLabel = new JLabel(game.getTitle());
        titleLabel.setFont(new Font("Roboto", Font.BOLD, 13));
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel genreLabel = new JLabel(game.getGenre());
        genreLabel.setFont(new Font("Roboto", Font.PLAIN, 11));
        genreLabel.setForeground(new Color(150, 150, 150));
        genreLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        textPanel.add(titleLabel);
        textPanel.add(genreLabel);
        card.add(textPanel, BorderLayout.SOUTH);

        card.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                mainApp.showGameDetails(game);
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                posterLabel.setBorder(BorderFactory.createLineBorder(Main.PRIMARY_COLOR, 3));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                posterLabel.setBorder(null);
            }
        });

        return card;
    }

    private void styleButton(JButton button, Color color) {
        button.setFont(new Font("Roboto", Font.BOLD, 14));
        button.setBackground(color);
        button.setForeground(Color.WHITE);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));

        button.setOpaque(true);
        button.setBorderPainted(false);

        button.setContentAreaFilled(false);

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