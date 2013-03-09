/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.iolani.frc.subsystems;

import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.Subsystem;
import org.iolani.frc.commands.SetConveyor;

/**
 *
 * @author iobotics
 */
public class Conveyor extends Subsystem {
    private Victor        _victor;
    private ConveyorMode  _mode;
    private ConveyorSpeed _speed;
    
    protected Conveyor(int channel) {
        _victor = new Victor(channel);
    }
    
    public void init() {
        this.set(ConveyorMode.kOff);
    }
    
    public ConveyorMode getMode() {
        return _mode;
    }
    
    public ConveyorSpeed getSpeed() {
        return _speed;
    }
    
    public void set(ConveyorMode mode) {
        this.set(mode, ConveyorSpeed.kFast);
    }
    
    public void set(ConveyorMode mode, ConveyorSpeed speed) {
        if(_mode == mode) {
            return;
        }
        double power = speed.value;
        switch(mode.value) {
            case ConveyorMode.kOff_val:
                power = 0.0;
                speed = ConveyorSpeed.kStopped;
                break;
            case ConveyorMode.kDown_val:
                power = -power;
                break;
        }
        _mode  = mode;
        _speed = speed;
        this.setPower(power, false);
    }
    
    public double getPower() {
        return _victor.get();
    }
    
    protected void setPower(double power) {
        this.setPower(power, true);
    }
    
    private void setPower(double power, boolean reset) {
        if(reset) {
            _mode  = ConveyorMode.kUnknown;
            _speed = ConveyorSpeed.kUnknown;
        }
        _victor.set(power);
    }
    
    public void initDefaultCommand() {
        this.setDefaultCommand(new SetConveyor(this, ConveyorMode.kOff));
    }
    
    // Until WPI updates its library from 1.3 to 1.5 we're stuck with this instead of enums. //
    public static final class ConveyorMode { 
        // int values //
        public static final int kOff_val     = 0;
        public static final int kUp_val      = 1;
        public static final int kDown_val    = 2;
        public static final int kUnknown_val = 3;
        // speeds //
        public static final double kSlow = 0.25;
        public static final double kMed  = 0.50;
        public static final double kFast = 1.00;
        // singletons //
        public static final ConveyorMode kOff     = new ConveyorMode(kOff_val);
        public static final ConveyorMode kUp      = new ConveyorMode(kUp_val);
        public static final ConveyorMode kDown    = new ConveyorMode(kDown_val);
        public static final ConveyorMode kUnknown = new ConveyorMode(kUnknown_val);
       
        public final int value;
        private ConveyorMode(int val) {
            this.value = val;
        }
    }
    
    public static final class ConveyorSpeed { 
        // int values //
        public static final double kStopped_val = 0.0;
        public static final double kSlow_val    = 0.25;
        public static final double kMed_val     = 0.5;
        public static final double kFast_val    = 1.0;
        public static final double kUnknown_val = Double.NaN;
        // singletons //
        public static final ConveyorSpeed kStopped = new ConveyorSpeed(kStopped_val);
        public static final ConveyorSpeed kSlow    = new ConveyorSpeed(kSlow_val);
        public static final ConveyorSpeed kMed     = new ConveyorSpeed(kMed_val);
        public static final ConveyorSpeed kFast    = new ConveyorSpeed(kFast_val);
        public static final ConveyorSpeed kUnknown = new ConveyorSpeed(kUnknown_val);
       
        public final double value;
        private ConveyorSpeed(double val) {
            this.value = val;
        }
    }
}