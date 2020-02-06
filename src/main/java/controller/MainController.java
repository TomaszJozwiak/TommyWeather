package controller;

import javafx.fxml.*;
import javafx.scene.control.*;
import javafx.scene.image.*;
import model.*;
import repository.*;
import org.xml.sax.*;

import javax.xml.parsers.*;
import java.io.*;
import java.util.*;
import java.util.function.*;

import static util.StringUtils.*;

public class MainController {

    @FXML
    public ChoiceBox<String> countryChoiceBox1;
    public ChoiceBox<String> countryChoiceBox2;
    public TextField cityTextField1;
    public TextField cityTextField2;
    public Label errorLabel1;
    public Label errorLabel2;
    public Label cityLabel1;
    public Label cityLabel2;

    public ImageView city1icon1;
    public Label city1date1;
    public Label city1clouds1;
    public Label city1temperature1;
    public Label city1wind1;
    public Label city1pressure1;

    public ImageView city1icon2;
    public Label city1date2;
    public Label city1clouds2;
    public Label city1temperature2;
    public Label city1wind2;
    public Label city1pressure2;

    public ImageView city1icon3;
    public Label city1date3;
    public Label city1clouds3;
    public Label city1temperature3;
    public Label city1wind3;
    public Label city1pressure3;

    public ImageView city1icon4;
    public Label city1date4;
    public Label city1clouds4;
    public Label city1temperature4;
    public Label city1wind4;
    public Label city1pressure4;

    public ImageView city1icon5;
    public Label city1date5;
    public Label city1clouds5;
    public Label city1temperature5;
    public Label city1wind5;
    public Label city1pressure5;

    public ImageView city2icon1;
    public Label city2date1;
    public Label city2clouds1;
    public Label city2temperature1;
    public Label city2wind1;
    public Label city2pressure1;

    public ImageView city2icon2;
    public Label city2date2;
    public Label city2clouds2;
    public Label city2temperature2;
    public Label city2wind2;
    public Label city2pressure2;

    public ImageView city2icon3;
    public Label city2date3;
    public Label city2clouds3;
    public Label city2temperature3;
    public Label city2wind3;
    public Label city2pressure3;

    public ImageView city2icon4;
    public Label city2date4;
    public Label city2clouds4;
    public Label city2temperature4;
    public Label city2wind4;
    public Label city2pressure4;

    public ImageView city2icon5;
    public Label city2date5;
    public Label city2clouds5;
    public Label city2temperature5;
    public Label city2wind5;
    public Label city2pressure5;

    private CountryModel countryModel;

    private WeatherRawDataToModelTransformer weatherRepository;

    @FXML
    void initialize() {

        weatherRepository = new WeatherRawDataToModelTransformer(new repository.WeatherRepository());
        try {
            countryModel = new CountryModel();
        } catch (ParserConfigurationException | IOException | SAXException e) {
            e.printStackTrace();
            throw new Error(e);
        }

        countryChoiceBox1.setItems(countryModel.getCountryObservableList());
        countryChoiceBox2.setItems(countryModel.getCountryObservableList());
        // Set default country to Poland
        countryChoiceBox1.getSelectionModel().select(167);
        countryChoiceBox2.getSelectionModel().selectFirst();
    }

    public void displayWeather() {
        String countryCode1 = countryModel.getCountryIsoCode(countryChoiceBox1.getValue());
        String city1 = cityTextField1.getText();

        String countryCode2 = countryModel.getCountryIsoCode(countryChoiceBox2.getValue());
        String city2 = cityTextField2.getText();

        this.clearAll();

        if (isTextFieldEmpty(cityTextField1)) {
            errorLabel1.setText("Proszę wpisać nazwę miasta");
        }
        if (isTextFieldEmpty(cityTextField2)) {
            errorLabel2.setText("Proszę wpisać nazwę miasta");
        }
        if (!isTextFieldEmpty(cityTextField1) && !isTextFieldEmpty(cityTextField2)) {
            setWeatherDataInFirstColumn(countryCode1, city1);
            setWeatherDataInSecondColumn(countryCode2, city2);
        }
    }

    private boolean isTextFieldEmpty(TextField textField) {
        return textField.getText().trim().equals("");
    }

    private void clearAll() {
        errorLabel1.setText("");
        errorLabel2.setText("");
    }

