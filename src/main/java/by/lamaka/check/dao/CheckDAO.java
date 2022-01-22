package by.lamaka.check.dao;

import by.lamaka.check.entity.Check;

import java.io.IOException;


public interface CheckDAO {
    void saveCheck(Check check) throws IOException;
}
