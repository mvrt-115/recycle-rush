package org.usfirst.frc.team115.recyclerush.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc.team115.recyclerush.RobotMap;

public class Grabber extends Subsystem {

    DoubleSolenoid grabberSolenoid;

    public Grabber() {
        super();
        grabberSolenoid = new DoubleSolenoid(RobotMap.GRABBER_SOLENOID_1, RobotMap.GRABBER_SOLENOID_2);
    }

    public void close() {
        grabberSolenoid.set(Value.kReverse);
    }

    public void open() {
        grabberSolenoid.set(Value.kForward);
    }

    @Override
    protected void initDefaultCommand() {
    }
    
    public void logStatus(){
    	SmartDashboard.putNumber("grabber_solenoid_state", grabberSolenoid.get().value);
    }

}
