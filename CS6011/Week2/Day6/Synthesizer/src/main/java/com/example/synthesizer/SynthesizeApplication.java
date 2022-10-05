package com.example.synthesizer;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import java.io.IOException;

public class SynthesizeApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException, LineUnavailableException {

        Clip c = AudioSystem.getClip(); // Note, this is different from our AudioClip class.

        // This is the format that we're following, 44.1 KHz mono audio, 16 bits per sample.
        AudioFormat format16 = new AudioFormat( 44100, 16, 1, true, false );

        Button play_b = new Button( "Play" );

        AnchorPane root = new AnchorPane();

        Scene scene = new Scene(root, 400, 300);

        VBox componentWidget = new VBox();

        componentWidget.setStyle("-fx-background-color: lightblue");

        Label title = new Label();
        title.setText( "Sine Wave (440 Hz)" );

        componentWidget.getChildren().add(title);
        componentWidget.relocate( 50, 100);

        // how to get box to move with mouse?
        componentWidget.setOnMousePressed(e -> handle_mouse_press(e));

        Slider slider = new Slider(220, 880, 440);
        componentWidget.getChildren().add(slider);
        componentWidget.getChildren().add(play_b);
        slider.setOnMouseDragged(e -> handle_slider(e, slider, title) );
        play_b.setOnMousePressed(e -> {
            try {
                c.close();
                play_pressed(e, play_b, c, format16, slider.getValue());

            } catch (LineUnavailableException ex) {
                throw new RuntimeException(ex);
            }
        });


        root.getChildren().add(componentWidget);
        // Write once, don’t worry about again…
        stage.setScene(scene);
        stage.show();

    }

    private void play_pressed(MouseEvent e, Button play_b, Clip c, AudioFormat format16, double frequency) throws LineUnavailableException {

        AudioComponent sin1 = new SineWave((int) frequency);

        AudioClip clip = sin1.getClip();
        c.open( format16, clip.getData(), 0, clip.getData().length ); // Reads data from our byte array to play it.

        System.out.println( "About to play..." );
        c.start(); // Plays it.

    }

    private void handle_slider(MouseEvent e, Slider slider, Label title) {
        int value = (int) slider.getValue();
        title.setText("Sine Wave (" + value + "Hz)");

    }

    private void handle_mouse_press(MouseEvent e) {

    }

     public static void main(String[] args) {
        launch();
    }
}