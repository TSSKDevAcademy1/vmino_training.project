/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sk.tuke.oop.aliens;

import sk.tuke.oop.aliens.actor.AbstractActor;
import sk.tuke.oop.framework.Animation;
import sk.tuke.oop.aliens.Reactor;
/**
 *
 * @author galilei-09
 */
public class Light extends AbstractActor{
    private boolean electricity;
    private boolean status;
    private Animation onAnimation;
    private Animation offAnimation;
    
    public Light(){
    this.onAnimation = new Animation("resources/images/light_on.png", 16, 16, 10);
    this.onAnimation.setPingPong(true);
    this.offAnimation = new Animation("resources/images/light_off.png", 16, 16, 10);
    this.offAnimation.setPingPong(false);
    this.setAnimation(offAnimation);
    this.electricity = false;
    this.status = false;
    }
    
    public void toggle(){
        if (this.status) {
            this.status = false;
            setAnimation(offAnimation);
        }
        else{
           this.status = true;
            if(electricity){
            setAnimation(onAnimation);
            }
        }
    }
    
    public void setElectricityFlow(boolean state){
        this.electricity = state;
        if(this.electricity && this.status){
                this.setAnimation(onAnimation);
        }
        else{
            this.setAnimation(offAnimation);
        }
    }
}
