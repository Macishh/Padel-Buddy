package com.danielkarlkvist.padelbuddy.Controller;

import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.AndroidJUnit4;

import com.danielkarlkvist.padelbuddy.MainActivity;
import com.danielkarlkvist.padelbuddy.Model.PadelBuddy;
import com.danielkarlkvist.padelbuddy.Model.Player;
import com.danielkarlkvist.padelbuddy.R;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.doesNotExist;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isClickable;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.junit.Assert.*;

/**
 * The ProfileFragmentControllerTest class defines Android instrumented unit test
 *
 * @author Robin Repo Wecklauf
 * @version 1.0
 * @since 2019-10-08
 */

@RunWith(AndroidJUnit4.class)
@LargeTest
public class ProfileFragmentControllerTest {

    private ProfileFragmentController profile;
    private PadelBuddy padelBuddy;
    private Player user;

    /**
     * Provides functional testing of a MainActivity
     */

    @Rule
    public ActivityTestRule<MainActivity> activityRule =
            new ActivityTestRule<>(MainActivity.class);

    /**
     * Initializes the fragment to test and allows to read and manipulate all id's in the given fragment
     *
     * @throws Exception any error
     */

    @Before
    public void setUp() throws Exception {
        profile = new ProfileFragmentController();
        padelBuddy = PadelBuddy.getInstance();
        user = padelBuddy.getPlayer();
        activityRule.getActivity().getSupportFragmentManager().beginTransaction().add(R.id.fragment_container, profile, "Profile").commit();
    }

    /**
     * Decides what to do after the tests is done
     *
     * @throws Exception any error
     */

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void profileIsNotNull() throws Exception {
        assertNotNull(onView(withId(R.id.profile_fragment)));
    }

    @Test
    public void shouldExist() throws Exception {
        onView(withId(R.id.profile_games_played)).check(matches(withText("Antal spelade matcher: " + user.getGamesPlayed())));
    }
}