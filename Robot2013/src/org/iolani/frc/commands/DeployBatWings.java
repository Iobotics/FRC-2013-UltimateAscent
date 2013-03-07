/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.iolani.frc.commands;

/**
 *
 * @author iobotics
 */
public class DeployBatWings extends CommandBase {
    
    private static final double DEPLOY_TIME = 0.75;                 //CHANGE
    private boolean _wait;
    
    public DeployBatWings() {
        requires(batWings);
        requires(pneumatics);
        setTimeout(DEPLOY_TIME);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        _wait = (!batWings.isDeployed());
        batWings.setDeployed(true);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return (_wait) ? isTimedOut() : true;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
