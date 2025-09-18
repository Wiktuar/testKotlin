package ru.wiktuar.testkotlin.services.resultServices

import org.apache.poi.ss.usermodel.*
import org.apache.poi.ss.util.CellRangeAddress
import org.apache.poi.xssf.usermodel.XSSFCellStyle
import kotlin.reflect.full.memberProperties

//  метод получения основных стилей таблицы
fun createDefaultStyle(workbook: Workbook): XSSFCellStyle {
    val cellStyle: XSSFCellStyle = workbook.createCellStyle() as XSSFCellStyle
    cellStyle.alignment = HorizontalAlignment.CENTER
    cellStyle.verticalAlignment = VerticalAlignment.CENTER
    cellStyle.wrapText = true
    cellStyle.borderTop = BorderStyle.THIN
    cellStyle.topBorderColor = IndexedColors.BLACK.getIndex()
    cellStyle.borderBottom = BorderStyle.THIN
    cellStyle.bottomBorderColor = IndexedColors.BLACK.getIndex()
    cellStyle.borderLeft = BorderStyle.THIN
    cellStyle.leftBorderColor = IndexedColors.BLACK.getIndex()
    cellStyle.borderRight = BorderStyle.THIN
    cellStyle.rightBorderColor = IndexedColors.BLACK.getIndex()
    cellStyle.setFont(createFont(workbook))
    return cellStyle
}

//  метод установки шрифта
fun createFont(workbook: Workbook, fontName: String = "Times New Roman", fontSize: Short = 14, bold: Boolean = false, italic: Boolean = false): Font{
    val font = workbook.createFont()
    font.fontName = fontName
    font.fontHeightInPoints = fontSize
    font.bold = bold
    font.italic = italic
    return font
}

//  метод заполнения таблицы значениями из dto
fun getPropertyValues(myObject: Any, row: Row, cellStyle:  CellStyle){
    var colNum = 0
    val properties = myObject::class.memberProperties.toList()
    for (property in properties) {
        val value = property.getter.call(myObject) // Получаем значение свойства
        val cell = row.createCell(colNum++)
        if(value is String) cell.setCellValue(value as String)
        else if (value is Int) cell.setCellValue(value.toDouble())
        cell.cellStyle = cellStyle
    }
    row.height = (row.height*3).toShort()
}



//  метод получения строки заголовка
fun createHeaderRow(sheet: Sheet, list: List<String>, cellStyle: CellStyle, coefficient: Int){
    val headerRow = sheet.createRow(1)
    for (n in 0 until list.size){
        val cell = headerRow.createCell(n)
        cell.setCellValue(list[n])
        cell.cellStyle = cellStyle
    }
    headerRow.height = (headerRow.height*coefficient).toShort()
}

//  метод создания объединенной области ячеек
fun mergeCells(sheet: Sheet, size: Int, value: String, cellStyle: CellStyle,
               fRow: Int, lRow: Int, fCol: Int, lCol: Int, rowNumber: Int){
    val row = sheet.createRow(rowNumber)
    for(n in 0 until size){
       row.createCell(n)
    }
    val cell = row.getCell(0)
    cell.setCellValue(value)
    cell.cellStyle = cellStyle
    row.height = (row.height*3).toShort()

    val mergedRegion = CellRangeAddress(fRow, lRow, fCol, lCol)
    sheet.addMergedRegion(mergedRegion)
}

fun createRowTotalVoters(sheet: Sheet, values: Array<String>, cellStyle: CellStyle, fRow: Int, lRow: Int, fCol: Int, lCol: Int, rowNumber: Int){
    val row = sheet.createRow(rowNumber)
    for(n in 0 until values.size){
        row.createCell(n)
        val cell = row.getCell(n)
        cell.setCellValue(values[n])
        cell.cellStyle = cellStyle
    }
    row.height = (row.height*3).toShort()
    val mergedRegion = CellRangeAddress(fRow, lRow, fCol, lCol)
    sheet.addMergedRegion(mergedRegion)
}