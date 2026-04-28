package findmyroomie;

import javax.swing.*;
import javax.swing.border.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.util.List;


public class MainFrame extends JFrame {


    static final Color BG        = new Color(15,  23,  42);   
    static final Color CARD      = new Color(30,  41,  59);   
    static final Color ACCENT    = new Color(20, 184, 166);   
    static final Color ACCENT2   = new Color(94, 234, 141);   
    static final Color TEXT      = new Color(226, 232, 240);  
    static final Color SUBTEXT   = new Color(148, 163, 184);  
    static final Color DANGER    = new Color(248,  113, 113); 
    static final Color WARNING   = new Color(251, 191,  36);  


    static final Font FONT_TITLE  = new Font("Segoe UI", Font.BOLD,  22);
    static final Font FONT_LABEL  = new Font("Segoe UI", Font.BOLD,  13);
    static final Font FONT_BODY   = new Font("Segoe UI", Font.PLAIN, 13);
    static final Font FONT_SMALL  = new Font("Segoe UI", Font.PLAIN, 11);
    static final Font FONT_CARD   = new Font("Segoe UI", Font.BOLD,  15);

    private CardLayout cardLayout;
    private JPanel     mainPanel;
    private User       loggedInUser; 


    static final String SCREEN_LOGIN    = "LOGIN";
    static final String SCREEN_REGISTER = "REGISTER";
    static final String SCREEN_HOST     = "HOST";
    static final String SCREEN_SEEKER   = "SEEKER";

