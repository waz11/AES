import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

public class BlocksList {
	private List<Block> blocks;
	private int size;

	//	constructors:
	public BlocksList() {
		this.blocks = new ArrayList<>();
		this.size = 0;
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

	
	//	getters & setters:
	public Block getBlock(int i) {
		if(i>=size) return null;
		return blocks.get(i);
	}
	public int getSize() {
		return this.size;
	}


	// opertaions:
	public void addBlock(Block block) {
		this.blocks.add(block);
		size++;
	}
	public BlocksList xor(Block other) {
		BlocksList result = new BlocksList();
		int i=0;
		System.out.println(this.size);
		for(Block block:this.blocks) {
			System.out.println(i++);
			result.addBlock(block.xor(other));
		}
		return result;
	}
	public void swapeIndexes() {
		for(Block block:this.blocks) 
			block.swapeIndexex();
	}
	public boolean isEquals(BlocksList other) {
		for(int i=0; i<size; i++) {
			if(!(this.getBlock(i).isEquals(other.getBlock(i))))
				return false;
		}
		return true;
	}

	
	//	prints:
	public void print() {
		int i=1;
		for(Block block:this.blocks) {
			System.out.println("Block no."+i+":");
			block.print();
			i++;
		}
	}
	public void printBytes() {
		for(int i=0; i<this.size; i++) {
			byte[] b = this.getBlock(i).blockToBytes();
			Bases.printBytes(b);
		}
	}
	public void printBytesDetails() {
		for(Block b:this.blocks)
			b.printBytesDetails();
	}


	//files:
	public void writeToBytesFile(String path) {
		createResultsDirectory();
		clearFile(path);
		for(int i=0; i<this.size; i++) {
			byte[] b = this.getBlock(i).blockToBytes();
			writeByteToFile(path, b);
		}
	}
	private void clearFile(String path) {
		try (FileOutputStream fos = new FileOutputStream(path)) {
			fos.write("".getBytes());
			fos.close();
		} catch (Exception e) {}
	}
	private void writeByteToFile(String path, byte[] data) {
		//save byte array to file
		try (FileOutputStream fos = new FileOutputStream(path, true)) {
			fos.write(data);
			fos.close();
		} catch (Exception e) {}
	}
	private void createResultsDirectory() {
		String PATH = "./files/";
		String directoryName = PATH.concat("results");

		File directory = new File(directoryName);
		if (! directory.exists()){
			directory.mkdir();
		}
	}

}
