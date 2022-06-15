package team.devim.Ability;

public class Constants {
    public static final String BOT_USERNAME = System.getenv("BOT_USERNAME");
    public static final String BOT_TOKEN = System.getenv("BOT_TOKEN");
    public static final String CREATOR_ID = System.getenv("CREATOR_ID");
    public static final String FLIGHT_SERVICE_HOST_GET = System.getenv("FLIGHT_SERVICE_HOST_GET");
    public static final String FLIGHT_SERVICE_HOST_POST = System.getenv("FLIGHT_SERVICE_HOST_POST");
    public  static final boolean PROCESS_GET = Boolean.parseBoolean(System.getenv("PROCESS_GET"));
}