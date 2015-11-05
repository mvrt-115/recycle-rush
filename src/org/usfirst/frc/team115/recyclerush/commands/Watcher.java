package org.usfirst.frc.team115.recyclerush.commands;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.usfirst.frc.team115.recyclerush.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class Watcher extends Command {

	private File logfile;
	private BufferedWriter os;
	private boolean donotrun = false;

	Charset charset = Charset.forName("US-ASCII");

	public Watcher() {
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd:HH-mm-ss");
		logfile = new File("/var/log/mvrt/state-log" + format.format(new Date()) + "-0");
		while (logfile.exists()) {
			String path = logfile.getAbsolutePath();
			char lastChar = path.charAt(path.length() - 1);
			logfile = new File(path.substring(0, path.length() - 2) + (Integer.parseInt(lastChar + "") + 1));
		}
		this.setRunWhenDisabled(true);

		try {
			logfile.createNewFile();
			os = Files.newBufferedWriter(logfile.toPath(), charset);
			os.write("v,i");
		} catch (IOException e) {
			donotrun = true;
			e.printStackTrace();
		}
	}

	@Override
	protected void initialize() {
		if (donotrun) {
			this.cancel();
			return;
		}
		try {
			os = Files.newBufferedWriter(logfile.toPath(), charset);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	protected void execute() {
		try {
			os.write(Robot.pdp.getVoltage() + "," + Robot.pdp.getTotalCurrent());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	protected boolean isFinished() {
		return false;
	}

	@Override
	protected void end() {
		try {
			os.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void interrupted() {
		try {
			os.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
