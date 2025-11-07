import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;


public class GameDetailPage extends JDialog {
    
    public GameDetailPage(JFrame owner, Game game) {
        super(owner, "Detail Game: " + game.getTitle(), true);
        
        setSize(800, 600);
        setLocationRelativeTo(owner);
        setLayout(new BorderLayout());
        getContentPane().setBackground(Color.BLACK); 

        JPanel posterAndImdbPanel = new JPanel();
        posterAndImdbPanel.setLayout(new BoxLayout(posterAndImdbPanel, BoxLayout.Y_AXIS));
        posterAndImdbPanel.setBackground(Color.BLACK);
        posterAndImdbPanel.setBorder(new EmptyBorder(20, 20, 20, 20)); 
        posterAndImdbPanel.setPreferredSize(new Dimension(350, 600)); 

        JLabel posterLabel;
        try {
            ImageIcon originalIcon = new ImageIcon(game.getPosterPath());
            
            if (originalIcon.getImageLoadStatus() != MediaTracker.COMPLETE) {
                throw new Exception("Gagal memuat gambar");
            }
            
            Image originalImage = originalIcon.getImage();
            Image scaledImg = originalImage.getScaledInstance(300, 450, Image.SCALE_SMOOTH);
            posterLabel = new JLabel(new ImageIcon(scaledImg));
            
        } catch (Exception e) { 
            posterLabel = new JLabel("[ POSTER TIDAK DITEMUKAN ]");
            posterLabel.setForeground(Color.WHITE); 
        }
        posterLabel.setAlignmentX(Component.CENTER_ALIGNMENT); 
        posterAndImdbPanel.add(posterLabel);
        
        posterAndImdbPanel.add(Box.createRigidArea(new Dimension(0, 15)));

        if (game.getImdbUrl() != null && !game.getImdbUrl().isEmpty()) {
            JButton imdbButton = new JButton("View on IMDB");
            styleButton(imdbButton, new Color(50, 150, 255));
            imdbButton.setAlignmentX(Component.CENTER_ALIGNMENT);
            imdbButton.setMaximumSize(new Dimension(200, 40));
            imdbButton.setPreferredSize(new Dimension(200, 40));
            imdbButton.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    try {
                        Desktop.getDesktop().browse(new URI(game.getImdbUrl()));
                    } catch (IOException | URISyntaxException ex) {
                        JOptionPane.showMessageDialog(GameDetailPage.this, 
                            "Gagal membuka link: " + ex.getMessage(), 
                            "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            });
            posterAndImdbPanel.add(imdbButton);
        }

        add(posterAndImdbPanel, BorderLayout.WEST); 

        JPanel detailsPanel = new JPanel();
        detailsPanel.setLayout(new BoxLayout(detailsPanel, BoxLayout.Y_AXIS));
        detailsPanel.setBorder(new EmptyBorder(20, 20, 20, 20));
        detailsPanel.setOpaque(false); 

        detailsPanel.add(createDetailLabel("", game.getTitle(), 24, true));
        detailsPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        detailsPanel.add(createDetailLabel("Genre:", game.getGenre(), 16, false));
        detailsPanel.add(createDetailLabel("Year:", String.valueOf(game.getYear()), 16, false)); 
        detailsPanel.add(createDetailLabel("Rating:", String.valueOf(game.getRating()) + "/10", 16, false));
        detailsPanel.add(createDetailLabel("Director:", game.getDirector(), 16, false)); 
        detailsPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        
        JSeparator separator = new JSeparator();
        separator.setForeground(Color.DARK_GRAY);
        separator.setBackground(Color.DARK_GRAY);
        detailsPanel.add(separator);

        detailsPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        
        JLabel descTitle = new JLabel("Description:");
        descTitle.setFont(new Font("Roboto", Font.BOLD, 16));
        descTitle.setForeground(Color.WHITE);
        descTitle.setAlignmentX(Component.LEFT_ALIGNMENT); 
        detailsPanel.add(descTitle);

        JTextArea descriptionArea = new JTextArea(game.getDescription());
        descriptionArea.setWrapStyleWord(true);
        descriptionArea.setLineWrap(true);
        descriptionArea.setEditable(false);
        descriptionArea.setFont(new Font("Roboto", Font.PLAIN, 14));
        descriptionArea.setBackground(Color.BLACK);
        descriptionArea.setForeground(Color.LIGHT_GRAY);
        
        JScrollPane descScrollPane = new JScrollPane(descriptionArea);
        descScrollPane.setBorder(null);
        descScrollPane.setOpaque(false);
        descScrollPane.getViewport().setOpaque(false);
        descScrollPane.setAlignmentX(Component.LEFT_ALIGNMENT);
        detailsPanel.add(descScrollPane);
        
        detailsPanel.add(descScrollPane);
        
        add(detailsPanel, BorderLayout.CENTER);
    }

    private JLabel createDetailLabel(String title, String value, int fontSize, boolean isTitle) {
        JLabel label = new JLabel("<html><b>" + title + "</b> " + value + "</html>");
        label.setFont(new Font("Roboto", isTitle ? Font.BOLD : Font.PLAIN, fontSize));
        label.setAlignmentX(Component.LEFT_ALIGNMENT);
        
        if (isTitle) {
            label.setForeground(Color.WHITE); 
        } else {
            label.setForeground(Color.LIGHT_GRAY);
        }
        
        return label;
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

        Color brighter = color.brighter();
        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                button.setBackground(brighter);
            }
            @Override
            public void mouseExited(MouseEvent e) {
                button.setBackground(color);
            }
        });
    }
}