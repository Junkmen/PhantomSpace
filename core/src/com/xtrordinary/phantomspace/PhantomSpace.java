package com.xtrordinary.phantomspace;


import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector3;

public class PhantomSpace extends ApplicationAdapter implements InputProcessor {
	SpriteBatch batch;
	Vector3 touchHandle;
	Texture floorTex,asteroid;
	OrthographicCamera myCamera;
	float mDeltaTime;
	float speed = 100;
	private int CAMERA_WIDTH=1024,CAMERA_HEIGHT = 600,OUT_OF_SCREEN;
	private Player player;
	private Background background;
	private Obstacle floor,floor2;
	private Obstacle asteroid1;
	private Obstacle asteroid2;
	private MusicStreamer mStream;
	private MenuWindow menuWindow;
	
	private int Score = 0;
	
	static final float WORLD_TO_BOX = 0.01f;
	static final float BOX_TO_WORLD = 100f;
	
	
	@Override
	public void create () {
		myCamera = new OrthographicCamera();
		myCamera.setToOrtho(false, CAMERA_WIDTH, CAMERA_HEIGHT);
		batch = new SpriteBatch();
		myCamera.viewportHeight = 600;
		myCamera.viewportWidth = 1024;
		Gdx.input.setInputProcessor(this);
		Gdx.input.setCatchBackKey(true);
		mStream = new MusicStreamer();
		mStream.StreamMusic(Gdx.files.internal("mfx/soundtrack.mp3"),true,0.5f);
		
		touchHandle = new Vector3();
		player = new Player();
		background = new Background();
		floor = new Obstacle();
		floor2 = new Obstacle();
		asteroid1 = new Obstacle();
		asteroid2 = new Obstacle();
		menuWindow = new MenuWindow();
		
		menuWindow.addLayer(new Texture("gfx/GameOverWindow.png"));
		
		floorTex = new Texture("gfx/floor.png");
		floor.setTexture( floorTex);
		floor.setX(0);
		floor.setY(0);
		
		floor2.setTexture(floorTex);
		floor2.setX(floor.getWidth());
		floor2.setY(0);
		
		//floorTex.dispose();
		
		 asteroid = new Texture("gfx/Object_Obstacle.png");
		asteroid1.setTexture(asteroid);
		asteroid1.setX(CAMERA_WIDTH+asteroid1.getWidth());
		asteroid1.setY(MathUtils.random(0, 550));
		asteroid1.Speed = 20;
		
		asteroid2.setTexture(asteroid);
		asteroid2.setX(CAMERA_WIDTH+asteroid2.getWidth());
		asteroid2.setY(MathUtils.random(0,550));
		asteroid2.Speed = 20;
	//	asteroid.dispose();
		
		background.addLayer(new Texture("gfx/background.png"));
		background.setX(0);
		background.setY(0);
		
		player.addTexture(  new Texture("gfx/Player.png") , player.WALK_1);
		player.setDrawableTexture(player.WALK_1);
		player.setX(200);
		player.setY(50);
		OUT_OF_SCREEN = CAMERA_HEIGHT+(player.getHeight()/2);
		
	}

