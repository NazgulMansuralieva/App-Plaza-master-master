package peaksoft.service.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import peaksoft.model.Application;
import peaksoft.service.ModelService;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class ApplicationService implements ModelService<Application> {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void save(Application application) {

        application.setGenre(application.getGenre());
        application.setGenreName(application.getGenre().getName());
        application.setCreateDate(LocalDate.now());
        entityManager.persist(application);
    }

    @Override
    public Application findById(Long id) {
        Application application = findById(id);
        return application;
    }

    @Override
    public List<Application> findAll() {
        List<Application> applications = entityManager.createQuery("from Application ").getResultList();
        return applications;
    }

    @Override
    public void update(Long id, Application application) {
        Application oldApplication = findById(id);
        oldApplication.setName(oldApplication.getName());
        oldApplication.setDeveloper(oldApplication.getDeveloper());
        oldApplication.setDescription(oldApplication.getDescription());
        oldApplication.setAppStatus(oldApplication.getAppStatus());
        oldApplication.setGenreName(oldApplication.getGenre().getName());
        oldApplication.setVersion(oldApplication.getVersion());
        entityManager.merge(oldApplication);
    }

    @Override
    public void deleteById(Long id) {
        entityManager.remove(findById(id));
    }
}
