/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.iolani.frc.commands;

/**
 *
 * @author Hobbes
 */
public class FireFrisbee extends CommandBase {
    
    // time to give the piston to push disk into shooter //
    private static final double FIRE_TIME   = 0.5;
    // time to give piston to return to home position //
    private static final double RELOAD_TIME = 0.5;
    
    private boolean _abort = false;
    
    public FireFrisbee() {
        this.requires(pusher);
        this.setInterruptible(false);
        this.setTimeout(FIRE_TIME + RELOAD_TIME); // timeout after both periods //
    }

    protected void initialize() {
        if(!shooter.isRunning()) {
            _abort = true;
        } else {
            pusher.set(true);
            _abort = false;
        }
    }

    protected void execute() {
        // after fire timeout, pull back pusher //
        if(pusher.get() && (this.timeSinceInitialized() > FIRE_TIME)) {
            pusher.set(false);
        }
    }

    protected boolean isFinished() {
        // abort returns immediately, otherwise delay until timeout //
        return _abort || isTimedOut();
    }

    protected void end() {
        // redundant because we are uninterruptible, but do for safety //
        pusher.set(false);
    }

    protected void interrupted() {
        end(); // should never be called //
    }
}
