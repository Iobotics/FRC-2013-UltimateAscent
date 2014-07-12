/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.iolani.frc.subsystems;


//import edu.wpi.first.wpilibj.can.CANTimeoutException
import edu.wpi.first.wpilibj.AnalogChannel;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Gyro;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;
import org.iolani.frc.RobotMap;
import org.iolani.frc.commands.OperatorDrive;

/**
 *
 * @author iobotics
 */
public class Drivetrain extends Subsystem {
    
    public static final double WHEEL_RADIUS = 6.0;
    public static final double WHEEL_BASE   = 19.0;
    
    private static final double FEEDBACK_RATIO = 11.0 / (50.0 * 360.0);
    private static final double OUTPUT_RATIO   = WHEEL_RADIUS * Math.PI;
    private static final double MAX_AUTO_POWER = 0.5;
    private static final double PID_TOLERANCE_INCHES = 5.0;
    
    private static final double K_P = 0.1;
    private static final double K_I = 0.0;
    private static final double K_D = 0.025;
    
    private Talon[]         _talons   = new Talon[DrivePod.numPods];
    private Encoder[]       _encoders = new Encoder[DrivePod.numPods];
    private PIDController[] _pids     = new PIDController[DrivePod.numPods];
    
    private Gyro _gyro;
    
    private boolean _encodersStarted = false;
    private DriveAction _action      = null;
    
    //private DigitalInput _encoderTest1, _encoderTest2;
    //private AnalogChannel _gyroTest1, _gyroTest2;
    
    private RobotDrive _drive;
    
    public void init() {
        _talons[DrivePod.kLeftFront_val]  = new Talon(RobotMap.driveLeftFrontPWM);
        _talons[DrivePod.kRightFront_val] = new Talon(RobotMap.driveRightFrontPWM);
        _talons[DrivePod.kLeftRear_val]   = new Talon(RobotMap.driveLeftRearPWM);
        _talons[DrivePod.kRightRear_val]  = new Talon(RobotMap.driveRightRearPWM);
        
        _encoders[DrivePod.kLeftFront_val]  = new Encoder(RobotMap.driveLFEncoderA, RobotMap.driveLFEncoderB);
        _encoders[DrivePod.kRightFront_val] = new Encoder(RobotMap.driveRFEncoderA, RobotMap.driveRFEncoderB);
        _encoders[DrivePod.kLeftRear_val]   = new Encoder(RobotMap.driveLREncoderA, RobotMap.driveLREncoderB);
        _encoders[DrivePod.kRightRear_val]  = new Encoder(RobotMap.driveRREncoderA, RobotMap.driveRREncoderB);
        
        _gyro = new Gyro(RobotMap.driveGyro);
        
        //_encoderTest1 = new DigitalInput(6);
        //_encoderTest2 = new DigitalInput(7);
        
        //_gyroTest1 = new AnalogChannel(2);
        //_gyroTest2 = new AnalogChannel(3);
        
        _drive = new RobotDrive(
                _talons[DrivePod.kLeftFront_val],  _talons[DrivePod.kLeftRear_val], 
                _talons[DrivePod.kRightFront_val], _talons[DrivePod.kRightRear_val]
            );
        _drive.setSafetyEnabled(false);
        
        // create PID controllers //
        for(int i = 0; i < DrivePod.numPods; i++) {
            if(_encoders[i] == null) { continue; }
            DrivePod pod = DrivePod.get(i);
            boolean full = _encoders[pod.getPartner().value] == null;
            DriveAdapter da = new DriveAdapter(pod, full);
            _pids[i] = new PIDController(K_P, K_I, K_D, da, da);
            _pids[i].setOutputRange(-MAX_AUTO_POWER, MAX_AUTO_POWER);
            _pids[i].setAbsoluteTolerance(PID_TOLERANCE_INCHES);
        }
    }
    
    public void startEncoders() {
        if(_encodersStarted) { return; }
        for(int i = 0; i < DrivePod.numPods; i++) {
            if(_encoders[i] == null) { continue; }
            _encoders[i].start();
        }
        _encodersStarted = true;
    }
    
