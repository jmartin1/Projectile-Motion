package ProjectileMotion;

import org.opensourcephysics.controls.AbstractSimulation;
import org.opensourcephysics.controls.SimulationControl;
import org.opensourcephysics.display.Circle;


/**
 * 
 * @author Julia Martin
 * Version: 1.0
 * Date: October-November, 2015
 * 
 * Description:
 * This class contains the method to calculate the x velocity, y velocity, x acceleration (with air resistance factored in), y acceleration (with air resistance factored in), the x-position, and the y-position of a particle (in this case, a football) after a timestep. It also sets the x-position and y-position of the particle (the football) based on the x-position and y-position calculated for each timestep.
 * 
 */
public class Particle extends Circle {
	//SETTERS AND GETTERS:
	double angle; //declares angle
	double xpos; //declares xpos
	double ypos; //declares ypos
	double accelerationx; //declares accelerationx
	double accelerationy; //declares accelerationy
	double initialvelocity; //declares initialvelocity
	double velocityx; //declares velocityx
	double velocityy; //declares velocityy
	double minimumvelocity; //declares minimumvelocity
	double maximumvelocity; //declares maximumvelocity
	double minimumangle; //declares minimumangle
	double maximumangle; //declares maximumangle
	
	/**
	 * This constructor takes velocity and angle of the particle, sets Initialvelocity to velocity and Angle to angle for the the given particle, and finds the velocity in the x direction and the velocity in the y direction for the given particle.
	 * 
	 * @param velocity
	 * 		The velocity of the particle.
	 * @param angle
	 * 		The angle of the particle.
	 */
	public Particle(double velocity, double angle) {
		setInitialvelocity(velocity); //sets initial velocity
		setAngle(angle); //sets angle
		setVelocityy(getInitialvelocity()*Math.sin(getAngle() * (Math.PI)/180)); //sets velocity in x direction to product of initial velocity and the sine of the angle converted to degrees
		setVelocityx(getInitialvelocity()*Math.cos(getAngle() * (Math.PI)/180)); //sets velocity in y direction to product of initial velocity and the cosine of the angle converted to degrees
	}
	
	public Particle() {
		//default constructor does nothing
	}
	
	/**
	 * This is a getter for minimum angle.
	 * 
	 * @return minimumangle
	 * 		The minimum angle.
	 */
	public double getMinimumangle() {
		return minimumangle;
	}

	/**
	 * This is a setter for minimum angle.
	 * 
	 * @param minimumangle
	 * 		The minimum angle.
	 */
	public void setMinimumangle(double minimumangle) {
		this.minimumangle = minimumangle;
	}

	/**
	 * This is a getter for the maximum angle.
	 * 
	 * @return maximumangle
	 * 		The maximum angle.
	 */
	public double getMaximumangle() {
		return maximumangle;
	}

	/**
	 * This is a setter for the maximum angle.
	 * 
	 * @param maximumangle
	 * 		The maximum angle.
	 */
	public void setMaximumangle(double maximumangle) {
		this.maximumangle = maximumangle;
	}

	/**
	 * This is a getter for the minimum velocity.
	 * 
	 * @return minimumvelocity
	 * 		The minimum velocity.
	 */
	public double getMinimumvelocity() {
		return minimumvelocity;
	}

	/**
	 * This is a setter for the minimum velocity.
	 * 
	 * @return minimumvelocity
	 * 		The minimum velocity.
	 */
	public void setMinimumvelocity(double minimumvelocity) {
		this.minimumvelocity = minimumvelocity;
	}

	/**
	 * This is a getter for maximum velocity.
	 * 
	 * @return maximumvelocity
	 * 		The maximum velocity.
	 */
	public double getMaximumvelocity() {
		return maximumvelocity;
	}

	/**
	 * This is a setter for the maximum velocity.
	 * 
	 * @return maximumvelocity
	 * 		The maximum velocity.
	 */
	public void setMaximumvelocity(double maximumvelocity) {
		this.maximumvelocity = maximumvelocity;
	}

	/**
	 * This is a getter for the angle.
	 * 
	 * @return angle
	 * 		The angle.
	 */
	public double getAngle() {
		return angle;
	}
	
	/**
	 * This is a setter for the angle.
	 * 
	 * @param angle
	 * 		The angle.
	 */
	public void setAngle(double angle) {
		this.angle = angle;
	}

	/**
	 * This is a getter for the x-position.
	 * 
	 * @return xpos
	 * 		The x-position.
	 * 
	 */
	public double getXpos() {
		return xpos;
	}

