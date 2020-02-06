 package model;

 import javafx.collections.FXCollections;
 import javafx.collections.ObservableList;
 import org.w3c.dom.Document;
 import org.w3c.dom.Element;
 import org.w3c.dom.Node;
 import org.w3c.dom.NodeList;
 import org.xml.sax.SAXException;

 import javax.xml.parsers.DocumentBuilder;
 import javax.xml.parsers.DocumentBuilderFactory;
 import javax.xml.parsers.ParserConfigurationException;
 import java.io.IOException;
 import java.util.List;
 import java.util.stream.Collectors;
 import java.util.stream.IntStream;

 public class CountryModel {

     private List<Element> countries;

     public CountryModel() throws ParserConfigurationException, IOException, SAXException {
         var countryCodesStream = CountryModel.class.getResourceAsStream("/xml/CountryIsoCodes.xml");

         DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
         DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
         Document doc = dBuilder.parse(countryCodesStream);
         doc.getDocumentElement().normalize();
         NodeList rawCountries = doc.getElementsByTagName("record");
         countries = IntStream.range(0, rawCountries.getLength())
                 .mapToObj(rawCountries::item)
                 .filter(it -> it.getNodeType() == Node.ELEMENT_NODE)
                 .map(it -> (Element) it)
                 .collect(Collectors.toList());
     }

     public ObservableList<String> getCountryObservableList() {
         return FXCollections.observableArrayList(createObservableList());
     }

     private List<String> createObservableList() {
         return countries.stream()
                 .map(it -> it.getElementsByTagName("countryName").item(0).getTextContent())
                 .collect(Collectors.toList());
     }

     public String getCountryIsoCode(String countryName) {
         return countries.stream()
                 .filter(it -> countryName.equalsIgnoreCase(it.getElementsByTagName("countryName").item(0).getTextContent()))
                 .findFirst()
                 .map(it -> it.getElementsByTagName("countryISOcode").item(0).getTextContent())
                 .orElse("");
     }
 }