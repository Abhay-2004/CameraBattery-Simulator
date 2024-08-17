package hw1;

/**
 * CameraBattery Application which models removable and rechargeable camera battery.
 * @author Abhay Prasanna Rao
 */

public class CameraBattery {
	
	/**
	 * Default values given to us for this assignment
	 */
	
	/**
	 * Number of external charger settings. Settings are numbered between 0 inclusive and 4 exclusive.
	 */
	
	public static final int NUM_CHARGER_SETTINGS = 4;
	
	/**
	 * A constant used in calculating the charge rate of the external charger.
	 */
	
	public static final double CHARGE_RATE = 2.0;
	
	/**
	 * Default power consumption of the camera at the start of simulation.
	 */
	
	public static final double DEFAULT_CAMERA_POWER_CONSUMPTION = 1.0;
	
	private double batteryCharge; // To store the current charge of the battery
	
	private double BatteryCapacity; //Variable to store the max capacity the battery can hold
	
	private double cameraPowerConsumption;//Created a variable for Total Camera power Consumption 
	
	private double totalDrain; // Created a variable to calculate the total drain
	
	private double batteryExternalConnected; // TO check the external background
	
	private int chargerSetting; // Created Variable to use for the button Press method
	
	private double TotalCameraCharge; // To store the total charge of the camera 
	
	private double batteryCameraconnected; // TO check the battery presence in camera
	
	
	/**
	 * Constructor, initialising the variables in it
	 * @param batteryStartingCharge
	 * @param batteryCapacity
	 */
	
	public CameraBattery(double batteryStartingCharge, double batteryCapacity) {
		BatteryCapacity = batteryCapacity;
		cameraPowerConsumption = DEFAULT_CAMERA_POWER_CONSUMPTION;
		chargerSetting = 0;
		totalDrain = 0;
		batteryCameraconnected = 0;
		batteryExternalConnected = 0;	
		batteryCharge = Math.min(batteryStartingCharge, batteryCapacity); 
		
	}
	
	/**
	 * Indicates the user has pressed the setting button one time on the external charger. 
	 * The charge setting increments by one or if already at the maximum setting wraps around to setting 0.
	 */
	
	public void buttonPress() {
		chargerSetting = (chargerSetting + 1) % NUM_CHARGER_SETTINGS;
	}
	
	/**
	 * Charges the battery connected to the camera (assuming it is connected) for a given number of minutes.
	 * The method returns the actual amount the battery has been charged.
	 * @param minutes
	 * @return Camera Charge
	 */
	
	public double cameraCharge(double minutes){ 
		double cameraCharge = Math.min(BatteryCapacity - batteryCharge, minutes * CHARGE_RATE * batteryCameraconnected);
		batteryCharge += cameraCharge;
		TotalCameraCharge += cameraCharge;
		return cameraCharge;
	}
	
	/**
	 * Drains the battery connected to the camera (assuming it is connected) for a given number of minutes.
	 * The amount of drain in milliamp-hours (mAh) is the number of minutes times the cameras power consumption. 
	 * @param minutes
	 * @return Battery Drain
	 */
	
	public double drain(double minutes) {
		double drain = Math.min(batteryCharge,cameraPowerConsumption * minutes * batteryCameraconnected);
		TotalCameraCharge -= drain;
		batteryCharge = batteryCharge - drain;
		totalDrain += drain;
		return drain;
	}
	
	/**
	 * Charges the battery connected to the external charger (assuming it is connected) for a given number of minutes.
	 * The amount of charging in milliamp-hours (mAh) is the number minutes times the charger setting (a number between 0 inclusive and NUM_CHARGER_SETTINGS exclusive) the CHARGE_RATE constant. 
	 * @param minutes
	 * @return Actual Charge in Battery
	 */
	
	public double externalCharge(double minutes) {
		double increaseInCharge = Math.min(BatteryCapacity - batteryCharge, minutes * chargerSetting * CHARGE_RATE * batteryExternalConnected);
		batteryCharge += increaseInCharge;
		return increaseInCharge;
	}
	
	/**
	 * Reset the battery monitoring system by setting the total battery drain count back to 0.
	 */
	
	public void resetBatteryMonitor() {
		totalDrain = 0;	
	}
	
	/**
	 * Get the battery's capacity.
	 * @return Total Battery Capacity
	 */
	
	public double getBatteryCapacity() {
		return BatteryCapacity;
	}
	
	/**
	 * Get the battery's current charge.
	 * @return Current Battery Charge
	 */
	
	public double getBatteryCharge() {
		return batteryCharge;
	}
	
	/**
	 * Get the current charge of the camera's battery.
	 * @return Camera Charge
	 */
	
	public double getCameraCharge() {
		return TotalCameraCharge;
	}
	
	/**
	 * Get the power consumption of the camera.
	 * @return Power Consumption of the Camera
	 */
	
	public double getCameraPowerConsumption() {
		return cameraPowerConsumption;
	}
	
	/**
	 * Get the external charger setting.
	 * @return Charger Setting
	 */
	
	public int getChargerSetting() {
		return chargerSetting;
	}
	
	/**
	 * Get the total amount of power drained from the battery since the last time the battery monitor was started or reset.
	 * @return Total Drain
	 */
	public double getTotalDrain() {
		return totalDrain;
	}
	
	/**
	 * Move the battery to the external charger. Updates any variables as needed to represent the move.
	 */
	
	public void moveBatteryExternal() {
		batteryExternalConnected = 1;
		TotalCameraCharge = 0;
		batteryCameraconnected = 0;	
	}
	
	/**
	 * Move the battery to the camera. Updates any variables as needed to represent the move.
	 */
	
	public void moveBatteryCamera() {
		batteryExternalConnected = 0;
		batteryCameraconnected = 1;
		TotalCameraCharge = batteryCharge;
	}
	
	/**
	 * Remove the battery from either the camera or external charger. Updates any variables as needed to represent the removal.
	 */
	
	public void removeBattery() {
		batteryExternalConnected = 0;
		TotalCameraCharge = 0;
		batteryCameraconnected = 0;
	}
	
	/**
	 * Set the power consumption of the camera.
	 * @param cameraPowerConsumption
	 */
	
	public void setCameraPowerConsumption(double cameraPowerConsumption) {
		this.cameraPowerConsumption = cameraPowerConsumption;
		
	}

	
}


