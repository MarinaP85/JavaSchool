package com.sber.patterns.tractor;

public class Tractor {
    private Position position = new Position(0, 0);
    private Field field = new Field(5, 5);
    Orientation orientation = Orientation.NORTH;

    public void move(String command) {
        if (command.equals("F")) {
            moveForwards();
        } else if (command.equals("T")) {
            turnClockwise();
        }
    }

    public void moveForwards() {
        position = orientation.move(position);
        if (position.getX() > field.getX() || position.getY() > field.getY()) {
            throw new TractorInDitchException("Position out of bounds");
        }
    }

    public void turnClockwise() {
        orientation = orientation.turn();
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public Field getField() {
        return field;
    }

    public void setField(Field field) {
        this.field = field;
    }

    public Orientation getOrientation() {
        return orientation;
    }

}
