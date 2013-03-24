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
 * @author Hobbes
 */
public class FiringPiston extends Subsystem {
    private DoubleSolenoid _solenoid;
    private boolean _fired;
    
    public void init() {
        setFired(false);
    }
    
    public void setFired(boolean state) {
        if(state) {
            _solenoid.set(DoubleSolenoid.Value.kForward);
        } else {
            _solenoid.set(DoubleSolenoid.Value.kReverse);
        }
        _fired = state;
    }

    public boolean isFired() {
        return _fired;
    }
    
    public void initDefaultCommand() {
        setFired(false);
    }
}
