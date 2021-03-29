package br.ufg.inf.aula4.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.ufg.inf.aula4.app.DB;
import br.ufg.inf.aula4.ctrl.exception.CursoExection;
import br.ufg.inf.aula4.model.entities.Curso;


public class CursoDAO {

	public Curso inserir(Curso curso) throws CursoExection {
		PreparedStatement st = null;
		try {
			Connection conn = DB.getConnection();
			st = (PreparedStatement) conn.prepareStatement(
					"INSERT INTO tb_curso " + "(nm_curso)" + "VALUES (?)",
					Statement.RETURN_GENERATED_KEYS);
			st.setString(1,  curso.getNmCurso());
			int rowsAffected = st.executeUpdate();
			System.out.println("Linhas alteradas: " + rowsAffected);
			if (rowsAffected > 0) {

				ResultSet rs = st.getGeneratedKeys();
				if (rs.next()) {
					int id = rs.getInt(1);
					curso.setIdCurso(id);
				}
			}
		} catch (SQLException e) {
			throw new CursoExection(e.getMessage());
		}
		return curso;
	}

	public List<Curso> buscaTodos() throws CursoExection {
		ResultSet rs = null;
		PreparedStatement st = null;
		List<Curso> cursos = new ArrayList<Curso>();
		try {
			Connection conn = DB.getConnection();
			String query = "SELECT id_curso, nm_curso FROM tb_curso ORDER BY id_curso ";
			st = conn.prepareStatement(query);
			rs = st.executeQuery();
			while (rs.next()) {
				cursos.add(this.vo(rs));
			}
		} catch (SQLException e) {
			throw new CursoExection(e.getMessage());
		}
		return cursos;
	}

	private Curso vo(ResultSet rs) throws SQLException {
		Curso curso = new Curso();
		curso.setIdCurso(rs.getInt("id_curso"));
		curso.setNmCurso(rs.getString("nm_curso"));
		return curso;
	}

	public Curso buscaPorId(Integer id) throws CursoExection {
		Curso curso = null;
		ResultSet rs = null;
		PreparedStatement st = null;
		try {
			Connection conn = DB.getConnection();
			String query = "SELECT id_curso, nm_curso FROM tb_curso WHERE id_curso = ? ";
			st = conn.prepareStatement(query);
			st.setInt(1, id);
			rs = st.executeQuery();
			if (rs.next()) {
				curso = this.vo(rs);
			}
		} catch (SQLException e) {
			throw new CursoExection(e.getMessage());
		}
		return curso;
	}

	public Curso alterar(Curso curso) throws CursoExection {
		PreparedStatement st = null;
		try {
			Connection conn = DB.getConnection();
			String query = "UPDATE tb_curso SET nm_curso = ? WHERE id_curso = ? ; ";
			st = (PreparedStatement) conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
			st.setString(1, curso.getNmCurso());
			st.setInt(2, curso.getIdCurso());
			int rowsAffected = st.executeUpdate();
			System.out.println("Linhas alteradas: " + rowsAffected);
		} catch (SQLException e) {
			throw new CursoExection(e.getMessage());
		}
		return curso;
	}

	public void excluir(Integer id) throws CursoExection {
		PreparedStatement st = null;
		try {
			Connection conn = DB.getConnection();
			String query = " DELETE FROM tb_curso WHERE id_curso = ? ; ";
			st = (PreparedStatement) conn.prepareStatement(query);
			st.setInt(1, id);
			int rowsAffected = st.executeUpdate();
			System.out.println("Linhas alteradas: " + rowsAffected);

		} catch (SQLException e) {
			throw new CursoExection(e.getMessage());
		}
	}
}
