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
    private boolean _deployed;
    
    public void setDeployed(boolean state) {
        if(state) {
            _solenoid.set(true);
        } else {
            _solenoid.set(false);
        }
        _deployed = state;
    } //values might need to be more robust //

    public boolean isDeployed() {
        return _deployed;
    }
    
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}
