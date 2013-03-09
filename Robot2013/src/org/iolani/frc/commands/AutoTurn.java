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
public class AutoTurn extends CommandBase {
    
    private final double _degrees;
    
    public AutoTurn(double degrees) {
        requires(drivetrain);
        _degrees = degrees;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        drivetrain.autoBeginTurn(_degrees);
        System.out.println("AutoTurn (begin): " + _degrees);
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
        System.out.println("AutoTurn (end): " + _degrees);
    }
    
    protected void interrupted() {
        this.end();
    }
}
