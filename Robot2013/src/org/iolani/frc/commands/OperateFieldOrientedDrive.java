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
public class OperateFieldOrientedDrive extends CommandBase {
    
    public OperateFieldOrientedDrive() {
        requires(drivetrain);
    }

    protected void initialize() {
    }

    protected void execute() {
        double x = oi.getRightStick().getX();
        double y = oi.getLeftStick().getY();
        double rot = oi.getLeftStick().getX();
        double gyro = drivetrain.getGyroDegrees();
        // scaler //
        drivetrain.setMecanum(x, y, rot, gyro);
        drivetrain.setGyroSensitivity(oi.getGyroSensitivity());
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
        drivetrain.setMecanum(0, 0, 0, 0);
    }

    protected void interrupted() {
        this.end();
    }
}
