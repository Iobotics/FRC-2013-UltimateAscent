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
public class Conveyor extends Subsystem {
    private static Victor _victor;
    private double _power;
    
    protected Conveyor(int channel) {
        _victor = new Victor(RobotMap.conveyorVictorPWM);
    }
    
    public void setConveyorPower(double pwr){
        _power = pwr;
        _victor.set(pwr);
    }
    
    public void initDefaultCommand() {
        
    }
}
