package ProjectileMotion;

import java.awt.Color;
import java.util.ArrayList;

import org.opensourcephysics.controls.AbstractSimulation;
import org.opensourcephysics.controls.SimulationControl;
import org.opensourcephysics.display.*;
import org.opensourcephysics.frames.*;

/**
 * 
 * @author Julia Martin
 * Version: 1.0
 * Date: October-November, 2015
 * 
 * Description:
 * This class contains the methods that sets values for variables related to the football (x-position, y-position, x acceleration, y acceleration, maximum velocity, minimum velocity, maximum angle, minimum angle, and wind) and uses these variables to calculate the minimum velocity and minimum corresponding angle for a football kicked from a certain distance away from a football goalpost to the goalpost.
 * The BONUS question I answered in this project is the following: What effect does wind have on a football kicked from a certain distance away from a football goalpost to the goalpost?
 * 
 * WHAT I LEARNED:
 * I learned a lot about physics, computer science, and the intersection between the two disciplines in this assignment.
 * Most importantly, however, I was able to code a working example of physics as applied to the real world, namely a football and the minimum velocity and minimum corresponding angle at which to kick the football to get it over the goalpost.
 * In finding the minimum velocity and corresponding angle, I had to factor in air resistance in the acceleration for a given direction by subtracting the product of beta and velocity for that given direction from the current acceleration.
 * In this endeavor, I learned that greater air resistance made the footballs fall faster; the footballs with a certain air resistance peaked at a lower height and landed closer to the starting position than footballs with lesser or no air resistance.
 * In addition, I gained a deeper understanding into how wind in the x direction effects the footballs.
 * In a trial without wind, a certain number of footballs made it over the goalpost; in a trial with positive wind (tailwind), a greater number of footballs made it over the goalpost; and, lastly, in a trial with negative wind (headwind), a lesser number of footballs made it over the goalpost.
 * This means that the same number of footballs will go over the goalpost if kicked from a certain distance with positive wind as the number of footballs kicked from a greater distance away from the goalpost with no wind at all.
 * All in all, this project was very interesting and I look forward to applying the same methods I used in my code to find the minimum velocity and angle needed for other sports, such as volleyball or javelin.
 * 
 */

public class ProjectileLaunch extends AbstractSimulation {

	Particle c = new Particle(); //creates new particle c, from which all particles c are created from
	DisplayFrame frame = new DisplayFrame("X" , "Y", "Display Frame Test"); //creates frame
	double goalpostheight = 3.00; //the height of a standard NFL goalpost is 3.00 meters high
	double goalpostdistance = 9.144 + 10.00; //the distance between the goalpost and the player is the sum of the length of the endzone, which is 9.144 meters, and the distance (in meters) that the football is from the endzone
	ArrayList<Double> minimumvelocityarraylist = new ArrayList<Double>(); //creates new array list for velocities
	ArrayList<Double> minimumanglearraylist = new ArrayList<Double>(); //creates new array list for angles
	double minangle = 0; //initializes minangle, the variable to represent the minimum angle needed for the minimum velocity to get over the goalpost
	double minvelo = 0; //initializes minvelo, the variable to represent the minimum velocity needed to get over the goalpost
	ArrayList<Particle> particles = new ArrayList<Particle>(); //creates new array list for particles
	ArrayList<Trail> trails = new ArrayList<Trail>(); //creates new array list for trails
	boolean running = true; //initializes running as true, the boolean that remains true until minimumvelocityarraylist and minimumanglearraylist have been sorted through
	boolean alllanded = false; //initializes alllanded as false, the boolean that remains false until the y-position all the particles are less than or equal to zero
	int particleslanded = 0; //initializes particleslanded, the variable that keeps track of how many particles have a y-position that is less than or equal to zero

