package com.rogersolerv.piweatherstation.db;

import java.sql.Connection;
import java.sql.SQLException;

import com.rogersolerv.piweatherstation.api.TemperatureAtTime;

public interface TemperatureRepository {

    Connection createConnection() throws SQLException;

    long save(TemperatureAtTime temperature);

    TemperatureAtTime getTemperatureById(long getwId);

}
