package com.alam.saif.messenger.service;

import com.alam.saif.messenger.DataStorage.DataStorage;
import com.alam.saif.messenger.model.Profile;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

/**
 * Created by BS102-Saif on 3/4/2016.
 */
public class ProfileService {
    private Map<String,Profile> profiles = DataStorage.getProfiles();

    public List<Profile> getAllProfiles() {
        return new ArrayList<Profile>(profiles.values());
    }

    public List<Profile> getAllProfilesForYear(int year) {
        List<Profile> profileList = new ArrayList<>();
        Calendar calendar = Calendar.getInstance();
        for(Profile profile : profiles.values()) {
            calendar.setTime(profile.getCreated());
            if(calendar.get(calendar.YEAR) == year) {
                profileList.add(profile);
            }
        }
        return profileList;
    }

    public List<Profile> getAllProfilesPaginated(int start, int size) {
        List<Profile> profileList = new ArrayList<>(profiles.values());

        if(start+size > profiles.size()) {
            return new ArrayList<>();
        }

        return  profileList.subList(start, start+size);
    }

    public Profile createProfile(Profile profile) {
        profile.setId(profiles.size()+1);
        profiles.put(profile.getProfileName(),profile);
        return profile;
    }

    public Profile getProfileByName(String name) {
        Profile profile = new Profile();

        for(Profile temp : profiles.values()) {
            if(name.equals(temp.getProfileName())) {
                profile = temp;
            }
        }
        return profile;
    }

    public Profile updateProfile(Profile profile, String name) {
        for(Profile temp : profiles.values()) {
            if(name.equals(temp.getProfileName())) {
                profile.setId(temp.getId());
                profiles.put(name,profile);
            }
        }
        return profile;
    }

    public void deleteProfile(String name) {
        for(Profile profile : profiles.values()) {
            if(name.equals(profile.getProfileName())) {
                profiles.remove(name);
                break;
            }
        }
    }
}
