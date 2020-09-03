/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes;
import java.applet.AudioClip;
/**
 *
 * @author Carlos
 */
public class Sound {
    AudioClip sound=java.applet.Applet.newAudioClip(getClass().getResource("/hurry.wav"));;
    
    public Sound() {

    }
    
    public void play(){
        sound.play();
    }
    
    public void stop(){
        sound.stop();
    }
}
