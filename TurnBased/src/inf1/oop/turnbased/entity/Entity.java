/* =========================
 * CLASS DETAILS
 * ---------------
 * 
 * CONSTRUCTOR: 
 * Entity object_name = new Entity(int x, int y, String name, int damage, int movespeed, int totalhealth)
 * 
 * creates the entity object_name with given x-y positions, name, damage, movespeed and totalhealth.
 * eg. for a wall we could have: Entity(10,5,"wall",0,0,999);
 * 
 *  
 * METHODS:
 * getObject() returns the object in order to access an object from separate methods and classes.
 * getX() returns x
 * setX(x) sets x
 * getY() returns y
 * setY(y) sets y
 * getName() returns name
 * setName(name) sets name
 * getArmor() returns armor
 * setArmor(armor) sets armor
 * getDamage() returns damage
 * attack() get attack, returns damage with a randomised equation and depending on weapon held.
 * setDamage(dmg) returns damage
 * getHealth() returns health
 * setHealth(health) sets health to new health, limits health to max-health if it exceeds the total.
 * addTotalHealth(health) adds health to totalhealth. use negative number to subtract
 * heal(heal) increases current health by heal amount. Automagically limits heal to the total if required.
 * hit(hit) decreases current health by hit amount. Death script when health equals or falls below 0.
 * died() automatically triggered when ownhealth <= 0. What to do in this method? see suggestions in method.
 * =========================
 */

package inf1.oop.turnbased.entity;

import inf1.oop.turnbased.combat.CombatEntity;
import inf1.oop.turnbased.combat.Stats;

public class Entity {
	private int x, y;; //HIGH PRIORITY
	//int money, stamina, attackspeed, level, experience, weapondamage, ;//LOWER PRIORITY	
	
	//equipment
	//M.Weapon ID: 	100-149 (main hand)
	//O.Weapon ID:	150-199 (off hand)
	//top-armor ID:	200-299
	//leg-armor ID:	300-399
	//shoulders ID:	400-499
	//vambraces ID: 500-599
	//Feet		ID: 600-699
	//private int mweapon, oweapon, top, leg, shoulder, vambs, foot;
	
	
	private CombatEntity fightingEntity;

	private String name;
	
	//constructor; similar to "instance_create(x,y,name);"
	public Entity(int x, int y, String name)
	{
		//Initializes variables
		this.x = x;
		this.y = y;
		this.name = name;
		
		fightingEntity = new CombatEntity(100, new Stats(), name);
		//this.weapon = 0;
		//this.armor = 0;
		
		//armor
		//numbers represent ID of the items
		/*mweapon=0;
		oweapon=0;
		top=0;
		leg=0;
		shoulder=0;
		vambs=0;
		foot=0;*/
	}
	
	//method that returns object's x-position
	public int getX()
	{
		return x;
	}
	
	//method that returns object's y-position
	public int getY()
	{
		return y;
	}
	
	//method that sets object's x-position
	public void setX(int x)
	{
		this.x = x;
	}
	
	//method that sets object's y-position
	public void setY(int y)
	{
		this.y = y;
	}
	
	//get object name
	public String getName()
	{
		return this.name;
	}
	
	//set object name
	public void setName(String name)
	{
		this.name = name;
	}

	public CombatEntity getCombatEntity() {
		return fightingEntity;
	}
	
	/**********************************
	  ------------- ARMOR -------------
	 *********************************/
	
	/*public void setMWeapon(int id)
	{
		mweapon=id;
	}
	public void setOWeapon(int id)
	{
		oweapon=id;
	}
	public void setTop(int id)
	{
		top=id;
	}
	public void setLeg(int id)
	{
		leg=id;
	}
	public void setShoulder(int id)
	{
		shoulder=id;
	}
	public void setVambs(int id)
	{
		vambs=id;
	}
	public void setFoot(int id)
	{
		foot=id;
	}*/
	
	
	
	
}
