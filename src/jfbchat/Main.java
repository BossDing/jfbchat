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

import jfbchat.resources.*;
import jfbchat.debug.DebugMessage;

public class Main {

  public static void main(String[] args) {

  Options.HOME_DIR = System.getProperty("user.home");

  // Process arguments
  for (int i = 0; i < args.length; i++) {
    // -d Enable debug messages
    if (args[i].equals("-d") || args[i].equals("--debug")) {

    System.out.println("########  Debug mode  ########");
    Options.DEBUG_ENABLED = true;

    }

    if (args[i].equals("-h") || args[i].equals("--help")) {

    System.out.print("\nUsage:\n  jfbchat [OPTION...] \n\n"
        + "A simple facebook client\n\n"
        + "Debug Options:\n"
        + " -d, --debug     Enable debug messages\n\n");
    System.exit(0);

    }

  }

  System.out.println("Starting IM client");
  try {

    Application run = new Application();

  }catch (Exception e) {

    new DebugMessage("Main : Cannot run the application :", e);

  }

  }

}

