import tkinter as tk

from database_impl.SchoolData import SchoolData

# Crear la ventana principal del programa.
GLOBAL_SCHOOL_DATA = SchoolData()

if __name__ == "__main__":

    WND_HEIGHT = 600
    WND_WIDTH = 800

    WND_DIM = str(WND_WIDTH) + "x" + str(WND_HEIGHT)

    WND = tk.Tk()
    WND.title("CMA")  # Nombre de la ventana
    WND.geometry(WND_DIM)  # Dimensiones de la ventana

    p1 = tk.Frame(WND, highlightbackground = "black", highlightthickness = 4, width = WND_WIDTH/2 - 60, height = WND_HEIGHT - 40)# Superframe P1
    p1.place(x=20, y=20)
    
    WND.update() # Esta funcion hace que las funciones winfo_width() y winfo_height regresen los valores correctos. (si no se usa, regresan 1)

    p2 = tk.Frame(WND, highlightbackground = "black", highlightthickness = 4, width = WND_WIDTH/2 + 10, height = WND_HEIGHT - 40) # Superframe P2
    p2.place(x = p1.winfo_width() + 30, y = 20)

    WND.update()

    p3 = tk.Frame(p1, highlightbackground = "white", highlightthickness = 4, height = p1.winfo_height()/3 + 40, width = p1.winfo_width() - 20) # Frame superior dentro de P1
    p3.place(x = 5, y = 10)
    p4 = tk.Frame(p1, highlightbackground = "white", highlightthickness = 4, height = p1.winfo_height()- (p1.winfo_height()/3 + 80), width = p1.winfo_width() - 20)
    p4.place(x = 5, y = p2.winfo_height()/3 + 60)

    p5 = tk.Frame(p2, highlightbackground = "white", highlightthickness = 4, height = p2.winfo_height()/3 + 40, width = p2.winfo_width() - 20) # Frame superior dentro de P1
    p5.place(x = 5, y = 10)
    p6 = tk.Frame(p2, highlightbackground = "white", highlightthickness = 4, height = p2.winfo_height()- (p2.winfo_height()/3 + 80), width = p2.winfo_width() - 20)
    p6.place(x = 5, y = p2.winfo_height()/3 + 60)

    WND.mainloop()
