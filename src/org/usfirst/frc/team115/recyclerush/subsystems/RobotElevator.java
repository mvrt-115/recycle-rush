package org.usfirst.frc.team115.recyclerush.subsystems;

import org.usfirst.frc.team115.recyclerush.RobotMap;
import org.usfirst.frc.team115.recyclerush.commands.ElevatorStop;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.Counter;
import edu.wpi.first.wpilibj.DigitalInput;

public class RobotElevator extends VBusMotorSystem {
	private CANTalon elevatorMotor;
	public final double ELEVATOR_SPEED_UP = 1;
	public final double ELEVATOR_SPEED_DOWN = -1;
	public double elevateHeight = 0.0;
	
	private DigitalInput limitSwitchUp;
	private Counter counterUp;
	
	private DigitalInput limitSwitchDown;
	private Counter counterDown;
	
	public RobotElevator() {
		super();
		elevatorMotor = new CANTalon(RobotMap.ELEVATOR);
		motors.add(elevatorMotor);
		
		limitSwitchUp = new DigitalInput(RobotMap.ELEVATOR_LIMIT_UP);
		counterUp = new Counter(limitSwitchUp);
		
		limitSwitchDown = new DigitalInput(RobotMap.ELEVATOR_LIMIT_DOWN);
		counterDown = new Counter(limitSwitchDown);
	}
	
	public void GoUp() {
		elevatorMotor.set(ELEVATOR_SPEED_UP);
	}
	
	public void GoDown() {
		elevatorMotor.set(ELEVATOR_SPEED_DOWN);
	}

	public boolean isUpSwitchSet(){
		return counterUp.get() > 0;
	}

	public boolean isDownSwitchSet(){
		return counterDown.get() > 0;
	}
	
	@Override
	protected void initDefaultCommand() {
		setDefaultCommand(new ElevatorStop());
		counterUp.reset();
		counterDown.reset();
	}
}
