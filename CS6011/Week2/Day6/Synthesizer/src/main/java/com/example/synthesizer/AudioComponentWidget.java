package com.example.synthesizer;
import javafx.geometry.Bounds;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;

import java.util.ArrayList;

public class AudioComponentWidget extends Pane {
    AudioComponentWidget(AudioComponent ac, AnchorPane parent, String name){
        audioComponent_ = ac;
        parent_ = parent;
        name_ = name;


        baseLayout_ = new HBox();
        baseLayout_.setStyle( "-fx-border-color: black; -fx-border-image-width: 5 ; -fx-background-color: WHITE");

        //left Side
        leftSide_ = new VBox();
        inputCircle_ = new Circle( 10 );
        inputCircle_.setFill(Color.BLACK);
        leftSide_.getChildren().add(inputCircle_);

        //Right Side
        VBox rightSide = new VBox();
        Button closeBtn = new Button( "X");
        closeBtn.setOnMousePressed(e -> closeWidget());

        Circle outputCircle = new Circle( 10 );
        outputCircle.setFill(Color.BLUE);
        outputCircle.setOnMousePressed(e -> startConnection(e, outputCircle));
        outputCircle.setOnMouseDragged( e -> moveConnection ( e ));
        outputCircle.setOnMouseReleased( e -> endConnection(e));


        rightSide.getChildren().add(closeBtn);
        rightSide.getChildren().add( outputCircle );
        rightSide.setAlignment( Pos.CENTER );
        rightSide.setSpacing( 5 );
        rightSide.setPadding(new Insets(5 ) );

        // Center panel
        center_ = new VBox();


        title_.setText(name_);
        center_.getChildren().add(title_);
        center_.setOnMousePressed(e -> startDrag( e ) );
        center_.setOnMouseDragged(e -> handleDrag( e, outputCircle ) );
        center_.setAlignment( Pos.CENTER );
        center_.setSpacing( 5 );
        center_.setPadding(new Insets(5 ) );

        baseLayout_.getChildren().add(leftSide_);
        baseLayout_.getChildren().add(center_);
        baseLayout_.getChildren().add(rightSide);
        this.getChildren().add( baseLayout_ );

        this.setLayoutX( 50 );
        this.setLayoutY( 100 );

        parent_.getChildren().add( this );

    }

    private void handleSlider(javafx.scene.input.MouseEvent e, Slider slider, Label title) {
        int value = (int) slider.getValue();
        title.setText("Sine Wave (" + value + "Hz)");
    }

    private void endConnection(javafx.scene.input.MouseEvent e) {
        Circle speaker = SynthesizeApplication.speaker_;

        boolean connected = false;

        // check for connection to speaker
        double distance = Math.sqrt( Math.pow(speaker.getCenterX() - e.getSceneX(), 2.0) +
                Math.pow(speaker.getCenterY() - e.getSceneY(), 2.0));

        if (distance < 10) {
            SynthesizeApplication.widgetsConnectedToSpeaker_.add(this);
            connected = true;
        } else {
            SynthesizeApplication.widgetsConnectedToSpeaker_.remove(this);
        }

        //check for connection to all other widgets
        ArrayList<AudioComponentWidget> allWidgets = new ArrayList<>();
        allWidgets = SynthesizeApplication.widgets_;


        for(AudioComponentWidget w : allWidgets) {

            distance = Math.sqrt( Math.pow(w.getInputCenterX() - e.getSceneX(), 2.0) +
                    Math.pow(w.getInputCenterY() - e.getSceneY(), 2.0));

            if (distance < 10) {
                w.addInput(this);

                connected = true;
                break;
            } else {
                w.inputs_.remove(this);
            }

        }
        if(connected == false) {
            parent_.getChildren().remove(line_);
            line_ = null;
        }
    }

    private void addInput(AudioComponentWidget input) {
        inputs_.add(input);
    }

    private void moveConnection(MouseEvent e) {
        line_.setEndX( e.getSceneX() );
        line_.setEndY( e.getSceneY() );
    }

    private void startConnection(javafx.scene.input.MouseEvent e, Circle outputCircle) {
        if(line_ != null ) {
            parent_.getChildren().remove( line_ );
        }

        Bounds parentBounds = parent_.getBoundsInParent();
        Bounds bounds = outputCircle.localToScene( outputCircle.getBoundsInLocal() );

        line_ = new Line();
        line_.setStrokeWidth( 4 );
        line_.setStartX(bounds.getCenterX() - parentBounds.getMinX());
        line_.setStartY(bounds.getCenterY() - parentBounds.getMinY());
        line_.setEndX( e.getSceneX() );
        line_.setEndY( e.getSceneY() );

        parent_.getChildren().add(line_);
    }

    private void startDrag(javafx.scene.input.MouseEvent e ){
        mouseStartDragX_ = e.getSceneX();
        mouseStartDragY_ = e.getSceneY();

        widgetStartDragX_ = this.getLayoutX();
        widgetStartDragY_ = this.getLayoutY();
    }

    private void handleDrag( MouseEvent e, Circle outputCircle)throws NullPointerException{
        double mouseDelX = e.getSceneX() - mouseStartDragX_;
        double mouseDelY = e.getSceneY() - mouseStartDragY_;

        this.relocate( widgetStartDragX_ + mouseDelX, widgetStartDragY_ + mouseDelY);

        if(line_ != null) {
            Bounds parentBounds = parent_.getBoundsInParent();
            Bounds bounds = outputCircle.localToScene(outputCircle.getBoundsInLocal());

            line_.setStartX(bounds.getCenterX() - parentBounds.getMinX());
            line_.setStartY(bounds.getCenterY() - parentBounds.getMinY());
        }

        for (AudioComponentWidget w : inputs_) {
            w.line_.setEndX(this.getInputCenterX());
            w.line_.setEndY(this.getInputCenterY());
        }
    }

    public void closeWidget(){

        for(AudioComponentWidget w: SynthesizeApplication.widgets_){
            if(w.inputs_.contains(this)){
                w.inputs_.remove(this);
            }
        }
        parent_.getChildren().remove( this );
        parent_.getChildren().remove( line_ );
        line_ = null;
        SynthesizeApplication.widgets_.remove( this );
        SynthesizeApplication.widgetsConnectedToSpeaker_.remove( this );

    }

    public AudioComponent getAudioComponent(){
        for(AudioComponentWidget w: inputs_) {
            audioComponent_.connectInput(w.getAudioComponent());
        }
        return audioComponent_;
    }

    public double getInputCenterX() {
        Bounds parentBounds = parent_.getBoundsInParent();
        Bounds bounds = inputCircle_.localToScene( inputCircle_.getBoundsInLocal() );

        double temp =  bounds.getCenterX() - parentBounds.getMinX();
        return temp;
    }
    private double getInputCenterY() {
        Bounds parentBounds = parent_.getBoundsInParent();
        Bounds bounds = inputCircle_.localToScene( inputCircle_.getBoundsInLocal() );

        return bounds.getCenterY() - parentBounds.getMinY();
    }

    AudioComponent audioComponent_;
    AnchorPane parent_;
    String name_, nameLabel_;
    double mouseStartDragX_, mouseStartDragY_, widgetStartDragX_,  widgetStartDragY_;
    Label title_ = new Label();
    Line line_;
    HBox baseLayout_;
    VBox center_, leftSide_;
    Circle inputCircle_;
    ArrayList<AudioComponentWidget> inputs_ = new ArrayList<>();

}
