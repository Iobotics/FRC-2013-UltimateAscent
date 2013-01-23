package org.iolani.frc;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
    public static final int driveLeftFrontTalonPWM  = 1;
    public static final int driveLeftRearTalonPWM   = 2;
    public static final int driveRightFrontTalonPWM = 3;
    public static final int driveRightRearTalonPWM  = 4;
}