    public void stopEncoders() {
        if(!_encodersStarted) { return; }
        for(int i = 0; i < DrivePod.numPods; i++) {
            if(_encoders[i] == null) { continue; }
            _encoders[i].stop();
        }
        _encodersStarted = false;
    }
    
    public void debug() {
        //System.out.println("2: " + _encoderTest1.get() + ", 3: " + _encoderTest2.get());
        //System.out.println("LF: " + this.getEncoderCount(DrivePod.kLeftFront) + ", RF: " + this.getEncoderCount(DrivePod.kRightFront));
        //System.out.println("LR: " + this.getLeftRearPosition() + ", RR: " + this.getRightRearPosition());
        //System.out.println("2: " + _gyroTest1.getVoltage() + ", 3: " + _gyroTest2.getVoltage());
        //System.out.println("Gyro angle: " + _gyro.getAngle() + ", gyro PID angle: " + _gyro.pidGet());
    }
    
    public void setMecanum(double mag, double dir, double rot) {
        _drive.mecanumDrive_Polar(mag, dir, rot);
    }
    
    public void setMecanum(double x, double y, double rot, double gyro) {
        _drive.mecanumDrive_Cartesian(x, y, rot, gyro);
    }
    
    public void setTank(double left, double right) {
        _drive.tankDrive(left, right);
    }
    
    public double getGyroDegrees() {
        return _gyro.getAngle();
    }
    
    public void resetGyro() {
        _gyro.reset();
    }
    
    public void setGyroSensitivity(double sensitivity) {
        _gyro.setSensitivity(sensitivity);
    }
    
    public void autoBeginMove(double distance) {
        if(_action != null) {
            throw new RuntimeException("An action is already running");
        }
        this.resetEncoders();
        
        this.setPIDSetpoint(DrivePod.kLeftFront, distance);
        this.setPIDSetpoint(DrivePod.kLeftRear, distance);
        this.setPIDSetpoint(DrivePod.kRightFront, distance);
        this.setPIDSetpoint(DrivePod.kRightRear, distance);
        
        this.enablePIDs();
        _action = DriveAction.kDrive;
    }
    
    public void autoBeginTurn(double degrees) {
        if(_action != null) {
            throw new RuntimeException("An action is already running");
        }
        this.resetEncoders();
        
        double distance = (degrees / 360.0) * Math.PI * WHEEL_BASE;
        
        this.setPIDSetpoint(DrivePod.kLeftFront, -distance);
        this.setPIDSetpoint(DrivePod.kLeftRear,  -distance);
        this.setPIDSetpoint(DrivePod.kRightFront, distance);
        this.setPIDSetpoint(DrivePod.kRightRear,  distance);
        
        this.enablePIDs();
        _action = DriveAction.kTurn;
    }
    
    public boolean autoIsComplete() {
        if(_action == null) { return true; }
        boolean done = true;
        for(int i = 0; i < DrivePod.numPods; i++) {
            if(_pids[i] == null) { continue; }
            done &= _pids[i].onTarget();
        }
        return done;
    }
    
    public void autoEnd() {
        this.disablePIDs();
        _action = null;
    }
    
    public int getEncoderCount(DrivePod pod) {
        Encoder e = this.getEncoder(pod);
        return (e != null)? e.get(): 0;
    }
    public double getPosition(DrivePod pod) {
        return this.getEncoderCount(pod) * FEEDBACK_RATIO * OUTPUT_RATIO;
    }
    public void resetPosition(DrivePod pod) {
        Encoder e = this.getEncoder(pod);
        if(e != null) { 
            e.reset();
        }
    }
    public boolean isPIDEnabled(DrivePod pod) {
        PIDController pid = _pids[pod.value];
        return (pid != null)? pid.isEnable(): false;
    }
    public void setPIDEnabled(DrivePod pod, boolean value) {
        PIDController pid = _pids[pod.value];
        if(pid != null) {
            if(value) {
                pid.enable();
            } else {
                pid.disable();
            }
        }
    }
    public void setPIDSetpoint(DrivePod pod, double value) {
        PIDController pid = _pids[pod.value];
        if(pid != null) {
            pid.setSetpoint(value);
        }
    }
    
