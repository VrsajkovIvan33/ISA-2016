package ISAProject.service.impl;

import ISAProject.model.Bartender;
import ISAProject.repository.BartenderRepository;
import ISAProject.service.BartenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Nole on 11/26/2016.
 */

@Service
@Transactional
public class JpaBartenderService implements BartenderService{

    @Autowired
    private BartenderRepository bartenderRepository;

    @Override
    public List<Bartender> findAll() {
        return bartenderRepository.findAll();
    }

    @Override
    public Bartender findOne(Long id) {
        return bartenderRepository.findOne(id);
    }

    @Override
    public Bartender save(Bartender bartender) {
        return bartenderRepository.save(bartender);
    }

    @Override
    public Bartender delete(Long id) {
        Bartender bartender = bartenderRepository.findOne(id);
        if(bartender == null){
            try{
                throw  new Exception("Bartender cannot be found");
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        bartenderRepository.delete(bartender);
        return bartender;
    }
}
