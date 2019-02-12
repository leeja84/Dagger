package jaeyoung.com.dagger.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import javax.annotation.Generated;

/**
 * Created by jaeyoung lee on 2019. 2. 12..
 */
@Generated("org.jsonschema2pojo")
public class WeatherData {
	@SerializedName("id")
	@Expose
	String id;
	@SerializedName("main")
	@Expose
	String main;
	@SerializedName("description")
	@Expose
	String description;
	@SerializedName("icon")
	@Expose
	String icon;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getMain() {
		return main;
	}

	public void setMain(String main) {
		this.main = main;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}
}
