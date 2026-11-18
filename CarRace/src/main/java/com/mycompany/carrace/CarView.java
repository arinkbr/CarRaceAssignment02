package com.mycompany.carrace;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

/**
 * Draws one car from shapes and turns its wheels as it moves.
 *
 * @author Arin Kabir
 */
public class CarView extends Group {
    public static final double FRONT_X = 98;
    private static final double WHEEL_RADIUS = 8;

    private final Group rearWheel = makeWheel(20);
    private final Group frontWheel = makeWheel(77);

    public CarView(Car car) {
        Color paint = paintFor(car.getColor());
        double roofHeight = roofHeightFor(car.getType());

        Polygon body = new Polygon(0, 24, 4, 16, 78, 16, FRONT_X, 22, FRONT_X, 33, 0, 33);
        body.setFill(paint);
        Polygon roof = new Polygon(14, 18, 31, roofHeight, 61, roofHeight, 80, 18);
        roof.setFill(paint.darker());

        Rectangle rearWindow = new Rectangle(32, roofHeight + 2, 12, 13 - roofHeight);
        Rectangle frontWindow = new Rectangle(47, roofHeight + 2, 14, 13 - roofHeight);
        rearWindow.setFill(Color.LIGHTCYAN);
        frontWindow.setFill(Color.LIGHTCYAN);

        Rectangle numberPlate = new Rectangle(36, 19, 25, 14);
        numberPlate.setArcWidth(5);
        numberPlate.setArcHeight(5);
        numberPlate.setFill(Color.WHITE);
        Text number = new Text(41, 30, Integer.toString(car.getNumber()));
        number.setFont(Font.font("System", FontWeight.BOLD, 11));

        getChildren().addAll(roof, body, rearWindow, frontWindow, numberPlate,
                number, rearWheel, frontWheel);
        setAccessibleText(car.getDescription());
        setLayoutX(CarRaceModel.START_X);
        setLayoutY(car.getY());
        setManaged(false);
    }

    public void updateWheels(double distance) {
        // Distance divided by the wheel radius gives the angle in radians.
        double degrees = Math.toDegrees(distance / WHEEL_RADIUS) % 360;
        rearWheel.setRotate(degrees);
        frontWheel.setRotate(degrees);
    }

    private static Group makeWheel(double x) {
        Circle tire = new Circle(0, 0, WHEEL_RADIUS, Color.web("#111923"));
        Line spoke = new Line(0, 0, 6, 0);
        spoke.setStroke(Color.WHITE);
        spoke.setStrokeWidth(2);
        Group wheel = new Group(tire, spoke);
        wheel.setTranslateX(x);
        wheel.setTranslateY(32);
        return wheel;
    }

    private static double roofHeightFor(String type) {
        switch (type) {
            case "Roadster": return 8;
            case "Rally Hatchback": return 0;
            case "GT": return 5;
            default: return 3;
        }
    }

    private static Color paintFor(String color) {
        switch (color) {
            case "Red": return Color.web("#ff6567");
            case "Green": return Color.web("#77e199");
            case "Gold": return Color.web("#ffd478");
            default: return Color.web("#63b3ff");
        }
    }
}
