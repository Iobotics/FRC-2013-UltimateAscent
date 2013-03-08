/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.iolani.frc.subsystems;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import org.iolani.frc.RobotMap;
/**
 *
 * @author iobotics
 */
public class BatWings extends Subsystem {
    private Solenoid _solenoid = new Solenoid(RobotMap.batWingsSolenoid);
    
    public void init() {
        setDeployed(false);
    }
    
    public void setDeployed(boolean state) {
        _solenoid.set(state);
    }

    public boolean isDeployed() {
        return _solenoid.get();
    }
    
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}
