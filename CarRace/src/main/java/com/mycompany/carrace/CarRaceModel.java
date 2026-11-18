package com.mycompany.carrace;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Stores the lineup, current positions, and finishing order.
 *
 * @author Arin Kabir
 */
public class CarRaceModel {
    public static final double START_X = 34;

    private final List<Car> cars = List.of(
            new Car("Coupe", 23, "Blue", 113, START_X, 17),
            new Car("Roadster", 45, "Red", 142, START_X, 84),
            new Car("Rally Hatchback", 78, "Green", 104, START_X, 151),
            new Car("GT", 88, "Gold", 126, START_X, 218)
    );
    private final List<Car> finishingOrder = new ArrayList<>();

    public List<Car> getCars() {
        return cars;
    }

    public List<Car> getFinishingOrder() {
        return Collections.unmodifiableList(finishingOrder);
    }

    public void updatePosition(Car car, double x) {
        car.setX(x);
    }

    public void recordFinish(Car car) {
        if (!finishingOrder.contains(car)) {
            finishingOrder.add(car);
        }
    }

    public void reset() {
        finishingOrder.clear();
        for (Car car : cars) {
            updatePosition(car, START_X);
        }
    }
}
