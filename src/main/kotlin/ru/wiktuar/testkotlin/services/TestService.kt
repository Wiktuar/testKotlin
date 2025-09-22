package ru.wiktuar.testkotlin.services

import jakarta.transaction.Transactional
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import ru.wiktuar.testkotlin.dto.TestDTO
import ru.wiktuar.testkotlin.entities.test.Result
import ru.wiktuar.testkotlin.entities.test.Test
import ru.wiktuar.testkotlin.repository.ResultRepo
import ru.wiktuar.testkotlin.repository.TestRepo
import ru.wiktuar.testkotlin.services.resultServices.ResultService

@Service
class TestService {

    @Autowired
    private lateinit var testRepo: TestRepo;

    @Autowired
    lateinit var depService: DepService

    @Autowired
    lateinit var resultRepo: ResultRepo

    //  метод сохранения нового теста
    @Transactional
    open fun saveTest(testDTO: TestDTO) {
        val department = depService.getDepartmentById(testDTO.depId);
        val test: Test = testDTO.test
        if (test.id != 0) {
            test.results = resultRepo.getResultsByTestId(test.id)
        }
        test.department = department;
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