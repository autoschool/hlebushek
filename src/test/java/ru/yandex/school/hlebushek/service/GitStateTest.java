package ru.yandex.school.hlebushek.service;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.junit.Before;
import org.junit.Test;
import ru.yandex.school.hlebushek.common.GitRepositoryState;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertThat;

import javax.ws.rs.core.Application;
import java.io.IOException;

/**
 * Created by ksenie on 25.01.15.
 */
public class GitStateTest extends JerseyTest{

    private GitRepositoryState gitRepositoryState;
    private ServiceGate serviceGate;

    @Override
    protected Application configure() {
        return new ResourceConfig(ServiceGate.class);
    }

    @Before
    public void testPrepare(){
        serviceGate = new ServiceGate();
    }

    @Test
    public void gitStateShouldBeNotNull() throws IOException {
        gitRepositoryState = serviceGate.getGitState();
        assertThat(gitRepositoryState, notNullValue());
    }

}
