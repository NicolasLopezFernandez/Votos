package dominio;

import java.util.ArrayList;

public class Eleccion {
    private ArrayList<Candidato> candidatos;
    private ArrayList<Papeleta> votos;
    private int ciclo;

    public Eleccion() {
        candidatos = new ArrayList<>();
        votos = new ArrayList<>();
        ciclo = 0;
    }

    public ArrayList<Candidato> getCandidatos() {
        return candidatos;
    }

    public ArrayList<Papeleta> getVotos() {
        return votos;
    }

    public void realizarRecuento(){
        for (Candidato candidato : candidatos) {
            candidato.resetearVotos();
        }
        for (Papeleta voto : votos) {
            Candidato preferido = voto.getPrimeraPreferencia();
            for (Candidato candidato : candidatos) {
                if (candidato.getNombre().equals(preferido.getNombre())) {
                    candidato.incrementarVotos();
                }
            }
        }
    }

    public Candidato comprobarMayoriaAbsoluta(){
        int candidatoPerdedor = 999999999;
        int candidatoGanador = 0;
        int votoTotal = 0;
        Candidato ganador = null;
        Candidato perdedor = null;
        ciclo ++;
        realizarRecuento();
        for (Candidato candidato : candidatos) {
            votoTotal += candidato.getVotos();
            if (candidato.getVotos() > candidatoGanador){
                candidatoGanador = candidato.getVotos();
            }else if (candidato.getVotos() < candidatoPerdedor){
                candidatoPerdedor = candidato.getVotos();
            }
        }
        double porcentajeGanador = (double) candidatoGanador / votoTotal;
        if (porcentajeGanador > 0.5){
            for (Candidato candidato : candidatos) {
                if (candidato.getVotos() == candidatoGanador){
                    ganador = candidato;
                }
            }
            return ganador;
        }else if (porcentajeGanador == 0.5){
            return ganador;
        }else{
            for (Candidato candidato : candidatos) {
                if (candidato.getVotos() == candidatoPerdedor){
                    perdedor = candidato;
                }
            }
            eliminarCandidatoMenosVotos(perdedor);
            realizarRecuento();
            ganador = comprobarMayoriaAbsoluta();
            return ganador;
        }
    }

    public void eliminarCandidatoMenosVotos(Candidato c){
        for (Papeleta papeleta : votos) {
            papeleta.eliminarCandidato(c);
        }
    }

    public boolean validarPapeletas() {
        for (Papeleta papeleta : votos) {
            if (papeleta.getPreferencias().size() != candidatos.size()) {
                return false;
            }
        }
        return true;
    }

    public String toString(){
        StringBuilder sb = new StringBuilder();
        for (Candidato candidato : candidatos) {
            sb.append(candidato.toString());
        }
        return sb.toString();
    }
}