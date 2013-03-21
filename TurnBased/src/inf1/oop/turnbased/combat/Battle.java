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
	
	private boolean areAllDead(ArrayList<CombatEntity> faction) {
		boolean result = true;
		
		for(CombatEntity e : faction) {
			result = result && e.isDead();
		}
		
		return result;
	}
	
	private void endBattle(BattleEndCondition cond) {
		for(BattleEndListener l : listeners) {
			l.onBattleEnd(cond);
		}
	}
	
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

	private void executeTurn(Turn turn) {
		TurnAction action = turn.getAction();
		CombatEntity src = turn.getSourceEntity();
		CombatEntity tgt = turn.getTargetEntity();
		
		switch(action) {
		case ATTACK:
			int damage = src.generateDamage(rng);			
			tgt.applyDamage(damage);
			break;
			
		case DEFEND:
			src.defend();
			break;
			
		case FIRE:
			break;
			
		case FLEE:
			endBattle(BattleEndCondition.FLEE);
			break;
			
		case HEAL:
			int healing = src.generateHealing(rng);
			tgt.applyHealing(healing);
			break;
			
		case ICE:
			break;
		}	
	}

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
