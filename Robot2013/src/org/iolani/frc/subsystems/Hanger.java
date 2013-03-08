/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.iolani.frc.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import org.iolani.frc.RobotMap;

/**
 *
 * @author iobotics
 */
public class Hanger extends Subsystem {
    private DoubleSolenoid _solenoid = new DoubleSolenoid(RobotMap.hangerValve1, RobotMap.hangerValve2);
    private boolean _retracted;
    
    public void init() {
        setRetracted(false);
    }
    
    public void setRetracted(boolean state) {
        if(state) {
            _solenoid.set(DoubleSolenoid.Value.kForward);
        } else {
            _solenoid.set(DoubleSolenoid.Value.kReverse);
        }
        _retracted = state;
    }

    public boolean isRetracted() {
        return _retracted;
    }
    
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}
