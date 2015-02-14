package org.usfirst.frc.team115.recyclerush.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.Subsystem;
import org.usfirst.frc.team115.recyclerush.RobotMap;

/**
 * A subsystem representing the robot's claw, which stabilizes the bins
 * @author MVRT
 */
public class Stabilizer extends Subsystem {

    DoubleSolenoid stabSolenoid;

    public Stabilizer() {
        super();
        stabSolenoid = new DoubleSolenoid(RobotMap.STABILIZER_SOLENOID_1, RobotMap.STABILIZER_SOLENOID_2);
    }

    /**
     * Closes the claw
     */
    public void close() {
        stabSolenoid.set(Value.kReverse);
    }

    /**
     * Opens the claw
     */
    public void open() {
        stabSolenoid.set(Value.kForward);
    }

    public boolean isOpen(){
    	return stabSolenoid.get() == Value.kForward;
    }
    
    public boolean isClosed(){
    	return stabSolenoid.get() == Value.kReverse;
    }
    
    @Override
    protected void initDefaultCommand() {}

}
