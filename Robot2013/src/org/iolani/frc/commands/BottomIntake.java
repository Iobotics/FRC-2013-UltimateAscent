/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.iolani.frc.commands;

import org.iolani.frc.subsystems.Conveyor;

/**
 *
 * @author iobotics
 */
public class BottomIntake extends CommandBase {
    
    private Conveyor.ConveyorMode _mode;
    
    public BottomIntake(Conveyor.ConveyorMode mode) {
        requires(intake);
        _mode = mode;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        intake.setConveyor(_mode);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        if(intake.getConveyorMode() == Conveyor.ConveyorMode.kDown)
            return (sensors.getBelowIntake());
        else if(intake.getConveyorMode() == Conveyor.ConveyorMode.kUp)
            return (sensors.getBetweenIntakeHopper());
        else
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
