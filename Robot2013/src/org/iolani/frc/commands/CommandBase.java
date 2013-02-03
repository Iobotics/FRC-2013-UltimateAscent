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
    public static Intake intake = new Intake(0);
    public static Loader loader = new Loader(0);
    public static Flipper flipper = new Flipper(0);
    public static Hopper hopper = new Hopper(0);
    public static Shooter shooter = new Shooter();
    public static Sensors sensors = new Sensors();

    public static void init() {
        // This MUST be here. If the OI creates Commands (which it very likely
        // will), constructing it during the construction of CommandBase (from
        // which commands extend), subsystems are not guaranteed to be
        // yet. Thus, their requires() statements may grab null pointers. Bad
        // news. Don't move it.
        oi = new OI();
        
        intake.init();
        drivetrain.init();

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
