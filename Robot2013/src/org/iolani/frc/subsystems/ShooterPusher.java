/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.iolani.frc.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import org.iolani.frc.RobotMap;
import org.iolani.frc.util.Utility;

/**
 *
 * @author iobotics
 */
public class ShooterPusher extends Subsystem {
    
    private Solenoid _pusher;
    
    public void init() {
        _pusher = new Solenoid(RobotMap.shooterPusherValve);
    }
    
    public void set(boolean value) {
        _pusher.set(value);
    }
    
    public boolean get() {
        return _pusher.get();
    }
    
    public void initDefaultCommand() {
    }
}
