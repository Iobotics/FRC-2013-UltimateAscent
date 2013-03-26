/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.iolani.frc.commands;

import org.iolani.frc.subsystems.Shooter;

/**
 *
 * @author Hobbes
 */
public class AutoAimShooter extends CommandBase {
    private static final double[] altitudeChart = {0,0,0,0,0};
    private static final int MAX_RANGE = 4;
    private static final int MIN_RANGE = 2;
    private double targetDistance;
    private double altitude;
    
    public AutoAimShooter(double targetDistance) {
        this.targetDistance = targetDistance;
        requires(shooter);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        if (MAX_RANGE <= (int) targetDistance) {
            System.out.println("target out of range");
            altitude = altitudeChart[MAX_RANGE];
            return;
        } else if (MIN_RANGE > (int) targetDistance) {
            System.out.println("target too close");
            altitude = altitudeChart[MIN_RANGE];
            return;
        }
        altitude = altitudeChart[(int) targetDistance];
        altitude += altitudeChart[(int) targetDistance + 1];
        altitude = altitude/2;
        shooter.set(Shooter.ShooterSpeedParameters.kLongRange, altitude);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return shooter.getLocked();
    }

    // Called once after isFinished returns true
    protected void end() {
        System.out.println("Shooter LOCKED ON");
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
