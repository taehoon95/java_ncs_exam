package java_ncs_exam.exception;

@SuppressWarnings("serial")
public class InvaildCheckException extends RuntimeException {
	public InvaildCheckException() {
		super("공란 존재");
	}
	
	public InvaildCheckException(Throwable cause) {
		super("공란 존재", cause);
	}

	public InvaildCheckException(String message) {
		super(message);
	}
}
