def archivoDeDrones():
    f = file('dronLoader.json', 'w')
    print("dronLoader.json creado")
    f.write("""{
    "_comment": "Este es un ejemplo de entrada y puede ser editada. El nombre de entrada de drones tiene que ser un int y estar ordenado.",
    "1" :{
      "idUsuario" : "1234",
      "horaSalida" : "1000",
      "horaLlegada" : "1100",
      "ciudadSalida" : "Bilbao",
      "ciudadLlegada" : "Madrid",
      "cargaDescripcion" : "Baterias"
    },
    "2" :{
      "idUsuario" : "1234",
      "horaSalida" : "1000",
      "horaLlegada" : "1100",
      "ciudadSalida" : "Bilbao",
      "ciudadLlegada" : "Madrid",
      "cargaDescripcion" : "Baterias"
    }
    }
    """)
    f.close()

if __name__ == "__main__":
    archivoDeDrones()