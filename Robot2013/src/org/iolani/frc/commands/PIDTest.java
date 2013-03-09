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
public class PIDTest extends CommandBase {
    
    private static final Drivetrain.DrivePod _POD1 = Drivetrain.DrivePod.kLeftRear;
    private static final Drivetrain.DrivePod _POD2 = Drivetrain.DrivePod.kRightRear;
    
    public PIDTest() {
        requires(drivetrain);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        drivetrain.setPIDEnabled(_POD1, true);
        drivetrain.setPIDEnabled(_POD2, true);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        double command = oi.getLeftStick().getTwist() * 20.0;
        double position1 = drivetrain.getPosition(_POD1);
        double position2 = drivetrain.getPosition(_POD2);
        drivetrain.setPIDSetpoint(_POD1, command);
        drivetrain.setPIDSetpoint(_POD2, command);
        System.out.println("Command: " + command + ", position1: " + position1 + ", position2: " + position2
                + " " + drivetrain.isPIDEnabled(_POD1) + " " + drivetrain.isPIDEnabled(_POD2)
            );
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
        drivetrain.setPIDEnabled(_POD1, false);
        drivetrain.setPIDEnabled(_POD2, false);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
        this.end();
    }
}
