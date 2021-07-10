package Arbol;

import java.util.LinkedList;

public class Arbol {

    private Nodo Raiz;
    private int n;

    public Arbol() {
        Raiz = null;
        n = 0;
    }

    public int cantNodos() {
        return n;
    }

    public void add(int x) {
        if (Raiz == null) {
            Raiz = new Nodo(x);
        } else {   //Caso general
            Nodo Ant = null;
            Nodo p = Raiz;

            while (p != null) {
                Ant = p;
                if (x < p.getData()) {
                    p = p.getHI();
                } else if (x > p.getData()) {
                    p = p.getHD();
                } else {
                    return;  //Salir. x ya está en el árbol.
                }
            }

            Nodo N = new Nodo(x);

            if (x < Ant.getData()) {
                Ant.setHI(N);
            } else {
                Ant.setHD(N);
            }
        }

        n++;
    }

    private boolean hoja(Nodo T) {   //Para usar en la RECURSION.
        return (T != null && T.cantHijos() == 0);
    }

    public void inorden() {
        System.out.print("Inorden : ");

        if (Raiz == null) {
            System.out.println("(Arbol vacío)");
        } else {
            inorden(Raiz, "");
            System.out.println();
        }
    }

    private void inorden(Nodo T, String coma) {
        if (T != null) {
            inorden(T.getHI(), ", ");
            System.out.print(", " + T.getData());
            inorden(T.getHD(), ", ");
        }
    }

    public void niveles() {
        niveles("");
    }

    public void niveles(String nombreVar) {
        if (nombreVar != null && nombreVar.length() > 0) {
            nombreVar = " del Arbol " + nombreVar;
        } else {
            nombreVar = "";
        }

        System.out.print("Niveles" + nombreVar + ": ");

        if (Raiz == null) {
            System.out.println("(Arbol vacío)");
        } else {
            niveles(Raiz);
        }
    }

//---------- Métodos auxiliares para mostrar el árbol por niveles --------------
    private void niveles(Nodo T) {   //Pre: T no es null.
        LinkedList<Nodo> colaNodos = new LinkedList<>();
        LinkedList<Integer> colaNivel = new LinkedList<>();

        int nivelActual = 0;
        String coma = "";

        colaNodos.addLast(T);
        colaNivel.addLast(1);

        do {
            Nodo p = colaNodos.pop();       //Sacar un nodo p de la cola
            int nivelP = colaNivel.pop();   //Sacar el nivel donde se encuentra p.

            if (nivelP != nivelActual) { //Se está cambiando de nivel
                System.out.println();
                System.out.print("  Nivel " + nivelP + ": ");
                nivelActual = nivelP;
                coma = "";
            }

            System.out.print(coma + p.getData());
            coma = ", ";

            addHijos(colaNodos, colaNivel, p, nivelP);
        } while (colaNodos.size() > 0);

        System.out.println();
    }

    private void addHijos(LinkedList<Nodo> colaNodos, LinkedList<Integer> colaNivel, Nodo p, int nivelP) {
        for (int i = 1; i <= Nodo.M; i++) {  //Insertar a la cola de nodos los hijos no-nulos de p
            Nodo hijo = p.getHijo(i);

            if (hijo != null) {
                colaNodos.addLast(hijo);
                colaNivel.addLast(nivelP + 1);
            }
        }
    }

    public int cantGajos() {
        return cantGajos(Raiz) - 1;
    }

    private int cantGajos(Nodo T) {
        int cant = 0;
        if (T == null) {
            return 0;
        }
        if (hoja(T)) {
            return 1;
        }
        int c = 1;
        c = c + cantGajos(T.HI);
        c = c + cantGajos(T.HD);
        return c;
    }

    public void descendientes(int x) {
        descendientes(Raiz, x, false);
    }

    private void descendientes(Nodo T, int x, boolean b) {
        if (T == null) {
            return;
        }
        if (b == true) {
            System.out.print(T.getData() + " - ");
        }
        if (T.getData() == x) {
            b = true;
        }
        descendientes(T.getHI(), x, b);
        descendientes(T.HD, x, b);
    }

    public void podar() {
        Raiz = podar(Raiz);
    }

    private Nodo podar(Nodo T) {
        if (T == null) {
            return null;
        }
        if (hoja(T)) {
            return null;
        }
        T.setHI(podar(T.HI));
        T.setHD(podar(T.HD));
        return T;
    }

    public boolean isPadre(int p, int h) {
        return isPadre(Raiz, p, h);
    }

    private boolean isPadre(Nodo T, int p, int h) {
        if (T == null) {
            return false;
        }
        if (hoja(T)) {
            return false;
        }
        if (T.getData() == p) {
            if (T.getHI() != null && T.getHI().getData() == h) {
                return true;
            }

            if (T.HD != null && T.HD.getData() == h) {
                return true;
            }
        }
        boolean izq = isPadre(T.HI, p, h);
        if (izq == true) {
            return true;
        }
        return isPadre(T.getHD(), p, h);
    }
    public boolean isHoja(int x){
       return true; 
    }
    private boolean isHoja(Nodo T, int x){
        return true;
    }

}
