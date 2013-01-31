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
 * 1/28/2013
 */


public class Shooter extends Subsystem {
//    private char[][][][] outputSpeed = new char[3][4][100][90];
    private CANJaguar _stageOne;
    private CANJaguar _stageTwo;
    private CANJaguar _stageThree;
    private CANJaguar _altitude;
    private ShotParameters _params;
    
    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
    public void init() {
        _stageOne   = Utility.createJaguar("stage one",   RobotMap.shooterStageOneJaguarID,   CANJaguar.ControlMode.kSpeed, CANJaguar.SpeedReference.kEncoder);
        _stageTwo   = Utility.createJaguar("stage two",   RobotMap.shooterStageTwoJaguarID,   CANJaguar.ControlMode.kSpeed, CANJaguar.SpeedReference.kEncoder);
        _stageThree = Utility.createJaguar("stage three", RobotMap.shooterStageThreeJaguarID, CANJaguar.ControlMode.kSpeed, CANJaguar.SpeedReference.kEncoder);
        _altitude   = Utility.createJaguar("altitude",    RobotMap.shooterAltitudeJaguarID,   CANJaguar.ControlMode.kPosition, CANJaguar.PositionReference.kQuadEncoder);
    }
    
    public void enableShooter() {
        try {
            _stageOne.enableControl();
            _stageTwo.enableControl();
            _stageThree.enableControl();
            _altitude.enableControl();
        } catch (CANTimeoutException e) {
            System.out.println("Error enabling Jaguars " + e);
        }
    }
    
    public void disableShooter() {
        try {
            _stageOne.disableControl();
            _stageTwo.disableControl();
            _stageThree.disableControl();
            _altitude.disableControl();
        } catch (CANTimeoutException e) {
            System.out.println("Error disabling Jaguars " + e);
        }
    }
    
    private CANJaguar getJaguar(int jaguarID) {
        switch (jaguarID) {
        case 20:
            return _stageOne;
        case 21:
            return _stageTwo;
        case 22:
            return _stageThree;
        case 23:
            return _altitude;
        default:
            throw new IllegalArgumentException("Invalid Jaguar ID: " + jaguarID);
        }
    }
    
    public void setCANkPID(int jaguarID, double kP, double kI, double kD) {
        try {
            getJaguar(jaguarID).setPID(kP, kI, kD);
        } catch (CANTimeoutException e) {
            System.out.println("Error setting Jaguar PID constants: " + jaguarID + "\n" + e);
        }
    }

    public double getCANkP(int jaguarID) {
        try {
            return getJaguar(jaguarID).getP();
        } catch (CANTimeoutException e) {
            System.out.println("Error getting Jaguar kP" + jaguarID + "\n" + e);
            return 0;
        }
    }
    
    public double getCANkI(int jaguarID) {
        try {
            return getJaguar(jaguarID).getI();
        } catch (CANTimeoutException e) {
            System.out.println("Error getting Jaguar kI" + jaguarID + "\n" + e);
            return 0;
        }
    }
    
    public double getCANkD(int jaguarID) {
        try {
            return getJaguar(jaguarID).getD();
        } catch (CANTimeoutException e) {
            System.out.println("Error getting Jaguar kD" + jaguarID + "\n" + e);
            return 0;
        }
    }
    
    public double getWheelSpeed(int jaguarID) {
        try {
            return getJaguar(jaguarID).getSpeed();
        } catch (CANTimeoutException e) {
            System.out.println("Error checking wheel speed: " + e);
            return 0.0;
        }
    }
    
    public double getWheelSpeedTarget(int jaguarID) {
        try {
            return getJaguar(jaguarID).getX();
        } catch (CANTimeoutException e) {
            System.out.println("Error checking wheel speed target: " + e);
            return 0.0;
        }
    }
    
    public void setWheelSpeed(int jaguarID, double jagSpeed) {
        Utility.setJaguar(getJaguar(jaguarID), jagSpeed);
        
    }
    
    public void setWheelSpeed(double stageOneSpeed, double stageTwoSpeed, double stageThreeSpeed) {
        Utility.setJaguar(_stageOne, stageOneSpeed);
        Utility.setJaguar(_stageTwo, stageTwoSpeed);
        Utility.setJaguar(_stageThree, stageThreeSpeed);
    }
    
    public double getShooterAltitude() {
        try {
            return _altitude.getPosition();
        } catch (CANTimeoutException e) {
            System.out.println("Error checking shooter altitude: " + e);
            return 0.0;
        }
    }
    
    public double getShooterAltitudeTarget() {
        try {
            return _altitude.getX();
        } catch (CANTimeoutException e) {
            System.out.println("Error checking shooter altitude: " + e);
            return 0.0;
        }
    }
        
    public void setShooterAltitude(double altitude) {
        Utility.setJaguar(_altitude, altitude);
    }

    public ShotParameters getShotParameters() {
        if(_params == null) {
            throw new IllegalStateException("Shot parameters not set");
        }
        return _params;
    }
    
    public void setShotParameters(ShotParameters params) {
        _params = params;
    }
    
    public void initDefaultCommand() {
        this.disableShooter();
    }
    
    public static class ShotParameters {
        public final double altitude;
        public final double stageOneSpeed;
        public final double stageTwoSpeed;
        public final double stageThreeSpeed;
        public ShotParameters(double alt, double stageOneVel, double stageTwoVel, double stageThreeVel) {
            this.altitude = alt;
            this.stageOneSpeed = stageOneVel;
            this.stageTwoSpeed = stageTwoVel;
            this.stageThreeSpeed = stageThreeVel;
        }
    }
        
}
