package model;

 import java.util.Objects;

 public class WeatherChunk {
     private final String icon;
     private final String date;
     private final String clouds;
     private final String temperature;
     private final String wind;
     private final String pressure;

     public WeatherChunk(String icon, String date, String clouds, String temperature, String wind, String pressure) {
         this.icon = icon;
         this.date = date;
         this.clouds = clouds;
         this.temperature = temperature;
         this.wind = wind;
         this.pressure = pressure;
     }

     public String getIcon() {
         return icon;
     }

     public String getDate() {
         return date;
     }

     public String getClouds() {
         return clouds;
     }

     public String getTemperature() {
         return temperature;
     }

     public String getWind() {
         return wind;
     }

     public String getPressure() {
         return pressure;
     }

     @Override
     public boolean equals(Object o) {
         if (this == o) return true;
         if (o == null || getClass() != o.getClass()) return false;
         WeatherChunk that = (WeatherChunk) o;
         return Objects.equals(icon, that.icon) &&
                 Objects.equals(date, that.date) &&
                 Objects.equals(clouds, that.clouds) &&
                 Objects.equals(temperature, that.temperature) &&
                 Objects.equals(wind, that.wind) &&
                 Objects.equals(pressure, that.pressure);
     }

     @Override
     public int hashCode() {
         return Objects.hash(icon, date, clouds, temperature, wind, pressure);
     }
 }