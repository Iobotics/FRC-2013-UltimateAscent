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
public class EjectFromConveyor extends CommandBase {
    protected boolean transitionSwitch;
    protected boolean changeInFrisbeeState;
    protected Conveyor _conveyor;
    public EjectFromConveyor(Conveyor belt) {
        _conveyor = belt;
        requires(_conveyor);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        transitionSwitch = false;
        changeInFrisbeeState = false;
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return (!_conveyor.hasFrisbee() || changeInFrisbeeState);
    }

    // Called once after isFinished returns true
    protected void end() {
        _conveyor.setConveyor(Conveyor.ConveyorMode.kOff, 0);
        _conveyor.setFrisbee(false);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
        _conveyor.setConveyor(Conveyor.ConveyorMode.kOff, 0);
        System.out.println(_conveyor + " Frisbee eject command interrupted");
    }
}
