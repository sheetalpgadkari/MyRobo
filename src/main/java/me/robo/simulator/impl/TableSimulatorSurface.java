package me.robo.simulator.impl;

import me.robo.movement.Position;
import me.robo.simulator.SimulatorSurface;

public class TableSimulatorSurface implements SimulatorSurface
{

    private Position objectPosition;

    public TableSimulatorSurface(int x, int y){
        objectPosition = new Position(x, y);
    }

    @Override
    public Position getObjectPosition() {
        return objectPosition;
    }
}
