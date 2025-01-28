package org.example.snake.camera;

import javafx.scene.PerspectiveCamera;
import javafx.scene.Scene;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Translate;

public class Camera {
    public void initializeCamera(double cameraFOV, Translate translate , Rotate rotate, Scene scene){
        PerspectiveCamera camera = new PerspectiveCamera(true);
        camera.setFieldOfView(cameraFOV);
        camera.getTransforms().addAll(translate,rotate);
        scene.setCamera(camera);
    }

    public double getBoundary(double cameraFOV){
        double cameraX = 0;
        double cameraY = -20;
        double cameraZ = -20;
        double distance = Math.sqrt(cameraX * cameraX + cameraY * cameraY + cameraZ * cameraZ);
        double fovInRadius = Math.toRadians(cameraFOV/2);
        double visibleHeight = 2*distance*Math.tan(fovInRadius);
        double visibleWidth = visibleHeight*(1660.0/1000.0);
        double multiplier = 1.015;
        return Math.max(visibleHeight, visibleWidth) * multiplier / 2;
    }
}