	/**
	 * This is a setter for the x-position.
	 * 
	 * @param xpos
	 * 		The x-position.
	 */
	public void setXpos(double xpos) {
		this.xpos = xpos;
	}

	/**
	 * This is a getter for the y-position.
	 * 
	 * @return ypos
	 * 		The y-position.
	 */
	public double getYpos() {
		return ypos;
	}

	/**
	 * This is a setter for the y-position.
	 * 
	 * @param ypos
	 * 		The y-position.
	 */
	public void setYpos(double ypos) {
		this.ypos = ypos;
	}

	/**
	 * This is a getter for the acceleration in the x direction.
	 * 
	 * @return accelerationx
	 * 		The acceleration in the x direction.
	 */
	public double getAccelerationx() {
		return accelerationx;
	}

	/**
	 * This is a setter for the acceleration in the x direction.
	 * 
	 * @param accelerationx
	 * 		The acceleration in the x direction.
	 */
	public void setAccelerationx(double accelerationx) {
		this.accelerationx = accelerationx;
	}

	/**
	 * This is a getter for the acceleration in the y direction.
	 * 
	 * @return accelerationy
	 * 		The acceleration in the y direction.
	 */
	public double getAccelerationy() {
		return accelerationy;
	}

	/**
	 * This is a setter for the acceleration in the y-direction.
	 * 
	 * @param accelerationy
	 * 		The acceleration in the y direction.
	 */
	public void setAccelerationy(double accelerationy) {
		this.accelerationy = accelerationy;
	}
	
	/**
	 * This is a getter for the initial velocity.
	 * 
	 * @return initialvelocity
	 * 		The initial velocity.
	 */
	public double getInitialvelocity() {
		return initialvelocity;
	}

	/**
	 * This is a setter for the initial velocity.
	 * 
	 * @param initialvelocity
	 * 		The initial velocity.
	 */
	public void setInitialvelocity(double initialvelocity) {
		this.initialvelocity = initialvelocity;
	}
	
	/**
	 * This is a getter for the velocity in the x direction.
	 * 
	 * @return velocityx
	 * 		The velocity in the x direction.
	 */
	public double getVelocityx() {
		return velocityx;
	}

	/**
	 * This is a setter for the velocity in the x direction.
	 * 
	 * @param velocityx
	 * 		The velocity in the x direction.
	 */
	public void setVelocityx(double velocityx) {
		this.velocityx = velocityx;
	}

	/**
	 * This is a getter for the velocity in the y direction.
	 * 
	 * @return velocityy
	 * 		The velocity in the y direction.
	 */
	public double getVelocityy() {
		return velocityy;
	}

	/**
	 * This is a setter for the velocity in the y direction.
	 * 
	 * @param velocityy
	 * 		The velocity in the y direction.
	 */
	public void setVelocityy(double velocityy) {
		this.velocityy = velocityy;
	}
	
	/**
	 * This method does all the calculations for each timestep; the method calculates the x velocity, the y velocity, the x acceleration, the y acceleration, the x-position and the y-position of the particle for each timestep and the method also sets the position of the particle based on the x-position and the y-position calculated.
	 * 
	 * @param wind
	 * 		The wind acting upon the particle.
	 * @param beta
	 * 		The value needed to calculate air resistance.
	 * @param timestep
	 * 		The time between each step and thus between each calculation.
	 */
	public void step(double wind, double beta, double timestep) {
		setVelocityy(getVelocityy() + getAccelerationy()*timestep); //sets y velocity to sum of current y velocity and the product of the y acceleration and timestep
		setVelocityx(getVelocityx() + getAccelerationx()*timestep); //sets x velocity to sum of current x velocity and the product of the x acceleration and timestep
		setAccelerationx(- beta*(getVelocityx() - wind)); //sets acceleration to the difference of the product of the beta and the difference of the x velocity and wind velocity from zero, which is the acceleration in the x-direction
		setAccelerationy(-9.8 - beta*getVelocityy()); //sets acceleration to the difference of the product of beta and the y velocity from -9.8, which is the acceleration in the y-direction
		setXpos(getXpos() + getVelocityx()*timestep); //sets x-position to the sum of the current x position and the product of the x velocity and timestep
		setYpos(getYpos() + getVelocityy()*timestep); //sets y-position to the sum of the current y position and the product of the y velocity and timestep
		setXY(getXpos(), getYpos()); //sets the position of the particle to have the calculated x-position and the calculated y-position
	}

}
