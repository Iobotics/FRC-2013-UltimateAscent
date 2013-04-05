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
        this.requires(shooter);
        this.setInterruptible(false);
        this.setTimeout(FIRE_TIME + RELOAD_TIME); // timeout after both periods //
    }

    protected void initialize() {
        if(shooter.getPower() == 0.0) {
            _abort = true;
        } else {
            shooter.setPusher(true);
            _abort = false;
        }
    }

    protected void execute() {
        // after fire timeout, pull back pusher //
        if(shooter.getPusher() && (this.timeSinceInitialized() > FIRE_TIME)) {
            shooter.setPusher(false);
        }
    }

    protected boolean isFinished() {
        // abort returns immediately, otherwise delay until timeout //
        return _abort || isTimedOut();
    }

    protected void end() {
        // redundant because we are uninterruptible, but do for safety //
        shooter.setPusher(false);
    }

    protected void interrupted() {
        end(); // should never be called //
    }
}
