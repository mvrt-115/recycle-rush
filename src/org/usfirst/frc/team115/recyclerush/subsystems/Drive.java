package org.usfirst.frc.team115.recyclerush.subsystems;

import org.usfirst.frc.team115.recyclerush.RobotMap;
import org.usfirst.frc.team115.recyclerush.commands.DriveArcadeWithJoystick;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.CANTalon.FeedbackDevice;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * Class defining the subsystem which encapsulates our DriveTrain
 *
 * @author Lee Mracek
 */
public class Drive extends Subsystem {

	private final int BACK_LEFT = 0;
	private final int BACK_RIGHT = 1;
	private final int FRONT_LEFT = 2;
	private final int FRONT_RIGHT = 3;

	private CANTalon[] motors = new CANTalon[4];

	private RobotDrive drive;
	private Joystick joystick;

	public Drive(Joystick joystick) {
		this.joystick = joystick;

		motors[BACK_LEFT] = new CANTalon(RobotMap.DRIVE_MOTOR_BACKLEFT);
		motors[BACK_RIGHT] = new CANTalon(RobotMap.DRIVE_MOTOR_BACKRIGHT);
		motors[FRONT_LEFT] = new CANTalon(RobotMap.DRIVE_MOTOR_FRONTLEFT);
		motors[FRONT_RIGHT] = new CANTalon(RobotMap.DRIVE_MOTOR_FRONTRIGHT);

		motors[FRONT_RIGHT].reverseSensor(true);
		motors[BACK_RIGHT].reverseSensor(true);

		drive = new RobotDrive(motors[FRONT_LEFT], motors[BACK_LEFT],
				motors[FRONT_RIGHT], motors[BACK_RIGHT]);

		for(CANTalon motor : motors) {
			motor.enableLimitSwitch(false, false);
			motor.setFeedbackDevice(FeedbackDevice.QuadEncoder);
		}
		resetEncoders();
	}

	/**
	 * Drives the robot with a joystick
	 * @param joystick: The joystick to use
	 */
	public void control(Joystick joystick) {
		drive.arcadeDrive(joystick);
	}

	/**
	 * Controls the robot drive with speed
	 * @param move: The forward speed
	 * @param rotate: The turn speed
	 */
	public void control(double move, double rotate){
		drive.arcadeDrive(move * -1, rotate);
	}

	/**
	 * Controls the robot drive, using joystick values
	 * for move and a speed value for rotate
	 * @param joystick: The joystick to use for fwd/back speed
	 * @param rotate: The double to use for rotate speed
	 */
	public void controlJoystickMove(Joystick joystick, double rotate) {
		drive.arcadeDrive(joystick.getY(), rotate);
	}

	/**
	 * Controls the drivetrain with a joystick, with scaled values
	 * @param joystick: The joystick to use
	 * @param speed: The scaler to multiply joystick values by
	 */
	public void control(Joystick joystick, double speed) {
		drive.arcadeDrive(joystick.getY() * speed, joystick.getX() * speed);
	}

	/**
	 * Controls the drivetrain, with joystick and scalers
	 * @param joystick: The joystick to use
	 * @param moveSpeed: The amount to scale the move value
	 * @param rotateSpeed: The amount to scale the rotate value
	 */
	public void control(Joystick joystick, double moveSpeed, double rotateSpeed) {
		drive.arcadeDrive(joystick.getY() * moveSpeed, joystick.getX() * rotateSpeed);
	}

	public void stop() {
		this.control(0, 0);
	}

	public double getDistance(){
		return (motors[FRONT_LEFT].getPosition() + motors[FRONT_RIGHT].getPosition())/2;
	}

	public void resetEncoders(){
		for(CANTalon t:motors){
			t.setPosition(0);
		}
	}

	public void log() {
		for (int it = 0; it < motors.length; it++) {
			CANTalon motor = motors[it];
			SmartDashboard.putNumber("Drivetrain Motor " + it + " Current", motor.getOutputCurrent());
			SmartDashboard.putNumber("Drivetrain Motor " + it + " Distance", motor.getEncPosition());
		}

		SmartDashboard.putNumber("Drive Distance", getDistance());
	}

	@Override
	protected void initDefaultCommand() {
		this.setDefaultCommand(new DriveArcadeWithJoystick(joystick, true));
	}
}
