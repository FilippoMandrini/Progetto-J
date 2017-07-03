/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package poker;

import actions.Action;
import actions.ActionFactory;
import actions.ActionSet;
import actions.Bet;
import actions.BigBlind;
import actions.Call;
import actions.Check;
import actions.Fold;
import actions.Raise;
import actions.SmallBlind;
import java.lang.reflect.InvocationTargetException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author Nickelsilver
 */
public class ActionFactoryTest {

    private ActionFactory factory;

    @Before
    public void init() {
        factory = new ActionFactory();
    }

    @Test
    public void testCall() {
        Action call = null;
        try {
            call = factory.createAction(ActionSet.CALL, 0);
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | NoSuchMethodException | IllegalArgumentException | InvocationTargetException | SecurityException ex) {
            Assert.fail("Eccezione lanciata");
        }
        Assert.assertTrue(call instanceof Call);
    }

    @Test
    public void testCheck() {
        Action check = null;
        try {
            check = factory.createAction(ActionSet.CHECK, 0);
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | NoSuchMethodException | IllegalArgumentException | InvocationTargetException | SecurityException ex) {
            Assert.fail("Eccezione lanciata");
        }
        Assert.assertTrue(check instanceof Check);
    }

    @Test
    public void testFold() {
        Action fold = null;
        try {
            fold = factory.createAction(ActionSet.FOLD, 0);
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | NoSuchMethodException | IllegalArgumentException | InvocationTargetException | SecurityException ex) {
            Assert.fail("Eccezione lanciata");
        }
        Assert.assertTrue(fold instanceof Fold);

    }

    @Test
    public void testRaise() {
        Action raise = null;
        try {
            raise = factory.createAction(ActionSet.RAISE, 0);
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | NoSuchMethodException | IllegalArgumentException | InvocationTargetException | SecurityException ex) {
            Assert.fail("Eccezione lanciata");
        }
        Assert.assertTrue(raise instanceof Raise);
    }

    @Test
    public void testBet() {

        Action bet = null;
        try {
            bet = factory.createAction(ActionSet.BET, 0);
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | NoSuchMethodException | IllegalArgumentException | InvocationTargetException | SecurityException ex) {
            Assert.fail("Eccezione lanciata");
        }
        Assert.assertTrue(bet instanceof Bet);
    }

    @Test
    public void testBlinds() {
        Action bigBlind = null;
        try {
            bigBlind = factory.createAction(ActionSet.BIG_BLIND, 0);
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | NoSuchMethodException | IllegalArgumentException | InvocationTargetException | SecurityException ex) {
            Assert.fail("Eccezione lanciata");
        }
        Assert.assertTrue(bigBlind instanceof BigBlind);
        
        Action smallBlind = null;
        try {
            smallBlind = factory.createAction(ActionSet.SMALL_BLIND, 0);
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | NoSuchMethodException | IllegalArgumentException | InvocationTargetException | SecurityException ex) {
            Assert.fail("Eccezione lanciata");
        }
        Assert.assertTrue(smallBlind instanceof SmallBlind);
    }

}
