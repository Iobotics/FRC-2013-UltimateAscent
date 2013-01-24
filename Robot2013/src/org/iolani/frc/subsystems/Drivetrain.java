/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.iolani.frc.subsystems;

import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Talon;
//import edu.wpi.first.wpilibj.can.CANTimeoutException;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Subsystem;
import org.iolani.frc.RobotMap;
import org.iolani.frc.commands.OperatorDrive;
import org.iolani.frc.util.*;

/**
 *
 * @author iobotics
 */
public class Drivetrain extends Subsystem {
    private Talon _LFt, _LRt, _RFt, _RRt;
    
    private RobotDrive _drive;
    
    public void init() {
        _LFt = new Talon(RobotMap.driveLeftFrontPWM);
        _LRt = new Talon(RobotMap.driveLeftRearPWM);
        _RFt = new Talon(RobotMap.driveRightFrontPWM);
        _RRt = new Talon(RobotMap.driveRightRearPWM);
        _drive = new RobotDrive(_LFt, _LRt, _RFt, _RRt);
        
        _drive.setSafetyEnabled(false);
    }
    
    public void setMecanum(double mag, double dir, double rot) {
        _drive.mecanumDrive_Polar(mag, dir, rot);
    }
    
    public void setTank(double left, double right) {
        _drive.tankDrive(left, -right);
    }
    
    public void initDefaultCommand() {
        this.setDefaultCommand(new OperatorDrive());
    }
}
