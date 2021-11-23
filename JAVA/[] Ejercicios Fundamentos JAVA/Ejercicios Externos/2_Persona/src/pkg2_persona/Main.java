/*
Haz una clase llamada Persona que siga las siguientes condiciones:

Sus atributos son: nombre, edad, DNI, sexo (H hombre, M mujer), peso y altura. No queremos que se accedan directamente a ellos. Piensa que modificador de acceso es el más adecuado, también su tipo. Si quieres añadir algún atributo puedes hacerlo.
Por defecto, todos los atributos menos el DNI serán valores por defecto según su tipo (0 números, cadena vacía para String, etc.). Sexo sera hombre por defecto, usa una constante para ello.
Se implantaran varios constructores:
Un constructor por defecto.
Un constructor con el nombre, edad y sexo, el resto por defecto.
Un constructor con todos los atributos como parámetro.
Los métodos que se implementaran son:
calcularIMC(): calculara si la persona esta en su peso ideal (peso en kg/(altura^2  en m)), si esta fórmula devuelve un valor menor que 20, la función devuelve un -1, si devuelve un número entre 20 y 25 (incluidos), significa que esta por debajo de su peso ideal la función devuelve un 0  y si devuelve un valor mayor que 25 significa que tiene sobrepeso, la función devuelve un 1. Te recomiendo que uses constantes para devolver estos valores.
esMayorDeEdad(): indica si es mayor de edad, devuelve un booleano.
comprobarSexo(char sexo): comprueba que el sexo introducido es correcto. Si no es correcto, sera H. No sera visible al exterior.
toString(): devuelve toda la información del objeto.
generaDNI(): genera un número aleatorio de 8 cifras, genera a partir de este su número su letra correspondiente. Este método sera invocado cuando se construya el objeto. Puedes dividir el método para que te sea más fácil. No será visible al exterior.
Métodos set de cada parámetro, excepto de DNI.


Ahora, crea una clase ejecutable que haga lo siguiente:

Pide por teclado el nombre, la edad, sexo, peso y altura.
Crea 3 objetos de la clase anterior, el primer objeto obtendrá las anteriores variables pedidas por teclado, el segundo objeto obtendrá todos los anteriores menos el peso y la altura y el último por defecto, para este último utiliza los métodos set para darle a los atributos un valor.
Para cada objeto, deberá comprobar si esta en su peso ideal, tiene sobrepeso o por debajo de su peso ideal con un mensaje.
Indicar para cada objeto si es mayor de edad.
Por último, mostrar la información de cada objeto.
Puedes usar métodos en la clase ejecutable, para que os sea mas fácil.
*/
package pkg2_persona;

import javax.swing.JOptionPane;

public class Main {

    public static void main(String[] args) {
        
        //Pedir por teclado
        String nombre = JOptionPane.showInputDialog("Introduce el nombre");
        
        String texto = JOptionPane.showInputDialog("Introduce la edad");
        int edad = Integer.parseInt(texto);
        
        texto = JOptionPane.showInputDialog("Introduce el sexo");
        char sexo = texto.charAt(0);
 
        texto = JOptionPane.showInputDialog("Introduce el peso");
        double peso = Double.parseDouble(texto);
 
        texto = JOptionPane.showInputDialog("Introduce la altura");
        double altura = Double.parseDouble(texto);
        
        //Objetos
        Persona persona1 = new Persona(nombre,edad, sexo, peso, altura);
        Persona persona2 = new Persona(nombre, edad, sexo);
        Persona persona3 = new Persona();
        
        persona2.setPeso(90.5);
        persona2.setAltura(1.80);
        
        persona3.setNombre("Laura");
        persona3.setEdad(30);
        persona3.setSexo('M');
        persona3.setPeso(60);
        persona3.setAltura(1.60);
 
        
        //Desplegar Objetos
        System.out.println("Persona1");
        MostrarPeso(persona1);
        MuestraMayorDeEdad(persona1);
        System.out.println(persona1.toString());
        
        System.out.println("\nPersona2");
        MostrarPeso(persona2);
        MuestraMayorDeEdad(persona2);
        System.out.println(persona2.toString());
        
        System.out.println("\nPersona3");
        MostrarPeso(persona3);
        MuestraMayorDeEdad(persona3);
        System.out.println(persona3.toString());
        

    }
    
    public static void MuestraMayorDeEdad(Persona p) {
        if (p.esMayorDeEdad()) 
            System.out.println("La persona es mayor de edad");
        else 
            System.out.println("La persona no es mayor de edad");
    }
    
    public static void MostrarPeso(Persona p){
        switch(p.calcularIMC()){
            case -1: System.out.println("La persona esta bajo de peso");     break;
            case 0:  System.out.println("La persona esta en su peso ideal"); break;
            case 1:  System.out.println("La persona esta alto de peso");     break;
        }
    } 
}
