package yu.service;

import yu.domain.FileNodeVO;

import java.util.List;


public interface DirService {

    long getParent(long child);

	List<FileNodeVO> getChildren(long parentId, String username);

    boolean existDir(long parentId, String filename, String username, boolean dir);

    boolean addDir(long parentId, String username, String filename, boolean dir, long size);

    boolean checkOwner(long id, String username);


    void removeDir(String ids, String username);

    long getId(long parentId, String username, String filename);

    //void renameDir(String newname, String id);
}