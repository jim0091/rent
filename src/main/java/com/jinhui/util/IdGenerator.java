package com.jinhui.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.TimeZone;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

public class IdGenerator {
    private static AtomicInteger sequence = new AtomicInteger(10000);

    private static class Timestamp {

        private static ThreadLocal<DateFormat> DATA_FORMAT = new ThreadLocal<DateFormat>() {
            @Override
            public DateFormat get() {
                return super.get();
            }

            @Override
            protected DateFormat initialValue() {
                DateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
                df.setTimeZone(TimeZone.getTimeZone("GMT+08"));
                df.setLenient(false);
                return df;
            }

            @Override
            public void remove() {
                super.remove();
            }

            @Override
            public void set(DateFormat value) {
                super.set(value);
            }

        };

        private static synchronized String currentTime() {
            return DATA_FORMAT.get().format(System.currentTimeMillis());
        }
    }

    public static String generate() {
        String uuid = UUID.randomUUID().toString();
        StringBuilder appender = new StringBuilder(30);
        String timestamp = Timestamp.currentTime();
        int sequence = nextSeq();
        appender.append(timestamp).append(uuid.substring(0,6)).append(sequence);
        return appender.toString();
    }

    private static int nextSeq() {
        for (;;) {
            int current = sequence.get();
            int next = (current > 90000) ? 10000 : current + 1;
            if (sequence.compareAndSet(current, next))
                return next;
        }
    }
}
