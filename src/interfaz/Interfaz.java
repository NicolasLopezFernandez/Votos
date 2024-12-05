package interfaz;

import dominio.*;
import java.util.Scanner;

public class Interfaz {
    private Scanner sc;
    private Eleccion eleccion;
    int i = 1;

    public Interfaz() {
        sc = new Scanner(System.in);
        eleccion = new Eleccion();
    }

    public String[] instr(){
        String instr = sc.nextLine();
        return instr.split(",");
    }

    public void menu(){
        help();
        while (true) {
            System.out.print("\nEscriba la instrucción: ");
            String[] instr = instr();

            switch (instr[0].toLowerCase()){
                case "help":
                    help();
                    break;
                case "addcandidato":
                    if(instr.length == 2) {
                        addCandidato(instr[1]);
                        i++;
                    }else{
                        System.out.println("Error para el comando (addcandidato). Ej: addcandidato,nombre");
                    }
                    break;
                case "addpapeleta":
                    if (instr.length == 1) {
                        addPapeleta();
                    } else {
                        System.out.println("Error para el comando (addpapeleta).");
                    }
                    break;
                case "elecciones":
                    elecciones();
                    break;
                case "exit":
                    System.out.println("Saliendo...");
                    return;
                default:
                    System.out.println("\nInstruccion no valida\n");
                    help();
                    break;
            }
        }
    }

    public void help(){
        System.out.println("" +
                "help = Muestra este mensaje de ayuda \n" +
                "addCandidato = Ingresa el nombre del candidato \n" +
                "addPapeleta = Ingresa en el orden de preferencia todos los candidatos \n" +
                "elecciones = Se realiza el recuento y se muestra el ganador/es \n" +
                "exit = Finalizar programa");
    }

    public void addCandidato(String nombre){
        Candidato nuevoCandidato = new Candidato(nombre);
        eleccion.getCandidatos().add(nuevoCandidato);
        System.out.println("Candidato añadido: "+ nombre);
    }

    public void addPapeleta(){
        Papeleta nuevaPapeleta = new Papeleta();
        System.out.println("Ingresa el nombre de los candidatos o escribe exit para finalizar la papeleta");
        int k = 1;
        while (k == 1) {
            String respuesta = sc.nextLine();
            if (respuesta.equals("exit")) {
                k = 0;
            } else {
                boolean encontrado = false;
                for (Candidato candidato : eleccion.getCandidatos()) {
                    if (respuesta.equalsIgnoreCase(candidato.getNombre())) {
                        nuevaPapeleta.addPapeleta(candidato);
                        encontrado = true;
                    }
                }
                if (encontrado == false) {
                    System.out.println("Candidato no encontrado. Intenta nuevamente.");
                }
            }
        }
        eleccion.getVotos().add(nuevaPapeleta);
        System.out.println("Papeleta añadida.");
    }

    public void elecciones(){
        boolean validez = eleccion.validarPapeletas();
        if (validez) {
            Candidato ganador = eleccion.comprobarMayoriaAbsoluta();
            if (ganador == null) {
                System.out.println("Ha ocurrido un empate");
            } else {
                System.out.println("El ganador es " + ganador.getNombre() + ", con " + ganador.getVotos() + " votos.");
            }
        } else {
            System.out.println("Hay papeletas invalidas");
        }
    }
}