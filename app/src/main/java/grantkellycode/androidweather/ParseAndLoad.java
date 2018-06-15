package grantkellycode.androidweather;

import android.os.AsyncTask;
import android.util.Log;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import java.io.File;


public class ParseAndLoad extends AsyncTask<Void, Void, CurrentWeatherData> {

    private static String XML;
    private static final String DEBUG_TAG= "Parse&Load: ";
    private static CurrentWeatherData currentWeatherData;

    public ParseAndLoad(String xml)throws org.xmlpull.v1.XmlPullParserException{
        XML = xml;
        currentWeatherData = new CurrentWeatherData();

    }

    protected CurrentWeatherData doInBackground(Void... urls){

        //get class ready to load
        CurrentWeatherData data = new CurrentWeatherData();

        try {

            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(XML);

            doc.getDocumentElement().normalize();

            System.out.println("Root element :" + doc.getDocumentElement().getNodeName());

            NodeList cityList = doc.getElementsByTagName("city");
            NodeList coordList = doc.getElementsByTagName("coord");
            NodeList sunList = doc.getElementsByTagName("sun");
            NodeList temperatureList = doc.getElementsByTagName("temperature");
            NodeList humidityList = doc.getElementsByTagName("humidity");
            NodeList pressureList = doc.getElementsByTagName("pressure");
            NodeList windList = doc.getElementsByTagName("wind");
            NodeList speedList = doc.getElementsByTagName("speed");
            NodeList directionList = doc.getElementsByTagName("direction");
            NodeList cloudsList = doc.getElementsByTagName("clouds");
            NodeList visibilityList = doc.getElementsByTagName("visibility");
            NodeList precipitationList = doc.getElementsByTagName("precipitation");
            NodeList weatherList = doc.getElementsByTagName("weather");
            NodeList lastupdateList = doc.getElementsByTagName("lastUpdate");

            currentWeatherData.cityID = cityList.item(0).getNodeName();
            currentWeatherData.cityName = cityList.item(1).getNodeName();
            currentWeatherData.cityCoordLat = coordList.item(0).getNodeName();
            currentWeatherData.cityCoordLon = coordList.item(1).getNodeName();
            currentWeatherData.cityCountry = cityList.item(2).getNodeName();
            currentWeatherData.citySunRise = sunList.item(0).getNodeName();
            currentWeatherData.citySunSet = sunList.item(1).getNodeName();
            currentWeatherData.tempValue = temperatureList.item(0).getNodeName();
            currentWeatherData.tempMin = temperatureList.item(2).getNodeName();
            currentWeatherData.tempMax = temperatureList.item(1).getNodeName();
            currentWeatherData.tempUnit = temperatureList.item(3).getNodeName();
            currentWeatherData.humidityValue = humidityList.item(0).getNodeName();
            currentWeatherData.humidityUnit = humidityList.item(1).getNodeName();
            currentWeatherData.pressureValue = pressureList.item(0).getNodeName();
            currentWeatherData.pressureUnit = pressureList.item(1).getNodeName();
            currentWeatherData.windSpeedValue = speedList.item(0).getNodeName();
            currentWeatherData.windSpeedName = speedList.item(1).getNodeName();
            currentWeatherData.windDirectionValue = directionList.item(0).getNodeName();
            currentWeatherData.windDirectionCode = directionList.item(1).getNodeName();
            currentWeatherData.windDirectionName = directionList.item(2).getNodeName();
            currentWeatherData.cloudsValue = cloudsList.item(0).getNodeName();
            currentWeatherData.cloudsName = cloudsList.item(1).getNodeName();
            currentWeatherData.visibilityValue = visibilityList.item(0).getNodeName();
            currentWeatherData.precipitationValue = precipitationList.item(0).getNodeName();
            currentWeatherData.precipitationMode = precipitationList.item(1).getNodeName();
            currentWeatherData.weatherNumber = weatherList.item(0).getNodeName();
            currentWeatherData.weatherValue = weatherList.item(1).getNodeName();
            currentWeatherData.weatherIcon = weatherList.item(2).getNodeName();
            currentWeatherData.lastUpdate = lastupdateList.item(0).getNodeName();


        }catch (Exception e){

        }
        return currentWeatherData;
    }
}
