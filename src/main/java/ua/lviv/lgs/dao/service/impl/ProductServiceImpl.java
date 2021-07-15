package ua.lviv.lgs.dao.service.impl;

import org.apache.log4j.Logger;
import ua.lviv.lgs.dao.ProductDao;
import ua.lviv.lgs.dao.impl.ProductDaoImpl;
import ua.lviv.lgs.dao.service.ProductService;
import ua.lviv.lgs.domain.Product;

import java.sql.SQLException;
import java.util.List;

public class ProductServiceImpl implements ProductService {
    private static Logger LOGGER = Logger.getLogger(ProductServiceImpl.class);
    private static ProductService productServiceImpl;
    private ProductDao productDao;

    public ProductServiceImpl(){
        try {
            productDao = new ProductDaoImpl();
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
    public static ProductService getProductService() {
        if(productServiceImpl == null) {
            productServiceImpl = new ProductServiceImpl();
        }
        return productServiceImpl;
    }
    @Override
    public Product create(Product product) throws SQLException {
        return productDao.create(product);
    }

    @Override
    public Product read(Integer id) throws SQLException {
        return productDao.read(id);
    }

    @Override
    public Product update(Product product) {
        return productDao.update(product);
    }

    @Override
    public void delete(Integer id) throws SQLException {
    productDao.delete(id);
    }

    @Override
    public List<Product> readAll() throws SQLException {
        return productDao.readAll();
    }
}
