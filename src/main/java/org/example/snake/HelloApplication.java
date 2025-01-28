package org.example.snake;

import javafx.animation.AnimationTimer;
import javafx.animation.ScaleTransition;
import javafx.application.Application;
import javafx.geometry.Point3D;
import javafx.scene.*;
import javafx.scene.effect.Bloom;
import javafx.scene.paint.Color;
import javafx.scene.shape.Sphere;
import javafx.scene.text.Text;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Translate;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.example.snake.animation.FoodAnimation;
import org.example.snake.background.Background;
import org.example.snake.camera.Camera;
import org.example.snake.mess.Cube;
import org.example.snake.mess.Food;
import org.example.snake.score.Score;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class HelloApplication extends Application {
    private FoodAnimation foodAnimation = new FoodAnimation();
    private Score scoreObj = new Score();
    private Point3D dir = new Point3D(1,0,0);
    private Point3D next = new Point3D(0,0,0);
    private Group root = new Group();
    private Group snake = new Group();
    private Food food = new Food(Color.CADETBLUE);
    private Camera camera = new Camera();
    private AnimationTimer timer;
    private double cameraFOV = 45;
    private String score ="0";
    private int growthCounter=0;
    Text text = new Text(score);
    private double t =0;
    private double speed = .15;
    private List<Sphere> particleList= new ArrayList<>();
    private int boundary=0;
    private Scene createScene(){
        scoreObj.score(text,root);
        foodAnimation.pulseAnimationFood(food);
        foodAnimation.glowEffect(food);
        Cube cube = new Cube(true);
        new Background().createBackground(root);
        snake.getChildren().add(cube);
        boundary=(int)camera.getBoundary(cameraFOV);
        food.randomMoveFood(boundary,snake,food,root,particleList);
        root.getChildren().addAll(snake,food);
        Scene scene= new Scene(root,1660,1000 ,true);
        camera.initializeCamera(cameraFOV,new Translate(0,-20,-20),new Rotate(-45,Rotate.X_AXIS),scene);
        timer = new AnimationTimer() {
            @Override
            public void handle(long l) {
                t+=0.016;
                if(t>speed){
                    onUpdate();
                    t=0;
                }
            }
        };
        timer.start();
        return scene;
    }


    private void onUpdate(){
        next=next.add(dir);
        double z = next.getZ();
        double x = next.getX();
        double y = next.getY();
        if(x>boundary)
            x=-boundary;
        else if(x<-boundary)
            x=boundary;
        if(z>boundary)
            z=-boundary;
        else if(z<-boundary)
            z=boundary;
        next=new Point3D(x,y,z);

        Cube head = (Cube) snake.getChildren().remove(0);
        //Make it whole different game by uncommenting this develop this during the bug fixing but it emerge as a whole new game
//        for (int i = snake.getChildren().size() - 1; i > 0; i--) {
//            Node current = snake.getChildren().get(i);
//            Node previous = snake.getChildren().get(i - 1);
//            current.setTranslateX(previous.getTranslateX());
//            current.setTranslateY(previous.getTranslateY());
//            current.setTranslateZ(previous.getTranslateZ());
//        }
        head.set(next);
        snake.getChildren().add(head);
        if(growthCounter>0){
            growthCounter--;
        }
        else {
            boolean selfCollision = snake.getChildren()
                    .stream()
                    .map(n->(Cube)n)
                    .filter(c->c!=head)
                    .anyMatch(c->c.isColliding(head));
            if(selfCollision){
                text.setText("Game Over");
                timer.stop();
                return;
            }
        }
        boolean collision = snake.getChildren()
                .stream()
                .map(n->(Cube) n)
                .anyMatch(Cube->head.isCollidingFood(food));
        if (collision){
            int s = Integer.parseInt(score);
            score=Integer.toString(++s);
            if(s%5==0)speed-=0.01;
            text.setText(score);
//            grow();
            growthCounter++;
            food.grow(root,particleList,food,snake,boundary,next,dir);
        }
    }

    @Override
    public void start(Stage stage) throws IOException {
        Scene scene = createScene();
        scene.setOnKeyPressed(e->{
            double z = 0;
            double x = 0;
            double y = 0;
            switch (e.getCode()){
                case W :
                    z+=1;
                    break;
                case S :
                    z-=1;
                    break;
                case A :
                    x-=1;
                    break;
                case D :
                    x+=1;
                    break;
            }
            dir=new Point3D(x,y,z);
        });

        stage.setScene(scene);
        stage.show();
    }
    public static void main(String[] args) {
        launch();
    }
}