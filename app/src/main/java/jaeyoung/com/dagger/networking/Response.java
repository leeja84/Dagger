package jaeyoung.com.dagger.networking;

import com.google.gson.annotations.SerializedName;

/**
 * Created by jaeyoung lee on 2019. 2. 12..
 */
public class Response {
    @SerializedName("status")
    public String status;

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    @SuppressWarnings({"unused", "used by Retrofit"})
    public Response() {
    }

    public Response(String status) {
        this.status = status;
    }
}
