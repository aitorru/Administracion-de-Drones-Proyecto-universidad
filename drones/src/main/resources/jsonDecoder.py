#import sys

#sys.path.append("modules")
import org.python.modules._json as jsonn

f = open("rutas.json", "r")

y = jsonn.read(f)

print(y["1"])