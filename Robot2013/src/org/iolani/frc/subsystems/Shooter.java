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
import java.lang.Math;

/**
 * Shooter.java
 * Controls frisbee shooter power & angle
 * 2/2/2013
 */


public class Shooter extends Subsystem {
    private double[][] slidingErrorWindow = new double[4][10];
    private int slidingWindowPos = 0;
    private static final double MAX_ALT = 0.0;
    private static final double MIN_ALT = 0.0;
    private CANJaguar _stageOne;
    private CANJaguar _stageTwo;
    private CANJaguar _stageThree;
    private CANJaguar _altitude;
    private ShooterSpeedParameters _shooterParams;
    
    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
    public void init() {
        
        _stageOne = Utility.createJaguar("stage one",   RobotMap.shooterStageOneJaguarID,   CANJaguar.ControlMode.kSpeed, CANJaguar.SpeedReference.kEncoder);
        _stageTwo = Utility.createJaguar("stage two",   RobotMap.shooterStageTwoJaguarID,   CANJaguar.ControlMode.kSpeed, CANJaguar.SpeedReference.kEncoder);
        _stageThree = Utility.createJaguar("stage two", RobotMap.shooterStageThreeJaguarID, CANJaguar.ControlMode.kSpeed, CANJaguar.SpeedReference.kEncoder);
        _altitude = Utility.createJaguar("altitude",    RobotMap.shooterAltitudeJaguarID,   CANJaguar.ControlMode.kPosition, CANJaguar.PositionReference.kQuadEncoder);
        try {
            _stageOne.configNeutralMode(CANJaguar.NeutralMode.kCoast);
            _stageTwo.configNeutralMode(CANJaguar.NeutralMode.kCoast);
            _stageThree.configNeutralMode(CANJaguar.NeutralMode.kCoast);
        } catch (CANTimeoutException e) {
            System.out.println("Error setting neutral mode of CANJaguar" + e);
        }
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
    
    public void set(ShooterSpeedParameters param, double altitude) {
        if (param == _shooterParams)
        {
            setShooterAltitude(altitude);
            return;
        }
        switch (param.value) {
            case 0:
                setWheelSpeeds(param.stageOneSpeed, param.stageTwoSpeed, param.stageThreeSpeed);
                break;
            case 1:
                setWheelSpeeds(0.0, 0.0, 0.0);
                break;
            case 2:
                setWheelSpeeds(0.0, 0.0, 0.0);
                break;
            case 3:
                setWheelSpeeds(0.0, 0.0, 0.0);
                break;
            case 4:
                coastWheels();
                break;
        }
        _shooterParams = param;
        setShooterAltitude(altitude);
    }
    
    private void coastWheels() {
        try {
            _stageOne.disableControl();
            _stageTwo.disableControl();
            _stageThree.disableControl();
        } catch (CANTimeoutException e) {
            System.out.println("Error coasting CANJaguar" + e);
        }
    }
    
    private void setWheelSpeeds(double stageOneSpeed, double stageTwoSpeed, double stageThreeSpeed) {
        if (_shooterParams.value == 4) {
            try {
                _stageOne.disableControl();
                _stageTwo.disableControl();
                _stageThree.disableControl();
            } catch (CANTimeoutException e) {
                System.out.println("Error re-enabling CANJaguar" + e);
            }
        }
        Utility.setJaguar(_stageOne, stageOneSpeed);
        Utility.setJaguar(_stageTwo, stageTwoSpeed);
        Utility.setJaguar(_stageThree, stageThreeSpeed);
    }
    
    private void setShooterAltitude(double altitude) {
        if (altitude > MAX_ALT || altitude < MIN_ALT) {
            System.out.println("Shooter set to unsafe position");
            return;
        }
        Utility.setJaguar(_altitude, altitude);
    }

    public boolean getLocked() {
        for (int i = 0; i < 4; i++) {
            slidingErrorWindow[i][9] -= slidingErrorWindow[i][slidingWindowPos];
        }
        try {
        slidingErrorWindow[0][slidingWindowPos] += Math.abs(_altitude.getX() - _altitude.getPosition());
        slidingErrorWindow[1][slidingWindowPos] += Math.abs(_stageOne.getX() - _stageOne.getPosition());
        slidingErrorWindow[2][slidingWindowPos] += Math.abs(_stageTwo.getX() - _stageTwo.getPosition());
        slidingErrorWindow[3][slidingWindowPos] += Math.abs(_stageThree.getX() - _stageThree.getPosition());
        } catch (CANTimeoutException e) {
            System.out.println("error determining error of CANJaguar" + e);
            return false;
        }
        for (int i = 0; i < 4; i++) {
            slidingErrorWindow[i][9] += slidingErrorWindow[i][slidingWindowPos];
        }
        slidingWindowPos++;
        slidingWindowPos = slidingWindowPos%9;
        if (slidingErrorWindow[0][9] < 20 * 9 && 
                slidingErrorWindow[1][9] < 5 * 9 &&
                slidingErrorWindow[2][9] < 5 * 9 &&
                slidingErrorWindow[3][9] < 5 * 9) {
            return true;
        }
        return false;
    }
       
    public void initDefaultCommand() {
        this.disableShooter();
    }
    
    public static class ShooterSpeedParameters {
        public static final int kUser_val = 0;
        public static final int kLong_val = 1;
        public static final int kMid_val  = 2;
        public static final int kShort_val = 3;
        public static final int kCoast_val = 4;
        
        public static final ShooterSpeedParameters kLongRange = new ShooterSpeedParameters(kLong_val);
        public static final ShooterSpeedParameters kMidRange = new ShooterSpeedParameters(kMid_val);
        public static final ShooterSpeedParameters kShortRange = new ShooterSpeedParameters(kShort_val);
        public static final ShooterSpeedParameters kCoast = new ShooterSpeedParameters(kCoast_val);
        
        public final double stageOneSpeed;
        public final double stageTwoSpeed;
        public final double stageThreeSpeed;
        public final int value;
        
        public ShooterSpeedParameters (double stageOneVel, double stageTwoVel, double stageThreeVel) {
            this.stageOneSpeed = stageOneVel;
            this.stageTwoSpeed = stageTwoVel;
            this.stageThreeSpeed = stageThreeVel;
            this.value = kUser_val;
        }
        private ShooterSpeedParameters (int val) {
            stageOneSpeed = Double.NaN;
            stageTwoSpeed = Double.NaN;
            stageThreeSpeed = Double.NaN;
            this.value = val;
        }
    }
}
