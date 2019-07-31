package com.example.ISAums.util;

import com.example.ISAums.model.enumeration.ReportType;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.lang.Nullable;

import java.util.*;

import static com.example.ISAums.util.Constants.DAY_IN_MILLISECONDS;

public class UtilService {

    public static void copyNonNullProperties(Object src, Object target, @Nullable String... ignoreProperties) {
        if (ignoreProperties != null) {
            BeanUtils.copyProperties(src, target, ignoreProperties);
            return;
        }

        BeanUtils.copyProperties(src, target, getNullPropertyNames(src));
    }

    private  static String[] getNullPropertyNames (Object source) {
        final BeanWrapper src = new BeanWrapperImpl(source);
        java.beans.PropertyDescriptor[] pds = src.getPropertyDescriptors();

        Set<String> emptyNames = new HashSet<String>();
        Arrays.stream(pds).forEach(pd -> {
            Object srcValue = src.getPropertyValue(pd.getName());
            if (srcValue == null) {
                emptyNames.add(pd.getName());
            }
        });

        String[] result = new String[emptyNames.size()];
        return emptyNames.toArray(result);
    }

    public static HashMap<String, Date> getStartEndDateFromReportType(ReportType reportType) {
        HashMap<String, Date> dates = new HashMap<String ,Date>();
        Date start = null;
        Date end = null;
        Date now = new Date();

        if (reportType == reportType.DAILY) {
            start = new Date(now.getTime() - (now.getTime() % DAY_IN_MILLISECONDS));
            end = new Date((now.getTime() + DAY_IN_MILLISECONDS) - ((now.getTime() + DAY_IN_MILLISECONDS) % DAY_IN_MILLISECONDS));
        }

        if (reportType == reportType.WEEKLY) {
            end = new Date((now.getTime() + DAY_IN_MILLISECONDS) - ((now.getTime() + DAY_IN_MILLISECONDS) % DAY_IN_MILLISECONDS));
            start = new Date(now.getTime() -  (DAY_IN_MILLISECONDS * 7));
        }

        if (reportType == reportType.MONTHLY) {
            end = new Date((now.getTime() + DAY_IN_MILLISECONDS) - ((now.getTime() + DAY_IN_MILLISECONDS) % DAY_IN_MILLISECONDS));
            start = new Date(now.getTime() -  (DAY_IN_MILLISECONDS * 30));
        }

        dates.put("start", start);
        dates.put("end", end);
        return dates;
    }
}
