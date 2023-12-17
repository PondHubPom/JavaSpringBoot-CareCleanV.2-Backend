package com.Hoime.CareClean.model.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.ReadOnlyProperty;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

@Document(indexName = "id-locker")
@Getter
@Setter
public class LockerIdEntity {
    @Id
    @ReadOnlyProperty
    private String id;

    @Field(type = FieldType.Text, name = "idLocker")
    private String idLocker;

    @Field(type = FieldType.Text, name = "idRecord")
    private String idRecord;

    public LockerIdEntity() {}
    public LockerIdEntity(String idRecord) {
        this.idRecord = idRecord;
    }
}