package ru.wiktuar.testkotlin.entities

import jakarta.persistence.*

@Entity
@Table(name = "courses")
class Course {

    @Id
    @SequenceGenerator(name = "course_local_seq", sequenceName = "courses_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "course_local_seq")
    var id: Int = 0

//    @Column(name = "header")
    private var header: String? = null

    @Column(name = "text")
    private var text: String? = null

    @ManyToOne(fetch = FetchType.LAZY, cascade = [CascadeType.DETACH])
    @JoinColumn(name = "department_id")
    private var department: Department? = null

    fun setHeader(header: String){
        this.header = header
    }

    fun getHeader(): String? {
        return this.header
    }

    fun setText(text: String){
        this.text = text
    }

    fun getText(): String? {
        return this.text
    }

    fun setDepartment(department: Department){
        department.also { this.department = it }
    }

    fun getDepartment(): Department?{
        return this.department
    }
}