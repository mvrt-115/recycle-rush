package org.usfirst.frc.team115.recyclerush.subsystems;

import org.usfirst.frc.team115.recyclerush.Robot;
import org.usfirst.frc.team115.recyclerush.RobotMap;
import org.usfirst.frc.team115.recyclerush.commands.ElevatorDriveWithJoystick;
import org.usfirst.frc.team115.recyclerush.commands.ElevatorResetEncoder;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Trigger;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * A subsystem representing the elevator, which
 * lifts/lowers totes and bins
 *
 * @author Akhil Palla and Lee Mracek
 */
public class Elevator extends Subsystem {

	private CANTalon elevatorMotor1;
	private CANTalon elevatorMotor2;
	private DoubleSolenoid brakeSolenoid;

	// the following measurements are in inches:
	private static final double BOTTOM_HEIGHT = 56;
	private static final double TOP_HEIGHT = 0;
	private static final double MAX_SPEED_FINE = 1.0;

	//scaling values
	private static final int TICKS_PER_ROTATION = 1024;
	private static final double INCHES_PER_ROTATION = 3.53559055;
	private static final double TICKS_PER_INCH = TICKS_PER_ROTATION / INCHES_PER_ROTATION;

	public static final double PRESET_AUTOINTAKE_OPEN = 17;
	public static final double PRESET_STABILIZE_TOTES = 20;

	public static final double PRESET_BOTTOM = 0;
	public static final double PRESET_TOTE_INTAKETOTE = 14;

	public static final double PRESET_BIN_INTAKETOTE = 24;
	public static final double PRESET_TOTE_INTAKETHREETOTES = 41;
	public static final double PRESET_TOP = BOTTOM_HEIGHT;

	public static final double[] presets = {PRESET_BOTTOM, PRESET_TOTE_INTAKETOTE, PRESET_BIN_INTAKETOTE,
		PRESET_TOTE_INTAKETHREETOTES, PRESET_TOP};

	@Override
	protected void initDefaultCommand() {
		setDefaultCommand(new ElevatorDriveWithJoystick());
	}

	public Elevator(){
		brakeSolenoid = new DoubleSolenoid(RobotMap.PCM, RobotMap.ELEV_SOL_BRAKEA, RobotMap.ELEV_SOL_BREAKB);
		elevatorMotor1 = new CANTalon(RobotMap.ELEV_MOTOR_1);
		elevatorMotor2 = new CANTalon(RobotMap.ELEV_MOTOR_2);

		elevatorMotor1.setReverseSoftLimit((int) (0.25 * TICKS_PER_INCH)); // set limit to (1 inch from top)
		elevatorMotor1.enableForwardSoftLimit(false);
		elevatorMotor1.enableReverseSoftLimit(true);
		elevatorMotor1.enableLimitSwitch(true, false);
		elevatorMotor1.setFeedbackDevice(CANTalon.FeedbackDevice.AnalogEncoder);

		elevatorMotor2.changeControlMode(CANTalon.ControlMode.Follower);
		elevatorMotor2.set(elevatorMotor1.getDeviceID());
	}

	public void initResetTrigger(){
		new ElevResetTrigger().whenActive(new ElevatorResetEncoder());
	}

	public double getVelocity() {
		return (elevatorMotor1.getSpeed() / TICKS_PER_INCH) / 10;
	}

	public boolean isLimitPressed() {
		return elevatorMotor1.isFwdLimitSwitchClosed();
	}

	public double getHeight() {
		return BOTTOM_HEIGHT - elevatorMotor1.getPosition() / TICKS_PER_INCH;
	}

	public void resetEncoder() {
		elevatorMotor1.setPosition(BOTTOM_HEIGHT * TICKS_PER_INCH);
		Robot.oi.rumbleXbox(Joystick.RumbleType.kLeftRumble, .6, 300);
		Robot.oi.rumbleXbox(Joystick.RumbleType.kRightRumble, .6, 300);
	}

	public void setBrake(boolean brake) {
		if(brake){
			brakeSolenoid.set(Value.kForward);
		}else{
			brakeSolenoid.set(Value.kReverse);
		}
	}

	public void stop() {
		setSpeed(0);
		setBrake(true);
	}

	/**
	 * Controls the elevator
	 * @param speed: Positive = up, negative = down
	 */
	public void setSpeed(double speed) {
		//we need to invert the motor, because otherwise
		//a positive speed makes the elevator go down
		if(speed < 0 && getHeight() < 5) {
			speed = speed * 0.4;
		} else if(speed < 0) {
			speed = speed * 0.8;
		}
		elevatorMotor1.set(speed * -1);
	}

	public void controlJoystick(int axis) {
		setSpeed(Robot.oi.getXboxAxis(axis, true) * MAX_SPEED_FINE);
	}

	public void log() {
		SmartDashboard.putBoolean("Elevator Limit", isLimitPressed());
		SmartDashboard.putNumber("Elevator Height", getHeight());
		SmartDashboard.putNumber("Elevator Height-ticks", elevatorMotor1.getPosition());
		SmartDashboard.putNumber("Elevator Speed", getVelocity());
		SmartDashboard.putBoolean("Elevator Straining", elevatorMotor1.getOutputCurrent() + elevatorMotor2.getOutputCurrent() >= 1);
	}

	class ElevResetTrigger extends Trigger {

		@Override
		public boolean get() {
			return elevatorMotor1.isFwdLimitSwitchClosed();
		}

	}

}