package es.tdmsl.app.dao.scrollableTableModel;

/*
 * Created on 09-jul-2004
 */


/*
 * Created on 09-jul-2004
 * Modified on 06-oct-2010
 */

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;



/**
 * Table model based on scrollable cursors.
 *
 * @author Francesc Rosés
 * @modifiedBy Yoshiki
 */

public class ScrollableTableModel extends AbstractTableModel {
        ResultSet resultSet = null;
        Connection connection = null;
        List colNames = null;
        int rowCount = -1;
        List colClasses = null;
        Statement stmt = null;

        /**
         * <p>Default constructor.</p>
         */

        public ScrollableTableModel() {
                super();
        }


        /**
         * <p>Constructor with an opened <code>java.sql.Connection</code>
         * and an SQL query.</p>
         * @param con The opened <code>java.sql.Connection</code>
         * @param select The SQL query
         */
        public ScrollableTableModel(Connection con, String select) {
                this(con, select, null);
        }   // ScrollableTableModel(Connection, String)

        /**
         * <p>Constructor with an opened <code>java.sql.Connection</code>,
         * an SQL query and a column names <code>java.util.List</code>.</p>
         * @param con The opened <code>java.sql.Connection</code>
         * @param select The SQL query
         * @param colNames A <code>java.util.List</code> containing the column names
         */
        public ScrollableTableModel(Connection con, String select, List colNames) {
                if (con == null) {
                        throw new IllegalArgumentException(
                                        "La conexión pasada como parámetro es nula.");
                }
                try {
                        if (con.isClosed()) {
                                throw new IllegalArgumentException(
                                                "La conexión pasada como parámetro está cerrada.");
                        }
                } catch (SQLException e) {
                                throw new ScrollableTableModelException("Error obteniedo el estado de la conexión.", e);
                        }
                if (!supportsScrollInsensitive(con)) {
                        throw new IllegalArgumentException(
                                        "La conexión pasada como parámetro no sorpota SCROLL INSENSITIVE.");
                }
                this.connection = con;
                if (select == null) {
                        throw new IllegalArgumentException(
                                        "El query pasado como parámetro es nulo.");
                }
                if (select.trim().length() == 0) {
                        throw new IllegalArgumentException(
                                        "El query pasado como parámetro está vacío.");
                }
                stmt = null;
                ResultSet rs = null;
                try {
                        stmt = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
                                        ResultSet.CONCUR_READ_ONLY);
                } catch (SQLException e) {
                                throw new ScrollableTableModelException("Error creando la sentencia.", e);
                        }

                try {
                        rs = stmt.executeQuery(select);
                        this.resultSet = rs;
                } catch (SQLException e) {
                                throw new ScrollableTableModelException("Error ejecutando el query.", e);
                        }

