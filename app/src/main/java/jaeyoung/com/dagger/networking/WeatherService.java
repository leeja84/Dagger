package jaeyoung.com.dagger.networking;

import jaeyoung.com.dagger.model.WeatherResponse;
import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by jaeyoung lee on 2019. 2. 12..
 */
public interface WeatherService {
    @GET("data/2.5/find?units=metric")
    Observable<WeatherResponse> getWeatherInfo(@Query("q") String location, @Query("appid") String appid);

//    http://api.openweathermap.org/data/2.5/find?units=metric&q=amsterdam&appid=17876471b2ff4ea26e51bca33d75313d

}
