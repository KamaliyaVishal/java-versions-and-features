package java1_4.nio;

import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.io.IOException;

/**
 * Java 1.4 NIO (New Input/Output) Example Demonstrates non-blocking I/O and buffers
 */
public class NIOExample
{
	public static void main(String[] args)
	{
		System.out.println("=== Java 1.4 NIO (New Input/Output) ===\n");

		System.out.println("1. Buffer Operations");
		System.out.println("-------------------");
		ByteBuffer buffer = ByteBuffer.allocate(1024);
		System.out.println("Buffer capacity: " + buffer.capacity());
		System.out.println("Buffer position: " + buffer.position());
		System.out.println("Buffer limit: " + buffer.limit());

		// Write to buffer
		buffer.put("Hello NIO".getBytes());
		System.out.println("After put - position: " + buffer.position());

		// Flip for reading
		buffer.flip();
		System.out.println("After flip - position: " + buffer.position() + ", limit: " + buffer.limit());

		// Read from buffer
		byte[] data = new byte[buffer.remaining()];
		buffer.get(data);
		System.out.println("Read data: " + new String(data));

		System.out.println("\n2. FileChannel Example");
		System.out.println("---------------------");
		System.out.println("""
				try {
				    // Write to file
				    FileChannel writeChannel = FileChannel.open(
				        Paths.get("output.txt"),
				        StandardOpenOption.CREATE,
				        StandardOpenOption.WRITE
				    );
				    
				    ByteBuffer writeBuffer = ByteBuffer.wrap("Hello NIO".getBytes());
				    writeChannel.write(writeBuffer);
				    writeChannel.close();
				    
				    // Read from file
				    FileChannel readChannel = FileChannel.open(
				        Paths.get("output.txt"),
				        StandardOpenOption.READ
				    );
				    
				    ByteBuffer readBuffer = ByteBuffer.allocate(1024);
				    readChannel.read(readBuffer);
				    readBuffer.flip();
				    
				    byte[] data = new byte[readBuffer.remaining()];
				    readBuffer.get(data);
				    System.out.println(new String(data));
				    
				    readChannel.close();
				    
				} catch (IOException e) {
				    e.printStackTrace();
				}
				""");

		System.out.println("\n3. Selector for Non-blocking I/O");
		System.out.println("-------------------------------");
		System.out.println("""
				Selector selector = Selector.open();
				ServerSocketChannel serverChannel = ServerSocketChannel.open();
				serverChannel.configureBlocking(false);
				serverChannel.bind(new InetSocketAddress(8080));
				serverChannel.register(selector, SelectionKey.OP_ACCEPT);
				
				while (true) {
				    selector.select();
				    Set<SelectionKey> keys = selector.selectedKeys();
				    for (SelectionKey key : keys) {
				        if (key.isAcceptable()) {
				            // Accept connection
				        } else if (key.isReadable()) {
				            // Read data
				        }
				    }
				}
				""");

		System.out.println("\nKey Features:");
		System.out.println("- Non-blocking I/O");
		System.out.println("- Buffers for efficient I/O");
		System.out.println("- Channels for I/O operations");
		System.out.println("- Selectors for multiplexing");
		System.out.println("- Better performance than traditional I/O");

		System.out.println("\nNIO Components:");
		System.out.println("- Buffer: Container for data");
		System.out.println("- Channel: Connection to I/O source");
		System.out.println("- Selector: Multiplexer for channels");
		System.out.println("- SelectionKey: Represents channel registration");

		System.out.println("\nBenefits:");
		System.out.println("- Better performance");
		System.out.println("- Non-blocking operations");
		System.out.println("- Scalable I/O");
		System.out.println("- Efficient memory usage");
	}
}