	@Override
	public void render () {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);  
		mDeltaTime = Gdx.graphics.getDeltaTime();
		GameLoop(mDeltaTime);
	}
	
	@Override
	public void dispose() {
		player.texture.dispose();
		for (int i = 0; i < player.numberOfTextures-1; i++) player.animatedTextures[i].dispose();
		for (int i = 0; i < background.Layers-1;i++) background.backgroundLayers[i].dispose();
		floor.texture.dispose();
		floor2.texture.dispose();	
		floorTex.dispose();
		asteroid.dispose();
		batch.dispose();
		mStream.soundtrackMp3.dispose();
		
	}

	   @Override
	   public void resize(int width, int height) {
	   }

	   @Override
	   public void pause() {
	   }

	   @Override
	   public void resume() {
	   }
	    @Override
	    public boolean keyDown(int keycode) {
	    	if (keycode == Keys.BACK) {
	    		Gdx.app.exit();
	    		return true;
	    	} else if (keycode == Keys.SPACE) {
	    		if (player.Y < CAMERA_HEIGHT-(player.getHeight()/2)) {
		    		player.addSpeed(550);
		    	}
	    	} else if (keycode == Keys.ESCAPE) {
	    		Gdx.app.exit();
	    	}
	        return false;
	    }

	    @Override
	    public boolean keyUp(int keycode) {
	        return false;
	    }
	    @Override
	    public boolean keyTyped(char character) {
	        return false;
	    }
	    @Override
	    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
	    	touchHandle.x = screenX;
	    	touchHandle.y = screenY;
	    	myCamera.unproject(touchHandle);
	    	if (player.Y < CAMERA_HEIGHT-(player.getHeight()/2)) {
	    		player.addSpeed(550);
	    	}
	        return true;
	    }
	    @Override
	    public boolean touchUp(int screenX, int screenY, int pointer, int button) {	        
	        return true;
	    }
	    @Override
	    public boolean touchDragged(int screenX, int screenY, int pointer) {
	        return false;
	    }
	    @Override
	    public boolean mouseMoved(int screenX, int screenY) {
	        return false;
	    }
	    @Override
	    public boolean scrolled(int amount) {
	        return false;
	    }
	    public void renderWorld() {
		   Gdx.gl.glClearColor(1, 0, 0, 1);
			Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
			myCamera.update();
			batch.setProjectionMatrix(myCamera.combined);
			batch.begin();
			background.drawLayers(batch);
			batch.draw(floor.texture,floor.X,floor.Y);
			batch.draw(floor2.texture,floor2.X,floor2.Y);
			batch.draw(player.texture, player.X, player.Y);
			batch.draw(asteroid1.texture,asteroid1.X,asteroid1.Y);
			batch.draw(asteroid2.texture,asteroid2.X,asteroid2.Y);			
			batch.end();
	   }
	   
	    public void updateWorld(float deltaTime) {
		   player.Y += player.Speed * deltaTime;
		   player.Speed -= 10+player.SpeedMultiplier;
		   player.SpeedMultiplier = player.SpeedMultiplier+2;
		   if (player.Y < floor.getHeight()) {
			   player.Y = floor.getHeight();
			   player.nullSpeed();
			   player.SpeedMultiplier = 0;
		   } else if (player.Y > OUT_OF_SCREEN) {
			   player.nullSpeed();
			   player.setY(OUT_OF_SCREEN-1);
		   }
		   speed = 200*deltaTime;
		   speed = (int) speed;
		   floor.X -=  speed;
		   floor2.X -= speed;
		   if(floor.X < -floor.getWidth()) {
			   floor.setX(floor2.X + floor2.getWidth());
		   } else if (floor2.X < -floor2.getWidth()) {
			   floor2.setX(floor.X+floor.getWidth());
		   } 
		   asteroid1.Speed++;
		   asteroid2.Speed++;
		   asteroid1.X -= asteroid1.Speed*deltaTime*10;
		   asteroid1.Y -= asteroid1.Speed*deltaTime*10*asteroid1.direction;
		   asteroid2.X -= asteroid2.Speed*deltaTime*10;
		   asteroid2.Y -= asteroid2.Speed*deltaTime*10*asteroid2.direction;
		   
		   
		   
		   if(asteroid1.X< -asteroid1.getWidth()) {
			   asteroid1.setX(1000);
			   asteroid1.Speed = 20;
			   asteroid1.reset(CAMERA_WIDTH);
		   }
		   if(asteroid2.X< -asteroid2.getWidth())  {
			   	asteroid2.setX(1000);
			   	asteroid2.Speed = 20;
			   	asteroid2.reset(CAMERA_WIDTH);
		   }
		   
		   if (asteroid1.active && player.X > asteroid1.X  ){
			   asteroid1.active = false;
			   Score++;
		   }  
		   
		   if (asteroid2.active && player.X > asteroid2.X) {
			   asteroid2.active = false;
			   Score ++;
		   }
	   }
	   
	  public void GameLoop(float deltaTime){
		   updateWorld(deltaTime);
		/*  Array<Body> bodies = new Array<Body>();
		  world.getBodies(bodies);
		  Vector2 playerPos = bodies.get(2).getPosition();
		  player.X =  (int) playerPos.x;
		player.Y = (int) playerPos.y;
		  debugRenderer.render(world,myCamera.combined);
		  world.step(5/60f, 6, 2); */
		   renderWorld();
	   }

	
	 	
	}



		


