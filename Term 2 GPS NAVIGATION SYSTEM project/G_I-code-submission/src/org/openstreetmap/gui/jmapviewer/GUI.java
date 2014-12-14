package org.openstreetmap.gui.jmapviewer;

//License: GPL. Copyright 2008 by Jan Peter Stotz

import java.awt.BorderLayout;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.swing.Timer;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.border.LineBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import org.jivesoftware.smack.XMPPConnection;
import org.openstreetmap.gui.jmapviewer.interfaces.MapMarker;
/**
 * Class for the graphical interface, including all parts for interaction
 * @author Johan and Sebastian and Ali
 */
public class GUI extends JFrame {

    static parse parser = new parse();
    static GPSInfo info = parser.getInfo();
    private static final long serialVersionUID = 1L;
    static JTextField clock = new JTextField();
    Clock clock2 = new Clock();
    public boolean isSoundOn = true;

    /**
     * enumerated type for handling time output
     */
    public enum TimeStd {
        TWELVE, TWENTYFOUR;
    }

    /**
     * enumerated type for handling distance measurement output 
     */
    public static enum Measure {
        KILO, METERS
    }

    static TimeStd timeStd = TimeStd.TWENTYFOUR;
    static Measure measure = Measure.METERS;
    JLayeredPane pane;
    protected ImageIcon routes;
    protected JButton routesButton;
    protected ImageIcon geocache;
    protected JButton geocacheButton;
    protected ImageIcon setPoint;
    protected JButton setPointButton;
    protected ImageIcon option;
    protected JButton optionButton;
    protected ImageIcon aboutImg;
    protected JButton aboutButton;
    protected ImageIcon hideImg;
    protected JButton hideButton;
    protected ImageIcon loadImg;
    protected JButton loadButton;
    protected JTextArea areaField;
    protected JTextArea areaField2;
    protected JTextArea areaField3;
    protected JTextArea areaField4;
    protected JTextArea areaField5;
    protected JTextArea areaFieldList[];
    protected ImageIcon selectedImage;
    protected ImageIcon checkImage;
    protected JCheckBox checkBox1;
    protected JCheckBox checkBox2;
    protected JCheckBox checkBox3;
    protected JCheckBox checkBox4;
    protected JCheckBox checkBox5;
    protected JCheckBox checkBox6;
    protected JCheckBox checkBox7;
    protected JCheckBox checkBox8;
    protected Container conn;
    protected ImageIcon createRouteImg;
    protected JButton createRouteButton;
    static JMapViewerExtension map;
    protected ImageIcon border;
    protected ImageIcon borderSmall;
    protected JLabel menuborder;
    protected JLabel menuborderSmall;
    protected ImageIcon infoBarImg;
    protected JLabel infoBoarder;
    protected JLayeredPane infBar;
    protected JTextArea latLabel;
    protected JTextArea distanceLabel;
    protected ImageIcon title;
    protected JLabel titleBoarder;
    protected Timer time;
    protected JLayeredPane arrowPane;
    protected ImageIcon upArrow;
    protected JButton upButton;
    protected ImageIcon downArrow;
    protected JButton downButton;
    protected ImageIcon rightArrow;
    protected JButton rightButton;
    protected ImageIcon leftArrow;
    protected JButton leftButton;
    protected ImageIcon backImg;
    protected JButton backButton;
    protected ImageIcon setPointImg;
    protected ImageIcon removePointImg;
    protected ImageIcon networkImg;
    protected JButton removePointButton;
    protected ImageIcon saveImg;
    protected JButton saveButton;
    protected static JComboBox placelist;
    protected ImageIcon optionsTitle;
    protected JLabel optionsBoarder;
    protected ImageIcon aboutTitle;
    protected JLabel aboutBoarder;
    protected ImageIcon aboutText;
    protected JLabel aboutTextBoarder;
    protected ButtonGroup group1;
    protected ButtonGroup group2;
    protected ButtonGroup group3;
    protected ButtonGroup group4;
    protected JRadioButton radio1;
    protected JRadioButton radio2;
    protected JRadioButton radio3;
    protected JRadioButton radio4;
    protected JRadioButton radio5;
    protected JRadioButton radio6;
    protected JRadioButton radio7;
    protected JRadioButton radio8;
    protected JTextField createRoute;
    protected ImageIcon enterName;
    protected JLabel enterNameLabel;
    protected static JTextField latField;
    protected static JTextField longField;
    protected static JTextField distanceTargetField;
    protected static JTextField signalStrengthField;
    protected ImageIcon showImg;
    protected JButton showButton;
    protected Route displayedPath;
    protected ImageIcon twitterImg;
    protected static String pathString;
    protected static double targetLat = 0;
    protected static double targetLon = 0;
    protected JButton netWork;
    protected JButton tweet;
    static boolean boo = false;
    static boolean boo2 = false;
    static XMLReader readXML;
    PathFinder pathfinder = new PathFinder();
    static PathStringParser pathStringParser = new PathStringParser();
    protected static MapMarker targetPoint;
//      private static Compass compass;
    protected WayPoint[] geocaches;
    protected static MapMarker[] targetPoints;
    protected static Route currentRoute;
    protected ArrayList<MapMarkerDot> geoCacheMarkers = new ArrayList<MapMarkerDot>();
    // for JCOMBOBOX
    int size = 10;
    protected static String loadFrom;

    protected static NetworkClient clientProperties = new NetworkClient();
    protected static JInternalFrame internalFrame = null;
    protected JList list;
    protected JScrollPane listScroller;
    protected JLayeredPane panel;
    protected MapMarker marker;
    protected MapMarker friendmarker;
    protected boolean targetReached;
    protected static CalculateDistance cd;
    ImageIcon reachedIcon;
    protected static ArrayList<MapMarkerDot> routeMarkers;
    protected static Route routeToSave;
    protected Route loadedRoute;
    static boolean newApi;
    static FileInputStream f;
    sound s;

    /**
     * enumerated type for handling the menustates
     */
    public enum MenuState {
        MAIN, ROUTE, START, CREATEROUTE, OPTION, ABOUT, HIDDEN, GEOCACHE, CHAT
    }

    /**
     * enumerated type for handling interest points
     *
     */
    public enum Interest {
        PARKING, ATM, DINERS, TRANSPORT;
    }

    public MenuState currentMenu;

    /**
     * Default constructor, sets up and loads the GUI and it components
     */
    public GUI() {
        super("Step By Step");
        targetReached = false;
        newApi = false;
        s = new sound();
        setUpGui();
    }

    /**
     * The method that contains the all running loop used for different purposes.
     * It will: Update the currentlocation. Your friends location if shown. The path if shown.
     * Set the interest values. Display the interestpoints if shown. Will indicate when the target
     * is reached if it is. Will display the geocache dots if any are to be displayed. Will clean up
     * after itself.
     */
    public void dot() {
        Runnable t4 = new Runnable() {
            @SuppressWarnings("static-access")
            public void run() {
                while (true) {
                    try {
                        if (marker != null)
                            map.removeMapMarker(marker);
                        marker = new MapMarkerDot(Color.red, info.getLat(), info.getLon());
                        map.addMapMarker(marker);
                        Thread.currentThread().sleep(1000);
                        latField.setText(Double.toString(info.getLat()));
                        longField.setText(Double.toString(info.getLon()));
                        String signal = info.getSignalStrength();
                        signalStrengthField.setText(signal);

                    } catch (InterruptedException e1) {
                    }

                    try {
                        if (targetLat != 0 && targetLon != 0) {
                            if (measure == Measure.METERS) {
                                if (cd.distFrom(info.getLat(), info.getLon(), targetLat, targetLon) < 20)
                                    targetReached = true;
                            } else {
                                if (cd.distFrom(info.getLat(), info.getLon(), targetLat, targetLon) < 0.02)
                                    targetReached = true;
                            }
                            if (targetReached) {
                                if (isSoundOn) {
                                    s.play(getClass().getResource("/sounds.WAV").getFile());
                                }

                                JOptionPane.showMessageDialog(null, "         Target reached!", "Congratulations!",
                                        JOptionPane.INFORMATION_MESSAGE, reachedIcon);

                                map.mapLineList.clear();
                                map.mapGeoList.clear();
                                map.mapMarkerList.clear();
                                map.removeGeoMarkers();
                                targetLat = 0;
                                targetLon = 0;
                                targetReached = false;
                            } else
                                updatePath(targetLat, targetLon);
                        } else if (routeToSave != null) {
                            updatePath(routeToSave);
                        } else if (loadedRoute != null) {
                            updatePath(loadedRoute);
                        }
                    } catch (Exception e1) {
                        e1.printStackTrace();
                    }

                    /** mahsa
                     * 
                     */
//                    try {
//                        double rotate=compass.calculateDirection(info.getLat(), info.getLon());
//                        if(rotate>0 && rotate<6.2)
//                            compass.rotateCompass(rotate);
//                    } catch (Exception e2) {
//                    } 

                }
            }
        };
        Thread dot = new Thread(t4);
        dot.start();
    }

