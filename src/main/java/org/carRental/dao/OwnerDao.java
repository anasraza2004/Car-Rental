package org.carRental.dao;

import org.carRental.domain.Owner;
import org.carRental.mapper.OwnerMapper;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import static org.carRental.dao.OwnerSqlQueryConstant.*;


public class OwnerDao extends BaseDAO implements ICrud<Owner> {
    private final OwnerMapper ownerMapper = new OwnerMapper();

    @Override
    public void post(Owner obj) {
        try {
            PreparedStatement ps = conn.prepareStatement(POST);
            ps.setString(1, obj.getOwner_name());
            ps.setInt(2, obj.getOwner_cnic());
            ps.setInt(3, obj.getOwner_comission());
            ps.setString(4, obj.getOwner_address());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Owner> getAll() {
        try {
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery(GET_ALL_FROM_OWNER);
            return ownerMapper.resultToList(rs);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Owner getById(Integer id) {
        try {
            PreparedStatement ps = conn.prepareStatement(GET_BY_ID);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            return ownerMapper.resultToObject(rs);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Owner> getByName(String name) {
        try {
            PreparedStatement ps = conn.prepareStatement("select * from vehicle_owner where owner_name like '%" + name + "%'");
            ResultSet rs = ps.executeQuery();
            return ownerMapper.resultToList(rs);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(Owner obj, Integer id) {
        try {
            PreparedStatement ps = conn.prepareStatement(UPDATE_BY_ID);
            ps.setString(1, obj.getOwner_name());
            ps.setInt(2, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(Integer id) {
        try {
            PreparedStatement ps = conn.prepareStatement(DELETE);
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
