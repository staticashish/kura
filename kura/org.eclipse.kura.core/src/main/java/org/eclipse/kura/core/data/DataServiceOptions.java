package org.eclipse.kura.core.data;

import static java.util.Objects.requireNonNull;

import java.util.Map;
import java.util.concurrent.TimeUnit;

public class DataServiceOptions {

    private static final String AUTOCONNECT_PROP_NAME = "connect.auto-on-startup";
    private static final String CONNECT_DELAY_PROP_NAME = "connect.retry-interval";
    private static final String DISCONNECT_DELAY_PROP_NAME = "disconnect.quiesce-timeout";
    private static final String STORE_HOUSEKEEPER_INTERVAL_PROP_NAME = "store.housekeeper-interval";
    private static final String STORE_PURGE_AGE_PROP_NAME = "store.purge-age";
    private static final String STORE_CAPACITY_PROP_NAME = "store.capacity";
    private static final String REPUBLISH_IN_FLIGHT_MSGS_PROP_NAME = "in-flight-messages.republish-on-new-session";
    private static final String MAX_IN_FLIGHT_MSGS_PROP_NAME = "in-flight-messages.max-number";
    private static final String IN_FLIGHT_MSGS_CONGESTION_TIMEOUT_PROP_NAME = "in-flight-messages.congestion-timeout";
    private static final String RATE_LIMIT_ENABLE_PROP_NAME = "enable.rate.limiting";
    private static final String AVERAGE_PUBLISH_RATE_PROP_NAME = "average.publish.rate";
    private static final String AVERAGE_PUBLISH_RATE_TIME_UNIT_PROP_NAME = "average.publish.rate.time.unit";
    private static final String BURST_SIZE_PROP_NAME = "publish.burst.size";

    private static final boolean AUTOCONNECT_PROP_DEFAULT = false;
    private static final int CONNECT_DELAY_DEFAULT = 60;
    private static final int DISCONNECT_DELAY_DEFAULT = 10;
    private static final int STORE_HOUSEKEEPER_INTERVAL_DEFAULT = 900;
    private static final int STORE_PURGE_AGE_DEFAULT = 60;
    private static final int STORE_CAPACITY_DEFAULT = 10000;
    private static final boolean REPUBLISH_IN_FLIGHT_MSGS_DEFAULT = true;
    private static final int MAX_IN_FLIGHT_MSGS_DEFAULT = 9;
    private static final int IN_FLIGHT_MSGS_CONGESTION_TIMEOUT_DEFAULT = 0;
    private static final boolean RATE_LIMIT_ENABLE_DEFAULT = false;
    private static final int AVERAGE_PUBLISH_RATE_DEFAULT = 1;
    private static final String AVERAGE_PUBLISH_RATE_TIME_UNIT_DEFAULT = "SECONDS";
    private static final int BURST_SIZE_DEFAULT = 10;

    private final Map<String, Object> properties;

    DataServiceOptions(Map<String, Object> properties) {
        requireNonNull(properties, "Required not null");
        this.properties = properties;
    }

    int getStoreHousekeeperInterval() {
        return (int) this.properties.getOrDefault(STORE_HOUSEKEEPER_INTERVAL_PROP_NAME,
                STORE_HOUSEKEEPER_INTERVAL_DEFAULT);
    }

    int getStorePurgeAge() {
        return (int) this.properties.getOrDefault(STORE_PURGE_AGE_PROP_NAME, STORE_PURGE_AGE_DEFAULT);
    }

    int getStoreCapacity() {
        return (int) this.properties.getOrDefault(STORE_CAPACITY_PROP_NAME, STORE_CAPACITY_DEFAULT);
    }

    boolean isPublishInFlightMessages() {
        return (boolean) this.properties.getOrDefault(REPUBLISH_IN_FLIGHT_MSGS_PROP_NAME,
                REPUBLISH_IN_FLIGHT_MSGS_DEFAULT);
    }

    int getMaxInFlightMessages() {
        return (int) this.properties.getOrDefault(MAX_IN_FLIGHT_MSGS_PROP_NAME, MAX_IN_FLIGHT_MSGS_DEFAULT);
    }

    int getInFlightMessagesCongestionTimeout() {
        return (int) this.properties.getOrDefault(IN_FLIGHT_MSGS_CONGESTION_TIMEOUT_PROP_NAME,
                IN_FLIGHT_MSGS_CONGESTION_TIMEOUT_DEFAULT);
    }

    boolean isAutoConnect() {
        return (boolean) this.properties.getOrDefault(AUTOCONNECT_PROP_NAME, AUTOCONNECT_PROP_DEFAULT);
    }

    int getConnectDelay() {
        return (int) this.properties.getOrDefault(CONNECT_DELAY_PROP_NAME, CONNECT_DELAY_DEFAULT);
    }

    int getDisconnectDelay() {
        return (int) this.properties.getOrDefault(DISCONNECT_DELAY_PROP_NAME, DISCONNECT_DELAY_DEFAULT);
    }

    boolean isRateLimitEnabled() {
        return (boolean) this.properties.getOrDefault(RATE_LIMIT_ENABLE_PROP_NAME, RATE_LIMIT_ENABLE_DEFAULT);
    }

    int getAveragePublishRate() {
        return (int) this.properties.getOrDefault(AVERAGE_PUBLISH_RATE_PROP_NAME, AVERAGE_PUBLISH_RATE_DEFAULT);
    }

    int getBurstSize() {
        return (int) this.properties.getOrDefault(BURST_SIZE_PROP_NAME, BURST_SIZE_DEFAULT);
    }

    long getSimpleTimeUnitMultiplier() throws IllegalArgumentException {
        String timeUnitString = (String) properties.getOrDefault(AVERAGE_PUBLISH_RATE_TIME_UNIT_PROP_NAME,
                AVERAGE_PUBLISH_RATE_TIME_UNIT_DEFAULT);
        TimeUnit timeUnit;

        if (TimeUnit.MILLISECONDS.name().equals(timeUnitString)) {
            timeUnit = TimeUnit.MILLISECONDS;
        } else if (TimeUnit.SECONDS.name().equals(timeUnitString)) {
            timeUnit = TimeUnit.SECONDS;
        } else if (TimeUnit.MINUTES.name().equals(timeUnitString)) {
            timeUnit = TimeUnit.MINUTES;
        } else if (TimeUnit.HOURS.name().equals(timeUnitString)) {
            timeUnit = TimeUnit.HOURS;
        } else if (TimeUnit.DAYS.name().equals(timeUnitString)) {
            timeUnit = TimeUnit.DAYS;
        } else {
            throw new IllegalArgumentException("Illegal time unit");
        }

        return timeUnit.toMillis(1);
    }
}
