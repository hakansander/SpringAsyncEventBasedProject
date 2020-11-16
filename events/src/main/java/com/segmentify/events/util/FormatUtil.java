package com.segmentify.events.util;

import com.segmentify.events.model.enums.PageViewConstants;

public class FormatUtil {

    public static boolean isPageViewCategoryValid(String pageViewConstant) {
        if(pageViewConstant == null) {
            return false;
        }

        for (PageViewConstants c : PageViewConstants.values()) {
            if (c.getResultCode().equals(pageViewConstant)) {
                return true;
            }
        }

        return false;

    }
}
