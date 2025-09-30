package ru.wiktuar.testkotlin.entities.courses

import jakarta.persistence.*
import ru.wiktuar.testkotlin.entities.Department

@Entity
@Table(name = "courses")
class Course {

    @Id
    @SequenceGenerator(name = "course_local_seq", sequenceName = "courses_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "course_local_seq")
    var id: Int = 0

    @Column(name = "header")
    var header: String? = null

    @Column(name = "text")
    var text: String? = null

    @OneToMany(fetch = FetchType.EAGER, cascade = [CascadeType.ALL], orphanRemoval = true)
    @JoinColumn(name = "course_id")
    var uploads: MutableList<Upload> = ArrayList()

    @ManyToOne(fetch = FetchType.LAZY, cascade = [CascadeType.DETACH])
    @JoinColumn(name = "department_id")
    var department: Department? = null

    fun addUploads(upload: Upload){
        this.uploads.add(upload)
    }

//    fun removeUploads(fileNames: Array<String>){
//        this.uploads.removeIf { fileNames.contains(it.name) }
//    }

    override fun toString(): String {
        return this.department?.id.toString()
    }

}