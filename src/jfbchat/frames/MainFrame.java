 /* ###########################################################################
  *
  *  JFBChat it's a simple software written in Java that let you in contact
  *  with yours Facebook friends without your browser.
  *  Copyright (C) 2011  Digitex (Giuseppe Federico)
  *
  *  This program is free software: you can redistribute it and/or modify
  *  it under the terms of the GNU General Public License as published by
  *  the Free Software Foundation, either version 3 of the License, or
  *  (at your option) any later version.
  *
  * This program is distributed in the hope that it will be useful,
  * but WITHOUT ANY WARRANTY; without even the implied warranty of
  * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
  * GNU General Public License for more details.
  *
  * You should have received a copy of the GNU General Public License
  * along with this program.  If not, see <http://www.gnu.org/licenses/>.
  *
  *###########################################################################
  *
  */

package jfbchat.frames;


import javax.swing.ImageIcon;
import javax.swing.JCheckBox;

import java.awt.CardLayout;
import java.util.Iterator;

import org.jivesoftware.smack.packet.Presence;

import jfbchat.*;
import jfbchat.debug.*;
import jfbchat.resources.*;
import jfbchat.frames.preferences.PreferencesFrame;
import jfbchat.debug.Error;

/* The MainFrame of the program.
 *
 */
public class MainFrame extends javax.swing.JFrame {

    //Enter key ID
    final int K_ENTER_ID = 10;
    
    //Max window dimension
    private final int MAX_DIMENSION = 32767;
    
    //A connection with the server
    private Connection connection;

    //Icon image
    private ImageIcon mainicon;

    //Frames
    private JFrameAbout jFrameAbout;
    private PreferencesFrame preferencesFrame;
    
    private User user;

    //Here will be stocked the value of the Status Combobox
    private boolean ComboBoxChoise;

    //Chat preferences
    private ChatPreferences prefs;

    public MainFrame() {

        //Init frames
        jFrameAbout = new JFrameAbout(Application.VERSION);
        preferencesFrame = new PreferencesFrame();

        initComponents();
     

        this.prefs = new ChatPreferences();
        ComboBoxChoise = false;

        //Load and set the icon.
        try{
         
            setIconImage(new ImageIcon( getClass().getResource( Imgs.MAINICON ) ).getImage() );

        }catch(Exception e){

            new DebugMessage(this.getClass(), "Cannot load image " + new ImageIcon(Imgs.MAINICON).toString(), e);

        }

        //Init CheckBoxes
        jCheckBoxAuto.setSelected(this.prefs.getPreferences().getBoolean(Options.AUTOLOGIN, false));
        jCheckBoxRemUser.setSelected(this.prefs.getPreferences().getBoolean(Options.REMEMBER_USER_AND_PASS, false));

        //Hide the disconnect menu.
        MenuDisconnect.setVisible(false);

        //Init a new user
        //TODO: should be modified when there will be more users
        user = new User();

        //Set the minimum size the same as the scrollListpanel
        this.setMinimumSize( this.ContactListPanel.getSize() );
        
        //Show the MainFrame at the center of the screen.
        setLocationRelativeTo( null );
        setVisible(true);

        //If the option is enabled login automatically
        autologin();

    }

    private void autologin(){

        //If the user has selected autologin or remember username

        if (user.isAutoLogin()){

            EntryUser.setText(user.getSavedUser());
            EntryPass.setText(user.getSavedPass());
            loginToServer();

        }else{
            
            if ( prefs.getPreferences().getBoolean(Options.REMEMBER_USER_AND_PASS, false) ){

                //Only remember user was pressed
                EntryUser.setText(user.getSavedUser());
                EntryPass.setText(user.getSavedPass());
            }

            else{
            
                //Clear the save username and password
                this.prefs.getPreferences().put( Options.USERNAME, "");
                this.prefs.getPreferences().put( Options.PASSWORD, "");

                //Init the entryboxes
                EntryUser.setText("");
                EntryPass.setText("");
            
            }

        }
    }


