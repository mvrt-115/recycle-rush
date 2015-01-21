package org.usfirst.frc.team115.recyclerush.subsystems;

import org.usfirst.frc.team115.recyclerush.commands.ElevatorStop;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.CANTalon.ControlMode;
import edu.wpi.first.wpilibj.CounterBase.EncodingType;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.command.PIDSubsystem;

public class Elevator extends PIDSubsystem {
	private Encoder encoder;
	private CANTalon elevatorMotor;
	
	public Elevator(double p, double i, double d) {
		super(p, i, d);

		encoder = new Encoder(1,2,true,EncodingType.k4X);
		elevatorMotor = new CANTalon(0);
		elevatorMotor.changeControlMode(ControlMode.Position);
	}

	@Override
	protected double returnPIDInput() {
		// TODO Auto-generated method stub
		return rotationToLinear(encoder.get());
	}
	public void goUp(){
		usePIDOutput(1);
	}
	public void goDown(){
		usePIDOutput(-1);
	}

	@Override
	protected void usePIDOutput(double output) {
		// TODO Auto-generated method stub
		elevatorMotor.set(output);
	}
	
	// filler code - to edit later. D:
	public double rotationToLinear(int rotation){
		return 0;
	}
	public double linearToRotation(int linear){
		return 0;
	}
	
	@Override
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub
		setDefaultCommand(new ElevatorStop());

	}

}
