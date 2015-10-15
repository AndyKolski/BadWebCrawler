package com.Andy;

import java.net.UnknownHostException;

public class Main {

    public static String lastScanurl = "";
    public static String secondlastScanurl = "";
    public static String Scanurl = "https://www.reddit.com";
    public static String ScanURLpast = "";


    public static String firstScanurl = "";
    public static boolean safe = true;
    public static boolean done = true;
    public static int numofloops = 0;
    public static int numoflooperrors = 0;

    public static void main(String[] args) throws UnknownHostException {

        firstScanurl = Scanurl;


        while (true) {
            lastScanurl = Scanurl.replaceFirst("https://", "");
            while (safe && done) {
                if (Scanurl.startsWith("/")) {
                    if (Scanurl.startsWith("https://")) {
                        Scanurl = "https://" + lastScanurl + Scanurl;
                    } else {
                        Scanurl = "http://" + lastScanurl + Scanurl;
                    }
                }
                if (Scanurl.endsWith("#")) {
                    Scanurl = Scanurl.replace("#", "");
                }
                if (Scanurl.replace("://", "").contains("//")) {
                    Scanurl = Scanurl.replace("//", "/").replace(":/", "://");
                }

                while (Main.Scanurl.contains("http://http://")) {
                    Main.Scanurl = Main.Scanurl.replace("http://http://", "http://");
                }
                while (Main.Scanurl.contains("https://https://")) {
                    Main.Scanurl = Main.Scanurl.replace("https://https://", "https://");
                }

                try {
                    System.out.println(Scanurl);
                    MyThreadold.code.run();
                } catch (NullPointerException e) {
                    try {
                        //System.out.println(e);
                        safe = true;
                        Thread.sleep(50);
                    } catch (InterruptedException e1) {
                        e1.printStackTrace();
                    }
                } catch (IllegalArgumentException e) {
                    reset("Strange error.");
                }
            }
            numofloops++;


            if (numofloops > 10) {
                safe = true;
                done = true;
                numofloops = 0;
                numoflooperrors++;
            }
            if (numoflooperrors > 10) {
                reset("Loopreset");
            }
        }
        }

    public static void reset(String str) {
        System.out.println(str);
        Scanurl = firstScanurl;
        numoflooperrors = 0;
        numofloops = 0;
    }

}