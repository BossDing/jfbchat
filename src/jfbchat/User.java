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
  * along with this program.  If not, see <http://www.gnu.org/licenses/>.
  *
  *###########################################################################
  *
  */

package jfbchat;

import java.io.*;

import jfbchat.resources.Options;

/**
 * A User
 * @author Digitex ( Giuseppe Federico - digitex3d@gmail.com )
 */

public class User {

    private File configFile;
    private String username;
    private String password;

    public User(String username, String password){


        this.username = username;
        this.password = password;
        this.configFile = new File( Options.CONFIGFILE );

    }

    public User(){

        this.username = null;
        this.password = null;
        this.configFile = new File( Options.CONFIGFILE );

    }

    public String getPassword(){
        return password;
    }

    public String getUsername(){
        return username;
    }


    public void createConfigFile() throws IOException{

        if(!configFile.exists()){
                configFile.createNewFile();
                System.out.println("New file  \"" + Options.CONFIGFILE + "\" + has been created.");
            }

    }

    public void saveUserAndPass(){

        /* Write username and password to a config file for autologin*/

        try{
           
            createConfigFile();

            // Open file
            FileWriter fstream = new FileWriter( Options.CONFIGFILE );

            BufferedWriter out = new BufferedWriter(fstream);
            
          
            out.write("Username: " + username+"\n");
            out.write("Password: " + password+"\n");
            

            //Close the output stream
            out.close();

       }catch (Exception e){

            //Catch exception if any
            System.err.println("Error: " + e.getMessage());
        }
    }

    public void setAutologin(){

        /* Write username and password to a config file for autologin*/

        try{

            createConfigFile();

            // Open file
            FileWriter fstream = new FileWriter( Options.CONFIGFILE );

            BufferedWriter out = new BufferedWriter(fstream);

            out.write("Autologin: true\n");
            out.write("Username: " + username+"\n");
            out.write("Password: " + password+"\n");


            //Close the output stream
            out.close();

       }catch (Exception e){

            //Catch exception if any
            System.err.println("Error: " + e.getMessage());
        }
    }

    public boolean isAutoLogin(){

        /* read the configfile and return true if autologin is enabled false if not*/
        
        boolean resu = false;
        FileInputStream fis;
        BufferedInputStream bis;
        DataInputStream dis;

        try {
            fis = new FileInputStream(configFile);

            // Here BufferedInputStream is added for fast reading.
            bis = new BufferedInputStream(fis);
            dis = new DataInputStream(bis);

            // this statement reads the line from the file and print it to
            // the console.
             
            if ( dis.readLine().equals("Autologin: true")){

                resu = true;
            }
            else{
                resu = false;
            }

         fis.close();
         bis.close();
         dis.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
           e.printStackTrace();
        }

        return resu;
  }

    

    public String getSavedPass(){

        /* read the configfile and return the saved password*/

        String resu = null;
        FileInputStream fis;
        BufferedInputStream bis;
        DataInputStream dis;
        String strLine;

        try {
            fis = new FileInputStream(configFile);

            // Here BufferedInputStream is added for fast reading.
            bis = new BufferedInputStream(fis);
            dis = new DataInputStream(bis);

             while ((strLine = dis.readLine()) != null)   {
                if(strLine.contains("Password")){
                    resu = strLine.substring(10);
                }

            }



         fis.close();
         bis.close();
         dis.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
           e.printStackTrace();
        }

        return resu;
  }

    public String getSavedUser(){

        /* read the configfile and return the saved password*/

        String resu = null;
        FileInputStream fis;
        BufferedInputStream bis;
        DataInputStream dis;
        String strLine;

        try {
            fis = new FileInputStream(configFile);

            // Here BufferedInputStream is added for fast reading.
            bis = new BufferedInputStream(fis);
            dis = new DataInputStream(bis);

             while ((strLine = dis.readLine()) != null)   {
                if(strLine.contains("Username")){
                    resu = strLine.substring(10);
                }

            }



         fis.close();
         bis.close();
         dis.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
           e.printStackTrace();
        }

        return resu;
  }

    @Override
    public String toString(){
        return "Username: " + username;
    }


}




