package org.example.snake.mess;

import javafx.geometry.Point3D;
import javafx.scene.image.Image;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Box;
import javafx.scene.transform.Rotate;

import java.util.Arrays;
import java.util.Objects;

public class Cube extends Box {
    public Cube(boolean isHead){
        PhongMaterial material = new PhongMaterial();
        if (isHead){
            setWidth(1);
            setHeight(1);
            setDepth(1);
            Image texture = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/snakeH.jpg")));
            material.setDiffuseMap(texture);
            setMaterial(material);
        }
        else{
            setWidth(0.98);
            setHeight(0.98);
            setDepth(0.98);
            Image texture = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/snakeH.jpg")));
            material.setDiffuseMap(texture);
            setMaterial(material);
        }
        setRotationAxis(Rotate.X_AXIS);
        setRotate(90);
    }
    public void set(Point3D p) {
        setTranslateX(p.getX());
        setTranslateY(p.getY());
        setTranslateZ(p.getZ());
    }
    public boolean isColliding(Cube c){
        return getTranslateX()==c.getTranslateX()&&
                getTranslateY()==c.getTranslateY()&&
                getTranslateZ()==c.getTranslateZ();
    }
    public boolean isCollidingFood(Food c){
        return getTranslateX()==c.getTranslateX()&&
                getTranslateY()==c.getTranslateY()&&
                getTranslateZ()==c.getTranslateZ();
    }
}
