package org.usfirst.frc.team115.recyclerush.subsystems;

import org.usfirst.frc.team115.recyclerush.RobotMap;
import org.usfirst.frc.team115.recyclerush.commands.ElevatorDriveWithJoystick;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Elevator extends Subsystem {

	public static final double MAX_SPEED_FINE = 1.0;

	//scaling values
	private static final int TICKS_PER_ROTATION = 1024;
	private static final double INCHES_PER_ROTATION = 3.53559055;
	private static final double TICKS_PER_INCH = TICKS_PER_ROTATION / INCHES_PER_ROTATION;

	private static final double BOTTOM_HEIGHT = 54;
	private static final double TOP_HEIGHT = 0;

	private static final int TOP_LIMIT = (int) (0.25 * TICKS_PER_INCH);
	private static final double PRESET_TOP = 52;
	private static final double PRESET_BOTTOM = 0;

	private CANTalon elevatorMotor1, elevatorMotor2;

	private DoubleSolenoid brakeSolenoid;

	public Elevator() {

		brakeSolenoid = new DoubleSolenoid(RobotMap.PCM,
				RobotMap.ELEV_SOL_BRAKEA, RobotMap.ELEV_SOL_BREAKB);

		elevatorMotor1 = new CANTalon(RobotMap.ELEV_MOTOR_1);
		elevatorMotor2 = new CANTalon(RobotMap.ELEV_MOTOR_2);

		elevatorMotor1.setReverseSoftLimit(TOP_LIMIT);
		elevatorMotor1.enableForwardSoftLimit(false);
		elevatorMotor1.enableReverseSoftLimit(true);
		elevatorMotor1.enableLimitSwitch(true, false);
		elevatorMotor1.setFeedbackDevice(CANTalon.FeedbackDevice.AnalogEncoder);

		elevatorMotor2.changeControlMode(CANTalon.ControlMode.Follower);
		elevatorMotor2.set(elevatorMotor1.getDeviceID());
	}

	public void control(double axis) {
		elevatorMotor1.set(axis);
	}

	public void setBrake(boolean brake) {
		if(brake) {
			brakeSolenoid.set(DoubleSolenoid.Value.kForward);
		} else {
			brakeSolenoid.set(DoubleSolenoid.Value.kReverse);
		}
	}

	public void log() {
		SmartDashboard.putBoolean("Elevator Limit", elevatorMotor1.isFwdLimitSwitchClosed());
		SmartDashboard.putNumber("Elevator Height", getHeight());
		SmartDashboard.putNumber("Elevator Height-ticks", elevatorMotor1.getPosition());
	}

	public double getHeight() {
		return BOTTOM_HEIGHT - elevatorMotor1.getPosition() / TICKS_PER_INCH;
	}

	@Override
	protected void initDefaultCommand() {
		setDefaultCommand(new ElevatorDriveWithJoystick());
	}

}
