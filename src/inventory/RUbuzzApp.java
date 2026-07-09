import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Ellipse2D;
import java.awt.geom.RoundRectangle2D;

public class RUbuzzApp extends JFrame {

    // --- PREMIUM DARK PALETTE ---
    private final Color BG_DARKEST    = new Color(11, 17, 32);      // Ultra-deep clean slate black
    private final Color BG_SIDEBAR    = new Color(20, 29, 47);      // Solid slate sidebar
    private final Color BG_CARD       = new Color(28, 40, 62);      // Sharp container surfaces
    private final Color ACCENT_BLUE   = new Color(56, 189, 248);    // Vibrant neon cyan/blue
    private final Color TEXT_MAIN     = new Color(248, 250, 252);   // Pure white headings
    private final Color TEXT_MUTED    = new Color(148, 163, 184);   // Soft grey body copy
    private final Color NEON_GREEN    = new Color(74, 222, 128);    // Performance indicator tint

    private BackgroundContentPanel contentPanel;
    private JPanel viewContainer; 
    private CardLayout cardLayout;
    private JPanel activeSidebarItem = null;
    private String currentSport = "Cricket";

    // Snappy Performance Animation Vectors
    private float welcomeAlpha = 0.0f;
    private int welcomeYOffset = 15; 

    public RUbuzzApp() {
        setTitle("RUbuzz - Professional High-Performance Sports Terminal");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setSize((int) (screenSize.width * 0.85), (int) (screenSize.height * 0.85));
        setMinimumSize(new Dimension(1150, 820));
        setLocationRelativeTo(null);

        contentPanel = new BackgroundContentPanel();
        contentPanel.setLayout(new BorderLayout());

        contentPanel.add(createHeader(), BorderLayout.NORTH);
        
        JPanel centerBodyPanel = new JPanel(new BorderLayout());
        centerBodyPanel.setOpaque(false); 
        centerBodyPanel.add(createSidebar(), BorderLayout.WEST);

        cardLayout = new CardLayout();
        viewContainer = new JPanel(cardLayout);
        viewContainer.setOpaque(false); 
        viewContainer.setBorder(new EmptyBorder(40, 40, 40, 40));

        viewContainer.add(createAnimatedWelcomeDashboard(), "Welcome");
        viewContainer.add(createDynamicView("Scores"), "Scores");
        viewContainer.add(createDynamicView("Matches"), "Matches");
        viewContainer.add(createDynamicView("Players"), "Players");
        viewContainer.add(createDynamicView("Fixtures"), "Fixtures");

        centerBodyPanel.add(viewContainer, BorderLayout.CENTER);
        contentPanel.add(centerBodyPanel, BorderLayout.CENTER);
        contentPanel.add(createFooter(), BorderLayout.SOUTH);

        add(contentPanel);
        startSnappyIntroAnimation();
    }

    // --- DYNAMIC OPTIMIZED SHADED SPORT GRAPHIC CANVAS ---
    class BackgroundContentPanel extends JPanel {
        public BackgroundContentPanel() {
            setBackground(BG_DARKEST);
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            int w = getWidth();
            int h = getHeight();

            g2.setColor(new Color(30, 58, 138, 120)); 
            g2.setStroke(new BasicStroke(3f));

            if ("Cricket".equalsIgnoreCase(currentSport)) {
                g2.drawOval(w - 320, h / 2 - 160, 380, 380);
                g2.fillRect(w - 140, h / 2 - 60, 10, 160); 
                g2.fillRect(w - 115, h / 2 - 60, 10, 160);
                g2.fillRect(w - 90, h / 2 - 60, 10, 160);
                g2.fillRect(w - 145, h / 2 - 70, 65, 8);  
            } else if ("Football".equalsIgnoreCase(currentSport)) {
                g2.drawRect(w - 420, h / 3, 460, 360);
                g2.drawOval(w - 420, h / 2 - 80, 160, 160);
                g2.drawRect(w - 180, h / 2 - 130, 200, 260);
                g2.draw(new Ellipse2D.Float(w - 300, h / 2 - 20, 60, 60));
            } else if ("Badminton".equalsIgnoreCase(currentSport)) {
                for (int i = 0; i < w; i += 60) {
                    g2.drawLine(w - 350 + (i / 4), 120 + i, w - 50 + (i / 2), h - 120);
                }
                g2.drawOval(w - 260, h / 2 - 40, 120, 160); 
                g2.drawLine(w - 200, h / 2 + 120, w - 200, h - 70);  
            }

            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.60f));
            g2.setColor(BG_DARKEST);
            g2.fillRect(0, 0, w, h);

