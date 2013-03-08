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
import edu.wpi.first.wpilibj.Encoder;
import org.iolani.frc.RobotMap;
import org.iolani.frc.commands.OperatorDrive;
import org.iolani.frc.util.*;

/**
 *
 * @author iobotics
 */
public class Drivetrain extends Subsystem {
    private Talon _LFt, _LRt, _RFt, _RRt;
    
    private Encoder _LFe, _LRe, _RFe, _RRe;
    
    private RobotDrive _drive;
    
    private double GEAR_RATIO = 11.9 / 1.0;
    
    public void init() {
        _LFt = new Talon(RobotMap.driveLeftFrontPWM);
        _LRt = new Talon(RobotMap.driveLeftRearPWM);
        _RFt = new Talon(RobotMap.driveRightFrontPWM);
        _RRt = new Talon(RobotMap.driveRightRearPWM);
        _LFe = new Encoder(RobotMap.driveLFEncoderA, RobotMap.driveLFEncoderB);
        _LRe = new Encoder(RobotMap.driveLREncoderA, RobotMap.driveLREncoderB);
        _RFe = new Encoder(RobotMap.driveRFEncoderA, RobotMap.driveRFEncoderB);
        _RRe = new Encoder(RobotMap.driveRREncoderA, RobotMap.driveRREncoderB);
        
        _drive = new RobotDrive(_LFt, _LRt, _RFt, _RRt);
        
        
        _drive.setSafetyEnabled(true);
    }
    
    public void setMecanum(double mag, double dir, double rot) {
        _drive.mecanumDrive_Polar(mag, dir, rot);
    }
    
    public void setTank(double left, double right) {
        _drive.tankDrive(left, -right);
    }
    
    public int getLFEncoder() {
        return _LFe.get();
    }
    
    public int getLREncoder() {
        return _LRe.get();
    }
    
    public int getRFEncoder() {
        return _RFe.get();
    }
    
    public int getRREncoder() {
        return _RRe.get();
    }
    
    //public double encoderToDist(int enc) {
    //    return 
    //}
    
    public void initDefaultCommand() {
        this.setDefaultCommand(new OperatorDrive());
    }
}
