package ru.wiktuar.testkotlin.services

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import ru.wiktuar.testkotlin.entities.Course
import ru.wiktuar.testkotlin.repository.CourseRepo

@Service
class CourseService {

    @Autowired
    private lateinit var courseRepo: CourseRepo;

    // метод сохранения круса
    fun saveCourse(course: Course) {
        courseRepo.save(course)
    }

    //  метод получения курса по его ID
    fun getCourseById(id: Int): Course {
        return courseRepo.findById(id).get()
    }

    //  метод получения курса по его ID вместе с отделом
    fun getCourseWithDepartment(id: Int): Course {
        return courseRepo.getCourseWithDepartment(id)
    }

    //  метод удаления курса по его id
    fun deleteCourseById(id: Int) {
        courseRepo.deleteById(id)
    }
}