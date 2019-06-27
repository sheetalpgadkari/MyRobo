package me.robo.simulator;

import me.robo.movement.Position;

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
