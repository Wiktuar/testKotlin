package ru.wiktuar.testkotlin.entities.courses

import jakarta.persistence.*

@Entity
@Table(name = "uploads")
class Upload {
    @Id
    @SequenceGenerator(name = "upload_local_seq", sequenceName = "uploads_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "upload_local_seq")
    var id: Int = 0

    @Column(name = "name")
    var name: String = ""

    @Column(name = "url")
    var url: String = ""

    @Column(name = "course_id")
    var courseId: Int? = null
}