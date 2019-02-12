package jaeyoung.com.dagger.main;

import jaeyoung.com.dagger.networking.MainService;
import jaeyoung.com.dagger.model.WeatherResponse;
import jaeyoung.com.dagger.networking.NetworkError;
import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by jaeyoung lee on 2019. 2. 12..
 */
public class MainPresenter {
	private final MainService mainService;
	private final MainView mainView;
	private CompositeSubscription subscriptions;

	public MainPresenter(MainService mainService, MainView mainView) {
		this.mainService = mainService;
		this.mainView = mainView;
		this.subscriptions = new CompositeSubscription();
	}

	public void getWeatherInfo(String location) {
		mainView.showWait();

		Subscription subscription = mainService.getWeatherInfo(location, new MainService.WeatherCallback() {
			@Override public void onSuccess(WeatherResponse weatherResponse) {
				mainView.removeWait();
				mainView.getWeather(weatherResponse);
			}

			@Override
			public void onError(NetworkError networkError) {
				mainView.removeWait();
				mainView.onFailure(networkError.getAppErrorMessage());
			}

		});

		subscriptions.add(subscription);
	}

	public void onStop() {
		subscriptions.unsubscribe();
	}
}
