# Car Race

A four-car JavaFX race by Arin Kabir. The app shows four cars and plays a short tune before opening the track. It uses JavaFX 21.0.9.

## Race controls

- **Start** begins the race. **Pause** stops the cars and wheels. **Resume** continues the race.
- **Reset** puts the cars back at the start and clears the results.
- The **speed slider** changes how fast the cars and wheels move.

The first car to reach the finish line wins. The results show all four cars in the order they finished.

## How the code works (MVC)

- `App.java` opens the window and loads the FXML.
- `CarRaceModel.java` stores the cars, their positions, and the finishing order. `Car.java` stores each car's details.
- `CarRaceFXML.fxml` lays out the screen. `CarView.java` draws the cars and turns the wheels.
- `CarRaceController.java` runs the slideshow, handles the controls, and updates the race and results.
