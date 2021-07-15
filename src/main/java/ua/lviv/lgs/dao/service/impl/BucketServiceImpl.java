package ua.lviv.lgs.dao.service.impl;

import org.apache.log4j.Logger;
import ua.lviv.lgs.dao.BucketDao;
import ua.lviv.lgs.dao.impl.BucketDaoImpl;
import ua.lviv.lgs.dao.service.BucketService;
import ua.lviv.lgs.domain.Bucket;

import java.sql.SQLException;
import java.util.List;

public class BucketServiceImpl implements BucketService {
    private static Logger LOGGER = Logger.getLogger(BucketServiceImpl.class);
    private static BucketService bucketServiceImpl;
    private BucketDao bucketDao;

    public BucketServiceImpl(){
        try {
            bucketDao = new BucketDaoImpl();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }
    public static BucketService getBucketService() {
        if(bucketServiceImpl == null) {
            bucketServiceImpl = new BucketServiceImpl();
        }
        return bucketServiceImpl;
    }


    @Override
    public Bucket create(Bucket bucket) throws SQLException {
        return bucketDao.create(bucket);
    }

    @Override
    public Bucket read(Integer id) throws SQLException {
        return bucketDao.read(id);
    }

    @Override
    public Bucket update(Bucket bucket) {
        return bucketDao.update(bucket);
    }

    @Override
    public void delete(Integer id) throws SQLException {
    bucketDao.delete(id);
    }

    @Override
    public List<Bucket> readAll() throws SQLException {
        return bucketDao.readAll();
    }
}
