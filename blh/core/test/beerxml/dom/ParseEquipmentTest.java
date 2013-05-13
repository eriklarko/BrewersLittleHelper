/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package blh.core.test.beerxml.dom;

import blh.core.beerxml.ParseException;
import blh.core.beerxml.types.Equipment;
import blh.core.beerxml.types.builders.Builder;
import blh.core.beerxml.types.builders.EquipmentBuilder;
import blh.core.units.Percentage;
import blh.core.units.time.Minutes;
import blh.core.units.volume.Liters;
import blh.core.units.weight.Kilograms;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author thinner
 */
public class ParseEquipmentTest {

    public ParseEquipmentTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of setBatchSize method, of class EquipmentBuilder.
     */
    @Test
    public void testSetBatchSize() {
        System.out.println("setBatchSize");
        Liters batchSize = null;
        EquipmentBuilder instance = new EquipmentBuilderImpl();
        EquipmentBuilder expResult = null;
        EquipmentBuilder result = instance.setBatchSize(batchSize);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setBoilSize method, of class EquipmentBuilder.
     */
    @Test
    public void testSetBoilSize() {
        System.out.println("setBoilSize");
        Liters boilSize = null;
        EquipmentBuilder instance = new EquipmentBuilderImpl();
        EquipmentBuilder expResult = null;
        EquipmentBuilder result = instance.setBoilSize(boilSize);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setBoilTime method, of class EquipmentBuilder.
     */
    @Test
    public void testSetBoilTime() {
        System.out.println("setBoilTime");
        Minutes boilTime = null;
        EquipmentBuilder instance = new EquipmentBuilderImpl();
        EquipmentBuilder expResult = null;
        EquipmentBuilder result = instance.setBoilTime(boilTime);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setCalculatedBoilVolume method, of class EquipmentBuilder.
     */
    @Test
    public void testSetCalculatedBoilVolume() {
        System.out.println("setCalculatedBoilVolume");
        boolean calculatedBoilVolume = false;
        EquipmentBuilder instance = new EquipmentBuilderImpl();
        EquipmentBuilder expResult = null;
        EquipmentBuilder result = instance.setCalculateBoilVolume(calculatedBoilVolume);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setEvapRate method, of class EquipmentBuilder.
     */
    @Test
    public void testSetEvapRate() {
        System.out.println("setEvapRate");
        Percentage evapRate = null;
        EquipmentBuilder instance = new EquipmentBuilderImpl();
        EquipmentBuilder expResult = null;
        EquipmentBuilder result = instance.setEvapRate(evapRate);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setHopUtilization method, of class EquipmentBuilder.
     */
    @Test
    public void testSetHopUtilization() {
        System.out.println("setHopUtilization");
        Percentage hopUtilization = null;
        EquipmentBuilder instance = new EquipmentBuilderImpl();
        EquipmentBuilder expResult = null;
        EquipmentBuilder result = instance.setHopUtilization(hopUtilization);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setLauterDeadSpace method, of class EquipmentBuilder.
     */
    @Test
    public void testSetLauterDeadSpace() {
        System.out.println("setLauterDeadSpace");
        Liters lauterDeadSpace = null;
        EquipmentBuilder instance = new EquipmentBuilderImpl();
        EquipmentBuilder expResult = null;
        EquipmentBuilder result = instance.setLauterDeadSpace(lauterDeadSpace);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setName method, of class EquipmentBuilder.
     */
    @Test
    public void testSetName() {
        System.out.println("setName");
        String name = "";
        EquipmentBuilder instance = new EquipmentBuilderImpl();
        EquipmentBuilder expResult = null;
        EquipmentBuilder result = instance.setName(name);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setNotes method, of class EquipmentBuilder.
     */
    @Test
    public void testSetNotes() {
        System.out.println("setNotes");
        String notes = "";
        EquipmentBuilder instance = new EquipmentBuilderImpl();
        EquipmentBuilder expResult = null;
        EquipmentBuilder result = instance.setNotes(notes);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setTopUpKettle method, of class EquipmentBuilder.
     */
    @Test
    public void testSetTopUpKettle() {
        System.out.println("setTopUpKettle");
        Liters topUpKettle = null;
        EquipmentBuilder instance = new EquipmentBuilderImpl();
        EquipmentBuilder expResult = null;
        EquipmentBuilder result = instance.setTopUpKettle(topUpKettle);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setTopUpWater method, of class EquipmentBuilder.
     */
    @Test
    public void testSetTopUpWater() {
        System.out.println("setTopUpWater");
        Liters topUpWater = null;
        EquipmentBuilder instance = new EquipmentBuilderImpl();
        EquipmentBuilder expResult = null;
        EquipmentBuilder result = instance.setTopUpWater(topUpWater);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setTrubChillerLoss method, of class EquipmentBuilder.
     */
    @Test
    public void testSetTrubChillerLoss() {
        System.out.println("setTrubChillerLoss");
        Liters trubChillerLoss = null;
        EquipmentBuilder instance = new EquipmentBuilderImpl();
        EquipmentBuilder expResult = null;
        EquipmentBuilder result = instance.setTrubChillerLoss(trubChillerLoss);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setTunSpecificHeat method, of class EquipmentBuilder.
     */
    @Test
    public void testSetTunSpecificHeat() {
        System.out.println("setTunSpecificHeat");
        double tunSpecificHeat = 0.0;
        EquipmentBuilder instance = new EquipmentBuilderImpl();
        EquipmentBuilder expResult = null;
        EquipmentBuilder result = instance.setTunSpecificHeat(tunSpecificHeat);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setTunVolume method, of class EquipmentBuilder.
     */
    @Test
    public void testSetTunVolume() {
        System.out.println("setTunVolume");
        Liters tunVolume = null;
        EquipmentBuilder instance = new EquipmentBuilderImpl();
        EquipmentBuilder expResult = null;
        EquipmentBuilder result = instance.setTunVolume(tunVolume);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setTunWeight method, of class EquipmentBuilder.
     */
    @Test
    public void testSetTunWeight() {
        System.out.println("setTunWeight");
        Kilograms tunWeight = null;
        EquipmentBuilder instance = new EquipmentBuilderImpl();
        EquipmentBuilder expResult = null;
        EquipmentBuilder result = instance.setTunWeight(tunWeight);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    public class EquipmentBuilderImpl implements EquipmentBuilder {

        public EquipmentBuilder setBatchSize(Liters batchSize) {
            return null;
        }

        public EquipmentBuilder setBoilSize(Liters boilSize) {
            return null;
        }

        public EquipmentBuilder setBoilTime(Minutes boilTime) {
            return null;
        }

        public EquipmentBuilder setCalculateBoilVolume(boolean calculatedBoilVolume) {
            return null;
        }

        public EquipmentBuilder setEvapRate(Percentage evapRate) {
            return null;
        }

        public EquipmentBuilder setHopUtilization(Percentage hopUtilization) {
            return null;
        }

        public EquipmentBuilder setLauterDeadSpace(Liters lauterDeadSpace) {
            return null;
        }

        public EquipmentBuilder setName(String name) {
            return null;
        }

        public EquipmentBuilder setNotes(String notes) {
            return null;
        }

        public EquipmentBuilder setTopUpKettle(Liters topUpKettle) {
            return null;
        }

        public EquipmentBuilder setTopUpWater(Liters topUpWater) {
            return null;
        }

        public EquipmentBuilder setTrubChillerLoss(Liters trubChillerLoss) {
            return null;
        }

        public EquipmentBuilder setTunSpecificHeat(double tunSpecificHeat) {
            return null;
        }

        public EquipmentBuilder setTunVolume(Liters tunVolume) {
            return null;
        }

        public EquipmentBuilder setTunWeight(Kilograms tunWeight) {
            return null;
        }

        @Override
        public Builder<Equipment> set(String tagName, String value) throws ParseException {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        @Override
        public Equipment create() {
            throw new UnsupportedOperationException("Not supported yet.");
        }
    }
}
