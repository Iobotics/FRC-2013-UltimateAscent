/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.iolani.frc.commands;

import org.iolani.frc.subsystems.Intake;

/**
 *
 * @author iobotics
 */
public class SetIntake extends CommandBase {
    
    private final Intake.IntakeMode _mode;
    
    public SetIntake(Intake.IntakeMode mode) {
        this.requires(intake);
        _mode = mode;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        this.intake.setIntake(_mode);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
