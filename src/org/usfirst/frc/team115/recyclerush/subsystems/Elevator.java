package org.usfirst.frc.team115.recyclerush.subsystems;

import org.usfirst.frc.team115.recyclerush.Robot;
import org.usfirst.frc.team115.recyclerush.RobotMap;
import org.usfirst.frc.team115.recyclerush.commands.ElevatorControl;
import org.usfirst.frc.team115.recyclerush.commands.ElevatorStop;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.CANTalon.ControlMode;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Elevator extends Subsystem {
	private CANTalon elevatorMotor;
	private DoubleSolenoid elevatorSolenoid;
	public static final double MAX_HEIGHT = 10.0;
	public static final double MIN_HEIGHT = 0;
	public static final double MAX_SPEED_FINE = 0.2;
	public static boolean stateOfSolenoid = false; // false is brakes are off
	
	public Elevator(double p, double i, double d) {		
		elevatorMotor = new CANTalon(RobotMap.ELEVATOR);
		elevatorMotor.setFeedbackDevice(CANTalon.FeedbackDevice.AnalogEncoder);
		elevatorMotor.changeControlMode(ControlMode.Position);
		elevatorMotor.setPID(p, i, d);
		
		elevatorSolenoid = new DoubleSolenoid(RobotMap.BRAKE_SOLENOID_1, RobotMap.BRAKE_SOLENOID_2);
	}
	
	public double getHeight() {
		return elevatorMotor.getPosition();
	}
	
	public void goToHeight(double height) {
		elevatorMotor.changeControlMode(CANTalon.ControlMode.Position);
		elevatorMotor.enableControl();
		
		if(height<MIN_HEIGHT){
			height = MIN_HEIGHT;
		}else if(height<MAX_HEIGHT){
			height = MAX_HEIGHT;
		}
		elevatorMotor.set(height);
	}
	
	public void goIncremental(double increment) {
		elevatorMotor.changeControlMode(CANTalon.ControlMode.Position);
		elevatorMotor.enableControl();
		if (elevatorMotor.getPosition() + increment <= MAX_HEIGHT) {
			elevatorMotor.set(elevatorMotor.getPosition() + increment);
		}
	}
	
	public void stop() {
		elevatorMotor.disableControl();
		elevatorMotor.set(0);
	}
	
	public void brake() {
		elevatorSolenoid.set(Value.kForward);
		stateOfSolenoid = true;
	}
	
	public void release() {
		elevatorSolenoid.set(Value.kReverse);
		stateOfSolenoid = false;
	}
	
	public void control(double y_axis) {
		if(Math.abs(y_axis) - 1 > 0) throw new IllegalArgumentException("Axis must be between -1 and 1");
		elevatorMotor.changeControlMode(CANTalon.ControlMode.PercentVbus);
		elevatorMotor.set(y_axis * MAX_SPEED_FINE);
	}
	
	@Override
	protected void initDefaultCommand() {
		setDefaultCommand(new ElevatorControl(Robot.oi.getJoystick()));
	}

}
