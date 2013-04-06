/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.iolani.frc.subsystems;

import edu.wpi.first.wpilibj.CANJaguar;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import org.iolani.frc.RobotMap;
import org.iolani.frc.util.Utility;


/**
 *
 * @author iobotics
 */
public class Shooter extends Subsystem {

    private static final double STAGE1_PERCENT = 0.75;
    private static final double STAGE2_PERCENT = 1.0;
    private static final double STAGE3_PERCENT = 1.0;
    
    private CANJaguar _stageOne, _stageTwo, _stageThree;
    private DoubleSolenoid _atco;
    private double _lastPower;
    
    public void init() {
        _stageOne   = Utility.createJaguar("stageOne",   RobotMap.shooterStageOneJaguarID);
        _stageTwo   = Utility.createJaguar("stageTwo",   RobotMap.shooterStageTwoJaguarID);
        _stageThree = Utility.createJaguar("stageThree", RobotMap.shooterStageThreeJaguarID);
        _atco       = new DoubleSolenoid(RobotMap.shooterAtcoValve1, RobotMap.shooterAtcoValve2);
        
        this.setPower(0.0);
        this.setElevation(false);
    }
    
    public void setElevation(boolean value) {
        if(value) {
            _atco.set(DoubleSolenoid.Value.kForward);
        } else {
            _atco.set(DoubleSolenoid.Value.kReverse);
        }
    }
    
    public boolean getElevation() {
        return (_atco.get() == DoubleSolenoid.Value.kForward);
    }
    
    public void setPower(double power) {
        Utility.setJaguar(_stageOne,   -power * STAGE1_PERCENT);
        Utility.setJaguar(_stageTwo,   -power * STAGE2_PERCENT);
        Utility.setJaguar(_stageThree, -power * STAGE3_PERCENT);
        _lastPower  = power;
    }
    
    public boolean isRunning() {
        return (_lastPower > 0);
    }
    
    public void setStageOnePower(double power) {
        Utility.setJaguar(_stageOne, -power * STAGE1_PERCENT);
        _lastPower = power;
    }
    
    public void setStageTwoPower(double power) {
        Utility.setJaguar(_stageTwo, -power * STAGE2_PERCENT);
        _lastPower = power;
    }
    
    public void setStageThreePower(double power) {
        Utility.setJaguar(_stageThree, -power * STAGE3_PERCENT);
        _lastPower = power;
    }
    
    public void setSpeed(double speed) {
        
    }
    
    // fixme //
    public double getSpeed() {
        return 0.0;
    }
    
    // fixme //
    public boolean onTarget() {
        return true;
    }
    
    public void initDefaultCommand() {
    }
}