    /**
     * author ali nazar
     * 
     * this will take the the coordinates provided by the networkClient class and it using these coordinates
     * it will plot a black dot on the map. 
     */
    public void friendDot() {
        Runnable t3 = new Runnable() {
            @SuppressWarnings("static-access")
            public void run() {
                while (true) {
                    try {
                        if (friendmarker != null)
                            map.removeMapMarker(friendmarker);
                            friendmarker = new MapMarkerDot(Color.BLACK, NetworkClient.getLatitude(), NetworkClient.getLongitude());
                        map.addMapMarker(friendmarker);
                        Thread.currentThread().sleep(1000);
                    } catch (Exception e1) {
                        e1.printStackTrace();
                    }
                }
            }
        };
        Thread friend = new Thread(t3);
        friend.start();
    }

    /**
     * Uses the {@link Geocoder} class. 
     * @param placelist
     * @param search
     *  @author Ali 
     */
    public void searchInField(JComboBox placelist, String search) {
        int listelements = placelist.getItemCount();
        int lengthOfelements = placelist.getSelectedItem().toString().length();
        String item;
        for (int i = 0; i < listelements; i++) {

            try {
                item = placelist.getItemAt(i).toString().substring(0, lengthOfelements);
                if (search.equalsIgnoreCase(item)) {
                    placelist.setPopupVisible(true);
                    placelist.setSelectedIndex(i);
                    break;

                } else if (!search.equalsIgnoreCase(item)) {
                    System.out.println("no match found");
                }
            } catch (StringIndexOutOfBoundsException ex) {
                System.out.println(ex.getLocalizedMessage());
            }

        }
    }

    /**
     * Author : ali nazar
     * in here you set the properties to connect to the jabber server, and you also provide a jabberFriend ID. 
     */

