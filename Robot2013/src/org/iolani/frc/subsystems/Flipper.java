/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.iolani.frc.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import org.iolani.frc.util.Utility;


/**
 *
 * @author iobotics
 */
public class Flipper extends Conveyor {
    private DoubleSolenoid _valve;
    private boolean _flipped;
    private boolean _switch1;
    private boolean _switch2;
    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
 
    public void init() {
        super.init();
    }
    
    public Flipper(int conveyorChannel, int flipValve, int unflipValve) {
        super(conveyorChannel);
        //_valve   = new DoubleSolenoid(flipValve, unflipValve);
        _flipped = false;
    }
    
    public void rotateFlipper() {
        // move flipper
        _flipped = (_flipped) ? false : true;
    }
    
    public boolean getFlipped() {
        return _flipped;
    }
    
    public boolean getSwitch1() {
        return _switch1;
    }
    
    public boolean getSwitch2() {
        return _switch2;
    }
    
    public void initDefaultCommand() {
    }
}
