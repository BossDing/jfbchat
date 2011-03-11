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


import java.awt.CardLayout;
import java.awt.Desktop;

import org.jivesoftware.smack.packet.Presence;
import java.util.Iterator;
import jfbchat.*;
import jfbchat.debug.DebugMessage;
import jfbchat.resources.*;
import jfbchat.frames.preferences.PreferencesFrame;


import java.net.URI;
import java.net.URISyntaxException;

import java.io.IOException;

public class MainFrame extends javax.swing.JFrame {

    /* The MainFrame is a JFrame that manage the contact list and the login
     * panel.
     */

    private Connection connection;

    //Icon image
    private ImageIcon mainicon;

    //Other Frames
    private JFrameAbout jFrameAbout;
    private PreferencesFrame preferencesFrame;
    
    private User user;

    //Here will be stocked the value of the Status Combobox
    private boolean ComboBoxChoise;

    private ChatPreferences prefs;

    public MainFrame() {

        jFrameAbout = new JFrameAbout(Application.VERSION);
        preferencesFrame = new PreferencesFrame();
        initComponents();

        this.prefs = new ChatPreferences();
        ComboBoxChoise = false;

        try{

            //Load and set the icon.
            setIconImage(new javax.swing.ImageIcon(getClass().getResource(Imgs.MAINICON)).getImage());

        }catch(Exception e){

            new DebugMessage(this.getClass(), "Cannot load image " + new ImageIcon(Imgs.MAINICON).toString(), e);

        }

        //Init CheckBoxs
        jCheckBoxAuto.setSelected(this.prefs.getPreferences().getBoolean(Options.AUTOLOGIN, false));
        jCheckBoxRemUser.setSelected(this.prefs.getPreferences().getBoolean(Options.REMEMBER_USER_AND_PASS, false));



        //Hide the disconnect menu.
        MenuDisconnect.setVisible(false);

         user = new User();

        //Show the MainFrame at the center of the screen.
        setLocationRelativeTo( null );
        setVisible(true);

        autologin();

    }

    private void autologin(){

        //If the user has selected autologin or remember username

        if (user.isAutoLogin()){

            EntryUser.setText(user.getSavedUser());
            EntryPass.setText(user.getSavedPass());
            ButtonLoginMouseClicked(null);

        }else if((user.getSavedUser() != null)){

            //Only remember user was pressed
            EntryUser.setText(user.getSavedUser());
            EntryPass.setText(user.getSavedPass());

        }

    }

