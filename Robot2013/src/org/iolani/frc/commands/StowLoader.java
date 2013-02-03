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
public class StowLoader extends CommandBase {
    
    public StowLoader() {
        requires(loader);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        if (!loader.loaderStowed()) {
            loader.setLoader(Loader.LoaderMode.kFlipDown);
        }
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return loader.loaderStowed();
    }

    // Called once after isFinished returns true
    protected void end() {
        loader.setLoader(Loader.LoaderMode.kHalt);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
        loader.setLoader(Loader.LoaderMode.kHalt);
        System.out.println("Loader Stowed switch not tripped");
    }
}
