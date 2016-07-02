/** 
 *  
 */
package cn.mmb.b2b.user.infrastructure;
  
import java.sql.CallableStatement;  
import java.sql.PreparedStatement;  
import java.sql.ResultSet;  
import java.sql.SQLException;  
  
import org.apache.ibatis.type.JdbcType;  
import org.apache.ibatis.type.TypeHandler;  
  
/** 
 * @author  
 * java中的boolean和jdbc中的char之间转换;true-Y;false-N 
 */  
public class BooleanTypeHandler implements TypeHandler {

    @Override
    public void setParameter(PreparedStatement preparedStatement,
                             int i, Object o, JdbcType jdbcType) throws SQLException {
        Boolean b = (Boolean) o;
        String value = b == true ? "Y" : "N";
        preparedStatement.setString(i, value);
    }

    @Override
    public Object getResult(ResultSet resultSet, String s) throws SQLException {
        String str = resultSet.getString(s);
        Boolean rt = Boolean.FALSE;
        if (str != null && str.equalsIgnoreCase("Y")){
            rt = Boolean.TRUE;
        }
        return rt;
    }

    @Override
    public Object getResult(ResultSet resultSet, int i) throws SQLException {
        String str = resultSet.getString(i);
        Boolean rt = Boolean.FALSE;
        if (str.equalsIgnoreCase("Y")){
            rt = Boolean.TRUE;
        }
        return rt;
    }

    @Override
    public Object getResult(CallableStatement callableStatement, int i) throws SQLException {
        Boolean b = callableStatement.getBoolean(i);
        return b == true ? "Y" : "N";
    }

}
