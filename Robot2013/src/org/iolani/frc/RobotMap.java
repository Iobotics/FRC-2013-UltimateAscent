package org.iolani.frc;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
    // drive train //
    public static final int driveLeftFrontPWM  = 1;
    public static final int driveLeftRearPWM   = 2;
    public static final int driveRightFrontPWM = 3;
    public static final int driveRightRearPWM  = 4;
    
    // intake //
    public static final int intakePWM = 5;
    
    // shooter //
    public static final int shooterStageOneJaguarID   = 20;
    public static final int shooterStageTwoJaguarID   = 21;
    public static final int shooterStageThreeJaguarID = 22;
    public static final int shooterAltitudeJaguarID   = 23;
}