    //Login procedure
    Runnable loginRunnable = new Runnable() {
     public void run() {
          //Create a new user
         
        user = new User(EntryUser.getText(),EntryPass.getText());

        //Create a new connection associated to the user
        connection = new Connection(user);

        //Connect to the server
        connection.connect();

        if (connection.isConnected()){
            
        
            //Populate the contact list
            for(Iterator<Group> iterGroup = connection.getContactList().getGroups().iterator(); iterGroup.hasNext();){

                Group nextGroup = iterGroup.next();

                ContactListPanel.add(nextGroup.getPanel());

                addContactsToPanel(nextGroup);

                 //Show the contactlist in the MainFrame
                setContactListVisible(true);

                }
            }
        
        //Init avatar user label
        avatarLabelUser.updateAvatarLabel(connection);
        avatarLabelUser.updateAvatarLabel(connection);
        //Init user name
        jLabelUser.setText(connection.getVCard().getFirstName());
        
        
        loginPanelComponentsSetEnabled(true);
     }
 };

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        MainPanel = new javax.swing.JPanel();
        LoginPanel = new javax.swing.JPanel();
        logoPanel = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        credentialsPanel = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        usernameLabel = new javax.swing.JLabel();
        EntryUser = new javax.swing.JTextField();
        questionButton = new javax.swing.JButton();
        passwordLabel = new javax.swing.JLabel();
        EntryPass = new javax.swing.JPasswordField();
        jPanel3 = new javax.swing.JPanel();
        jCheckBoxRemUser = new javax.swing.JCheckBox();
        jCheckBoxAuto = new javax.swing.JCheckBox();
        ButtonLogin = new javax.swing.JButton();
        ConnectedPanel = new javax.swing.JPanel();
        comboPanel = new javax.swing.JPanel();
        jPanelUserInfo = new javax.swing.JPanel();
        avatarLabelUser = new jfbchat.labels.AvatarLabel();
        jLabelUser = new javax.swing.JLabel();
        ComboBoxStatus = new javax.swing.JComboBox();
        jLabel5 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        ScrollListpanel = new javax.swing.JPanel();
        ContactListScrollPane = new javax.swing.JScrollPane();
        ContactListPanel = new javax.swing.JPanel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenuChat = new javax.swing.JMenu();
        MenuDisconnect = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        MenuExit = new javax.swing.JMenuItem();
        MenuEdit = new javax.swing.JMenu();
        MenuItemPreferences = new javax.swing.JMenuItem();
        MenuHelp = new javax.swing.JMenu();
        jMenuItemHelpOnline = new javax.swing.JMenuItem();
        jMenuItemReportProblem = new javax.swing.JMenuItem();
        jSeparator2 = new javax.swing.JPopupMenu.Separator();
        MenuItemAbout = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Jfbchat");
        setBounds(new java.awt.Rectangle(0, 0, 0, 0));

        MainPanel.setLayout(new java.awt.CardLayout());

        LoginPanel.setBackground(new java.awt.Color(255, 255, 255));
        LoginPanel.setName("loginPanel"); // NOI18N

        logoPanel.setBackground(new java.awt.Color(255, 255, 255));

        jLabel3.setBackground(new java.awt.Color(255, 255, 255));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/jfbchat/imgs/icon1.png"))); // NOI18N

        javax.swing.GroupLayout logoPanelLayout = new javax.swing.GroupLayout(logoPanel);
        logoPanel.setLayout(logoPanelLayout);
        logoPanelLayout.setHorizontalGroup(
            logoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, logoPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        logoPanelLayout.setVerticalGroup(
            logoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(logoPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        credentialsPanel.setLayout(new javax.swing.BoxLayout(credentialsPanel, javax.swing.BoxLayout.PAGE_AXIS));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        usernameLabel.setFont(new java.awt.Font("Verdana", 1, 16)); // NOI18N
        usernameLabel.setText("Username");

        questionButton.setFont(new java.awt.Font("Verdana", 1, 16)); // NOI18N
        questionButton.setText("?");
        questionButton.setToolTipText("Click here if you have a connection problem.");
        questionButton.setFocusable(false);
        questionButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                questionButtonMouseClicked(evt);
            }
        });

