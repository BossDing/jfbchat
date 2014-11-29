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

package jfbchat;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import jfbchat.debug.DebugMessage;
import jfbchat.resources.ChatPreferences;
import jfbchat.resources.Options;
import org.jivesoftware.smack.XMPPException;
import org.jivesoftware.smackx.packet.VCard;

/**
 *
 * Author Digitex (Giuseppe Federico support@digisoftware.org)
 */
public class MyVCard {

  private VCard vCard;

  private ImageIcon avatar;
  private Contact contact;
  private Connection connection;
  private String cache_dir_path;
  private ChatPreferences prefs;

  public MyVCard(Connection connection, Contact contact) {
   this.vCard = new VCard();
   this.contact = contact;
   this.connection = connection;
   //The path where avatars are saved for this user
   cache_dir_path = Options.HOME_DIR + "/" + Options.CACHED_AVATARS_PATH + this.connection.getUser().getUsername() + "/";
   this.prefs = new ChatPreferences();

  }

  public MyVCard() {

   this.vCard = new VCard();

  }

  //Constructor for the user's vCard
  public MyVCard(Connection connection) {
  this.connection = connection;
  this.vCard = new VCard();

   //The path where avatars are saved for this user
   cache_dir_path = Options.HOME_DIR + "/" + Options.CACHED_AVATARS_PATH + this.connection.getUser().getUsername() + "/";

   this.prefs = new ChatPreferences();

  }

  /**
   * If the contact is saved in the cache directory, load it , if not
   * load the VCard and the avatar of the contact in a temp byte array and store it
   * in avatar as ImageIcon
   */
  private void loadAvatar() {
  File tmp_image;
  String jID = null;

  if (this.contact != null) {
    //The contact JID associated to CONTACT the avatar.
    jID = this.contact.getJID();
  }

  //If the avatar exists in the cached avatars directory
  if (new File(cache_dir_path + jID + ".jpg").exists()) {

     //Load the avatar from the cache directory
    load_cached_avatar(jID);

  } else {

    //if DownloadImgs option is enabled
    if (prefs.getPreferences().getBoolean(Options.DownloadImgs, true)) {
    byte[] avatarBytes;

    //Load the vCard associated to the contact or user
    try {
      if (this.contact != null) {
      //The vCard associated to the CONTACT
      vCard.load(connection.getConnection(), contact.getAdress());

      } else {
      //The vCard associated to the USER
      vCard.load(connection.getConnection());

      }

    }catch (XMPPException e) {
      new DebugMessage(this.getClass(), "Couldn't load the VCard of " + contact.getUser(), e);

    }

    //Download the avatar and init the instance variable
    try {

      avatarBytes = vCard.getAvatar();
      this.avatar = new ImageIcon(avatarBytes);

      //Save avatars to a local directory if the option is enabled
      if (this.prefs.getPreferences().getBoolean(Options.SAVE_AVATARS_TO_CACHE, true)) {

      saveAvatarToCache();

      }

    }catch (Exception e) {

      new DebugMessage(this.getClass(), "Couldn't load the Avatar of " + contact.getUser(), e);

    }
    }
  }
  }

  //TODO: if the image changes the avatar loaded is still the avatar from the cache
  /**
   * Load the avatar associated to the id in the vCard
   * @param id of the avatar to load in the vCard
   */
  public void load_cached_avatar(String formatted_name) {

  File image = null;
  byte[] avatarBytes = null;

  try {

    image = new File(cache_dir_path + formatted_name + ".jpg");
    if (image.exists()) {

    avatarBytes = getBytesFromFile(image);
    this.avatar = new ImageIcon(avatarBytes);

    } else {

    new DebugMessage(this.getClass(),"Cannot load " + cache_dir_path + formatted_name + ".jpg the image does not exist.");

    }

  }catch (Exception e) {

    new DebugMessage(this.getClass(),"Cannot load "  + cache_dir_path + formatted_name +  ".jpg" , e);

  }

  }

  /**
   * Save the contact associated avatar to the local cache directory
   */
  public void saveAvatarToCache() {

  //If the cache directory does not exist
  if (! (this.connection.getUser().hasCachedAvatars())) {

    // Create multiple directories
    boolean success = (new File(cache_dir_path)).mkdirs();

    if (!success) {

    new DebugMessage(this.getClass(), "Cannot create " + cache_dir_path);

    }

  }

    String formatted_name = this.contact.getJID();

    Image img = this.avatar.getImage();

    BufferedImage bi = new BufferedImage(img.getWidth(null), img.getHeight(null),
           BufferedImage.TYPE_INT_RGB);

    Graphics2D g2 = bi.createGraphics();

    // Draw img into bi so we can write it to file.
    g2.drawImage(img, 0, 0, null);
    g2.dispose();

    try {

    ImageIO.write(bi, "jpg", new File(cache_dir_path + formatted_name + ".jpg"));

    }catch (Exception e) {

    new DebugMessage(this.getClass(), "Cannot save " + cache_dir_path + formatted_name + ".jpg", e);

  }

  }

  /**
  * Return the avatar of the VCard if is already loaded, otherwise try to load
  * it
  * @return An ImageIcon or null
  */
  public ImageIcon getAvatar() {
  //Test if the avatar is already loaded.
  if (this.avatar != null) {
    return this.avatar;

  } else {
    //Load the avatar
    loadAvatar();
    //Test if the avatar is loaded.
    if (this.avatar != null) {
    return this.avatar;

    }
  }
  return null;

  }

  public String getNickName() {
  return this.vCard.getNickName();

  }

  public String getFirstName() {
  String fn = this.vCard.toXML().replaceFirst("^.*<FN>", "");
  fn = fn.replaceFirst("<.*\n.*\n.*", "");
  return fn;

  }

  public static byte[] getBytesFromFile(File file) throws IOException {
  InputStream is = new FileInputStream(file);

  // Get the size of the file
  long length = file.length();

  if (length > Integer.MAX_VALUE) {
    // File is too large
  }

  // Create the byte array to hold the data
  byte[] bytes = new byte[(int)length];

  // Read in the bytes
  int offset = 0;
  int numRead = 0;
  while (offset < bytes.length
     && (numRead=is.read(bytes, offset, bytes.length-offset)) >= 0) {
    offset += numRead;
  }

  // Ensure all the bytes have been read in
  if (offset < bytes.length) {
    throw new IOException("Could not completely read file "+file.getName());
  }

  // Close the input stream and return bytes
  is.close();
  return bytes;
  }

}
