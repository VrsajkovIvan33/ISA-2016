package ISAProject.service.impl;

import ISAProject.model.Cook;
import ISAProject.repository.CookRepository;
import ISAProject.service.CookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Nole on 11/26/2016.
 */

@Service
@Transactional
public class JpaCookService implements CookService {

    @Autowired
    private CookRepository cookRepository;

    @Override
    public List<Cook> findAll() {
        return cookRepository.findAll();
    }

    @Override
    public Cook findOne(Long id) {
        return cookRepository.findOne(id);
    }

    @Override
    public Cook save(Cook cook) {
        return cookRepository.save(cook);
    }

    @Override
    public Cook delete(Long id) {
        Cook cook = cookRepository.findOne(id);
        if(cook == null){
            try{
                throw new Exception("Cannot find cook");
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        cookRepository.delete(cook);
        return cook;
    }

    @Override
    public Cook findByEmail(String email) {
        List<Cook> listCook = cookRepository.findByCEmail(email);
        Cook cook = listCook.get(0);
        return cook;
    }
}
