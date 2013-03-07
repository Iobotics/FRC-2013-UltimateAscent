/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.iolani.frc.subsystems;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.Solenoid;
import org.iolani.frc.RobotMap;
import org.iolani.frc.util.*;
/**
 *
 * @author Hobbes
 */
public class Loader extends Conveyor {
    private Solenoid _valve;
    private LoaderMode _loaderMode;
    private static DigitalInput _chamberedSwitch; //loader in shooter position
    private static DigitalInput _stowedSwitch;  //loader in safety position (positioned in conveyor system)
    
    public void init() {
        super.init();
        // initialize belt actuator //
        // initialize lever actuator //
        // initialize chambered sensor //
        // initialize stowed sensor //
    }
    
    public Loader(int beltChannel, int valveChannel) {
        super(beltChannel);
        _valve = new Solenoid(valveChannel);
    }
    
    public boolean loaderChambered() {
        return _chamberedSwitch.get();
    }
    
    public boolean loaderStowed() {
        return _stowedSwitch.get();
    }
    
    public boolean loaderTransitioning() {
        return !(loaderStowed() || loaderChambered());
    }
    
    public LoaderMode getLoader() {
        return _loaderMode;
    }
    
    public void setLoader(LoaderMode mode) {
        if (_loaderMode == mode) return;
        switch (mode.value) {
            case LoaderMode.kHalt_val:
                //either brake actuator or set to 0.0
                break;
            case LoaderMode.kFlipUp_val:
                //set actuator to 1.0
                break;
            case LoaderMode.kFlipDown_val:
                //set actuator to -1.0
                break;
        }
        _loaderMode = mode;
    }
    
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
        
    public static final class LoaderMode {
        public static final int kHalt_val     = 0;
        public static final int kFlipUp_val   = 1;
        public static final int kFlipDown_val = 2;
        
        public static final LoaderMode kHalt     = new LoaderMode(kHalt_val);
        public static final LoaderMode kFlipUp   = new LoaderMode(kFlipUp_val);
        public static final LoaderMode kFlipDown = new LoaderMode(kFlipDown_val);
        
        private final int value;
        private LoaderMode(int val) {
            this.value = val;
        } 
    }
}