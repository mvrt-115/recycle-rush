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
	public static final double MAX_HEIGHT = 70.36;
	public static final double MIN_HEIGHT = 0;
	public static final double MAX_SPEED_FINE = 0.2;
	public static final double PID_TOLERANCE = 1.0;
	
	//scaling values
	private static final double INCHES_PER_ROTATION = 3.22;
	private static final int TICKS_PER_ROTATION = 1024;
	private static final double TICKS_PER_INCH = TICKS_PER_ROTATION/INCHES_PER_ROTATION;

	public static final double P = 0.0;
    public static final double I = 0.0;
    public static final double D = 0.0;
    
    public final int[] presets = {0, 12, 24, 36, 48};

	public Elevator() {
		super("Elevator", P, I, D);
		getPIDController().setContinuous(false);
		setAbsoluteTolerance(PID_TOLERANCE);
		setInputRange(MIN_HEIGHT, MAX_HEIGHT);
		brakeSolenoid = new DoubleSolenoid(RobotMap.BRAKE_SOLENOID_1, RobotMap.BRAKE_SOLENOID_2);
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
    	elevatorMotor.enableForwardSoftLimit(true);
    	elevatorMotor.enableReverseSoftLimit(true);
    	elevatorMotor.enableLimitSwitch(false, true);
        elevatorMotor.setFeedbackDevice(CANTalon.FeedbackDevice.AnalogEncoder);
        new ElevResetTrigger().whenActive(new ResetElevatorEncoder());
    }
    
	@Override
	protected double returnPIDInput() {
		return getHeight();
	}

	@Override
	protected void usePIDOutput(double output) {
		elevatorMotor.set(output);
	}
	
	public double getHeight() {
		return INCHES_PER_ROTATION * (elevatorMotor.getPosition() / 1023);
	}
	
    public void resetEncoder(){
    	elevatorMotor.setPosition(MIN_HEIGHT);
    }
	
	public void brake() {
		if(!brakeSolenoid.get().equals(Value.kForward)){
			brakeSolenoid.set(Value.kForward);
		}
	}
	
	public void release() {
		if(!brakeSolenoid.get().equals(Value.kReverse)){
			brakeSolenoid.set(Value.kReverse);
		}
	}
	
	public boolean isBraking(){
		return brakeSolenoid.get().equals(Value.kForward);
	}
	
	public void control(double y_axis) {
		if(Math.abs(y_axis) - 1 > 0) throw new IllegalArgumentException("Axis must be between -1 and 1");
		if((getHeight() >= MAX_HEIGHT && y_axis >= 0 ) || (getHeight() <= MIN_HEIGHT && y_axis <= 0)){
			elevatorMotor.set(0);
		}else{
			elevatorMotor.set(y_axis * MAX_SPEED_FINE);
		}
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
