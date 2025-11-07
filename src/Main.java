import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Main {
    private JFrame frame;
    private CardLayout cardLayout;
    private JPanel mainPanel;

    private LandingPage landingPage;
    private StationPage stationPage;
    private PaymentPage paymentPage;
    private SuccessPage successPage;

    private List<Game> gameList;
    private List<Paket> paketList;
    private List<Station> stationList;

    private Paket selectedPaket;

    public static final Color PRIMARY_COLOR = new Color(0x3F99E3);
    public static final Color DARK_BACKGROUND = new Color(0x0F0F1A);
    public static final Color DARK_PANEL = new Color(0x1E1E2C);
    public static final Color STATION_AVAILABLE = new Color(0, 200, 50);
    public static final Color STATION_UNAVAILABLE = new Color(220, 30, 30);

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Main().createAndShowGUI());
    }

    private void createAndShowGUI() {
        frame = new JFrame("Rental PS Rijal");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setUndecorated(true);

        frame.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                    exitApplication();
                }
            }
        });
        frame.setFocusable(true);

        initializeData();

        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);
        mainPanel.setBackground(DARK_BACKGROUND);

        landingPage = new LandingPage(this);
        stationPage = new StationPage(this);
        paymentPage = new PaymentPage(this);
        successPage = new SuccessPage(this);

        mainPanel.add(landingPage, "LANDING");
        mainPanel.add(stationPage, "STATION");
        mainPanel.add(paymentPage, "PAYMENT");
        mainPanel.add(successPage, "SUCCESS");

        frame.getContentPane().add(mainPanel);
        cardLayout.show(mainPanel, "LANDING");
        frame.setVisible(true);
    }

    private void initializeData() {
        paketList = new ArrayList<>();
        
        paketList.add(new Paket(
                "🥉Paket Bronze (1 Jam)", 
                12000, 
                1,
                "Cocok buat kamu yang cuma ingin main sebentar sekadar mengisi waktu luang.",
                Arrays.asList("1 Jam bermain", "Bebas pilih game", "Konsol PS5", "Headset & sofa nyaman"),
                "*Ideal untuk solo play atau duel singkat bareng teman."
        ));
        
        paketList.add(new Paket(
                "🥈Paket Silver (3 Jam)", 
                20000, 
                3,
                "Pilihan pas untuk sesi bermain lebih lama tanpa khawatir atau waktu cepat habis.",
                Arrays.asList("3 Jam bermain", "Ganti game kapan saja", "Bonus 1 snack ringan",
                        "Wi-Fi gratis & ruangan ber-AC"),
                "*Cocok buat sesi mabar atau turnamen kecil."
        ));
        
        paketList.add(new Paket(
                "🥇Paket Gold (5 Jam)", 
                60000, 
                5,
                "Buat kamu yang mau puas main game favorit tanpa batas waktu yang mepet!",
                Arrays.asList("5 Jam bermain", "Akses semua game premium", "1 minuman gratis",
                        "Prioritas room (bisa pilih ruangan)"),
                "*Waktu lebih lama, pengalaman lebih maksimal."
        ));
        
        paketList.add(new Paket(
                "🌙 Paket Malam", 
                80000, 
                8,
                "Spesial untuk gamer malam yang mau suasana tenang dan batas antre!",
                Arrays.asList("Bermain nonstop jam 00.00-06.00", "Snack & minuman gratis", "Bisa main ramai-ramai",
                        "Diskon tambahan untuk member"),
                "*Nikmati night gaming session yang seru bareng teman-teman."
        ));

        stationList = new ArrayList<>();
        for (int i = 1; i <= 12; i++) {
            stationList.add(new Station(i));
        }

        gameList = new ArrayList<>();

        gameList.add(new Game("Red Dead Redemption 2", 
                "Action-Adventure", 
                2018, 
                9.7, 
                "Rob Nelson",
                "An epic tale of life in America's unforgiving heartland. The game's vast and atmospheric world will also provide the foundation for a brand new online multiplayer experience.",
                "assets/games/games1.png", 
                "https://www.imdb.com/title/tt6161168/"));

        gameList.add(new Game("The Last of Us Part I", 
                "Survival-Horror", 
                2022, 
                9.2, 
                "Neil Druckmann",
                "Experience the emotional storytelling and unforgettable characters in Joel and Ellie's journey through a pandemic-ravaged America.",
                "assets/games/games2.png", 
                "https://www.imdb.com/title/tt20918700/"));

        gameList.add(new Game("God of War: Ragnarok", 
                "Action-Adventure", 
                2022, 
                9.5, 
                "Eric Williams",
                "Kratos and Atreus embark on a mythic journey for answers before Ragnarök arrives as they explore the Nine Realms of Norse mythology.",
                "assets/games/games3.png", 
                "https://www.imdb.com/title/tt13119450/"));

        gameList.add(new Game("Metal Gear Solid", 
                "Action, Stealth", 
                1998, 
                9.4, 
                "Hideo Kojima",
                "A crack government anti-terrorist squad takes over an obscure Alaskan nuclear disposal facility. Solid Snake is up for the task to infiltrate the facility, rescue the two hostages and thwart the terrorists' plans.",
                "assets/games/games4.png", 
                "https://www.imdb.com/title/tt0180825/"));

        gameList.add(new Game("Final Fantasy VII", 
                "Sci-Fi, Fantasy", 
                1997, 
                9.5, 
                "Yoshinori Kitase",
                "A former soldier from an evil company joins a mercenary clan to fight the company he once served and a former colleague who has become obsessed with destruction.",
                "assets/games/games5.png", 
                "https://www.imdb.com/title/tt0208155/"));

        gameList.add(new Game("Baldur's Gate 3", 
                "RPG, Adventure", 
                2023, 
                9.6, 
                "Swen Vincke",
                "Return to the Forgotten Realms in a tale of fellowship and betrayal, sacrifice and survival, and the lure of absolute power.",
                "assets/games/games6.jpg", 
                "https://www.imdb.com/title/tt13258344/"));

        gameList.add(new Game("Grand Theft Auto V", 
                "Action-Adventure", 
                2013, 
                9.5, 
                "Rockstar North",
                "Three very different criminals team up for a series of heists in the corrupt city of Los Santos.",
                "assets/games/games7.jpg", 
                "https://www.imdb.com/title/tt2103188/"));

        gameList.add(new Game("Elden Ring",
                "Action-RPG", 
                2022, 
                9.6, 
                "Hidetaka Miyazaki",
                "In a dark fantasy world created by Hidetaka Miyazaki and George R.R. Martin, the player takes on the role of a Tarnished who is called back to the Lands Between.",
                "assets/games/games8.jpg", 
                "https://www.imdb.com/title/tt10562854/"));

        gameList.add(new Game(
                "Middle Earth: Shadow Of Mordor",
                "Dark Fantasy",
                2014,
                8.2,
                "Matthew Allen",
                "The family of Talion, a ranger of Gondor responsible for guarding the Black Gate of Mordor, is killed by Saurons armies, but Talion is revived with wraith-like abilities and heads into Mordor to exact his revenge.",
                "assets/games/ShadowMordor.jpeg", 
                "https://www.imdb.com/title/tt3330488/"));

        gameList.add(new Game(
                "Middle Earth: Shadow Of War",
                "Dark Fantasy",
                2014,
                8.1,
                "JB Blanc",
                "The sequel to the critically acclaimed Middle-Earth: Shadow of Mordor (2014) features an original story with the return of Talion and Celebrimbor, who must go behind enemy lines to forge an army and turn all of Mordor against the Dark Lord Sauron.",
                "assets/games/ShadowofWar.jpeg", 
                "https://www.imdb.com/title/tt6579422/"));

        gameList.add(new Game(
                "Ori and the Blind Forest",
                "Adventure",
                2015,
                8.6,
                "Thomas Mahler",
                "Ori fell from the Spirit Tree in the forest of Nibel during a storm and was adopted by a bear-like creature named Naru, who raised Ori as her own.",
                "assets/games/ori.jpeg", 
                "https://www.imdb.com/title/tt4550698/"));

        gameList.add(new Game(
                "Ori and the Will of the Wisps",
                "Adventure",
                2015,
                9.0,
                "Thomas Mahler",
                "Ori begins a new journey that goes beyond the forest of Nibel where he uncovers lost secrets, and unearths his true destiny.",
                "assets/games/oriAndWisps.jpeg", 
                "https://www.imdb.com/title/tt8329350/"));

        gameList.add(new Game(
                "Dead Cells",
                "Action",
                2018,
                7.8,
                "Sebastian Benard",
                "Unable to die, a mysterious being attempts to escape a magical island prison, but every death sends him back to the beginning.",
                "assets/games/DeadofCelss.jpeg", 
                "https://www.imdb.com/title/tt9616440/"));

        gameList.add(new Game(
                "Enter the Gungeon",
                "Action",
                2016,
                7.3,
                "Dave Crooks",
                "Enter the Gungeon is a gunfight dungeon crawler following a band of misfits seeking to shoot, loot, dodge roll and table-flip their way to personal absolution by reaching the legendary Gungeon's ultimate treasure: the gun that can kill the past.",
                "assets/games/Gungeon.jpeg", 
                "https://www.imdb.com/title/tt5886006/"));

        gameList.add(new Game(
                "Celeste",
                "Action",
                2018,
                8.6,
                "Maddy Thorson",
                "While scaling a supernatural mountain in an attempt to salvage her self-respect, a depressed young woman is confronted by a doppelganger manifesting her doubts and fears.",
                "assets/games/Celeste.jpeg", 
                "https://www.imdb.com/title/tt7903902/"));

        gameList.add(new Game(
                "Journey",
                "Adventure",
                2012,
                8.4,
                "Jenova Chen",
                "A robed figure in a desolate world undertakes a journey towards a distant, glowing mountain.",
                "assets/games/Journey.jpeg", 
                "https://www.imdb.com/title/tt1821474"));

        gameList.add(new Game(
                "Slay the Spire",
                "Action",
                2019,
                7.8,
                "Anthony Giovannetti",
                "It's a combination of roguelike-like gameplay with a deck-building card game. It requires players to develop a strategy for their deck on the fly based on the cards they can obtain from loot.",
                "assets/games/SlayOfTheSpire.jpeg", 
                "https://www.imdb.com/title/tt8008930/"));

        gameList.add(new Game(
                "Hades",
                "Dark Fantasy",
                2018,
                8.9,
                "Greg Kasavin",
                "Hades is a rogue-like dungeon crawler from the creators of Bastion and Transistor, in which you defy the god of death as you hack and slash your way out of the Underworld of Greek myth.",
                "assets/games/Hades.jpeg",
                "https://www.imdb.com/title/tt9459718/"));
    }

    public void showPage(String pageName) {
        cardLayout.show(mainPanel, pageName);
    }

    public void selectPaketAndShowStations(Paket paket) {
        this.selectedPaket = paket;
        stationPage.updateStationView();
        showPage("STATION");
    }

    public void selectStationAndShowPayment(Station station) {
        if (station.isAvailable()) {
            paymentPage.setSelectedStation(station);
            showPage("PAYMENT");
        } else {
            JOptionPane.showMessageDialog(frame,
                    "Station " + station.getStationNumber() + " masih occupied, tidak bisa dipilih.",
                    "Station Occupied",
                    JOptionPane.WARNING_MESSAGE);
        }
    }

    public void completePayment(Station station) {
        station.bookStation(selectedPaket);
        String randomCode = generateRandomCode();

        successPage.setBookingCode(randomCode);
        
        stationPage.updateStationView();
        
        showPage("SUCCESS");
    }

    public void showGameDetails(Game game) {
        GameDetailPage detailPage = new GameDetailPage(frame, game);
        detailPage.setVisible(true);
    }

    public void exitApplication() {
        int confirm = JOptionPane.showConfirmDialog(
            frame,
            "Apakah Anda yakin ingin keluar?",
            "Konfirmasi Keluar",
            JOptionPane.YES_NO_OPTION,
            JOptionPane.QUESTION_MESSAGE
        );
        if (confirm == JOptionPane.YES_OPTION) {
            System.exit(0);
        }
    }

    public List<Game> getGameList() {
        return gameList;
    }

    public List<Paket> getPaketList() {
        return paketList;
    }

    public List<Station> getStationList() {
        return stationList;
    }

    public Paket getSelectedPaket() {
        return selectedPaket;
    }

    public JFrame getFrame() {
        return frame;
    }

    private String generateRandomCode() {
        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        StringBuilder sb = new StringBuilder();
        Random rnd = new Random();
        while (sb.length() < 6) {
            int index = (int) (rnd.nextFloat() * chars.length());
            sb.append(chars.charAt(index));
        }
        return sb.toString();
    }
}