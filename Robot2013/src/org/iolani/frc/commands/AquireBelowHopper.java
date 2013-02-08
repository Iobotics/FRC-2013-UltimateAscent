/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.iolani.frc.commands;

import org.iolani.frc.subsystems.Conveyor;
import org.iolani.frc.subsystems.Flipper;

/**
 *
 * @author Hobbes
 */
public class AquireBelowHopper extends AquireIntoConveyor {
    
    public AquireBelowHopper() {
        super(hopper);
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        super.initialize();
        hopper.setConveyor(Flipper.ConveyorMode.kUp, Conveyor.ConveyorMode.kSlow);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        transitionSwitch = (sensors.getBetweenIntakeHopper() || transitionSwitch);
        changeInFrisbeeState = (transitionSwitch && !sensors.getBetweenIntakeHopper());
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
