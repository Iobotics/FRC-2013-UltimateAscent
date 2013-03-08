/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.iolani.frc.commands;

import org.iolani.frc.OI;
/**
 *
 * @author iobotics
 */
public class ToggleBatDeployed extends CommandBase {
    
    public ToggleBatDeployed() {
        requires(batWings);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        batWings.setDeployed(!batWings.isDeployed());
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return !oi.getBatWingsButton();
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {                                                          
    }
}
