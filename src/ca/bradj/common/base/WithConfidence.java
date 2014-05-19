package ca.bradj.common.base;

import java.util.Collection;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;

public class WithConfidence<T> {

	private final T item;
	private final Confidence confidence;

	private WithConfidence(Confidence confidence, T item) {
		this.confidence = confidence;
		this.item = item;
	}

	public static <T> WithConfidence<T> low(T item) {
		return new WithConfidence<>(Confidence.LOW, item);
	}

	public static <T> WithConfidence<T> medium(T item) {
		return new WithConfidence<T>(Confidence.MEDIUM, item);
	}

	public static <T> WithConfidence<T> high(T item) {
		return new WithConfidence<T>(Confidence.HIGH, item);
	}

	public Confidence getConfidence() {
		return confidence;
	}

	public static <T> WithConfidence<T> getHighest(WithConfidence<T> wc1, WithConfidence<T> wc2) {
		if (wc1.getConfidence() == wc2.getConfidence()) {
			return wc1;
		}
		if (wc1.getConfidence() == Confidence.HIGH) {
			return wc1;
		}
		if (wc2.getConfidence() == Confidence.HIGH) {
			return wc2;
		}
		if (wc1.getConfidence() == Confidence.MEDIUM) {
			return wc1;
		}
		return wc2;
	}

	public T getItem() {
		return item;
	}

	public static <T> Collection<T> stripConfidence(Collection<WithConfidence<T>> items) {
		Collection<T> list = Lists.newArrayList();
		for (WithConfidence<T> i : ImmutableList.copyOf(items)) {
			list.add(i.getItem());
		}
		return list;
	}

	@Override
	public String toString() {
		return confidence + " confidence: " + item;
	}

}
