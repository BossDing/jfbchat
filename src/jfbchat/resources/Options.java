/*###################################################
 

    JFBChat it's a simple software written in Java that let you conncted with 
    yours Facebook friends without your browser.
    Copyright (C) 2011  Digitex (Giuseppe Federico)This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program.  If not, see <http://www.gnu.org/licenses/>. To change this template, choose Tools | Templates
 

    JFBChat it's a simple software written in Java that let you conncted with 
    yours Facebook friends without your browser.
    Copyright (C) 2011  Digitex (Giuseppe Federico)This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program.  If not, see <http://www.gnu.org/licenses/>. and open the template in the editor.
 ###################################################*/

package jfbchat.resources;

/**
 *
 * Author Digitex (Giuseppe Federico digitex3d@gmail.com)
 */
public class Options {

    //---------CONNECTION-------------
    public static final int PORT = 5222;
    public static final String SERVER = "chat.facebook.com";
    public static final String SERVICE_NAME = "chat.facebook.com";

    //---------DEBUG-------------
    public static boolean DEBUG_ENABLED = true;

    //---------SYSTEM--------------
    public static String HOME_DIR = "";
    public static String USERNAME = "Username";
    public static String PASSWORD = "Password";
    public static String AUTOLOGIN = "Autologin";
    public static String REMEMBER_USER_AND_PASS = "RememberUserandPass";
    public static String CACHED_AVATARS_PATH = ".jfbchat/cache/avatars/";

    //---------ASPECT---------------
    public static String SHOW_EMPTY_GROUPS = "ShowEmptyGroups";
    public static String SHOW_AVATARS = "ShowAvatars";
    //=====----------ChatFrame-----------
    public static java.awt.Color RECEIVED_MSG_PANEL_COLOR = new java.awt.Color(255,242,161);

    //---------BEHAVIOR-------------
    public static String DownloadImgs = "DownloadImages";
    public static String SAVE_AVATARS_TO_CACHE= "SaveAvatarsToCache";

    //---------WEBPAGES--------------
    public static String PROJECT_WEBPAGE = "http://www.digisoftware.org";
    public static String ONLINE_HELP = "http://www.digisoftware.org/phpBB/";
    public static String USERNAME_ONLINE_HELP = "http://www.digisoftware.org/phpBB/viewtopic.php?f=4&t=2";
    public static String WEBPAGE_BUG_TRACKER = "http://sourceforge.net/tracker/?group_id=405668&atid=1680548";


    //---------NOTIFICATIONS-------------
    public static String INCOMING_SOUND = "IncomingSnd";
    public static String SENDED_SOUND = "SendedSnd";

}
