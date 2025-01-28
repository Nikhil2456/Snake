package org.example.snake.score;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.transform.Rotate;

public class Score {
    public void score(Text text, Group root){
        text.setFill(Color.WHITE);
        text.setSelectionFill(Color.GREEN);
        text.setSmooth(true);
        text.setStyle("-fx-font: 20px Verdana;");
        text.setTranslateX(-65);
        text.setTranslateY(35);
        text.setTranslateZ(70);
        text.setRotationAxis(Rotate.X_AXIS);
        text.setRotate(-45);
        root.getChildren().add(text);
    }
}
