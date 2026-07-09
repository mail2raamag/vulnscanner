import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.RoundRectangle2D;

public class IVSmartLogin extends JFrame {

    // Color Palette (Modern Dark Theme)
    private static final Color COLOR_BG = new Color(18, 18, 24);         // Deep dark background
    private static final Color COLOR_CARD = new Color(30, 30, 40);       // Lighter container card
    private static final Color COLOR_ACCENT = new Color(110, 68, 255);   // Vibrant Purple/Indigo
    private static final Color COLOR_ACCENT_HOVER = new Color(138, 102, 255);
    private static final Color COLOR_TEXT_MAIN = new Color(240, 240, 245);
    private static final Color COLOR_TEXT_MUTED = new Color(150, 150, 165);
    private static final Color COLOR_FIELD_BG = new Color(42, 42, 56);

    public IVSmartLogin() {
        // Frame Settings
        setTitle("IV SMART - Login");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(450, 600);
        setLocationRelativeTo(null); // Center on screen
        setResizable(false);

        // Main Panel with background color
        JPanel mainPanel = new JPanel();
        mainPanel.setBackground(COLOR_BG);
        mainPanel.setLayout(new GridBagLayout());
        add(mainPanel);

        // Card Container (Gives a nice layered floating look)
        JPanel cardPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(COLOR_CARD);
                g2.fill(new RoundRectangle2D.Float(0, 0, getWidth(), getHeight(), 20, 20));
                g2.dispose();
            }
        };
        cardPanel.setOpaque(false);
        cardPanel.setPreferredSize(new Dimension(360, 480));
        cardPanel.setLayout(null); // Absolute layout for precise UI control

        // --- UI ELEMENTS ---

        // App Title
        JLabel titleLabel = new JLabel("IV SMART", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 28));
        titleLabel.setForeground(COLOR_ACCENT);
        titleLabel.setBounds(20, 40, 320, 35);
        cardPanel.add(titleLabel);

        // Subtitle
        JLabel subtitleLabel = new JLabel("Welcome back! Please login.", SwingConstants.CENTER);
        subtitleLabel.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        subtitleLabel.setForeground(COLOR_TEXT_MUTED);
        subtitleLabel.setBounds(20, 75, 320, 20);
        cardPanel.add(subtitleLabel);

        // Username Label & Field
        JLabel userLabel = new JLabel("Username");
        userLabel.setFont(new Font("Segoe UI", Font.BOLD, 12));
        userLabel.setForeground(COLOR_TEXT_MAIN);
        userLabel.setBounds(35, 130, 290, 20);
        cardPanel.add(userLabel);

        JTextField userField = createCustomTextField();
        userField.setBounds(35, 155, 290, 40);
        cardPanel.add(userField);

        // Password Label & Field
        JLabel passLabel = new JLabel("Password");
        passLabel.setFont(new Font("Segoe UI", Font.BOLD, 12));
        passLabel.setForeground(COLOR_TEXT_MAIN);
        passLabel.setBounds(35, 215, 290, 20);
        cardPanel.add(passLabel);

        JPasswordField passField = createCustomPasswordField();
        passField.setBounds(35, 240, 290, 40);
        cardPanel.add(passField);

        // Error Message Label
        JLabel errorLabel = new JLabel("", SwingConstants.CENTER);
        errorLabel.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        errorLabel.setForeground(new Color(255, 100, 100)); // Soft red error
        errorLabel.setBounds(35, 300, 290, 20);
        cardPanel.add(errorLabel);

        // Login Button
        JButton loginButton = new JButton("Sign In") {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                if (getModel().isArmed()) {
                    g2.setColor(COLOR_ACCENT_HOVER);
                } else if (getModel().isRollover()) {
                    g2.setColor(COLOR_ACCENT_HOVER);
                } else {
                    g2.setColor(COLOR_ACCENT);
                }
                g2.fill(new RoundRectangle2D.Float(0, 0, getWidth(), getHeight(), 12, 12));
                g2.dispose();
                super.paintComponent(g);
            }
        };
        loginButton.setFont(new Font("Segoe UI", Font.BOLD, 14));
        loginButton.setForeground(Color.WHITE);
        loginButton.setContentAreaFilled(false);
        loginButton.setBorderPainted(false);
        loginButton.setFocusPainted(false);
        loginButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        loginButton.setBounds(35, 340, 290, 45);
        cardPanel.add(loginButton);

        // --- AUTHENTICATION LOGIC ---
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = userField.getText().trim();
                String password = new String(passField.getPassword());

                // Simple check logic (Replace with DB check or proper logic later)
                if (username.isEmpty() || password.isEmpty()) {
                    errorLabel.setText("Fields cannot be empty!");
                } else if (username.equals("admin") && password.equals("password")) {
                    errorLabel.setText("");
                    JOptionPane.showMessageDialog(IVSmartLogin.this, 
                            "Login Successful!", "Success", JOptionPane.INFORMATION_MESSAGE);
                    
                    // Switch to profile screen view
                    showProfilePage(username);
                } else {
                    errorLabel.setText("Invalid username or password.");
                }
            }
        });

        mainPanel.add(cardPanel);
    }

    // Modern profile view mock
    private void showProfilePage(String username) {
        getContentPane().removeAll();
        
        JPanel profilePanel = new JPanel();
        profilePanel.setBackground(COLOR_BG);
        profilePanel.setLayout(new GridBagLayout());

        JPanel profileCard = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(COLOR_CARD);
                g2.fill(new RoundRectangle2D.Float(0, 0, getWidth(), getHeight(), 20, 20));
                g2.dispose();
            }
        };
        profileCard.setOpaque(false);
        profileCard.setPreferredSize(new Dimension(360, 480));
        profileCard.setLayout(null);

        JLabel welcomeLabel = new JLabel("IV SMART PROFILE", SwingConstants.CENTER);
        welcomeLabel.setFont(new Font("Segoe UI", Font.BOLD, 22));
        welcomeLabel.setForeground(COLOR_ACCENT);
        welcomeLabel.setBounds(20, 40, 320, 30);
        profileCard.add(welcomeLabel);

        // Profile Avatar Mock
        JPanel avatarMock = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(COLOR_ACCENT);
                g2.fillOval(0, 0, getWidth(), getHeight());
                g2.dispose();
            }
        };
        avatarMock.setOpaque(false);
        avatarMock.setBounds(130, 100, 100, 100);
        profileCard.add(avatarMock);

        JLabel userDisplay = new JLabel("@" + username, SwingConstants.CENTER);
        userDisplay.setFont(new Font("Segoe UI", Font.BOLD, 16));
        userDisplay.setForeground(COLOR_TEXT_MAIN);
        userDisplay.setBounds(20, 220, 320, 25);
        profileCard.add(userDisplay);

        JLabel statusLabel = new JLabel("Status: Active Professional", SwingConstants.CENTER);
        statusLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        statusLabel.setForeground(COLOR_TEXT_MUTED);
        statusLabel.setBounds(20, 250, 320, 20);
        profileCard.add(statusLabel);

        profilePanel.add(profileCard);
        add(profilePanel);
        revalidate();
        repaint();
    }

    // Helper to generate modern flat styled textfields
    private JTextField createCustomTextField() {
        JTextField field = new JTextField();
        field.setBackground(COLOR_FIELD_BG);
        field.setForeground(COLOR_TEXT_MAIN);
        field.setCaretColor(COLOR_TEXT_MAIN);
        field.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        field.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        return field;
    }

    private JPasswordField createCustomPasswordField() {
        JPasswordField field = new JPasswordField();
        field.setBackground(COLOR_FIELD_BG);
        field.setForeground(COLOR_TEXT_MAIN);
        field.setCaretColor(COLOR_TEXT_MAIN);
        field.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        field.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        return field;
    }

    public static void main(String[] args) {
        // Thread-safe UI building
        SwingUtilities.invokeLater(() -> {
            new IVSmartLogin().setVisible(true);
        });
    }
}