	/**
	 * This method calls on the step method in the Particle Class for each particle. If the particle makes it over the goalpost, the particle's initial velocity and angle are added to an minimum velocity and angle array list, respectively. Once all the particles have landed, the minimum velocity array list is sorted (using the bubble sort method) to find the smallest velocity and the minimum corresponding angle for that minimum velocity. The minimum velocity and minimum corresponding angle is printed and doStep() stops running. 
	 */
	protected void doStep() {
		if (running == true) { //if running (boolean that keeps track of whether or not the doStep is running) is true, then do the following:
			if (alllanded == false) { //if alllanded (boolean that keeps track of whether or not all the particles have a y-position that is less than or equal to zero) is true, then do the following:
				particleslanded = 0; //sets particleslanded to zero
				for (int i = 0; i < particles.size(); i++) { //for loop that loops through all the particles
					Particle p = particles.get(i); //sets particle p to a particle in the list with index i
					p.step(control.getDouble("wind"), control.getDouble("beta"), control.getDouble("timestep")); //calls on step method in the Particle Class with parameters wind, beta, and timestep that have been set in the control panel
					Trail t = trails.get(i); //sets trail t to a trail in the list with index i
					t.addPoint(p.getXpos(),p.getYpos()); //adds point with an x-value of the x-position of the particle and a y-value of the y-position of the particle to trail t
					if (p.getYpos() >= goalpostheight && p.getXpos() >= goalpostdistance) { //if the y-position of the particle is greater than or equal to the goalpost height and the x-position is greater than or equal to the distance from the football to the goalpost, then do the following:
						minimumvelocityarraylist.add(p.getInitialvelocity()); //add the velocity of that particle to minimumvelocityarraylist
						minimumanglearraylist.add(p.getAngle()); //add the angle of that particle to minimumvelocityarraylist
						p.color = Color.cyan; //change the color of the particle to cyan
					}
					if(p.getYpos() <= 0) { //if the y-position of the particle is less than or equal to zero, then do the following:
						particleslanded++; //increment particleslanded by one
					}
				}
				if (particleslanded == particles.size()) { //if particleslanded equals the number of particles in the particles array list, then do the following:
					alllanded = true; //set alllanded to true
				}
			}

			if (alllanded == true) { //if alllanded is true, then do the following:
				if (minimumvelocityarraylist.size() > 0) { //if minimumvelocityarraylist is not empty, then do the following:
					minvelo = minimumvelocityarraylist.get(0); //set minvelo to the first double in minimumvelocityarraylist
					for(int j = 0; j < minimumvelocityarraylist.size(); j++) {//for loop that continues looping until j is less than the number of doubles in minimumvelocityarraylist
						if (minvelo > minimumvelocityarraylist.get(j)) { //if minvelo is greater than or equal to double in minimumvelocityarraylist at index j, do the following:
							minvelo = minimumvelocityarraylist.get(j); //set minvelo to the double in minimumvelocityarraylist at index j
							minangle = minimumanglearraylist.get(j); //set minangle to the double in minimumanglearraylist at index j
						}
						else { //else, do the following:
							//do nothing
						}
					}
					System.out.println("The minimum velocity is " + minvelo + " meters per second, for an angle of " + minangle + " degrees."); //print minimum velocity and angle
					running = false; //set running to false
					return; //get out of doStep()
				}
				else { //else, do the following:
					System.out.println("None of the footballs made it over the goalpost."); //prints none made it over poalpost
					running = false; //set running to false
					return; //get out of doStep()
				}
			}
		}
	}


	/**
	 * This method initializes all the values in the control panel, creates all the particles, and adds them to the frame.
	 */
	public void initialize()
	{
		c.setXpos(control.getDouble("x position")); //sets Xpos to value in control panel for particle c
		c.setYpos(control.getDouble("y position")); //sets Ypos to value in control panel for particle c
		c.setAngle(37); //sets Angle to 37 degrees, a random value, for particle c
		c.setAccelerationx(control.getDouble("x acceleration")); //sets Accelerationx to value in control panel for particle c
		c.setAccelerationy(control.getDouble("y acceleration")); //sets Accelerationy to value in control panel for particle c
		c.setMaximumvelocity(control.getDouble("maximum velocity")); //sets Maximumvelocity to value in control panel for particle c
		c.setMinimumvelocity(control.getDouble("minimum velocity")); //sets Minimumvelocity to value in control panel for particle c
		c.setMinimumangle(control.getDouble("minimum angle")); //sets Minimumangle to value in control panel for particle c
		c.setMaximumangle(control.getDouble("maximum angle")); //sets Maximumangle to value in control panel for particle c

		for(double i = c.getMinimumvelocity(); i < c.getMaximumvelocity(); i++) { //for loops that loops through the velocities entered in the control panel
			for(double j = c.getMinimumangle(); j < c.getMaximumangle(); j++) { //for loops that loops through the angles entered in the control panel
				Particle p = new Particle(i, j); //creates new particle p
				p.color = Color.magenta; //sets color of particle to magenta
				p.setAccelerationy(control.getDouble("y acceleration")); //sets Accelerationx to value in control panel for particle p
				p.setAccelerationx(control.getDouble("x acceleration")); //sets Accelerationy to value in control panel for particle p
				particles.add(p); //adds particle p to particle array list
				frame.addDrawable(p); //adds particle p to frame
				Trail t = new Trail(); //creates new trail
				trails.add(t); //adds trail to trail array list
				frame.addDrawable(t); //adds trail to frame
			}
		}

		frame.setVisible(true); //makes frame appear on screen
		frame.addDrawable(c); //adds particle c to frame
		frame.setPreferredMinMax(-5, 100, -5, 50); //sets x and y min and max for frame
		DrawableShape goalpost = DrawableShape.createRectangle(goalpostdistance + .5, goalpostheight/2, 1, goalpostheight); //creates goalpost rectangle
		frame.addDrawable(goalpost); //adds goalpost rectangle to screen
		goalpost.setMarkerColor(Color.green, Color.green); //sets goalpost rectangle color to green
	}


	/**
	 * This method sets all the values in the control panel.
	 */
	public void reset() {
		control.setValue("x position", 0); //sets value of x-position
		control.setValue("y position", 0); //sets value of y-position
		control.setValue("x acceleration", 0); //sets value of x-acceleration
		control.setValue("y acceleration", -10); //sets value of y-acceleration
		control.setValue("maximum velocity", 40); //sets value of maximum velocity
		control.setValue("minimum velocity", 20); //sets value of minimum velocity
		control.setValue("maximum angle", 80); //sets value of maximum velocity
		control.setValue("minimum angle", 20); //sets value of minimum velocity
		control.setValue("wind", 0); //sets value of wind velocity
		control.setValue("beta", 0.2); //sets value of beta
		control.setValue("timestep", 0.01); //sets value of timestep
	}

	public static void main(String[] args) { //main
		SimulationControl.createApp(new ProjectileLaunch()); //Creates a SIP animation control and establishes communication between the control and the model

	}

}
