package launcher.aircraft;

import launcher.aircraft.*;
import launcher.weather.*;
import launcher.writer.*;

public class Helicopter extends Aircraft implements Flyable
{
    private WeatherTower NweatherTower;

    Helicopter(String name, Coordinates coordinates) 
    {
        super(name, coordinates);
    }

    public void updateConditions() 
    {
        int copyHeight = this.coordinates.getHeight();
        int copyLong = this.coordinates.getLongitude(); 
        int copyLat = this.coordinates.getLatitude();
        String weather = this.NweatherTower.getWeather(this.coordinates);
        switch (weather) {
            case "RAIN":
                this.coordinates = new Coordinates(copyLong + 5, copyLat, copyHeight);
                writr.writetofile("Helicopter#" + this.name + "(" + this.id + "): Anyone have an umbrella big enough? ");
                break;
            case "SNOW":
                this.coordinates = new Coordinates(copyLong, copyLat, copyHeight - 12);
                writr.writetofile("Helicopter#" + this.name + "(" + this.id + "): Is it just me or is it a bit chilly?");
                break;
            case "FOG":
                this.coordinates = new Coordinates(copyLong + 1, copyLat, copyHeight);
                writr.writetofile("Helicopter#" + this.name + "(" + this.id + "): Time to use your other senses. We've lost our eyes.");
                break;
            case "SUN":
                this.coordinates = new Coordinates(copyLong + 10, copyLat, copyHeight + 2);
                writr.writetofile("Helicopter#" + this.name + "(" + this.id + "): I hope these windows are Photochromic!");
                break;            
            default:
                writr.writetofile("unknown weather: ");
                break;
        }
        if (this.coordinates.getHeight() <= 0)
        {
            unregisterTower(this.NweatherTower);            
        }
    }

    public void registerTower(WeatherTower weatherTower) 
    {
        writr.writetofile("Tower says: " + "Helicopter#" + this.name + "(" + this.id + ")" + " registered to weather tower.");
        this.NweatherTower = weatherTower;
        weatherTower.register(this);
    }

    public void unregisterTower(WeatherTower weatherTower) 
    {
        writr.writetofile("Tower says: " + "Helicopter#" + this.name + "(" + this.id + ")" + " unregisteres from weather tower and lands.");
        this.NweatherTower = weatherTower;
        weatherTower.unregister(this);
    }
}