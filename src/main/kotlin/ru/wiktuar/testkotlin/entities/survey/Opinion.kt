package ru.wiktuar.testkotlin.entities.survey

import com.fasterxml.jackson.annotation.JsonIgnore
import jakarta.persistence.*

@Entity
@Table(name = "opinions")
class Opinion {
    @Id
    @SequenceGenerator(name = "opinion_local_seq", sequenceName = "opinions_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "opinion_local_seq")
    var id: Int = 0

    @Column(name = "text")
    var text: String? = null

//    @ManyToMany(fetch = FetchType.EAGER, cascade = [CascadeType.PERSIST])
//    @JoinTable(
//        name = "op_voters",
//        joinColumns = [JoinColumn(name = "op_id")],
//        inverseJoinColumns = [JoinColumn(name = "voter_id")]
//    )
//    var likes: List<Voter> = mutableListOf()

    @Column(name = "survey_id")
    var survey: Int = 0

    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, cascade = [CascadeType.ALL])
    @JoinColumn(name = "opinion_id")
    var likes: List<Like> = listOf()
}