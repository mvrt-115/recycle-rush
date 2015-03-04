package org.usfirst.frc.team115.recyclerush.subsystems;

import org.usfirst.frc.team115.recyclerush.RobotMap;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.Subsystem;

public class BinGrabber extends Subsystem{
	
	private DoubleSolenoid extendSolenoid;
	private DoubleSolenoid moveSolenoid;

	public BinGrabber() {
		extendSolenoid = new DoubleSolenoid(RobotMap.EXTEND_PORT_A, RobotMap.EXTEND_PORT_B);
		moveSolenoid = new DoubleSolenoid(RobotMap.MOVE_PORT_A, RobotMap.MOVE_PORT_B);
		
	}
	
	public void extend() {
		extendSolenoid.set(Value.kForward);
		moveSolenoid.set(Value.kReverse);
	}
	
	public void retract() {
		extendSolenoid.set(Value.kForward);
		moveSolenoid.set(Value.kReverse);
	}

	@Override
	protected void initDefaultCommand() {}

}
