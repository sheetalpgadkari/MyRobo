package me.robo.action.impl;

import me.robo.MyRobo;
import me.robo.movement.Facing;
import me.robo.movement.Position;

import java.util.Optional;
import java.util.function.Predicate;

public class PlaceAction extends Action {

    private Position position;

    public PlaceAction(int x, int y, Facing facing) {
        position = new Position(x, y, facing);
    }

    @Override
    protected boolean validate(MyRobo myRobo) {
        Predicate<Position> isNotFallingFromX = isNotFallingFromX(myRobo.getSimulatorSurface());
        Predicate<Position> isNotFallingFromY = isNotFallingFromY(myRobo.getSimulatorSurface());
        return
                Optional.ofNullable(myRobo)
                        .flatMap(s -> Optional.ofNullable(position))
                        .filter(isNotFallingFromX)
                        .filter(isNotFallingFromY)
                        .isPresent();

    }

    @Override
    Optional<Position> performAction(MyRobo myRobo) {
        return Optional.of(new Position(position.getX(), position.getY(), position.getFacing()));
    }
}
