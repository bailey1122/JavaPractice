package auth;

import java.net.*;
import java.io.*;

// parsing a url
public class Urlexample {
    public static void main(String[] args) throws IOException {
        URL urlex = new URL("https://en.wikipedia.org/wiki/Philosophy");
//        BufferedReader in = new BufferedReader(
//                new InputStreamReader(urlex.openStream())); // get a stream directly from url to reade the url
        URLConnection uc = urlex.openConnection(); //
        BufferedReader in = new BufferedReader(
                new InputStreamReader(uc.getInputStream())); // open the connection

        String inputLine;
        while ((inputLine = in.readLine()) != null)
            System.out.println(inputLine);
        in.close();

        System.out.println("protocol = " + urlex.getProtocol());
        System.out.println("authority = " + urlex.getAuthority());
        System.out.println("host = " + urlex.getHost());
        System.out.println("port = " + urlex.getPort());
        System.out.println("query = " + urlex.getQuery());
        System.out.println("filename = " + urlex.getFile());
        System.out.println("ref = " + urlex.getRef());
    }
}
