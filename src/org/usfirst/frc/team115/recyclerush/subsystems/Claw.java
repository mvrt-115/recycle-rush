package org.usfirst.frc.team115.recyclerush.subsystems;

import org.usfirst.frc.team115.recyclerush.RobotMap;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Claw extends Subsystem {

	public static final double TIME_TO_ACTUATE = 0.3;

	private DoubleSolenoid clawSolenoid;

	public Claw() {
		clawSolenoid = new DoubleSolenoid(RobotMap.PCM,
				RobotMap.CLAW_SOL_A, RobotMap.CLAW_SOL_B);
	}

	public void open() {
		clawSolenoid.set(DoubleSolenoid.Value.kForward);
		System.out.println("Claw: Closing");
	}

	public void close() {
		clawSolenoid.set(DoubleSolenoid.Value.kReverse);
		System.out.println("Claw: Closing");
	}

	public void log() {
		SmartDashboard.putBoolean("Is Claw Open?",
				clawSolenoid.get() == DoubleSolenoid.Value.kForward);
	}

	@Override
	protected void initDefaultCommand() {
	}

}
