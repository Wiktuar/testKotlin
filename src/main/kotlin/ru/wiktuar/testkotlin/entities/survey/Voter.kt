package ru.wiktuar.testkotlin.entities.survey

import jakarta.persistence.*

@Entity
@Table(name = "voters")
class Voter() {
    @Id
    @SequenceGenerator(name = "voter_local_seq", sequenceName = "voters_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "voter_local_seq")
    var id: Int = 0

    @Column(name = "login")
    var login: String? = null

    @Column(name = "poll_id")
    var pollId: Int = 0

    constructor(login: String): this(){
        this.login = login
    }
}