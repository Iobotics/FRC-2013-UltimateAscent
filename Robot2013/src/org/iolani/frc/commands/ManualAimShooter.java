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
public class ManualAimShooter extends CommandBase {
    private Shooter.ShooterSpeedParameters params;
    private double altitude;
    public ManualAimShooter(Shooter.ShooterSpeedParameters params, double altitude) {
        requires(shooter);
        this.params = params;
        this.altitude = altitude;
    }
    
    public ManualAimShooter(double altitude) {
        this(Shooter.ShooterSpeedParameters.kLongRange, altitude);
    }
    
    // Called just before this Command runs the first time
    protected void initialize() {
        shooter.set(params, altitude);
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
