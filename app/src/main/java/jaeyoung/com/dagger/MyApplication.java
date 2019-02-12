package jaeyoung.com.dagger;

import android.app.Application;
import jaeyoung.com.dagger.dagger.AppModule;
import jaeyoung.com.dagger.dagger.DaggerNetComponent;
import jaeyoung.com.dagger.dagger.NetComponent;
import jaeyoung.com.dagger.dagger.NetModule;

/**
 * Created by jaeyoung lee on 2019. 2. 12..
 */
public class MyApplication extends Application {
	private NetComponent netComponent;

	@Override
	public void onCreate() {
		super.onCreate();

		netComponent = DaggerNetComponent.builder()
			.appModule(new AppModule(this))
			.netModule(new NetModule(UrlConstants.BASE_URL))
			.build();

	}

	public NetComponent getNetComponent() {
		return netComponent;
	}
}
