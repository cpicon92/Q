package in.waffl.q;

public class PromiseInterruptedException extends RuntimeException {
	private static final long serialVersionUID = -3572806859720161681L;

	public PromiseInterruptedException() {
	}

	public PromiseInterruptedException(String msg) {
		super(msg);
	}

	public PromiseInterruptedException(Throwable parent) {
		super(parent);
	}

	public PromiseInterruptedException(String msg, Throwable parent) {
		super(msg, parent);
	}


}
