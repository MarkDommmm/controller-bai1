package hh.entity;

import hh.model.Roles;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Employee {
    private Long id;
    private String name;
    private String  email;
    private String department;
    private List<String> roles;



}
