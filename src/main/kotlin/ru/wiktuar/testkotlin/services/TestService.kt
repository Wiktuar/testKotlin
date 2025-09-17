package ru.wiktuar.testkotlin.services

import jakarta.transaction.Transactional
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import ru.wiktuar.testkotlin.entities.test.Test
import ru.wiktuar.testkotlin.repository.TestRepo

@Service
class TestService {

    @Autowired
    private lateinit var testRepo: TestRepo;

    //  метод сохранения нового теста
    @Transactional
    fun saveTest(test: Test) {
        testRepo.save(test)
    }

    //  метод получения списка тестов одного отдела
    fun getTestByDepartmentId(id: Int): MutableList<Test> {
        return testRepo.getTestByDepartmentId(id)
    }

    //  метод удаления теста по его ID
    fun deleteTestById(id: Int) {
        testRepo.deleteById(id)
    }

    fun getTest(id: Int): Test {
        return testRepo.getTest(id)
    }

    fun getTestWithResults(id: Int): Test {
        return testRepo.getTestWithResults(id)
    }

    fun getTestHeaderByTestId(testId: Int): String{
        return testRepo.getTestHeaderByTestId(testId)
    }
}