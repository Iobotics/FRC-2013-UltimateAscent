/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.iolani.frc.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import org.iolani.frc.subsystems.Conveyor;

/**
 *
 * @author iobotics
 */
public class Flipper extends Conveyor {
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public Flipper(int channel) {
        super(channel);
    }
    
    public void initDefaultCommand() {
        this.setConveyor(Conveyor.ConveyorMode.kOff);
    }
}
