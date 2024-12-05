package dominio;

public class Candidato {
    private String nombre;
    private int votos;
    public Candidato(String nombre) {
        this.nombre = nombre;
        votos = 0;
    }
    public int incrementarVotos(){
        votos++;
        return votos;
    }
    public int resetearVotos(){
        votos = 0;
        return votos;
    }
    public int getVotos(){
        return votos;
    }
    public String getNombre() {
        return nombre;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Nombre: " + nombre + ", Votos: "+ votos + "\n");
        return sb.toString();
    }
}