package com.minedev.game;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

public class Main {
    public static void main(String[] args) {
        // Esta línea le pide a Java que use la tarjeta Intel que acabas de activar
        System.setProperty("sun.java2d.opengl", "true");

        LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        config.title = "¡POR FIN MI MINECRAFT!";
        config.width = 854;
        config.height = 480;
        config.useGL30 = false; // Importante: mantén esto en false porque tu tarjeta es 3.1
        
        new LwjglApplication(new MyGame(), config);
    }
}