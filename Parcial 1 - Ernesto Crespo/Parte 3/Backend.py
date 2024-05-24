# Aplicacion Web Backend - Ernesto Crespo

# Importamos librerias
import json
from http.server import BaseHTTPRequestHandler, HTTPServer
import concurrent.futures
import time

# Función que realiza un cálculo intensivo
def intensive_calculation(x):
    time.sleep(2)  # Simula un cálculo intensivo con una espera de 2 segundos
    return x * x

# Define la clse que manejara solicitudes HTTP
class RequestHandler(BaseHTTPRequestHandler):
    #Configura los encabezados de solicitudes HTTP
    def _set_headers(self):
        self.send_response(200)  # Enviar respuesta HTTP 200 (OK)
        self.send_header('Content-type', 'application/json')  # Establecer el tipo de contenido como JSON
        self.end_headers()  # Finalizar los encabezados

    #Maneja solicitudes POST
    def do_POST(self):
        content_length = int(self.headers['Content-Length'])  # Obtener la longitud del contenido de la solicitud
        post_data = self.rfile.read(content_length)  # Leer los datos de la solicitud
        data = json.loads(post_data.decode('utf-8'))  # Decodificar los datos JSON
        numbers = data.get('numbers', [])  # Obtener la lista de números de los datos

        # Utilizamos un ThreadPoolExecutor para manejar la concurrencia
        with concurrent.futures.ThreadPoolExecutor(max_workers=4) as executor:
            results = list(executor.map(intensive_calculation, numbers))  # Ejecutar cálculos concurrentemente

        # Enviar la respuesta
        self._set_headers()  # Establecer los encabezados de la respuesta
        self.wfile.write(json.dumps(results).encode('utf-8'))  # Enviar los resultados como JSON

# Define la funcion que se ejecutara en el servidor
def run(server_class=HTTPServer, handler_class=RequestHandler, port=8080):
    server_address = ('', port)  # Configurar la dirección del servidor
    httpd = server_class(server_address, handler_class)  # Crear una instancia del servidor HTTP
    print(f'Starting httpd server on port {port}')  # Imprimir mensaje de inicio del servidor
    httpd.serve_forever()  # Iniciar el bucle de servicio del servidor

#Comprueba que el script se ejecute como el programa principal
if __name__ == "__main__":
    run()  # Ejecutar la función run para iniciar el servidor
