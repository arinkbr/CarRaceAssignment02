package com.mycompany.carrace;

/**
 * A car's identity, speed in pixels per second, and current position.
 *
 * @author Arin Kabir
 */
public class Car {
    private final String type;
    private final int number;
    private final String color;
    private final double speed;
    private final double y;
    private double x;

    public Car(String type, int number, String color, double speed, double x, double y) {
        this.type = type;
        this.number = number;
        this.color = color;
        this.speed = speed;
        this.x = x;
        this.y = y;
    }

    public String getType() {
        return type;
    }

    public int getNumber() {
        return number;
    }

    public String getColor() {
        return color;
    }

    public double getSpeed() {
        return speed;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public void setX(double x) {
        this.x = x;
    }

    public String getDescription() {
        return "#" + number + " " + color + " " + type;
    }
}
