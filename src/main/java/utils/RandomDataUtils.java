package utils;

import java.util.UUID;

public final class RandomDataUtils {
    private RandomDataUtils() {
    }

    public static String randomUserName() {
        return "user_" + UUID.randomUUID().toString().substring(0, 8);
    }

    public static String randomQuestText() {
        return "quest_" + UUID.randomUUID().toString().substring(0, 12);
    }
}
