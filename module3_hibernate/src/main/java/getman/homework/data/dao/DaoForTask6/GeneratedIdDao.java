package getman.homework.data.dao.DaoForTask6;

import getman.homework.data.pojo.forTask6.*;

public interface GeneratedIdDao {
    public String saveAndGetUUID(IdUuid uuid);
    public String saveAndGetIdGuid(IdGUID idGUID);
    public long saveAndGetHiLo(IdHiLo idHiLo);
    public long saveAndGetIdentity(IdIdentity idIdentity);
    public long saveAndGetIdSequence(IdSequence idSequence);
}
