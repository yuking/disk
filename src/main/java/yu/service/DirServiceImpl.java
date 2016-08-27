package yu.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import yu.domain.FileNode;
import yu.domain.FileNodeVO;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class DirServiceImpl implements DirService {

    @Resource
    private FileNodeRepository fileNodeRepository;

    @Override
    public List<FileNodeVO> getChildren(long parentId, String username) {
        List<FileNode> fileNodes;
        if (parentId == 0) {
            fileNodes = fileNodeRepository.findByParentIdAndUsername(0, username);
        } else {
            fileNodes = fileNodeRepository.findByParentId(parentId);
        }
        List<FileNodeVO> fileNodeVOs = new ArrayList<>();
        for (FileNode f : fileNodes) {
            fileNodeVOs.add(f.getVO());
        }
        return fileNodeVOs;
    }

    @Override
    public long getParent(long child) {
        if (child == 0) return 0;
        return fileNodeRepository.findById(child).getParentId();
    }

    @Override
    public boolean existDir(long parentId, String filename, String username, boolean dir) {
        if (parentId == 0) {
            return fileNodeRepository.findByParentIdAndUsernameAndFilenameAndDir(parentId, username, filename, dir) != null;
        }
        return fileNodeRepository.findByParentIdAndFilenameAndDir(parentId, filename, dir) != null;
    }

    @Override
    public boolean addDir(long parentId, String username, String filename, boolean dir, long size) {

        FileNode fn = new FileNode();
        fn.setUsername(username);
        fn.setFilename(filename);
        fn.setParentId(parentId);
        fn.setDir(dir);
        fn.setSize(size);
        Date date = new Date();
        fn.setCreateTime(date);
        fileNodeRepository.save(fn);
        return true;
    }

    @Override
    public boolean checkOwner(long id, String username) {
        if (id == 0) {
            return true;
        }
        FileNode f = fileNodeRepository.findById(id);
        return f != null && f.getUsername().equals(username);
    }

    @Override
    @Transactional
    public void removeDir(String ids, String username) {
        String[] idArray = ids.split(",");
        for (String idStr : idArray) {
            long id = Long.parseLong(idStr);
            if (checkOwner(id, username))
                removeFileNode(id);
        }
    }

    @Override
    public long getId(long parentId, String username, String filename) {
        if (parentId == 0) {
            return fileNodeRepository.findByParentIdAndUsernameAndFilenameAndDir(parentId, username, filename, false).getId();
        }
        return fileNodeRepository.findByParentIdAndFilenameAndDir(parentId, filename, false).getId();
    }

    private void removeFileNode(long id) {
        List<FileNode> fileNodeList = fileNodeRepository.findByParentId(id);
        for (FileNode f : fileNodeList) {
            removeFileNode(f.getId());
        }
        fileNodeRepository.deleteById(id);
    }

//    @Override
//    public void renameDir(String newname, String id) {
//        fileNodeRepository.setFixedNameFor(newname, Long.parseLong(id));
//    }
}
