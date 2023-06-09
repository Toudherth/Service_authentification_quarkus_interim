package org.acme.service;

import org.acme.entity.Candidat;
import org.acme.repository.CandidatRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class CandidatService {

    @Inject
    CandidatRepository candidatRepository;

    public List<Candidat> getAll() {
        return (List<Candidat>) candidatRepository.findAll().list();
    }

    public Candidat getById(Long id) {
        return candidatRepository.findByIdOptional(id).orElseThrow(() -> new NotFoundException("Candidat with Id" + id + "not found"));
    }
    /*
    public Candidat getCandidatByLogin(String  login, String password) {
        return candidatRepository.findByLoginAndPassword(login, password);
        }*/

    public void addCandidat(Candidat candidat) {
        candidatRepository.persist(candidat);
    }


    // candidat 
    public Candidat findByNomCandidat(String nom_candidat) {
        return candidatRepository.findByNomCandidat(nom_candidat);
    }


    public Response UpdateCandidat(Long id, Candidat candidat) {
        Candidat entity = candidatRepository.findById(id);
        if (entity == null) {
            return Response.status(Response.Status.NOT_FOUND).build(); // build(): construire la réponse HTTP sans aucun contenu.
        }
        if (candidat.getNom_candidat() != null) {
            entity.setPrenom_candidat(candidat.getPrenom_candidat());
        }
        if (candidat.getPrenom_candidat() != null) {
            entity.setPrenom_candidat(candidat.getPrenom_candidat());
        }
        if (candidat.getDatenaissance() != null) {
            entity.setDatenaissance(candidat.getDatenaissance());
        }
        if (candidat.getNationalite() != null) {
            entity.setNationalite(candidat.getNationalite());
        }
        if (candidat.getNumerotel() != null) {
            entity.setNumerotel(candidat.getNumerotel());
        }
        if (candidat.getCv() != null) {
            entity.setCv(candidat.getCv());
        }
        if (candidat.getLettremotivation() != null) {
            entity.setLettremotivation(candidat.getLettremotivation());
        }
   /*     if (candidat.getLogin() != null) {
            entity.setLogin(candidat.getLogin());
        }

        if (candidat.getPassword() != null) {
            entity.setPassword(candidat.getPassword());
        }*/

        candidatRepository.persist(entity);
        return Response.ok(entity).build();
    }

    public void deleteCandidat(Long id) {
        Optional<Candidat> optionalCandidat = Optional.ofNullable(candidatRepository.findById(id));
        if (optionalCandidat.isPresent()) {
            Candidat candidat = optionalCandidat.get();
            candidatRepository.delete(candidat);
        } else {
            throw new NotFoundException();
        }
    }
}
