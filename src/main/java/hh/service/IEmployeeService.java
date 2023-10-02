package hh.service;

import hh.exception.CustomsException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IEmployeeService<T, E> {
    List<T> getEmployees();

    void save(T t) throws CustomsException;

    void update(T t, E e) throws CustomsException;

    T findById(E e) throws CustomsException;

    void remove(E e) throws CustomsException;


}
