package client;

import com.github.nicolasperuch.CalculatorRequest;
import com.github.nicolasperuch.CalculatorResponse;
import com.github.nicolasperuch.CalculatorServiceGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

public class CalculatorClient {

	private static final String HASCKER_ADDRESS = "localhost";
	private static final int HASCKER_PORT = 1337;

	public static void main(String[] args) {
		System.out.println(" --- gRPC client --- ");
		ManagedChannel channel = ManagedChannelBuilder
										.forAddress(HASCKER_ADDRESS, HASCKER_PORT)
										.usePlaintext()
										.build();

		System.out.println("Creating stub");
		CalculatorServiceGrpc.CalculatorServiceBlockingStub client = CalculatorServiceGrpc.newBlockingStub(channel);

		CalculatorRequest request = CalculatorRequest
										.newBuilder()
										.setFirstNumber(6)
										.setSecondNumber(9)
										.build();
		CalculatorResponse response = client.addition(request);
		System.out.println("Response value: " + response.getResponse());


		request = CalculatorRequest
				.newBuilder()
				.setFirstNumber(10)
				.setSecondNumber(0)
				.build();
		response = client.division(request);
		System.out.println("Response value: " + response.getResponse());

	}
}
