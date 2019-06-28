package me.robo.application;


import me.robo.MyRobo;
import me.robo.action.RoboAction;
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
        try {
            List<RoboAction> actions = getCommandsFromInput();
            MyRobo robotBobSimulator = getMyRobo();
            actions.stream().forEachOrdered(c -> robotBobSimulator.performAction(c));
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }
    }

    private static List<RoboAction> getCommandsFromInput() {
        List<RoboAction> actions = new ArrayList<>();
        Scanner scanIn = new Scanner(System.in);
        boolean exit = false;
        while (!exit) {
            String inputString = scanIn.nextLine();
            if (getAction(inputString).isPresent()) {
                actions.add(getAction(inputString).get());
            } else {
                throw new RuntimeException("Invalid Input " + inputString);
            }
            if (Actions.isReportAction(inputString)) exit = true;
        }
        scanIn.close();
        return actions;
    }

    private static Optional<RoboAction> getAction(String actionString) {
        if (actionString == null || actionString.length() == 0) return Optional.empty();
        if (Actions.isPlaceAction(actionString)) {
            return getPlaceAction(actionString);
        } else if (Actions.isMoveAction(actionString)) {
            return Optional.of(new MoveAction());
        } else if (Actions.isLeftAction(actionString)) {
            return Optional.of(new LeftAction());
        } else if (Actions.isRightAction(actionString)) {
            return Optional.of(new RightAction());
        } else if (Actions.isReportAction(actionString)) {
            return Optional.of(new ReportAction());
        }
        return Optional.empty();

    }

    private static Optional<RoboAction> getPlaceAction(String actionString) {
        String arr[] = actionString.split(" ")[1].split(",");
        if (arr.length == 3) {
            return Optional.of(
                    new PlaceAction(
                            Integer.parseInt(arr[0]),
                            Integer.parseInt(arr[1]), Facing.valueOf(arr[2])
                    ));
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