    public void resetEncoders() {
        for(int i = 0; i < DrivePod.numPods; i++) {
            this.resetPosition(DrivePod.get(i));
        }
    }
    
    public void setPower(DrivePod pod, double power) {
        this.getTalon(pod).set(power);
    }
    
    public DriveAction getAction() {
        return _action;
    }
    
    public void initDefaultCommand() {
        this.setDefaultCommand(new OperatorDrive());
    }
    
    private void enablePIDs() {
        for(int i = 0; i < DrivePod.numPods; i++) {
            this.setPIDEnabled(DrivePod.get(i), true);
        }
    }
    
    private void disablePIDs() {
        for(int i = 0; i < DrivePod.numPods; i++) {
            this.setPIDEnabled(DrivePod.get(i), false);
        }
    }
    
    private Talon getTalon(DrivePod pod) {
        return _talons[pod.value];
    }
    
    private Encoder getEncoder(DrivePod pod) {
        return _encoders[pod.value];
    }
    
    public static final class DrivePod {
        public static final int kLeftFront_val  = 0;
        public static final int kRightFront_val = 1;
        public static final int kLeftRear_val   = 2;
        public static final int kRightRear_val  = 3;
        
        public static final DrivePod kLeftFront  = new DrivePod(kLeftFront_val);
        public static final DrivePod kRightFront = new DrivePod(kRightFront_val);
        public static final DrivePod kLeftRear   = new DrivePod(kLeftRear_val);
        public static final DrivePod kRightRear  = new DrivePod(kRightRear_val);
        
        private static final DrivePod[] _PODS = new DrivePod[] {
            kLeftFront, kRightFront, kLeftRear, kRightRear
        };
        
        public static final int numPods = 4;
        
        public final int value;
        private DrivePod(int val) {
            this.value = val;
        }
        
        
        public static DrivePod get(int i) {
            if(i < 0 || i >= numPods) {
                return null;
            }
            return _PODS[i];
        }
                
        // return the partner pod, the other on same side //
        public DrivePod getPartner() {
            switch(this.value) {
                case kLeftFront_val:  return kLeftRear;
                case kRightFront_val: return kRightRear;
                case kLeftRear_val:   return kLeftFront;
                case kRightRear_val:  return kRightFront;
            }
            return null;
        }
        
        // return the opposite side pod //
        public DrivePod getOpposite() {
            switch(this.value) {
                case kLeftFront_val:  return kRightFront;
                case kRightFront_val: return kLeftFront;
                case kLeftRear_val:   return kRightRear;
                case kRightRear_val:  return kLeftRear;
            }
            return null;
        }
    }
    
    public static class DriveAction {
        public static final int kNone_val  = 0;
        public static final int kDrive_val = 1;
        public static final int kTurn_val  = 2;
        
        public static final DriveAction kNone  = new DriveAction(kNone_val);
        public static final DriveAction kDrive = new DriveAction(kDrive_val);
        public static final DriveAction kTurn  = new DriveAction(kTurn_val);
       
        public final int value;
        private DriveAction(int val) {
            this.value = val;
        }
    }
    
    private class DriveAdapter implements PIDSource, PIDOutput {
        final Drivetrain.DrivePod _pod;
        final boolean _fullSideMode;
        public DriveAdapter(Drivetrain.DrivePod pod, boolean fullSideMode) {
            _pod          = pod;
            _fullSideMode = fullSideMode;
        }
        
        public double pidGet() {
            return Drivetrain.this.getPosition(_pod);
        }
        
        public void pidWrite(double power) {
            //System.out.println("setpower: " + _pod.value + " " + _pod.getPartner().value + " " + power + " " + _fullSideMode);
            Drivetrain.this.setPower(_pod, power);
            if(_fullSideMode) {
                Drivetrain.this.setPower(_pod.getPartner(), power);
            }
        }
    }
}
