package com.spring.core.jdbc.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;
import javax.swing.tree.RowMapper;
import javax.swing.tree.TreePath;

import org.springframework.jdbc.core.JdbcTemplate;

import com.spring.core.jdbc.model.Category;

public class CategoryDAOImp implements CategoryDAO {

	private DataSource dataSource;
	
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	@Override
	public void save(Category category) {
		
		//select save operation
				String query = " insert categories(id, name, description) values (?, ?, ?)";
				
				JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
				
				Object[] values = new Object[] {
					category.getId(),
					category.getName(),
					category.getDescription()
				};
				
				int out = jdbcTemplate.update(query, values);
				

				if(out != 0)
					System.out.println("Category saved with id = " + category.getId());
				else
					System.out.println("Category save failed with id =" + category.getId());
							
				
	}

	@Override
	public Category getById(int id) {
		String query = "select id, name, description from categories where id = ?";
		
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		
					
		//using RowMapper anonymous class, we can create a separate RowMapper for reuse
		@SuppressWarnings("deprecation")
		Category ct = jdbcTemplate.queryForObject(query,new Object[]{id}, new RowMapper<Category>(){

			@Override
			public Category mapRow(ResultSet rs, int rowNum)
					throws SQLException {
				Category c1 = new Category();
				c1.setId(rs.getInt("id"));
				c1.setName(rs.getString("name"));
				c1.setDescription(rs.getString("description"));
				return c1;
			}});
		
		return ct;
	}
	@Override
	public void update(Category category) {
		//update operation
				String query = "update categories set name=?, description=? where id=?";
				
				JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
				
				Object[] args = new Object[] {
						
						category.getName(),
						category.getDescription(),
						category.getId()
				};
				
				int out = jdbcTemplate.update(query, args);
				
				if(out !=0){
					System.out.println("Category updated with id="+category.getId());
				}else System.out.println("No category is found with id="+category.getId());
				
			}


	@Override
	public void deleteById(int id) {
		//delete operation
		String query = "delete from categories where id=?";
		
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		
		int out = jdbcTemplate.update(query, id);
		
		if(out !=0){
			System.out.println("Category data deleted with id="+id);
		}
		else 
			System.out.println("No category data is found with id="+id);
		
	}

	@Override
	public List<Category> getAll() {
		//selecting multiple Category
				String query = "select * from categories";
				
				JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
				
				List<Category> ctList = new ArrayList<Category>();

				List<Map<String,Object>> ctRows = jdbcTemplate.queryForList(query);
				
				for(Map<String,Object> ctRow : ctRows){
					Category ct = new Category();
					
					ct.setId(Integer.parseInt(String.valueOf(ctRow.get("id"))));
					
					ct.setName(String.valueOf(ctRow.get("name")));
					ct.setDescription(String.valueOf(ctRow.get("description")));
					
					ctList.add(ct);
	 }
				return ctList;
	}

	@Override
	public Category getByName(String name) {
		//select by name
		String query = "select * from categories where name = ?";
		
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		
					
		//using RowMapper anonymous class, we can create a separate RowMapper for reuse
		@SuppressWarnings("deprecation")
		Category ct = jdbcTemplate.queryForObject(query,new Object[]{name}, new RowMapper(){

			public Category mapRow(ResultSet rs, int rowNum)
					throws SQLException {
				Category c1 = new Category();
				c1.setId(rs.getInt("id"));
				c1.setName(rs.getString("name"));
				c1.setDescription(rs.getString("description"));
				return c1;
			}

			@Override
			public int[] getRowsForPaths(TreePath[] path) {
				// TODO Auto-generated method stub
				return null;
			}});
		
		return ct;
	}
	@Override
	public List<Category> getByNames(String substring) {
	
		String query = "select * from categories where name like 'To%'";
		
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		
		List<Category> ctList = new ArrayList<Category>();

		List<Map<String,Object>> ctRows = jdbcTemplate.queryForList(query);
		
		for(Map<String,Object> ctRow : ctRows){
			Category ct = new Category();
			
			ct.setId(Integer.parseInt(String.valueOf(ctRow.get("id"))));
			ct.setName(String.valueOf(ctRow.get("name")));
			ct.setDescription(String.valueOf(ctRow.get("description")));
			
			ctList.add(ct);
      }
		return ctList;
}

}
