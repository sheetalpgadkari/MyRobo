package me.robo.action.impl;

import me.robo.MyRobo;
import me.robo.movement.Facing;
import me.robo.movement.Position;

import java.util.Optional;

public class LeftAction extends Action {

    @Override
    protected boolean validate(MyRobo myRobo) {
        return isOnSurface(myRobo);
    }


    @Override
    protected Optional<Position> performAction(MyRobo myRobo) {
        Position currentPosition = getCurrentPosition(myRobo).get();
        Facing newFacing = moveLeft(currentPosition.getFacing());
        return Optional.of(new Position(currentPosition.getX(), currentPosition.getY(), newFacing));
    }


    private Facing moveLeft(Facing facing) {
        Facing newFacing = null;
        switch (facing) {
            case EAST:
                newFacing = Facing.NORTH;
                break;
            case WEST:
                newFacing = Facing.SOUTH;
                break;
            case NORTH:
                newFacing = Facing.WEST;
                break;
            case SOUTH:
                newFacing = Facing.EAST;
                break;
        }
        return newFacing;

    }
}
