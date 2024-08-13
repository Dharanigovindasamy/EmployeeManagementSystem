package com.ideas2it.ems.department.controller;

import java.util.Set;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ideas2it.ems.Dto.DepartmentDto;
import com.ideas2it.ems.Dto.EmployeeDto;
import com.ideas2it.ems.department.service.DepartmentService;
import com.ideas2it.ems.model.Department;

/**
* <p>The class used for user input and output for department details.
 * Adding the departments, displaying, updating and deleting the details by department id
 * </p>
* @author Dharani G
*
*/

@RestController
@RequestMapping ("/api/v1/departments")
public class DepartmentController {

    private static final Logger logger = LogManager.getLogger();

    @Autowired
    private DepartmentService departmentService;


    /**
    * <p>Add department details into the department object
     * @param departmentDto - {@link DepartmentDto}
     * @return DepartmentDto when added the department data
    * if added - successfully added, </p>
    */
    @PostMapping
    public ResponseEntity<DepartmentDto> addDepartment(@RequestBody DepartmentDto departmentDto) {departmentDto = departmentService.addDepartment(departmentDto);
        logger.info("Department details added successfully {}", departmentDto.getDepartmentName());
        return new ResponseEntity<>(departmentDto, HttpStatus.CREATED);
    }

    /**
    * <p>Display all department details
     * if no department found, shows warn , otherwise
     * @return Set<DepartmentDto> - set of department data
     * </p>
     *
    */
    @GetMapping
    public ResponseEntity<Set<DepartmentDto>> displayDepartments() {
        Set<DepartmentDto> departmentDtos  = departmentService.getAllDepartments();
        if (departmentDtos.isEmpty()) {
            logger.warn("Departments are not found");
            return new ResponseEntity<>(departmentDtos, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(departmentDtos, HttpStatus.OK);
    }

    /** 
    * <p>Display Department details By department Id
     * @param departmentId - departmentId of the department
    * if department is null, shows warn.
    * if department present by given id, it displays @return DepartmentDto </p>
    */
    @GetMapping("{id}")
    public ResponseEntity<DepartmentDto> displayDepartmentById(@PathVariable("id") int departmentId) {
        DepartmentDto departmentDto =  departmentService.getDepartmentById(departmentId);
        if (null == departmentDto) {
            logger.warn("Department is not found under this department id {}", departmentId);
            return new ResponseEntity<>(departmentDto, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(departmentDto, HttpStatus.OK);
    }

    /**
     * <p>Update department by checking with department id
     * @param departmentId - departmentId of the department
     * @param departmentDto - {@link DepartmentDto}
     * If department not found shows warn otherwise it updates
     * @return departmentDto - departmentDto of the user </p>
    */
    @PutMapping("{id}")
    public ResponseEntity<DepartmentDto> updateDepartment(@PathVariable("id") int departmentId, @RequestBody DepartmentDto departmentDto) {
        departmentDto =  departmentService.updateDepartment(departmentId, departmentDto);
        if (null == departmentDto) {
            logger.warn("No department found under this department Id {}" , departmentId);
            return new ResponseEntity<>(departmentDto, HttpStatus.NOT_FOUND);
        }
        logger.info("Department updated successfully {}", departmentId);
        return new ResponseEntity<>(departmentDto, HttpStatus.OK);
        
    }

    /**
    * <p>Delete department by giving department id
     * @param departmentId -departmentId of the department
     *  if department does not exist, shows warn
     *  otherwise it delete as soft delete @return DepartmentDto
    * If error occurs while deleting, shows exception otherwise, it deletes</p>
    */
    @DeleteMapping("/{id}")
    public ResponseEntity<DepartmentDto> deleteDepartment(@PathVariable("id") int departmentId) {
        DepartmentDto departmentDto = departmentService.getDepartmentById(departmentId);
        if (null == departmentDto) {
            logger.warn("Department does not exist {}", departmentId);
            return new ResponseEntity<>(departmentDto, HttpStatus.NOT_FOUND);
        }
        logger.info("Department deleted successfully {}", departmentId);
        return new ResponseEntity<>(departmentDto, HttpStatus.OK);
    }

    /**
     *<p>
     *    Display employees by providing departmentId
     * @param departmentId - Id of the department
     * @return Set<EmployeeDto> - set of employees under the department id
     * </p>
     */
    @GetMapping("/employees/{id}")
    public  ResponseEntity<Set<EmployeeDto>> getEmployeesByDepartmentId(@PathVariable("id") int departmentId) {
         Set<EmployeeDto> employeeDtos = departmentService.getEmployeesByDepartmentId(departmentId);
        if(null == employeeDtos) {
            logger.warn("Department not exists under this department id {}", departmentId);
            return new ResponseEntity<>(employeeDtos, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(employeeDtos, HttpStatus.OK);
    }
}
