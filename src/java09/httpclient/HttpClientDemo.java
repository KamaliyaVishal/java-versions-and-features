package java9.httpclient;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

/**
 * Java 9 HTTP Client API (Incubator) Demonstrates HTTP/2 client usage with synchronous and asynchronous requests
 */
public class HttpClientDemo
{
	public static void main(String[] args)
	{

		// Create HTTP Client
		HttpClient client = HttpClient.newBuilder()
				.connectTimeout(Duration.ofSeconds(10))
				.followRedirects(HttpClient.Redirect.NORMAL)
				.build();

		// Build HTTP Request
		HttpRequest request = HttpRequest.newBuilder()
				.uri(URI.create("https://httpbin.org/get"))
				.timeout(Duration.ofSeconds(5))
				.header("User-Agent", "Java 9 HttpClient")
				.GET()
				.build();

		// Synchronous Request
		System.out.println("=== Synchronous Request ===");
		try
		{
			HttpResponse<String> response = client.send(request,
					HttpResponse.BodyHandlers.ofString());

			System.out.println("Status Code: " + response.statusCode());
			System.out.println("Headers: " + response.headers().map());
			System.out.println("Body: " + response.body().substring(0, Math.min(200, response.body().length())));
		}
		catch (Exception e)
		{
			System.err.println("Error: " + e.getMessage());
		}

		// Asynchronous Request
		System.out.println("\n=== Asynchronous Request ===");
		client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
				.thenApply(HttpResponse::body)
				.thenAccept(body -> {
					System.out.println("Async Response received");
					System.out.println("Body length: " + body.length());
				})
				.exceptionally(ex -> {
					System.err.println("Async Error: " + ex.getMessage());
					return null;
				})
				.join(); // Wait for completion

		// POST Request Example
		System.out.println("\n=== POST Request Example ===");
		String jsonBody = "{\"name\":\"John\",\"age\":30}";

		HttpRequest postRequest = HttpRequest.newBuilder()
				.uri(URI.create("https://httpbin.org/post"))
				.header("Content-Type", "application/json")
				.POST(HttpRequest.BodyPublishers.ofString(jsonBody))
				.build();

		try
		{
			HttpResponse<String> postResponse = client.send(postRequest,
					HttpResponse.BodyHandlers.ofString());

			System.out.println("POST Status Code: " + postResponse.statusCode());
		}
		catch (Exception e)
		{
			System.err.println("POST Error: " + e.getMessage());
		}
	}
}

