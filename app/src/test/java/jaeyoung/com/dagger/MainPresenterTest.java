package jaeyoung.com.dagger;

import jaeyoung.com.dagger.main.MainPresenter;
import jaeyoung.com.dagger.main.MainView;
import jaeyoung.com.dagger.model.WeatherResponse;
import jaeyoung.com.dagger.networking.MainService;
import jaeyoung.com.dagger.networking.WeatherService;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import rx.Observable;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class MainPresenterTest {

	@Rule
	public final RxSchedulersOverrideRule mOverrideSchedulersRule = new RxSchedulersOverrideRule();

	@Mock
	private WeatherService weatherService;

	@Mock
	private MainView mainView;

	@Mock
	private WeatherResponse weatherResponse;

	private MainService mainService;
	private MainPresenter mainPresenter;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		mainService = new MainService(weatherService);
		mainPresenter = new MainPresenter(mainService, mainView);
	}

	@After
	public void teardown() {
		mainPresenter.onStop();
	}

	@Test
	public void loadCitiesFromAPIAndLoadIntoView() {

		Observable<WeatherResponse> responseObservable = Observable.just(weatherResponse);
		when(weatherService.getWeatherInfo("seoul", UrlConstants.APP_KEY)).thenReturn(responseObservable);

		mainPresenter.getWeatherInfo("seoul");

		InOrder inOrder = Mockito.inOrder(mainView);
		inOrder.verify(mainView).showWait();
		inOrder.verify(mainView).removeWait();
		inOrder.verify(mainView).getWeather(weatherResponse);
	}
}
