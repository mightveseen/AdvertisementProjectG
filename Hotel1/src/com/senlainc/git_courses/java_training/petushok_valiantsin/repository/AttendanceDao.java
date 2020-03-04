package com.senlainc.git_courses.java_training.petushok_valiantsin.repository;

import com.senlainc.git_courses.java_training.petushok_valiantsin.api.repository.IAttendanceDao;
import com.senlainc.git_courses.java_training.petushok_valiantsin.injection.annotation.DependencyClass;
import com.senlainc.git_courses.java_training.petushok_valiantsin.injection.annotation.DependencyComponent;
import com.senlainc.git_courses.java_training.petushok_valiantsin.injection.annotation.DependencyPrimary;
import com.senlainc.git_courses.java_training.petushok_valiantsin.model.Attendance;
import com.senlainc.git_courses.java_training.petushok_valiantsin.utility.base_conection.ConnectionManager;
import com.senlainc.git_courses.java_training.petushok_valiantsin.utility.exception.DaoException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@DependencyClass
@DependencyPrimary
public class AttendanceDao implements IAttendanceDao {
    @DependencyComponent
    private ConnectionManager connectionManager;

    @Override
    public Attendance create(Attendance attendance) {
        final String SQL_INSERT_QUARY = "INSERT INTO `Attendance`(`name`, `section`, `price`)\n" + "VALUES (?, ?, ?);";
        try (final PreparedStatement statement = connectionManager.getStatment(SQL_INSERT_QUARY)) {
            statement.setString(1, attendance.getName());
            statement.setString(2, attendance.getSection());
            statement.setDouble(3, attendance.getPrice());
            statement.execute();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return attendance;
    }

    @Override
    public void delete(Integer index) {
        final String SQL_DELETE_QUARY = "DELETE FROM `Attendance`\n" + "WHERE `id` = ?;";
        try (final PreparedStatement statement = connectionManager.getStatment(SQL_DELETE_QUARY)) {
            statement.setInt(1, index);
            statement.execute();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public void update(Attendance attendance) {
        final String SQL_UPDATE_QUARY = "UPDATE `Attendance`\n"
                + "SET `name` = ?, `section` = ?, `price` = ?\n"
                + "WHERE `id` = ?;";
        try (final PreparedStatement statement = connectionManager.getStatment(SQL_UPDATE_QUARY)) {
            statement.setString(1, attendance.getName());
            statement.setString(2, attendance.getSection());
            statement.setDouble(3, attendance.getPrice());
            statement.setInt(4, attendance.getId());
            statement.execute();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public List<Attendance> readAll() {
        final String SQL_READ_ALL_QUARY = "SELECT *\n" + "FROM `Attendance`;";
        final List<Attendance> attendanceList = new ArrayList<>();
        try (final PreparedStatement statement = connectionManager.getStatment(SQL_READ_ALL_QUARY)) {
            final ResultSet result = statement.executeQuery();
            while (result.next()) {
                final Attendance attendance = new Attendance(result.getString(2), result.getString(3), result.getDouble(4));
                attendance.setId(result.getInt(1));
                attendanceList.add(attendance);
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return attendanceList;
    }

    @Override
    public Attendance read(Integer index) {
        final String SQL_READ_QUARY = "SELECT *\n" + "FROM `Attendance`\n" + "WHERE `id` = ?;";
        try (final PreparedStatement statement = connectionManager.getStatment(SQL_READ_QUARY)) {
            statement.setInt(1, index);
            final ResultSet result = statement.executeQuery();
            if (result.next()) {
                final Attendance attendance = new Attendance(result.getString(2), result.getString(3), result.getDouble(4));
                attendance.setId(result.getInt(1));
                return attendance;
            }
            throw new DaoException();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }
}
