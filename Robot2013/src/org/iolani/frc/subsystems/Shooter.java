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

    private CANJaguar _stageOne, _stageTwo, _stageThree;
    private Solenoid  _pusher;
    private DoubleSolenoid _atco;
    private double _lastPower = 0.0;
    
    public void init() {
        _stageOne   = Utility.createJaguar("stageOne",   RobotMap.shooterStageOneJaguarID);
        _stageTwo   = Utility.createJaguar("stageTwo",   RobotMap.shooterStageTwoJaguarID);
        _stageThree = Utility.createJaguar("stageThree", RobotMap.shooterStageThreeJaguarID);
        _pusher     = new Solenoid(RobotMap.shooterPusherValve);
        _atco       = new DoubleSolenoid(RobotMap.shooterAtcoValve1, RobotMap.shooterAtcoValve2);
        
        this.setPower(0.0);
        this.setAtco(false);
    }
    
    public void setPusher(boolean value) {
        _pusher.set(value);
    }
    
    public boolean getPusher() {
        return _pusher.get();
    }
    
    public void setAtco(boolean value) {
        if(value) {
            _atco.set(DoubleSolenoid.Value.kForward);
        } else {
            _atco.set(DoubleSolenoid.Value.kReverse);
        }
    }
    
    public boolean getAtco() {
        return (_atco.get() == DoubleSolenoid.Value.kForward);
    }
    
    public void setPower(double power) {
        Utility.setJaguar(_stageOne,   -power);
        Utility.setJaguar(_stageTwo,   -power);
        Utility.setJaguar(_stageThree, -power);
        _lastPower  = power;
    }
    
    public double getPower() {
        return _lastPower;
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
