package com.rogersolerv.piweatherstation.db;

import static org.junit.Assert.assertEquals;

import java.util.Date;

import org.junit.Test;

import com.rogersolerv.piweatherstation.api.TemperatureAtTime;
import com.rogersolerv.piweatherstation.api.WeatherStationIds;

public class TemperatureRepositoryTest {

    private TemperatureRepository repository = new TemperatureMySqlRepository();

    @Test
    public void shouldRetrieveSavedTemperature() {
	TemperatureAtTime temperature = sampeTemperature();
	long newId = repository.save(temperature);
	assertEquals(temperature, repository.getTemperatureById(newId));
    }

    private TemperatureAtTime sampeTemperature() {
	return new TemperatureAtTime(-1, WeatherStationIds.OTHILIE_TONNINGS_VEI_18, new Date(), 12.4, 75.4);
    }
}
