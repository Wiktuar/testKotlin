package ru.wiktuar.testkotlin.entities.survey

import com.fasterxml.jackson.annotation.JsonIgnore
import jakarta.persistence.*

@Entity
@Table(name = "surveys")
class Survey {
    @Id
    @SequenceGenerator(name = "vote_local_seq", sequenceName = "votes_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "vote_local_seq")
    var id: Int = 0

    @Column(name = "topic")
    var topic: String? = null

    @OneToMany(fetch = FetchType.EAGER, cascade = [CascadeType.ALL])
    @JoinColumn(name = "survey_id")
    val opinions: List<Opinion> = mutableListOf()
}