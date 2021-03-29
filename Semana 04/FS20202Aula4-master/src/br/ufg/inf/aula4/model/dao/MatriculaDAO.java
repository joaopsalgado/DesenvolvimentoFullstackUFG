package br.ufg.inf.aula4.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.ufg.inf.aula4.app.DB;
import br.ufg.inf.aula4.ctrl.exception.MatriculaExection;
import br.ufg.inf.aula4.model.entities.Aluno;
import br.ufg.inf.aula4.model.entities.Matricula;
import br.ufg.inf.aula4.model.entities.Oferta;



public class MatriculaDAO {

	// CREATE
		public Matricula inserir(Matricula matricula) throws MatriculaExection {

			PreparedStatement st = null;
			try {
				Connection conn = DB.getConnection();
				st = conn.prepareStatement(
						"INSERT INTO tb_matricula (id_aluno, id_oferta) VALUES (?, ?)",
						Statement.RETURN_GENERATED_KEYS);
				
				st.setInt(1, matricula.getAluno().getIdAluno());
				st.setInt(2, matricula.getOferta().getIdOferta());

				int rowsAffected = st.executeUpdate();
				System.out.println("Linhas alteradas: " + rowsAffected);

				if (rowsAffected > 0) {

					ResultSet rs = st.getGeneratedKeys();
					if (rs.next()) {
						int id = rs.getInt(1);
						matricula.setIdMatricula(id);
					}
				}
			} catch (SQLException e) {
				throw new MatriculaExection(e.getMessage());
			}

			return matricula;
		}

		// READ
		public List<Matricula> buscaTodos() throws MatriculaExection {
			ResultSet rs = null;
			PreparedStatement st = null;
			List<Matricula> matriculas = new ArrayList<Matricula>();
			try {
				Connection conn = DB.getConnection();

				String query = "SELECT id_matricula, id_aluno, id_oferta FROM tb_matricula ORDER BY id_matricula ";
				st = conn.prepareStatement(query);

				rs = st.executeQuery();

				while (rs.next()) {
					matriculas.add(this.vo(rs));
				}

			} catch (SQLException e) {
				throw new MatriculaExection(e.getMessage());

			}

			return matriculas;
		}

		private Matricula vo(ResultSet rs) throws SQLException {
			Matricula matricula = new Matricula(null, null, null);
			matricula.setIdMatricula(rs.getInt("id_matricula"));
			matricula.setOferta(new Oferta ((rs.getInt("id_oferta")), null, null, null, null, null, null));
			matricula.setAluno(new Aluno (rs.getInt("id_aluno"), null, null, null, null));

			return matricula;
		}

		public Matricula buscaPorId(Integer id) throws MatriculaExection {
			Matricula matricula = null;

			ResultSet rs = null;
			PreparedStatement st = null;
			try {
				Connection conn = DB.getConnection();
				
				String query = "SELECT id_matricula, id_aluno, id_oferta FROM tb_matricula WHERE id_matricula = ? ";
				st = conn.prepareStatement(query);
				st.setInt(1, id);
				rs = st.executeQuery();

				if (rs.next()) {
					matricula = this.vo(rs);
				}

			} catch (SQLException e) {
				throw new MatriculaExection(e.getMessage());

			}

			return matricula;
		}

		// UPDATE

		public Matricula alterar(Matricula matricula) throws MatriculaExection {

			PreparedStatement st = null;
			try {
				Connection conn = DB.getConnection();

				
				String query = "UPDATE tb_matricula SET id_aluno = ?, id_oferta = ? WHERE id_matricula = ? ; ";
				st = (PreparedStatement) conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
				System.out.println("Entrou aq: " + matricula.toString());
				st.setInt(1, matricula.getAluno().getIdAluno());
				st.setInt(2, matricula.getOferta().getIdOferta());
				st.setInt(3, matricula.getIdMatricula());
				
				int rowsAffected = st.executeUpdate();
				System.out.println("Linhas alteradas: " + rowsAffected);

			} catch (SQLException e) {
				System.out.println("Linhas alteradas: " + 0);
				throw new MatriculaExection(e.getMessage());
			}

			return matricula;
		}

		// DELETE

		public void excluir(Integer id) throws MatriculaExection {
			PreparedStatement st = null;
			try {
				Connection conn = DB.getConnection();

				String query = " DELETE FROM tb_matricula WHERE id_matricula = ? ; ";
				st = conn.prepareStatement(query);
				st.setInt(1, id);
				int rowsAffected = st.executeUpdate();
				System.out.println("Linhas alteradas: " + rowsAffected);

			} catch (SQLException e) {
				throw new MatriculaExection(e.getMessage());
			}

	}
}
