 package repository;

 import org.w3c.dom.*;

 import java.util.*;
 import java.util.stream.*;

 public class WeatherRawDataToModelTransformer {
     private final repository.WeatherRepository weatherRepository;

     public WeatherRawDataToModelTransformer(repository.WeatherRepository weatherRepository) {
         this.weatherRepository = weatherRepository;
     }

     public List<model.WeatherChunk> fetchAndTransform(String countryCode, String city) {
         NodeList data = weatherRepository.fetchWeather(countryCode, city);
         return IntStream.range(0, data.getLength())
                 .filter(i -> i % 8 == 0)
                 .mapToObj(data::item)
                 .filter(it -> it instanceof Element)
                 .map(it -> (Element) it)
                 .map(weatherData ->
                         new model.WeatherChunk(
                                 ((Element) weatherData.getElementsByTagName("symbol").item(0)).getAttribute("var"),
                                 weatherData.getAttribute("from"),
                                 ((Element) weatherData.getElementsByTagName("clouds").item(0)).getAttribute("value"),
                                 ((Element) weatherData.getElementsByTagName("temperature").item(0)).getAttribute("value"),
                                 ((Element) weatherData.getElementsByTagName("windSpeed").item(0)).getAttribute("mps"),
                                 ((Element) weatherData.getElementsByTagName("pressure").item(0)).getAttribute("value")
                         )
                 )
                 .collect(Collectors.toList());
     }
 }