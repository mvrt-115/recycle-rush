package org.usfirst.frc.team115.recyclerush.commands;

import org.usfirst.frc.team115.lib.SynchronousPID;
import org.usfirst.frc.team115.lib.trajectory.TrajectoryFollower;
import org.usfirst.frc.team115.lib.trajectory.TrajectoryFollower.TrajectorySetpoint;
import org.usfirst.frc.team115.lib.trajectory.TrajectoryFollowingPositionController;
import org.usfirst.frc.team115.recyclerush.Robot;
import org.usfirst.frc.team115.recyclerush.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

public class DriveStraightWithTrajectory extends Command {
	private final double goal;

	private TrajectoryFollowingPositionController distanceController;
	private SynchronousPID turnPid;

	private static int ENCODER_SCALE = 1444;
	private static double ERROR = 1;
	private final double SCALE = (1 / (Math.PI * 8)) * 2 * ENCODER_SCALE / ERROR;

	public DriveStraightWithTrajectory(double goal, double maxVelocity) {
		TrajectoryFollower.TrajectoryConfig config = new TrajectoryFollower.TrajectoryConfig();
		config.dt = 0.02;
		config.maxAcceleration = RobotMap.DRIVE_MAX_ACCEL;

		distanceController = new TrajectoryFollowingPositionController(
				RobotMap.DRIVE_POSITION_KP,
				RobotMap.DRIVE_POSITION_KI,
				RobotMap.DRIVE_POSITION_KD,
				RobotMap.DRIVE_POSITION_KV,
				RobotMap.DRIVE_POSITION_KA,
				RobotMap.DRIVE_ON_TARGET_ERROR,
				config);

		this.goal = goal;
		requires(Robot.drive);
	}

	@Override
	protected void initialize() {
		TrajectorySetpoint initialSetpoint = new TrajectorySetpoint();
		initialSetpoint.position = Robot.drive.getDistance();
		initialSetpoint.velocity = Robot.drive.getVelocity();
		distanceController.setGoal(initialSetpoint, goal * SCALE);

		turnPid = new SynchronousPID();
		turnPid.setPID(RobotMap.DRIVE_STRAIGHT_KP,
				RobotMap.DRIVE_STRAIGHT_KI,
				RobotMap.DRIVE_STRAIGHT_KD);
		turnPid.setSetpoint(Math.toRadians(Robot.navx.getYaw()));
	}

	@Override
	protected void execute() {
		distanceController.update(Robot.drive.getDistance(), Robot.drive.getVelocity());
		double throttle = distanceController.get();
		double turn = turnPid.calculate(Math.toRadians(Robot.navx.getYaw()));

		Robot.drive.control(throttle, turn);
	}

	@Override
	protected boolean isFinished() {
		return distanceController.isOnTarget();
	}

	@Override
	protected void end() {
		Robot.drive.stop();
	}

	@Override
	protected void interrupted() {
		Robot.drive.stop();
	}

}
