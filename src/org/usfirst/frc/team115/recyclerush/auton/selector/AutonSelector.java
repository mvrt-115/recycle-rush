package org.usfirst.frc.team115.recyclerush.auton.selector;

import java.util.Arrays;

import org.usfirst.frc.team115.recyclerush.auton.Turn;
import org.usfirst.frc.team115.recyclerush.commands.ClawClose;
import org.usfirst.frc.team115.recyclerush.commands.ClawOpen;
import org.usfirst.frc.team115.recyclerush.commands.DriveStraightForDistance;
import org.usfirst.frc.team115.recyclerush.commands.ElevatorHardReset;
import org.usfirst.frc.team115.recyclerush.commands.ElevatorToHeight;
import org.usfirst.frc.team115.recyclerush.commands.IntakeClose;
import org.usfirst.frc.team115.recyclerush.commands.IntakeOpen;
import org.usfirst.frc.team115.recyclerush.commands.StabilizerClose;
import org.usfirst.frc.team115.recyclerush.commands.StabilizerOpen;
import org.usfirst.frc.team115.recyclerush.commands.indep.RollerRotate;
import org.usfirst.frc.team115.recyclerush.subsystems.Elevator;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.tables.ITable;
import edu.wpi.first.wpilibj.tables.TableKeyNotDefinedException;

public class AutonSelector {
	ITable container;
	public AutonSelector(ITable table) {
		this.container = table;
	}

	public CommandGroup getAuton() throws TableKeyNotDefinedException {
		String[] commands = getStringArray();
		return new CustomCommandGroup(commands);
	}

	protected Command parseString(String command) {
		String[] split = command.split(" ");
		if(split[1].equalsIgnoreCase("claw")) {
			if(split[0].equalsIgnoreCase("close")) {
				return new ClawClose();
			} else if(split[0].equalsIgnoreCase("open")) {
				return new ClawOpen();
			}
		} else if(split[1].equalsIgnoreCase("intake")) {
			if(split[0].equalsIgnoreCase("close")) {
				return new IntakeClose();
			} else if(split[0].equalsIgnoreCase("open")) {
				return new IntakeOpen();
			}
		} else if(split[1].equalsIgnoreCase("stabilizer")) {
			if(split[0].equalsIgnoreCase("close")) {
				return new StabilizerClose();
			} else if(split[0].equalsIgnoreCase("open")) {
				return new StabilizerOpen();
			}
		} else if(split[0].equalsIgnoreCase("drive")) {
			return new DriveStraightForDistance(
					split[1].equalsIgnoreCase("for") ? Double.parseDouble(split[2])
							: Double.parseDouble(split[1]));
		} else if(split[0].equalsIgnoreCase("elevator")) {
			String height = split[1].equalsIgnoreCase("to") ? arrayToString(Arrays.copyOfRange(split, 2, split.length)) : arrayToString(Arrays.copyOfRange(split, 1, split.length));
			double heightNum = 0;
			try {
				heightNum = Double.parseDouble(height);
			} catch (NumberFormatException e) {
				heightNum = parsePreset(height);
			}
			if(heightNum == 0) {
				return new ElevatorHardReset();
			} else {
				return new ElevatorToHeight(heightNum);
			}
		} else if(split[0].equalsIgnoreCase("roller")) {
			new RollerRotate(Double.parseDouble(split[1]), Double.parseDouble(split[2]), Double.parseDouble(split[3]));
		} else if(split[0].equalsIgnoreCase("turn")) {
			new Turn(Double.parseDouble(split[1]));
		}
		return null;
	}

	public String arrayToString(String[] arr) {
		StringBuffer result = new StringBuffer();
		for (int i = 0; i < arr.length; i++) {
			result.append( arr[i] );
		}
		return result.toString();
	}

	public double parsePreset(String string) {
		string = string.toLowerCase();
		string = string.replace(' ', '_');
		switch(string) {
		case "bottom":
			return 0;
		case "tote_intake_tote":
			return Elevator.PRESET_TOTE_INTAKETOTE;
		case "bin_intake_tote":
			return Elevator.PRESET_BIN_INTAKETOTE;
		case "top":
			return Elevator.PRESET_TOP;
		case "stabilize":
			return Elevator.PRESET_STABILIZE_TOTES;
		case "release":
			return Elevator.PRESET_CLAW_RELEASE;
		case "tote_intake_three_totes":
		case "tote_three_totes":
			return Elevator.PRESET_TOTE_INTAKETHREETOTES;
		}
		return Elevator.PRESET_TOP;
	}

	public String[] getStringArray() throws TableKeyNotDefinedException {
		String s = container.getString("Auton Array");
		s = s.substring(1, s.length() - 1);
		String[] split = s.split(", ");
		return split;
	}

	private class CustomCommandGroup extends CommandGroup {
		public CustomCommandGroup(String[] commands) {
			for(String command : commands) {
				if(command.charAt(0) == '+') {
					addSequential(parseString(command.substring(1)));
				} else if(command.charAt(0) == '-') {
					addParallel(parseString(command.substring(1)));
				}
			}
		}
	}
}
