package com.Andy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MyThreadold extends Thread {

    public static Thread code = new Thread() {
        public void run() {

            Main.done = false;


            if (Main.ScanURLpast == Main.Scanurl)
            {
                Main.reset("Website loop detected!");
            }

            Main.secondlastScanurl = Main.Scanurl;
            Main.ScanURLpast = Main.secondlastScanurl;

            URL url;
            String CurrentURL = Main.Scanurl;

            List<String> links = null;
            try {

                url = new URL(CurrentURL);
                URLConnection conn = url.openConnection();

                BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));

                String inputLine;
                String data = "";
                List<String> datalist = new ArrayList<String>();
                links = new ArrayList<String>();


                while ((inputLine = br.readLine()) != null) {
                    //System.out.println(inputLine);

                    if (inputLine.contains("<a href=")) {

                        data = data + inputLine;
                        datalist.add(inputLine);


                    }
                }

                for (int i = 0; i < datalist.size(); i++) {
                    String html = datalist.get(i);

                    Pattern p = Pattern.compile("<a href=\"(.*?)\"");
                    Matcher m = p.matcher(html);
                    while (m.find()) {
                        links.add(m.group(1).replace("<a href=\"", ""));
                    }
                }


                br.close();

                //for (int i = 0; i < links.size(); i++) {
                //    System.out.println(links.get(i));
                //}
            } catch (IOException e) {
                System.out.println(e);
                if (String.valueOf(e).contains("429")) {
                    System.out.println("Too many requests!");
                    try {
                        Thread.sleep(5000);
                        Main.secondlastScanurl = "";
                        Main.ScanURLpast = "";
                    } catch (InterruptedException e1) {
                        e1.printStackTrace();
                    }
                    Main.safe = true;
                    Main.done = true;
                }
                Main.safe = true;
            }

            if (links.size() == 0) {
                Main.safe = false;
            } else {
                int R = 0;
                Random r = new Random();
                R = r.nextInt(links.size()-1 - 0 + 1) + 0;

                Main.Scanurl = links.get(R);
            }
            Main.done = true;
            Main.numofloops = 0;
            Main.numoflooperrors = 0;
        }

    };

}
