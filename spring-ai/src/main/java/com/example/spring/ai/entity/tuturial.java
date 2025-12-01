package com.example.spring.ai.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Data
@Getter
@Setter
public class tuturial {
    String title;
    String summary;
   String date;
  List <String> version;

}
