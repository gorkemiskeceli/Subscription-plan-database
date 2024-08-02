package org.example.Plans;

public class PlansService {
    private final PlansRepository plansRepository;

    public PlansService(PlansRepository plansRepository) {
        this.plansRepository=plansRepository;
    }

    public void createPlansTable(){
        PlansRepository.createPlansTable();
    }

    public void save(Plans plans){
        plansRepository.save(plans);
    }

}
