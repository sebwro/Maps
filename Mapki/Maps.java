//package maps;

//import java.io.*;
import javax.imageio.*;
import java.awt.*;
import java.awt.image.*;
import java.awt.image.BufferedImage;

import java.net.*;
import java.io.*;
import java.lang.*;

/**
 *
 * @author Sebastian
 */
public class Maps {

//    String origin, destination;
//
//    public void Track(String origin, String destination) {
//
//        this.origin = origin;
//        this.destination = destination;
//
//    }
    double longitude, latitude;
    int size, zoom;
//    String scale, format, maptype;

//    public static String buildURL(String origin, String destination, String latitude, String longitude, String size_h, String size_w) {
    public static String buildURL(double longitude, double latitude, int zoom, int size) {
        StringBuilder urlBuilder = new StringBuilder();
        //urlBuilder.append("https://www.google.com/maps/dir/").append(origin).append("/").append(destination).append("/");

        // urlBuilder.append("http://maps.google.com/maps/api/staticmap?center=").append(longitude + ",").append(latitude + ",&zoom=").append(zoom + "&markers").append(longitude + ",").append(latitude + ",&size=");
        urlBuilder.append("http://maps.google.com/maps/api/staticmap?center=").append(longitude + ",").append(latitude + "&zoom=" + zoom).append("&size=" + size + "x" + size).append("&markers=size:mid%7Ccolor:blue" + longitude + "," + latitude + "%7C" + longitude + "," + latitude); // + "&path=color:0x0000FF80"); // "%7C = |"

        String urlLink = urlBuilder.toString();
        return urlLink;
    }

    public static void downloadImage(String urlLink) throws IOException {
        BufferedImage image = null;
       
        
        File outputfile = new File("\\mapaa.png");
        try {
            URL url = new URL(urlLink);
            image = ImageIO.read(url);
            if (image == null) {

                System.out.println("image = null");
            }

        } catch (IOException e) {
            System.out.println("couldn't download image.");
        }

        ImageIO.write(image, "png", outputfile);
        image = null;
        //  return image;
    }

    public static void main(String[] args) {
//        String origin = args[0];
//        String destination = args[1];
        String origin = "Warszawa";
        String destination = "Krakow";
        while (origin != null & destination != null) {

            //    String requestString = buildURL(origin, destination, 15, 500);
            String requestString = buildURL(52.231838, 21.0038063, 15, 500); // Palac Kultury
            System.out.println(requestString);

            try {

                URL url = new URL(requestString);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.connect();
                InputStream stream = conn.getInputStream();
                System.out.println(stream.toString());
                downloadImage(requestString);
                System.out.println("zapisano");
            } catch (MalformedURLException e) {
                System.out.println("the URL is not in a valid form");
            } catch (IOException e) {
                System.out.println("the connection couldn't be established");
            }
            System.exit(0); // koniec programu
        }

    }

}

//
//        String[] schemes = {"http", "https"}; // DEFAULT schemes = "http", "https", "ftp"
//        UrlValidator urlValidator = new UrlValidator(schemes);
//        if (urlValidator.isValid(requestString)) {
//            System.out.println("URL is valid");
//        } else {
//            System.out.println("URL is invalid");
//        }
