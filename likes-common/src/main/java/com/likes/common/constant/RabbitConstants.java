package com.likes.common.constant;

public class RabbitConstants {


    /**
     * 用户升级Exchange
     */
    public static final String TASK_TOPIC = "task_topic";

    public static final String YUEBAO_TOPIC = "yuebao_topic";

    public interface Queue {

        String TASK_QUEUE = "task_queue";

        String DAILY_QUEUE = "daily_queue";
        String SEVEN_DAY_QUEUE = "seven_day_queue";
        String TWO_WEEK_QUEUE = "two_week_queue";
        String MONTH_QUEUE = "month_queue";

    }


    public  interface Key {

        String TASK_KEY = "task_key";

        String DAILY_KEY = "daily_key";
        String SEVEN_DAY_KEY = "seven_day_key";
        String TWO_WEEK_KEY = "two_week_key";
        String MONTH_KEY = "month_key";

    }







}
