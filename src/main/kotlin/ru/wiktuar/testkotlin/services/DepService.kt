package ru.wiktuar.testkotlin.services

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import ru.wiktuar.testkotlin.entities.Department
import ru.wiktuar.testkotlin.repository.DepRepo

@Service
class DepService {

    @Autowired
    private lateinit var depRepo: DepRepo

    open fun getAllDepartments(): List<Department>{
        return depRepo.findAll().toList();
    }

    open fun save(department: Department){
        depRepo.save<Department>(department);
    }

    //  метод получения банковского отдела по его ID
    fun getDepartmentById(id: Int): Department {
        return depRepo.findById(id).get();
    }

    //  метод получения отдела с его курсами
    fun getDepartmentWithCourses(id: Int): Department {
        return depRepo.getDepartmentWithCourses(id)
    }

    open fun deleteDepartment(id: Int){
        depRepo.deleteById(id);
    }
}