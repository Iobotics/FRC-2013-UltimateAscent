/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.iolani.frc.commands;

import org.iolani.frc.subsystems.Drivetrain;

/**
 *
 * @author iobotics
 */
public class AutoDrive extends CommandBase {
    
    private final double _distance;
    
    public AutoDrive(double distance) {
        requires(drivetrain);
        _distance = distance;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        drivetrain.autoBeginMove(_distance);
        System.out.println("AutoDrive (begin): " + _distance);
    }

    protected void execute() {    
    }
    
    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return drivetrain.autoIsComplete();
    }

    // Called once after isFinished returns true
    protected void end() {
        drivetrain.autoEnd();
        System.out.println("AutoDrive (end): " + _distance);
    }
    
    protected void interrupted() {
        this.end();
    }
}