            g2.dispose();
        }
    }

    // --- CLEAN FLUID STANDARD CONTAINER CARD ---
    class CleanCard extends JPanel {
        public CleanCard(LayoutManager layout) {
            setLayout(layout);
            setOpaque(false);
            setBackground(BG_CARD);
        }

        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(getBackground());
            g2.fill(new RoundRectangle2D.Float(0, 0, getWidth(), getHeight(), 12, 12));
            g2.setColor(new Color(38, 52, 78)); // Fixed professional subtle border line
            g2.setStroke(new BasicStroke(1.2f));
            g2.draw(new RoundRectangle2D.Float(0, 0, getWidth() - 1, getHeight() - 1, 12, 12));
            g2.dispose();
            super.paintComponent(g);
        }
    }

    // --- BRAND HEADER ---
    private JPanel createHeader() {
        JPanel header = new JPanel(new BorderLayout());
        header.setBackground(new Color(20, 29, 47));
        header.setPreferredSize(new Dimension(getWidth(), 80));
        header.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(38, 52, 78)),
            new EmptyBorder(0, 40, 0, 40)
        ));

        JLabel logo = new JLabel("RUbuzz");
        logo.setFont(new Font("Segoe UI", Font.BOLD, 28));
        logo.setForeground(TEXT_MAIN);
        header.add(logo, BorderLayout.WEST);

        JPanel navWrapper = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 18));
        navWrapper.setOpaque(false);

        String[] sports = {"Cricket", "Football", "Badminton"};
        ButtonGroup group = new ButtonGroup();
        for (String sport : sports) {
            JToggleButton navBtn = new JToggleButton(sport);
            navBtn.setFont(new Font("Segoe UI", Font.BOLD, 14));
            navBtn.setForeground(TEXT_MUTED);
            navBtn.setContentAreaFilled(false);
            navBtn.setBorderPainted(false);
            navBtn.setFocusPainted(false);
            navBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
            navBtn.setBorder(new EmptyBorder(8, 16, 8, 16));

            if (sport.equals("Cricket")) {
                navBtn.setSelected(true);
                navBtn.setForeground(ACCENT_BLUE);
            }

            // Snappy text color shift hover interaction
            navBtn.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseEntered(MouseEvent e) {
                    if (!navBtn.isSelected()) navBtn.setForeground(TEXT_MAIN);
                }
                @Override
                public void mouseExited(MouseEvent e) {
                    if (!navBtn.isSelected()) navBtn.setForeground(TEXT_MUTED);
                }
            });

            navBtn.addActionListener(e -> {
                currentSport = sport;
                for (Component c : navWrapper.getComponents()) {
                    if (c instanceof JToggleButton) {
                        c.setForeground(((JToggleButton) c).isSelected() ? ACCENT_BLUE : TEXT_MUTED);
                    }
                }
                contentPanel.repaint();
                cardLayout.show(viewContainer, "Welcome");
            });

            group.add(navBtn);
            navWrapper.add(navBtn);
        }

        header.add(navWrapper, BorderLayout.EAST);
        return header;
    }

    // --- SIDEBAR COMPONENT ---
    private JPanel createSidebar() {
        JPanel sidebar = new JPanel();
        sidebar.setLayout(new BoxLayout(sidebar, BoxLayout.Y_AXIS));
        sidebar.setBackground(BG_SIDEBAR);
        sidebar.setPreferredSize(new Dimension(260, getHeight()));
        sidebar.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 1, new Color(38, 52, 78)));

        JLabel menuTitle = new JLabel("ANALYTICS ENGINE");
        menuTitle.setFont(new Font("Segoe UI", Font.BOLD, 11));
        menuTitle.setForeground(TEXT_MUTED);
        menuTitle.setBorder(new EmptyBorder(35, 30, 20, 30));
        sidebar.add(menuTitle);

        String[] modules = {"Scores", "Matches", "Players", "Fixtures"};
        for (String module : modules) {
            JPanel itemPanel = new JPanel(new BorderLayout());
            itemPanel.setOpaque(false);
            itemPanel.setMaximumSize(new Dimension(260, 50));
            itemPanel.setPreferredSize(new Dimension(260, 50));
            itemPanel.setBorder(new EmptyBorder(0, 30, 0, 0));
            itemPanel.setCursor(new Cursor(Cursor.HAND_CURSOR));

            JLabel label = new JLabel(module);
            label.setFont(new Font("Segoe UI", Font.PLAIN, 15));
            label.setForeground(TEXT_MUTED);
            itemPanel.add(label, BorderLayout.WEST);

            // Fast Hover Effect: Directly changes text color natively
            itemPanel.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseEntered(MouseEvent e) {
                    if (activeSidebarItem != itemPanel) {
                        label.setForeground(ACCENT_BLUE); // Highlight text on hover
                    }
                }
                @Override
                public void mouseExited(MouseEvent e) {
                    if (activeSidebarItem != itemPanel) {
                        label.setForeground(TEXT_MUTED); // Revert text color on exit
                    }
                }
                @Override
                public void mousePressed(MouseEvent e) {
                    if (activeSidebarItem != null) {
                        activeSidebarItem.setOpaque(false);
                        ((JLabel) activeSidebarItem.getComponent(0)).setForeground(TEXT_MUTED);
                    }
                    itemPanel.setOpaque(true);
                    itemPanel.setBackground(new Color(30, 58, 138, 150)); // Subtle background selection block
                    label.setForeground(TEXT_MAIN);
                    activeSidebarItem = itemPanel;

                    cardLayout.show(viewContainer, module);
                }
            });

            sidebar.add(itemPanel);
        }
        return sidebar;
    }

    // --- SNAPPY WELCOME DASHBOARD GRID ---
    private JPanel createAnimatedWelcomeDashboard() {
        JPanel welcomePanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, welcomeAlpha));
                g2.translate(0, welcomeYOffset);
                super.paintComponent(g2);
                g2.dispose();
            }
        };
        welcomePanel.setLayout(new BoxLayout(welcomePanel, BoxLayout.Y_AXIS));
        welcomePanel.setOpaque(false);

        JLabel welcomeTitle = new JLabel("Welcome to RUbuzz Command Hub");
        welcomeTitle.setFont(new Font("Segoe UI", Font.BOLD, 36));
        welcomeTitle.setForeground(TEXT_MAIN);
        welcomeTitle.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel welcomeSubtitle = new JLabel("Real-time telemetry and immersive graphics for selected sports channels.");
        welcomeSubtitle.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        welcomeSubtitle.setForeground(TEXT_MUTED);
        welcomeSubtitle.setAlignmentX(Component.LEFT_ALIGNMENT);

        welcomePanel.add(Box.createVerticalStrut(20));
        welcomePanel.add(welcomeTitle);
        welcomePanel.add(Box.createVerticalStrut(10));
        welcomePanel.add(welcomeSubtitle);
        welcomePanel.add(Box.createVerticalStrut(40));

        JPanel statsCardContainer = new JPanel(new GridLayout(1, 3, 25, 0));
        statsCardContainer.setOpaque(false);
        statsCardContainer.setMaximumSize(new Dimension(1400, 130));
        statsCardContainer.setAlignmentX(Component.LEFT_ALIGNMENT);

        statsCardContainer.add(createQuickStatCard("Live Channels", "14 Channels Active"));
        statsCardContainer.add(createQuickStatCard("Telemetry Ping", "18ms Stable"));
        statsCardContainer.add(createQuickStatCard("System Loads", "32% Nominal"));

        welcomePanel.add(statsCardContainer);
        return welcomePanel;
    }

    // --- ALIGNED LIVE CONTEXT DATA COMPONENT ---
    private JPanel createDynamicView(String viewName) {
        JPanel view = new JPanel(new BorderLayout(0, 25));
        view.setOpaque(false);

        CleanCard headerCard = new CleanCard(new BorderLayout());
        headerCard.setBorder(new EmptyBorder(25, 30, 25, 30));

        JLabel title = new JLabel(viewName + " Overview");
        title.setFont(new Font("Segoe UI", Font.BOLD, 24));
        title.setForeground(ACCENT_BLUE);

        JButton actionButton = new JButton("Run Live Query");
        actionButton.setBackground(new Color(14, 165, 233));
        actionButton.setForeground(TEXT_MAIN);
        actionButton.setFont(new Font("Segoe UI", Font.BOLD, 13));
        actionButton.setFocusPainted(false);
        actionButton.setBorder(new EmptyBorder(10, 22, 10, 22));
        actionButton.setCursor(new Cursor(Cursor.HAND_CURSOR));

        headerCard.add(title, BorderLayout.WEST);
        headerCard.add(actionButton, BorderLayout.EAST);

        String[] columns = {"Telemetry ID", "Instance", "Data Feed String", "Node"};
        Object[][] data = {
            {"#0819", viewName + " Instance Alpha", "Payload Delivery Success", "Cluster Edge-01"},
            {"#0820", viewName + " Instance Beta", "Sync Pipeline Synchronized", "Cluster Edge-04"},
            {"#0821", viewName + " Instance Gamma", "Awaiting Next Sequence", "Cloud Core-Main"}
        };
        
        JTable table = new JTable(data, columns);
        table.setRowHeight(45);
        table.setBackground(new Color(24, 34, 54));
        table.setForeground(TEXT_MAIN);
        table.setGridColor(new Color(45, 58, 84));
        table.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        table.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 14));
        table.getTableHeader().setBackground(new Color(16, 24, 40));
        table.getTableHeader().setForeground(ACCENT_BLUE);
        
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setOpaque(false);
        scrollPane.getViewport().setOpaque(false);
        scrollPane.setBorder(BorderFactory.createLineBorder(new Color(45, 58, 84), 1));

        view.add(headerCard, BorderLayout.NORTH);
        view.add(scrollPane, BorderLayout.CENTER);

        actionButton.addActionListener(e -> JOptionPane.showMessageDialog(this, 
                "Executing secure data pull for " + currentSport + " -> " + viewName));

        return view;
    }

    private JPanel createQuickStatCard(String title, String value) {
        CleanCard card = new CleanCard(new BorderLayout());
        card.setBorder(new EmptyBorder(25, 25, 25, 25));

        JLabel titleLabel = new JLabel(title);
        titleLabel.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        titleLabel.setForeground(TEXT_MUTED);

        JLabel valueLabel = new JLabel(value);
        valueLabel.setFont(new Font("Segoe UI", Font.BOLD, 22));
        valueLabel.setForeground(TEXT_MAIN);

        card.add(titleLabel, BorderLayout.NORTH);
        card.add(valueLabel, BorderLayout.SOUTH);
        return card;
    }

    private JPanel createFooter() {
        JPanel footer = new JPanel(new BorderLayout());
        footer.setBackground(new Color(20, 29, 47));
        footer.setPreferredSize(new Dimension(getWidth(), 45));
        footer.setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0, new Color(38, 52, 78)));

        JLabel copyright = new JLabel("© 2026 RUbuzz Enterprise Network. Snappy Text UI Engine Active.");
        copyright.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        copyright.setForeground(TEXT_MUTED);
        copyright.setBorder(new EmptyBorder(0, 40, 0, 0));

        JLabel status = new JLabel("● ENVIRONMENT ENGINE ONLINE ");
        status.setFont(new Font("Segoe UI", Font.BOLD, 11));
        status.setForeground(NEON_GREEN);
        status.setBorder(new EmptyBorder(0, 0, 0, 40));

        footer.add(copyright, BorderLayout.WEST);
        footer.add(status, BorderLayout.EAST);
        return footer;
    }

    private void startSnappyIntroAnimation() {
        Timer introTimer = new Timer(5, null);
        introTimer.addActionListener(e -> {
            welcomeAlpha += 0.08f; 
            if (welcomeYOffset > 0) welcomeYOffset -= 1;
            
            if (welcomeAlpha >= 1.0f) {
                welcomeAlpha = 1.0f;
                welcomeYOffset = 0;
                introTimer.stop();
            }
            viewContainer.repaint();
        });
        introTimer.start();
    }

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ignored) {}

        SwingUtilities.invokeLater(() -> {
            new RUbuzzApp().setVisible(true);
        });
    }
}