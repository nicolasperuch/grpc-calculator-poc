package server;

import com.github.nicolasperuch.CalculatorRequest;
import com.github.nicolasperuch.CalculatorResponse;
import com.github.nicolasperuch.CalculatorServiceGrpc;
import io.grpc.stub.StreamObserver;

public class CalculatorService extends CalculatorServiceGrpc.CalculatorServiceImplBase {

	@Override
	public void addition(CalculatorRequest request, StreamObserver<CalculatorResponse> responseObserver) {
		double result = executeMathOperation(request, MathOperation.ADDITION);
		CalculatorResponse response = CalculatorResponse
											.newBuilder()
											.setResponse(result)
											.build();
		responseObserver.onNext(response);
		responseObserver.onCompleted();
	}

	@Override
	public void subtraction(CalculatorRequest request, StreamObserver<CalculatorResponse> responseObserver) {
		//Is this empty method annoying?
	}

	@Override
	public void multiplication(CalculatorRequest request, StreamObserver<CalculatorResponse> responseObserver) {
		// send me a pr with this empty method is annoying you
	}

	@Override
	public void division(CalculatorRequest request, StreamObserver<CalculatorResponse> responseObserver) {
		// I'm w8
	}


	public double executeMathOperation(CalculatorRequest request, MathOperation operation) {
		double firstNumber = request.getFirstNumber();
		double secondNumber = request.getSecondNumber();

		switch (operation) {
			case ADDITION:
				return firstNumber + secondNumber;
			default:
				throw new IllegalArgumentException("Unexpected Operation");
		}

	}
}
