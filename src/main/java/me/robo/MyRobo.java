package me.robo;

import me.robo.action.RoboAction;
import me.robo.movement.Position;
import me.robo.simulator.SimulatorSurface;

import java.util.Optional;

public class MyRobo
{
    Position currentPosition;
    SimulatorSurface simulatorSurface;

    public MyRobo(SimulatorSurface simulatorSurface) {
        this.simulatorSurface = simulatorSurface;
    }

    public void performAction(RoboAction action) {
        currentPosition = action.execute(this).orElse(null);
    }

    public String report()
    {
        return  Optional.ofNullable(currentPosition).map(Position::toString).orElse("Not valid position");
    }

    public Position getCurrentPosition() {
        return currentPosition;
    }

    public SimulatorSurface getSimulatorSurface() {
        return simulatorSurface;
    }
}
