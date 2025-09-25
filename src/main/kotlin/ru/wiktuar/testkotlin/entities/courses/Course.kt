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

    @OneToMany(fetch = FetchType.EAGER, cascade = [CascadeType.ALL])
    @JoinColumn(name = "course_id")
    val uploads: MutableList<Upload> = ArrayList()

    @ManyToOne(fetch = FetchType.LAZY, cascade = [CascadeType.DETACH])
    @JoinColumn(name = "department_id")
    private var department: Department? = null

    fun setDepartment(department: Department){
        department.also { this.department = it }
    }

    fun addUploads(upload: Upload){
        this.uploads.add(upload)
    }

}