package tests;

import com.fujitsu.fs.utils.PropertiesRetriever;
import org.dbunit.DBTestCase;
import org.dbunit.IDatabaseTester;
import org.dbunit.JdbcDatabaseTester;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.ITable;
import org.dbunit.dataset.xml.FlatXmlDataSet;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class IntegrationDB extends DBTestCase {

    private IDatabaseTester databaseTester;

    public IntegrationDB(String name) {
        super(name);
    }

    protected IDataSet getDataSet() throws Exception {
        return new FlatXmlDataSet(getClass().getResourceAsStream("/src/test/resources/users_test.xml"));
    }

    @BeforeClass
    public void setUp() throws Exception {
          databaseTester = new JdbcDatabaseTester(PropertiesRetriever.getDriver(),
                  PropertiesRetriever.getConnection_URL(),
                  PropertiesRetriever.getUSERNAME(),
                  PropertiesRetriever.getPassword());


    }



    @Test
    public void testFirst() throws Exception{

        //Fetch database data
        IDataSet databaseDataSet = getConnection().createDataSet();
        //fetch table from all data
        ITable actualTable = databaseDataSet.getTable("Users");

        Assert.assertTrue(actualTable.getRowCount() > 0);

    }

    @Test
    protected void testSecond() throws Exception {



    }

    @AfterClass
    public void tearDown() throws Exception {
        databaseTester.onTearDown();
    }

}
