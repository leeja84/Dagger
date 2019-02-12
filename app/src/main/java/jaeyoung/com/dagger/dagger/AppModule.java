package jaeyoung.com.dagger.dagger;

import android.app.Application;
import dagger.Module;
import dagger.Provides;

import javax.inject.Singleton;

/**
 * Created by jaeyoung lee on 2019. 2. 12..
 */
@Module
public class AppModule {
	Application application;

	public AppModule(Application application) {
		this.application= application;
	}

	@Provides
	@Singleton
	Application providesApplication() {
		return application;
	}
}
