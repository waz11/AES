import java.io.File;
import java.io.FileOutputStream;
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
					String hexString = Bases.byteToHex(b);
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

	public void swapeIndexes() {
		for(Block block:this.blocks) 
			block.swipeIndexex();
	}
	
	
	public void printBytes() {
		for(int i=0; i<this.size; i++) {
			byte[] b = this.getBlock(i).blockToBytes();
			Bases.printBytesArr(b);
		}
	}
	
	
	public void printBytesDetails() {
		for(Block b:this.blocks)
			b.printBytesDetails();
	}

	public void writeToBytesFile(String path) {
		for(int i=0; i<this.size; i++) {
			byte[] b = this.getBlock(i).blockToBytes();
			writeByteToFile(path, b);
		}
	}

	private static void writeByteToFile(String path, byte[] data) {
		//clear previous content from file
		try (FileOutputStream fos = new FileOutputStream(path)) {
			fos.write("".getBytes());
			fos.close();
		} catch (Exception e) {}
		//save byte array to file
		try (FileOutputStream fos = new FileOutputStream(path, true)) {
			fos.write(data);
			fos.close();
		} catch (Exception e) {}
	}


	public boolean isEquals(BlocksList other) {
		for(int i=0; i<size; i++) {
			if(!(this.getBlock(i).isEquals(other.getBlock(i))))
					return false;
		}
		return true;
	}


}
