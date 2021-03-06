/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.iolani.frc.commands;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import org.iolani.frc.commands.CommandBase;

/**
 *
 * @author iobotics
 */
public class SetHangerRetracted extends CommandBase {
        
    private final boolean _state;
    public SetHangerRetracted(boolean state) {
        requires(hanger);
        _state = state;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        hanger.setRetracted(_state);
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
