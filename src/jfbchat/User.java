 /* ###########################################################################
  *
  *  JFBChat it's a simple software written in Java that let you int contact
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
  * along with this program.  If not, see <http:// www.gnu.org/licenses/>.
  *
  *###########################################################################
  *
  */

package jfbchat;

import java.io.*;

import jfbchat.resources.*;
import jfbchat.debug.*;

/**
 * Contains the informations of the user
 * @author Digitex (Giuseppe Federico - digitex3d@gmail.com)
 */

public class User {

  private String username;
  private String password;

  private ChatPreferences prefs;

  public User(String username, String password) {

  this.username = username;
  this.password = password;

  this.prefs = new ChatPreferences();

  /* Write username and password to  preferences for autologin*/
  this.prefs.getPreferences().put(Options.USERNAME, username);
  this.prefs.getPreferences().put(Options.PASSWORD, password);

  }

  public User() {

  this.username = null;
  this.password = null;
  this.prefs = new ChatPreferences();
  }

  /**
  * @return the user password
  */
  public String getPassword() {
  if (password.isEmpty()) {
    new DebugMessage(this.getClass(), "Password is empty");

  }

  return password;

  }

  /**
  *
  * @return the user username
  */
  public String getUsername() {
  if (username.isEmpty()) {
    new DebugMessage(this.getClass(), "Username is empty");

  }
  return username;
  }

  /**
  *
  *
  * @return true if the user has some avatars in the cache otherwise return false.
  */
  public boolean hasCachedAvatars() {

  // Path of the directory where cached images are saved by default.
  String cache_dir_path = Options.HOME_DIR + "/" + Options.CACHED_AVATARS_PATH + username;

  new DebugMessage(this.getClass(), " Searching for " + cache_dir_path + " for cached avatars");

  return (new File(cache_dir_path)).exists();

  }

  /**
  * Set the autologin preference
  * @param value
  */
  public void setAutologin(boolean value) {

  this.prefs.getPreferences().putBoolean(Options.AUTOLOGIN, value);
  }

  /**
  *
  * @return true if Autologin preference is enabled, otherwise return false.
  */
  public boolean isAutoLogin() {

  return this.prefs.getPreferences().getBoolean(Options.AUTOLOGIN, false);
  }

  /**
  *
  * @return the saved password.
  */
  public String getSavedPass() {

  return this.prefs.getPreferences().get(Options.PASSWORD, "");
  }

  /**
  *
  * @return the saved username.
  */
  public String getSavedUser() {

  return this.prefs.getPreferences().get(Options.USERNAME, "");
  }

  @Override
  public String toString() {
  return "Username: " + username;

  }

}

