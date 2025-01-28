package org.example.snake.animation;

import javafx.animation.ScaleTransition;
import javafx.geometry.Point3D;
import javafx.scene.Group;
import javafx.scene.PointLight;
import javafx.scene.effect.Bloom;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Sphere;
import javafx.util.Duration;
import org.example.snake.mess.Food;

import java.util.List;

public class FoodAnimation {
    public void addParticlesAroundFood(Group root, Point3D foodPosition, List<Sphere> particleList) {
        for (int i = 0; i < 20; i++) {
            Sphere particle = new Sphere(0.1);
            if(i==0){
                root.getChildren().removeAll(particle);
            }
            particle.setMaterial(new PhongMaterial(Color.WHITE));
            double offsetX = (Math.random() - 0.5) * 2;
            double offsetY = (Math.random() - 0.5) * 2;
            double offsetZ = (Math.random() - 0.5) * 2;
            particle.setTranslateX(foodPosition.getX() + offsetX);
            particle.setTranslateY(foodPosition.getY() + offsetY);
            particle.setTranslateZ(foodPosition.getZ() + offsetZ);
            pulseAnimationFoodParticla(particle);
            particleList.add(particle);
            root.getChildren().add(particle);
        }
    }

    private void pulseAnimationFoodParticla(Sphere particle){
        ScaleTransition pulse = new ScaleTransition(Duration.seconds(1),particle);
        pulse.setFromX(0.5);
        pulse.setFromY(0.5);
        pulse.setFromZ(0.5);
        pulse.setToX(0.9); // Slightly enlarge
        pulse.setToY(0.9);
        pulse.setToZ(0.9);
        pulse.setAutoReverse(true);
        pulse.setCycleCount(ScaleTransition.INDEFINITE);
        pulse.play();
    }

    public void glowEffect(Food food){
        Bloom bloom  = new Bloom();
        bloom.setThreshold(0.5);
        PointLight light = new PointLight(Color.WHITE);
        food.setEffect(bloom);
    }
    public void pulseAnimationFood(Food food){
        ScaleTransition pulse = new ScaleTransition(Duration.seconds(1),food);
        pulse.setFromX(1.0);
        pulse.setFromY(1.0);
        pulse.setFromZ(1.0);
        pulse.setToX(1.2); // Slightly enlarge
        pulse.setToY(1.2);
        pulse.setToZ(1.2);
        pulse.setAutoReverse(true);
        pulse.setCycleCount(ScaleTransition.INDEFINITE);
        pulse.play();
    }
}
