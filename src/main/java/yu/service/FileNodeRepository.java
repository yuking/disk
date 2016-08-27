package yu.service;

import org.springframework.data.repository.Repository;
import yu.domain.FileNode;

import java.util.List;

interface FileNodeRepository extends Repository<FileNode, Long> {

    List<FileNode> findByParentId(long parentId);

    List<FileNode> findByParentIdAndUsername(long parentId, String username);

    FileNode findById(long id);

    FileNode findByParentIdAndFilenameAndDir(long parentId, String filename, boolean dir);

    FileNode findByParentIdAndUsernameAndFilenameAndDir(long parentId, String username, String filename, boolean dir);

    FileNode save(FileNode fileNode);

    void deleteById(long id);

//    @Modifying
//    @Query("update file_node fn set fn.name = ?1 where fn.id = ?2")
//    int setFixedNameFor(String name, long id);
}
