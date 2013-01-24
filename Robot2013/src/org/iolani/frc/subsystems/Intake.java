/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.iolani.frc.subsystems;

import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.Subsystem;
import org.iolani.frc.RobotMap;
import org.iolani.frc.util.*;

/**
 *
 * @author iobotics
 */
public class Intake extends Subsystem {
    private static Victor _victor;
    
    public void init() {
        _victor = new Victor(RobotMap.intakePWM);
        _intake = new 
    }
    
    public void rotate(double speed){
        _victo
    }
    
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}
