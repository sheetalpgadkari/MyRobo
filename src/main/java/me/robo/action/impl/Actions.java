package me.robo.action.impl;

import java.util.regex.Pattern;

public enum Actions
{
    LEFT, MOVE, PLACE, REPORT, RIGHT;

    public static boolean  isLeftAction (String a)
    {
        return isThisAction(LEFT, a);
    }

    public static boolean  isMoveAction (String a)
    {
        return isThisAction(MOVE, a);
    }

    public static boolean  isPlaceAction (String a)
    {
        return isThisAction(PLACE, a);
    }

    public static boolean  isRightAction (String a)
    {
        return isThisAction(RIGHT, a);
    }

    public static boolean  isReportAction (String a)
    {
        return isThisAction(REPORT, a);
    }

    private static boolean isThisAction(Actions action, String a )
    {
        if(a == null)return false;
        return Pattern.compile(action.name() + "*").
                matcher(a).lookingAt();
    }
}
