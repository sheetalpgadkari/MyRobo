package me.robo;

import me.robo.action.impl.LeftAction;
import me.robo.action.impl.MoveAction;
import me.robo.action.impl.PlaceAction;
import me.robo.action.impl.RightAction;
import me.robo.movement.Facing;
import me.robo.simulator.impl.TableSimulatorSurface;
import org.junit.Assert;
import org.junit.Test;

public class TestMyRobo {

    public static final int xMax = 5;
    public static final int yMax = 5;

    @Test
    public void movementTest1() {
        MyRobo myRobo = getMyRobo();
        myRobo.performAction(new PlaceAction(0, 0, Facing.NORTH));
        myRobo.performAction(new MoveAction());
        Assert.assertEquals("0,1,NORTH", myRobo.report());
    }

    @Test
    public void movementTest2() {
        MyRobo myRobo = getMyRobo();
        myRobo.performAction(new PlaceAction(0, 0, Facing.NORTH));
        myRobo.performAction(new LeftAction());
        Assert.assertEquals("0,0,WEST", myRobo.report());
    }

    @Test
    public void movementTest3() {
        MyRobo myRobo = getMyRobo();
        myRobo.performAction(new PlaceAction(1, 2, Facing.EAST));
        myRobo.performAction(new MoveAction());
        myRobo.performAction(new MoveAction());
        myRobo.performAction(new LeftAction());
        myRobo.performAction(new MoveAction());
        Assert.assertEquals("3,3,NORTH", myRobo.report());
    }

    @Test
    public void moveLeft() throws Exception {
        MyRobo myRobo = getMyRobo();
        myRobo.performAction(new PlaceAction(0, 1, Facing.NORTH));
        myRobo.performAction(new LeftAction());
        Assert.assertEquals("0,1,WEST", myRobo.report());
    }

    @Test
    public void moveRight() throws Exception {
        MyRobo myRobo = getMyRobo();
        myRobo.performAction(new PlaceAction(2, 1, Facing.NORTH));
        myRobo.performAction(new RightAction());
        Assert.assertEquals("2,1,EAST", myRobo.report());
    }

    @Test
    public void move() throws Exception {
        MyRobo myRobo = getMyRobo();
        myRobo.performAction(new PlaceAction(4, 4, Facing.NORTH));
        myRobo.performAction(new MoveAction());
        Assert.assertEquals("4,5,NORTH", myRobo.report());
    }

    @Test
    public void move2() throws Exception {
        MyRobo myRobo = getMyRobo();
        myRobo.performAction(new PlaceAction(0, 1, Facing.NORTH));
        myRobo.performAction(new MoveAction());
        Assert.assertEquals("0,2,NORTH", myRobo.report());
    }

    private MyRobo getMyRobo() {
        return new MyRobo(getTableSimulatorSurface());
    }


    @Test
    public void invalidMovement1() {
        MyRobo myRobo = getMyRobo();
        myRobo.performAction(new PlaceAction(6, 7, Facing.EAST));
        Assert.assertEquals("Not valid position", myRobo.report());
    }

    @Test
    public void invalidMovement2() {
        MyRobo myRobo = getMyRobo();
        myRobo.performAction(new PlaceAction(5, 5, Facing.EAST));
        myRobo.performAction(new MoveAction());
        Assert.assertEquals("Not valid position", myRobo.report());
    }

    private TableSimulatorSurface getTableSimulatorSurface() {
        return new TableSimulatorSurface(xMax, yMax);
    }
}
