/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.iolani.frc.commands.autoprograms;

import edu.wpi.first.wpilibj.command.*;
import org.iolani.frc.commands.*;

/**
 *
 * @author iobotics
 */
public class AutoTest extends CommandGroup {
    
    public AutoTest() {
        this.addSequential(new PrintCommand("AutoTest (begin)"));
        this.addSequential(new AutoDrive(30.0));
        this.addSequential(new WaitCommand(1.0));
        this.addSequential(new AutoTurn(180.0));
        this.addSequential(new WaitCommand(1.0));
        this.addSequential(new AutoDrive(30.0));
        this.addSequential(new PrintCommand("AutoTest (end)"));
    }
}
