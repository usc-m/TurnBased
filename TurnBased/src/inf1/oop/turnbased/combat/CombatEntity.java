package inf1.oop.turnbased.combat;

import java.util.ArrayList;
import java.util.Random;

public class CombatEntity {
	private ArrayList<DeathEventListener> listeners;
	
	private int maxHp, currentHp;
	
	private boolean isDead;
	private boolean isDefending;
	
	// stats
	private int attack, defence, healFactor;
	
	// elemental stats
	private int fireResist, firePower;
	private int iceResist, icePower;
	
	// The % damage blocked per point of defense
	private static final double DEFENCE_EFFECTIVENESS=0.05;
	private static final double DEFENDING_MODIFIER=2; // How much defence is multiplied by when defending
	
	private static final double HEALING_EFFECTIVENESS=0.25; // how much each point of heal factor can contribute to the heal mutliplier
	private static final int BASE_HEALING=5; // the minimum amount healed
	
	private static final double RESIST_EFFECTIVENESS=0.1; // how much each point of fire/ice resist affects incoming fire/ice damage
	
	// create a new entity
	public CombatEntity(int maxHp, int initialHp) {
		this.listeners = new ArrayList<DeathEventListener>();
		
		this.maxHp = maxHp;
		this.currentHp = initialHp;
	}
	
	// create a new entity with full health
	public CombatEntity(int maxHp) {
		this(maxHp, maxHp);
	}
	
	// put the entity into a defending state
	public void defend() {
		isDefending = true;
	}
	
	// check to see if an entity is dead
	public boolean isDead() {
		return isDead;
	}
	
	// TODO: unify damage functions
	
	// apply damage to the entity
	public void applyDamage(int dmg) {
		if(isDead) return; // beating a dead horse
		
		double reductionPct = (DEFENCE_EFFECTIVENESS*defence); // total percentage of damage blocked
		
		if(isDefending) {
			reductionPct *= DEFENDING_MODIFIER;
		}
		
		currentHp -= Math.round(dmg * reductionPct);
		
		if(currentHp < 1) {
			this.die();
		}
	}
	
	// apply fire damage to the entity
	public void applyFireDamage(int dmg) {
		if(isDead) return; // beating a dead horse
		
		double reductionPct = (RESIST_EFFECTIVENESS*fireResist); // total percentage of damage blocked
		
		currentHp -= Math.round(dmg * reductionPct);
		
		if(currentHp < 1) {
			this.die();
		}
	}
	
	// apply ice damage to the entity
	public void applyIceDamage(int dmg) {
		if(isDead) return; // beating a dead horse
		
		double reductionPct = (RESIST_EFFECTIVENESS*iceResist); // total percentage of damage blocked
		
		currentHp -= Math.round(dmg * reductionPct);
		
		if(currentHp < 1) {
			this.die();
		}
	}
	
	// apply healing to the entity
	public void applyHealing(int heal) {
		if(isDead) return; // necromancy not allowed
		
		int total = currentHp + heal;
		
		if(total > maxHp) { 
			currentHp = maxHp;
		} else {
			currentHp = total;
		}
	}
	
	// add an object to our list of objects that want to know if we die
	public void addDeathEventListener(DeathEventListener listener) {
		listeners.add(listener);
	}
	
	// TODO: Unify damage generators
	
	// produce a damage value to apply to an entity
	public int generateDamage(Random rng) {
		// TODO: Come up with a more fun damage generator, maybe add more stats or something
		return rng.nextInt(attack);
	}
	
	public int getAttack() {
		return attack;
	}

	public void setAttack(int attack) {
		this.attack = attack;
	}

	public int getDefence() {
		return defence;
	}

	public void setDefence(int defence) {
		this.defence = defence;
	}

	public int getHealFactor() {
		return healFactor;
	}

	public void setHealFactor(int healFactor) {
		this.healFactor = healFactor;
	}

	public int getFireResist() {
		return fireResist;
	}

	public void setFireResist(int fireResist) {
		this.fireResist = fireResist;
	}

	public int getFirePower() {
		return firePower;
	}

	public void setFirePower(int firePower) {
		this.firePower = firePower;
	}

	public int getIceResist() {
		return iceResist;
	}

	public void setIceResist(int iceResist) {
		this.iceResist = iceResist;
	}

	public int getIcePower() {
		return icePower;
	}

	public void setIcePower(int icePower) {
		this.icePower = icePower;
	}

	// produce a damage value to apply to an entity
	public int generateFireDamage(Random rng) {
		// TODO: Come up with a more fun damage generator, maybe add more stats or something
		return rng.nextInt(firePower);
	}
	
	// produce a damage value to apply to an entity
	public int generateIceDamage(Random rng) {
		// TODO: Come up with a more fun damage generator, maybe add more stats or something
		return rng.nextInt(icePower);
	}
	
	// produce a heal value to apply to an entity
	public int generateHealing(Random rng) {
		int maxHealMultiplier = (int)Math.round(HEALING_EFFECTIVENESS*healFactor);
		
		return BASE_HEALING * (1 + rng.nextInt(maxHealMultiplier)); // add 1 so the healing multiplier is never 0, so healing is always performed
	}
	
	// internal function to handle death
	private void die() {
		isDead = true;
		
		for(DeathEventListener listener : listeners) {
			listener.onEntityKilled(this);
		}
	}
}
