
 import java.sql.Connection;
		    import java.sql.DriverManager;
		    import java.sql.ResultSet;
		    import java.sql.SQLException;
		    import java.sql.Statement;
		    import java.sql.PreparedStatement;
		
		   

		    public class Main {
		        public static void main(String[] args) {
		            try {
		            
		                Class.forName("com.mysql.cj.jdbc.Driver");

		              
		                String url = "jdbc:mysql://localhost:3306/tp_jdbc";
		                String user = "root";
		                String password = "";
		                Connection conn = DriverManager.getConnection(url, user, password);

		                
		              
		                System.out.println("----- Liste des étudiants -----");
		                selectAllEtudiants(conn);

		                System.out.println("\n----- Insertion d'un nouvel étudiant -----");
		                insertEtudiant(conn, "ktiri", "marieme", 25);

		           
		                System.out.println("\n----- Mise à jour des informations d'un étudiant -----");
		                updateEtudiant(conn, 1, "ouijjane", "rahma", 30);

		              
		                System.out.println("\n----- Liste des étudiants après la mise à jour -----");
		                selectAllEtudiants(conn);

		                System.out.println("\n----- Suppression d'un étudiant -----");
		                deleteEtudiant(conn, 2); // Replace 2 with the actual ID you want to delete

		          
		                System.out.println("\n----- Liste des étudiants après la suppression -----");
		                selectAllEtudiants(conn);

		            } catch (ClassNotFoundException | SQLException e) {
		                e.printStackTrace();
		            }
		        }


		        private static void selectAllEtudiants(Connection conn) throws SQLException {
		           
		            try (Statement statement = conn.createStatement()) {
		               
		                String query = "SELECT * FROM etudiants";

		              
		                try (ResultSet resultSet = statement.executeQuery(query)) {
		                  
		                    while (resultSet.next()) {
		                        int id = resultSet.getInt("id");
		                        String nom = resultSet.getString("nom");
		                        String prenom = resultSet.getString("prenom");
		                        int age = resultSet.getInt("age");

		                     
		                        System.out.println("ID: " + id + ", Nom: " + nom + ", Prénom: " + prenom + ", Âge: " + age);
		                    }
		                }
		            }
		        }
		        private static void insertEtudiant(Connection conn, String nom, String prenom, int age) throws SQLException {
		            
		            try (PreparedStatement preparedStatement = conn.prepareStatement(
		                    "INSERT INTO etudiants (nom, prenom, age) VALUES (?, ?, ?)")) {
		                
		              
		                preparedStatement.setString(1, nom);
		                preparedStatement.setString(2, prenom);
		                preparedStatement.setInt(3, age);

		              
		                int rowsAffected = preparedStatement.executeUpdate();

		              
		                if (rowsAffected > 0) {
		                    System.out.println("Nouvel étudiant inséré avec succès.");
		                } else {
		                    System.out.println("Échec de l'insertion de l'étudiant.");
		                }
		            }
		        }
		    
		    private static void updateEtudiant(Connection conn, int id, String nouveauNom, String nouveauPrenom, int nouvelAge) throws SQLException {
		       
		        try (PreparedStatement preparedStatement = conn.prepareStatement(
		                "UPDATE etudiants SET nom = ?, prenom = ?, age = ? WHERE id = ?")) {

		          
		            preparedStatement.setString(1, nouveauNom);
		            preparedStatement.setString(2, nouveauPrenom);
		            preparedStatement.setInt(3, nouvelAge);
		            preparedStatement.setInt(4, id);

		          
		            int rowsAffected = preparedStatement.executeUpdate();

		        
		            if (rowsAffected > 0) {
		                System.out.println("Informations de l'étudiant mises à jour avec succès.");
		            } else {
		                System.out.println("Échec de la mise à jour des informations de l'étudiant.");
		            }
		        }
		    }
		    private static void deleteEtudiant(Connection conn, int id) throws SQLException {
		      
		        try (PreparedStatement preparedStatement = conn.prepareStatement(
		                "DELETE FROM etudiants WHERE id = ?")) {

		            preparedStatement.setInt(1, id);

		         
		            int rowsAffected = preparedStatement.executeUpdate();

		            if (rowsAffected > 0) {
		                System.out.println("Étudiant supprimé avec succès.");
		            } else {
		                System.out.println("Aucun étudiant trouvé avec l'ID fourni.");
		            }
		        }
		    }
		    }
		    
		    
