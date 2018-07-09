package lumut.app.trackapp;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class HttpClient {

    OkHttpClient client = new OkHttpClient();
    Call call;
    Request request;
    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");

    public String doGetRequest(String url) throws IOException {
        request = new Request.Builder()
                .url(url)
                .build();

        call = client.newCall(request);

        Response response = call.execute();
        return response.body().string();
    }

}
