package me.robo.action;

import me.robo.MyRobo;
import me.robo.movement.Position;

import java.util.Optional;

public class ReportAction extends Action implements RobotAction
{

    @Override
    public Optional<Position> execute(MyRobo myRobo) {
        System.out.println("Output : " + myRobo.report());
        return Optional.empty();
    }
}
