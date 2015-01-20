package org.usfirst.frc.team115.recyclerush.commands;

import org.usfirst.frc.team115.recyclerush.Robot;

import edu.wpi.first.wpilibj.Gyro;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

public class DriveStraight extends Command{
	//idk how to control this.
	Joystick joystick;	
	Gyro gyro;
	PIDController control = new PIDController( 0.1, 0.001, 0.0, gyro, (PIDOutput)Robot.drive);
	
	double Kp = 0.03;
	
	public DriveStraight(Joystick j){
		requires(Robot.drive);
		joystick = j;
	}

	// reset gyro, and start using PID
	@Override
	protected void initialize() {
		gyro.reset();
		control.enable();
		control.get();
		
	}
	
	// return angle output from gyro
	public double getAngle() {
		return gyro.getAngle();
	}
	
	// turn-drive based on angle. If angle is 0 +- 1 degrees, just don't turn; just drive
	public void driveWithAngle(double angle) {
		// Use output to drive your system, like a motor
		// e.g. yourMotor.set(output);
		if (angle > 1) {
			Robot.drive.drive(joystick.getY()-(angle*Kp/2), joystick.getY()+angle*Kp);
		}else if (angle < -1){
			Robot.drive.drive(joystick.getY()+angle*Kp, joystick.getY()-(angle*Kp/2));
		}else Robot.drive.drive(joystick.getY(),joystick.getY());
	}

	@Override	// please add this command to fourmotordrive's drive(joystick).

	// arcadedrive -> ?
	// when execute(), don't call drive(); in drive(), please call new DriveStraight() or similar.
	// do the current drive()'s methods in driveStraight() 
	public void execute() {
		driveWithAngle(getAngle());
        Timer.delay(0.004);
	}

	@Override
	// keep on driving - don't stop driving straight.
	protected boolean isFinished() {
		return false;
	}

	// end all running programs in relation to driveStraight.
	@Override
	protected void end() {
		control.reset();	//resets the controller and disables it.
		Robot.drive.stop();
	}

	@Override
	protected void interrupted() {
		end();
	}
}
