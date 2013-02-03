/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.iolani.frc.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import org.iolani.frc.subsystems.Conveyor;
import org.iolani.frc.util.Utility;
import edu.wpi.first.wpilibj.CANJaguar;

/**
 *
 * @author iobotics
 */
public class Flipper extends Conveyor {
    private CANJaguar _twister;
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void init() {
        super.init();
    }
    
    public Flipper(int conveyorChannel, int twisterChannel) {
        super(conveyorChannel);
        _twister = Utility.createJaguar("_twister", twisterChannel);
    }
    
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}
