/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.iolani.frc.commands;


import org.iolani.frc.subsystems.Shooter;
import org.iolani.frc.subsystems.FiringPiston;
/**
 *
 * @author Hobbes
 */
public class FireFrisbee extends CommandBase {
    
    public FireFrisbee() {
        requires(shooter);
        requires(firingPiston);
        setInterruptible(false);
    }

    protected void initialize() {
        setTimeout(250);
        firingPiston.setFired(true);
    }

    protected void execute() {
    }

    protected boolean isFinished() {
        return isTimedOut();
    }

    protected void end() {
        firingPiston.setFired(false);
    }

    protected void interrupted() {
        firingPiston.setFired(false);
    }
}
