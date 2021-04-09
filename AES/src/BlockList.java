import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

public class BlockList {
	private List<Block> blocks;
	private int size;

	//	constructors:
	public BlockList() {
		this.blocks = new ArrayList<>();
		this.size = 0;
	}
	
	public BlockList(BlockList other) {
		this.blocks = new ArrayList<>();
		this.size = 0;
		for(Block block:other.blocks)
			this.blocks.add(new Block(block));
	}
	
	public BlockList(String path) {
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
	public BlockList xor(Block other) {
		BlockList result = new BlockList();
		for(Block block:this.blocks) {
			result.addBlock(block.xor(other));
		}
		return result;
	}
	public void swapeIndexes() {
		for(Block block:this.blocks) 
			block.swapeIndexex();
	}
	public boolean isEquals(BlockList other) {
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
	public void printChars() {
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
		String directoryName = PATH.concat("output");

		File directory = new File(directoryName);
		if (! directory.exists()){
			directory.mkdir();
		}
		
//		String PATH2 = "./files/output/";
//		String directoryName2 = PATH2.concat("break");
//		File directory2 = new File(directoryName2);
//		if (! directory2.exists()){
//			directory2.mkdir();
//		}
//		
//		String PATH3 = "./files/output/";
//		String directoryName3 = PATH3.concat("tests");
//		File directory3 = new File(directoryName3);
//		if (! directory3.exists()){
//			directory3.mkdir();
//		}
	}

}
