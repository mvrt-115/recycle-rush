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
	
	private CANTalon elevatorMotor;
	private DoubleSolenoid brakeSolenoid;
	
	private DigitalInput resetLimitSwitch;
	
	private boolean past = false; 
	
	// the following measurements are in inches:
	public static final double MAX_HEIGHT = 52;
	public static final double MIN_HEIGHT = 0;
	public static final double MAX_SPEED_FINE = 0.8;
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
        elevatorMotor = new CANTalon(RobotMap.ELEVATOR);
        getPIDController().disable();
	}
	
	@Override
	protected void initDefaultCommand() {
		setDefaultCommand(new ElevatorControl());
	}
	
    public void initialize() {
    	elevatorMotor.setPosition(MIN_HEIGHT);
    	elevatorMotor.setReverseSoftLimit((int)(TICKS_PER_INCH * MIN_HEIGHT));
    	elevatorMotor.setForwardSoftLimit((int)(TICKS_PER_INCH * MAX_HEIGHT));
    	elevatorMotor.enableForwardSoftLimit(false);
    	elevatorMotor.enableReverseSoftLimit(false);
    	elevatorMotor.enableLimitSwitch(false, true);
    	elevatorMotor.reverseSensor(true);
        elevatorMotor.setFeedbackDevice(CANTalon.FeedbackDevice.AnalogEncoder);
        new ElevResetTrigger().whenActive(new ResetElevatorEncoder());
    }
    
	@Override
	protected double returnPIDInput() {
		return getHeight();
	}

	@Override
	protected void usePIDOutput(double output) {
		elevatorMotor.set(output * -1);
	}
	
	public double getHeight() {
		return elevatorMotor.getPosition() / TICKS_PER_INCH;
	}
	
    public void resetEncoder() {
    	System.out.println("reset");
    	elevatorMotor.setPosition(MIN_HEIGHT);
    }
	
	public void brake() {
		brakeSolenoid.set(Value.kForward);	
	}
	
	public void release() {
		brakeSolenoid.set(Value.kReverse);
	}
	
	public void control(double y_axis) {
		if(Math.abs(y_axis) - 1 > 0) throw new IllegalArgumentException("Axis must be between -1 and 1");
		elevatorMotor.set(y_axis * MAX_SPEED_FINE * -1);
		SmartDashboard.putNumber("Position", getHeight());
		SmartDashboard.putBoolean("Limit", elevatorMotor.isRevLimitSwitchClosed());
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
			return elevatorMotor.isRevLimitSwitchClosed();
		}
		
	}
	
}
