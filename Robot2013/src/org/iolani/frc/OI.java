
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
    private final Button _batWingsButton = new JoystickButton(_rStick, 1);
    private final Button _hangerButton = new JoystickButton(_rStick, 2);
    
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
    
    public OI() {
        _intakeButton.whileHeld(new IntakeTest());
        if(CommandBase.batWings.isDeployed()) {
            _batWingsButton.whenPressed(new RetractBatWings());
        }
        else {
            _batWingsButton.whenPressed(new DeployBatWings());
        }
        
        if(CommandBase.hanger.isDeployed()) {
            _hangerButton.whenPressed(new RetractHanger());
        }
        else {
            _hangerButton.whenPressed(new DeployHanger());
        }
    }
}

