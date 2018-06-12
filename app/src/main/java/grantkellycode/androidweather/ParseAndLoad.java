package grantkellycode.androidweather;

import android.os.AsyncTask;
import android.util.Log;

import java.io.StringReader;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;


public class ParseAndLoad extends AsyncTask<Void, Void, String> {

    private static XmlPullParserFactory xmlFactoryObject;
    private static XmlPullParser myParser;
    private static String XML;
    private static final String DEBUG_TAG= "Parse&Load: ";

    public ParseAndLoad(String xml)throws org.xmlpull.v1.XmlPullParserException{
        XML = xml;
        xmlFactoryObject = XmlPullParserFactory.newInstance();
        myParser = xmlFactoryObject.newPullParser();
    }

    protected String doInBackground(Void... urls){

        int eventType = 0;
        //get class ready to load
        CurrentWeatherData data = new CurrentWeatherData();

        //get class ready to parse
        try {
            myParser.setInput(new StringReader(XML));
            eventType = myParser.getEventType();
        }
        catch (Exception e){
            Log.e("ERROR", e.getMessage(), e);
            return null;
        }
        while (eventType != XmlPullParser.END_DOCUMENT) {
            if (eventType == XmlPullParser.TEXT) {
                Log.d(DEBUG_TAG, myParser.getText());
            }
            try {
                eventType = myParser.next();
            }
            catch(Exception e){
                Log.e("ERROR", e.getMessage(), e);
                return null;
            }
        }
        return "success";
    }
}
