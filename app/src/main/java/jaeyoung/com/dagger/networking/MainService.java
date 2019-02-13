package jaeyoung.com.dagger.networking;

import jaeyoung.com.dagger.model.WeatherResponse;
import jaeyoung.com.dagger.UrlConstants;
import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by jaeyoung lee on 2019. 2. 12..
 */
public class MainService {
    private final WeatherService weatherService;

    public MainService(WeatherService weatherService) {
        this.weatherService = weatherService;
    }

    public Subscription getWeatherInfo(String location, final WeatherCallback callback) {
        return weatherService.getWeatherInfo(location, UrlConstants.APP_KEY)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .onErrorResumeNext(new Func1<Throwable, Observable<? extends WeatherResponse>>() {
                    @Override
                    public Observable<? extends WeatherResponse> call(Throwable throwable) {
                        return Observable.error(throwable);
                    }
                })
                .subscribe(new Subscriber<WeatherResponse>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        callback.onError(new NetworkError(e));

                    }

                    @Override
                    public void onNext(WeatherResponse weatherResponse) {
                        callback.onSuccess(weatherResponse);

                    }
                });
    }

    public interface WeatherCallback{
        void onSuccess(WeatherResponse weatherResponse);

        void onError(NetworkError networkError);
    }
}
