/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.iolani.frc.commands;

/**
 *
 * @author iobotics
 */
public class ShooterTest extends CommandBase {
    
    public ShooterTest() {
        requires(shooter);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        System.out.println("test: " + oi.getLeftStick().getThrottle() + " " + oi.getLeftStick().getRawButton(7));
        shooter.setPower(oi.getLeftStick().getTwist());
        shooter.setPusher(oi.getLeftStick().getRawButton(7));
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
        shooter.setPower(0.0);
        shooter.setPusher(false);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
        this.end();
    }
}