                if (colNames == null || colNames.isEmpty()) {
                        fillColNames(rs);
                }
                else {
                        this.colNames = new ArrayList(colNames);
                        ResultSetMetaData rsmd = null;
                        try {
                                rsmd = resultSet.getMetaData();
                        } catch (SQLException e) {
                                        throw new ScrollableTableModelException("Error obteniendo el ResultSetMetaData.", e);
                                }

                        int colCount = -1;
                        try {
                                colCount = rsmd.getColumnCount();
                        } catch (SQLException e) {
                                        throw new ScrollableTableModelException("error obteniendo la cantidad de columnas.", e);
                                }
                        if (colCount != colNames.size()) {
                                throw new IllegalArgumentException("El parámetro colNames contiene un número no válido de columnas.");
                        }
                }
        }   // ScrollableTableModel(Connection, String, List)

        /**
         * <p>A constructor with a scrollable
         * <code>java.sql.ResultSet</code> as parameter.</p>
         * @param rs The scrollable <code>java.sql.ResultSet</code>
         */
        public ScrollableTableModel(ResultSet rs) {
                this(rs, null);
        }

        /**
         * <p>A constructor with a scrollable
         * <code>java.sql.ResultSet</code> as parameter.</p>
         * @param rs The scrollable <code>java.sql.ResultSet</code>
         * @param colNames A <code>java.util.List</code> containing
         * the column names
         */
        public ScrollableTableModel(ResultSet rs, List colNames) {
                if (rs == null) {
                        new IllegalArgumentException("El ResultSet pasado como parámetro es nulo.");
                }
                Statement s = null;
                try {
                        s = rs.getStatement();
                } catch (SQLException e) {
                                throw new ScrollableTableModelException("Error obteniendo la sentencia del ResultSet.", e);
                        }
                Connection c = null;
                try {
                        c = s.getConnection();
                } catch (SQLException e) {
                                throw new ScrollableTableModelException("Error obteniendo la conexión del ResultSet.", e);
                        }
                if (!supportsScrollInsensitive(c)) {
                        throw new IllegalArgumentException(
                                        "La conexión del ResultSet no soporta SCROLL INSENTIVE.");
                }

                if (colNames != null) {
                        ResultSetMetaData rmd = null;
                        try {
                                rmd = rs.getMetaData();
                        } catch (SQLException e) {
                                        throw new ScrollableTableModelException (
                                                "Error obteniendo el ResultSetMetaData.", e);
                        }

                        int colCount = -1;
                        try {
                                colCount = rmd.getColumnCount();
                        } catch (SQLException e) {
                                        throw new ScrollableTableModelException (
                                                        "Error obteniendo la cantidad de columnas.", e);
                                }
                        if (colNames.size() != colCount) {
                                throw new IllegalArgumentException("El parámetro ColNames contiene un número no válido de columnas.");
                        }

                        this.colNames = new ArrayList(colNames);
                }
                else {
                        fillColNames(rs);
                }

                this.resultSet = rs;
                this.stmt = s;
        }

        /**
         * <p>A constructor with a <code>java.sql.Statement</code>
         * containing a scrollable <code>java.sql.ResultSet</code>.</p>
         * @param stmt The <code>java.sql.Statement</code>
         * containing a scrollable <code>java.sql.ResultSet</code>
         */
        public ScrollableTableModel(Statement stmt) {
                this(stmt, null);
        }

        /**
         * <p>A constructor with a <code>java.sql.Statement</code>
         * containing a scrollable <code>java.sql.ResultSet</code>.</p>
         * @param stmt The <code>java.sql.Statement</code>
         * containing a scrollable <code>java.sql.ResultSet</code>
         * @param colNames A <code>java.util.List</code> containing
         * the column names
         */
        public ScrollableTableModel(Statement stmt, List colNames) {
                if (stmt == null) {
                        new IllegalArgumentException("La sentencia pasada como parámetro es nula.");
                }
                ResultSet rs = null;
                try {
                        rs = stmt.getResultSet();
                } catch (SQLException e) {
                                throw new ScrollableTableModelException("Error obteniendo el ResultSet de la sentencia.", e);
                        }
                Connection c = null;
                try {
                        c = stmt.getConnection();
                } catch (SQLException e) {
                                throw new ScrollableTableModelException("Error obteniendo la conexión de la sentencia.", e);
                        }
                if (!supportsScrollInsensitive(c)) {
                        throw new IllegalArgumentException(
                                        "La conexión de la sentencia no soporta SCROLL INSENSITIVE.");
                }

                if (colNames != null) {
                        ResultSetMetaData rmd = null;
                        try {
                                rmd = rs.getMetaData();
                        } catch (SQLException e) {
                                        throw new ScrollableTableModelException (
                                                "Error obteniendo el ResultSetMetaData.", e);
                        }

                        int colCount = -1;
                        try {
                                colCount = rmd.getColumnCount();
                        } catch (SQLException e) {
                                        throw new ScrollableTableModelException (
                                                        "Error obteniendo la cantidad de columndas.", e);
                                }
                        if (colNames.size() != colCount) {
                                throw new IllegalArgumentException("El parámetro colCount contiene un número no válido de columnas.");
                        }

                        this.colNames = new ArrayList(colNames);
                }
                else {
                        fillColNames(rs);
                }

                this.resultSet = rs;
                this.stmt = stmt;
        }


        /**
         * <p>Fills the <code>colNames</code> property from the
         * <code>resultSet</code> if this property is null
         * using the current <code>resultSet</code>.</p>
         * @see ScrollableTableModel#fillColNames(ResultSet)
         */
        private void fillColNames() {
                fillColNames(this.resultSet);
        }   // fillColNames()

        /**
         * <p>Fills the <code>colNames</code> property from the
         * <code>resultSet</code> if this property is null.</p>
         * @param resultSet
         */
        private void fillColNames(ResultSet resultSet) {
                if (resultSet == null) {
                        throw new IllegalArgumentException("Error llenando los nombres de las columnas: El parámetro ResultSet es nulo.");
                }
                if (this.colNames == null) {
                        ResultSetMetaData rsmd = null;
                        try {
                                rsmd = resultSet.getMetaData();
                                int colCount = rsmd.getColumnCount();
                                if (colCount == 0) {
                                        // DO NOTHING!
                                }
                                this.colNames = new ArrayList();
                                for (int i = 0; i < colCount; i++) {
                                        String colLabel = rsmd.getColumnLabel(i+1);
                                        this.colNames.add(colLabel);
                                }
                        } catch (SQLException e) {
                                        e.printStackTrace();
                                        throw new ScrollableTableModelException("Error obteniendo el ResultSetMetadata", e);
                                }
                }
        }   // fillColNames(ResultSet)

        /**
         * @return The number of columns in this model.
         */
        public int getColumnCount() {
                return colNames.size();
        }

        /**
         * @return The number of rows in this model.
         */
        public int getRowCount() {
                if (this.rowCount == -1) {
                        try {
                                resultSet.last();
                                this.rowCount = resultSet.getRow();
                        } catch (SQLException e) {
                                        throw new ScrollableTableModelException("Error de desplazamiento a la última fila.", e);
                                }
                }

                return rowCount;
        }

        /**
         * <p>Returns the value for the cell at columnIndex
         * and rowIndex</p>
         * @param rowIndex The row whose value is to be queried
         * @param columnIndex The column whose value is to be queried
         * @return The value Object at the specified cell
         */
        public Object getValueAt(int rowIndex, int columnIndex) {
                int rowNdx = rowIndex + 1;
                int colNdx = columnIndex + 1;
                try {
                        resultSet.absolute(rowNdx);
                        return resultSet.getObject(colNdx);
                } catch (SQLException e) {
                                throw new ScrollableTableModelException("Error obteniendo el valor en " + rowIndex + ", " + columnIndex, e);
                        }
        }

        /**
         * @return The column name
         */
        public String getColumnName(int column) {
                return (String)colNames.get(column);
        }

        /**
         * <p>Returns the most specific superclass for all the cell values in the column.</p>
         * @param columnIndex The index of the column
         * @return The common ancestor class of the object values in the model.
         */
        public Class getColumnClass(int columnIndex) {
                if (colClasses == null) {
                        colClasses = new ArrayList();
                        ResultSetMetaData md = null;
                        try {
                                md = resultSet.getMetaData();
                                int colCount = md.getColumnCount();
                                for (int i = 0; i < colCount; i++) {
                                        try {
                                                String className = md.getColumnClassName(i + 1);
                                                Class c = Class.forName(className);
                                                colClasses.add(c);
                                        } catch (ClassNotFoundException e) {
                                                throw new ScrollableTableModelException("Error obteniendo las clases de la columna.", e);
                                                }
                                }   // for i
                        } catch (SQLException e) {
                                        throw new ScrollableTableModelException("Error obteniendo las clases de la columna.", e);
                                }
                }
                Class c = (Class)colClasses.get(columnIndex);

                return c;
        }

        /**
         * <p>Verifies if the current DBM supports the scroll insensitive feature.</p>
         * @param con The opened connection.
         * @return <code>true</code> if the DMB supports the scroll insensitive
         * feature or <code>false</code> if not.
         */
        private boolean supportsScrollInsensitive(Connection con) {
                DatabaseMetaData md = null;
                try {
                        md = con.getMetaData();
                } catch (SQLException e) {
                                throw new ScrollableTableModelException("Error obteniendo la MetaData de la base de datos.", e);
                        }
                try {
                        return md.supportsResultSetType(ResultSet.TYPE_SCROLL_INSENSITIVE);
                } catch (SQLException e) {
                                throw new ScrollableTableModelException("Error obteniendo información de la MetaData de la base de datos.", e);
                        }
        } // supportsScrollInsensitive()

        /**
         * Closes the <code>java.sql.Statement</code> used to
         * execute the query.
         */
        public void destroy() {
                if (stmt != null) {
                        try {
                                stmt.close();
                        } catch (SQLException e) {
                                }
                }
                stmt = null;
        }

}