/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.iolani.frc.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 * @author iobotics
 */
public class LoadFromGround extends CommandGroup {
    
    public LoadFromGround() {
        addSequential(new BottomIntake());
    }
}
