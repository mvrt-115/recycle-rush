package org.usfirst.frc.team115.recyclerush.subsystems;

import org.usfirst.frc.team115.recyclerush.RobotMap;
import org.usfirst.frc.team115.recyclerush.commands.ElevatorStop;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.CANTalon.ControlMode;
import edu.wpi.first.wpilibj.CounterBase.EncodingType;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.command.PIDSubsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Elevator extends PIDSubsystem {
	private Encoder encoder;
	private CANTalon elevatorMotor;
	private DoubleSolenoid elevatorSolenoid;
	public static final double MAX_HEIGHT = 10.0;
	public static final double MIN_HEIGHT = 0;
	public static final double MAX_SPEED_FINE = 0.4;
	public static boolean stateOfSolenoid = false; //false is brakes are off
	
	public Elevator(double p, double i, double d) {
		super(p, i, d);

		encoder = new Encoder(1,2,true,EncodingType.k4X);
		
		elevatorMotor = new CANTalon(RobotMap.ELEVATOR);
		elevatorMotor.changeControlMode(ControlMode.Position);
		
		elevatorSolenoid = new DoubleSolenoid(RobotMap.BREAK_SOLENOID_1, RobotMap.BREAK_SOLENOID_2);
		
		SmartDashboard.putNumber("Elevator Height", returnPIDInput());
	}

	@Override
	protected double returnPIDInput() {
		return encoder.getDistance();
	}
	
	@Override
	protected void usePIDOutput(double output) {
		elevatorMotor.set(output);
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
	
	public void brake() {
		elevatorSolenoid.set(Value.kForward);
		stateOfSolenoid = true;
	}
	
	public void release() {
		elevatorSolenoid.set(Value.kReverse);
		stateOfSolenoid = false;
	}
	
	public void control(double y_axis) {	
		if (returnPIDInput()< MAX_HEIGHT && returnPIDInput()> MIN_HEIGHT) {
			if (stateOfSolenoid == true) {
				release();
			}
			elevatorMotor.set(y_axis * MAX_SPEED_FINE);
		}
		else if (returnPIDInput()== MAX_HEIGHT || returnPIDInput()==MIN_HEIGHT) {
			elevatorMotor.set(0.0);
			brake();
		}
	}
	

	
	@Override
	protected void initDefaultCommand() {
		setDefaultCommand(new ElevatorStop());

	}

}