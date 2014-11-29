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

import java.io.InputStream;

import jfbchat.debug.DebugMessage;

import javazoom.jl.player.Player;

public class MP3 {
  private String filename;
  private Player player;

  // constructor that takes the name of an MP3 file
  public MP3(String filename) {
  this.filename = filename;
  }

  public void close() { if (player != null) player.close(); }

  // play the MP3 file to the sound card
  public void play() {
  try {
    InputStream audioStream = this.getClass().getResourceAsStream(filename);

    player = new Player(audioStream);

  }
  catch (Exception e) {
    new DebugMessage(this.getClass(), "Problem loading " + filename, e);
  }

  // run in new thread to play in background
  new Thread() {
    public void run() {
    try { player.play(); }
    catch (Exception e) {
      new DebugMessage(this.getClass(), "Problem playing " + filename, e);
    }
    }
  }.start();

  }

}
