package ru.wiktuar.testkotlin.entities.test

import jakarta.persistence.*

@Entity @Table(name = "questions")
class Question {
    @Id
    @SequenceGenerator(name = "question_local_seq", sequenceName = "questions_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "question_local_seq")
    val id: Int = 0

    @Column(name = "text")
    var text: String? = null

    @OneToMany(fetch = FetchType.EAGER, cascade = [CascadeType.ALL], orphanRemoval = true)
    @JoinColumn(name = "ques_id") //  коллекция должна быть инициализирована и поле для внешнего ключа должно быть null
    val answers: MutableList<Answer?> = ArrayList()
}