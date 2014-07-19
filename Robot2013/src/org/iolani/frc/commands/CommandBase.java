package org.iolani.frc.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.iolani.frc.*;
import org.iolani.frc.subsystems.*;

/**
 * The base for all commands. All atomic commands should subclass CommandBase.
 * CommandBase stores creates and stores each control system. To access a
 * subsystem elsewhere in your code in your code use CommandBase.exampleSubsystem
 * @author Author
 */
public abstract class CommandBase extends Command {

    public static OI oi;
    // Create a single static instance of all of your subsystems
    public static Drivetrain drivetrain = new Drivetrain();
    public static Intake intake = new Intake(RobotMap.intakePWM);
    public static Shooter shooter = new Shooter();
    public static ShooterPusher pusher = new ShooterPusher();
    public static BatWings batWings = new BatWings();
    public static Hanger hanger = new Hanger();
    public static Pneumatics pneumatics = new Pneumatics(RobotMap.compressorRelay, RobotMap.pressureSwitch);
    
    public static void init() {
        // This MUST be here. If the OI creates Commands (which it very likely
        // will), constructing it during the construction of CommandBase (from
        // which commands extend), subsystems are not guaranteed to be
        // yet. Thus, their requires() statements may grab null pointers. Bad
        // news. Don't move it.
        oi = new OI();
        
        pneumatics.init();
        intake.init();
        //shooter.init();
        pusher.init();
        drivetrain.init();
        batWings.init();
        hanger.init();
        

        // Show what command your subsystem is running on the SmartDashboard
        SmartDashboard.putData(drivetrain);
    }

    public CommandBase(String name) {
        super(name);
    }

    public CommandBase() {
        super();
    }
}
