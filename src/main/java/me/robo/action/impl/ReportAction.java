package me.robo.action.impl;

import me.robo.MyRobo;
import me.robo.movement.Position;

import java.util.Optional;

public class ReportAction extends Action {


    @Override
    boolean validate(MyRobo myRobo) {
        return true;
    }

    @Override
    Optional<Position> performAction(MyRobo myRobo) {
        System.out.println("Output : " + myRobo.report());
        return Optional.empty();
    }
}
