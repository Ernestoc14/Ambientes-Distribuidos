// Fibonacci - Ernesto Crespo

import multiprocessing

def fibonacci(n):
    if n <= 0:
        return 0
    elif n == 1:
        return 1
    else:
        a, b = 0, 1
        for _ in range(2, n+1):
            a, b = b, a + b
        return b

def calculate_fibonacci_range(start, end, result_list):
    for i in range(start, end):
        result_list.append(fibonacci(i))

if __name__ == "__main__":
    N = 20  # Rango de números para calcular la secuencia Fibonacci
    num_processes = 4  # Número de procesos

    # Dividir el rango N en partes iguales para cada proceso
    ranges = [(i * (N // num_processes), (i + 1) * (N // num_processes)) for i in range(num_processes)]
    ranges[-1] = (ranges[-1][0], N)  # Asegurar que el último proceso cubra hasta N

    manager = multiprocessing.Manager()
    results = manager.list()  # Lista compartida entre procesos

    processes = []
    for start, end in ranges:
        p = multiprocessing.Process(target=calculate_fibonacci_range, args=(start, end, results))
        processes.append(p)
        p.start()

    for p in processes:
        p.join()

    # Ordenar los resultados ya que los procesos pueden haberlos agregado en desorden
    sorted_results = sorted(results)

    print(f"Fibonacci sequence for range 0 to {N-1}:")
    for i, value in enumerate(sorted_results):
        print(f"F({i}) = {value}")
