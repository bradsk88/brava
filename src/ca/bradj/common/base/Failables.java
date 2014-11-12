package ca.bradj.common.base;

import com.google.common.base.Function;

public class Failables {

    public static <S, T> Failable<T> map(Failable<S> in, Function<? super S, T> function) {
        if (in.isSuccess()) {
            return Failable.ofSuccess(function.apply(in.get()));
        }
        return Failable.fail(in.getReason());
    }

}
