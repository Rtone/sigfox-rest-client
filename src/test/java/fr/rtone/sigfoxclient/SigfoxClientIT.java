/**
 * Copyright 2020 Rtone
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

import fr.rtone.sigfoxclient.model.Group;
import fr.rtone.sigfoxclient.model.SigfoxData;
import okhttp3.logging.HttpLoggingInterceptor;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import retrofit2.HttpException;
import rx.observers.TestSubscriber;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


@Category(SigfoxIntegrationTest.class)
public class SigfoxClientIT {

    private SigfoxClient sigfoxClient;

    private String login;
    private String password;

    @Before
    public void setup() {
        login = System.getProperty("sigfox.login");
        password = System.getProperty("sigfox.password");

        this.sigfoxClient = SigfoxClientFactory.create()
                .setLoggingLevel(HttpLoggingInterceptor.Level.BODY)
                .setCredentials(login, password)
                .build();
    }

    @Test
    public void groupApiShouldReturnGroupList() {
        // Given
        TestSubscriber<SigfoxData<Group>> testObserver = new TestSubscriber<>();

        // When
        sigfoxClient.getGroupList(null, null, null).subscribe(testObserver);

        // Then
        testObserver.assertNoErrors();
        List<SigfoxData<Group>> sigfoxData = testObserver.getOnNextEvents();
        assertThat(sigfoxData).isNotNull().isNotEmpty();
    }

    @Test
    public void groupApishouldReturnBadRequest() {
        // Given
        login = "wrongLogin";
        password = "wrongPassword";
        TestSubscriber<SigfoxData<Group>> testObserver = new TestSubscriber<>();

        // When
        sigfoxClient.getGroupList(null, null, null).subscribe(testObserver);

        // Then
        testObserver.assertError(HttpException.class);
        List<Throwable> errorEvents = testObserver.getOnErrorEvents();
        assertThat(errorEvents).isNotNull().isNotEmpty();
        assertThat(errorEvents.get(0)).hasMessage("HTTP 400 Bad Request");
    }


}
