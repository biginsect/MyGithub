package com.lipeng.mygithub.dao;

import java.util.Map;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.AbstractDaoSession;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.identityscope.IdentityScopeType;
import org.greenrobot.greendao.internal.DaoConfig;

import com.biginsect.easygithub.dao.AuthUser;

import com.lipeng.mygithub.dao.AuthUserDao;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.

/**
 * {@inheritDoc}
 * 
 * @see org.greenrobot.greendao.AbstractDaoSession
 */
public class DaoSession extends AbstractDaoSession {

    private final DaoConfig authUserDaoConfig;

    private final AuthUserDao authUserDao;

    public DaoSession(Database db, IdentityScopeType type, Map<Class<? extends AbstractDao<?, ?>>, DaoConfig>
            daoConfigMap) {
        super(db);

        authUserDaoConfig = daoConfigMap.get(AuthUserDao.class).clone();
        authUserDaoConfig.initIdentityScope(type);

        authUserDao = new AuthUserDao(authUserDaoConfig, this);

        registerDao(AuthUser.class, authUserDao);
    }
    
    public void clear() {
        authUserDaoConfig.clearIdentityScope();
    }

    public AuthUserDao getAuthUserDao() {
        return authUserDao;
    }

}
