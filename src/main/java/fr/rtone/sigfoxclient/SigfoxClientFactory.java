/**
 * Copyright 2017 Rtone
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package fr.rtone.sigfoxclient;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import fr.rtone.sigfoxclient.util.SigfoxApiConstants;
import okhttp3.Credentials;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.jackson.JacksonConverterFactory;

import static okhttp3.logging.HttpLoggingInterceptor.Level;


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

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        // Build Retrofit client
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(SigfoxApiConstants.BASE_URL)
                .client(client)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(JacksonConverterFactory.create(objectMapper))
                .build();

        sigfoxClient = retrofit.create(SigfoxClient.class);


        return INSTANCE.sigfoxClient;
    }

}
