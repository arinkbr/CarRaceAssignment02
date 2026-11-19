# Car Race

A four-lane JavaFX racing simulation by Arin Kabir. The app opens with a greeting, four car slides, and a short piece of audio. When the slides end, the track opens and waits for you to press **Start**.

## Open and run

- Use JDK 21 or 24 and Maven 3.9. The project uses JavaFX 21.0.9 for controls, FXML, and audio. Maven downloads the JavaFX libraries for your platform.
- In NetBeans, choose **File > Open Project** and select the `CarRace` folder that contains `pom.xml`. Set the project's Java platform to your JDK and click **Run Project**. The included NetBeans run action uses `javafx:run`.
- From a terminal with Maven on your PATH, go to the `CarRace` folder and run `mvn clean verify` to build or `mvn javafx:run` to launch. The first build needs access to Maven Central.

## Edit the layout in Scene Builder

Open `CarRace/src/main/resources/com/mycompany/carrace/CarRaceFXML.fxml` in Scene Builder. The layout was edited directly in FXML. The file includes the lanes, controls, and results area. The intro is hidden by default so the race layout is visible when you open the file. When you run the app, the controller adds the cars and shows the intro first. The matching stylesheet is `race.css` beside the FXML.

## Run a race

- **Start** begins the race. **Pause** stops the cars and wheels. **Resume** appears on the Start button while paused.
- Use the **Simulation speed** slider to run the race at 0.5× to 2× speed. It also changes how fast the wheels spin.
- The first car whose front reaches the finish line wins. The other cars keep going and appear in the results as they finish. At the default settings, the order is red #45, gold #88, blue #23, then green #78.
- **Reset** puts all four cars back on the starting line and clears the results. Press **Start** to race again.

## How the code works

| File | Job |
| --- | --- |
| `Car.java` | Stores one car's type, number, color, position, and speed. |
| `CarRaceModel.java` | Creates the four cars, updates their positions, tracks the order in which they finish, and resets the race data. |
| `CarRaceFXML.fxml` and `race.css` | Set up the slideshow, track, and controls and style the interface. |
| `CarView.java` | Builds each car as a group of shapes and turns the wheel spokes based on distance traveled. |
| `CarRaceController.java` | Runs the slideshow timer and audio. It handles button presses, updates the movement animations and model, and displays race messages and results. |
| `App.java` | Loads the FXML, opens the window, starts the slideshow, and tells the controller to clean up when the app closes. |

## Slideshow assets

The `slides` folder is beside the FXML file. The four included PNGs are temporary renders of the JavaFX car shapes. `intro.wav` is a temporary synthesized melody made for this project. The app loads these files from the project resources. During the race, the cars are drawn with JavaFX shapes.

Replace these temporary files before submitting the final version.

| File | What to supply |
| --- | --- |
| `car-23.png` | Your drawing of the blue #23 coupe |
| `car-45.png` | Your drawing of the red #45 roadster |
| `car-78.png` | Your drawing of the green #78 rally hatchback |
| `car-88.png` | Your drawing of the gold #88 GT |
| `intro.wav` | A PCM WAV file you made or have permission to submit, ideally around 12 seconds |

Keep the names the same and use a wide image for each car. The slideshow shows each slide for three seconds. Audio loops if the clip ends before the slideshow and stops when the track appears.

## Assignment checklist

- [x] Four cars with distinct types, numbers, colors, speeds, and lane positions.
- [x] MVC with `CarRaceModel`, `CarRaceController`, and `CarRaceFXML.fxml`.
- [x] Four-lane track with visible start and finish lines, bottom-left controls, and bottom-right results.
- [x] Polygon body and roof, rectangle windows, two circle wheels, visible spokes, and a text number on each animated car.
- [x] Start, pause, resume, reset, and speed control for movement and wheels.
- [x] Winner announced by number and color, with all four cars listed in finishing order.
- [x] Welcome message and four timed slides, followed by the race screen.
- [x] JavaFX audio playback and cleanup with the temporary WAV.
- [ ] Replace the temporary PNGs with four drawings made by Arin and include the final sound file.
- [ ] Open the layout in Scene Builder and listen to the final audio on the Mac used for the demo.
- [ ] Upload the matching final project to the course's LEA and demonstrate it to the teacher.

## Checks run on macOS

- Maven `clean verify` and `javafx:run` both succeeded with JDK 24. JavaFX printed native-access and deprecation warnings under that JDK.
- A JavaFX window check loaded the FXML and checked the car shapes, movement, wheel rotation, pause and resume, speed, repeated Start, reset during and after a race, finish positions and order, a second race, and stopping movement on close.
- A check from the built JAR verified all four slideshow images, the switch to the race, and JavaFX Media playback and cleanup. The playback check measured the player's status and elapsed time. A listening check is still pending.
