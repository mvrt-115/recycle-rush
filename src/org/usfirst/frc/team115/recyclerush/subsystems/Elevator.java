package org.usfirst.frc.team115.recyclerush.subsystems;

import org.usfirst.frc.team115.recyclerush.RobotMap;
import org.usfirst.frc.team115.recyclerush.commands.ElevatorStop;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.CANTalon.ControlMode;
import edu.wpi.first.wpilibj.CounterBase.EncodingType;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.command.PIDSubsystem;

public class Elevator extends PIDSubsystem {
	private Encoder encoder;
	private CANTalon elevatorMotor;
	public static final double MAX_HEIGHT = 10.0;
	public static final double MIN_HEIGHT = 0;
	
	public Elevator(double p, double i, double d) {
		super(p, i, d);

		encoder = new Encoder(1,2,true,EncodingType.k4X);
		elevatorMotor = new CANTalon(RobotMap.ELEVATOR);
		elevatorMotor.changeControlMode(ControlMode.Position);
		encoder.reset();
	}

	@Override
	protected double returnPIDInput() {
		//return rotationToLinear(encoder.get());
		return encoder.getDistance();
	}
	
	public void goUp() {
		if (returnPIDInput() < MAX_HEIGHT){
			usePIDOutput(1);
		}
	}
		
	public void goDown() {
		if (returnPIDInput() > MIN_HEIGHT){
			usePIDOutput(-1);
		}	
	}
	
	public void stop() {
		usePIDOutput(0);
	}

	@Override
	protected void usePIDOutput(double output) {
		elevatorMotor.set(output);
	}
	
//	// filler code - to edit later. D:
//	public double rotationToLinear(int rotation){
//		return 0;
//	}
//	public double linearToRotation(int linear){
//		return 0;
//	}
//	
	@Override
	protected void initDefaultCommand() {
		setDefaultCommand(new ElevatorStop());

	}

}
