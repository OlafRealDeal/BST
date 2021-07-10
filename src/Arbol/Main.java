
package Arbol;

public class Main {
    //3, 7, 9, 8, 3, 5
    private static final int[] datos = {30, 20, 10, 25, 40, 50};
      
    private static void cargarDatos(Arbol T){
        for (int i = 0; i < datos.length; i++) {
            T.add(datos[i]);
        }
    }
    
    
    public static void main(String[] args) {
        Arbol A = new Arbol();
        cargarDatos(A);
        
        A.inorden();
        A.niveles();
        System.out.println(A.isPadre(30, 40)); 
        System.out.println(A.isPadre(40, 30));
        System.out.println(A.isPadre(20, 50));
        System.out.println(A.isPadre(80, 40));
        System.out.println(A.isPadre(20, 25));
        
    }
    
}
