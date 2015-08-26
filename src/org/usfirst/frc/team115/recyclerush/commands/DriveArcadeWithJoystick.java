package org.usfirst.frc.team115.recyclerush.commands;

import org.usfirst.frc.team115.recyclerush.OI;
import org.usfirst.frc.team115.recyclerush.Robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.Command;

/**
 * Drives the robot using a joystick and specified axis
 *
 * @author Lee Mracek
 */
public class DriveArcadeWithJoystick extends Command {

    private double oldWheel, quickStopAccumulator, negInertiaAccumulator = 0;
	private double throttleDeadband = 0.02;
	private double wheelDeadband = 0.02;


    private Joystick joystick;

    private boolean precision;

    public DriveArcadeWithJoystick(Joystick joystick) {
        this(joystick, false);
    }

    public DriveArcadeWithJoystick(Joystick joystick, boolean precision){
        requires(Robot.drive);
        this.joystick = joystick;
        this.precision = precision;
    }

    @Override
    protected void initialize() {
        Robot.drive.stop();
    }

    @Override
    protected void execute() {
        boolean quickTurn = joystick.getTrigger();
        double turn = joystick.getX();

        if (quickTurn) {
			double sign = Math.signum(turn);
			turn = turn * turn * sign;
		}

        drive(joystick.getY(), turn, quickTurn);
    }

    @Override
    protected boolean isFinished() {
        return false;
    }

    @Override
    protected void end() {
        Robot.drive.stop();
    }

    @Override
    protected void interrupted() {
        end();
    }

	public void drive(double throttle, double wheel, boolean quickTurn) {
		if (DriverStation.getInstance().isAutonomous())
			return;

		double logScale = 0.6;

		wheel = handleDeadband(wheel, wheelDeadband);
		throttle = handleDeadband(throttle, throttleDeadband);

		double negativeInertia = wheel - oldWheel;

		oldWheel = wheel;

		wheel = Math.sin(Math.PI / 2.0 * logScale * wheel) / Math.sin(Math.PI / 2.0 * logScale);
		wheel = Math.sin(Math.PI / 2.0 * logScale * wheel) / Math.sin(Math.PI / 2.0 * logScale);

		double leftPwm, rightPwm, overPower;
		double sensitivity;

		double angularPower;
		double linearPower;

		// Negative inertia!
		double negInertiaScalar;
		if (wheel * negativeInertia > 0) {
			negInertiaScalar = 2.5;
		} else {
			if (Math.abs(wheel) > 0.65) {
				negInertiaScalar = 5.0;
			} else {
				negInertiaScalar = 3.0;
			}
		}
		sensitivity = Constants.sensitivity.getDouble();

		double negInertiaPower = negativeInertia * negInertiaScalar;
		negInertiaAccumulator += negInertiaPower;

		wheel = wheel + negInertiaAccumulator;
		if (negInertiaAccumulator > 1) {
			negInertiaAccumulator -= 1;
		} else if (negInertiaAccumulator < -1) {
			negInertiaAccumulator += 1;
		} else {
			negInertiaAccumulator = 0;
		}

		linearPower = throttle;

		if (quickTurn) {
			if (Math.abs(linearPower) < 0.2) {
				double alpha = 0.1;
				quickStopAccumulator = (1 - alpha) * quickStopAccumulator + alpha * limit(wheel, 1.0) * 5;
			}
			overPower = 1.0;
			sensitivity = 1.0;
			angularPower = wheel;
		} else {
			overPower = 0.0;
			angularPower = Math.abs(throttle) * wheel * sensitivity - quickStopAccumulator;
			if (quickStopAccumulator > 1) {
				quickStopAccumulator -= 1;
			} else if (quickStopAccumulator < -1) {
				quickStopAccumulator += 1;
			} else {
				quickStopAccumulator = 0.0;
			}
		}

		rightPwm = leftPwm = linearPower;
		leftPwm += angularPower;
		rightPwm -= angularPower;

		if (leftPwm > 1.0) {
			rightPwm -= overPower * (leftPwm - 1.0);
			leftPwm = 1.0;
		} else if (rightPwm > 1.0) {
			leftPwm -= overPower * (rightPwm - 1.0);
			rightPwm = 1.0;
		} else if (leftPwm < -1.0) {
			rightPwm += overPower * (-1.0 - leftPwm);
			leftPwm = -1.0;
		} else if (rightPwm < -1.0) {
			leftPwm += overPower * (-1.0 - rightPwm);
			rightPwm = -1.0;
		}
		drive.setLeftRightPower(leftPwm, rightPwm);

        Robot.drive.control(leftPwm, rightPwm);

	}

	public double handleDeadband(double val, double deadband) {
		return (Math.abs(val) > Math.abs(deadband)) ? val : 0.0;
	}

	public static double limit(double v, double limit) {
		return (Math.abs(v) < limit) ? v : limit * (v < 0 ? -1 : 1);
	}
}
