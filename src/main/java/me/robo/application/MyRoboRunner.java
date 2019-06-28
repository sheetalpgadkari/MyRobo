package me.robo.application;


import me.robo.MyRobo;
import me.robo.action.RobotAction;
import me.robo.action.impl.*;
import me.robo.movement.Facing;
import me.robo.simulator.impl.TableSimulatorSurface;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class MyRoboRunner {

    public static final int xMax = 5;
    public static final int yMax = 5;

    public static void main(String[] args) {
        List<RobotAction> actions = getCommandsFromInput();
        MyRobo robotBobSimulator = getMyRobo();
        actions.stream().forEachOrdered(c -> robotBobSimulator.performAction(c));
    }

    private static List<RobotAction> getCommandsFromInput() {
        List<RobotAction> actions = new ArrayList<>();
        Scanner scanIn = new Scanner(System.in);
        boolean exit = false;
        while (!exit) {
            String inputString = scanIn.nextLine();
            if (getCommand(inputString).isPresent()) {
                actions.add(getCommand(inputString).get());
            } else {
                throw new RuntimeException("Invalid Input" + inputString);
            }
           if (inputString.equals("REPORT")) exit = true;
        }
        scanIn.close();
        return actions;
    }

    private static Optional<RobotAction> getCommand(String inputString) {
        if (inputString == null || inputString.length() == 0) return Optional.empty();

        if (inputString.indexOf("PLACE") >= 0) {
            String arr[] = inputString.split(" ")[1].split(",");
            if (arr.length == 3) {
                return Optional.ofNullable(
                        new PlaceAction(
                                Integer.parseInt(arr[0]),
                                Integer.parseInt(arr[1]), Facing.valueOf(arr[2])
                        ));
            }
        } else if (inputString.indexOf("MOVE") >= 0) {
            return Optional.ofNullable(new MoveAction());
        } else if (inputString.indexOf("LEFT") >= 0) {
            return Optional.ofNullable(new LeftAction());
        } else if (inputString.indexOf("RIGHT") >= 0) {
            return Optional.ofNullable(new RightAction());
        } else if (inputString.indexOf("REPORT") >= 0) {
            return Optional.ofNullable(new ReportAction());
        }
        return Optional.empty();

    }

    private static MyRobo getMyRobo() {
        return new MyRobo(getTableSimulatorSurface());
    }

    private static TableSimulatorSurface getTableSimulatorSurface() {
        return new TableSimulatorSurface(xMax, yMax);
    }
}
