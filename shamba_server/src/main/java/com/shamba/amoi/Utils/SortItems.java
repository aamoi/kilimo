package com.shamba.amoi.Utils;

import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.lang.Nullable;

//import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties;

/**
 * Created by amoi on 7/22/18.
 */
public class SortItems extends org.springframework.data.domain.Sort.Order {

    public SortItems(@Nullable Sort.Direction direction, String property) {
        super(direction, property);
    }
}
