package org.usfirst.frc.team115.recyclerush.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc.team115.recyclerush.RobotMap;

public class Claw extends Subsystem {

    DoubleSolenoid clawSolenoid;

    public Claw() {
        super();
        clawSolenoid = new DoubleSolenoid(RobotMap.CLAW_SOLENOID_1, RobotMap.CLAW_SOLENOID_2);
    }

    public void close() {
        clawSolenoid.set(Value.kReverse);
    }

    public void open() {
        clawSolenoid.set(Value.kForward);
    }

    @Override
    protected void initDefaultCommand() {
    }
    
    public void logStatus(){
    	SmartDashboard.putNumber("claw_solenoid_state", clawSolenoid.get().value);
    }

}
