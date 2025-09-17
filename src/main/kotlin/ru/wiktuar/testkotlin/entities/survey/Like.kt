package ru.wiktuar.testkotlin.entities.survey

import jakarta.persistence.*

@Entity
@Table(name = "likes")
class Like() {
    @Id
    @SequenceGenerator(name = "like_local_seq", sequenceName = "likes_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "like_local_seq")
    var id: Int = 0

    @Column(name = "opinion_id")
    var like: Int = 0

    @Column(name = "survey_id")
    var survey: Int = 0

    constructor(like: Int): this() {
        this.like = like
    }

}