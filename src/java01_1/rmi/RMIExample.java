package java1_1.rmi;

/**
 * Java 1.1 RMI (Remote Method Invocation) Example Demonstrates distributed computing concepts
 * NOTE: This is a conceptual example. Actual RMI requires registry and network setup.
 */
public class RMIExample
{
	public static void main(String[] args)
	{
		System.out.println("=== Java 1.1 RMI (Remote Method Invocation) ===\n");

		System.out.println("RMI enables distributed computing by allowing objects");
		System.out.println("to invoke methods on remote objects.\n");

		System.out.println("Example code structure:");
		System.out.println("""
				// Remote Interface
				import java.rmi.Remote;
				import java.rmi.RemoteException;
				
				public interface Calculator extends Remote {
				    int add(int a, int b) throws RemoteException;
				    int subtract(int a, int b) throws RemoteException;
				}
				
				// Remote Implementation
				import java.rmi.server.UnicastRemoteObject;
				
				public class CalculatorImpl extends UnicastRemoteObject 
				        implements Calculator {
				    public CalculatorImpl() throws RemoteException {
				        super();
				    }
				    
				    public int add(int a, int b) throws RemoteException {
				        return a + b;
				    }
				    
				    public int subtract(int a, int b) throws RemoteException {
				        return a - b;
				    }
				}
				
				// Server
				import java.rmi.registry.LocateRegistry;
				import java.rmi.registry.Registry;
				
				public class Server {
				    public static void main(String[] args) {
				        try {
				            Registry registry = LocateRegistry.createRegistry(1099);
				            Calculator calc = new CalculatorImpl();
				            registry.rebind("Calculator", calc);
				            System.out.println("Server ready");
				        } catch (Exception e) {
				            e.printStackTrace();
				        }
				    }
				}
				
				// Client
				import java.rmi.registry.LocateRegistry;
				import java.rmi.registry.Registry;
				
				public class Client {
				    public static void main(String[] args) {
				        try {
				            Registry registry = LocateRegistry.getRegistry("localhost", 1099);
				            Calculator calc = (Calculator) registry.lookup("Calculator");
				            int result = calc.add(5, 3);
				            System.out.println("Result: " + result);
				        } catch (Exception e) {
				            e.printStackTrace();
				        }
				    }
				}
				""");

		System.out.println("\nKey Features:");
		System.out.println("- Remote method invocation");
		System.out.println("- Distributed computing");
		System.out.println("- Object serialization");
		System.out.println("- Registry for service lookup");
		System.out.println("- Network transparency");

		System.out.println("\nRMI Components:");
		System.out.println("- Remote Interface: Defines remote methods");
		System.out.println("- Remote Implementation: Implements remote interface");
		System.out.println("- Registry: Service lookup mechanism");
		System.out.println("- Stub/Skeleton: Communication layer");

		System.out.println("\nBenefits:");
		System.out.println("- Distributed computing");
		System.out.println("- Object-oriented remote calls");
		System.out.println("- Automatic serialization");
		System.out.println("- Type safety");

		System.out.println("\nNote: This example is conceptual.");
		System.out.println("Actual implementation requires RMI registry and network setup.");
	}
}

