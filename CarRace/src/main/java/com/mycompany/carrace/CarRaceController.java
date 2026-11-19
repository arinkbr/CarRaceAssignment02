package com.mycompany.carrace;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import javafx.animation.Animation;
import javafx.animation.Interpolator;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

/**
 * Connects the race controls, car animations, and results.
 *
 * @author Arin Kabir
 */
public class CarRaceController {
    private enum RaceState { READY, RUNNING, PAUSED, FINISHED }

    @FXML private Pane track;
    @FXML private Rectangle finishLine;
    @FXML private Button startButton;
    @FXML private Button pauseButton;
    @FXML private Slider speedSlider;
    @FXML private Label speedValue;
    @FXML private Label statusMessage;
    @FXML private VBox results;

    private final CarRaceModel model = new CarRaceModel();
    private final List<CarView> carViews = new ArrayList<>();
    private final List<TranslateTransition> movements = new ArrayList<>();
    private RaceState state = RaceState.READY;

    @FXML
    private void initialize() {
        for (Car car : model.getCars()) {
            addCar(car);
        }

        speedSlider.valueProperty().addListener((value, oldSpeed, newSpeed) -> updateSpeed());
        updateSpeed();
        updateButtons();
    }

    private void addCar(Car car) {
        CarView view = new CarView(car);
        carViews.add(view);
        track.getChildren().add(view);

        // Stop when the car's front reaches the near edge of the finish line.
        double distance = finishLine.getX() - CarRaceModel.START_X - CarView.FRONT_X;
        TranslateTransition movement = new TranslateTransition(
                Duration.seconds(distance / car.getSpeed()), view);
        movement.setFromX(0);
        movement.setToX(distance);
        movement.setInterpolator(Interpolator.LINEAR);
        view.translateXProperty().addListener((value, oldX, newX) -> {
            model.updatePosition(car, CarRaceModel.START_X + newX.doubleValue());
            view.updateWheels(newX.doubleValue());
        });
        movement.setOnFinished(event -> finish(car));
        movements.add(movement);
    }

    @FXML
    private void startRace() {
        if (state != RaceState.READY && state != RaceState.PAUSED) {
            return;
        }
        boolean firstStart = state == RaceState.READY;
        state = RaceState.RUNNING;
        for (TranslateTransition movement : movements) {
            // A finished car has stopped. Resume only the cars that were paused.
            if (firstStart || movement.getStatus() == Animation.Status.PAUSED) {
                movement.play();
            }
        }
        updateMessage("Race in progress.");
        updateButtons();
    }

    @FXML
    private void pauseRace() {
        if (state != RaceState.RUNNING) {
            return;
        }
        for (TranslateTransition movement : movements) {
            movement.pause();
        }
        state = RaceState.PAUSED;
        updateMessage("Race paused.");
        updateButtons();
    }

    @FXML
    private void resetRace() {
        stopAnimations();
        for (int i = 0; i < movements.size(); i++) {
            movements.get(i).jumpTo(Duration.ZERO);
            carViews.get(i).setTranslateX(0);
        }
        model.reset();
        results.getChildren().clear();
        state = RaceState.READY;
        updateMessage("Press Start when you are ready.");
        updateButtons();
    }

    private void finish(Car car) {
        model.recordFinish(car);
        int place = model.getFinishingOrder().size();
        results.getChildren().add(new Label(place + ".  " + car.getDescription()));

        if (place == model.getCars().size()) {
            state = RaceState.FINISHED;
            updateButtons();
        }
        updateMessage("");
    }

    private void updateMessage(String message) {
        if (model.getFinishingOrder().isEmpty()) {
            statusMessage.setText(message);
        } else {
            Car winner = model.getFinishingOrder().get(0);
            String note = message.isEmpty() ? "" : " " + message;
            statusMessage.setText("Congratulations, #" + winner.getNumber()
                    + " (" + winner.getColor() + ") wins!" + note);
        }
    }

    private void updateButtons() {
        startButton.setDisable(state == RaceState.RUNNING || state == RaceState.FINISHED);
        startButton.setText(state == RaceState.PAUSED ? "Resume" : "Start");
        pauseButton.setDisable(state != RaceState.RUNNING);
    }

    private void updateSpeed() {
        double speed = speedSlider.getValue();
        speedValue.setText(String.format(Locale.ROOT, "%.1f×", speed));
        for (TranslateTransition movement : movements) {
            movement.setRate(speed);
        }
    }

    public void stopAnimations() {
        for (TranslateTransition movement : movements) {
            movement.stop();
        }
    }
}
