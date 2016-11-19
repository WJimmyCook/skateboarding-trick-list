/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swcguild.skatetricklibrary.dao;

import com.tsguild.skatetricklibrary.dao.SearchTerm;
import com.tsguild.skatetricklibrary.dao.SkateTrickDao;
import com.tsguild.skatetricklibrary.model.Trick;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author Jimmy Cook
 */
public class SkateTrickDaoTest {

    private SkateTrickDao testDao;

    public SkateTrickDaoTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        ApplicationContext ctx
                = new ClassPathXmlApplicationContext("test-applicationContext.xml");
        testDao = ctx.getBean("skateTrickDao", SkateTrickDao.class);
    }

    Trick[] tricksForTesting = {
        new Trick(-1, "Kickflip", "Street", "Flip", 2),
        new Trick(-1, "Heeflip", "Street", "Flip", 3),
        new Trick(-1, "Crooked Grind", "Street", "Grind", 4),
        new Trick(-1, "Rock n' Roll", "Ramp", "Stall", 1),
        new Trick(-1, "Boardslide", "Street", "Slide", 1),
        new Trick(-1, "Tailslide", "Street", "Slide", 5)
    };
    
    Trick[] duplicateTricks = {
        new Trick(-1, "Kickflip", "Street", "Flip", 2),
        new Trick(-1, "Heeflip", "Street", "Flip", 3),
        new Trick(-1, "Crooked Grind", "Street", "Grind", 4),
        new Trick(-1, "Rock n' Roll", "Ramp", "Stall", 1),
        new Trick(-1, "Boardslide", "Street", "Slide", 1),
        new Trick(-1, "Tailslide", "Street", "Slide", 5)
    };
    
    Trick[] similarTricks = {
        new Trick(-1, "Hardflip", "Street", "Flip", 2),
        new Trick(-1, "Casperflip", "Street", "Flip", 3),
        new Trick(-1, "Nose Grind", "Street", "Grind", 4),
        new Trick(-1, "50-50", "Ramp", "Grind", 1),
        new Trick(-1, "Melon", "Ramp", "Grab", 1),
        new Trick(-1, "5-0", "Street", "Griind", 5)
    };

    @Test
    public void addOneToEmptyDaoTest() {
        // Step1: Make a Trick
        Trick trick = new Trick(-1, "5-0", "Street", "Griind", 5);

        // Step2: Add trick to dao
        testDao.addTrick(trick);

        // Step3: get trick out of dao by id
        Trick hopefullyTrick = testDao.getTrickById(trick.getId());

        // Step4: Make sure the trick added looks like the trick retrieved
        // We can only use assertEquals here because we overrode the .equals method in Trick
        junit.framework.Assert.assertEquals("trick stored, vs. trick retrieved does not match",
                trick, hopefullyTrick);
        //        Assert.assertEquals("Added/GetByID trick id did not match.", fido.getId(), hopefullyFido.getId());
        //        Assert.assertEquals("Added/GetByID trick name did not match 'fido' as expected", "fido", hopefullyFido.getId());
    }

    @Test
    public void testAgainstEmptyDAO() {

        junit.framework.Assert.assertNull("Asking for a non existant trick should return null.", testDao.getTrickById(445));
        junit.framework.Assert.assertNotNull("List of all tricks should not be null.", testDao.getAllTricks());
        junit.framework.Assert.assertEquals("Expected trick count of 'all tricks' is nonzero with empty DAO.", 0, testDao.getAllTricks().size());
    }

    @Test
    public void testAddOneTrick() {
        Trick trickToAdd = tricksForTesting[0];
        testDao.addTrick(trickToAdd);

        junit.framework.Assert.assertEquals("Returned trick does not match expected.", trickToAdd, testDao.getTrickById(trickToAdd.getId()));
        junit.framework.Assert.assertNotNull("List of all tricks should not be null.", testDao.getAllTricks());
        junit.framework.Assert.assertEquals("Expected trick count of 'all tricks' does not match after adding one trick.", 1, testDao.getAllTricks().size());
        junit.framework.Assert.assertTrue("Returned trick in getAllTricks does not match expected.", testDao.getAllTricks().contains(trickToAdd));

    }

    @Test
    public void testUpdateTrick() {
        testDao.addTrick(tricksForTesting[0]);
        similarTricks[0].setId(tricksForTesting[0].getId());
        testDao.updateTrick(similarTricks[0]);

        junit.framework.Assert.assertEquals("Updated trick get does not match expected.", similarTricks[0], testDao.getTrickById(similarTricks[0].getId()));
        junit.framework.Assert.assertNotNull("List of all tricks should not be null.", testDao.getAllTricks());
        junit.framework.Assert.assertEquals("Expected trick count of 'all tricks' does not match after replacing one trick.", 1, testDao.getAllTricks().size());
        junit.framework.Assert.assertTrue("Returned trick in getAllTricks does not match expected.", testDao.getAllTricks().contains(similarTricks[0]));
    }

    @Test
    public void testAddMultipleTricks() {
        for (Trick trick : tricksForTesting) {
            testDao.addTrick(trick);
        }

        junit.framework.Assert.assertNotNull("List of all tricks should not be null.", testDao.getAllTricks());
        junit.framework.Assert.assertEquals("Expected trick count of 'all tricks' does not match after adding several tricks.",
                tricksForTesting.length, testDao.getAllTricks().size());

        for (int i = 0; i < tricksForTesting.length; i++) {
            junit.framework.Assert.assertEquals("Returned trick does not match expected.", tricksForTesting[i], testDao.getTrickById(tricksForTesting[i].getId()));
            junit.framework.Assert.assertTrue("Returned trick in getAllTricks does not match expected.", testDao.getAllTricks().contains(tricksForTesting[i]));
        }

    }

    @Test
    public void testUpdateMultipleTricks() {
        for (Trick trick : tricksForTesting) {
            testDao.addTrick(trick);
        }

        for (int i = 0; i < tricksForTesting.length; i++) {
            similarTricks[i].setId(tricksForTesting[i].getId());
            testDao.updateTrick(similarTricks[i]);
        }

        junit.framework.Assert.assertNotNull("List of all tricks should not be null.", testDao.getAllTricks());
        junit.framework.Assert.assertEquals("Expected trick count of 'all tricks' does not match after replacing several tricks.",
                tricksForTesting.length, testDao.getAllTricks().size());

        for (int i = 0; i < similarTricks.length; i++) {
            junit.framework.Assert.assertEquals("Get trick does not match expected return on update.", similarTricks[i], testDao.getTrickById(similarTricks[i].getId()));
            junit.framework.Assert.assertTrue("Returned trick in getAllTricks does not match expected.", testDao.getAllTricks().contains(similarTricks[i]));
        }

    }

    @Test
    public void testAddSimilarTricks() {
        for (Trick trick : tricksForTesting) {
            testDao.addTrick(trick);
        }

        for (Trick trick : similarTricks) {
            testDao.addTrick(trick);
        }

        junit.framework.Assert.assertNotNull("List of all tricks should not be null.", testDao.getAllTricks());
        junit.framework.Assert.assertEquals("Expected trick count does not match after adding several similar tricks.",
                tricksForTesting.length + similarTricks.length, testDao.getAllTricks().size());
        junit.framework.Assert.assertEquals("Expected trick count of 'all tricks' does not match after adding several similar tricks.",
                tricksForTesting.length + similarTricks.length, testDao.getAllTricks().size());

        for (int i = 0; i < similarTricks.length; i++) {
            junit.framework.Assert.assertEquals("Get trick does not match expected return on addition of similar trick.", similarTricks[i],
                    testDao.getTrickById(similarTricks[i].getId()));
            junit.framework.Assert.assertTrue("Returned trick in getAllTricks does not match expected.", testDao.getAllTricks().contains(similarTricks[i]));
        }

        for (int i = 0; i < tricksForTesting.length; i++) {
            junit.framework.Assert.assertEquals("Get trick does not match expected return on addition of similar trick.", tricksForTesting[i],
                    testDao.getTrickById(tricksForTesting[i].getId()));
            junit.framework.Assert.assertTrue("Returned trick in getAllTricks does not match expected.", testDao.getAllTricks().contains(tricksForTesting[i]));
        }

    }

    @Test
    public void testAddAndRemoveOneTrick() {
        testDao.addTrick(tricksForTesting[0]);
        testDao.removeTrick(tricksForTesting[0].getId());

        junit.framework.Assert.assertNotNull("List of all tricks should not be null.", testDao.getAllTricks());
        junit.framework.Assert.assertNull("Get trick should return null after being removed.", testDao.getTrickById(tricksForTesting[0].getId()));
        junit.framework.Assert.assertEquals("Expected trick count of 'all tricks' should be zero when adding/removing a single trick.", 0, testDao.getAllTricks().size());
    }

    @Test
    public void testAddAndRemoveTrickTwice() {
        testDao.addTrick(tricksForTesting[0]);
        testDao.removeTrick(tricksForTesting[0].getId());
        testDao.removeTrick(tricksForTesting[0].getId());

        junit.framework.Assert.assertNotNull("List of all tricks should not be null.", testDao.getAllTricks());
        junit.framework.Assert.assertNull("Trick should return null after being removed.", testDao.getTrickById(tricksForTesting[0].getId()));
        junit.framework.Assert.assertEquals("Expected trick count of 'all tricks' should be zero when adding/removing a single trick twice.", 0, testDao.getAllTricks().size());
    }

    @Test
    public void testAddAndRemoveMultipleTricks() {

        for (Trick trick : tricksForTesting) {
            testDao.addTrick(trick);
        }

        int tricksAdded = tricksForTesting.length;
        for (int i = 0; i < tricksForTesting.length; i += 2) {
            testDao.removeTrick(tricksForTesting[i].getId());
            tricksAdded--;
        }

        junit.framework.Assert.assertNotNull("List of all tricks should not be null.", testDao.getAllTricks());
        junit.framework.Assert.assertEquals("Expected trick count of 'all tricks' does not match after adding & removing several tricks.",
                tricksAdded, testDao.getAllTricks().size());

        for (int i = 0; i < tricksForTesting.length; i++) {
            if (i % 2 == 1) {
                junit.framework.Assert.assertEquals("Returned trick does not match expected.", tricksForTesting[i], testDao.getTrickById(tricksForTesting[i].getId()));
            } else {
                junit.framework.Assert.assertNull("Trick should be removed and return null.", testDao.getTrickById(tricksForTesting[i].getId()));
            }
        }

    }

    @Test
    public void testAddAndRemoveTricksMultipleTimes() {

        for (Trick trick : tricksForTesting) {
            testDao.addTrick(trick);
        }

        for (Trick trick : tricksForTesting) {
            testDao.removeTrick(trick.getId());
        }

        for (Trick trick : tricksForTesting) {
            testDao.addTrick(trick);
        }

        junit.framework.Assert.assertNotNull("List of all tricks should not be null.", testDao.getAllTricks());
        junit.framework.Assert.assertEquals("Expected trick count of 'all tricks' should be zero when adding/removing a all tricks.",
                tricksForTesting.length, testDao.getAllTricks().size());



        for (int i = 0; i < tricksForTesting.length; i++) {
            Trick trick = tricksForTesting[i];
            junit.framework.Assert.assertEquals("Trick should return after being re-added.", trick, testDao.getTrickById(trick.getId()));
            testDao.removeTrick(trick.getId());
            junit.framework.Assert.assertNull("Trick should return null after being removed.", testDao.getTrickById(trick.getId()));
        }

        junit.framework.Assert.assertEquals("Expected trick count of 'all tricks' should be zero when adding/removing a all tricks.", 0, testDao.getAllTricks().size());

    }

    @Test
    public void testTrickCountOnAddition() {

        // Add all tricks and check that count increments appropriately
        for (int i = 0; i < tricksForTesting.length; i++) {
            testDao.addTrick(tricksForTesting[i]);
            junit.framework.Assert.assertEquals("Expected " + (i + 1) + " tricks after adding trickses.",
                    i + 1, testDao.getAllTricks().size());
        }

    }
    
    @Test
    public void testTrickCountOnUpdate() {

        // Add all tricks and check that count increments appropriately
        for (int i = 0; i < tricksForTesting.length; i++) {
            testDao.addTrick(tricksForTesting[i]);
            similarTricks[i].setId(tricksForTesting[i].getId());
            testDao.updateTrick(similarTricks[i]);
            junit.framework.Assert.assertEquals("Expected " + (i + 1) + " tricks after updating trickses.",
                    i + 1, testDao.getAllTricks().size());
        }

    }

    @Test
    public void testTrickCountAfterRemoval() {

        // Add all tricks
        for (int i = 0; i < tricksForTesting.length; i++) {
            testDao.addTrick(tricksForTesting[i]);
        }

        // Remove tricks one by one and test that count decrements properly
        for (int i = 0; i < tricksForTesting.length; i++) {
            testDao.removeTrick(tricksForTesting[i].getId());
            junit.framework.Assert.assertEquals("Expected " + (tricksForTesting.length - i - 1) + " tricks after removing trickses.",
                    tricksForTesting.length - i - 1, testDao.getAllTricks().size());
        }
    }

    @Test
    public void testTricksAfterRemovalOfNonExistent() {

        // Add all tricks
        for (int i = 0; i < tricksForTesting.length; i++) {
            testDao.addTrick(tricksForTesting[i]);
        }

        testDao.removeTrick(100);
        junit.framework.Assert.assertEquals("Expected " + tricksForTesting.length + " tricks after removing trickses.",
                tricksForTesting.length, testDao.getAllTricks().size());

    }

    @Test
    public void testTrickCountAfterTwiceRemoved() {

        // Add all tricks
        for (int i = 0; i < tricksForTesting.length; i++) {
            testDao.addTrick(tricksForTesting[i]);
        }

        // Remove tricks one by one and test that count decrements properly
        for (int i = 0; i < tricksForTesting.length; i++) {
            testDao.removeTrick(tricksForTesting[i].getId());
        }

        junit.framework.Assert.assertEquals("Expected " + 0 + " tricks after removing trickses.",
                0, testDao.getAllTricks().size());

        // Remove tricks one by one and test that count decrements properly
        for (int i = 0; i < tricksForTesting.length; i++) {
            testDao.removeTrick(tricksForTesting[i].getId());
        }

        junit.framework.Assert.assertEquals("Expected " + 0 + " tricks after attempting to re-remove trickses.",
                0, testDao.getAllTricks().size());

    }
}
