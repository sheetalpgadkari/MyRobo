package me.robo.action;

import me.robo.MyRobo;
import me.robo.movement.Position;

import java.util.Optional;

public interface RoboAction
{
    Optional<Position> execute(MyRobo robotBobSimulator);


}
