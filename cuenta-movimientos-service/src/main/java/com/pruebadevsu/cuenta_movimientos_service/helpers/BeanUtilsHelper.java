package com.pruebadevsu.cuenta_movimientos_service.helpers;


import lombok.extern.slf4j.Slf4j;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.stereotype.Component;

import java.lang.reflect.InvocationTargetException;

@Component
@Slf4j
public class BeanUtilsHelper {
    public void copyNonNullProperties(Object dest, Object orig) {
        try {
            String[] propertyNames = BeanUtils.describe(orig).keySet().toArray(new String[0]);

            for (String propertyName : propertyNames) {
                if (propertyName.equals("class")) {
                    continue;
                }

                String value = BeanUtils.getProperty(orig, propertyName);

                if (value != null) {
                    BeanUtils.setProperty(dest, propertyName, value);
                }
            }
        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            log.error("error combin object helper {}", e.getMessage());
            e.printStackTrace();
        }
    }
}