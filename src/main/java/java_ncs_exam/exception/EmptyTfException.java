package java_ncs_exam.exception;

public class EmptyTfException extends RuntimeException {
	public EmptyTfException() {
		super("형식이 맞지 않습니다. ");
	}
	
	public EmptyTfException(Throwable cause) {
		super("형식이 맞지 않습니다.", cause);
	}

	public EmptyTfException(String message) {
		super(message);
	}
}
