package vn.smartdev.javatrainingpractice.springmvcpractice.dao.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.security.authentication.LockedException;
import org.springframework.stereotype.Repository;
import vn.smartdev.javatrainingpractice.springmvcpractice.dao.UserDetailsDao;
import vn.smartdev.javatrainingpractice.springmvcpractice.entities.UserAttempts;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class UserDetailsDaoImpl extends JdbcDaoSupport implements UserDetailsDao {
    private static final String SQL_USERS_UPDATE_LOCKED = "UPDATE user SET account_non_locked = ? WHERE username = ?";
    private static final String SQL_USERS_COUNT = "SELECT count(*) FROM user WHERE username = ?";

    private static final String SQL_USER_ATTEMPTS_GET = "SELECT * FROM user_attempts WHERE username = ?";
    private static final String SQL_USER_ATTEMPTS_INSERT = "INSERT INTO user_attempts (username, attempts) VALUES(?,?)";
    private static final String SQL_USER_ATTEMPTS_UPDATE_ATTEMPTS = "UPDATE user_attempts SET attempts = attempts + 1 WHERE username = ?";
    private static final String SQL_USER_ATTEMPTS_RESET_ATTEMPTS = "UPDATE user_attempts SET attempts = 0 WHERE username = ?";

    private static final int MAX_ATTEMPTS = 3;

    @Autowired
    private DataSource dataSource;

    @PostConstruct
    private void initialize() {
        setDataSource(dataSource);
    }


    public void updateFailAttempts(String username) {

        UserAttempts user = getUserAttempts(username);
        if (user == null) {
            if (isUserExists(username)) {
                // if no record, insert a new
                getJdbcTemplate().update(SQL_USER_ATTEMPTS_INSERT, new Object[] { username, 1 });
            }
        } else {

            if (isUserExists(username)) {
                // update attempts count, +1
                getJdbcTemplate().update(SQL_USER_ATTEMPTS_UPDATE_ATTEMPTS, new Object[] { username });
            }

            if (user.getAttempts() + 1 >= MAX_ATTEMPTS) {
                // locked user
                getJdbcTemplate().update(SQL_USERS_UPDATE_LOCKED, new Object[] { false, username });
                // throw exception
                throw new LockedException("User Account is locked!");
            }

        }

    }

    public UserAttempts getUserAttempts(String username) {

        try {

            UserAttempts userAttempts = getJdbcTemplate().queryForObject(SQL_USER_ATTEMPTS_GET,
                    new Object[] { username }, new RowMapper<UserAttempts>() {
                        public UserAttempts mapRow(ResultSet rs, int rowNum) throws SQLException {

                            UserAttempts user = new UserAttempts();
                            user.setId(rs.getInt("id"));
                            user.setUsername(rs.getString("username"));
                            user.setAttempts(rs.getInt("attempts"));
                            return user;
                        }

                    });
            return userAttempts;

        } catch (EmptyResultDataAccessException e) {
            return null;
        }

    }

    public void resetFailAttempts(String username) {

        getJdbcTemplate().update(SQL_USER_ATTEMPTS_RESET_ATTEMPTS, new Object[] { username });

    }

    private boolean isUserExists(String username) {

        boolean result = false;

        int count = getJdbcTemplate().queryForObject(SQL_USERS_COUNT, new Object[] { username }, Integer.class);
        if (count > 0) {
            result = true;
        }

        return result;
    }
}
