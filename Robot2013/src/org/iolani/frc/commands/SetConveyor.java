/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.iolani.frc.commands;

import org.iolani.frc.subsystems.*;

/**
 *
 * @author iobotics
 */
public class SetConveyor extends CommandBase {
    
    private final Conveyor               _conv;
    private final Conveyor.ConveyorMode  _mode;
    private final Conveyor.ConveyorSpeed _speed;
    
    public SetConveyor(Conveyor conv, Conveyor.ConveyorMode mode) {
        this(conv, mode, Conveyor.ConveyorSpeed.kFast);
    }
    
    public SetConveyor(Conveyor conv, Conveyor.ConveyorMode mode, Conveyor.ConveyorSpeed speed) {
        requires(conv);
        _conv  = conv;
        _mode  = mode;
        _speed = speed;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        _conv.set(_mode, _speed);
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
