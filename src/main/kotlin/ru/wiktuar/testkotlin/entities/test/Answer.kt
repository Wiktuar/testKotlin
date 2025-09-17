package ru.wiktuar.testkotlin.entities.test

import jakarta.persistence.*

@Entity
@Table(name = "answers")
class Answer {
    @Id
    @SequenceGenerator(name = "answer_local_seq", sequenceName = "answers_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "answer_local_seq")
    var id: Int = 0

    @Column(name = "text")
    var text: String? = null

    @Column(name = "correct")
    var correct = false
}