    @SuppressWarnings("unused")
    public void networkPropertiesFrame() {

        final NetworkClient client = new NetworkClient();
        JLayeredPane networkPane = new JLayeredPane();
        JLayeredPane paneInside = new JLayeredPane();

        /**
         * variables
         */
        int port = NetworkClient.getPort();
        final String user = client.getUser();
        String Friend = client.getJabberFriend();
        String connect = null;
        parse parser = new parse();
        GPSInfo info = parser.getInfo();
        /**
         */
        XMPPConnection serverConn = NetworkClient.connecting;
        if (serverConn != null) {
            connect = "true";
        } else {
            connect = "false";
        }

        internalFrame = new JInternalFrame("Set the properties", true);

        internalFrame.setOpaque(false);
        internalFrame.getContentPane().setBackground(new Color(255, 255, 255, 190));
        internalFrame.setSize(new Dimension(300, 350));
        LineBorder round = new LineBorder(Color.BLACK, 2, true);
        internalFrame.setBorder(round);
        internalFrame.setLocation(480, 34);
        networkPane.add(internalFrame, 0);
        internalFrame.add(paneInside, 0);
        /**
         * adding more components.
         */
        JLabel welcome = new JLabel("Networking", JLabel.CENTER);
        welcome.setFont(new Font("Raavi", 0, 20));
        welcome.setBounds(70, 1, 150, 30);
        paneInside.add(welcome, 0);

        JLabel socketConnection = new JLabel("Socket#: " + port, JLabel.CENTER);
        socketConnection.setBounds(5, 7, 100, 50);
        paneInside.add(socketConnection, 0);

        JLabel JabberUsername = new JLabel("Jabber User:", JLabel.CENTER);
        JabberUsername.setBounds(5, 30, 100, 50);
        paneInside.add(JabberUsername, 0);

        final JTextField userField = new JTextField(30);
        userField.setBounds(100, 40, 150, 25);
        paneInside.add(userField, 0);

        JLabel userconn = new JLabel("User: " + user, JLabel.CENTER);
        userconn.setBounds(100, 55, 100, 50);
        paneInside.add(userconn, 0);

        JLabel JabberFriendUsername = new JLabel("Jabber Friend:", JLabel.CENTER);
        JabberFriendUsername.setBounds(5, 80, 100, 50);
        paneInside.add(JabberFriendUsername, 0);

        final JTextField FrienduserField = new JTextField(30);

        FrienduserField.setBounds(100, 95, 150, 25);
        paneInside.add(FrienduserField, 0);

        JLabel Friendconn = new JLabel("Friend: " + Friend, JLabel.CENTER);
        Friendconn.setBounds(100, 110, 100, 50);
        paneInside.add(Friendconn, 0);

        JLabel connected = new JLabel("Connected ?: " + connect);
        connected.setBounds(20, 110, 150, 100);
        paneInside.add(connected, 0);

        JLabel lon = new JLabel("Longitude: " + NetworkClient.getLongitude());
        lon.setBounds(20, 130, 150, 100);
        paneInside.add(lon, 0);

        JLabel lat = new JLabel("Latitude: " + NetworkClient.getLatitude());
        lat.setBounds(20, 150, 150, 100);
        paneInside.add(lat, 0);
        /*
                JTextArea chatRoom= new JTextArea(100,100);
                chatRoom.setBounds(20,300,250,100);
                chatRoom.setBorder(BorderFactory.createLoweredBevelBorder());
                chatRoom.setEditable(false);
                paneInside.add(chatRoom,0);

                JLabel send = new JLabel("Send:");
                send.setBounds(50,380,150,100);
                paneInside.add(send,0);
        */
        ImageIcon disIcon = new ImageIcon(getClass().getResource("/disconnect.png"));
        JButton disconnectButton = new JButton(disIcon);
        disconnectButton.setBounds(220, 255, 40, 40);
        disconnectButton.setOpaque(false);
        disconnectButton.setContentAreaFilled(false);
        disconnectButton.setBorder(null);
        paneInside.add(disconnectButton, 0);
        disconnectButton.addActionListener(new ActionListener() {

            @SuppressWarnings("static-access")
            public void actionPerformed(ActionEvent arg0) {
                if (NetworkClient.connecting != null) {
                    client.disconnect();
                    internalFrame.setVisible(false);
                    internalFrame = null;

                } else {
                    internalFrame.setVisible(false);
                    internalFrame = null;
                }
            }
        });

        ImageIcon connIcon = new ImageIcon(getClass().getResource("/connect-icon.png"));
        JButton connectButton = new JButton();
        connectButton.setIcon(connIcon);
        connectButton.setBounds(220, 120, 60, 60);
        connectButton.setOpaque(false);
        connectButton.setBorder(null);
        connectButton.setContentAreaFilled(false);
        paneInside.add(connectButton, 1);
        connectButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent arg0) {
                String userproperties = userField.getText();
                String friendProperties = FrienduserField.getText();
                new NetworkClient(userproperties, friendProperties);
                friendDot();
            }
        });

        //JLabel chat = new JLabel("Chat");

        //chat.setBounds(20, 240, 150, 100);
        //paneInside.add(chat, 0);

        final JTextField sendMessage = new JTextField(30);
        sendMessage.setBounds(100, 420, 150, 25);
        sendMessage.setEditable(false);
        paneInside.add(sendMessage, 0);

        sendMessage.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent arg0) {
                String myMessage = sendMessage.getText();
                new NetworkClient(myMessage);

            }

        });

        conn.add(networkPane, 0);
        internalFrame.setVisible(true);

    }

    /**
     * for saving and loading routes inside the JComboBox
     * 
     */
    public void Save(String locationAdress) {
                PrintWriter pw = null;
                FileWriter fw;
                try {
                    fw = new FileWriter("placeinputs.txt");
                    pw = new PrintWriter(fw);
                    pw.println(locationAdress);
        
                } catch (IOException e) {
                    System.out.println(e.getLocalizedMessage());
                }
                pw.close();
    }

    /**
     * displays the current state of the menu. First calls the undisplay() method
     * then switches the currentState attribute and displays the new state.
     * @param current
     *              the current state of the menu, of enumerated type MenuState
     * @param previous
     *              the previous state of the menu, of enumerated type MenuState, 
     *              used for the back and hide/show functionality
     */
    @SuppressWarnings("unused")
    public void displayMenu(MenuState current, final MenuState previous) {

        undisplayMenu(); // we test this position first, before the change of currentMenu, to see how that works out

        currentMenu = current;

        switch (currentMenu) {
        case MAIN: // do all the stuff needed to display the objects unique for main menu (intialize, set all bounds, add to layered pane etc etc)

            //TransBoarder
            border = new ImageIcon(getClass().getResource("/menuborder.png"));
            menuborder = new JLabel(border);
            border = null;
            menuborder.setVisible(true);
            menuborder.setBounds(0, 0, 226, 514);
            pane.add(menuborder, -1);

            //ARROWS POSITION
            upButton.setBounds(390, 10, 38, 38);
            downButton.setBounds(390, 510, 38, 38);
            leftButton.setBounds(10, 278, 38, 38);
            rightButton.setBounds(732, 278, 38, 38);

            //Hide Button
            hideImg = new ImageIcon(getClass().getResource("/hidebutton.png"));
            hideButton = new JButton(hideImg);
            hideImg = null;
            hideButton.setBorder(null);
            hideButton.setContentAreaFilled(false);
            hideButton.setToolTipText("Hides the Menupanel for bigger map view");
            hideButton.setVisible(true);
            hideButton.setBounds(66, 494, 93, 18);
            pane.add(hideButton, 0);

            hideButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    displayMenu(MenuState.HIDDEN, currentMenu);
                }
            });

            //routesbutton
            routes = new ImageIcon(getClass().getResource("/routesbutton.png"));
            routesButton = new JButton(routes);
            routes = null;
            routesButton.setBorder(null);
            routesButton.setContentAreaFilled(false);
            routesButton.setVisible(true);
            routesButton.setToolTipText("Opens Route panel for displaying and creating routes");
            routesButton.setBounds(18, 100, 86, 29);
            pane.add(routesButton, 0);

            routesButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    displayMenu(MenuState.ROUTE, currentMenu);
                }
            });

            //Geocache button
            geocache = new ImageIcon(getClass().getResource("/geocachesmenu.png"));
            geocacheButton = new JButton(geocache);
            geocache = null;
            geocacheButton.setBorder(null);
            geocacheButton.setContentAreaFilled(false);
            geocacheButton.setVisible(true);
            geocacheButton.setToolTipText("Opens Filechooser for loc file");
            geocacheButton.setBounds(122, 100, 86, 29);
            geocacheButton.addActionListener(new ActionListener() {
                @SuppressWarnings("static-access")
                public void actionPerformed(ActionEvent e) {
                    int check = readXML.fileChoose();
                    if (check == 0)
                        displayMenu(MenuState.GEOCACHE, currentMenu);
                }
            });
            pane.add(geocacheButton, 0);

            //Set point button
            setPoint = new ImageIcon(getClass().getResource("/setpointbutton.png"));
            setPointButton = new JButton(setPoint);
            setPoint = null;
            setPointButton.setBorder(null);
            setPointButton.setContentAreaFilled(false);
            setPointButton.setVisible(true);
            setPointButton.setToolTipText("Sets a point on the map, displays path from current location");
            setPointButton.setBounds(18, 135, 86, 29);
            pane.add(setPointButton, 0);

            setPointButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    boo = true;
                }
            });

            //Option button
            option = new ImageIcon(getClass().getResource("/optionsbutton.png"));
            optionButton = new JButton(option);
            option = null;
            optionButton.setBorder(null);
            optionButton.setContentAreaFilled(false);
            optionButton.setVisible(true);
            optionButton.setToolTipText("Open Option Panel");
            optionButton.setBounds(122, 135, 86, 29);
            optionButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    displayMenu(MenuState.OPTION, currentMenu);
                }
            });
            pane.add(optionButton, 0);

            //About Button

            aboutImg = new ImageIcon(getClass().getResource("/aboutbutton.png"));
            aboutButton = new JButton(aboutImg);
            aboutImg = null;
            aboutButton.setBorder(null);
            aboutButton.setContentAreaFilled(false);
            aboutButton.setVisible(true);
            aboutButton.setToolTipText("Project Information");
            aboutButton.setBounds(80, 450, 65, 29);
            pane.add(aboutButton, 0);
            aboutButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    displayMenu(MenuState.ABOUT, currentMenu);
                }
            });

            //checkboxes and titles
            areaField = new JTextArea("Off\nRestaurants\nParking\nAtm's\nTram & Buss");
            areaField.setBounds(50, 275, 85, 125);
            areaField.setFont(new Font("raavi", Font.BOLD, 15));
            areaField.setVisible(true);
            areaField.setEditable(false);
            areaField.setOpaque(false);
            pane.add(areaField, 0);

            group1 = new ButtonGroup();

            checkImage = new ImageIcon(getClass().getResource("/checkbox.png"));
            selectedImage = new ImageIcon(getClass().getResource("/selectedcheckbox.png"));

            //radio1 OFF
            radio1 = new JRadioButton();
            radio1.setIcon(checkImage);
            radio1.setSelectedIcon(selectedImage);
            radio1.setVisible(true);
            radio1.setOpaque(false);
            radio1.setSelected(true);
            radio1.setBounds(150, 275, 28, 26);
            group1.add(radio1);
            pane.add(radio1, 0);

            radio1.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    if (MapInterestPoint.interestLabelList != null)
                        map.removeInterestPoints();
                    map.mapInterestPointList.clear();
                }
            });

            //radio2 Restaurants
            radio2 = new JRadioButton();
            radio2.setIcon(checkImage);
            radio2.setSelectedIcon(selectedImage);
            radio2.setVisible(true);
            radio2.setOpaque(false);
            radio2.setBounds(150, 301, 28, 26);
            group1.add(radio2);
            pane.add(radio2, 0);

            radio2.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    if (MapInterestPoint.interestLabelList != null)
                        map.removeInterestPoints();
                    map.mapInterestPointList.clear();
                    showPlaces(Interest.DINERS);
                }
            });

            //radio3 Parking
            radio3 = new JRadioButton();
            radio3.setIcon(checkImage);
            radio3.setSelectedIcon(selectedImage);
            radio3.setVisible(true);
            radio3.setOpaque(false);
            radio3.setSelected(true);
            radio3.setBounds(150, 327, 28, 26);
            group1.add(radio3);
            pane.add(radio3, 0);

            radio3.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    if (MapInterestPoint.interestLabelList != null)
                        map.removeInterestPoints();
                    map.mapInterestPointList.clear();
                    showPlaces(Interest.PARKING);

                }
            });

            //radio4 Atm
            radio4 = new JRadioButton();
            radio4.setIcon(checkImage);
            radio4.setSelectedIcon(selectedImage);
            radio4.setVisible(true);
            radio4.setOpaque(false);
            radio4.setBounds(150, 353, 28, 26);
            group1.add(radio4);
            pane.add(radio4, 0);

            radio4.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    if (MapInterestPoint.interestLabelList != null)
                        map.removeInterestPoints();
                    map.mapInterestPointList.clear();
                    showPlaces(Interest.ATM);
                }
            });
            // Transport
            radio5 = new JRadioButton();
            radio5.setIcon(checkImage);
            radio5.setSelectedIcon(selectedImage);
            radio5.setVisible(true);
            radio5.setOpaque(false);
            radio5.setBounds(150, 379, 28, 26);
            group1.add(radio5);
            pane.add(radio5, 0);

            radio5.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    if (MapInterestPoint.interestLabelList != null)
                        map.removeInterestPoints();
                    map.mapInterestPointList.clear();
                    showPlaces(Interest.TRANSPORT);
                }
            });

            //Network
            networkImg = new ImageIcon(getClass().getResource("/networkicon.png"));
            netWork = new JButton(networkImg);
            networkImg = null;
            netWork.setContentAreaFilled(false);
            netWork.setBorder(null);
            netWork.setToolTipText("Chat with a friend also using Step by Step");
            netWork.setVisible(true);
            netWork.setBounds(134, 180, 50, 50);
            pane.add(netWork, 0);
            final NetworkClient client = new NetworkClient();

            netWork.addActionListener(new ActionListener() {

                public void actionPerformed(ActionEvent e) {
                    if (internalFrame == null || internalFrame.isClosed()) {
                        NetworkClient.SocketConnection();
                        networkPropertiesFrame();
                    } else {
                        JOptionPane.showMessageDialog(null, "The window you are trying to open is already open.");
                    }

                }
            });

            //tweet
            twitterImg = new ImageIcon(getClass().getResource("/twitterbutton.png"));
            tweet = new JButton(twitterImg);
            twitterImg = null;
            tweet.setBorder(null);
            tweet.setContentAreaFilled(false);
            tweet.setVisible(true);
            tweet.setToolTipText("Send Message and your location to Twitter");
            tweet.setBounds(42, 180, 50, 50);
            pane.add(tweet, 0);

            tweet.addActionListener(new ActionListener() {

                public void actionPerformed(ActionEvent e) {
                    TweetBox tweetbox = new TweetBox();
                }
            });

            String places[] = { "Enter Address, City" };
            placelist = new JComboBox(places);
            Load();
            placelist.setEditable(true);
            placelist.setFont(new Font("raavi", Font.BOLD, 15));
            placelist.setBounds(22, 60, 180, 30);
            placelist.setToolTipText("Search for a address(e.g. Diagonalen 5, Gothenburg");
            pane.add(placelist, 0);
            placelist.addActionListener(new ActionListener() {

                @SuppressWarnings("static-access")
                public void actionPerformed(ActionEvent e) {
                    try {
                        map.mapMarkerList.clear();
                        targetLat =
                                Double.parseDouble(Geocoder.getLocation(placelist.getSelectedItem().toString()).lat);
                        targetLon =
                                Double.parseDouble(Geocoder.getLocation(placelist.getSelectedItem().toString()).lon);
                        targetPoint = new MapMarkerDot(Color.blue, targetLat, targetLon);
                        map.addMapMarker(targetPoint);
                        map.mapLineList.clear();
                        readXML.geocacheList = null;
                        map.mapGeoList.clear();
                        map.removeGeoMarkers();
                        java.lang.Runtime.getRuntime().gc();
                        updatePath(targetLat, targetLon);
                        repaint();
                        // SAVING THE INPUTTED Addresses into a file.
                        String LocationName = placelist.getSelectedItem().toString();

                        if (placelist.getItemAt(0) != LocationName
                                && placelist.getItemAt(placelist.getItemCount()) != LocationName) {
                            Save(LocationName);
                            placelist.insertItemAt(LocationName, 0);
                        } else {
                            //Prevented duplication
                        }

                    } catch (IOException e1) {
                        e1.printStackTrace();
                    } catch (Exception e2) {
                    }

                    JComboBox placelist = (JComboBox) e.getSource();
                    //                    searchInField(placelist, placelist.getSelectedItem().toString());

                }
            });

            break;
        case ROUTE:
            //TransBoarder
            border = new ImageIcon(getClass().getResource("/menuborder.png"));
            menuborder = new JLabel(border);
            border = null;
            menuborder.setVisible(true);
            menuborder.setBounds(0, 0, 226, 514);
            pane.add(menuborder, -1);

            //ARROWS POSITION
            upButton.setBounds(390, 10, 38, 38);
            downButton.setBounds(390, 510, 38, 38);
            leftButton.setBounds(10, 278, 38, 38);
            rightButton.setBounds(732, 278, 38, 38);

            //Hide Button
            hideImg = new ImageIcon(getClass().getResource("/hidebutton.png"));
            hideButton = new JButton(hideImg);
            hideImg = null;
            hideButton.setBorder(null);
            hideButton.setContentAreaFilled(false);
            hideButton.setVisible(true);
            hideButton.setToolTipText("Hides the menupanel for bigger map view");
            hideButton.setBounds(66, 494, 93, 18);
            pane.add(hideButton, 0);

            hideButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    displayMenu(MenuState.HIDDEN, currentMenu);
                }
            });

            // JComboBox 

            RouteParser parse = new RouteParser();
            final Route[] routeList = parse.getRoutes();
            placelist = new JComboBox(routeList);
            placelist.setEditable(false);
            placelist.setFont(new Font("raavi", Font.BOLD, 15));
            placelist.setBounds(22, 60, 180, 30);
            placelist.setOpaque(true);
            placelist.setToolTipText("Select a pre-planned from the list");
            placelist.setBackground(new Color(255, 255, 255, 255));
            pane.add(placelist, 0);
            placelist.addActionListener(new ActionListener() {

                public void actionPerformed(ActionEvent e) {
                    JComboBox placelist = (JComboBox) e.getSource();
                    searchInField(placelist, placelist.getSelectedItem().toString());

                    loadedRoute = routeList[placelist.getSelectedIndex()];
                    map.mapMarkerList.clear();
                    map.mapLineList.clear();
                    map.mapGeoList.clear();
                    map.removeGeoMarkers();
                    targetLat = 0;
                    targetLon = 0;
                    map.addMapMarker(new MapMarkerDot(Color.blue, routeList[placelist.getSelectedIndex()].wayPoints
                            .get(routeList[placelist.getSelectedIndex()].wayPoints.size() - 1).getLat(),
                            routeList[placelist.getSelectedIndex()].wayPoints.get(
                                    routeList[placelist.getSelectedIndex()].wayPoints.size() - 1).getLong()));
                    map.addMapMarker(new MapMarkerDot(Color.blue, routeList[placelist.getSelectedIndex()].wayPoints
                            .get(0).getLat(), routeList[placelist.getSelectedIndex()].wayPoints.get(0).getLong()));
                }
            });
            placelist.addKeyListener(new KeyListener() {

                public void keyTyped(KeyEvent e) {
                    JComboBox placelist = (JComboBox) e.getSource();
                    searchInField(placelist, placelist.getSelectedItem().toString());
                }

                public void keyReleased(KeyEvent e) {
                    searchInField(placelist, placelist.getSelectedItem().toString());
                }

                public void keyPressed(KeyEvent e) {
                    JComboBox placelist = (JComboBox) e.getSource();
                    searchInField(placelist, placelist.getSelectedItem().toString());

                }
            });

            createRouteImg = new ImageIcon(getClass().getResource("/createroutebutton.png"));
            createRouteButton = new JButton(createRouteImg);
            createRouteImg = null;
            createRouteButton.setBorder(null);
            createRouteButton.setToolTipText("Create a new Route yourself");
            createRouteButton.setContentAreaFilled(false);
            createRouteButton.setVisible(true);
            createRouteButton.setBounds(18, 100, 98, 29);

            createRouteButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    map.mapMarkerList.clear();
                    map.mapGeoList.clear();
                    map.mapLineList.clear();
                    map.removeGeoMarkers();
                    targetLat = 0;
                    targetLon = 0;
                    routeMarkers = new ArrayList<MapMarkerDot>();
                    routeToSave = new Route();
                    displayMenu(MenuState.CREATEROUTE, currentMenu);
                }
            });
            pane.add(createRouteButton, 0);

            backImg = new ImageIcon(getClass().getResource("/backbutton.png"));
            backButton = new JButton(backImg);
            backImg = null;
            backButton.setBorder(null);
            backButton.setContentAreaFilled(false);
            backButton.setVisible(true);
            backButton.setBounds(84, 450, 57, 29);

            backButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    displayMenu(MenuState.MAIN, currentMenu);
                }
            });

            pane.add(backButton, 0);
            break;
        case CREATEROUTE: // do all the stuff needed to display the objects uniqe for geocache menu

            //TransBoarder
            border = new ImageIcon(getClass().getResource("/menuborder.png"));
            menuborder = new JLabel(border);
            border = null;
            menuborder.setVisible(true);
            menuborder.setBounds(0, 0, 226, 514);
            pane.add(menuborder, -1);

            //ARROWS POSITION
            upButton.setBounds(390, 10, 38, 38);
            downButton.setBounds(390, 510, 38, 38);
            leftButton.setBounds(10, 278, 38, 38);
            rightButton.setBounds(732, 278, 38, 38);

            //Hide Button
            hideImg = new ImageIcon(getClass().getResource("/hidebutton.png"));
            hideButton = new JButton(hideImg);
            hideImg = null;
            hideButton.setBorder(null);
            hideButton.setContentAreaFilled(false);
            hideButton.setVisible(true);
            hideButton.setToolTipText("Hides the menupanel for bigger map view");
            hideButton.setBounds(66, 494, 93, 18);
            pane.add(hideButton, 0);

            hideButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    displayMenu(MenuState.HIDDEN, currentMenu);
                }
            });

            createRoute = new JTextField("enter route name");
            createRoute.setBounds(22, 60, 180, 30);
            createRoute.setFont(new Font("raavi", Font.BOLD, 15));
            createRoute.setVisible(true);
            createRoute.setEditable(true);
            createRoute.setToolTipText("Enter name for your new route");
            createRoute.setOpaque(true);
            pane.add(createRoute, 0);

            backImg = new ImageIcon(getClass().getResource("/backbutton.png"));
            backButton = new JButton(backImg);
            backImg = null;
            backButton.setBorder(null);
            backButton.setContentAreaFilled(false);
            backButton.setVisible(true);
            backButton.setBounds(84, 450, 57, 29);

            backButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    if (routeMarkers.size() > 1) {
                        int choice = JOptionPane.showConfirmDialog(null, "Proceed without saving changes?");
                        if (choice == JOptionPane.YES_OPTION) {
                            map.mapLineList.clear();
                            map.mapMarkerList.clear();
                            displayMenu(MenuState.ROUTE, currentMenu);
                        }
                        if (choice == JOptionPane.NO_OPTION) {
                        }
                        if (choice == JOptionPane.CANCEL_OPTION) {
                        }
                        if (choice == JOptionPane.CLOSED_OPTION) {
                        }
                    } else {
                        displayMenu(MenuState.ROUTE, currentMenu);
                    }
                }
            });
            pane.add(backButton, 0);

            setPointImg = new ImageIcon(getClass().getResource("/setpointbutton.png"));
            setPointButton = new JButton(setPointImg);
            setPointImg = null;
            setPointButton.setBorder(null);
            setPointButton.setToolTipText("Sets a new point on the map");
            setPointButton.setContentAreaFilled(false);
            setPointButton.setVisible(true);
            setPointButton.setBounds(15, 100, 86, 29);
            setPointButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    boo2 = true;
                }
            });

            pane.add(setPointButton, 0);

            removePointImg = new ImageIcon(getClass().getResource("/removepointbutton.png"));
            removePointButton = new JButton(removePointImg);
            removePointImg = null;
            removePointButton.setBorder(null);
            removePointButton.setToolTipText("Removes a point on the map");
            removePointButton.setContentAreaFilled(false);
            removePointButton.setVisible(true);
            removePointButton.setBounds(108, 100, 103, 29);
            removePointButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    if (routeMarkers.size() > 0) {
                        map.removeMapMarker(routeMarkers.get(routeMarkers.size() - 1));
                        routeMarkers.remove(routeMarkers.size() - 1);
                        int i = routeToSave.wayPoints.size() - 1;
                        if (routeMarkers.size() - 1 > -1) {
                            while (routeMarkers.get(routeMarkers.size() - 1).lat != routeToSave.wayPoints.get(i).latitude
                                    && routeMarkers.get(routeMarkers.size() - 1).lon != routeToSave.wayPoints.get(i).longitude) {
                                routeToSave.wayPoints.set(i, null);
                                routeToSave.wayPoints.remove(i);
                                i--;
                            }
                        }
                    }
                }
            });
            pane.add(removePointButton, 0);

            saveImg = new ImageIcon(getClass().getResource("/savebutton.png"));
            saveButton = new JButton(saveImg);
            saveImg = null;
            saveButton.setBorder(null);
            saveButton.setContentAreaFilled(false);
            saveButton.setVisible(true);
            saveButton.setToolTipText("Saves your route to route list");
            saveButton.setBounds(76, 136, 73, 29);
            saveButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    if (createRoute.getText().equalsIgnoreCase("enter route name")) {
                        JOptionPane.showMessageDialog(null, "Please enter route name", "Warning",
                                JOptionPane.WARNING_MESSAGE);
                    } else {
                        routeToSave.createAndSaveRoute(createRoute.getText());
                        routeToSave.wayPoints.clear();
                        routeMarkers.clear();
                        map.mapLineList.clear();
                        map.mapMarkerList.clear();
                    }
                }
            });
            pane.add(saveButton, 0);
            break;
        case OPTION:

            //TransBoarder
            border = new ImageIcon(getClass().getResource("/menuborder.png"));
            menuborder = new JLabel(border);
            border = null;
            menuborder.setVisible(true);
            menuborder.setBounds(0, 0, 226, 514);
            pane.add(menuborder, -1);

            //ARROWS POSITION
            upButton.setBounds(390, 10, 38, 38);
            downButton.setBounds(390, 510, 38, 38);
            leftButton.setBounds(10, 278, 38, 38);
            rightButton.setBounds(732, 278, 38, 38);

            //Hide Button
            hideImg = new ImageIcon(getClass().getResource("/hidebutton.png"));
            hideButton = new JButton(hideImg);
            hideImg = null;
            hideButton.setBorder(null);
            hideButton.setContentAreaFilled(false);
            hideButton.setVisible(true);
            hideButton.setToolTipText("Hides the menupanel for bigger map view");
            hideButton.setBounds(66, 494, 93, 18);
            pane.add(hideButton, 0);

            hideButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    displayMenu(MenuState.HIDDEN, currentMenu);
                }
            });

            optionsTitle = new ImageIcon(getClass().getResource("/optionstext.png"));
            optionsBoarder = new JLabel(optionsTitle);
            optionsTitle = null;
            optionsBoarder.setVisible(true);
            optionsBoarder.setBounds(74, 70, 77, 22);
            pane.add(optionsBoarder, 0);

            backImg = new ImageIcon(getClass().getResource("/backbutton.png"));
            backButton = new JButton(backImg);
            backImg = null;
            backButton.setBorder(null);
            backButton.setContentAreaFilled(false);
            backButton.setVisible(true);
            backButton.setBounds(84, 450, 57, 29);

            backButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    displayMenu(MenuState.MAIN, currentMenu);
                }
            });
            pane.add(backButton, 0);

            areaField = new JTextArea("Measuring Standard\n\nSound\n\nTime Standard\n\nPathfinding");
            areaField.setBounds(30, 110, 200, 300);
            areaField.setFont(new Font("raavi", Font.BOLD, 20));
            areaField.setVisible(true);
            areaField.setEditable(false);
            areaField.setOpaque(false);
            pane.add(areaField, 0);

            areaField2 = new JTextArea("Meters\nKilometers");
            areaField2.setBounds(50, 137, 200, 50);
            areaField2.setFont(new Font("raavi", Font.BOLD, 15));
            areaField2.setVisible(true);
            areaField2.setEditable(false);
            areaField2.setOpaque(false);
            pane.add(areaField2, 0);

            areaField3 = new JTextArea("On\nOff");
            areaField3.setBounds(50, 209, 200, 50);
            areaField3.setFont(new Font("raavi", Font.BOLD, 15));
            areaField3.setVisible(true);
            areaField3.setEditable(false);
            areaField3.setOpaque(false);
            pane.add(areaField3, 0);

            areaField4 = new JTextArea("12 hours\n24 hours");
            areaField4.setBounds(50, 287, 200, 50);
            areaField4.setFont(new Font("raavi", Font.BOLD, 15));
            areaField4.setVisible(true);
            areaField4.setEditable(false);
            areaField4.setOpaque(false);
            pane.add(areaField4, 0);

            areaField5 = new JTextArea("New\nOld");
            areaField5.setBounds(50, 362, 200, 50);
            areaField5.setFont(new Font("raavi", Font.BOLD, 15));
            areaField5.setVisible(true);
            areaField5.setEditable(false);
            areaField5.setOpaque(false);
            pane.add(areaField5, 0);

            group1 = new ButtonGroup();
            group2 = new ButtonGroup();
            group3 = new ButtonGroup();
            group4 = new ButtonGroup();

            checkImage = new ImageIcon(getClass().getResource("/checkbox.png"));
            selectedImage = new ImageIcon(getClass().getResource("/selectedcheckbox.png"));
            //radio1 Meters
            radio1 = new JRadioButton();
            radio1.setIcon(checkImage);
            radio1.setSelectedIcon(selectedImage);
            radio1.setVisible(true);
            radio1.setOpaque(false);
            radio1.setSelected(true);
            radio1.setBounds(150, 137, 28, 26);
            group1.add(radio1);
            pane.add(radio1, 0);

            radio1.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    measure = Measure.METERS;
                    repaint();
                }
            });

            //radio2 Kilometers
            radio2 = new JRadioButton();
            radio2.setIcon(checkImage);
            radio2.setSelectedIcon(selectedImage);
            radio2.setVisible(true);
            radio2.setOpaque(false);
            radio2.setBounds(150, 163, 28, 26);
            group1.add(radio2);
            pane.add(radio2, 0);

            radio2.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    measure = Measure.KILO;
                    repaint();
                }
            });

            if (measure == Measure.METERS)
                group1.setSelected(radio1.getModel(), true);
            else
                group1.setSelected(radio2.getModel(), true);

            //radio3 On
            radio3 = new JRadioButton();
            radio3.setIcon(checkImage);
            radio3.setSelectedIcon(selectedImage);
            radio3.setVisible(true);
            radio3.setOpaque(false);
            radio3.setSelected(true);
            radio3.setBounds(150, 209, 28, 26);
            group2.add(radio3);
            pane.add(radio3, 0);

            radio3.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    isSoundOn = true;
                }
            });

            //radio4 Off
            radio4 = new JRadioButton();
            radio4.setIcon(checkImage);
            radio4.setSelectedIcon(selectedImage);
            radio4.setVisible(true);
            radio4.setOpaque(false);
            radio4.setBounds(150, 235, 28, 26);
            group2.add(radio4);
            pane.add(radio4, 0);

            radio4.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    isSoundOn = false;
                }
            });

            if (isSoundOn)
                group2.setSelected(radio3.getModel(), true);
            else
                group2.setSelected(radio4.getModel(), true);

            //radio5 12Hours
            radio5 = new JRadioButton();
            radio5.setIcon(checkImage);
            radio5.setSelectedIcon(selectedImage);
            radio5.setVisible(true);
            radio5.setOpaque(false);
            radio5.setBounds(150, 287, 28, 26);
            group3.add(radio5);
            pane.add(radio5, 0);

            radio5.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    timeStd = TimeStd.TWELVE;
                    clock2.setClock();
                }
            });

            //radio6 24hours
            radio6 = new JRadioButton();
            radio6.setIcon(checkImage);
            radio6.setSelectedIcon(selectedImage);
            radio6.setVisible(true);
            radio6.setOpaque(false);
            radio6.setSelected(true);
            radio6.setBounds(150, 313, 28, 26);
            group3.add(radio6);
            pane.add(radio6, 0);

            radio6.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    timeStd = TimeStd.TWENTYFOUR;
                    clock2.setClock();
                }
            });

            if (timeStd == TimeStd.TWELVE)
                group3.setSelected(radio5.getModel(), true);
            else
                group3.setSelected(radio6.getModel(), true);

            //radio7 New Pathfinding
            radio7 = new JRadioButton();
            radio7.setIcon(checkImage);
            radio7.setSelectedIcon(selectedImage);
            radio7.setVisible(true);
            radio7.setOpaque(false);
            radio7.setSelected(true);
            radio7.setBounds(150, 362, 28, 26);
            group4.add(radio7);
            pane.add(radio7, 0);

            radio7.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    newApi = true;
                }
            });

            //radio8 Old Pathfinding
            radio8 = new JRadioButton();
            radio8.setIcon(checkImage);
            radio8.setSelectedIcon(selectedImage);
            checkImage = null;
            selectedImage = null;
            radio8.setVisible(true);
            radio8.setOpaque(false);
            radio8.setSelected(true);
            radio8.setBounds(150, 388, 28, 26);
            group4.add(radio8);
            pane.add(radio8, 0);

            radio8.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    newApi = false;
                }
            });

            if (newApi)
                group4.setSelected(radio7.getModel(), true);
            else
                group4.setSelected(radio8.getModel(), true);

            break;

        case ABOUT:

            //TransBoarder
            border = new ImageIcon(getClass().getResource("/menuborder.png"));
            menuborder = new JLabel(border);
            border = null;
            menuborder.setVisible(true);
            menuborder.setBounds(0, 0, 226, 514);
            pane.add(menuborder, -1);

            //ARROWS POSITION
            upButton.setBounds(390, 10, 38, 38);
            downButton.setBounds(390, 510, 38, 38);
            leftButton.setBounds(10, 278, 38, 38);
            rightButton.setBounds(732, 278, 38, 38);

            //Hide Button
            hideImg = new ImageIcon(getClass().getResource("/hidebutton.png"));
            hideButton = new JButton(hideImg);
            hideImg = null;
            hideButton.setBorder(null);
            hideButton.setContentAreaFilled(false);
            hideButton.setVisible(true);
            hideButton.setToolTipText("Hides the menupanel for bigger map view");
            hideButton.setBounds(66, 494, 93, 18);
            pane.add(hideButton, 0);

            hideButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    displayMenu(MenuState.HIDDEN, currentMenu);
                }
            });

            aboutTitle = new ImageIcon(getClass().getResource("/abouttext.png"));
            aboutBoarder = new JLabel(aboutTitle);
            aboutTitle = null;
            aboutBoarder.setVisible(true);
            aboutBoarder.setBounds(74, 70, 77, 22);
            pane.add(aboutBoarder, 0);

            backImg = new ImageIcon(getClass().getResource("/backbutton.png"));
            backButton = new JButton(backImg);
            backImg = null;
            backButton.setBorder(null);
            backButton.setContentAreaFilled(false);
            backButton.setVisible(true);
            backButton.setBounds(84, 450, 57, 29);

            backButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    displayMenu(MenuState.MAIN, currentMenu);
                }
            });
            pane.add(backButton, 0);

            aboutText = new ImageIcon(getClass().getResource("/abouttext2.png"));
            aboutTextBoarder = new JLabel(aboutText);
            aboutText = null;
            aboutTextBoarder.setVisible(true);
            aboutTextBoarder.setBounds(33, 150, 159, 245);
            pane.add(aboutTextBoarder, 0);
            break;
        case HIDDEN:
            borderSmall = new ImageIcon(getClass().getResource("/closedborder.png"));
            menuborderSmall = new JLabel(borderSmall);
            borderSmall = null;
            menuborderSmall.setVisible(true);
            menuborderSmall.setBounds(0, 0, 226, 65);
            pane.add(menuborderSmall, -1);

            //ARROWS POSITION
            upButton.setBounds(493, 10, 38, 38);
            downButton.setBounds(493, 510, 38, 38);
            leftButton.setBounds(10, 278, 38, 38);
            rightButton.setBounds(976, 278, 38, 38);

            showImg = new ImageIcon(getClass().getResource("/showbutton.png"));
            showButton = new JButton(showImg);
            showImg = null;
            showButton.setBorder(null);
            showButton.setContentAreaFilled(false);
            showButton.setVisible(true);
            showButton.setToolTipText("Displays the menupanel");
            showButton.setBounds(66, 45, 93, 18);
            pane.add(showButton, 0);

            showButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    displayMenu(previous, currentMenu);
                }
            });

            break;
        case GEOCACHE:
            //TransBoarder
            border = new ImageIcon(getClass().getResource("/menuborder.png"));
            menuborder = new JLabel(border);
            border = null;
            menuborder.setVisible(true);
            menuborder.setBounds(0, 0, 226, 514);
            pane.add(menuborder, -1);

            //ARROWS POSITION
            upButton.setBounds(390, 10, 38, 38);
            downButton.setBounds(390, 510, 38, 38);
            leftButton.setBounds(10, 278, 38, 38);
            rightButton.setBounds(732, 278, 38, 38);

            //Hide Button
            hideImg = new ImageIcon(getClass().getResource("/hidebutton.png"));
            hideButton = new JButton(hideImg);
            hideImg = null;
            hideButton.setBorder(null);
            hideButton.setContentAreaFilled(false);
            hideButton.setToolTipText("Hides the menupanel for bigger map view");
            hideButton.setVisible(true);
            hideButton.setBounds(66, 494, 93, 18);
            pane.add(hideButton, 0);

            hideButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    displayMenu(MenuState.HIDDEN, currentMenu);
                }
            });

            geocaches = XMLReader.geoList;
            list = new JList(geocaches);
            list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
            list.setLayoutOrientation(JList.VERTICAL);
            list.setToolTipText("Select a geocache to display path from current location");
            list.setVisibleRowCount(-1);
            list.setSize(new Dimension(150, 250));

            listScroller = new JScrollPane(list);
            listScroller.setBounds(819, 130, 150, 250);
            panel = new JLayeredPane();
            panel.add(listScroller, 0);
            conn.add(panel, 0);

            areaField = new JTextArea("Geocaches");
            areaField.setBounds(66, 57, 95, 30);
            areaField.setFont(new Font("raavi", Font.BOLD, 20));
            areaField.setVisible(true);
            areaField.setEditable(false);
            areaField.setOpaque(false);
            pane.add(areaField, 0);

            areaField2 = new JTextArea("Select a geogache to\ndisplay and navigate it");
            areaField2.setBounds(40, 360, 150, 100);
            areaField2.setFont(new Font("raavi", Font.BOLD, 15));
            areaField2.setVisible(true);
            areaField2.setEditable(false);
            areaField2.setOpaque(false);
            pane.add(areaField2, 0);

            list.addListSelectionListener(new ListSelectionListener() {
                public void valueChanged(ListSelectionEvent evt) {
                    map.mapLineList.clear();
                    map.mapMarkerList.clear();
                    int selected = list.getSelectedIndex();
                    targetLat = geocaches[selected].getLat();
                    targetLon = geocaches[selected].getLong();
                    map.addMapGeocacheDot(new MapGeocacheDot(targetLat, targetLon));
                    updatePath(targetLat, targetLon);
                    repaint();
                }
            });

            checkImage = null;
            selectedImage = null;

            backImg = new ImageIcon(getClass().getResource("/backbutton.png"));
            backButton = new JButton(backImg);
            backImg = null;
            backButton.setBorder(null);
            backButton.setContentAreaFilled(false);
            backButton.setVisible(true);
            backButton.setBounds(84, 450, 57, 29);

            backButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    displayMenu(MenuState.MAIN, currentMenu);
                }
            });

            pane.add(backButton, 0);
            break;
        case CHAT:
            //
            border = new ImageIcon(getClass().getResource("/menuborder.png"));
            menuborder = new JLabel(border);
            border = null;
            menuborder.setVisible(true);
            menuborder.setBounds(0, 0, 226, 514);
            pane.add(menuborder, -1);

        }
        repaint();
    }

    /**
     * 
     * Author: Ali Nazar 
     *  Loads the text file containing the user inputted adresses.
     */
    public static void Load() {
        BufferedReader load = null;
        DataInputStream d;
        // JComboBox 
        d = new DataInputStream(f);
        load = new BufferedReader(new InputStreamReader(d));
                try {
                    while ((loadFrom = load.readLine()) != null) {
                        placelist.insertItemAt(loadFrom, 0);
                    }
                } catch (IOException e3) {

                }
                try {
                    load.close();
                } catch (IOException e3) {
                }
    }

    /**
     * Undisplays the components to be undisplayed. Sets them to not visible, then
     * to null. At last it calls the garbage collector, making sure that anything not visble
     * is not allocated in the memory.
     */
    public void undisplayMenu() {

        switch (currentMenu) {
        case MAIN: // set all visual objects unique for main menu to null (this will work since this method will be called before the currentMenu is changed)
            menuborder.setVisible(false);
            menuborder = null;
            hideButton.setVisible(false);
            hideButton = null;
            placelist.setVisible(false);
            placelist = null;
            routesButton.setVisible(false);
            routesButton = null;
            geocacheButton.setVisible(false);
            geocacheButton = null;
            setPointButton.setVisible(false);
            setPointButton = null;
            optionButton.setVisible(false);
            optionButton = null;
            aboutButton.setVisible(false);
            aboutButton = null;
            areaField.setVisible(false);
            areaField = null;
            radio1.setVisible(false);
            radio1 = null;
            radio2.setVisible(false);
            radio2 = null;
            radio3.setVisible(false);
            radio3 = null;
            radio4.setVisible(false);
            radio4 = null;
            radio5.setVisible(false);
            radio5 = null;
            netWork.setVisible(false);
            netWork = null;
            tweet.setVisible(false);
            tweet = null;
            java.lang.Runtime.getRuntime().gc(); //this comes last just before break
            break;
        case ROUTE: // set all visual objects unique for route menu to null
            menuborder.setVisible(false);
            menuborder = null;
            hideButton.setVisible(false);
            hideButton = null;
            createRouteButton.setVisible(false);
            createRouteButton = null;
            backButton.setVisible(false);
            backButton = null;
            placelist.setVisible(false);
            placelist = null;
            loadedRoute = null;
            java.lang.Runtime.getRuntime().gc(); //this comes last just before break
            break;
        case CREATEROUTE: // set all visual objects unique for createroute to null
            menuborder.setVisible(false);
            menuborder = null;
            hideButton.setVisible(false);
            hideButton = null;
            setPointButton.setVisible(false);
            setPointButton = null;
            removePointButton.setVisible(false);
            removePointButton = null;
            saveButton.setVisible(false);
            saveButton = null;
            createRoute.setVisible(false);
            createRoute = null;
            backButton.setVisible(false);
            backButton = null;
            routeMarkers = null;
            routeToSave = null;
            map.mapMarkerList.clear();
            //                enterNameLabel.setVisible(false);
            //                enterNameLabel = null;
            java.lang.Runtime.getRuntime().gc(); //this comes last just before break
            break;
        case OPTION: // set all visual objects unique for createroute to null
            menuborder.setVisible(false);
            menuborder = null;
            hideButton.setVisible(false);
            hideButton = null;
            backButton.setVisible(false);
            backButton = null;
            areaField.setVisible(false);
            areaField = null;
            areaField2.setVisible(false);
            areaField2 = null;
            areaField3.setVisible(false);
            areaField3 = null;
            areaField4.setVisible(false);
            areaField4 = null;
            areaField5.setVisible(false);
            areaField5 = null;
            optionsBoarder.setVisible(false);
            optionsBoarder = null;
            radio1.setVisible(false);
            radio1 = null;
            radio2.setVisible(false);
            radio2 = null;
            radio3.setVisible(false);
            radio3 = null;
            radio4.setVisible(false);
            radio4 = null;
            radio5.setVisible(false);
            radio5 = null;
            radio6.setVisible(false);
            radio6 = null;
            radio7.setVisible(false);
            radio7 = null;
            radio8.setVisible(false);
            radio8 = null;
            java.lang.Runtime.getRuntime().gc(); //this comes last just before break
            break;

        case ABOUT: // set all visual objects uniqe for createroute to null
            menuborder.setVisible(false);
            menuborder = null;
            hideButton.setVisible(false);
            hideButton = null;
            aboutBoarder.setVisible(false);
            aboutBoarder = null;
            backButton.setVisible(false);
            backButton = null;
            aboutTextBoarder.setVisible(false);
            aboutTextBoarder = null;

            java.lang.Runtime.getRuntime().gc(); //this comes last just before break
            break;
        case HIDDEN:
            menuborderSmall.setVisible(false);
            menuborderSmall = null;
            showButton.setVisible(false);
            showButton = null;

            java.lang.Runtime.getRuntime().gc(); //this comes last just before break
            break;

        case GEOCACHE:
            menuborder.setVisible(false);
            menuborder = null;
            hideButton.setVisible(false);
            hideButton = null;
            backButton.setVisible(false);
            backButton = null;
            list.setVisible(false);
            listScroller.setVisible(false);
            panel.setVisible(false);
            areaField.setVisible(false);
            areaField = null;
            areaField2.setVisible(false);
            areaField2 = null;
            list = null;
            listScroller = null;
            panel = null;
            java.lang.Runtime.getRuntime().gc(); //this comes last just before break
            break;
        }

    }

    /**
     * @return
     *          the map
     */
    public static JMapViewerExtension getMap() {
        return map;
    }

    /**
     * sets the map
     * @param map
     *          the given map
     */
    public static void setMap(JMapViewerExtension map) {
        GUI.map = map;
    }

    /**
     * updates the path from current location to the given point.
     * removes the previous marker, adds the new one. gets the path from
     * current location to target and creates a route currentRoute from it.
     * Paints over the old lines, then removes the old lines. 
     * Calculates the distance of the route and displays it according to the standard.
     * @param lat
     *          the given latitude
     * @param lon
     *          the given longitude
     */
    public static void updatePath(double lat, double lon) {
        map.removeMapMarker(targetPoint);
        targetPoint = new MapMarkerDot(Color.BLUE, lat, lon);
        map.addMapMarker(targetPoint);
        try {
            pathString = PathFinder.getPath(info.getLat(), info.getLon(), lat, lon, newApi);
        } catch (Exception e) {
            e.printStackTrace();
        }
        currentRoute = pathStringParser.parsePathString(pathString);
        currentRoute.addHead(new WayPoint(info.getLat(), info.getLon(), 0));
        currentRoute.addPoint(new WayPoint(lat, lon, currentRoute.wayPoints.size()));
        int i;
        for (i = 0; i < currentRoute.wayPoints.size() - 1; i++) {
            if (map.mapLineList.size() < i + 1)
                map.addMapLine(new MapLine2(currentRoute.getPoint(i), currentRoute.getPoint(i + 1)), i);
            else
                map.mapLineList.set(i, new MapLine2(currentRoute.getPoint(i), currentRoute.getPoint(i + 1)));
        }
        for (int j = i; j < map.getMapLineList().size(); j++) {
            map.mapLineList.remove(j);
        }
        cd = new CalculateDistance();
        if (measure == Measure.METERS) {
            distanceTargetField.setText(cd.calculate(currentRoute) + " Meters");
        } else {
            distanceTargetField.setText(cd.calculate(currentRoute) + " Km");
        }

    }

    /**
     * updates the path by the given route.
     * works like the over updatepath method, but is not as heavy due to the lack
     * of updating need (due to its not connected to the current location).
     * @param route
     *          the given route. 
     */
    public static void updatePath(Route route) {
        int i;
        for (i = 0; i < route.wayPoints.size() - 1; i++) {
            if (map.mapLineList.size() < i + 1)
                map.addMapLine(new MapLine2(route.getPoint(i), route.getPoint(i + 1)), i);
            else
                map.mapLineList.set(i, new MapLine2(route.getPoint(i), route.getPoint(i + 1)));
        }
        for (int j = i; j < map.getMapLineList().size(); j++) {
            map.mapLineList.remove(j);
        }
    }

    /**
     * Sets up the GUI at startup.
     */
    public void setUpGui() {
        try {
                    f = new FileInputStream("placeinputs.txt");
        } catch (Exception e) {
            e.printStackTrace();

        }
        reachedIcon = new ImageIcon(getClass().getResource("/reached.png"));

        //Map
        map = new JMapViewerExtension();
        setLayout(new BorderLayout());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setResizable(false);

        // Transparent Panel
        conn = super.getContentPane();
        pane = new JLayeredPane();
        pane.setBounds(780, 30, 226, 514);
        pane.setPreferredSize(new Dimension(226, 514));
        currentMenu = MenuState.START;

        //infobar
        infoBarImg = new ImageIcon(getClass().getResource("/infobar.png"));
        infoBoarder = new JLabel(infoBarImg);
        infoBarImg = null;
        infoBoarder.setVisible(true);
        infoBoarder.setBounds(0, 0, 1024, 44);

        //infobar
        infBar = new JLayeredPane();
        infBar.setPreferredSize(new Dimension(1024, 44));
        infBar.setBounds(0, 556, 1024, 44);
        infBar.add(infoBoarder, -1);

        //Latitude and longitude labels.
        latLabel = new JTextArea("Latitude:\nLongitude:");
        latLabel.setFont(new Font("raavi", Font.BOLD, 12));
        latLabel.setVisible(true);
        latLabel.setOpaque(false);
        latLabel.setEditable(false);
        latLabel.setBounds(5, 2, 70, 40);
        infoBoarder.add(latLabel);

        latField = new JTextField();
        latField.setFont(new Font("raavi", Font.BOLD, 12));
        latField.setVisible(true);
        latField.setOpaque(false);
        latField.setBorder(null);
        latField.setEditable(false);
        latField.setBounds(60, 2, 50, 20);
        infoBoarder.add(latField, 0);
        //Waiting for long value
        longField = new JTextField();
        longField.setFont(new Font("raavi", Font.BOLD, 12));
        longField.setVisible(true);
        longField.setBorder(null);
        longField.setOpaque(true);
        longField.setEditable(false);
        longField.setBounds(60, 23, 50, 20);
        infoBoarder.add(longField, 0);

        // distance & signal Label
        distanceLabel = new JTextArea("Distance to target: \nSignal Strength: ");
        distanceLabel.setFont(new Font("raavi", Font.BOLD, 12));
        distanceLabel.setVisible(true);
        distanceLabel.setBorder(null);
        distanceLabel.setOpaque(false);
        distanceLabel.setEditable(false);
        distanceLabel.setBounds(420, 2, 100, 50);
        infoBoarder.add(distanceLabel, 0);

        //Waiting for meters value
        distanceTargetField = new JTextField();
        distanceTargetField.setFont(new Font("raavi", Font.BOLD, 12));
        distanceTargetField.setVisible(true);
        distanceTargetField.setBorder(null);
        distanceTargetField.setOpaque(true);
        distanceTargetField.setEditable(false);
        distanceTargetField.setBounds(520, 2, 100, 20);
        infoBoarder.add(distanceTargetField, 0);
        //Waiting for meters value
        signalStrengthField = new JTextField();
        signalStrengthField.setFont(new Font("raavi", Font.BOLD, 12));
        signalStrengthField.setVisible(true);
        signalStrengthField.setBorder(null);
        signalStrengthField.setOpaque(true);
        signalStrengthField.setEditable(false);
        signalStrengthField.setBounds(520, 23, 50, 20);
        infoBoarder.add(signalStrengthField, 0);

        //TextField for clock
        clock.setFont(new Font("raavi", Font.BOLD, 15));
        clock.setVisible(true);
        clock.setOpaque(false);
        clock.setBorder(null);
        clock.setEditable(false);
        clock.setBounds(950, 2, 70, 40);
        time = new Timer(1000, new Clock());
        time.start();
        infoBoarder.add(clock, 0);

        //titletext
        title = new ImageIcon(getClass().getResource("/titletext.png"));
        titleBoarder = new JLabel(title);
        title = null;
        titleBoarder.setVisible(true);
        titleBoarder.setBounds(48, 15, 131, 22);
        pane.add(titleBoarder, 0);

        //Navigation Arrows
        //LayeredPane for arrows
        arrowPane = new JLayeredPane();
        arrowPane.setBounds(0, 0, 1024, 600);
        arrowPane.setPreferredSize(new Dimension(1024, 600));

        //Up arrow
        upArrow = new ImageIcon(getClass().getResource("/uparrow.png"));
        upButton = new JButton(upArrow);
        upArrow = null;
        upButton.setBounds(390, 10, 38, 38);
        upButton.setContentAreaFilled(false);
        upButton.setBorder(null);
        arrowPane.add(upButton, -1);

        upButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                map.moveMap(0, -100);

            }
        });
        upButton.addMouseListener(new MouseAdapter() {

            public void mousePressed(MouseEvent e) {
                upButton.setIcon(new ImageIcon(getClass().getResource("/uparrowpressed.png")));
            }

            public void mouseReleased(MouseEvent e) {
                upButton.setIcon(new ImageIcon(getClass().getResource("/uparrow.png")));
            }
        });

        //Down Arrow
        downArrow = new ImageIcon(getClass().getResource("/downarrow.png"));
        downButton = new JButton(downArrow);
        downArrow = null;
        downButton.setBounds(390, 510, 38, 38);
        downButton.setContentAreaFilled(false);
        downButton.setBorder(null);
        arrowPane.add(downButton, -1);

        downButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                map.moveMap(0, 100);

            }
        });
        downButton.addMouseListener(new MouseAdapter() {

            public void mousePressed(MouseEvent e) {
                downButton.setIcon(new ImageIcon(getClass().getResource("/downarrowpressed.png")));
            }

            public void mouseReleased(MouseEvent e) {
                downButton.setIcon(new ImageIcon(getClass().getResource("/downarrow.png")));
            }
        });

        //Left Arrow
        leftArrow = new ImageIcon(getClass().getResource("/leftarrow.png"));
        leftButton = new JButton(leftArrow);
        leftArrow = null;
        leftButton.setBounds(10, 278, 38, 38);
        leftButton.setContentAreaFilled(false);
        leftButton.setBorder(null);
        arrowPane.add(leftButton, -1);

        leftButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                map.moveMap(-100, 0);

            }
        });
        leftButton.addMouseListener(new MouseAdapter() {

            public void mousePressed(MouseEvent e) {
                leftButton.setIcon(new ImageIcon(getClass().getResource("/leftarrowpressed.png")));
            }

            public void mouseReleased(MouseEvent e) {
                leftButton.setIcon(new ImageIcon(getClass().getResource("/leftarrow.png")));
            }
        });

        //Right Arrow
        rightArrow = new ImageIcon(getClass().getResource("/rightarrow.png"));
        rightButton = new JButton(rightArrow);
        rightArrow = null;
        rightButton.setBounds(732, 278, 38, 38);
        rightButton.setContentAreaFilled(false);
        rightButton.setBorder(null);
        arrowPane.add(rightButton, -1);

        rightButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                map.moveMap(100, 0);

            }
        });

        rightButton.addMouseListener(new MouseAdapter() {

            public void mousePressed(MouseEvent e) {
                rightButton.setIcon(new ImageIcon(getClass().getResource("/rightarrowpressed.png")));
            }

            public void mouseReleased(MouseEvent e) {
                rightButton.setIcon(new ImageIcon(getClass().getResource("/rightarrow.png")));
            }
        });

