package photoclient.lazada.com.photoclient.net;

import com.google.gson.Gson;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import photoclient.lazada.com.photoclient.utility.Constants;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by avi_chopra on 15/06/16.
 */
public class RetrofitInstance {

    private static RetrofitInstance instance = new RetrofitInstance();

    public static RetrofitInstance getInstance() {
        if(instance == null){
            instance = new RetrofitInstance();
        }
        return instance;
    }

    public Retrofit getRetrofit() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder()
                .retryOnConnectionFailure(true)
                .connectTimeout(Constants.TIMEOUT, TimeUnit.SECONDS)
                .addInterceptor(interceptor)
                .addInterceptor(new Interceptor() {
                    @Override
                    public Response intercept(Chain chain) throws IOException {
                        Request request = chain.request();
                        HttpUrl url = request.url()
                                .newBuilder()
                                .addQueryParameter(Constants.consumer_key_name ,Constants.consumer_key_value)
                                .addQueryParameter(Constants.feature_key_name, Constants.feature_key_value)
                                .build();

                        request = request.newBuilder()
                                .url(url)
                                .build();

                        Response response = chain.proceed(request);
                        return response;
                    }
                })
                .build();


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();

        return retrofit;
    }

    /**
     * Get the singleton Retrofit API
     * @param serviceClass
     * @param <S>
     * @return
     */
    public <S> S createService(Class<S> serviceClass) {
        return getRetrofit().create(serviceClass);
    }
}
