/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.iolani.frc.commands.autoprograms;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;
import org.iolani.frc.commands.FireFrisbee;
import org.iolani.frc.commands.SetShooterElevation;
import org.iolani.frc.commands.SetShooterPower;

/**
 *
 * @author iobotics
 */
public class FireFromPyramid extends CommandGroup {
    
    public FireFromPyramid() {
        this.addSequential(new SetShooterPower(1.0, true));
        //this.addSequential(new SetShooterElevation(true));
        this.addSequential(new WaitCommand(3.0));
        this.addSequential(new FireFrisbee());
        this.addSequential(new WaitCommand(1.0));
        this.addSequential(new FireFrisbee());
        this.addSequential(new WaitCommand(1.0));
        this.addSequential(new FireFrisbee());
        this.addSequential(new SetShooterPower(0.0));
    }
}
