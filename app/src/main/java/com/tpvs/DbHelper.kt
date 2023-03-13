package com.tpvs

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

var DB_NAME = "TpvsEncuesta"

class DbHelper(contexto: Context) : SQLiteOpenHelper(contexto, DB_NAME, null, 1) {


    override fun onCreate(db: SQLiteDatabase?) {
        val createTable = "CREATE TABLE " +
                "Encuesta (idEncuesta Integer PRIMARY KEY AUTOINCREMENT, pregunta1 Varchar(2),pregunta2 Varchar(2),pregunta3 Varchar(2),pregunta4 Varchar(2),imagen1 varchar(120),imagen2 varchar(120),imagen3 varchar(120), latitud varchar(100), longitud varchar(100))"
        db?.execSQL(createTable)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        TODO("Not yet implemented")
    }

    fun saveEncuesta(encuesta: Encuesta): String {
        val db = this.writableDatabase
        var contenedor = ContentValues()

        contenedor.put("pregunta1", encuesta.pregunta1)
        contenedor.put("pregunta2", encuesta.pregunta2)
        contenedor.put("pregunta3", encuesta.pregunta3)
        contenedor.put("pregunta4", encuesta.pregunta4)
        contenedor.put("imagen1", encuesta.imagen1)
        contenedor.put("imagen2", encuesta.imagen2)
        contenedor.put("imagen3", encuesta.imagen3)
        contenedor.put("latitud", encuesta.latitud)
        contenedor.put("longitud", encuesta.longitud)

        var resultado = db.insert("Encuesta", null, contenedor)
        if (resultado == -1.toLong()) {
            return "Falla en la base de datos"
        } else {
            return "Encuesta insertada correctamente"
        }
    }
//    fun readEncuesta():MutableList<Encuesta>{
//        var lista:MutableList<Encuesta> = ArrayList()
//        val db =  this.readableDatabase
//        val sql = "SELECT ID , DIRECCION , FECHA FROM Encuesta "
//        val resultado = db.rawQuery(sql,null)
//        if(resultado.moveToFirst()){
//            do {
//                var encuesta= Encuesta()
//                encuesta.idEncuesta = resultado.getString(resultado.getColumnIndex())
//            }
//        }
//    }
}