package pe.edu.upc.testingapirest.jsonserver.runner;

import io.cucumber.spring.CucumberContextConfiguration;
import org.springframework.boot.test.context.SpringBootContextLoader;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import pe.edu.upc.testingapirest.TestingApirestApplication;

/*@CucumberContextConfiguration
@SpringBootTest(classes = TestingApirestApplication.class,
    webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ContextConfiguration(classes = TestingApirestApplication.class, loader = SpringBootContextLoader.class)*/
@CucumberContextConfiguration
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CucumberSpringConfiguration {
}
