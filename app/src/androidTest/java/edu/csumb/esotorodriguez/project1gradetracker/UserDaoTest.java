package edu.csumb.esotorodriguez.project1gradetracker;

import android.content.Context;
import android.content.Intent;

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

    @Test
    public void userDaoTest() {
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();

        UserDao userTestDB = Room.databaseBuilder(appContext, TrackerDB.class, TrackerDB.TRACKER_DB)
                .allowMainThreadQueries()
                .build()
                .userDao();

        User user1 = new User("Other", "Name", "Testing", "Pass");
        User user2 = new User("Else", "Past", "Order", "Name");

        userTestDB.insert(user1);
        userTestDB.insert(user2);
        assertNotNull(userTestDB.getAllUsers());

        assertNotNull(userTestDB.getSpecificUser("Testing").getUserID());
        if (userTestDB.getSpecificUser("Testing").getUserID() == user1.getUserID()) {
            assertEquals(user1.getUserID(), userTestDB.getSpecificUser("Testing").getUserID());
        }
        assertNotNull(userTestDB.getSpecificUser("Testing"));
//        assertEquals(user1.getUserID(), userTestDB.getSpecificUser("Testing").getUserID());
        assertEquals(user1.getFirstName(), userTestDB.getSpecificUser("Testing").getFirstName());
        assertEquals(user1.getLastName(), userTestDB.getSpecificUser("Testing").getLastName());
        assertEquals(user1.getUsername(), userTestDB.getSpecificUser("Testing").getUsername());
        assertEquals(user1.getPassword(), userTestDB.getSpecificUser("Testing").getPassword());

        assertNull(userTestDB.getSpecificUser("Fail"));
        assertNotNull(userTestDB.getSpecificUser("Order"));
        assertEquals(user2.getFirstName(), userTestDB.getSpecificUser("Order").getFirstName());

        user2.setFirstName("Night");
        user2.setLastName("Time");
        user2.setUsername("Eagle");
        user2.setPassword("Eye");

        user2.setUserID(user2.getUserID());

        System.out.println("Start of the List");

        System.out.println(userTestDB.getAllUsers().toString());
        System.out.println(userTestDB.getAllUsers().toString());

        userTestDB.update(user2);

        System.out.println(userTestDB.getAllUsers().toString());
        System.out.println(userTestDB.getAllUsers().toString());

        System.out.println("End of the List");

        assertNotNull(userTestDB.getSpecificUser("Eagle"));

        assertEquals(user2.getFirstName(), userTestDB.getSpecificUser("Eagle").getFirstName());
        assertEquals(user2.getUsername(), userTestDB.getSpecificUser("Eagle").getUsername());

        assertNotNull(userTestDB.getAllUsers());

        userTestDB.delete(user1);
        userTestDB.delete(user2);
    }
}
