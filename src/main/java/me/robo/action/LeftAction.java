package me.robo.action;

import me.robo.MyRobo;
import me.robo.movement.Facing;
import me.robo.movement.Position;

import java.util.Optional;

public class LeftAction extends Action implements RobotAction
{

    private boolean validate(MyRobo myRobo)
    {
        return allowAction(myRobo);
    }

    @Override
    public Optional<Position> execute(MyRobo myRobo)
    {
        if(!validate(myRobo)){
            System.out.println("Command Ignored");
            return Optional.empty();
        }
        Position currentPosition = getCurrentPosition(myRobo).get();
        Facing newFacing = moveLeft(currentPosition.getFacing());
        return Optional.of(new Position(currentPosition.getX(), currentPosition.getY(), newFacing));
    }

    private Facing moveLeft(Facing facing)
    {
        Facing newFacing = null;
        switch (facing){
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
