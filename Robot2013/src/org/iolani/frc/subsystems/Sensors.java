/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.iolani.frc.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 * @author iobotics
 */
public class Sensors extends Subsystem {
    
    public boolean getBelowIntake(){
        return true;
    }
    
    public boolean getBetweenIntakeHopper() {                                  //change
        return true;                       
    }
    
    public boolean getBetweenHopperFlipper() {
        return true;
    }
    
    public boolean getBetweenFlipperLoader() {
        return true;
    }
    
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}
