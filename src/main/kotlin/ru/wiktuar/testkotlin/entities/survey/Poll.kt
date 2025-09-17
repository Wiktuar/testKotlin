package ru.wiktuar.testkotlin.entities.survey

import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonProperty
import jakarta.persistence.*
import ru.wiktuar.testkotlin.entities.Department
import kotlin.jvm.Transient

@Entity
@Table(name = "polls")
class Poll {
    @Id
    @SequenceGenerator(name = "poll_local_seq", sequenceName = "polls_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "poll_local_seq")
    var id: Int = 0

    @Column(name = "header")
    var header: String? = null

    @JsonProperty("status")
    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    var status: Status = Status.SAVED

    @Transient
    var statusValue = this.status.value

    @Column(name = "personal")
    var personal: Boolean = false

    @ManyToOne(fetch = FetchType.LAZY, cascade = [CascadeType.DETACH])
    @JoinColumn(name = "department_id")
    var department: Department? = null

    @OneToMany(fetch = FetchType.LAZY, cascade = [CascadeType.ALL], orphanRemoval = true)
    @JoinColumn(name = "poll_id")
    val surveys: MutableList<Survey> = ArrayList()

    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, cascade = [CascadeType.ALL], orphanRemoval = true)
    @JoinColumn(name = "poll_id")
    val voters: MutableList<Voter> = ArrayList()

}