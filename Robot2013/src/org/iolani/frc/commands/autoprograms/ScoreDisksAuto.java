/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.iolani.frc.commands.autoprograms;

import edu.wpi.first.wpilibj.command.*;
import org.iolani.frc.commands.*;
import org.iolani.frc.subsystems.Conveyor;

/**
 *
 * @author iobotics
 */
public class ScoreDisksAuto extends CommandGroup {
    
    public ScoreDisksAuto() {
        this.addSequential(new AutoDrive(-94.0));
        this.addSequential(new WaitCommand(1.0));
        this.addSequential(new SetConveyor(CommandBase.loader, Conveyor.ConveyorMode.kUp));
        this.addSequential(new WaitCommand(1.0));
        this.addSequential(new SetConveyor(CommandBase.loader, Conveyor.ConveyorMode.kOff));
        this.addSequential(new WaitCommand(1.0));
        this.addSequential(new AutoDrive(94.0));
    }
}
