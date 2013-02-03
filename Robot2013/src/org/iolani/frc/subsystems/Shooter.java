/*
 * 
 * 
 * 
 */
package org.iolani.frc.subsystems;
import edu.wpi.first.wpilibj.can.CANTimeoutException;
import edu.wpi.first.wpilibj.CANJaguar;
import edu.wpi.first.wpilibj.command.Subsystem;
import org.iolani.frc.RobotMap;
import org.iolani.frc.util.*;

/**
 * Shooter.java
 * Controls frisbee shooter power & angle
 * 2/2/2013
 */


public class Shooter extends Subsystem {
//    private char[][][][] outputSpeed = new char[3][4][100][90];
    private CANJaguar _stageOne;
    private CANJaguar _stageTwo;
    private CANJaguar _altitude;
    private ShooterParameters _shooterParams;
    private TargetParameters _targetParams;
    private final double MAX_STAGE_ONE_SPEED = 0.0;
    private final double SAFE_STAGE_ONE_SPEED = 0.0;
    private final double MAX_STAGE_TWO_SPEED = 0.0;
    private final double SAFE_STAGE_TWO_SPEED = 0.0;
    private final double MAX_NO_SLIP_SPEED_CHANGE = 0.0;
    private final double WHEEL_TO_FRISBEE_CONVERSION_K = 0.0;
    
    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
    public void init() {
        _stageOne = Utility.createJaguar("stage one",   RobotMap.shooterStageOneJaguarID,   CANJaguar.ControlMode.kSpeed, CANJaguar.SpeedReference.kEncoder);
        _stageTwo = Utility.createJaguar("stage two",   RobotMap.shooterStageTwoJaguarID,   CANJaguar.ControlMode.kSpeed, CANJaguar.SpeedReference.kEncoder);
        _altitude = Utility.createJaguar("altitude",    RobotMap.shooterAltitudeJaguarID,   CANJaguar.ControlMode.kPosition, CANJaguar.PositionReference.kQuadEncoder);
    }
    
    public void enableShooter() {
        try {
            _stageOne.enableControl();
            _stageTwo.enableControl();
            _altitude.enableControl();
        } catch (CANTimeoutException e) {
            System.out.println("Error enabling Jaguars " + e);
        }
    }
    
    public void disableShooter() {
        try {
            _stageOne.disableControl();
            _stageTwo.disableControl();
            _altitude.disableControl();
        } catch (CANTimeoutException e) {
            System.out.println("Error disabling Jaguars " + e);
        }
    }
    
    public TargetParameters getTargetParameters() {
        if (_targetParams == null) {
            throw new IllegalStateException("Shot parameters not set");
        }
        return _targetParams;
    }
    
    public void setTargetParameters(TargetParameters params) {
        _targetParams = params;
    }
    
    private void setWheelSpeeds(double stageOneSpeed, double stageTwoSpeed) {
        Utility.setJaguar(_stageOne, stageOneSpeed);
        Utility.setJaguar(_stageTwo, stageTwoSpeed);
    }
    
    private void setShooterAltitude(double altitude) {
        Utility.setJaguar(_altitude, altitude);
    }

    private void setShooterParameters(TargetParameters params) {
        /* The functions of this method need to be in the subsystem for robot safety, 
         * but it really needs to be broken down into something less monolithic.
         */
        double alt;
        double stageOneV;
        double stageTwoV;
        double frisbeeRotV;
        
        frisbeeRotV = params.frisbeeSpeed / WHEEL_TO_FRISBEE_CONVERSION_K;
        alt = params.frisbeeAngle;
        stageTwoV = params.frisbeeSpeed;
        stageOneV = 0.0;
        if (frisbeeRotV <= MAX_NO_SLIP_SPEED_CHANGE && frisbeeRotV <= SAFE_STAGE_ONE_SPEED) {
            stageOneV = frisbeeRotV;
        } else if (SAFE_STAGE_ONE_SPEED <= frisbeeRotV && frisbeeRotV <= MAX_NO_SLIP_SPEED_CHANGE) {
            stageOneV = SAFE_STAGE_ONE_SPEED;
        } else if ((MAX_NO_SLIP_SPEED_CHANGE + SAFE_STAGE_ONE_SPEED) <= frisbeeRotV) {
            stageOneV = frisbeeRotV - MAX_NO_SLIP_SPEED_CHANGE;
        }
        
        if (alt < -10.0 || alt > 85.0) {
            System.out.println("IMPOSSIBLE: Shooter altitude of " + alt + "impossible");
            return;
        } else if (stageOneV > MAX_STAGE_ONE_SPEED || stageTwoV > MAX_STAGE_TWO_SPEED) {
            System.out.println("IMPOSSIBLE: Shot Frisbee velocity of " + params.frisbeeSpeed + "impossible");
            return;
        } else if (stageOneV > SAFE_STAGE_ONE_SPEED || stageTwoV > SAFE_STAGE_TWO_SPEED) {
            System.out.println("Unreliable: Shot Frisbee velocity of " + params.frisbeeSpeed + " too great for reliable shot");
        }
        setShooterAltitude(alt);
        setWheelSpeeds(stageOneV, stageTwoV);
        return;
    }
    
    public void initDefaultCommand() {
        this.disableShooter();
    }
    
    public static class TargetParameters {
        public final double frisbeeAngle;
        public final double frisbeeSpeed;
        
        public TargetParameters(double angle, double velocity) {
            this.frisbeeAngle = angle;
            this.frisbeeSpeed = velocity;
        }        
    }
    
    private static class ShooterParameters {
        public final double altitude;
        public final double stageOneSpeed;
        public final double stageTwoSpeed;
        public ShooterParameters(double alt, double stageOneVel, double stageTwoVel) {
            this.altitude = alt;
            this.stageOneSpeed = stageOneVel;
            this.stageTwoSpeed = stageTwoVel;
        }
    }
        
}
