/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.iolani.frc.commands;


import org.iolani.frc.subsystems.Loader;

/**
 *
 * @author Hobbes
 */
public class AquireBelowLoader extends AquireIntoConveyor {
    public AquireBelowLoader() {
        super(loader);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        super.initialize();
        loader.setConveyor(Loader.ConveyorMode.kUp);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        transitionSwitch = (sensors.getBetweenFlipperLoader() || transitionSwitch);
        changeInFrisbeeState = (transitionSwitch && !sensors.getBetweenFlipperLoader());
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return super.isFinished();
    }

    // Called once after isFinished returns true
    protected void end() {
        super.end();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
        super.interrupted();
    }
}
