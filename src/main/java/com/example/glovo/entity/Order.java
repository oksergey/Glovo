package com.example.glovo.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.io.Serializable;
import java.sql.Date;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table("orders")

public class Order implements Serializable {
    @Id
    private int id;
    private Date date;
}


