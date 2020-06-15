package server;

import com.github.nicolasperuch.CalculatorRequest;
import com.github.nicolasperuch.CalculatorResponse;
import com.github.nicolasperuch.CalculatorServiceGrpc;
import io.grpc.Status;
import io.grpc.stub.StreamObserver;

public class CalculatorService extends CalculatorServiceGrpc.CalculatorServiceImplBase {

	private static final String INDIVISIBLE_VALUE = "Impossible to divide by this value";

	@Override
	public void addition(CalculatorRequest request, StreamObserver<CalculatorResponse> responseObserver) {
		double result = executeMathOperation(request, MathOperation.ADDITION);
		CalculatorResponse response = buildResponse(result);
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
		if(request.getSecondNumber() > 0) {
			double result = executeMathOperation(request, MathOperation.DIVISION);
			CalculatorResponse response = buildResponse(result);
			responseObserver.onNext(response);
			responseObserver.onCompleted();
		} else {
			responseObserver
					.onError(Status.INVALID_ARGUMENT
								.withDescription(INDIVISIBLE_VALUE)
								.asRuntimeException());
		}
	}

	private double executeMathOperation(CalculatorRequest request, MathOperation operation) {
		double firstNumber = request.getFirstNumber();
		double secondNumber = request.getSecondNumber();

		switch (operation) {
			case ADDITION:
				return firstNumber + secondNumber;
			case DIVISION:
				return firstNumber / secondNumber;
			default:
				throw new IllegalArgumentException("Unexpected Operation");
		}
	}

	private CalculatorResponse buildResponse (double result) {
		return CalculatorResponse
				.newBuilder()
				.setResponse(result)
				.build();
	}
}
