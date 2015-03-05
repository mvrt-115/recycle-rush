package org.usfirst.frc.team115.recyclerush.subsystems;

import org.usfirst.frc.team115.recyclerush.RobotMap;
import org.usfirst.frc.team115.recyclerush.commands.ElevatorControl;
import org.usfirst.frc.team115.recyclerush.commands.ResetElevatorEncoder;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.buttons.Trigger;
import edu.wpi.first.wpilibj.command.PIDSubsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * A subsystem representing the elevator, which  
 * lifts/lowers totes and bins
 * @author Akhil Palla and Lee Mracek
 */
public class Elevator extends PIDSubsystem {
	
	private CANTalon elevatorMotor1;
	private CANTalon elevatorMotor2;
	private DoubleSolenoid brakeSolenoid;
	
	private boolean past = false; 
	
	// the following measurements are in inches:
	public static final double MAX_HEIGHT = 52;
	public static final double MIN_HEIGHT = 0;
	public static final double MAX_SPEED_FINE = 1.0;
	public static final double PID_TOLERANCE = 1.0;
	
	//scaling values
	private static final double INCHES_PER_ROTATION = 3.53559055;
	private static final int TICKS_PER_ROTATION = 1024;
	private static final double TICKS_PER_INCH = TICKS_PER_ROTATION/INCHES_PER_ROTATION;

	public static final double P = 0.00003;
    public static final double I = 0.0;
    public static final double D = 0.0;
    
    public final int[] presets = {0, 12, 24, 36, 48};

	public Elevator() {
		super("Elevator", P, I, D);
		getPIDController().setContinuous(false);
		setAbsoluteTolerance(PID_TOLERANCE);
		setInputRange(MIN_HEIGHT, MAX_HEIGHT);
		setOutputRange(-0.5, 0.5);
		brakeSolenoid = new DoubleSolenoid(RobotMap.PCM, RobotMap.BRAKE_PORT_A, RobotMap.BRAKE_PORT_B);
        elevatorMotor1 = new CANTalon(RobotMap.ELEV_MOTOR_1);
        elevatorMotor2 = new CANTalon(RobotMap.ELEV_MOTOR_2);
        getPIDController().disable();
	}
	
	@Override
	protected void initDefaultCommand() {
		setDefaultCommand(new ElevatorControl());
	}
	
    public void initialize() {
    	elevatorMotor1.setPosition(MIN_HEIGHT);
    	elevatorMotor1.setForwardSoftLimit((int)(TICKS_PER_INCH * MIN_HEIGHT));
    	elevatorMotor1.setReverseSoftLimit((int)(TICKS_PER_INCH * MAX_HEIGHT));
    	elevatorMotor1.enableForwardSoftLimit(false);
    	elevatorMotor1.enableReverseSoftLimit(false);
    	elevatorMotor1.enableLimitSwitch(true, false);
    	elevatorMotor1.reverseSensor(true);
        elevatorMotor1.setFeedbackDevice(CANTalon.FeedbackDevice.AnalogEncoder);
        new ElevResetTrigger().whenActive(new ResetElevatorEncoder());
        
        elevatorMotor2.changeControlMode(CANTalon.ControlMode.Follower);
        elevatorMotor2.set(elevatorMotor1.getDeviceID());
        
    }
    
	@Override
	protected double returnPIDInput() {
		return getHeight();
	}

	@Override
	protected void usePIDOutput(double output) {
		elevatorMotor1.set(output);
	}
	
	public double getHeight() {
		return elevatorMotor1.getPosition() / TICKS_PER_INCH;
	}
	
    public void resetEncoder() {
    	System.out.println("reset");
    	elevatorMotor1.setPosition(MIN_HEIGHT);
    }
	
	public void brake() {
		brakeSolenoid.set(Value.kForward);	
	}
	
	public void release() {
		brakeSolenoid.set(Value.kReverse);
	}
	
	public void control(double y_axis) {
		if(Math.abs(y_axis) - 1 > 0) throw new IllegalArgumentException("Axis must be between -1 and 1");
		elevatorMotor1.set(y_axis * MAX_SPEED_FINE);
		SmartDashboard.putNumber("Position", getHeight());
		SmartDashboard.putBoolean("Limit", elevatorMotor1.isRevLimitSwitchClosed());
	}
	
	@Override
	public boolean onTarget(){
		if(past && super.onTarget())return true;
		past = super.onTarget();
		return false;
	}
	
	class ElevResetTrigger extends Trigger{

		@Override
		public boolean get() {
			return elevatorMotor1.isRevLimitSwitchClosed();
		}
		
	}
	
}
