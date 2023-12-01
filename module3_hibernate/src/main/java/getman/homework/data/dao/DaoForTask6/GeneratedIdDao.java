package getman.homework.data.dao.DaoForTask6;

import getman.homework.data.pojo.forTask6.*;

public interface GeneratedIdDao {
    String saveAndGetUUID(IdUuid uuid);
    String saveAndGetIdGuid(IdGUID idGUID);
    long saveAndGetHiLo(IdHiLo idHiLo);
    long saveAndGetIdentity(IdIdentity idIdentity);
    long saveAndGetIdSequence(IdSequence idSequence);
}
