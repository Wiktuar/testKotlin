package ru.wiktuar.testkotlin.entities.survey

import com.fasterxml.jackson.annotation.JsonIgnore
import jakarta.persistence.*

@Entity
@Table(name = "surveys")
class   Survey {
    @Id
    @SequenceGenerator(name = "survey_local_seq", sequenceName = "surveys_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "survey_local_seq")
    var id: Int = 0

    @Column(name = "topic")
    var topic: String? = null

//    @OneToMany(mappedBy = "survey", fetch = FetchType.EAGER, cascade = [CascadeType.ALL], orphanRemoval = true)
//    val opinions: List<Opinion> = mutableListOf()

    @OneToMany(fetch = FetchType.EAGER, cascade = [CascadeType.ALL], orphanRemoval = true)
    @JoinColumn(name = "survey_id")
    val opinions: List<Opinion> = mutableListOf()
}