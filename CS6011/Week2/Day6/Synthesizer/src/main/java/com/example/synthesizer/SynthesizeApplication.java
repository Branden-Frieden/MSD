package com.example.synthesizer;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javax.sound.sampled.*;
import java.util.ArrayList;

public class SynthesizeApplication extends Application {
    @Override
    public void start(Stage stage) {

        BorderPane root = new BorderPane();

        Scene scene = new Scene(root, 1000, 800);

        stage.setTitle( "My Synthesizer");

        ///////////////////
        // Right pane
        VBox rightPanel = new VBox(35);
        rightPanel.setAlignment(Pos.CENTER);
        rightPanel.setPadding(new Insets(10));
        rightPanel.setStyle( "-fx-background-color: grey");

        Button sineWaveBtn = new Button( "Sine Wave");
        rightPanel.getChildren().add(sineWaveBtn);
        sineWaveBtn.setOnAction( e -> createComponent( "SineWave"));

        Button VolumeBtn = new Button( "Volume");
        rightPanel.getChildren().add(VolumeBtn);
        VolumeBtn.setOnAction( e -> createComponent( "Volume"));

        Button MixerBtn = new Button( "Mixer");
        rightPanel.getChildren().add(MixerBtn);
        MixerBtn.setOnAction( e -> createComponent( "Mixer"));

        Button vfSineWaveBtn = new Button( "VF SineWave");
        rightPanel.getChildren().add(vfSineWaveBtn);
        vfSineWaveBtn.setOnAction( e -> createComponent( "VF SineWave"));


        //////////////////
        // center panel
        mainCanvas_ = new AnchorPane();
        mainCanvas_.setStyle( "-fx-background-color: darkgray");

        speaker_ = new Circle( 850, 400, 15);
        speaker_.setFill(Color.BLACK);
        mainCanvas_.getChildren().add( speaker_ );

        //////////////////
        // Bottom Panel
        HBox bottomPanel = new HBox();
        Button playBtn = new Button( "Play" );
        bottomPanel.getChildren().add( playBtn );
        bottomPanel.setAlignment(Pos.CENTER);
        playBtn.setOnAction(e -> playNetwork() );

        ///////////////////
        //set up root with all the created sections
        root.setBottom( bottomPanel );
        root.setRight( rightPanel );
        root.setCenter(mainCanvas_);

        // Write once, don’t worry about again…
        stage.setScene(scene);
        stage.show();
    }

    private void playNetwork() {

        if(widgets_.size() == 0){
            return;
        }
        try {
            Clip c = AudioSystem.getClip(); // Note, this is different from our AudioClip class.
            AudioListener listener = new AudioListener( c );

            Mixer mixer = new Mixer();
            for( AudioComponentWidget w : widgetsConnectedToSpeaker_){
                AudioComponent ac = w.getAudioComponent();
                mixer.connectInput(ac);
            }

            // This is the format that we're following, 44.1 KHz mono audio, 16 bits per sample.
            AudioFormat format = new AudioFormat(44100, 16, 1, true, false);


            byte[] data = mixer.getClip().getData();

            c.open(format, data, 0, data.length);
            c.start();
            c.addLineListener( listener );

        }catch( LineUnavailableException e ){

        }

    }

    private void createComponent(String type) {
        AudioComponentWidget acw = null;

        if(type.equals("SineWave")) {
            AudioComponent sinewave = new SineWave(440);
            acw = new SineWaveWidget(sinewave, mainCanvas_, "SineWave (440 Hz)");
        }
        else if(type.equals("Volume")) {
            AudioComponent volume = new filter( 1 );
            acw = new FilterWidget(volume, mainCanvas_, "Volume: 1.0");
        }
        else if(type.equals("Mixer")) {
            AudioComponent mixer = new Mixer();
            acw = new MixerWidget(mixer, mainCanvas_, "Mixer");
        }
        else if(type.equals("VF SineWave")) {
            AudioComponent vfSineWave = new VFSineWave();
            acw = new VFSineWaveWidget(vfSineWave, mainCanvas_, "VF SineWave min: 50, max: 2000");
        }

        widgets_.add(acw);
    }

    private void handle_mouse_press(MouseEvent e) {

    }

     public static void main(String[] args) {
        launch();
    }
    private AnchorPane mainCanvas_;
    public static Circle speaker_;

    public static ArrayList<AudioComponentWidget> widgets_ = new ArrayList<>();
    public static ArrayList<AudioComponentWidget> widgetsConnectedToSpeaker_ = new ArrayList<>();
}