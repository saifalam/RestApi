package com.alam.saif.messenger.dataStorage;

import com.alam.saif.messenger.model.Message;
import com.alam.saif.messenger.model.Profile;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by BS102-Saif on 3/4/2016.
 */
public class DataStorage {

    private static Map<Long, Message> messages = new HashMap<>();
    private static Map<String, Profile> profiles = new HashMap<>();

    public static Map<Long, Message> getMessages() {
        return messages;
    }

    public static void setMessages(Map<Long, Message> messages) {
        DataStorage.messages = messages;
    }

    public static Map<String, Profile> getProfiles() {
        return profiles;
    }

    public static void setProfiles(Map<String, Profile> profiles) {
        DataStorage.profiles = profiles;
    }
}
