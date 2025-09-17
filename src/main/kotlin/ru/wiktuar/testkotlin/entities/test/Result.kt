package ru.wiktuar.testkotlin.entities.test

import jakarta.persistence.*

@Entity
@Table(name = "results")
class Result {
    @Id
    @SequenceGenerator(name = "result_local_seq", sequenceName = "results_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "result_local_seq")
    var id: Int = 0

    @Column(name = "first_name")
    var firstName: String? = null

    @Column(name = "surname")
    var surname: String? = null

    @Column(name = "last_name")
    var lastName: String? = null

    @Column(name = "result")
    var result: String? = null

    @Column(name = "message")
    var message: String? = null

    @Column(name = "time_stamp")
    var timeStamp: String? = null

    @Column(name = "test_id")
    var testId: Int = 0
}