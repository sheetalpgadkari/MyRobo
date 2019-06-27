package me.robo.action;

import me.robo.MyRobo;
import me.robo.movement.Facing;
import me.robo.movement.Position;

import java.util.Optional;

public class RightAction extends Action implements RobotAction
{
    private boolean allowRight(MyRobo myRobo)
    {
        return allowAction(myRobo);
    }

    @Override
    public Optional<Position> execute(MyRobo myRobo) {
        if(!allowRight(myRobo)){
            System.out.println("Command Ignored");
            return Optional.empty();
        }
        Position currentPosition = getCurrentPosition(myRobo).get();
        Facing newFacing = moveRight(currentPosition.getFacing());
        return Optional.of(new Position(currentPosition.getX(), currentPosition.getY(), newFacing));    }

    private Facing moveRight(Facing facing)
    {
        Facing newFacing = null;
        switch (facing){
            case EAST:
                newFacing = Facing.SOUTH;
                break;
            case WEST:
                newFacing = Facing.NORTH;
                break;
            case NORTH:
                newFacing = Facing.EAST;
                break;
            case SOUTH:
                newFacing = Facing.WEST;
                break;
        }
        return newFacing;
    }
}
