package inf1.oop.turnbased.combat;

import java.util.ArrayList;
import java.util.Random;

public class CombatEntity {
	private ArrayList<DeathEventListener> listeners;
	
	private int maxHp, currentHp;
	
	private boolean isDead;
	private boolean isDefending;
	
	// all of the entities stats are kept in this object
	private Stats stats;

	
	// create a new entity
	public CombatEntity(int maxHp, int initialHp, Stats stats) {
		this.listeners = new ArrayList<DeathEventListener>();
		
		this.maxHp = maxHp;
		this.currentHp = initialHp;
		this.stats = stats;
	}
	
	// create a new entity with full health
	public CombatEntity(int maxHp, Stats stats) {
		this(maxHp, maxHp, stats);
	}
	
	// put the entity into a defending state
	public void defend() {
		isDefending = true;
	}
	
	// check to see if an entity is dead
	public boolean isDead() {
		return isDead;
	}
	
	// apply damage to the entity
	public void applyDamage(DamageTypes type, int dmg) {
		if(isDead) return; // beating a dead horse
		
		double reductionPct = (Stats.getResistanceEffectiveness(type)*stats.getAttackResists(type)); // total percentage of damage blocked
		
		if(isDefending) {
			reductionPct *= Stats.DEFENDING_MODIFIER;
		}
		
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
	
	public Stats getStats() {
		return stats;
	}

	public void setStats(Stats stats) {
		this.stats = stats;
	}

	// produce a damage value to apply to an entity
	public int generateDamage(DamageTypes type, Random rng) {
		// TODO: Come up with a more fun damage generator, maybe add more stats or something
		return rng.nextInt(stats.getAttackPower(type));
	}
	
	// produce a heal value to apply to an entity
	public int generateHealing(Random rng) {
		int maxHealMultiplier = (int)Math.round(Stats.HEALING_EFFECTIVENESS*stats.getHealFactor());
		
		return Stats.BASE_HEALING * (1 + rng.nextInt(maxHealMultiplier)); // add 1 so the healing multiplier is never 0, so healing is always performed
	}
	
	// internal function to handle death
	private void die() {
		isDead = true;
		
		for(DeathEventListener listener : listeners) {
			listener.onEntityKilled(this);
		}
	}
}
