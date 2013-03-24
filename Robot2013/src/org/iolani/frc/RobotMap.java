package org.iolani.frc;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
    // drive train //
    public static final int driveLeftFrontPWM  = 10;
    public static final int driveLeftRearPWM   = 8;
    public static final int driveRightFrontPWM = 9;
    public static final int driveRightRearPWM  = 7;
    public static final int driveLFEncoderA = 9;
    public static final int driveLFEncoderB = 8;
    public static final int driveLREncoderA = 3;
    public static final int driveLREncoderB = 2;
    public static final int driveRFEncoderA = 6;
    public static final int driveRFEncoderB = 7;
    public static final int driveRREncoderA = 5;
    public static final int driveRREncoderB = 4;
    public static final int driveGyro = 2;
    
    // intake //
    public static final int intakePWM = 2;
    
    // conveyor //
    public static final int hopperPWM = 3;
    
    // flipper //
    public static final int flipperPWM    = 4;
    public static final int flipperValve1 = 1;
    public static final int flipperValve2 = 2;
    
    // loader //
    public static final int loaderPWM   = 5;
    public static final int loaderValve = 3;
   
    // shooter //
    public static final int shooterStageOneJaguarID   = 20;
    public static final int shooterStageTwoJaguarID   = 21;
    public static final int shooterStageThreeJaguarID = 22;
    public static final int shooterAltitudeJaguarID   = 23;
    
    // bat wings //
    public static final int batWingsSolenoid = 6;
    
    // hanger //
    public static final int hangerValve1 = 8;
    public static final int hangerValve2 = 7;
    
    // pneumatics //
    public static final int compressorRelay = 8;
    public static final int pressureSwitch  = 1;
}