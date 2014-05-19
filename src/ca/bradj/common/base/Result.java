package ca.bradj.common.base;

import com.google.common.base.Preconditions;

public class Result {

	public static Result failure(String reason) {
		return new Result(reason, false);
	}

	public static Result success() {
		return new Result("", true);
	}

	private final String reason;
	private final boolean isSuccess;

	private Result(String reason, boolean isSuccess) {
		this.reason = Preconditions.checkNotNull(reason);
		this.isSuccess = isSuccess;
	}

	public String getReason() {
		return reason;
	}

	public boolean isFailure() {
		return !isSuccess;
	}

}
