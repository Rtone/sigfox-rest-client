package fr.rtone.sigfoxclient;

import fr.rtone.sigfoxclient.util.SigfoxApiConstants;
import okhttp3.Credentials;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.jackson.JacksonConverterFactory;

import static okhttp3.logging.HttpLoggingInterceptor.Level;

/**
 * @Author: Hani
 */
public class SigfoxClientFactory {

    private static SigfoxClientFactory INSTANCE;

    private SigfoxClient sigfoxClient;

    private HttpLoggingInterceptor loggingInterceptor;

    private String credentials;

    private SigfoxClientFactory() {
        // Configure default Logging Level
        loggingInterceptor = new HttpLoggingInterceptor();

        // No configured authentication
        credentials = null;
    }

    public static SigfoxClientFactory create() {
        INSTANCE = new SigfoxClientFactory();
        return INSTANCE;
    }

    public SigfoxClientFactory setLoggingLevel(Level level) {
        this.loggingInterceptor.setLevel(level);
        return INSTANCE;
    }

    public SigfoxClientFactory setCredentials(String login, String password) {
        this.credentials = Credentials.basic(login, password);
        return INSTANCE;
    }


    public SigfoxClient build() {

        // OkHttp client creation & configuration
        OkHttpClient.Builder builder = new OkHttpClient.Builder();

        // Configure logging level
        builder.interceptors().add(loggingInterceptor);

        // Configure authentication

        if (credentials != null && !credentials.isEmpty()) {
            builder.authenticator((route, response) -> response.request().newBuilder().addHeader("Authorization", credentials).build());
        }

        OkHttpClient client = builder.build();

        // Build Retrofit client
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(SigfoxApiConstants.BASE_URL)
                .client(client)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(JacksonConverterFactory.create())
                .build();

        sigfoxClient = retrofit.create(SigfoxClient.class);


        return INSTANCE.sigfoxClient;
    }

}
