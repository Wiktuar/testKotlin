package ru.wiktuar.testkotlin.entities.test

import com.fasterxml.jackson.annotation.JsonIgnore
import jakarta.persistence.*
import ru.wiktuar.testkotlin.entities.Department

@Entity
@Table(name = "tests")
class Test {
    @Id
    @SequenceGenerator(name = "test_local_seq", sequenceName = "tests_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "test_local_seq")
    var id: Int = 0

    @Column(name = "header")
    var header: String? = null

    @ManyToOne(fetch = FetchType.LAZY, cascade = [CascadeType.DETACH])
    @JoinColumn(name = "department_id")
    var department: Department? = null

    @OneToMany(fetch = FetchType.LAZY, cascade = [CascadeType.ALL], orphanRemoval = true)
    @JoinColumn(name = "test_id")
    val questions: MutableList<Question?> = ArrayList()

//  эта аннотация необходима, чтобы отработал метод TestJsonController.getTestForUpdate()
//  иначе он не может создать JSON представление объекта Test
    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, cascade = [CascadeType.PERSIST, CascadeType.REMOVE], orphanRemoval = true)
    @JoinColumn(name = "test_id")
    var results: MutableList<Result> = ArrayList()
}