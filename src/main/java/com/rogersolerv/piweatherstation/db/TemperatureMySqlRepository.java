package com.rogersolerv.piweatherstation.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.Date;

import org.apache.log4j.Logger;

import com.rogersolerv.piweatherstation.api.TemperatureAtTime;

public class TemperatureMySqlRepository implements TemperatureRepository {

    private static final Logger LOG = Logger.getLogger(TemperatureMySqlRepository.class);

    @Override
    public Connection createConnection() throws SQLException {
	return DriverManager.getConnection("jdbc:mysql://localhost/test?" + "user=minty&password=greatsqldb");
    }

    @Override
    public long save(TemperatureAtTime temperature) {
	try (Connection connection = createConnection();
		PreparedStatement preparedStatement = connection.prepareStatement(
			"INSERT weather_data ('sourceid', 'date', 'temperature', 'humidity') VALUES (?,?,?,?)",
			Statement.RETURN_GENERATED_KEYS);) {
	    preparedStatement.setInt(1, temperature.getSourceId());
	    preparedStatement.setTimestamp(2, new Timestamp(temperature.getTimestamp().getTime()));
	    preparedStatement.setDouble(3, temperature.getTemperature());
	    preparedStatement.setDouble(4, temperature.getHumidity());

	    preparedStatement.executeUpdate();

	    ResultSet tableKeys = preparedStatement.getGeneratedKeys();
	    tableKeys.next();
	    long autoGeneratedID = tableKeys.getLong(1);
	    return autoGeneratedID;
	} catch (SQLException e) {
	    LOG.error("Error inserting a new Temperature", e);
	}
	return 0;
    }

    @Override
    public TemperatureAtTime getTemperatureById(long getwId) {
	try (Connection connection = createConnection();
		PreparedStatement ps = connection.prepareStatement("SELECT * FROM weather_data wd WHERE wd.wid");
		ResultSet rs = ps.executeQuery()) {

	    if (rs.next()) {
		long wId = rs.getLong("wid");
		int sourceId = rs.getInt("sourceid");
		Date timestamp = rs.getTimestamp("date");
		double temperature = rs.getDouble("temperature");
		double humidity = rs.getDouble("humidity");

		return new TemperatureAtTime(wId, sourceId, timestamp, temperature, humidity);
	    }
	} catch (SQLException e) {
	    LOG.error("Error inserting a new Temperature", e);
	}
	return null;
    }

}
