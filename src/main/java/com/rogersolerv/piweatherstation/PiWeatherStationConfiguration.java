package com.rogersolerv.piweatherstation;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.smoketurner.dropwizard.zipkin.LoggingZipkinFactory;
import com.smoketurner.dropwizard.zipkin.ZipkinFactory;
import com.smoketurner.dropwizard.zipkin.client.ZipkinClientConfiguration;

import io.dropwizard.Configuration;
import io.federecio.dropwizard.swagger.SwaggerBundleConfiguration;

public class PiWeatherStationConfiguration extends Configuration {

    @NotEmpty
    private String template;

    @NotEmpty
    private String defaultName = "Stranger";

    @Valid
    @NotNull
    public final ZipkinFactory zipkin = new LoggingZipkinFactory();

    @Valid
    @NotNull
    private final ZipkinClientConfiguration zipkinClient = new ZipkinClientConfiguration();

    @JsonProperty("swagger")
    public SwaggerBundleConfiguration swaggerBundleConfiguration;

    @JsonProperty
    public String getTemplate() {
	return template;
    }

    @JsonProperty
    public void setTemplate(String template) {
	this.template = template;
    }

    @JsonProperty
    public String getDefaultName() {
	return defaultName;
    }

    @JsonProperty
    public void setDefaultName(String name) {
	this.defaultName = name;
    }

    @JsonProperty
    public ZipkinFactory getZipkinFactory() {
	return zipkin;
    }

    @JsonProperty
    public ZipkinClientConfiguration getZipkinClient() {
	return zipkinClient;
    }
}
