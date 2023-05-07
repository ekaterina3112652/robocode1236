package robot1236;

import robocode.*;
import robocode.util.Utils;
import java.util.Random;

import java.awt.Color;
import java.awt.geom.Point2D;

// API help : https://robocode.sourceforge.io/docs/robocode/robocode/Robot.html

/**
 * Robot1236 - a robot by (Luanna Virginia)
 */
public class Robot1236 extends AdvancedRobot {

	private Random random = new Random();
	private double moveDistance = 100;

	public void run() {
		// Set colors
		setColors(Color.yellow, Color.green, Color.red);

		while (true) {
			moveRandomly();
		}
	}

	private void moveRandomly() {
		setAhead(moveDistance * (random.nextDouble() * 2 - 1));
		setTurnRight(90 * (random.nextDouble() * 2 - 1));
		execute();
	}

	public void onHitWall(HitWallEvent e) {
		// Will randomly generate an angle between 45 and 135 degrees
		double turnAngle = Math.toRadians((Math.random() * 90) + 45);
		// Will randomly generate a distance to move forward between 50 and 150 units
		double distance = (Math.random() * 100) + 50;
		// If math random is less than 0.5 will randomly turn for right or left
		if (Math.random() < 0.5) {
			// Turn right based on the random turnAngle
			setTurnRightRadians(turnAngle);
		} else {
			// Turn left based on the random turnAngle
			setTurnLeftRadians(turnAngle);
		}
		// Go ahead a random distance
		setAhead(distance);
		execute();
	}

	public void onScannedRobot(ScannedRobotEvent e) {
		// Turn towards scanned robot
		setTurnRight(e.getBearing());
		// Fire at scanned robot
		fire(1);
	}
}