    private void setWeatherDataInFirstColumn(String countryIsoCode, String city) {
        Function<List<model.WeatherChunk>, Void> onSuccess = (weather) -> {
            setColumn1City1(weather.get(0));
            setColumn2City1(weather.get(1));
            setColumn3City1(weather.get(2));
            setColumn4City1(weather.get(3));
            setColumn5City1(weather.get(4));
            cityLabel1.setText(firstLetterToUpperCaseRestToLowerCase(cityTextField1.getText()));
            return null;
        };
        Function<Exception, Void> onException = (e) -> {
            errorLabel1.setText("Pogoda dla tego miasta niedostępna");
            return null;
        };
        setWeatherData(countryIsoCode, city, onSuccess, onException);
    }

    private void setWeatherDataInSecondColumn(String countryIsoCode, String city) {
        Function<List<model.WeatherChunk>, Void> onSuccess = (weather) -> {
            setColumn1City2(weather.get(0));
            setColumn2City2(weather.get(1));
            setColumn3City2(weather.get(2));
            setColumn4City2(weather.get(3));
            setColumn5City2(weather.get(4));
            cityLabel2.setText(firstLetterToUpperCaseRestToLowerCase(cityTextField2.getText()));
            return null;
        };
        Function<Exception, Void> onException = (e) -> {
            errorLabel2.setText("Pogoda dla tego miasta niedostępna");
            return null;
        };
        setWeatherData(countryIsoCode, city, onSuccess, onException);
    }

    private void setWeatherData(String countryIsoCode,
                                String city,
                                Function<List<model.WeatherChunk>, Void> onSuccess,
                                Function<Exception, Void> onException) {
        try {
            var weatherNodeList = weatherRepository.fetchAndTransform(countryIsoCode, city);
            onSuccess.apply(weatherNodeList);
        } catch (Exception e) {
            e.printStackTrace();
            onException.apply(e);
        }
    }

    private void setColumn1City1 (model.WeatherChunk eElement) {
        setWeatherData(eElement, city1icon1, city1date1, city1clouds1, city1temperature1, city1wind1, city1pressure1);
    }

    private void setColumn2City1 (model.WeatherChunk eElement) {
        setWeatherData(eElement, city1icon2, city1date2, city1clouds2, city1temperature2, city1wind2, city1pressure2);
    }

    private void setColumn3City1 (model.WeatherChunk eElement) {
        setWeatherData(eElement, city1icon3, city1date3, city1clouds3, city1temperature3, city1wind3, city1pressure3);
    }

    private void setColumn4City1 (model.WeatherChunk eElement) {
        setWeatherData(eElement, city1icon4, city1date4, city1clouds4, city1temperature4, city1wind4, city1pressure4);
    }

    private void setColumn5City1 (model.WeatherChunk eElement) {
        setWeatherData(eElement, city1icon5, city1date5, city1clouds5, city1temperature5, city1wind5, city1pressure5);
    }

    private void setColumn1City2 (model.WeatherChunk eElement) {
        setWeatherData(eElement, city2icon1, city2date1, city2clouds1, city2temperature1, city2wind1, city2pressure1);
    }

    private void setColumn2City2 (model.WeatherChunk eElement) {
        setWeatherData(eElement, city2icon2, city2date2, city2clouds2, city2temperature2, city2wind2, city2pressure2);
    }

    private void setColumn3City2 (model.WeatherChunk eElement) {
        setWeatherData(eElement, city2icon3, city2date3, city2clouds3, city2temperature3, city2wind3, city2pressure3);
    }

    private void setColumn4City2 (model.WeatherChunk eElement) {
        setWeatherData(eElement, city2icon4, city2date4, city2clouds4, city2temperature4, city2wind4, city2pressure4);
    }

    private void setColumn5City2 (model.WeatherChunk eElement) {
        setWeatherData(eElement, city2icon5, city2date5, city2clouds5, city2temperature5, city2wind5, city2pressure5);
    }

    private void setWeatherData(model.WeatherChunk weatherChunk,
                                ImageView cityIcon,
                                Label cityDate,
                                Label cityClouds,
                                Label cityTemperature,
                                Label cityWind,
                                Label cityPressure) {
        var imageInputStream = MainController.class.getResourceAsStream("/images/" + prepareImageName(weatherChunk.getIcon()) + ".png");
        Image image = new Image(imageInputStream);
        cityIcon.setImage(image);
        cityDate.setText(parseAndFormatDate(weatherChunk.getDate()));
        cityClouds.setText("Stan: " + firstLetterToUpperCase(weatherChunk.getClouds()));
        cityTemperature.setText("Temperatura: " + weatherChunk.getTemperature() + "°C");
        cityWind.setText("Wiatr: " + weatherChunk.getWind() + "m/s");
        cityPressure.setText("Ciśnienie: " + weatherChunk.getPressure() + "hPa");
    }


    private String prepareImageName(String imageName) {
        return imageName.substring(0, 2);
    }

    public void exit() {
        System.exit(0);
    }

}