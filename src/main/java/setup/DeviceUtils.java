package setup;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;

import java.io.File;

import static io.restassured.RestAssured.given;

public class DeviceUtils {
    public static void installApk(String endpoint, String token, String udid, String filePath) {
        RequestSpecification REQUEST_SPECIFICATION = new RequestSpecBuilder()
                .setBaseUri(endpoint + udid)
                .addHeader("Authorization", "Bearer " + "287b38ef-4799-4371-b8b4-44a01ba5c30d")
                .addMultiPart(new File(filePath))
                .setRelaxedHTTPSValidation()
                .build();

        io.restassured.response.Response response = given(REQUEST_SPECIFICATION)
                .post();
        System.out.println("Apk installation post response with code: " + response.getStatusCode());
    }
}
