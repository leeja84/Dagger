package jaeyoung.com.dagger.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import javax.annotation.Generated;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by jaeyoung lee on 2019. 2. 12..
 */
@Generated("org.jsonschema2pojo")
public class WeatherInfo {
	@SerializedName("weather")
	@Expose
	private List<WeatherData> weather = new ArrayList<WeatherData>();

	@SerializedName("main")
	@Expose
	private WeatherMainData main = new WeatherMainData();

	@SerializedName("name")
	@Expose
	private String name;

	public List<WeatherData> getWeather() {
		return weather;
	}

	public void setWeather(List<WeatherData> weather) {
		this.weather = weather;
	}

	public WeatherMainData getMain() {
		return main;
	}

	public void setMain(WeatherMainData main) {
		this.main = main;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
