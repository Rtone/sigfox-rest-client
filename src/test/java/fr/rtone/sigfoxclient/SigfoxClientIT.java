package fr.rtone.sigfoxclient;

import fr.rtone.sigfoxclient.model.SigfoxData;
import fr.rtone.sigfoxclient.model.Group;
import okhttp3.logging.HttpLoggingInterceptor;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import retrofit2.adapter.rxjava.HttpException;
import rx.observers.TestSubscriber;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @Author: Hani
 */
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
