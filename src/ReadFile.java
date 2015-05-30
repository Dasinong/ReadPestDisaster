import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.LineIterator;


public class ReadFile {
	
	public static Connection getConnection(){
		Connection conn = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/jsp_db?characterEncoding=utf8","root","");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}
	
	public static void createTable(){
		Connection conn = getConnection();
		
		String pestdisaster = 	"CREATE TABLE pestdisaster"+
								"(pestdisasterid INTEGER not null, "+
								"type VARCHAR(50), "+
								"pestname VARCHAR(50), "+
								"alias VARCHAR(50), "+
								"crop VARCHAR(50), "+
								"impactedarea TEXT, "+
								"color TEXT, "+
//								"dataerrortype TINYINT,"+
								"PRIMARY KEY (pestdisasterid) ) "+
								"DEFAULT CHARSET = UTF8;";
		String solution = 		"CREATE TABLE solution"+
								"(solutionid INTEGER not null, "+
								"solution TEXT not null, "+
								"crop VARCHAR(50), "+
								"pestdisasterid INTEGER not null, "+
								"cpproductsolution BOOL, "+
				//				"dataerrortype TINYINT,"+
								"PRIMARY KEY (solutionid) ) "+
								"DEFAULT CHARSET = UTF8;";
		
		try {
			Statement st = conn.createStatement();
			st.executeUpdate(pestdisaster);
			st.executeUpdate(solution);
			st.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static PreparedStatement[] getSql(PestDisaster pest, Connection conn){
		int solutionSize = 0;
		if (!pest.getSolutions().isEmpty()) {
			solutionSize = pest.getSolutions().size();
		}
		
		PreparedStatement query[] = new PreparedStatement[solutionSize+1]; // sql[0] for pestdisaster; sql[1,] for solution
//		sql[0] = "INSERT INTO pestdisaster(pestdisasterid,type,pestname,alias,crop,impactedarea,color)"+
//				"VALUES('"+pest.getPestDisasterID()+"','"+pest.getType()+"','"+pest.getPestName()+
//				"','"+pest.getAlias()+"','"+pest.getCrop()+"','"+pest.getImpactedArea()+
//				"','"+pest.getColor()+"')";
		String sql = "INSERT INTO pestdisaster VALUES (?,?,?,?,?,?,?)";
		try {
			query[0] = conn.prepareStatement(sql);
			query[0].setLong(1, pest.getPestDisasterID());
			query[0].setString(2, pest.getType());
			query[0].setString(3, pest.getPestName());
			query[0].setString(4, pest.getAlias());
			query[0].setString(5, pest.getCrop());
			query[0].setString(6, pest.getImpactedArea());
			query[0].setString(7, pest.getColor());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		ArrayList<Solution> solutions = pest.getSolutions();

		for (int i = 0; i < solutions.size(); i++) {
			Solution solution = solutions.get(i);
			
//			sql[1+i] = "INSERT INTO solution(solutionid,solution,crop,pestdisasterid,cpproductsolution)"+
//					"VALUES('"+solution.getSolutionID()+"','"+solution.getSolution()+"','"+solution.getCrop()+
//					"','"+solution.getPestDisasterID()+"',"+solution.isCpProductSolution()+")";
			sql = "INSERT INTO solution VALUES (?,?,?,?,?)";
			try {
				query[i+1] = conn.prepareStatement(sql);
				query[i+1].setLong(1, solution.getSolutionID());
				query[i+1].setString(2, solution.getSolution());
				query[i+1].setString(3, solution.getCrop());
				query[i+1].setLong(4, solution.getPestDisasterID());
				query[i+1].setBoolean(5, solution.isCpProductSolution());
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}
		
		return query;

	}

	public static void main(String[] args) throws IOException {
		createTable();
		
		Connection conn = getConnection();
		
		String separator = "--------------------";
		File file = new File("bingchonghai_all.txt");
		LineIterator it = FileUtils.lineIterator(file, "UTF-8");

		ArrayList<String> lines = new ArrayList<String>();
		int count = 0;
		long pestDisasterID = 1;
		long initialSolutionID = 1;

		try {
			while (it.hasNext()) {
				String line = (String) it.next();
				lines.add(line);

				if (line.equals(separator)) { // process block by block, which is separated by "--------------------"

					PestDisaster pest = new PestDisaster(pestDisasterID, initialSolutionID, lines); // create a pest
					// 写入数据库 同时写入 pestdisaster和solution两张表
					PreparedStatement query[] = getSql(pest,conn);
					
					for (int i = 0; i < query.length; i++) {
						query[i].executeUpdate();
					}
					
					pestDisasterID++;
					initialSolutionID += pest.getSolutions().size();
					count = count+1;
					lines.clear();

				}

			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(pestDisasterID);
		} finally {
			it.close();
		}
		
		try {
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}



}
