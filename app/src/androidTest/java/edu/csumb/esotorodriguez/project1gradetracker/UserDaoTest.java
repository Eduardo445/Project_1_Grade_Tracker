package edu.csumb.esotorodriguez.project1gradetracker;

import android.content.Context;

import androidx.room.Room;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class UserDaoTest {

    /**
     * This is the Test Unit for the User table.
     * Testing consists of:
     * - Adding users to the DB
     * - Check users were added to DB
     * - Check for users not in the DB
     * - Update one of the users currently in the DB
     * - Check updated user is up to date
     * - Delete user from the DB
     */
    @Test
    public void userDaoTest() {
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();

        UserDao userTestDB = Room.databaseBuilder(appContext, TrackerDB.class, TrackerDB.TRACKER_DB)
                .allowMainThreadQueries()
                .build()
                .userDao();

        User user1 = new User("Other", "Name", "Testing", "Pass");
        User user2 = new User("Else", "Past", "Order", "Name");
        User user3 = new User("Else", "Past", "Checking", "Name");

        userTestDB.insert(user1);
        userTestDB.insert(user2);
        userTestDB.insert(user3);

        user1.setUserID(userTestDB.getSpecificUser(user1.getUsername()).getUserID());
        user2.setUserID(userTestDB.getSpecificUser(user2.getUsername()).getUserID());
        user3.setUserID(userTestDB.getSpecificUser(user3.getUsername()).getUserID());

        assertNotNull(userTestDB.getAllUsers());
        assertNotNull(userTestDB.getSpecificUser("Testing"));
        assertNotNull(userTestDB.getSpecificUser("Order"));
        assertNotNull(userTestDB.getSpecificUser("Checking"));
        assertNull(userTestDB.getSpecificUser("Fail"));
        assertNull(userTestDB.getSpecificUser("Trickster"));

        assertEquals(user1.getFirstName(), userTestDB.getSpecificUser("Testing").getFirstName());
        assertEquals(user1.getLastName(), userTestDB.getSpecificUser("Testing").getLastName());
        assertEquals(user1.getUsername(), userTestDB.getSpecificUser("Testing").getUsername());
        assertEquals(user1.getPassword(), userTestDB.getSpecificUser("Testing").getPassword());

        user2.setFirstName("Night");
        user2.setLastName("Time");
        user2.setUsername("Eagle");
        user2.setPassword("Eye");

        userTestDB.update(user2);

        assertNull(userTestDB.getSpecificUser("Order"));
        assertNotNull(userTestDB.getSpecificUser("Eagle"));

        assertEquals(user2.getFirstName(), userTestDB.getSpecificUser("Eagle").getFirstName());
        assertEquals(user2.getLastName(), userTestDB.getSpecificUser("Eagle").getLastName());
        assertEquals(user2.getUsername(), userTestDB.getSpecificUser("Eagle").getUsername());
        assertEquals(user2.getPassword(), userTestDB.getSpecificUser("Eagle").getPassword());

        userTestDB.delete(user1);
        userTestDB.delete(user2);
        userTestDB.delete(user3);
    }
}
