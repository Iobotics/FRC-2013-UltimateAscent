/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.iolani.frc.commands;

import org.iolani.frc.subsystems.Conveyor;

/**
 *
 * @author Hobbes
 */
public class EjectAboveIntake extends EjectFromConveyor {
    
    public EjectAboveIntake() {
        super(intake);
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        super.initialize();
        intake.setConveyor(Conveyor.ConveyorMode.kUp, Conveyor.ConveyorMode.kSlow);
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
