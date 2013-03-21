package inf1.oop.turnbased.combat;

import java.util.ArrayList;
import java.util.Random;

public class Battle {
	private Random rng;
	private ArrayList<CombatEntity> playerFaction, enemyFaction; // put this in so there's potential to have multiple entity battles
	
	private ArrayList<BattleEndListener> listeners;
	
	// added this so we can keep track of turns, but not sure if that's necessary for saving or not
	private ArrayList<Turn> turns;
	
	// This takes two entities because a battle needs two participants minimum in order to work
	public Battle(CombatEntity a, CombatEntity b) {
		listeners = new ArrayList<BattleEndListener>();
		
		playerFaction = new ArrayList<CombatEntity>();
		playerFaction.add(a);
		
		enemyFaction = new ArrayList<CombatEntity>();
		enemyFaction.add(b);
		
		turns = new ArrayList<Turn>();
		
		rng = new Random();
	}
	
	// adds an object that wants to know if the battle has ended to our list of things that want to know
	public void addBattleEndListener(BattleEndListener listener) {
		listeners.add(listener);
	}
	
	// checks to see if all the combatants in one team are dead
	private boolean areAllDead(ArrayList<CombatEntity> faction) {
		boolean result = true;
		
		for(CombatEntity e : faction) {
			result = result && e.isDead();
		}
		
		return result;
	}
	
	// internal function to end the battle and notify things that want to know if the battle has ended
	private void endBattle(BattleEndCondition cond) {
		for(BattleEndListener l : listeners) {
			l.onBattleEnd(cond);
		}
	}
	
	// applies a turn to the current battle state, possibly ending the battle
	public void applyTurn(Turn turn) {
		if(isValidTurn(turn)) {
			turns.add(turn);
			executeTurn(turn);
		}
		
		if(areAllDead(playerFaction)) {
			endBattle(BattleEndCondition.LOSE);
		}
		
		if(areAllDead(enemyFaction)) {
			endBattle(BattleEndCondition.WIN);
		}
		
		// TODO: Throw exception
	}

	// performs the actual turn action
	private void executeTurn(Turn turn) {
		TurnAction action = turn.getAction();
		CombatEntity src = turn.getSourceEntity();
		CombatEntity tgt = turn.getTargetEntity();
		
		switch(action) {
		case ATTACK:
			int damage = src.generateDamage(DamageTypes.PHYSICAL, rng);			
			tgt.applyDamage(DamageTypes.PHYSICAL, damage);
			break;
			
		case DEFEND:
			src.defend();
			break;
			
		case FIRE:
			int fDmg = src.generateDamage(DamageTypes.FIRE, rng);
			tgt.applyDamage(DamageTypes.FIRE, fDmg);
			break;
			
		case FLEE:
			endBattle(BattleEndCondition.FLEE);
			break;
			
		case HEAL:
			int healing = src.generateHealing(rng);
			tgt.applyHealing(healing);
			break;
			
		case ICE:
			int iDmg = src.generateDamage(DamageTypes.ICE, rng);
			tgt.applyDamage(DamageTypes.ICE, iDmg);
			break;
		}	
	}

	// checks to see if the turn is a plausible turn
	private boolean isValidTurn(Turn turn) {
		TurnAction action = turn.getAction();
		CombatEntity src = turn.getSourceEntity();
		
		boolean aContainsSrc = playerFaction.contains(src);
		boolean srcExists = aContainsSrc || enemyFaction.contains(src); // check that one of the teams contains the "source" entity
		
		CombatEntity tgt = turn.getTargetEntity();
		boolean aContainsTgt = playerFaction.contains(tgt);
		boolean tgtExists = aContainsTgt || enemyFaction.contains(tgt); // check that one of the teams contains the "target" entity
		
		boolean differentTeams = (aContainsSrc && !aContainsTgt) || (!aContainsSrc && aContainsTgt); // check that the entities aren't both on the same team
		
		
		// if we're healing, we want them to be on the same team
		if(action == TurnAction.HEAL) {
			return (!src.isDead()) && (!tgt.isDead()) && srcExists && tgtExists && (!differentTeams);
		} else { // otherwise we want them on different teams team
			return (!src.isDead()) && (!tgt.isDead()) && srcExists && tgtExists && differentTeams;
		}
	}
	
}
