package me.robo.movement;

import java.security.InvalidParameterException;
import java.util.Optional;

public class Position {
    private int x;
    private int y;
    private Facing facing;

    public Position(int x, int y) {
        validateX(x);
        validateY(y);
        this.x = x;
        this.y = y;
    }

    public Position(int x, int y, Facing facing) {
        validateX(x);
        validateY(y);
        validatePosition(facing);
        this.x = x;
        this.y = y;
        this.facing = facing;
    }

    private void validatePosition(Facing facing) {
        Optional.ofNullable(facing).orElseThrow(() -> new InvalidParameterException("Facing is not valid"));
    }

    private void validateY(int y) {
        if (y < 0) throw new InvalidParameterException("Not a valid y value");
    }

    private void validateX(int x) {
        if (x < 0) throw new InvalidParameterException("Not a valid x value");
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Facing getFacing() {
        return facing;
    }

    @Override
    public String toString(){
        return
                x + ","  +
                y + ""
                + Optional.ofNullable(facing).map(f -> "," + f.name()).orElse("");
    }
}
