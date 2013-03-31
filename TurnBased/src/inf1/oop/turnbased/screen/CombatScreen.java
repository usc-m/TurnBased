package inf1.oop.turnbased.screen;

import inf1.oop.turnbased.TurnBasedGame;
import inf1.oop.turnbased.combat.Battle;
import inf1.oop.turnbased.combat.BattleEndCondition;
import inf1.oop.turnbased.combat.BattleEndListener;
import inf1.oop.turnbased.combat.CombatEntity;
import inf1.oop.turnbased.combat.Turn;
import inf1.oop.turnbased.combat.TurnAction;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;

public class CombatScreen implements Screen {

	private final TurnBasedGame game;
	private Battle battle;
	
	private Stage stage;
	private Table combatActions;
	
	private BitmapFont font;
	private TextureAtlas atlas;
	private Skin skin;
	private TextButtonStyle style;
	private SpriteBatch batch;
	
	private CombatEntity player, monster;
	private final MapScreen mapScreen;
	
	public CombatScreen(TurnBasedGame _game, Battle battle, CombatEntity player, CombatEntity monster, MapScreen parent) {
		this.game = _game;
		this.battle = battle;
		this.player = player;
		this.monster = monster;
		this.mapScreen = parent;
		
		battle.addBattleEndListener(new BattleEndListener() {
			@Override
			public void onBattleEnd(BattleEndCondition cond) {
				game.setScreen(mapScreen);
			}
		});
	}
	
	private void addButton(Table table, String text, InputListener listener) {
		TextButton b = new TextButton(text, style);
		b.addListener(listener);
		table.add(b);
	}
	
	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);

		stage.act(delta);

		batch.begin();
		stage.draw();
		batch.end();
		
	}

	@Override
	public void resize(int width, int height) {
		if(stage == null)
			stage = new Stage(width, height, true);
		Gdx.input.setInputProcessor(stage);
		
		combatActions = new Table();
		
		style = new TextButtonStyle();
		style.up = skin.getDrawable("buttonnormal");
		style.down = skin.getDrawable("buttonpressed");
		style.font = font;
		
		addButton(combatActions, "Attack", new InputListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {
				return true;
			}

			@Override
			public void touchUp(InputEvent event, float x, float y,
					int pointer, int button) {
				battle.applyTurn(new Turn(player, TurnAction.ATTACK, monster));
			}
		});
		
		addButton(combatActions, "Fire", new InputListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {
				return true;
			}

			@Override
			public void touchUp(InputEvent event, float x, float y,
					int pointer, int button) {
				battle.applyTurn(new Turn(player, TurnAction.FIRE, monster));
			}
		});
		
		addButton(combatActions, "Ice", new InputListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {
				return true;
			}

			@Override
			public void touchUp(InputEvent event, float x, float y,
					int pointer, int button) {
				battle.applyTurn(new Turn(player, TurnAction.ICE, monster));
			}
		});
		
		addButton(combatActions, "Heal", new InputListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {
				return true;
			}

			@Override
			public void touchUp(InputEvent event, float x, float y,
					int pointer, int button) {
				battle.applyTurn(new Turn(player, TurnAction.HEAL, player));
			}
		});
		
		addButton(combatActions, "Defend", new InputListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {
				return true;
			}

			@Override
			public void touchUp(InputEvent event, float x, float y,
					int pointer, int button) {
				battle.applyTurn(new Turn(player, TurnAction.DEFEND, player));
			}
		});
		
		addButton(combatActions, "Flee", new InputListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {
				return true;
			}

			@Override
			public void touchUp(InputEvent event, float x, float y,
					int pointer, int button) {
				battle.applyTurn(new Turn(player, TurnAction.FLEE, player));
			}
		});
		
		combatActions.setWidth(width);
		combatActions.setHeight(40);
		combatActions.setY(10);
		stage.addActor(combatActions);
		
	}

	@Override
	public void show() {
		atlas = new TextureAtlas("assets/data/button.pack");
		skin = new Skin();
		skin.addRegions(atlas);
		font = new BitmapFont(Gdx.files.internal("assets/data/font.fnt"), false);
		batch = new SpriteBatch();
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}

}