    public MainFrame() {
        setTitle("FindMyRoomie");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(960, 680);
        setLocationRelativeTo(null);
        setBackground(BG);

        cardLayout = new CardLayout();
        mainPanel  = new JPanel(cardLayout);
        mainPanel.setBackground(BG);

        mainPanel.add(buildLoginScreen(),    SCREEN_LOGIN);
        mainPanel.add(buildRegisterScreen(), SCREEN_REGISTER);

        add(mainPanel);
        cardLayout.show(mainPanel, SCREEN_LOGIN);
        setVisible(true);
    }





    
    static JButton btn(String text, Color bg) {
        JButton b = new JButton(text);
        b.setBackground(bg);
        b.setForeground(Color.WHITE);
        b.setFont(FONT_LABEL);
        b.setFocusPainted(false);
        b.setBorderPainted(false);
        b.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        b.setOpaque(true);
        return b;
    }

    
    static JTextField field(String placeholder) {
        JTextField f = new JTextField(20);
        f.setBackground(new Color(51, 65, 85));
        f.setForeground(TEXT);
        f.setCaretColor(TEXT);
        f.setFont(FONT_BODY);
        f.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(71, 85, 105)),
                BorderFactory.createEmptyBorder(6, 10, 6, 10)));
        return f;
    }

    
    static JPasswordField passField() {
        JPasswordField f = new JPasswordField(20);
        f.setBackground(new Color(51, 65, 85));
        f.setForeground(TEXT);
        f.setCaretColor(TEXT);
        f.setFont(FONT_BODY);
        f.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(71, 85, 105)),
                BorderFactory.createEmptyBorder(6, 10, 6, 10)));
        return f;
    }

    
    static JComboBox<String> combo(String... items) {
        JComboBox<String> c = new JComboBox<>(items);
        c.setBackground(new Color(51, 65, 85));
        c.setForeground(TEXT);
        c.setFont(FONT_BODY);
        ((JLabel) c.getRenderer()).setBackground(new Color(51, 65, 85));
        return c;
    }

    
    static JLabel lbl(String text) {
        JLabel l = new JLabel(text);
        l.setForeground(SUBTEXT);
        l.setFont(FONT_LABEL);
        return l;
    }

    
    static JPanel card() {
        JPanel p = new JPanel();
        p.setBackground(CARD);
        p.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(51, 65, 85)),
                BorderFactory.createEmptyBorder(16, 20, 16, 20)));
        return p;
    }

    
    static JScrollPane scroll(JComponent c) {
        JScrollPane sp = new JScrollPane(c);
        sp.setBackground(BG);
        sp.getViewport().setBackground(BG);
        sp.setBorder(BorderFactory.createEmptyBorder());
        return sp;
    }

    
    static void error(Component parent, String msg) {
        JOptionPane.showMessageDialog(parent, msg, "Error", JOptionPane.ERROR_MESSAGE);
    }

    
    static void info(Component parent, String msg) {
        JOptionPane.showMessageDialog(parent, msg, "Info", JOptionPane.INFORMATION_MESSAGE);
    }

    void showScreen(String name) {
        cardLayout.show(mainPanel, name);
    }

    void switchToHost() {
        mainPanel.add(buildHostScreen(), SCREEN_HOST);
        cardLayout.show(mainPanel, SCREEN_HOST);
    }

    void switchToSeeker() {
        mainPanel.add(buildSeekerScreen(), SCREEN_SEEKER);
        cardLayout.show(mainPanel, SCREEN_SEEKER);
    }





    JPanel buildLoginScreen() {
        JPanel root = new JPanel(new GridBagLayout());
        root.setBackground(BG);

        JPanel box = card();
        box.setLayout(new BoxLayout(box, BoxLayout.Y_AXIS));
        box.setPreferredSize(new Dimension(380, 480));

        JLabel title = new JLabel("FindMyRoomie");
        title.setFont(FONT_TITLE);
        title.setForeground(ACCENT);
        title.setAlignmentX(CENTER_ALIGNMENT);

        JLabel sub = new JLabel("Login to your account");
        sub.setFont(FONT_BODY);
        sub.setForeground(SUBTEXT);
        sub.setAlignmentX(CENTER_ALIGNMENT);

        JTextField emailF   = field("Email");
        JPasswordField passF = passField();

        JButton loginBtn  = btn("Login",    ACCENT);
        JButton regBtn    = btn("Register", new Color(51, 65, 85));

        loginBtn.setAlignmentX(CENTER_ALIGNMENT);
        regBtn.setAlignmentX(CENTER_ALIGNMENT);
        emailF.setMaximumSize(new Dimension(Integer.MAX_VALUE, 38));
        passF.setMaximumSize(new Dimension(Integer.MAX_VALUE, 38));
        loginBtn.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));
        regBtn.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));

        box.add(Box.createVerticalStrut(8));
        box.add(title);
        box.add(Box.createVerticalStrut(4));
        box.add(sub);
        box.add(Box.createVerticalStrut(24));
        box.add(lbl("Email")); box.add(Box.createVerticalStrut(4));
        box.add(emailF);
        box.add(Box.createVerticalStrut(12));
        box.add(lbl("Password")); box.add(Box.createVerticalStrut(4));
        box.add(passF);
        box.add(Box.createVerticalStrut(20));
        box.add(loginBtn);
        box.add(Box.createVerticalStrut(8));
        box.add(regBtn);
        box.add(Box.createVerticalStrut(8));

        loginBtn.addActionListener(e -> {
            String email = emailF.getText().trim();
            String pass  = new String(passF.getPassword()).trim();

            if (email.isEmpty() || pass.isEmpty()) { error(this, "Please fill all fields."); return; }
            if (!Validator.isValidEmail(email))    { error(this, "Invalid email format."); return; }

            User u = UserDAO.login(email, pass);
            if (u == null) { error(this, "Wrong email or password."); return; }

            loggedInUser = u;
            NotificationService.notify("Welcome back, " + u.getName() + "! 👋");
            if (u.getRole().equals("HOST")) switchToHost();
            else                             switchToSeeker();
        });

        regBtn.addActionListener(e -> showScreen(SCREEN_REGISTER));

        root.add(box);
        return root;
    }

    JPanel buildRegisterScreen() {
        JPanel root = new JPanel(new BorderLayout());
        root.setBackground(BG);


        JPanel header = new JPanel(new FlowLayout(FlowLayout.LEFT, 20, 12));
        header.setBackground(CARD);
        JLabel titleLbl = new JLabel(" FindMyRoomie  —  Create Account");
        titleLbl.setFont(FONT_TITLE);
        titleLbl.setForeground(ACCENT);
        header.add(titleLbl);
        root.add(header, BorderLayout.NORTH);


        JPanel form = new JPanel(new GridBagLayout());
        form.setBackground(BG);
        form.setBorder(BorderFactory.createEmptyBorder(20, 60, 20, 60));
        GridBagConstraints g = new GridBagConstraints();
        g.fill = GridBagConstraints.HORIZONTAL;
        g.insets = new Insets(5, 5, 5, 5);


        JTextField  nameF    = field("Full Name");
        JTextField  emailF   = field("Email");
        JPasswordField passF = passField();
        JTextField  phoneF   = field("10-digit phone");
        JTextField  aadharF  = field("12-digit Aadhar");
        JTextField  ageF     = field("Age");
        JTextField  cityF    = field("Preferred City");
        JTextField  occupF   = field("Student / Working Professional / etc.");
        JTextArea   bioA     = new JTextArea(3, 20);
        bioA.setBackground(new Color(51, 65, 85));
        bioA.setForeground(TEXT);
        bioA.setFont(FONT_BODY);
        bioA.setLineWrap(true);
        bioA.setWrapStyleWord(true);

        JComboBox<String> roleC     = combo("SEEKER", "HOST");
        JComboBox<String> genderC   = combo("MALE", "FEMALE", "OTHER");
        JComboBox<String> smokingC  = combo("NEVER", "OCCASIONALLY", "REGULARLY");
        JComboBox<String> drinkingC = combo("NEVER", "OCCASIONALLY", "REGULARLY");


        JLabel passHint = new JLabel(" ");
        passHint.setFont(FONT_SMALL);
        passHint.setForeground(SUBTEXT);

        passF.addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent e) {
                String p = new String(passF.getPassword());
                String err = Validator.getPasswordError(p);
                if (err == null) {
                    passHint.setText(" Strong password");
                    passHint.setForeground(ACCENT2);
                } else {
                    passHint.setText("X " + err);
                    passHint.setForeground(DANGER);
                }
            }
        });


        int row = 0;
        Object[][] rows = {
            {"Full Name",        nameF},
            {"Email",            emailF},
            {"Password",         passF},
            {"",                 passHint},
            {"Phone",            phoneF},
            {"Aadhar Number",    aadharF},
            {"Age",              ageF},
            {"Role",             roleC},
            {"Gender",           genderC},
            {"Smoking Habit",    smokingC},
            {"Drinking Habit",   drinkingC},
            {"Occupation",       occupF},
            {"Preferred City",   cityF},
            {"About You (Bio)",  new JScrollPane(bioA)},
        };

        for (Object[] r : rows) {
            String lbText = (String) r[0];
            Component comp = (Component) r[1];

            if (!lbText.isEmpty()) {
                g.gridx = 0; g.gridy = row; g.weightx = 0.3;
                form.add(lbl(lbText), g);
            }
            g.gridx = 1; g.gridy = row; g.weightx = 0.7;
            comp.setPreferredSize(new Dimension(300, 36));
            form.add(comp, g);
            row++;
        }


        JButton regBtn  = btn("Create Account", ACCENT);
        JButton backBtn = btn("← Back to Login", new Color(51, 65, 85));
        g.gridx = 1; g.gridy = row++; form.add(regBtn,  g);
        g.gridx = 1; g.gridy = row++;   form.add(backBtn, g);

        regBtn.addActionListener(e -> {
            String name    = nameF.getText().trim();
            String email   = emailF.getText().trim();
            String pass    = new String(passF.getPassword()).trim();
            String phone   = phoneF.getText().trim();
            String aadhar  = aadharF.getText().trim();
            String ageStr  = ageF.getText().trim();
            String city    = cityF.getText().trim();
            String occup   = occupF.getText().trim();
            String bio     = bioA.getText().trim();
            String role    = (String) roleC.getSelectedItem();
            String gender  = (String) genderC.getSelectedItem();
            String smoking = (String) smokingC.getSelectedItem();
            String drinking= (String) drinkingC.getSelectedItem();


            if (name.isEmpty() || email.isEmpty() || pass.isEmpty() || phone.isEmpty()) {
                error(this, "Name, email, password and phone are required."); return;
            }
            if (!Validator.isValidEmail(email))    { error(this, "Invalid email format."); return; }
            if (!Validator.isValidPhone(phone))    { error(this, "Phone must be 10 digits starting with 6-9."); return; }
            if (!aadhar.isEmpty() && !Validator.isValidAadhar(aadhar)) {
                error(this, "Aadhar must be 12 digits."); return;
            }
            String passErr = Validator.getPasswordError(pass);
            if (passErr != null) { error(this, passErr); return; }
            if (UserDAO.emailExists(email)) { error(this, "Email already registered."); return; }

            int age = 0;
            try { age = Integer.parseInt(ageStr); } catch (NumberFormatException ex) {}

            boolean ok = UserDAO.register(name, email, pass, phone, role, aadhar,
                                          smoking, drinking, occup, gender, city, age, bio);
            if (ok) {
                info(this, "Account created! Please login.");
                showScreen(SCREEN_LOGIN);
            } else {
                String reason = UserDAO.lastError.isEmpty() ? "Unknown error." : UserDAO.lastError;
                error(this, "Registration failed:\n" + reason);
            }
        });

        backBtn.addActionListener(e -> showScreen(SCREEN_LOGIN));

        root.add(scroll(form), BorderLayout.CENTER);
        return root;
    }





    JPanel buildHostScreen() {
        JPanel root = new JPanel(new BorderLayout());
        root.setBackground(BG);


        JPanel header = buildHeader("HOST Dashboard  —  " + loggedInUser.getName());


        JPanel sidebar = new JPanel();
        sidebar.setLayout(new BoxLayout(sidebar, BoxLayout.Y_AXIS));
        sidebar.setBackground(CARD);
        sidebar.setPreferredSize(new Dimension(200, 0));
        sidebar.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));

        JPanel content = new JPanel(new CardLayout());
        content.setBackground(BG);

        String[] tabs = {"List a Room", "My Listings", "Room Requests", "My Profile"};
        JPanel[] panels = {
            buildListRoomPanel(content),
            buildHostListingsPanel(content),
            buildHostRequestsPanel(),
            buildProfilePanel(content)
        };

        CardLayout cl = (CardLayout) content.getLayout();
        for (int i = 0; i < tabs.length; i++) {
            content.add(panels[i], tabs[i]);
        }
        cl.show(content, tabs[0]);


        for (String tab : tabs) {
            JButton b = btn("  " + tab, CARD);
            b.setForeground(SUBTEXT);
            b.setFont(FONT_BODY);
            b.setMaximumSize(new Dimension(Integer.MAX_VALUE, 44));
            b.setHorizontalAlignment(SwingConstants.LEFT);
            b.addActionListener(e -> {
                cl.show(content, tab);

            });
            sidebar.add(b);
            sidebar.add(Box.createVerticalStrut(4));
        }

        JButton logoutBtn = btn("Logout", DANGER);
        logoutBtn.setMaximumSize(new Dimension(Integer.MAX_VALUE, 44));
        logoutBtn.setFont(FONT_BODY);
        sidebar.add(Box.createVerticalGlue());
        sidebar.add(logoutBtn);

        logoutBtn.addActionListener(e -> {
            loggedInUser = null;
            showScreen(SCREEN_LOGIN);
        });

        root.add(header,  BorderLayout.NORTH);
        root.add(sidebar, BorderLayout.WEST);
        root.add(content, BorderLayout.CENTER);
        return root;
    }

    JPanel buildListRoomPanel(JPanel parent) {
        JPanel root = new JPanel(new GridBagLayout());
        root.setBackground(BG);

        JPanel form = card();
        form.setLayout(new GridBagLayout());
        form.setPreferredSize(new Dimension(600, 600));
        GridBagConstraints g = new GridBagConstraints();
        g.fill = GridBagConstraints.HORIZONTAL;
        g.insets = new Insets(6, 8, 6, 8);

        JTextField titleF    = field("Room title");
        JTextField cityF     = field("City");
        JTextField rentF     = field("Monthly rent in Rs.");
        JTextField bedsF     = field("Number of bedrooms");
        JTextField bathsF    = field("Number of bathrooms");
        JTextField availF    = field("YYYY-MM-DD");
        JTextField amenF     = field("WiFi, AC, Parking, Geyser ...");
        JTextArea  descA     = new JTextArea(3, 20);
        descA.setBackground(new Color(51, 65, 85));
        descA.setForeground(TEXT);
        descA.setFont(FONT_BODY);
        descA.setLineWrap(true);

        JComboBox<String> typeC     = combo("SINGLE", "SHARED", "ENTIRE_APARTMENT");
        JCheckBox furnishedCB       = new JCheckBox("Furnished");
        JCheckBox petsCB            = new JCheckBox("Pets Allowed");
        JCheckBox smokingCB         = new JCheckBox("Smoking Allowed");
        furnishedCB.setBackground(CARD); furnishedCB.setForeground(TEXT);
        petsCB.setBackground(CARD);      petsCB.setForeground(TEXT);
        smokingCB.setBackground(CARD);   smokingCB.setForeground(TEXT);


        JLabel imageLabel = new JLabel("No image selected");
        imageLabel.setForeground(SUBTEXT);
        imageLabel.setFont(FONT_SMALL);
        JButton pickImgBtn = btn("Upload Room Photo", new Color(51, 65, 85));
        final String[] imagePath = {""};
        pickImgBtn.addActionListener(e -> {
            JFileChooser fc = new JFileChooser();
            fc.setFileFilter(new FileNameExtensionFilter("Images", "jpg", "jpeg", "png"));
            if (fc.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
                File f = fc.getSelectedFile();
                imagePath[0] = f.getAbsolutePath();
                imageLabel.setText(f.getName());
                imageLabel.setForeground(ACCENT2);
            }
        });

        Object[][] rows = {
            {"Title",            titleF},
            {"City",             cityF},
            {"Rent (Rs/month)",  rentF},
            {"Room Type",        typeC},
            {"Bedrooms",         bedsF},
            {"Bathrooms",        bathsF},
            {"Available From",   availF},
            {"Amenities",        amenF},
            {"Description",      new JScrollPane(descA)},
            {"Photo",            pickImgBtn},
            {"",                 imageLabel},
            {"Options",          furnishedCB},
            {"",                 petsCB},
            {"",                 smokingCB},
        };

        int row = 0;
        for (Object[] r : rows) {
            if (!((String) r[0]).isEmpty()) {
                g.gridx = 0; g.gridy = row; g.weightx = 0.3;
                form.add(lbl((String) r[0]), g);
            }
            g.gridx = 1; g.gridy = row; g.weightx = 0.7;
            ((Component) r[1]).setPreferredSize(new Dimension(300, 34));
            form.add((Component) r[1], g);
            row++;
        }

        JButton submitBtn = btn("List Room", ACCENT);
        g.gridx = 1; g.gridy = row; form.add(submitBtn, g);

        submitBtn.addActionListener(e -> {
            try {
                String title = titleF.getText().trim();
                String city  = cityF.getText().trim();
                double rent  = Double.parseDouble(rentF.getText().trim());
                int beds     = Integer.parseInt(bedsF.getText().trim());
                int baths    = Integer.parseInt(bathsF.getText().trim());
                String type  = (String) typeC.getSelectedItem();
                String avail = availF.getText().trim();
                String amen  = amenF.getText().trim();
                String desc  = descA.getText().trim();

                if (title.isEmpty() || city.isEmpty()) { error(this, "Title and city are required."); return; }

                boolean ok = RoomDAO.addRoom(title, city, rent, furnishedCB.isSelected(),
                                             loggedInUser.getId(), desc, imagePath[0],
                                             beds, baths, type, petsCB.isSelected(),
                                             smokingCB.isSelected(), amen, avail);

                if (ok) {
                    NotificationService.notify("Room listed successfully! 🏠");
                    titleF.setText(""); cityF.setText(""); rentF.setText(""); descA.setText("");
                } else {
                    error(this, "Failed to list room.");
                }
            } catch (NumberFormatException ex) {
                error(this, "Rent, bedrooms and bathrooms must be numbers.");
            }
        });

        g.gridx = 0;
        g.gridy = 0;
        g.weightx = 1;
        g.weighty = 1;
        g.fill = GridBagConstraints.BOTH;
        root.add(scroll(form), g);
        return root;
    }

    JPanel buildHostListingsPanel(JPanel parent) {
        JPanel root = new JPanel(new BorderLayout(0, 10));
        root.setBackground(BG);
        root.setBorder(BorderFactory.createEmptyBorder(16, 16, 16, 16));

        JLabel hdr = new JLabel("My Room Listings");
        hdr.setFont(FONT_TITLE); hdr.setForeground(ACCENT);
        root.add(hdr, BorderLayout.NORTH);

        JPanel listPanel = new JPanel();
        listPanel.setLayout(new BoxLayout(listPanel, BoxLayout.Y_AXIS));
        listPanel.setBackground(BG);

        JButton refreshBtn = btn("Refresh", new Color(51, 65, 85));
        refreshBtn.addActionListener(e -> loadHostListings(listPanel));

        root.add(refreshBtn, BorderLayout.SOUTH);
        root.add(scroll(listPanel), BorderLayout.CENTER);

        loadHostListings(listPanel);
        return root;
    }

    void loadHostListings(JPanel listPanel) {
        listPanel.removeAll();
        List<Room> rooms = RoomDAO.getRoomsByHost(loggedInUser.getId());

        if (rooms.isEmpty()) {
            JLabel empty = new JLabel("No listings yet. Add a room!");
            empty.setForeground(SUBTEXT); empty.setFont(FONT_BODY);
            listPanel.add(empty);
        } else {
            for (Room r : rooms) {
                listPanel.add(buildRoomCard(r, true, listPanel));
                listPanel.add(Box.createVerticalStrut(10));
            }
        }
        listPanel.revalidate();
        listPanel.repaint();
    }

    JPanel buildRoomCard(Room r, boolean showDelete, JPanel listPanel) {
        JPanel card = card();
        card.setLayout(new BorderLayout(10, 6));
        card.setMaximumSize(new Dimension(Integer.MAX_VALUE, 140));


        if (r.getImagePath() != null && !r.getImagePath().isEmpty()) {
            try {
                ImageIcon img = new ImageIcon(new ImageIcon(r.getImagePath())
                        .getImage().getScaledInstance(120, 90, Image.SCALE_SMOOTH));
                JLabel imgLbl = new JLabel(img);
                imgLbl.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 10));
                card.add(imgLbl, BorderLayout.WEST);
            } catch (Exception ignored) {}
        }


        JPanel info = new JPanel(new GridLayout(0, 1, 2, 2));
        info.setBackground(CARD);

        JLabel titleLbl  = new JLabel(r.getTitle());
        titleLbl.setFont(FONT_CARD); titleLbl.setForeground(TEXT);
        JLabel details   = new JLabel(r.getCity() + "  |  Rs." + r.getRent() + "/mo  |  " + r.getBedrooms() + "BHK  |  " + r.getRoomType());
        details.setFont(FONT_BODY); details.setForeground(SUBTEXT);
        JLabel extras    = new JLabel("Pets: " + (r.isPetsAllowed()?"✓":"✗") + "   Smoking: " + (r.isSmokingAllowed()?"✓":"✗")
                + "   Furnished: " + (r.isFurnished()?"✓":"✗") + "   From: " + r.getAvailableFrom());
        extras.setFont(FONT_SMALL); extras.setForeground(SUBTEXT);
        JLabel amenLbl   = new JLabel("Amenities: " + (r.getAmenities() != null ? r.getAmenities() : "-"));
        amenLbl.setFont(FONT_SMALL); amenLbl.setForeground(SUBTEXT);

        info.add(titleLbl); info.add(details); info.add(extras); info.add(amenLbl);
        card.add(info, BorderLayout.CENTER);

        if (showDelete) {
            JButton del = btn("Delete", DANGER);
            del.setPreferredSize(new Dimension(80, 30));
            del.addActionListener(e -> {
                int confirm = JOptionPane.showConfirmDialog(this,
                        "Delete '" + r.getTitle() + "'?", "Confirm", JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION) {
                    RoomDAO.deleteRoom(r.getId());
                    loadHostListings(listPanel);
                    NotificationService.notify("Room deleted.");
                }
            });
            card.add(del, BorderLayout.EAST);
        }
        return card;
    }

    JPanel buildHostRequestsPanel() {
        JPanel root = new JPanel(new BorderLayout(0, 10));
        root.setBackground(BG);
        root.setBorder(BorderFactory.createEmptyBorder(16, 16, 16, 16));

        JLabel hdr = new JLabel("Room Requests");
        hdr.setFont(FONT_TITLE); hdr.setForeground(ACCENT);
        root.add(hdr, BorderLayout.NORTH);

        JPanel listPanel = new JPanel();
        listPanel.setLayout(new BoxLayout(listPanel, BoxLayout.Y_AXIS));
        listPanel.setBackground(BG);

        Runnable refresh = () -> {
            listPanel.removeAll();
            List<Application> apps = ApplicationDAO.getByHost(loggedInUser.getId());
            if (apps.isEmpty()) {
                JLabel empty = new JLabel("No requests yet.");
                empty.setForeground(SUBTEXT); empty.setFont(FONT_BODY);
                listPanel.add(empty);
            } else {
                for (Application a : apps) {
                    listPanel.add(buildRequestCard(a, listPanel, true));
                    listPanel.add(Box.createVerticalStrut(10));
                }
            }
            listPanel.revalidate(); listPanel.repaint();
        };

        refresh.run();

        JButton refreshBtn = btn("Refresh", new Color(51, 65, 85));
        refreshBtn.addActionListener(e -> refresh.run());
        root.add(refreshBtn, BorderLayout.SOUTH);
        root.add(scroll(listPanel), BorderLayout.CENTER);
        return root;
    }

    JPanel buildRequestCard(Application a, JPanel listPanel, boolean showActions) {
        JPanel card = card();
        card.setLayout(new BorderLayout(10, 6));
        card.setMaximumSize(new Dimension(Integer.MAX_VALUE, 160));

        Room r = RoomDAO.getRoomById(a.getRoomId());
        User seeker = UserDAO.getUserById(a.getSeekerId());

        JPanel info = new JPanel(new GridLayout(0, 1, 2, 2));
        info.setBackground(CARD);

        Color statusColor = a.getStatus().equals("ACCEPTED") ? ACCENT2
                          : a.getStatus().equals("REJECTED") ? DANGER : WARNING;

        JLabel statusLbl = new JLabel("● " + a.getStatus());
        statusLbl.setForeground(statusColor); statusLbl.setFont(FONT_LABEL);
        JLabel roomLbl   = new JLabel("Room: " + (r != null ? r.getTitle() : "#" + a.getRoomId()));
        roomLbl.setFont(FONT_CARD); roomLbl.setForeground(TEXT);
        JLabel seekerLbl = new JLabel("Seeker: " + (seeker != null ? seeker.getName() : "?")
                + "  |  📞 " + (seeker != null ? seeker.getPhone() : "?")
                + "  |  " + (seeker != null ? seeker.getOccupation() : ""));
        seekerLbl.setFont(FONT_BODY); seekerLbl.setForeground(SUBTEXT);
        JLabel habitsLbl = new JLabel("Smoking: " + (seeker != null ? seeker.getSmokingHabit() : "?")
                + "  |  Drinking: " + (seeker != null ? seeker.getDrinkingHabit() : "?"));
        habitsLbl.setFont(FONT_SMALL); habitsLbl.setForeground(SUBTEXT);
        JLabel msgLbl    = new JLabel("Message: " + a.getMessage());
        msgLbl.setFont(FONT_SMALL); msgLbl.setForeground(SUBTEXT);
        JLabel moveLbl   = new JLabel("Move-in: " + a.getMoveInDate() + "  |  Duration: " + a.getDurationMonths() + " months");
        moveLbl.setFont(FONT_SMALL); moveLbl.setForeground(SUBTEXT);

        info.add(statusLbl); info.add(roomLbl); info.add(seekerLbl);
        info.add(habitsLbl); info.add(msgLbl);  info.add(moveLbl);
        card.add(info, BorderLayout.CENTER);

        if (showActions && a.getStatus().equals("PENDING")) {
            JPanel btnPanel = new JPanel(new GridLayout(2, 1, 4, 4));
            btnPanel.setBackground(CARD);
            JButton acc = btn("Accept", ACCENT2);
            JButton rej = btn("Reject", DANGER);
            acc.setForeground(Color.BLACK);

            acc.addActionListener(e -> {
                ApplicationDAO.updateStatus(a.getId(), "ACCEPTED");


                NotificationService.notify("Application #" + a.getId() + " ACCEPTED ✅");

                NotificationService.notifyWithDelay("Seeker notified of acceptance.", 1000);
                if (listPanel != null) buildHostRequestsPanel();
            });
            rej.addActionListener(e -> {
                ApplicationDAO.updateStatus(a.getId(), "REJECTED");
                NotificationService.notify("Application #" + a.getId() + " REJECTED ❌");
            });

            btnPanel.add(acc); btnPanel.add(rej);
            card.add(btnPanel, BorderLayout.EAST);
        }
        return card;
    }





    JPanel buildSeekerScreen() {
        JPanel root = new JPanel(new BorderLayout());
        root.setBackground(BG);

        JPanel header = buildHeader("SEEKER Dashboard  —  " + loggedInUser.getName());

        JPanel sidebar = new JPanel();
        sidebar.setLayout(new BoxLayout(sidebar, BoxLayout.Y_AXIS));
        sidebar.setBackground(CARD);
        sidebar.setPreferredSize(new Dimension(200, 0));
        sidebar.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));

        JPanel content = new JPanel(new CardLayout());
        content.setBackground(BG);
        CardLayout cl = (CardLayout) content.getLayout();

        String[] tabs = {"Browse Rooms", "Find Roommate", "My Applications", "My Profile"};
        JPanel[] panels = {
            buildBrowseRoomsPanel(),
            buildRoommatePanel(),
            buildMyApplicationsPanel(),
            buildProfilePanel(content)
        };

        for (int i = 0; i < tabs.length; i++) {
            content.add(panels[i], tabs[i]);
        }

        for (String tab : tabs) {
            JButton b = btn("  " + tab, CARD);
            b.setForeground(SUBTEXT);
            b.setFont(FONT_BODY);
            b.setMaximumSize(new Dimension(Integer.MAX_VALUE, 44));
            b.setHorizontalAlignment(SwingConstants.LEFT);
            b.addActionListener(e -> cl.show(content, tab));
            sidebar.add(b);
            sidebar.add(Box.createVerticalStrut(4));
        }

        JButton logoutBtn = btn("Logout", DANGER);
        logoutBtn.setMaximumSize(new Dimension(Integer.MAX_VALUE, 44));
        sidebar.add(Box.createVerticalGlue());
        sidebar.add(logoutBtn);
        logoutBtn.addActionListener(e -> { loggedInUser = null; showScreen(SCREEN_LOGIN); });

        root.add(header,  BorderLayout.NORTH);
        root.add(sidebar, BorderLayout.WEST);
        root.add(content, BorderLayout.CENTER);
        return root;
    }

    JPanel buildBrowseRoomsPanel() {
        JPanel root = new JPanel(new BorderLayout(0, 10));
        root.setBackground(BG);
        root.setBorder(BorderFactory.createEmptyBorder(16, 16, 16, 16));


        JPanel filters = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 5));
        filters.setBackground(BG);
        JTextField cityF    = field("City");  cityF.setPreferredSize(new Dimension(160, 34));
        JTextField maxRentF = field("Max Rent (0=any)"); maxRentF.setPreferredSize(new Dimension(160, 34));
        JButton searchBtn   = btn("Search", ACCENT);

        filters.add(lbl("City:")); filters.add(cityF);
        filters.add(lbl("Max Rent:")); filters.add(maxRentF);
        filters.add(searchBtn);
        root.add(filters, BorderLayout.NORTH);

        JPanel listPanel = new JPanel();
        listPanel.setLayout(new BoxLayout(listPanel, BoxLayout.Y_AXIS));
        listPanel.setBackground(BG);

        Runnable loadRooms = () -> {
            listPanel.removeAll();
            String city = cityF.getText().trim();
            double maxRent = 0;
            try { maxRent = Double.parseDouble(maxRentF.getText().trim()); } catch (Exception ignored) {}
            List<Room> rooms = RoomDAO.searchRooms(city, maxRent);

            if (rooms.isEmpty()) {
                JLabel empty = new JLabel("No rooms found.");
                empty.setForeground(SUBTEXT); empty.setFont(FONT_BODY);
                listPanel.add(empty);
            } else {
                for (Room r : rooms) {
                    listPanel.add(buildSeekerRoomCard(r));
                    listPanel.add(Box.createVerticalStrut(10));
                }
            }
            listPanel.revalidate(); listPanel.repaint();
        };

        searchBtn.addActionListener(e -> loadRooms.run());
        loadRooms.run();

        root.add(scroll(listPanel), BorderLayout.CENTER);
        return root;
    }

    JPanel buildSeekerRoomCard(Room r) {
        JPanel card = card();
        card.setLayout(new BorderLayout(12, 6));
        card.setMaximumSize(new Dimension(Integer.MAX_VALUE, 180));


        if (r.getImagePath() != null && !r.getImagePath().isEmpty()) {
            try {
                ImageIcon img = new ImageIcon(new ImageIcon(r.getImagePath())
                        .getImage().getScaledInstance(140, 105, Image.SCALE_SMOOTH));
                card.add(new JLabel(img), BorderLayout.WEST);
            } catch (Exception ignored) {}
        }


        JPanel info = new JPanel(new GridLayout(0, 1, 2, 2));
        info.setBackground(CARD);

        JLabel titleLbl = new JLabel(r.getTitle());
        titleLbl.setFont(FONT_CARD); titleLbl.setForeground(TEXT);
        JLabel line2 = new JLabel(r.getCity() + "  |  Rs." + r.getRent() + "/mo  |  " + r.getBedrooms() + " Bed / " + r.getBathrooms() + " Bath  |  " + r.getRoomType());
        line2.setFont(FONT_BODY); line2.setForeground(SUBTEXT);
        JLabel line3 = new JLabel("Furnished: " + (r.isFurnished()?"✓":"✗")
                + "   Pets: " + (r.isPetsAllowed()?"✓":"✗")
                + "   Smoking: " + (r.isSmokingAllowed()?"✓":"✗")
                + "   Available: " + r.getAvailableFrom());
        line3.setFont(FONT_SMALL); line3.setForeground(SUBTEXT);
        JLabel line4 = new JLabel("Amenities: " + (r.getAmenities() != null ? r.getAmenities() : "-"));
        line4.setFont(FONT_SMALL); line4.setForeground(SUBTEXT);
        JLabel descLbl = new JLabel("<html>" + r.getDescription() + "</html>");
        descLbl.setFont(FONT_SMALL); descLbl.setForeground(SUBTEXT);

        info.add(titleLbl); info.add(line2); info.add(line3); info.add(line4); info.add(descLbl);
        card.add(info, BorderLayout.CENTER);

        JButton applyBtn = btn("Apply", ACCENT);
        applyBtn.setPreferredSize(new Dimension(80, 30));
        applyBtn.addActionListener(e -> showApplyDialog(r));
        card.add(applyBtn, BorderLayout.EAST);

        return card;
    }

    void showApplyDialog(Room r) {
        JDialog dlg = new JDialog(this, "Apply for: " + r.getTitle(), true);
        dlg.setSize(420, 280);
        dlg.setLocationRelativeTo(this);
        dlg.getContentPane().setBackground(BG);
        dlg.setLayout(new GridBagLayout());

        GridBagConstraints g = new GridBagConstraints();
        g.fill = GridBagConstraints.HORIZONTAL;
        g.insets = new Insets(8, 10, 8, 10);

        JTextField moveInF = field("YYYY-MM-DD");
        JTextField durF    = field("e.g. 6");
        JTextArea  msgA    = new JTextArea(3, 20);
        msgA.setBackground(new Color(51, 65, 85));
        msgA.setForeground(TEXT); msgA.setFont(FONT_BODY);
        msgA.setLineWrap(true);

        g.gridy = 0; g.gridx = 0; dlg.add(lbl("Move-in Date"), g);
        g.gridx = 1; dlg.add(moveInF, g);
        g.gridy = 1; g.gridx = 0; dlg.add(lbl("Duration (months)"), g);
        g.gridx = 1; dlg.add(durF, g);
        g.gridy = 2; g.gridx = 0; dlg.add(lbl("Message"), g);
        g.gridx = 1; dlg.add(new JScrollPane(msgA), g);

        JButton submitBtn = btn("Submit Application", ACCENT);
        g.gridy = 3; g.gridx = 1; dlg.add(submitBtn, g);

        submitBtn.addActionListener(ev -> {
            String moveIn = moveInF.getText().trim();
            String msg    = msgA.getText().trim();
            int dur = 0;
            try { dur = Integer.parseInt(durF.getText().trim()); } catch (Exception ignored) {}
            if (msg.isEmpty()) { error(dlg, "Please write a message."); return; }

            boolean ok = ApplicationDAO.apply(loggedInUser.getId(), r.getId(), msg, moveIn, dur);
            if (ok) {
                NotificationService.notify("Application submitted! ✅");
                dlg.dispose();
            } else {
                error(dlg, "Failed to submit application.");
            }
        });

        dlg.setVisible(true);
    }

    JPanel buildRoommatePanel() {
        JPanel root = new JPanel(new BorderLayout(0, 10));
        root.setBackground(BG);
        root.setBorder(BorderFactory.createEmptyBorder(16, 16, 16, 16));

        JLabel hdr = new JLabel("Find a Roommate  —  Sorted by Compatibility");
        hdr.setFont(FONT_TITLE); hdr.setForeground(ACCENT);
        root.add(hdr, BorderLayout.NORTH);

        JPanel listPanel = new JPanel();
        listPanel.setLayout(new BoxLayout(listPanel, BoxLayout.Y_AXIS));
        listPanel.setBackground(BG);

        List<User> seekers = UserDAO.getSeekersSortedByCompatibility(loggedInUser);

        if (seekers.isEmpty()) {
            JLabel empty = new JLabel("No other seekers found.");
            empty.setForeground(SUBTEXT); empty.setFont(FONT_BODY);
            listPanel.add(empty);
        } else {
            for (User u : seekers) {
                listPanel.add(buildSeekerCard(u));
                listPanel.add(Box.createVerticalStrut(10));
            }
        }

        root.add(scroll(listPanel), BorderLayout.CENTER);
        return root;
    }

    JPanel buildSeekerCard(User u) {
        JPanel card = card();
        card.setLayout(new GridLayout(0, 1, 2, 2));
        card.setMaximumSize(new Dimension(Integer.MAX_VALUE, 140));

        int score = loggedInUser.compatibilityScore(u);
        Color scoreColor = score >= 60 ? ACCENT2 : score >= 30 ? WARNING : DANGER;

        JLabel nameLbl  = new JLabel(u.getName() + "  (" + u.getGender() + ", " + (u.getAge() > 0 ? u.getAge() + " yrs" : "?") + ")");
        nameLbl.setFont(FONT_CARD); nameLbl.setForeground(TEXT);

        JLabel scoreLbl = new JLabel("Compatibility: " + score + "%");
        scoreLbl.setFont(FONT_LABEL); scoreLbl.setForeground(scoreColor);

        JLabel contact  = new JLabel("📧 " + u.getEmail() + "   📞 " + u.getPhone());
        contact.setFont(FONT_BODY); contact.setForeground(SUBTEXT);

        JLabel habits   = new JLabel("Smoking: " + u.getSmokingHabit() + "   |   Drinking: " + u.getDrinkingHabit()
                + "   |   Occupation: " + u.getOccupation()
                + "   |   Prefers: " + u.getPreferredCity());
        habits.setFont(FONT_SMALL); habits.setForeground(SUBTEXT);

        JLabel bioLbl   = new JLabel("<html><i>" + (u.getBio() != null && !u.getBio().isEmpty() ? u.getBio() : "-") + "</i></html>");
        bioLbl.setFont(FONT_SMALL); bioLbl.setForeground(SUBTEXT);

        card.add(nameLbl); card.add(scoreLbl); card.add(contact); card.add(habits); card.add(bioLbl);
        return card;
    }

    JPanel buildMyApplicationsPanel() {
        JPanel root = new JPanel(new BorderLayout(0, 10));
        root.setBackground(BG);
        root.setBorder(BorderFactory.createEmptyBorder(16, 16, 16, 16));

        JLabel hdr = new JLabel("My Applications");
        hdr.setFont(FONT_TITLE); hdr.setForeground(ACCENT);
        root.add(hdr, BorderLayout.NORTH);

        JPanel listPanel = new JPanel();
        listPanel.setLayout(new BoxLayout(listPanel, BoxLayout.Y_AXIS));
        listPanel.setBackground(BG);

        List<Application> apps = ApplicationDAO.getBySeeker(loggedInUser.getId());
        if (apps.isEmpty()) {
            JLabel empty = new JLabel("You haven't applied anywhere yet.");
            empty.setForeground(SUBTEXT); empty.setFont(FONT_BODY);
            listPanel.add(empty);
        } else {
            for (Application a : apps) {
                listPanel.add(buildRequestCard(a, null, false));
                listPanel.add(Box.createVerticalStrut(10));
            }
        }

        root.add(scroll(listPanel), BorderLayout.CENTER);
        return root;
    }





    JPanel buildProfilePanel(JPanel parent) {
        JPanel root = new JPanel(new GridBagLayout());
        root.setBackground(BG);

        JPanel form = card();
        form.setLayout(new GridBagLayout());
        form.setPreferredSize(new Dimension(560, 540));
        GridBagConstraints g = new GridBagConstraints();
        g.fill = GridBagConstraints.HORIZONTAL;
        g.insets = new Insets(6, 8, 6, 8);

        JTextField nameF     = field(loggedInUser.getName());
        nameF.setText(loggedInUser.getName());
        JTextField phoneF    = field(loggedInUser.getPhone());
        phoneF.setText(loggedInUser.getPhone());
        JPasswordField passF = passField();
        JTextField cityF     = field(loggedInUser.getPreferredCity());
        cityF.setText(loggedInUser.getPreferredCity() != null ? loggedInUser.getPreferredCity() : "");
        JTextField occupF    = field(loggedInUser.getOccupation());
        occupF.setText(loggedInUser.getOccupation() != null ? loggedInUser.getOccupation() : "");
        JTextField ageF      = field(String.valueOf(loggedInUser.getAge()));
        ageF.setText(String.valueOf(loggedInUser.getAge()));
        JTextArea bioA       = new JTextArea(3, 20);
        bioA.setBackground(new Color(51, 65, 85));
        bioA.setForeground(TEXT); bioA.setFont(FONT_BODY);
        bioA.setText(loggedInUser.getBio() != null ? loggedInUser.getBio() : "");
        bioA.setLineWrap(true);

        JComboBox<String> smokingC  = combo("NEVER", "OCCASIONALLY", "REGULARLY");
        JComboBox<String> drinkingC = combo("NEVER", "OCCASIONALLY", "REGULARLY");
        smokingC.setSelectedItem(loggedInUser.getSmokingHabit());
        drinkingC.setSelectedItem(loggedInUser.getDrinkingHabit());


        JLabel emailLbl = new JLabel(loggedInUser.getEmail());
        emailLbl.setForeground(TEXT); emailLbl.setFont(FONT_BODY);
        JLabel roleLbl  = new JLabel(loggedInUser.getRole());
        roleLbl.setForeground(ACCENT); roleLbl.setFont(FONT_LABEL);

        JLabel passHint = new JLabel(" ");
        passHint.setFont(FONT_SMALL); passHint.setForeground(SUBTEXT);
        passF.addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent e) {
                String p = new String(passF.getPassword());
                String err = Validator.getPasswordError(p);
                if (p.isEmpty()) { passHint.setText("Leave blank to keep current password"); passHint.setForeground(SUBTEXT); return; }
                if (err == null) { passHint.setText("✓ Strong password"); passHint.setForeground(ACCENT2); }
                else             { passHint.setText("✗ " + err); passHint.setForeground(DANGER); }
            }
        });

        Object[][] rows = {
            {"Email (read-only)", emailLbl},
            {"Role",              roleLbl},
            {"Name",              nameF},
            {"Phone",             phoneF},
            {"New Password",      passF},
            {"",                  passHint},
            {"Smoking Habit",     smokingC},
            {"Drinking Habit",    drinkingC},
            {"Occupation",        occupF},
            {"Preferred City",    cityF},
            {"Age",               ageF},
            {"Bio",               new JScrollPane(bioA)},
        };

        int row = 0;
        for (Object[] r : rows) {
            if (!((String) r[0]).isEmpty()) {
                g.gridx = 0; g.gridy = row; g.weightx = 0.35;
                form.add(lbl((String) r[0]), g);
            }
            g.gridx = 1; g.gridy = row; g.weightx = 0.65;
            ((Component) r[1]).setPreferredSize(new Dimension(280, 34));
            form.add((Component) r[1], g);
            row++;
        }

        JButton saveBtn = btn("Save Profile", ACCENT);
        g.gridx = 1; g.gridy = row; form.add(saveBtn, g);

        saveBtn.addActionListener(e -> {
            String name  = nameF.getText().trim();
            String phone = phoneF.getText().trim();
            String pass  = new String(passF.getPassword()).trim();
            String city  = cityF.getText().trim();
            String occup = occupF.getText().trim();
            String bio   = bioA.getText().trim();
            String smoke = (String) smokingC.getSelectedItem();
            String drink = (String) drinkingC.getSelectedItem();
            int age = 0;
            try { age = Integer.parseInt(ageF.getText().trim()); } catch (Exception ignored) {}

            if (name.isEmpty()) { error(this, "Name cannot be empty."); return; }
            if (!phone.isEmpty() && !Validator.isValidPhone(phone)) { error(this, "Invalid phone number."); return; }


            if (!pass.isEmpty()) {
                String passErr = Validator.getPasswordError(pass);
                if (passErr != null) { error(this, passErr); return; }
            } else {
                pass = loggedInUser.getPassword();
            }

            boolean ok = UserDAO.updateProfile(loggedInUser.getId(), name, phone, pass,
                                               smoke, drink, occup, city, age, bio);
            if (ok) {

                loggedInUser.setName(name);
                loggedInUser.setPhone(phone);
                loggedInUser.setPassword(pass);
                loggedInUser.setSmokingHabit(smoke);
                loggedInUser.setDrinkingHabit(drink);
                loggedInUser.setOccupation(occup);
                loggedInUser.setPreferredCity(city);
                loggedInUser.setAge(age);
                loggedInUser.setBio(bio);
                NotificationService.notify("Profile updated! ✅");
            } else {
                error(this, "Update failed.");
            }
        });

        root.add(scroll(form));
        return root;
    }





    JPanel buildHeader(String title) {
        JPanel header = new JPanel(new BorderLayout());
        header.setBackground(CARD);
        header.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        JLabel lbl = new JLabel(" FindMyRoomie  —  " + title);
        lbl.setFont(FONT_TITLE);
        lbl.setForeground(ACCENT);
        header.add(lbl, BorderLayout.WEST);

        return header;
    }





    public static void main(String[] args) {

        try {
            UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
        } catch (Exception ignored) {}


        UIManager.put("Panel.background",      new ColorUIResource(BG));
        UIManager.put("OptionPane.background",  new ColorUIResource(CARD));
        UIManager.put("OptionPane.messageForeground", new ColorUIResource(TEXT));

        SwingUtilities.invokeLater(MainFrame::new);
    }


    static class ColorUIResource extends Color implements javax.swing.plaf.UIResource {
        public ColorUIResource(Color c) { super(c.getRGB()); }
        public ColorUIResource(int r, int g, int b) { super(r, g, b); }
    }
}

