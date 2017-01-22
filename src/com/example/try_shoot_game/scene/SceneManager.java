package com.example.try_shoot_game.scene;

import java.util.ArrayList;
import java.util.List;

public class SceneManager {
	public static List<Scene> scenes = new ArrayList<Scene>();
	private static Scene currentActiveScene;
	private static int currentSceneIndex;
	
	public static void addScene(Scene scene){
		scenes.add(scene);
	}
	
	public static List<Scene> getScenes(){
		return scenes;
	}
	
	public static Scene getScene(String id){
		Scene targetScene = null;
		for(int i =0; i<scenes.size(); i++){
			Scene scene = scenes.get(i);
			if(scene.getId().equals(id)){
				targetScene = scene;
			}
		}

		return targetScene;
	}
	
	public static int getSceneIndex(String id){
		int targetSceneIndex = -1;
		for(int i =0; i<scenes.size(); i++){
			Scene scene = scenes.get(i);
			if(scene.getId().equals(id)){
				targetSceneIndex = i;
			}
		}

		return targetSceneIndex;
	}
	
	public static void startScene(String id){
		if(currentActiveScene!=null)
			currentActiveScene.stop(); 
		Scene scene = getScene(id);
		if(scene!=null){
			scene.start();
			currentActiveScene = scene;
		}
	}
	
	public static void stopScene(String id){
		Scene scene = getScene(id);
		if(scene!=null){
			scene.stop();
		}
	}
	
	public static void startScene(int index){
		if(currentActiveScene!=null)
			currentActiveScene.stop(); 
		if(index >=0 && index < scenes.size()){
			Scene scene = scenes.get(index);
			scene.start();
			currentActiveScene = scene;
			currentSceneIndex = index;
		}
	}
	
	public static void stopScene(int index){
		if(index >=0 && index < scenes.size()){
			scenes.get(index).stop();
		}
	}
	
	public static void next(){
		currentSceneIndex++;
		if(currentActiveScene!=null)
			currentActiveScene.stop(); 
		if(currentSceneIndex == scenes.size()){
			currentSceneIndex = 0;
		}
		Scene scene = scenes.get(currentSceneIndex);
		scene.start();
		currentActiveScene = scene;
	}
	
	public static void previous(){
		currentSceneIndex--;
		if(currentActiveScene!=null)
			currentActiveScene.stop(); 
		if(currentSceneIndex == -1){
			currentSceneIndex = scenes.size()-1;
		}
		Scene scene = scenes.get(currentSceneIndex);
		scene.start();
		currentActiveScene = scene;
	}
}
