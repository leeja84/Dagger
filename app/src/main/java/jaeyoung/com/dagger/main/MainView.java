package jaeyoung.com.dagger.main;

import jaeyoung.com.dagger.model.WeatherResponse;

/**
 * Created by jaeyoung lee on 2019. 2. 12..
 */
public interface MainView {
	void showWait();

	void removeWait();

	void onFailure(String appErrorMessage);

	void getWeather(WeatherResponse weatherResponse);
}
