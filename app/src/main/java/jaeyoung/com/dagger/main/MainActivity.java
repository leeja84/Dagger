package jaeyoung.com.dagger.main;

import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import jaeyoung.com.dagger.model.WeatherMainData;
import jaeyoung.com.dagger.networking.MainService;
import jaeyoung.com.dagger.model.WeatherData;
import jaeyoung.com.dagger.model.WeatherResponse;
import jaeyoung.com.dagger.MyApplication;
import jaeyoung.com.dagger.R;

import javax.inject.Inject;

/**
 * Created by jaeyoung lee on 2019. 2. 12..
 * retrofit + dagger + mvp
 */
public class MainActivity extends AppCompatActivity implements MainView {
	@Inject MainService mainService;

	private MainPresenter mainPresenter;
	private ProgressBar pbMain;
	private TextView tvMain;
	private AppCompatButton btnMain;
	private TextInputEditText etMain;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		((MyApplication)getApplication()).getNetComponent().inject(this);

		setContentView(R.layout.activity_main);
		initView();

		mainPresenter = new MainPresenter(mainService, this);
		mainPresenter.getWeatherInfo("seoul");
	}

	private void initView() {
		tvMain = findViewById(R.id.tv_main);
		pbMain = findViewById(R.id.pb_main);
		btnMain = findViewById(R.id.btn_main);
		etMain = findViewById(R.id.et_main);
		btnMain.setOnClickListener(clickListener);
	}

	@Override public void showWait() {
		pbMain.setVisibility(View.VISIBLE);
	}

	@Override public void removeWait() {
		pbMain.setVisibility(View.GONE);
	}

	@Override public void onFailure(String appErrorMessage) {
		tvMain.setText(appErrorMessage);
	}

	@Override public void getWeather(WeatherResponse weatherResponse) {
		WeatherData firstWeatherData = weatherResponse.getWeatherInfo().get(0).getWeather().get(0);
		WeatherMainData firstMainData = weatherResponse.getWeatherInfo().get(0).getMain();

		tvMain.setText("City : " + weatherResponse.getWeatherInfo().get(0).getName() + "\n\n"
			+ "Temp : " + firstMainData.getTemp()
			+ " / Max : " + firstMainData.getTempMax()
			+ " / Min : " + firstMainData.getTempMin()
			+ " / Humidity : " + firstMainData.getHumidity() + "\n"
			+ " / main : " + firstWeatherData.getMain()
			+ " description : " + firstWeatherData.getDescription());
	}

	private View.OnClickListener clickListener = new View.OnClickListener() {
		public void onClick(View v) {
			String location = etMain.getText().toString();
			mainPresenter.getWeatherInfo(location);
		}
	};
}