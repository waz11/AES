import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

public class BlocksList {
	private List<Block> blocks;
	private int size;
	
	public BlocksList() {
		this.blocks = new ArrayList<>();
		this.size = 0;
	}
	
	public Block getBlock(int i) {
		if(i>=size) return null;
		return blocks.get(i);
	}

	public BlocksList(String path) {
		this.blocks = new ArrayList<>();
		this.size = 0;

		File file = new File(path);
		byte[] bytes;
		try {
			bytes = Files.readAllBytes(file.toPath());
			Block block = new Block();
			if (bytes != null) {
				this.addBlock(block);
				for (byte b:bytes) {
					if(block.isFull()) {
						block = new Block();
						this.addBlock(block);
					}
					String hexString = Basics.byteToHex(b);
					block.add(hexString);
				}
			}
		}
		catch (IOException e) {}
	}

	public void addBlock(Block block) {
		this.blocks.add(block);
		size++;
	}

	public int getSize() {
		return this.size;
	}

	public void print() {
		int i=1;
		for(Block block:this.blocks) {
			System.out.println("Block no."+i+":");
			block.print();
			i++;
		}
	}
	
	public void swipeIndexes() {
		for(Block block:this.blocks) 
			block.swipeIndexex();
	}

}
