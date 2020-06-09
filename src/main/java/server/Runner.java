package server;

import io.grpc.Server;
import io.grpc.ServerBuilder;

import java.io.IOException;

public class Runner {
	private static final int HASCKER_PORT = 1337;
	public static void main(String[] args) throws IOException, InterruptedException {
		System.out.println("Server is going up");
		Server server = ServerBuilder
								.forPort(HASCKER_PORT)
								.addService(new CalculatorService())
								.build();
		server.start();
		System.out.println("Server is up and running");
		Runtime.getRuntime().addShutdownHook(new Thread(() -> {
			System.out.println("Received Shutdown Request");
			server.shutdown();
			System.out.println("Server went down successfully");
		}));
		server.awaitTermination();
	}
}
