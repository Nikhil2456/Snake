package org.example.snake.animation;

import javafx.animation.ScaleTransition;
import javafx.geometry.Point3D;
import javafx.scene.Group;
import javafx.scene.transform.Rotate;
import javafx.util.Duration;
import org.example.snake.mess.Cube;

public class SnakeAnimation {
    private void applyRotation(Cube segment, Point3D direction) {
        double angle = Math.toDegrees(Math.atan2(direction.getY(), direction.getX()));
        segment.setRotationAxis(Rotate.Y_AXIS);
        segment.setRotate(angle);
    }
    private void pulseAnimationSnake(Group snake){
        ScaleTransition pulse = new ScaleTransition(Duration.seconds(1),snake);
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
