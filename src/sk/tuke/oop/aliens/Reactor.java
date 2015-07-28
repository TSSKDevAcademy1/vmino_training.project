/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sk.tuke.oop.aliens;

import sk.tuke.oop.aliens.actor.AbstractActor;
import sk.tuke.oop.framework.Animation;

/**
 *
 * @author galilei-09
 */
public class Reactor extends AbstractActor {
    private int temperature;
    private Animation normalAnimation;
    private Animation hotAnimation;
    private Animation brokenAnimation;
    private Animation offAnimation;
    private int damage;
    private boolean status;
    private Light light;
    
    public Reactor(){
        temperature = 0;
        damage = 0;
        status = false;
        normalAnimation = new Animation("resources/images/reactor_on.png", 80, 80, 100);
        normalAnimation.setPingPong(true);
        hotAnimation = new Animation("resources/images/reactor_hot.png", 80, 80, 50);
        hotAnimation.setPingPong(true);
        brokenAnimation = new Animation("resources/images/reactor_broken.png", 80, 80, 100);
        brokenAnimation.setPingPong(true);
        offAnimation = new Animation("resources/images/reactor.png", 80, 80, 100);
        setAnimation(offAnimation);
        if(light != null){
        light.setElectricityFlow(false);
        }
    }

    /**
     * @return the temperature
     */
    public float getTemperature() {
        return temperature;
    }

    /**
     * @return the damage
     */
    public int getDamage() {
        return damage;
    }
    
    public void increaseTemperature(int inc) {
        if (inc<0 || status == false){
            return;
        }
        if (damage > 50){
            temperature += inc*2;
        }
        else{
            temperature += inc;           
        }        
        if(temperature >= 5000){
            damage = 100;
        }
        else if(temperature >= 2000 && damage <=(temperature-2000)/30){
            damage = (temperature-2000)/30;
        }    
        this.updateAnimation();
    }
    
    public void decreaseTemperature(int dec){
        if(dec <0 || status == false){
            return;
        }
        if(damage >=100){
            return;
        }
        else if (damage < 50) {
            temperature-=dec;   
        }
        else {
            temperature-=dec/2;
        }
        
        this.updateAnimation();
    }
    
    private void updateAnimation(){
        if (temperature < 4000){
            this.setAnimation(normalAnimation);
            if(light != null){
                light.setElectricityFlow(true);
            }
        }
        else if (temperature < 5000){
            this.setAnimation(hotAnimation);
            if(light != null){
                light.setElectricityFlow(true);
            }
        }
        else {
            this.setAnimation(brokenAnimation);
            if(light != null){
                light.setElectricityFlow(false);
            }
        }
    }
    
    public void resetReactor(){
        temperature = 0;
        damage = 0; 
        this.updateAnimation();
    }
    
    public void repair(Hammer hammer){
        if (hammer == null){
            return;
        }
        if (temperature > 1000) {
            temperature = 1000;
        }
        if(damage > 50){
            damage -= 50;
        }
        else {
            damage = 0;
        }
        this.updateAnimation();
    }
    
    public void turnOn(){
        status = true;
        this.getAnimation().start();
        this.updateAnimation();
    }
    
    public void turnOff(){
        status = false;
        this.getAnimation().stop();
        this.updateAnimation();
        if(light != null){
                light.setElectricityFlow(false);
        }
    }
    
    public boolean isRunning(){
        return status;
    }
    
    public void addLight(Light light){
        this.light = light;
    }
    
    public void removeLight(Light light){
        light.setElectricityFlow(false);
        this.light = null;

    }
}


