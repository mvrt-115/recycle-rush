package org.usfirst.frc.team115.recyclerush.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.Subsystem;
import org.usfirst.frc.team115.recyclerush.RobotMap;

/**
 * A subsystem representing the robot's claw, which stabilizes the bins
 * @author MVRT
 */
public class Claw extends Subsystem {

    DoubleSolenoid clawSolenoid;

    public Claw() {
        super();
        clawSolenoid = new DoubleSolenoid(RobotMap.CLAW_SOLENOID_1, RobotMap.CLAW_SOLENOID_2);
    }

    /**
     * Closes the claw
     */
    public void close() {
        clawSolenoid.set(Value.kReverse);
    }

    /**
     * Opens the claw
     */
    public void open() {
        clawSolenoid.set(Value.kForward);
    }

    /**
     * Runs the default command for this subsystem
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