    Runnable loginRunnable = new Runnable() {
     public void run() {
          //Create a new user
         
        user = new User(EntryUser.getText(),EntryPass.getText());

        //Create a new connection associated to the user
        connection = new Connection(user);

        //Connect to the server
        connection.connect();

        if (connection.isConnected()){

            //Check if remeberuser or autologin checkbox are selected.
            checkBoxStatus();

            //Populate the contact list
            for(Iterator<Group> iterGroup = connection.getContactList().getGroups().iterator(); iterGroup.hasNext();){
                Group nextGroup = iterGroup.next();

                ContactListPanel.add(nextGroup.getPanel());

                addContactsToPanel(nextGroup);

                 //Show the contactlist in the MainFrame
                setContactListVisible(true);

                }
            }
        
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
        connectingPanel = new javax.swing.JPanel();
        animationLogo = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jProgressBar1 = new javax.swing.JProgressBar();
        ConnectedPanel = new javax.swing.JPanel();
        comboPanel = new javax.swing.JPanel();
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
        MenuItemAbout = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Jfbchat");
        setBounds(new java.awt.Rectangle(0, 0, 0, 0));
        setMinimumSize(new java.awt.Dimension(300, 520));

        MainPanel.setLayout(new java.awt.CardLayout());

        LoginPanel.setName("loginPanel"); // NOI18N

        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/jfbchat/imgs/icon1.png"))); // NOI18N

        javax.swing.GroupLayout logoPanelLayout = new javax.swing.GroupLayout(logoPanel);
        logoPanel.setLayout(logoPanelLayout);
        logoPanelLayout.setHorizontalGroup(
            logoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, logoPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, 319, Short.MAX_VALUE)
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

        usernameLabel.setFont(new java.awt.Font("Ubuntu", 1, 16));
        usernameLabel.setText("Username");

        EntryUser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EntryUserActionPerformed(evt);
            }
        });

        questionButton.setFont(new java.awt.Font("Ubuntu", 1, 16));
        questionButton.setText("?");
        questionButton.setToolTipText("Click here if you have a connection problem.");
        questionButton.setFocusable(false);
        questionButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                questionButtonMouseClicked(evt);
            }
        });

        passwordLabel.setFont(new java.awt.Font("Ubuntu", 1, 16));
        passwordLabel.setText("Password");

        EntryPass.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EntryPassActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(EntryPass, javax.swing.GroupLayout.DEFAULT_SIZE, 331, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(passwordLabel)
                        .addContainerGap(260, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(usernameLabel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(EntryUser, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 306, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(questionButton, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE))))
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

        jCheckBoxRemUser.setFont(new java.awt.Font("Ubuntu", 0, 14));
        jCheckBoxRemUser.setText("Remember username and password");
        jCheckBoxRemUser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBoxRemUserActionPerformed(evt);
            }
        });

        jCheckBoxAuto.setFont(new java.awt.Font("Ubuntu", 0, 14));
        jCheckBoxAuto.setText("Auto login");

        ButtonLogin.setText("Login");
        ButtonLogin.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ButtonLoginMouseClicked(evt);
            }
        });
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
                .addContainerGap(81, Short.MAX_VALUE))
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
                .addContainerGap(78, Short.MAX_VALUE))
        );

        credentialsPanel.add(jPanel3);

        javax.swing.GroupLayout LoginPanelLayout = new javax.swing.GroupLayout(LoginPanel);
        LoginPanel.setLayout(LoginPanelLayout);
        LoginPanelLayout.setHorizontalGroup(
            LoginPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, LoginPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(LoginPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(credentialsPanel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 343, Short.MAX_VALUE)
                    .addComponent(logoPanel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        LoginPanelLayout.setVerticalGroup(
            LoginPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(LoginPanelLayout.createSequentialGroup()
                .addComponent(logoPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(credentialsPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 319, Short.MAX_VALUE)
                .addContainerGap())
        );

        MainPanel.add(LoginPanel, "loginPanel");

        connectingPanel.setName("card3"); // NOI18N

        animationLogo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        animationLogo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/jfbchat/imgs/LogoAnimated.gif"))); // NOI18N

        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("Connecting...");

        javax.swing.GroupLayout connectingPanelLayout = new javax.swing.GroupLayout(connectingPanel);
        connectingPanel.setLayout(connectingPanelLayout);
        connectingPanelLayout.setHorizontalGroup(
            connectingPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 367, Short.MAX_VALUE)
            .addComponent(animationLogo, javax.swing.GroupLayout.DEFAULT_SIZE, 367, Short.MAX_VALUE)
            .addGroup(connectingPanelLayout.createSequentialGroup()
                .addGap(60, 60, 60)
                .addComponent(jProgressBar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(157, Short.MAX_VALUE))
        );
        connectingPanelLayout.setVerticalGroup(
            connectingPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(connectingPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(animationLogo, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jProgressBar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(275, Short.MAX_VALUE))
        );

        MainPanel.add(connectingPanel, "card3");

        ConnectedPanel.setName("connectedPanel"); // NOI18N

        ComboBoxStatus.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Available", "Away", "Offline" }));
        ComboBoxStatus.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                ComboBoxStatusItemStateChanged(evt);
            }
        });
        ComboBoxStatus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ComboBoxStatusActionPerformed(evt);
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
                .addComponent(ComboBoxStatus, 0, 287, Short.MAX_VALUE)
                .addContainerGap())
        );
        comboPanelLayout.setVerticalGroup(
            comboPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(comboPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(comboPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ComboBoxStatus, javax.swing.GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE)
                    .addComponent(jLabel5))
                .addContainerGap())
        );

        jPanel2.setLayout(new java.awt.BorderLayout());

        ScrollListpanel.setLayout(new java.awt.BorderLayout());

        ContactListScrollPane.setBackground(new java.awt.Color(255, 255, 255));
        ContactListScrollPane.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        ContactListScrollPane.setMinimumSize(new java.awt.Dimension(0, 0));

        ContactListPanel.setBackground(new java.awt.Color(255, 255, 255));
        ContactListPanel.setBorder(null);
        ContactListPanel.setAlignmentY(0.0F);
        ContactListPanel.setLayout(new javax.swing.BoxLayout(ContactListPanel, javax.swing.BoxLayout.PAGE_AXIS));
        ContactListScrollPane.setViewportView(ContactListPanel);

        ScrollListpanel.add(ContactListScrollPane, java.awt.BorderLayout.CENTER);

        jPanel2.add(ScrollListpanel, java.awt.BorderLayout.CENTER);

        javax.swing.GroupLayout ConnectedPanelLayout = new javax.swing.GroupLayout(ConnectedPanel);
        ConnectedPanel.setLayout(ConnectedPanelLayout);
        ConnectedPanelLayout.setHorizontalGroup(
            ConnectedPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(comboPanel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 367, Short.MAX_VALUE)
        );
        ConnectedPanelLayout.setVerticalGroup(
            ConnectedPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ConnectedPanelLayout.createSequentialGroup()
                .addComponent(comboPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, 431, Short.MAX_VALUE))
        );

        MainPanel.add(ConnectedPanel, "connectedPanel");

        jMenuChat.setText("Chat");

        MenuDisconnect.setText("Disconnect");
        MenuDisconnect.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                MenuDisconnectMousePressed(evt);
            }
        });
        jMenuChat.add(MenuDisconnect);

        jSeparator1.setBackground(new java.awt.Color(0, 0, 0));
        jMenuChat.add(jSeparator1);

        MenuExit.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_Q, java.awt.event.InputEvent.CTRL_MASK));
        MenuExit.setText("Quit");
        MenuExit.setToolTipText("Quit");
        MenuExit.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                MenuExitMousePressed(evt);
            }
        });
        MenuExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MenuExitActionPerformed(evt);
            }
        });
        MenuExit.addMenuKeyListener(new javax.swing.event.MenuKeyListener() {
            public void menuKeyPressed(javax.swing.event.MenuKeyEvent evt) {
            }
            public void menuKeyReleased(javax.swing.event.MenuKeyEvent evt) {
                MenuExitMenuKeyReleased(evt);
            }
            public void menuKeyTyped(javax.swing.event.MenuKeyEvent evt) {
            }
        });
        jMenuChat.add(MenuExit);

        jMenuBar1.add(jMenuChat);

        MenuEdit.setText("Edit");
        MenuEdit.setToolTipText("Edit");

        MenuItemPreferences.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_P, java.awt.event.InputEvent.CTRL_MASK));
        MenuItemPreferences.setText("Preferences");
        MenuItemPreferences.setToolTipText("Preferences");
        MenuItemPreferences.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                MenuItemPreferencesMousePressed(evt);
            }
        });
        MenuEdit.add(MenuItemPreferences);

        jMenuBar1.add(MenuEdit);

        MenuHelp.setText("Help");

        MenuItemAbout.setText("About");
        MenuItemAbout.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                MenuItemAboutMousePressed(evt);
            }
        });
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
            .addComponent(MainPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 367, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(MainPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 489, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void ButtonLoginMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ButtonLoginMouseClicked

                //Disable all the LoginPanel components
                loginPanelComponentsSetEnabled(false);

                try{
                    //Login to the chat
                    javax.swing.SwingUtilities.invokeLater(loginRunnable);
                    
                    
                }catch(Exception e){

                    new DebugMessage("ButtnLoginMouseThread: Can't invoke runnable thread");

                }

                

    }//GEN-LAST:event_ButtonLoginMouseClicked


   

    private void addContactsToPanel(Group contactList){
        //Populate the ContactListPanel with all the contacts

        for(Iterator<Contact> iter = contactList.iterator(); iter.hasNext();){
                Contact nextContact = iter.next();

                ContactListPanel.add(nextContact.getContactPanelbyGroup(contactList.getName()));


        }
    }
    
    /**
     * Disable all the login panel components except the progressbar
     */

    public void loginPanelComponentsSetEnabled(boolean value){

        ButtonLogin.setEnabled(value);
        EntryUser.setEnabled(value);
        EntryPass.setEnabled(value);
        jCheckBoxAuto.setEnabled(value);
        jCheckBoxRemUser.setEnabled(value);
        questionButton.setEnabled(value);
        

    }



    private void ButtonLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ButtonLoginActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ButtonLoginActionPerformed

    private void EntryPassActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EntryPassActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_EntryPassActionPerformed

    private void EntryUserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EntryUserActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_EntryUserActionPerformed

    private void MenuItemAboutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MenuItemAboutActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_MenuItemAboutActionPerformed

    private void MenuItemAboutMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_MenuItemAboutMousePressed
        jFrameAbout.setVisible(true);
        
    }//GEN-LAST:event_MenuItemAboutMousePressed

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


    

    private void MenuExitMenuKeyReleased(javax.swing.event.MenuKeyEvent evt) {//GEN-FIRST:event_MenuExitMenuKeyReleased
  

    }//GEN-LAST:event_MenuExitMenuKeyReleased

    private void MenuExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MenuExitActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_MenuExitActionPerformed

    private void MenuExitMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_MenuExitMousePressed
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
    }//GEN-LAST:event_MenuExitMousePressed

    private void MenuDisconnectMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_MenuDisconnectMousePressed
        try{
            if(connection.isConnected()){

                connection.closeConnection();

                setContactListVisible(false);

                this.ContactListPanel.removeAll();
            }
        }
        catch (NullPointerException e){
            new DebugMessage("MainFrame.MenuDisconnectMousePressed : " + e.getMessage() );
        }     
    }//GEN-LAST:event_MenuDisconnectMousePressed

    private void ComboBoxStatusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ComboBoxStatusActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ComboBoxStatusActionPerformed

    private void jCheckBoxRemUserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBoxRemUserActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jCheckBoxRemUserActionPerformed

 /**
 * Connect to the project webpage fr support
 * @param evt
 */
    private void questionButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_questionButtonMouseClicked
        
        Desktop desktop = Desktop.getDesktop();
        
        URI uri = null;
        try {
            uri = new URI("http://digisoftware.org");
            desktop.browse(uri);
        }
        catch(IOException ioe) {
            ioe.printStackTrace();
        }
        catch(URISyntaxException use) {
            use.printStackTrace();
        }
    }//GEN-LAST:event_questionButtonMouseClicked

    private void MenuItemPreferencesMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_MenuItemPreferencesMousePressed
        preferencesFrame.setVisible(true);
    }//GEN-LAST:event_MenuItemPreferencesMousePressed

    public void checkBoxStatus(){
        
        //Check if the remeberuser or autologin are enabled

        this.prefs.getPreferences().putBoolean(Options.REMEMBER_USER_AND_PASS, jCheckBoxRemUser.isSelected());
        this.prefs.getPreferences().putBoolean(Options.AUTOLOGIN, jCheckBoxAuto.isSelected());

        if (jCheckBoxRemUser.isSelected()){

            user.saveUserAndPass();
            
        }
        
        user.setAutologin(jCheckBoxAuto.isSelected());
        
    }
    

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
    private javax.swing.JLabel animationLogo;
    private javax.swing.JPanel comboPanel;
    private javax.swing.JPanel connectingPanel;
    private javax.swing.JPanel credentialsPanel;
    private javax.swing.JCheckBox jCheckBoxAuto;
    private javax.swing.JCheckBox jCheckBoxRemUser;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenu jMenuChat;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JProgressBar jProgressBar1;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JPanel logoPanel;
    private javax.swing.JLabel passwordLabel;
    private javax.swing.JButton questionButton;
    private javax.swing.JLabel usernameLabel;
    // End of variables declaration//GEN-END:variables

}
