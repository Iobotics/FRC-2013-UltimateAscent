/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.iolani.frc.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.Subsystem;
import org.iolani.frc.RobotMap;
import org.iolani.frc.util.*;
/**
 *
 * @author Hobbes
 */
public class Loader extends Conveyor {
    private LoaderMode _loaderMode;
    private boolean hasFrisbeeSwitch;
    private boolean frisbeeCenteredSwitch; //frisbee centered in loader conveyor
    private boolean chamberedSwitch; //loader in shooter position
    private boolean safetiedSwitch;  //loader in safety position (positioned in conveyor system)
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
    
    public Loader(int channel) {
        super(channel);
    }
    
    public boolean hasFrisbee() {
        hasFrisbeeSwitch = false; //replace with sensor check for frisbee
        return hasFrisbeeSwitch;
    }
    
    public boolean centeredFrisbee() {
        frisbeeCenteredSwitch = false; //replace with sensor check for frisbee
        return frisbeeCenteredSwitch;
    }
    
    public boolean loaderChambered() {
        chamberedSwitch = false;
        return chamberedSwitch;
    }
    
    public boolean loaderSafetied() {
        safetiedSwitch = false;
        return safetiedSwitch;
    }
    
    public boolean loaderTransitioning() {
        return !(loaderSafetied() || loaderChambered());
    }
    
    public LoaderMode getLoaderMode() {
        return _loaderMode;
    }
    
    public void setLoader(LoaderMode mode) {
        if (_loaderMode == mode) return;
        switch (mode.value) {
            //case LoaderMode.kHalt_val:
                //either brake actuator or set to 0.0
            //    break;
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
        //public static final int kHalt_val     = 0;
        public static final int kFlipUp_val   = 1;
        public static final int kFlipDown_val = 2;
        
        //public static final LoaderMode kHalt     = new LoaderMode (kHalt_val);
        public static final LoaderMode kFlipUp   = new LoaderMode (kFlipUp_val);
        public static final LoaderMode kFlipDown = new LoaderMode (kFlipDown_val);
        
        private final int value;
        private LoaderMode(int val) {
            this.value = val;
        } 
    }
}
