package launcher.weather;

import launcher.aircraft.*;
import launcher.weather.*;

public class WeatherTower extends Tower
{
    public String getWeather(Coordinates coordinates) 
    {
        String weather = WeatherProvider.getProvider().getCurrentWeather(coordinates);
        return weather;
    }

    public void changeWeather() 
    {
        this.conditionsChanged();     
    }
}