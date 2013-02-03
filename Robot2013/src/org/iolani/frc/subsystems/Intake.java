/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.iolani.frc.subsystems;

//import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.Subsystem;
import org.iolani.frc.RobotMap;
import org.iolani.frc.subsystems.Conveyor;
import org.iolani.frc.util.*;

/**
 *
 * @author iobotics
 */
public class Intake extends Conveyor {
    //private static Victor _victor;
    //private double _power;
    //private IntakeMode _mode;

    
    public void init() {
        super.init();
    }
    
    public Intake(int channel) {
        super(channel);
    }
    
    /*public void setIntakePower(double pwr) {
        _power = pwr;
        _victor.set(pwr);
    }
    
    public double getPower() {
        return _power;
    }
    
    public void setIntake(IntakeMode mode) {
        if(_mode == mode) return; 
        switch(mode.value) {
            case IntakeMode.kOff_val:
                _victor.set(0.0);
                break;
            case IntakeMode.kSuck_val:
                _victor.set(-1.0);
                break;
            case IntakeMode.kBlow_val:
                _victor.set(1.0);
                break;
        }
        _mode = mode;
    }
    
    public IntakeMode getIntake() {
        return _mode;
    }
    
    public static final class IntakeMode {
        public static final int kOff_val  = 0;
        public static final int kSuck_val = 1;
        public static final int kBlow_val = 2;
        
        public static final IntakeMode kOff  = new IntakeMode(kOff_val);
        public static final IntakeMode kSuck = new IntakeMode(kSuck_val);
        public static final IntakeMode kBlow = new IntakeMode(kBlow_val);
        
        private int value;
        
        private IntakeMode (int val) {
            value = val;
        }
    }
    
    public void initDefaultCommand() {
        this.setIntake(IntakeMode.kOff);
    }
    */
    
}
