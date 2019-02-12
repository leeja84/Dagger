package jaeyoung.com.dagger.dagger;

import android.app.Application;
import dagger.Module;
import dagger.Provides;
import jaeyoung.com.dagger.networking.MainService;
import jaeyoung.com.dagger.networking.WeatherService;
import okhttp3.Cache;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import javax.inject.Singleton;

/**
 * Created by jaeyoung lee on 2019. 2. 12..
 */
@Module
public class NetModule {
	String baseUrl;

	public NetModule(String baseUrl) {
		this.baseUrl = baseUrl;
	}


	@Provides
	@Singleton
	Cache provideOkHttpCache(Application application) {
		int cacheSize = 10 * 1024 * 1024; // 10 MiB
		Cache cache = new Cache(application.getCacheDir(), cacheSize);
		return cache;
	}

//	@Provides
//	@Singleton
//	Gson provideGson() {
//		GsonBuilder gsonBuilder = new GsonBuilder();
//		gsonBuilder.setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES);
//		return gsonBuilder.create();
//	}

	@Provides
	@Singleton
	OkHttpClient provideOkHttpClient(Cache cache) {
		OkHttpClient.Builder client = new OkHttpClient.Builder();
		client.cache(cache);
		return client.build();
	}

//	@Provides @Named("cached")
//	@Singleton
//	OkHttpClient provideOkHttpClient(Cache cache) {
//		OkHttpClient.Builder client = new OkHttpClient.Builder();
//		client.cache(cache);
//		return client.build();
//	}
//
//	@Provides @Named("non_cached")
//	@Singleton
//	OkHttpClient provideOkHttpClient() {
//		OkHttpClient client = new OkHttpClient();
//		return client;
//	}

	@Provides
	@Singleton
	Retrofit provideRetrofit(OkHttpClient okHttpClient) {
		Retrofit retrofit = new Retrofit.Builder()
			.baseUrl(baseUrl)
			.client(okHttpClient)
			.addConverterFactory(GsonConverterFactory.create())
			.addCallAdapterFactory(RxJavaCallAdapterFactory.create())
			.build();
		return retrofit;
	}

	@Provides
	@Singleton
	@SuppressWarnings("unused")
	public WeatherService providesNetworkService(
		Retrofit retrofit) {
		return retrofit.create(WeatherService.class);
	}

	@Provides
	@Singleton
	@SuppressWarnings("unused")
	public MainService providesService(
		WeatherService weatherService) {
		return new MainService(weatherService);
	}
}
