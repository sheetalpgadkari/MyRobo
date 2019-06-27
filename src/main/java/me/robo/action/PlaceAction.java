package me.robo.action;

import me.robo.MyRobo;
import me.robo.movement.Facing;
import me.robo.movement.Position;

import java.util.Optional;
import java.util.function.Predicate;

public class PlaceAction extends Action implements RobotAction
{

    private Position position;

    public PlaceAction(int x, int y, Facing facing)
    {
        position = new Position(x, y, facing);
    }

    @Override
    public Optional<Position> execute(MyRobo myRobo) {
        if (!allowPlacement(myRobo)) {
            System.out.println("Command Ignored");
            return Optional.empty();
        }
        return Optional.of(new Position(position.getX(), position.getY(), position.getFacing()));
    }


    private boolean allowPlacement(MyRobo myRobo) {
        Predicate< Position> isNotFallingFromX = isNotFallingFromX(myRobo.getSimulatorSurface());
        Predicate< Position> isNotFallingFromY = isNotFallingFromY(myRobo.getSimulatorSurface());
        return
                Optional.ofNullable(myRobo)
                        .flatMap(s -> Optional.ofNullable(position))
                        .filter(isNotFallingFromX)
                        .filter(isNotFallingFromY)
                        .isPresent();

    }
}
