package me.robo.action;

import me.robo.simulator.SimulatorSurface;
import me.robo.MyRobo;
import me.robo.movement.Position;

import java.util.Optional;
import java.util.function.Predicate;

public abstract class Action
{
    protected Optional<Position> getCurrentPosition(MyRobo myRobo)
    {
        return Optional.ofNullable(myRobo)
                .flatMap( d -> Optional.ofNullable(myRobo.getCurrentPosition()));

    }

    protected boolean allowAction (MyRobo myRobo)
    {

      return getCurrentPosition(myRobo)
               .filter( d -> ( d.getX() >= 0 && d.getY() >= 0 && d.getFacing() != null ))
              .isPresent();

    }

    protected Predicate<Position> isNotFallingFromX (SimulatorSurface simulatorSurface)
    {
        return position -> position.getX() <= simulatorSurface.getObjectPosition().getX()  ;
    }

    protected Predicate<Position> isNotFallingFromY (SimulatorSurface simulatorSurface)
    {
        return position -> position.getY() <= simulatorSurface.getObjectPosition().getY();
    }

}
