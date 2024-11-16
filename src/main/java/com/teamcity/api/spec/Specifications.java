package com.teamcity.api.spec;

import com.teamcity.api.config.Configuration;
import com.teamcity.api.models.User;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

public class Specifications {
    private static Specifications spec;

    private Specifications() {
    }

    public static Specifications getSpec() {
        if (spec == null) {
            spec = new Specifications();
        }
        return spec;
    }

    public RequestSpecification unAuthSpec() {
        RequestSpecBuilder requestBuilder = requestSpecBuilder();
        requestBuilder.setContentType(ContentType.JSON);
        requestBuilder.setAccept(ContentType.JSON);
        return requestBuilder.build();
    }

    public RequestSpecification authSpec(User user) {
        RequestSpecBuilder requestBuilder = requestSpecBuilder();
        requestBuilder.setContentType(ContentType.JSON);
        requestBuilder.setAccept(ContentType.JSON);
        requestBuilder.setBaseUri("http://" + user.getUsername() + ":" + user.getPassword() + "@" + Configuration.getProperty("base.api.host"));
        return requestBuilder.build();
    }

    private RequestSpecBuilder requestSpecBuilder() {
        RequestSpecBuilder requestBuilder = new RequestSpecBuilder();
        requestBuilder.addFilter(new RequestLoggingFilter());
        requestBuilder.addFilter(new ResponseLoggingFilter());
        return requestBuilder;
    }
}
