# Semaforo - Ernesto Crespo

# importamos librerias
import threading
import time

#Creamos el  Recurso compartido
shared_resource = []

#Creamos el semáforo que permite un máximo de 2 hilos a la vez
sem = threading.Semaphore(2)

#Creamos la funcion para Acceder al Recurso Compartido
def access_shared_resource(thread_id):
    print(f'Thread {thread_id} intentando acceder al recurso compartido...')
    with sem:
        print(f'Thread {thread_id} ha accedido al recurso compartido.')
        shared_resource.append(thread_id)
        time.sleep(2)  # Simula el tiempo de uso del recurso
        print(f'Thread {thread_id} ha liberado el recurso compartido.')
        shared_resource.remove(thread_id)

# Crear y lanzar múltiples hilos
threads = []
for i in range(5):
    thread = threading.Thread(target=access_shared_resource, args=(i,))
    threads.append(thread)
    thread.start()

# Esperar a que todos los hilos terminen
for thread in threads:
    thread.join()

#Imprimir que todos los hilos terminarno
print('Todos los hilos han terminado.')

