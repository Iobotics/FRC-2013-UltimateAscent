/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.templates.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 * @author iobotics
 */
public class DriveTrain extends Subsystem {
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
    public static RobotDrive drive;
    
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
        setDefaultCommand(new DriveWithJoystick());   // set default command
    }
    
    public DriveTrain() {
        //drive = new RobotDrive(2, 1);
        //drive.setSafetyEnabled(false);
        //add victors
    }
    
    public void setMecanum(double mag, double dir, double rot) {
        drive.mecanumDrive_Polar(mag, dir, rot);
    }
    
    public void setTank(double left, double right) {
        drive.tankDrive(left, -right);
    }
    
    public void initDefaultCommand() {
        this.setDefaultCommand(new OperatorDrive());
    }
}
