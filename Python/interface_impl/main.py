import tkinter as tk

# Crear la ventana principal del programa.


if __name__ == "__main__":
    WND_HEIGHT = 600
    WND_WIDTH = 400

    WND_DIM = str(WND_HEIGHT) + "x" + str(WND_WIDTH)

    wnd = tk.Tk()
    wnd.title("CMA")  # Nombre de la ventana
    wnd.geometry(WND_DIM)  # Dimensiones de la ventana

    p1 = tk.Frame(wnd, bg="red", height=WND_HEIGHT-40, width = WND_WIDTH-40)# Superframe
    p1.place(x=20, y=20, width= 240, height=360)
    p2 = tk.Frame(wnd, bg="blue") # Superframe 2
    p2.place(x=280, y=20, width= 300, height=360)
    p3 = tk.Frame(p1, bg = "purple", height = 100, width = 230)
    p3.place(relx = 0.046, rely = 0.046, height = 150, width = 210)
    p4 = tk.Frame(p1, bg = "green", height = 240, width = 230)

    wnd.mainloop()
