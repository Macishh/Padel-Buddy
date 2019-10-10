package com.danielkarlkvist.padelbuddy.Model;

import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.*;

public class PadelBuddyTest {

    private PadelBuddy padelBuddy;

    @Test
    public void createAd_GameExist_ReturnsTrue() {
        padelBuddy = PadelBuddy.getInstance();
        padelBuddy.createAd("Göteborg", new Date(2019, 11, 12));
        assertTrue(padelBuddy.getGames().size() > 0);
    }

}