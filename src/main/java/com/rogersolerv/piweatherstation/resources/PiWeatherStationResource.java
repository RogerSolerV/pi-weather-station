package com.rogersolerv.piweatherstation.resources;

import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.codahale.metrics.annotation.Timed;
import com.rogersolerv.piweatherstation.api.TemperatureAtTime;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Path("/pi-weather-station")
@Api("/pi-weather-station")
@Produces(MediaType.APPLICATION_JSON)
public class PiWeatherStationResource {

    private final String template;
    private final String defaultName;
    private final AtomicLong counter;

    public PiWeatherStationResource(String template, String defaultName) {
	this.template = template;
	this.defaultName = defaultName;
	this.counter = new AtomicLong();
    }

    @GET
    @Timed
    @ApiOperation(value = "Get something", response = TemperatureAtTime.class)
    public TemperatureAtTime sayHello(@QueryParam("name") Optional<String> name) {
	final String value = String.format(template, name.orElse(defaultName));
	// try {
	// doSomethingExpensive();
	// } finally {
	// span.finish();
	// }
	return new TemperatureAtTime();
    }
}
