package java1_3.jndi;

/**
 * Java 1.3 JNDI (Java Naming and Directory Interface) Example Demonstrates naming and directory services
 * NOTE: This is a conceptual example. Actual JNDI requires naming service setup.
 */
public class JNDIExample
{
	public static void main(String[] args)
	{
		System.out.println("=== Java 1.3 JNDI (Java Naming and Directory Interface) ===\n");

		System.out.println("JNDI provides a standard API for accessing naming and directory services.\n");

		System.out.println("Example code structure:");
		System.out.println("""
				import javax.naming.*;
				import javax.naming.directory.*;
				
				public class JNDIExample {
				    public static void main(String[] args) {
				        try {
				            // 1. Create initial context
				            Context ctx = new InitialContext();
				            
				            // 2. Lookup object
				            Object obj = ctx.lookup("java:comp/env/jdbc/MyDB");
				            
				            // 3. Bind object
				            MyObject myObj = new MyObject();
				            ctx.bind("myObject", myObj);
				            
				            // 4. Directory operations
				            DirContext dirCtx = (DirContext) ctx;
				            Attributes attrs = dirCtx.getAttributes("cn=John,ou=People");
				            
				        } catch (NamingException e) {
				            e.printStackTrace();
				        }
				    }
				}
				""");

		System.out.println("\nKey Features:");
		System.out.println("- Naming service access");
		System.out.println("- Directory service access");
		System.out.println("- LDAP support");
		System.out.println("- DNS support");
		System.out.println("- File system naming");

		System.out.println("\nUse Cases:");
		System.out.println("- Enterprise applications");
		System.out.println("- J2EE/JEE applications");
		System.out.println("- LDAP directory access");
		System.out.println("- Resource lookup");

		System.out.println("\nNote: This example is conceptual.");
		System.out.println("Actual implementation requires naming service setup.");
	}
}

