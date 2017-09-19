package com.rogersolerv.piweatherstation;

import java.util.Optional;

import com.github.kristofa.brave.Brave;
import com.rogersolerv.piweatherstation.health.TemplateHealthCheck;
import com.rogersolerv.piweatherstation.resources.PiWeatherStationResource;
import com.smoketurner.dropwizard.zipkin.ZipkinBundle;
import com.smoketurner.dropwizard.zipkin.ZipkinFactory;

import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import io.federecio.dropwizard.swagger.SwaggerBundle;
import io.federecio.dropwizard.swagger.SwaggerBundleConfiguration;

public class PiWeatherStationApplication extends Application<PiWeatherStationConfiguration> {

    @Override
    public String getName() {
	return "PiWeatherStation";
    }

    @Override
    public void initialize(Bootstrap<PiWeatherStationConfiguration> bootstrap) {
	bootstrap.addBundle(new ZipkinBundle<PiWeatherStationConfiguration>(getName()) {
	    @Override
	    public ZipkinFactory getZipkinFactory(PiWeatherStationConfiguration configuration) {
		return configuration.getZipkinFactory();
	    }
	});

	bootstrap.addBundle(new SwaggerBundle<PiWeatherStationConfiguration>() {
	    @Override
	    protected SwaggerBundleConfiguration getSwaggerBundleConfiguration(
		    PiWeatherStationConfiguration configuration) {
		return configuration.swaggerBundleConfiguration;
	    }
	});
    }

    @Override
    public void run(PiWeatherStationConfiguration configuration, Environment environment) {

	// Reporter<Brave> reporter =
	// AsyncReporter<Brave>.builder(OkHttpSender.create("1.2.3.4:9411/api/v1/spans")).build();
	// Brave brave = Brave.Builder("example").reporter(reporter).build()
	Optional<Brave> braveOpt = configuration.getZipkinFactory().build(environment);
	Brave brave = braveOpt.get();
	
	//PROJECT_ID=pi-weather-cloud-app GOOGLE_APPLICATION_CREDENTIALS=PiWeatherCloudApp-3a67498d3e0d.json java -jar collector-0.3.0.jar
	// TODO SpanId span = brave.localTracer().startNewSpan(getName(), "test");
	// try {
	// doSomethingExpensive();
	// } finally {
	// span.finish();
	// }

	final PiWeatherStationResource resource = new PiWeatherStationResource(configuration.getTemplate(),
		configuration.getDefaultName());
	environment.jersey().register(resource);

	final TemplateHealthCheck healthCheck = new TemplateHealthCheck(configuration.getTemplate());
	environment.healthChecks().register("template", healthCheck);
	environment.jersey().register(resource);
    }

    public static void main(String[] args) throws Exception {
	new PiWeatherStationApplication().run(args);
    }

}
