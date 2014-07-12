/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.iolani.frc.commands;

/**
 *
 * @author raridao
 */
public class SetGyroSensitivity extends CommandBase {
    
    private double _voltsPerDegreePerSecond;
    
    public SetGyroSensitivity(double sensitivity) {
        requires(drivetrain);
        _voltsPerDegreePerSecond = sensitivity;
    }
    
    protected void initialize() {
        drivetrain.setGyroSensitivity(_voltsPerDegreePerSecond);
    }

    protected void execute() {
    }

    protected boolean isFinished() {
        return true;
    }

    protected void end() {
    }

    protected void interrupted() {
    }
}