        passwordLabel.setFont(new java.awt.Font("Verdana", 1, 16)); // NOI18N
        passwordLabel.setText("Password");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(passwordLabel)
                        .addContainerGap(221, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(EntryPass, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 298, Short.MAX_VALUE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(usernameLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(EntryUser, javax.swing.GroupLayout.DEFAULT_SIZE, 270, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(questionButton)))
                        .addGap(10, 10, 10))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(usernameLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(EntryUser)
                    .addComponent(questionButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(passwordLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(EntryPass, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        credentialsPanel.add(jPanel1);

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        jCheckBoxRemUser.setBackground(new java.awt.Color(255, 255, 255));
        jCheckBoxRemUser.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        jCheckBoxRemUser.setText("Remember username and password");
        jCheckBoxRemUser.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jCheckBoxRemUserItemStateChanged(evt);
            }
        });

        jCheckBoxAuto.setBackground(new java.awt.Color(255, 255, 255));
        jCheckBoxAuto.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        jCheckBoxAuto.setText("Auto login");
        jCheckBoxAuto.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jCheckBoxAutoItemStateChanged(evt);
            }
        });

        ButtonLogin.setFont(new java.awt.Font("Verdana", 0, 15)); // NOI18N
        ButtonLogin.setText("Login");
        ButtonLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ButtonLoginActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jCheckBoxRemUser)
                    .addComponent(jCheckBoxAuto)
                    .addComponent(ButtonLogin))
                .addContainerGap(29, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jCheckBoxRemUser)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jCheckBoxAuto)
                .addGap(18, 18, 18)
                .addComponent(ButtonLogin)
                .addContainerGap(38, Short.MAX_VALUE))
        );

        credentialsPanel.add(jPanel3);

        javax.swing.GroupLayout LoginPanelLayout = new javax.swing.GroupLayout(LoginPanel);
        LoginPanel.setLayout(LoginPanelLayout);
        LoginPanelLayout.setHorizontalGroup(
            LoginPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, LoginPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(LoginPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(credentialsPanel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(logoPanel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        LoginPanelLayout.setVerticalGroup(
            LoginPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(LoginPanelLayout.createSequentialGroup()
                .addComponent(logoPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(credentialsPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        MainPanel.add(LoginPanel, "loginPanel");

        ConnectedPanel.setName("connectedPanel"); // NOI18N

        jLabelUser.setFont(new java.awt.Font("Arial", 1, 15)); // NOI18N
        jLabelUser.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabelUser.setText("USER NAME");

        javax.swing.GroupLayout jPanelUserInfoLayout = new javax.swing.GroupLayout(jPanelUserInfo);
        jPanelUserInfo.setLayout(jPanelUserInfoLayout);
        jPanelUserInfoLayout.setHorizontalGroup(
            jPanelUserInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelUserInfoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(avatarLabelUser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabelUser, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanelUserInfoLayout.setVerticalGroup(
            jPanelUserInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelUserInfoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelUserInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(avatarLabelUser, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabelUser, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        ComboBoxStatus.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Available", "Away", "Offline" }));
        ComboBoxStatus.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                ComboBoxStatusItemStateChanged(evt);
            }
        });

        jLabel5.setText("Status");

        javax.swing.GroupLayout comboPanelLayout = new javax.swing.GroupLayout(comboPanel);
        comboPanel.setLayout(comboPanelLayout);
        comboPanelLayout.setHorizontalGroup(
            comboPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(comboPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ComboBoxStatus, 0, 264, Short.MAX_VALUE)
                .addContainerGap())
            .addComponent(jPanelUserInfo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        comboPanelLayout.setVerticalGroup(
            comboPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(comboPanelLayout.createSequentialGroup()
                .addComponent(jPanelUserInfo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(comboPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ComboBoxStatus)
                    .addComponent(jLabel5))
                .addContainerGap())
        );

        jPanel2.setLayout(new java.awt.BorderLayout());

        ScrollListpanel.setLayout(new java.awt.BorderLayout());

        ContactListScrollPane.setBackground(new java.awt.Color(255, 255, 255));
        ContactListScrollPane.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        ContactListPanel.setBackground(new java.awt.Color(255, 255, 255));
        ContactListPanel.setLayout(new javax.swing.BoxLayout(ContactListPanel, javax.swing.BoxLayout.PAGE_AXIS));
        ContactListScrollPane.setViewportView(ContactListPanel);

        ScrollListpanel.add(ContactListScrollPane, java.awt.BorderLayout.CENTER);

        jPanel2.add(ScrollListpanel, java.awt.BorderLayout.CENTER);

        javax.swing.GroupLayout ConnectedPanelLayout = new javax.swing.GroupLayout(ConnectedPanel);
        ConnectedPanel.setLayout(ConnectedPanelLayout);
        ConnectedPanelLayout.setHorizontalGroup(
            ConnectedPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(comboPanel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 344, Short.MAX_VALUE)
        );
        ConnectedPanelLayout.setVerticalGroup(
            ConnectedPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ConnectedPanelLayout.createSequentialGroup()
                .addComponent(comboPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, 337, Short.MAX_VALUE))
        );

        MainPanel.add(ConnectedPanel, "connectedPanel");

        jMenuChat.setText("Chat");

        MenuDisconnect.setText("Disconnect");
        MenuDisconnect.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MenuDisconnectActionPerformed(evt);
            }
        });
        jMenuChat.add(MenuDisconnect);

        jSeparator1.setBackground(new java.awt.Color(0, 0, 0));
        jMenuChat.add(jSeparator1);

        MenuExit.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_Q, java.awt.event.InputEvent.CTRL_MASK));
        MenuExit.setText("Quit");
        MenuExit.setToolTipText("Quit");
        MenuExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MenuExitActionPerformed(evt);
            }
        });
        jMenuChat.add(MenuExit);

        jMenuBar1.add(jMenuChat);

        MenuEdit.setText("Edit");
        MenuEdit.setToolTipText("Edit");

        MenuItemPreferences.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_P, java.awt.event.InputEvent.CTRL_MASK));
        MenuItemPreferences.setText("Preferences");
        MenuItemPreferences.setToolTipText("Preferences");
        MenuItemPreferences.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MenuItemPreferencesActionPerformed(evt);
            }
        });
        MenuEdit.add(MenuItemPreferences);

        jMenuBar1.add(MenuEdit);

        MenuHelp.setText("Help");

        jMenuItemHelpOnline.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F1, 0));
        jMenuItemHelpOnline.setText("Help Online");
        jMenuItemHelpOnline.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemHelpOnlineActionPerformed(evt);
            }
        });
        MenuHelp.add(jMenuItemHelpOnline);

        jMenuItemReportProblem.setText("Report a problem");
        jMenuItemReportProblem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemReportProblemActionPerformed(evt);
            }
        });
        MenuHelp.add(jMenuItemReportProblem);
        MenuHelp.add(jSeparator2);

        MenuItemAbout.setText("About");
        MenuItemAbout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MenuItemAboutActionPerformed(evt);
            }
        });
        MenuHelp.add(MenuItemAbout);

        jMenuBar1.add(MenuHelp);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(MainPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(MainPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Login to the server
     */
    public void loginToServer(){

        String password = EntryPass.getText();
        String username = EntryUser.getText();

        if( !( password.isEmpty() && username.isEmpty()) ){
            if ( username.contains("@") ){
                //Open Options.USERNAME_ONLINE_HELP
                UtilFunctions.openURL(Options.USERNAME_ONLINE_HELP);
                new Error(this.connection, 3, "Wrong username: Please use your Facebook username");
                
            }else{
                //Disable all the LoginPanel components
                loginPanelComponentsSetEnabled(false);

                try{
                     // Write username and password to  preferences
                    this.prefs.getPreferences().put( Options.USERNAME, username);
                    this.prefs.getPreferences().put( Options.PASSWORD, password);

                    //Login to the chat
                    javax.swing.SwingUtilities.invokeLater(loginRunnable);


                }catch(Exception e){

                    new DebugMessage("ButtnLoginMouseThread: Can't invoke runnable thread");

                }
            }
        }else{

            new jfbchat.debug.Error(this.connection,1 , "Please enter an username or password");

        }


    }

   
    /**
     * Ad a group to the Contact List
     * @param A group
     */
    private void addContactsToPanel(Group contactList){
        //Populate the ContactListPanel with all the contacts of a group

        for(Iterator<Contact> iter = contactList.iterator(); iter.hasNext();){
                Contact nextContact = iter.next();
                try{

                    ContactListPanel.add(nextContact.getContactPanelbyGroup(contactList.getName()));

                }catch(Exception e){


                    new DebugMessage(this.getClass(), "Cannot add " + nextContact + " to the contactList" );
                    

                }


        }
        
        //Set the minimum size the same as the scrollListpanel
        this.setMinimumSize( new java.awt.Dimension(this.ContactListPanel.getSize().height, this.ContactListPanel.getSize().width));
        
    }
    
    /**
     * Disable all the login panel components 
     */

    public void loginPanelComponentsSetEnabled(boolean value){

        ButtonLogin.setEnabled(value);
        EntryUser.setEnabled(value);
        EntryPass.setEnabled(value);
        jCheckBoxAuto.setEnabled(value);
        jCheckBoxRemUser.setEnabled(value);
        questionButton.setEnabled(value);
        

    }



    private void MenuItemAboutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MenuItemAboutActionPerformed
        
        //Shows the jFrameAbout
        UtilFunctions.showFrame(this.jFrameAbout);

    }//GEN-LAST:event_MenuItemAboutActionPerformed

    private void ComboBoxStatusItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_ComboBoxStatusItemStateChanged
        
 	String item = evt.getItem().toString();
 	
 	if (ComboBoxChoise == false){
            //To avoid the repetition problem
            ComboBoxChoise = true;
 	}
	else{
            ComboBoxChoise = false;

            if (item.equals("Available")){

                connection.getPresence().setMode(Presence.Mode.available);
                connection.updatePresence();

            }else if(item.equals("Away")){

                connection.getPresence().setMode(Presence.Mode.away);
                connection.updatePresence();

            }else{

              
                //TODO: make the contactListPanel "addable" in order to manage more connections in the same time
                //Remove everything in the contactlist
                this.ContactListPanel.removeAll();

                connection.closeConnection();
                
                //Back to the loginPanel
                setContactListVisible(false);
                
            }
        }
    }//GEN-LAST:event_ComboBoxStatusItemStateChanged

    public void setContactListVisible(boolean visible){

        if (visible){

            //Change the card in the MainPanel
            CardLayout cl = (CardLayout)(MainPanel.getLayout());
            cl.show(MainPanel, "connectedPanel");

            MenuDisconnect.setVisible(true);
        }
        else{

            //Change the card in the MainPanel
            CardLayout cl = (CardLayout)(MainPanel.getLayout());
            cl.show(MainPanel, "loginPanel");

            MenuDisconnect.setVisible(false);
            loginPanelComponentsSetEnabled(true);

        }
    }


    

    private void MenuExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MenuExitActionPerformed
        
        try{
            if(connection.isConnected()){
                connection.closeConnection();
            }
        }
        catch (NullPointerException e){
            System.out.println("Nothing to disconnect...");
        }

        System.out.println("Stop the execution.");
        System.exit(0);

    }//GEN-LAST:event_MenuExitActionPerformed

 /**
 * Connect to the project webpage for support
 * @param evt
 */
    private void questionButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_questionButtonMouseClicked
        //Open Options.USERNAME_ONLINE_HELP
        UtilFunctions.openURL(Options.USERNAME_ONLINE_HELP);
            
    }//GEN-LAST:event_questionButtonMouseClicked

    /**
     *
     * MenuItem Disconnect Action
     *
     * @param evt
     */
    private void MenuDisconnectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MenuDisconnectActionPerformed
        
        try{

            if( connection.isConnected() ){

                connection.closeConnection();

                setContactListVisible(false);
                
                //Clear avatarLabelUser
                this.avatarLabelUser.clearIcon();
                //Clear jLabelUser
                this.jLabelUser.setText(null);
                
                this.ContactListPanel.removeAll();

            }
        }
        catch (NullPointerException e){

            new DebugMessage("MainFrame.MenuDisconnectMousePressed : " + e.getMessage() );
            
        }     
    }//GEN-LAST:event_MenuDisconnectActionPerformed
    /**
     * MenuItem Preferences Action
     * @param evt
     */
    private void MenuItemPreferencesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MenuItemPreferencesActionPerformed
        
        //Shows the preferencesFrame
        UtilFunctions.showFrame(this.preferencesFrame);

    }//GEN-LAST:event_MenuItemPreferencesActionPerformed

    /**
     * MenuItem Help Online Action
     * @param evt
     */
    private void jMenuItemHelpOnlineActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemHelpOnlineActionPerformed

        //Open the ONLINE_HELP page.
        UtilFunctions.openURL(Options.ONLINE_HELP);
        
    }//GEN-LAST:event_jMenuItemHelpOnlineActionPerformed

    private void jMenuItemReportProblemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemReportProblemActionPerformed

        //Open the Options.WEBPAGE_BUG_TRACKER page.
        UtilFunctions.openURL( Options.WEBPAGE_BUG_TRACKER );
        
    }//GEN-LAST:event_jMenuItemReportProblemActionPerformed

    /**
     * CheckBox Autologin Action Performed
     * @param evt
     */
    /**
     * ButtonLogin Action Performed
     * @param evt
     */
    private void ButtonLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ButtonLoginActionPerformed

        //Login to the server
        loginToServer();
        
    }//GEN-LAST:event_ButtonLoginActionPerformed

    /**
     * CheckBox Remember user and password state changed
     * @param evt
     */
    private void jCheckBoxRemUserItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jCheckBoxRemUserItemStateChanged

        //Get the item
        JCheckBox item = (JCheckBox) evt.getItem();

        //Change the value in the preferences with the value of the checkbox
        prefs.getPreferences().putBoolean(Options.REMEMBER_USER_AND_PASS, item.isSelected());

    }//GEN-LAST:event_jCheckBoxRemUserItemStateChanged

    /**
     * CheckBox Autologin state changed
     * @param evt
     */
    private void jCheckBoxAutoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jCheckBoxAutoItemStateChanged
         //Get the item
        JCheckBox item = (JCheckBox) evt.getItem();

        //Change the value in the preferences with the value of the checkbox
        prefs.getPreferences().putBoolean(Options.AUTOLOGIN, item.isSelected());
    }//GEN-LAST:event_jCheckBoxAutoItemStateChanged
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton ButtonLogin;
    private javax.swing.JComboBox ComboBoxStatus;
    private javax.swing.JPanel ConnectedPanel;
    private javax.swing.JPanel ContactListPanel;
    private javax.swing.JScrollPane ContactListScrollPane;
    private javax.swing.JPasswordField EntryPass;
    private javax.swing.JTextField EntryUser;
    private javax.swing.JPanel LoginPanel;
    private javax.swing.JPanel MainPanel;
    private javax.swing.JMenuItem MenuDisconnect;
    private javax.swing.JMenu MenuEdit;
    private javax.swing.JMenuItem MenuExit;
    private javax.swing.JMenu MenuHelp;
    private javax.swing.JMenuItem MenuItemAbout;
    private javax.swing.JMenuItem MenuItemPreferences;
    private javax.swing.JPanel ScrollListpanel;
    private jfbchat.labels.AvatarLabel avatarLabelUser;
    private javax.swing.JPanel comboPanel;
    private javax.swing.JPanel credentialsPanel;
    private javax.swing.JCheckBox jCheckBoxAuto;
    private javax.swing.JCheckBox jCheckBoxRemUser;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabelUser;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenu jMenuChat;
    private javax.swing.JMenuItem jMenuItemHelpOnline;
    private javax.swing.JMenuItem jMenuItemReportProblem;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanelUserInfo;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JPopupMenu.Separator jSeparator2;
    private javax.swing.JPanel logoPanel;
    private javax.swing.JLabel passwordLabel;
    private javax.swing.JButton questionButton;
    private javax.swing.JLabel usernameLabel;
    // End of variables declaration//GEN-END:variables

}
