def archivoDeDrones():
    f = file('dronesDataBase.sqlite', 'w')
    print("Creado sqlite")
    f.close()

if __name__ == "__main__":
    archivoDeDrones()