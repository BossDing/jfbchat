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
  along with this program.  If not, see <http:// www.gnu.org/licenses/>. To change this template, choose Tools | Templates

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
  along with this program.  If not, see <http:// www.gnu.org/licenses/>. and open the template in the editor.
 ###################################################*/

package jfbchat.resources;

import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.prefs.BackingStoreException;
import java.util.prefs.Preferences;

/**
 * In this class there are stored the preferences of the chat
 *
 * Author Digitex (Giuseppe Federico digitex3d@gmail.com)
 */

public class ChatPreferences {

  private Preferences prefs;

  public ChatPreferences() {

    // This will define a node in which the preferences can be stored
    this.prefs = Preferences.userRoot().node(this.getClass().getName());

    // Init preferences
    init_prefs();

  }

  private void init_prefs() {

    // Init default value for preferences
    prefs.getBoolean(Options.INCOMING_SOUND, true);
    prefs.getBoolean(Options.SENDED_SOUND , true);
    prefs.getBoolean(Options.AUTOLOGIN, false);
    prefs.getBoolean(Options.REMEMBER_USER_AND_PASS , false);
    prefs.get(Options.PASSWORD, "");
    prefs.get(Options.USERNAME , "");
    prefs.getBoolean(Options.SHOW_EMPTY_GROUPS, false);
    prefs.getBoolean(Options.DownloadImgs , true);

    // -------------ASPECT--------------
    prefs.getBoolean(Options.SHOW_AVATARS , true);

    // -------------BEHAVIOR------------
    prefs.getBoolean(Options.SAVE_AVATARS_TO_CACHE , true);
    prefs.getBoolean(Options.SEND_TYPING_MESSAGE , true);

    // -------------NOTIFICATIONS-----------
    prefs.getBoolean(Options.NOTIFICATION_FRAME , true);
    prefs.getBoolean(Options.NOTIFICATION_FRAME_SND, true);

  }
  /**
  *
  * @return The preferences
  */
  public Preferences getPreferences() {

    return prefs;

  }

}

