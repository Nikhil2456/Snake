package org.example.snake.mess;
import javafx.geometry.Point3D;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Sphere;
import javafx.scene.transform.Rotate;
import org.example.snake.animation.FoodAnimation;

import java.util.List;
import java.util.Random;

public class Food extends Sphere {
    FoodAnimation foodAnimation = new FoodAnimation();
    public Food(Color color){
        super(0.5);
        setMaterial(new PhongMaterial(color));
        setRotationAxis(Rotate.X_AXIS);
        setRotate(90);
    }

    public void randomMoveFood(int boundary,Group snake ,Food food,Group root,List<Sphere> particleList){
        Random random = new Random();
        boolean validPosition = false;
        while(!validPosition){
            food.setTranslateX((int)(random.nextInt(boundary)-((double) boundary /2)));
            food.setTranslateZ((int)(random.nextInt(boundary)-((double) boundary /2)));
            validPosition = snake.getChildren().stream()
                    .map(node -> (Cube)node)
                    .noneMatch(segment->segment.isCollidingFood(food));
        }
        foodAnimation.addParticlesAroundFood(root,new Point3D(food.getTranslateX(),food.getTranslateY(),food.getTranslateZ()),particleList);
    }


    public void grow(Group root , List<Sphere> particleList, Food food, Group snake, int boundary , Point3D next , Point3D dir){
        root.getChildren().removeAll(particleList);
        food.randomMoveFood(boundary,snake,food,root,particleList);
        Cube cube = new Cube(false);
        cube.set(next.add(dir));
        snake.getChildren().add(cube);
    }
}
