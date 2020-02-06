 package repository;

 import exception.WeatherRepositoryException;
 import org.w3c.dom.Document;
 import org.w3c.dom.NodeList;

 import javax.xml.parsers.DocumentBuilder;
 import javax.xml.parsers.DocumentBuilderFactory;
 import java.net.HttpURLConnection;
 import java.net.URL;

 public class WeatherRepository {

     public NodeList fetchWeather(String countryIsoCode, String city) {
         try {
             URL url = new URL("http://api.openweathermap.org/data/2.5/forecast?q=" + city + "," + countryIsoCode + "&units=metric&lang=pl&mode=xml&APPID=8cbc4f1e6576a2478f1d70e0a17cc594");
             HttpURLConnection connection = (HttpURLConnection) url.openConnection();
             connection.setRequestMethod("GET");
             DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
             DocumentBuilder db = dbf.newDocumentBuilder();
             Document doc = db.parse(connection.getInputStream());
             doc.getDocumentElement().normalize();
             connection.disconnect();
             if (connection.getResponseCode() != 200) {
                 throw new WeatherRepositoryException();
             } else {
                 return doc.getElementsByTagName("time");
             }
         } catch (Exception e) {
             e.printStackTrace();
             throw new WeatherRepositoryException(e);
         }
     }

 }