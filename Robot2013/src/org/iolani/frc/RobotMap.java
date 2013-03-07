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
    public static final int shooterAltitudeJaguarID   = 22;
    
    // bat wings //
    public static final int batWingsSolenoid = 0;         //CHANGE CHANGE
    
    // hanger //
    public static final int hangerValve1 = 4;
    public static final int hangerValve2 = 5;
    
    // pneumatics //
    public static final int compressorRelay = 8;
    public static final int pressureSwitch  = 1;
}