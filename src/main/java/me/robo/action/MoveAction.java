package me.robo.action;

import me.robo.MyRobo;
import me.robo.movement.Position;

import java.util.Optional;
import java.util.function.Predicate;

public class MoveAction extends Action implements RobotAction {


    @Override
    public Optional<Position> execute(MyRobo myRobo) {
        if (!allowMove(myRobo)) {
            System.out.println("Command Ignored");
            return Optional.empty();
        }
        Position currentPosition = getCurrentPosition(myRobo).get();
        return Optional.ofNullable(move(currentPosition));

    }

    private Position move(Position currentPosition)
    {

        switch (currentPosition.getFacing()){
            case NORTH:
                return new Position(currentPosition.getX(), currentPosition.getY() + 1, currentPosition.getFacing());
            case EAST:
                return new Position(currentPosition.getX() + 1, currentPosition.getY() , currentPosition.getFacing());
            case SOUTH:
                return new Position(currentPosition.getX(), currentPosition.getY() - 1, currentPosition.getFacing());
            case WEST:
                return new Position(currentPosition.getX() -1, currentPosition.getY() , currentPosition.getFacing());
        }
        return null;
    }

    private boolean allowMove(MyRobo myRobo) {
        Predicate< Position> isNotFallingFromX = isNotFallingFromX(myRobo.getSimulatorSurface());
        Predicate< Position> isNotFallingFromY = isNotFallingFromY(myRobo.getSimulatorSurface());
        Position newPosition = move(myRobo.getCurrentPosition());
        return
                Optional.ofNullable(myRobo)
                        .filter(s -> allowAction(s))
                        .flatMap(s -> Optional.ofNullable(newPosition))
                        .filter(isNotFallingFromX)
                        .filter(isNotFallingFromY)
                        .isPresent();

    }



}