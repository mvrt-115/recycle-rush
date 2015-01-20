package org.usfirst.frc.team115.recyclerush.subsystems;

import java.util.ArrayList;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.command.Subsystem;


public class VBusMotorSystem extends Subsystem {
	protected ArrayList<CANTalon> motors = new ArrayList<CANTalon>();
	protected boolean invert;

	public void stop() {
		for (CANTalon motor : motors) {
			motor.set(0);
			}
		}
		
	@Override
	protected void initDefaultCommand() {}
	
	public void setInvert(boolean invert){
		this.invert = invert;
	}
	
	public boolean isInverted(){
		return invert;
	}
}
