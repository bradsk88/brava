package ca.bradj.common.base;

import com.google.common.base.Optional;

public class Failable<T> {

	private final boolean success;
	private final Optional<T> object;
	private final Optional<String> message;

	private Failable(Optional<T> object, Optional<String> message, boolean success) {
		this.success = success;
		this.object = object;
		this.message = message;
	}

	public boolean isSuccess() {
		return success;
	}

	public T get() {
		if (object.isPresent()) {
			return object.get();
		}
		throw new CannotGetFromFailException();
	}

	public static <T> Failable<T> fail(String string) {
		return new Failable<>(Optional.<T> absent(), Optional.of(string), false);
	}

	public static <T> Failable<T> ofSuccess(T next) {
		return new Failable<>(Optional.of(next), Optional.<String> absent(), true);
	}

	public String getReason() {
		return message.get();
	}

	@Override
	public String toString() {
		if (isSuccess()) {
			return "Failable.success: " + object;
		}
		return "Failable.fail: " + message;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((message == null) ? 0 : message.hashCode());
		result = prime * result + ((object == null) ? 0 : object.hashCode());
		result = prime * result + (success ? 1231 : 1237);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		Failable<?> other = (Failable<?>) obj;
		if (message == null) {
			if (other.message != null) {
				return false;
			}
		} else if (!message.equals(other.message)) {
			return false;
		}
		if (object == null) {
			if (other.object != null) {
				return false;
			}
		} else if (!object.equals(other.object)) {
			return false;
		}
		if (success != other.success) {
			return false;
		}
		return true;
	}

	public boolean isFailure() {
		return !isSuccess();
	}

}
