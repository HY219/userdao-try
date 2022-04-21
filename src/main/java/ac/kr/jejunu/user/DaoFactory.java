package ac.kr.jejunu.user;

public class DaoFactory {
    public UserDao getUserDao() {
        return new UserDao(getConnectionMaker());
    }
    private JejuConnectionMaker getConnectionMaker(){
        return new JejuConnectionMaker();
    }
}
