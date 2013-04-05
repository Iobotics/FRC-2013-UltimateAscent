/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.iolani.frc.commands;

/**
 *
 * @author iobotics
 */
public class SetShooterSpeed extends CommandBase {
    
    private final double  _speed;
    private final boolean _wait;
    
    public SetShooterSpeed(double speed, boolean wait) {
        requires(shooter);
        _speed = speed;
        _wait   = wait;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        shooter.setSpeed(_speed);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return !_wait || shooter.onTarget();
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
