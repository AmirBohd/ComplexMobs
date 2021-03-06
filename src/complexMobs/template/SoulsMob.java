package complexMobs.template;

import org.bukkit.entity.LivingEntity;
import org.bukkit.util.Vector;

public abstract class SoulsMob extends LivingComplexMob {

	private LivingEntity target;
	private double stamina = 100;
	private int staminaUseTick = 0;
	private int staminaUseTickMax;
	private int invulTick = 0;
	private double poise;
	private double maxPoise;
	private Vector post;
	
	/**
	* Creates a SoulsMob object, This extends the LivingComplexMob type with more values of and methods to fit the category
	* @param ID The ID of this ComplexMob
	* @param name The display name of this ComplexMob
	* @param maxHealth The max health of this LivingComplexMob
	* @param maxPoise The max poise of this SoulsMob
	*/
	protected SoulsMob(String ID, String name, double maxHealth, double maxPoise, int staminaUseTickMax, Vector post) {
		super(ID, name, maxHealth);
		this.maxPoise = maxPoise;
		this.poise = maxPoise;
		this.staminaUseTickMax = staminaUseTickMax;
		this.post = post;
	}
	
	/**
	* Gets the Post of this SoulsMob
	* @return The Post of this SoulsMob
	*/
	public Vector getPost() {
		return this.post;
	}
	
	/**
	* Gets the target LivingEntity of this SoulsMob
	* @return The target LivingEntity of this SoulsMob
	*/
	public LivingEntity getTarget() {
		return this.target;
	}
	
	/**
	* Sets the target LivingEntity of this SoulsMob
	* @param The new target LivingEntity of this SoulsMob
	*/
	public void setTarget(LivingEntity target) {
		this.target = target;
		if (target != null) {
			getBrain().setTarget(target);
		}
	}
	
	/**
	* Gets the current stamina of this SoulsMob
	* @return The current stamina of this SoulsMob
	*/
	public double getStamina() {
		return this.stamina;
	}
	
	/**
	Sets the current stamina of this SoulsMob
	* @param stamina The new stamina of this SoulsMob
	*/
	public void setStamina(double stamina) {
		this.stamina = stamina;
	}
	
	/**
	* Gets the ticks before the SoulsMob's stamina regenerates
	* @return The ticks before the SoulsMob's stamina regenerates
	*/
	public int getStaminaUseTick() {
		return this.staminaUseTick;
	}
	
	/**
	* Gets the ticks before the SoulsMob's stamina regenerates
	* @return The ticks before the SoulsMob's stamina regenerates
	*/
	public void setStaminaUseTick(int staminaUseTick) {
		this.staminaUseTick = staminaUseTick;
	}
	
	/**
	* Gets the max ticks before the SoulsMob's stamina regenerates
	* @return The max ticks before the SoulsMob's stamina regenerates
	*/
	public int getStaminaUseTickMax() {
		return this.staminaUseTickMax;
	}
	
	/**
	* Gets the ticks before the SoulsMob can be hit again
	* @return The ticks before the SoulsMob can be hit again
	*/
	public int getInvulTick() {
		return this.invulTick;
	}
	
	/**
	* Gets the ticks before the SoulsMob can be hit again
	* @return The ticks before the SoulsMob can be hit again
	*/
	public void setInvulTick(int invulTick) {
		this.invulTick = invulTick;
	}
	
	/**
	* Gets the current poise of this SoulsMob 
	@return The current poise of this SoulsMob
	*/
	public double getPoise() {
		return this.poise;
	}
	
	/**
	* Sets the current poise of this SoulsMob
	* @param The new poise of this SoulsMob
	*/
	public void setPoise(double poise) {
		this.poise = poise;
	}
	
	/**
	* Gets the max poise of this SoulsMob
	* @return The max poise of this SoulsMob
	*/
	public double getMaxPoise() {
		return this.maxPoise;
	}
}
