package com.mongo.example.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class City {

  private Long id;
  private Long provinceId;
  private String cityName;
  private String description;
}
