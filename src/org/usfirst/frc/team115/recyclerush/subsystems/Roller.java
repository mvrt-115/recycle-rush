package org.usfirst.frc.team115.recyclerush.subsystems;

import org.usfirst.frc.team115.recyclerush.RobotMap;
import org.usfirst.frc.team115.recyclerush.commands.RollerArcadeWithJoystick;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * Subsystem containing roller motors
 * @author Lee Mracek
 */
public class Roller extends Subsystem {
	private Joystick joystick;

	private CANTalon leftMotor, rightMotor;
	private RobotDrive twoMotorDrive;

	private int moveAxis, rotateAxis;

	public Roller(Joystick joystick, int moveAxis, int rotateAxis) {
		this.joystick = joystick;
		this.moveAxis = moveAxis;
		this.rotateAxis = rotateAxis;

		leftMotor = new CANTalon(RobotMap.ROLLER_MOTOR_LEFT);
		rightMotor = new CANTalon(RobotMap.ROLLER_MOTOR_RIGHT);

		twoMotorDrive = new RobotDrive(leftMotor, rightMotor);

		twoMotorDrive.setInvertedMotor(RobotDrive.MotorType.kFrontLeft, true);
		twoMotorDrive.setInvertedMotor(RobotDrive.MotorType.kFrontRight, true);

		leftMotor.enableLimitSwitch(false, false);
		rightMotor.enableLimitSwitch(false, false);
	}

	public void control(double move, double rotate) {
		twoMotorDrive.arcadeDrive(move, rotate);
	}

	public void control(Joystick joystick, int moveAxis, int rotateAxis) {
		twoMotorDrive.arcadeDrive(joystick, moveAxis, joystick, rotateAxis);
	}

	public void stop() {
		twoMotorDrive.arcadeDrive(0, 0);
	}

	public void log() {
		// TODO find something to log
	}

	@Override
	protected void initDefaultCommand() {
		setDefaultCommand(new RollerArcadeWithJoystick(joystick, moveAxis, rotateAxis));
	}
}
