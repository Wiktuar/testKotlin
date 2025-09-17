package ru.wiktuar.testkotlin.entities

import com.fasterxml.jackson.annotation.JsonIgnore
import jakarta.persistence.*
import ru.wiktuar.testkotlin.entities.survey.Poll
import ru.wiktuar.testkotlin.entities.test.Test


@Entity
@Table(name = "departments")
class Department {

    //    name = "dep_local_seq" - это мое произвольное имя
    //    sequenceName = "departments_id_seq" - а это имя из базы данных
    @Id
    @SequenceGenerator(name = "dep_local_seq", sequenceName = "departments_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "dep_local_seq")
    var id: Int = 0
    var name: String? = null

    @JsonIgnore
    @OneToMany(mappedBy = "department",
                fetch = FetchType.LAZY,
                cascade = [CascadeType.ALL])
    var courses: List<Course> = mutableListOf()

    @JsonIgnore
    @OneToMany(mappedBy = "department",
                fetch = FetchType.LAZY,
                cascade = [CascadeType.ALL])
    var tests: List<Test> = mutableListOf()

    @JsonIgnore
    @OneToMany(mappedBy = "department",
        fetch = FetchType.LAZY,
        cascade = [CascadeType.ALL])
    var polls: List<Poll> = mutableListOf()
}
