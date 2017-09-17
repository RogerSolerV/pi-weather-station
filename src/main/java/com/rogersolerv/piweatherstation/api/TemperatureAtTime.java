package com.rogersolerv.piweatherstation.api;

import java.util.Date;

public class TemperatureAtTime {

    private long wId;
    private int sourceId;
    private Date timestamp;
    private double temperature;
    private double humidity;

    public TemperatureAtTime() {
    }

    public TemperatureAtTime(long wId, int sourceId, Date timestamp, double temperature, double humidity) {
	super();
	this.wId = wId;
	this.sourceId = sourceId;
	this.timestamp = timestamp;
	this.temperature = temperature;
	this.humidity = humidity;
    }

    public long getwId() {
	return wId;
    }

    public int getSourceId() {
	return sourceId;
    }

    public Date getTimestamp() {
	return timestamp;
    }

    public double getTemperature() {
	return temperature;
    }

    public double getHumidity() {
	return humidity;
    }

    @Override
    public int hashCode() {
	final int prime = 31;
	int result = 1;
	long temp;
	temp = Double.doubleToLongBits(humidity);
	result = prime * result + (int) (temp ^ (temp >>> 32));
	result = prime * result + sourceId;
	temp = Double.doubleToLongBits(temperature);
	result = prime * result + (int) (temp ^ (temp >>> 32));
	result = prime * result + ((timestamp == null) ? 0 : timestamp.hashCode());
	result = prime * result + (int) (wId ^ (wId >>> 32));
	return result;
    }

    @Override
    public boolean equals(Object obj) {
	if (this == obj)
	    return true;
	if (obj == null)
	    return false;
	if (getClass() != obj.getClass())
	    return false;
	TemperatureAtTime other = (TemperatureAtTime) obj;
	if (Double.doubleToLongBits(humidity) != Double.doubleToLongBits(other.humidity))
	    return false;
	if (sourceId != other.sourceId)
	    return false;
	if (Double.doubleToLongBits(temperature) != Double.doubleToLongBits(other.temperature))
	    return false;
	if (timestamp == null) {
	    if (other.timestamp != null)
		return false;
	} else if (!timestamp.equals(other.timestamp))
	    return false;
	if (wId != other.wId)
	    return false;
	return true;
    }

    @Override
    public String toString() {
	return "TemperatureAtTime [wId=" + wId + ", sourceId=" + sourceId + ", timestamp=" + timestamp
		+ ", temperature=" + temperature + ", humidity=" + humidity + "]";
    }

}
