package com.tpvs

class Encuesta {

    var idEncuesta: Int=0

    var pregunta1: String = ""

    var pregunta2: String = ""

    var pregunta3: String = ""

    var pregunta4: String = ""

    var imagen1: String = ""

    var imagen2: String = ""

    var imagen3: String = ""

    var latitud: String = ""

    var longitud: String = ""

    constructor(
        idEncuesta: Int,
        pregunta1: String,
        pregunta2: String,
        pregunta3: String,
        pregunta4: String,
        imagen1: String,
        imagen2: String,
        imagen3: String,
        latitud: String,
        longitud: String
    ) {
        this.idEncuesta = idEncuesta
        this.pregunta1 = pregunta1
        this.pregunta2 = pregunta2
        this.pregunta3 = pregunta3
        this.pregunta4 = pregunta4
        this.imagen1 = imagen1
        this.imagen2 = imagen2
        this.imagen3 = imagen3
        this.latitud = latitud
        this.longitud = longitud
    }

    constructor()


}