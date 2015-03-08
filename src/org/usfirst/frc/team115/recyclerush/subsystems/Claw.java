package org.usfirst.frc.team115.recyclerush.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc.team115.recyclerush.RobotMap;

/**
 * A subsystem representing the robot's grabber, which opens/closes
 * to hold totes and bins
 * @author MVRT
 */
public class Claw extends Subsystem {

    DoubleSolenoid clawSolenoid;

    public Claw() {
        super();
        clawSolenoid = new DoubleSolenoid(RobotMap.PCM, RobotMap.CLAW_PORT_A, RobotMap.CLAW_PORT_B);
    }

    /**
     * Closes the grabber
     */
    public void close() {
        clawSolenoid.set(Value.kReverse);
        SmartDashboard.putString("Claw Open?", "NO");
    }

    /**
     * Opens the grabber
     */
    public void open() {
        clawSolenoid.set(Value.kForward);
        SmartDashboard.putString("Claw Open?", "NO");
    }

    /**
     * Sets the default command for this subsystem
     */
    @Override
    protected void initDefaultCommand() {}

    public boolean isOpen(){
    	return clawSolenoid.get() == Value.kForward;
    }
    
    public boolean isClosed(){
    	return clawSolenoid.get() == Value.kReverse;
    }

}
