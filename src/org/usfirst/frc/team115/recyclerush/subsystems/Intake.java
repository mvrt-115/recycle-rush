package org.usfirst.frc.team115.recyclerush.subsystems;

import org.usfirst.frc.team115.recyclerush.RobotMap;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Intake extends Subsystem {

	private DoubleSolenoid intakeSolenoid;

	public Intake() {
		intakeSolenoid = new DoubleSolenoid(RobotMap.PCM,
				RobotMap.INTAKE_SOL_A, RobotMap.INTAKE_SOL_B);
	}

	public void open() {
		intakeSolenoid.set(DoubleSolenoid.Value.kForward);
		System.out.println("Intake Opening");
	}

	public void close() {
		intakeSolenoid.set(DoubleSolenoid.Value.kReverse);
		System.out.println("Intake Closing");
	}

	public void setState(DoubleSolenoid.Value value) {
		intakeSolenoid.set(value);

		switch (value.value) {
		case DoubleSolenoid.Value.kForward_val:
			System.out.println("Intake set: Forward");
			break;
		case DoubleSolenoid.Value.kReverse_val:
			System.out.println("Intake set: Reverse");
			break;
		case DoubleSolenoid.Value.kOff_val:
			System.out.println("Intake set: Off");
			break;
		default:
			System.err.println("Intake set invalid!");
		}
	}

	public DoubleSolenoid.Value getState() {
		return intakeSolenoid.get();
	}

	@Override
	protected void initDefaultCommand() {
	}
}
