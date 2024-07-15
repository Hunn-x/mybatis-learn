package com.xunn;

import com.xunn.Employee;

public interface EmployeeMapper {
    /*增加*/
    void insertEmployee(Employee employee);
    /*刪除*/
    void deleteEmployee(Integer empId);

    /*根据id修改名字*/
    void updateEmployee(Integer empId,String empName);

    /*查找*/
    Employee selectEmployee(Integer id);
}

