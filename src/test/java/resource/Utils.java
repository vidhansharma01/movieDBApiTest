package resource;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Properties;

public class Utils {
    private RequestSpecification reqSpec;
    private Properties properties;
    public RequestSpecification requestSpecification() throws IOException {
        PrintStream log =new PrintStream(new FileOutputStream("log.txt"));
        init();
        reqSpec = new RequestSpecBuilder().setBaseUri(getUri()).addQueryParam("api_key", getKey())
                .addQueryParam("guest_session_id", getSessionId())
                .addFilter(RequestLoggingFilter.logRequestTo(log))
                .setContentType(ContentType.JSON).build();
        return reqSpec;
    }
    public void init() throws IOException{
        FileInputStream fs = new FileInputStream("C:\\Users\\Vidhan Chandra\\Downloads\\movieDB\\src\\test\\java\\RestAssured\\Api.properties");
        properties = new Properties();
        properties.load(fs);
    }
    public String getKey(){
        String key = properties.getProperty("api_key");
        return key;
    }
    public String getUri(){
        String uri = properties.getProperty("baseUri");
        return uri;
    }
    public String getSessionId(){
        String session_id = properties.getProperty("guest_session_id");
        return session_id;
    }

}
