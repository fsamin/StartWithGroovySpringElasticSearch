package com.github.fsamin.utils

import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable

/**
 * Created by fsamin on 01/02/15.
 */
class Utils {

    static Pageable getSingle() {
        return new PageRequest(0, 1);
    }

}
