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
public class WeatherResponse {
	@SerializedName("list")
	@Expose
	private List<WeatherInfo> weatherInfo = new ArrayList<>();

	public List<WeatherInfo> getWeatherInfo() {
		return weatherInfo;
	}

	public void setWeatherInfo(List<WeatherInfo> weatherInfo) {
		this.weatherInfo = weatherInfo;
	}
}
