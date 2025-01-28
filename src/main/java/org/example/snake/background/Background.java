package org.example.snake.background;

import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Box;
import javafx.scene.transform.Rotate;

import java.util.Objects;

public class Background {
    public void createBackground(Group root) {
        Image backgroundImage = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/bg2.jpg")));  // Provide path to the image
        Box background = new Box(1660, 1000, 1);
        PhongMaterial material = new PhongMaterial();
        material.setDiffuseMap(backgroundImage);
        background.setMaterial(material);
        background.setHeight(130);
        background.setWidth(150);
        background.setScaleX(1);
        background.setScaleY(1);
        background.setTranslateX(-6);
        background.setTranslateY(35);
        background.setTranslateZ(67);
        background.setRotationAxis(Rotate.X_AXIS);
        background.setRotate(-45);
        root.getChildren().add(background);
    }
}
