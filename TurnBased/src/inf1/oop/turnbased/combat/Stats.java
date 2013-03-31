package inf1.oop.turnbased.combat;

import java.util.HashMap;

public class Stats {
	private static final double DEFENCE_EFFECTIVENESS=0.05; // The % damage blocked per point of defense
	public static final double DEFENDING_MODIFIER=2; // How much defence is multiplied by when defending
	
	public static final double HEALING_EFFECTIVENESS=0.25; // how much each point of heal factor can contribute to the heal mutliplier
	public static final int BASE_HEALING=5; // the minimum amount healed
	
	private static final double RESIST_EFFECTIVENESS=0.1; // how much each point of fire/ice resist affects incoming fire/ice damage
	
	private int healFactor;
	
	private HashMap<DamageTypes, Integer> attackPowers;
	private HashMap<DamageTypes, Integer> attackResists;
	
	public Stats() {
		attackPowers = new HashMap<DamageTypes, Integer>();
		attackResists = new HashMap<DamageTypes, Integer>();
		healFactor = 1; // default value so healing won't just mysteriously fail
	}
	
	public static final double getResistanceEffectiveness(DamageTypes type) {
		switch(type) {
		case FIRE:
		case ICE:
			return RESIST_EFFECTIVENESS;
			
		case PHYSICAL:
			return DEFENCE_EFFECTIVENESS;
		
		default:
			return 1; // TODO: Make this throw an error
		}
	}
	
	public Stats setHealFactor(int hf) {
		this.healFactor = hf;
		return this;
	}
	
	public int getHealFactor() {
		return healFactor;
	}
	
	public Stats setAttackPower(DamageTypes type, int power) {
		attackPowers.put(type, power);
		return this;
	}
	
	public int getAttackPower(DamageTypes type) {
		if(attackPowers.containsKey(type)){
			return attackPowers.get(type);
		}
		
		return 1; // so all attacks will actually attempt to do damage
	}
	
	public Stats setAttackResist(DamageTypes type, int resist) {
		attackResists.put(type, resist);
		return this;
	}
	
	public int getAttackResists(DamageTypes type) {
		if(attackResists.containsKey(type)){
			return attackResists.get(type);
		}
		
		return 1; // since it's possible to be undefended
	}
}
