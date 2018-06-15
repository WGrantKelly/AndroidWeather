package grantkellycode.androidweather;

import android.os.AsyncTask;
import android.util.Log;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.InputStream;


public class ParseAndLoad extends AsyncTask<Void, Void, CurrentWeatherData> {

    private static String XML;
    private static final String DEBUG_TAG= "Parse&Load: ";
    private static CurrentWeatherData currentWeatherData;

    public ParseAndLoad(String xml){
        XML = xml;
        currentWeatherData = new CurrentWeatherData();
    }

    protected CurrentWeatherData doInBackground(Void... urls){

        //get class ready to load
        CurrentWeatherData data = new CurrentWeatherData();

        try {

            String initialString = "text";
            InputStream stream = new ByteArrayInputStream(XML.getBytes());

            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(stream);

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

            currentWeatherData.cityID = cityList.item(0).getAttributes().getNamedItem("id").getNodeValue();
            currentWeatherData.cityName = cityList.item(0).getAttributes().getNamedItem("name").getNodeValue();
            currentWeatherData.cityCoordLat = coordList.item(0).getAttributes().getNamedItem("lon").getNodeValue();
            currentWeatherData.cityCoordLon = coordList.item(0).getAttributes().getNamedItem("lat").getNodeValue();
            currentWeatherData.cityCountry = cityList.item(0).getChildNodes().item(1).getFirstChild().getNodeValue();
            currentWeatherData.citySunRise = sunList.item(0).getAttributes().getNamedItem("rise").getNodeValue();
            currentWeatherData.citySunSet = sunList.item(0).getAttributes().getNamedItem("set").getNodeValue();
            currentWeatherData.tempValue = temperatureList.item(0).getAttributes().getNamedItem("value").getNodeValue();
            currentWeatherData.tempMin = temperatureList.item(0).getAttributes().getNamedItem("min").getNodeValue();
            currentWeatherData.tempMax = temperatureList.item(0).getAttributes().getNamedItem("max").getNodeValue();
            currentWeatherData.tempUnit = temperatureList.item(0).getAttributes().getNamedItem("unit").getNodeValue();
            currentWeatherData.humidityValue = humidityList.item(0).getAttributes().getNamedItem("value").getNodeValue();
            currentWeatherData.humidityUnit = humidityList.item(0).getAttributes().getNamedItem("unit").getNodeValue();
            currentWeatherData.pressureValue = pressureList.item(0).getAttributes().getNamedItem("value").getNodeValue();
            currentWeatherData.pressureUnit = pressureList.item(0).getAttributes().getNamedItem("unit").getNodeValue();
            currentWeatherData.windSpeedValue = speedList.item(0).getAttributes().getNamedItem("value").getNodeValue();
            currentWeatherData.windSpeedName = speedList.item(0).getAttributes().getNamedItem("name").getNodeValue();
            currentWeatherData.windDirectionValue = directionList.item(0).getAttributes().getNamedItem("value").getNodeValue();
            currentWeatherData.windDirectionCode = directionList.item(0).getAttributes().getNamedItem("code").getNodeValue();
            currentWeatherData.windDirectionName = directionList.item(0).getAttributes().getNamedItem("name").getNodeValue();
            currentWeatherData.cloudsValue = cloudsList.item(0).getAttributes().getNamedItem("value").getNodeValue();
            currentWeatherData.cloudsName = cloudsList.item(0).getAttributes().getNamedItem("name").getNodeValue();
            currentWeatherData.visibilityValue = visibilityList.item(0).getAttributes().getNamedItem("value").getNodeValue();
            currentWeatherData.precipitationValue = precipitationList.item(0).getAttributes().getNamedItem("mode").getNodeValue();
            if(!currentWeatherData.precipitationValue.equals("no"))
                currentWeatherData.precipitationMode = precipitationList.item(0).getAttributes().getNamedItem("value").getNodeValue();
            currentWeatherData.weatherNumber = weatherList.item(0).getAttributes().getNamedItem("number").getNodeValue();
            currentWeatherData.weatherValue = weatherList.item(0).getAttributes().getNamedItem("value").getNodeValue();
            currentWeatherData.weatherIcon = weatherList.item(0).getAttributes().getNamedItem("icon").getNodeValue();
            if(lastupdateList.item(0)!=null)
                currentWeatherData.lastUpdate = lastupdateList.item(0).getAttributes().getNamedItem("value").getNodeValue();
        }catch (Exception e){

        }
        return currentWeatherData;
    }
}
