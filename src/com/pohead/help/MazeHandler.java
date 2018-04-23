package com.pohead.help;
import static com.pohead.help.Constants.TILESIZE;
import static com.pohead.help.Constants.WIDTH;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * meant to update and render everything about the maze map.
 * this includes handling the "player" in the maze, checking collision with walls
 * 
 * this is also "turn-based" to an extent
 * @author A
 *
 */
public class MazeHandler {
//	String[] maze1String = {
//			"aaaaaaaaaaaaaaaaaaaa",
//			"aaaaaaaaaaaaaaaaaaaa",
//			"bbbbbabbbbaaaaaaaaaa",
//			"baaababaabaaaaaaaaaa",
//			"bbbbbabaabaaaaaaaaaa",
//			"baaaaabaabaaaaaaaaaa",
//			"baaaaabaabaaaaaaaaaa",
//			"baaaaabbbbaaaaaaaaaa",
//			"aaaaaaaaaaaaaaaaaaaa",
//			"aaaaaaaaaaaaaaaaaaaa",
//			"aaaaaaaaaaaaaaaaaaaa",
//			"aaaaaaaaaaaaaaaaaaaa",
//			"aaaaaaabaaaaaaaaaaaa",
//			"aaaaaaabaaaaaaaaaaaa",
//			"aaabbbbbbbbbbbbbaaaa",
//			"aaaaaaabaaaaaaaaaaaa",
//			"aaaaaaabaaaaaaaaaaaa",
//			"aaaaaaaaaaaaaaaaaaaa",
//			"aaaaaaaaaaaaaaaaaaaa",
//			"aaaaaaaaaaaaaaaaaaaa",
//			"aaaaaaaaaaaaaaaaaaaa",
//			"aaaaabbbbaaaaaaaaaaa",
//			"aaaaaaaaaaaaaaaaaaaa",
//			"aaaaaaaaaaaaaaaaaaaa",
//			"aaaaabbbaaaaaaaaaaaa",
//			"aaaaabaaaaaaaaaaaaaa",
//			"aaaaaaaaaaaaaaaaaaaa",
//			"aaaaaaabbbbbbbaaaaaa",
//			"aaaaaaaaaaaaaaaaaaaa",
//			"aaaaabbbbbbbbbbbbaaa"
//	};
	
	Tile[][] currentTileMap = new Tile[30][20];
	MazeCrawler mazeCrawler;
	private int mazeLevel = 1;
	private GameScreen screen;
	
	public MazeHandler(GameScreen screen) {
		this.screen=screen;
//		List<String> reversedMaze = Arrays.asList(maze1String);
//		Collections.reverse(reversedMaze);
//		maze1String = (String[]) reversedMaze.toArray();
		
		generateMaze();
		initMazeCrawler();

		
	}
	

	
	private void initMazeCrawler() {
		mazeCrawler = new MazeCrawler(1,9);
		
		
	
		
	}

	/**
	 * entire maze should be drawn past halfway x point.
	 */
	private void generateMaze() {
		Pixmap p = new Pixmap(Gdx.files.internal("maze"+mazeLevel+".png"));

		Sprite  s = new Sprite(new Texture(p));
		s.rotate(180);
		p = s.getTexture().getTextureData().consumePixmap();
		String file = "missingimg.png"; boolean isSolid = false;
		for(int r = 0; r < 30; r++)
			for(int c = 0; c < 20; c++) {
				//switch(maze1String[r].charAt(c)) {
				switch(p.getPixel(c,29-r)) { //29-r because getpixel() has inverted y value
				case 0x00ff21ff: //grass
					file = "grass_1.png"; isSolid = false; break;
				case 0x808080ff:
					file = "wall_1.png"; isSolid = true;break;
				case 0x404040ff: //wall_2
					file = "wall_2.png"; isSolid = true;break;
				case 0x303030ff: //stone
					file = "stone_1.png"; isSolid = true; break;
				case 0xff0000ff:
					file =  "lava_1.png"; isSolid = true;break;
				case 0x00ffffff:
					file = "watertile.png"; isSolid = true;break;
				case 0xffffffff:
					file = "goaltile.png"; isSolid = false;break;
				case 0xc0c0c0ff:
					file = "tile_1.png"; isSolid = false;break;
				case 0x000000ff:
					file = "tile_2.png"; isSolid = true;break;
					
				default: 
					file = "missingimg.png"; isSolid = false;
					break;
				}
				currentTileMap[r][c] = new Tile(c*TILESIZE + WIDTH/2, r*TILESIZE, file,isSolid);
				
			}
	}
	public void update(float dt) {
		if(currentTileMap[mazeCrawler.row][mazeCrawler.column].file.equals("goaltile.png"))
			nextLevel();
		mazeCrawler.update();
	}
	
	private void nextLevel() {
		if(mazeLevel >= 2) {
			screen.inGameTheme.stop();
			screen.getGame().setScreen(new VictoryScreen(screen.getGame()));
		} else {
			mazeLevel++;
		generateMaze();
		mazeCrawler.row=1; mazeCrawler.column=9;
		}
	}
	
	public void render(SpriteBatch batch) {

		
		for(Tile[] tiles : currentTileMap)
			for(Tile tile : tiles) {
				tile.draw(batch);
			}
		
		mazeCrawler.draw(batch);
		
		
		//help.draw(batch, "row: "+mazeCrawler.row+"\ncolumn: "+mazeCrawler.column, 100, 100);
	}
	BitmapFont help=new BitmapFont();
	
	class Tile extends Sprite{
		boolean isSolid;
		String file;
		Tile(float xPixel, float yPixel, String file, boolean isSolid){
			super(new Texture(file)); //TODO Put filname for textures here
			//every texture is 20x20 pixels.
			this.file=file;
			this.isSolid=isSolid;
			setPosition(xPixel, yPixel);
			
		}
	}
	
	class MazeCrawler extends Sprite{
		int row;
		int column;

		
	
		
		public MazeCrawler(int row, int column) {
			super(new Texture("mazecrawler.png"));
			this.setPosition(column*20+400,row*20);
			this.row=row;this.column=column;
		}
		
		public void update() {
			setPosition(column*20+400,row*20);

			///debugging
//			if (Gdx.input.isKeyJustPressed(Input.Keys.A))
//				moveLeft();
//			if (Gdx.input.isKeyJustPressed(Input.Keys.D))
//				moveRight();
//			if (Gdx.input.isKeyJustPressed(Input.Keys.W))
//				moveUp();
//			if (Gdx.input.isKeyJustPressed(Input.Keys.S))
//				moveDown();
		}
		
		public void moveUp() {
			this.setRotation(0);
			if(row != 29 && !currentTileMap[row+1][column].isSolid)
				row++;
		}
		public void moveDown() {
			this.setRotation(180);
			if(row != 0 && !currentTileMap[row-1][column].isSolid)
				row--;
		}
		public void moveLeft() {
			this.setRotation(90);
			if(column != 0 && !currentTileMap[row][column-1].isSolid)
				column--;
		}
		public void moveRight() {
			this.setRotation(270);
			if(column != 19 && !currentTileMap[row][column+1].isSolid)
				column++;
		}

	}
}
