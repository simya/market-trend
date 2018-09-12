package com.msdeneme.newms.utility;

import java.util.List;
import java.util.Optional;

public class MsDaoUtil {

    /**
     * @param list
     * @return Optional based generic
     * */
    public static <T> Optional<T> findFirst(List<T> list) {
        Optional<List<T>> optionalList = Optional.ofNullable(list);
        if (optionalList.isPresent()) {
            return optionalList.get().stream().findFirst();
        }
        return Optional.empty();
    }
}
