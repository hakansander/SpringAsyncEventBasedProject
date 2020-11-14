package com.segmentify.events.util;

import java.time.Instant;

public class TimeUtil {

    public static String getCurrentUnixTimestamp() {
        return Long.toString(Instant.now().getEpochSecond());
    }

}
