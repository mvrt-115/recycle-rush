package org.usfirst.frc.team115.recyclerush.subsystems;

import org.usfirst.frc.team115.recyclerush.RobotMap;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Stabilizer extends Subsystem {

	public static final double TIME_TO_ACTUATE = 0.8;

	private DoubleSolenoid stabSolenoid;

	public Stabilizer() {
		stabSolenoid = new DoubleSolenoid(RobotMap.PCM,
				RobotMap.STABILIZER_SOL_A, RobotMap.STABILIZER_SOL_B);
	}

	public void open() {
		stabSolenoid.set(DoubleSolenoid.Value.kForward);
		System.out.println("Stabilizer: Closing");
	}

	public void close() {
		stabSolenoid.set(DoubleSolenoid.Value.kReverse);
		System.out.println("Stabilizer: Closing");
	}

	public boolean isOpen(){
		return stabSolenoid.get().equals(Value.kForward);
	}

	public void log() {
		SmartDashboard.putBoolean("Is Stabilizer Open?",
				stabSolenoid.get().equals(DoubleSolenoid.Value.kForward));
	}

	@Override
	protected void initDefaultCommand() {
	}

}