//          compass = new Compass(0, 0);
//           arrowPane.add(compass);

        displayMenu(MenuState.MAIN, MenuState.MAIN);
        conn.add(pane);
        conn.add(arrowPane);
        conn.add(infBar);

        add(map, BorderLayout.CENTER);

        super.pack();
        super.setVisible(true);
    }

    /**
     * Gets the interest points, according to the given value of the enum variabel interest.
     * Gets the points only in the displayed area of the map. 
     * @param interest
     *          the given Interest value;
     *          PARKING
     *          DINERS
     *          TRANSPORT
     *          ATM
     */
    void showPlaces(Interest interest) {
        double lat1 = map.getPosition(30, 30).getLat();
        double lon1 = map.getPosition(30, 30).getLon();
        double lat2 = map.getPosition(950, 550).getLat();
        double lon2 = map.getPosition(950, 550).getLon();
        String tag = new String();
        switch (interest) {
        case PARKING:
            tag = "parking";
            break;
        case DINERS:
            tag = "bar";
            break;
        case TRANSPORT:
            tag = "bus_stop";
            break;
        case ATM:
            tag = "atm";
            break;

        }
        Explorer ex = new Explorer();
        try {
            String s = ex.getXML(lat2, lon1, lat1, lon2, tag);
            ObjectParser op = new ObjectParser();
            ArrayList<MapObject> o = new ArrayList<MapObject>();
            o = op.Parse(s);

            for (int i = 0; i < o.size(); i++) {
                map.addMapInterestPoint(new MapInterestPoint(o.get(i).getLat(), o.get(i).getLon(), interest));
            }
        } catch (Exception exc) {
            System.out.println(exc.getLocalizedMessage());
        }
    }
}
