package jaeyoung.com.dagger.dagger;

import dagger.Component;
import jaeyoung.com.dagger.main.MainActivity;

import javax.inject.Singleton;

/**
 * Created by jaeyoung lee on 2019. 2. 12..
 */
@Singleton
@Component(modules = {AppModule.class, NetModule.class})
public interface NetComponent {
	void inject(MainActivity activity);
}
