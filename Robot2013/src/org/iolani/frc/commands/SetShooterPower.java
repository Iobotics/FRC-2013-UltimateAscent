/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.iolani.frc.commands;

/**
 *
 * @author iobotics
 */
public class SetShooterPower extends CommandBase {
    
    private static final double STAGE_TIME = 0.5;
    
    private double  _power;
    private boolean _staged;
    private int     _currentStage;
    
    public SetShooterPower(double power) {
        this(power, false);
    }
    
    public SetShooterPower(double power, boolean staged) {
        requires(shooter);
        _power  = power;
        _staged = staged;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        _currentStage = 1;
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        if(!_staged) {
            shooter.setPower(_power);
            return;
        }
        switch(_currentStage++) {
            case 1:
                shooter.setStageOnePower(_power);
                break;
            case 2:
                shooter.setStageTwoPower(_power);
                break;
            case 3:
                shooter.setStageThreePower(_power);
                break;
        }
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return !_staged || (_currentStage > 3);
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
        shooter.setPower(0.0);
    }
}
