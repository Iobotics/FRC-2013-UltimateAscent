/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.iolani.frc.subsystems;

import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.Subsystem;
import org.iolani.frc.RobotMap;
import org.iolani.frc.util.*;

/**
 *
 * @author iobotics
 */
public class Conveyor extends Subsystem {
    private ConveyorMode _conveyorMode;
    
    public ConveyorMode getConveyorMode() {
        return _conveyorMode;
    }
    public void setConveyor(ConveyorMode mode) {
        if (_conveyorMode == mode) return;
        switch (mode.value) {
            case ConveyorMode.kOff_val:
                setPower(0.0);
                break;
            case ConveyorMode.kUp_val:
                setPower(1.0);
                break;
            case ConveyorMode.kDown_val:
                setPower(-1.0);
                break;
        }
        _conveyorMode = mode;
    }
    
    private void setPower(double pwr){
    }
    
    public void initDefaultCommand() {
        
    }
    
    public static final class ConveyorMode {
        // int values //
        public static final int kOff_val  = 0;
        public static final int kUp_val   = 1;
        public static final int kDown_val = 2;
        // singletons //
        public static final ConveyorMode kOff  = new ConveyorMode(kOff_val);
        public static final ConveyorMode kUp   = new ConveyorMode(kUp_val);
        public static final ConveyorMode kDown = new ConveyorMode(kDown_val);
       
        public final int value;
        private ConveyorMode(int val) {
            this.value = val;
        }
    }
}
