package org.usfirst.frc.team115.recyclerush.subsystems;

import org.usfirst.frc.team115.recyclerush.RobotMap;
import org.usfirst.frc.team115.recyclerush.commands.DriveArcadeWithJoystick;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.command.Subsystem;

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
	}

	public void initialize() {
		motors[BACK_LEFT] = new CANTalon(RobotMap.DRIVE_MOTOR_BACKLEFT);
		motors[BACK_RIGHT] = new CANTalon(RobotMap.DRIVE_MOTOR_BACKRIGHT);
		motors[FRONT_LEFT] = new CANTalon(RobotMap.DRIVE_MOTOR_FRONTLEFT);
		motors[FRONT_RIGHT] = new CANTalon(RobotMap.DRIVE_MOTOR_FRONTRIGHT);

		drive = new RobotDrive(motors[FRONT_LEFT], motors[BACK_LEFT],
				motors[FRONT_RIGHT], motors[BACK_RIGHT]);

		for(CANTalon motor : motors) {
			motor.enableLimitSwitch(false, false);
		}
	}

	public void control(Joystick joystick) {
		drive.arcadeDrive(joystick);
	}

	public void control(double move, double rotate) {
		drive.arcadeDrive(move, rotate);
	}

	public void stop() {
		this.control(0, 0);
	}

	@Override
	protected void initDefaultCommand() {
		this.setDefaultCommand(new DriveArcadeWithJoystick(joystick));
	}
}
