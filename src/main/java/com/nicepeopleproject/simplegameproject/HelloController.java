package com.nicepeopleproject.simplegameproject;

import javafx.animation.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.List;

public class HelloController {
    AnimationTimer timer;
    Timeline eggs;
    @FXML
    private AnchorPane anchorPane;
    @FXML
    private Button startButton;

    @FXML
    public Label introLabel;

    @FXML
    public ImageView basket;
    private List<Circle> drop = new ArrayList<>();

    @FXML
    protected void onHelloButtonClick() throws InterruptedException {
        startButton.setVisible(false);
//        PauseTransition intro = new PauseTransition(Duration.seconds(1));
        Counter counter = new Counter();
//        intro.setOnFinished(new EventHandler<ActionEvent>() {
//            @Override
//            public void handle(ActionEvent actionEvent) {
//
//                if (counter.i > 0) {
//                    intro.play();
//                    introLabel.setText(Integer.toString(counter.i--));
//                    intro.stop();
//                } else {
//                    introLabel.setVisible(false);
//                    basket.setVisible(true);
//
//
//                }
//            }
//        });
//        intro.play();
        Timeline timeline = new Timeline();
//        timeline.setCycleCount(3);
//        timeline.setAutoReverse(true);
        timeline.getKeyFrames().add(new KeyFrame(Duration.seconds(4.5), new KeyValue(introLabel.scaleXProperty(), 10)));
        timeline.getKeyFrames().add(new KeyFrame(Duration.seconds(1.5), new KeyValue(introLabel.scaleXProperty(), 3)));
        timeline.getKeyFrames().add(new KeyFrame(Duration.seconds(4.5), new KeyValue(introLabel.scaleYProperty(), 10)));
        timeline.getKeyFrames().add(new KeyFrame(Duration.seconds(1.5), new KeyValue(introLabel.scaleYProperty(), 3)));
        timeline.getKeyFrames().add(new KeyFrame(Duration.seconds(0), new KeyValue(introLabel.textProperty(), "Let's go!")));
        timeline.getKeyFrames().add(new KeyFrame(Duration.seconds(1.5), new KeyValue(introLabel.textProperty(), "3")));
        timeline.getKeyFrames().add(new KeyFrame(Duration.seconds(2.5), new KeyValue(introLabel.textProperty(), "2")));
        timeline.getKeyFrames().add(new KeyFrame(Duration.seconds(3.5), new KeyValue(introLabel.textProperty(), "1")));
        timeline.getKeyFrames().add(new KeyFrame(Duration.seconds(4.5), new KeyValue(introLabel.textProperty(), "")));
        timeline.play();
        timer = new AnimationTimer() {
            @Override
            public void handle(long l) {
                updateGame();

            }
        };
        eggs = new Timeline();
        timeline.setOnFinished(actionEvent -> {
            if (!basket.isVisible()) {
                basket.setVisible(true);
                eggs.play();
                timer.start();
            }
        });
        eggs.getKeyFrames().add(new KeyFrame(Duration.seconds(1), event -> {
            drop.add(circle());
            anchorPane.getChildren().add(drop.get(drop.size() - 1));
        }));

        eggs.setCycleCount(1000);


    }

    private void updateGame() {
        for (int i = 0; i <drop.size();i++){
            drop.get(i).setLayoutY(drop.get(i).getLayoutY() + 3);

        }

    }

    private Circle circle() {
        Circle circle = new Circle();
        circle.setLayoutX(Math.random() * 494 + 3);
        circle.setLayoutY(3);
        circle.setRadius(6);
        circle.setFill(Color.BLUEVIOLET);
        return circle;
    }

    public void onKeyPressed(KeyEvent keyEvent) {
        if (basket.isVisible()) {
            switch (keyEvent.getCode()) {
                case UP -> {
                    basket.setLayoutY(basket.getLayoutY() - 5);
                    break;
                }
                case DOWN -> {
                    basket.setLayoutY(basket.getLayoutY() + 5);
                    break;
                }
                case LEFT -> {
                    basket.setLayoutX(basket.getLayoutX() - 5);
                    break;
                }
                case RIGHT -> {
                    basket.setLayoutX(basket.getLayoutX() + 5);
                }
                case ESCAPE -> {
                    timer.stop();
                    eggs.pause();
                }
                case ENTER -> {
                    timer.start();
                    eggs.play();
                }
            }

        }
    }


    public void backetDrugDetected(MouseEvent mouseEvent) {
        System.out.println("drug");
    }

    public void basketDrugRealesed(MouseDragEvent mouseDragEvent) {
        // todo fix start coords
        System.out.println("drug realeased");
    }


    public void basketOnMouseDragged(MouseEvent mouseEvent) {
        // todo move image view
        System.out.println("mouse dragged");
    }
}

class Counter {
    int i = 3;
}