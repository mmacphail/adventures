package com.macphail.adventures.business.persistence.boundary;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.time.LocalDate;

@Converter(autoApply = true)
public class LocalDateAttributeConverter implements AttributeConverter<LocalDate, java.sql.Date> {

    @Override
    public java.sql.Date convertToDatabaseColumn(LocalDate localDate) {
        return localDate == null ? null : java.sql.Date.valueOf(localDate);
    }

    @Override
    public LocalDate convertToEntityAttribute(java.sql.Date sqlDate) {
        return sqlDate == null ? null : sqlDate.toLocalDate();
    }
}
