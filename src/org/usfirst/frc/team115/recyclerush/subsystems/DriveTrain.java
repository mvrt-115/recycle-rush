
/**
 * This is the drive train for the robot for the competition.
 * 
 * @author Heather Baker
 */

package org.usfirst.frc.team115.recyclerush.subsystems;

import edu.wpi.first.wpilibj.*;
import org.usfirst.frc.team115.recyclerush.Robot;
import org.usfirst.frc.team115.recyclerush.RobotMap;
import org.usfirst.frc.team115.recyclerush.commands.ArcadeDriveWithJoystick;

import edu.wpi.first.wpilibj.command.Subsystem;

public class DriveTrain extends Subsystem {

	private RobotDrive drive;
	private final int BACK_LEFT 	= 0;
	private final int BACK_RIGHT 	= 1;
	private final int FRONT_LEFT 	= 2;
	private final int FRONT_RIGHT 	= 3;
	private final double MAX_SPEED 	= 1.0;
	private final double MIN_SPEED  = -1.0;

	private CANTalon motors[];

	private PIDController outputSpeed;
	private PIDSource joystick;
	private PIDOutput pidSpeed;

	private double kP = 0.0;
	private double kI = 0.0;
	private double kD = 0.0;

	/**
	 * Initializes each other motors based on ports set in RobotMap
	 */
	public DriveTrain() {
		motors = new CANTalon[4];
		motors[BACK_LEFT] 	= new CANTalon(RobotMap.BACK_LEFT_DRIVE);
		motors[BACK_RIGHT]	 = new CANTalon(RobotMap.BACK_RIGHT_DRIVE);
		motors[FRONT_LEFT] 	= new CANTalon(RobotMap.FRONT_LEFT_DRIVE);
		motors[FRONT_RIGHT] = new CANTalon(RobotMap.FRONT_RIGHT_DRIVE);
		drive = new RobotDrive(motors[FRONT_LEFT], motors[BACK_LEFT],
				motors[FRONT_RIGHT], motors[BACK_RIGHT]);

		joystick = new PIDSource() {
			@Override
			public double pidGet() {
				return Robot.oi.getJoystick().getY();
			}
		};

		pidSpeed = new PIDOutput() {
			@Override
			public void pidWrite(double output) {
				drive(output, Robot.oi.getJoystick().getX());
			}
		};

		outputSpeed = new PIDController(kP, kI, kD, joystick, pidSpeed);
	}

	/**
	 * This thing drives the robot!
	 * @param move   	The move speed
	 * @param rotate	The rotate speed
	 */
	public void drive(double move, double rotate) {
		move = Math.max(MIN_SPEED, Math.min(MAX_SPEED, move));
		rotate = Math.max(MIN_SPEED, Math.min(MAX_SPEED, rotate));
		drive.arcadeDrive(move, rotate);
	}

	/**
	 * Drives the robot
	 * @param joystick The joystick to drive based on
	 */
	public void drive(Joystick joystick) {
		drive.arcadeDrive(joystick);
	}
	
	/**
	 * Initializes the default command of the subsystem.
	 */
	@Override
	protected void initDefaultCommand() {
		setDefaultCommand(new ArcadeDriveWithJoystick());
	}
	
	/**
	 * This returns the current.
	 * @return 		the current
	 */
	public double getCurrent() {
		double current = 0;
		for (CANTalon motor : motors)
			current += motor.getOutputCurrent();
		return current;
	}

}
