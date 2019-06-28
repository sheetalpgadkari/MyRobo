package me.robo.action.impl;

import me.robo.MyRobo;
import me.robo.action.RoboAction;
import me.robo.movement.Position;
import me.robo.simulator.SimulatorSurface;

import java.util.Optional;
import java.util.function.Predicate;

public abstract class Action implements RoboAction {

    @Override
    public Optional<Position> execute(MyRobo myRobo) {
        if (!validate(myRobo)) {
            return ignoreCommand();
        }
        return performAction(myRobo);
    }

    abstract boolean validate(MyRobo myRobo);

    abstract Optional<Position> performAction(MyRobo myRobo);


    protected Optional<Position> ignoreCommand() {
        System.out.println("Command Ignored");
        return Optional.empty();
    }

    protected Optional<Position> getCurrentPosition(MyRobo myRobo) {
        return Optional.ofNullable(myRobo)
                .flatMap(d -> Optional.ofNullable(myRobo.getCurrentPosition()));

    }

    protected boolean isOnSurface(MyRobo myRobo) {

        return getCurrentPosition(myRobo)
                .filter(d -> (d.getX() >= 0 && d.getY() >= 0 && d.getFacing() != null))
                .isPresent();

    }

    protected Predicate<Position> isNotFallingFromX(SimulatorSurface simulatorSurface) {
        return position -> position.getX() <= simulatorSurface.getObjectPosition().getX();
    }

    protected Predicate<Position> isNotFallingFromY(SimulatorSurface simulatorSurface) {
        return position -> position.getY() <= simulatorSurface.getObjectPosition().getY();
    }

}
