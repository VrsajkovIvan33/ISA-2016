package ISAProject.service.impl;

import ISAProject.model.Restaurant;
import ISAProject.model.Tender;
import ISAProject.repository.TenderRepository;
import ISAProject.service.TenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Marko on 2/24/2017.
 */
@Service
@Transactional
public class JpaTenderService implements TenderService{
    @Autowired
    private TenderRepository tenderRepository;

    @Override
    public List<Tender> findAll() {
        return tenderRepository.findAll();
    }

    @Override
    public Tender findOne(Long id) { return tenderRepository.findOne(id); }

    @Override
    public Tender save(Tender tender) { return tenderRepository.save(tender); }

    @Override
    public void delete(Long id) { tenderRepository.delete(id); }

    @Override
    public List<Tender> findByTRestaurantAndTStatus(Restaurant tRestaurant, String tStatus) {
        return tenderRepository.findByTRestaurantAndTStatus(tRestaurant, tStatus);
    }

    @Override
    public List<Tender> findByTRestaurant(Restaurant tRestaurant) {
        return tenderRepository.findByTRestaurant(tRestaurant);
    }
}
