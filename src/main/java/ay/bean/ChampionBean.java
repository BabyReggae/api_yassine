package ay.bean;

import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.inject.Inject;

import ay.dao.ChampionDao;
import ay.entity.Champion;

import java.util.List;

@Stateless
@TransactionManagement(TransactionManagementType.BEAN)
public class ChampionBean {

    @Inject
    private ChampionDao championDao;

    // private List<Champion> champions = championDao.getChampions();
    
    public List<Champion> getChampions() {
        return championDao.getChampions();
    }

    public boolean addChampion(Champion champion) {
        return championDao.addChampion(champion);
    }

    public boolean editChampion(Long id, Champion champion) {
        champion.setid(id);
        return championDao.editChampion(id , champion);
    }

    public boolean deleteChampion(Long id) {
        return championDao.deleteChampion(id);
    }
}