/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.iolani.frc.commands;

/**
 *   FIXME: need more than two values
 * @author iobotics
 */
public class SetShooterElevation extends CommandBase {
    
    private final boolean _value;
    
    public SetShooterElevation(boolean value) {
        this.requires(shooter);
        _value = value;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        shooter.setElevation(_value);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return true;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
