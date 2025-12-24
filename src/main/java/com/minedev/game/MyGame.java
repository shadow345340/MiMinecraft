package com.minedev.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.VertexAttributes.Usage;
import com.badlogic.gdx.graphics.g3d.*;
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;
import com.badlogic.gdx.graphics.g3d.utils.ModelBuilder;
import com.badlogic.gdx.graphics.g3d.utils.FirstPersonCameraController;
import com.badlogic.gdx.utils.Array;

public class MyGame extends ApplicationAdapter {
    private PerspectiveCamera cam;
    private ModelBatch modelBatch;
    private Model cubeModel;
    private Array<ModelInstance> instances = new Array<>();
    private FirstPersonCameraController camController;

    @Override
    public void create() {
        modelBatch = new ModelBatch();
        
        // Cámara tipo Minecraft
        cam = new PerspectiveCamera(67, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        cam.position.set(10f, 10f, 10f);
        cam.lookAt(0, 0, 0);
        cam.near = 0.1f;
        cam.far = 300f;
        cam.update();

        // Creamos un bloque base
        ModelBuilder modelBuilder = new ModelBuilder();
        cubeModel = modelBuilder.createBox(1f, 1f, 1f, 
            new Material(ColorAttribute.createDiffuse(Color.GREEN)),
            Usage.Position | Usage.Normal);

        // Generamos un suelo de 20x20 bloques
        for (int x = -10; x <= 10; x++) {
            for (int z = -10; z <= 10; z++) {
                instances.add(new ModelInstance(cubeModel, x, 0, z));
            }
        }

        // Controlamos el movimiento con WASD
        camController = new FirstPersonCameraController(cam);
        Gdx.input.setInputProcessor(camController);
    }

    @Override
    public void render() {
        camController.update();
        
        // Color del cielo
        Gdx.gl.glClearColor(0.5f, 0.8f, 1f, 1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);

        modelBatch.begin(cam);
        modelBatch.render(instances);
        modelBatch.end();
    }

    @Override
    public void dispose() {
        modelBatch.dispose();
        cubeModel.dispose();
    }
}