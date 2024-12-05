package com.badlogic.circledemo;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class CircleDemo extends ApplicationAdapter {
    private OrthographicCamera camera;
    private ShapeRenderer shapeRenderer;
    private Viewport viewport;
    private Circle circle;
    private float followSpeed = 0.1f;

    @Override
    public void create() {
        camera = new OrthographicCamera();
        viewport = new ExtendViewport(800, 600, camera);  // Set base width and height for scaling
        shapeRenderer = new ShapeRenderer();

        // Initialize the circle at the center of the viewport
        circle = new Circle(400, 300, 20); // Centered at (400, 300) with a radius of 20
    }

    @Override
    public void render() {
        // Clear the screen
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // Update the circle's position to follow the mouse
        float mouseX = Gdx.input.getX();
        float mouseY = Gdx.input.getY();

        // Convert screen coordinates to world coordinates using the camera
        mouseX = viewport.unproject(new com.badlogic.gdx.math.Vector2(mouseX, mouseY)).x;
        mouseY = viewport.unproject(new com.badlogic.gdx.math.Vector2(mouseX, mouseY)).y;

        // Smoothly move the circle towards the mouse position
        circle.x += (mouseX - circle.x) * followSpeed;
        circle.y += (mouseY - circle.y) * followSpeed;

        // Draw the circle
        shapeRenderer.setProjectionMatrix(camera.combined);
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.circle(circle.x, circle.y, circle.radius);
        shapeRenderer.end();
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height);  // Adjust viewport on screen resize
    }

    @Override
    public void dispose() {
        shapeRenderer.dispose();
    }
}
