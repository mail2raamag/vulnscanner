import java.awt.*;
import java.awt.event.*;

 class NetherScanner extends Frame {

    // Define Spotify-inspired Neon Green and Dark Color Palette
    private static final Color COLOR_BG_DARK = new Color(18, 18, 18);
    private static final Color COLOR_SIDEBAR = new Color(0, 0, 0);
    private static final Color COLOR_CARD_BG = new Color(32, 32, 32);
    private static final Color COLOR_SPOTIFY_GREEN = new Color(30, 215, 96);
    private static final Color COLOR_TEXT_LIGHT = new Color(240, 240, 240);
    private static final Color COLOR_TEXT_MUTED = new Color(160, 160, 160);

    public NetherScanner() {
        // 1. Frame Setup
        setTitle("NetherScanner");
        setSize(1000, 650);
        setBackground(COLOR_BG_DARK);
        setLayout(new BorderLayout(10, 10));

        // 2. Sidebar Navigation Panel (Left)
        Panel sidebar = new Panel();
        sidebar.setBackground(COLOR_SIDEBAR);
        sidebar.setLayout(new GridLayout(8, 1, 0, 15));
        sidebar.setPreferredSize(new Dimension(200, 0));

        // App Title branding
        Label brandLabel = new Label("  NETHERSCANNER", Label.LEFT);
        brandLabel.setFont(new Font("SansSerif", Font.BOLD, 22));
        brandLabel.setForeground(COLOR_SPOTIFY_GREEN);
        sidebar.add(brandLabel);

        // Sidebar Menu Items
        sidebar.add(createSidebarButton("  Home"));
        sidebar.add(createSidebarButton("  Shell"));
        sidebar.add(createSidebarButton(" AI Ethical Hacker"));
        // sidebar.add(createSidebarButton("  Local Scanner"));
        // sidebar.add(createSidebarButton("  Reports"));
        // sidebar.add(createSidebarButton("  Settings"));

        add(sidebar, BorderLayout.WEST);

        // 3. Main Dashboard Panel (Center Content)
        Panel mainContent = new Panel();
        mainContent.setLayout(new BorderLayout(0, 20));

        // Welcome Header
        Label headerLabel = new Label("Security Dashboard", Label.LEFT);
        headerLabel.setFont(new Font("SansSerif", Font.BOLD, 26));
        headerLabel.setForeground(COLOR_TEXT_LIGHT);
        mainContent.add(headerLabel, BorderLayout.NORTH);

        // Grid of Music Cards (Simulating playlists/albums)
        Panel cardGrid = new Panel();
        cardGrid.setLayout(new GridLayout(2, 3, 20, 20));

        cardGrid.add(createMusicCard("Website Vulnerability Scanner", "Scan websites for common vulnerabilities"));
        cardGrid.add(createMusicCard("IP Address Scanner", "Gather information about an IP address"));
        cardGrid.add(createMusicCard("WHOIS Lookup", "Retrieve domain registration details"));
        cardGrid.add(createMusicCard("DNS Enumeration", "Discover DNS records"));
        cardGrid.add(createMusicCard("Port Scanner", "Scan common TCP ports"));
        cardGrid.add(createMusicCard("Local System Scanner", "Inspect local machine security"));

        mainContent.add(cardGrid, BorderLayout.CENTER);
        add(mainContent, BorderLayout.CENTER);

        // 4. Bottom Media Player Controller Panel (South)
        Panel playerBar = new Panel();
        playerBar.setBackground(COLOR_SIDEBAR);
        playerBar.setPreferredSize(new Dimension(0, 90));
        playerBar.setLayout(new GridLayout(1, 3, 10, 0));

        // Player Section Left: Currently Playing Track Info
        Panel trackInfo = new Panel();
        trackInfo.setLayout(new GridLayout(2, 1, 0, 2));
        Label trackName = new Label("  Status : Ready", Label.LEFT);
        trackName.setFont(new Font("SansSerif", Font.BOLD, 14));
        trackName.setForeground(COLOR_TEXT_LIGHT);
        Label trackArtist = new Label("  Last Scan : None", Label.LEFT);
        trackArtist.setFont(new Font("SansSerif", Font.PLAIN, 12));
        trackArtist.setForeground(COLOR_TEXT_MUTED);
        trackInfo.add(trackName);
        trackInfo.add(trackArtist);
        playerBar.add(trackInfo);

        // Player Section Center: Playback Controls
        Panel controls = new Panel();
        controls.setLayout(new FlowLayout(FlowLayout.CENTER, 15, 25));

        Label systemStatus = new Label("System : Idle");
        systemStatus.setFont(new Font("SansSerif", Font.BOLD, 16));
        systemStatus.setForeground(COLOR_SPOTIFY_GREEN);

        controls.add(systemStatus);
        playerBar.add(controls);

        // Player Section Right: Utility / Volume dummy space
        Panel utilities = new Panel();
        utilities.setLayout(new FlowLayout(FlowLayout.RIGHT, 20, 30));
        Label volLabel = new Label("", Label.RIGHT);
        volLabel.setFont(new Font("SansSerif", Font.PLAIN, 12));
        volLabel.setForeground(COLOR_TEXT_MUTED);
        utilities.add(volLabel);
        playerBar.add(utilities);

        add(playerBar, BorderLayout.SOUTH);

        // Window Closing Event Listener
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent we) {
                System.exit(0);
            }
        });

        // Set layout padding borders via custom Insets
        setVisible(true);
    }

    // Helper method to create uniform, styled sidebar options
    private Label createSidebarButton(String text) {
        Label lbl = new Label(text, Label.LEFT);
        lbl.setFont(new Font("SansSerif", Font.BOLD, 14));
        lbl.setForeground(COLOR_TEXT_MUTED);
        return lbl;
    }

    // Helper method to build an album/playlist card layout
    private Panel createMusicCard(String title, String subtitle) {
        Panel card = new Panel();
        card.setBackground(COLOR_CARD_BG);
        card.setLayout(new BorderLayout(5, 5));

        // Top colored placeholder simulating album art cover
        Panel artPlaceholder = new Panel();
        artPlaceholder.setBackground(new Color(45, 45, 45));
        Label noteIcon = new Label("🎵", Label.CENTER);
        noteIcon.setFont(new Font("SansSerif", Font.PLAIN, 32));
        noteIcon.setForeground(COLOR_SPOTIFY_GREEN);
        artPlaceholder.setLayout(new BorderLayout());
        artPlaceholder.add(noteIcon, BorderLayout.CENTER);
        card.add(artPlaceholder, BorderLayout.CENTER);

        // Bottom text labels inside the card
        Panel textPanel = new Panel();
        textPanel.setLayout(new GridLayout(2, 1));
        textPanel.setBackground(COLOR_CARD_BG);

        Label lblTitle = new Label(" " + title, Label.LEFT);
        lblTitle.setFont(new Font("SansSerif", Font.BOLD, 14));
        lblTitle.setForeground(COLOR_TEXT_LIGHT);

        Label lblSub = new Label(" " + subtitle, Label.LEFT);
        lblSub.setFont(new Font("SansSerif", Font.PLAIN, 11));
        lblSub.setForeground(COLOR_TEXT_MUTED);

        textPanel.add(lblTitle);
        textPanel.add(lblSub);

        card.add(textPanel, BorderLayout.SOUTH);
        return card;
    }

    // Quick control button styling method
    private void styleControlButton(Button btn, int fontSize, Color fgColor) {
        btn.setFont(new Font("SansSerif", Font.PLAIN, fontSize));
        btn.setBackground(COLOR_CARD_BG);
        btn.setForeground(fgColor);
    }

    // Frame window border padding overrides
    @Override
    public Insets getInsets() {
        return new Insets(40, 15, 15, 15);
    }

    public static void main(String[] args) {
        new NetherScanner();
    }
}
