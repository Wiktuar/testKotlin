package ru.wiktuar.testkotlin.controllers.rest

import jakarta.servlet.http.HttpServletResponse
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile
import java.io.File
import java.io.IOException
import java.nio.file.Files
import java.nio.file.Paths


@RestController
class FileController {

    @Value("\${upload.pathW}")
    lateinit var writePath: String

    @PostMapping("/upload")
    fun getFile(@RequestParam("file") file: MultipartFile): ResponseEntity<String> {
        val destFile: File = File(writePath + file.originalFilename)
        val imagePath = String.format("{\"location\": \"%s\" }", "/upload/" + file.originalFilename)

        try {
            file.transferTo(destFile.toPath())
        } catch (e: IOException) {
            println(e)
        }
        return ResponseEntity(imagePath, HttpStatus.OK)
    }

    @GetMapping("/download/{filename}")
    fun downloadFile(@PathVariable("filename") fileName: String, response: HttpServletResponse): String {
        val file = Paths.get(writePath + fileName)

        if (Files.exists(file)) {
            response.setHeader("Content-disposition", "attachment; filename=$fileName");
            response.contentType = "APPLICATION/OCTET-STREAM"
            Files.copy(file, response.outputStream);
            response.outputStream.flush()
        }
        return "ok"
    }
}