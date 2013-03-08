
package org.iolani.frc;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.DigitalIOButton;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import org.iolani.frc.commands.*;
import org.iolani.frc.commands.CommandBase;
import org.iolani.frc.util.PowerScaler;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
    private final Joystick _lStick = new Joystick(1);
    private final Joystick _rStick = new Joystick(2);
    
    private final Button _intakeButton = new JoystickButton(_lStick, 1);
    private final Button _batWingsButton = new JoystickButton(_rStick, 4);
    private final Button _hangerUnretractButton = new JoystickButton(_lStick, 4);
    private final Button _hangerRetractButton = new JoystickButton(_lStick, 5);
    
    public Joystick getLeftStick() {
        return _lStick;
    }
    
    public Joystick getRightStick() {
        return _rStick;
    }
    
    private PowerScaler _driveScaler;
    
    public PowerScaler getDriveScaler() {
        return _driveScaler;
    }
    
    public boolean getBatWingsButton() {
        return _batWingsButton.get();
    }
    
    public OI() {
        _intakeButton.whileHeld(new IntakeTest());
        _batWingsButton.whenPressed(new ToggleBatDeployed());
        
        _hangerUnretractButton.whenPressed(new SetHangerRetracted(false));
        _hangerRetractButton.whenPressed(new SetHangerRetracted(true));
        
        _driveScaler = new PowerScaler(new PowerScaler.PowerPoint[] {
                new PowerScaler.PowerPoint(0.05, 0.0),
                new PowerScaler.PowerPoint(0.8, 0.5),
                new PowerScaler.PowerPoint(0.95, 1.0)
            });
    }
}

